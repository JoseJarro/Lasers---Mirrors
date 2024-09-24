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

    //public Vector2D agregar(Vector2D v) {
    //    return new Vector2D(this.getPosX() + v.getPosX(), this.getPosY() + v.getPosY());
    //}

    //public Vector2D invertX() {
    //    return new Vector2D(-x, y);
    //}

    //public Vector2D invertY() {
    //    return new Vector2D(x, -y);
    //}

    @Override
    public String toString() {
        return super.toString();
    }
}