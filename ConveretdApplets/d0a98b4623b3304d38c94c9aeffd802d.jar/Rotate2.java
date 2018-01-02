import java.awt.Rectangle;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.image.PixelGrabber;
import java.awt.image.ImageObserver;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Graphics;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Rotate2 extends Applet implements Runnable
{
    private Thread Rotate;
    private boolean Image_Loaded;
    private Graphics grafics;
    private Image Image_1;
    private Image Image_2;
    private Image Image_3;
    private Image buffer;
    private int BackGround_Color;
    private int Image_Width;
    private int Image_Height;
    private int Wide;
    private int High;
    private int counter;
    private int Step;
    private int Pause;
    private int[] pixels1;
    private int[] pixels2;
    private String Image_Name;
    private String color;
    private String step;
    private String pause;
    
    public void init() {
        final Dimension size = this.size();
        this.Wide = size.width;
        this.High = size.height;
        this.buffer = this.createImage(this.Wide, this.High);
        this.Image_Name = this.getParameter("Image");
        this.color = this.getParameter("BackGround");
        if (this.color != null) {
            this.BackGround_Color = Integer.parseInt(this.color, 16);
        }
        else {
            this.BackGround_Color = Integer.parseInt("000000", 16);
        }
        this.step = this.getParameter("Steps");
        if (this.step != null) {
            this.Step = Integer.parseInt(this.step);
        }
        else {
            this.Step = 255;
        }
        if (this.Step > 255) {
            this.Step = 255;
        }
        this.Step = 255 / this.Step;
        this.pause = this.getParameter("Speed");
        if (this.pause != null) {
            this.Pause = Integer.parseInt(this.pause);
        }
        else {
            this.Pause = 10;
        }
    }
    
    public void start() {
        if (this.Rotate == null) {
            (this.Rotate = new Thread(this)).start();
        }
    }
    
    public void run() {
        boolean b = false;
        this.setBackground(new Color(this.BackGround_Color));
        if (!this.Image_Loaded) {
            this.repaint();
            this.grafics = this.getGraphics();
            final MediaTracker mediaTracker = new MediaTracker(this);
            mediaTracker.addImage(this.Image_1 = this.getImage(this.getDocumentBase(), this.Image_Name), 0);
            try {
                mediaTracker.waitForAll();
                this.Image_Loaded = !mediaTracker.isErrorAny();
            }
            catch (InterruptedException ex) {}
            if (!this.Image_Loaded) {
                this.stop();
                this.grafics.drawString("Error loading images!", 10, 40);
                return;
            }
        }
        this.Image_Width = this.Image_1.getWidth(this);
        this.Image_Height = this.Image_1.getHeight(this);
        this.Image_2 = this.Image_1;
        this.Image_3 = this.Image_1;
        final int n = this.Image_Width * this.Image_Height;
        this.pixels1 = new int[n];
        this.pixels2 = new int[n];
        try {
            new PixelGrabber(this.Image_1, 0, 0, this.Image_Width, this.Image_Height, this.pixels1, 0, this.Image_Width).grabPixels();
        }
        catch (InterruptedException ex2) {}
        for (int i = 0; i < n; i += this.Image_Width) {
            for (int j = 0; j < this.Image_Width; ++j) {
                this.pixels2[j + i] = this.pixels1[i + this.Image_Width - j - 1];
            }
        }
        this.Image_3 = this.createImage(new MemoryImageSource(this.Image_Width, this.Image_Height, this.pixels2, 0, this.Image_Width));
        this.Image_Width = this.size().width;
        this.Image_Height = this.size().height;
        while (true) {
            try {
                this.showStatus("");
                this.counter = 0;
                while (this.counter < this.Image_Width) {
                    this.displayImage(this.grafics);
                    Thread.sleep(this.Pause);
                    this.counter += this.Step;
                }
                b = !b;
                if (b) {
                    this.Image_1 = this.Image_2;
                }
                else {
                    this.Image_1 = this.Image_3;
                }
                this.counter = this.Image_Width;
                while (this.counter > 0) {
                    this.displayImage(this.grafics);
                    Thread.sleep(this.Pause);
                    this.counter -= this.Step;
                }
            }
            catch (InterruptedException ex3) {
                this.stop();
            }
        }
    }
    
    public void paint(final Graphics graphics) {
        final Rectangle clipRect = graphics.getClipRect();
        if (this.Image_Loaded) {
            graphics.clearRect(clipRect.x, clipRect.y, clipRect.width + this.Step, clipRect.height + this.Step);
            this.displayImage(graphics);
        }
        else {
            graphics.clearRect(clipRect.x, clipRect.y, clipRect.width, clipRect.height);
        }
        graphics.drawString("Loading image ...", 10, 20);
    }
    
    private void displayImage(Graphics graphics) {
        if (!this.Image_Loaded) {
            return;
        }
        final Graphics graphics2 = graphics;
        graphics = this.buffer.getGraphics();
        graphics.clearRect((this.size().width - this.Image_Width) / 2, (this.size().height - this.Image_Height) / 2, this.Image_Width, this.Image_Height);
        graphics.drawImage(this.Image_1, (this.size().width - this.Image_Width + this.counter) / 2, (this.size().height - this.Image_Height) / 2, this.Image_Width - this.counter, this.Image_Height, null);
        graphics2.drawImage(this.buffer, 0, 0, null);
    }
    
    public void stop() {
        if (this.Rotate != null) {
            this.Rotate.stop();
            this.Rotate = null;
        }
    }
}
