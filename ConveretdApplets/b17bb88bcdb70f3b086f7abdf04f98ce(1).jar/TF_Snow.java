import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Frame;
import java.awt.image.ImageObserver;
import java.awt.Component;
import java.awt.Event;
import java.awt.MediaTracker;
import java.awt.Color;
import java.awt.image.PixelGrabber;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class TF_Snow extends Applet implements Runnable
{
    private Thread Tarek;
    private Image im;
    private Graphics os;
    private PixelGrabber pg;
    private Image image;
    private String copyright_text;
    private String url;
    private String target;
    private Color bg;
    private Color fg;
    private MediaTracker tracker;
    private int delay;
    private int snow_update;
    private int width;
    private int height;
    private int[] PixelIndex;
    private int snow_number;
    private int[] snow;
    private int[] old_snow;
    private boolean loaded;
    private static boolean mouse_enter;
    private static boolean cursor_hand;
    private static boolean url_ok;
    private static boolean copyright;
    
    public void stop() {
        if (this.Tarek != null) {
            (this.Tarek = null).stop();
        }
        System.gc();
    }
    
    public boolean mouseEnter(final Event e, final int x, final int y) {
        if (TF_Snow.cursor_hand) {
            this.getFrame(this).setCursor(12);
            return true;
        }
        return false;
    }
    
    public void MakeSnow(final Graphics g) {
        if (this.image != null) {
            this.os.drawImage(this.image, 0, 0, this);
            for (int i = 0; i < this.snow_number; i += 2) {
                this.os.setColor(this.bg);
                this.os.fillOval(this.old_snow[i], this.old_snow[i + 1], 1, 1);
            }
            this.os.setColor(new Color(15329769));
            for (int j = 0; j < this.snow_number; j += 2) {
                final int[] snow = this.snow;
                final int n = j + 1;
                ++snow[n];
                if (j % 5 == 0) {
                    final int[] snow2 = this.snow;
                    final int n2 = j + 1;
                    ++snow2[n2];
                    this.os.setColor(new Color(15329769));
                }
                else {
                    this.os.setColor(new Color(10197915));
                }
                final int[] snow3 = this.snow;
                final int n3 = j;
                snow3[n3] += (int)(Math.random() * 3.0) - 1;
                this.old_snow[j] = this.snow[j];
                this.old_snow[j + 1] = this.snow[j + 1];
                this.os.fillOval(this.snow[j], this.snow[j + 1], 1, 1);
                if (this.snow[j + 1] > this.height - 1) {
                    this.snow[j + 1] = 0;
                    this.snow[j] = (int)(Math.random() * this.width);
                }
                if (this.snow[j + 1] > 0 && this.PixelIndex[this.snow[j + 1] * this.width + this.snow[j]] != 0) {
                    this.os.setColor(new Color(15329769));
                    this.os.fillOval(this.snow[j], this.snow[j + 1] - 1, 3, 3);
                    this.PixelIndex[this.snow[j + 1] * this.width + this.snow[j] - this.width] = 1;
                    this.snow[j + 1] = 0;
                    this.snow[j] = (int)(Math.random() * this.width);
                    this.old_snow[j] = this.snow[j];
                    this.old_snow[j + 1] = this.snow[j + 1];
                }
            }
            g.drawImage(this.im, 0, 0, this);
        }
    }
    
    public boolean mouseExit(final Event e, final int x, final int y) {
        if (TF_Snow.cursor_hand) {
            this.getFrame(this).setCursor(0);
            return true;
        }
        return false;
    }
    
    public void paint(final Graphics g) {
        if (!TF_Snow.copyright) {
            g.setColor(new Color(0));
            g.fillRect(0, 0, this.width, this.height);
            g.setColor(new Color(16777215));
            g.drawString("Unregisterd Version", 5, 10);
            g.drawString("Please contact the Author :", 5, 25);
            g.drawString("     \" Tarek Fouda \"", 5, 40);
            g.drawString("tarek@fouda.de", 5, 55);
            g.drawString("Thank you . . .", 5, 70);
            this.stop();
        }
        else if (this.loaded) {
            this.MakeSnow(g);
        }
        else {
            g.setColor(this.bg);
            g.fillRect(0, 0, this.width, this.height);
            g.setColor(this.fg);
            g.drawString("Please wait ...", this.width / 2 - 30, this.height / 2 + 3);
            if (this.image != null) {
                this.loaded = true;
            }
        }
    }
    
    public TF_Snow() {
        this.loaded = false;
    }
    
    public Color getColor(String s) {
        if (s != null && s.length() >= 5) {
            try {
                int i = s.indexOf(",");
                int R = Integer.parseInt(s.substring(0, i));
                s = s.substring(i + 1);
                i = s.indexOf(",");
                int G = Integer.parseInt(s.substring(0, i));
                int B = Integer.parseInt(s.substring(i + 1));
                R = ((R > 255) ? 255 : R);
                R = ((R < 0) ? 0 : R);
                G = ((G > 255) ? 255 : G);
                G = ((G < 0) ? 0 : G);
                B = ((B > 255) ? 255 : B);
                B = ((B < 0) ? 0 : B);
                return new Color(R, G, B);
            }
            catch (Exception e) {
                return null;
            }
        }
        return null;
    }
    
    public boolean mouseUp(final Event e, final int x, final int y) {
        if ((e.modifiers & 0x4) == 0x4) {
            this.init();
        }
        else if (TF_Snow.url_ok) {
            this.MakeURL(this.url, this.target);
        }
        return true;
    }
    
    public Frame getFrame(final Component c) {
        if (c != null && !(c instanceof Frame)) {
            return (Frame)c.getParent();
        }
        return null;
    }
    
    public void updateSnow() {
        ++this.snow_update;
        if (this.snow_update == 2300) {
            this.init();
        }
    }
    
    public void update(final Graphics g) {
        this.paint(g);
    }
    
    static {
        TF_Snow.mouse_enter = false;
        TF_Snow.cursor_hand = false;
        TF_Snow.url_ok = true;
        TF_Snow.copyright = false;
    }
    
    public void start() {
        if (this.Tarek == null) {
            (this.Tarek = new Thread(this)).start();
        }
    }
    
    public String getAppletInfo() {
        return "Name : TF_Snow\r\nAuthor : Tarek Fouda\r\nContact : tarek@fouda.de\r\nHomepage : http://www.fouda.de\r\nLast Modify : 05.12.1999\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n";
    }
    
    public void SetupImages() {
        try {
            this.image = this.getImage(this.getCodeBase(), this.getParameter("image"));
            if (this.image != null) {
                this.tracker.addImage(this.image, 0);
                (this.pg = new PixelGrabber(this.image, 0, 0, this.width, this.height, this.PixelIndex, 0, this.width)).grabPixels();
            }
            this.tracker.waitForID(0);
        }
        catch (InterruptedException e) {}
    }
    
    public void MakeURL(final String url, final String target) {
        URL link = null;
        if (url != null) {
            try {
                link = new URL(this.getDocumentBase(), url);
                this.getAppletContext().showDocument(link, target);
            }
            catch (MalformedURLException e) {
                link = null;
            }
        }
    }
    
    public void getParameters() {
        try {
            final String param = this.getParameter("copyright");
            TF_Snow.copyright = param.toLowerCase().equalsIgnoreCase(this.copyright_text);
        }
        catch (Exception e) {
            TF_Snow.copyright = false;
        }
        try {
            this.url = this.getParameter("url");
        }
        catch (Exception e) {
            TF_Snow.url_ok = false;
        }
        try {
            this.target = this.getParameter("target");
        }
        catch (Exception e) {
            this.target = "_top";
        }
        try {
            this.bg = this.getColor(this.getParameter("bg_color"));
        }
        catch (Exception e) {
            this.bg = new Color(0);
        }
        try {
            this.fg = this.getColor(this.getParameter("fg_color"));
        }
        catch (Exception e) {
            this.fg = new Color(16777215);
        }
        try {
            this.delay = Integer.parseInt(this.getParameter("delay"));
            this.delay = ((this.delay > 200) ? 200 : this.delay);
            this.delay = ((this.delay < 0) ? 0 : this.delay);
        }
        catch (Exception e) {
            this.delay = 30;
        }
        try {
            final String param = this.getParameter("cursor_hand");
            TF_Snow.cursor_hand = param.toLowerCase().equalsIgnoreCase("yes");
        }
        catch (Exception e) {
            TF_Snow.cursor_hand = false;
        }
    }
    
    public void run() {
        while (this.Tarek != null) {
            this.showStatus("Programmed by Tarek Fouda 'tarek@fouda.de'");
            this.repaint();
            try {
                this.updateSnow();
                Thread.sleep(this.delay);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public void init() {
        this.copyright_text = "http://www.fouda.de";
        this.delay = 30;
        this.bg = new Color(0);
        this.fg = new Color(16777215);
        this.snow_number = 500;
        this.snow_update = 0;
        this.getParameters();
        this.setBackground(this.bg);
        this.tracker = new MediaTracker(this);
        this.width = this.size().width;
        this.height = this.size().height;
        this.PixelIndex = new int[this.width * this.height];
        this.snow = new int[this.snow_number];
        this.old_snow = new int[this.snow_number];
        for (int i = 0; i < this.snow_number; i += 2) {
            this.snow[i] = (int)(Math.random() * this.width);
            this.snow[i + 1] = (int)(Math.random() * this.height - this.height);
        }
        this.SetupImages();
        this.im = this.createImage(this.width, this.height);
        this.os = this.im.getGraphics();
        System.gc();
    }
}
