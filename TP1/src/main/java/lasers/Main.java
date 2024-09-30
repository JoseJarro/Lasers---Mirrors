package lasers;

public class Main {
    public static void main(String[] args) {
        Nivel nivel = new Nivel("level4.dat");
        System.out.println(nivel.esNivelCompletado());
    }
}
