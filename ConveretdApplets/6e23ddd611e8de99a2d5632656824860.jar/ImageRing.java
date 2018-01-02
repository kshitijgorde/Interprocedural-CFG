import netscape.javascript.JSObject;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class ImageRing extends Applet implements Runnable
{
    private Image a312;
    private Graphics a332;
    private Image[] a362;
    private Color a213;
    private Color a363;
    private double a89;
    private double[] a191;
    private int height;
    private int a364;
    private double a365;
    private double a131;
    private double a277;
    private double a366;
    private Thread a367;
    private int a214;
    private int a368;
    private int a84;
    private int a369;
    private int a370;
    private String[][] menu;
    private String INFO;
    private boolean a32;
    
    public void init() {
        this.a312 = this.createImage(this.size().width, this.size().height);
        this.a332 = this.a312.getGraphics();
        System.out.println(this.INFO);
        final String lowerCase = this.getDocumentBase().getHost().toLowerCase();
        if (lowerCase == null || lowerCase.equals("localhost") || lowerCase.equals("127.0.0.1") || lowerCase.equals("")) {
            this.a32 = false;
        }
        if (this.getParameter("key") != null) {
            final String a98 = this.a98(lowerCase);
            String s;
            for (s = this.getParameter("key"); this.a32 && s.indexOf(32) > 0; s = s.substring(s.indexOf(32) + 1, s.length())) {
                this.a32 = !s.substring(0, s.indexOf(32)).equals(a98);
            }
            if (this.a32) {
                this.a32 = !s.equals(a98);
            }
            if (this.a32 && this.getParameter("host") != null && this.getParameter("host").length() > 4 && lowerCase.indexOf(this.getParameter("host")) > -1 && this.getParameter("key").equals(this.a98(this.getParameter("host")))) {
                this.a32 = false;
            }
        }
        this.a332.setFont(new Font("Times", 0, 9));
        final String parameter = this.getParameter("images");
        int n = -1;
        for (int i = 0; i >= 0; i = parameter.indexOf(125, i + 1), ++n) {}
        this.menu = new String[n][3];
        int index = 0;
        for (int j = 0; j < n; ++j) {
            final int n2 = parameter.indexOf(123, index) + 1;
            index = parameter.indexOf(125, n2);
            final String substring = parameter.substring(n2, index);
            System.out.println(substring);
            int index2 = -1;
            for (int k = 0; k < 3; ++k) {
                final int n3 = index2 + 1;
                index2 = substring.indexOf(44, n3);
                this.menu[j][k] = substring.substring(n3, (index2 < 0) ? substring.length() : index2);
                if (index2 < 0) {
                    ++k;
                    while (k < 3) {
                        this.menu[j][k] = "";
                        ++k;
                    }
                }
            }
        }
        if (this.getParameter("AppletHomePage") == null || !this.getParameter("AppletHomePage").equals("http://go.to/javabase")) {
            this.menu[0][0] = "AppletHomePage required";
        }
        this.a362 = new Image[n];
        this.a191 = new double[n + 1];
        final MediaTracker mediaTracker = new MediaTracker(this);
        for (int l = 0; l < n; ++l) {
            System.out.println(this.menu[l][0]);
            mediaTracker.addImage(this.a362[l] = this.getImage(this.getDocumentBase(), this.menu[l][0]), l);
        }
        try {
            mediaTracker.waitForAll();
        }
        catch (InterruptedException ex) {}
        System.out.flush();
        this.a213 = this.a50("bgCol", Color.black);
        this.a363 = this.a50("highlightCol", Color.black);
        this.a368 = this.a47("sleep", 50);
        this.a370 = this.a47("speed", 50);
        this.a89 = 0.0;
        for (int n4 = 0; n4 < this.a362.length; ++n4) {
            this.a89 += this.a362[n4].getWidth(this) / this.a362[n4].getHeight(this);
        }
        this.height = (int)(3.141592653589793 * this.size().width / this.a89);
        this.a365 = (this.size().height - this.height - 2 * this.a369) / this.size().width;
        this.a364 = (int)(this.a365 * this.size().width / 2.0);
        this.a191[0] = 0.0;
        for (int n5 = 1; n5 < this.a362.length; ++n5) {
            this.a191[n5] = this.a191[n5 - 1] + this.a362[n5 - 1].getWidth(this) / this.a362[n5 - 1].getHeight(this) * 2.0 * 3.141592653589793 / this.a89;
            System.out.println("angle" + n5 + " " + this.a191[n5] + " " + this.a362[n5 - 1].getWidth(this));
        }
        this.a191[this.a362.length] = 6.283185307179586;
        this.a277 = this.size().width / 2;
        (this.a367 = new Thread(this)).start();
    }
    
    protected Color a50(final String s, final Color color) {
        final String parameter = this.getParameter(s);
        if (parameter == null || parameter.equals("") || parameter.length() != 6) {
            return color;
        }
        return new Color(Integer.parseInt(parameter.substring(0, 2), 16), Integer.parseInt(parameter.substring(2, 4), 16), Integer.parseInt(parameter.substring(4, 6), 16));
    }
    
    private void a371() {
        this.a332.setColor(this.a213);
        this.a332.fillRect(0, 0, this.size().width, this.size().height);
        for (int i = 0; i < this.size().width; ++i) {
            final double acos = Math.acos(1.0 - i / this.a277);
            final double n = -Math.acos(1.0 - i / this.a277);
            final int n2 = (int)(this.a277 * Math.sin(acos) * this.a365);
            final int n3 = n2 + this.a364;
            this.a376(i, -n2 + this.a364, n + this.a131);
            this.a376(i, n3, acos + this.a131);
        }
        if (this.a32) {
            this.a332.setColor(this.a363);
            this.a332.drawString("http://go.to/javabase", 10, this.size().height - 2);
        }
    }
    
    private void a376(final int n, final int n2, double n3) {
        while (n3 < 0.0) {
            n3 += 6.283185307179586;
        }
        while (n3 >= 6.283185307179586) {
            n3 -= 6.283185307179586;
        }
        int n4;
        for (n4 = 0; n4 < this.a362.length - 1 && this.a191[n4 + 1] < n3; ++n4) {}
        final int n5 = (int)(this.a362[n4].getWidth(this) * ((n3 - this.a191[n4]) / (this.a191[n4 + 1] - this.a191[n4])));
        this.a332.setColor(this.a363);
        if (n4 == this.a214 && this.menu[n4][1].length() > 0) {
            this.a332.drawLine(n, n2 - this.a369, n, n2);
            this.a332.drawLine(n, n2 + this.height + this.a369, n, n2 + this.height);
        }
        this.a332.drawImage(this.a362[n4], n, n2, n + 1, n2 + this.height, n5, 0, n5 + 1, this.a362[n4].getHeight(this), this);
    }
    
    public void run() {
        this.a371();
        this.repaint();
        long n = System.currentTimeMillis();
        while (this.a367.isAlive()) {
            if (this.a366 != 0.0) {
                this.a131 += 0.001 * this.a366 * (System.currentTimeMillis() - n);
                n = System.currentTimeMillis();
                double n2;
                for (n2 = Math.acos(1.0 - this.a84 / this.a277) + this.a131; n2 < 0.0; n2 += 6.283185307179586) {}
                while (n2 >= 6.283185307179586) {
                    n2 -= 6.283185307179586;
                }
                int a214;
                for (a214 = 0; a214 < this.a362.length - 1 && this.a191[a214 + 1] < n2; ++a214) {}
                this.a214 = a214;
                this.a371();
                this.repaint();
            }
            else if (this.a84 == -1) {
                this.a214 = -1;
                this.a371();
                this.repaint();
                this.a84 = -2;
            }
            try {
                Thread.sleep(this.a368);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public boolean mouseMove(final Event event, final int a84, final int n) {
        this.a366 = this.a370 * (a84 - this.size().width / 2) / this.size().width;
        this.a366 /= 100.0;
        this.a84 = a84;
        return true;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        final int a214 = this.a214;
        if (n2 > this.size().height - 20 && this.a32) {
            try {
                this.getAppletContext().showDocument(new URL("http://go.to/javabase"), "_blank");
            }
            catch (MalformedURLException ex) {}
        }
        if (a214 == -1) {
            return true;
        }
        if (this.menu[a214][1].startsWith("javascript:")) {
            final String substring = this.menu[a214][1].substring(11);
            try {
                JSObject.getWindow((Applet)this).eval(substring);
            }
            catch (Throwable t) {}
        }
        else if (this.menu[a214][1] != "") {
            URL url = null;
            try {
                url = new URL(this.menu[a214][1]);
            }
            catch (MalformedURLException ex2) {
                try {
                    url = new URL(this.getDocumentBase(), this.menu[a214][1]);
                }
                catch (MalformedURLException ex3) {}
            }
            if (url != null) {
                if (this.menu[a214][2] != "") {
                    this.getAppletContext().showDocument(url, this.menu[a214][2]);
                }
                else {
                    this.getAppletContext().showDocument(url);
                }
            }
        }
        return true;
    }
    
    public boolean mouseDrag(final Event event, final int n, final int n2) {
        return this.mouseMove(event, n, n2);
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        return this.mouseMove(event, n, n2);
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.a366 = 0.0;
        this.a84 = -1;
        return true;
    }
    
    public void update(final Graphics graphics) {
        graphics.drawImage(this.a312, 0, 0, this);
    }
    
    public void paint(final Graphics graphics) {
        this.update(graphics);
    }
    
    protected String a98(final String s) {
        final char[] charArray = s.toCharArray();
        for (int i = 0; i < charArray.length; ++i) {
            charArray[i] = (char)((charArray[i] + i * 7) % 26 + 97);
        }
        return new String(charArray);
    }
    
    int a47(final String s, final int n) {
        if (this.getParameter(s) != null && !this.getParameter(s).equals("")) {
            return Integer.parseInt(this.getParameter(s));
        }
        return n;
    }
    
    public ImageRing() {
        this.a365 = 0.2;
        this.a214 = 1;
        this.a368 = 50;
        this.a369 = 5;
        this.INFO = "ImageRing http://go.to/javabase";
        this.a32 = true;
    }
}
