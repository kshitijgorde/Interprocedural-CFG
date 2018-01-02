import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Event;
import java.awt.Color;
import java.awt.Image;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class MandelCanvas extends Canvas
{
    Image mandel_img;
    JuliaCanvas juliaCanvas;
    ControlPanel p;
    Thread canvasRunner;
    boolean update_positionQ;
    static final double z_left = -2.0;
    static final double z_right = 0.6;
    static final double z_top = 1.3;
    static final double z_bot = -1.3;
    int pixel_size;
    
    MandelCanvas(final Image mandel_img, final JuliaCanvas juliaCanvas, final ControlPanel p4, final int pixel_size) {
        this.update_positionQ = true;
        this.mandel_img = mandel_img;
        this.juliaCanvas = juliaCanvas;
        this.p = p4;
        this.pixel_size = pixel_size;
        this.setBackground(Color.white);
        this.show();
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        if (this.update_positionQ) {
            ControlPanel.update_position(this.complexConvert(n, n2));
        }
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        if (this.update_positionQ) {
            ControlPanel.positionDisplay.setText("");
        }
        return true;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        final ComplexNumber complexConvert = this.complexConvert(n, n2);
        ControlPanel.update_position(complexConvert);
        this.update_positionQ = false;
        JuliaCanvas.c = complexConvert;
        if (this.canvasRunner == null) {
            (this.canvasRunner = new Thread(this.juliaCanvas)).setPriority(1);
            this.canvasRunner.start();
        }
        if (!this.canvasRunner.isAlive()) {
            this.canvasRunner.stop();
            (this.canvasRunner = new Thread(this.juliaCanvas)).setPriority(1);
            this.canvasRunner.start();
        }
        return true;
    }
    
    ComplexNumber complexConvert(final int n, final int n2) {
        return new ComplexNumber(2.6 / this.pixel_size * n - 2.0, -2.6 / this.pixel_size * n2 + 1.3);
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawImage(this.mandel_img, 0, 0, this);
    }
}
