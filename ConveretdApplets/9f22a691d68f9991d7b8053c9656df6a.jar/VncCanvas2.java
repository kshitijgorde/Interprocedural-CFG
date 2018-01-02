import java.awt.image.ImageObserver;
import java.awt.RenderingHints;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.io.IOException;

// 
// Decompiled by Procyon v0.5.30
// 

class VncCanvas2 extends VncCanvas
{
    public VncCanvas2(final VncViewer vncViewer) throws IOException {
        super(vncViewer);
        this.disableFocusTraversalKeys();
    }
    
    public VncCanvas2(final VncViewer vncViewer, final int n, final int n2) throws IOException {
        super(vncViewer, n, n2);
        this.disableFocusTraversalKeys();
    }
    
    public void paintScaledFrameBuffer(final Graphics graphics) {
        final Graphics2D graphics2D = (Graphics2D)graphics;
        graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        graphics2D.drawImage(super.memImage, 0, 0, super.scaledWidth, super.scaledHeight, null);
    }
    
    private void disableFocusTraversalKeys() {
        try {
            this.getClass().getMethod("setFocusTraversalKeysEnabled", Boolean.TYPE).invoke(this, new Boolean(false));
        }
        catch (Exception ex) {}
    }
}
