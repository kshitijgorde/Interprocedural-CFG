import java.awt.Frame;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Component;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class DrawTest extends Applet
{
    DrawPanel panel;
    DrawControls controls;
    
    public void destroy() {
        this.remove(this.panel);
        this.remove(this.controls);
    }
    
    public String getAppletInfo() {
        return "A simple drawing program.";
    }
    
    public void init() {
        this.setLayout(new BorderLayout());
        this.panel = new DrawPanel();
        this.controls = new DrawControls(this.panel);
        this.add("Center", this.panel);
        this.add("North", this.controls);
    }
    
    public static void main(final String[] array) {
        final Frame frame = new Frame("DrawTest");
        final DrawTest drawTest = new DrawTest();
        drawTest.init();
        drawTest.start();
        frame.add("Center", drawTest);
        frame.setSize(500, 750);
        frame.show();
    }
}
