package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

import model.BombaSuper;
import model.BomberMan;
import model.Elemento;
import model.TableroModel;

@SuppressWarnings("deprecation")
public class TableroView extends JPanel implements Observer{
	
	//ATRIBUTOS
    private int ancho = 17;
    private int alto = 11;
    private JLabel[][] labels;
    private TableroController controlador;

    // CONSTRUCTORA
    public TableroView() {
    	setLayout(new GridLayout(alto, ancho, 1, 1));
        labels = new JLabel[ancho][alto];
        this.setBackground(Color.blue);
        inicializarVista();
        TableroModel.getMiTablero().addObserver(this);
        BomberMan.getMiBomberMan().addObserver(this);
        //se aniade el controlador al tablero
        addKeyListener(this.getTController());
        setFocusable(true); 
    }

    // METODOS
    private void inicializarVista() {
        for (int i = 0; i < alto; i++) {
            for (int j = 0; j < ancho; j++) {
            visualizarCasilla(j,i);
                
            }
        }
    }
   
    private void visualizarCasilla(int pI, int pJ)
    {        
        if (labels[pI][pJ] == null) {
            labels[pI][pJ] = new JLabel();
            labels[pI][pJ].setOpaque(false);
            labels[pI][pJ].setHorizontalAlignment(JLabel.CENTER);
            labels[pI][pJ].setForeground(Color.BLACK);
            add(labels[pI][pJ]);
        }
        else {
        	labels[pI][pJ].revalidate();
        	labels[pI][pJ].repaint();
        }
        	
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);  // Llamar al método de la superclase para asegurar un repintado correcto

        Image backgroundImage= new ImageIcon(getClass().getResource("stageBack1.png")).getImage();
		// Dibujar la imagen de fondo, ajustándola al tamaño del panel
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
    
    private void verificarCasilla(String pCont,int pJ, int pI)
    {
    	if (pCont.equals("bombermanW")) {
    		String iconBmW= "whitefront1.png"; 
        	labels[pJ][pI].setIcon(new StretchIcon(this.getClass().getResource(iconBmW)));
        } else if (pCont.equals("bloqueD")) {
        	String iconBloqD= "hard5.png"; 
        	labels[pJ][pI].setIcon(new StretchIcon(this.getClass().getResource(iconBloqD)));
        } else if (pCont.equals("bloqueB")) {
            String iconBloqB= "soft1.png"; 
        	labels[pJ][pI].setIcon(new StretchIcon(this.getClass().getResource(iconBloqB)));
        } else if (pCont.equals("enemigo.")) {
        	String iconEnemigo= "baloon1.png"; 
        	labels[pJ][pI].setIcon(new StretchIcon(this.getClass().getResource(iconEnemigo)));
        } else if(pCont.equals("")){ 
        	labels[pJ][pI].setIcon(null);
        }else if(pCont.equals("bomba.S.")) {
        	String iconBombaS = "bomb1.png";
        	labels[pJ][pI].setIcon(new ImageIcon(this.getClass().getResource(iconBombaS))); 
        }else if(pCont.equals("explosion.S.")) {
        	String iconExploS= "blast.gif";
        	for(int i= pI-1; i<= pI+1;i++) {
        		for(int j= pJ-1; j<=pJ+1;j++) {
        			 if (j >= 0 && j < labels.length && i >= 0 && i < labels[j].length) {  
             	        labels[j][i].setIcon(new ImageIcon(this.getClass().getResource(iconExploS)));

        			 }
                 }
              }
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof TableroModel)
        {
        	Object[] res = (Object[])arg;
        	int pX= (int)res[0];
        	int pY= (int)res[1];
        	String pCont= (String)res[2];
        	verificarCasilla(pCont,pX,pY);	
        }
        else if (o instanceof BomberMan)
        {
        	int[] res = (int[])arg;
        	int pX0= (int)res[0];
        	int pY0= (int)res[1];
        	int pX1= (int)res[2];
        	int pY1= (int)res[3];
        	verificarCasilla("bombermanW",pX1,pY1);	
        }
    }

    //INSTANCIA
    private TableroController getTController()
    {
    	if (controlador==null)
    	{
    		controlador= new TableroController();
    	}
    	return controlador;
    }
    //CONTROLADOR 
    private class TableroController implements KeyListener
    {
    	Set<Integer> pressedKeys = new TreeSet<Integer>();
    	
    	@Override
        public void keyPressed(KeyEvent e) {
    		int key = e.getKeyCode();
    		Integer val = Integer.valueOf(key);
            BomberMan bomberman = BomberMan.getMiBomberMan();
            if (pressedKeys.contains(val)) 
            {
        	    return;
        	}
            else 
            {
            	pressedKeys.add(val);
            	if (key == KeyEvent.VK_UP) bomberman.mover(0, -1);
                if (key == KeyEvent.VK_DOWN) bomberman.mover(0, 1);
                if (key == KeyEvent.VK_LEFT) bomberman.mover(-1, 0);
                if (key == KeyEvent.VK_RIGHT) bomberman.mover(1, 0);
                 if (key == KeyEvent.VK_X) {
                	BombaSuper nuevaBomba= new BombaSuper(bomberman.getX(),bomberman.getY());
                 }
        	}
        }

        @Override
        public void keyReleased(KeyEvent e) {
        	pressedKeys.remove(e.getKeyCode());
        }

        @Override
        public void keyTyped(KeyEvent e) {
        }
        
    }
}
