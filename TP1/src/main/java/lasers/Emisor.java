package lasers;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.Graph;

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

    public Graph<CustomVector, DefaultEdge> emitirLaser(Nivel nivel) {
        Graph<CustomVector, DefaultEdge> laser = new SimpleGraph<>(DefaultEdge.class);
        var inicio = new CustomVector(this.getPosicion(), this.getDireccion());
        var posGiros = new HashSet<CustomVector>();
        laser.addVertex(inicio);
        emitirLaserRec(inicio, laser, nivel, posGiros);
        return laser;
    }

    private void emitirLaserRec(CustomVector padre, Graph<CustomVector, DefaultEdge> grafoLaser, Nivel nivel, Set<CustomVector> giros) {
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

    private void avanzarLazer (CustomVector padre , Graph<CustomVector, DefaultEdge> grafoLaser, Nivel nivel, Set<CustomVector> giros) {
        CustomVector avanzar = padre.clonar();
        avanzar.moverDireccion();
        grafoLaser.addVertex(avanzar);
        grafoLaser.addEdge(padre, avanzar);
        emitirLaserRec(avanzar, grafoLaser, nivel, giros);
    }

    private void colisionarBloque(CustomVector padre, Graph<CustomVector, DefaultEdge> grafoLaser, Nivel nivel, Set<CustomVector> giros, Bloque bloque, String direccion){
        var caminos = bloque.comportamientosBloque(padre);
        for (CustomVector camino : caminos) {
            if (camino == null) { continue;}
            if (giros.contains(padre)) {
                continue;
            }
            if (!(direccion.equals(padre.getDireccion())) && camino.equals(padre)) {
                giros.add(padre);
                emitirLaserRec(camino, grafoLaser, nivel, giros);
                continue;
            }
            grafoLaser.addVertex(camino);
            grafoLaser.addEdge(padre, camino);
            emitirLaserRec(camino, grafoLaser, nivel, giros);
        }
    }

    @Override
    public String toString() {
        return this.posicion.toString();
    }
}