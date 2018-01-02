import java.awt.Event;
import com.sygem.jazz3d3.TextureLoader;
import java.util.Properties;
import com.sygem.jazz3d3.Vertex;
import java.awt.Font;
import java.io.InputStream;
import com.sygem.jazz3d3.Render;
import java.net.URL;
import com.sygem.jazz3d3.Object3d;
import com.sygem.jazz3d3.Light;
import java.awt.Color;
import java.io.FilenameFilter;
import java.io.File;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Graphics;
import com.sygem.jazz3d3.Texture;
import com.sygem.jazz3d3.RenderTextured;
import com.sygem.jazz3d3.RenderSolid;
import com.sygem.jazz3d3.RenderOutline;
import com.sygem.jazz3d3.RenderTexturedHQ;
import a.a;
import a.f;
import com.sygem.jazz3d3.Camera3d;
import com.sygem.jazz3d3.World;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class SmorfApplet extends Applet implements Runnable
{
    private static final long new = 1L;
    private static final String P = "Smorf 19.36 01-05-2006";
    private static final boolean o = false;
    private final int S = 1;
    private final int L = 2;
    private final int do = 4;
    private int j;
    private String g;
    private String J;
    boolean d;
    boolean r;
    int f;
    String[] l;
    Thread c;
    World U;
    Camera3d D;
    f b;
    a byte;
    int long;
    int goto;
    double n;
    double k;
    boolean char;
    boolean E;
    boolean case;
    boolean p;
    boolean w;
    boolean int;
    boolean A;
    boolean F;
    boolean B;
    boolean R;
    boolean C;
    boolean u;
    int N;
    int z;
    int i;
    int m;
    int t;
    int H;
    RenderTexturedHQ void;
    RenderOutline else;
    RenderSolid K;
    RenderOutline G;
    RenderTextured h;
    RenderSolid null;
    int[] T;
    int s;
    int q;
    int I;
    int O;
    Texture[] try;
    String e;
    String v;
    int for;
    String if;
    String[] V;
    Texture[] a;
    boolean M;
    Graphics Q;
    
    public SmorfApplet() {
        this.j = 1;
        this.J = "";
        this.d = false;
        this.r = false;
        this.f = 0;
        this.long = 0;
        this.goto = 0;
        this.n = 0.0;
        this.k = 0.0;
        this.char = false;
        this.E = false;
        this.case = false;
        this.p = true;
        this.w = false;
        this.int = false;
        this.A = false;
        this.F = false;
        this.B = true;
        this.R = false;
        this.C = false;
        this.u = false;
        this.void = new RenderTexturedHQ();
        this.else = new RenderOutline();
        this.K = new RenderSolid();
        this.G = new RenderOutline();
        this.h = (RenderTextured)new RenderTexturedHQ();
        this.null = new RenderSolid();
        this.s = 0;
        this.q = 2;
        this.I = 0;
        this.O = 0;
        this.e = "";
        this.v = "";
        this.for = 0;
        this.if = "";
        this.M = false;
    }
    
    public void init() {
        System.out.println("Smorf 19.36 01-05-2006");
        this.U = new World((Applet)this);
        this.setLayout(new BorderLayout());
        this.add("Center", (Component)this.U);
    }
    
    public void start() {
        if (this.c == null) {
            (this.c = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.c != null) {
            this.c.stop();
            this.c = null;
        }
    }
    
    public void destroy() {
        this.U.destroy();
    }
    
    private void try() {
        final File file = new File(this.J);
        System.out.println(file.toString());
        this.l = file.list(new FilenameFilter() {
            public boolean accept(final File file, final String s) {
                return s.endsWith(".smorf.bin");
            }
        });
        System.out.println(this.l[0].toString());
    }
    
    public void run() {
        int n = 0;
        long n2 = System.currentTimeMillis();
        final double n3 = 0.0;
        final String s = "Loading..";
        this.for();
        this.int();
        this.U.setRegCode("J3D40609B781C29-D632");
        this.U.prepareCanvas();
        this.U.drawImage();
        final Graphics canvas = this.U.getCanvas();
        this.a(s, new Color(255, 255, 255));
        this.U.prep();
        final String string = String.valueOf(s) + "..";
        this.a(string, new Color(255, 255, 255));
        this.else.setPenColour(255, 0, 0);
        this.h.wrapUV(false);
        this.U.finishCanvas();
        ((Render)this.K).setDrawingMode(4);
        this.K.setReflectivity(0.5);
        this.K.setTransparency(0.5);
        this.a(String.valueOf(string) + "..", new Color(255, 255, 255));
        ((Render)this.null).setDrawingMode(4);
        this.G.setPenColour(0, 0, 255);
        this.U.enableTransparentObjects();
        this.U.addLight(new Light(0.0, 0.0, 1.0));
        (this.D = new Camera3d()).setPosition(0.0, 0.0, -200.0);
        this.D.setViewingAngle(0.5);
        this.U.setCamera(this.D);
        this.U.prepNewObjects();
        this.a("ready!", new Color(255, 255, 255));
        if (this.a != null) {
            this.U.setBackground(this.a[0]);
        }
        this.U.prepareCanvas();
        this.U.generateImage();
        this.U.drawImage();
        this.U.finishCanvas();
        canvas.setColor(new Color(255, 255, 255));
        final String parameter = this.getParameter("filename");
        if (parameter != null) {
            this.leesfile(parameter);
        }
        double doubleValue = 0.0;
        final String parameter2 = this.getParameter("rotation");
        if (parameter2 != null) {
            this.E = true;
            doubleValue = Double.valueOf(parameter2);
        }
        double n4 = doubleValue / 50.0;
        final String parameter3 = this.getParameter("axes");
        if (parameter3 != null && parameter3.trim().toLowerCase().equals("on")) {
            this.u = true;
            this.C = true;
        }
        while (true) {
            Thread.yield();
            if (this.r) {
                if (this.d) {
                    this.r = false;
                    this.U.suspend();
                    this.k = 0.0;
                    this.n = 0.0;
                    if (this.new()) {
                        n2 = System.currentTimeMillis();
                        n = 0;
                        this.E = true;
                        n4 = doubleValue / 50.0;
                    }
                    this.d = false;
                    this.U.resume();
                    this.r = true;
                }
                else {
                    this.b.a(this.k, this.n, n3);
                    if (this.E) {
                        this.b.a(4, n4);
                    }
                    this.U.prepareCanvas();
                    this.U.generateImage();
                    this.U.drawImage();
                    if (this.int) {
                        this.a();
                    }
                    if (this.w) {
                        final Graphics canvas2 = this.U.getCanvas();
                        try {
                            this.b.case.a(this.size().height, this.size().width, canvas2, (Object3d)this.b.int);
                        }
                        catch (Exception ex) {
                            System.err.println(ex.toString());
                        }
                    }
                    this.U.finishCanvas();
                }
                if (!this.E || n4 <= 0.0) {
                    continue;
                }
                double n5 = System.currentTimeMillis() - n2;
                if (n5 == 0.0) {
                    n5 = 1.0;
                }
                n4 = doubleValue / (++n * 1000 / n5);
            }
        }
    }
    
    private boolean new() {
        try {
            final InputStream openStream = new URL(this.getCodeBase(), this.g).openStream();
            this.U.deleteAllObjects();
            this.b = new f(openStream);
            ((Object3d)this.b.int).setRenderer((Render)this.K);
            ((Object3d)this.b.byte).setRenderer((Render)this.K);
            ((Object3d)this.b.long).setRenderer((Render)this.else);
            ((Object3d)this.b.long).setVisible(this.B);
            ((Object3d)this.b.try).setRenderer((Render)this.null);
            ((Object3d)this.b.new).setRenderer((Render)this.G);
            this.H = this.U.addObject((Object3d)this.b.try);
            this.t = this.U.addObject((Object3d)this.b.new);
            this.N = this.U.addObject((Object3d)this.b.long);
            this.m = this.U.addObject((Object3d)this.b.byte);
            this.z = this.U.addObject((Object3d)this.b.int);
            if (this.u) {
                this.int = this.C;
            }
            ((Object3d)this.b.new).setVisible(this.int);
            ((Object3d)this.b.byte).setVisible(!this.R);
            ((Object3d)this.b.int).setVisible(!this.R);
            ((Object3d)this.b.try).setVisible(this.R);
            this.U.prep();
            this.U.prepNewObjects();
            this.b.a(0.0, -18.43, 0.0);
            this.b.a(9.47, 0.0, 0.0);
            this.r = true;
            if (this.j == 4) {
                this.displayTextured();
            }
            return true;
        }
        catch (Exception ex) {
            System.err.println("Error reading Smorf file: " + this.g);
            ex.printStackTrace();
            return this.r = false;
        }
    }
    
    private void a() {
        this.Q = this.U.getCanvas();
        final Font font = new Font("Arial", 0, 20);
        final int height = this.size().height;
        final int width = this.size().width;
        if (this.b.char) {
            a.a.a(this.Q, height, width, (Object3d)this.b.int, font, Color.blue, ((Object3d)this.b.new).getVertex(0), "a1", true, false, false);
            a.a.a(this.Q, height, width, (Object3d)this.b.int, font, Color.blue, ((Object3d)this.b.new).getVertex(1), "-a1", true, false, false);
            a.a.a(this.Q, height, width, (Object3d)this.b.int, font, Color.blue, ((Object3d)this.b.new).getVertex(2), "a2", true, false, false);
            a.a.a(this.Q, height, width, (Object3d)this.b.int, font, Color.blue, ((Object3d)this.b.new).getVertex(3), "-a2", true, false, false);
            a.a.a(this.Q, height, width, (Object3d)this.b.int, font, Color.blue, ((Object3d)this.b.new).getVertex(4), "c", true, false, false);
            a.a.a(this.Q, height, width, (Object3d)this.b.int, font, Color.blue, ((Object3d)this.b.new).getVertex(5), "-c", true, false, false);
            a.a.a(this.Q, height, width, (Object3d)this.b.int, font, Color.blue, ((Object3d)this.b.new).getVertex(6), "a3", true, false, false);
            a.a.a(this.Q, height, width, (Object3d)this.b.int, font, Color.blue, ((Object3d)this.b.new).getVertex(7), "-a3", true, false, false);
        }
        else {
            a.a.a(this.Q, height, width, (Object3d)this.b.int, font, Color.blue, ((Object3d)this.b.new).getVertex(0), "a", true, false, false);
            a.a.a(this.Q, height, width, (Object3d)this.b.int, font, Color.blue, ((Object3d)this.b.new).getVertex(1), "-a", true, false, false);
            a.a.a(this.Q, height, width, (Object3d)this.b.int, font, Color.blue, ((Object3d)this.b.new).getVertex(2), "b", true, false, false);
            a.a.a(this.Q, height, width, (Object3d)this.b.int, font, Color.blue, ((Object3d)this.b.new).getVertex(3), "-b", true, false, false);
            a.a.a(this.Q, height, width, (Object3d)this.b.int, font, Color.blue, ((Object3d)this.b.new).getVertex(4), "c", true, false, false);
            a.a.a(this.Q, height, width, (Object3d)this.b.int, font, Color.blue, ((Object3d)this.b.new).getVertex(5), "-c", true, false, false);
        }
    }
    
    private void if() {
        (this.Q = this.U.getCanvas()).setFont(new Font("Arial", 0, 16));
        this.Q.setColor(Color.BLUE);
        final Vertex[] vertexArray = this.U.getParentObject(this.z).getVertexArray();
        for (int i = 0; i < vertexArray.length - 8; ++i) {
            final int height = this.size().height;
            final int width = this.size().width;
            final double n = height / 2 + height * vertexArray[i].getX() / 1.715;
            final double n2 = width / 2 - width * vertexArray[i].getY() / 1.715;
            final double n3 = height / 2 + height * vertexArray[i].getX() / 1.0;
            final double n4 = width / 2 - width * vertexArray[i].getY() / 1.0;
            this.Q.setColor(Color.BLACK);
            this.Q.drawString(String.valueOf(i), (int)n3, (int)n4);
            this.Q.setColor(Color.WHITE);
            this.Q.drawString(String.valueOf(i), (int)(n3 - 1.0), (int)(n4 - 1.0));
            this.Q.drawLine((int)n, (int)n2, (int)n3, (int)n4);
        }
    }
    
    private void int() {
        try {
            final Properties properties = new Properties();
            properties.load(new URL(this.getCodeBase(), "Smorf.ini").openStream());
            final String property = properties.getProperty("domaincode");
            if (property != null) {
                this.U.setDomainRegCode(property);
            }
            else {
                System.out.println("No domaincode setting found in Smorf.ini file");
            }
            String j = properties.getProperty("files_directory");
            if (j != null && j.length() > 0) {
                if (!j.endsWith("/")) {
                    j = String.valueOf(j) + "/";
                }
                this.J = j;
            }
            this.e = properties.getProperty("textures_directory");
            this.v = properties.getProperty("textures");
            this.if = properties.getProperty("backgrounds_directory");
            final String property2 = properties.getProperty("backgrounds");
            if (this.if != null && property2 != null) {
                if (!this.if.endsWith("/")) {
                    this.if = String.valueOf(this.if) + "/";
                }
                this.V = a.a.a(property2, ";");
                this.a = new Texture[this.V.length];
                if (this.V[0].trim().length() > 0) {
                    this.a[0] = TextureLoader.loadImage(String.valueOf(this.if) + this.V[0].trim());
                }
            }
            final String property3 = properties.getProperty("backgroundcolor");
            if (property3 != null) {
                final Color decode = Color.decode(property3);
                this.U.setBackgroundColour(decode.getRed(), decode.getGreen(), decode.getBlue());
            }
            else {
                this.U.setBackgroundColour(255, 255, 255);
            }
        }
        catch (Exception ex) {
            System.out.println("Error reading Smorf.ini file: " + ex.toString());
        }
    }
    
    private void for() {
        final URL codeBase = this.getCodeBase();
        codeBase.getProtocol();
        if (!"file".toLowerCase().equals(codeBase.getProtocol().toLowerCase()) && codeBase.getHost().indexOf("localhost") == -1 && !codeBase.getHost().equals("www.mindat.org") && !codeBase.getHost().equals("www.smorf.nl") && !codeBase.getHost().equals("www.xs4all.nl") && !codeBase.getHost().equals("mineralienatlas.de") && !codeBase.getHost().equals("www.mineralienatlas.de")) {
            System.out.println("Unregisterd domain: " + codeBase.getHost());
            System.out.println(this.getCodeBase());
        }
    }
    
    public boolean mouseDown(final Event event, final int long1, final int goto1) {
        this.long = long1;
        this.goto = goto1;
        return this.char = true;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        this.char = false;
        if (!this.p) {
            this.k = 0.0;
            this.n = 0.0;
        }
        return true;
    }
    
    public boolean mouseDrag(final Event event, final int n, final int n2) {
        if (this.char) {
            final double n3 = n - this.long;
            final double n4 = n2 - this.goto;
            this.n = n3 / this.size().width * 15.0;
            this.k = n4 / this.size().height * 15.0;
        }
        this.E = false;
        return true;
    }
    
    public boolean keyDown(final Event event, final int n) {
        switch ((char)n) {
            case 'a': {
                this.setAntiAlias(!this.case);
                break;
            }
            case 'w': {
                this.showWireframe(!this.B);
                break;
            }
            case 'm': {
                this.showMillers(!this.w);
                break;
            }
            case 's': {
                this.displaySticks();
                break;
            }
            case 'k': {
                this.showAxes(!this.int);
                break;
            }
            case '+':
            case '=': {
                if (event.modifiers == 8) {
                    this.zoomMillers(1.2f);
                    break;
                }
                this.zoom(1.2f);
                break;
            }
            case '-':
            case '_': {
                if (event.modifiers == 8) {
                    this.zoomMillers(0.8333333f);
                    break;
                }
                this.zoom(0.8333333f);
                break;
            }
            case 'R':
            case 'r': {
                this.setRotation(!this.p);
                break;
            }
            case 't': {
                if (this.j == 1) {
                    this.q = this.if(this.q, 0, 4);
                    this.setTransparency(this.q / 4.0f);
                    break;
                }
                this.displaySolid();
                break;
            }
            case 'T': {
                if (this.j == 1) {
                    this.q = this.a(this.q, 0, 4);
                    this.setTransparency(this.q / 4.0f);
                    break;
                }
                this.displaySolid();
                break;
            }
            case 'b': {
                this.nextBackground(true);
                break;
            }
            case 'B': {
                this.nextBackground(false);
                break;
            }
            case 'p': {
                this.setPerspective(!this.A);
                break;
            }
            case 'x': {
                if (this.j == 4) {
                    this.nextTexture(true);
                    break;
                }
                this.displayTextured();
                break;
            }
            case 'X': {
                if (this.j == 4) {
                    this.nextTexture(false);
                    break;
                }
                this.displayTextured();
                break;
            }
            case '1': {
                this.b.char();
                break;
            }
            case '2': {
                this.b.do();
                break;
            }
            case '3': {
                this.b.int();
                break;
            }
            case 'f': {
                this.a(true);
                break;
            }
            case 'F': {
                this.a(false);
                break;
            }
            case 'd': {
                this.F = !this.F;
                break;
            }
        }
        return true;
    }
    
    public void setAntiAlias(final boolean case1) {
        this.case = case1;
        this.U.setAntiAliasing(this.case);
    }
    
    public void showWireframe(final boolean b) {
        if (this.r && this.j != 2) {
            this.B = b;
            ((Object3d)this.b.long).setVisible(this.B);
        }
    }
    
    public void showMillers(final boolean w) {
        this.w = w;
    }
    
    public void displaySticks() {
        if (this.r) {
            this.R = true;
            ((Object3d)this.b.int).setVisible(false);
            ((Object3d)this.b.byte).setVisible(false);
            ((Object3d)this.b.try).setVisible(true);
            ((Object3d)this.b.long).setVisible(true);
            this.j = 2;
        }
    }
    
    public void displayTextured() {
        if (this.r && this.do()) {
            this.U.suspend();
            ((Object3d)this.b.int).setVisible(true);
            ((Object3d)this.b.byte).setVisible(false);
            ((Object3d)this.b.int).setRenderer((Render)this.h);
            ((Object3d)this.b.try).setVisible(false);
            this.U.prep();
            this.U.resume();
            this.j = 4;
        }
    }
    
    public void displaySolid() {
        if (this.r) {
            this.R = false;
            ((Object3d)this.b.int).setVisible(true);
            ((Object3d)this.b.byte).setVisible(true);
            ((Object3d)this.b.int).setRenderer((Render)this.K);
            ((Object3d)this.b.byte).setRenderer((Render)this.K);
            ((Object3d)this.b.try).setVisible(false);
            this.j = 1;
        }
    }
    
    public void showAxes(final boolean int1) {
        if (this.r) {
            this.int = int1;
            ((Object3d)this.b.new).setVisible(this.int);
        }
    }
    
    public void zoom(final float n) {
        this.b.if(n);
    }
    
    public void zoomMillers(final float n) {
        this.b.a(n);
    }
    
    public void setRotation(final boolean p) {
        this.k = 0.0;
        this.n = 0.0;
        this.p = p;
    }
    
    public void stopRotation() {
        this.k = 0.0;
        this.n = 0.0;
        this.E = false;
    }
    
    private void a(final boolean b) {
    }
    
    private boolean do() {
        if (!this.M) {
            this.M = true;
            if (this.e != null && this.v != null) {
                final String[] a = a.a.a(this.v, ";");
                this.try = new Texture[a.length + 1];
                for (int i = 0; i < a.length; ++i) {
                    if (a[i].trim().length() > 0) {
                        this.try[i + 1] = TextureLoader.loadImage(String.valueOf(this.e) + '/' + a[i].trim());
                        this.h.setTexture(this.try[i + 1]);
                    }
                }
                this.h.setTexture(0);
            }
        }
        return this.try != null;
    }
    
    public void nextTexture(final boolean b) {
        if (this.j == 4 && this.do()) {
            this.U.suspend();
            if (b) {
                this.O = this.if(this.O, 0, this.try.length - 2);
            }
            else {
                this.O = this.a(this.O, 0, this.try.length - 2);
            }
            this.U.prep();
            this.h.setTexture(this.O);
            this.U.resume();
        }
    }
    
    public void setTransparency(float n) {
        if (this.j == 1) {
            if (n < 0.0f) {
                n = 0.0f;
            }
            if (n > 1.0f) {
                n = 1.0f;
            }
            this.K.setTransparency((double)n);
        }
    }
    
    public void nextBackground(final boolean b) {
        if (this.a != null) {
            if (b) {
                this.for = this.if(this.for, 0, this.a.length - 1);
            }
            else {
                this.for = this.a(this.for, 0, this.a.length - 1);
            }
            try {
                if (this.a[this.for] == null) {
                    this.a[this.for] = TextureLoader.loadImage(String.valueOf(this.if) + '/' + this.V[this.for].trim());
                }
                this.U.setBackground(this.a[this.for]);
            }
            catch (Exception ex) {}
        }
    }
    
    public void setPerspective(final boolean b) {
        if (b) {
            this.A = true;
            this.D.setPosition(0.0, 0.0, -3.5);
            this.D.setViewingAngle(30.0);
        }
        else {
            this.A = false;
            this.D.setPosition(0.0, 0.0, -200.0);
            this.D.setViewingAngle(0.5);
        }
    }
    
    public void leesfile(final String s) {
        this.g = String.valueOf(this.J) + s;
        this.d = true;
        this.r = true;
    }
    
    public void loadFile(final String s) {
        this.g = String.valueOf(this.J) + s;
        this.d = true;
        this.r = true;
    }
    
    private void a(final String s, final Color color) {
        final Graphics canvas = this.U.getCanvas();
        canvas.setFont(new Font("Arial", 1, 14));
        canvas.setColor(color);
        canvas.drawString(s, 20, 80);
        this.U.finishCanvas();
    }
    
    private int if(final int n, final int n2, final int n3) {
        return n2 + (n - n2 + 1) % (n3 - n2 + 1);
    }
    
    private int a(final int n, final int n2, final int n3) {
        return n3 - (n3 - n + 1) % (n3 - n2 + 1);
    }
}
