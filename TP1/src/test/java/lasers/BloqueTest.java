package lasers;

import java.util.Objects;

class BloqueTest {

    @org.junit.jupiter.api.Test//&& this.direccion.equals(otro.direccion); //peligro
    void testMoverBloque() {
        var pos = new Coordenada(1, 1);

        // Prueba bloque opaco fijo
        Bloque bloque = new Bloque(pos, TipoBloque.OPACO_FIJO.esfijo(), TipoBloque.OPACO_FIJO.getComportamiento(), 'F');
        bloque.moverBloque(3,3);
        assert (bloque.getCoordenada().getPosX() == 1);
        assert (bloque.getCoordenada().getPosY() == 1);

        // Prueba bloque opaco movil
        Bloque bloque2 = new Bloque(pos, TipoBloque.OPACO_MOVIL.esfijo(), TipoBloque.OPACO_FIJO.getComportamiento(), 'B');
        bloque2.moverBloque(3,3);
        assert (bloque2.getCoordenada().getPosX() == 3);
        assert (bloque2.getCoordenada().getPosY() == 3);

        //Prueba bloque vidrio
        Bloque bloque3= new Bloque(pos, TipoBloque.VIDRIO.esfijo(), TipoBloque.VIDRIO.getComportamiento(), 'G');
        bloque3.moverBloque(3,3);
        assert (bloque2.getCoordenada().getPosX() == 3);
        assert (bloque2.getCoordenada().getPosY() == 3);

        //Prueba bloque vidrio
        Bloque bloque4= new Bloque(pos, TipoBloque.ESPEJO.esfijo(), TipoBloque.ESPEJO.getComportamiento(), 'R');
        bloque4.moverBloque(3,3);
        assert (bloque2.getCoordenada().getPosX() == 3);
        assert (bloque2.getCoordenada().getPosY() == 3);

        //Prueba bloque vidrio
        Bloque bloque5= new Bloque(pos, TipoBloque.CRISTAL.esfijo(), TipoBloque.CRISTAL.getComportamiento(), 'C');
        bloque5.moverBloque(3,3);
        assert (bloque2.getCoordenada().getPosX() == 3);
        assert (bloque2.getCoordenada().getPosY() == 3);
        System.out.println(bloque2.getCoordenada().getPosY());

    }

    @org.junit.jupiter.api.Test
    void testComportamientosBloque() {
        var posBloque = new Coordenada(3, 3);
        var posLaser = new Coordenada(2, 3); // Cara 1 del bloque ubicado en 3,3

        Bloque bloqueOpacoFijo = new Bloque(posBloque, TipoBloque.OPACO_FIJO.esfijo(), TipoBloque.OPACO_FIJO.getComportamiento(), 'F');
        Bloque bloqueOpacoMovil = new Bloque(posBloque, TipoBloque.OPACO_MOVIL.esfijo(), TipoBloque.OPACO_MOVIL.getComportamiento(), 'B');
        Bloque bloqueEspejo = new Bloque(posBloque, TipoBloque.ESPEJO.esfijo(), TipoBloque.ESPEJO.getComportamiento(), 'R');
        Bloque bloqueCristal = new Bloque(posBloque, TipoBloque.CRISTAL.esfijo(), TipoBloque.CRISTAL.getComportamiento(), 'C');
        Bloque bloqueVidrio = new Bloque(posBloque, TipoBloque.VIDRIO.esfijo(), TipoBloque.VIDRIO.getComportamiento(), 'G');

        CustomVector vectorPadre = new CustomVector(posLaser,Direccion.SURESTE);

        CustomVector vectorReflejado = new CustomVector(posLaser,Direccion.SUROESTE);

        var posRefractada = new Coordenada( 4 ,3);
        CustomVector vectorRefractado = new CustomVector(posRefractada,vectorPadre.getDireccion());

        // Prueba comportamiento bloque opaco fijo
        assert  (bloqueOpacoFijo.comportamientosBloque(vectorPadre)[0] == null );
        // Prueba comportamiento bloque opaco movil
        assert  (bloqueOpacoMovil.comportamientosBloque(vectorPadre)[0] == null );
        // Prueba comportamiento bloque espejo
        assert Objects.equals(bloqueEspejo.comportamientosBloque (vectorPadre)[0].getDireccion(), vectorReflejado.getDireccion());
        System.out.println(bloqueEspejo.comportamientosBloque(vectorPadre  )[0]);
        assert Objects.equals(bloqueEspejo.comportamientosBloque(vectorPadre)[0].getPosicion().getPosX(), vectorReflejado.getPosicion().getPosX());
        assert Objects.equals(bloqueEspejo.comportamientosBloque(vectorPadre)[0].getPosicion().getPosY(), vectorReflejado.getPosicion().getPosY());
        //Prueba comportamiento cristal
        System.out.println(bloqueCristal.comportamientosBloque(vectorPadre  )[0].getDireccion());
        //assert Objects.equals(bloqueCristal.comportamientosBloque(vectorPadre)[0].getDireccion(),vectorRefractado.getDireccion());

        //assert Objects.equals(bloqueEspejo.comportamientosBloque(vectorPadre)[0].getPosicion().getPosX(), vectorRefractado.getPosicion().getPosX());
        //assert Objects.equals(bloqueEspejo.comportamientosBloque(vectorPadre)[0].getPosicion().getPosY(), vectorRefractado.getPosicion().getPosY());
    }

    @org.junit.jupiter.api.Test
    void movimientosBloques() {
        var juego = new Juego();
        var nivel = juego.getNivel();
        juego.validoMoverBloque(new Coordenada(5,7), new Coordenada(5,1));
        juego.validoMoverBloque(new Coordenada(5,3), new Coordenada(5,5));
        juego.validoMoverBloque(new Coordenada(5,5), new Coordenada(5,3));
        juego.validoMoverBloque(new Coordenada(5,3), new Coordenada(5,1));
        juego.cambiarNivel("level2.dat");
    }

}