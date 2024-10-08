package lasers;

public class ComportamientoBloqueCristal implements ComportamientoBloque{

    @Override
    public Vector2D[] comportamientoBloque(Vector2D padre,int cara) {
        int posX = 0;
        int posY = 0;
        var posicion = padre.getPosicion();
        Vector2D[] arregloVector = new Vector2D[1];

        switch (cara) {
            case 1:
                posX = posicion.getPosX() + 2;
                posY = posicion.getPosY();
                break;
            case 2:
                posX = posicion.getPosX();
                posY = posicion.getPosY() - 2;
                break;
            case 3:
                posX = posicion.getPosX() - 2;
                posY = posicion.getPosY();
                break;
            case 4:
                posX = posicion.getPosX();
                posY = posicion.getPosY() + 2;
                break;
        }
        arregloVector[0]=new Vector2D(new Coordenada(posX, posY),padre.getDireccion());
        return arregloVector;
    }
}
