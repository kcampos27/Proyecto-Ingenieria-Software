package model;

public class StrategySuper implements StrategySoltarBomba{
    public void soltarBomba(BomberMan pBomber) {
        if (pBomber.tipo.equals("blanco")){
            if (pBomber.bombasActivas < 10 &&
                    (Gestor.getInstance().getTablero().casillaIncluye(pBomber.x, pBomber.y, "") ||
                            Gestor.getInstance().getTablero().casillaIncluye(pBomber.x, pBomber.y, pBomber.nombre))) {
                Gestor.getInstance().getTablero().eliminarContent(pBomber.x, pBomber.y, pBomber.nombre);
                pBomber.cambiarTipo("bomberBomba");
                Gestor.getInstance().getTablero().aniadirContent(pBomber.x, pBomber.y, pBomber);
                pBomber.cambiarTipo("bombermanW");
                Gestor.getInstance().getTablero().aniadirContent(pBomber.x, pBomber.y, new BombaSuper(pBomber.x, pBomber.y));
                pBomber.bombasActivas++;
                System.out.println("BOMBA ULTRA colocada. Activas: " + pBomber.bombasActivas);
            }
        }
        else if (pBomber.tipo.equals("negro")){
            if (pBomber.bombasActivas < 10 &&
                    (Gestor.getInstance().getTablero().casillaIncluye(pBomber.x, pBomber.y, "") ||
                            Gestor.getInstance().getTablero().casillaIncluye(pBomber.x, pBomber.y, pBomber.nombre))) {
                Gestor.getInstance().getTablero().eliminarContent(pBomber.x, pBomber.y, pBomber.nombre);
                pBomber.cambiarTipo("blackwithbomb1");
                Gestor.getInstance().getTablero().aniadirContent(pBomber.x, pBomber.y, pBomber);
                pBomber.cambiarTipo("bombermanN");
                Gestor.getInstance().getTablero().aniadirContent(pBomber.x, pBomber.y, new BombaSuper(pBomber.x, pBomber.y));
                pBomber.bombasActivas++;
                System.out.println("BOMBA ULTRA colocada. Activas: " + pBomber.bombasActivas);
            }
        }
    }
}
