package lasers;

public enum TipoBloque {

    ESPEJO(new ComportamientoBloqueEspejo(),  false),
    VIDRIO(new ComportamientoBloqueVidrio(),  false),
    CRISTAL(new ComportamientoBloqueCristal(),  false),
    OPACO_FIJO(new ComportamientoBloqueOpaco(),  true),
    OPACO_MOVIL(new ComportamientoBloqueOpaco(), false);

    private final ComportamientoBloque comportamientoBloque;
    private final boolean fijo;

    TipoBloque (ComportamientoBloque comportamientoBloque,  boolean fijo){
       this.comportamientoBloque = comportamientoBloque;
       this.fijo = fijo;

    }

    public  boolean esfijo() {return fijo;}

    public  ComportamientoBloque getComportamiento() {return comportamientoBloque;}

}
