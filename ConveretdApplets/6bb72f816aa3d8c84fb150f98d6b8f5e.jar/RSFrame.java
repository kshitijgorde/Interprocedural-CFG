import java.awt.Graphics;
import java.awt.Component;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

final class RSFrame extends Frame
{
    private final RSApplet rsApplet;
    
    public RSFrame(final RSApplet rsApplet, final int n, final int n2) {
        this.rsApplet = rsApplet;
        this.setTitle("Devilishpkz");
        this.setResizable(false);
        this.setVisible(true);
        this.toFront();
        this.setSize(n + 8, n2 + 28);
        this.setLocationRelativeTo(null);
    }
    
    @Override
    public Graphics getGraphics() {
        final Graphics graphics = super.getGraphics();
        graphics.translate(4, 24);
        return graphics;
    }
    
    @Override
    public void update(final Graphics graphics) {
        this.rsApplet.update(graphics);
    }
    
    @Override
    public void paint(final Graphics graphics) {
        this.rsApplet.paint(graphics);
    }
}
