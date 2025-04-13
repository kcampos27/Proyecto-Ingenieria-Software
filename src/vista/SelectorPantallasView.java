package vista;

import model.PantallaModel;

import javax.swing.*;
import javax.swing.plaf.basic.BasicArrowButton;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Set;
import java.util.TreeSet;

public class SelectorPantallasView extends JPanel {

	private static final long serialVersionUID = 1L;
	private String pantalla;
	private BasicArrowButton btnLeft;
	private BasicArrowButton btnRight;
	private JLabel chooseText;
	private SpringLayout springLayout;

	private SelPanController controlador;

	/**
	 * Create the panel.
	 */
	public SelectorPantallasView() {
		springLayout = new SpringLayout();
		setLayout(springLayout);
		this.setBackground(new Color(255, 255, 255));
		add(getBtnLeft());
		add(getBtnRight());
		add(getChooseText());
		pantalla = "stageBack1";
		addKeyListener(this.getSPController());
		setFocusable(true);
		actualizar();

	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);  // Llamar al método de la superclase para asegurar un repintado correcto

		Image backgroundImage= new StretchIcon(getClass().getResource(pantalla+".png")).getImage();
		// Dibujar la imagen de fondo, ajustándola al tamaño del panel
		g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
	}

	
	private BasicArrowButton getBtnLeft() {
		if (btnLeft == null) {
			btnLeft = new BasicArrowButton(7);
			
			springLayout.putConstraint(SpringLayout.NORTH, btnLeft, 220, SpringLayout.NORTH, this);
			springLayout.putConstraint(SpringLayout.WEST, btnLeft, 48, SpringLayout.WEST, this);
			springLayout.putConstraint(SpringLayout.EAST, btnLeft, 88, SpringLayout.WEST, this);
			springLayout.putConstraint(SpringLayout.SOUTH, btnLeft, 330, SpringLayout.NORTH, this);
			btnLeft.setFont(new Font("Rockwell Extra Bold", Font.BOLD, 16));
			btnLeft.addMouseListener(getSPController());
		}
		return btnLeft;
	}
	
	private BasicArrowButton getBtnRight() {
		if (btnRight == null) {
			btnRight = new BasicArrowButton(3);
			
			springLayout.putConstraint(SpringLayout.NORTH, btnRight, 220, SpringLayout.NORTH, this);
			springLayout.putConstraint(SpringLayout.WEST, btnRight, 688, SpringLayout.WEST, this);
			springLayout.putConstraint(SpringLayout.EAST, btnRight, 728, SpringLayout.WEST, this);
			springLayout.putConstraint(SpringLayout.SOUTH, btnRight, 330, SpringLayout.NORTH, this);
			btnRight.setFont(new Font("Rockwell Extra Bold", Font.BOLD, 16));
			btnRight.addMouseListener(getSPController());
		}
		return btnRight;
	}
	
	private JLabel getChooseText() {
		if (chooseText == null) {
			chooseText = new JLabel("Choose Map (press X)");
			springLayout.putConstraint(SpringLayout.NORTH, chooseText, 320, SpringLayout.NORTH, this);
			springLayout.putConstraint(SpringLayout.WEST, chooseText, 258, SpringLayout.WEST, this);
			springLayout.putConstraint(SpringLayout.EAST, chooseText, 558, SpringLayout.WEST, this);
			springLayout.putConstraint(SpringLayout.SOUTH, chooseText, 350, SpringLayout.NORTH, this);
			chooseText.setFont(new Font("Rockwell Extra Bold", Font.BOLD, 20));
			chooseText.setForeground(new Color(255, 0, 0));
		}
		return chooseText;
	}
	
	private void actualizar()
	{
		revalidate();
		repaint();
	}

	public void close()
	{
		Window ventana = SwingUtilities.getWindowAncestor(this);
		if (ventana != null) {
			ventana.dispose();

		}
	}

	public void changeScreen(int pNext)
	{
		if (pNext == -1) {
			switch (pantalla) {
				case "stageBack1" -> pantalla = "stageBack2";
				case "stageBack3" -> pantalla = "stageBack1";
				case "stageBack2" -> pantalla = "stageBack3";
			}
		}
		else if (pNext == 1) {
			switch (pantalla) {
				case "stageBack1" -> pantalla = "stageBack3";
				case "stageBack3" -> pantalla = "stageBack2";
				case "stageBack2" -> pantalla = "stageBack1";
			}
		}
		actualizar();
	}
	
    //INSTANCIA
    private SelPanController getSPController()
    {
    	if (controlador==null)
    	{
    		controlador= new SelPanController();
    	}
    	return controlador;
    }
    //CONTROLADOR 
    private class SelPanController implements MouseListener, KeyListener
    {
            @SuppressWarnings({"FieldMayBeFinal", "Convert2Diamond"})
    	private Set<Integer> pressedKeys = new TreeSet<Integer>();
    	
    	@Override
        public void keyPressed(KeyEvent e) {
    		int key = e.getKeyCode();
    		Integer val = key;
            if (!pressedKeys.contains(val)) 
            {
            	pressedKeys.add(val);
                if (key == KeyEvent.VK_LEFT)
				{
					PantallaModel.getMiPantalla().select(-1);
					changeScreen(-1);
					System.out.println("anterior");

				}
                if (key == KeyEvent.VK_RIGHT) 
				{
					PantallaModel.getMiPantalla().select(1);
					changeScreen(1);
					System.out.println("siguiente");

				}
				if (key== KeyEvent.VK_X)
				{
					close();
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

		@Override
		public void mouseClicked(MouseEvent e) {
			
		    if (e.getSource().equals(btnRight))
			{
		    	PantallaModel.getMiPantalla().select(1);
				changeScreen(1);
				System.out.println("siguiente");
			}
			else if (e.getSource().equals(btnLeft))
			{
				PantallaModel.getMiPantalla().select(-1);
				changeScreen(-1);
				System.out.println("anterior");
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
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
        
    }
}
