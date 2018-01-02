import java.util.Date;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.event.ItemListener;
import java.awt.event.ActionListener;
import java.awt.Checkbox;
import java.awt.Button;
import java.awt.Panel;
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

public class Grid_int extends Applet
{
    private static final int O = 25;
    private static final boolean B = false;
    private static final boolean Z = false;
    private static final int F = 11;
    private static final int W = 25;
    private String Q;
    private A T;
    private long N;
    private E[] R;
    private static final String L = " Software © 2010 ";
    private int D;
    private com.cc.C.A X;
    private com.cc.B.A A;
    private C Y;
    private B S;
    private A P;
    public A U;
    private A H;
    private Rectangle G;
    private Rectangle E;
    public com.cc.applet.C I;
    private F K;
    private F M;
    private int J;
    private D C;
    public ScrollPane V;
    
    public Grid_int() {
        this.Q = null;
        this.T = null;
        this.U = null;
        this.V = null;
    }
    
    public void init() {
        this.setLayout(null);
        final Font font = new Font("SansSerif", 0, 11);
        final FontMetrics fontMetrics = Toolkit.getDefaultToolkit().getFontMetrics(font);
        this.A();
        if (this.Q == null) {
            this.setBackground(this.X.\u00c2);
            if (this.getParameter("EDU") == null) {
                (this.P = new A(" Software © 2010 ", "crossword-compiler.com")).A("http://www.crossword-compiler.com/cgi-bin/applet8.pl?" + URLEncoder.encode(this.getDocumentBase().toString()));
            }
            else {
                this.P = new A(" Software © 2010 crossword-compiler.com", "");
            }
            this.P.setFont(font);
            this.P.W = 11 - fontMetrics.getDescent() + 2;
            this.P.H = fontMetrics.stringWidth(this.P.U);
            if (this.X.P != null) {
                this.K = new F(this.X.P, this.P.getFont(), this.getBounds().width, false, this);
                this.J = this.K.H().height;
            }
            if (this.X.x != null) {
                this.M = new F(this.X.x, this.P.getFont(), this.getBounds().width, false, this);
            }
            int n = 55;
            final int n2 = 4;
            if (this.X.S) {
                n = 75;
            }
            if (this.X.s) {
                n += 25;
            }
            this.D = ((this.X.H.U() == 0 || this.X.¢) ? 0 : (n + n2 * 2));
            this.I();
            this.I = new com.cc.applet.C(this, this.S, this.A, this.X);
            this.D();
            final com.cc.gui.B[] array = new com.cc.gui.B[this.R.length];
            for (int i = 0; i < this.R.length; ++i) {
                array[i] = this.R[i].A;
            }
            this.I.A(array);
            if (this.A.K() != null) {
                this.F();
            }
            if (this.A.Y == null) {
                this.A.G = false;
            }
            if (this.A.G) {
                this.C = new D(this.A, this.I);
                Dimension preferredSize = new Dimension(0, 0);
                for (int j = 0; j < 20; ++j) {
                    this.C.setFont(new Font("SansSerif", 1, this.S.G() - j));
                    preferredSize = this.C.getPreferredSize();
                    if (preferredSize.width <= this.G.width) {
                        break;
                    }
                    if (this.S.G() - j < 6) {
                        break;
                    }
                }
                this.C.setForeground(this.getForeground());
                int n3 = this.G.x + (this.G.width - preferredSize.width) / 2;
                if (n3 + preferredSize.width > this.getSize().width) {
                    n3 = (this.getSize().width - preferredSize.width) / 2;
                }
                this.C.setBounds(n3, this.G.y + this.G.height + 2, preferredSize.width, preferredSize.height);
                this.add(this.C);
            }
            if (this.X.\u00c0) {
                (this.U = new A()).setName("TIMER");
                Font font2 = font;
                FontMetrics fontMetrics2 = fontMetrics;
                if (this.X.H.U() != 0) {
                    font2 = new Font("SansSerif", 1, 13);
                    fontMetrics2 = this.U.getFontMetrics(font2);
                }
                this.U.setFont(font2);
                this.U.W = this.P.W + ((this.X.H.U() != 0) ? 2 : 0);
                this.U.setSize(Math.min(n, fontMetrics2.stringWidth("10:00:00")), 13 + ((this.X.H.U() != 0) ? 4 : 0));
                this.U.A(this.X.F);
                this.U.J = this.X.\u00c1;
            }
            this.J();
            this.E();
            if (this.getSize().height < this.P.getBounds().y + this.P.getBounds().height && System.getProperty("os.version").compareTo("4.2") > 0) {
                this.Q = "Wrong size";
                this.removeAll();
            }
            if (this.Q == null) {
                if (this.X.m != 0) {
                    (this.V = new ScrollPane(0)).add(this.S);
                    this.V.getVAdjustable().setUnitIncrement(this.S.C());
                    this.V.getHAdjustable().setUnitIncrement(this.S.C());
                    this.V.setSize(this.X.m, this.X.Y);
                    this.add(this.V);
                }
                else {
                    this.add(this.S);
                }
            }
            if (!this.X.s) {
                this.A(25, n, n2, this.J + (this.A.F ? this.A.L : 0));
            }
            else {
                this.B(25, n, n2, this.J + (this.A.F ? this.A.L : 0));
            }
            if (this.U != null) {
                this.add(this.U);
            }
            this.A(this);
            this.S.requestFocus();
            if (this.X.\u00c7) {
                this.S.E();
            }
            if (this.U != null && this.X.U) {
                this.U.A();
            }
            if (this.getBackground().getGreen() < 130 && this.getBackground().getRed() < 130 && this.getBackground().getBlue() < 130) {
                if (this.K != null) {
                    this.K.A(Color.white);
                }
                if (this.M != null) {
                    this.M.A(Color.white);
                }
            }
            this.S.repaint();
        }
    }
    
