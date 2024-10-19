package lasers;

public class Juego {
    String nivelInicial = "level1.dat";
    private Nivel nivel;


    public Juego(){
        var cargadorNivel = new LevelLoader(nivelInicial, new VerificadorNivel());
        nivel = new Nivel(cargadorNivel);
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
        var cargadorNivel = new LevelLoader(nivel, new VerificadorNivel());
        this.nivel = new Nivel(cargadorNivel);
    }

    public boolean juegoTerminado(){
        return nivel.esNivelCompletado();
    }

    public Nivel getNivel() {return nivel;}
}
