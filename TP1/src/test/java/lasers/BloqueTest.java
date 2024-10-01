package lasers;

import static org.junit.jupiter.api.Assertions.*;

class BloqueTest {

    @org.junit.jupiter.api.Test
    void testMoverBloque() {
        Bloque bloque = new Bloque(1,1,TipoBloque.BLOQUE_OPACO_FIJO.esfijo(), TipoBloque.BLOQUE_OPACO_FIJO.obtenerComportamiento());
        bloque.moverBloque(0,0);
        assert (bloque.getCoordenada().getPosX() == 1);
        Bloque bloque2 = new Bloque(1,1,TipoBloque.BLOQUE_OPACO_MOVIL.esfijo(), TipoBloque.BLOQUE_OPACO_FIJO.obtenerComportamiento());
        bloque2.moverBloque(0,0);
        assert (bloque.getCoordenada().getPosX() == 1);


    }

    @org.junit.jupiter.api.Test
    void testComportamientosBloque() {
        Bloque bloqueOpacoFijo = new Bloque(3,3,TipoBloque.BLOQUE_OPACO_FIJO.esfijo(), TipoBloque.BLOQUE_OPACO_FIJO.obtenerComportamiento());
        Bloque bloqueOpacoMovil = new Bloque(3,3,TipoBloque.BLOQUE_OPACO_MOVIL.esfijo(), TipoBloque.BLOQUE_OPACO_MOVIL.obtenerComportamiento());
        Vector2D vectorPadre = new Vector2D(new Coordenada(2,3),"SE");

        assert  (bloqueOpacoFijo.comportamientosBloque(vectorPadre)[0] == null );
        assert  (bloqueOpacoMovil.comportamientosBloque(vectorPadre)[0] == null );

    }

    @org.junit.jupiter.api.Test
    void tocaLaser() {
    }

}