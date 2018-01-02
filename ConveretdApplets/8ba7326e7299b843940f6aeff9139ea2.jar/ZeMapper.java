import java.io.IOException;
import java.io.DataInputStream;
import java.awt.Rectangle;
import java.net.URL;
import java.util.StringTokenizer;
import java.awt.MediaTracker;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Panel;
import java.awt.image.ImageObserver;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import java.awt.Button;
import java.awt.Label;
import java.awt.Component;
import java.awt.Event;
import java.awt.Color;
import java.util.Vector;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Frame;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class ZeMapper extends Applet implements Runnable
{
    Frame goto;
    int char;
    String long;
    public Applet f;
    private Image int;
    private Image try;
    private Graphics do;
    private int new;
    private Thread byte;
    int c;
    a else;
    Vector e;
    b b;
    Vector g;
    c case;
    Color[] d;
    Color[] if;
    Color[] null;
    int[] void;
    private String[] a;
    private int for;
    
    public ZeMapper() {
        this.char = 0;
        this.d = new Color[4];
        this.if = new Color[2];
        this.null = new Color[2];
        this.void = new int[2];
        this.a = new String[300];
    }
    
    protected String a(final String s, final String s2) {
        final String parameter = this.getParameter(s);
        return (parameter == null) ? s2 : parameter;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        if (this.char == 1) {
            this.char = 2;
            this.remove(this.case);
            this.case.if(this);
        }
        return true;
    }
    
    public void start() {
        if (this.byte == null) {
            (this.byte = new Thread(this)).start();
        }
    }
    
    public void stop() {
        this.goto.hide();
        if (this.byte != null) {
            this.byte.stop();
            this.byte = null;
        }
    }
    
    public void init() {
    }
    
    public Image gImage(final String s) {
        try {
            return this.getImage(this.getDocumentBase(), s);
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
    
    public Image cImage(final int n, final int n2) {
        return this.getPeer().createImage(n, n2);
    }
    
    public void DoShareWare() {
        final Label label = new Label("ZeMapper 2.0 Beta Release");
        final Label label2 = new Label("This Product is in beta release, purchasing");
        final Label label3 = new Label("is not available yet.The final release is expected");
        final Label label4 = new Label("middle of February.For more information");
        final Button button = new Button("Go to CMWALOLO.MULTIMANIA.COM");
        (this.goto = new Frame()).setLayout(new FlowLayout(1, 0, 0));
        this.goto.setSize(300, 150);
        this.goto.add(label);
        this.goto.add(label2);
        this.goto.add(label3);
        this.goto.add(label4);
        this.goto.add(button);
        this.goto.show();
    }
    
    public void run() {
        this.d[0] = Color.black;
        this.d[1] = Color.black;
        this.d[2] = Color.black;
        this.d[3] = Color.black;
        this.if[0] = Color.red;
        this.if[1] = Color.red;
        this.null[0] = Color.black;
        this.null[1] = Color.black;
        this.try = this.createImage(this.size().width, this.size().height);
        this.do = this.try.getGraphics();
        this.DoShareWare();
        this.f = this;
        this.new = 0;
        this.showStatus("Loading Configuration Map");
        this.repaint();
        this.c = 1;
        this.else = new a(this);
        (this.e = new Vector()).addElement(this.else);
        this.b = new b();
        (this.g = new Vector()).addElement(this.b);
        this.else.A = this.a("cfgfile", "none");
        this.else.a(this.b, this.size().width, this.size().height);
        this.new = 1;
        this.repaint();
        this.long = "";
        while (true) {
            if (this.char != 1) {
                this.char = 0;
                this.long = "";
                try {
                    this.add(this.else);
                    this.else.show();
                    this.else.do();
                }
                catch (Exception ex) {
                    System.out.println(ex.getMessage());
                    this.stop();
                }
            }
            else {
                try {
                    Thread.currentThread();
                    Thread.sleep(100L);
                }
                catch (InterruptedException ex2) {}
            }
            if (this.long.equals("HELP")) {
                if (this.char != 0) {
                    continue;
                }
                this.remove(this.else);
                this.else.hide();
                this.void[0] = 0;
                this.void[1] = 0;
                this.char = 1;
                this.add(this.case = new c(null, 5, 5, 10, 10, String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf("Ze Mapper 2.0")).concat("|---------------------------------------------|"))).concat("Move your mouse on the map, you can select regions or objects that should be highlighted. Different actions are "))).concat("available related to the WebMaster configuration. Those different actions are :"))).concat("|---------------------------------------------|"))).concat("|1. Loading a new Map "))).concat("|2. Launching an URL"))).concat("|3. Load a list box with cities to select"))).concat("|4. Displaying this help"))).concat("|5. Going to the previous map"))).concat("|---------------------------------------------|"))).concat("Click in the window to return to the map."), "", "", this.size().width - 10, this.size().height, "Helvetica", 12, "", this.d, this.if, this.null, this.void, "FILL", 100, null, this.do, this), 0);
                this.case.requestFocus();
                this.case.a(this);
            }
            else if (!this.long.equals("PREVIOUS")) {
                this.else.hide();
                this.remove(this.else);
                ++this.c;
                this.else = new a(this);
                this.e.addElement(this.else);
                this.b = new b();
                this.g.addElement(this.b);
                this.else.A = this.long;
                this.long = "";
                this.else.a(this.b, this.size().width, this.size().height);
            }
            else {
                --this.c;
                this.else.hide();
                this.remove(this.else);
                this.repaint();
                this.e.removeElement(this.else);
                this.g.removeElement(this.b);
                this.else = this.e.elementAt(this.c - 1);
                this.b = this.g.elementAt(this.c - 1);
                this.long = "";
            }
        }
    }
    
    public void paint(final Graphics graphics) {
        if (this.new == 1) {
            this.do.setColor(Color.black);
            this.do.fillRect(0, 0, this.size().width, this.size().height);
            graphics.drawImage(this.try, 0, 0, this);
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    protected void if(final String s, final String s2) {
        this.for = 0;
        int index;
        for (int length = s.length(), i = 0; i < length; i = index + 1, ++this.for) {
            index = s.indexOf(s2, i);
            if (index < 0) {
                index = length;
            }
            this.a[this.for] = s.substring(i, index);
        }
    }
    
    public class c extends Panel
    {
        Vector k;
        private FontMetrics g;
        private String b;
        private int h;
        int s;
        boolean do;
        int x;
        int y;
        int height;
        int width;
        private Font font;
        private Color null;
        private Color try;
        private Color goto;
        private Component a;
        int o;
        int if;
        int u;
        int t;
        int n;
        int j;
        int else;
        int void;
        private int p;
        private int f;
        private String e;
        private String r;
        private Image[] new;
        private int[] d;
        private MediaTracker q;
        private Color[] int;
        private int m;
        private int i;
        private int char;
        private int byte;
        private String v;
        private int l;
        private Image for;
        int w;
        String c;
        int case;
        String long;
        
        public void if() {
            this.a = null;
            this.q = null;
            this.new = null;
            this.font = null;
            this.g = null;
            this.k.clear();
            this.k = null;
        }
        
        public c(final Image image, final int x, final int y, final int u, final int t, final String long1, final String e, final String r, final int width, final int height, final String c, final int case1, final String s, final Color[] array, final Color[] array2, final Color[] array3, final int[] array4, final String v, final int l, final Image for1, final Graphics graphics, final Component a) {
            this.k = new Vector();
            this.s = 0;
            this.do = false;
            this.null = Color.white;
            this.try = Color.white;
            this.new = new Image[4];
            this.d = new int[2];
            this.int = new Color[2];
            this.w = 0;
            this.c = "";
            this.case = 0;
            this.long = long1;
            this.v = v;
            this.l = l;
            this.x = x;
            this.y = y;
            this.int[0] = array3[0];
            this.int[1] = array3[1];
            this.m = array4[0];
            this.i = array4[1];
            if (this.p == -1) {
                this.p = 0;
            }
            if (this.f == -1) {
                this.f = 0;
            }
            this.e = e;
            this.r = r;
            this.a = a;
            this.width = width;
            this.height = height;
            this.u = u;
            this.n = this.u;
            this.t = t;
            this.j = this.t;
            this.null = array[0];
            this.try = array[1];
            this.goto = array2[0];
            if (s != null) {
                if (s.indexOf("BOLD") != -1) {
                    ++this.w;
                }
                if (s.indexOf("ITALIC") != -1) {
                    this.w += 2;
                }
            }
            this.new[2] = image;
            this.c = c;
            this.case = case1;
            this.for = for1;
            this.font = new Font(this.c, this.w, this.case);
            this.g = graphics.getFontMetrics(this.font);
            this.a(this.long);
            this.a();
        }
        
        private void a() {
            this.void = this.g.getHeight() * this.h;
            this.else = this.g.getHeight() * this.h + this.j * 2;
            this.height = this.else;
            this.new[0] = ZeMapper.this.cImage(this.width, this.height);
            this.new[1] = ZeMapper.this.cImage(this.width, this.height);
            this.a(this.new[0].getGraphics(), this.null, 2, this.new[0]);
            (this.q = new MediaTracker(this.a)).addImage(this.new[0], 2);
            try {
                this.q.waitForAll();
            }
            catch (InterruptedException ex) {
                System.out.println("Error waiting for image to load");
            }
            this.q = null;
        }
        
        private void a(final String s) {
            this.b = s.trim();
            this.h = 0;
            String s2 = "";
            final StringTokenizer stringTokenizer = new StringTokenizer(this.b, "|");
            while (stringTokenizer.hasMoreTokens()) {
                final String nextToken = stringTokenizer.nextToken();
                for (int i = 0; i < nextToken.length(); ++i) {
                    String substring = "";
                    try {
                        substring = nextToken.substring(i, i + 1);
                    }
                    catch (StringIndexOutOfBoundsException ex) {}
                    s2 = String.valueOf(String.valueOf(s2)).concat(String.valueOf(String.valueOf(substring)));
                    if (this.g.stringWidth(s2) >= this.width - this.n * 2) {
                        final int lastIndex = s2.lastIndexOf(" ");
                        String substring2 = "";
                        if (lastIndex != -1) {
                            try {
                                substring2 = s2.substring(lastIndex + 1);
                                s2 = s2.substring(0, lastIndex);
                                substring2.trim();
                            }
                            catch (StringIndexOutOfBoundsException ex2) {}
                        }
                        ++this.h;
                        this.k.addElement(s2);
                        s2 = substring2;
                    }
                }
                ++this.h;
                this.k.addElement(s2);
                s2 = "";
            }
        }
        
        public void a(final Graphics graphics, final Color color, final int n, final Image image) {
            final int[] array = new int[this.width * this.height];
            for (int i = 0; i < this.width * this.height; ++i) {
                array[i] = 1;
            }
            if (this.for != null) {
                final Image cImage = ZeMapper.this.cImage(this.width, this.height);
                final Graphics graphics2 = cImage.getGraphics();
                graphics2.drawImage(this.for, -this.x, -this.y, this.a);
                if (this.new[2] != null) {
                    graphics2.drawImage(this.new[2], 0, 0, this.width, this.height, this.a);
                }
                (this.q = new MediaTracker(this.a)).addImage(cImage, 100);
                try {
                    this.q.waitForAll();
                }
                catch (Exception ex) {}
                final a a = new a();
                a.a(cImage, cImage.getWidth(this), cImage.getHeight(this));
                a.if(this);
                a.a(this.goto, array, this.v, this.l, null, 1);
                graphics.drawImage(a.a(this), 0, 0, this.a);
            }
            graphics.setFont(this.font);
            graphics.setColor(color);
            for (int j = 0; j < this.h; ++j) {
                int n2 = (this.u < 0) ? 0 : this.u;
                if (this.e.startsWith("CENTER")) {
                    n2 = ((this.u < 0) ? 0 : this.n) + (this.width - this.n - this.g.stringWidth(this.k.elementAt(j))) / 2;
                }
                if (this.e.startsWith("RIGHT")) {
                    n2 = this.width - ((this.u < 0) ? this.n : 0) - this.g.stringWidth(this.k.elementAt(j));
                }
                int n3;
                if (this.r.startsWith("BOTTOM")) {
                    n3 = this.else - this.j - this.void + (j + 1) * this.g.getHeight();
                }
                else if (this.r.startsWith("CENTER")) {
                    System.out.println(String.valueOf(String.valueOf(new StringBuffer(" ").append(this.else).append(" ").append(this.void).append(" ").append(j).append(" ").append(this.g.getHeight()))));
                    n3 = (this.else - this.void) / 2 + (j + 1) * this.g.getHeight() - this.g.getHeight() / 3;
                }
                else {
                    n3 = this.j + (j + 1) * this.g.getHeight();
                }
                if (this.m != 0 && this.i != 0) {
                    graphics.setColor(this.int[n - 2]);
                    graphics.drawString(this.k.elementAt(j), n2 + this.m, n3 + this.i);
                    graphics.setColor(color);
                }
                graphics.drawString(this.k.elementAt(j), n2, n3);
            }
        }
        
        public void paint(final Graphics graphics) {
            super.repaint();
            graphics.drawImage(this.new[0], 0, 0, this);
        }
        
        public void update(final Graphics graphics) {
            this.paint(graphics);
        }
        
        public void a(final Component component) {
            this.setBounds(this.x, this.y, this.width, this.height);
            this.show();
            this.do = true;
        }
        
        public void if(final Component component) {
            this.remove(this);
            this.do = false;
        }
    }
    
    public class d extends Panel
    {
        Image do;
        Image if;
        boolean for;
        int byte;
        int try;
        int height;
        int width;
        String int;
        String new;
        String else;
        c char;
        Image a;
        Graphics case;
        
        public d() {
            this.for = false;
        }
        
        public void a(final Component component) {
            this.height = this.do.getHeight(component);
            this.width = this.if.getWidth(component);
            this.setBounds(this.byte, this.try, this.width, this.height);
            this.show();
        }
        
        public void if(final Component component) {
            this.hide();
            this.remove(this);
        }
        
        public void paint(final Graphics graphics) {
            this.a = ZeMapper.this.cImage(this.width, this.height);
            (this.case = this.a.getGraphics()).drawImage(ZeMapper.this.int, -this.byte, -this.try, this);
            if (!this.for) {
                this.case.drawImage(this.do, 0, 0, this);
            }
            else {
                this.case.drawImage(this.if, 0, 0, this);
            }
            graphics.drawImage(this.a, 0, 0, null);
            this.case.dispose();
        }
        
        public void update(final Graphics graphics) {
            this.paint(graphics);
        }
        
        public boolean mouseUp(final Event event, final int n, final int n2) {
            if (this.int.startsWith("DOURL")) {
                ZeMapper.this.if(this.int, ",");
                try {
                    ZeMapper.this.getAppletContext().showDocument(new URL(ZeMapper.this.getDocumentBase(), ZeMapper.this.a[1]), this.new);
                }
                catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
                return true;
            }
            if (this.int.startsWith("DOMAP")) {
                ZeMapper.this.if(this.int, ",");
                ZeMapper.this.long = ZeMapper.this.a[1];
                return true;
            }
            return true;
        }
        
        public boolean mouseEnter(final Event event, final int n, final int n2) {
            this.for = true;
            this.repaint();
            if (this.char != null) {
                ZeMapper.this.else.add(this.char, 0);
                this.char.requestFocus();
                this.char.a(this);
            }
            return false;
        }
        
        public boolean mouseExit(final Event event, final int n, final int n2) {
            if (this.char != null) {
                ZeMapper.this.else.remove(this.char);
                this.char.if(this);
            }
            this.for = false;
            this.repaint();
            return false;
        }
    }
    
    public class a extends Panel
    {
        private int aa;
        private String[] Q;
        private int[] C;
        private String[] O;
        private String[] j;
        private int[] s;
        private int[][] p;
        private Image[] I;
        private Color[][] P;
        private Color[][] ab;
        private Color[][] goto;
        private int ac;
        c[] if;
        public Vector ai;
        public Vector h;
        private String z;
        private int g;
        private int F;
        private int char;
        private int R;
        private int u;
        private int int;
        private int for;
        private MediaTracker new;
        private Image try;
        private Image i;
        private Image E;
        private Graphics V;
        private Graphics m;
        public String S;
        public String ah;
        public String X;
        public String A;
        public String G;
        public String H;
        private String U;
        private String do;
        private String f;
        private String J;
        private String w;
        private Image[] B;
        private String[] long;
        private int[] M;
        private int[] K;
        private String[] ag;
        private int[] void;
        private int L;
        private Image[] T;
        private Image[] byte;
        private int o;
        private Rectangle[] D;
        private int[] case;
        private b ae;
        private Rectangle k;
        private int d;
        private int t;
        private int r;
        private int else;
        private int v;
        private int n;
        private int null;
        private int Z;
        private int a;
        private Color Y;
        private Color q;
        private Color c;
        private Color af;
        private int e;
        private int l;
        b ad;
        Applet b;
        private String[] N;
        private int W;
        
        public a(final Applet b) {
            this.aa = 0;
            this.Q = new String[20];
            this.C = new int[20];
            this.O = new String[20];
            this.j = new String[20];
            this.s = new int[20];
            this.p = new int[20][2];
            this.I = new Image[20];
            this.P = new Color[20][4];
            this.ab = new Color[20][2];
            this.goto = new Color[20][2];
            this.if = new c[200];
            this.ai = new Vector();
            this.h = new Vector();
            this.F = 0;
            this.char = 0;
            this.B = new Image[200];
            this.long = new String[200];
            this.M = new int[200];
            this.K = new int[200];
            this.ag = new String[200];
            this.T = new Image[200];
            this.byte = new Image[200];
            this.D = new Rectangle[200];
            this.case = new int[200];
            this.d = 0;
            this.t = 0;
            this.r = 0;
            this.else = 0;
            this.v = -1;
            this.n = -1;
            this.null = -1;
            this.Z = -1;
            this.a = -1;
            this.Y = Color.blue;
            this.q = Color.white;
            this.c = Color.white;
            this.af = Color.red;
            this.N = new String[300];
            this.b = b;
        }
        
        public void a(final b ad, final int e, final int l) {
            d d = null;
            this.new = new MediaTracker(this);
            this.try = null;
            this.i = null;
            this.add(this.ad = ad, 0);
            this.ad.requestFocus();
            this.e = e;
            this.l = l;
            int n = 0;
            int n2 = 0;
            this.t = 0;
            this.r = 0;
            this.else = 0;
            this.Z = -1;
            this.E = ZeMapper.this.cImage(e, l);
            this.V = this.E.getGraphics();
            boolean b = true;
            int n3 = 0;
            String s = "";
            int int1 = 0;
            int int2 = 0;
            int int3 = 0;
            int int4 = 1;
            int int5 = 0;
            int int6 = 1;
            int n4 = 0;
            int int7 = 0;
            int int8 = -1;
            String s2 = "";
            String s3 = "";
            int int9 = 0;
            int int10 = 0;
            int int11 = 0;
            int int12 = 0;
            try {
                final DataInputStream dataInputStream = new DataInputStream(new URL(this.b.getDocumentBase(), this.A).openStream());
                do {
                    final String trim = dataInputStream.readLine().trim();
                    if (trim != null) {
                        if (trim.startsWith("BEGIN")) {
                            b = false;
                            if (trim.startsWith("BEGIN SETUP")) {
                                n = 1;
                            }
                            else if (trim.startsWith("BEGIN REGION")) {
                                n = 2;
                            }
                            else if (trim.startsWith("BEGIN OBJECT")) {
                                n = 3;
                                final ZeMapper this$0 = ZeMapper.this;
                                this$0.getClass();
                                d = this$0.new d();
                                d.new = this.H;
                            }
                            if (n <= 1 || !trim.startsWith("BEGIN TEXTBOX")) {
                                continue;
                            }
                            n3 = 1;
                            s = "";
                            int1 = 0;
                            int2 = 0;
                            int3 = 0;
                            int5 = 0;
                            int4 = 0;
                            int6 = 0;
                            n4 = 0;
                            int7 = 0;
                            int11 = 0;
                            int12 = 0;
                            s2 = "";
                            s3 = "";
                            int9 = 0;
                            int10 = 0;
                            int8 = -1;
                        }
                        else {
                            if (trim.startsWith("END")) {
                                if (n3 == 1) {
                                    if (n == 2) {
                                        if (int8 > 0) {
                                            final c[] if1 = this.if;
                                            final int n5 = n2;
                                            final ZeMapper this$2 = ZeMapper.this;
                                            this$2.getClass();
                                            if1[n5] = this$2.new c(this.I[int8 - 1], int1, int2, int9, int10, s, s3, s2, int11, int12, this.O[int6 - 1], this.s[int6 - 1], this.j[int6 - 1], this.P[int4 - 1], this.ab[int5 - 1], (Color[])((int3 == 0) ? null : this.goto[int3 - 1]), (int[])((int3 == 0) ? null : this.p[int3 - 1]), (int7 == 0) ? "" : this.Q[int7 - 1], (int7 == 0) ? 0 : this.C[int7 - 1], this.try, this.V, this);
                                        }
                                        else {
                                            final c[] if2 = this.if;
                                            final int n6 = n2;
                                            final ZeMapper this$3 = ZeMapper.this;
                                            this$3.getClass();
                                            if2[n6] = this$3.new c(null, int1, int2, int9, int10, s, s3, s2, int11, int12, this.O[int6 - 1], this.s[int6 - 1], this.j[int6 - 1], this.P[int4 - 1], this.ab[int5 - 1], (Color[])((int3 == 0) ? null : this.goto[int3 - 1]), (int[])((int3 == 0) ? null : this.p[int3 - 1]), (int7 == 0) ? "" : this.Q[int7 - 1], (int7 == 0) ? 0 : this.C[int7 - 1], this.try, this.V, this);
                                        }
                                    }
                                    if (n == 3) {
                                        this.h.addElement(null);
                                        if (int8 > 0) {
                                            try {
                                                final d d2 = d;
                                                final ZeMapper this$4 = ZeMapper.this;
                                                this$4.getClass();
                                                d2.char = this$4.new c(this.I[int8 - 1], int1, int2, int9, int10, s, s3, s2, int11, int12, this.O[int6 - 1], this.s[int6 - 1], this.j[int6 - 1], this.P[int4 - 1], this.ab[int5 - 1], (Color[])((int3 == 0) ? null : this.goto[int3 - 1]), (int[])((int3 == 0) ? null : this.p[int3 - 1]), (int7 == 0) ? "" : this.Q[int7 - 1], (int7 == 0) ? 0 : this.C[int7 - 1], this.try, this.V, this);
                                            }
                                            catch (NullPointerException ex7) {}
                                        }
                                        else {
                                            try {
                                                final d d3 = d;
                                                final ZeMapper this$5 = ZeMapper.this;
                                                this$5.getClass();
                                                d3.char = this$5.new c(null, int1, int2, int9, int10, s, s3, s2, int11, int12, this.O[int6 - 1], this.s[int6 - 1], this.j[int6 - 1], this.P[int4 - 1], this.ab[int5 - 1], (Color[])((int3 == 0) ? null : this.goto[int3 - 1]), (int[])((int3 == 0) ? null : this.p[int3 - 1]), (int7 == 0) ? "" : this.Q[int7 - 1], (int7 == 0) ? 0 : this.C[int7 - 1], this.try, this.V, this);
                                            }
                                            catch (NullPointerException ex8) {}
                                        }
                                    }
                                    n3 = 0;
                                }
                                else {
                                    if (n == 1) {
                                        try {
                                            this.new.waitForAll();
                                        }
                                        catch (InterruptedException ex9) {}
                                    }
                                    if (n == 3) {
                                        try {
                                            this.ai.addElement(d);
                                            d.a(this);
                                            this.add(d);
                                        }
                                        catch (NullPointerException ex10) {}
                                    }
                                    b = true;
                                }
                            }
                            if (b) {
                                continue;
                            }
                            this.a(trim, "=");
                            if (this.W <= 1) {
                                continue;
                            }
                            if (n == 1) {
                                this.N[0] = this.N[0].toUpperCase();
                                if (this.N[0].startsWith("C_TCOLOR")) {
                                    this.a(this.N[1], ",");
                                    this.L = Integer.parseInt(this.N[0]) - 1;
                                    try {
                                        this.P[this.L][0] = new Color(Integer.parseInt(this.N[1], 16));
                                    }
                                    catch (Exception ex11) {}
                                    try {
                                        this.P[this.L][1] = new Color(Integer.parseInt(this.N[2], 16));
                                    }
                                    catch (Exception ex12) {}
                                    try {
                                        this.P[this.L][2] = new Color(Integer.parseInt(this.N[3], 16));
                                    }
                                    catch (Exception ex13) {}
                                    try {
                                        this.P[this.L][3] = new Color(Integer.parseInt(this.N[4], 16));
                                    }
                                    catch (Exception ex14) {}
                                }
                                if (this.N[0].startsWith("C_BCOLOR")) {
                                    this.a(this.N[1], ",");
                                    this.L = Integer.parseInt(this.N[0]) - 1;
                                    try {
                                        this.ab[this.L][0] = new Color(Integer.parseInt(this.N[1], 16));
                                    }
                                    catch (Exception ex15) {}
                                    try {
                                        this.ab[this.L][1] = new Color(Integer.parseInt(this.N[2], 16));
                                    }
                                    catch (Exception ex16) {}
                                }
                                if (this.N[0].startsWith("C_FONTNAME")) {
                                    this.a(this.N[1], ",");
                                    this.L = Integer.parseInt(this.N[0]) - 1;
                                    this.O[this.L] = this.N[1];
                                }
                                if (this.N[0].startsWith("C_FONTSTYLE")) {
                                    this.a(this.N[1], ",");
                                    this.L = Integer.parseInt(this.N[0]) - 1;
                                    this.j[this.L] = this.N[1];
                                }
                                if (this.N[0].startsWith("C_FONTSIZE")) {
                                    this.a(this.N[1], ",");
                                    this.L = Integer.parseInt(this.N[0]) - 1;
                                    this.s[this.L] = Integer.parseInt(this.N[1]);
                                }
                                if (this.N[0].startsWith("C_SHADOW")) {
                                    this.a(this.N[1], ",");
                                    this.L = Integer.parseInt(this.N[0]) - 1;
                                    try {
                                        this.goto[this.L][0] = new Color(Integer.parseInt(this.N[1], 16));
                                    }
                                    catch (Exception ex17) {}
                                    try {
                                        this.goto[this.L][1] = new Color(Integer.parseInt(this.N[2], 16));
                                    }
                                    catch (Exception ex18) {}
                                    try {
                                        this.p[this.L][2] = Integer.parseInt(this.N[3]);
                                    }
                                    catch (Exception ex19) {}
                                    try {
                                        this.p[this.L][3] = Integer.parseInt(this.N[4]);
                                    }
                                    catch (Exception ex20) {}
                                }
                                if (this.N[0].startsWith("C_IMAGE")) {
                                    this.a(this.N[1], ",");
                                    this.L = Integer.parseInt(this.N[0]) - 1;
                                    try {
                                        this.I[this.L] = ZeMapper.this.getImage(ZeMapper.this.getDocumentBase(), this.N[1]);
                                    }
                                    catch (Exception ex) {
                                        System.out.println(ex.getMessage());
                                    }
                                    this.new.addImage(this.I[this.L], 1);
                                }
                                if (this.N[0].startsWith("C_EFFECT")) {
                                    this.a(this.N[1], ",");
                                    this.L = Integer.parseInt(this.N[0]) - 1;
                                    this.Q[this.L] = this.N[1];
                                    this.C[this.L] = Integer.parseInt(this.N[2]);
                                }
                                if (this.N[0].startsWith("EFFECT")) {
                                    this.a(this.N[1], ",");
                                    this.z = this.N[0];
                                    this.g = Integer.parseInt(this.N[1]);
                                }
                                if (this.N[0].startsWith("OBJECT")) {
                                    this.a(this.N[1], ",");
                                    if (this.W == 3) {
                                        this.L = Integer.parseInt(this.N[0]) - 1;
                                        try {
                                            this.T[this.L] = ZeMapper.this.getImage(ZeMapper.this.getDocumentBase(), this.N[1]);
                                        }
                                        catch (Exception ex2) {
                                            System.out.println(ex2.getMessage());
                                        }
                                        try {
                                            this.byte[this.L] = ZeMapper.this.getImage(ZeMapper.this.getDocumentBase(), this.N[2]);
                                        }
                                        catch (Exception ex3) {
                                            System.out.println(ex3.getMessage());
                                        }
                                        this.new.addImage(this.T[this.L], 1);
                                        this.new.addImage(this.byte[this.L], 1);
                                    }
                                }
                                this.N[0] = this.N[0].toUpperCase();
                                if (this.N[0].startsWith("IMAGE")) {
                                    this.S = this.N[1];
                                    try {
                                        this.try = ZeMapper.this.getImage(ZeMapper.this.getDocumentBase(), this.S);
                                    }
                                    catch (Exception ex4) {
                                        System.out.println(ex4.getMessage());
                                    }
                                    this.new.addImage(this.try, 1);
                                }
                                this.N[0] = this.N[0].toUpperCase();
                                if (this.N[0].startsWith("MASK")) {
                                    this.S = this.N[1];
                                    try {
                                        this.i = ZeMapper.this.getImage(ZeMapper.this.getDocumentBase(), this.S);
                                    }
                                    catch (Exception ex5) {
                                        System.out.println(ex5.getMessage());
                                    }
                                    this.new.addImage(this.i, 1);
                                }
                                if (this.N[0].startsWith("REDIRECTPAGE")) {
                                    this.G = this.N[1];
                                }
                                if (this.N[0].startsWith("TARGET")) {
                                    this.H = this.N[1];
                                }
                                if (this.N[0].startsWith("INPUTFILE")) {
                                    this.X = this.N[1];
                                }
                                if (this.N[0].startsWith("BCOLOR")) {
                                    try {
                                        this.Y = new Color(Integer.parseInt(this.N[1], 16));
                                    }
                                    catch (Exception ex21) {}
                                }
                                if (this.N[0].startsWith("BORDERCOLOR")) {
                                    try {
                                        this.c = new Color(Integer.parseInt(this.N[1], 16));
                                    }
                                    catch (Exception ex22) {}
                                }
                                if (this.N[0].startsWith("FILLCOLOR")) {
                                    try {
                                        this.af = new Color(Integer.parseInt(this.N[1], 16));
                                    }
                                    catch (Exception ex23) {}
                                }
                                if (this.N[0].startsWith("CAPTIONFONTDEF")) {
                                    this.ae = new b(this.N[1], this.V);
                                }
                                if (this.N[0].startsWith("CAPTIONAREA")) {
                                    this.ae.a(this.N[1]);
                                }
                                if (this.N[0].startsWith("LISTBOXCFG")) {
                                    this.U = this.N[1];
                                }
                                if (this.N[0].startsWith("LISTBOXFONT")) {
                                    this.w = this.N[1];
                                }
                                if (this.N[0].startsWith("LISTBOXUA")) {
                                    this.do = this.N[1];
                                }
                                if (this.N[0].startsWith("LISTBOXDA")) {
                                    this.f = this.N[1];
                                }
                                if (this.N[0].startsWith("LISTBOXSLIDER")) {
                                    this.J = this.N[1];
                                }
                                if (this.N[0].startsWith("LISTBOXIMAGE")) {
                                    this.ah = this.N[1];
                                }
                            }
                            if (n <= 1) {
                                continue;
                            }
                            if (n3 == 1) {
                                if (this.N[0].startsWith("IMAGE")) {
                                    int8 = Integer.parseInt(this.N[1]);
                                }
                                if (this.N[0].startsWith("EFFECT")) {
                                    int7 = Integer.parseInt(this.N[1]);
                                }
                                if (this.N[0].startsWith("COLOR")) {
                                    int4 = Integer.parseInt(this.N[1]);
                                }
                                if (this.N[0].startsWith("BCOLOR")) {
                                    int5 = Integer.parseInt(this.N[1]);
                                }
                                if (this.N[0].startsWith("FONT")) {
                                    int6 = Integer.parseInt(this.N[1]);
                                }
                                if (this.N[0].startsWith("SHADOW")) {
                                    int3 = Integer.parseInt(this.N[1]);
                                }
                                if (this.N[0].startsWith("WIDTH")) {
                                    int11 = Integer.parseInt(this.N[1]);
                                }
                                if (this.N[0].startsWith("HEIGHT")) {
                                    int12 = Integer.parseInt(this.N[1]);
                                }
                                if (this.N[0].startsWith("VALIGN")) {
                                    s2 = this.N[1];
                                }
                                if (this.N[0].startsWith("HALIGN")) {
                                    s3 = this.N[1];
                                }
                                if (this.N[0].startsWith("PADX")) {
                                    int9 = Integer.parseInt(this.N[1]);
                                }
                                if (this.N[0].startsWith("POSX")) {
                                    int1 = Integer.parseInt(this.N[1]);
                                }
                                if (this.N[0].startsWith("POSY")) {
                                    int2 = Integer.parseInt(this.N[1]);
                                }
                                if (this.N[0].startsWith("PADY")) {
                                    int10 = Integer.parseInt(this.N[1]);
                                }
                                if (!this.N[0].startsWith("TEXT")) {
                                    continue;
                                }
                                if (n4 > 0) {
                                    s = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s))).append("|").append(this.N[1])));
                                }
                                else {
                                    s = String.valueOf(String.valueOf(s)).concat(String.valueOf(String.valueOf(this.N[1])));
                                }
                                for (int i = 2; i < this.W; ++i) {
                                    s = String.valueOf(String.valueOf(s)).concat(String.valueOf(String.valueOf("=".concat(String.valueOf(String.valueOf(this.N[i]))))));
                                }
                                ++n4;
                            }
                            else if (n == 2) {
                                if (this.N[0].startsWith("INDEX")) {
                                    n2 = Integer.parseInt(this.N[1]) - 1;
                                    if (n2 + 1 > this.else) {
                                        this.else = n2 + 1;
                                    }
                                }
                                if (this.N[0].startsWith("FILLPOINT")) {
                                    this.long[n2] = this.N[1];
                                }
                                if (this.N[0].startsWith("CAPTION")) {
                                    this.ae.a(n2, this.N[1]);
                                }
                                if (!this.N[0].startsWith("ACTION")) {
                                    continue;
                                }
                                this.ag[n2] = this.N[1];
                            }
                            else {
                                if (this.N[0].startsWith("ACTION")) {
                                    d.int = this.N[1];
                                }
                                if (this.N[0].startsWith("TARGET")) {
                                    d.new = this.N[1];
                                }
                                if (this.N[0].startsWith("POSX")) {
                                    d.byte = Integer.parseInt(this.N[1]);
                                }
                                if (this.N[0].startsWith("POSY")) {
                                    d.try = Integer.parseInt(this.N[1]);
                                }
                                if (this.N[0].startsWith("CAPTION")) {
                                    d.else = this.N[1];
                                }
                                if (!this.N[0].startsWith("IMAGE")) {
                                    continue;
                                }
                                d.do = this.T[Integer.parseInt(this.N[1]) - 1];
                                d.if = this.byte[Integer.parseInt(this.N[1]) - 1];
                            }
                        }
                    }
                } while (dataInputStream.available() > 0);
                dataInputStream.close();
            }
            catch (IOException ex6) {
                System.out.println(ex6.getMessage());
            }
            if (this.else > 0) {
                this.ad.a(this, this.ah, this.U, this.do, this.f, this.J);
                this.ad.a(this.w, this.V);
                this.ad.do();
            }
        }
        
        public void paint(final Graphics graphics) {
            if (this.r == 1) {
                graphics.drawImage(this.E, 0, 0, this);
                if (this.ai.size() > 0) {
                    for (int i = 0; i < this.ai.size(); ++i) {
                        ((d)this.ai.elementAt(i)).repaint();
                    }
                }
            }
            super.paint(graphics);
        }
        
        public void update(final Graphics graphics) {
            this.paint(graphics);
        }
        
        private void if() {
            while (this.R == 1) {}
            this.R = 1;
            this.V.setColor(this.Y);
            this.V.fillRect(0, 0, this.size().width, this.size().height);
            this.V.drawImage(this.try, 0, 0, this);
            if (this.Z != -1 && this.r == 1) {
                this.V.drawImage(this.B[this.Z], this.M[this.Z], this.K[this.Z], this);
            }
            this.ae.a(this.Z, this.V);
            if (this.Z != -1) {
                if (this.n != this.Z && this.n != -1 && this.if[this.n] != null && this.if[this.n].do) {
                    this.remove(this.if[this.n]);
                    this.if[this.n].if(this);
                    this.n = -1;
                }
                if (this.if[this.Z] != null && !this.if[this.Z].do) {
                    this.add(this.if[this.Z], 0);
                    this.if[this.Z].requestFocus();
                    this.if[this.Z].a(this);
                    this.n = this.Z;
                }
            }
            if (this.v != -1) {
                this.ad.a(this.m, this.char);
            }
            this.R = 0;
            ZeMapper.this.int = this.E;
            this.repaint();
        }
        
        public void do() {
            this.r = -1;
            this.v = -1;
            this.u = 0;
            if (this.aa == 0) {
                (this.new = new MediaTracker(this)).addImage(this.try, 1);
                if (this.i != null) {
                    this.new.addImage(this.i, 1);
                }
                try {
                    this.new.waitForAll();
                }
                catch (InterruptedException ex2) {
                    System.out.println("Error waiting for image to load");
                }
                this.r = 0;
                this.repaint();
                System.currentTimeMillis();
                this.b.showStatus("Waiting for background images");
                final int[] array = new int[30];
                this.void = new int[this.i.getWidth(this) * this.i.getHeight(this)];
                for (int i = 0; i < this.i.getWidth(this) * this.i.getHeight(this); ++i) {
                    this.void[i] = -1;
                }
                if (this.i == null) {
                    this.i = this.try;
                }
                final a a = new a();
                a.a(this.i, this.i.getWidth(this), this.i.getHeight(this));
                a.if(this);
                for (int j = 0; j < this.else; ++j) {
                    this.a(this.long[j], ",");
                    for (int k = 0; k < this.W; ++k) {
                        array[k] = Integer.parseInt(this.N[k]);
                    }
                    this.M[j] = array[0];
                    this.K[j] = array[1];
                    for (int l = 4; l < this.W; l += 2) {
                        a.a(this, array[l], array[l + 1], this.c, this.af, this.void, j);
                    }
                }
                final a a2 = new a();
                for (int n = 0; n < this.else; ++n) {
                    this.a(this.long[n], ",");
                    for (int n2 = 0; n2 < this.W; ++n2) {
                        array[n2] = Integer.parseInt(this.N[n2]);
                    }
                    a2.a(this.try, this.try.getWidth(this), this.try.getHeight(this));
                    a2.if(this);
                    a2.a(this.af, this.void, this.z, this.g, null, n);
                    a2.a(this);
                    this.B[n] = a2.a(this, array[0], array[1], array[2], array[3]);
                    this.new.addImage(this.B[n], 1);
                }
                try {
                    this.new.waitForAll();
                }
                catch (InterruptedException ex3) {
                    System.out.println("Error waiting for image to load");
                }
                this.r = 1;
                final int width = this.try.getWidth(this);
                final int height = this.try.getHeight(this);
                this.setBounds((this.e - width) / 2, (this.l - height) / 2, width, height);
                this.if();
                this.b.showStatus("Loading Textboxes");
                for (int n3 = 0; n3 < this.else; ++n3) {
                    this.ad.void[n3] = new Vector();
                    this.ad.q[n3] = new Vector();
                }
                if (this.X == null) {
                    this.X = "";
                }
                if (this.X.length() > 0) {
                    try {
                        final DataInputStream dataInputStream = new DataInputStream(new URL(this.b.getDocumentBase(), this.X).openStream());
                        do {
                            final String line = dataInputStream.readLine();
                            if (line != null) {
                                this.a(line, ",");
                                final int n4 = Integer.parseInt(this.N[0]) - 1;
                                this.ad.void[n4].addElement(this.N[1]);
                                this.ad.q[n4].addElement(this.N[2]);
                                ++this.d;
                            }
                        } while (dataInputStream.available() > 0);
                        dataInputStream.close();
                    }
                    catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
            }
            this.aa = 1;
            this.r = 1;
            this.t = 1;
            this.if();
            this.b.showStatus("Applet loaded");
            Thread.currentThread().setPriority(1);
            while (ZeMapper.this.long.equals("")) {
                if (this.char == 1) {
                    try {
                        Thread.currentThread();
                        Thread.sleep(100L);
                    }
                    catch (InterruptedException ex4) {}
                    if (this.char != 1) {
                        continue;
                    }
                    this.ad.a(this.int, this.for);
                    this.if();
                }
                else {
                    try {
                        Thread.currentThread();
                        Thread.sleep(1000L);
                    }
                    catch (InterruptedException ex5) {}
                }
            }
        }
        
        public boolean mouseMove(final Event event, final int n, final int n2) {
            if (this.char == 1) {
                this.char = 0;
                this.if();
            }
            if (this.F == 1) {
                if (this.ad.a(n2) == 1) {
                    this.if();
                }
                return true;
            }
            if (this.r == 1 && !(event.target instanceof b)) {
                this.Z = this.a(this.else, n, n2);
                if (this.Z != this.a) {
                    this.v = -1;
                    this.ad.do();
                    this.if();
                }
                this.a = this.Z;
                return true;
            }
            return false;
        }
        
        public boolean mouseDown(final Event event, final int int1, final int for1) {
            if (this.F == 1) {
                this.F = 0;
                return true;
            }
            if (this.r == 1 && this.t == 1) {
                final int z = this.Z;
                this.int = int1;
                this.for = for1;
                final int a = this.ad.a(int1, for1);
                this.char = 0;
                if (a != 0) {
                    this.char = 1;
                    this.if();
                    return true;
                }
            }
            return false;
        }
        
        public boolean mouseUp(final Event event, final int n, final int n2) {
            if (this.r == 0) {
                try {
                    this.b.getAppletContext().showDocument(new URL(this.b.getDocumentBase(), "http://cmwalolo.multimania.com/ZeMapper/Index.htm"), "_top");
                }
                catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
                return true;
            }
            if (this.char == 1) {
                this.char = 0;
                this.if();
            }
            if (this.F == 1) {
                this.F = 0;
                return true;
            }
            if (this.r != 1) {
                return false;
            }
            if (this.u == 1) {
                this.u = 0;
                return true;
            }
            this.u = 0;
            if (this.t == 1) {
                final int z = this.Z;
                final int if1 = this.ad.if(n, n2);
                if (if1 > 0) {
                    if (if1 > 1) {
                        this.if();
                    }
                    if (if1 == 3) {
                        this.a();
                    }
                }
                else {
                    this.Z = this.a(this.else, n, n2);
                    if (this.Z != z && this.v != -1) {
                        this.v = -1;
                        this.ad.do();
                        this.if();
                    }
                    if (this.v != -1) {
                        this.v = -1;
                        this.ad.do();
                        this.if();
                    }
                    else {
                        if (this.Z != -1 && this.ag[this.Z].startsWith("DOURL")) {
                            this.a(this.ag[this.Z], ",");
                            try {
                                this.b.getAppletContext().showDocument(new URL(this.b.getDocumentBase(), this.N[1]), this.H);
                            }
                            catch (Exception ex2) {
                                System.out.println(ex2.getMessage());
                            }
                            return true;
                        }
                        if (this.Z != -1 && this.ag[this.Z].startsWith("DOMAP")) {
                            this.a(this.ag[this.Z], ",");
                            ZeMapper.this.long = this.N[1];
                            return true;
                        }
                        if (this.Z != -1 && this.ag[this.Z].startsWith("DOLIST")) {
                            if (this.v != this.Z) {
                                int n3 = n;
                                int n4 = n2;
                                if (n3 + this.ad.for() > this.size().width) {
                                    n3 = this.size().width - this.ad.for();
                                }
                                if (n4 + this.ad.a() > this.size().height) {
                                    n4 = this.size().height - this.ad.a();
                                }
                                this.v = this.Z;
                                this.add(this.ad, 0);
                                this.ad.a(this.v, n3, n4);
                                this.ad.show();
                                this.ad.requestFocus();
                                this.m = this.V;
                            }
                            else {
                                this.ad.do();
                                this.v = -1;
                            }
                            this.if();
                        }
                    }
                }
            }
            return true;
        }
        
        public boolean mouseDrag(final Event event, final int n, final int n2) {
            if (this.char == 1) {
                this.char = 0;
                this.if();
            }
            if (this.F != 1 && this.ad.do(n, n2) == 1) {
                this.F = 1;
                return true;
            }
            if (this.F == 1) {
                if (this.ad.a(n2) == 1) {
                    this.if();
                }
                return true;
            }
            return false;
        }
        
        public boolean keyDown(final Event event, final int n) {
            if (this.v == -1) {
                return false;
            }
            switch (n) {
                case 1004: {
                    this.ad.int(-1);
                    this.if();
                    return true;
                }
                case 1005: {
                    this.ad.int(1);
                    this.if();
                    return true;
                }
                case 1003: {
                    this.ad.int(2);
                    this.if();
                    return true;
                }
                case 1002: {
                    this.ad.int(-2);
                    this.if();
                    return true;
                }
                case 10: {
                    this.a();
                    return true;
                }
                default: {
                    return false;
                }
            }
        }
        
        private int a(final int n, final int n2, final int n3) {
            return this.void[n3 * this.i.getWidth(this) + n2];
        }
        
        private void a() {
            try {
                this.b.getAppletContext().showDocument(new URL(this.b.getDocumentBase(), String.valueOf(String.valueOf(this.G)).concat(String.valueOf(String.valueOf(this.ad.if())))), this.H);
            }
            catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        
        protected void a(final String s, final String s2) {
            this.W = 0;
            int index;
            for (int length = s.length(), i = 0; i < length; i = index + 1, ++this.W) {
                index = s.indexOf(s2, i);
                if (index < 0) {
                    index = length;
                }
                this.N[this.W] = s.substring(i, index);
            }
        }
    }
    
    public class b extends Panel
    {
        String f;
        String new;
        String t;
        String s;
        String r;
        private int int;
        private int p;
        public Vector[] void;
        public Vector[] q;
        private int l;
        private int byte;
        private int do;
        private int else;
        private int d;
        private int long;
        private int goto;
        private int case;
        private int e;
        private int char;
        private int k;
        private Image c;
        public Graphics if;
        public Graphics m;
        private Rectangle[] i;
        private FontMetrics g;
        private Font font;
        private Color o;
        private Color h;
        private Color[] n;
        private Color[] b;
        private MediaTracker j;
        private Applet a;
        private Image[] try;
        private String[] null;
        private int for;
        
        public b() {
            this.int = 0;
            this.void = new Vector[200];
            this.q = new Vector[200];
            this.i = new Rectangle[6];
            this.n = new Color[2];
            this.b = new Color[3];
            this.try = new Image[6];
            this.null = new String[300];
        }
        
        public void a(final int byte1, final int long1, final int goto1) {
            this.long = long1;
            this.goto = goto1;
            this.byte = byte1;
            this.d = 1;
            this.do = 0;
            this.else = 0;
            this.char = this.void[byte1].size();
            this.k = (int)(this.char / this.case + 0.9);
            this.i[5].x = this.long;
            this.i[5].y = this.goto;
            this.i[5].width = this.for();
            this.i[5].height = this.a();
            int height = this.i[4].height / this.k + 1;
            if (height < 10) {
                height = 10;
            }
            this.try[3] = ZeMapper.this.cImage(this.i[3].width, height);
            this.i[3].height = height;
            this.j.addImage(this.try[3], 15);
            try {
                this.j.waitForID(15);
            }
            catch (InterruptedException ex) {
                System.out.println("Error waiting for image to load");
            }
            (this.m = this.try[3].getGraphics()).setColor(this.b[1]);
            this.m.fillRect(0, 0, this.i[3].width, this.i[3].height);
            this.m.setColor(this.b[2]);
            this.m.fillRect(this.i[3].width - 1, 0, 1, this.i[3].height);
            this.m.fillRect(0, this.i[3].height - 1, this.i[3].width, 1);
            this.m.setColor(this.b[0]);
            this.m.fillRect(1, 1, this.i[3].width - 2, this.i[3].height - 2);
            try {
                this.j.waitForID(15);
            }
            catch (InterruptedException ex2) {
                System.out.println("Error waiting for image to load");
            }
            this.int = 3;
            this.setBounds(this.long, this.goto, this.for(), this.a());
            this.try[5] = ZeMapper.this.cImage(this.for(), this.a());
            this.m = this.try[5].getGraphics();
            this.show();
        }
        
        public int a() {
            return this.try[4].getHeight(this);
        }
        
        public int for() {
            return this.try[4].getWidth(this);
        }
        
        public String if() {
            return this.q[this.byte].elementAt(this.else);
        }
        
        public void a(final Component component, final String s, final String new1, final String t, final String s2, final String r) {
            this.new = new1;
            this.t = t;
            this.s = s2;
            this.r = r;
            this.i[0] = new Rectangle();
            this.i[1] = new Rectangle();
            this.i[2] = new Rectangle();
            this.i[3] = new Rectangle();
            this.i[4] = new Rectangle();
            this.i[5] = new Rectangle();
            this.a(this.new);
            try {
                this.try[4] = ZeMapper.this.getImage(ZeMapper.this.getDocumentBase(), s);
            }
            catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
            this.a(this.t, ",");
            try {
                this.try[1] = ZeMapper.this.getImage(ZeMapper.this.getDocumentBase(), this.null[0]);
            }
            catch (Exception ex2) {
                System.out.println(ex2.getMessage());
            }
            this.i[1].x = Integer.parseInt(this.null[1]);
            this.i[1].y = Integer.parseInt(this.null[2]);
            this.i[1].width = Integer.parseInt(this.null[3]);
            this.i[1].height = Integer.parseInt(this.null[4]);
            this.n[0] = new Color(Integer.parseInt(this.null[5], 16));
            this.a(this.s, ",");
            try {
                this.try[2] = ZeMapper.this.getImage(ZeMapper.this.getDocumentBase(), this.null[0]);
            }
            catch (Exception ex3) {
                System.out.println(ex3.getMessage());
            }
            this.i[2].x = Integer.parseInt(this.null[1]);
            this.i[2].y = Integer.parseInt(this.null[2]);
            this.i[2].width = Integer.parseInt(this.null[3]);
            this.i[2].height = Integer.parseInt(this.null[4]);
            this.n[1] = new Color(Integer.parseInt(this.null[5], 16));
            this.a(this.r, ",");
            this.i[3].x = Integer.parseInt(this.null[0]);
            this.i[3].y = Integer.parseInt(this.null[1]);
            this.i[3].width = Integer.parseInt(this.null[2]);
            this.i[3].height = Integer.parseInt(this.null[3]);
            this.i[4].x = Integer.parseInt(this.null[0]);
            this.i[4].y = Integer.parseInt(this.null[1]);
            this.i[4].width = Integer.parseInt(this.null[4]);
            this.i[4].height = Integer.parseInt(this.null[5]);
            this.b[0] = new Color(Integer.parseInt(this.null[6], 16));
            this.b[1] = new Color(Integer.parseInt(this.null[7], 16));
            this.b[2] = new Color(Integer.parseInt(this.null[8], 16));
            this.j = new MediaTracker(this);
            for (int i = 1; i < 5; ++i) {
                if (i != 3) {
                    this.j.addImage(this.try[i], i);
                }
            }
            try {
                this.j.waitForAll();
            }
            catch (InterruptedException ex4) {
                System.out.println("Error waiting for image to load");
            }
            this.int = 2;
        }
        
        public void a(final String s, final Graphics graphics) {
            int n = 0;
            final StringTokenizer stringTokenizer = new StringTokenizer(s, ",");
            final String nextToken = stringTokenizer.nextToken();
            final String nextToken2 = stringTokenizer.nextToken();
            final int int1 = Integer.parseInt(stringTokenizer.nextToken());
            if (nextToken2.indexOf("BOLD") >= 0) {
                ++n;
            }
            if (nextToken2.indexOf("ITALIC") >= 0) {
                n += 2;
            }
            this.font = new Font(nextToken, n, int1);
            this.g = graphics.getFontMetrics(this.font);
            this.o = new Color(Integer.parseInt(stringTokenizer.nextToken(), 16));
            this.h = new Color(Integer.parseInt(stringTokenizer.nextToken(), 16));
            this.case = this.i[0].height / this.g.getHeight();
            this.e = this.i[0].height / this.case;
        }
        
        private void a(final String s) {
            this.a(s, ",");
            this.i[0].x = Integer.parseInt(this.null[0]);
            this.i[0].y = Integer.parseInt(this.null[1]);
            this.i[0].width = Integer.parseInt(this.null[2]);
            this.i[0].height = Integer.parseInt(this.null[3]);
            this.try[0] = ZeMapper.this.cImage(this.i[0].width, this.i[0].height);
            this.if = this.try[0].getGraphics();
        }
        
        public void do() {
            this.d = 0;
            this.do = -1;
            this.else = -1;
            this.hide();
        }
        
        public void paint(final Graphics graphics) {
            if (this.int == 0) {}
            if (this.int == 3) {
                if (this.p == 0) {
                    this.l = 0;
                }
                this.m.drawImage(this.try[4], 0, 0, this);
                this.m.drawImage(this.try[1], this.i[1].x, this.i[1].y, this);
                this.m.drawImage(this.try[2], this.i[2].x, this.i[2].y, this);
                if (this.l == 1) {
                    this.m.setColor(this.n[0]);
                    this.m.drawRect(this.i[1].x, this.i[1].y, this.i[1].width, this.i[1].height);
                }
                if (this.l == 2) {
                    this.m.setColor(this.n[1]);
                    this.m.drawRect(this.i[2].x, this.i[2].y, this.i[2].width, this.i[2].height);
                }
                final double n = this.do;
                final double n2 = this.char - this.case;
                double n3 = 0.0;
                if (n2 > 0) {
                    n3 = n * (this.i[4].height - this.i[3].height) / n2;
                }
                final int n4 = (int)n3;
                this.m.drawImage(this.try[3], this.i[3].x, this.i[4].y + n4, this);
                this.i[3].y = this.i[4].y + n4;
                this.if.setColor(this.h);
                this.if.fillRect(0, 0, this.i[0].width, this.i[0].height);
                this.if.setFont(this.font);
                for (int i = 0; i < this.case; ++i) {
                    if (i + this.do < this.char) {
                        if (this.else == i + this.do) {
                            this.if.setColor(this.o);
                            this.if.fillRect(0, i * this.e, this.i[0].width, this.e);
                            this.if.setColor(this.h);
                            this.if.drawString((String)this.void[this.byte].elementAt(i + this.do), 3, (i + 1) * this.e - 2);
                        }
                        else {
                            this.if.setColor(this.o);
                            this.if.drawString((String)this.void[this.byte].elementAt(i + this.do), 3, (i + 1) * this.e - 2);
                        }
                    }
                }
                this.m.drawImage(this.try[0], this.i[0].x, this.i[0].y, this);
                graphics.drawImage(this.try[5], 0, 0, this);
            }
        }
        
        public void update(final Graphics graphics) {
            this.paint(graphics);
        }
        
        public void a(final Graphics graphics, final int p2) {
            this.p = p2;
            this.repaint();
        }
        
        public void int(final int n) {
            switch (n) {
                case 1: {
                    ++this.else;
                    if (this.else - this.do == this.case) {
                        ++this.do;
                        break;
                    }
                    break;
                }
                case -1: {
                    --this.else;
                    if (this.else < this.do) {
                        --this.do;
                        break;
                    }
                    break;
                }
                case 2: {
                    this.else += this.case;
                    this.do += this.case;
                    break;
                }
                case -2: {
                    this.else -= this.case;
                    this.do -= this.case;
                    break;
                }
            }
            if (this.else < 0) {
                this.else = 0;
            }
            if (this.else >= this.char) {
                this.else = this.char - 1;
            }
            this.if(this.do);
        }
        
        public void for(final int n) {
            this.do += n;
            if (this.do > this.char - this.case) {
                this.do = this.char - this.case;
            }
            if (this.do < 0) {
                this.do = 0;
            }
        }
        
        public void do(final int else1) {
            if (this.else != else1) {
                this.else = else1;
            }
        }
        
        public void if(final int do1) {
            this.do = do1;
            if (this.do > this.char - this.case) {
                this.do = this.char - this.case;
            }
            if (this.do < 0) {
                this.do = 0;
            }
        }
        
        public int a(final int n) {
            final int n2 = n - this.i[5].y - this.i[4].y;
            if (n2 < 0 || n2 > this.i[4].height) {
                return 0;
            }
            this.if(n2 * this.char / this.i[4].height);
            return 1;
        }
        
        public int do(final int n, final int n2) {
            if (!this.i[5].inside(n, n2)) {
                return 0;
            }
            if (this.i[3].inside(n - this.i[5].x, n2 - this.i[5].y)) {
                return 1;
            }
            return 0;
        }
        
        public int a(final int n, final int n2) {
            if (!this.i[5].inside(n, n2)) {
                return 0;
            }
            final int n3 = n - this.i[5].x;
            final int n4 = n2 - this.i[5].y;
            this.l = 0;
            if (this.i[1].inside(n3, n4)) {
                this.for(-1);
                return this.l = 1;
            }
            if (this.i[2].inside(n3, n4)) {
                this.for(1);
                return this.l = 2;
            }
            return 0;
        }
        
        public int if(final int n, final int n2) {
            this.int = 3;
            if (!this.i[5].inside(n, n2)) {
                return 0;
            }
            final int n3 = n - this.i[5].x;
            final int n4 = n2 - this.i[5].y;
            if (this.i[0].inside(n3, n4)) {
                this.do(this.do + n4 / this.e);
                return 3;
            }
            if (!this.i[3].inside(n3, n4) && this.i[4].inside(n3, n4)) {
                if (n4 < this.i[3].y) {
                    this.for(-this.case);
                }
                else {
                    this.for(this.case);
                }
                return 2;
            }
            return 1;
        }
        
        protected void a(final String s, final String s2) {
            this.for = 0;
            int index;
            for (int length = s.length(), i = 0; i < length; i = index + 1, ++this.for) {
                index = s.indexOf(s2, i);
                if (index < 0) {
                    index = length;
                }
                this.null[this.for] = s.substring(i, index);
            }
        }
    }
}
