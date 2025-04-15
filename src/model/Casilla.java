package model;

import java.util.HashSet;

public class Casilla {
	
   //ATRIBUTOS
    private int x;
    private int y;
    private int maxContent=10;
    private Elemento[] contenido; 

    // CONSTRUCTORA
    public Casilla(int x, int y) {
        this.x = x;
        this.y = y;
        this.contenido = new Elemento[maxContent];  
    }

    // METODOS
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    public void damageElems(int pDmg, HashSet<String> targets)
    {
    	for(int i=0;i<maxContent;i++)
    	{
    		if(contenido[i]  != null)
    		{
    			if(targets.contains(contenido[i].getNombre())) 
    			{contenido[i].getHurt(1);}
    		}
    	}
    }
    
    
	public String[] getContent()
    {
    	String[] content = new String[maxContent];
    	
    	for(int i = 0;i<maxContent;i++) {
    		content[i]="";
    		if (contenido[i] != null)
    		{
    			content[i] = contenido[i].getNombre() ;
    		}
    	}
    	return content; 	
    }
    public boolean crearContent(String newContent) 
    {
    	boolean added = false;
    	if(newContent != "" && !estaContent(newContent)) 
    	{
    		int i=0;
    		while(i<maxContent && !added)
    		{
    			//if(contenido[i] != null)System.out.println("hay "+contenido[i].getNombre()+" en "+x+", "+y+" pos: "+ i);
    			if(contenido[i] == null){contenido[i] = GeneradorElementos.getGen().generarElemento(x, y, newContent);
    			//System.out.println("add "+newContent+" a "+x+", "+y);
    			added=true;}
    			i++;
    		}
    	}
    	return added;	
    }
    
    public boolean addContent(Elemento newContent) 
    {
    	boolean added = false;
    	if(newContent.getNombre() != "" && !estaContent(newContent.getNombre())) 
    	{
    		int i=0;
    		while(i<maxContent && !added)
    		{
    			//if(contenido[i] != null)System.out.println("hay "+contenido[i].getNombre()+" en "+x+", "+y+" pos: "+ i);
    			if(contenido[i] == null){contenido[i] = newContent;
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
    	while(i<maxContent)
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
    		while(i<maxContent && !removed)
    		{
    			if(contenido[i] != null)
    			{
    				if(contenido[i].getNombre().equals(newContent)) 
    				{	
    					contenido[i] = null;
    					removed=true;
    				}
    			}
    			i++;
    		}
    	}
    	return removed;
    }
    
    public void imprimirContent()
    {
    	System.out.print(x+", "+y+" contiene: {");
    	for(int i = 0;i<maxContent;i++) {
    		if (contenido[i] != null)
    		{
    			System.out.print(contenido[i].getNombre()+", ");
    		}
    		else {System.out.print("null"+", ");}
    		if(i==maxContent-1) {System.out.print("}");}
    	} 
    	System.out.println("");
    }
  
}


