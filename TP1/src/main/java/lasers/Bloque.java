package lasers;

public class Bloque {

    private Coordenada posicion;
    private final boolean fijo;
    private final ComportamientoBloque comportamientoBloque;
    private final char tipo;

    Bloque(Coordenada posicion, boolean fijo, ComportamientoBloque comportamientoBloque,char tipo){
        this.posicion = posicion;
        this.comportamientoBloque = comportamientoBloque;
        this.fijo = fijo;
        this.tipo=tipo;

    }

    public void moverBloque(int x,int y){
        if (!fijo) {
            this.posicion.setPosX(x);
            this.posicion.setPosY(y);
        }
    }

    public Boolean esFijo() {
        return fijo;
    }

    private CaraBloque obtenerCaraColisionada(CustomVector padre) {
        CaraBloque cara = CaraBloque.NO_ENCONTRADO;
        int posBloqueX = posicion.getPosX();
        int posBloqueY = posicion.getPosY();

        if (padre.getPosicion().getPosY() == posBloqueY){
            if (padre.getPosicion().getPosX() == posBloqueX - 1) {
                cara = CaraBloque.IZQUIERDA;
            }else if (padre.getPosicion().getPosX() == posBloqueX + 1) {
                cara = CaraBloque.DERECHA;
            }
        }
        else if (padre.getPosicion().getPosX() == posBloqueX){
            if (padre.getPosicion().getPosY() == posBloqueY - 1){
                cara = CaraBloque.ARRIBA;
            }else if (padre.getPosicion().getPosY() == posBloqueY + 1) {
                cara = CaraBloque.ABAJO;
            }
        }
        return cara;
    }

    public CustomVector[] comportamientosBloque(CustomVector v){
        CaraBloque cara = obtenerCaraColisionada(v);
        return this.comportamientoBloque.comportamientoBloque(v,cara);
    }

    public boolean colisionaLaser(CustomVector v) {
        CaraBloque cara = obtenerCaraColisionada(v);
        var direccion = v.getDireccion();
        switch (cara){
            case IZQUIERDA:
                if (direccion.equals(Direccion.SURESTE) || direccion.equals(Direccion.NORESTE)) {
                    return true;
                }
                break;
            case ABAJO:
                if (direccion.equals(Direccion.NORESTE) || direccion.equals(Direccion.NOROESTE)) {
                    return true;
                }
                break;
            case DERECHA:
                if (direccion.equals(Direccion.NOROESTE) || direccion.equals(Direccion.SUROESTE)) {
                    return true;
                }
                break;
            case ARRIBA:
                if (direccion.equals(Direccion.SURESTE) || direccion.equals(Direccion.SUROESTE)) {
                    return true;
                }
                break;
        }
        return false;
    }

    public Coordenada getCoordenada() {
        return this.posicion;
    }

    public char getTipo(){return this.tipo;}

}
