// 
// Decompiled by Procyon v0.5.30
// 

public class Object3dList extends Object3dAdaptor
{
    protected double[] keys;
    protected Object3d[] elementData;
    protected int elementCount;
    protected long lasttime;
    protected int delay;
    protected int defaultColor;
    static Timer sortTimer;
    static Timer renderTimer;
    
    public Object3dList(final int n) {
        this.lasttime = 0L;
        this.delay = 0;
        this.defaultColor = -1;
        this.elementData = new Object3d[n];
        this.elementCount = 0;
    }
    
    public void setDefaultColor(final int defaultColor) {
        this.defaultColor = defaultColor;
    }
    
    public void addElement(final Object3d object3d) {
        if (this.elementData.length == this.elementCount) {
            final Object3d[] elementData = new Object3d[1 + 2 * this.elementData.length];
            System.arraycopy(this.elementData, 0, elementData, 0, this.elementCount);
            this.elementData = elementData;
        }
        this.elementData[this.elementCount++] = object3d;
        super.centre = object3d.centre();
    }
    
    public void append(final Object3dList list) {
        for (int i = 0; i < list.size(); ++i) {
            this.addElement(list.elementAt(i));
        }
    }
    
    public Object3d elementAt(final int n) {
        return this.elementData[n];
    }
    
    public int size() {
        return this.elementCount;
    }
    
    void sort() {
        int i = 1;
        int elementCount = this.elementCount;
        while (i != 0) {
            i = 0;
            for (int j = 1; j < elementCount; ++j) {
                final int n = j - 1;
                if (this.keys[n] < this.keys[j]) {
                    final Object3d object3d = this.elementData[n];
                    this.elementData[n] = this.elementData[j];
                    this.elementData[j] = object3d;
                    final double n2 = this.keys[n];
                    this.keys[n] = this.keys[j];
                    this.keys[j] = n2;
                    i = 1;
                }
            }
            --elementCount;
        }
    }
    
    void initKeys() {
        if (this.keys == null || this.keys.length != this.elementCount) {
            this.keys = new double[this.elementCount];
        }
    }
    
    public void sortByFirstFrame() {
        this.initKeys();
        for (int i = 0; i < this.elementCount; ++i) {
            this.keys[i] = this.elementData[i].getFirstFrame();
        }
        this.sort();
    }
    
    public void sortByLastFrame() {
        this.initKeys();
        for (int i = 0; i < this.elementCount; ++i) {
            this.keys[i] = this.elementData[i].getLastFrame();
        }
        this.sort();
    }
    
    public void sortBySelectFrame() {
        this.initKeys();
        for (int i = 0; i < this.elementCount; ++i) {
            this.keys[i] = this.elementData[i].getSelectFrame();
        }
        this.sort();
    }
    
    public void render(final View3d view3d) {
        final int frameNo = view3d.getFrameNo();
        Object3dList.renderTimer.start();
        for (int i = this.elementCount - 1; i >= 0; --i) {
            if (this.elementData[i].visible(frameNo)) {
                this.elementData[i].render(view3d);
                if (this.delay > 0) {
                    try {
                        Thread.sleep(this.delay);
                    }
                    catch (InterruptedException ex) {}
                }
            }
        }
        Object3dList.renderTimer.stop();
    }
    
    static {
        Object3dList.sortTimer = new Timer("sort");
        Object3dList.renderTimer = new Timer("render");
    }
}
