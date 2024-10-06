package lasers;

import java.util.Objects;


class BloqueTest {

    @org.junit.jupiter.api.Test
    void testMoverBloque() {
        var pos = new Coordenada(1, 1);

        // Prueba bloque opaco fijo
        Bloque bloqueOpacoFijo = new Bloque(pos, TipoBloque.OPACO_FIJO.esfijo(), TipoBloque.OPACO_FIJO.getComportamiento(),'F');
        bloqueOpacoFijo.moverBloque(3,3);
        assert (bloqueOpacoFijo.getCoordenada().getPosX() == 1);
        assert (bloqueOpacoFijo.getCoordenada().getPosY() == 1);

        // Prueba bloque opaco movil
        Bloque bloqueOpacoMovil = new Bloque(pos, TipoBloque.OPACO_MOVIL.esfijo(), TipoBloque.OPACO_FIJO.getComportamiento(),'B');
        bloqueOpacoMovil.moverBloque(3,3);
        assert (bloqueOpacoMovil.getCoordenada().getPosX() == 3);
        assert (bloqueOpacoMovil.getCoordenada().getPosY() == 3);

        //Prueba bloque vidrio
        Bloque bloqueVidrio= new Bloque(pos, TipoBloque.VIDRIO.esfijo(), TipoBloque.VIDRIO.getComportamiento(),'R');
        bloqueVidrio.moverBloque(3,3);
        assert (bloqueVidrio.getCoordenada().getPosX() == 3);
        assert (bloqueVidrio.getCoordenada().getPosY() == 3);

        //Prueba bloque Espejo
        Bloque bloqueEspejo= new Bloque(pos, TipoBloque.ESPEJO.esfijo(), TipoBloque.ESPEJO.getComportamiento(),'C');
        bloqueEspejo.moverBloque(3,3);
        assert (bloqueEspejo.getCoordenada().getPosX() == 3);
        assert (bloqueEspejo.getCoordenada().getPosY() == 3);

        //Prueba bloque cristal
        Bloque bloqueCristal= new Bloque(pos, TipoBloque.CRISTAL.esfijo(), TipoBloque.CRISTAL.getComportamiento(),'G');
        bloqueCristal.moverBloque(3,3);
        assert (bloqueCristal.getCoordenada().getPosX() == 3);
        assert (bloqueCristal.getCoordenada().getPosY() == 3);

    }

