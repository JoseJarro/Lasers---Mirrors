package lasers;

public class Juego {
    String nivelInicial = "level1.dat";
    private boolean estado;
    private Nivel nivel;
    private final VerificadorNivel verificador = new VerificadorNivel();


    public Juego(){
        nivel = new Nivel(nivelInicial, verificador);
    }

    public void moverBloque(Coordenada posInicial ,Coordenada posFinal){
        for (Celda celda : nivel.getCeldas()){
            Coordenada posCelda = celda.getCoordenada();
            if (posFinal.equals(posCelda)) {
                if (celda.getOcupado()) {
                    break;
                }else celda.ocupar();
            }
        }

        for (Celda celda : nivel.getCeldas()){
            Coordenada posCelda = celda.getCoordenada();
            if (posInicial.equals(posCelda)) {
                celda.desocupar();
            }
        }
        nivel.moverBloque(posInicial, posFinal);
    }

    public void cambiarNivel(String nivel){
        this.nivel = new Nivel(nivel, verificador);
    }

    public boolean juegoTerminado(){
       return nivel.esNivelCompletado();
    }

    public Nivel getNivel() {return nivel;}
}
