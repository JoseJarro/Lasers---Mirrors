package lasers;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Nivel {
    private final String nivel;
    private final List<Celda> celdas = new ArrayList<>();
    private final List<Bloque> bloques = new ArrayList<>();
    private final List<Emisor> emisores = new ArrayList<>();
    private final List<Objetivo> objetivos = new ArrayList<>();
    private final Coordenada dimension = new Coordenada(0, 0);

    //CARGA DE OBJETOS
    public Nivel(String archivo) {
        this.nivel = archivo;
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(archivo);
        if (inputStream == null) {
            System.out.println("Error. Archivo no encontrado");
            return;
        }
        try(BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String linea;
            int ancho = 0;
            int i = 1;
            while (!Objects.equals(linea = br.readLine(), "")) {
                if (linea == null) {
                    break;
                }
                int j = 1;
                for (char c : linea.toCharArray()) {
                    crearTablero(j, i, c);
                    j += 2;
                    if (j > ancho) {
                        ancho = j;
                    }
                }
                i += 2;
            }
            this.dimension.setPosX(ancho - 1);
            this.dimension.setPosY(i - 1);
            while ((linea = br.readLine()) != null) {
                crearElementos(linea.split(" "));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void crearTablero(int x, int y, char c) {
        if (c == ' ') {
            return;
        }
        if (c == '.') {
            this.celdas.add(new Celda(x, y, false));
            return;
        }
        if (c == 'F') {
            this.bloques.add(new Bloque(x ,y , TipoBloque.BLOQUE_OPACO_FIJO.esfijo(), TipoBloque.BLOQUE_OPACO_FIJO.obtenerComportamiento() ));
        } else if (c == 'B') {
            this.bloques.add(new Bloque(x, y, TipoBloque.BLOQUE_OPACO_MOVIL.esfijo(), TipoBloque.BLOQUE_OPACO_MOVIL.obtenerComportamiento() ));
        } else if (c == 'R') {
            this.bloques.add(new Bloque(x, y, TipoBloque.BLOQUE_ESPEJO.esfijo(), TipoBloque.BLOQUE_ESPEJO.obtenerComportamiento() ));
        } else if (c == 'G') {
            this.bloques.add(new Bloque(x, y, TipoBloque.BLOQUE_VIDRIO.esfijo(), TipoBloque.BLOQUE_VIDRIO.obtenerComportamiento() ));
        } else if (c == 'C') {
            this.bloques.add(new Bloque(x, y, TipoBloque.BLOQUE_CRISTAL.esfijo(), TipoBloque.BLOQUE_CRISTAL.obtenerComportamiento() ));
        }
        this.celdas.add(new Celda(x, y, true));
    }

    private void crearElementos(String[] valores) {
        int x = Integer.parseInt(valores[1]);
        int y = Integer.parseInt(valores[2]);
        if (valores[0].equals("E")) {
            this.emisores.add(new Emisor(x, y, valores[3]));
        } else if (valores[0].equals("G")) {
            this.objetivos.add(new Objetivo(x, y));
        }
    }

    public Boolean esNivelCompletado() {
        for (Objetivo objetivo : this.objetivos) {
            if (!objetivo.objetivoAlcanzado(this)) {
                return false;
            }
        }
        return true;
    }

    public Boolean fueraDimension(Coordenada posicion) {
        if (posicion.getPosX() > this.dimension.getPosX() || posicion.getPosX() < 0) {
            return true;
        }
        if (posicion.getPosY() > this.dimension.getPosY() || posicion.getPosY() < 0) {
            return true;
        }
        return false;
    }

    //GETS ATRIBUTOS
    public List<Emisor> getEmisores() {
        return this.emisores;
    }

    public List<Bloque> getBloques() {
        return this.bloques;
    }

    @Override
    public String toString() {
        return this.nivel;
    }
}


