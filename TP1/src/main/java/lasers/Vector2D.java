package lasers;

public class Vector2D {
    private Coordenada posicion;
    private String direccion;

    public Vector2D(int x, int y, String direccion) {
        this.posicion = new Coordenada(x, y);
        this.direccion = direccion;
    }

    public String getDireccion() {
        return this.direccion;
    }

    public Coordenada getPosicion() { return this.posicion; }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    //public Vector2D agregar(Vector2D v) {
    //    return new Vector2D(this.getPosX() + v.getPosX(), this.getPosY() + v.getPosY());
    //}

    //public Vector2D invertX() {
    //    return new Vector2D(-x, y);
    //}

    //public Vector2D invertY() {
    //    return new Vector2D(x, -y);
    //}

    public Vector2D moverDireccion(Vector2D v) {
        Coordenada posicion = v.getPosicion();
        return switch (v.getDireccion()) {
            case "SE" -> new Vector2D(posicion.getPosX() + 1, posicion.getPosY() + 1, "SE");
            case "SW" -> new Vector2D(posicion.getPosX() - 1, posicion.getPosY() + 1, "SW");
            case "NE" -> new Vector2D(posicion.getPosX() + 1, posicion.getPosY() - 1, "NE");
            case "NW" -> new Vector2D(posicion.getPosX() - 1, posicion.getPosY() - 1, "NW");
            default -> null;
        };
    }

    @Override
    public String toString() {
        return String.format("(%d, %d) %s", this.posicion.getPosX(), this.posicion.getPosY(), this.direccion);
    }

}