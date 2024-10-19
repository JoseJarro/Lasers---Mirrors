package lasers;

public class ComportamientoBloqueOpaco implements ComportamientoBloque{

    @Override
    public CustomVector[] comportamientoBloque(CustomVector padre, CaraBloque cara) {
        CustomVector[] v = new CustomVector[1];
        v[0] = null;
        return v;
    }
}
