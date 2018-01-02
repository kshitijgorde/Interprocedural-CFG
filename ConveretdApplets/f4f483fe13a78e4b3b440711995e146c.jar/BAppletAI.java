import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

public class BAppletAI extends BApplet
{
    static final String BASEPATH = "image-";
    
    public void allocate() {
        super.g = new BGraphicsAI(super.width, super.height, "image-");
    }
    
    public void update() {
    }
    
    public void paint(final Graphics graphics) {
    }
    
    public void start() {
    }
}
