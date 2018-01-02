import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.util.Vector;
import java.awt.Component;
import java.applet.AudioClip;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class FreeCell extends Applet
{
    Image Xa;
    Graphics Ya;
    Dimension size;
    FontMetrics Za;
    Font _b;
    Image ab;
    Image bb;
    Image s;
    Color cb;
    Color db;
    String eb;
    boolean fb;
    boolean gb;
    boolean hb;
    Rectangle ib;
    Rectangle jb;
    AudioClip kb;
    AudioClip lb;
    AudioClip G4;
    AudioClip H4;
    AudioClip I4;
    int J4;
    int K4;
    int L4;
    int M4;
    break[] N4;
    case[] O4;
    catch[] P4;
    class[] Q4;
    const R4;
    class S4;
    continue z;
    default T4;
    private static String qb = "\u4406\u440e\u4418\u4418\u440a\u440c\u440e\u440d\u4402\u4407\u440e";
    private static String rb = "\u4406\u440e\u4418\u4418\u440a\u440c\u440e\u4418\u4434\u440e\u4405\u4445\u440f\u440a\u441f";
    private static String sb = "\u441c\u4402\u4405";
    private static String tb = "\u4403\u4402\u441f";
    private static String _ = "\u4402\u4407\u4407\u440e\u440c\u440a\u4407";
    private static String a = "\u4418\u4402\u4407\u440e\u4405\u441f\u4445\u440a\u441e";
    private static String b = "\u4409\u440a\u4408\u4400\u4428\u4404\u4407\u4404\u4419";
    private static String C = "\u440d\u4404\u4405\u441f\u4428\u4404\u4407\u4404\u4419";
    private static String D = "\u442a\u4419\u4402\u440a\u4407";
    private static String E = "\u4402\u4405\u4418\u441f\u4419\u441e\u4408\u4434\u4406\u4418\u440c";
    private static String F = "\u440d\u4404\u4405\u441f\u4418\u4402\u4411\u440e";
    private static String G = "\u440d\u4404\u4405\u4418\u441f\u4412\u4407\u440e";
    private static String H = "\u4408\u440a\u4419\u440f\u4418\u4403\u440e\u440e\u441f\u4445\u440c\u4402\u440d";
    private static String I = "\u440d\u4419\u440e\u440e\u4408\u440e\u4407\u4407\u4434\u4409\u440c\u4445\u440c\u4402\u440d";
    private static String J = "";
    private static String K = "\u4404\u4408\u441e\u441b\u4402\u440e\u440f\u4434\u4406\u4418\u440c";
    private static String L = "\u4402\u4407\u4407\u440e\u440c\u440a\u4407\u4434\u4406\u4418\u440c";
    private static String M = "\u4402\u4418\u4418\u441f\u441e\u4408\u4400\u4434\u4406\u4418\u440c";
    private static String N = "\u441c\u4402\u4405\u4405\u4402\u4405\u440c\u4434\u4406\u4418\u440c";
    
    public void init() {
        String s = this.getParameter(FreeCell.qb);
        if (s == null) {
            s = FreeCell.rb;
        }
        this.z = new continue(this.getCodeBase(), s);
        this.T4 = new default(this, this.z);
        this.kb = this.T4._(FreeCell.sb);
        this.G4 = this.T4._(FreeCell.tb);
        this.H4 = this.T4._(FreeCell._);
        this.lb = this.H4;
        if (this.T4.d()) {
            (this.I4 = this.T4._(FreeCell.a)).loop();
        }
        this.size = this.getSize();
        this.db = this.T4.b(FreeCell.b, new Color(5416274));
        this.cb = this.T4.b(FreeCell.C, Color.white);
        this.setBackground(this.db);
        this.setFont(new Font(FreeCell.D, 1, 12));
        this.Za = this.getFontMetrics(this.getFont());
    }
    
    private boolean j() {
        if (this.T4._() && !this.T4.b(default.x)) {
            this.T4._(true);
            return false;
        }
        if (!this.k() || !this.l()) {
            return false;
        }
        this.Xa = this.createImage(this.size.width, this.size.height);
        this.Ya = this.Xa.getGraphics();
        this._b = new Font(FreeCell.D, 1, 13);
        this.Za = this.getFontMetrics(this._b);
        final Image[][] array = new Image[4][12];
        final Dimension dimension = new Dimension(51, 66);
        final do do1 = new do(this.ab, true, false, dimension, this);
        final Image[][] _ = do1._();
        this.s = do1._();
        this.Q4 = new class[52];
        for (int i = 0; i < 52; ++i) {
            this.Q4[i] = new class(_[i / 13][i % 13], 0, 0, dimension, i % 13, i / 26, i / 13);
        }
        this.N4 = new break[4];
        for (int j = 0; j < 4; ++j) {
            this.N4[j] = new break(this.L4 + j * (dimension.width + this.M4), this.L4, dimension);
        }
        this.O4 = new case[4];
        for (int k = 4; k < 8; ++k) {
            this.O4[k - 4] = new case(this.L4 + k * (dimension.width + this.M4), this.L4, dimension);
        }
        this.P4 = new catch[8];
        for (int l = 0; l < 8; ++l) {
            this.P4[l] = new catch(this.L4 + l * (dimension.width + this.M4), 2 * this.L4 + dimension.height, dimension, 18, 144);
        }
        this.enableEvents(16L);
        this.eb = this.z.i(FreeCell.E);
        return !this.T4.f();
    }
    
    private boolean k() {
        this.J4 = this.T4.a(FreeCell.F, 12);
        this.K4 = this.T4.b(FreeCell.G, 1);
        return !this.T4.f();
    }
    
    private boolean l() {
        final Vector<Image> vector = new Vector<Image>();
        vector.addElement(this.ab = this.getImage(this.getCodeBase(), FreeCell.H));
        vector.addElement(this.bb = this.getImage(this.getCodeBase(), FreeCell.I));
        this.T4._(vector, 0);
        return !this.T4.f();
    }
    
    public void f() {
        for (int i = 0; i < 52; ++i) {
            final int a = extends.a(52);
            final class class1 = this.Q4[a];
            this.Q4[a] = this.Q4[i];
            this.Q4[i] = class1;
        }
    }
    
    private void g() {
        this.hb = false;
        this.f();
        this.h();
        this._(FreeCell.J);
        int n = 0;
        for (int i = 0; i < this.P4.length; ++i) {
            this.P4[i].reset();
            for (int n2 = (i < 4) ? 7 : 6, j = 0; j < n2; ++j) {
                this.P4[i].b(this.Q4[n++]);
            }
        }
        for (int k = 0; k < this.O4.length; ++k) {
            this.O4[k].b();
        }
        for (int l = 0; l < this.N4.length; ++l) {
            this.N4[l].b();
        }
    }
    
    private void b(final Graphics graphics) {
        this.T4._(graphics);
        final String b = this.z.b();
        graphics.setColor(this.cb);
        graphics.drawString(b, this.T4.a(b, true, graphics), this.T4.a(b, false, graphics));
        if (this.j()) {
            this.fb = true;
            this.T4._(graphics);
            this.repaint();
        }
        else {
            this.T4._(graphics);
            String s;
            if (this.T4.e()) {
                s = default.v;
            }
            else {
                s = this.z._();
            }
            graphics.setColor(this.cb);
            graphics.drawString(s, this.T4.a(s, true, graphics), this.T4.a(s, false, graphics));
        }
    }
    
    public void update(final Graphics graphics) {
        if (!this.fb) {
            this.b(graphics);
            return;
        }
        this.Ya.drawImage(this.bb, 0, 0, this);
        if (this.hb) {
            final Dimension dimension = new Dimension(51, 66);
            for (int i = 0; i < this.P4.length; ++i) {
                this.P4[i].reset();
                for (int n = (i < 4) ? 7 : 6, j = 0; j < n; ++j) {
                    this.Ya.drawImage(this.s, this.L4 + i * (dimension.width + this.M4), 2 * this.L4 + dimension.height + j * 18, this);
                }
            }
        }
        else {
            for (int k = 0; k < this.N4.length; ++k) {
                this.N4[k].a(this.Ya);
            }
            for (int l = 0; l < this.O4.length; ++l) {
                this.O4[l].a(this.Ya);
            }
            for (int n2 = 0; n2 < this.P4.length; ++n2) {
                this.P4[n2].a(this.Ya);
            }
        }
        if (this.eb != null) {
            this.Ya.setColor(this.cb);
            this.Ya.setFont(this._b);
            this.Ya.drawString(this.eb, default._(this.eb, true, this.ib, this.Ya), this.ib.y);
        }
        graphics.drawImage(this.Xa, 0, 0, this);
        if (this.T4.f()) {
            this.showStatus(this.z.a());
        }
    }
    
    public void paint(final Graphics graphics) {
        this.update(graphics);
    }
    
    public void processMouseEvent(final MouseEvent mouseEvent) {
        switch (mouseEvent.getID()) {
            case 501: {
                this._(mouseEvent, mouseEvent.getX(), mouseEvent.getY());
                break;
            }
            case 502: {
                this.a(mouseEvent, mouseEvent.getX(), mouseEvent.getY());
                break;
            }
            default: {
                mouseEvent.consume();
                break;
            }
        }
    }
    
    public boolean _(final MouseEvent mouseEvent, final int n, final int n2) {
        if (this.jb.contains(n, n2)) {
            this.g();
            return true;
        }
        this._(FreeCell.J);
        this.gb = false;
        final const a;
        if ((a = this.a(n, n2)) != null) {
            if (mouseEvent.isMetaDown()) {
                final break a2;
                if ((a2 = this.a()) != null) {
                    final class b = a.b();
                    if (b != null && a2.b(b)) {
                        this.gb = true;
                        a.b();
                    }
                }
                else {
                    this._(this.z.i(FreeCell.K));
                    if (this.H4 != null) {
                        this.H4.play();
                    }
                }
                this.h();
                return true;
            }
            if (this.R4 == null) {
                this.S4 = a.b();
                if (this.S4 != null) {
                    this.S4.a(true);
                    this.R4 = a;
                }
            }
            else {
                if (!this.R4.equals(a) && this.S4 != null) {
                    if (a.b(this.S4)) {
                        this.gb = true;
                        this.R4.b();
                    }
                    else if (this.R4 instanceof catch && a instanceof catch) {
                        final catch catch1 = (catch)this.R4;
                        final catch catch2 = (catch)a;
                        final class[] _;
                        if ((_ = catch1._(catch2, this.j())) != null) {
                            for (int i = 0; i < _.length; ++i) {
                                catch2.b(_[i]);
                                catch1.b();
                            }
                            this.gb = true;
                        }
                        else {
                            this._(this.z.i(FreeCell.L));
                            if (this.H4 != null) {
                                this.H4.play();
                            }
                        }
                    }
                    else {
                        this._(this.z.i(FreeCell.L));
                        if (this.H4 != null) {
                            this.H4.play();
                        }
                    }
                }
                this.h();
            }
        }
        else {
            this.h();
        }
        return true;
    }
    
    public void _(final String eb) {
        this.eb = eb;
    }
    
    private void h() {
        if (this.S4 != null) {
            this.S4.a(false);
        }
        this.S4 = null;
        this.R4 = null;
    }
    
    public boolean a(final MouseEvent mouseEvent, final int n, final int n2) {
        this.i();
        if (this.m()) {
            this._(this.z.i(FreeCell.M));
            if (this.lb != null) {
                this.lb.play();
            }
        }
        else if (this.h()) {
            this._(this.z.i(FreeCell.N));
            if (this.kb != null) {
                this.kb.play();
            }
        }
        else if (this.gb && this.G4 != null) {
            this.G4.play();
        }
        this.repaint();
        return true;
    }
    
    private const a(final int n, final int n2) {
        for (int i = 0; i < this.P4.length; ++i) {
            if (this.P4[i].contains(n, n2)) {
                return this.P4[i];
            }
        }
        for (int j = 0; j < this.N4.length; ++j) {
            if (this.N4[j].contains(n, n2)) {
                return this.N4[j];
            }
        }
        for (int k = 0; k < this.O4.length; ++k) {
            if (this.O4[k].contains(n, n2)) {
                return this.O4[k];
            }
        }
        return null;
    }
    
    private break a() {
        for (int i = 0; i < this.N4.length; ++i) {
            if (this.N4[i].i()) {
                return this.N4[i];
            }
        }
        return null;
    }
    
    private int j() {
        int n = 0;
        for (int i = 0; i < this.N4.length; ++i) {
            if (this.N4[i].i()) {
                ++n;
            }
        }
        return n;
    }
    
    private void i() {
        boolean b;
        do {
            b = false;
            for (int i = 0; i < this.P4.length; ++i) {
                for (int j = 0; j < this.O4.length; ++j) {
                    final class b2 = this.P4[i].b();
                    if (this.O4[j]._(b2) && this.a(b2)) {
                        this.O4[j].b(b2);
                        this.P4[i].b();
                        b = true;
                    }
                }
            }
            for (int k = 0; k < this.N4.length; ++k) {
                for (int l = 0; l < this.O4.length; ++l) {
                    final class b3 = this.N4[k].b();
                    if (this.O4[l]._(b3) && this.a(b3)) {
                        this.O4[l].b(b3);
                        this.N4[k].b();
                        b = true;
                    }
                }
            }
        } while (b);
    }
    
    private boolean a(final class class1) {
        for (int i = 0; i < this.P4.length; ++i) {
            final class[] _ = this.P4[i]._();
            for (int j = 0; j < _.length; ++j) {
                if (this.a(class1, _[j])) {
                    return false;
                }
            }
        }
        for (int k = 0; k < this.N4.length; ++k) {
            final class b = this.N4[k].b();
            if (b != null && this.a(class1, b)) {
                return false;
            }
        }
        return true;
    }
    
    private final boolean a(final class class1, final class class2) {
        return class2.getID() != 0 && class2.getID() < class1.getID() && ((class1.getID() - class2.getID()) % 2 != 0 || class1.a() == class2.a()) && ((class1.getID() - class2.getID()) % 2 != 1 || class1.a() != class2.a());
    }
    
    public boolean m() {
        if (this.j() > 0) {
            return false;
        }
        for (int i = 0; i < this.P4.length; ++i) {
            if (this.P4[i].isEmpty()) {
                return false;
            }
            final class b = this.P4[i].b();
            for (int j = 0; j < this.P4.length; ++j) {
                if (i != j && this.P4[j]._(b)) {
                    return false;
                }
            }
            for (int k = 0; k < this.O4.length; ++k) {
                if (this.O4[k]._(b)) {
                    return false;
                }
            }
        }
        for (int l = 0; l < this.N4.length; ++l) {
            final class b2 = this.N4[l].b();
            for (int n = 0; n < this.P4.length; ++n) {
                if (this.P4[n]._(b2)) {
                    return false;
                }
            }
            for (int n2 = 0; n2 < this.O4.length; ++n2) {
                if (this.O4[n2]._(b2)) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public boolean h() {
        for (int i = 0; i < this.O4.length; ++i) {
            if (!this.O4[i].h()) {
                return false;
            }
        }
        return true;
    }
    
    public void destroy() {
        default._((Object)this.kb);
        default._((Object)this.G4);
        default._((Object)this.H4);
        default._((Object)this.I4);
    }
    
    public FreeCell() {
        this.hb = true;
        this.ib = new Rectangle(6, 340, 255, 40);
        this.jb = new Rectangle(434, 330, 40, 16);
        this.L4 = 17;
        this.M4 = 9;
    }
    
    private static String a(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ '\u446b');
        }
        return new String(array);
    }
    
    static {
        FreeCell.qb = a(FreeCell.qb);
        FreeCell.rb = a(FreeCell.rb);
        FreeCell.sb = a(FreeCell.sb);
        FreeCell.tb = a(FreeCell.tb);
        FreeCell._ = a(FreeCell._);
        FreeCell.a = a(FreeCell.a);
        FreeCell.b = a(FreeCell.b);
        FreeCell.C = a(FreeCell.C);
        FreeCell.D = a(FreeCell.D);
        FreeCell.E = a(FreeCell.E);
        FreeCell.F = a(FreeCell.F);
        FreeCell.G = a(FreeCell.G);
        FreeCell.H = a(FreeCell.H);
        FreeCell.I = a(FreeCell.I);
        FreeCell.J = a(FreeCell.J);
        FreeCell.K = a(FreeCell.K);
        FreeCell.L = a(FreeCell.L);
        FreeCell.M = a(FreeCell.M);
        FreeCell.N = a(FreeCell.N);
    }
}
