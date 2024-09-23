package lasers;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Objects;

public class Nivel {
    private final ArrayList<Celda> celdas = new ArrayList<>();
    //private final ArrayList<Bloque> bloques = new ArrayList<>();
    private final ArrayList<Emisor> emisores = new ArrayList<>();
    private final ArrayList<Objetivo> objetivos = new ArrayList<>();
    private final String nivel;


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
            int i = 1;
            while (!Objects.equals(linea = br.readLine(), "")) {
                if (linea == null) {
                    break;
                }
                int j = 1;
                for (char c : linea.toCharArray()) {
                    crearTablero(i, j, c);
                    j += 2;
                }
                i += 2;
            }
            while ((linea = br.readLine()) != null) {
                crearElementos(linea.split(" "));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void crearTablero(int x, int y, char c) {
        if (c == '.') {
            this.celdas.add(new Celda(x, y, true, false));
        } else if (c == 'F') {
            this.celdas.add(new Celda(x, y, true, true));
            //agregar al array de bloques, asi con los demas
        } else if (c == 'B') {
            this.celdas.add(new Celda(x, y, true, true));
        } else if (c == 'R') {
            this.celdas.add(new Celda(x, y, true, true));
        } else if (c == 'G') {
            this.celdas.add(new Celda(x, y, true, true));
        } else if (c == 'C') {
            this.celdas.add(new Celda(x, y, true, true));
        }

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

    public Boolean nivelCompletado() {
        for (Objetivo objetivo : this.objetivos) {
            if (!objetivo.objetivoAlcanzado(this.emisores)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return this.nivel;
    }
}
