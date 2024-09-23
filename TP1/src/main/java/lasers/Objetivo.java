package lasers;

import java.util.ArrayList;

public class Objetivo extends Coordenada{
    private Boolean estado;

    public Objetivo(int x, int y) {
        super(x, y);
        this.estado = false;
    }

    public Boolean objetivoAlcanzado(ArrayList<Emisor> emisores) {
        for (Emisor emisor : emisores) {
            for (Emisor laser : emisor.)
        }
        return true;
    }

    @Override
    public String toString() {
        return "Objetivo: " + super.toString();
    }
}
