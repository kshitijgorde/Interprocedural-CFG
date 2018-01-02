import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class Paint3d extends Canvas implements Painter
{
    Viewer viewer;
    Image offscreen;
    Object3d model;
    Graphics g;
    
    public Paint3d(final Viewer viewer) {
        this.viewer = viewer;
    }
    
    public void setModel(final Object3d model) {
        this.model = model;
        this.viewer.put();
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawImage(this.offscreen, 0, 0, null);
    }
    
    public void run() {
        while (true) {
            final View3d value = this.viewer.get();
            value.clear();
            this.model.render(value);
            final Graphics graphics = this.getParent().getGraphics();
            if (graphics != null) {
                graphics.drawImage(this.offscreen, 0, 0, null);
            }
        }
    }
    
    public void resize(final int n, final int n2) {
        this.offscreen = this.createImage(n, n2);
        this.viewer.setGraphics(this.offscreen.getGraphics(), n, n2);
    }
    
    public void reshape(final int n, final int n2, final int n3, final int n4) {
        this.resize(n3, n4);
    }
}
