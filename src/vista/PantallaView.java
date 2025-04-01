package vista;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SpringLayout;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;

public class PantallaView extends JPanel {

	private static final long serialVersionUID = 1L;
	private SpringLayout springLayout;
	private JLabel lbl1;
	private JLabel lbl2;
	private JLabel titulo;
	private PantallaController controlador;
	private JLabel lblExploW;
	private JLabel lblExploB;

	/**
	 * Create the panel.
	 */
	public PantallaView() {
		setBackground(new Color(255, 255, 255));
		springLayout = new SpringLayout();
		setLayout(springLayout);
		add(getLbl1());
		add(getLbl2());
		add(getTitulo());
		add(getLblExploW());
		add(getLblExploB());
		getLblExploW().setVisible(false);
		getLblExploB().setVisible(false);

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
    private class PantallaController implements MouseListener
    {

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource().equals(lbl1))
			{
				System.out.println("Blanco");
			}
			else if (e.getSource().equals(lbl2))
			{
				System.out.println("Negro");
			}
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
