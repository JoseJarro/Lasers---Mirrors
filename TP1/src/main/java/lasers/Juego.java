package lasers;

public class Juego {
    String nivelInicial = "level1.dat";
    private boolean estado;
    private Nivel nivel;
    private final VerificadorNivel verificador = new VerificadorNivel();


    public Juego(){
        nivel = new Nivel(nivelInicial, verificador);
    }

    public void validoMoverBloque(Coordenada posInicial ,Coordenada posFinal){
        if (posInicial.equals(posFinal)){
            return;
        }
        Celda celdaInicial = null;
        Celda celdaFinal = null;
        for (Celda celda : nivel.getCeldas()){
            Coordenada posCelda = celda.getCoordenada();
            if (posFinal.equals(posCelda)) {
                celdaFinal = celda;
            } else if (posInicial.equals(posCelda)) {
                celdaInicial = celda;
            }
        }
        if (celdaInicial == null || celdaFinal == null) {
            return;
        }
        if (!(celdaInicial.getOcupado())) {
            return;
        }
        nivel.moverBloque(celdaInicial, celdaFinal);
    }

    public void cambiarNivel(String nivel){
        this.nivel = new Nivel(nivel, verificador);
    }

    public boolean juegoTerminado(){
        System.out.println(nivel.esNivelCompletado());
        return nivel.esNivelCompletado();
    }

    public Nivel getNivel() {return nivel;}
}
