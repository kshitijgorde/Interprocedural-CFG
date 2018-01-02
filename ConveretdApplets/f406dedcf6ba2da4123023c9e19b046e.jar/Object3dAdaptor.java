// 
// Decompiled by Procyon v0.5.30
// 

public class Object3dAdaptor implements Object3d
{
    protected Point3d centre;
    static int normalColor;
    static int addColor;
    static int deleteColor;
    static int selectColor;
    static int idCount;
    private int id;
    protected int firstFrame;
    protected int lastFrame;
    int selectFrameNo;
    
    public Object3dAdaptor() {
        this.firstFrame = 0;
        this.lastFrame = Integer.MAX_VALUE;
        this.selectFrameNo = -1;
        this.id = Object3dAdaptor.idCount++;
    }
    
    public Object3dAdaptor(final Point3d centre) {
        this.firstFrame = 0;
        this.lastFrame = Integer.MAX_VALUE;
        this.selectFrameNo = -1;
        this.centre = centre;
    }
    
    public String id() {
        return "O" + this.id;
    }
    
    public void setCentre(final Point3d centre) {
        this.centre = centre;
    }
    
    public Point3d centre() {
        return this.centre;
    }
    
    public void setFirstFrame(final int firstFrame) {
        this.firstFrame = firstFrame;
    }
    
    public int getFirstFrame() {
        return this.firstFrame;
    }
    
    public void setLastFrame(final int lastFrame) {
        this.lastFrame = lastFrame;
    }
    
    public int getLastFrame() {
        return this.lastFrame;
    }
    
    public void select(final int selectFrameNo) {
        this.selectFrameNo = selectFrameNo;
    }
    
    public int getSelectFrame() {
        return this.selectFrameNo;
    }
    
    public boolean visible(final int n) {
        return this.firstFrame <= n && n <= this.lastFrame;
    }
    
    public double depthBias(final View3d view3d) {
        return 0.0;
    }
    
    public int getColorIndex(final View3d view3d, final int n) {
        return (this.firstFrame == view3d.getFrameNo()) ? Object3dAdaptor.addColor : ((this.lastFrame == view3d.getFrameNo()) ? Object3dAdaptor.deleteColor : n);
    }
    
    public void render(final View3d view3d) {
    }
}
