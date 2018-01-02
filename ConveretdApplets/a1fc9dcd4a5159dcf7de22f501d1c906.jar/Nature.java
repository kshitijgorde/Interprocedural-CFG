import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.FontMetrics;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Nature extends Applet implements Runnable
{
    Thread Animator;
    Image BackImg;
    Image OffScreenImage;
    Graphics OffScreenGraphics;
    Color RainColor;
    int slptime;
    int Season;
    int LRdir;
    int MaxCount;
    int Radius;
    int MaxSpeed;
    int WndSpeed;
    int[] Xpos;
    int[] Ypos;
    int[] Height;
    int[] Speed;
    boolean RndDirection;
    boolean Accumulate;
    boolean DevInfo;
    
    private void fog(final Graphics g) {
        g.drawImage(this.OffScreenImage, 0, 0, this.size().width, this.size().height, this);
    }
    
    public void paint(final Graphics g) {
        this.update(g);
    }
    
    private void rain(final Graphics g) {
        int Stat = 0;
        this.OffScreenGraphics.setColor(this.RainColor);
        this.OffScreenGraphics.drawImage(this.BackImg, 0, 0, this.size().width, this.size().height, this);
        Stat = (int)(Math.random() * 100.0);
        if (this.RndDirection) {
            if (Stat > 0 && Stat < 5) {
                this.LRdir = 1;
                this.WndSpeed = (int)(Math.random() * -5.0);
            }
            else if (Stat > 55 && Stat < 60) {
                this.LRdir = 3;
                this.WndSpeed = (int)(Math.random() * 5.0);
            }
            else if (Stat > 98) {
                this.LRdir = 2;
                this.WndSpeed = 0;
            }
        }
        for (int I = 0; I < this.MaxCount; ++I) {
            final int[] ypos = this.Ypos;
            final int n = I;
            ypos[n] += this.Speed[I];
            if (this.LRdir == 1) {
                final int[] xpos = this.Xpos;
                final int n2 = I;
                xpos[n2] += this.WndSpeed;
            }
            else if (this.LRdir == 3) {
                final int[] xpos2 = this.Xpos;
                final int n3 = I;
                xpos2[n3] += this.WndSpeed;
            }
            if (this.Xpos[I] < 0) {
                this.Xpos[I] = this.size().width - 1;
            }
            if (this.Xpos[I] > this.size().width) {
                this.Xpos[I] = 1;
            }
            if (this.Ypos[I] > this.size().height) {
                this.Ypos[I] = 0;
                this.Xpos[I] = (int)(Math.random() * this.size().width);
            }
            this.OffScreenGraphics.drawLine(this.Xpos[I], this.Ypos[I], this.Xpos[I] + this.WndSpeed / 2, this.Ypos[I] + 2);
            this.OffScreenGraphics.drawLine(this.Xpos[I], this.Ypos[I] + 1, this.Xpos[I] + this.WndSpeed / 2, this.Ypos[I] + 3);
        }
        g.drawImage(this.OffScreenImage, 0, 0, this.size().width, this.size().height, this);
    }
    
    public Nature() {
        this.BackImg = null;
        this.RainColor = new Color(145, 164, 164);
        this.slptime = 75;
        this.Season = 1;
        this.LRdir = 2;
        this.MaxCount = 100;
        this.Radius = 2;
        this.MaxSpeed = 8;
        this.WndSpeed = 0;
        this.Xpos = new int[1024];
        this.Ypos = new int[1024];
        this.Height = new int[1024];
        this.Speed = new int[1024];
        this.RndDirection = true;
        this.Accumulate = false;
        this.DevInfo = false;
    }
    
    public void getAppletParams() {
        final Image DummyImage = this.createImage(this.size().width, this.size().height);
        final Graphics DummyGraphics = DummyImage.getGraphics();
        final Graphics ThisG = this.getGraphics();
        final MediaTracker Tracker = new MediaTracker(this);
        final FontMetrics MyMetrics = DummyGraphics.getFontMetrics();
        if (this.getParameter("DeveloperInfo") != null) {
            if (this.getParameter("DeveloperInfo").compareTo("Michael Chancey Jr.") == 0) {
                this.DevInfo = true;
            }
            else {
                this.DevInfo = false;
            }
        }
        else {
            this.DevInfo = false;
        }
        if (this.getParameter("speed") != null) {
            this.slptime = Integer.parseInt(this.getParameter("speed"));
        }
        if (this.getParameter("season") != null) {
            if (this.getParameter("season").compareTo("winter") == 0) {
                this.Season = 1;
            }
            else if (this.getParameter("season").compareTo("summer") == 0) {
                this.Season = 2;
                if (this.getParameter("raincolor") != null) {
                    this.RainColor = this.stringToColor(this.getParameter("raincolor"));
                }
            }
        }
        if (this.getParameter("accumulate") != null && this.getParameter("accumulate").compareTo("yes") == 0) {
            this.Accumulate = true;
        }
        if (this.getParameter("fallcount") != null) {
            this.MaxCount = Integer.parseInt(this.getParameter("fallcount"));
        }
        if (this.getParameter("randomdir") != null) {
            if (this.getParameter("randomdir").compareTo("yes") == 0) {
                this.RndDirection = true;
            }
            else {
                this.RndDirection = false;
            }
        }
        if (this.getParameter("graphic") != null) {
            Tracker.addImage(this.BackImg = this.getImage(this.getCodeBase(), this.getParameter("graphic")), 0);
            try {
                this.showStatus("Loading Graphic: " + this.getParameter("graphic"));
                Tracker.waitForAll();
            }
            catch (InterruptedException e) {
                this.showStatus("Error Code: " + e.getMessage());
            }
        }
        else {
            DummyGraphics.drawString("Graphic Needed", 0, 20);
            this.BackImg = DummyImage;
        }
        if (!this.DevInfo) {
            final int Top = this.size().height / 2 - 40;
            DummyGraphics.drawString("This Applet Was", this.size().width / 2 - MyMetrics.stringWidth("This Appplet Was") / 2, Top + 20);
            DummyGraphics.drawString("Created By:", this.size().width / 2 - MyMetrics.stringWidth("Created By:") / 2, Top + 40);
            DummyGraphics.drawString("Michael Chancey Jr.", this.size().width / 2 - MyMetrics.stringWidth("Michael Chancey Jr.") / 2, Top + 60);
            DummyGraphics.drawString("mchancey@bellatlantic.net", this.size().width / 2 - MyMetrics.stringWidth("mchancey@bellatlantic.net") / 2, Top + 80);
            this.BackImg = DummyImage;
        }
    }
    
    public String[][] getParameterInfo() {
        final String[][] info = new String[0][];
        return info;
    }
    
    void initForm() {
        this.setBackground(Color.lightGray);
        this.setForeground(Color.black);
        this.setLayout(new BorderLayout());
    }
    
    private void snow(final Graphics g) {
        int Stat = 0;
        this.OffScreenGraphics.setColor(Color.white);
        this.OffScreenGraphics.drawImage(this.BackImg, 0, 0, this.size().width, this.size().height, this);
        Stat = (int)(Math.random() * 100.0);
        if (this.RndDirection) {
            if (Stat > 0 && Stat < 5) {
                this.LRdir = 1;
                this.WndSpeed = (int)(Math.random() * 5.0);
            }
            else if (Stat > 55 && Stat < 60) {
                this.LRdir = 3;
                this.WndSpeed = (int)(Math.random() * 5.0);
            }
            else if (Stat > 98) {
                this.LRdir = 2;
            }
        }
        for (int I = 0; I < this.MaxCount; ++I) {
            final int[] ypos = this.Ypos;
            final int n = I;
            ypos[n] += this.Speed[I];
            if (this.LRdir == 1) {
                final int[] xpos = this.Xpos;
                final int n2 = I;
                xpos[n2] -= this.WndSpeed;
            }
            else if (this.LRdir == 3) {
                final int[] xpos2 = this.Xpos;
                final int n3 = I;
                xpos2[n3] += this.WndSpeed;
            }
            if (this.Xpos[I] < 0) {
                this.Xpos[I] = this.size().width - 1;
            }
            if (this.Xpos[I] > this.size().width) {
                this.Xpos[I] = 1;
            }
            if (this.Ypos[I] >= this.Height[this.Xpos[I]]) {
                this.Ypos[I] = 0;
                this.Xpos[I] = (int)(Math.random() * this.size().width);
                if (this.Accumulate && Math.random() * 100.0 > 50.0) {
                    final int[] height = this.Height;
                    final int n4 = this.Xpos[I];
                    height[n4] -= this.Radius - 1;
                    final int[] height2 = this.Height;
                    final int n5 = this.Xpos[I] - 1;
                    height2[n5] -= this.Radius - 2;
                    final int[] height3 = this.Height;
                    final int n6 = this.Xpos[I] + 1;
                    height3[n6] -= this.Radius - 2;
                }
            }
            this.OffScreenGraphics.fillOval(this.Xpos[I], this.Ypos[I], this.Radius, this.Radius);
        }
        for (int I = 0; I < this.size().width; ++I) {
            this.OffScreenGraphics.drawLine(I, this.Height[I], I, this.size().height);
        }
        g.drawImage(this.OffScreenImage, 0, 0, this.size().width, this.size().height, this);
    }
    
    public void update(final Graphics g) {
        switch (this.Season) {
            case 1: {
                this.snow(g);
                break;
            }
            case 2: {
                this.rain(g);
                break;
            }
            case 3: {
                this.fog(g);
                break;
            }
        }
    }
    
    public void start() {
    }
    
    public void run() {
        while (true) {
            this.repaint();
            try {
                Thread.sleep(this.slptime);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public void init() {
        this.initForm();
        this.getAppletParams();
        this.OffScreenImage = this.createImage(this.size().width, this.size().height);
        this.OffScreenGraphics = this.OffScreenImage.getGraphics();
        (this.Animator = new Thread(this)).start();
        for (int I = 0; I < this.MaxCount; ++I) {
            this.Speed[I] = (int)(Math.random() * this.MaxSpeed) + 3;
            this.Xpos[I] = (int)(Math.random() * this.size().width);
            this.Ypos[I] = (int)(Math.random() * this.size().height);
        }
        final int height = this.size().height;
        for (int I = 0; I < 1024; ++I) {
            this.Height[I] = height;
        }
    }
    
    private Color stringToColor(final String paramValue) {
        final int red = Integer.decode("0x" + paramValue.substring(0, 2));
        final int green = Integer.decode("0x" + paramValue.substring(2, 4));
        final int blue = Integer.decode("0x" + paramValue.substring(4, 6));
        return new Color(red, green, blue);
    }
}
