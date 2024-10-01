package lasers;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.util.*;

public class Nivel {
    private final String nivel;
    private final List<Celda> celdas = new ArrayList<>();
    private final List<Bloque> bloques = new ArrayList<>();
    private final List<Emisor> emisores = new ArrayList<>();
    private final List<Coordenada> objetivos = new ArrayList<>();
    private final Coordenada dimension = new Coordenada(0, 0);

    //CARGA DE OBJETOS
    public Nivel(String archivo, VerificadorNivel verificador) {
        this.nivel = archivo;
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(archivo);
        if (inputStream == null) {
            System.out.println("Error. Archivo no encontrado");
            return;
        }
        try(BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String linea;
            int ancho = 0;
            int alto = 1;
            while (!Objects.equals(linea = br.readLine(), "")) {
                if (linea == null) {
                    break;
                }
                int j = 1;
                for (char c : linea.toCharArray()) {
                    crearTablero(j, alto, c);
                    j += 2;
                    if (j > ancho) {
                        ancho = j;
                    }
                }
                alto += 2;
            }
            this.dimension.setPosX(ancho - 1);
            this.dimension.setPosY(alto - 1);
            while ((linea = br.readLine()) != null) {
                crearElementos(linea.split(" "));
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
                this.celdas.add(new Celda(posicion, false));
                return;
            case 'F':
                this.bloques.add(new Bloque(posicion, TipoBloque.BLOQUE_OPACO_FIJO.esfijo(), TipoBloque.BLOQUE_OPACO_FIJO.obtenerComportamiento() ));
                break;
            case 'B':
                this.bloques.add(new Bloque(posicion, TipoBloque.BLOQUE_OPACO_MOVIL.esfijo(), TipoBloque.BLOQUE_OPACO_MOVIL.obtenerComportamiento() ));
                break;
            case 'R':
                this.bloques.add(new Bloque(posicion, TipoBloque.BLOQUE_ESPEJO.esfijo(), TipoBloque.BLOQUE_ESPEJO.obtenerComportamiento() ));
                break;
            case 'G':
                this.bloques.add(new Bloque(posicion, TipoBloque.BLOQUE_VIDRIO.esfijo(), TipoBloque.BLOQUE_VIDRIO.obtenerComportamiento() ));
                break;
            case 'C':
                this.bloques.add(new Bloque(posicion, TipoBloque.BLOQUE_CRISTAL.esfijo(), TipoBloque.BLOQUE_CRISTAL.obtenerComportamiento() ));
                break;
        }
        this.celdas.add(new Celda(posicion, true));
    }

    private void crearElementos(String[] valores) {
        int x = Integer.parseInt(valores[1]);
        int y = Integer.parseInt(valores[2]);
        var posicion = new Coordenada(x, y);
        if (valores[0].equals("E")) {
            this.emisores.add(new Emisor(posicion, valores[3]));
        } else if (valores[0].equals("G")) {
            this.objetivos.add(posicion);
        }
    }

    public Boolean esNivelCompletado() {
        Map<Coordenada, Vector2D> laser = new HashMap<>();
        for (Emisor emisor : this.emisores) {
            for (Vector2D v: emisor.emitirLaser(this)) {
                laser.put(v.getPosicion(), v);
            }
        }
        //Error: siempre da false, pero emitir laser funciionaria.
        for (Coordenada objetivo : this.objetivos) {
            if (!(laser.containsKey(objetivo))) {
                return false;
            }
        }
        return true;
    }

    public Boolean fueraDimension(Coordenada posicion) {
        if (posicion.getPosX() > this.dimension.getPosX() || posicion.getPosX() < 0) {
            return true;
        }
        else return posicion.getPosY() > this.dimension.getPosY() || posicion.getPosY() < 0;
    }

    public void moverBloque(Coordenada posInicial, Coordenada posFinal){
        for (Bloque bloque : this.bloques){
            Coordenada posBloque = bloque.getCoordenada();
            if (posInicial.iguales(posBloque)) {
                bloque.moverBloque(posFinal.getPosX(), posFinal.getPosY());
            }
        }
    }

    //GETS ATRIBUTOS
    public List<Emisor> getEmisores() {
        return this.emisores;
    }

    public List<Bloque> getBloques() {
        return this.bloques;
    }

    public List<Celda> getCeldas() {
        return this.celdas;
    }

    @Override
    public String toString() {
        return this.nivel;
    }
}


