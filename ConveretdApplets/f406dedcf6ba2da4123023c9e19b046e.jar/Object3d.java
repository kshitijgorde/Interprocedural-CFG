// 
// Decompiled by Procyon v0.5.30
// 

public interface Object3d
{
    Point3d centre();
    
    void setCentre(final Point3d p0);
    
    void setFirstFrame(final int p0);
    
    int getLastFrame();
    
    void setLastFrame(final int p0);
    
    int getFirstFrame();
    
    void select(final int p0);
    
    int getSelectFrame();
    
    boolean visible(final int p0);
    
    void render(final View3d p0);
}
