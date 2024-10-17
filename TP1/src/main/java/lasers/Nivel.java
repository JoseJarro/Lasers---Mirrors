package lasers;
import java.util.*;

public class Nivel {
    private final String nivel;
    private final List<Celda> celdas = new ArrayList<>();
    private final List<Bloque> bloques = new ArrayList<>();
    private final List<Emisor> emisores = new ArrayList<>();
    private final List<Coordenada> objetivos = new ArrayList<>();
    private Coordenada dimension;

    //CARGA DE OBJETOS
    public Nivel(LevelLoader cargador) {
        this.nivel = cargador.getArchivo();
        cargador.cargarObjetos(this);
    }

    public Boolean esNivelCompletado() {
        for (Coordenada objetivo : this.objetivos) {
            var encontrado = false;
            for (Emisor emisor : this.emisores) {
                var puntosLaser = emisor.emitirLaser(this).vertexSet();
                if (puntosLaser.contains(new CustomVector(objetivo, ""))) {
                    encontrado = true;
                }
            }
            if (!encontrado) {
                return false;
            }
        }
        return true;
    }

    public Boolean fueraDimension(Coordenada posicion) {
        if (posicion.getPosX() > this.dimension.getPosX() || posicion.getPosX() < 0) {
            return true;
        }
        else return posicion.getPosY() > this.dimension.getPosY() || posicion.getPosY() < 0;
    }

    public void moverBloque(Celda inicial, Celda fin) {
        Bloque bloqueInicial = null;
        Bloque bloqueFinal = null;
        for (Bloque bloque : bloques) {
            var posBloque = bloque.getCoordenada();
            if (posBloque.equals(inicial.getCoordenada())) {
                bloqueInicial = bloque;
            }
            else if (posBloque.equals(fin.getCoordenada())) {
                bloqueFinal = bloque;
            }
        }
        if (bloqueInicial == null) {
            return;
        }
        var posInicial = inicial.getCoordenada().clonar();
        var posFinal = fin.getCoordenada().clonar();
        if (bloqueFinal == null) {
            inicial.desocupar();
            fin.ocupar();
            bloqueInicial.moverBloque(posFinal.getPosX(), posFinal.getPosY());
        } else if (!bloqueFinal.esFijo() && !bloqueInicial.esFijo()) {
            bloqueInicial.moverBloque(posFinal.getPosX(), posFinal.getPosY());
            bloqueFinal.moverBloque(posInicial.getPosX(), posInicial.getPosY());
        }
    }

    //SETTERS
    public void setDimension(Coordenada dimension) {
        this.dimension = dimension;
    }

    public void agregarCelda(Celda celda) {
        this.celdas.add(celda);
    }

    public void agregarBloque(Bloque bloque) {
        this.bloques.add(bloque);
    }

    public void agregarEmisor(Emisor emisor) {
        this.emisores.add(emisor);
    }

    public void agregarObjetivo(Coordenada objetivo) {
        this.objetivos.add(objetivo);
    }

    //GETS ATRIBUTOS
    public List<Emisor> getEmisores() {
        return this.emisores;
    }

    public List<Bloque> getBloques() {
        return this.bloques;
    }

    public List<Celda> getCeldas() {
        return this.celdas;
    }

    public Coordenada getDimension() {return dimension;}

    public List<Coordenada> getObjetivos() {return objetivos;}

    @Override
    public String toString() {
        return this.nivel;
    }
}


