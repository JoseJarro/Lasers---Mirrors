package lasers;

import static org.junit.jupiter.api.Assertions.*;

class BloqueTest {

    @org.junit.jupiter.api.Test
    void testMoverBloque() {
        var pos1 = new Coordenada(1, 1);
        Bloque bloque = new Bloque(pos1, TipoBloque.BLOQUE_OPACO_FIJO.esfijo(), TipoBloque.BLOQUE_OPACO_FIJO.obtenerComportamiento());
        bloque.moverBloque(0,0);
        assert (bloque.getCoordenada().getPosX() == 1);
        var pos2 = new Coordenada(1, 1);
        Bloque bloque2 = new Bloque(pos2, TipoBloque.BLOQUE_OPACO_MOVIL.esfijo(), TipoBloque.BLOQUE_OPACO_FIJO.obtenerComportamiento());
        bloque2.moverBloque(0,0);
        assert (bloque.getCoordenada().getPosX() == 1);

    }

    @org.junit.jupiter.api.Test
    void testComportamientosBloque() {
        var pos1 = new Coordenada(3, 3);
        var pos2 = new Coordenada(3, 3);
        var posV = new Coordenada(2, 3);
        Bloque bloqueOpacoFijo = new Bloque(pos1, TipoBloque.BLOQUE_OPACO_FIJO.esfijo(), TipoBloque.BLOQUE_OPACO_FIJO.obtenerComportamiento());
        Bloque bloqueOpacoMovil = new Bloque(pos2, TipoBloque.BLOQUE_OPACO_MOVIL.esfijo(), TipoBloque.BLOQUE_OPACO_MOVIL.obtenerComportamiento());
        Vector2D vectorPadre = new Vector2D(posV,"SE");

        assert  (bloqueOpacoFijo.comportamientosBloque(vectorPadre)[0] == null );
        assert  (bloqueOpacoMovil.comportamientosBloque(vectorPadre)[0] == null );
    }

    @org.junit.jupiter.api.Test
    void tocaLaser() {
    }

}