import java.awt.Container;
import java.awt.Frame;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Font;
import java.util.Hashtable;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class KLMenu extends Applet implements Runnable
{
    final int a31 = 0;
    final int a32 = 1;
    final int a33 = 2;
    final int a34 = 0;
    final int LEFT = 1;
    final int RIGHT = 2;
    String INFO;
    boolean a35;
    int a36;
    int a37;
    int[] a38;
    public int optionOver;
    public int optionDown;
    public int a39;
    int a40;
    int a41;
    public boolean started;
    int a42;
    int a43;
    int a44;
    int width;
    int height;
    int a45;
    Thread a46;
    Image a47;
    Graphics a48;
    Color[] a49;
    Color[] a50;
    Color a51;
    Color a52;
    boolean fading;
    Rectangle[] a53;
    String a54;
    String a55;
    String a56;
    String a57;
    String a58;
    Image a59;
    Hashtable a60;
    public String[] a61;
    public String menu;
    String[] a62;
    
    public void init() {
        this.init(this.INFO);
    }
    
    public void init(final String info) {
        this.INFO = info;
        this.width = this.size().width;
        this.height = this.size().height;
        this.a47 = this.createImage(this.width, this.height);
        this.a48 = this.a47.getGraphics();
        this.a101("menuMain");
        this.a36 = this.a77("steps", 25);
        this.a37 = this.a77("switchSteps", 10);
        this.a40 = this.a77("delay", 20);
        this.a44 = this.a77("rounding", 0);
        this.a41 = this.a77("timeout", 0);
        this.a54 = this.a79("highlightFade", "");
        this.a55 = this.a79("inFade", "");
        this.a56 = this.a79("outFade", this.a55);
        this.a57 = this.getParameter("type");
        if (this.a57 == null) {
            this.a57 = ((this.width > this.height * 2) ? "bar" : "menu");
        }
        this.a58 = this.getParameter("justify");
        if (this.a58 == null) {
            this.a58 = "";
        }
        this.a51 = this.a81("bgcolor", Color.white);
        final Color a81 = this.a81("fgcolor", Color.black);
        final Color a82 = this.a81("mouse", Color.blue);
        this.a52 = this.a81("highlight", this.a51);
        this.setBackground(this.a51);
        this.setForeground(a81);
        this.a49 = this.a74(a81, a82, this.a36);
        this.a50 = this.a74(this.a51, this.a52, this.a36);
        this.a48.setColor(this.a51);
        this.a48.fillRect(0, 0, this.width, this.height);
        this.a48.setColor(Color.black);
        this.a48.drawString("Loading...", 0, 40);
        this.repaint();
        final String s = (this.getParameter("font") != null) ? this.getParameter("font") : "Times";
        final String lowerCase = this.a79("fontstyle", "").toLowerCase();
        this.a48.setFont(new Font(s, ((lowerCase.indexOf(105) != -1) ? 2 : 0) | ((lowerCase.indexOf(98) != -1) ? 1 : 0), this.a77("fontsize", 16)));
        System.out.println(info);
        if (this.getParameter("AppletHomePage") == null || !this.getParameter("AppletHomePage").equals("http://go.to/javabase")) {
            this.a48.clipRect(0, 0, 1, 1);
            this.showStatus("Home page not specified");
        }
        final String host = this.getDocumentBase().getHost();
        if (host == null || host.equals("localhost") || host.equals("127.0.0.1") || host.equals("")) {
            this.a35 = false;
        }
        if (this.getParameter("key") != null) {
            final String a83 = this.a84(host);
            String s2;
            for (s2 = this.getParameter("key"); this.a35 && s2.indexOf(32) > 0; s2 = s2.substring(s2.indexOf(32) + 1, s2.length())) {
                this.a35 = !s2.substring(0, s2.indexOf(32)).equals(a83);
            }
            if (this.a35) {
                this.a35 = !s2.equals(a83);
            }
            if (this.a35 && this.getParameter("host") != null && this.getParameter("host").length() > 4 && host.indexOf(this.getParameter("host")) > -1 && this.getParameter("key").equals(this.a84(this.getParameter("host")))) {
                this.a35 = false;
            }
        }
        if (this.a35) {
            this.a61 = this.a60.get("menuMain");
            final String[] array = new String[this.a61.length + 1];
            for (int i = 0; i < this.a61.length; ++i) {
                array[i] = this.a61[i];
            }
            array[this.a61.length] = "Register,http://www.javabase.fsnet.co.uk/,_blank";
            this.a60.remove("menuMain");
            this.a60.put("menuMain", array);
        }
        if (this.getParameter("background") != null) {
            this.backgroundImage(this.getParameter("background"));
        }
        this.a109("menuMain");
        this.started = true;
    }
    
    public boolean backgroundImage(final String s) {
        final Image image = this.getImage(this.getDocumentBase(), s);
        this.a59 = this.createImage(this.width, this.height);
        final Graphics graphics = this.a59.getGraphics();
        final MediaTracker mediaTracker = new MediaTracker(this);
        mediaTracker.addImage(image, 0);
        try {
            mediaTracker.waitForAll();
        }
        catch (InterruptedException ex) {}
        if (this.getParameter("tile") != null) {
            for (int i = 0; i < this.width; i += image.getWidth(this)) {
                for (int j = 0; j < this.height; j += image.getHeight(this)) {
                    graphics.drawImage(image, i, j, this);
                }
            }
        }
        else {
            graphics.drawImage(image, 0, 0, this.width, this.height, this);
        }
        return true;
    }
    
    protected Color[] a74(final Color color, final Color color2, final int n) {
        final Color[] array = new Color[n];
        for (int i = 0; i < n; ++i) {
            array[i] = new Color(color.getRed() + i * (color2.getRed() - color.getRed()) / (n - 1), color.getGreen() + i * (color2.getGreen() - color.getGreen()) / (n - 1), color.getBlue() + i * (color2.getBlue() - color.getBlue()) / (n - 1));
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
    
    public void start() {
        if (this.a46 == null) {
            (this.a46 = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.a46 != null && this.a46.isAlive()) {
            this.a46.stop();
            this.a46 = null;
        }
    }
    
    public void paint(final Graphics graphics) {
        this.update(graphics);
    }
    
    public void update(final Graphics graphics) {
        this.a39 = 0;
        this.fading = true;
    }
    
    protected void a83() {
        Thread.yield();
        this.getGraphics().drawImage(this.a47, 0, 0, this);
        Thread.yield();
    }
    
    protected String a84(final String s) {
        final char[] charArray = s.toCharArray();
        for (int i = 0; i < charArray.length; ++i) {
            charArray[i] = (char)((charArray[i] + i * 7) % 26 + 97);
        }
        return new String(charArray);
    }
    
    public void setupMenu(final String menu) {
        if (!this.a60.containsKey(menu)) {
            this.a101(menu);
        }
        this.menu = menu;
        this.a43 = this.a37 - 1;
        this.fading = true;
        this.a42 = 1;
    }
    
    public void run() {
        while (this.a46 != null) {
            final long currentTimeMillis = System.currentTimeMillis();
            if (this.fading && this.a42 == 0) {
                this.a86();
            }
            else if (this.a42 == 1) {
                --this.a43;
                if (this.a43 <= 0) {
                    this.a42 = 2;
                    this.a109(this.menu);
                }
                this.render();
            }
            else if (this.a42 == 2) {
                ++this.a43;
                if (this.a43 >= this.a37 - 1) {
                    this.a42 = 0;
                }
                this.render();
            }
            try {
                Thread.sleep(Math.max(7L, this.a40 - System.currentTimeMillis() + currentTimeMillis));
            }
            catch (InterruptedException ex) {}
            if (this.a41 != 0 && !this.menu.equals("menuMain")) {
                this.a45 -= this.a40;
                if (this.a45 >= 0) {
                    continue;
                }
                this.setupMenu("menuMain");
            }
        }
    }
    
    protected void a86() {
        this.fading = false;
        for (int i = 0; i < this.a61.length; ++i) {
            if (this.optionOver == i) {
                if (this.a38[i] != this.a36 - 1) {
                    this.a38[i] = this.a36 - 1;
                    this.render(i);
                }
            }
            else if (this.a38[i] > 0) {
                final int[] a38 = this.a38;
                final int n = i;
                --a38[n];
                this.fading = true;
                this.render(i);
            }
        }
        if (this.a39 != -1) {
            this.render(this.a39);
            this.a39 = -1;
        }
        this.a83();
    }
    
    public void render() {
        if (this.a59 != null) {
            this.a48.drawImage(this.a59, 0, 0, this);
        }
        else {
            this.a48.setColor(this.a51);
            this.a48.fillRect(0, 0, this.width, this.height);
        }
        for (int i = 0; i < this.a61.length; ++i) {
            this.render(i);
        }
        this.a83();
    }
    
    protected synchronized void render(final int n) {
        final Rectangle rectangle = new Rectangle(this.a53[n].x, this.a53[n].y, this.a53[n].width, this.a53[n].height);
        if (this.a42 == 2) {
            this.a93(this.a55, rectangle, this.a43, this.a37, n);
        }
        else if (this.a42 == 1) {
            this.a93(this.a56, rectangle, this.a43, this.a37, n);
        }
        final Graphics create = this.a48.create(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        create.clipRect(this.a53[n].x - rectangle.x, this.a53[n].y - rectangle.y, this.a53[n].width, this.a53[n].height);
        final int n2 = rectangle.y + (rectangle.height - create.getFontMetrics().getHeight()) / 2 + create.getFontMetrics().getAscent();
        int x;
        if (this.a58.equals("left")) {
            x = rectangle.x;
        }
        else if (this.a58.equals("right")) {
            x = rectangle.x + rectangle.width - create.getFontMetrics().stringWidth(this.a105(n, 0));
        }
        else {
            x = rectangle.x + (rectangle.width - create.getFontMetrics().stringWidth(this.a105(n, 0))) / 2;
        }
        if (this.a59 == null) {
            create.setColor(this.a51);
            create.fillRect(0, 0, this.a53[n].width, this.a53[n].height);
        }
        else {
            create.drawImage(this.a59, -rectangle.x, -rectangle.y, this);
        }
        if (this.a105(n, 0).length() > 0 && this.a105(n, 1).length() > 0) {
            final Rectangle rectangle2 = new Rectangle(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
            if (this.a54 != null) {
                this.a93(this.a54, rectangle2, this.a38[n], this.a36, n);
            }
            this.a18(rectangle2.intersection(rectangle).intersection(this.a53[n]), n);
        }
        create.setColor((this.a105(n, 1).length() > 0) ? this.a49[this.a38[n]] : this.a49[0]);
        create.drawString(this.a105(n, 0), x - rectangle.x + ((this.optionDown == n) ? 2 : 0), n2 - rectangle.y + ((this.optionDown == n) ? 2 : 0));
    }
    
    protected void a93(final String s, final Rectangle rectangle, final int n, final int n2, final int n3) {
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
        final int a108 = this.a108(n, n2);
        if (a108 == -1) {
            return true;
        }
        this.optionDown = a108;
        this.fading = true;
        this.a39 = a108;
        return true;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        final int a108 = this.a108(n, n2);
        if (a108 == -1) {
            return true;
        }
        if (this.a105(a108, 1).indexOf("menu") == 0) {
            this.setupMenu(this.a105(a108, 1));
        }
        else if (this.a105(a108, 1) != "") {
            URL url = null;
            try {
                url = new URL(this.a105(a108, 1));
            }
            catch (MalformedURLException ex) {
                try {
                    url = new URL(this.getDocumentBase(), this.a105(a108, 1));
                }
                catch (MalformedURLException ex2) {}
            }
            if (url != null) {
                if (this.a105(a108, 2) != "") {
                    this.getAppletContext().showDocument(url, this.a105(a108, 2));
                }
                else {
                    this.getAppletContext().showDocument(url);
                }
            }
        }
        this.optionDown = -1;
        this.fading = true;
        this.a39 = a108;
        return true;
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        this.optionOver = this.a108(n, n2);
        if (this.a35) {
            this.showStatus(this.INFO);
        }
        else {
            this.showStatus(this.a105(this.optionOver, 0));
        }
        if (this.a105(this.optionOver, 0).length() > 0 && this.a105(this.optionOver, 1).length() > 0) {
            this.setCursor(12);
        }
        else {
            this.setCursor(0);
        }
        return this.fading = true;
    }
    
    public boolean showhighlight(final int optionOver) {
        this.optionOver = optionOver;
        return this.fading = true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.optionOver = -1;
        this.optionDown = -1;
        return this.fading = true;
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        return this.mouseMove(event, n, n2);
    }
    
    protected void a101(final String s) {
        final String parameter = this.getParameter(s);
        String[] a62;
        if (parameter != null) {
            int n = -1;
            for (int i = 0; i >= 0; i = parameter.indexOf(125, i + 1), ++n) {}
            a62 = new String[n];
            int index = 0;
            for (int j = 0; j < n; ++j) {
                final int n2 = parameter.indexOf(123, index) + 1;
                index = parameter.indexOf(125, n2);
                a62[j] = parameter.substring(n2, index);
            }
        }
        else {
            a62 = this.a62;
        }
        this.a60.put(s, a62);
    }
    
    protected String a105(final int n, final int n2) {
        return this.a107(this.a61[n], n2, ',');
    }
    
    protected String a107(final String s, final int n, final char c) {
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
    
    protected int a108(final int n, final int n2) {
        for (int i = 0; i < this.a61.length; ++i) {
            if (this.a53[i].inside(n, n2)) {
                return i;
            }
        }
        return -1;
    }
    
    protected void a109(final String s) {
        if (this.a41 != 0) {
            this.a45 = this.a41 * 1000;
        }
        this.a61 = this.a60.get(s);
        this.a38 = new int[this.a61.length];
        this.optionOver = -1;
        this.optionDown = -1;
        this.a39 = -1;
        this.a53 = new Rectangle[this.a61.length];
        if (this.a57.equals("bar")) {
            double n = this.width;
            int n2 = 0;
            for (int i = 0; i < this.a61.length; ++i) {
                n -= this.a48.getFontMetrics().stringWidth(this.a105(i, 0));
            }
            final double n3 = n / this.a61.length;
            for (int j = 0; j < this.a61.length; ++j) {
                final int stringWidth = this.a48.getFontMetrics().stringWidth(this.a105(j, 0));
                this.a53[j] = new Rectangle(n2 + (int)(j * n3), 0, (int)((j + 1) * n3) - (int)(j * n3) + stringWidth, this.height);
                n2 += stringWidth;
            }
            return;
        }
        if (this.a57.equals("menu")) {
            final double n4 = this.height / this.a61.length;
            for (int k = 0; k < this.a61.length; ++k) {
                this.a53[k] = new Rectangle(0, (int)(k * n4), this.width, (int)((k + 1) * n4) - (int)(k * n4));
            }
        }
    }
    
    protected void a18(final Rectangle rectangle, final int n) {
        if (this.a38[n] != 0) {
            this.a48.setColor(this.a50[this.a38[n]]);
            this.a48.fillRoundRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height, this.a44, this.a44);
        }
    }
    
    public void setCursor(final int cursor) {
        Container container;
        for (container = this.getParent(); container != null && !(container instanceof Frame); container = container.getParent()) {}
        ((Frame)container).setCursor(cursor);
    }
    
    public KLMenu() {
        this.INFO = "KLMenu http://www.javabase.fsnet.co.uk/";
        this.a35 = true;
        this.optionOver = -1;
        this.optionDown = -1;
        this.a39 = -1;
        this.a40 = 20;
        this.started = false;
        this.a42 = 2;
        this.fading = true;
        this.a60 = new Hashtable();
        this.menu = "menuMain";
        this.a62 = new String[] { ",menuMain" };
    }
}
