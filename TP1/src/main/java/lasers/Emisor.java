package lasers;
import org.jgrapht.graph.SimpleGraph;
import java.util.ArrayList;

public class Emisor extends Vector2D{
    //private final int limiteX;
    //private final int limiteY;

    public Emisor(int x, int y, String direccion){
        super(x, y, direccion);
    }

    public SimpleGraph<Vector2D, Integer> emitirLaser(ArrayList<Bloque> bloques) {
        SimpleGraph<Vector2D, Integer> grafoLaser = new SimpleGraph<>(null);
        Vector2D inicio = new Vector2D(this.getPosX(), this.getPosY(), this.getDireccion());
        grafoLaser.addVertex(inicio);
        _emitirLaser(inicio, grafoLaser, bloques);
        return grafoLaser;
    }

    private void _emitirLaser(Vector2D padre, SimpleGraph<Vector2D, Integer> grafoLaser, ArrayList<Bloque> bloques) {
        //agregar un limite del mapa, sino es recursivo infinito
        for(Bloque bloque: bloques) {
            // si la posicion actual choca con un bloque entonces devuelv una nueva coordenada.
            if (bloque.chocaLaser(padre)) {
                //posiblesCaminos porque hay bloques que devuelven muchas direcciones.
                //Retorna un sola coordenada (null, null) si el laser es absorbido por un bloque.
                Vector2D[] posiblesCaminos = bloque.comportamientoBloque();
                for (Vector2D camino : posiblesCaminos) {
                    grafoLaser.addVertex(camino);
                    grafoLaser.addEdge(padre, camino);
                    _emitirLaser(camino, grafoLaser, bloques);
                    return;
                }

            }
        }
        Vector2D avanzar = moverDireccion(padre);
        grafoLaser.addVertex(avanzar);
        grafoLaser.addEdge(padre, avanzar);
        _emitirLaser(avanzar, grafoLaser, bloques);
    }

    private Vector2D moverDireccion(Vector2D posActual) {
        return switch (posActual.getDireccion()) {
            case "SE" -> new Vector2D(getPosX() + 1, getPosY() + 1, "SE");
            case "SW" -> new Vector2D(getPosX() - 1, getPosY() + 1, "SW");
            case "NE" -> new Vector2D(getPosX() + 1, getPosY() - 1, "NE");
            case "NW" -> new Vector2D(getPosX() - 1, getPosY() - 1, "NW");
            default -> null;
        };
    }

    @Override
    public String toString() {
        return "Emisor: " + super.toString();
    }
}
