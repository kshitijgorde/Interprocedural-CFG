import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Event;
import java.awt.Component;
import java.awt.image.ImageProducer;
import java.awt.Color;
import java.awt.image.PixelGrabber;
import java.awt.MediaTracker;
import java.awt.Image;
import java.awt.image.MemoryImageSource;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class TF_Flame extends Applet implements Runnable
{
    Thread Tarek;
    MemoryImageSource MeImSo;
    Image im;
    Image image;
    Image image1;
    Image image2;
    MediaTracker tracker;
    PixelGrabber pg;
    String copyright_text;
    Color bg;
    Color fg;
    int r;
    int g;
    int b;
    int[] Lines;
    int[] BGImage;
    int[] Flame;
    int[] PixelIndex;
    int scroll;
    int delay;
    int power;
    int width;
    int height;
    double burn_plus;
    double burn_minus;
    double BurnPower;
    double BurnSpeed;
    boolean burnning_out;
    boolean FlameFade;
    boolean Countinue;
    boolean MouseReaction;
    boolean CopyRight;
    static double fade;
    static double burn;
    
    static {
        TF_Flame.fade = 1.0;
        TF_Flame.burn = 1.0;
    }
    
    public void DoIt() {
        final byte b = (byte)this.r;
        final byte b2 = (byte)this.g;
        final byte b3 = (byte)this.b;
        for (int i = 0; i < this.PixelIndex.length; ++i) {
            final int n = this.BGImage[i];
            final int n2 = this.Lines[i];
            final int n3 = this.Flame[(i + this.scroll) % this.PixelIndex.length] & 0xFF;
            final double n4 = (0xFF & n >> 16) / TF_Flame.burn + ((0xFF & n2 >> 16) * n3 >> b) / TF_Flame.fade / this.power;
            final double n5 = (0xFF & n >> 8) / TF_Flame.burn + ((0xFF & n2 >> 8) * n3 >> b2) / TF_Flame.fade / this.power;
            final double n6 = (0xFF & n) / TF_Flame.burn + ((0xFF & n2) * n3 >> b3) / TF_Flame.fade / this.power;
            this.PixelIndex[i] = (0xFF000000 | (int)((n4 > 255.0) ? 255.0 : n4) << 16 | (int)((n5 > 255.0) ? 255.0 : n5) << 8 | (int)((n6 > 255.0) ? 255.0 : n6));
        }
        this.scroll += this.width;
        this.scroll = ((this.scroll > this.width * this.height) ? 0 : this.scroll);
        if (this.burnning_out) {
            this.burn_plus += this.BurnSpeed;
            TF_Flame.burn = this.burn_plus;
            if (this.Countinue) {
                if (this.burn_plus > this.BurnPower) {
                    this.burn_plus = this.BurnPower;
                    this.burn_minus -= this.BurnSpeed;
                    TF_Flame.burn = this.burn_minus;
                    if (this.burn_minus < 1.0) {
                        this.burn_minus = this.BurnPower;
                        this.burn_plus = 1.0;
                        TF_Flame.burn = this.burn_plus;
                    }
                }
            }
            else {
                TF_Flame.burn = ((TF_Flame.burn > this.BurnPower) ? this.BurnPower : TF_Flame.burn);
            }
        }
        if (this.FlameFade) {
            this.burn_plus += this.BurnSpeed;
            TF_Flame.fade = this.burn_plus;
            if (this.Countinue) {
                if (this.burn_plus > this.BurnPower) {
                    this.burn_plus = this.BurnPower;
                    this.burn_minus -= this.BurnSpeed;
                    TF_Flame.fade = this.burn_minus;
                    if (this.burn_minus < 1.0) {
                        this.burn_minus = this.BurnPower;
                        this.burn_plus = 1.0;
                        TF_Flame.fade = this.burn_plus;
                    }
                }
            }
            else {
                TF_Flame.fade = ((TF_Flame.fade > this.BurnPower) ? this.BurnPower : TF_Flame.fade);
            }
        }
    }
    
    public void MakeBlackImage() {
        this.image = this.createImage(new MemoryImageSource(this.width, this.height, new int[0], 0, this.width));
    }
    
    public void SetupImages() {
        try {
            this.image = this.getImage(this.getCodeBase(), this.getParameter("bg_image"));
            if (this.image == null) {
                this.burnning_out = false;
                this.MakeBlackImage();
            }
            this.image1 = this.getImage(this.getCodeBase(), this.getParameter("flame_line_image"));
            this.image2 = this.getImage(this.getCodeBase(), this.getParameter("flame_image"));
            this.tracker.addImage(this.image, 0);
            this.tracker.addImage(this.image1, 1);
            this.tracker.addImage(this.image2, 2);
            this.tracker.waitForAll();
            (this.pg = new PixelGrabber(this.image, 0, 0, this.width, this.height, this.BGImage, 0, this.width)).grabPixels();
            (this.pg = new PixelGrabber(this.image1, 0, 0, this.width, this.height, this.Lines, 0, this.width)).grabPixels();
            (this.pg = new PixelGrabber(this.image2, 0, 0, this.width, this.height, this.Flame, 0, this.width)).grabPixels();
        }
        catch (InterruptedException ex) {}
    }
    
    public Color getColor(String substring) {
        final int index = substring.indexOf(",");
        final int int1 = Integer.parseInt(substring.substring(0, index));
        substring = substring.substring(index + 1);
        final int index2 = substring.indexOf(",");
        return new Color(int1, Integer.parseInt(substring.substring(0, index2)), Integer.parseInt(substring.substring(index2 + 1)));
    }
    
    public void getParameters() {
        try {
            this.CopyRight = this.getParameter("copyright").toLowerCase().equalsIgnoreCase(this.copyright_text);
        }
        catch (Exception ex) {
            this.CopyRight = false;
        }
        try {
            this.BurnSpeed = Integer.parseInt(this.getParameter("burn_speed")) / 100.0;
            this.BurnSpeed = ((this.BurnSpeed > 1.0) ? 1.0 : this.BurnSpeed);
            this.BurnSpeed = ((this.BurnSpeed < 0.01) ? 0.01 : this.BurnSpeed);
        }
        catch (Exception ex2) {
            this.BurnSpeed = 0.01;
        }
        try {
            this.BurnPower = Integer.parseInt(this.getParameter("burn_power"));
            this.BurnPower = ((this.BurnPower > 8.0) ? 8.0 : this.BurnPower);
            this.BurnPower = ((this.BurnPower < 1.0) ? 1.0 : this.BurnPower);
        }
        catch (Exception ex3) {
            this.BurnPower = 5.0;
        }
        try {
            final String parameter = this.getParameter("flame_fade");
            this.FlameFade = (!parameter.toLowerCase().equalsIgnoreCase("no") && this.FlameFade);
            this.FlameFade = (parameter.toLowerCase().equalsIgnoreCase("yes") || this.FlameFade);
        }
        catch (Exception ex4) {
            this.FlameFade = false;
        }
        try {
            final String parameter2 = this.getParameter("burnning_out");
            this.burnning_out = (!parameter2.toLowerCase().equalsIgnoreCase("no") && this.burnning_out);
            this.burnning_out = (parameter2.toLowerCase().equalsIgnoreCase("yes") || this.burnning_out);
        }
        catch (Exception ex5) {
            this.burnning_out = false;
        }
        try {
            final String parameter3 = this.getParameter("countinue");
            this.Countinue = (!parameter3.toLowerCase().equalsIgnoreCase("no") && this.Countinue);
            this.Countinue = (parameter3.toLowerCase().equalsIgnoreCase("yes") || this.Countinue);
        }
        catch (Exception ex6) {
            this.Countinue = true;
        }
        try {
            this.bg = this.getColor(this.getParameter("bg_color"));
        }
        catch (Exception ex7) {
            this.bg = new Color(0);
        }
        try {
            this.fg = this.getColor(this.getParameter("fg_color"));
        }
        catch (Exception ex8) {
            this.fg = new Color(16777215);
        }
        try {
            this.r = Integer.parseInt(this.getParameter("flame_red"));
            this.r = ((this.r > 8) ? 8 : this.r);
            this.r = ((this.r < 1) ? 1 : this.r);
        }
        catch (Exception ex9) {
            this.r = 3;
        }
        try {
            this.g = Integer.parseInt(this.getParameter("flame_green"));
            this.g = ((this.g > 8) ? 8 : this.g);
            this.g = ((this.g < 1) ? 1 : this.g);
        }
        catch (Exception ex10) {
            this.g = 4;
        }
        try {
            this.b = Integer.parseInt(this.getParameter("flame_blue"));
            this.b = ((this.b > 8) ? 8 : this.b);
            this.b = ((this.b < 1) ? 1 : this.b);
        }
        catch (Exception ex11) {
            this.b = 5;
        }
        try {
            final String parameter4 = this.getParameter("mouse_click");
            this.MouseReaction = (!parameter4.toLowerCase().equalsIgnoreCase("no") && this.MouseReaction);
            this.MouseReaction = (parameter4.toLowerCase().equalsIgnoreCase("yes") || this.MouseReaction);
        }
        catch (Exception ex12) {
            this.MouseReaction = false;
        }
        try {
            this.power = Integer.parseInt(this.getParameter("flame_power"));
            this.power = ((this.power > 10) ? 10 : this.power);
            this.power = ((this.power < 1) ? 1 : this.power);
        }
        catch (Exception ex13) {
            this.power = 2;
        }
        try {
            this.delay = Integer.parseInt(this.getParameter("delay"));
            this.delay = ((this.delay > 60) ? 60 : this.delay);
            this.delay = ((this.delay < 0) ? 0 : this.delay);
        }
        catch (Exception ex14) {
            this.delay = 20;
        }
    }
    
    public void init() {
        this.copyright_text = "http://www.fouda.de";
        this.CopyRight = false;
        this.Countinue = true;
        this.burnning_out = true;
        this.FlameFade = false;
        this.BurnPower = 5.0;
        this.BurnSpeed = 0.01;
        this.bg = new Color(0);
        this.fg = new Color(16777215);
        this.r = 3;
        this.g = 4;
        this.b = 5;
        this.power = 2;
        this.delay = 20;
        this.MouseReaction = false;
        this.getParameters();
        this.setBackground(this.bg);
        this.tracker = new MediaTracker(this);
        this.width = this.size().width;
        this.height = this.size().height;
        this.Lines = new int[this.width * this.height];
        this.BGImage = new int[this.width * this.height];
        this.Flame = new int[this.width * this.height];
        this.PixelIndex = new int[this.width * this.height];
        for (int i = 0; i < this.PixelIndex.length; ++i) {
            this.PixelIndex[i] = -16777216;
            this.Lines[i] = -16777216;
            this.Flame[i] = -16777216;
        }
        this.burn_plus = TF_Flame.burn;
        this.burn_minus = this.BurnPower;
        System.gc();
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (this.MouseReaction) {
            this.power = ((this.power >= 10) ? 1 : (this.power + 1));
        }
        return true;
    }
    
    public void paint(final Graphics graphics) {
        if (!this.CopyRight) {
            graphics.setColor(new Color(0));
            graphics.fillRect(0, 0, this.width, this.height);
            graphics.setColor(new Color(16777215));
            graphics.drawString("Unregisterd Version", 5, 10);
            graphics.drawString("Please contact the Autor :", 5, 25);
            graphics.drawString("     \" Tarek Fouda \"", 5, 40);
            graphics.drawString("tarek@fouda.de", 5, 55);
            graphics.drawString("Thanx . . .", 5, 70);
        }
        else if (this.im != null) {
            graphics.drawImage(this.im, 0, 0, this);
            if (this.MouseReaction) {
                graphics.setColor(this.fg);
                graphics.drawString("Flame_Power : " + this.power, 5, 10);
            }
        }
        else {
            graphics.setColor(this.bg);
            graphics.fillRect(0, 0, this.width, this.height);
            graphics.setColor(this.fg);
            graphics.drawString("Please wait ...", this.width / 2 - 30, this.height / 2 + 3);
        }
    }
    
    public void run() {
        this.SetupImages();
        (this.MeImSo = new MemoryImageSource(this.width, this.height, this.PixelIndex, 0, this.width)).setAnimated(true);
        this.MeImSo.setFullBufferUpdates(true);
        this.im = this.createImage(this.MeImSo);
        this.repaint();
        while (this.Tarek != null) {
            this.showStatus("Programmed by Tarek Fouda 'tarek@fouda.de'");
            this.DoIt();
            try {
                Thread.sleep(this.delay);
            }
            catch (Exception ex) {}
            this.MeImSo.newPixels(0, 0, this.width, this.height);
        }
    }
    
    public void start() {
        if (this.Tarek == null) {
            (this.Tarek = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.Tarek != null) {
            this.Tarek.stop();
            this.Tarek = null;
        }
        System.gc();
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
