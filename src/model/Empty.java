package model;

import java.util.Random;

public class Empty extends TableroModel{
		
    public Empty(){}
    @Override
    public void inicializarPantalla(int pJ, int pI) {
        int p = getRandom().nextInt(100);

        if((pI==0&&pJ==0) || (pI==0&&pJ==1) || (pI==1&&pJ==0))
        {//Bomberman
        }
        else if (p > 33 && p<=40)
        {
            generarContent(pJ,pI,"enemigo");
            sumarEnemigos(1);
            getCasilla(pJ,pI).imprimirContent();
        }
        else if (p > 40 && p<=42)
        {
            generarContent(pJ,pI,"enemigoR");
            sumarEnemigos(1);
            getCasilla(pJ,pI).imprimirContent();
        }
        else
        {
            generarContent(pJ,pI,"");
        }
        System.out.println("");
    }
}
