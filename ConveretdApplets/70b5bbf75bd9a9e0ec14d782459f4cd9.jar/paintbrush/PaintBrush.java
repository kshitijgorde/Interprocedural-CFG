// 
// Decompiled by Procyon v0.5.30
// 

package paintbrush;

import Acme.JPM.Encoders.ImageEncoder;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.Button;
import java.awt.Component;
import java.awt.LayoutManager;
import storage.Storage;
import Acme.JPM.Encoders.GifEncoder;
import java.io.IOException;
import java.io.OutputStream;
import Acme.JPM.Encoders.JpegEncoder;
import java.io.ByteArrayOutputStream;
import java.awt.Image;
import java.awt.Color;
import java.awt.TextField;
import java.awt.Checkbox;
import java.awt.ScrollPane;
import java.awt.MediaTracker;
import java.applet.Applet;

public class PaintBrush extends Applet
{
    MediaTracker tracker;
    ScrollPane pane;
    PaintCanvas pc;
    ActionCanvas ac;
    ColorCanvas cc;
    FontChoice fc;
    InputField ifd;
    Checkbox gray;
    TextField width;
    TextField height;
    String jpgq;
    String saveport;
    boolean usetrans;
    boolean ViewOnly;
    String imagename;
    String loadimagename;
    private ImageReceiver imageReceiver;
    Color lightGray;
    int ccHt;
    
    public PaintBrush() {
        this.usetrans = false;
        this.ViewOnly = false;
        this.lightGray = new Color(212, 210, 204);
    }
    
    public byte[] convertImage(final Image image, final String filename, final boolean transparent) {
        final String typename = filename.toLowerCase();
        if (typename.endsWith("jpg")) {
            try {
                final ByteArrayOutputStream baos = new ByteArrayOutputStream();
                final JpegEncoder jpg = new JpegEncoder(image, (OutputStream)baos);
                ((ImageEncoder)jpg).encode();
                return baos.toByteArray();
            }
            catch (IOException ie) {
                ie.printStackTrace();
                return null;
            }
        }
        if (typename.endsWith("gif")) {
            try {
                final ByteArrayOutputStream baos = new ByteArrayOutputStream();
                final GifEncoder ge = new GifEncoder(image, (OutputStream)baos);
                if (transparent) {
                    ge.setTransparentColor(this.pc.getBackColor());
                }
                ((ImageEncoder)ge).encode();
                return baos.toByteArray();
            }
            catch (IOException ie) {
                ie.printStackTrace();
                return null;
            }
        }
        throw new RuntimeException("Bad image type: " + filename);
    }
    
    public void doPreview() {
        if (this.imageReceiver != null) {
            this.imageReceiver.sendImage(this.pc.getCurrentImage());
        }
        else {
            System.out.println("Can't preview: no receiver.");
        }
    }
    
    public void doSave() {
        try {
            final byte[] raw = this.convertImage(this.pc.getCurrentImage(), this.imagename, this.usetrans);
            Storage.write(this.imagename, raw);
        }
        catch (Exception ie) {
            System.out.println("Error saving file: ");
            System.out.println("ie");
            ie.printStackTrace();
        }
    }
    
