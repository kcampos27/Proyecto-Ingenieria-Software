package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

import model.TableroModel;

@SuppressWarnings("deprecation")
public class TableroView extends JPanel implements Observer{
	
	//ATRIBUTOS
    private int ancho = 17;
    private int alto = 11;
    private JLabel[][] labels;

    // CONSTRUCTORA
    public TableroView() {
    	setLayout(new GridLayout(alto, ancho, 1, 1));
        labels = new JLabel[alto][ancho];
        this.setBackground(Color.blue);
        inicializarVista();
        TableroModel.getMiTablero().addObserver(this);
    }

    // METODOS
    private void inicializarVista() {
        for (int i = 0; i < alto; i++) {
            for (int j = 0; j < ancho; j++) {
            visualizarCasilla(i,j);
                
            }
        }
    }
    
   /* private JLabel moverBomberman()
    {
    	JLabel bomberman = buscarBomberman();
    	if(bomberman.getKeyListeners() == null)
    	{
    		bomberman.addKeyListener(TableroModel.getMiTablero());
    	}
    	return bomberman;
    }
 
    
    private JLabel buscarBomberman()
    {
    	int i = 0, j= 0;
    	boolean enc = false;
    	JLabel res = null;
    	while(i  < alto)
    	{
    		while( j < ancho)
    		{
    			if(labels[i][j].getBackground() == Color.BLUE)
    			{
    				enc = true;
    			}
    			j++;
    		}
    		i++;
    	}
    	if(enc)
    	{
    		res = labels[i][j];
    	}
    	return res;
    }
    */
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
    
    private void verificarCasilla(String pCont,int pI, int pJ)
    {
    	if (pCont.equals("bomberman.W.")) {
    		String iconBmW= "whitefront1.png"; 
        	labels[pI][pJ].setIcon(new ImageIcon(this.getClass().getResource(iconBmW)));
        } else if (pCont.equals("bloqueD")) {
        	String iconBloqD= "hard5.png"; 
        	labels[pI][pJ].setIcon(new ImageIcon(this.getClass().getResource(iconBloqD)));
        } else if (pCont.equals("bloqueB")) {
            String iconBloqB= "soft1.png"; 
        	labels[pI][pJ].setIcon(new ImageIcon(this.getClass().getResource(iconBloqB)));
        } else if (pCont.equals("enemigo.")) {
        	String iconEnemigo= "baloon1.png"; 
        	labels[pI][pJ].setIcon(new ImageIcon(this.getClass().getResource(iconEnemigo)));
        } else if(pCont.equals("")){ 
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
    }
    
    //CONTROLADOR 
    private class TableroController implements KeyListener
    {
    	@Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_UP) modelo.moverBomberman(-1, 0);
            if (key == KeyEvent.VK_DOWN) modelo.moverBomberman(1, 0);
            if (key == KeyEvent.VK_LEFT) modelo.moverBomberman(0, -1);
            if (key == KeyEvent.VK_RIGHT) modelo.moverBomberman(0, 1);
        }

        @Override
        public void keyReleased(KeyEvent e) {}

        @Override
        public void keyTyped(KeyEvent e) {}
    }
}
