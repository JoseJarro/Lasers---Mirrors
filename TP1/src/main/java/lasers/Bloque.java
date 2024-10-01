package lasers;

public class Bloque {

    private Coordenada posicion;
    private final boolean fijo;
    private final ComportamientoBloque comportamientoBloque;

    Bloque(Coordenada posicion, boolean fijo, ComportamientoBloque comportamientoBloque){
        this.posicion = posicion;
        this.comportamientoBloque = comportamientoBloque;
        this.fijo = fijo;

    }

    public void moverBloque(int x,int y){
        if (!fijo) {
            this.posicion.setPosX(x);
            this.posicion.setPosY(y);
        }
    }


    private Integer obtenerCaraColisionada(Vector2D padre) {
        int cara = -1;
        int posBloqueX = posicion.getPosX();
        int posBloqueY = posicion.getPosY();

        if (padre.getPosicion().getPosY() == posBloqueY){
            if (padre.getPosicion().getPosX() == posBloqueX - 1) {
                cara = 1;
            }else if (padre.getPosicion().getPosX() == posBloqueX + 1) {
                cara = 3;
            }
        }
        else if (padre.getPosicion().getPosX() == posBloqueX){
            if (padre.getPosicion().getPosY() == posBloqueY - 1){
                cara = 4;
            }else if (padre.getPosicion().getPosY() == posBloqueY + 1) {
                cara = 2;
            }
        }
        return cara;
    }

    public Vector2D[] comportamientosBloque(Vector2D v){
        int cara = obtenerCaraColisionada(v);
        return this.comportamientoBloque.comportamientoBloque(v,cara);
    }

    public boolean colisionaLaser(Vector2D v) {
        int cara = obtenerCaraColisionada(v);
        switch (cara){
            case 1:
                if (v.getDireccion().equals("SE") || v.getDireccion().equals("NE")) {
                    return true;
                }
                break;
            case 2:
                if (v.getDireccion().equals("NE") || v.getDireccion().equals("NW")) {
                    return true;
                }
                break;
            case 3:
                if (v.getDireccion().equals("NW") || v.getDireccion().equals("SW")) {
                    return true;
                }
                break;
            case 4:
                if (v.getDireccion().equals("SE") || v.getDireccion().equals("SW")) {
                    return true;
                }
                break;
        }
        return false;
    }

    public Coordenada getCoordenada(){
        return this.posicion;
    }
}
