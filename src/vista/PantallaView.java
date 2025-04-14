package vista;

import model.PantallaModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;
import java.util.TreeSet;

public class PantallaView extends JPanel implements Observer {

	private static final long serialVersionUID = 1L;
	private SpringLayout springLayout;
	private JLabel lbl1;
	private JLabel lbl2;
	private JButton btnPlay;
	private JButton btnSelectMap;
	private JLabel titulo;
	private PantallaController controlador;
	private JLabel lblExploW;
	private JLabel lblExploB;

	/**
	 * Create the panel.
	 */
	//CONSTRUCTORA
	public PantallaView() {
	    setBackground(new Color(255, 255, 255));
	    springLayout = new SpringLayout();
	    setLayout(springLayout);
	    add(getLbl1());
	    add(getLbl2());
	    add(getBtnPlay());
	    add(getBtnSelectMap());
	    add(getTitulo());
	    add(getLblExploW());
	    add(getLblExploB());

	    getLblExploW().setVisible(false);
	    getLblExploB().setVisible(false);

	    PantallaModel.getMiPantalla().addObserver(this);

	    // IMPORTANTE PARA CAPTURAR TECLA ESPACIO
	    setFocusable(true);
	    requestFocusInWindow();
	    addKeyListener(getPController());

	    // Si cambia el foco por otra ventana, lo recupera
	    addHierarchyListener(e -> {
	        if (isShowing()) {
	            requestFocusInWindow();
	        }
	    });
	}

