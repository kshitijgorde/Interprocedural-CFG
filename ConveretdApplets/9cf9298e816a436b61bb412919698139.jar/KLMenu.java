import java.awt.Event;
import java.awt.image.ImageObserver;
import java.net.MalformedURLException;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class KLMenu extends Applet implements Runnable
{
    final String a0 = "javabase.tripod.com";
    final String a1 = "www.mycgiserver.com";
    final boolean a2 = true;
    int a3;
    int a4;
    int WIDTH;
    int HEIGHT;
    String[] text;
    URL[] a5;
    String[] target;
    int[] a6;
    Thread a7;
    Image a8;
    Graphics a9;
    Font a10;
    Color[] a11;
    Color[] a12;
    int a13;
    boolean a14;
    boolean a15;
    int a16;
    int a17;
    int a18;
    Color a19;
    Color a20;
    boolean a21;
    final int NONE = 0;
    final int LEFT = 1;
    final int RIGHT = 2;
    final int a22 = 3;
    final int DEFAULT = 4;
    int a23;
    
    public void init() {
        this.WIDTH = this.size().width;
        this.HEIGHT = this.size().height;
        System.out.println("KLMenu v1.03 ed.smith@mail.com http://javabase.tripod.com/");
        final Color a41 = this.a41("bgcolor");
        final Color a42 = this.a41("fgcolor");
        final Color a43 = this.a41("mouse");
        if (this.getParameter("highlight") == null) {
            this.a19 = a41;
        }
        else {
            this.a19 = this.a41("highlight");
        }
        if (this.getParameter("shadow") == null) {
            this.a20 = this.a19;
        }
        else {
            this.a20 = this.a41("shadow");
        }
        this.a4 = this.a40("steps", 25);
        this.a3 = this.a40("delay", 50);
        this.a23 = this.a40("rounding", 0);
        final String s = (this.getParameter("font") != null) ? this.getParameter("font") : "Times";
        final String parameter = this.getParameter("fontstyle");
        String lowerCase;
        if (parameter == null) {
            lowerCase = "";
        }
        else {
            lowerCase = parameter.toLowerCase();
        }
        final int a44 = this.a40("fontsize", 16);
        this.a21 = (this.getParameter("leftSpread") != null);
        final String parameter2 = this.getParameter("highlightFade");
        if (parameter2 != null) {
            if (parameter2.equalsIgnoreCase("l")) {
                this.a18 = 1;
            }
            else if (parameter2.equalsIgnoreCase("r")) {
                this.a18 = 2;
            }
            else if (parameter2.equalsIgnoreCase("m")) {
                this.a18 = 3;
            }
            else {
                this.a18 = 4;
            }
        }
        else {
            this.a18 = 0;
        }
        int n;
        for (n = 0; this.getParameter("" + n) != null; ++n) {}
        this.text = new String[n];
        this.a5 = new URL[n];
        this.a6 = new int[n];
        this.target = new String[n];
        for (int i = 0; i < n; ++i) {
            this.text[i] = this.getParameter("" + i);
            if (this.getParameter("L" + i) != null) {
                try {
                    this.a5[i] = new URL(this.getParameter("L" + i));
                }
                catch (MalformedURLException ex) {
                    try {
                        this.a5[i] = new URL(this.getDocumentBase(), this.getParameter("L" + i));
                    }
                    catch (MalformedURLException ex2) {
                        System.out.println("link not ok");
                    }
                }
            }
            if (this.getParameter("T" + i) != null) {
                this.target[i] = this.getParameter("T" + i);
            }
            else {
                this.target[i] = null;
            }
        }
        this.a8 = this.createImage(this.WIDTH, this.HEIGHT);
        (this.a9 = this.a8.getGraphics()).setFont(new Font(s, ((lowerCase.indexOf(105) != -1) ? 2 : 0) | ((lowerCase.indexOf(98) != -1) ? 1 : 0), a44));
        this.setBackground(a41);
        this.setForeground(a42);
        if (this.getParameter("AppletHomePage") == null || !this.getParameter("AppletHomePage").equals("http://go.to/javabase")) {
            (this.text = new String[2])[0] = "Specify";
            this.text[1] = "AppletHome";
            this.showStatus("Home page not specified");
        }
        this.a11 = this.a37(a42, a43);
        if (this.a18 != 0) {
            this.a12 = this.a37(a41, this.a19);
        }
        this.a9.clearRect(0, 0, this.WIDTH, this.HEIGHT);
        this.a17 = this.HEIGHT / this.text.length;
        this.a16 = (this.a17 - this.a9.getFontMetrics().getHeight()) / 2 + this.a9.getFontMetrics().getAscent();
        for (int j = 0; j < this.text.length; ++j) {
            this.a46(j);
        }
        this.repaint();
    }
    
    protected Color[] a37(final Color color, final Color color2) {
        final Color[] array = new Color[this.a4];
        for (int i = 0; i < this.a4; ++i) {
            array[i] = new Color(color.getRed() + i * (color2.getRed() - color.getRed()) / this.a4, color.getGreen() + i * (color2.getGreen() - color.getGreen()) / this.a4, color.getBlue() + i * (color2.getBlue() - color.getBlue()) / this.a4);
        }
        return array;
    }
    
    protected int a40(final String s, final int n) {
        if (this.getParameter(s) != null && !this.getParameter(s).equals("")) {
            return Integer.parseInt(this.getParameter(s));
        }
        return n;
    }
    
    protected Color a41(final String s) {
        String parameter = this.getParameter(s);
        if (parameter == null || parameter.equals("")) {
            parameter = "FFFFFF";
        }
        return new Color(Integer.parseInt(parameter.substring(0, 2), 16), Integer.parseInt(parameter.substring(2, 4), 16), Integer.parseInt(parameter.substring(4, 6), 16));
    }
    
    public void start() {
        if (this.a7 == null) {
            (this.a7 = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.a7 != null && this.a7.isAlive()) {
            this.a7.stop();
            this.a7 = null;
        }
    }
    
    public void paint(final Graphics graphics) {
        this.update(graphics);
    }
    
    public void update(final Graphics graphics) {
        graphics.drawImage(this.a8, 0, 0, this);
    }
    
    public void run() {
        while (this.a7 != null) {
            try {
                Thread.sleep(this.a3);
            }
            catch (InterruptedException ex) {}
            if (this.a15) {
                this.a45();
            }
            if (this.a14) {
                this.repaint();
                this.a14 = false;
            }
        }
    }
    
    protected void a45() {
        this.a15 = false;
        for (int i = 0; i < this.text.length; ++i) {
            if (this.a13 == i) {
                if (this.a6[i] != this.a4 - 1) {
                    this.a6[i] = this.a4 - 1;
                    this.a46(i);
                }
            }
            else if (this.a6[i] > 0) {
                final int[] a6 = this.a6;
                final int n = i;
                --a6[n];
                this.a15 = true;
                this.a46(i);
            }
        }
    }
    
    protected void a46(final int n) {
        this.a14 = true;
        final int n2 = (this.WIDTH - this.a9.getFontMetrics().stringWidth(this.text[n])) / 2;
        if (this.a13 == n) {
            this.a9.setColor(this.a19);
            this.a9.fillRoundRect(0, n * this.a17, this.WIDTH, this.a17, this.a23, this.a23);
            this.a9.setColor(this.a20);
            this.a9.drawString(this.text[n], n2 + 2, this.a16 + n * this.a17 + 2);
        }
        else {
            this.a9.clearRect(0, n * this.a17, this.WIDTH, this.a17);
            if (this.a18 != 0) {
                this.a9.setColor(this.a12[this.a6[n]]);
                final int n3 = this.WIDTH * this.a6[n] / (this.a4 - 1);
                int n4 = 0;
                if (this.a18 == 1) {
                    n4 = n3 - this.WIDTH;
                }
                else if (this.a18 == 2) {
                    n4 = this.WIDTH - n3;
                }
                else if (this.a18 == 3) {
                    n4 = (this.WIDTH - n3) / 2;
                }
                this.a9.fillRoundRect(n4, n * this.a17, (this.a18 != 3) ? this.WIDTH : n3, this.a17, this.a23, this.a23);
            }
        }
        this.a9.setColor(this.a11[this.a6[n]]);
        if (this.a21) {
            for (int i = 0; i < this.text[n].length(); ++i) {
                this.a9.setColor(this.a11[this.a51(this.a6[n], i, this.text[n].length())]);
                this.a9.drawString(this.text[n].substring(i, i + 1), n2 + this.a9.getFontMetrics().stringWidth(this.text[n].substring(0, i)), this.a16 + n * this.a17);
            }
            return;
        }
        this.a9.drawString(this.text[n], n2, this.a16 + n * this.a17);
    }
    
    protected int a51(final int n, final int n2, final int n3) {
        if (!this.a21) {
            return n;
        }
        final int n4 = n - 2 * n2 * (this.a4 - 1 - n) / n3;
        if (n4 <= 0) {
            return 0;
        }
        if (n4 < this.a4 - 1) {
            return n4;
        }
        return this.a4 - 1;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        final int n3 = n2 / this.a17;
        if (this.a5[n3] != null) {
            if (this.target[n3] != null) {
                this.getAppletContext().showDocument(this.a5[n3], this.target[n3]);
            }
            else {
                this.getAppletContext().showDocument(this.a5[n3]);
            }
        }
        return true;
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        this.showStatus("KLMenu http://go.to/javabase");
        this.a13 = n2 / this.a17;
        this.a45();
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.a13 = -1;
        this.a45();
        return true;
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        return this.mouseMove(event, n, n2);
    }
    
    public KLMenu() {
        this.a3 = 50;
        this.WIDTH = 600;
        this.HEIGHT = 150;
        this.a13 = -1;
    }
}
