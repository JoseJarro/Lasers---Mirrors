package lasers;

public class Bloque {

    private int posicionx;
    private int posiciony;
    private final boolean fijo;
    private final ComportamientoBloque comportamientoBloque;

    Bloque(int posicionx, int posiciony,boolean fijo, ComportamientoBloque comportamientoBloque){
        this.posicionx = posicionx;
        this.posiciony = posiciony;
        this.comportamientoBloque = comportamientoBloque;
        this.fijo = fijo;

    }

    public void moverBloque(int posicionx,int posiciony){
        if (!fijo) {
            this.posicionx = posicionx;
            this.posiciony = posiciony;
        }

    }

    public Vector2D[] comportamientosBloque(Vector2D padre){
        int cara = 0;
        if (padre.getPosicion().getPosY() == posiciony){
            if (padre.getPosicion().getPosX() == posicionx -1) {
                cara = 1;
            }else if (padre.getPosicion().getPosX() == posicionx +1) {
                cara = 3;
            }
        }
        if (padre.getPosicion().getPosX() == posicionx){
            if (padre.getPosicion().getPosY() == posiciony -1){
                cara = 4;
            }else if (padre.getPosicion().getPosY() == posiciony +1) {
                cara = 2;
            }
        }
        return  this.comportamientoBloque.comportamientoBloque(padre,cara);
    }


    public boolean tocaLaser(Vector2D padre) {
        boolean toco = false;
        if (padre.getPosicion().getPosY() == posiciony){
            if (padre.getPosicion().getPosX() == posicionx -1) {
                toco = true;
            }else if (padre.getPosicion().getPosX() == posicionx +1) {
                toco = true;
            }
        }
        if (padre.getPosicion().getPosX() == posicionx){
            if (padre.getPosicion().getPosY() == posiciony -1){
                toco = true;
            }else if (padre.getPosicion().getPosY() == posiciony +1) {
                toco = true;
            }
        }
        return toco;

    }
}
