// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Date;
import java.awt.Event;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import java.awt.Component;
import java.awt.Color;
import java.awt.Panel;
import java.util.Calendar;
import java.text.DateFormat;
import java.awt.Checkbox;
import java.awt.Choice;
import java.awt.TextField;

public final class E extends T
{
    private TextField a;
    private TextField b;
    private TextField c;
    private K b;
    private cC b;
    protected Z a;
    private D a;
    private D b;
    private TextField d;
    private v a;
    private v b;
    private TextField e;
    private cm a;
    private TextField f;
    private Choice a;
    private Checkbox a;
    private bi a;
    private bi b;
    private DateFormat a;
    private Calendar a;
    private Panel a;
    private Choice b;
    private cr e;
    private cr f;
    
    public final U a() {
        final bZ bz;
        (bz = new bZ(-999, "")).a(new long[] { 2590695664122855424L, 0L });
        bz.b(52, false);
        return bz;
    }
    
    public final void a(final U u) {
        final bZ bz = (bZ)u;
        this.a.a(bz.d);
        if (this.a.b() < 0) {
            this.a.a(0);
        }
        this.a.setText(bz.d);
        if (bz.a != null) {
            this.b.setText(bz.a.toString());
            this.c.setText(bz.a.toString());
            this.e.setText(bz.b);
        }
        else {
            this.b.setText("");
            this.c.setText("");
            this.e.setText("");
        }
        final int i;
        if ((i = u.i) == -999) {
            this.a.a(Color.red);
        }
        else {
            this.a.a(new Color(bz.a));
        }
        if (bz.b != 0) {
            this.b.a(new Color(bz.b));
        }
        else {
            this.b.b();
        }
        if (i < 1000 && i != -999) {
            this.a.disable();
            this.b.enable();
            this.c.enable();
            this.e.enable();
            this.a.enable();
            this.f.enable();
            this.a.disable();
            this.a.enable();
            if (i == 2) {
                this.b.disable();
                this.c.disable();
                this.e.disable();
                this.f.disable();
                this.a.disable();
                this.a.disable();
                this.a.disable();
                this.e.disable();
                this.d.disable();
            }
        }
        else {
            this.b.enable();
            this.c.enable();
            this.e.enable();
            this.a.enable();
            this.a.enable();
            this.f.enable();
            this.a.enable();
            this.a.enable();
            this.a.enable();
            this.e.enable();
            this.d.enable();
        }
        this.a.setState(bz.a(17));
        if (this.a.getState()) {
            this.f.setText(bz.a);
            this.a.select(bz.c);
            this.f.enable();
            this.a.enable();
            this.f.a();
            if (this.f.getText().length() > 0) {
                this.a.a(aS.a(684) + this.a(this.f.getText(), 3, this.a.getSelectedIndex() + 1));
            }
            else {
                this.a.a("                              ");
            }
        }
        else {
            this.f.setText("");
            this.a.a("                              ");
            this.a.select(0);
            this.f.disable();
            this.a.disable();
            this.f.b();
        }
        this.d.setText(bz.c);
        this.b.a(this.b, i != 2);
        this.b.a(this.a, i != 2);
        (this.a = new Z(bz.i, bz.d)).a(bz.a);
        this.b.f();
    }
    
    public final boolean a(final U u) {
        final bZ bz = (bZ)u;
        final String text = this.b.getText();
        final String trim;
        if ((trim = this.a.getText().trim()).length() == 0) {
            new bD(this.a(), aS.a(1), aS.a(223) + aS.a(10), super.a).setVisible(true);
            return false;
        }
        if ("Admin".equalsIgnoreCase(trim)) {
            new bD(this.a(), aS.a(1), aS.a(224), super.a).setVisible(true);
            return false;
        }
        if (this.a(trim, (bZ)u)) {
            new bD(this.a(), aS.a(1), bm.a(aS.a(225), new String[] { trim }), super.a).setVisible(true);
            return false;
        }
        if (text.length() < 3 && u.i != 2) {
            this.b.selectAll();
            new bD(this.a(), aS.a(1), aS.a(25) + aS.a(10), super.a).setVisible(true);
            return false;
        }
        if (this.c != null && !this.c.getText().equals(text)) {
            this.c.selectAll();
            new bD(this.a(), aS.a(1), aS.a(226) + aS.a(10), super.a).setVisible(true);
            return false;
        }
        bz.d = trim;
        bz.a = new aV(text);
        bz.a = this.a.a;
        bz.b = this.b.a;
        String trim2;
        if ((trim2 = this.d.getText().trim()).length() == 0) {
            trim2 = null;
        }
        this.a.b(17, this.a.getState());
        if (!this.a.getState()) {
            this.f.setText("");
            this.a.select(0);
        }
        String text2;
        if ((text2 = this.f.getText()).length() == 0) {
            text2 = null;
        }
        bz.a = text2;
        bz.c = this.a.getSelectedIndex();
        String text3;
        if ((text3 = this.e.getText()).trim().length() == 0) {
            text3 = null;
        }
        bz.b = text3;
        bz.c = trim2;
        bz.d = this.a.a();
        bz.a = (Y)super.a.q.b(bz.d);
        bz.a(this.a.a);
        return true;
    }
    
