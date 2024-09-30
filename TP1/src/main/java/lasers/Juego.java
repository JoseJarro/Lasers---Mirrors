package lasers;

public class Juego {
    String pathNivel = "level1.dat";
    private boolean estado;
    private Nivel nivel;


    public Juego(){
        nivel = new Nivel(pathNivel);
    }

    public void moverBloque(Coordenada coordenadaInicial ,Coordenada coordenadaFinal){
        for (Celda celda : nivel.getCeldas()){
            Coordenada coordenadaCelda = celda.getCoordenada();
            if (coordenadaFinal.getPosY() == coordenadaCelda.getPosY()){
                if (coordenadaFinal.getPosX() == coordenadaCelda.getPosX()){
                    if (celda.getOcupado()) {
                        break;
                    }
                }
            }
        }
        nivel.moverBloque(coordenadaInicial,coordenadaFinal);

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
        nivel = new Nivel(pathNivel);

    }
    public boolean juegoTerminado(){
       return nivel.esNivelCompletado() ;
    }
}
