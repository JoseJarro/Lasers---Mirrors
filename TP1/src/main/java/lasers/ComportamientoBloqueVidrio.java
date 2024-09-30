package lasers;

public class ComportamientoBloqueVidrio implements ComportamientoBloque{
    @Override
    public Vector2D[] comportamientoBloque(Vector2D padre,int cara) {
        Vector2D[] arregloVector = new Vector2D[2];
        String direccion ="";
        if (cara == 1){
            if (padre.getDireccion().equals("NE")){
                direccion = "NW";
            }else if (padre.getDireccion().equals("SE")){
                direccion = "SW";
            }
        }
        if (cara == 2){
            if (padre.getDireccion().equals("NE")){
                direccion = "SE";
            }else if (padre.getDireccion().equals("NW")){
                direccion = "SW";
            }
        }
        if (cara == 3){
            if (padre.getDireccion().equals("SW")){
                direccion = "SE";
            }else if (padre.getDireccion().equals("NW")){
                direccion = "NE";
            }
        }
        if (cara == 4){
            if (padre.getDireccion().equals("SE")){
                direccion = "NE";
            }else if (padre.getDireccion().equals("SW")){
                direccion = "NW";
            }
        }

        if (direccion.isEmpty()){
            Vector2D sigue = padre.moverDireccion(padre);
            arregloVector[0] = sigue;
            return arregloVector;
        }
        Vector2D sigue = padre.moverDireccion(padre);
        arregloVector[0] = sigue;

        Vector2D cambio = new Vector2D( padre.getPosicion().getPosX() ,padre.getPosicion().getPosY(), direccion);
        arregloVector[1] = cambio.moverDireccion(cambio);

        return arregloVector;

    }
}
