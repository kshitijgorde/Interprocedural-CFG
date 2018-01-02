import java.util.Date;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.event.ItemListener;
import java.awt.event.ActionListener;
import java.awt.Checkbox;
import java.awt.Button;
import java.awt.GridLayout;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.awt.Image;
import java.io.IOException;
import java.awt.Point;
import java.awt.event.MouseListener;
import java.awt.event.KeyListener;
import java.awt.FontMetrics;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Component;
import java.net.URLEncoder;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.Panel;
import java.awt.ScrollPane;
import com.cc.applet.D;
import com.cc.gui.F;
import java.awt.Rectangle;
import com.cc.applet.B;
import com.cc.D.C;
import com.cc.applet.E;
import com.cc.applet.A;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Grid_int extends Applet implements Runnable
{
    private static final int R = 25;
    private static final boolean B = false;
    private static final boolean b = false;
    private static final int G = 11;
    private static final int Z = 25;
    private String T;
    private A W;
    private long Q;
    private E[] U;
    private static final String M = " Software © 2011 ";
    private int D;
    private com.cc.C.A _;
    private com.cc.B.A A;
    private C a;
    private B V;
    private A S;
    public A X;
    private A I;
    private Rectangle H;
    private Rectangle F;
    public com.cc.applet.C J;
    private F L;
    private F P;
    private int K;
    private D C;
    public ScrollPane Y;
    private com.cc.gui.B[] O;
    private Panel N;
    private Thread E;
    
    public Grid_int() {
        this.T = null;
        this.W = null;
        this.X = null;
        this.Y = null;
        this.N = null;
        this.E = null;
    }
    
    public void init() {
        this.setLayout(null);
        final Font font = new Font("SansSerif", 0, 11);
        final FontMetrics fontMetrics = Toolkit.getDefaultToolkit().getFontMetrics(font);
        this.A();
        if (this.T == null) {
            this.C();
            this.setBackground(this._.\u00c4);
            if (this.getParameter("EDU") == null) {
                (this.S = new A(" Software © 2011 ", "crossword-compiler.com")).A("http://www.crossword-compiler.com/cgi-bin/applet8.pl?" + URLEncoder.encode(this.getDocumentBase().toString()));
            }
            else {
                this.S = new A(" Software © 2011 crossword-compiler.com", "");
            }
            this.S.setFont(font);
            this.S.W = 11 - fontMetrics.getDescent() + 2;
            this.S.H = fontMetrics.stringWidth(this.S.U);
            if (this._.Q != null) {
                (this.L = new F(this._.Q, new Font("SansSerif", 0, this._.Z), this.getBounds().width, false, this)).C(true);
                this.L.B(true);
                this.K = this.L.H().height;
            }
            if (this._.z != null) {
                (this.P = new F(this._.z, this.S.getFont(), this.getBounds().width, false, this)).C(true);
            }
            int n = 55;
            final int n2 = 4;
            if (this._.T) {
                n = 75;
            }
            if (this._.u) {
                n += 25;
            }
            this.D = ((this._.H.U() == 0 || this._.¤) ? 0 : (n + n2 * 2));
            this.I();
            this.J = new com.cc.applet.C(this, this.V, this.A, this._);
            this.D();
            this.O = new com.cc.gui.B[this.U.length];
            for (int i = 0; i < this.U.length; ++i) {
                this.O[i] = this.U[i].C;
            }
            this.J.A(this.O);
            if (this.A.K() != null) {
                this.F();
            }
            if (this.A.Y == null) {
                this.A.G = false;
            }
            if (this.A.G) {
                this.C = new D(this.A, this.J);
                Dimension preferredSize = new Dimension(0, 0);
                for (int j = 0; j < 20; ++j) {
                    this.C.setFont(new Font("SansSerif", 1, this.V.G() - j));
                    preferredSize = this.C.getPreferredSize();
                    if (preferredSize.width <= this.H.width) {
                        break;
                    }
                    if (this.V.G() - j < 6) {
                        break;
                    }
                }
                this.C.setForeground(this.getForeground());
                int n3 = this.H.x + (this.H.width - preferredSize.width) / 2;
                if (n3 + preferredSize.width > this.getSize().width) {
                    n3 = (this.getSize().width - preferredSize.width) / 2;
                }
                this.C.setBounds(n3, this.H.y + this.H.height + 2, preferredSize.width, preferredSize.height);
                this.add(this.C);
            }
            if (this._.\u00c2) {
                (this.X = new A()).setName("TIMER");
                Font font2 = font;
                FontMetrics fontMetrics2 = fontMetrics;
                if (this._.H.U() != 0) {
                    font2 = new Font("SansSerif", 1, 13);
                    fontMetrics2 = this.X.getFontMetrics(font2);
                }
                this.X.setFont(font2);
                this.X.W = this.S.W + ((this._.H.U() != 0) ? 2 : 0);
                this.X.setSize(Math.min(n, fontMetrics2.stringWidth("10:00:00")), 13 + ((this._.H.U() != 0) ? 4 : 0));
                this.X.A(this._.F);
                this.X.J = this._.\u00c3;
            }
            this.J();
            this.E();
            if (this.getSize().height < this.S.getBounds().y + this.S.getBounds().height && System.getProperty("os.version").compareTo("4.2") > 0) {
                this.T = "Wrong size (check your web browser is not zoomed in/out and re-load?)";
                this.removeAll();
            }
            if (this.T == null) {
                if (this._.o != 0) {
                    (this.Y = new ScrollPane(0)).add(this.V);
                    this.Y.getVAdjustable().setUnitIncrement(this.V.C());
                    this.Y.getHAdjustable().setUnitIncrement(this.V.C());
                    this.Y.setSize(this._.o, this._._);
                    this.add(this.Y);
                }
                else {
                    this.add(this.V);
                }
            }
            if (!this._.u) {
                this.A(25, n, n2, this.K + (this.A.F ? this.A.L : 0));
            }
            else {
                this.B(25, n, n2, this.K + (this.A.F ? this.A.L : 0));
            }
            if (this.X != null) {
                this.add(this.X);
            }
            this.A(this);
            this.V.requestFocus();
            if (this._.\u00c9) {
                this.V.E();
            }
            if (this.X != null && this._.V) {
                this.X.A();
            }
            if (this.getBackground().getGreen() < 130 && this.getBackground().getRed() < 130 && this.getBackground().getBlue() < 130) {
                if (this.L != null) {
                    this.L.A(Color.white);
                }
                if (this.P != null) {
                    this.P.A(Color.white);
                }
            }
            this.V.repaint();
        }
        (this.E = new Thread(this)).start();
    }
    
    private void A(final Container container) {
        container.addKeyListener(this.J);
        container.addMouseListener(this.J);
        final Component[] components = container.getComponents();
        for (int i = 0; i < components.length; ++i) {
            if (components[i] instanceof Container) {
                this.A((Container)components[i]);
            }
            else {
                components[i].addKeyListener(this.J);
                components[i].addMouseListener(this.J);
            }
        }
    }
    
    private void F() {
        final B b = new B(this.A, this.a, this._, true);
        final int n = 3;
        final int width = b.getPreferredSize().width;
        b.setBounds((this.H.width - width) / 2 + this.H.x, n + this.H.y + this.H.height, width, b.getPreferredSize().height);
        this.add(b);
        final Rectangle h = this.H;
        h.height += n + b.getPreferredSize().height;
    }
    
    private void J() {
        int max = this.H.x + this.H.width;
        if (this.a.A() > 0) {
            max = Math.max(this.F.x + this.F.width, max);
        }
        int n = this.H.y + this.H.height;
        int n2 = this.H.x + this.H.width - this.S.H;
        if (this.A.G) {
            n = Math.max(n, this.C.getBounds().y + this.C.getBounds().height);
            n2 = this.C.A() + ((this.P != null) ? this.P.H().width : 0) / 2 - this.S.H / 2;
        }
        if (this.a.A() > 0) {
            n = Math.max(n, this.F.y + this.F.height);
        }
        this.S.setBounds(n2, n + 2, this.S.H, 13);
        this.add(this.S);
        if (this.X != null && this.X.getLocation().equals(new Point(0, 0))) {
            int n3 = this.H.x + this.H.width;
            if (this.a.A() > 0) {
                n3 = this.F.x + this.F.width;
            }
            this.X.setLocation(n3 - this.X.getSize().width, n + 2);
        }
        if (this.L != null) {
            if (this.H.width >= this.L.H().width) {
                this.L.B(this.H.x, 0, this.H.width, this.L.H().height);
                if (!this._.I) {
                    this.L.A(0.5f);
                }
            }
            else {
                int n4 = this.H.x;
                int width = this.H.width;
                if (this.a.A() > 0) {
                    if (this._.L == 1) {
                        n4 = this.F.x;
                    }
                    width = this.getBounds().width - n4;
                }
                else if (this._.L == 0) {
                    width = this.getBounds().width - n4;
                }
                this.L.E(width);
                this.L.B(n4, 0, width, this.K);
            }
        }
        if (this.P != null) {
            int n5 = this.H.x;
            if (this.a.A() > 0) {
                n5 = Math.min(n5, this.F.x);
            }
            if (this.C != null) {
                n5 = this.S.getBounds().x - this.P.H().width;
            }
            this.P.D(true);
            this.P.B(0);
            final int n6 = this.P.H().width + 2;
            final int n7 = (this.X != null && (this._.¤ || this._.H.U() == 0)) ? (this.X.getSize().width + 5) : 0;
            if (n5 + n6 > this.S.getBounds().x && max - this.S.H - n7 <= this.getSize().width) {
                this.S.setBounds(max - this.S.H - n7, n + 2, this.S.H, 13);
            }
            this.P.B(n5, this.S.getBounds().y, n6, this.P.H().height);
        }
    }
    
    private void E() {
        String s = "";
        int n = 0;
        try {
            while (true) {
                final String string = Integer.toString(n);
                String s2 = this.getParameter(s + "PICTURE" + string);
                if (s2 == null && s == "") {
                    s = "S";
                    n = 0;
                    s2 = this.getParameter("SPICTURE0");
                }
                if (s2 == null) {
                    break;
                }
                final A a = new A(this.getImage(this.getDocumentBase(), s2));
                final String parameter = this.getParameter(s + "PICTURERECT" + string);
                final int int1 = Integer.parseInt(parameter.substring(0, 1));
                a.setBounds(Integer.parseInt(parameter.substring(1, 5)) + (((int1 & 0x1) != 0x0) ? this.V.getBounds().x : 0), Integer.parseInt(parameter.substring(5, 9)) + (((int1 & 0x1) != 0x0) ? this.V.getBounds().y : 0), Integer.parseInt(parameter.substring(9, 13)), Integer.parseInt(parameter.substring(13, 17)));
                if ((int1 & 0x1) != 0x0) {
                    a.getBounds().translate(this.V.getBounds().x, this.V.getBounds().y);
                }
                a.A(this.getParameter(s + "PICTUREURL" + string));
                a.S = this.getParameter(s + "PICTUREURLFRAME" + string);
                if (!a.getBounds().intersects(this.S.getBounds())) {
                    this.add(a, ((int1 & 0x2) != 0x0) ? -1 : 0);
                }
                ++n;
            }
            if (this._.g != null) {
                this.I = new A(this._.g);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void C() {
        try {
            this._.H.B(this.A("check.gif"));
            this._.H.F(this.A("reveal_letter.gif"));
            this._.H.H(this.A("reveal_word.gif"));
            this._.H.E(this.A("revert.gif"));
            this._.H.C(this.A("save.gif"));
            this._.H.D(this.A("solution.gif"));
            this._.H.A(this.A("submit.gif"));
            this._.H.G(this.A("pencil.gif"));
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    private Image A(final String s) throws IOException {
        InputStream resourceAsStream;
        byte[] array;
        int n;
        int read;
        for (resourceAsStream = this.getClass().getClassLoader().getResourceAsStream("com/cc/applet/actions/" + s), array = new byte[100000], n = 0; (read = resourceAsStream.read(array, n, 10000)) > -1; n += read) {}
        final byte[] array2 = new byte[n];
        for (int i = 0; i < array2.length; ++i) {
            array2[i] = array[i];
        }
        return Toolkit.getDefaultToolkit().createImage(array2);
    }
    
    private int B() {
        if (this.a.A() > 0 && this._.L == 1) {
            return this._.\u00c7 + this._.K;
        }
        return 0;
    }
    
    private void I() {
        this.V = new B(this.A, this.a, this._, false);
        try {
            this.V.F();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        this.H = new Rectangle(new Point(this.D + this.B(), this.K), this.V.getPreferredSize());
        this.V.setBounds(this.H);
        if (this._.o != 0) {
            this.H.width = this._.o;
            this.H.height = this._._;
        }
    }
    
    private byte[] A(final InputStream inputStream) throws IOException {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final byte[] array = new byte[1000];
        for (int i = inputStream.read(array); i > -1; i = inputStream.read(array)) {
            byteArrayOutputStream.write(array, 0, i);
        }
        return byteArrayOutputStream.toByteArray();
    }
    
    private void A() {
        InputStream k = null;
        try {
            k = this.K();
            final byte[] a = this.A(k);
            final com.cc.A.B b = new com.cc.A.B(a);
            if (b.B()) {
                this._ = new com.cc.C.A();
                this.A = new com.cc.B.A();
                this.a = new C(this.A);
                b.A(this._, this.A, this.a);
            }
            else {
                final com.cc.A.A a2 = com.cc.A.A.A(a, this.getParameter("CHARSET"), this.getParameter("OUTPUTCHARSET"));
                this._ = a2.E;
                this.A = a2.K;
                (this.a = new C(this.A)).A(a2.B);
            }
            this.A.A(this.a);
            this.G();
        }
        catch (IOException ex) {
            this.T = "Cannot read file: " + ex.getMessage();
            ex.printStackTrace();
        }
        finally {
            try {
                if (k != null) {
                    k.close();
                }
            }
            catch (IOException ex2) {
                ex2.printStackTrace();
            }
        }
    }
    
    private void D() {
        final com.cc.D.B[] d = this.a.D();
        this.U = new E[d.length];
        if (d.length == 0) {
            this._.\u00c7 = 0;
            return;
        }
        if (d[0].G) {
            this._.\u00c7 = 0;
        }
        int n = this._.\u00c7;
        int n2 = this.H.height;
        int n3 = this.H.x + this.H.width + this._.K;
        int y = this.H.y;
        int a = this.a.A();
        int a2 = 1;
        if (this._.L == 1) {
            n3 = this.D;
        }
        if (this._.L == 2) {
            n3 = this.D;
            y = this.H.y + this.H.height + this._.K;
            n = this.H.width;
            n2 = this._.\u00c7;
            a = 1;
            a2 = this.a.A();
        }
        this.F = new Rectangle(n3, y, n, n2);
        (this.N = new Panel(new GridLayout(a, a2, 6, 6))).setBounds(this.F.x, this.F.y, this.F.width, this.F.height);
        this.add(this.N);
        for (int i = 0; i < this.U.length; ++i) {
            (this.U[i] = new E(this._, d[i], this, this.A, this.a)).setForeground(this._.\u00cb);
            this.N.add(this.U[i]);
        }
    }
    
    private void A(final int n, final int n2, final int n3, final int n4) {
        final Font font = new Font("SansSerif", 0, 11);
        if (this._.H.U() != 0) {
            final Component[] array = new Component[8];
            if (this._.H.V()) {
                array[0] = new Button(this._.H.O());
                ((Button)array[0]).setActionCommand(String.valueOf(4));
            }
            if (this._.H.J()) {
                array[1] = new Button(this._.H.F());
                ((Button)array[1]).setActionCommand(String.valueOf(3));
            }
            if (this._.H.Q()) {
                array[2] = new Button(this._.H.N());
                ((Button)array[2]).setActionCommand(String.valueOf(2));
            }
            if (this._.H.S()) {
                array[3] = new Button(this._.H.M());
                ((Button)array[3]).setActionCommand(String.valueOf(1));
            }
            if (this._.H.Y()) {
                array[4] = new Button(this._.H.P());
                ((Button)array[4]).setActionCommand(String.valueOf(6));
            }
            if (this._.H.E()) {
                array[5] = new Button(this._.H.K());
                ((Button)array[5]).setActionCommand(String.valueOf(0));
            }
            if (this._.H.H()) {
                array[6] = new Button(this._.H.T());
                ((Button)array[6]).setActionCommand(String.valueOf(5));
            }
            if (this._.H.G()) {
                array[7] = new Checkbox(this._.H.B());
            }
            if (this._.¤) {
                final int max = Math.max(0, Math.min((this.H.width + ((this.U.length == 0) ? 0 : (this._.\u00c7 + this._.K)) - (this._.H.U() + 2) * n2) / this._.H.U(), n2 * 2 / 5));
                final int n5 = this.S.getBounds().y + this.S.getBounds().height - 2 + 10;
                final int n6 = (this.H.width + ((this.U.length == 0) ? 0 : (this._.\u00c7 + this._.K)) - this._.H.U() * (n2 + max) + max) / 2;
                int n7 = 0;
                for (int i = 0; i < 8; ++i) {
                    if (array[i] != null) {
                        array[i].setFont(font);
                        array[i].setBounds(n6 + n7 * (n2 + max), n5, n2, n);
                        this.add(array[i]);
                        if (array[i] instanceof Button) {
                            ((Button)array[i]).addActionListener(this.J);
                        }
                        else {
                            ((Checkbox)array[i]).addItemListener(this.J);
                        }
                        ++n7;
                    }
                }
            }
            else {
                int n8 = 0;
                for (int j = 0; j < 8; ++j) {
                    if (array[j] != null) {
                        array[j].setFont(font);
                        array[j].setBounds(n3, n4 + n8 * (n + 5), n2, n);
                        this.add(array[j]);
                        if (array[j] instanceof Button) {
                            ((Button)array[j]).addActionListener(this.J);
                        }
                        else {
                            ((Checkbox)array[j]).addItemListener(this.J);
                        }
                        ++n8;
                    }
                }
                if (this.X != null) {
                    this.X.setBounds(n3, n4 + n8 * (n + 5) + (n - 11) / 2, n2, 13);
                    this.X.L = true;
                }
            }
        }
    }
    
    private void B(final int n, final int n2, final int n3, final int n4) {
        final Font font = new Font("SansSerif", 0, 11);
        if (this._.H.U() != 0) {
            final A[] array = new A[8];
            if (this._.H.V()) {
                (array[0] = new A(this._.H.O())).C(String.valueOf(4));
                array[0].Y = this._.H.W();
            }
            if (this._.H.J()) {
                (array[1] = new A(this._.H.F())).C(String.valueOf(3));
                array[1].Y = this._.H.Z();
            }
            if (this._.H.Q()) {
                (array[2] = new A(this._.H.N())).C(String.valueOf(2));
                array[2].Y = this._.H.D();
            }
            if (this._.H.S()) {
                (array[3] = new A(this._.H.M())).C(String.valueOf(1));
                array[3].Y = this._.H.X();
            }
            if (this._.H.Y()) {
                (array[4] = new A(this._.H.P())).C(String.valueOf(6));
                array[4].Y = this._.H.L();
            }
            if (this._.H.E()) {
                (array[5] = new A(this._.H.K())).C(String.valueOf(0));
                array[5].Y = this._.H.I();
            }
            if (this._.H.H()) {
                (array[6] = new A(this._.H.T())).C(String.valueOf(5));
                array[6].Y = this._.H.A();
            }
            if (this._.H.G()) {
                (array[7] = new A(this._.H.B())).C(String.valueOf(7));
                array[7].Y = this._.H.C();
            }
            if (this._.¤) {
                final int max = Math.max(Math.min((this.H.width + ((this.U.length == 0) ? 0 : (this._.\u00c7 + this._.K)) - (this._.H.U() + 2) * n2) / this._.H.U(), n2 * 2 / 5), 0);
                final int n5 = this.S.getBounds().y + this.S.getBounds().height + 10;
                final int n6 = (this.H.width + ((this.U.length == 0) ? 0 : (this._.\u00c7 + this._.K)) - this._.H.U() * (n2 + max) + max) / 2;
                int n7 = 0;
                for (int i = 0; i < 8; ++i) {
                    if (array[i] != null) {
                        array[i].setFont(font);
                        array[i].setBounds(n6 + n7 * (n2 + max), n5, n2, n);
                        this.add(array[i]);
                        array[i].A(this.J);
                        ++n7;
                    }
                }
            }
            else {
                int n8 = 0;
                for (int j = 0; j < 8; ++j) {
                    if (array[j] != null) {
                        array[j].setFont(font);
                        array[j].setBounds(n3, n4 + n8 * (n + 5), n2, n);
                        this.add(array[j]);
                        array[j].A(this.J);
                        ++n8;
                    }
                }
                if (this.X != null) {
                    this.X.setBounds(n3, n4 + n8 * (n + 5) + (n - 11) / 2, n2, 13);
                    this.X.L = true;
                }
            }
        }
    }
    
    private InputStream K() throws MalformedURLException, IOException {
        return new URL(this.getDocumentBase(), this.A("DATAFILE", "")).openStream();
    }
    
    public void paint(final Graphics graphics) {
        if (this.T != null) {
            graphics.drawString(this.T, this.D, graphics.getFontMetrics().getHeight());
            if (this.S == null) {
                graphics.drawString("If Win2003+ check extension is MIME type in IIS", this.D, graphics.getFontMetrics().getHeight() * 2);
            }
        }
        else {
            if (this.P != null) {
                this.P.B(graphics.create(this.P.B().x, this.P.B().y, this.P.B().width, this.P.B().height));
            }
            if (this.L != null) {
                this.L.B(graphics.create(this.L.B().x, this.L.B().y, this.L.B().width, this.L.B().height));
            }
        }
    }
    
    public void showProcessedURL(final String s, final String s2, final boolean b, final boolean b2) {
        if (s != null && s.length() != 0) {
            final String a = this.A.A(this._, s, (this.X == null) ? -1 : this.X.O, b2);
            String s3 = s2;
            if (s3 == null) {
                s3 = (b ? "_self" : "_blank");
            }
            try {
                if (b2) {
                    this.showProcessedURL(com.cc.B.F.A(a), s2, b, false);
                }
                else {
                    this.getAppletContext().showDocument(new URL(this.getDocumentBase(), a), s3);
                }
            }
            catch (MalformedURLException ex) {
                ex.printStackTrace();
            }
            catch (IOException ex2) {
                ex2.printStackTrace();
            }
            this.V.requestFocus();
        }
    }
    
    public void ShowCompletionPic() {
        try {
            if (this.I.G != null) {
                this.I.G.waitForAll();
            }
            final int width = this.I.Y.getWidth(this);
            final int height = this.I.Y.getHeight(this);
            this.I.setBounds((this.getBounds().width - width) / 2, (this.getBounds().height - height) / 2, width, height);
            this.I.A(this._.\u00c0);
            this.I.S = this._.¢;
            this.I.addMouseListener(this.J);
            this.add(this.I, 0);
            this.I.repaint();
            this.W = this.I;
            this.Q = new Date().getTime();
        }
        catch (Exception ex) {}
    }
    
    public void ShowCompletionMessage() {
        if (this._.s != null && this._.s.length() > 0) {
            try {
                this.W = new A(this._.s, this._.M);
                final int width = this.W.getSize().width;
                final int height = this.W.getSize().height;
                this.W.setBounds((this.getSize().width - width) / 2, (this.getSize().height - height) / 2, width, height);
                this.W.addMouseListener(this.J);
                this.add(this.W, 0);
                this.W.repaint();
                this.Q = new Date().getTime();
            }
            catch (Exception ex) {}
        }
    }
    
    public String GetSubmit() {
        return this.A.A(this._, false);
    }
    
    public String GetProgress() {
        return this.A.B(0, false);
    }
    
    public String GetProgressLong() {
        return this.A.B(1, false);
    }
    
    public String GetProgressLongMarks() {
        return this.A.B(2, false);
    }
    
    public int GetTime() {
        if (this.X != null) {
            return this.X.O;
        }
        return -1;
    }
    
    private String A(final String s, final String s2) {
        final String parameter = this.getParameter(s);
        if (parameter != null) {
            return parameter;
        }
        return s2;
    }
    
    private void G() {
        this._.\u00c4 = this.A("BACKCOLOR", this._.\u00c4);
        if (this.getBackground().getGreen() < 130 && this.getBackground().getRed() < 130 && this.getBackground().getBlue() < 130 && this._.\u00cb == Color.black) {
            this._.\u00cb = Color.white;
        }
        this._.w = this.A("SELCOLOR", this._.w);
        this._.\u00d1 = this.A("URLCOLOR", this._.\u00d1);
        this._.\u00cc = this.A("CLUEURLTARGET", this._.\u00cc);
        this._.i = this.A("HINTURLTEXT", this._.i);
        this._.\u00cb = this.A("CLUECOLOR", this._.\u00cb);
        this._.X = this.A("SCROLLCOLOR", this._.X);
        this._.x = this.A("FINISHEDURL", this._.x);
        this._.J = this.A("FINISHEDURLFRAME", this._.J);
        final String parameter = this.getParameter("COMPLETIONPICTURE");
        if (parameter != null) {
            this._.g = this.getImage(this.getDocumentBase(), parameter);
        }
        this._.\u00c0 = this.A("COMPLETIONPICTUREURL", this._.\u00c0);
        this._.¢ = this.A("COMPLETIONPICTUREURLFRAME", this._.¢);
        final String parameter2 = this.getParameter("MAXCHEATS");
        if (parameter2 != null) {
            this._.\u00d2 = Integer.valueOf(parameter2);
        }
        final String parameter3 = this.getParameter("SCROLLWIDTH");
        if (parameter3 != null) {
            this._.o = Integer.valueOf(parameter3);
            this._._ = Integer.valueOf(this.getParameter("SCROLLHEIGHT"));
        }
        this._.\u00c8 = this.A("SUBMIT", this._.\u00c8);
        this._.q = this.A("SUBMITMETHOD", this._.q);
        this._.e = this.A("SUBMITFRAME", this._.e);
        this._.h = this.A("SAVE", this._.h);
        this.A.o = this.A("GRIDBACKCOLOR", this.A.o);
        this.A.E = this.A("WRONGCOLOR", this.A.E);
        this.A._ = this.A("GRIDCOLOR", this.A._);
        this.A.w = this.A("BLOCKCOLOR", this.A.w);
        this.A.e = this.A("FONTCOLOR", this.A.e);
        this.A.y = this.A("NUMCOLOR", this.A.y);
        this.A.X = this.A("SELCOLOR", this.A.X);
        this.A.a = this.A("CURCOLOR", this.A.a);
        this.A.s = this.A("REVEALEDCOLOR", this.A.s);
        final String parameter4 = this.getParameter("COMPLETECORRECT");
        if (parameter4 != null) {
            this._.b = parameter4.equals("1");
        }
        final String parameter5 = this.getParameter("FRIENDLYSUBMIT");
        if (parameter5 != null) {
            this._.t = parameter5.equals("1");
        }
        if (this.getParameter("SHOWSOL") != null) {
            this._.\u00c9 = true;
        }
        final String parameter6 = this.getParameter("TITLE");
        if (parameter6 != null) {
            this._.Q = this._.C(parameter6);
        }
        final String parameter7 = this.getParameter("COPYRIGHT");
        if (parameter7 != null) {
            this._.z = this._.C(parameter7);
        }
        this._.\u00ca = this.getParameter("PROGRESS");
        if (this.getParameter("TIMEFROMLOAD") != null) {
            this._.V = true;
        }
        final String parameter8 = this.getParameter("STARTTIME");
        if (parameter8 != null) {
            try {
                this._.F = Integer.parseInt(parameter8);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        if (this.getParameter("TIMER") != null) {
            this._.\u00c2 = true;
        }
        if (this.getParameter("NOPAUSE") != null) {
            this._.\u00c3 = false;
        }
        final String parameter9 = this.getParameter("TITLEHEIGHT");
        if (parameter9 != null) {
            this._.Z = Integer.valueOf(parameter9);
        }
        if (this.getParameter("TITLELEFT") != null) {
            this._.I = true;
        }
    }
    
    private Color A(final String s, final Color color) {
        final String parameter = this.getParameter(s);
        if (parameter != null) {
            return new Color(Integer.parseInt(parameter.substring(1, 3), 16), Integer.parseInt(parameter.substring(3, 5), 16), Integer.parseInt(parameter.substring(5, 7), 16));
        }
        return color;
    }
    
    public void startTimer() {
        if (this.W != null && new Date().getTime() - this.Q > 2000L) {
            this.remove(this.W);
            this.W = null;
        }
        if (this.X != null && !this.X.I) {
            this.X.A();
        }
    }
    
    public void revert(final String \u00ea) {
        this._.\u00ca = \u00ea;
        this.V.F();
    }
    
    public void showStatus(final String s) {
        this.getAppletContext().showStatus(s);
    }
    
    public void puzzleCompleted() {
        if (this.W != null) {
            return;
        }
        if (this.X != null) {
            this.X.I = true;
        }
        if (this.I != null) {
            this.ShowCompletionPic();
        }
        else if (this._.x != null) {
            this.showProcessedURL(this._.x, this._.J, true, false);
        }
        else {
            this.ShowCompletionMessage();
        }
    }
    
    private void H() {
        if (com.cc.C.A.D(this._.\u00c8)) {
            this._.\u00c8 += this.GetSubmit();
        }
    }
    
    public void SolutionClick() {
        this.V.E();
        this.V.requestFocus();
    }
    
    public void CheckClick() {
        this.V.D();
        this.V.requestFocus();
    }
    
    public void RevealClick() {
        this.J.C(null);
    }
    
    public void RevertClick() {
        this.V.F();
        this.V.requestFocus();
    }
    
    public void RevealLetterClick() {
        this.J.A((Object)null);
    }
    
    public void SubmitClick() {
        this.H();
        this.showProcessedURL(this._.\u00c8, this._.e, true, "POST".equals(this._.q));
    }
    
    public void SaveClick() {
        this.showProcessedURL(this._.h, this._.e, true, false);
    }
    
    private void A(final Component component) {
        component.repaint();
        if (component instanceof Container) {
            for (int componentCount = ((Container)component).getComponentCount(), i = 0; i < componentCount; ++i) {
                this.A(((Container)component).getComponent(i));
            }
        }
    }
    
    public void run() {
        Point point = new Point(-1, 0);
    Label_0013:
        while (true) {
            break Label_0013;
            while (true) {
                try {
                    while (true) {
                        Thread.sleep(500L);
                        final Point locationOnScreen = this.getLocationOnScreen();
                        if (point.x == -1) {
                            point = locationOnScreen;
                        }
                        else {
                            if (point.equals(locationOnScreen)) {
                                continue;
                            }
                            Thread.sleep(300L);
                            if (!locationOnScreen.equals(this.getLocationOnScreen())) {
                                continue;
                            }
                            point = locationOnScreen;
                            this.A((Component)this);
                        }
                    }
                }
                catch (Exception ex) {
                    continue;
                }
                continue Label_0013;
            }
            break;
        }
    }
}