    private void A(final Container container) {
        container.addKeyListener(this.I);
        container.addMouseListener(this.I);
        final Component[] components = container.getComponents();
        for (int i = 0; i < components.length; ++i) {
            if (components[i] instanceof Container) {
                this.A((Container)components[i]);
            }
            else {
                components[i].addKeyListener(this.I);
                components[i].addMouseListener(this.I);
            }
        }
    }
    
    private void F() {
        final B b = new B(this.A, this.Y, this.X, true);
        final int n = 3;
        final int width = b.getPreferredSize().width;
        b.setBounds((this.G.width - width) / 2 + this.G.x, n + this.G.y + this.G.height, width, b.getPreferredSize().height);
        this.add(b);
        final Rectangle g = this.G;
        g.height += n + b.getPreferredSize().height;
    }
    
    private void J() {
        int max = this.G.x + this.G.width;
        if (this.Y.A() > 0) {
            max = Math.max(this.E.x + this.E.width, max);
        }
        int n = this.G.y + this.G.height;
        int n2 = this.G.x + this.G.width - this.P.H;
        if (this.A.G) {
            n = Math.max(n, this.C.getBounds().y + this.C.getBounds().height);
            n2 = this.C.A() + ((this.M != null) ? this.M.H().width : 0) / 2 - this.P.H / 2;
        }
        if (this.Y.A() > 0) {
            n = Math.max(n, this.E.y + this.E.height);
        }
        this.P.setBounds(n2, n + 2, this.P.H, 13);
        this.add(this.P);
        if (this.U != null && this.U.getLocation().equals(new Point(0, 0))) {
            int n3 = this.G.x + this.G.width;
            if (this.Y.A() > 0) {
                n3 = this.E.x + this.E.width;
            }
            this.U.setLocation(n3 - this.U.getSize().width, n + 2);
        }
        if (this.K != null) {
            if (this.G.width >= this.K.H().width) {
                this.K.B(this.G.x, 0, this.G.width, this.K.H().height);
                this.K.A(0.5f);
            }
            else {
                this.K.B(this.G.x - (this.K.H().width - this.G.width) / 2, 0, this.K.H().height, this.K.H().height);
            }
        }
        if (this.M != null) {
            int n4 = this.G.x;
            if (this.Y.A() > 0) {
                n4 = Math.min(n4, this.E.x);
            }
            if (this.C != null) {
                n4 = this.P.getBounds().x - this.M.H().width;
            }
            this.M.C(true);
            this.M.B(0);
            final int n5 = this.M.H().width + 2;
            final int n6 = (this.U != null && (this.X.¢ || this.X.H.U() == 0)) ? (this.U.getSize().width + 5) : 0;
            if (n4 + n5 > this.P.getBounds().x && max - this.P.H - n6 <= this.getSize().width) {
                this.P.setBounds(max - this.P.H - n6, n + 2, this.P.H, 13);
            }
            this.M.B(n4, this.P.getBounds().y, n5, this.M.H().height);
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
                a.setBounds(Integer.parseInt(parameter.substring(1, 5)) + (((int1 & 0x1) != 0x0) ? this.S.getBounds().x : 0), Integer.parseInt(parameter.substring(5, 9)) + (((int1 & 0x1) != 0x0) ? this.S.getBounds().y : 0), Integer.parseInt(parameter.substring(9, 13)), Integer.parseInt(parameter.substring(13, 17)));
                if ((int1 & 0x1) != 0x0) {
                    a.getBounds().translate(this.S.getBounds().x, this.S.getBounds().y);
                }
                a.A(this.getParameter(s + "PICTUREURL" + string));
                a.S = this.getParameter(s + "PICTUREURLFRAME" + string);
                if (!a.getBounds().intersects(this.P.getBounds())) {
                    this.add(a, ((int1 & 0x2) != 0x0) ? -1 : 0);
                }
                ++n;
            }
            if (this.X.e != null) {
                this.H = new A(this.X.e);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void C() {
        try {
            this.X.H.B(this.A("check.gif"));
            this.X.H.F(this.A("reveal_letter.gif"));
            this.X.H.H(this.A("reveal_word.gif"));
            this.X.H.E(this.A("revert.gif"));
            this.X.H.C(this.A("save.gif"));
            this.X.H.D(this.A("solution.gif"));
            this.X.H.A(this.A("submit.gif"));
            this.X.H.G(this.A("pencil.gif"));
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
        if (this.Y.A() > 0 && this.X.K == 1) {
            return this.X.\u00c5 + this.X.J;
        }
        return 0;
    }
    
    private void I() {
        this.S = new B(this.A, this.Y, this.X, false);
        try {
            this.S.F();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        this.G = new Rectangle(new Point(this.D + this.B(), this.J), this.S.getPreferredSize());
        this.S.setBounds(this.G);
        if (this.X.m != 0) {
            this.G.width = this.X.m;
            this.G.height = this.X.Y;
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
                this.X = new com.cc.C.A();
                this.A = new com.cc.B.A();
                this.Y = new C(this.A);
                b.A(this.X, this.A, this.Y);
            }
            else {
                final com.cc.A.A a2 = com.cc.A.A.A(a, this.getParameter("CHARSET"), this.getParameter("OUTPUTCHARSET"));
                this.X = a2.E;
                this.A = a2.K;
                (this.Y = new C(this.A)).A(a2.B);
            }
            this.A.A(this.Y);
            this.G();
        }
        catch (IOException ex) {
            this.Q = "Cannot read file: " + ex.getMessage();
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
        final com.cc.D.B[] d = this.Y.D();
        this.R = new E[d.length];
        if (d.length == 0) {
            this.X.\u00c5 = 0;
            return;
        }
        if (d[0].G) {
            this.X.\u00c5 = 0;
        }
        int n = this.X.\u00c5;
        int n2 = this.G.height;
        int n3 = this.G.x + this.G.width + this.X.J;
        int y = this.G.y;
        int a = this.Y.A();
        int a2 = 1;
        if (this.X.K == 1) {
            n3 = this.D;
        }
        if (this.X.K == 2) {
            n3 = this.D;
            y = this.G.y + this.G.height + this.X.J;
            n = this.G.width;
            n2 = this.X.\u00c5;
            a = 1;
            a2 = this.Y.A();
        }
        this.E = new Rectangle(n3, y, n, n2);
        final Panel panel = new Panel(new GridLayout(a, a2, 6, 6));
        panel.setBounds(this.E.x, this.E.y, this.E.width, this.E.height);
        this.add(panel);
        for (int i = 0; i < this.R.length; ++i) {
            (this.R[i] = new E(this.X, d[i], this, this.A, this.Y)).setForeground(this.X.\u00c9);
            panel.add(this.R[i]);
        }
    }
    
    private void A(final int n, final int n2, final int n3, final int n4) {
        final Font font = new Font("SansSerif", 0, 11);
        if (this.X.H.U() != 0) {
            final Component[] array = new Component[8];
            if (this.X.H.V()) {
                array[0] = new Button(this.X.H.O());
                ((Button)array[0]).setActionCommand(String.valueOf(4));
            }
            if (this.X.H.J()) {
                array[1] = new Button(this.X.H.F());
                ((Button)array[1]).setActionCommand(String.valueOf(3));
            }
            if (this.X.H.Q()) {
                array[2] = new Button(this.X.H.N());
                ((Button)array[2]).setActionCommand(String.valueOf(2));
            }
            if (this.X.H.S()) {
                array[3] = new Button(this.X.H.M());
                ((Button)array[3]).setActionCommand(String.valueOf(1));
            }
            if (this.X.H.Y()) {
                array[4] = new Button(this.X.H.P());
                ((Button)array[4]).setActionCommand(String.valueOf(6));
            }
            if (this.X.H.E()) {
                array[5] = new Button(this.X.H.K());
                ((Button)array[5]).setActionCommand(String.valueOf(0));
            }
            if (this.X.H.H()) {
                array[6] = new Button(this.X.H.T());
                ((Button)array[6]).setActionCommand(String.valueOf(5));
            }
            if (this.X.H.G()) {
                array[7] = new Checkbox(this.X.H.B());
            }
            if (this.X.¢) {
                final int max = Math.max(0, Math.min((this.G.width + ((this.R.length == 0) ? 0 : (this.X.\u00c5 + this.X.J)) - (this.X.H.U() + 2) * n2) / this.X.H.U(), n2 * 2 / 5));
                final int n5 = this.P.getBounds().y + this.P.getBounds().height - 2 + 10;
                final int n6 = (this.G.width + ((this.R.length == 0) ? 0 : (this.X.\u00c5 + this.X.J)) - this.X.H.U() * (n2 + max) + max) / 2;
                int n7 = 0;
                for (int i = 0; i < 8; ++i) {
                    if (array[i] != null) {
                        array[i].setFont(font);
                        array[i].setBounds(n6 + n7 * (n2 + max), n5, n2, n);
                        this.add(array[i]);
                        if (array[i] instanceof Button) {
                            ((Button)array[i]).addActionListener(this.I);
                        }
                        else {
                            ((Checkbox)array[i]).addItemListener(this.I);
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
                            ((Button)array[j]).addActionListener(this.I);
                        }
                        else {
                            ((Checkbox)array[j]).addItemListener(this.I);
                        }
                        ++n8;
                    }
                }
                if (this.U != null) {
                    this.U.setBounds(n3, n4 + n8 * (n + 5) + (n - 11) / 2, n2, 13);
                    this.U.L = true;
                }
            }
        }
    }
    
    private void B(final int n, final int n2, final int n3, final int n4) {
        final Font font = new Font("SansSerif", 0, 11);
        if (this.X.H.U() != 0) {
            final A[] array = new A[8];
            if (this.X.H.V()) {
                (array[0] = new A(this.X.H.O())).B(String.valueOf(4));
                array[0].X = this.X.H.W();
            }
            if (this.X.H.J()) {
                (array[1] = new A(this.X.H.F())).B(String.valueOf(3));
                array[1].X = this.X.H.Z();
            }
            if (this.X.H.Q()) {
                (array[2] = new A(this.X.H.N())).B(String.valueOf(2));
                array[2].X = this.X.H.D();
            }
            if (this.X.H.S()) {
                (array[3] = new A(this.X.H.M())).B(String.valueOf(1));
                array[3].X = this.X.H.X();
            }
            if (this.X.H.Y()) {
                (array[4] = new A(this.X.H.P())).B(String.valueOf(6));
                array[4].X = this.X.H.L();
            }
            if (this.X.H.E()) {
                (array[5] = new A(this.X.H.K())).B(String.valueOf(0));
                array[5].X = this.X.H.I();
            }
            if (this.X.H.H()) {
                (array[6] = new A(this.X.H.T())).B(String.valueOf(5));
                array[6].X = this.X.H.A();
            }
            if (this.X.H.G()) {
                (array[7] = new A(this.X.H.B())).B(String.valueOf(7));
                array[7].X = this.X.H.C();
            }
            if (this.X.¢) {
                final int max = Math.max(Math.min((this.G.width + ((this.R.length == 0) ? 0 : (this.X.\u00c5 + this.X.J)) - (this.X.H.U() + 2) * n2) / this.X.H.U(), n2 * 2 / 5), 0);
                final int n5 = this.P.getBounds().y + this.P.getBounds().height + 10;
                final int n6 = (this.G.width + ((this.R.length == 0) ? 0 : (this.X.\u00c5 + this.X.J)) - this.X.H.U() * (n2 + max) + max) / 2;
                int n7 = 0;
                for (int i = 0; i < 8; ++i) {
                    if (array[i] != null) {
                        array[i].setFont(font);
                        array[i].setBounds(n6 + n7 * (n2 + max), n5, n2, n);
                        this.add(array[i]);
                        array[i].A(this.I);
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
                        array[j].A(this.I);
                        ++n8;
                    }
                }
                if (this.U != null) {
                    this.U.setBounds(n3, n4 + n8 * (n + 5) + (n - 11) / 2, n2, 13);
                    this.U.L = true;
                }
            }
        }
    }
    
    private InputStream K() throws MalformedURLException, IOException {
        return new URL(this.getDocumentBase(), this.A("DATAFILE", "")).openStream();
    }
    
    public void paint(final Graphics graphics) {
        if (this.Q != null) {
            graphics.drawString(this.Q, this.D, graphics.getFontMetrics().getHeight());
            if (this.P == null) {
                graphics.drawString("If Win2003+ check extension is MIME type in IIS", this.D, graphics.getFontMetrics().getHeight() * 2);
            }
        }
        else {
            if (this.M != null) {
                this.M.B(graphics.create(this.M.B().x, this.M.B().y, this.M.B().width, this.M.B().height));
            }
            if (this.K != null) {
                this.K.B(graphics.create(this.K.B().x, this.K.B().y, this.K.B().width, this.K.B().height));
            }
            if (this.getBackground().getGreen() < 130 && this.getBackground().getRed() < 130 && this.getBackground().getBlue() < 130) {
                graphics.setColor(Color.white);
            }
            else {
                graphics.setColor(Color.black);
            }
            graphics.setFont(this.P.getFont());
        }
    }
    
    public void showProcessedURL(final String s, final String s2, final boolean b, final boolean b2) {
        if (s != null && s.length() != 0) {
            final String a = this.A.A(this.X, s, (this.U == null) ? -1 : this.U.O, b2);
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
            this.S.requestFocus();
        }
    }
    
    public void ShowCompletionPic() {
        try {
            if (this.H.G != null) {
                this.H.G.waitForAll();
            }
            final int width = this.H.X.getWidth(this);
            final int height = this.H.X.getHeight(this);
            this.H.setBounds((this.getBounds().width - width) / 2, (this.getBounds().height - height) / 2, width, height);
            this.H.A(this.X.µ);
            this.H.S = this.X.y;
            this.H.addMouseListener(this.I);
            this.add(this.H, 0);
            this.H.repaint();
            this.T = this.H;
            this.N = new Date().getTime();
        }
        catch (Exception ex) {}
    }
    
    public void ShowCompletionMessage() {
        if (this.X.q != null && this.X.q.length() > 0) {
            try {
                this.T = new A(this.X.q, this.X.L);
                final int width = this.T.getSize().width;
                final int height = this.T.getSize().height;
                this.T.setBounds((this.getSize().width - width) / 2, (this.getSize().height - height) / 2, width, height);
                this.T.addMouseListener(this.I);
                this.add(this.T, 0);
                this.T.repaint();
                this.N = new Date().getTime();
            }
            catch (Exception ex) {}
        }
    }
    
    public String GetSubmit() {
        return this.A.A(this.X, false);
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
        if (this.U != null) {
            return this.U.O;
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
        this.X.\u00c2 = this.A("BACKCOLOR", this.X.\u00c2);
        if (this.getBackground().getGreen() < 130 && this.getBackground().getRed() < 130 && this.getBackground().getBlue() < 130 && this.X.\u00c9 == Color.black) {
            this.X.\u00c9 = Color.white;
        }
        this.X.u = this.A("SELCOLOR", this.X.u);
        this.X.\u00cf = this.A("URLCOLOR", this.X.\u00cf);
        this.X.\u00ca = this.A("CLUEURLTARGET", this.X.\u00ca);
        this.X.g = this.A("HINTURLTEXT", this.X.g);
        this.X.\u00c9 = this.A("CLUECOLOR", this.X.\u00c9);
        this.X.W = this.A("SCROLLCOLOR", this.X.W);
        this.X.v = this.A("FINISHEDURL", this.X.v);
        this.X.I = this.A("FINISHEDURLFRAME", this.X.I);
        final String parameter = this.getParameter("COMPLETIONPICTURE");
        if (parameter != null) {
            this.X.e = this.getImage(this.getDocumentBase(), parameter);
        }
        this.X.µ = this.A("COMPLETIONPICTUREURL", this.X.µ);
        this.X.y = this.A("COMPLETIONPICTUREURLFRAME", this.X.y);
        final String parameter2 = this.getParameter("MAXCHEATS");
        if (parameter2 != null) {
            this.X.\u00d0 = Integer.valueOf(parameter2);
        }
        final String parameter3 = this.getParameter("SCROLLWIDTH");
        if (parameter3 != null) {
            this.X.m = Integer.valueOf(parameter3);
            this.X.Y = Integer.valueOf(this.getParameter("SCROLLHEIGHT"));
        }
        this.X.\u00c6 = this.A("SUBMIT", this.X.\u00c6);
        this.X.o = this.A("SUBMITMETHOD", this.X.o);
        this.X.c = this.A("SUBMITFRAME", this.X.c);
        this.X.f = this.A("SAVE", this.X.f);
        this.A.o = this.A("GRIDBACKCOLOR", this.A.o);
        this.A.E = this.A("WRONGCOLOR", this.A.E);
        this.A._ = this.A("GRIDCOLOR", this.A._);
        this.A.v = this.A("BLOCKCOLOR", this.A.v);
        this.A.e = this.A("FONTCOLOR", this.A.e);
        this.A.x = this.A("NUMCOLOR", this.A.x);
        this.A.X = this.A("SELCOLOR", this.A.X);
        this.A.a = this.A("CURCOLOR", this.A.a);
        final String parameter4 = this.getParameter("COMPLETECORRECT");
        if (parameter4 != null) {
            this.X._ = parameter4.equals("1");
        }
        final String parameter5 = this.getParameter("FRIENDLYSUBMIT");
        if (parameter5 != null) {
            this.X.r = parameter5.equals("1");
        }
        if (this.getParameter("SHOWSOL") != null) {
            this.X.\u00c7 = true;
        }
        final String parameter6 = this.getParameter("TITLE");
        if (parameter6 != null) {
            this.X.P = this.X.C(parameter6);
        }
        final String parameter7 = this.getParameter("COPYRIGHT");
        if (parameter7 != null) {
            this.X.x = this.X.C(parameter7);
        }
        this.X.\u00c8 = this.getParameter("PROGRESS");
        if (this.getParameter("TIMEFROMLOAD") != null) {
            this.X.U = true;
        }
        final String parameter8 = this.getParameter("STARTTIME");
        if (parameter8 != null) {
            try {
                this.X.F = Integer.parseInt(parameter8);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        if (this.getParameter("TIMER") != null) {
            this.X.\u00c0 = true;
        }
        if (this.getParameter("NOPAUSE") != null) {
            this.X.\u00c1 = false;
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
        if (this.T != null && new Date().getTime() - this.N > 2000L) {
            this.remove(this.T);
            this.T = null;
        }
        if (this.U != null && !this.U.I) {
            this.U.A();
        }
    }
    
    public void revert(final String \u00e8) {
        this.X.\u00c8 = \u00e8;
        this.S.F();
    }
    
    public void showStatus(final String s) {
        this.getAppletContext().showStatus(s);
    }
    
    public void puzzleCompleted() {
        if (this.T != null) {
            return;
        }
        if (this.U != null) {
            this.U.I = true;
        }
        if (this.H != null) {
            this.ShowCompletionPic();
        }
        else if (this.X.v != null) {
            this.showProcessedURL(this.X.v, this.X.I, true, false);
        }
        else {
            this.ShowCompletionMessage();
        }
    }
    
    private void H() {
        if (com.cc.C.A.D(this.X.\u00c6)) {
            this.X.\u00c6 += this.GetSubmit();
        }
    }
    
    public void SolutionClick() {
        this.S.E();
        this.S.requestFocus();
    }
    
    public void CheckClick() {
        this.S.D();
        this.S.requestFocus();
    }
    
    public void RevealClick() {
        this.I.C(null);
    }
    
    public void RevertClick() {
        this.S.F();
        this.S.requestFocus();
    }
    
    public void RevealLetterClick() {
        this.I.A((Object)null);
    }
    
    public void SubmitClick() {
        this.H();
        this.showProcessedURL(this.X.\u00c6, this.X.c, true, "POST".equals(this.X.o));
    }
    
    public void SaveClick() {
        this.showProcessedURL(this.X.f, this.X.c, true, false);
    }
}
