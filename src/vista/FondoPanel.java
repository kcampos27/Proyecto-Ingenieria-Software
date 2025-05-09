package vista;

import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JPanel;

public class FondoPanel extends JPanel {
	private int ancho = 17;
    private int alto = 11;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String imagenFondo;

    public FondoPanel(String imagenFondo) {
        this.imagenFondo = imagenFondo;
        // Para que se vean los componentes encima
        setLayout(new GridLayout(alto, ancho, 1, 1));
        revalidate();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (imagenFondo != null) {
            g.drawImage(new StretchIcon(getClass().getResource("/vista/sprites/"+imagenFondo+".png")).getImage(), 0, 0, getWidth(), getHeight(), this);
        }
    }
	

}
