import java.util.ArrayList;
import java.util.List;

public class Nivel {

    private List<Celda> celdas = new ArrayList<>();
    private List<Bloque> bloques = new ArrayList<>();
    private Emisor emisor = new Emisor();
    private List<Objetivo> objetivos = new ArrayList<>();

    private int nuemeroNivel;

    private TipoBloque tipoBloque;

    public void crearNivel(int numeroNivel){



    }

    private void crearEmisor(){

    }

    private void crearObjetivos(){

    }

    private void crearCeldas(int numeroNivel){

        Archivo archivo = new Archivo();

        for (int i=0; i<= archivo.hallarAnchoNivel();i++){
            for(int j=0;j<=archivo.hallarAltoNivel();j++){
                if (' ' != archivo.hallarCaracter(i, j)){
                    Celda celda = new Celda();
                    celda.setPiso();
                    celdas.add(new Celda());

                }else {
                    celdas.add(new Celda());
                }

            }
        }

    }

    private void crearBloques(){
        Archivo archivo = new Archivo();
        for (int i=0; i<= archivo.hallarAnchoNivel();i++){
            for(int j=0;j<=archivo.hallarAltoNivel();j++){
                if (TipoBloque.BLOQUE_OPACO_MOVIL.obtererCaracter() != archivo.hallarCaracter(i,j)){
                    bloques.add(new Bloque(i,j, TipoBloque.BLOQUE_OPACO_MOVIL.esfijo(), TipoBloque.BLOQUE_OPACO_MOVIL.obtenerComportamiento() ));
                } else if(TipoBloque.BLOQUE_OPACO_FIJO.obtererCaracter() != archivo.hallarCaracter(i,j)){
                    bloques.add(new Bloque(i,j, TipoBloque.BLOQUE_OPACO_FIJO.esfijo(),TipoBloque.BLOQUE_OPACO_FIJO.obtenerComportamiento() ));
                } else if(TipoBloque.BLOQUE_ESPEJO.obtererCaracter() != archivo.hallarCaracter(i,j)){
                    bloques.add(new Bloque(i,j, TipoBloque.BLOQUE_ESPEJO.esfijo(), TipoBloque.BLOQUE_ESPEJO.obtenerComportamiento() ));
                } else if(TipoBloque.BLOQUE_VIDRIO.obtererCaracter() != archivo.hallarCaracter(i,j)){
                    bloques.add(new Bloque(i,j, TipoBloque.BLOQUE_VIDRIO.esfijo(), TipoBloque.BLOQUE_VIDRIO.obtenerComportamiento() ));
                } else if(TipoBloque.BLOQUE_CRISTAL.obtererCaracter() != archivo.hallarCaracter(i,j)){
                    bloques.add(new Bloque(i,j, TipoBloque.BLOQUE_CRISTAL.esfijo(),TipoBloque.BLOQUE_CRISTAL.obtenerComportamiento() ));
                }
            }
        }
    }


    /*
    public boolean verificarEstadoObjetivos(){
        boolean estadoObjetibos = true;
        for(Objetivo objetivo : objetivos){
           if (!objetivo.objetivoAlcanzado()){
               estadoObjetibos = false;
               break;
           }
        }
        return estadoObjetibos;
    }
*/


}
