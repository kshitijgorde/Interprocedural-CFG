// 
// Decompiled by Procyon v0.5.30
// 

package br.com.bb.aapj.bbteclado;

import com.ms.com.StdCOMClassObject;
import java.awt.event.TextEvent;
import java.awt.event.ComponentEvent;
import java.awt.event.FocusEvent;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import netscape.javascript.JSObject;
import java.awt.image.ImageObserver;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ByteArrayInputStream;
import java.awt.Label;
import java.awt.Component;
import java.awt.Panel;
import java.awt.LayoutManager;
import java.awt.image.ImageProducer;
import java.awt.image.ImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.Toolkit;
import java.awt.TextField;
import java.awt.Font;
import java.util.Random;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.MediaTracker;
import java.awt.Image;
import java.awt.event.TextListener;
import java.awt.event.ComponentListener;
import java.awt.event.FocusListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.applet.Applet;

public class CampoTeclado extends Applet implements MouseListener, MouseMotionListener, FocusListener, ComponentListener, TextListener, Runnable
{
    private boolean a;
    private Image b;
    private static Image[][] c;
    private static Image[][] d;
    private String e;
    private static Image f;
    private static Image g;
    private static Image h;
    private static Image i;
    private boolean j;
    private boolean k;
    private boolean l;
    private boolean m;
    private MediaTracker n;
    private Image o;
    private Graphics p;
    private Dimension q;
    private Color r;
    private Color s;
    private Color t;
    private Color u;
    private Color v;
    private Thread w;
    private boolean x;
    private boolean y;
    private int z;
    private int aa;
    private int ab;
    private Random ac;
    private Random ad;
    private int[] ae;
    private int af;
    private final int[] ag;
    private final int[] ah;
    private final int[] ai;
    private final int[] aj;
    private final int ak = 301;
    private final int al = 70;
    private final int am = 15;
    private final int an = 18;
    private final Color ao;
    private Color ap;
    private final int aq = 5;
    private final int ar = 70;
    private final int as = 5;
    private final int at = 20;
    private final int au = 110;
    private final int av = 20;
    private final Color aw;
    private final Font ax;
    private String ay;
    private String az;
    private String a0;
    private String a1;
    private final Color a2;
    private final Color a3;
    private final Font a4;
    private final Font a5;
    private TextField a6;
    private final int a7 = 190;
    private final int a8 = 95;
    private final int a9 = 306;
    private final int ba = 95;
    private int bb;
    private final Font bc;
    private final Color bd;
    private final int be = 120;
    private final int bf = 70;
    private String bg;
    private int bh;
    private boolean bi;
    private final byte[] bj;
    private String[] bk;
    private boolean bl;
    private String bm;
    private static String bn;
    public boolean bo;
    
    static {
        CampoTeclado.c = new Image[2][36];
        CampoTeclado.d = new Image[2][36];
        final e e = new e();
        try {
            for (int i = 0; i < 10; ++i) {
                final byte[] a = e.a("d" + i);
                CampoTeclado.c[0][i] = Toolkit.getDefaultToolkit().createImage(a);
                CampoTeclado.d[0][i] = Toolkit.getDefaultToolkit().createImage(a);
            }
            for (int j = 10; j < 36; ++j) {
                CampoTeclado.c[0][j] = Toolkit.getDefaultToolkit().createImage(e.a("d" + j));
            }
            for (int k = 10; k < 36; ++k) {
                CampoTeclado.d[0][k] = Toolkit.getDefaultToolkit().createImage(e.a("d" + k + "CA"));
            }
        }
        catch (Exception ex) {
            CampoTeclado.c = null;
            CampoTeclado.d = null;
        }
        try {
            CampoTeclado.f = Toolkit.getDefaultToolkit().createImage(e.a("caps"));
        }
        catch (Exception ex2) {}
        try {
            CampoTeclado.g = Toolkit.getDefaultToolkit().createImage(e.a("menos"));
        }
        catch (Exception ex3) {}
        try {
            CampoTeclado.h = Toolkit.getDefaultToolkit().createImage(e.a("mais"));
        }
        catch (Exception ex4) {}
        try {
            CampoTeclado.i = Toolkit.getDefaultToolkit().createImage(e.a("voltar"));
        }
        catch (Exception ex5) {}
        CampoTeclado.bn = "nao";
    }
    
