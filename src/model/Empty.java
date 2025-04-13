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
            System.out.println("BOMBERMAN");
            generarContent(pJ,pI,"bombermanW");
            tablero[pJ][pI].imprimirContent();
        }
        else
        {
            generarContent(pJ,pI,"");
        }
        System.out.println("");
    }
}
