import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class FadeScroll extends Applet implements Runnable
{
    Image a179;
    Graphics a180;
    Thread a46;
    int y;
    int a181;
    int a182;
    int a183;
    int a184;
    int a185;
    int a186;
    int a187;
    float a188;
    String a189;
    String a190;
    String a100;
    String target;
    Color[] in;
    Color[] out;
    Color[] a191;
    Color[] a192;
    Color a49;
    Color a193;
    Color a194;
    boolean a35;
    
    public void init() {
        this.a179 = this.createImage(this.size().width, this.size().height);
        this.a180 = this.a179.getGraphics();
        this.a190 = this.getParameter("message");
        this.a189 = this.getParameter("font");
        if (this.a189 == null) {
            this.a189 = "Times";
        }
        this.a184 = this.a77("fontsize", 12);
        this.a180.setFont(new Font(this.a189, 0, this.a184));
        this.a183 = this.a77("speed", 40);
        this.a185 = this.a77("shadowOffsetX", 1);
        this.a186 = this.a77("shadowOffsetY", 1);
        this.a187 = this.a77("shadowDelay", 0);
        this.a182 = this.a77("delay", 30);
        this.a100 = this.getParameter("link");
        this.target = this.getParameter("target");
        this.a49 = this.a81("textColor", Color.red);
        this.a193 = this.a81("backgroundColor", Color.white);
        this.a194 = this.a81("shadowColor", Color.orange);
        this.in = this.a74(this.a193, this.a49, 100);
        this.out = this.a74(this.a49, this.a193, 100);
        this.a191 = this.a74(this.a193, this.a194, 100);
        this.a192 = this.a74(this.a194, this.a193, 100);
        this.y = this.a180.getFontMetrics().getAscent() + (this.size().height - this.a180.getFontMetrics().getHeight()) / 2;
        this.a188 = this.a180.getFontMetrics().stringWidth(this.a190);
        this.a188 /= this.a190.length();
        this.a181 = (int)((this.size().width - 40) / this.a188) - this.a187;
        System.out.println("FadeScroll http://www.javabase.co.uk/");
        if (this.getParameter("AppletHomePage") == null || !this.getParameter("AppletHomePage").equals("http://go.to/javabase")) {
            this.a180.clipRect(0, 0, 1, 1);
            this.showStatus("Home page not specified");
        }
        final String host = this.getDocumentBase().getHost();
        if (host == null || host.equals("localhost") || host.equals("127.0.0.1") || host.equals("")) {
            this.a35 = false;
        }
        if (this.getParameter("key") != null) {
            final String a84 = this.a84(host);
            String s;
            for (s = this.getParameter("key"); this.a35 && s.indexOf(32) > 0; s = s.substring(s.indexOf(32) + 1, s.length())) {
                this.a35 = !s.substring(0, s.indexOf(32)).equals(a84);
            }
            if (this.a35) {
                this.a35 = !s.equals(a84);
            }
            if (this.a35 && this.getParameter("host") != null && this.getParameter("host").length() > 4 && host.indexOf(this.getParameter("host")) > -1 && this.getParameter("key").equals(this.a84(this.getParameter("host")))) {
                this.a35 = false;
            }
        }
        if (this.a35) {
            this.target = "http://www.javabase.fsnet.co.uk/";
        }
    }
    
    protected String a84(final String s) {
        final char[] charArray = s.toCharArray();
        for (int i = 0; i < charArray.length; ++i) {
            charArray[i] = (char)((charArray[i] + i * 7) % 26 + 97);
        }
        return new String(charArray);
    }
    
    public void paint(final Graphics graphics) {
        this.update(graphics);
    }
    
    public void update(final Graphics graphics) {
        graphics.drawImage(this.a179, 0, 0, this);
    }
    
    public void start() {
        if (this.a46 == null) {
            (this.a46 = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.a46 != null) {
            this.a46.stop();
            this.a46 = null;
        }
    }
    
    public void run() {
        float n = 0.0f;
        while (this.a46 != null) {
            n += (float)(this.a183 / 100.0);
            this.a180.setColor(this.a193);
            this.a180.fillRect(0, 0, this.size().width, this.size().height);
            final int n2 = this.size().width - 30 - (int)(n * this.a188);
            this.a196(this.a191, n - this.a187, n2 + this.a185, this.y + this.a186, this.a190);
            this.a196(this.a192, n - this.a181 + 10.0f - this.a187, n2 + this.a185, this.y + this.a186, this.a190);
            this.a196(this.in, n, n2, this.y, this.a190);
            this.a196(this.out, n - this.a181 + 10.0f, n2, this.y, this.a190);
            try {
                Thread.sleep(this.a182);
            }
            catch (InterruptedException ex) {}
            this.getGraphics().drawImage(this.a179, 0, 0, this);
            if (n - this.a181 - 2.0f - this.a187 > this.a190.length()) {
                n = 0.0f;
            }
        }
    }
    
    private void a196(final Color[] array, final float n, final int n2, final int n3, final String s) {
        final String substring = s.substring(0, 1);
        final int n4 = (int)((n > 10.0f) ? 0.0f : (100.0f - 10.0f * n));
        if (n > 0.0f && n < this.a181 * 3 / 4 + 3) {
            this.a180.setColor(array[n4]);
            this.a180.drawString(substring, n2, n3);
        }
        if (s.length() > 1) {
            this.a196(array, n - 1.0f, n2 + this.a180.getFontMetrics().stringWidth(substring), n3, s.substring(1, s.length()));
        }
    }
    
    protected Color[] a74(final Color color, final Color color2, final int n) {
        final Color[] array = new Color[n];
        for (int i = 0; i < n; ++i) {
            array[i] = new Color(color2.getRed() + i * (color.getRed() - color2.getRed()) / (n - 1), color2.getGreen() + i * (color.getGreen() - color2.getGreen()) / (n - 1), color2.getBlue() + i * (color.getBlue() - color2.getBlue()) / (n - 1));
        }
        return array;
    }
    
    int a77(final String s, final int n) {
        if (this.getParameter(s) != null && !this.getParameter(s).equals("")) {
            return Integer.parseInt(this.getParameter(s));
        }
        return n;
    }
    
    protected String a79(final String s, final String s2) {
        if (this.getParameter(s) != null && !this.getParameter(s).equals("")) {
            return this.getParameter(s);
        }
        return s2;
    }
    
    protected Color a81(final String s, final Color color) {
        final String parameter = this.getParameter(s);
        if (parameter == null || parameter.equals("") || parameter.length() != 6) {
            return color;
        }
        return new Color(Integer.parseInt(parameter.substring(0, 2), 16), Integer.parseInt(parameter.substring(2, 4), 16), Integer.parseInt(parameter.substring(4, 6), 16));
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        URL url = null;
        try {
            url = new URL(this.a100);
        }
        catch (MalformedURLException ex) {
            try {
                url = new URL(this.getDocumentBase(), this.a100);
            }
            catch (MalformedURLException ex2) {}
        }
        if (url != null) {
            if (this.target != null && this.target != "") {
                this.getAppletContext().showDocument(url, this.target);
            }
            else {
                this.getAppletContext().showDocument(url);
            }
        }
        return true;
    }
    
    public FadeScroll() {
        this.a35 = true;
    }
}
