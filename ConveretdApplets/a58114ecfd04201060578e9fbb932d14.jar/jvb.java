import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Component;
import java.awt.MediaTracker;
import java.net.URL;
import java.util.Random;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class jvb extends Applet implements Runnable
{
    private static final int SINTABINCX = 280;
    private static final int SINTABINCY = 100;
    private static final int MAXOBJECTS = 9;
    private boolean bInit;
    private int wScreen;
    private int hScreen;
    private Image imRender;
    private Graphics gRender;
    private VbObject pObject;
    private int iObject;
    private VbObject[] Objects;
    private int cObjects;
    private VbSprite GlobalSprite;
    private float xrot;
    private float yrot;
    private float zrot;
    private float xinc;
    private float yinc;
    private float zinc;
    private Random m_rand;
    private Thread m_jvb;
    private String m_Object;
    private final String PARAM_Object = "Object";
    
    public void stop() {
        if (this.m_jvb != null) {
            this.m_jvb.stop();
            this.m_jvb = null;
        }
        this.bInit = false;
    }
    
    private Image ImageLoader(final URL url, final String s) {
        final MediaTracker mediaTracker = new MediaTracker(this);
        final Image image = this.getImage(url, s);
        mediaTracker.addImage(image, 0);
        try {
            mediaTracker.waitForAll();
        }
        catch (InterruptedException ex) {}
        return image;
    }
    
    public void paint(final Graphics graphics) {
        if (this.bInit) {
            this.gRender.setColor(Color.white);
            this.gRender.drawString("Java VectorBalls (c)Obi. Press +/- for shapes.", 2 - this.wScreen / 2, this.hScreen / 2 - 2);
            graphics.drawImage(this.imRender, 0, 0, this);
            return;
        }
        final Color[] array = { Color.blue, Color.magenta, Color.red, Color.white, Color.green, Color.yellow };
        graphics.setColor(array[this.m_rand.nextInt() % array.length]);
        graphics.drawString("Loading Graphics...", 5, 15);
    }
    
    public String[][] getParameterInfo() {
        return new String[][] { { "Object", "String", "ID Object: 0..1 (Currently) " } };
    }
    
    public void destroy() {
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public boolean keyUp(final Event event, final int n) {
        switch (n) {
            case 43: {
                this.iObject = (this.iObject + 1) % this.cObjects;
                this.pObject = this.Objects[this.iObject];
                break;
            }
            case 45: {
                this.iObject = (this.iObject - 1 + this.cObjects) % this.cObjects;
                this.pObject = this.Objects[this.iObject];
                break;
            }
        }
        return true;
    }
    
    public void start() {
        if (this.m_jvb == null) {
            (this.m_jvb = new Thread(this)).start();
        }
        this.GlobalSprite = new VbSprite(this.ImageLoader(this.getDocumentBase(), "orb_all.gif"), 6, this);
        for (int i = 0; i < this.cObjects; ++i) {
            this.Objects[i].SetSprite(this.GlobalSprite);
        }
        this.bInit = true;
    }
    
    public jvb() {
        this.m_rand = new Random();
        this.m_Object = "Apple";
    }
    
    public String getAppletInfo() {
        return "Name: jvb\r\n" + "Author: Andy Buchanan\r\n" + "Created with Microsoft Visual J++ Version 1.1";
    }
    
    private void DrawVBFrame(final Graphics graphics) {
        if (!this.bInit) {
            return;
        }
        graphics.setColor(Color.black);
        graphics.fillRect(-this.wScreen / 2, -this.hScreen / 2, this.wScreen, this.hScreen);
        this.pObject.Balls[0].TreeWalkAndDraw(graphics);
    }
    
    private void UpdateVBFrame() {
        if (!this.bInit) {
            return;
        }
        if (this.pObject == null) {
            return;
        }
        final VbMatrix vbMatrix = new VbMatrix();
        final VbMatrix vbMatrix2 = new VbMatrix();
        final VbMatrix vbMatrix3 = new VbMatrix();
        vbMatrix.RotX(this.xrot);
        vbMatrix2.RotY(this.yrot);
        vbMatrix3.RotZ(this.zrot);
        final VbMatrix vbMatrix4 = new VbMatrix();
        vbMatrix4.Concat(vbMatrix2, vbMatrix);
        final VbMatrix vbMatrix5 = new VbMatrix();
        vbMatrix5.Concat(vbMatrix3, vbMatrix4);
        this.pObject.TransformAndAnimate(vbMatrix5);
        this.xrot += this.xinc;
        this.yrot += this.yinc;
        this.zrot += this.zinc;
    }
    
    public void run() {
        while (true) {
            try {
                while (true) {
                    if (this.bInit) {
                        this.UpdateVBFrame();
                        this.DrawVBFrame(this.gRender);
                    }
                    this.repaint();
                    Thread.sleep(10L);
                }
            }
            catch (InterruptedException ex) {
                this.stop();
                continue;
            }
            break;
        }
    }
    
    public void init() {
        final String parameter = this.getParameter("Object");
        if (parameter != null) {
            this.m_Object = parameter;
        }
        this.wScreen = 480;
        this.hScreen = 300;
        this.setBackground(Color.black);
        this.resize(this.wScreen, this.hScreen);
        final int int1 = Integer.parseInt(this.m_Object);
        this.Objects = new VbObject[9];
        this.cObjects = 0;
        (this.Objects[this.cObjects] = new VbObject_Rectangle(11, 11, 242, 242, false, true, 40)).SetAnimation(1, 40, 280, 100, 0, 0);
        ++this.cObjects;
        this.Objects[this.cObjects] = new VbObject_Cube(6, 264, false, false);
        ++this.cObjects;
        this.Objects[this.cObjects] = new VbObject_Cube(6, 132, true, true);
        ++this.cObjects;
        this.Objects[this.cObjects] = new VbObject_ABLogo(224);
        ++this.cObjects;
        this.Objects[this.cObjects] = new VbObject_Cube(5, 125, false, true);
        ++this.cObjects;
        this.Objects[this.cObjects] = new VbObject_Cube(7, 160, true, true);
        ++this.cObjects;
        (this.Objects[this.cObjects] = new VbObject_Rectangle(12, 12, 264, 264, true, true, 60)).SetAnimation(1, 20, 140, 50, 0, 0);
        ++this.cObjects;
        (this.Objects[this.cObjects] = new VbObject_Cube(5, 130, false, true)).SetAnimation(2, 15, 70, 0, 0, 0);
        ++this.cObjects;
        (this.Objects[this.cObjects] = new VbObject_Cube(6, 150, true, true)).SetAnimation(3, 10, 46, 3, 0, 0);
        ++this.cObjects;
        this.iObject = int1;
        this.pObject = this.Objects[this.iObject];
        this.xrot = 0.0f;
        this.yrot = 0.0f;
        this.zrot = 0.0f;
        this.xinc = 0.017453292f;
        this.yinc = 0.034906585f;
        this.zinc = -0.034906585f;
        this.imRender = this.createImage(this.wScreen, this.hScreen);
        (this.gRender = this.imRender.getGraphics()).setColor(Color.black);
        this.gRender.fillRect(0, 0, this.wScreen, this.hScreen);
        this.gRender.translate(this.wScreen / 2, this.hScreen / 2);
        System.out.println("Applet Initialised OK");
    }
}