    public final void a(final cg cg) {
        cg.a(aS.a(74), this.a, 0);
        cg.a(aS.a(156), this.b, 0);
        cg.a(aS.a(77), this.c, 0);
        cg.a(aS.a(449), this.e, 0);
        final Panel panel;
        (panel = new Panel()).setLayout(new FlowLayout(1, 0, 0));
        panel.add(this.a);
        panel.add(this.b);
        panel.add(this.b);
        cg.a(aS.a(157), panel, 0);
        cg.a(aS.a(635), this.d, 0);
        final Panel panel2;
        (panel2 = new Panel()).add(this.f);
        panel2.add(this.a);
        cg.a(aS.a(578), this.f, panel2, 0);
        cg.a(aS.a(579), this.a, this.a, 0);
        cg.a(aS.a(580), this.a, 0);
        this.a.resize(200, 35);
        cg.a(this.a, 1, 1.0f, 0.0f);
        cg.a(new i(this.b), 1, 1.0f, 1.0f);
    }
    
    private boolean a(final String s, final bZ bz) {
        for (int i = 0; i < this.a(); ++i) {
            final U a = this.a(i);
            if (bz != a && a.d.equalsIgnoreCase(s)) {
                return true;
            }
        }
        return false;
    }
    
    public final void b() {
        if (super.a) {
            int n = 0;
            final r r;
            (r = new r(67335, this.b())).e = -1;
            r.d = -1;
            for (int i = 0; i < this.a(); ++i) {
                final Z z;
                if ((z = (Z)this.a(i)).j != 0) {
                    final r r2 = r;
                    final int n2 = n;
                    final long[] a = z.a;
                    final int n3 = n2;
                    final r r3 = r2;
                    if (n3 < -1 || n3 >= r3.b) {
                        throw new ArrayIndexOutOfBoundsException("itemNumber is out-of-bounds: " + n3);
                    }
                    r3.a[(n3 << 1) + 2] = a[0];
                    r3.a[(n3 << 1) + 3] = a[1];
                    r.a(n, 0, z.i);
                    if (!z.a(63)) {
                        r.a(n, 0, z.d);
                        r.a(n, 0, z.a);
                        r.a(n, 1, z.a);
                        r.a(n, 1, z.b);
                        r.a(n, 2, z.a);
                        r.a(n, 3, z.c);
                        r.a(n, 2, z.c);
                        r.a(n, 3, z.d);
                        r.a(n, 4, z.b);
                    }
                    ++n;
                }
            }
            super.a.o(r);
            super.a = false;
        }
    }
    
    public final void a() {
        super.a();
        try {
            for (int i = 0; i < super.a.g.a(); ++i) {
                final bZ bz;
                final int j = (bz = new bZ((Z)super.a.g.a(i))).i;
                bz.a = (Y)super.a.q.b(bz.d);
                bz.a = (j >= 1000);
                this.b(bz);
                super.a.a(bz, j > 1 || (super.a.a(62) && j > 0));
            }
        }
        finally {
            throw loadexception(java.lang.Throwable.class);
        }
    }
    
    public final void b(final U u) {
        super.b(u);
        super.a.a(u, new Color(((bZ)u).a), Color.white, new Color((((bZ)u).b == 0) ? 15658734 : ((bZ)u).b));
    }
    
