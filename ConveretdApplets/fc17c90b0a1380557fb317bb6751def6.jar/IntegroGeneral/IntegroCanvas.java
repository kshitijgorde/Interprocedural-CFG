// 
// Decompiled by Procyon v0.5.30
// 

package IntegroGeneral;

import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Component;
import java.awt.Frame;
import java.net.URL;
import java.applet.Applet;
import java.awt.Image;
import java.awt.Canvas;

public class IntegroCanvas extends Canvas
{
    String fieldFileName;
    boolean imageIsReady;
    private Image thePicture;
    private Applet fieldParentApplet;
    
    public IntegroCanvas() {
        this.fieldFileName = "";
        this.imageIsReady = false;
        this.fieldParentApplet = new Applet();
        this.initialize();
    }
    
    public IntegroCanvas(final String fileName) {
        this.fieldFileName = "";
        this.imageIsReady = false;
        this.fieldParentApplet = new Applet();
        this.fieldFileName = fileName;
    }
    
    public IntegroCanvas(final String fileName, final Applet applet) {
        this.fieldFileName = "";
        this.imageIsReady = false;
        this.fieldParentApplet = new Applet();
        this.fieldParentApplet = applet;
        this.fieldFileName = fileName;
    }
    
    public String getFileName() {
        if (this.fieldFileName == null) {
            try {
                this.fieldFileName = new String();
            }
            catch (Throwable t) {
                System.err.println("Exception creating fileNameproperty.");
            }
        }
        return this.fieldFileName;
    }
    
    public Applet getParentApplet() {
        return this.fieldParentApplet;
    }
    
    private void handleException(final Throwable exception) {
    }
    
    public void init() {
        try {
            this.thePicture = this.loadResourceImage(this.fieldFileName);
            this.thePicture = this.thePicture.getScaledInstance(this.getSize().width, this.getSize().height, 1);
            this.imageIsReady = true;
            this.invalidate();
            this.repaint();
        }
        catch (Exception e) {
            System.out.println(e);
            e.printStackTrace(System.out);
        }
    }
    
    private void initialize() {
        this.setName("IntegroCanvas");
        this.setSize(100, 30);
    }
    
    public Image loadResourceImage(final String fileName) {
        URL imageURL = null;
        Applet parentApplet = null;
        if (this.fieldParentApplet == null) {
            parentApplet = (Applet)this.getParent();
        }
        else {
            parentApplet = this.fieldParentApplet;
        }
        try {
            imageURL = parentApplet.getCodeBase();
        }
        catch (Exception e) {
            e.printStackTrace(System.err);
        }
        return parentApplet.getImage(imageURL, fileName);
    }
    
    public static void main(final String[] args) {
        try {
            Frame frame;
            try {
                final Class aFrameClass = Class.forName("uvm.abt.edit.TestFrame");
                frame = aFrameClass.newInstance();
            }
            catch (Throwable t) {
                frame = new Frame();
            }
            final IntegroCanvas aIntegroCanvas = new IntegroCanvas();
            frame.add("Center", aIntegroCanvas);
            frame.setSize(aIntegroCanvas.getSize());
            frame.setVisible(true);
        }
        catch (Throwable t2) {
            System.err.println("Exception occurred in main() of %1IntegroCanvas");
        }
    }
    
    public void paint(final Graphics g) {
        if (this.imageIsReady) {
            g.drawImage(this.thePicture, 0, 0, this);
        }
    }
    
    public void setFileName(final String fileName) {
        this.fieldFileName = fileName;
    }
    
    public void setParentApplet(final Applet parentApplet) {
        this.fieldParentApplet = parentApplet;
        this.init();
    }
}
