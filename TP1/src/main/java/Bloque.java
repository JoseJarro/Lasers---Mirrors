public class Bloque {

    private int posicionx;
    private int posiciony;
    private final boolean fijo;
    private ComportamientoBloque comportamientoBloque;

    Bloque(int posicionx, int posiciony,boolean fijo, ComportamientoBloque comportamientoBloque){
        this.posicionx = posicionx;
        this.posiciony = posiciony;
        this.fijo = fijo;
        this.comportamientoBloque= comportamientoBloque;

    }

    public void moverBloque(int posicionx,int posiciony){
        if (!fijo) {
            this.posicionx = posicionx;
            this.posiciony = posiciony;
        }

    }

    public void ejecutarComportamientoBloque(){
        comportamientoBloque.comportamientoBloque();

    }

}
