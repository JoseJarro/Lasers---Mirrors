import java.io.*;


public class Archivo {
    public static void main (String [] args) throws IOException{
        Archivo arcvhivo = new Archivo();
        arcvhivo.hallarDimenciones();


}

    public char hallarCaracter(int coordenadax, int coorednaday){
        char caracter = 0;
        try {
            FileReader lector = null;
            lector = new FileReader("./TP1/src/main/resources/level1.dat");
            BufferedReader lectura = new BufferedReader(lector);
            for (int i=0;i<= coordenadax;i++){
               caracter = lectura.readLine().charAt(coorednaday);
            }
            lectura.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return caracter;
    }

    public void hallarDimenciones(){

        int anchoNivel =  hallarAnchoNivel();
        System.out.println(anchoNivel);
        int altoNivel = hallarAltoNivel();
        System.out.println(altoNivel);
        char dato = hallarCaracter(1,0);
        System.out.println(dato);
        dato = hallarCaracter(2,1);
        System.out.println(dato);
        dato = hallarCaracter(1,2);
        System.out.println(dato);
        dato = hallarCaracter(1,3);
        System.out.println(dato);


    }

    public int hallarAltoNivel() {
        int altoNivel = 0;


        try {
            FileReader lector = null;
            lector = new FileReader("./TP1/src/main/resources/level1.dat");
            BufferedReader lectura = new BufferedReader(lector);
            altoNivel = lectura.readLine().length();
            lectura.close();
        } catch (IOException e) {
           throw new RuntimeException(e);
        }

        return altoNivel;
    }


    public int hallarAnchoNivel(){

        int anchoNivel = 0;
        String datosNivel ;

            try {
                FileReader lector = null;
                lector = new FileReader("./TP1/src/main/resources/level1.dat");
                BufferedReader lectura = new BufferedReader(lector);
                while (!(datosNivel = lectura.readLine()).isEmpty()) {
                    anchoNivel +=1;
                }
                lectura.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        return anchoNivel;
    }
}
