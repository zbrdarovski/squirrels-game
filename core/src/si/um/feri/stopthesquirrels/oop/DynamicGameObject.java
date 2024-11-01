package si.um.feri.stopthesquirrels.oop;

public abstract class DynamicGameObject extends GameObject {
    public long createTime;
    public int size;
    public int speed;

    public DynamicGameObject(float x, float y, float width, float height, int s, int v, long t) {
        super(x, y, width, height);
        createTime = t;
        size = s;
        speed = v;
    }
}
