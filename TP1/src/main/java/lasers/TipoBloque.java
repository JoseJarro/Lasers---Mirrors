package lasers;

public enum TipoBloque {

    BLOQUE_ESPEJO(new ComportamientoBloqueEspejo(), 'R', false),
    BLOQUE_VIDRIO(new ComportamientoBloqueVidrio(), 'G', false),
    BLOQUE_CRISTAL(new ComportamientoBloqueCristal(), 'C', false),
    BLOQUE_OPACO_FIJO(new ComportamientoBloqueOpacoFijo(), 'F', true),
    BLOQUE_OPACO_MOVIL(new ComportamientoBloqueOpacoMovil(), 'M', false);

    private  final ComportamientoBloque comportamientoBloque;
    private final char caracter;
    private final boolean fijo;

   TipoBloque (ComportamientoBloque comportamientoBloque, char caracter, boolean fijo){
       this.comportamientoBloque = comportamientoBloque;
       this.caracter=caracter;
       this.fijo=fijo;

   }

   public char obtererCaracter() {return caracter;}

   public  boolean esfijo() {return fijo;}

    public  ComportamientoBloque obtenerComportamiento() {return comportamientoBloque;}

}
