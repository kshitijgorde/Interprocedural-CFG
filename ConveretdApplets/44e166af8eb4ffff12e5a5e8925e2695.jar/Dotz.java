import java.awt.Event;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.image.PixelGrabber;
import java.awt.image.ImageObserver;
import java.awt.Component;
import java.awt.Frame;
import java.awt.LayoutManager;
import java.awt.Graphics;
import java.awt.Image;
import java.util.StringTokenizer;
import java.awt.MediaTracker;
import java.awt.Color;
import java.awt.Container;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Dotz extends Applet implements Runnable
{
    private Container v;
    private b[] w;
    private o[][] x;
    private i y;
    private Color z;
    private int \u00c0;
    private MediaTracker \u00c1;
    private StringTokenizer \u00c2;
    private Image \u00c3;
    private Graphics \u00c4;
    private String \u00c5;
    private boolean \u00c6;
    private int \u00c7;
    private int \u00c8;
    private boolean \u00c9;
    private Thread \u00ca;
    private int \u00cb;
    private int \u00cc;
    private int \u00cd;
    private int \u00ce;
    
    public void init() {
        super.init();
        this.setLayout(null);
        this.addNotify();
        this.v = this.getParent();
        while (!(this.v instanceof Frame)) {
            this.v = this.v.getParent();
        }
        ((Frame)this.v).setCursor(3);
        System.out.println("# Dotz v2.0 by Jos van Ouwerkerk");
        System.out.println("# This applet is part of the Jvo Java package");
        System.out.println("# Contact me at jvohome@dds.nl");
        System.out.println("# More information at http://come.to/jvo");
        this.\u00cb = this.size().width;
        this.\u00cc = this.size().height;
        this.\u00cd = this.\u00cb >> 1;
        this.\u00ce = this.\u00cc >> 1;
        this.\u00c3 = this.createImage(this.\u00cb, this.\u00cc);
        this.\u00c4 = this.\u00c3.getGraphics();
        this.\u00c1 = new MediaTracker(this);
        String s = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789,.?!@#$%*()+-=:/\\[]'";
        final String parameter;
        if ((parameter = this.getParameter("fontstring")) != null) {
            s = parameter;
        }
        String s2 = "dotfont.gif,000000,82,3";
        final String parameter2;
        if ((parameter2 = this.getParameter("fontimage")) != null) {
            s2 = parameter2;
        }
        f.B(this.e(s2), s);
        this.z = new Color(0, 0, 0);
        final String parameter3;
        if ((parameter3 = this.getParameter("bgcolor")) != null) {
            this.z = this.b(parameter3);
        }
        this.x = new o[100][];
        for (int i = 0; i < 100; ++i) {
            final String parameter4;
            if ((parameter4 = this.getParameter("image" + i)) != null) {
                this.x[i] = this.e(parameter4);
            }
        }
        this.w = new b[100];
        for (int j = 0; j < 100; ++j) {
            final String parameter5;
            if ((parameter5 = this.getParameter("item" + j)) != null) {
                this.w[j] = this.r(parameter5);
            }
        }
        for (int k = 99; k >= 0; --k) {
            if (this.w[k] != null) {
                final String parameter6;
                if ((parameter6 = this.getParameter("color" + k)) != null) {
                    this.w[k].Q(this.s(parameter6));
                }
                final String parameter7;
                if ((parameter7 = this.getParameter("link" + k)) != null) {
                    final String parameter8;
                    if ((parameter8 = this.getParameter("target" + k)) != null) {
                        this.w[k].L(parameter7, parameter8);
                    }
                    else {
                        this.w[k].L(parameter7, "");
                    }
                }
            }
        }
        this.y = new m();
        for (int l = 0; l < 100; ++l) {
            final String parameter9;
            if ((parameter9 = this.getParameter("animation" + l)) != null) {
                this.y.D(this.q(parameter9));
            }
        }
        this.\u00c0 = 0;
        final String parameter10;
        if ((parameter10 = this.getParameter("dotstyle")) != null) {
            final String upperCase = this.d(parameter10).toUpperCase();
            if (upperCase.equals("BLOCK")) {
                this.\u00c0 = 1;
            }
            else if (upperCase.equals("OPEN")) {
                this.\u00c0 = 2;
            }
        }
        this.\u00c6 = false;
        this.y.S();
        System.out.println("Dotz animation time: " + this.y.p / 30 + " seconds");
        ((Frame)this.v).setCursor(0);
    }
    
    public void start() {
        if (this.\u00ca == null) {
            (this.\u00ca = new Thread(this, "Dotz main thread")).setPriority(3);
            this.\u00ca.start();
        }
    }
    
    public void stop() {
        if (this.\u00ca != null) {
            this.\u00ca.stop();
            this.\u00ca = null;
        }
    }
    
    public void run() {
        while (true) {
            this.repaint();
            try {
                Thread.sleep(33L);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public void update(final Graphics graphics) {
        this.y.K();
        this.g();
        if (this.y.q == this.y.p) {
            this.y.S();
        }
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        this.\u00c4.setColor(this.z);
        this.\u00c4.fillRect(0, 0, this.\u00cb, this.\u00cc);
        this.y.J();
        switch (this.\u00c0) {
            case 0: {
                for (int i = 0; i < this.y.j; ++i) {
                    final l n = this.y.N();
                    this.\u00c4.setColor(n.f);
                    this.\u00c4.fillRect(n.d - 1, n.e, 3, 1);
                    this.\u00c4.fillRect(n.d, n.e - 1, 1, 3);
                }
                break;
            }
            case 1: {
                for (int j = 0; j < this.y.j; ++j) {
                    final l n2 = this.y.N();
                    this.\u00c4.setColor(n2.f);
                    this.\u00c4.fillRect(n2.d - 1, n2.e - 1, 3, 3);
                }
                break;
            }
            case 2: {
                for (int k = 0; k < this.y.j; ++k) {
                    final l n3 = this.y.N();
                    this.\u00c4.setColor(n3.f);
                    this.\u00c4.drawRect(n3.d - 1, n3.e - 1, 2, 2);
                }
                break;
            }
        }
        graphics.drawImage(this.\u00c3, 0, 0, this);
    }
    
    private o[] e(final String s) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, ",");
        if (stringTokenizer.countTokens() == 4) {
            final Image image = this.getImage(this.getCodeBase(), stringTokenizer.nextToken());
            final int rgb = this.b(this.d(stringTokenizer.nextToken())).getRGB();
            final int int1 = Integer.parseInt(stringTokenizer.nextToken());
            final int int2 = Integer.parseInt(stringTokenizer.nextToken());
            this.\u00c1.addImage(image, 0);
            try {
                this.\u00c1.waitForID(0);
            }
            catch (InterruptedException ex) {}
            if (!this.\u00c1.isErrorID(0)) {
                final int width = image.getWidth(this);
                final int height = image.getHeight(this);
                final int n = width / int1;
                final int[] array = new int[width * height];
                final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, width, height, array, 0, width);
                final o[] array2 = new o[int1];
                try {
                    pixelGrabber.grabPixels();
                }
                catch (InterruptedException ex2) {}
                for (int i = 0; i < int1; ++i) {
                    array2[i] = new o();
                    int n2 = 0;
                    for (int j = 0; j < height; ++j) {
                        for (int k = 0; k < n; ++k) {
                            if (array[i * n + k + j * width] != rgb) {
                                ++n2;
                            }
                        }
                    }
                    final o o = array2[i];
                    final o o2 = array2[i];
                    final int l = n2;
                    o2.j = l;
                    o.k = new l[l];
                    int n3 = 0;
                    for (int n4 = 0; n4 < height; ++n4) {
                        for (int n5 = 0; n5 < n; ++n5) {
                            if (array[i * n + n5 + n4 * width] != rgb) {
                                array2[i].k[n3++] = new l(n5 * int2 - (n * int2 >> 1), n4 * int2 - (height * int2 >> 1), new Color(array[i * n + n5 + n4 * width]));
                            }
                        }
                    }
                    array2[i].n = (n + 1) * int2;
                    array2[i].o = (height - 1) * int2;
                }
                return array2;
            }
        }
        return new o[0];
    }
    
    private b r(final String s) {
        final b b = new b(this.\u00cd, this.\u00ce);
        this.\u00c2 = new StringTokenizer(s, "<>", true);
        int n = 0;
        while (this.\u00c2.hasMoreElements()) {
            o o = null;
            final String s2 = this.\u00c2.hasMoreElements() ? this.\u00c2.nextToken() : "error";
            if (s2.equals("<")) {
                n = 1;
            }
            else if (!s2.equals(">")) {
                if (n != 0) {
                    final String upperCase = this.d(s2).toUpperCase();
                    if (upperCase.equals("TIME")) {
                        o = new j(0);
                    }
                    else if (upperCase.equals("DATE")) {
                        o = new j(1);
                    }
                    else if (upperCase.length() > 4 && upperCase.substring(0, 5).equals("CLOCK")) {
                        final int c;
                        if ((c = this.c("CLOCK", upperCase)) != -1) {
                            o = new n(this.z, c);
                        }
                        else {
                            o = new n(this.z, 24);
                        }
                    }
                    else {
                        final int c2;
                        if ((c2 = this.c("ITEM", upperCase)) != -1) {
                            o = this.w[c2];
                        }
                        else {
                            final int c3;
                            if ((c3 = this.c("IMAGE", upperCase)) != -1) {
                                o = new d(this.x[c3]);
                            }
                        }
                    }
                    n = 0;
                }
                else {
                    o = new f(s2);
                }
            }
            if (o != null) {
                b.D(o);
            }
        }
        return b;
    }
    
    private a s(final String s) {
        this.\u00c2 = new StringTokenizer(s, ",", false);
        switch (this.\u00c2.countTokens()) {
            case 1: {
                return new a(this.b(this.d(this.\u00c2.hasMoreElements() ? this.\u00c2.nextToken() : "error").toUpperCase()));
            }
            case 3: {
                if (this.d(this.\u00c2.hasMoreElements() ? this.\u00c2.nextToken() : "error").toUpperCase().equals("GRADIENT")) {
                    return new a(this.b(this.d(this.\u00c2.hasMoreElements() ? this.\u00c2.nextToken() : "error").toUpperCase()), this.b(this.d(this.\u00c2.hasMoreElements() ? this.\u00c2.nextToken() : "error").toUpperCase()), this.\u00ce);
                }
                break;
            }
        }
        return new a(new Color(255, 255, 255));
    }
    
    private i q(final String s) {
        final c c = new c();
        final StringTokenizer stringTokenizer = new StringTokenizer(s, "&");
        while (stringTokenizer.hasMoreElements()) {
            c.D(this.l(stringTokenizer.nextToken()));
        }
        return c;
    }
    
    private i l(final String s) {
        final m m = new m();
        final StringTokenizer stringTokenizer = new StringTokenizer(s, ":");
        while (stringTokenizer.hasMoreElements()) {
            m.D(this.p(stringTokenizer.nextToken()));
        }
        return m;
    }
    
    private o p(final String s) {
        final int lastIndex = s.lastIndexOf(44);
        int int1 = 20;
        try {
            int1 = Integer.parseInt(s.substring(lastIndex + 1, s.length()));
        }
        catch (NumberFormatException ex) {}
        this.\u00c2 = new StringTokenizer(s.substring(0, lastIndex), ",<>", false);
        if (this.\u00c2.countTokens() > 0) {
            final String upperCase = this.d(this.\u00c2.hasMoreElements() ? this.\u00c2.nextToken() : "error").toUpperCase();
            switch (this.\u00c2.countTokens()) {
                case 0: {
                    if (!upperCase.equals("DELAY") && this.w[this.c("ITEM", upperCase)] != null) {
                        return new h(this.w[this.c("ITEM", upperCase)], 0, 0, 0, 0, int1);
                    }
                    break;
                }
                case 2: {
                    if (upperCase.equals("ENTER") || upperCase.equals("LEAVE")) {
                        return this.a(upperCase, this.d(this.\u00c2.hasMoreElements() ? this.\u00c2.nextToken() : "error").toUpperCase(), this.n(), int1);
                    }
                    if (upperCase.equals("ALIGN")) {
                        return this.Z(this.n(), this.n(), int1);
                    }
                    if (upperCase.equals("MORPH")) {
                        return new e(this.n(), this.n(), int1);
                    }
                    if (upperCase.equals("TWIRL")) {
                        final String upperCase2 = this.d(this.\u00c2.hasMoreElements() ? this.\u00c2.nextToken() : "error").toUpperCase();
                        final o n = this.n();
                        if (upperCase2.equals("IN")) {
                            return new q(n, true, int1);
                        }
                        return new q(n, false, int1);
                    }
                    else {
                        if (!upperCase.equals("SWEEP")) {
                            break;
                        }
                        final String upperCase3 = this.d(this.\u00c2.hasMoreElements() ? this.\u00c2.nextToken() : "error").toUpperCase();
                        final o n2 = this.n();
                        if (upperCase3.equals("IN")) {
                            return new p(n2, true, int1);
                        }
                        return new p(n2, false, int1);
                    }
                    break;
                }
            }
        }
        return new k(int1);
    }
    
    private o a(final String s, final String s2, final o o, final int n) {
        int n2;
        if (s2.equals("TOP")) {
            n2 = 0;
        }
        else if (s2.equals("BOTTOM")) {
            n2 = 180;
        }
        else if (s2.equals("LEFT")) {
            n2 = 270;
        }
        else if (s2.equals("RIGHT")) {
            n2 = 90;
        }
        else {
            n2 = Integer.parseInt(s2) % 360;
        }
        final double n3 = 3.141592653589793 * n2 / 180.0;
        double n5;
        if (Math.sin(n3) != 0.0) {
            double n4;
            if (n2 < 180) {
                n4 = this.\u00cb - o.l + (o.n >> 1);
            }
            else {
                n4 = o.l + (o.n >> 1);
            }
            n5 = n4 / Math.abs(Math.sin(n3));
        }
        else {
            n5 = 1000000.0;
        }
        double n7;
        if (Math.cos(n3) != 0.0) {
            double n6;
            if (n2 > 90 && n2 < 270) {
                n6 = this.\u00cc - o.m + (o.o >> 1);
            }
            else {
                n6 = o.m + (o.o >> 1);
            }
            n7 = n6 / Math.abs(Math.cos(n3));
        }
        else {
            n7 = 1000000.0;
        }
        double n8;
        if (n5 < n7) {
            n8 = n5;
        }
        else {
            n8 = n7;
        }
        final int n9 = (int)(n8 * Math.sin(n3));
        final int n10 = (int)(-n8 * Math.cos(n3));
        if (s.equals("ENTER")) {
            return new h(o, n9, n10, 0, 0, n);
        }
        return new h(o, 0, 0, n9, n10, n);
    }
    
    private o Z(final o o, final o o2, final int n) {
        return new g(o, 0, 0, o2.l - o.l, o2.m - o.m, o.s, o2.s, n);
    }
    
    private o Y(final String s, final o o, final int n) {
        if (s.equals("IN")) {
            return new q(o, true, n);
        }
        return new q(o, false, n);
    }
    
    private o X(final String s, final o o, final int n) {
        if (s.equals("IN")) {
            return new p(o, true, n);
        }
        return new p(o, false, n);
    }
    
    private void m(final String s, final String s2, final boolean b) {
        this.\u00c2 = new StringTokenizer(s, s2, b);
    }
    
    private int k() {
        return this.\u00c2.countTokens();
    }
    
    private boolean j() {
        return this.\u00c2.hasMoreElements();
    }
    
    private String h() {
        if (this.\u00c2.hasMoreElements()) {
            return this.\u00c2.nextToken();
        }
        return "error";
    }
    
    private String W() {
        return this.d(this.\u00c2.hasMoreElements() ? this.\u00c2.nextToken() : "error").toUpperCase();
    }
    
    private Color f() {
        return this.b(this.d(this.\u00c2.hasMoreElements() ? this.\u00c2.nextToken() : "error").toUpperCase());
    }
    
    private o n() {
        final int c = this.c("ITEM", this.d(this.\u00c2.hasMoreElements() ? this.\u00c2.nextToken() : "error").toUpperCase());
        if (this.w[c] != null) {
            return this.w[c];
        }
        return new f("error");
    }
    
    private String d(final String s) {
        int n;
        for (n = 0; n < s.length() && s.charAt(n) == ' '; ++n) {}
        int length;
        for (length = s.length(); length > 0 && s.charAt(length - 1) == ' '; --length) {}
        return s.substring(n, length);
    }
    
    private int c(final String s, final String s2) {
        if (s2.length() >= s.length() + 1 && s2.substring(0, s.length()).toUpperCase().equals(s)) {
            try {
                return Integer.parseInt(s2.substring(s.length(), s2.length()));
            }
            catch (NumberFormatException ex) {}
        }
        return -1;
    }
    
    private Color b(final String s) {
        s.toUpperCase();
        if (s.length() != 6) {
            return new Color(0);
        }
        return new Color(this.o(s.charAt(0)) << 4 | this.o(s.charAt(1)), this.o(s.charAt(2)) << 4 | this.o(s.charAt(3)), this.o(s.charAt(4)) << 4 | this.o(s.charAt(5)));
    }
    
    private int o(final char c) {
        switch (c) {
            case '1': {
                return 1;
            }
            case '2': {
                return 2;
            }
            case '3': {
                return 3;
            }
            case '4': {
                return 4;
            }
            case '5': {
                return 5;
            }
            case '6': {
                return 6;
            }
            case '7': {
                return 7;
            }
            case '8': {
                return 8;
            }
            case '9': {
                return 9;
            }
            case 'A': {
                return 10;
            }
            case 'B': {
                return 11;
            }
            case 'C': {
                return 12;
            }
            case 'D': {
                return 13;
            }
            case 'E': {
                return 14;
            }
            case 'F': {
                return 15;
            }
            default: {
                return 0;
            }
        }
    }
    
    private void g() {
        if (this.\u00c6) {
            String string = "";
            final o m = this.y.M(this.\u00c7, this.\u00c8);
            if (m == null || m.t.equals("")) {
                ((Frame)this.v).setCursor(0);
                string = "Dotz v2.0";
            }
            else {
                ((Frame)this.v).setCursor(12);
                try {
                    string = new URL(this.getDocumentBase(), m.t).toString();
                }
                catch (MalformedURLException ex) {}
            }
            this.getAppletContext().showStatus(string);
        }
    }
    
    private void i(final Event event) {
        final o m = this.y.M(event.x, event.y);
        if (m != null && !m.t.equals("") && this.\u00c9) {
            this.\u00c9 = false;
            try {
                if (m.u.equals("")) {
                    this.getAppletContext().showDocument(new URL(this.getDocumentBase(), m.t));
                    return;
                }
                this.getAppletContext().showDocument(new URL(this.getDocumentBase(), m.t), m.u);
            }
            catch (MalformedURLException ex) {}
        }
    }
    
    public boolean handleEvent(final Event event) {
        if (event.target == this) {
            switch (event.id) {
                case 503:
                case 504: {
                    this.\u00c6 = true;
                    this.\u00c7 = event.x;
                    this.\u00c8 = event.y;
                    return this.\u00c9 = true;
                }
                case 505: {
                    this.\u00c6 = false;
                    return true;
                }
                case 501: {
                    this.i(event);
                    return true;
                }
            }
        }
        return super.handleEvent(event);
    }
}
