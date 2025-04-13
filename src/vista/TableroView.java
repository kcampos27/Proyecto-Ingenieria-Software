package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;
import java.util.TreeSet;
import java.awt.event.KeyEvent;

import model.Gestor;
import model.TableroModel;

@SuppressWarnings({ "deprecation", "serial" })
public class TableroView extends JPanel implements Observer{
	
	//ATRIBUTOS
    private int ancho = 17;
    private int alto = 11;
    private JPanel[][] casillas;
    private TableroController controlador;
	private String tipoPantalla;

    // CONSTRUCTORA
    public TableroView() {
		tipoPantalla = "stageBack1";
    	setLayout(new GridLayout(alto, ancho, 1, 1));
        casillas = new JPanel[ancho][alto];
        this.setBackground(Color.blue);
        inicializarVista();
        Gestor.getInstance().getTablero().addObserver(this);
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
        if (casillas[pI][pJ] == null) {
            casillas[pI][pJ] = new JPanel();
            casillas[pI][pJ].setLayout(new BorderLayout());
            casillas[pI][pJ].setOpaque(false);
            casillas[pI][pJ].setForeground(Color.BLACK);
            add(casillas[pI][pJ]);
        }
        else {
        	casillas[pI][pJ].revalidate();
        	casillas[pI][pJ].repaint();
        }
        	
    }

	public void setTipoPantalla(String tipoPantalla) {
		{
			this.tipoPantalla = tipoPantalla;
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);  // Llamar al metodo de la superclase para asegurar un repintado correcto

		Image backgroundImage= new ImageIcon(getClass().getResource(tipoPantalla+".png")).getImage();
		// Dibujar la imagen de fondo, ajustándola al tamaño del panel
		g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
	}
    
    private void addImagen(int pJ, int pI, String nuevaImagen, int pCapa)
    {
    	// Verificar la ruta de la imagen asociada al contenido
        String imagen = verificarCasilla(nuevaImagen);
        String nombre = nuevaImagen;
		if(esBomberman(nuevaImagen)) {nombre = "bombermanW";}//orientaciones bomberman

        System.out.println("Aniadiendo imagen a [" + pJ + "][" + pI + "]: " + imagen);
        
        if (imagen != "") 
        {	// No hacer nada si la imagen no existe o es vacia

            // Crear JLabel con la imagen
        	if(casillas[pJ][pI].getComponents().length < 3 && !esta(pJ,pI,nuevaImagen))
            {
        		JLabel labelImagen = new JLabel(new StretchIcon(this.getClass().getResource(imagen)));
        		// Aniadir la imagen al panel
        		casillas[pJ][pI].add(labelImagen, BorderLayout.CENTER);
        		
        		//Poner un nombre a la casilla
        		labelImagen.setName(nombre);	
        		System.out.println("Se ha aniadido la imagen a "+pJ+", "+pI+", "+labelImagen.getName()+", posicion: "+casillas[pJ][pI].getComponentCount());
            }
        	else
        	{
        		boolean added = false;
        		for(Component c : casillas[pJ][pI].getComponents())
        		{	
        			if(c instanceof JLabel && !added && !esta(pJ,pI,nuevaImagen))
        			{//Si hay un JLabel sin icono y no se ha aniadido
        				if(((JLabel) c).getName().equals("null"))
        				{
        					// Aniadir la imagen al panel
        					((JLabel) c).setIcon(new StretchIcon(this.getClass().getResource(imagen)));
        					
        					//Poner un nombre a la casilla
        					((JLabel) c).setName(nombre);
        					System.out.println("imagen aniadida a "+c.getName());
        					added = true;
        				}
        			}
        		}
        		//if(!added) {System.out.println("Imagen "+imagen+" no aniadida, panel lleno");}
        		
        	}
        	// Refrescar la casilla
    		casillas[pJ][pI].revalidate();
    		casillas[pJ][pI].repaint();
        }
        else {System.out.println("Imagen no encontrada para: " + nuevaImagen);}
        System.out.print("Componentes de "+pJ+", "+pI+" : {");
		for(Component c : casillas[pJ][pI].getComponents())
		{	
			System.out.print(c.getName()+", ");
		}
		System.out.println("}");
    }
    
    private void removeImagen(int pJ, int pI, String pImagen) 
    {
    	String imagen = verificarCasilla(pImagen);
    	String nombre = pImagen;
		if(esBomberman(pImagen)) {nombre = "bombermanW";}//orientancion bomberman
		
    	boolean removed = false;
		for(Component c : casillas[pJ][pI].getComponents())
		{
			if(c instanceof JLabel && !removed)
			{//Si hay un JLabel sin icono y no se ha aniadido
				if(((JLabel) c).getName().equals(nombre))
				{
					// Aniadir la imagen al panel
					((JLabel) c).setIcon(null);
					((JLabel) c).setName("null");
					System.out.println("imagen "+imagen+" eliminada");
					//removed = true;
				}
			}
		}
		//if(!removed) {System.out.println("Imagen "+imagen+" no eliminada, no esta");}
		casillas[pJ][pI].revalidate();
		casillas[pJ][pI].repaint();
    }
    
