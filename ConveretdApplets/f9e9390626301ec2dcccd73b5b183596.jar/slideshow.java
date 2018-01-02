import java.awt.image.ImageObserver;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class slideshow extends Applet implements Runnable
{
    private Thread runner;
    private boolean amIdrawing;
    private boolean fading_out;
    private boolean[] got_da_pic;
    private MediaTracker trackin;
    private Image[] pic_img;
    private String[] pic_names;
    private Image buffer;
    private Graphics g_buffer;
    private Image temp_img;
    private Graphics g_temp;
    private int width;
    private int height;
    private pixelman pic;
    private pixelman current;
    private int num_of_pics;
    private int current_pic;
    private double amplitude;
    private double wave_position;
    private int till_change;
    
    public slideshow() {
        this.runner = null;
        this.amIdrawing = false;
    }
    
    public void init() {
        this.num_of_pics = Integer.parseInt(this.getParameter("NUMBER"));
        this.pic_img = new Image[this.num_of_pics];
        this.pic_names = new String[this.num_of_pics];
        this.trackin = new MediaTracker(this);
        this.got_da_pic = new boolean[this.num_of_pics];
        for (int i = 0; i < this.num_of_pics; ++i) {
            this.pic_names[i] = this.getParameter("PIC" + (i + 1));
        }
        this.width = this.getSize().width;
        this.height = this.getSize().height;
        this.buffer = this.createImage(this.width, this.height);
        this.g_buffer = this.buffer.getGraphics();
        this.temp_img = this.createImage(this.width, this.height);
        this.g_temp = this.temp_img.getGraphics();
        for (int j = 0; j < this.num_of_pics; ++j) {
            this.pic_img[j] = this.getImage(this.getCodeBase(), this.pic_names[j]);
            this.trackin.addImage(this.pic_img[j], j);
            this.got_da_pic[j] = false;
        }
        this.current = new pixelman();
        this.current_pic = 0;
        this.amplitude = 0.0;
        this.wave_position = 0.0;
    }
    
    public void start() {
        if (this.runner == null) {
            (this.runner = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.runner != null) {
            this.runner.stop();
            this.runner = null;
        }
    }
    
    public void run() {
        try {
            this.trackin.waitForID(this.current_pic);
        }
        catch (InterruptedException ex) {}
        this.got_da_pic[this.current_pic] = true;
        this.g_temp.drawImage(this.pic_img[this.current_pic], 0, 0, this.width, this.height, this);
        this.pic = new pixelman(this.temp_img);
        while (true) {
            this.showStatus("Trippy Slide Show v1.0 by Warren Price");
            this.till_change = 50;
            this.amplitude = 0.0;
            this.wave_position = 0.0;
            this.fading_out = true;
            this.current.MakeEqual(this.pic);
            this.makeNewImage();
            this.repaint();
            if (this.current_pic + 1 != this.num_of_pics && !this.got_da_pic[this.current_pic + 1]) {
                try {
                    this.trackin.waitForID(this.current_pic + 1);
                }
                catch (InterruptedException ex2) {}
                this.got_da_pic[this.current_pic + 1] = true;
            }
            try {
                Thread.sleep(2000L);
            }
            catch (InterruptedException ex3) {}
            while (this.till_change >= 0) {
                this.amplitude += 2.0;
                this.wave_position += 0.39269908169872414;
                this.shadeImage();
                this.makeNewImage();
                this.repaint();
                --this.till_change;
                try {
                    Thread.sleep(50L);
                }
                catch (InterruptedException ex4) {}
            }
            ++this.current_pic;
            if (this.current_pic == this.num_of_pics) {
                this.current_pic = 0;
            }
            this.fading_out = false;
            this.g_temp.drawImage(this.pic_img[this.current_pic], 0, 0, this.width, this.height, this);
            this.pic = new pixelman(this.temp_img);
            this.current.MakeEqual(this.pic);
            this.till_change = 50;
            for (int i = 0; i < this.pic.getHeight(); ++i) {
                for (int j = 0; j < this.pic.getWidth(); ++j) {
                    this.pic.setRGB(-1, j, i);
                    this.pic.setAlpha(this.pic.Alpha(j, i), j, i);
                }
            }
            while (this.till_change >= 0) {
                this.amplitude -= 2.0;
                this.wave_position -= 0.39269908169872414;
                this.shadeImage();
                this.makeNewImage();
                this.repaint();
                --this.till_change;
                try {
                    Thread.sleep(50L);
                }
                catch (InterruptedException ex5) {}
            }
        }
    }
    
    public void paint(final Graphics graphics) {
        if (!this.amIdrawing) {
            this.update(graphics);
        }
    }
    
    public void update(final Graphics graphics) {
        graphics.drawImage(this.buffer, 0, 0, this);
    }
    
    public void makeNewImage() {
        this.amIdrawing = true;
        for (int i = 0; i < this.current.getHeight(); ++i) {
            int n = 0;
            final int n2 = (int)(Math.sin(3.141592653589793 * i / 20.0 + this.wave_position) * this.amplitude);
            if (n2 < 0) {
                n = -n2;
            }
            for (int j = 0; j < this.current.getWidth(); ++j) {
                this.current.setRGB(this.pic.getRGB(n, i), j, i);
                if (j < n2) {
                    n = 0;
                }
                if (j >= n2) {
                    ++n;
                }
                if (n >= this.pic.getWidth()) {
                    n = this.pic.getWidth() - 1;
                }
            }
        }
        this.current.DrawPixels(this.g_buffer, 0, 0, this.width, this.height);
        this.amIdrawing = false;
    }
    
    public void shadeImage() {
        this.amIdrawing = true;
        for (int i = 0; i < this.pic.getHeight(); ++i) {
            for (int j = 0; j < this.pic.getWidth(); ++j) {
                this.pic.setAlpha(this.pic.Alpha(j, i), j, i);
                if (this.fading_out) {
                    final int red = this.pic.getRed(j, i);
                    int n;
                    if (this.till_change != 0) {
                        n = red + (255 - red) / this.till_change;
                    }
                    else {
                        n = 255;
                    }
                    this.pic.setRed(n, j, i);
                    final int green = this.pic.getGreen(j, i);
                    int n2;
                    if (this.till_change != 0) {
                        n2 = green + (255 - green) / this.till_change;
                    }
                    else {
                        n2 = 255;
                    }
                    this.pic.setGreen(n2, j, i);
                    final int blue = this.pic.getBlue(j, i);
                    int n3;
                    if (this.till_change != 0) {
                        n3 = blue + (255 - blue) / this.till_change;
                    }
                    else {
                        n3 = 255;
                    }
                    this.pic.setBlue(n3, j, i);
                }
                else {
                    final int red2 = this.pic.getRed(j, i);
                    final int red3 = this.pic.Red(j, i);
                    int n4;
                    if (this.till_change != 0) {
                        n4 = red2 - (red2 - red3) / this.till_change;
                    }
                    else {
                        n4 = red3;
                    }
                    this.pic.setRed(n4, j, i);
                    final int green2 = this.pic.getGreen(j, i);
                    final int green3 = this.pic.Green(j, i);
                    int n5;
                    if (this.till_change != 0) {
                        n5 = green2 - (green2 - green3) / this.till_change;
                    }
                    else {
                        n5 = green3;
                    }
                    this.pic.setGreen(n5, j, i);
                    final int blue2 = this.pic.getBlue(j, i);
                    final int blue3 = this.pic.Blue(j, i);
                    int n6;
                    if (this.till_change != 0) {
                        n6 = blue2 - (blue2 - blue3) / this.till_change;
                    }
                    else {
                        n6 = blue3;
                    }
                    this.pic.setBlue(n6, j, i);
                }
            }
        }
        this.amIdrawing = false;
    }
}
