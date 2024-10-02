package lasers;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.Graph;
import org.jgrapht.traverse.DepthFirstIterator;

import java.util.*;

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
        var posGiros = new HashSet<Vector2D>();
        grafoLaser.addVertex(inicio);
        _emitirLaser(inicio, grafoLaser, nivel, posGiros);
        System.out.println(grafoLaser);
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

    private void _emitirLaser(Vector2D padre, Graph<Vector2D, DefaultEdge> grafoLaser, Nivel nivel, Set<Vector2D> giros) {
        if (nivel.fueraDimension(padre.getPosicion())) {
            return;
        }
        if (padre.getPosicion().getPosX() == 5 && padre.getPosicion().getPosY() == 8) {
            System.out.println("aaa");
        }
        var direccion = padre.getDireccion();
        for(Bloque bloque: nivel.getBloques()) {
            if (bloque.colisionaLaser(padre)) {
                var caminos = bloque.comportamientosBloque(padre);
                for (Vector2D camino : caminos) {
                    if (camino == null) { continue;}
                    if (giros.contains(padre)) {
                        continue;
                    }
                    if (!(direccion.equals(padre.getDireccion())) && camino.equals(padre)) {
                        giros.add(padre);
                        _emitirLaser(camino, grafoLaser, nivel, giros);
                        continue;
                    }
                    grafoLaser.addVertex(camino);
                    grafoLaser.addEdge(padre, camino);
                    _emitirLaser(camino, grafoLaser, nivel, giros);
                }
                return;
            }
        }
        Vector2D avanzar = padre.clonar();
        avanzar.moverDireccion();
        grafoLaser.addVertex(avanzar);
        grafoLaser.addEdge(padre, avanzar);
        _emitirLaser(avanzar, grafoLaser, nivel, giros);
    }

    @Override
    public String toString() {
        return this.posicion.toString();
    }
}
