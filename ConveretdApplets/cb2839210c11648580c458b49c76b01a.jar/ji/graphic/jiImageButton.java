// 
// Decompiled by Procyon v0.5.30
// 

package ji.graphic;

import ji.v1event.ar;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import ji.v1event.af;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import ji.awt.bb;
import ji.util.i;
import java.awt.Component;
import ji.util.e;
import ji.res.z;
import ji.util.d;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Dimension;
import java.util.Vector;
import java.awt.Image;
import java.awt.event.FocusListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import ji.v1base.bz;

public class jiImageButton extends bz implements MouseListener, KeyListener, FocusListener
{
    private int a;
    private int b;
    private int c;
    private int d;
    private Image e;
    private Image f;
    private Image g;
    private Image h;
    private int i;
    private int j;
    private boolean k;
    private boolean l;
    private int m;
    private boolean n;
    private boolean o;
    protected boolean p;
    private boolean q;
    private boolean r;
    private boolean s;
    private boolean t;
    private boolean u;
    private boolean v;
    private int w;
    private boolean x;
    private boolean y;
    private boolean z;
    private Vector aa;
    private String ab;
    private Dimension ac;
    private boolean ad;
    private String ae;
    private String af;
    private String ag;
    private String ah;
    private boolean ai;
    private int aj;
    private int ak;
    private int al;
    private int am;
    private Color an;
    private Color ao;
    private Color ap;
    private boolean aq;
    private int ar;
    private int as;
    private int at;
    private int au;
    private int av;
    private boolean aw;
    private boolean ax;
    private boolean ay;
    private ActionEvent az;
    private boolean a0;
    private boolean a1;
    private boolean a2;
    private boolean a3;
    private boolean a4;
    private Color a5;
    private String a6;
    private boolean a7;
    private int a8;
    private String a9;
    private String ba;
    private String bb;
    public String parentId;
    private boolean bc;
    private int bd;
    private String be;
    private int bf;
    private boolean bg;
    private boolean bh;
    private t1 bi;
    
    public jiImageButton(final String s, final int n, final int n2, final int n3, final boolean b, final boolean b2, final boolean b3, final boolean b4, final int n4, final String s2, final String s3, final int n5, final boolean b5, final String s4, final String s5, final boolean b6, final String s6, final int n6, final String s7) {
        this(s, n, n2, n3, b, b2, b3, b4, n4, s2, s3, n5, b5, s4, s5, b6, s6, n6, s7, false);
    }
    
    public jiImageButton(final String id, final int imageFileDisabled, final int au, final int av, final boolean toggle, final boolean b, final boolean x, final boolean flat, final int n, final String s, final String helpTextInset, final int n2, final boolean l, final String a9, final String bb, final boolean bc, final String s2, final int n3, final String parentId, final boolean bg) {
        super(parentId);
        this.a = 2;
        this.b = -1;
        this.c = -1;
        this.d = -1;
        this.e = null;
        this.f = null;
        this.g = null;
        this.h = null;
        this.i = 0;
        this.j = 0;
        this.k = false;
        this.l = false;
        this.m = -1;
        this.n = false;
        this.o = true;
        this.p = false;
        this.q = false;
        this.r = true;
        this.s = false;
        this.t = true;
        this.u = false;
        this.v = false;
        this.y = false;
        this.z = false;
        this.ab = "buttonX";
        this.ac = new Dimension(20, 20);
        this.ad = true;
        this.ae = "";
        this.af = null;
        this.ag = "";
        this.ah = null;
        this.ak = 2;
        this.al = 1;
        this.am = 0;
        this.an = null;
        this.ao = null;
        this.ap = null;
        this.aq = false;
        this.ar = 1;
        this.as = 0;
        this.at = 0;
        this.au = 0;
        this.av = 0;
        this.aw = false;
        this.ax = false;
        this.ay = true;
        this.az = null;
        this.a0 = false;
        this.a1 = true;
        this.a2 = false;
        this.a3 = false;
        this.a4 = false;
        this.a5 = null;
        this.a6 = null;
        this.a7 = false;
        this.a8 = 0;
        this.a9 = null;
        this.ba = null;
        this.bb = null;
        this.parentId = null;
        this.bc = true;
        this.bd = 0;
        this.be = null;
        this.bf = -1;
        this.bg = false;
        this.bh = false;
        this.bi = new t1((ad8)null);
        ++this.a8;
        this.parentId = parentId;
        try {
            this.bc = bc;
            this.m();
            this.d(true);
            this.addKeyListener(this);
            this.addFocusListener(this);
            this.a6 = ji.util.d.ai(n2);
            this.aj = n;
            this.ai = b;
            this.a9 = a9;
            this.bb = bb;
            this.l = l;
            this.bg = bg;
            this.setHotKey(s2, n3);
            this.setToggle(toggle);
            this.setEnabled(b);
            this.setState(n);
            this.setHelpTextRaised(s);
            if (helpTextInset != null) {
                this.setHelpTextInset(helpTextInset);
            }
            else {
                this.setHelpTextInset(s);
            }
            this.x = x;
            this.setFlat(flat);
            this.setId(id);
            final Dimension d = ji.res.z.d();
            if (au == 0) {
                this.au = d.width;
            }
            else {
                this.au = au;
            }
            if (av == 0) {
                this.av = d.height;
            }
            else {
                this.av = av;
            }
            this.i = this.au;
            this.j = this.av;
            this.setImageFile(imageFileDisabled, -1);
            this.setImageFileDisabled(imageFileDisabled);
        }
        catch (Exception ex) {}
    }
    
    private final void m() throws Exception {
        this.a(0, 11);
        ji.util.e.a(this);
        this.ap = this.getBackground();
        this.addMouseListener(this);
        this.q();
    }
    
    private final boolean n() {
        return this.u;
    }
    
    public final void setBorderStyle(final int n) {
        this.bd = n;
        if (this.e != null) {
            super.setBorderStyle(n);
        }
        else {
            super.setBorderStyle(0);
        }
    }
    
    private final void a(final int n, final int n2) {
        final Color background = super.getBackground();
        this.w();
        if (n == 2) {
            this.setBorderStyle(this.ak);
            if (this.v && this.ao != null) {
                if (!background.equals(this.ao)) {
                    super.setBackground(this.ao);
                    this.o();
                }
            }
            else if (this.k && this.an != null) {
                if (!background.equals(this.an)) {
                    super.setBackground(this.an);
                    this.o();
                }
            }
            else if (!background.equals(this.ap)) {
                super.setBackground(this.ap);
                this.o();
            }
        }
        else if (n == 0) {
            this.setBorderStyle(this.am);
            if (this.v && this.ao != null) {
                if (!background.equals(this.ao)) {
                    super.setBackground(this.ao);
                    this.o();
                }
            }
            else if (this.k && this.an != null) {
                if (!background.equals(this.an)) {
                    super.setBackground(this.an);
                    this.o();
                }
            }
            else if (!background.equals(this.ap)) {
                super.setBackground(this.ap);
                this.o();
            }
        }
        else {
            this.setBorderStyle(this.al);
            if (this.v && this.ao != null) {
                if (!background.equals(this.ao)) {
                    super.setBackground(this.ao);
                    this.o();
                }
            }
            else if (this.k && this.an != null) {
                if (!background.equals(this.an)) {
                    super.setBackground(this.an);
                    this.o();
                }
            }
            else if (!background.equals(this.ap)) {
                super.setBackground(this.ap);
                this.o();
            }
        }
    }
    
