// 
// Decompiled by Procyon v0.5.30
// 

package dlt.mandelbrot;

import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;

public class f extends JPanel
{
    private Image a;
    private a if;
    
    public f(final a if1) {
        this.if = if1;
        this.a();
    }
    
    public void a(final Image a) {
        this.a = a;
    }
    
    private void a() {
        this.enableEvents(16L);
    }
    
    public void paint(final Graphics graphics) {
        super.paint(graphics);
        if (this.a != null) {
            graphics.drawImage(this.a, 0, 0, this.getWidth(), this.getHeight(), this);
        }
    }
    
    protected void processMouseEvent(final MouseEvent mouseEvent) {
        switch (mouseEvent.getID()) {
            case 501: {
                if ((mouseEvent.getModifiers() & 0x10) != 0x0) {
                    this.if.doMagnify(mouseEvent.getX(), mouseEvent.getY());
                    this.if.disablePlotButton();
                }
                if ((mouseEvent.getModifiers() & 0x4) != 0x0) {
                    this.if.doBack();
                    this.if.disablePlotButton();
                    break;
                }
                break;
            }
            case 502: {}
            case 500: {}
        }
        super.processMouseEvent(mouseEvent);
    }
}
