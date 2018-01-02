import java.awt.Container;
import java.awt.event.ItemEvent;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Panel;
import java.awt.Label;
import java.awt.Component;
import java.awt.TextField;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.event.ItemListener;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

public class s extends db implements ActionListener, ItemListener
{
    private static final Color p;
    private static final Color d;
    private static int a;
    private static int[][] n;
    private do v;
    private boolean i;
    private Checkbox l;
    private Checkbox b;
    private Checkbox c;
    private Checkbox e;
    private Checkbox f;
    private Checkbox g;
    private Checkbox h;
    private Checkbox j;
    private Checkbox k;
    private TextField m;
    private TextField o;
    private TextField q;
    private TextField r;
    private TextField s;
    private TextField t;
    private TextField u;
    private TextField w;
    private TextField x;
    private TextField y;
    private dl z;
    private dl dp;
    private dl dd;
    private dl da;
    private dl dn;
    private Component[] dv;
    
    public final boolean p() {
        return this.k.getState();
    }
    
    public final boolean d() {
        return this.l.getState();
    }
    
    public final boolean a() {
        return this.e.getState();
    }
    
    public final boolean n() {
        return !this.b.getState();
    }
    
    public final boolean v() {
        return !this.c.getState();
    }
    
    public final boolean i() {
        return this.f.getState();
    }
    
    public final boolean l() {
        return this.g.getState();
    }
    
    public final boolean b() {
        return this.h.getState();
    }
    
    public final void n() {
        this.h.setState(true);
    }
    
    public final void p(final boolean state) {
        this.j.setState(state);
    }
    
    public s(final y y) {
        super(y, "Options");
        this.i = true;
        this.v = y.p();
        this.dv = new Component[s.a];
        this.p(0, this.l = new Checkbox(), new Label("Sound"), null, null);
        this.l.setState(true);
        this.l.addItemListener(this);
        this.p(1, this.k = new Checkbox(), new Label("Popup private windows"), null, null);
        this.k.setState(true);
        this.k.addItemListener(this);
        this.p(3, this.b = new Checkbox(), new Label("No shout message"), null, null);
        this.b.addItemListener(this);
        this.p(4, this.e = new Checkbox(), new Label("Show Join/Leave"), null, null);
        this.e.setState(true);
        this.e.addItemListener(this);
        this.p(5, this.f = new Checkbox(), new Label("Show Open/Close"), null, null);
        this.f.setState(true);
        this.f.addItemListener(this);
        this.p(2, this.g = new Checkbox(), new Label("Flooding detection"), null, null);
        this.g.setState(true);
        this.g.addItemListener(this);
        this.p(6, this.h = new Checkbox(), new Label("No Invitation"), null, null);
        this.h.addItemListener(this);
        final dl dl;
        this.p(7, dl = new dl("Show all users"), null, null, null);
        final dl dl2;
        this.p(8, dl2 = new dl("Make table public"), null, null, null);
        final dl dl3;
        this.p(9, this.m = new TextField(10), dl3 = new dl("Set table password"), null, null);
        dl.p(this);
        dl2.p(this);
        dl3.p(this);
        dl.setBackground(s.p);
        dl.setForeground(s.d);
        dl2.setBackground(s.p);
        dl2.setForeground(s.d);
        dl3.setBackground(s.p);
        dl3.setForeground(s.d);
        this.p(10, this.j = new Checkbox(), new Label("Non-rating game"), null, null);
        this.j.addItemListener(this);
        this.p(11, this.o = new TextField(4), this.q = new TextField(4), this.r = new TextField(3), this.z = new dl("Set times"));
        this.z.setBackground(s.p);
        this.z.setForeground(s.d);
        this.o.addActionListener(this);
        this.q.addActionListener(this);
        this.r.addActionListener(this);
        this.z.p(this);
        this.p(12, this.s = new TextField(4), this.t = new TextField(4), this.u = new TextField(3), this.dp = new dl("Add times"));
        this.dp.setBackground(s.p);
        this.dp.setForeground(s.d);
        this.s.addActionListener(this);
        this.t.addActionListener(this);
        this.u.addActionListener(this);
        this.dp.p(this);
        this.p(13, new Label("Lotto #$"), this.x = new TextField(2), this.w = new TextField(4), this.dd = new dl("Buy"));
        this.dd.setBackground(s.p);
        this.dd.setForeground(s.d);
        this.dd.p(this);
        this.x.addActionListener(this);
        this.w.addActionListener(this);
        this.p(14, this.y = new TextField(18), this.da = new dl("Set topic"), null, null);
        this.da.setBackground(s.p);
        this.da.setForeground(s.d);
        this.y.addActionListener(this);
        this.da.p(this);
        this.p(15, this.c = new Checkbox(), new Label("Disable most messages"), null, null);
        this.c.addItemListener(this);
        this.p(16, this.dn = new dl("Clear chat window"), null, null, null);
        this.dn.setBackground(s.p);
        this.dn.setForeground(s.d);
        this.dn.p(this);
    }
    
