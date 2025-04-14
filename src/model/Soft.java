package model;

import java.util.Random;

public class Soft extends TableroModel {
    public Soft ()
    {
        super();
    }
    @Override
    public void inicializarPantalla(int pJ, int pI) {
        random = new Random();
        int p = random.nextInt(100);

        if(pI==0&&pJ==0)
        {//Bomberman
        }
        else if(pI%2!=0 && pJ%2!=0)
        {//Bloques blandos
            generarContent(pJ,pI,"bloqueB");
            tablero[pJ][pI].imprimirContent();
        }
        //NO OCURRE NADA
        else if( (pI == 1 && pJ == 1)
                || (pI == 1 && pJ == 0) || (pI == 0 && pJ == 1)){
            generarContent(pJ,pI,"");
        }
        else if((pI==0 && pJ == 2) || (pI==2 && pJ==0))
        {
            generarContent(pJ,pI,"bloqueB");
            tablero[pJ][pI].imprimirContent();
        }
        else
        {
            if (p <= 33)
            {
                generarContent(pJ,pI,"bloqueB");
                tablero[pJ][pI].imprimirContent();
            }
            else if (p > 33 && p<=40)
            {
                generarContent(pJ,pI,"enemigo");
                tablero[pJ][pI].imprimirContent();
            }
            else
            {
                generarContent(pJ,pI,"");
                tablero[pJ][pI].imprimirContent();
            }
        }
        System.out.println("");
    }
}
