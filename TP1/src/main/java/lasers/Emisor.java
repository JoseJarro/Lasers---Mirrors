package lasers;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.Graph;

import java.util.*;

public class Emisor {
    private final Coordenada posicion;
    private final String direccion;
    private Graph<Vector2D, DefaultEdge> laser;

    public Emisor(Coordenada posicion, String direccion){
        this.posicion = posicion;
        this.direccion = direccion;
    }

    public Graph<Vector2D, DefaultEdge> getLaser(){
        return laser;
    }

    public Coordenada getPosicion(){
        return this.posicion;
    }

    public String getDireccion(){
        return this.direccion;
    }

    public Graph<Vector2D, DefaultEdge> emitirLaser(Nivel nivel) {
        laser = new SimpleGraph<>(DefaultEdge.class);
        var inicio = new Vector2D(this.getPosicion(), this.getDireccion());
        var posGiros = new HashSet<Vector2D>();
        laser.addVertex(inicio);
        _emitirLaser(inicio, laser, nivel, posGiros);
        return laser;
    }


    private void _emitirLaser(Vector2D padre, Graph<Vector2D, DefaultEdge> grafoLaser, Nivel nivel, Set<Vector2D> giros) {
        if (nivel.fueraDimension(padre.getPosicion())) {
            return;
        }

        var direccion = padre.getDireccion();
        for(Bloque bloque: nivel.getBloques()) {
            if (bloque.colisionaLaser(padre)) {
                colisionarBloque(padre,grafoLaser,nivel,giros,bloque,direccion);
                return;
            }
        }

        avanzarLazer(padre,grafoLaser,nivel,giros);
    }


    private void avanzarLazer (Vector2D padre ,Graph<Vector2D, DefaultEdge> grafoLaser,Nivel nivel, Set<Vector2D> giros) {
        Vector2D avanzar = padre.clonar();
        avanzar.moverDireccion();
        grafoLaser.addVertex(avanzar);
        grafoLaser.addEdge(padre, avanzar);
        _emitirLaser(avanzar, grafoLaser, nivel, giros);
    }


    private void colisionarBloque(Vector2D padre, Graph<Vector2D, DefaultEdge> grafoLaser,Nivel nivel,Set<Vector2D> giros, Bloque bloque, String direccion){

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
    }


    @Override
    public String toString() {
        return this.posicion.toString();
    }
}
