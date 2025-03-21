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
import model.Bombas;
import model.BomberMan;
import model.Elemento;
import model.TableroModel;

@SuppressWarnings({ "deprecation", "serial" })
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
        }else if(pCont.equals("bombaS")) {
        	String iconBombaS = "bomb1.png";
        	labels[pJ][pI].setIcon(new StretchIcon(this.getClass().getResource(iconBombaS))); 
        }else if(pCont.equals("*")) {
        	String iconExploS= "blast.gif";
        	labels[pJ][pI].setIcon(new StretchIcon(this.getClass().getResource(iconExploS)));
        }
    }

    @Override
    public void update(Observable o, Object arg) {
    	
    	Object[] res = (Object[])arg;
        int pX= (int)res[0];
        int pY= (int)res[1];
        String pCont= (String)res[2];
        verificarCasilla(pCont,pX,pY);	
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
            TableroModel tablero = TableroModel.getMiTablero();
            if (pressedKeys.contains(val)) 
            {
        	    return;
        	}
            else 
            {
            	pressedKeys.add(val);
            	if (key == KeyEvent.VK_UP) tablero.moverBomberman(0, -1);
                if (key == KeyEvent.VK_DOWN) tablero.moverBomberman(0, 1);
                if (key == KeyEvent.VK_LEFT) tablero.moverBomberman(-1, 0);
                if (key == KeyEvent.VK_RIGHT) tablero.moverBomberman(1, 0);
                if (key == KeyEvent.VK_X) {
                	tablero.crearBomba();
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
