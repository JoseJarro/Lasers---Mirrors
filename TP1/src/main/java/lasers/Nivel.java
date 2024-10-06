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
            this.dimension.setPosX(ancho - 1);
            this.dimension.setPosY(alto - 1);
            while ((linea = br.readLine()) != null) {
                var parametros = linea.split(" ");
                if (verificador.parametrosValidos(parametros, this)) {
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
                this.celdas.add(new Celda(posicion, false));
                return;
            case 'F':
                this.bloques.add(new Bloque(posicion, TipoBloque.OPACO_FIJO.esfijo(), TipoBloque.OPACO_FIJO.getComportamiento(),'F' ));
                break;
            case 'B':
                this.bloques.add(new Bloque(posicion, TipoBloque.OPACO_MOVIL.esfijo(), TipoBloque.OPACO_MOVIL.getComportamiento(),'B' ));
                break;
            case 'R':
                this.bloques.add(new Bloque(posicion, TipoBloque.ESPEJO.esfijo(), TipoBloque.ESPEJO.getComportamiento(),'R' ));
                break;
            case 'G':
                this.bloques.add(new Bloque(posicion, TipoBloque.VIDRIO.esfijo(), TipoBloque.VIDRIO.getComportamiento(),'G' ));
                break;
            case 'C':
                this.bloques.add(new Bloque(posicion, TipoBloque.CRISTAL.esfijo(), TipoBloque.CRISTAL.getComportamiento(),'C' ));
                break;
            default: return;
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
        var objetivosCompletados = 0;
        for (Emisor emisor : this.emisores) {
            for (Coordenada objetivo : this.objetivos) {
                if (emisor.emitirLaser(this).vertexSet().contains(new Vector2D(objetivo, ""))) {
                    objetivosCompletados++;
                }
            }
        }
        return objetivosCompletados >= this.objetivos.size();
    }

    public Coordenada getDimension() {return dimension;}

    public List<Coordenada> getObjetivos() {return objetivos;}

    public Boolean fueraDimension(Coordenada posicion) {
        if (posicion.getPosX() > this.dimension.getPosX() || posicion.getPosX() < 0) {
            return true;
        }
        else return posicion.getPosY() > this.dimension.getPosY() || posicion.getPosY() < 0;
    }

    public void moverBloque(Coordenada posInicial, Coordenada posFinal){
        for (Bloque bloque : this.bloques){
            Coordenada posBloque = bloque.getCoordenada();
            if (posInicial.equals(posBloque)) {
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


