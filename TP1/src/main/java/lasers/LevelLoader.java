package lasers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class LevelLoader {
    private final String archivo;
    private final VerificadorNivel verificador;
    private Nivel nivel;

    public LevelLoader(String archivo, VerificadorNivel verificador) {
        this.archivo = archivo;
        this.verificador = verificador;
    }

    public String getArchivo() {
        return this.archivo;
    }

    public void cargarObjetos(Nivel nivel) {
        this.nivel = nivel;
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(archivo);
        if (inputStream == null) {
            System.err.println("Error. Archivo no encontrado");
            return;
        }
        try(BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String linea;
            int ancho = 0;
            int alto = 1;
            while (!((linea = br.readLine()).isEmpty())) {
                int i = 1;
                for (char c : linea.toCharArray()) {
                    crearTablero(i, alto, c);
                    i += 2;
                    if (i > ancho) {
                        ancho = i;
                    }
                }
                alto += 2;
            }
            nivel.setDimension(new Coordenada(ancho-1, alto-1));
            while ((linea = br.readLine()) != null) {
                var parametros = linea.split(" ");
                if (verificador.parametrosValidos(parametros, nivel)) {
                    crearElementos(parametros);
                    continue;
                }
                throw new IOException("Error. Emisores o Objetivos incorrectos");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void crearTablero(int x, int y, char c) {
        var posicion = new Coordenada(x, y);
        switch (c) {
            case ' ':
                return;
            case '.':
                nivel.agregarCelda(new Celda(posicion, false));
                return;
        }
        Bloque bloque;
        switch (c) {
            case 'F':
                bloque = new Bloque(posicion, TipoBloque.OPACO_FIJO.esfijo(), TipoBloque.OPACO_FIJO.getComportamiento(),'F' );
                break;
            case 'B':
                bloque = new Bloque(posicion, TipoBloque.OPACO_MOVIL.esfijo(), TipoBloque.OPACO_MOVIL.getComportamiento(),'B' );
                break;
            case 'R':
                bloque = new Bloque(posicion, TipoBloque.ESPEJO.esfijo(), TipoBloque.ESPEJO.getComportamiento(),'R' );
                break;
            case 'G':
                bloque = new Bloque(posicion, TipoBloque.VIDRIO.esfijo(), TipoBloque.VIDRIO.getComportamiento(),'G' );
                break;
            case 'C':
                bloque = new Bloque(posicion, TipoBloque.CRISTAL.esfijo(), TipoBloque.CRISTAL.getComportamiento(),'C' );
                break;
            default: return;
        }
        nivel.agregarBloque(bloque);
        nivel.agregarCelda(new Celda(posicion.clonar(), true));
    }

    private void crearElementos(String[] valores) {
        int x = Integer.parseInt(valores[1]);
        int y = Integer.parseInt(valores[2]);
        var posicion = new Coordenada(x, y);
        if (valores[0].equals("E")) {
            nivel.agregarEmisor(new Emisor(posicion, valores[3]));
        } else if (valores[0].equals("G")) {
            nivel.agregarObjetivo(posicion);
        }
    }

}