    private final void p(final int n, final Component component, final Component component2, final Component component3, final Component component4) {
        final Component[] array = { component, component2, component3, component4 };
        Component component5 = new Panel();
        this.dv[n] = component5;
        for (int n2 = 0; n2 < 4 && array[n2] != null; ++n2) {
            ((Container)component5).setLayout(new BorderLayout());
            if (n2 >= 3 || array[n2 + 1] == null) {
                ((Container)component5).add(array[n2], "Center");
            }
            else {
                ((Container)component5).add(array[n2], "West");
                final Panel panel = new Panel();
                ((Container)component5).add(panel, "Center");
                component5 = panel;
            }
        }
    }
    
    private final void p(final int n) {
        this.invalidate();
        this.removeAll();
        this.setLayout(new BorderLayout());
        Component component = new Panel();
        this.add(new di(4, 0), "East");
        this.add(new di(4, 0), "West");
        this.add(new di(0, 4), "North");
        this.add(new di(0, 4), "South");
        this.add(component, "Center");
        for (int i = 0; i < s.n[n].length - 1; ++i) {
            final Panel panel = new Panel();
            ((Container)component).setLayout(new BorderLayout());
            ((Container)component).add(this.dv[s.n[n][i]], "North");
            ((Container)component).add(panel, "Center");
            component = panel;
        }
        ((Container)component).setLayout(new BorderLayout());
        ((Container)component).add(this.dv[s.n[n][s.n[n].length - 1]], "Center");
        if (super.p.p() != null) {
            this.l();
        }
        this.pack();
    }
    
    public final void setVisible(final boolean visible) {
        if (!visible) {
            super.setVisible(visible);
            return;
        }
        super.setVisible(false);
        this.v();
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setBounds((screenSize.width - this.getSize().width) / 2, (screenSize.height - this.getSize().height) / 2, this.getSize().width, this.getSize().height);
        super.setVisible(true);
        try {
            this.toFront();
        }
        catch (Exception ex) {}
    }
    
    public final void v() {
        final String d = super.p.p().d();
        if (d.equals("Chat")) {
            this.w.setText(String.valueOf(super.p.n()));
            this.x.setText(String.valueOf(super.p.d()));
            this.p(0);
            return;
        }
        if (!d.equals("Xiangqi") && !d.equals("Gomoku")) {
            this.w.setText(String.valueOf(super.p.n()));
            this.x.setText(String.valueOf(super.p.d()));
            this.p(3);
            return;
        }
        final r r = (r)super.p.p();
        this.j.setState(r.n());
        this.o.setText(String.valueOf(r.a() / 60));
        this.q.setText(String.valueOf(r.n()));
        this.r.setText(String.valueOf(r.v()));
        this.s.setText("1");
        this.t.setText("60");
        this.u.setText("30");
        if (d.equals("Xiangqi")) {
            this.p(1);
            return;
        }
        this.p(2);
    }
    
    private final void i() {
        if (!(super.p.p() instanceof r)) {
            this.v.l("<4>Error: Game time can't be set");
            this.v();
            return;
        }
        final int p = du.p(this.o.getText().trim(), 10);
        final int p2 = du.p(this.q.getText().trim(), 10);
        final int p3 = du.p(this.r.getText().trim(), 10);
        if (p < 0 || p2 < 0 || p3 < 0) {
            this.v.l("<4>Error: Invalid time setting");
            return;
        }
        this.setVisible(false);
        this.v();
        this.v.l("<12>*** Setting time option...");
        super.p.p().d("TIMES " + p * 60 + " " + p2 + " " + p3);
    }
    
    private final void d() {
        if (!(super.p.p() instanceof r)) {
            this.v.l("<4>Error: Game time can't be set");
            this.v();
            return;
        }
        final int p = du.p(this.s.getText().trim(), 10);
        final int p2 = du.p(this.t.getText().trim(), 10);
        final int p3 = du.p(this.u.getText().trim(), 10);
        if (p < 0 || p > 3600 || p2 < 0 || p3 < 0) {
            this.v.l("<4>Error: Invalid add time setting");
            return;
        }
        this.setVisible(false);
        this.v();
        this.v.l("<12>***Giving opponent time...");
        super.p.p().d("ADDTIMES " + p * 60 + " " + p2 + " " + p3);
    }
    