    private final void o() {
        try {
            this.aw = !this.r;
            if (this.a()) {
                this.repaint();
            }
            else {
                this.paintComponent(this.getGraphics());
            }
        }
        catch (Exception ex) {}
    }
    
    public final void setEnabled(final boolean b) {
        if (ji.util.i.c(136)) {
            if (!ji.util.e.av() || ji.util.e.a7()) {
                this.f(b);
            }
            else if (this.bi != null) {
                this.bi.a(b);
                ji.util.e.a(this.bi, this.parentId);
            }
        }
        else {
            this.f(b);
        }
    }
    
    private final void f(final boolean r) {
        try {
            if (this.r != r) {
                super.setEnabled(this.r = r);
                this.repaint();
            }
            if (!this.r && this.a4) {
                this.repaint();
            }
        }
        catch (Exception ex) {}
    }
    
    public final boolean isEnabled() {
        if (this.isSpacer()) {
            this.r = false;
        }
        return this.r;
    }
    
    public final void setState(final int a) {
        if (this.a != a) {
            this.a = a;
            this.w();
            this.repaint();
        }
    }
    
    public final int getState() {
        return this.a;
    }
    
    private final Dimension p() {
        Dimension dimension = null;
        if (this.isSpacerSmall()) {
            dimension = new Dimension(5, 5);
        }
        else if (this.isSpacerLarge()) {
            dimension = new Dimension(30, 19);
        }
        else if (this.isSpacer()) {
            dimension = new Dimension(10, 10);
        }
        return dimension;
    }
    
    public final Dimension getButtonSize() {
        Dimension p = this.p();
        if (p == null) {
            p = new Dimension(this.au + 7, this.av + 8);
        }
        return p;
    }
    
    public final int getImageFile() {
        return this.b;
    }
    
    public final void setImageFile(final int n, final int n2) throws Exception {
        if (this.a2) {
            new bb(this.parentId, new t4(n, n2)).start();
        }
        else {
            this.b(n, n2);
        }
    }
    
    private final void b(final int b, final int c) throws Exception {
        while (this.a2) {
            ji.util.d.b(10, 43, this.parentId);
        }
        if (!this.isSpacer()) {
            if (b >= 0 && (b != this.b || this.c != c)) {
                this.b = b;
                this.c = c;
                if (this.e != null) {
                    this.e.flush();
                }
                if (this.f != null) {
                    this.f.flush();
                }
                if (this.g != null) {
                    this.g.flush();
                }
                this.e = null;
                this.g = null;
                this.f = null;
                if (!this.bc) {
                    this.setImageFileNow();
                }
            }
        }
        else {
            if (this.e != null) {
                this.e.flush();
            }
            if (this.f != null) {
                this.f.flush();
            }
            this.e = null;
            this.f = null;
        }
    }
    
    public final void setImageFileNow() throws Exception {
        while (this.a2) {
            ji.util.d.b(10, 44, this.parentId);
        }
        this.setBorderStyle(this.bd);
        if (!this.isSpacer()) {
            if (this.b >= 0) {
                if (this.e != null) {
                    this.e.flush();
                }
                if (this.f != null) {
                    this.f.flush();
                }
                if (this.g != null) {
                    this.g.flush();
                }
                this.e = null;
                this.g = null;
                this.f = null;
                if (!this.p && !this.n) {
                    try {
                        if (this.l) {
                            this.e = this.k();
                            if (this.e == null) {
                                this.n = this.o;
                            }
                            if (this.ba != null) {
                                this.g = this.j();
                                if (this.g == null) {
                                    this.n = this.o;
                                }
                            }
                        }
                        else {
                            this.e = this.k();
                            if (ji.util.d.a8()) {
                                this.f = this.i();
                            }
                            if (this.c >= 0) {
                                this.g = this.j();
                            }
                        }
                    }
                    catch (Exception ex) {}
                    if (this.e != null) {
                        this.i = this.e.getWidth(null);
                        this.j = this.e.getHeight(null);
                    }
                }
                if (this.x) {
                    this.setSize(this.getPreferredSize());
                }
            }
            else {
                if (this.e != null) {
                    this.e.flush();
                }
                if (this.f != null) {
                    this.f.flush();
                }
                this.e = null;
                this.f = null;
                if (this.g != null) {
                    this.g.flush();
                }
                this.g = null;
                this.i = 0;
                this.j = 0;
            }
            this.a1 = true;
            this.repaint();
        }
        else {
            if (this.e != null) {
                this.e.flush();
            }
            if (this.f != null) {
                this.f.flush();
            }
            this.e = null;
            this.f = null;
        }
    }
    
    public final void setImageFileDisabled(final int n) throws Exception {
        if (this.a2) {
            new bb(this.parentId, new t5(n)).start();
        }
        else {
            this.a(n);
        }
    }
    
    private final void a(final int d) throws Exception {
        while (this.a2) {
            d.b(10, 45, this.parentId);
        }
        if (!this.isSpacer()) {
            if (d >= 0) {
                if (d != this.d) {
                    this.d = d;
                    if (this.h != null) {
                        this.h.flush();
                    }
                    this.h = null;
                    if (!this.bc) {
                        this.setImageFileDisabledNow();
                    }
                }
            }
            else {
                if (this.h != null) {
                    this.h.flush();
                }
                this.h = null;
            }
            this.repaint();
        }
        else {
            if (this.h != null) {
                this.h.flush();
            }
            this.h = null;
        }
    }
    
    public final void setImageFileDisabledNow() throws Exception {
        while (this.a2) {
            ji.util.d.b(10, 46, this.parentId);
        }
        if (!this.isSpacer()) {
            if (this.d >= 0) {
                if (this.h != null) {
                    this.h.flush();
                }
                this.h = null;
                if (!this.p && !this.n) {
                    try {
                        if (this.l) {
                            this.h = this.h();
                        }
                        else {
                            this.h = this.h();
                        }
                        if (this.h == null) {
                            this.n = this.o;
                        }
                    }
                    catch (Exception ex) {}
                }
            }
            else {
                if (this.h != null) {
                    this.h.flush();
                }
                this.h = null;
            }
            this.repaint();
        }
        else {
            if (this.h != null) {
                this.h.flush();
            }
            this.h = null;
        }
    }
    
    public final void setFlat(final boolean q) {
        if (this.q != q) {
            this.q = q;
            this.repaint();
        }
    }
    
