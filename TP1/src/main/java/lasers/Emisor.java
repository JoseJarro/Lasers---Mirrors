package lasers;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.Graph;
import org.jgrapht.traverse.DepthFirstIterator;

import java.util.ArrayList;
import java.util.List;

public class Emisor extends Vector2D{

    public Emisor(int x, int y, String direccion){
        super(x, y, direccion);
    }

    public List<Vector2D> emitirLaser(Nivel nivel) {
        Graph<Vector2D, DefaultEdge> grafoLaser = new SimpleGraph<>(DefaultEdge.class);
        Vector2D inicio = new Vector2D(this.getPosicion().getPosX(), this.getPosicion().getPosY(), this.getDireccion());
        grafoLaser.addVertex(inicio);
        _emitirLaser(inicio, grafoLaser, nivel);
        DepthFirstIterator<Vector2D, DefaultEdge> recorridoLaserIter = new DepthFirstIterator<>(grafoLaser);
        List<Vector2D> recorridoLaser = new ArrayList<>();
        while (recorridoLaserIter.hasNext()) {
            recorridoLaser.add(recorridoLaserIter.next());
        }
        System.out.println(recorridoLaser); //imprime recorrido DFS
        return recorridoLaser;
    }

    private void _emitirLaser(Vector2D padre, Graph<Vector2D, DefaultEdge> grafoLaser, Nivel nivel) {
        if (nivel.fueraDimension(padre.getPosicion())) {
            return; //cumple pero falta casos especiales
        }
        for(Bloque bloque: nivel.getBloques()) {
            if (bloque.tocaLaser(padre)) {
                Vector2D[] posiblesCaminos = bloque.comportamientosBloque(padre);
                for (Vector2D camino : posiblesCaminos) {
                    if (camino == null) { return;}
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

    @Override
    public String toString() {
        return "Emisor: " + super.toString();
    }
}
