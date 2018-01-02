// 
// Decompiled by Procyon v0.5.30
// 

public final class Settings
{
    public int group;
    public boolean superGroup;
    public boolean solving;
    public MoveSequence generator;
    public boolean edit;
    public boolean lockViewer;
    public CubePosition cubePos;
    
    public Settings() {
        this.superGroup = false;
        this.solving = false;
        this.edit = false;
        this.lockViewer = false;
        this.cubePos = new CubePosition();
    }
}
