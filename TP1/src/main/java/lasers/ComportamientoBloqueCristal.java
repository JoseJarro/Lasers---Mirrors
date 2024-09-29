package lasers;

public class ComportamientoBloqueCristal implements ComportamientoBloque{

    int posx =0;
    int posy =0;


    @Override
    public Vector2D[] comportamientoBloque(Vector2D padre,int cara) {
        if (cara ==1){
            posx = padre.getPosicion().getPosX()+2;
            posy = padre.getPosicion().getPosY();
        }else if (cara ==2){
            posx = padre.getPosicion().getPosX();
            posy = padre.getPosicion().getPosY()-2;
        }else if(cara ==3){
            posx = padre.getPosicion().getPosX()-2;
            posy = padre.getPosicion().getPosY();
        }else if(cara ==4){
            posx = padre.getPosicion().getPosX();
            posy = padre.getPosicion().getPosY()+2;

        }
        return null;
    }
}