    public final void c(final U u) {
        super.c(u);
        if (u == null) {
            super.d.b();
        }
        else if (u.i < 3) {
            super.d.b();
        }
        else {
            if (u == super.a.a(0)) {
                if (super.a.a() > 1) {
                    super.d.a();
                }
                super.c.b();
                return;
            }
            if (u == super.a.b(super.a.b() - 1)) {
                super.c.a();
                super.d.b();
                return;
            }
            super.d.a();
            if (super.a.a((Object)u) > 2) {
                super.c.a();
                return;
            }
        }
        super.c.b();
    }
    
    private String a(final String s, final int n, final int n2) {
        final int int1 = Integer.parseInt(s.substring(6));
        final int n3 = Integer.parseInt(s.substring(3, 5)) - 1;
        final int int2 = Integer.parseInt(s.substring(0, 2));
        switch (n) {
            case 0: {
                this.a.set(int1, n3, int2);
                break;
            }
            case 1: {
                this.a.set(int1, n3, int2 + n2);
                break;
            }
            case 2: {
                this.a.set(int1, n3, int2 - n2);
                break;
            }
            case 3: {
                this.a.set(int1, n3 + n2, int2);
                break;
            }
            case 4: {
                this.a.set(int1, n3 - n2, int2);
                break;
            }
        }
        return this.a.format(this.a.getTime());
    }
    
    public final boolean a(final cg cg, final Event event) {
        switch (event.id) {
            case 402: {
                if (event.target == this.f) {
                    switch (event.key) {
                        case 43: {
                            this.f.setText(this.a(this.f.getText(), 1, 1));
                            break;
                        }
                        case 45: {
                            this.f.setText(this.a(this.f.getText(), 2, 1));
                            break;
                        }
                        case 42: {
                            this.f.setText(this.a(this.f.getText(), 3, 1));
                            break;
                        }
                        case 47: {
                            this.f.setText(this.a(this.f.getText(), 4, 1));
                            break;
                        }
                    }
                    if (this.a.getSelectedIndex() < 5) {
                        this.a.a(aS.a(684) + this.a(this.f.getText(), 3, this.a.getSelectedIndex() + 1));
                    }
                    else if (this.a.getSelectedIndex() < 8) {
                        this.a.a(aS.a(684) + this.a(this.f.getText(), 1, this.a.getSelectedIndex() - 5 + 1));
                    }
                    else if (this.a.getSelectedIndex() == 8) {
                        this.a.a(aS.a(684) + this.a(this.f.getText(), 1, 7));
                    }
                    return true;
                }
            }
            case 401: {
                if (event.target == this.f) {
                    if (event.key > 26 && (event.key < 48 || event.key > 57)) {
                        event.key = 0;
                    }
                    return true;
                }
                break;
            }
            case 1001: {
                if (event.target == this.a) {
                    if (this.a.getSelectedIndex() < 5) {
                        this.a.a(aS.a(684) + this.a(this.f.getText(), 3, this.a.getSelectedIndex() + 1));
                    }
                    else if (this.a.getSelectedIndex() < 8) {
                        this.a.a(aS.a(684) + this.a(this.f.getText(), 1, this.a.getSelectedIndex() - 5 + 1));
                    }
                    else if (this.a.getSelectedIndex() == 8) {
                        this.a.a(aS.a(684) + this.a(this.f.getText(), 1, 7));
                    }
                    return true;
                }
                if (event.target == this.e) {
                    final int selectedIndex = this.b.getSelectedIndex();
                    this.a.a(((Z)super.a.g.a((selectedIndex > 0) ? (selectedIndex + 1) : selectedIndex)).a);
                    this.b.f();
                    return true;
                }
                if (event.target == this.f) {
                    this.a(this.f.getText(), 0, 0);
                    final ap ap;
                    (ap = new ap(this.a(), super.a, this.f, cg, this.a)).e();
                    ap.setVisible(true);
                    return true;
                }
                if (event.target == this.a) {
                    if (this.a.getState()) {
                        if (this.f.getText().length() == 0) {
                            this.f.setText(this.a.format(new Date()));
                        }
                        this.f.enable();
                        this.a.enable();
                        this.f.a();
                        if (this.a.getSelectedIndex() < 5) {
                            this.a.a(aS.a(684) + this.a(this.f.getText(), 3, this.a.getSelectedIndex() + 1));
                        }
                        else if (this.a.getSelectedIndex() < 8) {
                            this.a.a(aS.a(684) + this.a(this.f.getText(), 1, this.a.getSelectedIndex() - 5 + 1));
                        }
                        else if (this.a.getSelectedIndex() == 8) {
                            this.a.a(aS.a(684) + this.a(this.f.getText(), 1, 7));
                        }
                    }
                    else {
                        this.f.disable();
                        this.a.disable();
                        this.f.b();
                    }
                    return true;
                }
                break;
            }
            case 7691: {
                if (event.arg == null) {
                    event.arg = "";
                }
                if (!this.a.getState()) {
                    this.f.disable();
                    this.a.disable();
                    break;
                }
                if (this.f.getText().length() == 0) {
                    this.f.setText(this.a.format(new Date()));
                }
                this.f.enable();
                this.a.enable();
                if (this.a.getSelectedIndex() < 5) {
                    this.a.a(aS.a(684) + this.a(this.f.getText(), 3, this.a.getSelectedIndex() + 1));
                    break;
                }
                if (this.a.getSelectedIndex() < 8) {
                    this.a.a(aS.a(684) + this.a(this.f.getText(), 1, this.a.getSelectedIndex() - 5 + 1));
                    break;
                }
                if (this.a.getSelectedIndex() == 8) {
                    this.a.a(aS.a(684) + this.a(this.f.getText(), 1, 7));
                    break;
                }
                break;
            }
        }
        return false;
    }
    