    public CampoTeclado() {
        this.a = false;
        this.b = null;
        this.e = "";
        this.j = false;
        this.k = false;
        this.l = false;
        this.m = false;
        this.r = new Color(213, 229, 254);
        this.s = new Color(234, 242, 255);
        this.t = new Color(230, 230, 230);
        this.u = new Color(115, 115, 115);
        this.v = new Color(198, 198, 198);
        this.x = true;
        this.y = true;
        this.ac = new Random();
        this.ad = new Random();
        this.ae = new int[] { -1, -1, -1, -1, -1, -1, -1, -1 };
        this.ag = new int[] { 49, 50, 51, 52, 53, 54, 55, 56, 57, 48, 113, 119, 101, 114, 116, 121, 117, 105, 111, 112, 97, 115, 100, 102, 103, 104, 106, 107, 108, 122, 120, 99, 118, 98, 110, 109 };
        this.ah = new int[] { 49, 50, 51, 52, 53, 54, 55, 56, 57, 48, 81, 87, 69, 82, 84, 89, 85, 73, 79, 80, 65, 83, 68, 70, 71, 72, 74, 75, 76, 90, 88, 67, 86, 66, 78, 77 };
        this.ai = new int[] { 147, 169, 191, 213, 235, 257, 279, 301, 323, 345, 147, 169, 191, 213, 235, 257, 279, 301, 323, 345, 147, 169, 191, 213, 235, 257, 279, 301, 323, 147, 169, 191, 213, 235, 257, 279 };
        this.aj = new int[] { 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 50, 50, 50, 50, 50, 50, 50, 50, 50, 70, 70, 70, 70, 70, 70, 70 };
        this.ao = new Color(189, 186, 189);
        this.ap = new Color(230, 230, 230);
        this.aw = new Color(0, 0, 0);
        this.ax = new Font("Arial", 1, 11);
        this.a2 = new Color(0, 0, 0);
        this.a3 = new Color(0, 0, 0);
        this.a4 = new Font("Arial", 1, 18);
        this.a5 = new Font("Arial", 1, 14);
        this.a6 = new TextField();
        this.bb = 0;
        this.bc = new Font("Arial", 0, 10);
        this.bd = new Color(0, 0, 153);
        this.bh = 0;
        this.bi = false;
        this.bj = c.a("rO0ABXVyABNbTGphdmEubGFuZy5TdHJpbmc7rdJW5+kde0cCAAB4cAAAADJ0AAhKQ0NFSUdIRnQACElFRkZHQ0NBdAAIQUFKQkNKSEh0AAhESEpJREpDRHQACElCQkFEREZFdAAISkpDRUJESkN0AAhGQ0hHQUpKQnQACEVCSkdERkFEdAAIQkZGREJCSkJ0AAhDSUdFSEpGR3QACEFFQkJDR0lJdAAIREhJQkhKRkd0AAhJRENCR0JESHQACEZFSElDSkNDdAAIR0pKREZGSUJ0AAhCSkZFQkRHSXQACEpISEJKR0FCdAAISUhHREVHRUJ0AAhHQ0NGQkJCSXQACEZJREdHRUdKdAAIRURERkhFRkF0AAhFRkNGQUJDRnQACEVGR0VJQ0FEdAAISEhGREdDRUd0AAhJQkNHRkFGR3QACEFEQkdKSERIdAAIR0hIREFJSkl0AAhFSUNER0hIRnQACENISUZIRkFGdAAISEJCQklGSEF0AAhJRERFSERDQXQACEVCSkVIQ0pIdAAIRkVDSkZJQ0Z0AAhEQkZERkVJQ3QACEVIRUJHR0NFdAAIREFDQ0dCREN0AAhKSUdHRUNBSXQACEhCQklBQkpGdAAIR0hHREZJQkh0AAhFRkJJSEdISXQACEdGRUVKQUJEdAAIQUlIQURBSEN0AAhFR0pGQkFEQXQACEJIRUFERUJGdAAISEVIR0hKQ0d0AAhFREFESkFBSnQACEZHRkJHQUpEdAAIQ0FER0RCREJ0AAhHSURFSkFBQ3QACENBQ0RISkdI");
        this.bo = true;
    }
    
