import java.awt.Container;
import java.awt.Frame;
import java.net.MalformedURLException;
import java.net.URL;
import netscape.javascript.JSObject;
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
    final int a200 = 0;
    final int a201 = 1;
    final int a202 = 2;
    final int a203 = 0;
    final int LEFT = 1;
    final int RIGHT = 2;
    String INFO;
    boolean a32;
    int a180;
    int a204;
    int[] a91;
    public int a5;
    public int a6;
    public int a205;
    int a7;
    int a206;
    public boolean a168;
    int a207;
    int a208;
    int a15;
    int width;
    int height;
    int a209;
    Thread a18;
    Image a19;
    Graphics a20;
    Color[] a22;
    Color[] a210;
    Color a211;
    Color a212;
    boolean a16;
    Rectangle[] a213;
    String a214;
    String a215;
    String a216;
    String a110;
    String a217;
    Image a35;
    Hashtable a218;
    public String[] a219;
    public String menu;
    String[] a220;
    
    public void init() {
        this.init(this.INFO);
    }
    
    public void init(final String info) {
        this.INFO = info;
        this.width = this.size().width;
        this.height = this.size().height;
        this.a19 = this.createImage(this.width, this.height);
        this.a20 = this.a19.getGraphics();
        this.a233("menuMain");
        this.a180 = this.a47("steps", 25);
        this.a204 = this.a47("switchSteps", 10);
        this.a7 = this.a47("delay", 20);
        this.a15 = this.a47("rounding", 0);
        this.a206 = this.a47("timeout", 0);
        this.a214 = this.a66("highlightFade", "");
        this.a215 = this.a66("inFade", "");
        this.a216 = this.a66("outFade", this.a215);
        this.a110 = this.getParameter("type");
        if (this.a110 == null) {
            this.a110 = ((this.width > this.height * 2) ? "bar" : "menu");
        }
        this.a217 = this.getParameter("justify");
        if (this.a217 == null) {
            this.a217 = "";
        }
        this.a211 = this.a50("bgcolor", Color.white);
        final Color a50 = this.a50("fgcolor", Color.black);
        final Color a51 = this.a50("mouse", Color.blue);
        this.a212 = this.a50("highlight", this.a211);
        this.setBackground(this.a211);
        this.setForeground(a50);
        this.a22 = this.a225(a50, a51, this.a180);
        this.a210 = this.a225(this.a211, this.a212, this.a180);
        this.a20.setColor(this.a211);
        this.a20.fillRect(0, 0, this.width, this.height);
        this.a20.setColor(Color.black);
        this.a20.drawString("Loading...", 0, 40);
        this.repaint();
        final String s = (this.getParameter("font") != null) ? this.getParameter("font") : "Times";
        final String lowerCase = this.a66("fontstyle", "").toLowerCase();
        this.a20.setFont(new Font(s, ((lowerCase.indexOf(105) != -1) ? 2 : 0) | ((lowerCase.indexOf(98) != -1) ? 1 : 0), this.a47("fontsize", 16)));
        System.out.println(info);
        if (this.getParameter("AppletHomePage") == null || !this.getParameter("AppletHomePage").equals("http://go.to/javabase")) {
            this.a20.clipRect(0, 0, 1, 1);
            this.showStatus("Home page not specified");
        }
        final String lowerCase2 = this.getDocumentBase().getHost().toLowerCase();
        if (lowerCase2 == null || lowerCase2.equals("localhost") || lowerCase2.equals("127.0.0.1") || lowerCase2.equals("")) {
            this.a32 = false;
        }
        if (this.getParameter("key") != null) {
            final String a52 = this.a98(lowerCase2);
            String s2;
            for (s2 = this.getParameter("key"); this.a32 && s2.indexOf(32) > 0; s2 = s2.substring(s2.indexOf(32) + 1, s2.length())) {
                this.a32 = !s2.substring(0, s2.indexOf(32)).equals(a52);
            }
            if (this.a32) {
                this.a32 = !s2.equals(a52);
            }
            if (this.a32 && this.getParameter("host") != null && this.getParameter("host").length() > 4 && lowerCase2.indexOf(this.getParameter("host")) > -1 && this.getParameter("key").equals(this.a98(this.getParameter("host")))) {
                this.a32 = false;
            }
        }
        if (this.a32) {
            this.a219 = this.a218.get("menuMain");
            final String[] array = new String[this.a219.length + 1];
            for (int i = 0; i < this.a219.length; ++i) {
                array[i] = this.a219[i];
            }
            array[this.a219.length] = "Java Applets,http://www.javabase.fsnet.co.uk/,_blank";
            this.a218.remove("menuMain");
            this.a218.put("menuMain", array);
        }
        if (this.getParameter("background") != null) {
            this.a154(this.getParameter("background"));
        }
        this.a239("menuMain");
        this.a168 = true;
    }
    
    public boolean a154(final String s) {
        final Image image = this.getImage(this.getDocumentBase(), s);
        this.a35 = this.createImage(this.width, this.height);
        final Graphics graphics = this.a35.getGraphics();
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
    
    protected Color[] a225(final Color color, final Color color2, final int n) {
        final Color[] array = new Color[n];
        for (int i = 0; i < n; ++i) {
            array[i] = new Color(color.getRed() + i * (color2.getRed() - color.getRed()) / (n - 1), color.getGreen() + i * (color2.getGreen() - color.getGreen()) / (n - 1), color.getBlue() + i * (color2.getBlue() - color.getBlue()) / (n - 1));
        }
        return array;
    }
    
    int a47(final String s, final int n) {
        if (this.getParameter(s) != null && !this.getParameter(s).equals("")) {
            return Integer.parseInt(this.getParameter(s));
        }
        return n;
    }
    
    protected String a66(final String s, final String s2) {
        if (this.getParameter(s) != null && !this.getParameter(s).equals("")) {
            return this.getParameter(s);
        }
        return s2;
    }
    
    protected Color a50(final String s, final Color color) {
        final String parameter = this.getParameter(s);
        if (parameter == null || parameter.equals("") || parameter.length() != 6) {
            return color;
        }
        return new Color(Integer.parseInt(parameter.substring(0, 2), 16), Integer.parseInt(parameter.substring(2, 4), 16), Integer.parseInt(parameter.substring(4, 6), 16));
    }
    
    public void start() {
        if (this.a18 == null) {
            (this.a18 = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.a18 != null && this.a18.isAlive()) {
            this.a18.stop();
            this.a18 = null;
        }
    }
    
    public void paint(final Graphics graphics) {
        this.update(graphics);
    }
    
    public void update(final Graphics graphics) {
        this.a205 = 0;
        if (this.a168) {
            graphics.drawImage(this.a19, 0, 0, this);
        }
        this.a16 = true;
    }
    
    protected void a67() {
        this.repaint();
    }
    
    protected String a98(final String s) {
        final char[] charArray = s.toCharArray();
        for (int i = 0; i < charArray.length; ++i) {
            charArray[i] = (char)((charArray[i] + i * 7) % 26 + 97);
        }
        return new String(charArray);
    }
    
    public void a227(final String menu) {
        if (!this.a218.containsKey(menu)) {
            this.a233(menu);
        }
        this.menu = menu;
        this.a208 = this.a204 - 1;
        this.a16 = true;
        this.a207 = 1;
    }
    
    public void run() {
        while (this.a18 != null) {
            final long currentTimeMillis = System.currentTimeMillis();
            if (this.a16 && this.a207 == 0) {
                this.a153();
            }
            else if (this.a207 == 1) {
                --this.a208;
                if (this.a208 <= 0) {
                    this.a207 = 2;
                    this.a239(this.menu);
                }
                this.a148();
            }
            else if (this.a207 == 2) {
                ++this.a208;
                if (this.a208 >= this.a204 - 1) {
                    this.a207 = 0;
                }
                this.a148();
            }
            try {
                Thread.sleep(Math.max(7L, this.a7 - System.currentTimeMillis() + currentTimeMillis));
            }
            catch (InterruptedException ex) {}
            if (this.a206 != 0 && !this.menu.equals("menuMain")) {
                this.a209 -= this.a7;
                if (this.a209 >= 0) {
                    continue;
                }
                this.a227("menuMain");
            }
        }
    }
    
    protected void a153() {
        this.a16 = false;
        for (int i = 0; i < this.a219.length; ++i) {
            if (this.a5 == i) {
                if (this.a91[i] != this.a180 - 1) {
                    this.a91[i] = this.a180 - 1;
                    this.a148(i);
                }
            }
            else if (this.a91[i] > 0) {
                final int[] a91 = this.a91;
                final int n = i;
                --a91[n];
                this.a16 = true;
                this.a148(i);
            }
        }
        if (this.a205 != -1) {
            this.a148(this.a205);
            this.a205 = -1;
        }
        this.a67();
    }
    
    public void a148() {
        if (this.a35 != null) {
            this.a20.drawImage(this.a35, 0, 0, this);
        }
        else {
            this.a20.setColor(this.a211);
            this.a20.fillRect(0, 0, this.width, this.height);
        }
        for (int i = 0; i < this.a219.length; ++i) {
            this.a148(i);
        }
        this.a67();
    }
    
    protected synchronized void a148(final int n) {
        final Rectangle rectangle = new Rectangle(this.a213[n].x, this.a213[n].y, this.a213[n].width, this.a213[n].height);
        if (this.a207 == 2) {
            this.a93(this.a215, rectangle, this.a208, this.a204, n);
        }
        else if (this.a207 == 1) {
            this.a93(this.a216, rectangle, this.a208, this.a204, n);
        }
        final Graphics create = this.a20.create(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        create.clipRect(this.a213[n].x - rectangle.x, this.a213[n].y - rectangle.y, this.a213[n].width, this.a213[n].height);
        final int n2 = rectangle.y + (rectangle.height - create.getFontMetrics().getHeight()) / 2 + create.getFontMetrics().getAscent();
        int x;
        if (this.a217.equals("left")) {
            x = rectangle.x;
        }
        else if (this.a217.equals("right")) {
            x = rectangle.x + rectangle.width - create.getFontMetrics().stringWidth(this.a236(n, 0));
        }
        else {
            x = rectangle.x + (rectangle.width - create.getFontMetrics().stringWidth(this.a236(n, 0))) / 2;
        }
        if (this.a35 == null) {
            create.setColor(this.a211);
            create.fillRect(0, 0, this.a213[n].width, this.a213[n].height);
        }
        else {
            create.drawImage(this.a35, -rectangle.x, -rectangle.y, this);
        }
        if (this.a236(n, 0).length() > 0 && this.a236(n, 1).length() > 0) {
            final Rectangle rectangle2 = new Rectangle(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
            if (this.a214 != null) {
                this.a93(this.a214, rectangle2, this.a91[n], this.a180, n);
            }
            this.a134(rectangle2.intersection(rectangle).intersection(this.a213[n]), n);
        }
        create.setColor((this.a236(n, 1).length() > 0) ? this.a22[this.a91[n]] : this.a22[0]);
        create.drawString(this.a236(n, 0), x - rectangle.x + ((this.a6 == n) ? 2 : 0), n2 - rectangle.y + ((this.a6 == n) ? 2 : 0));
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
        final int a82 = this.a82(n, n2);
        if (a82 == -1) {
            return true;
        }
        this.a6 = a82;
        this.a16 = true;
        this.a205 = a82;
        return true;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        final int a82 = this.a82(n, n2);
        if (a82 == -1) {
            return true;
        }
        if (this.a236(a82, 1).indexOf("menu") == 0) {
            this.a227(this.a236(a82, 1));
        }
        else if (this.a236(a82, 1).startsWith("javascript:")) {
            final String substring = this.a236(a82, 1).substring(11);
            try {
                JSObject.getWindow((Applet)this).eval(substring);
            }
            catch (Throwable t) {}
        }
        else if (this.a236(a82, 1) != "") {
            URL url = null;
            try {
                url = new URL(this.a236(a82, 1));
            }
            catch (MalformedURLException ex) {
                try {
                    url = new URL(this.getDocumentBase(), this.a236(a82, 1));
                }
                catch (MalformedURLException ex2) {}
            }
            if (url != null) {
                if (this.a236(a82, 2) != "") {
                    this.getAppletContext().showDocument(url, this.a236(a82, 2));
                }
                else {
                    this.getAppletContext().showDocument(url);
                }
            }
        }
        this.a6 = -1;
        this.a16 = true;
        this.a205 = a82;
        return true;
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        this.a5 = this.a82(n, n2);
        if (this.a32) {
            this.showStatus(this.INFO);
        }
        else {
            this.showStatus(this.a236(this.a5, 0));
        }
        if (this.a236(this.a5, 0).length() > 0 && this.a236(this.a5, 1).length() > 0) {
            this.setCursor(12);
        }
        else {
            this.setCursor(0);
        }
        return this.a16 = true;
    }
    
    public boolean a232(final int a5) {
        this.a5 = a5;
        return this.a16 = true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.a5 = -1;
        this.a6 = -1;
        return this.a16 = true;
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        return this.mouseMove(event, n, n2);
    }
    
    protected void a233(final String s) {
        final String parameter = this.getParameter(s);
        String[] a220;
        if (parameter != null) {
            int n = -1;
            for (int i = 0; i >= 0; i = parameter.indexOf(125, i + 1), ++n) {}
            a220 = new String[n];
            int index = 0;
            for (int j = 0; j < n; ++j) {
                final int n2 = parameter.indexOf(123, index) + 1;
                index = parameter.indexOf(125, n2);
                a220[j] = parameter.substring(n2, index);
            }
        }
        else {
            a220 = this.a220;
        }
        this.a218.put(s, a220);
    }
    
    protected String a236(final int n, final int n2) {
        return this.a238(this.a219[n], n2, ',');
    }
    
    protected String a238(final String s, final int n, final char c) {
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
    
    protected int a82(final int n, final int n2) {
        for (int i = 0; i < this.a219.length; ++i) {
            if (this.a213[i].inside(n, n2)) {
                return i;
            }
        }
        return -1;
    }
    
    protected void a239(final String s) {
        if (this.a206 != 0) {
            this.a209 = this.a206 * 1000;
        }
        this.a219 = this.a218.get(s);
        this.a91 = new int[this.a219.length];
        this.a5 = -1;
        this.a6 = -1;
        this.a205 = -1;
        this.a213 = new Rectangle[this.a219.length];
        if (this.a110.equals("bar")) {
            double n = this.width;
            int n2 = 0;
            for (int i = 0; i < this.a219.length; ++i) {
                n -= this.a20.getFontMetrics().stringWidth(this.a236(i, 0));
            }
            final double n3 = n / this.a219.length;
            for (int j = 0; j < this.a219.length; ++j) {
                final int stringWidth = this.a20.getFontMetrics().stringWidth(this.a236(j, 0));
                this.a213[j] = new Rectangle(n2 + (int)(j * n3), 0, (int)((j + 1) * n3) - (int)(j * n3) + stringWidth, this.height);
                n2 += stringWidth;
            }
            return;
        }
        if (this.a110.equals("menu")) {
            final double n4 = this.height / this.a219.length;
            for (int k = 0; k < this.a219.length; ++k) {
                this.a213[k] = new Rectangle(0, (int)(k * n4), this.width, (int)((k + 1) * n4) - (int)(k * n4));
            }
        }
    }
    
    protected void a134(final Rectangle rectangle, final int n) {
        if (this.a91[n] != 0) {
            this.a20.setColor(this.a210[this.a91[n]]);
            this.a20.fillRoundRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height, this.a15, this.a15);
        }
    }
    
    public void setCursor(final int cursor) {
        Container container;
        for (container = this.getParent(); container != null && !(container instanceof Frame); container = container.getParent()) {}
        ((Frame)container).setCursor(cursor);
    }
    
    public KLMenu() {
        this.INFO = "KLMenu 2 http://www.javabase.fsnet.co.uk/";
        this.a32 = true;
        this.a5 = -1;
        this.a6 = -1;
        this.a205 = -1;
        this.a7 = 20;
        this.a168 = false;
        this.a207 = 2;
        this.a16 = true;
        this.a218 = new Hashtable();
        this.menu = "menuMain";
        this.a220 = new String[] { ",menuMain" };
    }
}
