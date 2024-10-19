package lasers;

public class ComportamientoBloqueCristal implements ComportamientoBloque{

    @Override
    public CustomVector[] comportamientoBloque(CustomVector padre, CaraBloque cara) {
        int posX = 0;
        int posY = 0;
        var posicion = padre.getPosicion();
        CustomVector[] arregloVector = new CustomVector[1];

        switch (cara) {
            case IZQUIERDA:
                posX = posicion.getPosX() + 2;
                posY = posicion.getPosY();
                break;
            case ABAJO:
                posX = posicion.getPosX();
                posY = posicion.getPosY() - 2;
                break;
            case DERECHA:
                posX = posicion.getPosX() - 2;
                posY = posicion.getPosY();
                break;
            case ARRIBA:
                posX = posicion.getPosX();
                posY = posicion.getPosY() + 2;
                break;
        }
        arregloVector[0]=new CustomVector(new Coordenada(posX, posY),padre.getDireccion());
        return arregloVector;
    }
}
