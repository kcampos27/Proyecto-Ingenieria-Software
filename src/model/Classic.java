package model;

import java.util.Random;

public class Classic extends TableroModel {
    public Classic() {super();}

    @Override
    public void inicializarPantalla(int pJ, int pI) {
        int p = getRandom().nextInt(100);

        if(pI==0&&pJ==0)
        {//Bomberman
        }
        else if(pI%2!=0 && pJ%2!=0)
        {//Bloques duros
            generarContent(pJ,pI,"bloqueD");
            getCasilla(pJ,pI).imprimirContent();
        }
        //NO OCURRE NADA
        else if( (pI == 1 && pJ == 1)
                || (pI == 1 && pJ == 0) || (pI == 0 && pJ == 1)){
            generarContent(pJ,pI,"");
        }
        else if((pI==0 && pJ == 2) || (pI==2 && pJ==0))
        {
            generarContent(pJ,pI,"bloqueB");
            getCasilla(pJ,pI).imprimirContent();
        }
        else
        {
            if (p <= 33)
            {
                generarContent(pJ,pI,"bloqueB");
                getCasilla(pJ,pI).imprimirContent();
            }
            else if (p > 33 && p<=40)
            {
                generarContent(pJ,pI,"enemigo");
                getCasilla(pJ,pI).imprimirContent();
            }
            else
            {
                generarContent(pJ,pI,"");
                getCasilla(pJ,pI).imprimirContent();
            }
        }
        System.out.println("");
    }
}