    public final void setToggle(final boolean s) {
        this.s = s;
    }
    
    public final boolean isSpacer() {
        boolean b = false;
        if (this.ab != null) {
            if (this.ab.toLowerCase().equals("spacer")) {
                b = true;
            }
            if (this.isSpacerSmall()) {
                b = true;
            }
            if (this.isSpacerLarge()) {
                b = true;
            }
        }
        return b;
    }
    
    public final boolean isSpacerSmall() {
        boolean b = false;
        if (this.ab != null && this.ab.toLowerCase().equals("spacersmall")) {
            b = true;
        }
        return b;
    }
    
    public final boolean isSpacerLarge() {
        boolean b = false;
        if (this.ab != null && this.ab.toLowerCase().equals("spacerlarge")) {
            b = true;
        }
        return b;
    }
    
    public final Dimension getMinimumSize() {
        Dimension dimension = this.p();
        if (dimension == null) {
            if (this.b >= 0) {
                if (this.x) {
                    dimension = this.getPreferredSize();
                }
                else {
                    dimension = this.ac;
                }
            }
            else {
                dimension = this.ac;
            }
        }
        return dimension;
    }
    
    private final void q() {
        this.setSize(this.getMinimumSize());
    }
    
    public Dimension getPreferredSize() {
        Dimension dimension = new Dimension();
        if (this.e != null || this.bc) {
            dimension.width = this.i + 6;
            dimension.height = this.j + 6;
            if (!this.isSpacer()) {
                if (dimension.width < this.ac.width) {
                    dimension.width = this.ac.width;
                }
                if (dimension.height < this.ac.height) {
                    dimension.height = this.ac.height;
                }
            }
        }
        else {
            dimension = new Dimension(this.au + 6, this.av + 6);
        }
        return dimension;
    }
    
    public final Dimension getSize() {
        if (this.x) {
            return this.getPreferredSize();
        }
        return super.getSize();
    }
    
    public void paint(final Graphics graphics) {
        if (this.a()) {
            super.paint(graphics);
        }
        else {
            this.paintComponent(graphics);
        }
    }
    
    public void paintComponent(final Graphics graphics) {
        this.a(graphics);
    }
    
    protected Image h() {
        Image image = null;
        try {
            if (this.l) {
                image = ji.res.z.a(this, this.bb, null, null, this.parentId);
            }
            else {
                image = ji.res.z.a(this.d, 1, this.au, this.av, this, this.a6, ji.util.d.b(this.a6, ".gif", ".v1"), null, null, this.parentId);
            }
        }
        catch (Exception ex) {}
        return image;
    }
    
    protected Image i() throws Exception {
        return ji.res.z.a(this.b, 0, this.au, this.av, this, this.a6, ji.util.d.b(this.a6, ".gif", ".v1"), null, ji.util.e.a0(), this.parentId);
    }
    
    protected Image j() throws Exception {
        if (this.l) {
            return ji.res.z.a(this, this.ba, null, null, this.parentId);
        }
        return ji.res.z.a(this.c, 0, this.au, this.av, this, this.a6, ji.util.d.b(this.a6, ".gif", ".v1"), null, null, this.parentId);
    }
    
    protected Image k() throws Exception {
        Image image;
        if (this.l) {
            image = ji.res.z.a(this, this.a9, null, null, this.parentId);
        }
        else {
            image = ji.res.z.a(this.b, 0, this.au, this.av, this, this.a6, ji.util.d.b(this.a6, ".gif", ".v1"), null, null, this.parentId);
        }
        return image;
    }
    