	private JLabel getLbl1() {
		if (lbl1 == null) {
			lbl1 = new JLabel("");
			springLayout.putConstraint(SpringLayout.WEST, lbl1, 157, SpringLayout.WEST, this);
			springLayout.putConstraint(SpringLayout.SOUTH, lbl1, -192, SpringLayout.SOUTH, this);
			springLayout.putConstraint(SpringLayout.EAST, lbl1, 262, SpringLayout.WEST, this);
			lbl1.setIcon(new StretchIcon(PantallaView.class.getResource("/vista/bomber1.png")));
			lbl1.addMouseListener(getPController());
		}
		return lbl1;
	}
	private JLabel getLbl2() {
		if (lbl2 == null) {
			lbl2 = new JLabel("");
			springLayout.putConstraint(SpringLayout.NORTH, getLbl1(), 0, SpringLayout.NORTH, lbl2);
			springLayout.putConstraint(SpringLayout.NORTH, lbl2, 203, SpringLayout.NORTH, this);
			springLayout.putConstraint(SpringLayout.EAST, lbl2, -159, SpringLayout.EAST, this);
			springLayout.putConstraint(SpringLayout.WEST, lbl2, -235, SpringLayout.EAST, this);
			springLayout.putConstraint(SpringLayout.SOUTH, lbl2, 318, SpringLayout.NORTH, this);
			lbl2.setIcon(new StretchIcon(PantallaView.class.getResource("/vista/bomber2.png")));
			lbl2.addMouseListener(getPController());
		}
		return lbl2;
	}
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);  // Llamar al método de la superclase para asegurar un repintado correcto

		Image backgroundImage= new StretchIcon(getClass().getResource("back.png")).getImage();
		// Dibujar la imagen de fondo, ajustándola al tamaño del panel
		g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
	}
	private JLabel getTitulo() {
		if (titulo == null) {
			titulo = new JLabel("");
			springLayout.putConstraint(SpringLayout.NORTH, titulo, 28, SpringLayout.NORTH, this);
			springLayout.putConstraint(SpringLayout.WEST, titulo, 208, SpringLayout.WEST, this);
			titulo.setIcon(new ImageIcon(PantallaView.class.getResource("/vista/title.png")));
		}
		return titulo;
	}
	
	private JButton getBtnPlay() {
		if (btnPlay == null) {
			btnPlay = new JButton("Play");
			
			springLayout.putConstraint(SpringLayout.NORTH, btnPlay, 220, SpringLayout.NORTH, this);
			springLayout.putConstraint(SpringLayout.WEST, btnPlay, 318, SpringLayout.WEST, this);
			springLayout.putConstraint(SpringLayout.EAST, btnPlay, 498, SpringLayout.WEST, this);
			springLayout.putConstraint(SpringLayout.SOUTH, btnPlay, 250, SpringLayout.NORTH, this);
			btnPlay.setFont(new Font("Rockwell Extra Bold", Font.BOLD, 16));
			btnPlay.addMouseListener(getPController());
			btnPlay.setFocusable(false); // evita que capture tecla espacio
		}
		return btnPlay;
	}
	
	private JButton getBtnSelectMap() {
		if (btnSelectMap == null) {
			btnSelectMap = new JButton("Select Map");
			
			springLayout.putConstraint(SpringLayout.NORTH, btnSelectMap, 270, SpringLayout.NORTH, this);
			springLayout.putConstraint(SpringLayout.WEST, btnSelectMap, 318, SpringLayout.WEST, this);
			springLayout.putConstraint(SpringLayout.EAST, btnSelectMap, 498, SpringLayout.WEST, this);
			springLayout.putConstraint(SpringLayout.SOUTH, btnSelectMap, 300, SpringLayout.NORTH, this);
			btnSelectMap.setFont(new Font("Rockwell Extra Bold", Font.BOLD, 16));
			btnSelectMap.addMouseListener(getPController());
			btnSelectMap.setFocusable(false); // evita que capture espacio
		}
		return btnSelectMap;
	}

	private void abrirSelPant()
	{
		JFrame frameSel = new JFrame("Selector de pantallas");
		frameSel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameSel.setSize(800, 530);
		frameSel.setResizable(false); // Permitir redimensionamiento

		// Crear instancia del panel
		JPanel panel = new SelectorPantallasView();
		frameSel.setContentPane(panel);
		frameSel.setVisible(true);
	}

	public void close()
	{
		Window ventana = SwingUtilities.getWindowAncestor(this);
		if (ventana != null) {
			ventana.dispose();
		}
	}

	private void abrirTablero()
	{
		TableroView vista = new TableroView();
		vista.setTipoPantalla(PantallaModel.getMiPantalla().getTipoPantalla());
		//TableroModel modelo= Gestor.getInstance().getTablero();
		//modelo.inicializar();
		JFrame frame = new JFrame("Tablero");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 400);
		frame.add(vista);
		frame.setVisible(true);
		close();
	}

	@Override
	public void update(Observable o, Object arg) {
		System.out.println("RECIBIDO");
		int codigo = (int)arg;
		if (codigo==1)
		{
			abrirTablero();
		}
	}

	//INSTANCIA
	private PantallaController getPController()
	{
		if (controlador==null)
		{
			controlador= new PantallaController();
		}
		return controlador;
	}

	//CONTROLADOR
	private class PantallaController implements MouseListener, KeyListener
	{
		private Set<Integer> pressedKeys = new TreeSet<Integer>();
		@Override
		public void mouseClicked(MouseEvent e) {
			
			if (e.getSource().equals(lbl1)) //Se ha pulsado el bomberman blanco
			{
				System.out.println("Blanco");
				lblExploW.setVisible(true);
				lblExploB.setVisible(false);
				PantallaModel.getMiPantalla().setBomberman("W");
			}
			else if (e.getSource().equals(lbl2))
			{
				System.out.println("Negro");
				lblExploB.setVisible(true);
				lblExploW.setVisible(false);
				PantallaModel.getMiPantalla().setBomberman("B");
			}
			else if (e.getSource().equals(btnPlay))
			{
				PantallaModel.getMiPantalla().play();
			}
			else if (e.getSource().equals(btnSelectMap))
			{
				abrirSelPant();
			}
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void keyTyped(KeyEvent e) {

		}

		@Override
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			Integer val = key;
			if (!pressedKeys.contains(val))
			{
				if (key == KeyEvent.VK_O) {
					abrirSelPant();
				}
				if (key == KeyEvent.VK_SPACE)
				{
					PantallaModel.getMiPantalla().play();
				}
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {

		}
	}
	private JLabel getLblExploW() {
		if (lblExploW == null) {
			lblExploW = new JLabel("");
			springLayout.putConstraint(SpringLayout.NORTH, lblExploW, 70, SpringLayout.SOUTH, getTitulo());
			springLayout.putConstraint(SpringLayout.WEST, lblExploW, -640, SpringLayout.EAST, this);
			lblExploW.setIcon(new ImageIcon(PantallaView.class.getResource("/vista/blast.gif")));
		}
		return lblExploW;
	}
	private JLabel getLblExploB() {
		if (lblExploB == null) {
			lblExploB = new JLabel("");
			springLayout.putConstraint(SpringLayout.NORTH, lblExploB, 203, SpringLayout.NORTH, this);
			springLayout.putConstraint(SpringLayout.WEST, lblExploB, -265, SpringLayout.EAST, this);
			lblExploB.setIcon(new ImageIcon(PantallaView.class.getResource("/vista/blast.gif")));
		}
		return lblExploB;
	}
}
