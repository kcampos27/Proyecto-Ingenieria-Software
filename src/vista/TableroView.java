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
        if (esBomberman(nuevaImagen)) {
            nombre = nuevaImagen; // ✅ Deja el nombre tal cual para que coincida con el PNG
        }



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
    
    private void removeImagen(int pJ, int pI, String pImagen) {
        String imagen = verificarCasilla(pImagen);

        for (Component c : casillas[pJ][pI].getComponents()) {
            if (c instanceof JLabel label) {
                String name = label.getName();
                // Eliminamos todos los sprites que sean de tipo bomberman
                if (esBomberman(name)) {
					label.setIcon(null);
                    label.setName("null");
                    System.out.println("imagen " + imagen + " eliminada de bomberman (" + name + ")");
                }

                // También eliminamos la imagen exacta si no es bomberman
                if (name.equals(pImagen)) {
                    label.setIcon(null);
                    label.setName("null");
                    System.out.println("imagen " + imagen + " eliminada (" + name + ")");
                }
            }
        }

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
    
    public boolean esBomberman(String pCont) {
    	boolean es = switch (pCont) {
    		// Blanco
    		case "left", "left3", "left4", "left5",
    		     "right", "right3", "right4", "right5",
    		     "up", "up3", "up4",
    		     "down", "down2", "down3", "down4",
    		     "bomberBomba" -> true;

    		// Negro
    		case "blackleft1", "blackleft2", "blackleft3", "blackleft4", "blackleft5",
    		     "blackright1", "blackright2", "blackright3", "blackright4", "blackright5",
    		     "blackup1", "blackup2", "blackup3", "blackup4", "blackup5",
    		     "blackdown1", "blackdown2", "blackdown3", "blackdown4",
    		     "blackwithbomb1", "blackwithbomb2", "blackwithbomb3" -> true;

    		default -> false;
    	};
    	return es;
    }


    
    private String verificarCasilla(String pCont) {
    	String recurso = "";

    	// Blanco
    	switch (pCont) {
    		case "bombermanW" -> recurso = "whitefront1.png";
    		case "left" -> recurso = "whiteleft.png";
    		case "left3" -> recurso = "whiteleft3.png";
    		case "left4" -> recurso = "whiteleft4.png";
    		case "left5" -> recurso = "whiteleft5.png";
    		case "right" -> recurso = "whiteright.png";
    		case "right3" -> recurso = "whiteright3.png";
    		case "right4" -> recurso = "whiteright4.png";
    		case "right5" -> recurso = "whiteright5.png";
    		case "up" -> recurso = "whiteup.png";
    		case "up3" -> recurso = "whiteup3.png";
    		case "up4" -> recurso = "whiteup4.png";
    		case "down" -> recurso = "whitedown.png";
    		case "down2" -> recurso = "whitedown2.png";
    		case "down3" -> recurso = "whitedown3.png";
    		case "down4" -> recurso = "whitedown4.png";
    		case "bomberBomba" -> recurso = "whitewithbomb.png";
    	}

    	// Negro
    	switch (pCont) {
    		case "bombermanN" -> recurso = "blackfront1.png";
    		case "blackleft1" -> recurso = "blackleft1.png";
    		case "blackleft2" -> recurso = "blackleft2.png";
    		case "blackleft3" -> recurso = "blackleft3.png";
    		case "blackleft4" -> recurso = "blackleft4.png";
    		case "blackleft5" -> recurso = "blackleft5.png";
    		case "blackright1" -> recurso = "blackright1.png";
    		case "blackright2" -> recurso = "blackright2.png";
    		case "blackright3" -> recurso = "blackright3.png";
    		case "blackright4" -> recurso = "blackright4.png";
    		case "blackright5" -> recurso = "blackright5.png";
    		case "blackup1" -> recurso = "blackup1.png";
    		case "blackup2" -> recurso = "blackup2.png";
    		case "blackup3" -> recurso = "blackup3.png";
    		case "blackup4" -> recurso = "blackup4.png";
    		case "blackup5" -> recurso = "blackup5.png";
    		case "blackdown1" -> recurso = "blackdown1.png";
    		case "blackdown2" -> recurso = "blackdown2.png";
    		case "blackdown3" -> recurso = "blackdown3.png";
    		case "blackdown4" -> recurso = "blackdown4.png";
    		case "blackwithbomb1" -> recurso = "blackwithbomb1.png";
    		case "blackwithbomb2" -> recurso = "blackwithbomb2.png";
    		case "blackwithbomb3" -> recurso = "blackwithbomb3.png";
    	}

    	// Otros elementos
    	switch (pCont) {
    		case "enllamas" -> recurso = "onFire2.png";
    		case "bloqueD" -> recurso = "hard5.png";
    		case "bloqueB" -> recurso = "soft1.png";
    		case "enemigo" -> recurso = "baloon1.png";
    		case "bombaS" -> recurso = "bomb1.png";
			case "bombaU" -> recurso = "bomb1.png";
    		case "*" -> recurso = "blast.gif";
    	}

    	System.out.println("Verificando imagen " + recurso + " para: " + pCont);
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
