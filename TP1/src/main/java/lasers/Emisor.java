package lasers;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.Graph;
import org.jgrapht.traverse.DepthFirstIterator;
import java.util.ArrayList;
import java.util.List;

public class Emisor {
    private final Coordenada posicion;
    private final String direccion;

    public Emisor(Coordenada posicion, String direccion){
        this.posicion = posicion;
        this.direccion = direccion;
    }

    public Coordenada getPosicion(){
        return this.posicion;
    }

    public String getDireccion(){
        return this.direccion;
    }

    public List<Vector2D> emitirLaser(Nivel nivel) {
        Graph<Vector2D, DefaultEdge> grafoLaser = new SimpleGraph<>(DefaultEdge.class);
        var inicio = new Vector2D(this.getPosicion(), this.getDireccion());
        grafoLaser.addVertex(inicio);
        _emitirLaser(inicio, grafoLaser, nivel);
        return recorridoDFS(grafoLaser);
    }

    private List<Vector2D> recorridoDFS(Graph<Vector2D, DefaultEdge> grafo) {
        var recorridoLaser = new ArrayList<Vector2D>();
        var recorridoLaserIter = new DepthFirstIterator<>(grafo);
        while (recorridoLaserIter.hasNext()) {
            recorridoLaser.add(recorridoLaserIter.next());
        }
        System.out.println(recorridoLaser); //imprime recorrido DFS
        return recorridoLaser;
    }

    private void _emitirLaser(Vector2D padre, Graph<Vector2D, DefaultEdge> grafoLaser, Nivel nivel) {
        if (nivel.fueraDimension(padre.getPosicion())) {
            return;
        }
        for(Bloque bloque: nivel.getBloques()) {
            if (bloque.colisionaLaser(padre)) {
                for (Vector2D camino : bloque.comportamientosBloque(padre)) {
                    if (camino == null) { continue;}
                    grafoLaser.addVertex(camino);
                    grafoLaser.addEdge(padre, camino);
                    _emitirLaser(camino, grafoLaser, nivel);
                }
                return;
            }
        }
        Vector2D avanzar = padre.clonar();
        avanzar.moverDireccion();
        grafoLaser.addVertex(avanzar);
        grafoLaser.addEdge(padre, avanzar);
        _emitirLaser(avanzar, grafoLaser, nivel);
    }

    @Override
    public String toString() {
        return this.posicion.toString();
    }
}
