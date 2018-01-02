// 
// Decompiled by Procyon v0.5.30
// 

public class Point3dObject3d extends Point3d implements Object3d
{
    private Object3dAdaptor site;
    
    public Point3dObject3d(final Point3d centre) {
        this.site = new Object3dAdaptor(centre);
        this.setCentre(centre);
    }
    
    public Point3dObject3d(final Point3d point3d, final int n) {
        this(point3d);
        this.setLastFrame(n);
        this.setFirstFrame(n);
    }
    
    public void setCentre(final Point3d point3d) {
        super.v = point3d.v;
    }
    
    public Point3d centre() {
        return this;
    }
    
    public void setFirstFrame(final int firstFrame) {
        this.site.setFirstFrame(firstFrame);
    }
    
    public void setLastFrame(final int lastFrame) {
        this.site.setLastFrame(lastFrame);
    }
    
    public void select(final int n) {
        this.site.select(n);
    }
    
    public int getSelectFrame() {
        return this.site.getSelectFrame();
    }
    
    public int getFirstFrame() {
        return this.site.getFirstFrame();
    }
    
    public int getLastFrame() {
        return this.site.getLastFrame();
    }
    
    public boolean visible(final int n) {
        return this.site.visible(n);
    }
    
    public void render(final View3d view3d) {
        this.site.render(view3d);
    }
}