    private final void a() {
        final int p = du.p(this.x.getText().trim(), 10);
        int p2;
        if (this.w.getText().trim() == "") {
            p2 = 0;
        }
        else {
            p2 = du.p(this.w.getText().trim(), 10);
        }
        if (p2 > 0) {
            p2 = p2 / 10 * 10;
        }
        if (p < 0 || p > 99 || p2 < 0 || p2 > 40000) {
            super.p.p().l("<4>*** number must be from 0 to 99, and price from 0 to 40000");
            this.w.setText(String.valueOf(super.p.n()));
            this.x.setText(String.valueOf(super.p.d()));
        }
        else {
            super.p.p(p2);
            super.p.v(p);
            if (p2 > 0) {
                super.p.p().l("<2>*** Start buying lottery ticket...");
            }
            else {
                super.p.p().l("<2>*** Stop buying lottery ticket...");
            }
            super.p.p().d("LOTTERY " + p + " " + p2);
        }
        this.setVisible(false);
    }
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        final i p = super.p.p();
        final String actionCommand = actionEvent.getActionCommand();
        final Object source = actionEvent.getSource();
        if (actionCommand == "Show all users") {
            this.setVisible(false);
            this.v.l("<12>*** Request user list...");
            p.d("USERLIST");
            return;
        }
        if (actionCommand == "Make table public") {
            this.setVisible(false);
            this.v.l("<12>*** Making table public...");
            p.d("PUBLIC");
            return;
        }
        if (actionCommand == "Set table password") {
            this.setVisible(false);
            this.v.l("<12>*** Setting table password...");
            p.d("PRIVATE " + this.m.getText());
            return;
        }
        if (actionCommand == "Set times" || source == this.o || source == this.q || source == this.r) {
            this.i();
            return;
        }
        if (actionCommand == "Add times" || source == this.s || source == this.t || source == this.u) {
            this.d();
            return;
        }
        if (source == this.dd || source == this.x || source == this.w) {
            this.a();
            return;
        }
        if (source == this.dn) {
            super.p.p().a();
            this.setVisible(false);
            return;
        }
        if (source == this.da || source == this.y) {
            final String trim = this.y.getText().trim();
            if (trim.length() == 0) {
                super.p.p().l("<12>*** Remove topic...");
            }
            else {
                super.p.p().l("<12>*** Setting topic...");
            }
            p.d("TOPIC " + trim);
            this.setVisible(false);
        }
    }
    
    public final void itemStateChanged(final ItemEvent itemEvent) {
        final i p = super.p.p();
        final Object source = itemEvent.getSource();
        if (source == this.j) {
            if (itemEvent.getStateChange() == 1) {
                this.j.setState(true);
                this.v.l("<4>***Turning off rating");
                if (super.p.p() instanceof r) {
                    p.d("NORMALRISK");
                }
                p.d("RATING false");
            }
            else {
                this.j.setState(false);
                this.v.l("<12>***Turning on rating");
                p.d("RATING true");
            }
            this.setVisible(false);
            return;
        }
        if (source == this.b) {
            if (itemEvent.getStateChange() == 1) {
                this.v.l("<12>***Shout messages are discarded");
                return;
            }
            this.c.setState(false);
            this.v.l("<12>***Display shout & talk messages");
        }
        else if (source == this.c) {
            if (itemEvent.getStateChange() == 1) {
                this.v.l("<12>***Talk & shout messages are discarded");
                this.v.l("<12>***Disable join/leave/open/close messages");
                this.b.setState(true);
                this.e.setState(false);
                this.f.setState(false);
                return;
            }
            this.v.l("<12>***Display talk messages");
        }
        else if (source == this.h) {
            if (itemEvent.getStateChange() == 1) {
                this.v.l("<12>***automatic reject invitation");
                return;
            }
            this.v.l("<12>***allowing invitation");
        }
        else if (source == this.l) {
            if (itemEvent.getStateChange() == 1) {
                this.v.l("<12>***Turning on sound");
                return;
            }
            this.v.l("<12>***Turning off sound");
        }
        else if (source == this.k) {
            if (itemEvent.getStateChange() == 1) {
                this.v.l("<12>***Private chat in new window");
                return;
            }
            this.v.l("<12>***No new window for private chat");
        }
        else if (source == this.e) {
            if (itemEvent.getStateChange() == 1) {
                this.v.l("<12>***Enable join/leave messages");
                return;
            }
            this.v.l("<12>***Disable join/leave messages");
        }
        else {
            if (source != this.g) {
                if (source == this.f) {
                    if (itemEvent.getStateChange() == 1) {
                        this.v.l("<12>***Enable open/close messages");
                        return;
                    }
                    this.v.l("<12>***Disable open/close messages");
                }
                return;
            }
            if (itemEvent.getStateChange() == 1) {
                this.v.l("<12>***Enable flooding detection");
                return;
            }
            this.v.l("<12>***Disable flooding detection");
        }
    }
    
    public final void d(final Component component) {
        super.d(component);
        final Color[] array = dw.p[super.p.p()];
        if (component instanceof Panel || component instanceof Label || component instanceof Checkbox) {
            component.setBackground(array[10]);
            component.setForeground(array[6]);
        }
    }
    
    static {
        p = new Color(196, 244, 247);
        d = new Color(51, 53, 76);
        s.a = 18;
        s.n = new int[][] { { 16, 1, 3, 4, 2, 7, 8, 9, 14, 13 }, { 16, 0, 1, 3, 15, 4, 5, 2, 6, 7, 8, 9, 14, 10, 11, 12 }, { 16, 0, 1, 3, 15, 4, 5, 2, 6, 7, 8, 9, 14, 10, 11, 12, 13 }, { 16, 0, 1, 3, 15, 4, 5, 2, 6, 7, 8, 9, 14, 10, 13 } };
    }
}