    private boolean esta(int pX, int pY, String pName)
    {
    	boolean esta = false;
    	
    	for (Component c : casillas[pX][pY].getComponents())
    	{
    		if(((JLabel)c).getName().equals(pName)) {esta = true;}
    	}
    	return esta;
    }

    @Override
    public void update(Observable o, Object arg) {
    	Object[] res = (Object[])arg;
        int pX= (int)res[0];
        int pY= (int)res[1];
        String pCont= (String)res[2];
        String pAccion= (String)res[3];
        int pCapa= (int)res[4];
        System.out.println("Observer actualizado con: " +pX+", "+pY+", "+pCont+", "+pAccion);
        if(pAccion.equals("add"))
        {
        	addImagen(pX,pY,pCont,pCapa);
        }
        else if(pAccion.equals("del"))
        {
        	removeImagen(pX,pY,pCont);
        }
        	
    }
    
    public boolean esBomberman(String pCont)
    {
    	boolean es = false;
    	if(pCont.equals("enllamas") 
    			|| pCont.equals("enllamas")
    			|| pCont.equals("left")
    			|| pCont.equals("left4")
    			|| pCont.equals("left3")
    			|| pCont.equals("left5")
    			|| pCont.equals("right")
    			|| pCont.equals("right4")
    			|| pCont.equals("right3")
    			|| pCont.equals("right5")
    			|| pCont.equals("up")
    			|| pCont.equals("up3")
    			|| pCont.equals("up4")
    			|| pCont.equals("down")
    			|| pCont.equals("down2")
    			|| pCont.equals("down3")
    			|| pCont.equals("down4")
    			|| pCont.equals("bomberBomba"))
    	{
    		es = true;
    	}
    	
    	return es;
    }
    
    private String verificarCasilla(String pCont)
    {
    	String recurso = "";
    	if(pCont.equals("bombermanW")){ recurso = "whitefront1.png";}
    	else if(pCont.equals("enllamas")){recurso = "onFire2.png";}
    	else if(pCont.equals("left")){recurso= "whiteleft.png";}
    	else if(pCont.equals("left4")){recurso= "whiteleft4.png";}
    	else if(pCont.equals("left3")){recurso= "whiteleft3.png";}
    	else if(pCont.equals("left5")){recurso= "whiteleft5.png";}
    	else if(pCont.equals("right")){recurso= "whiteright.png";}
    	else if(pCont.equals("right4")){recurso= "whiteright4.png";}
    	else if(pCont.equals("right3")){recurso= "whiteright3.png";}
    	else if(pCont.equals("right5")){recurso= "whiteright5.png";}
    	else if(pCont.equals("up")){recurso= "whiteup.png";}
    	else if(pCont.equals("up3")){recurso= "whiteup3.png";}
    	else if(pCont.equals("up4")){recurso= "whiteup4.png";}
    	else if(pCont.equals("down")){recurso= "whitedown.png";}
    	else if(pCont.equals("down2")){recurso= "whitedown2.png";}
    	else if(pCont.equals("down3")){recurso= "whitedown3.png";}
    	else if(pCont.equals("down4")){recurso= "whitedown4.png";}
    	else if(pCont.equals("bomberBomba")){recurso= "whitewithbomb.png";}
    	else if(pCont.equals("bloqueD")){recurso= "hard5.png";}	
    	else if(pCont.equals("bloqueB")){recurso= "soft1.png";}
    	else if(pCont.equals("enemigo")){recurso= "baloon1.png";}
    	else if(pCont.equals("bombaS")){recurso= "bomb1.png";}
    	else if(pCont.equals("*")) {recurso= "blast.gif";}
    	System.out.println("Verificando imagen "+recurso+" para: " + pCont);
    	return recurso;
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
    	private Set<Integer> pressedKeys = new TreeSet<Integer>();
    	
    	@Override
        public void keyPressed(KeyEvent e) {
    		int key = e.getKeyCode();
    		Integer val = key;
            if (!pressedKeys.contains(val)) 
            {
            	pressedKeys.add(val);
            	if (key == KeyEvent.VK_UP) Gestor.getInstance().getTablero().moverBomberman(0, -1);System.out.println("");
                if (key == KeyEvent.VK_DOWN) Gestor.getInstance().getTablero().moverBomberman(0, 1);System.out.println("");
                if (key == KeyEvent.VK_LEFT) Gestor.getInstance().getTablero().moverBomberman(-1, 0);System.out.println("");
                if (key == KeyEvent.VK_RIGHT) Gestor.getInstance().getTablero().moverBomberman(1, 0);System.out.println("");
                if (key == KeyEvent.VK_X) {
                	Gestor.getInstance().getTablero().crearBomba();System.out.println("");
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
