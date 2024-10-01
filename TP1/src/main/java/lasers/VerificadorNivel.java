package lasers;

public class VerificadorNivel {

    public Boolean parametrosValidos(String[] parametros, Nivel nivel) {
        if (parametros.length < 3) {
            return false;
        }
        if (!(posicionValida(parametros))) {
            return false;
        }
        var x = Integer.parseInt(parametros[1]);
        var y = Integer.parseInt(parametros[2]);
        if (nivel.fueraDimension(new Coordenada(x, y))) {
            return false;
        }
        return switch (parametros[0]) {
            case "G" -> true;
            case "E" -> validarDireccion(parametros);
            default -> false;
        };
    }

    private Boolean posicionValida(String[] parametros) {
        try {
            Integer.parseInt(parametros[1]);
            Integer.parseInt(parametros[2]);
            return true;

        } catch (NumberFormatException e) {
            System.err.println("Error. Coordenada no válida");
        }
        return false;
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

