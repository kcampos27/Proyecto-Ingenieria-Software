package model;

import java.util.Random;

public class Empty extends TableroModel{
		
    public Empty(){}
    @Override
    public void inicializarPantalla(int pJ, int pI) {
        random = new Random();
        int p = random.nextInt(100);

        if(pI==0&&pJ==0)
        {//Bomberman
        }
        else if (p > 33 && p<=40)
        {
            //generarContent(pJ,pI,"enemigo");
            tablero[pJ][pI].imprimirContent();
        }
        else
        {
            generarContent(pJ,pI,"");
        }
        System.out.println("");
    }
}
