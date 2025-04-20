package model;

import java.util.Random;

public class Empty extends TableroModel{
		
    public Empty(){}
    @Override
    public void inicializarPantalla(int pJ, int pI) {
        int p = getRandom().nextInt(100);

        if(pI==0&&pJ==0)
        {//Bomberman
        }
        else if (p > 33 && p<=40)
        {
            generarContent(pJ,pI,"enemigo");
            getCasilla(pJ,pI).imprimirContent();
        }
        else
        {
            generarContent(pJ,pI,"");
        }
        System.out.println("");
    }
}
