package lasers;

public class Main {
    public static void main(String[] args) {
        Nivel nivel = new Nivel("level6.dat");
        System.out.println(nivel.esNivelCompletado());
    }
}
