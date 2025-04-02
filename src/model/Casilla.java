package model;

public class Casilla {
	
   //ATRIBUTOS
    private int x;
    private int y;
    private Elemento[] contenido; 

    // CONSTRUCTORA
    public Casilla(int x, int y) {
        this.x = x;
        this.y = y;
        this.contenido = new Elemento[3];  
    }

    // METODOS
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    public void damageElems(int pDmg)
    {
    	for(int i=0;i<3;i++)
    	{
    		if(contenido[i] != null)
    		{
    			contenido[i].getHurt(pDmg);
    		}
    	}
    }
    
	public String[] getContent()
    {
    	String[] content = new String[3];
    	
    	for(int i = 0;i<3;i++) {
    		content[i]="";
    		if (contenido[i] != null)
    		{
    			content[i] = contenido[i].getNombre() ;
    		}
    	}
    	return content; 	
    }
    public boolean addContent(String newContent) 
    {
    	boolean added = false;
    	if(newContent != "" && !estaContent(newContent)) 
    	{
    		int i=0;
    		while(i<3 && !added)
    		{
    			//if(contenido[i] != null)System.out.println("hay "+contenido[i].getNombre()+" en "+x+", "+y+" pos: "+ i);
    			if(contenido[i] == null){contenido[i] = Elemento.escogerTipoElemento(x,y,newContent);
    			//System.out.println("add "+newContent+" a "+x+", "+y);
    			added=true;}
    			i++;
    		}
    	}
    	return added;	
    }
    
    public boolean estaContent(String pCont)
    {
    	boolean enc = false;
    	int i = 0;
    	while(i<3)
    	{
    		if(contenido[i] != null) {if(contenido[i].getNombre().equals(pCont)) {enc = true;}}
    		i++;
    	}
    	return enc; 
    }
    
    public boolean removeContent(String newContent) 
    {
    	boolean removed = false;
        int i=0;
    	if(newContent !="")
    	{
    		while(i<3 && !removed)
    		{
    			if(contenido[i] != null)
    			{
    				if(contenido[i].getNombre().equals(newContent)) 
    				{contenido[i] = null;
    				removed=true;}
    			}
    			i++;
    		}
    	}
    	return removed;
    }
    
    public void imprimirContent()
    {
    	System.out.print(x+", "+y+" contiene: {");
    	for(int i = 0;i<3;i++) {
    		if (contenido[i] != null)
    		{
    			System.out.print(contenido[i].getNombre()+", ");
    		}
    		else {System.out.print("null"+", ");}
    		if(i==2) {System.out.print("}");}
    	} 
    	System.out.println("");
    }
  
}


