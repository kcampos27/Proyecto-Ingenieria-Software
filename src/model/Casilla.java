package model;

import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
		Arrays.stream(contenido).
				filter(e -> e!=null && targets.contains(e.getNombre())).
				forEach(e -> e.getHurt(pDmg));
	}

	public String[] getContent() {
		return Arrays.stream(contenido)
				.map(e -> e != null ? e.getNombre() : "")
				.toArray(String[]::new);
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
    			if(contenido[i] == null){contenido[i] = ElementosFactory.getFactory().fabricarElemento(x, y, newContent);
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
		return Arrays.stream(contenido).
				anyMatch(e -> e != null && e.getNombre().equals(pCont));
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

	public void imprimirContent() {
		System.out.print(x + ", " + y + " contiene: {");

		String contentString = IntStream.range(0, maxContent)
				.mapToObj(i -> contenido[i] != null ? contenido[i].getNombre() : "null")
				.collect(Collectors.joining(", "));

		System.out.println(contentString + "}");
	}
    
    public void pause()
    {
    	Arrays.stream(contenido).filter(c -> c != null).forEach(c -> c.detener());
    }
    
    public void goOn()
    {
    	Arrays.stream(contenido).filter(c -> c != null).forEach(c -> c.continuar());
    }
  
}


