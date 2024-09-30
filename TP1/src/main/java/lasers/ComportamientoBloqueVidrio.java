package lasers;

public class ComportamientoBloqueVidrio implements ComportamientoBloque{
    @Override
    public Vector2D[] comportamientoBloque(Vector2D padre,int cara) {
        Vector2D[] arregloVector = new Vector2D[2];
        var direccion ="";

        if (cara == 1){
            if (padre.getDireccion().equals("NE")){
                direccion = "NW";
            }else if (padre.getDireccion().equals("SE")){
                direccion = "SW";
            }
        }
        else if (cara == 2){
            if (padre.getDireccion().equals("NE")){
                direccion = "SE";
            }else if (padre.getDireccion().equals("NW")){
                direccion = "SW";
            }
        }
        else if (cara == 3){
            if (padre.getDireccion().equals("SW")){
                direccion = "SE";
            }else if (padre.getDireccion().equals("NW")){
                direccion = "NE";
            }
        }
        else if (cara == 4){
            if (padre.getDireccion().equals("SE")){
                direccion = "NE";
            }else if (padre.getDireccion().equals("SW")){
                direccion = "NW";
            }
        }

        if (direccion.isEmpty()){
            Vector2D sigue = padre.clonar();
            sigue.moverDireccion();
            arregloVector[0] = sigue;
            return arregloVector;
        }

        Vector2D sigue = padre.clonar();
        sigue.moverDireccion();
        arregloVector[0] = sigue;

        Vector2D cambio = padre.clonar();
        cambio.setDireccion(direccion);
        cambio.moverDireccion();
        arregloVector[1] = cambio;

        return arregloVector;

    }
}