    @org.junit.jupiter.api.Test
    void testComportamientosBloque() {
        var posBloque = new Coordenada(3, 3);
        var posLaser = new Coordenada(2, 3); // Cara 1 del bloque ubicado en 3,3

        Bloque bloqueOpacoFijo = new Bloque(posBloque, TipoBloque.OPACO_FIJO.esfijo(), TipoBloque.OPACO_FIJO.getComportamiento(),'F');
        Bloque bloqueOpacoMovil = new Bloque(posBloque, TipoBloque.OPACO_MOVIL.esfijo(), TipoBloque.OPACO_MOVIL.getComportamiento(),'B');
        Bloque bloqueEspejo = new Bloque(posBloque, TipoBloque.ESPEJO.esfijo(), TipoBloque.ESPEJO.getComportamiento(),'R');
        Bloque bloqueCristal = new Bloque(posBloque, TipoBloque.CRISTAL.esfijo(), TipoBloque.CRISTAL.getComportamiento(),'C');
        Bloque bloqueVidrio = new Bloque(posBloque, TipoBloque.VIDRIO.esfijo(), TipoBloque.VIDRIO.getComportamiento(),'G');

        Vector2D vectorPadre = new Vector2D(posLaser,"SE");

        Vector2D vectorReflejado = new Vector2D(posLaser,"SW");


        var posRefractada = new Coordenada( 4 ,3);
        Vector2D vectorRefractado = new Vector2D(posRefractada,vectorPadre.getDireccion());

        Coordenada posContinuado = new Coordenada(3,4);
        Vector2D vectorContinuado = new Vector2D(posContinuado,vectorPadre.getDireccion());

        // Prueba comportamiento bloque opaco fijo
        assert  (bloqueOpacoFijo.comportamientosBloque(vectorPadre)[0] == null );

        // Prueba comportamiento bloque opaco movil
        assert  (bloqueOpacoMovil.comportamientosBloque(vectorPadre)[0] == null );

        //Prueba comportamiento cristal
        assert Objects.equals(bloqueCristal.comportamientosBloque (vectorPadre)[0].getDireccion(), vectorRefractado.getDireccion());
        assert Objects.equals(bloqueCristal.comportamientosBloque(vectorPadre)[0].getPosicion().getPosX(), vectorRefractado.getPosicion().getPosX());
        assert Objects.equals(bloqueCristal.comportamientosBloque(vectorPadre)[0].getPosicion().getPosY(), vectorRefractado.getPosicion().getPosY());

        // Prueba comportamiento bloque espejo
        vectorPadre = new Vector2D(posLaser,"SE");
        assert Objects.equals(bloqueEspejo.comportamientosBloque (vectorPadre)[0].getDireccion(), vectorReflejado.getDireccion());
        vectorPadre = new Vector2D(posLaser,"SE");
        assert Objects.equals(bloqueEspejo.comportamientosBloque(vectorPadre)[0].getPosicion().getPosX(), vectorReflejado.getPosicion().getPosX());
        vectorPadre = new Vector2D(posLaser,"SE");
        assert Objects.equals(bloqueEspejo.comportamientosBloque(vectorPadre)[0].getPosicion().getPosY(), vectorReflejado.getPosicion().getPosY());

        //Prueba comportamiento Vidrio
        vectorPadre = new Vector2D(posLaser,"SE");
        assert  Objects.equals(bloqueVidrio.comportamientosBloque(vectorPadre)[0].getDireccion(), vectorContinuado.getDireccion());
        vectorPadre = new Vector2D(posLaser,"SE");
        assert  Objects.equals(bloqueVidrio.comportamientosBloque(vectorPadre)[0].getPosicion().getPosX(), vectorContinuado.getPosicion().getPosX());
        vectorPadre = new Vector2D(posLaser,"SE");
        assert  Objects.equals(bloqueVidrio.comportamientosBloque(vectorPadre)[0].getPosicion().getPosY(), vectorContinuado.getPosicion().getPosY());
        vectorPadre = new Vector2D(posLaser,"SE");
        assert Objects.equals(bloqueVidrio.comportamientosBloque (vectorPadre)[1].getDireccion(), vectorReflejado.getDireccion());
        vectorPadre = new Vector2D(posLaser,"SE");
        assert Objects.equals(bloqueVidrio.comportamientosBloque(vectorPadre)[1].getPosicion().getPosX(), vectorReflejado.getPosicion().getPosX());
        vectorPadre = new Vector2D(posLaser,"SE");
        assert Objects.equals(bloqueVidrio.comportamientosBloque(vectorPadre)[1].getPosicion().getPosY(), vectorReflejado.getPosicion().getPosY());
    }


    @org.junit.jupiter.api.Test
    void testObtenerCaraColicionada() {

        var posBloque = new Coordenada(3, 3);
        var posLaser = new Coordenada(2, 3); // Cara 1 del bloque ubicado en 3,3

        Vector2D vectorPadre = new Vector2D(posLaser,"SE");
        Bloque bloqueOpacoFijo = new Bloque(posBloque, TipoBloque.OPACO_FIJO.esfijo(), TipoBloque.OPACO_FIJO.getComportamiento(),'F');
        assert Objects.equals( bloqueOpacoFijo.colisionaLaser(vectorPadre),true);
        vectorPadre = new Vector2D(posLaser,"NW");
        assert Objects.equals( bloqueOpacoFijo.colisionaLaser(vectorPadre),false);

    }

}