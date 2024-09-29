package lasers;

public class ComportamientoBloqueVidrio implements ComportamientoBloque{


    private final Vector2D[] arregloVector = null;
    @Override
    public Vector2D[] comportamientoBloque(Vector2D padre,int cara) {
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
        arregloVector[0] = new Vector2D( padre.getPosicion().getPosX() ,padre.getPosicion().getPosY(),direccion );
        arregloVector[1] = new Vector2D( padre.getPosicion().getPosX() ,padre.getPosicion().getPosY(), padre.getDireccion() );

        return arregloVector;

    }
}
