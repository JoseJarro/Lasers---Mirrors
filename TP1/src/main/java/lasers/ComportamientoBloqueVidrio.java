package lasers;

public class ComportamientoBloqueVidrio implements ComportamientoBloque{
    @Override
    public CustomVector[] comportamientoBloque(CustomVector padre, CaraBloque cara) {
        CustomVector[] arregloVector = new CustomVector[2];
        var direccion = "";
        direccion = switch (cara) {
            case IZQUIERDA -> switch (padre.getDireccion()) {
                case Direccion.NORESTE -> Direccion.NOROESTE;
                case Direccion.SURESTE -> Direccion.SUROESTE;
                default -> direccion;
            };
            case ABAJO -> switch (padre.getDireccion()) {
                case Direccion.NORESTE -> Direccion.SURESTE;
                case Direccion.NOROESTE -> Direccion.SUROESTE;
                default -> direccion;
            };
            case DERECHA -> switch (padre.getDireccion()) {
                case Direccion.SUROESTE -> Direccion.SURESTE;
                case Direccion.NOROESTE -> Direccion.NORESTE;
                default -> direccion;
            };
            case ARRIBA -> switch (padre.getDireccion()) {
                case Direccion.SURESTE -> Direccion.NORESTE;
                case Direccion.SUROESTE -> Direccion.NOROESTE;
                default -> direccion;
            };
            default -> "";
        };

        CustomVector continuo = padre.clonar();
        continuo.moverDireccion();

        padre.setDireccion(direccion);
        arregloVector[0] = continuo;
        arregloVector[1] = padre;

        return arregloVector;

    }
}
