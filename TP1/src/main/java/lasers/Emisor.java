package lasers;
import org.jgrapht.graph.SimpleGraph;
import java.util.ArrayList;

public class Emisor extends Vector2D{
    //private final int limiteX;
    //private final int limiteY;

    public Emisor(int x, int y, String direccion){
        super(x, y, direccion);
    }

    public SimpleGraph<Vector2D, Integer> emitirLaser(Nivel nivel) {
        SimpleGraph<Vector2D, Integer> grafoLaser = new SimpleGraph<>(null);
        Vector2D inicio = new Vector2D(this.getPosicion().getPosX(), this.getPosicion().getPosY(), this.getDireccion());
        grafoLaser.addVertex(inicio);
        _emitirLaser(inicio, grafoLaser, nivel);
        return grafoLaser;
    }

    private void _emitirLaser(Vector2D padre, SimpleGraph<Vector2D, Integer> grafoLaser, Nivel nivel) {
        if (nivel.fueraDimension(padre.getPosicion())) {
            return;
        }
        for(Bloque bloque: nivel.getBloques()) {
            // si la posicion actual choca con un bloque entonces devuelve una nueva coordenada. La posicion de un bloque es su centro
            if (bloque.tocaLaser(padre)) {
                //posiblesCaminos porque existen bloques que devuelven muchas direcciones.
                //Retorna un sola coordenada (null, null) si el laser es absorbido por un bloque.
                Vector2D[] posiblesCaminos = bloque.comportamientoBloque(padre);
                for (Vector2D camino : posiblesCaminos) {
                    grafoLaser.addVertex(camino);
                    grafoLaser.addEdge(padre, camino);
                    _emitirLaser(camino, grafoLaser, nivel);
                    return;
                }
            }
        }
        Vector2D avanzar = moverDireccion(padre);
        grafoLaser.addVertex(avanzar);
        grafoLaser.addEdge(padre, avanzar);
        _emitirLaser(avanzar, grafoLaser, nivel);
    }

    private Vector2D moverDireccion(Vector2D v) {
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
        return "Emisor: " + super.toString();
    }
}
