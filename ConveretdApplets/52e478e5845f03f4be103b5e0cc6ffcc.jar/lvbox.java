import java.io.DataInputStream;
import java.util.Vector;
import java.net.MalformedURLException;
import java.awt.Component;
import java.awt.image.ImageObserver;
import java.awt.Frame;
import java.util.StringTokenizer;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.MediaTracker;
import java.awt.Image;
import java.net.URL;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Color;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public final class lvbox extends Applet implements Runnable
{
    static final String w = "lvbox 1.7";
    static final int d = 21;
    boolean h;
    boolean v;
    boolean[] a;
    int width;
    int height;
    int long;
    int f;
    int try;
    int byte;
    int s;
    int else;
    int u;
    int int;
    int new;
    int o;
    int e;
    int l;
    int[] g;
    long if;
    String b;
    String for;
    String i;
    String q;
    String[] c;
    Color n;
    Color m;
    Color void;
    Font font;
    FontMetrics case;
    URL null;
    URL[] p;
    Image do;
    Image char;
    Image[] t;
    MediaTracker k;
    Graphics goto;
    Graphics z;
    Graphics r;
    Thread j;
    
    public void stop() {
        if (this.j != null) {
            this.j.stop();
            this.j = null;
        }
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        this.a();
        this.v = true;
        this.if();
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        if (this.p[this.int] != null && this.goto != null) {
            this.a(this.goto);
        }
        this.v = false;
        this.showStatus("");
        return true;
    }
    
    public void paint(final Graphics graphics) {
        this.a(graphics);
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        if (this.p[this.int] != null) {
            this.getAppletContext().showDocument(this.p[this.int], this.c[this.int]);
            return true;
        }
        return false;
    }
    
    void a(final Graphics graphics, final String s, final int n, final int n2, final int n3) {
        switch (this.new) {
            case 1: {
                graphics.drawString(s, (this.width - n2) / 2, n3);
                break;
            }
            case 2: {
                graphics.drawString(s, this.width - n2 - this.o, n3);
                break;
            }
            case 3: {
                if (n < 2 || ".!?".indexOf(s.charAt(s.length() - 1)) != -1) {
                    graphics.drawString(s, this.o, n3);
                }
                else {
                    final int n4 = (this.e - n2) / (n - 1);
                    int n5 = this.e - n2 - n4 * (n - 1);
                    final StringTokenizer stringTokenizer = new StringTokenizer(s);
                    int o = this.o;
                    while (stringTokenizer.hasMoreTokens()) {
                        final String nextToken = stringTokenizer.nextToken();
                        graphics.drawString(nextToken, o, n3);
                        if (n5 > 0) {
                            o += this.case.stringWidth(nextToken) + this.u + n4 + 1;
                            --n5;
                        }
                        else {
                            o += this.case.stringWidth(nextToken) + this.u + n4;
                        }
                    }
                }
                break;
            }
            default: {
                graphics.drawString(s, this.o, n3);
                break;
            }
        }
    }
    
    String a(final String s, final String s2) {
        final String parameter = this.getParameter(s);
        if (parameter == null) {
            System.out.println("Using default value for " + s);
            return s2;
        }
        return parameter;
    }
    
    void if() {
        if (this.v && this.p[this.int] != null) {
            if (this.h && this.getParent() instanceof Frame) {
                ((Frame)this.getParent()).setCursor(12);
            }
            this.showStatus(this.p[this.int].toExternalForm());
        }
        else {
            if (this.h && this.getParent() instanceof Frame) {
                ((Frame)this.getParent()).setCursor(0);
            }
            if (this.v) {
                this.showStatus("Applet " + "lvbox 1.7" + " running");
            }
        }
    }
    
    public void update(final Graphics graphics) {
        this.a(graphics);
    }
    
    public void start() {
        if (!this.getDocumentBase().toExternalForm().toLowerCase().startsWith("file") && !this.a("Credits", "none").equals("applet by www.cromoteca.com")) {
            return;
        }
        if (this.j == null) {
            this.j = new Thread(this);
        }
        this.j.start();
    }
    
    int a(final String s, final String s2, final int n) {
        final String a = this.a(s, s2);
        try {
            return Integer.parseInt(a, n);
        }
        catch (NumberFormatException ex) {
            return 0;
        }
    }
    
    int if(final String s, final String s2) {
        return this.a(s, s2, 10);
    }
    
    void a() {
        if (this.p[this.int] != null && this.goto != null) {
            this.goto.setColor(this.m);
            this.goto.draw3DRect(0, 0, this.width - 1, this.height - 1, true);
        }
    }
    
    public void run() {
        Thread.currentThread().setPriority(1);
        while (true) {
            if (!this.a[this.int]) {
                try {
                    this.k.waitForID(this.int);
                }
                catch (InterruptedException ex) {}
                this.a[this.int] = true;
            }
            this.z.setColor(this.n);
            this.z.fillRect(0, 0, this.width, this.height);
            this.z.drawImage(this.t[this.int], (this.width - this.t[this.int].getWidth(this)) / 2, (this.height - this.t[this.int].getHeight(this)) / 2, this);
            this.if();
            this.a(this.goto);
            if (this.v) {
                this.a();
            }
            try {
                Thread.currentThread();
                Thread.sleep(this.f);
            }
            catch (InterruptedException ex2) {}
            final int int1 = (this.int + 1) % this.try;
            if (!this.a[int1]) {
                try {
                    this.k.waitForID(int1);
                }
                catch (InterruptedException ex3) {}
                this.a[int1] = true;
            }
            this.r.setColor(this.n);
            this.r.fillRect(0, 0, this.width, this.height);
            this.r.drawImage(this.t[int1], (this.width - this.t[int1].getWidth(this)) / 2, (this.height - this.t[int1].getHeight(this)) / 2, this);
            int n;
            if (this.g[int1] < 0 || this.g[int1] > 21) {
                n = (int)(Math.random() * 21.0) + 1;
            }
            else {
                n = this.g[int1];
            }
            if (n > 0) {
                this.if = System.currentTimeMillis();
                Label_1461: {
                    break Label_1461;
                    int i;
                    do {
                        final int n2 = this.width - this.l * this.width / this.byte;
                        final int n3 = this.height - this.l * this.height / this.byte;
                        switch (n) {
                            case 1: {
                                this.z.drawImage(this.char, 0, n3, this);
                                break;
                            }
                            case 2: {
                                this.z.drawImage(this.char, 0, -n3, this);
                                break;
                            }
                            case 3: {
                                this.z.drawImage(this.char, n2, 0, this);
                                break;
                            }
                            case 4: {
                                this.z.drawImage(this.char, -n2, 0, this);
                                break;
                            }
                            case 5: {
                                this.z.create(n2 / 2, n3 / 2, this.width - n2, this.height - n3).drawImage(this.char, -n2 / 2, -n3 / 2, this);
                                break;
                            }
                            case 6: {
                                this.z.create(0, n3 / 2, this.width, this.height - n3).drawImage(this.char, 0, -n3 / 2, this);
                                break;
                            }
                            case 7: {
                                this.z.create(n2 / 2, 0, this.width - n2, this.height).drawImage(this.char, -n2 / 2, 0, this);
                                break;
                            }
                            case 8: {
                                this.z.drawImage(this.char, 0, 0, this.width - n2, this.height - n3, this);
                                break;
                            }
                            case 9: {
                                this.z.drawImage(this.char, n2, 0, this.width - n2, this.height - n3, this);
                                break;
                            }
                            case 10: {
                                this.z.drawImage(this.char, 0, n3, this.width - n2, this.height - n3, this);
                                break;
                            }
                            case 11: {
                                this.z.drawImage(this.char, n2, n3, this.width - n2, this.height - n3, this);
                                break;
                            }
                            case 12: {
                                this.z.drawImage(this.char, 0, n3, this.width, this.height - n3, this);
                                break;
                            }
                            case 13: {
                                this.z.drawImage(this.char, 0, 0, this.width, this.height - n3, this);
                                break;
                            }
                            case 14: {
                                this.z.drawImage(this.char, n2, 0, this.width - n2, this.height, this);
                                break;
                            }
                            case 15: {
                                this.z.drawImage(this.char, 0, 0, this.width - n2, this.height, this);
                                break;
                            }
                            case 16: {
                                this.z.create(0, 0, (this.width - n2) / 2, this.height).drawImage(this.char, -n2 / 2, 0, this);
                                this.z.create((this.width + n2) / 2, 0, (this.width - n2) / 2, this.height).drawImage(this.char, -this.width / 2, 0, this);
                                break;
                            }
                            case 17: {
                                this.z.create(0, 0, this.width, (this.height - n3) / 2).drawImage(this.char, 0, -n3 / 2, this);
                                this.z.create(0, (this.height + n3) / 2, this.width, (this.height - n3) / 2).drawImage(this.char, 0, -this.height / 2, this);
                                break;
                            }
                            case 18: {
                                this.z.create(0, 0, this.width, this.height / 2).drawImage(this.char, -n2, 0, this);
                                this.z.create(0, this.height / 2, this.width, this.height / 2).drawImage(this.char, n2, -this.height / 2, this);
                                break;
                            }
                            case 19: {
                                this.z.create(0, 0, this.width, this.height / 2).drawImage(this.char, n2, 0, this);
                                this.z.create(0, this.height / 2, this.width, this.height / 2).drawImage(this.char, -n2, -this.height / 2, this);
                                break;
                            }
                            case 20: {
                                this.z.create(0, 0, this.width / 2, this.height).drawImage(this.char, 0, n3, this);
                                this.z.create(this.width / 2, 0, this.width / 2, this.height).drawImage(this.char, -this.width / 2, -n3, this);
                                break;
                            }
                            case 21: {
                                this.z.create(0, 0, this.width / 2, this.height).drawImage(this.char, 0, -n3, this);
                                this.z.create(this.width / 2, 0, this.width / 2, this.height).drawImage(this.char, -this.width / 2, n3, this);
                                break;
                            }
                        }
                        this.a(this.goto);
                        try {
                            Thread.currentThread();
                            Thread.sleep(this.long);
                        }
                        catch (InterruptedException ex4) {}
                        i = (int)(System.currentTimeMillis() - this.if);
                        this.l = i;
                    } while (i < this.byte);
                }
            }
            this.int = int1;
        }
    }
    
    public void init() {
        final String a = this.a("Background1", "ffffff");
        this.setBackground(this.n = new Color(Integer.parseInt(a, 16)));
        this.getParent().setBackground(this.n);
        this.getParent().repaint();
        this.m = new Color(this.a("Background2", a, 16));
        this.width = this.size().width;
        this.height = this.size().height;
        this.goto = this.getGraphics();
        this.do = this.createImage(this.width, this.height);
        this.z = this.do.getGraphics();
        this.char = this.createImage(this.width, this.height);
        this.r = this.char.getGraphics();
        this.int = 0;
        this.k = new MediaTracker(this);
        this.void = new Color(this.a("Foreground", "0", 16));
        this.long = this.if("SleepTime", "10");
        this.new = this.if("TextAlign", "0");
        this.byte = this.if("EffectDuration", "1000");
        this.f = this.if("PauseDuration", "3000");
        this.h = this.a("HandCursor", "yes").equalsIgnoreCase("yes");
        this.b = this.a("Separator", ",");
        this.q = this.a("DataFile", null);
        this.for = this.a("Text", "Click here for news");
        this.i = this.a("Target", "_self");
        final String a2 = this.a("Link", null);
        this.o = this.if("TextMargin", "2") + 1;
        if (a2 != null) {
            try {
                this.null = new URL(this.getDocumentBase(), a2);
            }
            catch (MalformedURLException ex) {}
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(this.a("Font", "Dialog" + this.b + "0" + this.b + "12"), this.b);
        try {
            this.font = new Font(stringTokenizer.nextToken(), Integer.parseInt(stringTokenizer.nextToken()), Integer.parseInt(stringTokenizer.nextToken()));
        }
        catch (Exception ex2) {
            this.font = new Font("Dialog", 0, 12);
        }
        this.case = this.getFontMetrics(this.font);
        this.s = this.case.getAscent();
        this.else = this.s + this.case.getDescent();
        this.u = this.case.stringWidth(" ");
        this.e = this.width - 2 * this.o;
        final Vector vector = new Vector<String>();
        try {
            final DataInputStream dataInputStream = new DataInputStream(new URL(this.getDocumentBase(), this.q).openConnection().getInputStream());
            String s = null;
            String line;
            while ((line = dataInputStream.readLine()) != null) {
                final StringTokenizer stringTokenizer2 = new StringTokenizer(line, "\n\r");
                if (stringTokenizer2.hasMoreTokens()) {
                    if (s == null) {
                        s = stringTokenizer2.nextToken();
                    }
                    else {
                        s = s + " " + stringTokenizer2.nextToken();
                    }
                }
                else {
                    if (s == null) {
                        continue;
                    }
                    vector.addElement(s);
                    s = null;
                }
            }
            if (s != null) {
                vector.addElement(s);
            }
            dataInputStream.close();
        }
        catch (Exception ex3) {
            this.try = 0;
            String a3;
            while (!(a3 = this.a("News" + ++this.try, "end")).equals("end")) {
                vector.addElement(a3);
            }
        }
        this.try = vector.size();
        this.t = new Image[this.try];
        this.g = new int[this.try];
        this.p = new URL[this.try];
        this.c = new String[this.try];
        this.a = new boolean[this.try];
        for (int i = 0; i < this.try; ++i) {
            final StringTokenizer stringTokenizer3 = new StringTokenizer(vector.elementAt(i), this.b);
            String s2;
            if (stringTokenizer3.hasMoreTokens()) {
                s2 = stringTokenizer3.nextToken();
            }
            else {
                s2 = this.for;
            }
            if (stringTokenizer3.hasMoreTokens()) {
                final String nextToken = stringTokenizer3.nextToken();
                if (!nextToken.startsWith(" ")) {
                    try {
                        this.p[i] = new URL(this.getDocumentBase(), nextToken);
                    }
                    catch (MalformedURLException ex4) {
                        this.p[i] = this.null;
                    }
                }
            }
            else {
                this.p[i] = this.null;
            }
            if (stringTokenizer3.hasMoreTokens()) {
                final String nextToken2 = stringTokenizer3.nextToken();
                if (!nextToken2.startsWith(" ")) {
                    this.c[i] = nextToken2;
                }
                else {
                    this.c[i] = this.i;
                }
            }
            else {
                this.c[i] = this.i;
            }
            this.g[i] = -1;
            if (stringTokenizer3.hasMoreTokens()) {
                try {
                    this.g[i] = Integer.parseInt(stringTokenizer3.nextToken());
                }
                catch (NumberFormatException ex5) {}
            }
            this.a[i] = true;
            Label_1190: {
                if (s2.indexOf(" ") == -1) {
                    if (!s2.toLowerCase().endsWith(".gif")) {
                        if (!s2.toLowerCase().endsWith(".jpg")) {
                            break Label_1190;
                        }
                    }
                    try {
                        this.t[i] = this.getImage(this.getDocumentBase(), s2);
                        this.k.addImage(this.t[i], i);
                        this.k.checkID(i, true);
                        this.a[i] = false;
                    }
                    catch (Exception ex6) {}
                }
            }
            if (this.a[i]) {
                this.t[i] = this.createImage(this.width, this.height);
                final Graphics graphics = this.t[i].getGraphics();
                graphics.setColor(this.n);
                graphics.fillRect(0, 0, this.width, this.height);
                graphics.setColor(this.void);
                graphics.setFont(this.font);
                final StringTokenizer stringTokenizer4 = new StringTokenizer(s2);
                int n = 0;
                int n2 = 1;
                int n3 = 0;
                String string = "";
                while (stringTokenizer4.hasMoreTokens()) {
                    final String nextToken3 = stringTokenizer4.nextToken();
                    final int stringWidth = this.case.stringWidth(nextToken3);
                    if (n + this.u + stringWidth > this.e) {
                        this.a(graphics, string, n3, this.case.stringWidth(string), n2 + this.s);
                        string = "";
                        n3 = 0;
                        n = 0;
                        n2 += this.else;
                    }
                    String s3;
                    if (n != 0) {
                        s3 = " ";
                    }
                    else {
                        s3 = "";
                    }
                    string = string + s3 + nextToken3;
                    n += this.u + stringWidth;
                    ++n3;
                }
                this.a(graphics, string, n3, this.case.stringWidth(string), n2 + this.s);
            }
        }
    }
    
    void a(final Graphics graphics) {
        if (this.do != null) {
            graphics.drawImage(this.do, 0, 0, this);
        }
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        this.a();
        return true;
    }
}
