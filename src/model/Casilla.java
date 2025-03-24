package model;

public class Casilla {
	
   //ATRIBUTOS
    private int x;
    private int y;
    private Elemento contenido; 

    // CONSTRUCTORA
    public Casilla(int x, int y) {
        this.x = x;
        this.y = y;
        this.contenido = null;  
    }

    // METODOS
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    public String getContent()
    {
    	String content = "";
    	if (contenido != null)
    	{
    		content = contenido.getNombre() ;
    	}
    	return content;
    }
    public void setContent(String newContent) {
    	
    	contenido = Elemento.escogerTipoElemento(x,y,newContent);	
    }
  
}


