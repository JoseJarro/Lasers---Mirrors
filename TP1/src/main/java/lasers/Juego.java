package lasers;

public class Juego {
    String pathNivel = "level1.dat";
    private boolean estado;
    private Nivel nivel;
    private VerificadorNivel verificador = new VerificadorNivel();


    public Juego(){
        nivel = new Nivel(pathNivel, verificador);
    }

    public void moverBloque(Coordenada posInicial ,Coordenada posFinal){
        for (Celda celda : nivel.getCeldas()){
            Coordenada posCelda = celda.getCoordenada();
            if (posFinal.iguales(posCelda)) {
                if (celda.getOcupado()) {
                    break;
                }
            }
        }
        nivel.moverBloque(posInicial, posFinal);
    }

    public void cambiarNivel(int numeroNivel){
        if (numeroNivel ==  1) {
            pathNivel = "level1.dat";
        }else if(numeroNivel == 2){
            pathNivel = "level2.dat";
        }else if(numeroNivel == 3){
            pathNivel = "level3.dat";
        }else if(numeroNivel == 4){
            pathNivel = "level4.dat";
        }else if(numeroNivel == 5){
            pathNivel = "level5.dat";
        }else if(numeroNivel == 6){
            pathNivel = "level6.dat";
        }
        nivel = new Nivel(pathNivel, verificador);

    }
    public boolean juegoTerminado(){
       return nivel.esNivelCompletado();
    }
}