    public void init() {
        final String parameter = this.getParameter("corFundo");
        if (parameter != null && parameter.length() > 0) {
            this.r = Color.decode(parameter);
        }
        final String parameter2 = this.getParameter("corFundoSenhaTeclado");
        if (parameter2 != null && parameter2.length() > 0) {
            this.s = Color.decode(parameter2);
        }
        final String parameter3 = this.getParameter("corFundoTeclado");
        if (parameter3 != null && parameter3.length() > 0) {
            this.t = Color.decode(parameter3);
        }
        final String parameter4 = this.getParameter("corFundoCampos");
        if (parameter4 != null && parameter4.length() > 0) {
            this.ap = Color.decode(parameter4);
        }
        final String parameter5 = this.getParameter("esconderCampoChave");
        if (parameter5 != null && parameter5.equals("true")) {
            this.m = true;
        }
        final String parameter6 = this.getParameter("tipoLegendaChave");
        if (parameter6 == null || parameter6.equals("acesso")) {
            if (!this.m) {
                this.a0 = "Chave de Acesso";
                this.a1 = "";
            }
            else {
                this.a0 = "";
                this.a1 = "";
            }
        }
        else if (parameter6.equals("definido")) {
            this.a0 = this.getParameter("legendaChave1");
            if (this.a0 == null) {
                this.a0 = "";
            }
            this.a1 = this.getParameter("legendaChave2");
            if (this.a1 == null) {
                this.a1 = "";
            }
        }
        else {
            this.a0 = "";
            this.a1 = "";
        }
        this.b = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(CampoTeclado.c[0][0].getSource(), new FiltroCor(255)));
        this.setLayout(null);
        this.a6.setBackground(this.ap);
        this.a6.setFont(this.a5);
        this.a6.addTextListener(this);
        this.a6.addFocusListener(this);
        this.a6.setBounds(-3, -3, 115, 25);
        if (!this.m) {
            final Panel panel = new Panel();
            panel.setBackground(this.ap);
            panel.setBounds(5, 20, 110, 20);
            panel.setLayout(null);
            panel.add(this.a6);
            this.add(panel);
        }
        final Label label = new Label(this.a0);
        label.setFont(this.ax);
        label.setBackground(this.r);
        label.setForeground(this.aw);
        final Label label2 = new Label(this.a1);
        label2.setFont(this.ax);
        label2.setBackground(this.r);
        label2.setForeground(this.aw);
        if (this.m) {
            if (this.a1.equals("")) {
                label.setBounds(5, 20, 128, 20);
            }
            else {
                label.setBounds(5, 5, 128, 20);
                label2.setBounds(5, 20, 128, 20);
            }
        }
        else {
            label.setBounds(8, -1, 128, 20);
            this.a1 = "";
        }
        this.add(label);
        if (!this.a1.equals("")) {
            this.add(label2);
        }
        final String parameter7 = this.getParameter("tipoLegenda");
        if (parameter7 == null || parameter7.equals("acesso")) {
            this.ay = "";
            this.az = "Senha de Acesso";
        }
        else if (parameter7.equals("definido")) {
            this.ay = this.getParameter("legenda1");
            if (this.ay == null) {
                this.ay = "";
            }
            this.az = this.getParameter("legenda2");
            if (this.az == null) {
                this.az = "";
            }
        }
        final String parameter8 = this.getParameter("chave");
        if (parameter8 != null) {
            this.e = parameter8;
        }
        this.a6.setText(this.e);
        this.bg = this.getParameter("codTrans");
        final String parameter9 = this.getParameter("valorContr");
        if (parameter9 == null) {
            this.bb = 0;
        }
        else {
            try {
                this.bb = Integer.parseInt(parameter9);
            }
            catch (NumberFormatException ex) {
                this.bb = 0;
            }
        }
        this.bh = this.bb;
        this.n = new MediaTracker(this);
        this.a(this.bb);
        this.z = this.getSize().width;
        this.aa = this.getSize().height;
        for (int i = 0; i < 10; ++i) {
            this.n.addImage(CampoTeclado.c[0][i], 0);
            this.n.addImage(CampoTeclado.d[0][i], 0);
        }
        for (int j = 10; j < 36; ++j) {
            this.n.addImage(CampoTeclado.c[0][j], 0);
        }
        for (int k = 10; k < 36; ++k) {
            this.n.addImage(CampoTeclado.d[0][k], 0);
        }
        this.n.addImage(CampoTeclado.f, 1);
        this.n.addImage(CampoTeclado.g, 1);
        this.n.addImage(CampoTeclado.h, 1);
        this.n.addImage(CampoTeclado.i, 2);
        try {
            this.n.waitForAll();
        }
        catch (Exception ex2) {}
        for (int l = 1; l < 2; ++l) {
            for (int n = 0; n < 36; ++n) {
                this.n.addImage(CampoTeclado.c[l][n], 2);
                this.n.addImage(CampoTeclado.d[l][n], 2);
            }
        }
        try {
            this.n.waitForAll();
        }
        catch (Exception ex3) {}
        this.ab = this.b(10);
        this.addMouseMotionListener(this);
        final String property = System.getProperty("java.vendor");
        final String property2 = System.getProperty("java.version");
        if ((property != null && property.toLowerCase().indexOf("microsoft") != -1) || (property2 != null && (property2.indexOf("1.3") != -1 || property2.indexOf("1.2") != -1 || property2.indexOf("1.1") != -1))) {
            this.addMouseListener((MouseListener)new CampoTeclado.b(this));
        }
        else {
            this.addMouseListener(this);
        }
        this.addFocusListener(this);
        this.addComponentListener(this);
        try {
            final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(this.bj);
            final ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            this.bk = (String[])objectInputStream.readObject();
            objectInputStream.close();
            byteArrayInputStream.close();
        }
        catch (Exception ex4) {
            return;
        }
        this.repaint();
    }
    
    private void a(final int n) {
        final FiltroCor filtroCor = new FiltroCor(25 + n * 50);
        for (int i = 0; i < 36; ++i) {
            this.n.removeImage(CampoTeclado.c[1][i], 2);
            this.n.removeImage(CampoTeclado.d[1][i], 2);
            CampoTeclado.c[1][i] = null;
            CampoTeclado.d[1][i] = null;
        }
        if (n == 5) {
            final Image image = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(CampoTeclado.c[0][0].getSource(), new FiltroCor(255)));
            for (int j = 0; j < 36; ++j) {
                CampoTeclado.c[1][j] = image;
                CampoTeclado.d[1][j] = image;
                this.n.addImage(CampoTeclado.c[1][j], 2);
                this.n.addImage(CampoTeclado.d[1][j], 2);
            }
        }
        else {
            for (int k = 0; k < 36; ++k) {
                CampoTeclado.c[1][k] = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(CampoTeclado.c[0][k].getSource(), filtroCor));
                CampoTeclado.d[1][k] = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(CampoTeclado.d[0][k].getSource(), filtroCor));
                this.n.addImage(CampoTeclado.c[1][k], 2);
                this.n.addImage(CampoTeclado.d[1][k], 2);
            }
        }
        try {
            this.n.waitForAll();
        }
        catch (Exception ex) {}
    }
    
    public void start() {
    }
    
    public void stop() {
        this.w = null;
    }
    
    public void paint(final Graphics graphics) {
        if (this.o != null) {
            graphics.drawImage(this.o, 0, 0, null);
        }
        this.a(graphics);
        if (this.bo) {
            this.bo = false;
            this.x = true;
            this.a6.requestFocus();
        }
        this.b(graphics);
    }
    
    public void update(final Graphics graphics) {
        final Dimension size = this.getSize();
        if (this.k || this.j || this.p == null || size.width != this.q.width || size.height != this.q.height) {
            this.j = false;
            this.k = false;
            this.q = size;
            if (this.o == null || this.o.getWidth(this) != size.width || this.o.getHeight(this) != size.height) {
                this.o = this.createImage(size.width, size.height);
            }
            if (this.o != null) {
                this.p = this.o.getGraphics();
                if (this.p != null) {
                    this.p.setColor(this.r);
                    this.p.fillRect(0, 0, this.z, this.aa);
                    this.p.setColor(this.s);
                    this.p.fillRect(2, 52, 135, this.aa - 2 - 52);
                    this.p.fillRect(137, this.aj[0] - 9, this.ai[9] + 15 + 20 - 137, this.aa - 1 - (this.aj[0] - 9));
                    this.p.setColor(this.t);
                    this.p.fillRect(144, this.aj[0] - 4, this.ai[9] + 15 + 9 - 144, 93 - (this.aj[0] - 4));
                    this.p.setColor(this.u);
                    this.p.drawLine(1, this.aa - 1, 1, 51);
                    this.p.drawLine(1, 51, 135, 51);
                    this.p.drawLine(136, 51, 136, this.aj[0] - 10);
                    this.p.drawLine(136, this.aj[0] - 10, this.ai[9] + 15 + 20, this.aj[0] - 10);
                    this.p.drawLine(this.ai[9] + 15 + 20, this.aj[0] - 10, this.ai[9] + 15 + 20, this.aa - 1);
                    this.p.drawLine(this.ai[9] + 15 + 20, this.aa - 1, 1, this.aa - 1);
                    this.p.setColor(this.v);
                    this.p.drawLine(143, this.aj[0] - 5, this.ai[9] + 15 + 10, this.aj[0] - 5);
                    this.p.drawLine(this.ai[9] + 15 + 10, this.aj[0] - 5, this.ai[9] + 15 + 10, 93);
                    this.p.drawLine(this.ai[9] + 15 + 10, 93, 143, 93);
                    this.p.drawLine(143, 93, 143, this.aj[0] - 5);
                    if (CampoTeclado.c != null) {
                        int ab = this.ab;
                        for (int i = 0; i < 10; ++i) {
                            this.p.drawImage(this.a ? this.b : CampoTeclado.c[1][ab], this.ai[i], this.aj[i], null);
                            if (++ab == 10) {
                                ab = 0;
                            }
                        }
                    }
                    if (!this.l) {
                        if (CampoTeclado.c != null) {
                            for (int j = 10; j < 36; ++j) {
                                this.p.drawImage(this.a ? this.b : CampoTeclado.c[1][j], this.ai[j], this.aj[j], null);
                            }
                        }
                    }
                    else if (CampoTeclado.d != null) {
                        for (int k = 10; k < 36; ++k) {
                            this.p.drawImage(this.a ? this.b : CampoTeclado.d[1][k], this.ai[k], this.aj[k], null);
                        }
                    }
                    this.p.setColor(this.ao);
                    this.p.drawRect(5, 70, 110, 20);
                    this.p.setColor(this.ap);
                    this.p.fillRect(6, 71, 108, 18);
                    this.p.setColor(this.aw);
                    this.p.setFont(this.ax);
                    this.p.drawString(this.ay, 8, 54);
                    this.p.drawString(this.az, 8, 66);
                    if (!this.m) {
                        this.p.setColor(this.ao);
                        this.p.fillRect(4, 19, 112, 22);
                    }
                    this.p.drawImage(CampoTeclado.f, 301, 70, null);
                    this.p.drawImage(CampoTeclado.g, 190, 95, null);
                    this.p.drawImage(CampoTeclado.h, 306, 95, null);
                    this.p.setColor(this.bd);
                    this.p.setFont(this.bc);
                    this.p.drawString("...  contraste  ...", 221, 106);
                    this.p.drawImage(CampoTeclado.i, 120, 70, null);
                }
            }
        }
        if (this.o != null) {
            graphics.drawImage(this.o, 0, 0, null);
        }
        this.a(graphics);
        this.b(graphics);
    }
    
    public void setFocus() {
        this.a6.requestFocus();
        this.repaint();
    }
    
    private boolean a(final int n, final int n2) {
        return n >= 301 && n2 >= 70 && n <= 366 && n2 <= 85;
    }
    
    private boolean b(final int n, final int n2) {
        return (n >= 190 && n2 >= 95 && n <= 207 && n2 <= 110) || (n >= 306 && n2 >= 95 && n <= 323 && n2 <= 110);
    }
    
    private int c(final int n, final int n2) {
        int n3 = 0;
        if (n >= 190 && n2 >= 95 && n <= 207 && n2 <= 110) {
            n3 = 1;
        }
        if (n >= 306 && n2 >= 95 && n <= 323 && n2 <= 110) {
            n3 = 2;
        }
        return n3;
    }
    
    private boolean d(final int n, final int n2) {
        for (int i = 0; i < 36; ++i) {
            if (n >= this.ai[i] && n <= this.ai[i] + 16 && n2 >= this.aj[i] && n2 <= this.aj[i] + 18) {
                return true;
            }
        }
        return false;
    }
    
    private boolean e(final int n, final int n2) {
        return n >= 5 && n <= 115 && n2 >= 70 && n2 <= 90;
    }
    
    private boolean f(final int n, final int n2) {
        return n >= 120 && n <= 135 && n2 >= 70 && n2 <= 88;
    }
    
    private int g(final int n, final int n2) {
        for (int i = 0; i < 36; ++i) {
            if (n >= this.ai[i] && n <= this.ai[i] + 16 && n2 >= this.aj[i] && n2 <= this.aj[i] + 18) {
                int n4;
                if (i < 10) {
                    int n3 = i + this.ab;
                    if (n3 >= 10) {
                        n3 -= 10;
                    }
                    n4 = this.ag[n3];
                }
                else if (this.l) {
                    n4 = this.ah[i];
                }
                else {
                    n4 = this.ag[i];
                }
                return n4 * (this.af + 1);
            }
        }
        return -1;
    }
    
    private String a() {
        String string = "";
        for (int i = 0; i < this.af; ++i) {
            string = String.valueOf(string) + "*";
        }
        return string;
    }
    
    private void a(final Graphics graphics) {
        graphics.setColor(this.a3);
        graphics.setFont(this.a4);
        graphics.drawString(this.a(), 9, 89);
    }
    
    private void b(final Graphics graphics) {
        if (this.x && !this.y) {
            graphics.setColor(this.a2);
            final int n = 9 + this.af * 7;
            graphics.drawLine(n, 87, n, 73);
        }
    }
    
    private int b(final int n) {
        this.ac.setSeed(this.ad.nextLong());
        return (int)(this.ac.nextDouble() * n);
    }
    
    public String getSenha() {
        String s = "";
        final int[] ae = this.ae;
        this.bl = true;
        if (this.bl) {
            final int b = this.b(50);
            final int[] a = this.a(this.ae, this.bk[b]);
            this.bm = new StringBuffer().append(b).toString();
            if (this.bg == null || !this.bg.equals("508")) {}
            for (int i = 0; i < this.af; ++i) {
                s = String.valueOf(s) + this.c(a[i]);
            }
        }
        else {
            for (int j = 0; j < this.af; ++j) {
                s = String.valueOf(s) + ae[j] / (j + 1);
            }
        }
        return s;
    }
    
    private String c(final int n) {
        String s = Integer.toHexString(n);
        if (s.length() == 1) {
            s = "0" + s;
        }
        if (s.length() > 2) {
            s = s.substring(s.length() - 2, s.length());
        }
        return s;
    }
    
    public String getNumCod() {
        return this.bm;
    }
    
    public String appletEstaCarregada() {
        return CampoTeclado.bn;
    }
    
    public void showCamposLogin() {
        try {
            JSObject.getWindow((Applet)this).eval("javascript:showCamposLogin();");
        }
        catch (Exception ex) {}
    }
    
    private int[] a(final int[] array, final String s) {
        for (int i = 0; i < array.length; ++i) {
            int a = array[i];
            if (a != -1) {
                a = this.a(a, s.charAt(i), i);
            }
            array[i] = a;
        }
        return array;
    }
    
    private int a(int n, final char c, final int n2) {
        n /= n2 + 1;
        n = (++n - n2 ^ c);
        return n;
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        if (mouseEvent.getSource() != this) {
            return;
        }
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        if (this.d(x, y)) {
            this.a6.setFocusable(false);
            final int g = this.g(x, y);
            if (g != -1 && this.af < 8) {
                this.ae[this.af] = g;
                ++this.af;
                this.repaint();
            }
            this.bi = true;
            this.a = true;
            this.j = true;
            this.a6.setFocusable(true);
            this.repaint();
        }
        else if (this.b(x, y)) {
            final int c = this.c(x, y);
            if (c == 1 && this.bb < 4) {
                this.a(this.bb = ((this.bb < 4) ? (this.bb + 1) : 4));
                this.bh = this.bb;
                this.j = true;
                this.repaint();
            }
            else if (c == 2 && this.bb > 0) {
                this.a(this.bb = ((this.bb > 0) ? (this.bb - 1) : 0));
                this.bh = this.bb;
                this.j = true;
                this.repaint();
            }
        }
        else if (this.f(x, y)) {
            this.a6.setFocusable(false);
            this.y = false;
            if (this.af > 0) {
                --this.af;
                this.ae[this.af] = -1;
                this.repaint();
            }
            this.a6.setFocusable(true);
        }
        else if (this.a(x, y)) {
            this.k = true;
            this.l = !this.l;
            this.repaint();
        }
        else if (this.e(x, y)) {
            this.a6.setFocusable(false);
            this.x = true;
            this.y = false;
            this.repaint();
            this.a6.setFocusable(true);
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        if (this.bi) {
            new Thread() {
                public void run() {
                    try {
                        Thread.sleep(350L);
                    }
                    catch (InterruptedException ex) {}
                    CampoTeclado.a(CampoTeclado.this, false);
                    CampoTeclado.b(CampoTeclado.this, true);
                    CampoTeclado.this.repaint();
                }
            }.start();
        }
        if (mouseEvent.getSource() == this.a6) {
            this.a6.setFocusable(true);
            this.a6.requestFocus();
            this.y = true;
            this.x = true;
        }
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        if (mouseEvent.getSource() != this) {
            return;
        }
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        if (this.d(x, y) || this.b(x, y) || this.f(x, y)) {
            this.setCursor(new Cursor(12));
        }
        else if (this.e(x, y)) {
            this.setCursor(new Cursor(2));
        }
        else {
            this.setCursor(new Cursor(0));
        }
    }
    
    public void focusGained(final FocusEvent focusEvent) {
        if (focusEvent.getSource() == this.a6) {
            this.x = true;
            this.y = true;
            this.repaint();
        }
        else {
            this.x = true;
            this.y = false;
            this.repaint();
        }
    }
    
    public void focusLost(final FocusEvent focusEvent) {
        if (focusEvent.getSource() == this.a6) {
            this.y = false;
            return;
        }
        this.x = false;
        this.repaint();
    }
    
    public void componentHidden(final ComponentEvent componentEvent) {
    }
    
    public void componentMoved(final ComponentEvent componentEvent) {
    }
    
    public void componentResized(final ComponentEvent componentEvent) {
    }
    
    public void componentShown(final ComponentEvent componentEvent) {
        this.repaint();
    }
    
    public void run() {
        while (this.w == Thread.currentThread()) {
            try {
                Thread.sleep(3000L);
            }
            catch (InterruptedException ex) {}
            this.repaint();
        }
    }
    
    public String getChave() {
        return this.a6.getText();
    }
    
    public void textValueChanged(final TextEvent textEvent) {
        final String text = this.a6.getText();
        final int length = text.length();
        final int caretPosition = this.a6.getCaretPosition();
        if (length == 1 && !Character.isLetter(text.charAt(0))) {
            this.a6.setText("");
            this.a6.setCaretPosition(1);
        }
        if (length > 1 && length <= 8 && !Character.isDigit(text.charAt(length - 1))) {
            this.a6.setText(text.substring(0, length - 1));
            this.a6.setCaretPosition(caretPosition);
        }
        else if (length > 8) {
            this.a6.setText(text.substring(0, 8));
            this.a6.setCaretPosition(caretPosition);
        }
    }
    
    public static /* synthetic */ void a(final CampoTeclado campoTeclado, final boolean a) {
        campoTeclado.a = a;
    }
    
    public static /* synthetic */ void b(final CampoTeclado campoTeclado, final boolean j) {
        campoTeclado.j = j;
    }
    
    private class COMClassObject extends StdCOMClassObject
    {
    }
}
