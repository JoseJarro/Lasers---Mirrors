package lasers;

public class Celda extends Coordenada{
    private final Boolean piso;
    private Boolean ocupado;

    public Celda(int x, int y, boolean piso, boolean ocupado) {
        super(x, y);
        this.piso = piso;
        this.ocupado = ocupado;
    }

    public Boolean getPiso() {
        return this.piso;
    }

    public Boolean getOcupado() {
        return this.ocupado;
    }

    public void ocupar() {
        this.ocupado = true;
    }

    public void desocupar() {
        this.ocupado = false;
    }

    @Override
    public String toString() {
        return super.toString() + " esPiso: " + this.piso;
    }
}
