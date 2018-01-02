import java.awt.Component;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Label;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Kaleidoscope extends Applet implements Runnable
{
    KaleidoscopeCanvas canvas;
    KaleidoscopeControl control;
    TriangleCreator triangleCreator;
    Label message;
    Thread kicker;
    
    public void init() {
        this.setLayout(new BorderLayout());
        this.control = new KaleidoscopeControl(this);
        this.canvas = new KaleidoscopeCanvas(this.control.getCanvasSize());
        (this.message = new Label(" ")).setAlignment(1);
        this.message.setForeground(Color.blue);
        this.add("North", this.canvas);
        this.add("Center", this.message);
        this.add("South", this.control);
    }
    
    public void resetCreator() {
        this.triangleCreator = null;
    }
    
    void setNewTriangle() {
        if (this.triangleCreator == null) {
            switch (this.control.getMethod()) {
                default: {
                    this.triangleCreator = new Method0(this.control, this);
                    break;
                }
            }
        }
        if (this.triangleCreator != null && this.triangleCreator.next()) {
            this.canvas.setBaseTriangle(this.triangleCreator.getImage(), this.triangleCreator.getSize());
            return;
        }
        this.imageProcessError();
    }
    
    public void run() {
        this.displayMessage("");
        Thread.currentThread().setPriority(1);
    Label_0013_Outer:
        while (true) {
            while (true) {
                try {
                    while (true) {
                        this.setNewTriangle();
                        Thread.sleep(this.control.getUpdateInterval());
                    }
                }
                catch (Exception ex) {
                    continue Label_0013_Outer;
                }
                continue;
            }
        }
    }
    
    public void start() {
        if (this.kicker == null) {
            (this.kicker = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.kicker != null && this.kicker.isAlive()) {
            this.kicker.stop();
        }
        this.kicker = null;
    }
    
    public void displayMessage(final String text) {
        this.message.setText(text);
    }
    
    public void imageLoadError() {
        System.err.println("image load error");
        this.showStatus("image load error");
        this.displayMessage("image load error");
        this.stop();
    }
    
    public void imageProcessError() {
        System.err.println("image convert error");
        this.showStatus("image convert error");
        this.displayMessage("image convert error");
        this.stop();
    }
}
