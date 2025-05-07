package vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import model.Gestor;
import model.PantallaModel;

@SuppressWarnings("deprecation")
public class PantallaPausaView extends JFrame implements Observer
{
	private JButton btnSalir;
	private JButton btnContinuar;
	private PPController controlador;
	private JPanel mainPanel;
	private JLabel lblLogo;
	private JLabel lblWin;
	private JLabel lblLose;
	private String tipo;
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public PantallaPausaView(String pTipo, boolean reanudable)
	{
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.add(getMainPanel(pTipo, reanudable));
		
		this.setUndecorated(true);
		this.setBackground(Color.GRAY);
		this.setOpacity(1);
    	this.setResizable(false);
    	this.setVisible(false);
    	
    	this.addMouseListener(getPPController(reanudable));
    	this.addKeyListener(getPPController(reanudable));
    	
    	this.setFocusable(true);
    	this.setLocationRelativeTo(this);
		this.setSize(300, 235);
    	this.requestFocusInWindow();
    	
    	tipo = pTipo;
    	
	}
	
	public String getTipo()
	{
		return tipo;
	}
	
	private JPanel getMainPanel(String pTipo, boolean reanudable)
	{
		if (mainPanel == null)
		{
			
			mainPanel = new JPanel();
			SpringLayout spLayout = new SpringLayout();
			mainPanel.setBackground(Color.GRAY);
			mainPanel.setOpaque(true);
			mainPanel.setLayout(spLayout);
			mainPanel.add(getLogo(spLayout));
	    	mainPanel.add(getBtnSalir(spLayout, reanudable));
			
			if(pTipo.equals("pausa"))
			{
				mainPanel.add(getBtnContinuar(spLayout, reanudable));
			}
			else if(pTipo.equals("victoria"))
			{
				mainPanel.add(getLblWin(spLayout));
			}
			else if(pTipo.equals("derrota"))
			{
				mainPanel.add(getLblLose(spLayout));
			}
			revalidate();
			repaint();
	    	
		}
		return mainPanel;
	}
	
	private JButton getBtnContinuar(SpringLayout springLayout, boolean reanudable)
	{
		if(btnContinuar == null) {
			btnContinuar = new JButton("Continue");
			btnContinuar.setFont(new Font("Rockwell Extra Bold", Font.BOLD, 16));
			springLayout.putConstraint(SpringLayout.NORTH, btnContinuar, 170, SpringLayout.NORTH, mainPanel);
			springLayout.putConstraint(SpringLayout.WEST, btnContinuar, 60, SpringLayout.WEST, mainPanel);
			springLayout.putConstraint(SpringLayout.EAST, btnContinuar, 240, SpringLayout.WEST, mainPanel);
			springLayout.putConstraint(SpringLayout.SOUTH, btnContinuar, 200, SpringLayout.NORTH, mainPanel);
			btnContinuar.addMouseListener(getPPController(reanudable));
		}
		return btnContinuar;
	}
	
	private JLabel getLogo(SpringLayout springLayout)
	{
		if(lblLogo == null)
		{
			lblLogo = new JLabel("");
			springLayout.putConstraint(SpringLayout.NORTH, lblLogo, 20, SpringLayout.NORTH, mainPanel);
			springLayout.putConstraint(SpringLayout.WEST, lblLogo, 50, SpringLayout.WEST, mainPanel);
			springLayout.putConstraint(SpringLayout.SOUTH, lblLogo, -160, SpringLayout.SOUTH, mainPanel);
			springLayout.putConstraint(SpringLayout.EAST, lblLogo, 250, SpringLayout.WEST, mainPanel);
			lblLogo.setIcon(new StretchIcon(PantallaView.class.getResource("/vista/title.png")));
			lblLogo.setVisible(true);
			lblLogo.setOpaque(false);
			revalidate();
			repaint();
		}
		
		return lblLogo;
	}
	
