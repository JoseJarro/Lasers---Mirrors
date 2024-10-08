package lasers;

public class ComportamientoBloqueVidrio implements ComportamientoBloque{
    @Override
    public Vector2D[] comportamientoBloque(Vector2D padre,int cara) {
        Vector2D[] arregloVector = new Vector2D[2];
        var direccion = "";
        direccion = switch (cara) {
            case 1 -> switch (padre.getDireccion()) {
                case "NE" -> "NW";
                case "SE" -> "SW";
                default -> direccion;
            };
            case 2 -> switch (padre.getDireccion()) {
                case "NE" -> "SE";
                case "NW" -> "SW";
                default -> direccion;
            };
            case 3 -> switch (padre.getDireccion()) {
                case "SW" -> "SE";
                case "NW" -> "NE";
                default -> direccion;
            };
            case 4 -> switch (padre.getDireccion()) {
                case "SE" -> "NE";
                case "SW" -> "NW";
                default -> direccion;
            };
            default -> "";
        };

        Vector2D continuo = padre.clonar();
        continuo.moverDireccion();

        padre.setDireccion(direccion);
        arregloVector[0] = continuo;
        arregloVector[1] = padre;

        return arregloVector;

    }
}