    public void init() {
        Storage.setApplet((Applet)this);
        this.setLayout(null);
        this.setBackground(this.lightGray);
        this.tracker = new MediaTracker(this);
        final String VO = this.getParameter("ViewOnly");
        if (VO.equalsIgnoreCase("true")) {
            this.ViewOnly = true;
        }
        final String sw = this.getParameter("cwidth");
        final String sh = this.getParameter("cheight");
        int width = 500;
        int height = 500;
        try {
            width = new Integer(sw);
            height = new Integer(sh);
            width = ((width > 500) ? 500 : width);
            height = ((height > 500) ? 500 : height);
        }
        catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
        }
        (this.pane = new ScrollPane()).setBackground(Color.white);
        this.pane.setSize(width + 4, height + 4);
        this.pane.setLocation(80, 35);
        if (this.ViewOnly) {
            this.pane.setLocation(5, 5);
        }
        (this.pc = new PaintCanvas(this, width, height, this.ViewOnly)).setSize(width, height);
        this.pane.add(this.pc);
        (this.fc = new FontChoice(this.pc)).setEnabled(false);
        this.fc.setSize(250, 30);
        this.fc.setLocation(10, 0);
        (this.ac = new ActionCanvas(this.pc, this.fc)).setSize(55, 200);
        this.ac.setLocation(5, 35);
        (this.cc = new ColorCanvas(this.pc)).setSize(200, 35);
        this.ccHt = height + 55;
        this.cc.setLocation(100, this.ccHt);
        final String sg = this.getParameter("grayscale");
        if (sg != null) {
            this.cc.setGrayscale(sg.equals("true"));
        }
        final String usetranss = this.getParameter("usetrans");
        if (usetranss != null) {
            this.usetrans = Boolean.valueOf(usetranss);
        }
        this.jpgq = this.getParameter("jpgqual");
        this.saveport = this.getParameter("saveport");
        this.imagename = this.getParameter("image");
        this.loadimagename = this.getParameter("loadimage");
        (this.ifd = new InputField(this)).setSize(300, 30);
        this.ifd.setLocation(177, 0);
        final Button clear = new Button("Clear");
        clear.addActionListener((ActionListener)new PaintBrush.PaintBrush$1(this));
        clear.setSize(70, 20);
        clear.setLocation(5, 258);
        final Button undo = new Button("Undo");
        undo.addActionListener((ActionListener)new PaintBrush.PaintBrush$2(this));
        undo.setSize(70, 20);
        undo.setLocation(5, 236);
        this.add(this.pane);
        if (!this.ViewOnly) {
            this.add(this.ac);
            this.add(this.cc);
            this.add(this.fc);
            this.add(clear);
            this.add(undo);
        }
        this.loadImages();
        this.loadPainting();
    }
    
    public void loadImages() {
        final Image spray = this.getImage(this.getCodeBase(), "spray.gif");
        final Image spray_on = this.getImage(this.getCodeBase(), "spray_on.gif");
        final Image pen = this.getImage(this.getCodeBase(), "pen.gif");
        final Image pen_on = this.getImage(this.getCodeBase(), "pen_on.gif");
        final Image eraser = this.getImage(this.getCodeBase(), "eraser.gif");
        final Image eraser_on = this.getImage(this.getCodeBase(), "eraser_on.gif");
        this.tracker.addImage(spray, 0);
        this.tracker.addImage(spray_on, 1);
        this.tracker.addImage(pen, 0);
        this.tracker.addImage(pen_on, 1);
        this.tracker.addImage(eraser, 0);
        this.tracker.addImage(eraser_on, 1);
        try {
            this.tracker.waitForAll();
        }
        catch (InterruptedException e) {
            System.out.println("Media tracker: " + e.toString());
        }
        this.ac.setImages(spray, spray_on, pen, pen_on, eraser, eraser_on);
    }
    
    private void loadPainting() {
        try {
            final Image image = Storage.getImage(this.loadimagename);
            this.pc.initImage(image);
        }
        catch (IOException ie) {
            ie.printStackTrace();
        }
    }
    
    public void loadPainting2(final String imagename) {
        try {
            final Image image = Storage.getImage(imagename);
            this.pc.initImage2(image);
        }
        catch (IOException ie) {
            ie.printStackTrace();
        }
    }
    
    public void paint(final Graphics g) {
        g.setColor(Color.black);
        if (!this.ViewOnly) {
            g.drawString("Right click to select fill color.", 120, this.ccHt + 55);
        }
    }
    
    public void setImageReceiver(final ImageReceiver imageReceiver) {
        this.imageReceiver = imageReceiver;
        System.out.println("PaintBrush sending to " + imageReceiver);
    }
    
    public void update(final Graphics g) {
        this.paint(g);
    }
}
