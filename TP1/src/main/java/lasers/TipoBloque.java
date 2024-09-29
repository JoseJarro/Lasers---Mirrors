package lasers;

public enum TipoBloque {

    BLOQUE_ESPEJO(new ComportamientoBloqueEspejo(),  false),
    BLOQUE_VIDRIO(new ComportamientoBloqueVidrio(),  false),
    BLOQUE_CRISTAL(new ComportamientoBloqueCristal(),  false),
    BLOQUE_OPACO_FIJO(new ComportamientoBloqueOpaco(),  true),
    BLOQUE_OPACO_MOVIL(new ComportamientoBloqueOpaco(), false);

    private  final ComportamientoBloque comportamientoBloque;
    private final boolean fijo;

   TipoBloque (ComportamientoBloque comportamientoBloque,  boolean fijo){
       this.comportamientoBloque = comportamientoBloque;
       this.fijo=fijo;

   }

   public  boolean esfijo() {return fijo;}

    public  ComportamientoBloque obtenerComportamiento() {return comportamientoBloque;}

}
