package vista;

import model.PantallaModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Set;
import java.util.TreeSet;

public class SelectorPantallasView extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel fondo;
	private String pantalla;
	private SelPanController controlador;

	/**
	 * Create the panel.
	 */
	public SelectorPantallasView() {
		setLayout(new BorderLayout());
		add(getFondo(),BorderLayout.CENTER);
		pantalla = "stageBack1";
		addKeyListener(this.getSPController());
		setFocusable(true);
		actualizar();

	}

	private JLabel getFondo() {
		if (fondo == null) {
			fondo = new JLabel("");
		}
		return fondo;
	}
	private void actualizar()
	{
		fondo.setIcon(new StretchIcon(PantallaView.class.getResource("/vista/"+pantalla+".png")));
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
				case "stageBack1" -> pantalla = "stageBack3";
				case "stageBack2" -> pantalla = "stageBack1";
				case "stageBack3" -> pantalla = "stageBack2";
			}
		}
		else if (pNext == 1) {
			switch (pantalla) {
				case "stageBack1" -> pantalla = "stageBack2";
				case "stageBack2" -> pantalla = "stageBack3";
				case "stageBack3" -> pantalla = "stageBack1";
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
    private class SelPanController implements KeyListener
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
        
    }
}
