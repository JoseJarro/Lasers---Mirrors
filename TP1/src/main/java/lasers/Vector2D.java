package lasers;

public class Vector2D {
    private final Coordenada posicion;
    private String direccion;

    public Vector2D(Coordenada posicion, String direccion) {
        this.posicion = posicion;
        this.direccion = direccion;
    }

    public String getDireccion() {
        return this.direccion;
    }

    public Coordenada getPosicion() { return this.posicion; }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void moverDireccion() {
        Coordenada posicion = this.getPosicion();
        switch (this.getDireccion()) {
            case "SE":
                posicion.setPosX(posicion.getPosX() + 1);
                posicion.setPosY(posicion.getPosY() + 1);
                break;
            case "SW":
                posicion.setPosX(posicion.getPosX() - 1);
                posicion.setPosY(posicion.getPosY() + 1);
                break;
            case "NE":
                posicion.setPosX(posicion.getPosX() + 1);
                posicion.setPosY(posicion.getPosY() - 1);
                break;
            case "NW":
                posicion.setPosX(posicion.getPosX() - 1);
                posicion.setPosY(posicion.getPosY() - 1);
                break;
            default: System.out.println("Error. Salida no hay direccion");
        };
    }

    public Vector2D clonar() {
        var x = this.posicion.getPosX();
        var y = this.posicion.getPosY();
        var dir = this.direccion;
        return new Vector2D(new Coordenada(x, y), dir);
    }

    @Override
    public String toString() {
        return this.posicion + ", " + this.direccion;
    }

}