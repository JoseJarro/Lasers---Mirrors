package lasers;

public class Celda {
    private Boolean ocupado;
    private final Coordenada posicion;

    public Celda(Coordenada posicion, boolean ocupado) {
        this.posicion = posicion;
        this.ocupado = ocupado;
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

    public  Coordenada getCoordenada(){
        return this.posicion;
    }

    @Override
    public String toString() {
        return this.posicion + ", Ocupado: " + this.ocupado;
    }
}