    protected void a(final Graphics p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: iconst_1       
        //     2: putfield        ji/graphic/jiImageButton.a2:Z
        //     5: aload_1        
        //     6: ifnonnull       13
        //     9: jsr             1228
        //    12: return         
        //    13: aload_0        
        //    14: invokevirtual   java/awt/Component.getLocation:()Ljava/awt/Point;
        //    17: astore_2       
        //    18: aload_0        
        //    19: invokevirtual   ji/graphic/jiImageButton.isSpacer:()Z
        //    22: ifeq            56
        //    25: aload_0        
        //    26: invokevirtual   ji/graphic/jiImageButton.getSize:()Ljava/awt/Dimension;
        //    29: astore_3       
        //    30: aload_1        
        //    31: aload_0        
        //    32: invokevirtual   ji/graphic/jiImageButton.getBackground:()Ljava/awt/Color;
        //    35: invokevirtual   java/awt/Graphics.setColor:(Ljava/awt/Color;)V
        //    38: aload_1        
        //    39: iconst_0       
        //    40: iconst_0       
        //    41: aload_3        
        //    42: getfield        java/awt/Dimension.width:I
        //    45: aload_3        
        //    46: getfield        java/awt/Dimension.height:I
        //    49: invokevirtual   java/awt/Graphics.fillRect:(IIII)V
        //    52: jsr             1228
        //    55: return         
        //    56: aload_2        
        //    57: getfield        java/awt/Point.x:I
        //    60: iflt            1214
        //    63: aload_2        
        //    64: getfield        java/awt/Point.y:I
        //    67: iflt            1214
        //    70: aload_0        
        //    71: invokevirtual   ji/graphic/jiImageButton.getSize:()Ljava/awt/Dimension;
        //    74: astore_3       
        //    75: aload_0        
        //    76: getfield        ji/graphic/jiImageButton.q:Z
        //    79: ifne            94
        //    82: aload_0        
        //    83: aload_0        
        //    84: getfield        ji/graphic/jiImageButton.a:I
        //    87: iconst_2       
        //    88: invokespecial   ji/graphic/jiImageButton.a:(II)V
        //    91: goto            163
        //    94: aload_0        
        //    95: getfield        ji/graphic/jiImageButton.u:Z
        //    98: ifne            108
        //   101: aload_0        
        //   102: getfield        ji/graphic/jiImageButton.v:Z
        //   105: ifeq            136
        //   108: aload_0        
        //   109: getfield        ji/graphic/jiImageButton.r:Z
        //   112: ifne            124
        //   115: aload_0        
        //   116: iconst_0       
        //   117: iconst_3       
        //   118: invokespecial   ji/graphic/jiImageButton.a:(II)V
        //   121: goto            163
        //   124: aload_0        
        //   125: aload_0        
        //   126: getfield        ji/graphic/jiImageButton.a:I
        //   129: iconst_4       
        //   130: invokespecial   ji/graphic/jiImageButton.a:(II)V
        //   133: goto            163
        //   136: aload_0        
        //   137: getfield        ji/graphic/jiImageButton.a:I
        //   140: iconst_1       
        //   141: if_icmpne       156
        //   144: aload_0        
        //   145: aload_0        
        //   146: getfield        ji/graphic/jiImageButton.a:I
        //   149: iconst_5       
        //   150: invokespecial   ji/graphic/jiImageButton.a:(II)V
        //   153: goto            163
        //   156: aload_0        
        //   157: iconst_0       
        //   158: bipush          6
        //   160: invokespecial   ji/graphic/jiImageButton.a:(II)V
        //   163: aload_0        
        //   164: getfield        ji/graphic/jiImageButton.r:Z
        //   167: ifeq            367
        //   170: aload_0        
        //   171: getfield        ji/graphic/jiImageButton.e:Ljava/awt/Image;
        //   174: ifnonnull       441
        //   177: aload_0        
        //   178: getfield        ji/graphic/jiImageButton.b:I
        //   181: iflt            441
        //   184: aload_0        
        //   185: getfield        ji/graphic/jiImageButton.p:Z
        //   188: ifeq            441
        //   191: aload_0        
        //   192: getfield        ji/graphic/jiImageButton.n:Z
        //   195: ifne            441
        //   198: aload_0        
        //   199: getfield        ji/graphic/jiImageButton.l:Z
        //   202: ifeq            261
        //   205: aload_0        
        //   206: aload_0        
        //   207: invokevirtual   ji/graphic/jiImageButton.k:()Ljava/awt/Image;
        //   210: putfield        ji/graphic/jiImageButton.e:Ljava/awt/Image;
        //   213: aload_0        
        //   214: getfield        ji/graphic/jiImageButton.e:Ljava/awt/Image;
        //   217: ifnonnull       228
        //   220: aload_0        
        //   221: aload_0        
        //   222: getfield        ji/graphic/jiImageButton.o:Z
        //   225: putfield        ji/graphic/jiImageButton.n:Z
        //   228: aload_0        
        //   229: getfield        ji/graphic/jiImageButton.ba:Ljava/lang/String;
        //   232: ifnull          328
        //   235: aload_0        
        //   236: aload_0        
        //   237: invokevirtual   ji/graphic/jiImageButton.j:()Ljava/awt/Image;
        //   240: putfield        ji/graphic/jiImageButton.g:Ljava/awt/Image;
        //   243: aload_0        
        //   244: getfield        ji/graphic/jiImageButton.g:Ljava/awt/Image;
        //   247: ifnonnull       328
        //   250: aload_0        
        //   251: aload_0        
        //   252: getfield        ji/graphic/jiImageButton.o:Z
        //   255: putfield        ji/graphic/jiImageButton.n:Z
        //   258: goto            328
        //   261: aload_0        
        //   262: aload_0        
        //   263: invokevirtual   ji/graphic/jiImageButton.k:()Ljava/awt/Image;
        //   266: putfield        ji/graphic/jiImageButton.e:Ljava/awt/Image;
        //   269: invokestatic    ji/util/d.a8:()Z
        //   272: ifeq            283
        //   275: aload_0        
        //   276: aload_0        
        //   277: invokevirtual   ji/graphic/jiImageButton.i:()Ljava/awt/Image;
        //   280: putfield        ji/graphic/jiImageButton.f:Ljava/awt/Image;
        //   283: aload_0        
        //   284: getfield        ji/graphic/jiImageButton.e:Ljava/awt/Image;
        //   287: ifnonnull       298
        //   290: aload_0        
        //   291: aload_0        
        //   292: getfield        ji/graphic/jiImageButton.o:Z
        //   295: putfield        ji/graphic/jiImageButton.n:Z
        //   298: aload_0        
        //   299: getfield        ji/graphic/jiImageButton.c:I
        //   302: iflt            328
        //   305: aload_0        
        //   306: aload_0        
        //   307: invokevirtual   ji/graphic/jiImageButton.j:()Ljava/awt/Image;
        //   310: putfield        ji/graphic/jiImageButton.g:Ljava/awt/Image;
        //   313: aload_0        
        //   314: getfield        ji/graphic/jiImageButton.g:Ljava/awt/Image;
        //   317: ifnonnull       328
        //   320: aload_0        
        //   321: aload_0        
        //   322: getfield        ji/graphic/jiImageButton.o:Z
        //   325: putfield        ji/graphic/jiImageButton.n:Z
        //   328: aload_0        
        //   329: getfield        ji/graphic/jiImageButton.e:Ljava/awt/Image;
        //   332: ifnull          441
        //   335: aload_0        
        //   336: aload_0        
        //   337: getfield        ji/graphic/jiImageButton.e:Ljava/awt/Image;
        //   340: aconst_null    
        //   341: invokevirtual   java/awt/Image.getWidth:(Ljava/awt/image/ImageObserver;)I
        //   344: putfield        ji/graphic/jiImageButton.i:I
        //   347: aload_0        
        //   348: aload_0        
        //   349: getfield        ji/graphic/jiImageButton.e:Ljava/awt/Image;
        //   352: aconst_null    
        //   353: invokevirtual   java/awt/Image.getHeight:(Ljava/awt/image/ImageObserver;)I
        //   356: putfield        ji/graphic/jiImageButton.j:I
        //   359: goto            441
        //   362: astore          4
        //   364: goto            441
        //   367: aload_0        
        //   368: getfield        ji/graphic/jiImageButton.h:Ljava/awt/Image;
        //   371: ifnonnull       441
        //   374: aload_0        
        //   375: getfield        ji/graphic/jiImageButton.d:I
        //   378: iflt            441
        //   381: aload_0        
        //   382: getfield        ji/graphic/jiImageButton.p:Z
        //   385: ifeq            441
        //   388: aload_0        
        //   389: getfield        ji/graphic/jiImageButton.n:Z
        //   392: ifne            441
        //   395: aload_0        
        //   396: getfield        ji/graphic/jiImageButton.l:Z
        //   399: ifeq            413
        //   402: aload_0        
        //   403: aload_0        
        //   404: invokevirtual   ji/graphic/jiImageButton.h:()Ljava/awt/Image;
        //   407: putfield        ji/graphic/jiImageButton.h:Ljava/awt/Image;
        //   410: goto            421
        //   413: aload_0        
        //   414: aload_0        
        //   415: invokevirtual   ji/graphic/jiImageButton.h:()Ljava/awt/Image;
        //   418: putfield        ji/graphic/jiImageButton.h:Ljava/awt/Image;
        //   421: aload_0        
        //   422: getfield        ji/graphic/jiImageButton.h:Ljava/awt/Image;
        //   425: ifnonnull       441
        //   428: aload_0        
        //   429: aload_0        
        //   430: getfield        ji/graphic/jiImageButton.o:Z
        //   433: putfield        ji/graphic/jiImageButton.n:Z
        //   436: goto            441
        //   439: astore          4
        //   441: aload_0        
        //   442: getfield        ji/graphic/jiImageButton.e:Ljava/awt/Image;
        //   445: ifnonnull       455
        //   448: aload_0        
        //   449: getfield        ji/graphic/jiImageButton.h:Ljava/awt/Image;
        //   452: ifnull          1025
        //   455: iconst_0       
        //   456: istore          4
        //   458: aload_0        
        //   459: getfield        ji/graphic/jiImageButton.i:I
        //   462: iconst_m1      
        //   463: if_icmpne       478
        //   466: aload_0        
        //   467: aload_0        
        //   468: getfield        ji/graphic/jiImageButton.e:Ljava/awt/Image;
        //   471: aconst_null    
        //   472: invokevirtual   java/awt/Image.getWidth:(Ljava/awt/image/ImageObserver;)I
        //   475: putfield        ji/graphic/jiImageButton.i:I
        //   478: aload_0        
        //   479: getfield        ji/graphic/jiImageButton.j:I
        //   482: iconst_m1      
        //   483: if_icmpne       498
        //   486: aload_0        
        //   487: aload_0        
        //   488: getfield        ji/graphic/jiImageButton.e:Ljava/awt/Image;
        //   491: aconst_null    
        //   492: invokevirtual   java/awt/Image.getHeight:(Ljava/awt/image/ImageObserver;)I
        //   495: putfield        ji/graphic/jiImageButton.j:I
        //   498: aload_0        
        //   499: getfield        ji/graphic/jiImageButton.a:I
        //   502: iconst_1       
        //   503: if_icmpne       512
        //   506: aload_0        
        //   507: getfield        ji/graphic/jiImageButton.ar:I
        //   510: istore          4
        //   512: iload           4
        //   514: aload_3        
        //   515: getfield        java/awt/Dimension.width:I
        //   518: aload_0        
        //   519: getfield        ji/graphic/jiImageButton.i:I
        //   522: isub           
        //   523: iconst_2       
        //   524: idiv           
        //   525: iadd           
        //   526: istore          5
        //   528: iload           4
        //   530: aload_3        
        //   531: getfield        java/awt/Dimension.height:I
        //   534: aload_0        
        //   535: getfield        ji/graphic/jiImageButton.j:I
        //   538: isub           
        //   539: iconst_2       
        //   540: idiv           
        //   541: iadd           
        //   542: istore          6
        //   544: aload_0        
        //   545: getfield        ji/graphic/jiImageButton.aw:Z
        //   548: aload_0        
        //   549: getfield        ji/graphic/jiImageButton.r:Z
        //   552: if_icmpne       580
        //   555: aload_0        
        //   556: getfield        ji/graphic/jiImageButton.as:I
        //   559: iload           5
        //   561: if_icmpne       580
        //   564: aload_0        
        //   565: getfield        ji/graphic/jiImageButton.at:I
        //   568: iload           6
        //   570: if_icmpne       580
        //   573: aload_0        
        //   574: getfield        ji/graphic/jiImageButton.a1:Z
        //   577: ifeq            601
        //   580: aload_0        
        //   581: invokevirtual   ji/v1base/bz.f:()V
        //   584: aload_0        
        //   585: iload           5
        //   587: putfield        ji/graphic/jiImageButton.as:I
        //   590: aload_0        
        //   591: iload           6
        //   593: putfield        ji/graphic/jiImageButton.at:I
        //   596: aload_0        
        //   597: iconst_0       
        //   598: putfield        ji/graphic/jiImageButton.a1:Z
        //   601: iload           5
        //   603: ifle            628
        //   606: aload_1        
        //   607: aload_0        
        //   608: invokevirtual   ji/graphic/jiImageButton.getBackground:()Ljava/awt/Color;
        //   611: invokevirtual   java/awt/Graphics.setColor:(Ljava/awt/Color;)V
        //   614: aload_1        
        //   615: iconst_0       
        //   616: iconst_0       
        //   617: iload           5
        //   619: iconst_1       
        //   620: isub           
        //   621: aload_3        
        //   622: getfield        java/awt/Dimension.height:I
        //   625: invokevirtual   java/awt/Graphics.fillRect:(IIII)V
        //   628: iload           5
        //   630: aload_0        
        //   631: getfield        ji/graphic/jiImageButton.i:I
        //   634: iadd           
        //   635: aload_3        
        //   636: getfield        java/awt/Dimension.width:I
        //   639: if_icmpge       670
        //   642: aload_1        
        //   643: aload_0        
        //   644: invokevirtual   ji/graphic/jiImageButton.getBackground:()Ljava/awt/Color;
        //   647: invokevirtual   java/awt/Graphics.setColor:(Ljava/awt/Color;)V
        //   650: aload_1        
        //   651: iload           5
        //   653: iconst_1       
        //   654: iadd           
        //   655: iconst_0       
        //   656: aload_3        
        //   657: getfield        java/awt/Dimension.width:I
        //   660: iload           5
        //   662: isub           
        //   663: aload_3        
        //   664: getfield        java/awt/Dimension.height:I
        //   667: invokevirtual   java/awt/Graphics.fillRect:(IIII)V
        //   670: iload           6
        //   672: ifle            697
        //   675: aload_1        
        //   676: aload_0        
        //   677: invokevirtual   ji/graphic/jiImageButton.getBackground:()Ljava/awt/Color;
        //   680: invokevirtual   java/awt/Graphics.setColor:(Ljava/awt/Color;)V
        //   683: aload_1        
        //   684: iconst_0       
        //   685: iconst_0       
        //   686: aload_3        
        //   687: getfield        java/awt/Dimension.width:I
        //   690: iload           6
        //   692: iconst_1       
        //   693: isub           
        //   694: invokevirtual   java/awt/Graphics.fillRect:(IIII)V
        //   697: invokestatic    ji/util/d.a8:()Z
        //   700: ifeq            846
        //   703: aload_0        
        //   704: getfield        ji/graphic/jiImageButton.u:Z
        //   707: ifeq            815
        //   710: aload_0        
        //   711: getfield        ji/graphic/jiImageButton.r:Z
        //   714: ifeq            815
        //   717: aload_0        
        //   718: getfield        ji/graphic/jiImageButton.bh:Z
        //   721: ifeq            815
        //   724: bipush          7
        //   726: invokestatic    ji/util/i.c:(I)Z
        //   729: ifeq            785
        //   732: invokestatic    ji/util/e.t:()Z
        //   735: ifeq            785
        //   738: invokestatic    ji/util/d.ar:()Z
        //   741: ifeq            785
        //   744: new             Ljava/awt/Rectangle;
        //   747: dup            
        //   748: iconst_0       
        //   749: iconst_0       
        //   750: aload_3        
        //   751: getfield        java/awt/Dimension.width:I
        //   754: bipush          6
        //   756: iadd           
        //   757: aload_3        
        //   758: getfield        java/awt/Dimension.height:I
        //   761: bipush          6
        //   763: iadd           
        //   764: invokespecial   java/awt/Rectangle.<init>:(IIII)V
        //   767: astore          7
        //   769: aload_1        
        //   770: aload           7
        //   772: invokestatic    ji/util/e.a0:()Ljava/awt/Color;
        //   775: invokestatic    ji/util/e.a3:()Ljava/awt/Color;
        //   778: iconst_1       
        //   779: invokestatic    ji/util/d.a:(Ljava/awt/Graphics;Ljava/awt/Rectangle;Ljava/awt/Color;Ljava/awt/Color;Z)V
        //   782: goto            894
        //   785: aload_1        
        //   786: invokestatic    ji/util/e.a0:()Ljava/awt/Color;
        //   789: invokevirtual   java/awt/Graphics.setColor:(Ljava/awt/Color;)V
        //   792: aload_1        
        //   793: iconst_0       
        //   794: iconst_0       
        //   795: aload_3        
        //   796: getfield        java/awt/Dimension.width:I
        //   799: bipush          6
        //   801: iadd           
        //   802: aload_3        
        //   803: getfield        java/awt/Dimension.height:I
        //   806: bipush          6
        //   808: iadd           
        //   809: invokevirtual   java/awt/Graphics.fillRect:(IIII)V
        //   812: goto            894
        //   815: aload_1        
        //   816: aload_0        
        //   817: invokevirtual   ji/graphic/jiImageButton.getBackground:()Ljava/awt/Color;
        //   820: invokevirtual   java/awt/Graphics.setColor:(Ljava/awt/Color;)V
        //   823: aload_1        
        //   824: iconst_0       
        //   825: iconst_0       
        //   826: aload_3        
        //   827: getfield        java/awt/Dimension.width:I
        //   830: bipush          6
        //   832: iadd           
        //   833: aload_3        
        //   834: getfield        java/awt/Dimension.height:I
        //   837: bipush          6
        //   839: iadd           
        //   840: invokevirtual   java/awt/Graphics.fillRect:(IIII)V
        //   843: goto            894
        //   846: iload           6
        //   848: aload_0        
        //   849: getfield        ji/graphic/jiImageButton.j:I
        //   852: iadd           
        //   853: aload_3        
        //   854: getfield        java/awt/Dimension.height:I
        //   857: if_icmpge       894
        //   860: aload_1        
        //   861: aload_0        
        //   862: invokevirtual   ji/graphic/jiImageButton.getBackground:()Ljava/awt/Color;
        //   865: invokevirtual   java/awt/Graphics.setColor:(Ljava/awt/Color;)V
        //   868: aload_1        
        //   869: iconst_0       
        //   870: iload           6
        //   872: iconst_1       
        //   873: iadd           
        //   874: aload_3        
        //   875: getfield        java/awt/Dimension.width:I
        //   878: bipush          6
        //   880: iadd           
        //   881: aload_3        
        //   882: getfield        java/awt/Dimension.height:I
        //   885: iload           6
        //   887: isub           
        //   888: bipush          6
        //   890: iadd           
        //   891: invokevirtual   java/awt/Graphics.fillRect:(IIII)V
        //   894: aload_0        
        //   895: iconst_0       
        //   896: putfield        ji/graphic/jiImageButton.a4:Z
        //   899: aload_0        
        //   900: getfield        ji/graphic/jiImageButton.r:Z
        //   903: ifeq            1008
        //   906: aload_0        
        //   907: getfield        ji/graphic/jiImageButton.c:I
        //   910: iflt            955
        //   913: aload_0        
        //   914: getfield        ji/graphic/jiImageButton.a:I
        //   917: iconst_1       
        //   918: if_icmpeq       938
        //   921: aload_1        
        //   922: aload_0        
        //   923: getfield        ji/graphic/jiImageButton.e:Ljava/awt/Image;
        //   926: iload           5
        //   928: iload           6
        //   930: aload_0        
        //   931: invokevirtual   java/awt/Graphics.drawImage:(Ljava/awt/Image;IILjava/awt/image/ImageObserver;)Z
        //   934: pop            
        //   935: goto            1047
        //   938: aload_1        
        //   939: aload_0        
        //   940: getfield        ji/graphic/jiImageButton.g:Ljava/awt/Image;
        //   943: iload           5
        //   945: iload           6
        //   947: aload_0        
        //   948: invokevirtual   java/awt/Graphics.drawImage:(Ljava/awt/Image;IILjava/awt/image/ImageObserver;)Z
        //   951: pop            
        //   952: goto            1047
        //   955: aload_0        
        //   956: getfield        ji/graphic/jiImageButton.u:Z
        //   959: ifeq            991
        //   962: aload_0        
        //   963: getfield        ji/graphic/jiImageButton.f:Ljava/awt/Image;
        //   966: ifnull          991
        //   969: aload_1        
        //   970: aload_0        
        //   971: getfield        ji/graphic/jiImageButton.f:Ljava/awt/Image;
        //   974: iload           5
        //   976: iload           6
        //   978: aload_0        
        //   979: invokevirtual   java/awt/Graphics.drawImage:(Ljava/awt/Image;IILjava/awt/image/ImageObserver;)Z
        //   982: pop            
        //   983: aload_0        
        //   984: iconst_1       
        //   985: putfield        ji/graphic/jiImageButton.a4:Z
        //   988: goto            1047
        //   991: aload_1        
        //   992: aload_0        
        //   993: getfield        ji/graphic/jiImageButton.e:Ljava/awt/Image;
        //   996: iload           5
        //   998: iload           6
        //  1000: aload_0        
        //  1001: invokevirtual   java/awt/Graphics.drawImage:(Ljava/awt/Image;IILjava/awt/image/ImageObserver;)Z
        //  1004: pop            
        //  1005: goto            1047
        //  1008: aload_1        
        //  1009: aload_0        
        //  1010: getfield        ji/graphic/jiImageButton.h:Ljava/awt/Image;
        //  1013: iload           5
        //  1015: iload           6
        //  1017: aload_0        
        //  1018: invokevirtual   java/awt/Graphics.drawImage:(Ljava/awt/Image;IILjava/awt/image/ImageObserver;)Z
        //  1021: pop            
        //  1022: goto            1047
        //  1025: aload_1        
        //  1026: aload_0        
        //  1027: invokevirtual   ji/graphic/jiImageButton.getBackground:()Ljava/awt/Color;
        //  1030: invokevirtual   java/awt/Graphics.setColor:(Ljava/awt/Color;)V
        //  1033: aload_1        
        //  1034: iconst_0       
        //  1035: iconst_0       
        //  1036: aload_3        
        //  1037: getfield        java/awt/Dimension.width:I
        //  1040: aload_3        
        //  1041: getfield        java/awt/Dimension.height:I
        //  1044: invokevirtual   java/awt/Graphics.fillRect:(IIII)V
        //  1047: aload_0        
        //  1048: aload_0        
        //  1049: getfield        ji/graphic/jiImageButton.r:Z
        //  1052: putfield        ji/graphic/jiImageButton.aw:Z
        //  1055: aload_0        
        //  1056: aload_1        
        //  1057: invokespecial   ji/v1base/bz.paintComponent:(Ljava/awt/Graphics;)V
        //  1060: aload_0        
        //  1061: invokevirtual   ji/v1base/bz.c:()Ljava/awt/Insets;
        //  1064: astore          4
        //  1066: aload_0        
        //  1067: getfield        ji/graphic/jiImageButton.ax:Z
        //  1070: ifeq            1091
        //  1073: aload_0        
        //  1074: getfield        ji/graphic/jiImageButton.r:Z
        //  1077: ifeq            1091
        //  1080: aload_1        
        //  1081: aload_0        
        //  1082: invokevirtual   java/awt/Component.getForeground:()Ljava/awt/Color;
        //  1085: invokevirtual   java/awt/Graphics.setColor:(Ljava/awt/Color;)V
        //  1088: goto            1099
        //  1091: aload_1        
        //  1092: aload_0        
        //  1093: invokevirtual   ji/graphic/jiImageButton.getBackground:()Ljava/awt/Color;
        //  1096: invokevirtual   java/awt/Graphics.setColor:(Ljava/awt/Color;)V
        //  1099: aload_0        
        //  1100: getfield        ji/graphic/jiImageButton.ax:Z
        //  1103: ifeq            1131
        //  1106: aload_0        
        //  1107: getfield        ji/graphic/jiImageButton.e:Ljava/awt/Image;
        //  1110: ifnull          1131
        //  1113: aload_1        
        //  1114: iconst_1       
        //  1115: iconst_1       
        //  1116: aload_3        
        //  1117: getfield        java/awt/Dimension.width:I
        //  1120: iconst_3       
        //  1121: isub           
        //  1122: aload_3        
        //  1123: getfield        java/awt/Dimension.height:I
        //  1126: iconst_3       
        //  1127: isub           
        //  1128: invokestatic    ji/util/d.c:(Ljava/awt/Graphics;IIII)V
        //  1131: invokestatic    ji/util/d.ew:()V
        //  1134: jsr             1155
        //  1137: goto            1214
        //  1140: astore_3       
        //  1141: jsr             1155
        //  1144: goto            1214
        //  1147: astore          8
        //  1149: jsr             1155
        //  1152: aload           8
        //  1154: athrow         
        //  1155: astore          9
        //  1157: aload_0        
        //  1158: getfield        ji/graphic/jiImageButton.az:Ljava/awt/event/ActionEvent;
        //  1161: ifnull          1212
        //  1164: aload_0        
        //  1165: getfield        ji/graphic/jiImageButton.a0:Z
        //  1168: ifne            1212
        //  1171: aload_0        
        //  1172: iconst_1       
        //  1173: putfield        ji/graphic/jiImageButton.a0:Z
        //  1176: aload_0        
        //  1177: aload_0        
        //  1178: getfield        ji/graphic/jiImageButton.az:Ljava/awt/event/ActionEvent;
        //  1181: invokevirtual   ji/graphic/jiImageButton.a:(Ljava/awt/event/ActionEvent;)V
        //  1184: aload_0        
        //  1185: aconst_null    
        //  1186: putfield        ji/graphic/jiImageButton.az:Ljava/awt/event/ActionEvent;
        //  1189: jsr             1203
        //  1192: goto            1212
        //  1195: astore          10
        //  1197: jsr             1203
        //  1200: aload           10
        //  1202: athrow         
        //  1203: astore          11
        //  1205: aload_0        
        //  1206: iconst_0       
        //  1207: putfield        ji/graphic/jiImageButton.a0:Z
        //  1210: ret             11
        //  1212: ret             9
        //  1214: jsr             1228
        //  1217: goto            1237
        //  1220: astore          12
        //  1222: jsr             1228
        //  1225: aload           12
        //  1227: athrow         
        //  1228: astore          13
        //  1230: aload_0        
        //  1231: iconst_0       
        //  1232: putfield        ji/graphic/jiImageButton.a2:Z
        //  1235: ret             13
        //  1237: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  198    359    362    367    Ljava/lang/Exception;
        //  395    436    439    441    Ljava/lang/Exception;
        //  70     1134   1140   1147   Ljava/lang/Exception;
        //  70     1147   1147   1155   Any
        //  1171   1195   1195   1203   Any
        //  5      1220   1220   1228   Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Inconsistent stack size at #1237 (coming from #1165).
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2181)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:108)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:210)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public final void update(final Graphics graphics) {
        if (this.a()) {
            super.update(graphics);
        }
        else {
            this.paintComponent(graphics);
        }
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        this.bh = true;
        this.processEntered(mouseEvent.getModifiers());
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.bh = false;
        this.processExited(mouseEvent.getModifiers());
    }
    
    protected Dimension l() {
        final Dimension preferredSize = this.getPreferredSize();
        return new Dimension(preferredSize.width - 6 - 2, preferredSize.height - 6 - 2);
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        if (this.s) {
            this.m = this.getState();
        }
        this.u = true;
        this.processPress(mouseEvent.getModifiers());
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        try {
            final Rectangle bounds = this.getBounds();
            bounds.x = 0;
            bounds.y = 0;
            if (!ji.util.e.a(bounds, mouseEvent.getPoint())) {
                return;
            }
        }
        catch (Exception ex) {}
        this.m = -1;
        this.processRelease(mouseEvent.getModifiers());
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        this.y = false;
        if (keyEvent.getKeyCode() == 10) {
            this.y = true;
            this.processPress(keyEvent.getModifiers());
        }
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == 10 && this.y) {
            final boolean u = this.u;
            this.u = true;
            this.processRelease(keyEvent.getModifiers());
            this.u = u;
        }
        this.y = false;
    }
    
    public void transferFocus() {
    }
    
    public void focusGained(final FocusEvent focusEvent) {
        if (!this.ax) {
            this.r();
            this.ax = true;
            this.repaint();
        }
    }
    
    public void focusLost(final FocusEvent focusEvent) {
        if (this.ax) {
            this.s();
            this.ax = false;
            if (this.r && !this.isSpacer()) {
                this.v();
            }
            this.repaint();
        }
    }
    
    private final void r() {
        ji.util.d.a(10, this);
    }
    
    private final void s() {
        ji.util.d.b(10, this);
    }
    
    public void processEntered(final int n) {
        this.u = true;
        if (this.r && !this.isSpacer()) {
            this.u();
        }
    }
    
    public void processExited(final int n) {
        this.u = false;
        if (this.r && !this.isSpacer()) {
            this.t = true;
            this.v();
            this.processRelease(n);
        }
    }
    
    private final boolean t() {
        return ji.util.d.me <= 0 || System.currentTimeMillis() - ji.util.d.me > ji.util.d.mf;
    }
    
    public final void setAllowModifiers(final boolean ay) {
        this.ay = ay;
    }
    
    private final boolean b(final int n) {
        return ((n & 0x2) <= 0 && (n & 0x1) <= 0 && n == 16) || this.ay;
    }
    
    public void processPress(final int n) {
        if (!this.t()) {
            return;
        }
        if (this.r && !this.isSpacer()) {
            this.v = true;
            this.w = this.a;
            if (this.b(n)) {
                if (this.a != 1) {
                    this.v();
                    this.a = 1;
                    this.u();
                    this.t = !this.s;
                }
                else {
                    this.a(this.a, 7);
                    this.t = true;
                }
            }
            if (ji.util.e.at()) {
                ji.util.e.b(this);
            }
            this.repaint();
        }
    }
    
    public void processRelease(final int n) {
        if (!this.t()) {
            return;
        }
        if (this.r && !this.isSpacer()) {
            if (this.v) {
                this.v = false;
                if (this.b(n) && this.t) {
                    if (this.s && this.m >= 0) {
                        this.a = this.m;
                    }
                    else if (this.a == 2) {
                        this.a = 1;
                    }
                    else {
                        this.a = 2;
                    }
                }
            }
            this.a(this.a, 8);
            this.repaint();
            this.t = true;
            if (this.n() || this.z) {
                this.c(n);
            }
            this.z = false;
        }
    }
    
    private final void c(final int n) {
        this.az = new ActionEvent(this, 1001, this.ab, n);
    }
    
    private final void u() {
        this.k = true;
        if (this.q && this.ad) {
            if (this.a != 1 || this.f != null) {
                this.a(2, 9);
                this.repaint();
            }
        }
        else if (this.f != null) {
            this.repaint();
        }
    }
    
    private final void v() {
        this.k = false;
        if (this.q && this.a != 1) {
            this.a(0, 10);
            this.repaint();
        }
    }
    
    public void setId(final String s) {
        super.setId(s);
        this.ab = s;
        if (this.isSpacer()) {
            this.setToggle(false);
            this.setState(2);
            this.setEnabled(false);
            this.setFlat(true);
            this.x = false;
            this.q();
            if (this.e != null) {
                this.e.flush();
            }
            if (this.h != null) {
                this.h.flush();
            }
            if (this.f != null) {
                this.f.flush();
            }
            this.e = null;
            this.f = null;
            this.h = null;
        }
    }
    
    public String getId() {
        return this.ab;
    }
    
    public final void removeActionListener(final ActionListener actionListener) {
        if (this.aa != null && this.aa.contains(actionListener)) {
            this.aa.removeElement(actionListener);
        }
    }
    
    public final void addActionListener(final ActionListener actionListener) {
        if (this.aa == null) {
            this.aa = new Vector(2);
        }
        if (!this.aa.contains(actionListener)) {
            this.aa.addElement(actionListener);
        }
    }
    
    protected final void a(final ActionEvent actionEvent) {
        if (this.aa != null) {
            final Vector aa = this.aa;
            for (int size = aa.size(), i = 0; i < size; ++i) {
                aa.elementAt(i).actionPerformed(actionEvent);
            }
        }
    }
    
    public final void setHelpTextRaised(final String ae) {
        this.ae = ae;
        this.af = null;
        this.w();
    }
    
    public final void setHelpTextRaisedDirect(final String s) {
        this.ae = s;
        this.af = s;
        this.w();
    }
    
    private final void w() {
        try {
            if (this.a()) {
                if (this.a == 2) {
                    this.setToolTipText(this.getHelpTextRaised());
                }
                else {
                    this.setToolTipText(this.getHelpTextInset());
                }
            }
        }
        catch (Exception ex) {}
    }
    
    public void setToolTipText(final String toolTipText) {
        boolean b = true;
        if (this.isSpacerSmall()) {
            b = false;
        }
        else if (this.isSpacerLarge()) {
            b = false;
        }
        else if (this.isSpacer()) {
            b = false;
        }
        if (b) {
            super.setToolTipText(toolTipText);
        }
    }
    
    public final String getHelpTextRaised() {
        if (this.af == null) {
            if (this.l) {
                this.af = this.ae;
            }
            else {
                this.af = ji.util.d.b(ji.util.d.c(this.ae, -1), this.parentId);
            }
        }
        return this.d(this.af);
    }
    
    public final void setHelpTextInset(final String ag) {
        this.ag = ag;
        this.ah = null;
        this.w();
    }
    
    public final void setHelpTextInsetDirect(final String s) {
        this.ag = s;
        this.ah = s;
        this.w();
    }
    
    public final String getHelpTextInset() {
        if (this.ah == null) {
            if (this.l) {
                this.ah = this.ag;
            }
            else {
                this.ah = ji.util.d.b(ji.util.d.c(this.ag, -1), this.parentId);
            }
        }
        return this.d(this.ah);
    }
    
    public final void setHotKey(final String s, final int bf) {
        this.be = ji.util.d.bc(s);
        if (this.be != null && this.be.equals("-")) {
            this.be = null;
        }
        if (this.be != null) {
            this.bf = bf;
        }
    }
    
    public final String getHotKeyText() {
        return this.be;
    }
    
    public final int getHotKey() {
        return this.bf;
    }
    
    private final String d(final String s) {
        return ji.util.d.b(this.getHotKeyText(), this.getHotKey(), s);
    }
    
    public final void setDefaultEnabled(final boolean ai) {
        this.ai = ai;
    }
    
    public final boolean isDefaultEnabled() {
        return this.ai;
    }
    
    public void setBackground(final Color a5) {
        boolean b = true;
        if (this.a5 != null && a5 != null && this.a5.equals(a5)) {
            b = false;
        }
        if (b) {
            super.setBackground(this.a5 = a5);
            try {
                this.setImageFileDisabled(this.d = -1);
            }
            catch (Exception ex) {}
        }
    }
    
    public Color getBackground() {
        if (this.a5 != null) {
            return this.a5;
        }
        return super.getBackground();
    }
    
    public void releaseResources() {
        if (!this.a7) {
            this.a7 = true;
            this.e = null;
            this.f = null;
            this.g = null;
            this.h = null;
            this.bi = null;
            if (this.aa != null) {
                this.aa.removeAllElements();
            }
            this.aa = null;
            ji.util.e.a(this.parentId, this, false);
            this.removeMouseListener(this);
            this.removeKeyListener(this);
            this.removeFocusListener(this);
            this.s();
            super.releaseResources();
        }
    }
    
    public void finalize() {
    }
    
    private class t1 implements Runnable
    {
        boolean a;
        
        private t1() {
            this.a = false;
        }
        
        public void run() {
            jiImageButton.this.f(this.a);
        }
        
        public void a(final boolean a) {
            this.a = a;
        }
    }
    
    class t5 implements Runnable
    {
        int a;
        
        public t5(final int a) {
            this.a = a;
        }
        
        public void run() {
            try {
                jiImageButton.this.a(this.a);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    
    class t4 implements Runnable
    {
        int a;
        int b;
        
        public t4(final int a, final int b) {
            this.a = a;
            this.b = b;
        }
        
        public void run() {
            try {
                jiImageButton.this.b(this.a, this.b);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    
    interface ad8
    {
    }
}
