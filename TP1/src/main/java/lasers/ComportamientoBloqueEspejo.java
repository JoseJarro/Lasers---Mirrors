package lasers;

import java.util.ArrayList;

public class ComportamientoBloqueEspejo implements ComportamientoBloque {

    private final Vector2D[] arregloVector = new Vector2D[1];

    @Override
    public Vector2D [] comportamientoBloque(Vector2D padre, int cara) {

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
        padre.setDireccion(direccion);
        Vector2D cambio = padre.moverDireccion(padre);
        arregloVector[0] = cambio;

        return arregloVector;
    }
}
