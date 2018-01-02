import java.awt.FontMetrics;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Component;

// 
// Decompiled by Procyon v0.5.30
// 

class ad extends Component
{
    public boolean D;
    public boolean f;
    public boolean _flddo;
    public boolean _fldvoid;
    public boolean l;
    public boolean m;
    public boolean _fldif;
    public boolean _fldfor;
    public boolean g;
    public int b;
    public int z;
    public int r;
    public int _fldcase;
    public int c;
    public int B;
    public int s;
    public int _fldchar;
    public int _fldelse;
    public int t;
    public int o;
    public int _fldnew;
    public int v;
    public int _fldlong;
    public int _fldtry;
    public int p;
    public int _fldnull;
    public int w;
    public int q;
    public int _fldbyte;
    public int G;
    public int j;
    public int e;
    public int C;
    public int _fldgoto;
    public int u;
    public int n;
    public int _fldint;
    public int i;
    public int F;
    public int A;
    public int d;
    public int a;
    public int k;
    public int h;
    public int E;
    
    public ad() {
        this.D = true;
        this.f = false;
        this._flddo = false;
        this._fldvoid = false;
        this.l = false;
        this.m = false;
        this._fldif = false;
        this._fldfor = false;
        this.g = false;
    }
    
    @Override
    public void invalidate() {
        super.invalidate();
    }
    
    @Override
    public void update(final Graphics graphics) {
        this.invalidate();
        this.paint(graphics);
    }
    
    @Override
    public void paint(final Graphics graphics) {
        this.getSize();
        super.paint(graphics);
        this.a(graphics);
    }
    
    void a(final Graphics graphics) {
        this.getSize();
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        final Color color = new Color(102, 102, 102);
        final Color color2 = new Color(178, 178, 178);
        final Color color3 = new Color(153, 153, 153);
        final Color color4 = new Color(160, 160, 154);
        final int n = 15;
        final int n2 = 10;
        final int n3 = 52;
        final int height = fontMetrics.getHeight();
        graphics.setColor(color3);
        graphics.fillRect(1, 1, 103, 24);
        final int n4 = n2;
        graphics.setColor(Color.white);
        graphics.drawString("\uba54\uc778\ucc28\ud2b8\uc124\uc815", n3 - fontMetrics.stringWidth("\uba54\uc778\ucc28\ud2b8\uc124\uc815") / 2, n4 + height / 2);
        graphics.setColor(color4);
        graphics.drawLine(0, 0, 0, 179);
        graphics.drawLine(104, 0, 104, 179);
        graphics.drawLine(0, 0, 104, 0);
        final int n5 = 35;
        if (this.D) {
            graphics.setColor(color);
        }
        else {
            graphics.setColor(color2);
        }
        final int stringWidth = fontMetrics.stringWidth("\ud22c\uc790\uc758\uacac");
        graphics.drawString("\ud22c\uc790\uc758\uacac", n3 - stringWidth / 2, n5 + height / 2);
        this.b = n3 - stringWidth / 2;
        this.z = n3 + stringWidth / 2;
        this.r = n5 - height / 2;
        this._fldcase = n5 + height / 2;
        final int n6 = n5 + n;
        if (this.f) {
            graphics.setColor(color);
        }
        else {
            graphics.setColor(color2);
        }
        final int stringWidth2 = fontMetrics.stringWidth("\ubaa9\ud45c\uc8fc\uac00");
        graphics.drawString("\ubaa9\ud45c\uc8fc\uac00", n3 - stringWidth2 / 2, n6 + height / 2);
        this.c = n3 - stringWidth2 / 2;
        this.B = n3 + stringWidth2 / 2;
        this.s = n6 - height / 2;
        this._fldchar = n6 + height / 2;
        final int n7 = n6 + n;
        if (this._flddo) {
            graphics.setColor(color);
        }
        else {
            graphics.setColor(color2);
        }
        final int stringWidth3 = fontMetrics.stringWidth("EPS2011");
        graphics.drawString("EPS2011", n3 - stringWidth3 / 2, n7 + height / 2);
        this._fldelse = n3 - stringWidth3 / 2;
        this.t = n3 + stringWidth3 / 2;
        this.o = n7 - height / 2;
        this._fldnew = n7 + height / 2;
        final int n8 = n7 + n;
        if (this._fldvoid) {
            graphics.setColor(color);
        }
        else {
            graphics.setColor(color2);
        }
        final int stringWidth4 = fontMetrics.stringWidth("EPS2012");
        graphics.drawString("EPS2012", n3 - stringWidth4 / 2, n8 + height / 2);
        this.v = n3 - stringWidth4 / 2;
        this._fldlong = n3 + stringWidth4 / 2;
        this._fldtry = n8 - height / 2;
        this.p = n8 + height / 2;
        final int n9 = n8 + n;
        if (this.l) {
            graphics.setColor(color);
        }
        else {
            graphics.setColor(color2);
        }
        final int stringWidth5 = fontMetrics.stringWidth("EPS2013");
        graphics.drawString("EPS2013", n3 - stringWidth5 / 2, n9 + height / 2);
        this._fldnull = n3 - stringWidth5 / 2;
        this.w = n3 + stringWidth5 / 2;
        this.q = n9 - height / 2;
        this._fldbyte = n9 + height / 2;
        final int n10 = n9 + (int)(n * 1.7);
        if (this.m) {
            graphics.setColor(color);
        }
        else {
            graphics.setColor(color2);
        }
        final int stringWidth6 = fontMetrics.stringWidth("\uc8fc\uac00\uc774\ub3d9\ud3c9\uade0\uc120");
        graphics.drawString("\uc8fc\uac00\uc774\ub3d9\ud3c9\uade0\uc120", n3 - stringWidth6 / 2, n10 + height / 2);
        this.G = n3 - stringWidth6 / 2;
        this.j = n3 + stringWidth6 / 2;
        this.e = n10 - height / 2;
        this.C = n10 + height / 2;
        final int n11 = n10 + n;
        if (this._fldif) {
            graphics.setColor(color);
        }
        else {
            graphics.setColor(color2);
        }
        final int stringWidth7 = fontMetrics.stringWidth("Parabolic");
        graphics.drawString("Parabolic", n3 - stringWidth7 / 2, n11 + height / 2);
        this._fldgoto = n3 - stringWidth7 / 2;
        this.u = n3 + stringWidth7 / 2;
        this.n = n11 - height / 2;
        this._fldint = n11 + height / 2;
        final int n12 = n11 + n;
        if (this._fldfor) {
            graphics.setColor(color);
        }
        else {
            graphics.setColor(color2);
        }
        final int stringWidth8 = fontMetrics.stringWidth("Envelope");
        graphics.drawString("Envelope", n3 - stringWidth8 / 2, n12 + height / 2);
        this.i = n3 - stringWidth8 / 2;
        this.F = n3 + stringWidth8 / 2;
        this.A = n12 - height / 2;
        this.d = n12 + height / 2;
        final int n13 = n12 + n;
        if (this.g) {
            graphics.setColor(color);
        }
        else {
            graphics.setColor(color2);
        }
        final int stringWidth9 = fontMetrics.stringWidth("Bolinger Band");
        graphics.drawString("Bolinger Band", n3 - stringWidth9 / 2, n13 + height / 2);
        this.a = n3 - stringWidth9 / 2;
        this.k = n3 + stringWidth9 / 2;
        this.h = n13 - height / 2;
        this.E = n13 + height / 2;
    }
}
