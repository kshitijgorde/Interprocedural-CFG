import java.io.OutputStream;
import java.net.URLConnection;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.awt.event.AdjustmentEvent;
import java.awt.event.WindowEvent;
import java.awt.event.TextEvent;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.Canvas;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.InputStream;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.event.ItemEvent;
import java.awt.MenuItem;
import java.util.Hashtable;
import java.util.StringTokenizer;
import java.awt.Checkbox;
import java.util.Random;
import java.net.URL;
import java.awt.Component;
import java.awt.Color;
import java.awt.Font;
import java.util.Vector;
import java.awt.Image;
import java.awt.Dialog;
import java.awt.Choice;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.PopupMenu;
import java.awt.TextField;
import java.awt.Scrollbar;
import java.awt.Button;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ItemListener;
import java.awt.event.FocusListener;
import java.awt.event.TextListener;
import java.awt.event.ActionListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.KeyListener;
import java.awt.event.WindowListener;
import java.awt.event.MouseListener;
import java.awt.event.AdjustmentListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class CHAT extends Applet implements AdjustmentListener, MouseListener, WindowListener, KeyListener, MouseMotionListener, Runnable, ActionListener, TextListener, FocusListener, ItemListener
{
    int[] a;
    Panel b;
    Panel a;
    Panel[] a;
    Label[] a;
    Button b;
    Button a;
    Scrollbar[] a;
    TextField[] a;
    PopupMenu a;
    Toolkit a;
    Frame a;
    Choice a;
    Dialog b;
    Dialog a;
    e[] a;
    f a;
    k a;
    b a;
    Image a;
    Image b;
    Thread a;
    String[] c;
    String d;
    public boolean C;
    public boolean d;
    public boolean x;
    public boolean b;
    public boolean B;
    public boolean w;
    public boolean c;
    public boolean E;
    public boolean z;
    public boolean e;
    public boolean v;
    public boolean a;
    boolean t;
    boolean q;
    boolean p;
    boolean m;
    boolean j;
    boolean h;
    Vector[] a;
    Vector a;
    Font[] a;
    Color[] a;
    byte a;
    byte c;
    byte b;
    char[] a;
    int[] b;
    byte[] a;
    byte[] c;
    byte[] b;
    String j;
    String f;
    String o;
    String e;
    String g;
    String i;
    Object a;
    Component a;
    URL a;
    int o;
    int a;
    int f;
    int s;
    int i;
    int m;
    int l;
    Random a;
    a b;
    a c;
    a a;
    a d;
    g a;
    boolean s;
    boolean y;
    Checkbox a;
    String n;
    String b;
    String k;
    String l;
    StringTokenizer a;
    String[] d;
    String[] h;
    String[] g;
    boolean[] a;
    String[] e;
    int[] d;
    Hashtable a;
    int n;
    String[] f;
    int b;
    int j;
    int p;
    int q;
    String a;
    String m;
    String[] a;
    int[] c;
    boolean D;
    boolean g;
    boolean o;
    boolean i;
    String[] b;
    h a;
    String c;
    d a;
    int d;
    int t;
    long b;
    boolean n;
    h b;
    boolean l;
    public int c;
    public int g;
    public int r;
    public int k;
    int h;
    String h;
    boolean u;
    MenuItem a;
    long a;
    boolean r;
    boolean k;
    int e;
    boolean f;
    public static boolean A;
    
    public CHAT() {
        this.a = new int[] { 25, 20, 8, 5, 4, 12, 2, 2, 2, 5, 1, 3, 2, 3 };
        this.a = new Panel[this.a[0]];
        this.a = new Label[this.a[1]];
        this.a = new Scrollbar[this.a[6]];
        this.a = new TextField[this.a[3]];
        this.a = new PopupMenu();
        this.a = new Frame();
        this.a = new Choice();
        this.a = new e[this.a[5]];
        this.c = new String[48];
        this.d = "";
        this.C = false;
        this.x = true;
        this.a = new Vector[5];
        this.a = new Vector();
        this.a = new Font[9];
        this.b = new int[] { 0, 65535, 255, 16711935, 8421504, 32768, 65280, 8388608, 128, 8421376, 8388736, 16711680, 12632256, 32896, 16776960, 15787660, 12433259, 9055202, 9127187, 10145074, 11584734, 12211667, 13458524, 13789470, 14315734, 14329120, 14381203, 14423100, 15631086, 15761536, 16606023, 16711680, 16711935, 16716947, 16747520, 16766720, 13421772, 0, 16777215, 16776960, 0, 0, 0, 0, 0, 0 };
        this.a = new byte[256];
        this.j = "f";
        this.f = "n";
        this.e = "0A";
        this.a = 7;
        this.f = 17;
        this.s = 13421772;
        this.a = new Random();
        this.s = false;
        this.y = false;
        this.d = new String[] { "login_first", "only_reading", "button_send", "arabic" };
        this.h = new String[] { "server_side", "config", "text", "smiles", "image", "auto-login", "banner_image" };
        this.g = new String[] { "fgColor", "bgColor" };
        this.d = new int[] { 0, 14540253 };
        this.n = 2;
        this.b = 30;
        this.j = 24;
        this.p = 1;
        this.q = 16777215;
        this.a = new String[] { "", "private", "", "", "Welcome to Chat", "Style", "Incorrect Password", "Nickname : ", "Password (optionally) : ", "Nickname is used", "Please, enter your nickname", "Login", "Logout", "to All ", "Ignore", "Unignore", "Now", "in Chat :", "" };
        this.c = new int[] { 0, 3, 25, 26, 1, 4, 5, 6, 7, 8, 21, 29, 47, 12, 10, 11, 30, 31, 9 };
        this.c = " ";
        this.t = 14;
        this.b = 0L;
        this.n = true;
        this.l = false;
        this.c = -1;
        this.r = -1;
        this.k = -1;
        this.h = ">";
        this.a = new MenuItem();
        this.r = false;
        this.e = 0;
    }
    
    public String getAppletInfo() {
        return "Morevil Chat (c) 2004 www.morevil.com";
    }
    
    int a(final int n, final float n2) {
        int n3 = 0;
        int n4 = 0;
        float n5 = 1.0f;
        final int[] array = new int[3];
        for (int i = 0; i < 3; ++i) {
            array[i] = (int)((n >> (i << 3) & 0xFF) * n2);
            if (array[i] > n4) {
                n4 = array[i];
            }
        }
        if (n4 > 255) {
            n5 = 255.0f / n4;
        }
        for (int j = 0; j < 3; ++j) {
            n3 |= (int)(array[j] * n5) << (j << 3);
        }
        return n3;
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        this.e();
        this.a.a(this.a.getSelectedItem(), this.t);
    }
    
    void e() {
        for (int i = 4; i < 8; ++i) {
            this.a[i] = new Font(this.a.getSelectedItem(), i - 4, this.t);
        }
        this.a[8] = new Font(this.a.getSelectedItem(), 0, this.t - this.t / 4);
    }
    
    String c(String s) {
        if ((s = this.a.get(s)) != null) {
            return s;
        }
        return "";
    }
    
    long a(String s) {
        s = s.trim();
        final long a = l.a(s.substring(12), 0L);
        final String s2 = s = s.substring(0, 9);
        try {
            s = s2.concat(l.a(s2));
        }
        catch (Exception ex) {
            return 0L;
        }
        long n = 13L;
        long n2 = this.m;
        final long n3 = 1L;
        final char[] charArray = s.toCharArray();
        final int length = charArray.length;
        int n4;
        int i = n4 = 0;
        while (i < length) {
            final long n5 = charArray[n4];
            long n7;
            long n6 = n7 = 0L;
            while (n6 < 8L) {
                if ((1 << (int)n7 & n5) > 0L) {
                    n2 ^= n3 << (int)((n7 << 2) + n & 0x1FL);
                }
                n6 = ++n7;
            }
            if (((n += n5) & 0x1FL) == 0x0L) {
                ++n;
            }
            i = ++n4;
        }
        return n2 - a;
    }
    
    public void init() {
        System.gc();
        this.a.add(this.a);
        this.a.setFont(this.a[0]);
        this.a = this.getToolkit();
        this.c();
        this.c[18] = this.c[18].replace('c', '©');
        final String[] fontList = this.a.getFontList();
        for (int i = 0; i < fontList.length; ++i) {
            this.a.add(fontList[i]);
        }
        this.a.select("SansSerif");
        this.a.addItemListener(this);
        for (int j = 0; j < 4; ++j) {
            this.a[j] = new Font("Dialog", j, 13);
        }
        this.e();
        final int length = this.d.length;
        this.a = new boolean[length];
        for (int k = 0; k < length; ++k) {
            final String parameter = this.getParameter(this.d[k]);
            if (parameter != null) {
                final String trim = parameter.trim();
                if (trim.equalsIgnoreCase("yes")) {
                    this.a[k] = true;
                }
                else if (trim.equalsIgnoreCase("no")) {
                    this.a[k] = false;
                }
            }
        }
        this.h = this.a[3];
        if (this.a[2]) {
            this.s = true;
        }
        this.e = new String[this.h.length];
        for (int l = 0; l < this.h.length; ++l) {
            final String parameter2 = this.getParameter(this.h[l]);
            if (parameter2 != null) {
                this.e[l] = parameter2.trim();
            }
        }
        if (this.e[3] == null || this.e[3].length() == 0) {
            this.e[3] = "smiles.gif";
        }
        final String n = this.e[5];
        if (n != null && n.length() > 0) {
            this.a = new b(this);
            this.j = true;
            this.n = n;
        }
        else if (this.a[0]) {
            final boolean b = true;
            this.v = b;
            this.a = b;
        }
        else if (this.a[1]) {
            this.l = true;
        }
        for (int n2 = 0; n2 < this.g.length; ++n2) {
            final String parameter3 = this.getParameter(this.g[n2]);
            if (parameter3 != null) {
                this.d[n2] = l.b(parameter3, this.d[n2]);
            }
        }
        this.b[37] = this.d[0];
        this.b[36] = this.d[1];
        this.b[38] = this.a(this.b[36], 1.176f);
        this.d = this.a(this.b[38], 0.96f);
        this.c[40] = this.c[40].replace('+', '\b');
        String s = this.e[0];
        if (s == null || s.trim().length() == 0) {
            s = "chat.pl";
        }
        String s2 = s.replace('\\', '/').trim();
        if (s2.endsWith("/")) {
            s2 = s2.concat("chat.pl");
        }
        final String string = this.getCodeBase().toString();
        this.b = string.substring(0, string.lastIndexOf("/") + 1);
        final String substring = string.substring(0, string.indexOf(47, 8));
        this.l = substring;
        final String s3 = substring;
        try {
            this.i = this.getDocumentBase().toString();
            String s4;
            if (!s2.startsWith("http")) {
                if (s2.startsWith("/")) {
                    s4 = s3.concat(s2);
                }
                else {
                    s4 = this.b.concat(s2);
                }
            }
            else {
                s4 = s2;
            }
            this.a = new URL(s4);
        }
        catch (Exception ex) {
            this.x = false;
            this.o = true;
        }
        this.a = new Hashtable();
        String s5 = "";
        try {
            final String s6 = this.e[1];
            if (s6 != null && s6.length() > 0) {
                if (s6.startsWith("/")) {
                    s5 = this.l.concat(s6);
                }
                else {
                    s5 = this.b.concat(s6);
                }
                final InputStream openStream = new URL(s5).openStream();
                String string2 = "";
                int read;
                while ((read = openStream.read()) != -1) {
                    string2 += (char)read;
                }
                openStream.close();
                final StringTokenizer stringTokenizer = new StringTokenizer(string2, "\n");
                while (stringTokenizer.hasMoreTokens()) {
                    final String trim2 = stringTokenizer.nextToken().trim();
                    if (trim2.indexOf("#") == 0) {
                        continue;
                    }
                    final int index = trim2.indexOf(" ");
                    if (index < 0) {
                        continue;
                    }
                    this.a.put(trim2.substring(0, index), trim2.substring(index).replace('\"', ' ').trim());
                }
            }
        }
        catch (Exception ex2) {
            this.c[22] = this.c[22].concat(s5).concat(this.c[23]);
            this.i = true;
        }
        this.n = l.a(this.a.get("delay_send"), this.n) * 1000;
        this.a = l.a(this.a.get("period"), this.a);
        if (this.a < 3) {
            this.a = 3;
        }
        this.f = l.a(this.a.get("bad_words"), ";");
        if (this.c("font_choice").trim().equalsIgnoreCase("yes")) {
            this.D = true;
        }
        this.b = l.a(this.a.get("permitted_hosts"), ";");
        this.b = l.a(this.a.get("banner_height"), this.b);
        this.a = this.a.get("banner_text");
        this.m = this.a.get("banner_font");
        this.k = this.c("kickpage");
        if (!this.k.startsWith("http")) {
            if (this.k.startsWith("/")) {
                this.k = this.l.concat(this.k);
            }
            else {
                this.k = this.b.concat(this.k);
            }
        }
        this.j = l.a(this.a.get("banner_font_size"), this.j);
        this.p = l.a(this.a.get("banner_font_style"), this.p);
        this.q = l.b(this.a.get("banner_color"), this.q);
        for (int n3 = 1; n3 < this.a.length; ++n3) {
            final String s7 = this.a.get(String.valueOf(n3));
            if (s7 != null) {
                this.c[this.c[n3]] = s7;
            }
            else {
                this.c[this.c[n3]] = this.a[n3];
            }
        }
        if (!this.c[25].equals("")) {
            this.c = true;
        }
        if (!this.c[26].equals("")) {
            this.E = true;
        }
        this.b = new Dialog(this.a, this.c[1]);
        this.a = new Dialog(this.a, "");
        for (int n4 = 0; n4 < 5; ++n4) {
            this.a[n4] = new Vector();
        }
        for (int n5 = 0; n5 < 256; ++n5) {
            this.a[n5] = 0;
        }
        this.c = this.a.clone();
        this.b = this.c.clone();
        for (int n6 = 49; n6 < 52; ++n6) {
            this.c[n6] = (byte)(n6 - 48);
        }
        this.a = new char[this.b.length];
        this.a = new Color[this.b.length];
        byte b2 = 0;
        for (char c = 'A'; c < 'X'; ++c, ++b2) {
            this.a[c] = b2;
            this.a[b2] = c;
        }
        for (char c2 = 'a'; c2 < 'x'; ++c2, ++b2) {
            this.a[c2] = b2;
            this.a[b2] = c2;
        }
        for (int n7 = 0; n7 < this.b.length; ++n7) {
            this.a[n7] = new Color(this.b[n7]);
        }
        for (int n8 = 0; n8 < this.a[0]; ++n8) {
            this.a[n8] = new Panel();
            if (n8 < 13) {
                this.a[n8].setLayout(new BorderLayout());
            }
            else if (n8 < 15) {
                this.a[n8].setLayout(new GridLayout(0, 2));
            }
            else if (n8 < 21) {
                this.a[n8].setLayout(new FlowLayout(1, 0, 0));
            }
            else if (this.h) {
                this.a[n8].setLayout(new FlowLayout(2, 0, 0));
            }
            else {
                this.a[n8].setLayout(new FlowLayout(0, 0, 0));
            }
            this.a[n8].addKeyListener(this);
        }
        for (int n9 = 0; n9 < this.a[1]; ++n9) {
            (this.a[n9] = new Label()).setAlignment(2);
            this.a[n9].addKeyListener(this);
        }
        this.a[7].setAlignment(1);
        (this.b = new c(this.c[10])).addActionListener(this);
        (this.a = new c("kick")).addActionListener(this);
        for (int n10 = 0; n10 < this.a[3]; ++n10) {
            (this.a[n10] = new TextField()).addKeyListener(this);
            this.a[n10].addFocusListener(this);
            this.a[n10].addTextListener(this);
            this.a[n10].setBackground(Color.white);
        }
        this.a[0].addMouseListener(this);
        this.b.setBackground(this.a[36]);
        this.a[7].setBackground(this.a[36]);
        for (byte b3 = 1; b3 < this.a[5]; ++b3) {
            if (b3 != 11) {
                if (b3 != 2) {
                    this.a[b3] = new e(b3, this);
                    if (b3 < 2) {
                        this.a[b3].setBackground(new Color(16645630));
                    }
                    if (this.a(7167, b3)) {
                        this.a[b3].addMouseListener(this);
                    }
                    if (this.a(6800, b3)) {
                        this.a[b3].setCursor(new Cursor(12));
                    }
                    this.a[b3].addKeyListener(this);
                }
            }
        }
        this.a[1].addMouseMotionListener(this);
        (this.a = new f(this)).addKeyListener(this);
        this.a.addMouseListener(this);
        this.a.setBackground(new Color(16645630));
        for (int n11 = 0; n11 < this.a[6]; ++n11) {
            (this.a[n11] = new Scrollbar(1)).setBackground(this.a[36]);
            this.a[n11].addAdjustmentListener(this);
            this.a[n11].addKeyListener(this);
            this.a[n11].setEnabled(false);
        }
        this.b.addWindowListener(this);
        this.b.addKeyListener(this);
        this.b = true;
        this.a();
        (this.a = new Thread(this, "777")).start();
    }
    
    public void run() {
        int n = 0;
        final String s = "  Chat applet must be loaded from the webserver";
        if (!this.getCodeBase().toString().startsWith("http")) {
            this.x = false;
            this.a(s);
            return;
        }
        final String host = this.getDocumentBase().getHost();
        if (this.b.length == 1 && this.b[0].equals("")) {
            n = -1;
        }
        else {
            for (int i = this.b.length - 1; i > -1; --i) {
                if (host.indexOf(this.b[i]) != -1) {
                    n = -1;
                    break;
                }
            }
        }
        if (n != -1) {
            this.a(host.concat(this.c[46]));
            return;
        }
        try {
            Thread.sleep(200L);
        }
        catch (InterruptedException ex) {}
        this.a.requestFocus();
        if (this.o) {
            this.a(this.c[15]);
        }
        if (this.i) {
            this.x = false;
            this.a(this.c[22]);
        }
        while (this.x) {
            if (System.currentTimeMillis() - this.b > this.a * 1000) {
                final int n2 = 5;
                final String concat = "b\f".concat(this.d).concat("\ta\fr\ti\f").concat(this.j).concat("\tj\f").concat(this.f);
                if (this.j.equals("f")) {
                    this.a(concat, 6, this.m);
                }
                else if (!this.a && this.n) {
                    this.n = false;
                    new d(this, concat, n2, this.m).start();
                }
                else {
                    this.b = System.currentTimeMillis();
                }
            }
            if (this.a.size() > 0) {
                final String s2 = this.a.firstElement();
                this.a.removeElementAt(0);
                final char c = (char)(s2.charAt(0) - '0');
                final String substring = s2.substring(1);
                if (c != '\u0004' && c != '\u0002' && c != '\t') {
                    continue;
                }
                this.a(substring, c, this.m);
            }
            else {
                try {
                    Thread.sleep(100L);
                }
                catch (InterruptedException ex2) {}
            }
        }
    }
    
    boolean a(final int n, final byte b) {
        return (n >> b & 0x1) > 0;
    }
    
    void a() {
        final boolean a = d.a;
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        this.b = new h(this.a[36], this.a[37], this.c);
        this.a.setLayout(new FlowLayout(1, 0, 0));
        this.a.add(this.b);
        this.a.pack();
        this.b.addMouseMotionListener(this);
        this.b.addMouseListener(this);
        this.a.addWindowListener(this);
        this.a.setResizable(false);
        this.b.addKeyListener(this);
        this.a(1, 3);
        this.a(2, 4);
        this.a[3].setEchoChar('*');
        this.a[1].setForeground(this.a[37]);
        this.a[2].setForeground(this.a[37]);
        Label_0368: {
            if (!a) {
                if (this.h) {
                    this.a[13].add(this.a[2]);
                    this.a[13].add(this.a[1]);
                    this.a[13].add(this.a[3]);
                    this.a[13].add(this.a[2]);
                    this.a[1].setAlignment(0);
                    this.a[2].setAlignment(0);
                    if (!a) {
                        break Label_0368;
                    }
                    CHAT.A = !CHAT.A;
                }
                this.a[13].add(this.a[1]);
                this.a[13].add(this.a[2]);
                this.a[13].add(this.a[2]);
                this.a[13].add(this.a[3]);
            }
        }
        this.a[5].setAlignment(1);
        this.a[6].setAlignment(1);
        this.a(5, 1);
        this.a[5].setFont(this.a[0]);
        this.a[5].setBackground(Color.white);
        this.a[6].setForeground(Color.red);
        this.a[13].add(this.a[3]);
        this.a[13].add(this.a[4]);
        (this.c = new a("Ok", this.b[38], this.d)).setForeground(this.a[37]);
        this.c.addKeyListener(this);
        this.a[16].add(this.c);
        Label_0618: {
            if (!a) {
                if (this.h) {
                    this.a[13].add(this.a[16]);
                    this.a[13].add(this.a[5]);
                    if (!a) {
                        break Label_0618;
                    }
                }
                this.a[13].add(this.a[5]);
                this.a[13].add(this.a[16]);
            }
        }
        this.c.a(this);
        this.c.a(50, 24);
        this.a[7].add(this.a[13], "North");
        this.a[7].add(this.a[6], "Center");
        this.a[7].add(this.a[8], "South");
        this.a[8].setBackground(this.a[36]);
        this.a[8].add(this.a[7], "North");
        this.a[8].add(this.a[17], "Center");
        this.a[11].add(this.a, "Center");
        String s = "East";
        int n = 0;
        boolean b2;
        boolean h;
        final boolean b = h = (b2 = this.h);
        if (!a) {
            if (b) {
                this.h = "<";
                s = "West";
                n = 2;
            }
            this.a[11].add(this.a[0], s);
            final boolean b3;
            h = (b3 = (b2 = this.D));
        }
        if (!a) {
            if (b) {
                this.a[11].add(this.a[20], "South");
            }
            this.a[10].setText(String.valueOf(this.t));
            this.a[10].setAlignment(1);
            this.a.setBackground(this.a[36]);
            this.a.setForeground(this.a[37]);
            this.a[10].setForeground(this.a[37]);
            this.a[20].setLayout(new FlowLayout(1, 10, 0));
            b2 = (h = this.h);
        }
        Panel panel = null;
        Label_1214: {
            if (!a) {
                if (!h) {
                    this.a[20].add(this.a);
                }
                (this.a = new a("+", this.b[38], this.d)).setForeground(this.a[37]);
                this.d = new a("-", this.b[38], this.d);
                this.a.a(this);
                this.d.a(this);
                this.a.a(18, 18);
                this.d.a(18, 18);
                this.d.setForeground(this.a[37]);
                this.a[20].add(this.d);
                this.a[20].add(this.a[10]);
                this.a[20].add(this.a);
                panel = this;
                if (a) {
                    break Label_1214;
                }
                b2 = this.h;
            }
            if (b2) {
                this.a[20].add(this.a);
            }
            this.a[5].add(this.a[11], "Center");
            (this.a = new g(this)).addKeyListener(this);
            panel = new Panel(new BorderLayout());
        }
        final Panel panel2 = panel;
        Panel panel3 = this;
        if (!a) {
            if (this.j) {
                this.a[21].add(this.a);
                panel2.add(this.a[21], "Center");
            }
            this.a[22].add(this.a);
            panel2.add(this.a[22], "North");
            this.a[5].add(panel2, "South");
            this.a[0].add(this.a[5], "Center");
            this.a[1].add(this.a[1], s);
            this.a[1].add(this.a[1], "Center");
            (this.a = new h(this, this.c[12])).addMouseListener(this);
            this.a.setBackground(new Color(16645630));
            this.a[1].add(this.a, "North");
            this.a[1].add(this.a[1], "Center");
            this.a[3].add(this.a[1], "Center");
            panel3 = new Panel(new BorderLayout());
        }
        final Panel panel4 = panel3;
        this.a[3].add(panel4, "South");
        panel4.add(this.a[10], "South");
        panel4.add(this.a[19], "North");
        final Panel panel5 = new Panel(new FlowLayout(n, 5, 2));
        panel4.add(panel5, "Center");
        this.a[19].setLayout(new FlowLayout(n, 5, 3));
        this.a[19].setBackground(new Color(16645630));
        panel5.setBackground(new Color(16645630));
        this.a[19].add(this.b);
        panel5.add(this.a);
        this.a.setVisible(false);
        this.b.setBackground(this.a[36]);
        this.b.setForeground(this.a[37]);
        this.a.setBackground(this.a[36]);
        this.a.setForeground(this.a[37]);
        this.a[18].setLayout(new FlowLayout(1, 0, 5));
        this.a[18].setBackground(this.a[36]);
        (this.b = new a(this.c[29], this.b[38], this.d)).a(0, 24);
        this.b.setForeground(this.a[37]);
        this.a[18].add(this.b);
        this.a[18].setVisible(false);
        this.b.a(this);
        (this.a = new Panel()).setLayout(new BorderLayout());
        this.a.setBackground(this.a[36]);
        this.a[10].add(new h(this), "North");
        this.a[10].add(this.a[18], "Center");
        (this.a = new k(this)).setBackground(this.a[36]);
        this.a.setSize(140, this.l + 22);
        this.a.addKeyListener(this);
        this.a[10].add(this.a, "South");
        this.a[12].add(this.a[4], "North");
        this.a[9].add(this.a[15], "South");
        final Canvas canvas = new Canvas();
        final Canvas canvas2 = new Canvas();
        final Canvas canvas3 = new Canvas();
        final Canvas canvas4 = new Canvas();
        canvas.setSize(2, 0);
        canvas2.setSize(2, 0);
        canvas3.setSize(0, 2);
        canvas4.setSize(0, 2);
        this.a.add(this.a[9], "Center");
        this.a.add(canvas, "East");
        this.a.add(canvas2, "West");
        this.a.add(canvas3, "North");
        this.a.add(canvas4, "South");
        CHAT chat = this;
        Label_2278: {
            if (!a) {
                if (!this.v) {
                    this.b.setResizable(false);
                    this.b.add(this.a[7]);
                    this.b.pack();
                    this.a[12].add(this.a, "Center");
                    if (!a) {
                        break Label_2278;
                    }
                }
                (this.b = new Panel(new FlowLayout(1, 50, 50))).add(this.a[7]);
                this.a[12].add(this.b, "Center");
                this.a[12].add(this.a[9], "South");
                chat = this;
            }
            chat.a[9].setAlignment(0);
        }
        final h h2 = new h(this);
        Panel panel6 = this;
        Label_2339: {
            if (!a) {
                if (this.l) {
                    this.a[15].setVisible(false);
                    panel2.setVisible(false);
                    if (!a) {
                        break Label_2339;
                    }
                }
                panel6 = this.a[9];
            }
            panel6.add(this.a[3], s);
        }
        this.a[9].add(this.a[0], "Center");
        this.a[4].setBackground(this.a[36]);
        this.setBackground(this.a[36]);
        final i i = new i(this);
        this.a[4].add(i, "North");
        i.addKeyListener(this);
        this.a[0].setColumns(14);
        gridBagConstraints.fill = 2;
        this.a[15].setLayout(layout);
        this.a[0].setFont(this.a[0]);
        this.a[1].setFont(this.a[0]);
        this.a[8].setBackground(this.a[36]);
        this.a[8].setForeground(this.a[37]);
        this.a[8].setText(" ");
        this.a[0].setEnabled(false);
        this.a[1].setEnabled(false);
        (this.a = new Checkbox(this.c[3])).setEnabled(false);
        this.a.setForeground(this.a[37]);
        this.a.addKeyListener(this);
        final boolean h3 = this.h;
        Label_2881: {
            if (!a) {
                if (h3) {
                    this.a[15].add(this.a[6]);
                    this.a[15].add(new Label());
                    this.a[15].add(this.a);
                    this.a[15].add(this.a[8]);
                    if (!a) {
                        if (this.s) {
                            this.a[15].add(this.a[4]);
                        }
                        gridBagConstraints.weightx = 1.0;
                        layout.setConstraints(this.a[1], gridBagConstraints);
                        this.a[15].add(this.a[1]);
                        this.a[15].add(this.a[5]);
                    }
                    if (!a) {
                        break Label_2881;
                    }
                }
                this.a[15].add(this.a[5]);
                gridBagConstraints.weightx = 0.5;
                layout.setConstraints(this.a[1], gridBagConstraints);
                this.a[15].add(this.a[1]);
                if (a) {
                    break Label_2881;
                }
                final boolean s2 = this.s;
            }
            if (h3) {
                this.a[15].add(this.a[4]);
            }
            this.a[15].add(this.a[8]);
            this.a[15].add(this.a);
            this.a[15].add(this.a[6]);
        }
        this.a[15].add(this.a);
        this.setLayout(new BorderLayout());
        this.add(this.a[12], "Center");
        this.validate();
        this.i();
    }
    
    public void focusGained(final FocusEvent focusEvent) {
    }
    
    public void focusLost(final FocusEvent focusEvent) {
        this.a = focusEvent.getSource();
        if (this.a == this.a[0]) {
            this.y = true;
        }
        if (this.a == this.a[1]) {
            this.y = false;
        }
    }
    
    public void h() {
        if (this.y) {
            this.a[0].requestFocus();
        }
        else {
            this.a[1].requestFocus();
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        this.a = actionEvent.getSource();
        if (this.b == this.a && this.e == 0) {
            this.c.b = this.c.d;
            if (this.d.length() == 0 && !this.v) {
                this.l();
            }
            else {
                this.a.setVisible(false);
                this.b.setLabel(this.c[10]);
                final String f = this.f;
                final String d = this.d;
                this.b(3);
                this.a.requestFocus();
                this.B = false;
                this.d = "";
                this.f = "n";
                this.a.a = " ";
                this.a.setState(false);
                (this.a = new d(this, "a\fo\tb\f".concat(d).concat("\tj\f").concat(f), 9, this.m)).start();
                for (int i = 0; i < 4; ++i) {
                    this.a[i].setText("");
                }
                this.a[0].setEnabled(false);
                this.a[1].setEnabled(false);
                this.a("f".concat(this.o).concat(d), 5);
            }
        }
        else if (this.c == this.a) {
            this.g();
        }
        else if (this.a == this.a) {
            if (this.t > 35) {
                return;
            }
            ++this.t;
            this.e();
            this.a[10].setText(String.valueOf(this.t));
            this.a.a(this.a.getSelectedItem(), this.t);
        }
        else if (this.a == this.d) {
            if (this.t < 9) {
                return;
            }
            --this.t;
            this.e();
            this.a[10].setText(String.valueOf(this.t));
            this.a.a(this.a.getSelectedItem(), this.t);
        }
        else if (this.a == this.b) {
            this.a(2, 0, "", null);
            this.a[1].repaint();
        }
        else if (this.a == this.a) {
            final String c = this.c;
            if (!this.d.equals(c) && this.k != -1) {
                this.a.addElement("4a\fk\tb\f".concat(c).concat("\tj\f").concat(this.d));
                this.a("f".concat(this.g).concat(c), 5);
            }
        }
    }
    
    void b() {
        this.getAppletContext().showStatus("");
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        this.a = keyEvent.getSource();
        final int keyCode = keyEvent.getKeyCode();
        if ((keyCode == 74 || keyCode == 106) && !this.t && this.d.equals("")) {
            this.l();
        }
        switch (keyCode) {
            case 10: {
                if (this.a == this.a[1] || this.a == this.a) {
                    this.f();
                }
                else if (this.a == this.a[2]) {
                    this.a[2].transferFocus();
                }
                else if (this.a == this.a[3]) {
                    this.g();
                }
                else if (this.a == this.a[0]) {
                    if (this.a[1].getText().trim().equals("")) {
                        this.a[0].transferFocus();
                    }
                    else {
                        this.f();
                    }
                }
                this.j();
                break;
            }
            case 27: {
                if (this.a == this.b) {
                    this.a.dispose();
                }
                else if (this.t) {
                    this.b.dispose();
                    this.t = false;
                }
                this.j();
                break;
            }
            case 38: {
                if (!this.p) {
                    this.a(0, -1, 0);
                    break;
                }
                this.a(1, -1, 1);
                break;
            }
            case 40: {
                if (!this.p) {
                    this.a(0, 1, 0);
                    break;
                }
                this.a(1, 1, 1);
                break;
            }
            case 33: {
                if (!this.p) {
                    this.a(0, -this.a[0].getVisibleAmount(), 0);
                    break;
                }
                this.a(1, -this.a[1].getVisibleAmount(), 1);
                break;
            }
            case 34: {
                if (!this.p) {
                    this.a(0, this.a[0].getVisibleAmount(), 0);
                    break;
                }
                this.a(1, this.a[1].getVisibleAmount(), 1);
                break;
            }
        }
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.a = mouseEvent.getComponent();
        if (this.a[1] == this.a) {
            this.g = this.c;
            this.r = -1;
            this.c = -1;
            this.a[1].repaint(0, this.g - 12, 145, 16);
        }
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        this.mouseMoved(mouseEvent);
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        final Component component = mouseEvent.getComponent();
        if (component == this.b) {
            if (mouseEvent.getY() < 85 && mouseEvent.getY() > 60 && mouseEvent.getX() > 45 && mouseEvent.getX() < 55 + this.b.b) {
                this.b.setCursor(new Cursor(12));
            }
            else {
                this.b.setCursor(new Cursor(0));
            }
            return;
        }
        if (component == this.a[1]) {
            int r;
            if (this.r == (r = (mouseEvent.getY() - 3) / 15)) {
                return;
            }
            this.r = r;
            this.g = this.c;
            this.c = ++r * 15;
            this.a[1].repaint(0, this.g - 12, 145, 15);
            this.a[1].repaint(0, this.c - 12, 145, 15);
        }
    }
    
    void a(final int n, final int n2, final int n3) {
        final int value = this.a[n].getValue();
        this.a[n].setValue(value + n2);
        if (value != this.a[n].getValue()) {
            if (n3 == 0) {
                this.a.repaint();
            }
            else {
                this.a[1].repaint();
            }
        }
    }
    
    void j() {
        this.a[4].a = false;
        this.a[4].repaint();
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
    }
    
    public void textValueChanged(final TextEvent textEvent) {
        this.a = textEvent.getSource();
        int n = 20;
        int n2;
        for (n2 = 0; this.a[n2] != this.a || n2 > 3; ++n2) {}
        if (n2 > 3) {
            return;
        }
        if (n2 == 1) {
            n = 200;
        }
        int caretPosition = this.a[n2].getCaretPosition();
        if (caretPosition > n) {
            caretPosition = n;
        }
        final String text;
        if (this.a == this.a[n2] && (text = this.a[n2].getText()).length() > n) {
            this.a[n2].setText(text.substring(0, n));
            this.a[n2].setCaretPosition(caretPosition);
        }
        if (this.a == this.a[0]) {
            this.a.setEnabled(this.a[0].indexOf(this.a[0].getText()) != -1 && !this.d.equals(""));
            this.a.setState(false);
        }
    }
    
    public void windowActivated(final WindowEvent windowEvent) {
        this.a = windowEvent.getComponent();
        if (this.a == this.b) {
            this.a[2].requestFocus();
        }
        else if (this.a == this.a) {
            this.b.requestFocus();
        }
    }
    
    public void windowClosed(final WindowEvent windowEvent) {
    }
    
    public void windowClosing(final WindowEvent windowEvent) {
        this.a = windowEvent.getComponent();
        if (this.a == this.b) {
            this.b.dispose();
            this.t = false;
        }
        else if (this.a == this.a) {
            this.a.dispose();
        }
    }
    
    public void windowDeactivated(final WindowEvent windowEvent) {
        this.a = windowEvent.getComponent();
        if (this.a == this.b) {
            this.b.dispose();
            this.t = false;
        }
        else if (this.a == this.a) {
            this.a.dispose();
        }
    }
    
    public void windowDeiconified(final WindowEvent windowEvent) {
    }
    
    public void windowIconified(final WindowEvent windowEvent) {
    }
    
    public void windowOpened(final WindowEvent windowEvent) {
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    void a(final int n, final int n2) {
        if (n2 > this.c.length - 4) {
            return;
        }
        this.a[n].setText(this.c[n2 + 3]);
        if (this.j && n2 == 5) {
            this.a.setLabel("! ! !  ".concat(this.c[n2 + 3]));
            this.a.beep();
            this.a.show(this.a[15], 100, -50);
        }
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        if (mouseEvent.getModifiers() != 16) {
            return;
        }
        this.a = mouseEvent.getComponent();
        Label_0544: {
            if (this.a == this.b && mouseEvent.getY() < 85 && mouseEvent.getY() > 60 && mouseEvent.getX() > 45 && mouseEvent.getX() < 55 + this.b.b) {
                try {
                    this.getAppletContext().showDocument(new URL(this.c[13].concat("?33=").concat(this.getDocumentBase().toString())), "_");
                }
                catch (Exception ex) {}
            }
            else if (this.a == this.a) {
                this.p = false;
            }
            else if (this.a == this.a) {
                this.a.a = " ";
                this.a[1].repaint();
            }
            else {
                this.h = 0;
                while (this.h < this.a[5]) {
                    if (this.a[this.h] == this.a) {
                        switch (this.h) {
                            case 0: {
                                this.p = false;
                                break Label_0544;
                            }
                            case 1: {
                                this.p = true;
                                final int k = (mouseEvent.getY() - 3) / 15;
                                this.k = k;
                                this.a(1, this.a[1].getValue() + k, "", null);
                                this.a[1].repaint();
                                break Label_0544;
                            }
                            case 8: {
                                final int y = mouseEvent.getY();
                                final int x = mouseEvent.getX();
                                final int n = (x - 12) / 24;
                                final int n2 = (y - 24) / 14;
                                if (y > 24 && n2 < 2 && n < 9 && n > 1) {
                                    this.c = (byte)(n2 * 7 + n - 2);
                                    this.a(5, 0);
                                    this.a[5].setForeground(this.a[this.c]);
                                    this.a[2].setForeground(this.a[this.c]);
                                    this.a(5, 1);
                                }
                                final int n3;
                                if (y < 22 && (n3 = x / 24) < 7 && n3 > 4) {
                                    this.a ^= (byte)(n3 & 0x3);
                                }
                                this.a[5].setFont(this.a[this.a]);
                                this.a[2].setFont(this.a[this.a]);
                                break Label_0544;
                            }
                            default: {
                                this.a[this.h].a = true;
                                this.a[this.h].repaint();
                                break Label_0544;
                            }
                        }
                    }
                    else {
                        ++this.h;
                    }
                }
            }
        }
    }
    
    synchronized String a(final int n, final int n2, final String s, final Graphics graphics) {
        switch (n) {
            case 1: {
                if (n2 >= 0 && n2 < this.a[2].size()) {
                    this.a[0].setText(this.c = this.a[0].elementAt(n2));
                    this.a.a = this.c;
                    this.a[0].setFont(this.a[2].elementAt(n2));
                    this.a[0].setForeground(this.a[1].elementAt(n2));
                    this.g = this.a[3].elementAt(n2);
                    this.a.setEnabled(true);
                    break;
                }
                this.a.a = " ";
                break;
            }
            case 2: {
                final int index = this.a[0].indexOf(this.c);
                this.a[4].setElementAt(new Boolean(!(boolean)this.a[4].elementAt(index)), index);
                break;
            }
            case 4: {
                final int index2 = this.a[0].indexOf(s);
                if (index2 != -1 && !(boolean)this.a[4].elementAt(index2)) {
                    return null;
                }
                break;
            }
            case 5: {
                if (this.a[0].size() <= 0) {
                    break;
                }
                final int index3 = this.a[0].indexOf(s);
                if (index3 == -1) {
                    break;
                }
                for (int i = 0; i < 5; ++i) {
                    this.a[i].removeElementAt(index3);
                }
                if (this.k < 0) {
                    break;
                }
                final int n3 = this.a[1].getValue() + this.k;
                if (index3 < n3) {
                    --this.k;
                    break;
                }
                if (index3 == n3) {
                    this.k = -1;
                    break;
                }
                break;
            }
            case 6: {
                this.a.a(graphics);
                break;
            }
        }
        return "";
    }
    
    void i() {
        if (!this.w) {
            this.w = true;
            final String s = "0A";
            this.g = s;
            this.o = s;
            final boolean b = false;
            this.e = b;
            this.m = b;
            this.p = b;
            this.q = b;
            this.a[3].setVisible(!this.q);
            this.a[4].setVisible(!this.m);
            this.m = this.a.nextInt();
            if (this.m < 0) {
                this.m = -this.m;
            }
            this.a = (byte)(this.m & 0x3);
            this.a[5].setFont(this.a[this.a]);
            this.a[2].setFont(this.a[this.a]);
            this.c = (byte)(this.m >> 2 & 0xF);
            if (this.c > 13) {
                this.c = 0;
            }
            this.a[5].setForeground(this.a[this.c]);
            this.a[2].setForeground(this.a[this.c]);
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        this.a = mouseEvent.getComponent();
        if (this.a == this.a || this.a == this.b || this.a == this.a[0] || this.a == this.a) {
            return;
        }
        if (this.h == 6) {
            this.h();
        }
        if (mouseEvent.getModifiers() != 16) {
            return;
        }
        if (this.h != 1) {
            if (this.h > 1 && this.h != 8) {
                final int width = this.a[this.h].getSize().width;
                final int height = this.a[this.h].getSize().height;
                this.a[this.h].a = false;
                this.a[this.h].repaint();
                if (mouseEvent.getX() < 0 || mouseEvent.getX() > width || mouseEvent.getY() < 0 || mouseEvent.getY() > height) {
                    return;
                }
                if (this.h == 7) {
                    this.l();
                }
                else if (this.h == 6) {
                    ++this.b;
                    this.b &= 0x1;
                    this.q = (this.b == 0);
                    String h = ">";
                    String h2 = "<";
                    if (this.h) {
                        h = "<";
                        h2 = ">";
                    }
                    if (this.q) {
                        this.a.c = true;
                        this.h = h;
                    }
                    else {
                        this.h = h2;
                    }
                    this.a[3].setVisible(this.q);
                    this.a[12].validate();
                }
                else if (this.h == 9) {
                    this.g();
                }
                else if (this.h == 4) {
                    this.f();
                }
            }
        }
    }
    
    void l() {
        this.a[9].a = false;
        this.a[6].setText("");
        this.a.a();
        if (!this.v) {
            this.b.setLocation(this.a[12].getLocationOnScreen().x + this.a[12].getBounds().width / 2 - 160, this.a[12].getLocationOnScreen().y + this.a[12].getBounds().height / 2 - 130);
            this.b.setSize(290, 220);
            if (this.u) {
                this.b.show();
            }
            this.a[2].requestFocus();
        }
        this.t = true;
    }
    
    void g() {
        if (!this.x) {
            return;
        }
        this.o = String.valueOf(this.a) + this.a[this.c];
        if (this.a[2].getText().trim().length() < 1) {
            this.a[6].setText(this.c[21]);
            return;
        }
        if (!this.B) {
            this.B = true;
            this.a[6].setText("");
            (this.a = new d(this, "a\fl\tb\f".concat(this.b(this.a[2].getText())).concat("\tc\f").concat(this.b(this.a[3].getText())).concat("\tj\fn"), 2, this.m)).start();
            this.a[9].setCursor(new Cursor(3));
            this.a[7].setCursor(new Cursor(3));
        }
    }
    
    void f() {
        if (this.e != 0) {
            return;
        }
        if (System.currentTimeMillis() - this.a < this.n) {
            return;
        }
        String trim = this.a[1].getText().trim();
        final int length = trim.length();
        if (length > 0) {
            final char[] charArray = "#$%&".toCharArray();
            final char[] charArray2 = trim.toCharArray();
            int n = 0;
            for (int n2 = this.f.length - 1; -1 < n2; --n2) {
                final String s = this.f[n2];
                final int i = s.length();
                if (i >= 2) {
                    int index;
                    for (int n3 = 0; (index = trim.indexOf(s, n3)) != -1; n3 = index + i) {
                        final boolean b = index > 0;
                        final boolean b2 = b && charArray2[index - 1] == ' ';
                        final boolean b3 = index + i < length;
                        final boolean b4 = b3 && charArray2[index + i] == ' ';
                        if (!(b ^ b2)) {
                            if (!(b3 ^ b4)) {
                                for (int n4 = 0; i > n4; ++n4) {
                                    if (charArray2[index + n4] != ' ') {
                                        charArray2[index + n4] = charArray[n++ & 0x3];
                                    }
                                }
                                trim = new String(charArray2);
                            }
                        }
                    }
                }
            }
            String s2 = "0";
            if (this.a.getState()) {
                s2 = "1";
            }
            this.a[1].setText("");
            if (!this.j) {
                this.e = this.o;
            }
            final String concat = this.e.concat("\b").concat(this.d).concat("\b").concat(this.g).concat(this.b(this.a[0].getText())).concat("\b").concat(this.b(trim).concat("\b").concat(this.a.a()).concat(s2));
            this.a.addElement("4a\fm\tb\f".concat(this.d).concat("\tj\f").concat(this.f).concat("\tk\f").concat(concat));
            this.a = System.currentTimeMillis();
            this.a("d".concat(concat), true);
            this.a.b();
        }
    }
    
    void a(final int n) {
        this.B = false;
        this.c[42] = "N";
    }
    
    String b(final String s) {
        return s.replace('\t', ' ').replace('\b', ' ').replace('\f', ' ');
    }
    
    void a(final String s, final boolean b) {
        final String[] array = new String[5];
        int n = 0;
        this.a = new StringTokenizer(s, "\b");
        while (this.a.hasMoreTokens() && n < 5) {
            array[n] = this.a.nextToken();
            ++n;
        }
        if (n < 4) {
            return;
        }
        final String s2 = array[1];
        if (this.a(4, 0, s2, null) == null) {
            return;
        }
        final String s3;
        int n2;
        if (n == 5 && (s3 = array[4]).length() > 0) {
            n2 = s3.charAt(0) - '0';
            if (n2 < 0 || n2 > 64) {
                n2 = 0;
            }
            if (s3.length() > 1 && s3.charAt(1) == '1') {
                if (!s2.equals(this.d) && !array[2].substring(2).equals(this.d)) {
                    return;
                }
                if (this.h) {
                    array[1] = "(".concat(this.c[3]).concat(")").concat(s2);
                }
                else {
                    array[1] = s2.concat("(").concat(this.c[3]).concat(")");
                }
            }
        }
        else {
            n2 = 0;
        }
        String s4 = array[2].substring(2);
        final String s5 = array[3];
        if (!s4.equals("")) {
            if (this.h) {
                s4 = "  ".concat(s4).concat(" <");
            }
            else {
                s4 = "> ".concat(s4);
            }
        }
        String s6;
        if (this.h) {
            s6 = s5.concat(" :");
        }
        else {
            s6 = ": ".concat(s5);
        }
        if (b || !s2.equals(this.d)) {
            this.a.a(this.a[this.c[(byte)array[0].charAt(1)] + 4], this.a[this.a[(byte)array[0].charAt(2)]], this.a[this.c[(byte)array[2].charAt(0)] + 4], this.a[this.a[(byte)array[2].charAt(1)]], array[1], s4, s6, n2);
        }
    }
    
    void d() {
        this.a[0].setValue(this.a[0].getValue() + 1);
    }
    
    void a(final String s, final int n) {
        if (n == 96) {
            this.a(this.c[40], 5);
        }
        if (s.length() > 0) {
            switch (s.charAt(0)) {
                case 'd': {
                    if (s.length() > 4) {
                        this.a(s, false);
                        break;
                    }
                    break;
                }
                case 'g': {
                    this.j = s.substring(1);
                    break;
                }
                case 'h': {
                    if (n == 6 || s.length() < 4) {
                        break;
                    }
                    if (s.length() > 23) {
                        break;
                    }
                    if (this.a[0].indexOf(s.substring(3)) != -1) {
                        break;
                    }
                    if (this.c) {
                        this.a.a(this.a[this.c[(byte)s.charAt(1)] + 4], this.a[this.a[(byte)s.charAt(2)]], this.a[8], this.a[0], s.substring(3), this.c[25], "", -1);
                    }
                }
                case 'e': {
                    if (s.length() < 4) {
                        break;
                    }
                    if (this.a[0].indexOf(s.substring(3)) != -1) {
                        break;
                    }
                    this.a[0].addElement(s.substring(3));
                    this.a[3].addElement(s.substring(1, 3));
                    this.a[2].addElement(this.a[this.c[(byte)s.charAt(1)]]);
                    this.a[1].addElement(this.a[this.a[(byte)s.charAt(2)]]);
                    this.a[4].addElement(new Boolean(true));
                    this.a[1].repaint();
                    break;
                }
                case 'f': {
                    if (n == 6) {
                        break;
                    }
                    if (s.length() < 4) {
                        break;
                    }
                    if (this.a[0].indexOf(s.substring(3)) == -1) {
                        break;
                    }
                    if (this.E) {
                        this.a.a(this.a[this.c[(byte)s.charAt(1)] + 4], this.a[this.a[(byte)s.charAt(2)]], this.a[8], this.a[0], s.substring(3), this.c[26], "", -1);
                    }
                }
                case 'l': {
                    if (n == 6) {
                        break;
                    }
                    if (s.length() < 4) {
                        break;
                    }
                    this.a(5, 0, s.substring(3), null);
                    this.a[1].repaint();
                    break;
                }
                case 'a': {
                    this.d = this.a[2].getText();
                    this.a("h".concat(this.o).concat(this.d), 5);
                    this.a[0].setEnabled(true);
                    this.a[1].setEnabled(true);
                    this.a[15].setEnabled(true);
                    if (s.endsWith("1")) {
                        this.g = true;
                        this.a.setVisible(true);
                        this.a[12].validate();
                    }
                    if (this.v) {
                        this.a = false;
                        this.a[12].remove(this.b);
                        this.a[12].add(this.a, "Center");
                        this.a[9].setVisible(false);
                        this.a[12].validate();
                        this.a.repaint();
                    }
                    else {
                        this.b.dispose();
                    }
                    this.b(2);
                    this.a[1].setForeground(this.a[this.c]);
                    this.a[1].setFont(this.a[this.a]);
                    this.a[1].requestFocus();
                    this.a.b();
                    break;
                }
                case 'c': {
                    this.B = false;
                    if (s.charAt(1) == 'v') {
                        final String concat = "  !!! ".concat(this.c[44]).concat(" ").concat(s.substring(2));
                        this.a[9].setText(concat);
                        if (!this.z && this.j.equals("f")) {
                            this.a(concat);
                            this.z = true;
                            break;
                        }
                        break;
                    }
                    else {
                        if (s.endsWith("99")) {
                            this.b.dispose();
                            this.k = true;
                            try {
                                this.getAppletContext().showDocument(new URL(this.k));
                            }
                            catch (Exception ex) {}
                            break;
                        }
                        this.a(6, (int)this.a[(byte)s.charAt(1)]);
                        break;
                    }
                    break;
                }
                case 'n': {}
                case 'i': {
                    if (s.length() < 2) {
                        break;
                    }
                    try {
                        Integer.parseInt(s.substring(1));
                        this.f = s.substring(1);
                    }
                    catch (Exception ex2) {}
                    break;
                }
                case 'p': {
                    if (s.length() < 2) {
                        break;
                    }
                    if (this.l) {
                        break;
                    }
                    if (this.j) {
                        this.a[2].setText(this.n);
                        this.a = 0;
                        this.c = 0;
                        this.g();
                        break;
                    }
                    if (!this.v) {
                        this.b(1);
                        break;
                    }
                    break;
                }
                case 'o': {
                    this.e = true;
                    break;
                }
                case 'r': {
                    if (n == 5 && this.d.equals(s.substring(1))) {
                        this.k = true;
                        try {
                            this.getAppletContext().showDocument(new URL(this.k));
                        }
                        catch (Exception ex3) {}
                        break;
                    }
                    break;
                }
                case 'q': {
                    if (s.length() > 13 && this.a(s.substring(1)) == 0L) {
                        this.c[40] = this.c[40].replace('\b', '+');
                        break;
                    }
                    break;
                }
            }
        }
        this.r = true;
    }
    
    void b(final int n) {
        if (this.j) {
            return;
        }
        switch (n) {
            case 1: {
                this.b.a = this.c[29];
                this.a[18].setVisible(true);
                break;
            }
            case 2: {
                this.b.a = this.c[47];
                this.a[18].setVisible(true);
                break;
            }
            case 3: {
                this.a[18].setVisible(false);
                break;
            }
        }
        this.b.resize(this.b.getPreferredSize());
        this.a[3].validate();
    }
    
    void c() {
        String concat = "";
        final char[] charArray = "H`ahawu+(ac*,r#p1n?l9j;h5f7d1b3`-~/|)zns|y0$#zyx>|}aqc\u007f{6zuv3b_}otv|tdtfel~n~-ai0ezv4tfgt|n;>n{mvdp\twlbb*)cx,ca{0bwgkZ]hUvh~jtr?Cicw$6(4wJe{u\u007fghxe2p4'&'(4(+,)2?MnpfrljxIef+^digdb2Aqfsen|~dkji1mnpfrlj)kfgt!rM`~w{t4s\u007f{}98d>=ppt!dlqkbxw$u&srMgqe2e';%h5f7d1ba\to".concat("olfgqohf)cexh|}eafvpjUxvw\u007fxhtqq sgppjtblvuu>l[Sn1lLtMDBzfdy@{a`\u007fe2B/%-7I\"^ce~.fc1s3r`z{a9|nr~jvooco$phumncxxh|jt1dvff\u007fxv9|tn={iamwbplii(|yn,b`ci?ml@]_DGZRZHBPPT^PFCLUSM[OOsH@2S##lWtxywm:tlxp?fhnf{*)kgjkgdb}{<w{\u007fq/:zwk\u007fmuq2]ae\"Kkvrx($*\u007fdd}/x~ag4|e7vvn;lxlriuvf`z+x").toCharArray();
        int n2;
        final int n = n2 = 0;
        final int length = charArray.length;
        int n3 = n;
        for (int i = n; i < length; ++i) {
            final char c;
            final int n4;
            if ((c = charArray[i]) == '\t') {
                n4 = 92;
            }
            final char c2;
            switch (c2 = (char)(n4 ^ (i & 0x1F))) {
                case '~': {
                    n3 = (char)(n3 ^ 0x3E0);
                    break;
                }
                case '\u007f': {
                    this.c[n2++] = concat;
                    concat = "";
                    break;
                }
                default: {
                    concat = concat.concat(String.valueOf((char)(c2 + n3)));
                    break;
                }
            }
        }
    }
    
    public void stop() {
        if (this.x) {
            this.x = false;
            if (this.d.length() > 0) {
                String s = this.d;
                if (this.k) {
                    s = this.d.concat("\tc\fk");
                }
                this.a("a\fo\tb\f".concat(s).concat("\tj\f").concat(this.f), 9, this.m);
            }
        }
    }
    
    public void destroy() {
    }
    
    public void adjustmentValueChanged(final AdjustmentEvent adjustmentEvent) {
        if (5 > 0) {
            this.a = adjustmentEvent.getSource();
            if (this.a == this.a[0]) {
                this.p = false;
                this.a.repaint();
            }
            if (this.a == this.a[1]) {
                this.k = -1;
                this.p = true;
                this.a[1].repaint();
            }
        }
    }
    
    String a(final String s) {
        return "px=".concat(URLEncoder.encode(s.substring(2)));
    }
    
    void a(String s, final int n, final int n2) {
        if (this.m != n2) {
            return;
        }
        final String value = String.valueOf(++this.i);
        try {
            final URLConnection openConnection = this.a.openConnection();
            openConnection.setDoOutput(true);
            openConnection.setDoInput(true);
            openConnection.setUseCaches(false);
            openConnection.setAllowUserInteraction(false);
            if (s.length() > 0) {
                if (n == 6) {
                    s = s.concat("\th\f").concat(String.valueOf(this.m));
                }
                s = s.concat("\td\f").concat(this.o).concat("\tl\f").concat(value);
                if (!this.e && this.d.length() != 0) {
                    s = "e\fe\t".concat(s);
                }
                s = "\t\t".concat(s).concat("\txend\n\n");
                if (this.d) {
                    s = this.a(s);
                }
                final OutputStream outputStream = openConnection.getOutputStream();
                this.b();
                outputStream.write(s.getBytes());
                outputStream.flush();
                outputStream.close();
            }
            this.b();
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(openConnection.getInputStream()));
            final Vector vector = new Vector<String>();
            final String s2 = "";
            if (s2 != null) {
                final int lastIndex = s2.lastIndexOf(" ");
                if (lastIndex > 0) {
                    final String substring = s2.substring(lastIndex + 1);
                    final int lastIndex2 = substring.lastIndexOf("/");
                    if (lastIndex2 > 0) {
                        substring.substring(lastIndex2 + 1);
                    }
                }
            }
            String line;
            while ((line = bufferedReader.readLine()) != null && !line.equals("$B$".concat(value))) {}
            boolean equals = false;
            if (line != null) {
                String line2;
                while ((line2 = bufferedReader.readLine()) != null && !(equals = line2.equals("$E$"))) {
                    if (line2.startsWith("$B$")) {
                        break;
                    }
                    if (line2.length() <= 0) {
                        continue;
                    }
                    vector.addElement(line2);
                }
                String line3;
                while ((line3 = bufferedReader.readLine()) != null) {}
            }
            if (!equals && n == 5) {
                this.b = System.currentTimeMillis();
                return;
            }
            bufferedReader.close();
            final int size = vector.size();
            if (size == 0) {
                throw new Exception();
            }
            this.e = 0;
            if (this.m == n2 && this.x) {
                this.a[9].setText("");
                for (int i = 0; i < size; ++i) {
                    this.a(vector.elementAt(i), n);
                }
            }
            this.a("", 90 + n);
            this.b();
            if (n > 4) {
                this.b = System.currentTimeMillis();
                if (n == 9) {
                    if (!this.v) {
                        this.b(1);
                        return;
                    }
                    this.a = true;
                    this.a[12].remove(this.a);
                    this.a[12].add(this.b, "Center");
                    this.a[9].setVisible(true);
                    this.a[12].validate();
                    this.a[2].requestFocus();
                    this.j = "f";
                    this.a.a();
                    this.b = 0L;
                }
            }
            else {
                if (n == 2) {
                    this.b = 0L;
                }
                this.setCursor(new Cursor(0));
                this.a[7].setCursor(new Cursor(0));
            }
            if (this.f) {
                this.f = false;
                this.a[9].setText(this.c[34]);
                this.a(this.c[34]);
            }
        }
        catch (Exception ex) {
            this.r = true;
            if (this.e > 0) {
                this.b = System.currentTimeMillis();
            }
            this.a(n);
            this.k();
        }
    }
    
    void k() {
        if (this.j.equals("f")) {
            if (this.e++ == 1) {
                if (!this.v) {
                    this.a("!!! Error");
                    this.a(this.a.toString());
                    this.a("Does not work");
                    this.a.repaint();
                }
                else {
                    this.a[9].setText(this.a.toString().concat(" Does not work"));
                }
            }
        }
        else if (this.e++ == 1) {
            this.f = true;
            this.a[9].setText(this.c[33]);
            this.a(this.c[33]);
        }
        this.setCursor(new Cursor(0));
        this.a[7].setCursor(new Cursor(0));
    }
    
    void a(String text) {
        if (text == null) {
            text = "";
        }
        if (!text.equals("")) {
            this.a[9].setText(text);
        }
        this.a.a(this.a[0], this.a[0], this.a[1], Color.red, "", "", text, 0);
    }
}