	private JLabel getLblWin(SpringLayout springLayout)
	{
		if(lblWin == null)
		{
			lblWin = new JLabel("You Won!");
			lblWin.setFont(new Font("Rockwell Extra Bold", Font.BOLD, 20));
			lblWin.setForeground(new Color(0, 255, 0));
			springLayout.putConstraint(SpringLayout.NORTH, lblWin, 30, SpringLayout.NORTH, mainPanel);
			springLayout.putConstraint(SpringLayout.WEST, lblWin, 90, SpringLayout.WEST, mainPanel);
			springLayout.putConstraint(SpringLayout.SOUTH, lblWin, 100, SpringLayout.SOUTH, mainPanel);
			springLayout.putConstraint(SpringLayout.EAST, lblWin, 400, SpringLayout.WEST, mainPanel);
			//lblWin.setIcon(new StretchIcon(PantallaView.class.getResource("/vista/title.png")));
			lblWin.setVisible(true);
			lblWin.setOpaque(false);
			revalidate();
			repaint();
		}
		
		return lblWin;
	}
	private JLabel getLblLose(SpringLayout springLayout)
	{
		if(lblLose == null)
		{
			lblLose = new JLabel("You Lost! Try again ...");
			lblLose.setFont(new Font("Rockwell Extra Bold", Font.BOLD, 20));
			lblLose.setForeground(new Color(255, 0, 0));
			springLayout.putConstraint(SpringLayout.NORTH, lblLose, 30, SpringLayout.NORTH, mainPanel);
			springLayout.putConstraint(SpringLayout.WEST, lblLose, 20, SpringLayout.WEST, mainPanel);
			springLayout.putConstraint(SpringLayout.SOUTH, lblLose, 100, SpringLayout.SOUTH, mainPanel);
			springLayout.putConstraint(SpringLayout.EAST, lblLose, 400, SpringLayout.WEST, mainPanel);
			//lblLose.setIcon(new StretchIcon(PantallaView.class.getResource("/vista/title.png")));
			lblLose.setVisible(true);
			lblLose.setOpaque(false);
			revalidate();
			repaint();
		}
		
		return lblLose;
	}
	
	private JButton getBtnSalir(SpringLayout springLayout, boolean reanudable)
	{
		if (btnSalir == null)
		{
			btnSalir = new JButton("Main menu");
			btnSalir.setFont(new Font("Rockwell Extra Bold", Font.BOLD, 16));
			springLayout.putConstraint(SpringLayout.NORTH, btnSalir, 100, SpringLayout.NORTH, mainPanel);
			springLayout.putConstraint(SpringLayout.WEST, btnSalir, 60, SpringLayout.WEST, mainPanel);
			springLayout.putConstraint(SpringLayout.EAST, btnSalir, 240, SpringLayout.WEST, mainPanel);
			springLayout.putConstraint(SpringLayout.SOUTH, btnSalir, 130, SpringLayout.NORTH, mainPanel);
			btnSalir.addMouseListener(getPPController(reanudable));
		}
		return btnSalir;
	}
	
	private PPController getPPController(boolean reanudable)
	{
		if (controlador == null)
		{
			controlador = new PPController(reanudable);
		}
		return controlador;
	}
	
	@Override
	public void update(Observable o, Object arg) {
		
		Object[] args = (Object[])arg;
		if(args.length == 1)
		{
			String pAccion = (String)args[0];
			
		}
		
	}
	
	private class PPController implements MouseListener, KeyListener
	{
    	private Set<Integer> pressedKeys = new TreeSet<Integer>();
    	private boolean reanudable;
    	
    	public PPController(boolean pReanudable)
    	{
    		reanudable = pReanudable;
    	}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
    		Integer val = key;
            if (!pressedKeys.contains(val)) 
            {
            	 if (key == KeyEvent.VK_ESCAPE && reanudable)
                 {
            		 pressedKeys.add(e.getKeyCode());
            		 Gestor.getInstance().getTablero().seguir();
                 }
        	}
		}

		@Override
		public void keyReleased(KeyEvent e) {
        	pressedKeys.remove(e.getKeyCode());
		}

		@Override
		public void mouseClicked(MouseEvent e) {

			if (e.getSource().equals(btnContinuar))
			{
				Gestor.getInstance().getTablero().seguir();
			}
			else if (e.getSource().equals(btnSalir))
			{
				Gestor.getInstance().getTablero().seguir();
				Gestor.getInstance().getTablero().pausar("", false);
				Gestor.getInstance().getTablero().cerrar();
				PantallaModel.getMiPantalla().abrir();
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