    public E(final cx cx) {
        super(cx, aS.a(96), aS.a(97));
        this.d();
        this.a = new TextField(30);
        this.b = new TextField(15);
        this.c = new TextField(15);
        this.e = new TextField(30);
        this.f = new TextField(10);
        this.d = new TextField(15);
        this.f.setEditable(false);
        this.a = new bi(aS.a(684), (byte)0);
        (this.b = new bi(aS.a(572), (byte)0)).setFont(bk.d);
        this.a = new Choice();
        this.b = new K();
        this.b = new cC("enabled");
        super.b = true;
        this.c.setEchoCharacter('*');
        this.b.setEchoCharacter('*');
        this.a = new v(cx);
        this.b = new v(cx);
        this.a.b = true;
        this.a.a(0);
        this.b.b = true;
        this.b.a(0);
        this.a.add(aS.a(582));
        this.a.add(aS.a(583));
        this.a.add(aS.a(584));
        this.a.add(aS.a(585));
        this.a.add(aS.a(586));
        this.a.add(aS.a(667));
        this.a.add(aS.a(668));
        this.a.add(aS.a(669));
        this.a.add(aS.a(670));
        this.a = new Checkbox(aS.a(581));
        this.a = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        this.a = Calendar.getInstance(Locale.US);
        this.a = new cm();
        this.a.a.a();
        try {
            for (int i = 0; i < cx.q.a(); ++i) {
                final Y y;
                if ((y = (Y)cx.q.a(i)).i != 1) {
                    this.a.a(y);
                }
            }
        }
        finally {
            throw loadexception(java.lang.Throwable.class);
        }
        (this.e = new cr(120, 20)).a(aS.a(588));
        (this.f = new cr(20, 20)).a(aS.a(645));
        this.f.d();
        (this.b = new Choice()).setForeground(Color.black);
        try {
            for (int j = 0; j < super.a.g.a(); ++j) {
                this.b.addItem(((Z)super.a.g.a(j)).d);
            }
        }
        finally {
            throw loadexception(java.lang.Throwable.class);
        }
        if (this.b.getItemCount() > 1) {
            this.b.remove(1);
        }
        (this.a = new Panel()).setLayout(new FlowLayout());
        this.a.add(this.b);
        this.a.add(this.e);
        final I k = new I(aS.a(114), "name");
        this.b.b(this.b);
        this.b.a(true);
        this.b.b(k);
        final I l;
        (l = new I(cs.b, "star")).a(22);
        this.a(super.a, 0);
        super.a.b(0);
        this.a(l, 2);
        l.b(0);
        this.b.c(new D(this, aS.a(26), bm.a(aS.a(227), new String[] { super.a.b() }), 35));
        this.b.c(new D(this, aS.a(115), aS.a(228), 31));
        this.b.c(new D(this, aS.a(116), aS.a(229), 30));
        this.b.c(new D(this, aS.a(117), aS.a(230), 37));
        this.a = new D(this, aS.a(118), aS.a(231), 45);
        this.b.c(this.a);
        ((K)(this.b = new D(this, aS.a(119), aS.a(232), 58))).c(this.b);
        this.b.c(new D(this, aS.a(120), aS.a(233), 60));
        this.b.c(new D(this, aS.a(121), aS.a(234), 59));
        this.b.c(new D(this, aS.a(122), aS.a(235), 29));
        this.b.c(new D(this, aS.a(123), aS.a(236), 38));
        this.b.c(new D(this, aS.a(124), aS.a(237), 27));
        this.b.c(new D(this, aS.a(125), aS.a(238), 28));
        this.b.c(new D(this, aS.a(126), aS.a(239), 36));
        this.b.c(new D(this, aS.a(127), aS.a(240), 52));
        this.b.c(new D(this, aS.a(128), aS.a(241), 49));
        this.b.c(new D(this, aS.a(129), aS.a(242), 50));
        this.b.c(new D(this, aS.a(130), aS.a(243), 57));
        this.b.c(new D(this, aS.a(131), aS.a(244), 53));
        this.b.c(new D(this, aS.a(132), aS.a(245), 26));
        this.b.c(new D(this, aS.a(133), aS.a(246), 54));
        this.b.c(new D(this, aS.a(134), aS.a(247), 46));
        this.b.c(new D(this, aS.a(135), aS.a(248), 55));
        this.b.c(new D(this, aS.a(136), aS.a(249), 47));
        this.b.c(new D(this, aS.a(137), aS.a(250), 56));
        this.b.c(new D(this, aS.a(589), aS.a(590), 10));
        this.b.c(new D(this, aS.a(138), aS.a(251), 44));
        this.b.c(new D(this, aS.a(139), aS.a(252), 34));
        this.b.c(new D(this, aS.a(140), aS.a(253), 42));
        this.b.c(new D(this, aS.a(141), aS.a(254), 43));
        this.b.c(new D(this, aS.a(9), aS.a(255), 24));
        this.b.c(new D(this, aS.a(591), aS.a(592), 25));
        this.b.c(new D(this, aS.a(142), aS.a(256), 22));
        this.b.c(new D(this, aS.a(143), aS.a(257), 21));
        this.b.c(new D(this, aS.a(567), aS.a(568), 20));
        this.b.c(new D(this, aS.a(145), aS.a(259), 5));
        this.b.c(new D(this, aS.a(593), aS.a(594), 32));
        this.b.c(new D(this, aS.a(595), aS.a(596), 4));
        this.b.c(new D(this, aS.a(597), aS.a(598), 3));
        this.b.c(new D(this, aS.a(146), aS.a(260), 6));
        this.b.c(new D(this, aS.a(147), aS.a(261), 7));
        this.b.c(new D(this, aS.a(148), aS.a(262), 8));
        this.b.c(new D(this, aS.a(149), aS.a(263), 9));
        this.b.c(new D(this, aS.a(150), aS.a(264), 11));
        this.b.c(new D(this, aS.a(151), aS.a(265), 12));
        this.b.c(new D(this, aS.a(152), aS.a(266), 13));
        this.b.c(new D(this, aS.a(153), aS.a(267), 16));
        this.b.c(new D(this, aS.a(154), aS.a(268), 15));
        this.b.c(new D(this, aS.a(155), aS.a(269), 14));
        this.b.c(new D(this, aS.a(666), aS.a(""), 2));
        this.b.c(new D(this, aS.a(599), aS.a(600), 19));
        this.b.c(new D(this, aS.a(624), aS.a(625), 1));
        this.b.c(new D(this, aS.a(639), aS.a(640), 39));
        this.b.c(new D(this, aS.a(629), aS.a(630), 69));
        this.b.c(new D(this, aS.a(671), aS.a(""), 65));
        this.b.c(new D(this, aS.a(672), aS.a(""), 66));
        this.b.c(new D(this, aS.a(673), aS.a(""), 67));
        this.b.c(new D(this, aS.a(674), aS.a(""), 68));
        this.b.c(new D(this, aS.a(675), aS.a(""), 70));
        this.b.c(new D(this, aS.a(650), aS.a(""), 71));
        this.b.c(new D(this, aS.a(676), aS.a(""), 72));
        this.b.c(new D(this, aS.a("Change User Icon"), aS.a(""), 73));
        this.b.resize(100, this.b.c() * 10);
        this.b.b(0);
    }
}
