package lasers;

public class VerificadorNivel {

    public Boolean parametrosValidos(String[] parametros, Nivel nivel) {
        if (parametros.length < 3) {
            return false;
        }
        var posicion = esCoordenadaValida(parametros);
        if (posicion == null) {
            return false;
        }
        if (nivel.fueraDimension(posicion)) {
            return false;
        }
        if (posicion.getPosX() % 2 == 0 && posicion.getPosY() % 2 == 0) {}
        return switch (parametros[0]) {
            case "G" -> true;
            case "E" -> validarDireccion(parametros);
            default -> false;
        };
    }

    private Coordenada esCoordenadaValida(String[] parametros) {
        try {
            var x = Integer.parseInt(parametros[1]);
            var y = Integer.parseInt(parametros[2]);
            if ((x % 2 == 0 && y % 2 != 0) || (x % 2 != 0 && y % 2 == 0)) {
                return new Coordenada(x, y);
            }
            return null;

        } catch (NumberFormatException e) {
            System.err.println("Error. Coordenada no válida");
        }
        return null;
    }

    private Boolean validarDireccion(String[] parametros) {
        var direccion = parametros[3];
        return switch (direccion) {
            case "NE" -> true;
            case "SE" -> true;
            case "SW" -> true;
            case "NW" -> true;
            default -> false;
        };
    }
}

