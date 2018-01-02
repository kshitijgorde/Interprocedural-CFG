import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.Component;
import java.awt.MediaTracker;
import java.util.Hashtable;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class KLMenu extends Applet implements Runnable
{
    final int a24 = 0;
    final int a25 = 1;
    final int a26 = 2;
    final int a27 = 0;
    final int LEFT = 1;
    final int RIGHT = 2;
    String INFO;
    boolean a28;
    int a29;
    int a30;
    int a31;
    int[] a32;
    int a33;
    int a34;
    int a35;
    int a36;
    int width;
    int height;
    Thread a37;
    Image a38;
    Graphics a39;
    Font a40;
    Color[] a41;
    Color[] a42;
    Color a43;
    Color a44;
    boolean a45;
    boolean[] a46;
    Rectangle[] a47;
    String a48;
    String a49;
    String a50;
    String a51;
    String a52;
    Image a53;
    Hashtable a54;
    String[] a55;
    String a56;
    
    public void init() {
        this.init(this.INFO);
    }
    
    public void init(final String info) {
        this.INFO = info;
        this.width = this.size().width;
        this.height = this.size().height;
        this.a38 = this.createImage(this.width, this.height);
        this.a39 = this.a38.getGraphics();
        this.a98("menuMain");
        this.a30 = this.a72("steps", 25);
        this.a31 = this.a72("switchSteps", 10);
        this.a29 = this.a72("delay", 50);
        this.a36 = this.a72("rounding", 0);
        this.a48 = this.a74("highlightFade", "");
        this.a49 = this.a74("inFade", "");
        this.a50 = this.a74("outFade", this.a49);
        this.a51 = this.getParameter("type");
        if (this.a51 == null) {
            this.a51 = ((this.width > this.height * 2) ? "bar" : "menu");
        }
        this.a52 = this.getParameter("justify");
        if (this.a52 == null) {
            this.a52 = "";
        }
        this.a43 = this.a76("bgcolor", Color.white);
        final Color a76 = this.a76("fgcolor", Color.black);
        final Color a77 = this.a76("mouse", Color.blue);
        this.a44 = this.a76("highlight", this.a43);
        this.setBackground(this.a43);
        this.setForeground(a76);
        this.a41 = this.a69(a76, a77, this.a30);
        this.a42 = this.a69(this.a43, this.a44, this.a30);
        final String s = (this.getParameter("font") != null) ? this.getParameter("font") : "Times";
        final String lowerCase = this.a74("fontstyle", "").toLowerCase();
        this.a39.setFont(new Font(s, ((lowerCase.indexOf(105) != -1) ? 2 : 0) | ((lowerCase.indexOf(98) != -1) ? 1 : 0), this.a72("fontsize", 16)));
        System.out.println(info);
        if (this.getParameter("AppletHomePage") == null || !this.getParameter("AppletHomePage").equals("http://go.to/javabase")) {
            this.a39.clipRect(0, 0, 1, 1);
            this.showStatus("Home page not specified");
        }
        final String host = this.getDocumentBase().getHost();
        if (host == null || host.equals("localhost") || host.equals("127.0.0.1") || host.equals("")) {
            this.a28 = false;
        }
        if (this.getParameter("key") != null) {
            final String a78 = this.a79(host);
            String s2;
            for (s2 = this.getParameter("key"); this.a28 && s2.indexOf(32) > 0; s2 = s2.substring(s2.indexOf(32) + 1, s2.length())) {
                this.a28 = !s2.substring(0, s2.indexOf(32)).equals(a78);
            }
            if (this.a28) {
                this.a28 = !s2.equals(a78);
            }
            if (this.a28 && this.getParameter("host") != null && host.indexOf(this.getParameter("host")) > -1 && this.getParameter("key").equals(this.a79(this.getParameter("host")))) {
                this.a28 = false;
            }
        }
        if (this.a28) {
            this.a55 = this.a54.get("menuMain");
            final String[] array = new String[this.a55.length + 1];
            for (int i = 0; i < this.a55.length; ++i) {
                array[i] = this.a55[i];
            }
            array[this.a55.length] = "Register,http://www.javabase.fsnet.co.uk/,_blank";
            this.a54.remove("menuMain");
            this.a54.put("menuMain", array);
        }
        if (this.getParameter("background") != null) {
            final Image image = this.getImage(this.getDocumentBase(), this.getParameter("background"));
            this.a53 = this.createImage(this.width, this.height);
            final Graphics graphics = this.a53.getGraphics();
            final MediaTracker mediaTracker = new MediaTracker(this);
            mediaTracker.addImage(image, 0);
            try {
                mediaTracker.waitForAll();
            }
            catch (InterruptedException ex) {}
            if (this.getParameter("tile") != null) {
                for (int j = 0; j < this.width; j += image.getWidth(this)) {
                    for (int k = 0; k < this.height; k += image.getHeight(this)) {
                        graphics.drawImage(image, j, k, this);
                    }
                }
            }
            else {
                graphics.drawImage(image, 0, 0, this.width, this.height, this);
            }
        }
        this.a106("menuMain");
    }
    
    protected Color[] a69(final Color color, final Color color2, final int n) {
        final Color[] array = new Color[n];
        for (int i = 0; i < n; ++i) {
            array[i] = new Color(color.getRed() + i * (color2.getRed() - color.getRed()) / n, color.getGreen() + i * (color2.getGreen() - color.getGreen()) / n, color.getBlue() + i * (color2.getBlue() - color.getBlue()) / n);
        }
        return array;
    }
    
    protected int a72(final String s, final int n) {
        if (this.getParameter(s) != null && !this.getParameter(s).equals("")) {
            return Integer.parseInt(this.getParameter(s));
        }
        return n;
    }
    
    protected String a74(final String s, final String s2) {
        if (this.getParameter(s) != null && !this.getParameter(s).equals("")) {
            return this.getParameter(s);
        }
        return s2;
    }
    
    protected Color a76(final String s, final Color color) {
        final String parameter = this.getParameter(s);
        if (parameter == null || parameter.equals("") || parameter.length() != 6) {
            return color;
        }
        return new Color(Integer.parseInt(parameter.substring(0, 2), 16), Integer.parseInt(parameter.substring(2, 4), 16), Integer.parseInt(parameter.substring(4, 6), 16));
    }
    
    public void start() {
        if (this.a37 == null) {
            (this.a37 = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.a37 != null && this.a37.isAlive()) {
            this.a37.stop();
            this.a37 = null;
        }
    }
    
    public void paint(final Graphics graphics) {
        this.update(graphics);
    }
    
    public void update(final Graphics graphics) {
        graphics.drawImage(this.a38, 0, 0, this);
    }
    
    protected String a79(final String s) {
        final char[] charArray = s.toCharArray();
        for (int i = 0; i < charArray.length; ++i) {
            charArray[i] = (char)((charArray[i] + i * 7) % 26 + 97);
        }
        return new String(charArray);
    }
    
    public void setupMenu(final String a56) {
        if (!this.a54.containsKey(a56)) {
            this.a98(a56);
        }
        this.a56 = a56;
        this.a35 = this.a31 - 1;
        this.a45 = true;
        this.a34 = 1;
        this.a82();
    }
    
    public void run() {
        Thread.currentThread().setPriority(4);
        while (this.a37 != null) {
            final long currentTimeMillis = System.currentTimeMillis();
            if (this.a45 && this.a34 == 0) {
                this.a81();
            }
            else if (this.a34 == 1) {
                --this.a35;
                if (this.a35 <= 0) {
                    this.a34 = 2;
                    this.a106(this.a56);
                }
                this.a82();
            }
            else if (this.a34 == 2) {
                ++this.a35;
                if (this.a35 >= this.a31 - 1) {
                    this.a34 = 0;
                }
                this.a82();
            }
            try {
                Thread.sleep(Math.max(7L, this.a29 - System.currentTimeMillis() + currentTimeMillis));
            }
            catch (InterruptedException ex) {}
        }
    }
    
    protected void a81() {
        this.a45 = false;
        for (int i = 0; i < this.a55.length; ++i) {
            if (this.a33 == i) {
                if (this.a32[i] != this.a30 - 1) {
                    this.a32[i] = this.a30 - 1;
                    this.a82(i);
                }
            }
            else if (this.a32[i] > 0) {
                final int[] a32 = this.a32;
                final int n = i;
                --a32[n];
                this.a45 = true;
                this.a82(i);
            }
        }
    }
    
    protected void a82() {
        if (this.a53 != null) {
            this.a39.drawImage(this.a53, 0, 0, this);
        }
        else {
            this.a39.setColor(this.a43);
            this.a39.fillRect(0, 0, this.width, this.height);
        }
        for (int i = 0; i < this.a55.length; ++i) {
            this.a82(i);
        }
    }
    
    protected void a82(final int n) {
        final Rectangle rectangle = new Rectangle(this.a47[n].x, this.a47[n].y, this.a47[n].width, this.a47[n].height);
        final Graphics create = this.a39.create();
        create.clipRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        if (this.a34 == 2) {
            this.a90(this.a49, rectangle, this.a35, this.a31, n);
        }
        else if (this.a34 == 1) {
            this.a90(this.a50, rectangle, this.a35, this.a31, n);
        }
        final int n2 = rectangle.y + (rectangle.height - create.getFontMetrics().getHeight()) / 2 + create.getFontMetrics().getAscent();
        int x = rectangle.x + (rectangle.width - create.getFontMetrics().stringWidth(this.a102(n, 0))) / 2;
        if (this.a52.equals("left")) {
            x = rectangle.x;
        }
        else if (this.a52.equals("right")) {
            x = rectangle.x + rectangle.width - create.getFontMetrics().stringWidth(this.a102(n, 0));
        }
        if (this.a34 == 0) {
            if (this.a53 == null) {
                create.setColor(this.a43);
                create.fillRect(this.a47[n].x, this.a47[n].y, this.a47[n].width, this.a47[n].height);
            }
            else {
                create.create(this.a47[n].x, this.a47[n].y, this.a47[n].width, this.a47[n].height).drawImage(this.a53, -this.a47[n].x, -this.a47[n].y, this);
            }
        }
        final Rectangle rectangle2 = new Rectangle(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        if (this.a48 != null) {
            this.a90(this.a48, rectangle2, this.a32[n], this.a30, n);
        }
        if (this.a102(n, 0).length() > 0 && this.a102(n, 1).length() > 0) {
            this.a14(rectangle2.intersection(rectangle), n);
        }
        final Graphics create2 = create.create(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        create2.setColor((this.a102(n, 1).length() > 0) ? this.a41[this.a32[n]] : this.a41[0]);
        create2.drawString(this.a102(n, 0), x - rectangle.x - (this.a46[n] ? 2 : 0), n2 - rectangle.y + (this.a46[n] ? 2 : 0));
        Thread.yield();
        this.repaint(this.a47[n].x, this.a47[n].y, this.a47[n].width, this.a47[n].height);
    }
    
    protected void a90(final String s, final Rectangle rectangle, final int n, final int n2, final int n3) {
        final double n4 = n / (n2 - 1);
        if (s.equals("flip") || s.equals("flipXY")) {
            final int n5 = (int)(n2 / 1.5);
            int n6 = (n2 - n) % n5;
            if (n6 > n5 / 2 - 1) {
                n6 = n5 - 1 - n6;
            }
            rectangle.grow(0, -(rectangle.height * n6) / (n5 - 2));
        }
        if (s.equals("flipY") || s.equals("flipXY")) {
            final int n7 = (int)(n2 / 1.5);
            int n8 = (n2 - n) % n7;
            if (n8 > n7 / 2 - 1) {
                n8 = n7 - 1 - n8;
            }
            rectangle.grow(-(rectangle.width * n8) / (n7 - 2), 0);
            return;
        }
        if ((s.equals("leftright") && n3 % 2 == 0) || s.equals("left")) {
            rectangle.x = rectangle.x - rectangle.width + (int)((rectangle.x + rectangle.width - rectangle.x) * n4);
            return;
        }
        if ((s.equals("leftright") && n3 % 2 == 1) || s.equals("right")) {
            rectangle.x = (int)(rectangle.x + rectangle.width + (rectangle.x - rectangle.x - rectangle.width) * n4);
            return;
        }
        if ((s.equals("updown") && n3 % 2 == 1) || s.equals("down")) {
            rectangle.y = (int)(rectangle.y + rectangle.height + (rectangle.y - rectangle.y - rectangle.height) * n4);
            return;
        }
        if ((s.equals("updown") && n3 % 2 == 0) || s.equals("up")) {
            rectangle.y = rectangle.y - rectangle.height + (int)((rectangle.y + rectangle.height - rectangle.y) * n4);
            return;
        }
        if (s.equals("shrinkVer")) {
            rectangle.grow(0, -(int)(rectangle.height * (1.0 - n4) / 2.0));
            return;
        }
        if (s.equals("shrinkHor")) {
            rectangle.grow(-(int)(rectangle.width * (1.0 - n4) / 2.0), 0);
            return;
        }
        if (s.equals("shrink2")) {
            rectangle.grow(-(int)(rectangle.height * (1.0 - n4) / 2.0), -(int)(rectangle.height * (1.0 - n4) / 2.0));
            return;
        }
        if (s.equals("shrink")) {
            rectangle.grow(-(int)(rectangle.width * (1.0 - n4) / 2.0), -(int)(rectangle.height * (1.0 - n4) / 2.0));
        }
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        final int a105 = this.a105(n, n2);
        if (a105 == -1) {
            return true;
        }
        this.a46[a105] = true;
        this.a45 = true;
        this.a82(a105);
        return true;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        final int a105 = this.a105(n, n2);
        if (a105 == -1) {
            return true;
        }
        if (this.a102(a105, 1).indexOf("menu") == 0) {
            this.setupMenu(this.a102(a105, 1));
        }
        else if (this.a102(a105, 1) != "") {
            URL url = null;
            try {
                url = new URL(this.a102(a105, 1));
            }
            catch (MalformedURLException ex) {
                try {
                    url = new URL(this.getDocumentBase(), this.a102(a105, 1));
                }
                catch (MalformedURLException ex2) {}
            }
            if (url != null) {
                if (this.a102(a105, 2) != "") {
                    this.getAppletContext().showDocument(url, this.a102(a105, 2));
                }
                else {
                    this.getAppletContext().showDocument(url);
                }
            }
        }
        this.a46[a105] = false;
        this.a45 = true;
        this.a82(a105);
        return true;
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        this.a33 = this.a105(n, n2);
        if (this.a28) {
            this.showStatus(this.INFO);
        }
        else {
            this.showStatus(this.a102(this.a33, 0));
        }
        return this.a45 = true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.a33 = -1;
        return this.a45 = true;
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        return this.mouseMove(event, n, n2);
    }
    
    protected void a98(final String s) {
        if (!this.a54.containsKey(s)) {
            final String parameter = this.getParameter(s);
            if (parameter == null) {
                return;
            }
            int n = -1;
            for (int i = 0; i >= 0; i = parameter.indexOf(125, i + 1), ++n) {}
            final String[] array = new String[n];
            int index = 0;
            for (int j = 0; j < n; ++j) {
                final int n2 = parameter.indexOf(123, index) + 1;
                index = parameter.indexOf(125, n2);
                array[j] = parameter.substring(n2, index);
            }
            this.a54.put(s, array);
        }
    }
    
    protected String a102(final int n, final int n2) {
        return this.a104(this.a55[n], n2, ',');
    }
    
    protected String a104(final String s, final int n, final char c) {
        int n2 = 0;
        int index = -1;
        int i;
        for (i = 0; i <= n; ++i) {
            n2 = index + 1;
            index = s.indexOf(c, n2);
            if (index < 0) {
                break;
            }
        }
        if (i < n) {
            return "";
        }
        return s.substring(n2, (index < 0) ? s.length() : index);
    }
    
    protected int a105(final int n, final int n2) {
        for (int i = 0; i < this.a55.length; ++i) {
            if (this.a47[i].inside(n, n2)) {
                return i;
            }
        }
        return -1;
    }
    
    protected void a106(final String s) {
        this.a55 = this.a54.get(s);
        this.a32 = new int[this.a55.length];
        this.a46 = new boolean[this.a55.length];
        this.a39.setColor(this.a43);
        this.a39.fillRect(0, 0, this.width, this.height);
        this.a33 = -1;
        this.a47 = new Rectangle[this.a55.length];
        if (this.a51.equals("bar")) {
            final int n = this.width / this.a55.length;
            double n2 = this.width;
            int n3 = 0;
            for (int i = 0; i < this.a55.length; ++i) {
                n2 -= this.a39.getFontMetrics().stringWidth(this.a102(i, 0));
            }
            final double n4 = n2 / this.a55.length;
            for (int j = 0; j < this.a55.length; ++j) {
                final int stringWidth = this.a39.getFontMetrics().stringWidth(this.a102(j, 0));
                this.a47[j] = new Rectangle(n3 + (int)(j * n4), 0, (int)((j + 1) * n4) - (int)(j * n4) + stringWidth, this.height);
                n3 += stringWidth;
            }
        }
        else if (this.a51.equals("menu")) {
            final double n5 = this.height / this.a55.length;
            for (int k = 0; k < this.a55.length; ++k) {
                this.a47[k] = new Rectangle(0, (int)(k * n5), this.width, (int)((k + 1) * n5) - (int)(k * n5));
            }
        }
        if (this.a37 != null) {
            this.a82();
        }
    }
    
    protected void a14(final Rectangle rectangle, final int n) {
        if (this.a32[n] != 0) {
            this.a39.setColor(this.a42[this.a32[n]]);
            this.a39.fillRoundRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height, this.a36, this.a36);
        }
    }
    
    public KLMenu() {
        this.INFO = "KLMenu http://www.javabase.fsnet.co.uk/";
        this.a28 = true;
        this.a29 = 50;
        this.a31 = 2;
        this.a33 = -1;
        this.a34 = 2;
        this.a45 = true;
        this.a54 = new Hashtable();
    }
}
