package lasers;

public class ComportamientoBloqueOpacoFijo implements ComportamientoBloque {

    @Override
    public Vector2D[] comportamientoBloque(Vector2D padre , int cara) {
        Vector2D[] v = new Vector2D[1];
        v[0] = null;
        return v;
    }
}
