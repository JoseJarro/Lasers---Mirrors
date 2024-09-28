package lasers;

import java.util.ArrayList;

public class ComportamientoBloqueEspejo implements ComportamientoBloque {

    private final Vector2D[] arregloVector = null;

    @Override
    public Vector2D [] comportamientoBloque(Vector2D padre, int cara) {

        String direccion ="";
        if (cara == 1){
          if (padre.getDireccion().equals("NO")){
                direccion = "NE";
          }else if (padre.getDireccion().equals("SE")){
              direccion = "SO";
          }
        }
        if (cara == 2){
            if (padre.getDireccion().equals("SO")){
                direccion = "NO";
            }else if (padre.getDireccion().equals("SE")){
                direccion = "NE";
            }
        }
        if (cara == 3){
            if (padre.getDireccion().equals("SE")){
                direccion = "SO";
            }else if (padre.getDireccion().equals("NE")){
                direccion = "NO";
            }
        }
        if (cara == 4){
            if (padre.getDireccion().equals("NO")){
                direccion = "SO";
            }else if (padre.getDireccion().equals("NE")){
                direccion = "SE";
            }
        }
        arregloVector[0] = new Vector2D( padre.getPosicion().getPosX() ,padre.getPosicion().getPosY(),direccion );

        return arregloVector;
    }
}
