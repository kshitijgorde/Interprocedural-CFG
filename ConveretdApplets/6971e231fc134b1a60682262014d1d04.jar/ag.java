import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Container;
import java.awt.Insets;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.CardLayout;
import java.awt.Label;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

class ag extends a8
{
    Color H;
    Color B;
    public String[] Q;
    Label aa;
    public a8 D;
    m ab;
    ca I;
    bj X;
    bl O;
    l F;
    ax Y;
    bo L;
    ao Z;
    z P;
    be W;
    ba K;
    t S;
    b0 U;
    ce G;
    h V;
    bn T;
    by J;
    br E;
    av R;
    r C;
    v N;
    b1 M;
    
    public ag() {
        super(false, false, false, false);
        this.Q = new String[] { new String("\uc8fc\uac00\uc774\ud3c9"), new String("\uac70\ub798\ub7c9"), new String("\uc774\ub3d9\ud3c9\uade0Osc"), new String("MACD/Sgl"), new String("MACD Osc"), new String("DMI/ADX"), new String("DI Osc"), new String("RSI"), new String("Stochastic"), new String("Williams%R"), new String("Momentum"), new String("Sonar Mtm"), new String("\uc2ec\ub9ac\ub3c4"), new String("\uc774\uaca9\ub3c4"), new String("VR"), new String("CCI"), new String("MFI"), new String("ROC"), new String("EOM"), new String("Parabolic"), new String("Envelope"), new String("PDO Osc"), new String("Band%B Osc"), new String("Empty") };
        this.H = new Color(153, 153, 153);
        this.B = new Color(160, 160, 154);
        this.aa = new Label("\uc635\uc158\uc124\uc815", 1);
        this.D = new a8(false, false, false, false);
        this.ab = new m();
        this.I = new ca();
        this.X = new bj();
        this.O = new bl();
        this.F = new l();
        this.Y = new ax();
        this.L = new bo();
        this.Z = new ao();
        this.P = new z();
        this.W = new be();
        this.K = new ba();
        this.S = new t();
        this.U = new b0();
        this.G = new ce();
        this.V = new h();
        this.T = new bn();
        this.J = new by();
        this.E = new br();
        this.R = new av();
        this.C = new r();
        this.N = new v();
        this.M = new b1();
        this.setBackground(new Color(255, 255, 255));
        this.D.setLayout(new CardLayout());
        this.D.add(this.ab, this.Q[0]);
        ((CardLayout)this.D.getLayout()).addLayoutComponent(this.ab, this.Q[0]);
        this.D.add(this.I, this.Q[1]);
        ((CardLayout)this.D.getLayout()).addLayoutComponent(this.I, this.Q[1]);
        this.D.add(this.X, this.Q[2]);
        ((CardLayout)this.D.getLayout()).addLayoutComponent(this.X, this.Q[2]);
        this.D.add(this.O, this.Q[3]);
        ((CardLayout)this.D.getLayout()).addLayoutComponent(this.O, this.Q[3]);
        this.D.add(this.F, this.Q[5]);
        ((CardLayout)this.D.getLayout()).addLayoutComponent(this.F, this.Q[5]);
        this.D.add(this.Y, this.Q[7]);
        ((CardLayout)this.D.getLayout()).addLayoutComponent(this.Y, this.Q[7]);
        this.D.add(this.L, this.Q[8]);
        ((CardLayout)this.D.getLayout()).addLayoutComponent(this.L, this.Q[8]);
        this.D.add(this.Z, this.Q[9]);
        ((CardLayout)this.D.getLayout()).addLayoutComponent(this.Z, this.Q[9]);
        this.D.add(this.P, this.Q[10]);
        ((CardLayout)this.D.getLayout()).addLayoutComponent(this.P, this.Q[10]);
        this.D.add(this.W, this.Q[11]);
        ((CardLayout)this.D.getLayout()).addLayoutComponent(this.W, this.Q[11]);
        this.D.add(this.K, this.Q[12]);
        ((CardLayout)this.D.getLayout()).addLayoutComponent(this.K, this.Q[12]);
        this.D.add(this.S, this.Q[13]);
        ((CardLayout)this.D.getLayout()).addLayoutComponent(this.S, this.Q[13]);
        this.D.add(this.U, this.Q[14]);
        ((CardLayout)this.D.getLayout()).addLayoutComponent(this.U, this.Q[14]);
        this.D.add(this.G, this.Q[15]);
        ((CardLayout)this.D.getLayout()).addLayoutComponent(this.G, this.Q[15]);
        this.D.add(this.V, this.Q[16]);
        ((CardLayout)this.D.getLayout()).addLayoutComponent(this.V, this.Q[16]);
        this.D.add(this.T, this.Q[17]);
        ((CardLayout)this.D.getLayout()).addLayoutComponent(this.T, this.Q[17]);
        this.D.add(this.J, this.Q[18]);
        ((CardLayout)this.D.getLayout()).addLayoutComponent(this.J, this.Q[18]);
        this.D.add(this.E, this.Q[19]);
        ((CardLayout)this.D.getLayout()).addLayoutComponent(this.E, this.Q[19]);
        this.D.add(this.C, this.Q[20]);
        ((CardLayout)this.D.getLayout()).addLayoutComponent(this.C, this.Q[20]);
        this.D.add(this.R, this.Q[21]);
        ((CardLayout)this.D.getLayout()).addLayoutComponent(this.R, this.Q[21]);
        this.D.add(this.N, this.Q[22]);
        ((CardLayout)this.D.getLayout()).addLayoutComponent(this.N, this.Q[22]);
        this.D.add(this.M, this.Q[23]);
        ((CardLayout)this.D.getLayout()).addLayoutComponent(this.M, this.Q[23]);
        this.getSize();
        this.setLayout(null);
        this.aa.setBounds(5, 7, 95, 15);
        this.aa.setBackground(this.H);
        this.aa.setForeground(Color.white);
        this.add(this.aa);
        this.D.setBounds(5, 25, 95, 179);
        this.add(this.D);
        this.a(this.Q[1]);
    }
    
    public Insets getInsets() {
        return new Insets(2, 2, 2, 2);
    }
    
    public void a(final String s) {
        ((CardLayout)this.D.getLayout()).show(this.D, s);
    }
    
    public void paint(final Graphics graphics) {
        final Dimension size = this.getSize();
        super.paint(graphics);
        graphics.setColor(this.H);
        graphics.fillRect(1, 1, 103, 24);
        graphics.setColor(this.B);
        graphics.setColor(Color.lightGray);
        graphics.drawLine(0, 0, 0, size.height - 1);
        graphics.drawLine(104, 0, 104, size.height - 1);
        graphics.drawLine(0, size.height - 1, 104, size.height - 1);
    }
}
