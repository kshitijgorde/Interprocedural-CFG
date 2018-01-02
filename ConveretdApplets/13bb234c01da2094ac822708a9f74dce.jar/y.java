import javax.swing.BorderFactory;
import javax.swing.table.TableColumn;
import javax.swing.table.TableCellRenderer;
import ca.odell.glazedlists.swing.TableComparatorChooser;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableModel;
import javax.swing.event.ListSelectionListener;
import ca.odell.glazedlists.gui.TableFormat;
import ca.odell.glazedlists.SortedList;
import ca.odell.glazedlists.matchers.MatcherEditor;
import java.util.Comparator;
import java.util.Collections;
import java.awt.Container;
import java.awt.event.TextEvent;
import java.awt.CheckboxMenuItem;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import com.daysofwonder.applet.g;
import java.awt.event.ActionEvent;
import com.daysofwonder.a.n;
import com.daysofwonder.applet.D;
import java.awt.Frame;
import com.daysofwonder.applet.aO;
import java.util.Enumeration;
import java.awt.event.MouseEvent;
import java.awt.FontMetrics;
import com.daysofwonder.applet.aL;
import java.awt.image.ImageObserver;
import java.awt.Toolkit;
import java.util.StringTokenizer;
import com.daysofwonder.a.a;
import javax.swing.JPasswordField;
import javax.swing.event.DocumentListener;
import java.awt.LayoutManager;
import java.awt.event.FocusEvent;
import com.daysofwonder.applet.filters.TableFilter;
import javax.swing.JMenuItem;
import javax.swing.Action;
import java.util.Iterator;
import ca.odell.glazedlists.GlazedLists;
import com.daysofwonder.req.x;
import com.daysofwonder.req.H;
import com.daysofwonder.util.t;
import java.util.List;
import java.awt.Component;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComponent;
import ca.odell.glazedlists.BasicEventList;
import java.util.HashMap;
import ca.odell.glazedlists.FilterList;
import com.daysofwonder.a.d;
import java.util.LinkedHashMap;
import com.daysofwonder.applet.k;
import com.daysofwonder.applet.ar;
import javax.swing.JScrollPane;
import ca.odell.glazedlists.swing.EventSelectionModel;
import ca.odell.glazedlists.swing.EventTableModel;
import ca.odell.glazedlists.EventList;
import com.daysofwonder.util.K;
import java.util.Map;
import com.daysofwonder.applet.au;
import java.util.Vector;
import com.daysofwonder.applet.m;
import com.daysofwonder.applet.ag;
import com.daysofwonder.applet.R;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.JPopupMenu;
import java.awt.Rectangle;
import java.awt.Color;
import com.daysofwonder.util.UIProperties;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import com.daysofwonder.applet.aG;
import java.awt.Font;
import java.awt.Dimension;
import java.util.Hashtable;
import java.awt.event.TextListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.event.ItemListener;
import java.awt.event.ActionListener;
import com.daysofwonder.applet.p;
import com.daysofwonder.applet.e;
import com.daysofwonder.applet.an;
import javax.swing.JPanel;

// 
// Decompiled by Procyon v0.5.30
// 

public class y extends JPanel implements an, e, p, ActionListener, ItemListener, MouseListener, MouseMotionListener, TextListener
{
    private static final String[] c;
    private final Object d;
    private int e;
    private Hashtable f;
    private Dimension g;
    private Font h;
    private aG i;
    private Point j;
    private Point k;
    private Dimension l;
    private Image m;
    private Image n;
    private Graphics o;
    private Image p;
    private Graphics q;
    private UIProperties r;
    private UIProperties s;
    private String t;
    private int u;
    private Font v;
    private Font w;
    private Font z;
    private Font A;
    private boolean B;
    private boolean C;
    private boolean D;
    private boolean E;
    private int F;
    private boolean G;
    private boolean H;
    private int I;
    private boolean J;
    private Color K;
    private B L;
    private B M;
    private B N;
    private B O;
    private B P;
    private B Q;
    private B R;
    private B S;
    private B T;
    private B U;
    private B V;
    private B W;
    private B X;
    private B Y;
    private B Z;
    private B aa;
    private B ab;
    private B ac;
    private B ad;
    private B ae;
    private B af;
    private B ag;
    private B ah;
    private B ai;
    private B aj;
    private B ak;
    private B al;
    private B am;
    private B an;
    private B ao;
    private B ap;
    private B aq;
    private B ar;
    private Rectangle as;
    private JPopupMenu at;
    private int au;
    private Rectangle av;
    private JTextField aw;
    private Rectangle ax;
    private Rectangle ay;
    private Rectangle az;
    private Rectangle aA;
    private Rectangle aB;
    private Rectangle aC;
    private Rectangle aD;
    private Rectangle aE;
    private Rectangle aF;
    private Rectangle aG;
    private Rectangle aH;
    private Rectangle aI;
    private JTextField aJ;
    private Rectangle aK;
    private JCheckBox aL;
    private Rectangle aM;
    private Rectangle aN;
    private Rectangle aO;
    private JComboBox aP;
    private Rectangle aQ;
    private JComboBox aR;
    private Rectangle aS;
    private JComboBox aT;
    private Rectangle aU;
    private JComboBox aV;
    private Rectangle aW;
    private Rectangle aX;
    private JComboBox aY;
    private Rectangle aZ;
    private Rectangle ba;
    private JCheckBox bb;
    private Rectangle bc;
    private JComboBox bd;
    private Rectangle be;
    private JCheckBox bf;
    private Rectangle bg;
    private JCheckBox bh;
    private Rectangle bi;
    private JCheckBox bj;
    private int bk;
    private Color bl;
    private Color bm;
    private Color bn;
    private Color bo;
    private Rectangle bp;
    private JTable bq;
    private Rectangle br;
    private JTable bs;
    private Rectangle bt;
    private JTable bu;
    private Rectangle bv;
    private R bw;
    private ag bx;
    private m by;
    private Vector bz;
    private long bA;
    private int bB;
    private int bC;
    private int bD;
    private String bE;
    private String bF;
    private String bG;
    private String bH;
    private String bI;
    private String bJ;
    private Color bK;
    private String bL;
    private float bM;
    private String bN;
    private String bO;
    private String[] bP;
    private int bQ;
    private String bR;
    private au bS;
    private Rectangle bT;
    private Rectangle bU;
    private JPopupMenu bV;
    private B bW;
    private Map bX;
    private K bY;
    private int bZ;
    private EventList ca;
    private EventList cb;
    private EventList cc;
    private EventTableModel cd;
    private EventSelectionModel ce;
    private EventTableModel cf;
    private EventSelectionModel cg;
    private JTable ch;
    private JTable ci;
    private JScrollPane cj;
    private JScrollPane ck;
    private ar cl;
    private Color cm;
    private K cn;
    private int co;
    private int cp;
    private EventTableModel cq;
    private EventSelectionModel cr;
    private k cs;
    private JScrollPane ct;
    private EventList cu;
    private EventTableModel cv;
    private EventSelectionModel cw;
    private JScrollPane cx;
    private EventList cy;
    private EventTableModel cz;
    private EventSelectionModel cA;
    private JScrollPane cB;
    private Integer cC;
    private ar cD;
    private int cE;
    private String cF;
    private LinkedHashMap cG;
    private Object cH;
    private B cI;
    public d a;
    public d b;
    private FilterList cJ;
    private FilterList cK;
    private M cL;
    private M cM;
    private boolean cN;
    private boolean cO;
    private boolean cP;
    private boolean cQ;
    private static final int[] cR;
    private static final Color[] cS;
    private static final Character cT;
    
    public y(final aG i, final ag bx, final au bs) {
        this.d = new Object();
        this.B = true;
        this.C = true;
        this.D = true;
        this.G = false;
        this.H = false;
        this.bP = new String[] { "lang.en", "lang.fr", "lang.de", "", "", "", "lang.jp" };
        this.bX = new HashMap();
        this.bY = new K();
        this.ca = new BasicEventList();
        this.cb = new BasicEventList();
        this.cc = new BasicEventList();
        this.cn = new K();
        this.cp = 0;
        this.cu = new BasicEventList();
        this.cy = new BasicEventList();
        this.cG = new aB(this);
        this.cH = null;
        this.bS = bs;
        this.bx = bx;
        this.g = new Dimension(798, 597);
        this.i = i;
        this.bQ = this.bS.k();
        this.a(this.getSize());
    }
    
    public y(final aG ag, final ag ag2, final int e, final au au) {
        this(ag, ag2, au);
        this.e = e;
    }
    
    public int a() {
        return this.e;
    }
    
    public String b() {
        if (this.bS.f()) {
            return "tableui-" + this.bS.g() + ".properties";
        }
        return "tableui.properties";
    }
    
    public String c() {
        if (this.bS.h().equals("en")) {
            return "tablemsg.properties";
        }
        return "tablemsg-" + this.bS.h() + ".properties";
    }
    
    public void d() {
        synchronized (this.d) {
            this.D = false;
            System.out.println("TableView shutdowning");
            final c ae = G.ae();
            ae.a = this.aJ.getText();
            ae.f = this.bb.isSelected();
            ae.c = this.aL.isSelected();
            ae.g = this.aY.getSelectedIndex();
            ae.h = this.aP.getSelectedIndex();
            ae.l = Integer.parseInt((String)this.aR.getSelectedItem());
            ae.i = Integer.parseInt((String)this.aT.getSelectedItem());
            ae.j = this.bd.getSelectedIndex();
            ae.d = this.bf.isSelected();
            ae.e = this.bh.isSelected();
            ae.m = this.bj.isSelected();
            ae.b = this.aw.getText();
            ae.k = this.aV.getSelectedIndex();
            ae.n = new LinkedHashMap(this.cG);
            ae.o = new HashMap();
            for (int i = 0; i < this.bV.getComponentCount(); ++i) {
                final JComponent component = (JComponent)this.bV.getComponent(i);
                if (component instanceof JCheckBoxMenuItem) {
                    ae.o.put(((JCheckBoxMenuItem)component).getActionCommand(), ((JCheckBoxMenuItem)component).getState());
                }
            }
            if (this.L != null && this.L.e != null) {
                this.L.e.flush();
            }
            if (this.M != null && this.M.e != null) {
                this.M.e.flush();
            }
            if (this.N != null && this.N.e != null) {
                this.N.e.flush();
            }
            if (this.ar != null && this.ar.e != null) {
                this.ar.e.flush();
            }
            if (this.O != null && this.O.e != null) {
                this.O.e.flush();
            }
            if (this.ac != null && this.ac.e != null) {
                this.ac.e.flush();
            }
            if (this.ab != null && this.ab.e != null) {
                this.ab.e.flush();
            }
            if (this.ad != null && this.ad.e != null) {
                this.ad.e.flush();
            }
            if (this.ae != null && this.ae.e != null) {
                this.ae.e.flush();
            }
            if (this.af != null && this.af.e != null) {
                this.af.e.flush();
            }
            if (this.ag != null && this.ag.e != null) {
                this.ag.e.flush();
            }
            if (this.T != null && this.T.e != null) {
                this.T.e.flush();
            }
            if (this.at != null) {
                this.remove(this.at);
            }
            if (this.bV != null) {
                this.remove(this.bV);
            }
            if (this.m != null) {
                this.m.flush();
            }
            if (this.q != null) {
                this.q.dispose();
            }
            if (this.p != null) {
                this.p.flush();
            }
            if (this.o != null) {
                this.o.dispose();
            }
            if (this.n != null) {
                this.n.flush();
            }
            if (this.by != null) {
                this.by.b();
                this.by = null;
            }
            this.m = null;
            if (this.bw != null) {
                this.bw.b((ActionListener)this);
                this.bw.b((TextListener)this);
                this.bw.k();
            }
            if (this.aR != null) {
                this.aR.removeActionListener(this);
            }
            if (this.aT != null) {
                this.aT.removeActionListener(this);
            }
            if (this.bb != null) {
                this.bb.removeItemListener(this);
            }
            this.bz.removeAllElements();
            this.bz = null;
        }
    }
    
    public boolean a(final int f, final boolean b, final List list, final List list2) {
        synchronized (this.d) {
            if (!this.D) {
                return true;
            }
            com.daysofwonder.util.t.a("tableChanged: nb " + f + " open: " + b + " Tables: " + list);
            if (b) {
                this.F = f;
            }
            if (this.cN) {
                this.a(b, list);
                this.a(list2);
                if (b) {
                    this.ck.repaint();
                }
                else {
                    this.cj.repaint();
                }
                this.ct.repaint();
                this.a(this.M);
                this.a(this.O);
                this.a(this.ah);
                this.a(this.ai);
            }
        }
        return true;
    }
    
    private void a(final B b) {
        this.repaint(b.a.x, b.a.y, b.a.width, b.a.height);
    }
    
    public void a(final H h) {
        com.daysofwonder.util.t.a("req:" + h);
        if (h.d() == this.i.aD()) {
            this.e = h.j();
            com.daysofwonder.util.t.a("my karma:" + this.e);
        }
        if (!this.G && this.r != null) {
            this.bB = h.h();
            this.bC = h.k();
            this.bD = h.m();
            this.bG = h.e();
            if (h.l() >= 0 && h.l() < this.bP.length) {
                this.bF = this.r.b(this.bP[h.l()]);
            }
            else {
                this.bF = "";
            }
            this.bH = h.f();
            this.bI = h.g();
            final int b = b(h.j());
            this.bJ = Integer.toString(h.j());
            this.bL = c(b);
            this.bK = y.cS[b];
            this.G = true;
        }
        this.repaint(this.aD.x, this.aD.y, this.aD.width, this.aD.height);
    }
    
    public void a(final x x) {
        if (!this.H) {
            this.bM = x.d();
            this.bN = x.e();
            this.bO = x.f();
            this.H = true;
        }
        this.repaint(this.ax.x, this.ax.y, this.ax.width, this.ax.height);
        this.repaint(this.bT.x, this.bT.y, this.bT.width, this.bT.height);
    }
    
    private void a(final boolean b, final List list) {
        final EventList list2 = b ? this.ca : this.cb;
        list2.b().a().a();
        try {
            GlazedLists.a(list2, list, true);
        }
        finally {
            list2.b().a().b();
        }
    }
    
    protected static int a(final com.daysofwonder.tt.m m) {
        for (int i = 0; i < y.c.length; ++i) {
            if (y.c[i].equals(m.a())) {
                return i;
            }
        }
        return -1;
    }
    
    private void a(final List list) {
        this.cc.b().a().a();
        try {
            if (this.cs != null) {
                for (final k cs : list) {
                    if (this.cs.b() == cs.b()) {
                        this.cs = cs;
                        break;
                    }
                }
            }
            GlazedLists.a(this.cc, list, true);
        }
        finally {
            this.cc.b().a().b();
        }
    }
    
    public void e() {
        this.r = com.daysofwonder.applet.y.c(this.c());
        this.s = com.daysofwonder.applet.y.c(this.b());
    }
    
    public void f() {
    }
    
    public void g() {
        this.h = new Font("sansserif", 0, 12);
        this.v = new Font("sansserif", 0, 11);
        this.z = new Font("sansserif", 0, 10);
        this.A = new Font("sansserif", 1, 13);
        this.w = new Font("sansserif", 1, 11);
        this.setFont(this.h);
        this.bR = this.bS.l();
        this.bz = new Vector();
        this.J = this.s.c("tiledbackground");
        this.K = this.a(this.s, "background.color");
        final UIProperties s = this.s;
        this.M = new B();
        this.M.a = this.b(s, "join.r");
        this.M.b = this.bS.b(s.a("join.on"));
        this.M.c = this.bS.b(s.a("join.off"));
        this.M.d = this.bS.b(s.a("join.disabled"));
        this.M.f = this.r.b(s.a("join.tooltip"));
        this.bz.addElement(this.M);
        this.N = new B();
        this.N.a = this.b(s, "observe.r");
        this.N.b = this.bS.b(s.a("observe.on"));
        this.N.c = this.bS.b(s.a("observe.off"));
        this.N.d = this.bS.b(s.a("observe.disabled"));
        this.N.f = this.r.b(s.a("observe.tooltip"));
        this.O = new B();
        this.O.a = this.b(s, "create.r");
        this.O.b = this.bS.b(s.a("create.on"));
        this.O.c = this.bS.b(s.a("create.off"));
        this.O.d = this.bS.b(s.a("create.disabled"));
        this.O.f = this.r.b(s.a("create.tooltip"));
        this.bz.addElement(this.O);
        this.L = new B();
        this.L.a = this.b(s, "send.r");
        this.L.b = this.bS.b(s.a("send.on"));
        this.L.c = this.bS.b(s.a("send.off"));
        this.L.d = this.bS.b(s.a("send.disabled"));
        this.L.f = this.r.b(s.a("send.tooltip"));
        this.bz.addElement(this.L);
        this.Y = new B();
        this.Y.a = this.b(s, "bell.r");
        this.Y.b = this.bS.b(s.a("bell.on"));
        this.Y.c = this.bS.b(s.a("bell.off"));
        this.Y.f = this.r.b(s.a("bell.tooltip"));
        this.bz.addElement(this.Y);
        this.Z = new B();
        this.Z.a = this.b(s, "ring.r");
        this.Z.b = this.bS.b(s.a("ring.on"));
        this.Z.c = this.bS.b(s.a("ring.off"));
        this.Z.f = this.r.b(s.a("ring.tooltip"));
        this.bz.addElement(this.Z);
        this.aa = new B();
        this.aa.a = this.b(s, "qmark.r");
        this.aa.b = this.bS.b(s.a("qmark.on"));
        this.aa.c = this.bS.b(s.a("qmark.off"));
        this.aa.f = this.r.b(s.a("qmark.tooltip"));
        this.bz.addElement(this.aa);
        if (this.bS.p()) {
            this.ab = new B();
            this.ab.a = this.b(s, "quit.r");
            this.ab.b = this.bS.b(s.a("quit.on"));
            this.ab.c = this.bS.b(s.a("quit.off"));
            this.ab.f = this.r.b(s.a("quit.tooltip"));
            this.bz.addElement(this.ab);
        }
        this.ag = new B();
        this.ag.a = this.b(s, "resume.r");
        this.ag.b = this.bS.b(s.a("resume.on"));
        this.ag.c = this.bS.b(s.a("resume.off"));
        this.ag.f = this.r.b(s.a("resume.tooltip"));
        this.bz.addElement(this.ag);
        if (this.bS.r()) {
            this.ac = new B();
            this.ac.a = this.b(s, "help.r");
            this.ac.b = this.bS.b(s.a("help.on"));
            this.ac.c = this.bS.b(s.a("help.off"));
            this.ac.f = this.r.b(s.a("help.tooltip"));
            this.bz.addElement(this.ac);
        }
        this.ae = new B();
        this.ae.a = this.b(s, "buddies.r");
        this.ae.b = this.bS.b(s.a("buddies.on"));
        this.ae.c = this.bS.b(s.a("buddies.off"));
        this.ae.f = this.r.b(s.a("buddies.tooltip"));
        this.bz.addElement(this.ae);
        if (this.bS.u()) {
            this.af = new B();
            this.af.a = this.b(s, "buy.r");
            this.af.b = this.bS.b(s.a("buy.on"));
            this.af.c = this.bS.b(s.a("buy.off"));
            this.af.f = this.r.b(s.a("buy.tooltip"));
            this.bz.addElement(this.af);
        }
        else if (this.bS.p()) {
            this.af = new B();
            this.af.a = this.b(s, "quit.r");
            this.af.b = this.bS.b(s.a("quit.on"));
            this.af.c = this.bS.b(s.a("quit.off"));
            this.af.f = this.r.b(s.a("quit.tooltip"));
            this.bz.addElement(this.af);
        }
        this.bp = this.b(s, "game.list.r");
        this.br = this.b(s, "players.list.r");
        this.bt = this.b(s, "buddy.list.r");
        this.bv = this.b(s, "ignore.list.r");
        this.P = new B();
        this.P.a = this.b(s, "chat.r");
        this.as = this.b(s, "smallchat.r");
        this.P.f = this.r.b(s.a("chat.tooltip"));
        this.bz.addElement(this.P);
        this.T = new B();
        this.T.a = this.b(s, "chatwin.r");
        this.T.f = this.r.b(s.a("chatwin.tooltip"));
        this.bz.addElement(this.T);
        this.bW = new B();
        this.bW.a = this.b(s, "filtertab.r");
        this.bW.b = this.bS.b(s.a("filtertab.on"));
        this.bW.c = this.bS.b(s.a("filtertab.off"));
        this.bW.f = this.r.b(s.a("filtertab.tooltip"));
        this.bz.addElement(this.bW);
        final c ae = G.ae();
        if (ae != null && ae.n != null) {
            this.cG = new LinkedHashMap(ae.n);
        }
        int n = 1;
        (this.bV = new JPopupMenu()).setLightWeightPopupEnabled(true);
        for (String s2 = this.r.b("filter.1"); s2 != null; s2 = this.r.b("filter." + n)) {
            if (!this.r.b("filter." + n + ".action").equals("dash")) {
                final JCheckBoxMenuItem checkBoxMenuItem = new JCheckBoxMenuItem(s2, true);
                checkBoxMenuItem.setState(this.r.b("filter." + n + ".enabled").equals("true"));
                checkBoxMenuItem.setActionCommand(this.r.b("filter." + n + ".action"));
                checkBoxMenuItem.setAction(new az(this, s2));
                if (ae.o != null) {
                    checkBoxMenuItem.setState((boolean)ae.o.get(checkBoxMenuItem.getActionCommand()));
                }
                this.bV.add(checkBoxMenuItem);
                try {
                    this.bX.put(checkBoxMenuItem.getActionCommand(), Class.forName("com.daysofwonder.applet.filters." + this.r.b("filter." + n + ".action") + "TableFilter").newInstance());
                }
                catch (InstantiationException ex) {
                    ex.printStackTrace();
                }
                catch (IllegalAccessException ex2) {
                    ex2.printStackTrace();
                }
                catch (ClassNotFoundException ex3) {
                    ex3.printStackTrace();
                }
            }
            else {
                this.bV.add(new JPopupMenu.Separator());
            }
            ++n;
        }
        this.add(this.bV);
        this.at = new JPopupMenu();
        this.m();
        this.add(this.at);
        this.W = new B();
        this.W.a = this.b(s, "chatup.r");
        this.W.b = this.bS.b(s.a("chatup.on"));
        this.W.c = this.bS.b(s.a("chatup.off"));
        this.W.d = this.bS.b(s.a("chatup.disabled"));
        this.X = new B();
        this.X.a = this.b(s, "chatdown.r");
        this.X.b = this.bS.b(s.a("chatdown.on"));
        this.X.c = this.bS.b(s.a("chatdown.off"));
        this.X.d = this.bS.b(s.a("chatdown.disabled"));
        (this.bw = new R(this, this.b(s, "freechat.r"))).b(true);
        this.bw.c(true);
        this.bw.b(this.a(s, "freechat.color"));
        this.bw.a(Color.black);
        this.bw.a((ActionListener)this);
        this.bw.a((TextListener)this);
        this.bw.a((FocusEvent)null);
        this.u = this.au;
        final B b = new B();
        b.a = this.b(s, "freechat.r");
        b.f = this.r.b(s.a("freechat.tooltip"));
        b.h = this.bw;
        this.bz.addElement(b);
        this.bl = this.a(s, "game.list.background.color");
        this.cm = this.a(s, "game.list.lightbackground.color");
        this.bm = this.a(s, "game.list.header.color");
        this.bn = this.a(s, "game.list.sel.header.color");
        this.bo = this.a(s, "chat.color");
        this.ah = new B();
        this.ah.a = this.b(s, "opentab.r");
        this.ah.b = this.bS.b(s.a("opentab.on"));
        this.ah.c = this.bS.b(s.a("opentab.off"));
        this.ah.f = this.r.b(s.a("opentab.tooltip"));
        this.bz.addElement(this.ah);
        this.ai = new B();
        this.ai.a = this.b(s, "closedtab.r");
        this.ai.b = this.bS.b(s.a("closedtab.on"));
        this.ai.c = this.bS.b(s.a("closedtab.off"));
        this.ai.f = this.r.b(s.a("closedtab.tooltip"));
        this.bz.addElement(this.ai);
        this.ap = new B();
        this.ap.a = this.b(s, "buddytab.r");
        this.ap.b = this.bS.b(s.a("buddytab.on"));
        this.ap.c = this.bS.b(s.a("buddytab.off"));
        this.ap.f = this.r.b(s.a("buddytab.tooltip"));
        this.bz.addElement(this.ap);
        this.aq = new B();
        this.aq.a = this.b(s, "ignoretab.r");
        this.aq.b = this.bS.b(s.a("ignoretab.on"));
        this.aq.c = this.bS.b(s.a("ignoretab.off"));
        this.aq.f = this.r.b(s.a("ignoretab.tooltip"));
        this.bz.addElement(this.aq);
        this.setLayout(null);
        this.b(this.ca);
        this.a(this.cb);
        this.v();
        this.t();
        this.u();
        this.av = this.b(s, "create.name.r");
        (this.aw = new JTextField()).setBounds(this.av.x, this.av.y, this.av.width, this.av.height);
        this.aw.setBackground(Color.white);
        this.aw.setColumns(25);
        this.aw.setFont(this.v);
        this.aw.setEnabled(false);
        this.aw.setVisible(false);
        this.aw.getDocument().addDocumentListener(new au(this));
        this.aw.addMouseMotionListener(this);
        this.add(this.aw);
        final B b2 = new B();
        b2.a = this.av;
        b2.f = this.r.b(s.a("create.name.tooltip"));
        b2.g = 1;
        b2.h = this.aw;
        this.bz.addElement(b2);
        this.aI = this.b(s, "create.pass.r");
        (this.aJ = new JPasswordField()).setBounds(this.aI.x, this.aI.y, this.aI.width, this.aI.height);
        this.aJ.setColumns(25);
        this.aJ.setBackground(Color.white);
        this.aJ.setFont(this.v);
        this.aJ.setEnabled(false);
        this.aJ.setVisible(false);
        this.aJ.addMouseMotionListener(this);
        this.add(this.aJ);
        final B b3 = new B();
        b3.a = this.aI;
        b3.f = this.r.b(s.a("create.pass.tooltip"));
        b3.g = 1;
        b3.h = this.aJ;
        this.bz.addElement(b3);
        this.aX = this.b(s, "create.maptext.r");
        this.aW = this.b(s, "create.map.r");
        (this.aY = new JComboBox()).setBounds(this.aW.x, this.aW.y, this.aW.width, this.aW.height);
        final a ak = this.i.aK();
        for (int i = 0; i < y.c.length; ++i) {
            if (ak.a(y.c[i])) {
                this.aY.addItem(this.r.b("map." + i));
            }
        }
        if (!ak.a() && this.aY.getItemCount() > 0) {
            this.aY.setSelectedIndex(0);
        }
        this.aY.setBackground(this.bl);
        this.aY.setFont(this.v);
        this.aY.setEnabled(false);
        this.aY.setVisible(false);
        this.a(this.aY);
        this.aY.addMouseMotionListener(this);
        this.add(this.aY);
        this.aK = this.b(s, "create.rank.r");
        (this.aL = new JCheckBox(this.r.b("create.rank"))).setBounds(this.aK.x, this.aK.y, this.aK.width, this.aK.height);
        this.aL.setBackground(this.bl);
        this.aL.setFont(this.v);
        this.aL.setEnabled(false);
        this.aL.setVisible(false);
        this.aL.addMouseMotionListener(this);
        this.add(this.aL);
        final B b4 = new B();
        b4.a = this.aK;
        b4.f = this.r.b(s.a("create.rank.tooltip"));
        b4.g = 1;
        b4.h = this.aL;
        this.bz.addElement(b4);
        final B b5 = new B();
        b5.a = this.aO;
        b5.f = this.r.b(s.a("create.bot.tooltip"));
        b5.g = 1;
        b5.h = this.aP;
        this.bz.addElement(b5);
        this.aS = this.b(s, "create.maxplayerschoice.r");
        (this.aT = new JComboBox()).setBounds(this.aS.x, this.aS.y, this.aS.width, this.aS.height);
        this.aT.setBackground(this.bl);
        this.aT.setFont(this.v);
        this.aT.setEnabled(false);
        this.aT.setVisible(false);
        this.aT.addItem("2");
        this.aT.addItem("3");
        this.aT.addItem("4");
        this.aT.addItem("5");
        this.aT.setSelectedIndex(2);
        this.aT.addActionListener(this);
        this.a(this.aT);
        this.add(this.aT);
        this.aQ = this.b(s, "create.minplayerschoice.r");
        (this.aR = new JComboBox()).setBounds(this.aQ.x, this.aQ.y, this.aQ.width, this.aQ.height);
        this.aR.setBackground(this.bl);
        this.aR.setFont(this.v);
        this.aR.setEnabled(false);
        this.aR.setVisible(false);
        this.aR.addItem("2");
        this.aR.addItem("3");
        this.aR.addItem("4");
        this.aR.addItem("5");
        this.aR.setSelectedIndex(0);
        this.aR.addActionListener(this);
        this.a(this.aR);
        this.add(this.aR);
        this.aU = this.b(s, "create.minkarmachoice.r");
        (this.aV = new JComboBox()).setBounds(this.aU.x, this.aU.y, this.aU.width, this.aU.height);
        for (int j = 0; j <= 4; ++j) {
            this.aV.addItem(c(j));
        }
        this.aV.setSelectedIndex(0);
        this.aV.setBackground(this.bl);
        this.aV.setFont(this.v);
        this.aV.setEnabled(false);
        this.aV.setVisible(false);
        this.aV.addMouseMotionListener(this);
        this.a(this.aV);
        this.add(this.aV);
        this.aZ = this.b(s, "create.lurkable.r");
        this.bc = this.b(s, "create.lurkablepopup.r");
        (this.bd = new JComboBox()).setBounds(this.bc.x, this.bc.y, this.bc.width, this.bc.height);
        this.bd.setBackground(this.bl);
        this.bd.setFont(this.v);
        this.bd.setEnabled(false);
        this.bd.setVisible(false);
        this.bd.addItem(this.r.b("create.lurkablepopup.everyone"));
        this.bd.addItem(this.r.b("create.lurkablepopup.buddies"));
        this.bd.addItem(this.r.b("create.lurkablepopup.noguest"));
        this.bd.addItem(this.r.b("create.lurkablepopup.nobody"));
        this.bd.setSelectedIndex(0);
        this.bd.addMouseMotionListener(this);
        this.a(this.bd);
        this.add(this.bd);
        final B b6 = new B();
        b6.a = this.bc;
        b6.f = this.r.b(s.a("create.lurkable.tooltip"));
        b6.g = 1;
        b6.h = this.bd;
        this.bz.addElement(b6);
        this.be = this.b(s, "create.lurkablecards.r");
        (this.bf = new JCheckBox(this.r.b("create.lurkablecards"))).setBounds(this.be.x, this.be.y, this.be.width, this.be.height);
        this.bf.setBackground(this.bl);
        this.bf.setFont(this.v);
        this.bf.setEnabled(false);
        this.bf.setVisible(false);
        this.bf.setSelected(true);
        this.bf.addMouseMotionListener(this);
        this.add(this.bf);
        final B b7 = new B();
        b7.a = this.be;
        b7.f = this.r.b(s.a("create.lurkable.tooltip"));
        b7.g = 1;
        b7.h = this.bf;
        this.bz.addElement(b7);
        this.bg = this.b(s, "create.notfirst.r");
        (this.bh = new JCheckBox(this.r.b("create.notfirst"))).setBounds(this.bg.x, this.bg.y, this.bg.width, this.bg.height);
        this.bh.setBackground(this.bl);
        this.bh.setFont(this.v);
        this.bh.setEnabled(false);
        this.bh.setVisible(false);
        this.bh.addItemListener(this);
        this.bh.addMouseMotionListener(this);
        this.add(this.bh);
        final B b8 = new B();
        b8.a = this.bg;
        b8.f = this.r.b(s.a("create.notfirst.tooltip"));
        b8.g = 1;
        b8.h = this.bh;
        this.bz.addElement(b8);
        this.bi = this.b(s, "create.talkingbots.r");
        (this.bj = new JCheckBox(this.r.b("create.talkingbots"))).setBounds(this.bi.x, this.bi.y, this.bi.width, this.bi.height);
        this.bj.setBackground(this.bl);
        this.bj.setFont(this.v);
        this.bj.setEnabled(false);
        this.bj.setVisible(false);
        this.bj.addItemListener(this);
        this.bj.addMouseMotionListener(this);
        this.add(this.bj);
        final B b9 = new B();
        b9.a = this.bi;
        b9.f = this.r.b(s.a("create.talkingbots.tooltip"));
        b9.g = 1;
        b9.h = this.bj;
        this.bz.addElement(b9);
        this.ba = this.b(s, "create.private.r");
        (this.bb = new JCheckBox(this.r.b("create.private"))).setBounds(this.ba.x, this.ba.y, this.ba.width, this.ba.height);
        this.bb.setBackground(this.bl);
        this.bb.setFont(this.v);
        this.bb.setEnabled(false);
        this.bb.setVisible(false);
        this.bb.addItemListener(this);
        this.bb.addMouseMotionListener(this);
        this.add(this.bb);
        final B b10 = new B();
        b10.a = this.ba;
        b10.f = this.r.b(s.a("create.private.tooltip"));
        b10.g = 1;
        b10.h = this.bb;
        this.bz.addElement(b10);
        this.aO = this.b(s, "create.bot.r");
        (this.aP = new JComboBox()).setBounds(this.aO.x, this.aO.y, this.aO.width, this.aO.height);
        this.aP.setBackground(this.bl);
        this.aP.setFont(this.v);
        this.aP.setEnabled(false);
        this.aP.setVisible(false);
        this.aP.addItem("0");
        this.aP.addItem("1");
        this.aP.addItem("2");
        this.aP.addItem("3");
        this.aP.addItem("4");
        this.aP.setSelectedIndex(0);
        this.aP.addMouseMotionListener(this);
        this.a(this.aP);
        this.add(this.aP);
        this.I = this.aP.getLocation().x + this.aP.getSize().width + 5;
        this.ax = this.b(s, "create.window.r");
        this.bT = this.b(s, "create.window2.r");
        this.aD = this.b(s, "info.window.r");
        this.aF = this.b(s, "find.window.r");
        this.aH = this.b(s, "buddies.window.r");
        final B b11 = new B();
        b11.a = this.ax;
        b11.f = this.r.b(s.a("create.window.tooltip"));
        b11.g = 1;
        this.bz.addElement(b11);
        final B b12 = new B();
        b12.a = this.bT;
        b12.f = this.r.b(s.a("create.window.tooltip"));
        b12.g = 1;
        this.bz.addElement(b12);
        this.ay = this.b(s, "create.nametext.r");
        this.az = this.b(s, "create.passtext.r");
        this.aA = this.b(s, "create.bottext1.r");
        this.bU = this.b(s, "create.minplayers.r");
        this.aM = this.b(s, "create.maxplayers.r");
        this.aN = this.b(s, "create.minkarma.r");
        this.aB = this.b(s, "create.header.r");
        this.aC = this.b(s, "info.header.r");
        this.aE = this.b(s, "find.header.r");
        this.aG = this.b(s, "buddies.header.r");
        this.U = new B();
        this.U.a = this.b(s, "go.r");
        this.U.b = this.bS.b(s.a("go.on"));
        this.U.c = this.bS.b(s.a("go.off"));
        this.U.d = this.bS.b(s.a("go.disabled"));
        this.U.f = this.r.b(s.a("go.tooltip"));
        this.U.g = 1;
        this.bz.addElement(this.U);
        this.ak = new B();
        this.ak.a = this.b(s, "add.r");
        this.ak.b = this.bS.b(s.a("add.on"));
        this.ak.c = this.bS.b(s.a("add.off"));
        this.ak.d = this.bS.b(s.a("add.disabled"));
        this.ak.f = this.r.b(s.a("add.tooltip"));
        this.ak.g = 2;
        this.bz.addElement(this.ak);
        this.an = new B();
        this.an.a = this.b(s, "newpm.r");
        this.an.b = this.bS.b(s.a("newpm.on"));
        this.an.c = this.bS.b(s.a("newpm.off"));
        this.an.f = this.r.b(s.a("newpm.tooltip"));
        this.an.g = 2;
        this.bz.addElement(this.an);
        this.aj = new B();
        this.aj.a = this.b(s, "remove.r");
        this.aj.b = this.bS.b(s.a("remove.on"));
        this.aj.c = this.bS.b(s.a("remove.off"));
        this.aj.d = this.bS.b(s.a("remove.disabled"));
        this.aj.f = this.r.b(s.a("remove.tooltip"));
        this.aj.g = 4;
        this.bz.addElement(this.aj);
        this.ao = new B();
        this.ao.a = this.b(s, "pinfo.r");
        this.ao.b = this.bS.b(s.a("pinfo.on"));
        this.ao.c = this.bS.b(s.a("pinfo.off"));
        this.ao.d = this.bS.b(s.a("pinfo.disabled"));
        this.ao.f = this.r.b(s.a("pinfo.tooltip"));
        this.ao.g = 4;
        this.bz.addElement(this.ao);
        this.ar = new B();
        this.ar.a = this.b(s, "observe_buddy.r");
        this.ar.b = this.bS.b(s.a("observe_buddy.on"));
        this.ar.c = this.bS.b(s.a("observe_buddy.off"));
        this.ar.d = this.bS.b(s.a("observe_buddy.disabled"));
        this.ar.f = this.r.b(s.a("observe.tooltip"));
        this.ar.g = 4;
        this.bz.addElement(this.ar);
        this.al = new B();
        this.al.a = this.b(s, "add.ignore.r");
        this.al.b = this.bS.b(s.a("add.ignore.on"));
        this.al.c = this.bS.b(s.a("add.ignore.off"));
        this.al.d = this.bS.b(s.a("add.ignore.disabled"));
        this.al.f = this.r.b(s.a("add.tooltip"));
        this.al.g = 2;
        this.bz.addElement(this.al);
        this.am = new B();
        this.am.a = this.b(s, "remove.r");
        this.am.b = this.bS.b(s.a("remove.on"));
        this.am.c = this.bS.b(s.a("remove.off"));
        this.am.d = this.bS.b(s.a("remove.disabled"));
        this.am.f = this.r.b(s.a("remove.tooltip"));
        this.am.g = 5;
        this.bz.addElement(this.am);
        if (this.bS.w()) {
            this.V = new B();
            this.V.a = this.b(s, "schedule.r");
            this.V.b = this.bS.b(s.a("schedule.on"));
            this.V.c = this.bS.b(s.a("schedule.off"));
            this.V.d = this.bS.b(s.a("schedule.disabled"));
            this.V.f = this.r.b(s.a("schedule.tooltip"));
            this.V.g = 3;
            this.bz.addElement(this.V);
            final B b13 = new B();
            b13.a = this.aD;
            b13.f = this.r.b(s.a("info.window.tooltip"));
            b13.g = 2;
            this.bz.addElement(b13);
            final B b14 = new B();
            b14.a = this.aF;
            b14.f = this.r.b(s.a("find.window.tooltip"));
            b14.g = 3;
            this.bz.addElement(b14);
        }
        if (ae != null) {
            this.aJ.setText(ae.a);
            this.aw.setText(ae.b);
            this.aL.setSelected(ae.c);
            this.bf.setSelected(ae.d);
            this.bh.setSelected(ae.e);
            this.bj.setSelected(ae.m);
            this.bb.setSelected(ae.f);
            this.aV.setSelectedIndex(ae.k);
            if (this.aY.getItemCount() > 0 && ae.g >= 0 && ae.g < this.aY.getItemCount()) {
                this.aY.setSelectedIndex(ae.g);
            }
            this.aP.setSelectedIndex(ae.h);
            this.aR.removeAllItems();
            for (int k = 2; k <= ae.i; ++k) {
                this.aR.addItem(Integer.toString(k));
            }
            this.aT.removeAllItems();
            for (int l = ae.l; l <= 5; ++l) {
                this.aT.addItem(Integer.toString(l));
            }
            this.aT.setSelectedItem(Integer.toString(ae.i));
            this.aR.setSelectedItem(Integer.toString(ae.l));
            this.bd.setSelectedIndex(ae.j);
        }
        this.cN = true;
    }
    
    private void m() {
        this.at.removeAll();
        int n = 1;
        for (String s = this.r.b("chat.1"); s != null; s = this.r.b("chat." + n)) {
            this.at.add(new JMenuItem(new q(this, s, n)));
            ++n;
        }
        this.at.addSeparator();
        ++n;
        if (this.cG.size() > 0) {
            this.bZ = n - 1;
            for (String string : this.cG.keySet()) {
                if (string.length() > 20) {
                    string = string.substring(0, Math.min(string.length(), 20)) + "...";
                }
                this.at.add(new JMenuItem(new C(this, string, n)));
                ++n;
            }
            this.at.addSeparator();
            ++n;
        }
        this.at.add(new JMenuItem(new i(this, this.r.b("chat.freechat"))));
        if (this.u == this.au) {
            this.u = n;
        }
        this.au = n;
    }
    
    private Color a(final UIProperties uiProperties, final String s) {
        final int[] array = new int[3];
        int n = 0;
        for (StringTokenizer stringTokenizer = new StringTokenizer(uiProperties.a(s), ","); stringTokenizer.hasMoreTokens() && n < 3; array[n++] = Integer.parseInt(stringTokenizer.nextToken().trim())) {}
        return new Color(array[0], array[1], array[2]);
    }
    
    private Rectangle b(final UIProperties uiProperties, final String s) {
        if (this.f == null) {
            this.f = new Hashtable();
        }
        final Rectangle rectangle;
        if ((rectangle = this.f.get(s)) != null) {
            return rectangle;
        }
        final int[] array = new int[4];
        int n = 0;
        for (StringTokenizer stringTokenizer = new StringTokenizer(uiProperties.a(s), ","); stringTokenizer.hasMoreTokens() && n < 4; array[n++] = Integer.parseInt(stringTokenizer.nextToken().trim())) {}
        final Rectangle rectangle2 = new Rectangle(array[0], array[1], array[2], array[3]);
        this.f.put(s, rectangle2);
        return rectangle2;
    }
    
    public Dimension preferredSize() {
        return this.minimumSize();
    }
    
    public Dimension minimumSize() {
        return this.g;
    }
    
    public void paint(final Graphics graphics) {
        synchronized (this.d) {
            if (!this.D) {
                return;
            }
            this.a(this.getSize());
            this.c(graphics);
            this.paintComponents(graphics);
            if (com.daysofwonder.applet.au.d) {
                Toolkit.getDefaultToolkit().sync();
            }
            this.bw.d(graphics);
            this.a(graphics);
        }
    }
    
    public void a(final Graphics graphics) {
        if (com.daysofwonder.applet.au.d) {
            Toolkit.getDefaultToolkit().sync();
        }
    }
    
    public synchronized void a(final Dimension l) {
        if (this.l == null || l.width != this.l.width || l.height != this.l.height) {
            if (l.width == 0 || l.height == 0) {
                return;
            }
            this.l = l;
            this.m = this.bS.createImage(l.width, l.height);
            final Graphics graphics = this.m.getGraphics();
            Image image;
            if (this.s.b("background") != null) {
                image = this.bS.b(this.s.b("background"));
            }
            else {
                image = this.bS.b("img/backtile.gif");
            }
            if (this.J) {
                final int width = image.getWidth(this);
                final int height = image.getHeight(this);
                for (int i = 0; i < l.width; i += width) {
                    for (int j = 0; j < l.width; j += height) {
                        graphics.drawImage(image, i, j, i + width, j + height, 0, 0, width, height, this);
                    }
                }
            }
            else {
                final int width2 = image.getWidth(this);
                final int height2 = image.getHeight(this);
                final int n = (l.width - width2) / 2;
                final int n2 = (l.height - height2) / 2;
                graphics.setColor(this.K);
                graphics.fillRect(0, 0, l.width, l.height);
                graphics.drawImage(image, n, n2, n + width2, n2 + height2, 0, 0, width2, height2, this);
            }
            graphics.dispose();
            this.requestFocus();
            if (this.L != null) {
                this.L.e = this.c(this.L);
            }
            if (this.M != null) {
                this.M.e = this.c(this.M);
            }
            if (this.N != null) {
                this.N.e = this.M.e;
            }
            if (this.O != null) {
                this.O.e = this.c(this.O);
            }
            if (this.ac != null) {
                this.ac.e = this.c(this.ac);
            }
            if (this.ab != null) {
                this.ab.e = this.c(this.ab);
            }
            if (this.ad != null) {
                this.ad.e = this.c(this.ad);
            }
            if (this.af != null) {
                this.af.e = this.c(this.af);
            }
            if (this.ag != null) {
                this.ag.e = this.c(this.ag);
            }
            if (this.ae != null) {
                this.ae.e = this.c(this.ae);
            }
            if (this.ah != null) {
                this.ah.e = this.c(this.ah);
            }
            if (this.ai != null) {
                this.ai.e = this.c(this.ai);
            }
            if (this.ap != null) {
                this.ap.e = this.c(this.ap);
            }
            if (this.aq != null) {
                this.aq.e = this.c(this.aq);
            }
            this.notify();
        }
    }
    
    public void b(final Graphics graphics) {
        if (this.m != null) {
            graphics.drawImage(this.m, 0, 0, this.m.getWidth(this), this.m.getHeight(this), this);
        }
    }
    
    public void c(final Graphics graphics) {
        try {
            this.b(graphics);
            this.g(graphics);
            this.d(graphics);
            graphics.setFont(this.v);
            graphics.drawString(this.bR, 350, 52);
            graphics.setFont(this.v);
        }
        catch (Exception ex) {
            com.daysofwonder.util.t.a(ex);
        }
    }
    
    public void d(final Graphics graphics) {
        if (graphics != null) {
            this.a(graphics, this.L, this.k(), false);
            this.a(graphics, this.W, true, false);
            this.a(graphics, this.X, true, false);
            if (this.q()) {
                this.a(graphics, this.M, true, false);
            }
            else if (this.o()) {
                this.a(graphics, this.N, true, false);
            }
            else {
                this.a(graphics, this.M, false, false);
            }
            this.a(graphics, this.O, this.r(), false);
            this.a(graphics, this.aa, true, this.E);
            this.a(graphics, this.ab, true, false);
            this.a(graphics, this.ad, true, false);
            this.a(graphics, this.af, true, false);
            this.a(graphics, this.ae, true, false);
            this.e(graphics);
            if (this.i.aC()) {
                this.a(graphics, this.ag, true, false);
            }
            this.a(graphics, this.P.a);
            this.a(graphics, this.Y, true, this.bS.y());
            graphics.setColor(Color.white);
            graphics.drawImage(this.bW.b, this.bW.a.x, this.bW.a.y, null);
            com.daysofwonder.applet.aL.a(graphics, this.bW.a, this.r.b("filters"), this.h, 1);
            graphics.setColor(Color.black);
            if (this.p()) {
                this.a(graphics, this.Z, true, false);
            }
            if (this.bk == 1) {
                this.b(graphics, "create.header", this.aB);
                graphics.setColor(this.bl);
                graphics.fillRect(this.ax.x, this.ax.y, this.ax.width, this.ax.height);
                graphics.fillRect(this.bT.x, this.bT.y, this.bT.width, this.bT.height);
                graphics.setColor(Color.black);
                this.a(graphics, this.r.b("create.name"), this.ay);
                this.a(graphics, this.r.b("create.bottext1"), this.aA);
                this.a(graphics, this.r.b("create.minplayers"), this.bU);
                this.a(graphics, this.r.b("create.maxplayers"), this.aM);
                this.a(graphics, this.r.b("create.minkarma"), this.aN);
                this.a(graphics, this.r.b("create.maptext"), this.aX);
                this.a(graphics, this.r.b("create.lurkable"), this.aZ);
                if (this.bb.isSelected()) {
                    this.a(graphics, this.r.b("create.pass"), this.az);
                }
                this.a(graphics, this.U, this.s(), false);
            }
            else if (this.bk == 2) {
                this.b(graphics, "info.header", this.aC);
                graphics.setColor(this.bl);
                graphics.fillRect(this.aD.x, this.aD.y, this.aD.width, this.aD.height);
                graphics.setColor(Color.black);
                if (this.bE != null) {
                    this.a(graphics, this.bE, this.b(this.s, "info.name.r"), this.w);
                    if (this.G) {
                        if (this.bC > 0 && this.bD > 0) {
                            if (this.bB > 0) {
                                this.a(graphics, this.r.b("info.rank") + " " + this.bB + " (" + ((this.bD >= 20) ? this.r.b("info.established") : this.r.b("info.provisional")) + ")", this.b(this.s, "info.rank.r"));
                            }
                            else {
                                this.a(graphics, this.r.b("info.rank") + " " + this.r.b("info.inactive"), this.b(this.s, "info.rank.r"));
                            }
                            this.a(graphics, this.r.b("info.score") + " " + this.bC, this.b(this.s, "info.score.r"));
                            this.a(graphics, this.r.b("info.nbgames") + " " + this.bD, this.b(this.s, "info.nbgames.r"));
                        }
                        else if (this.bD == -1) {
                            this.a(graphics, this.r.b("info.guest"), this.b(this.s, "info.rank.r"));
                        }
                        else {
                            this.a(graphics, this.r.b("info.neverplayed"), this.b(this.s, "info.rank.r"));
                        }
                        if (this.bJ != null && this.bK != null && this.bL != null) {
                            final Rectangle b = this.b(this.s, "info.karma.r");
                            graphics.setColor(Color.black);
                            final Font font = graphics.getFont();
                            graphics.setFont(this.v);
                            final int n = b.x + 2;
                            graphics.drawString(this.r.b("info.karma"), n, b.y + b.height - 2);
                            final FontMetrics fontMetrics = graphics.getFontMetrics();
                            final int n2 = n + fontMetrics.stringWidth(this.r.b("info.karma") + " ");
                            graphics.drawString(this.bJ, n2, b.y + b.height - 2);
                            graphics.setColor(this.bK);
                            graphics.drawString(this.bL, n2 + fontMetrics.stringWidth(this.bJ + " "), b.y + b.height - 2);
                            graphics.setFont(font);
                            graphics.setColor(Color.black);
                        }
                        this.a(graphics, this.r.b("info.lang") + " " + this.bF, this.b(this.s, "info.lang.r"));
                        this.a(graphics, this.r.b("info.tz") + " " + this.bG, this.b(this.s, "info.tz.r"));
                        if (this.bH != null && this.bI != null) {
                            this.a(graphics, this.r.b("info.playtime") + " " + this.bH + " " + this.r.b("info.and") + " " + this.bI, this.b(this.s, "info.playtime.r"));
                        }
                        this.a(graphics, this.ak, this.cs != null && this.cs.b() != this.i.aD() && !this.a(this.cs) && !this.b(this.cs), false);
                        this.a(graphics, this.al, this.cs != null && this.cs.b() != this.i.aD() && !this.b(this.cs) && !this.a(this.cs), false);
                        this.a(graphics, this.an, this.bS.q() && this.cs != null, false);
                    }
                    else {
                        this.a(graphics, this.r.b("info.loading"), this.b(this.s, "info.loading.r"));
                    }
                }
            }
            else if (this.bk == 3) {
                this.b(graphics, "find.header", this.aE);
                graphics.setColor(this.bl);
                graphics.fillRect(this.aF.x, this.aF.y, this.aF.width, this.aF.height);
                graphics.setColor(Color.black);
                if (this.H) {
                    if (this.bN != null) {
                        this.a(graphics, this.r.b("find.lastgame") + " " + this.bN, this.b(this.s, "find.lastgame.r"));
                    }
                    else {
                        this.a(graphics, this.r.b("find.nolastgame"), this.b(this.s, "find.lastgame.r"));
                    }
                    if (this.V != null) {
                        this.a(graphics, this.r.b("find.nexttext"), this.b(this.s, "find.nexttext.r"));
                        if (this.bO != null) {
                            this.a(graphics, this.bO, this.b(this.s, "find.next.r"), this.w);
                        }
                        else {
                            this.a(graphics, this.r.b("find.noscheduled"), this.b(this.s, "find.next.r"), this.w);
                        }
                    }
                    final int n3 = (int)Math.floor(this.bM);
                    if (n3 > 0) {
                        this.a(graphics, this.r.b("find.frequency") + " " + n3 + " " + this.r.b("find.minutes"), this.b(this.s, "find.frequency.r"));
                    }
                    this.a(graphics, this.V, true, false);
                }
                else {
                    this.a(graphics, this.r.b("find.loading"), this.b(this.s, "find.loading.r"));
                }
            }
            else if (this.bk == 4) {
                this.f(graphics);
                graphics.setColor(this.bl);
                graphics.fillRect(this.aH.x, this.aH.y, this.aH.width, this.aH.height);
                graphics.setColor(Color.black);
                this.a(graphics, this.aj, this.a != null && this.d(this.a), false);
                this.a(graphics, this.ao, this.a != null, false);
                this.a(graphics, this.ar, this.a != null && this.b(this.a), false);
            }
            else if (this.bk == 5) {
                this.f(graphics);
                graphics.setColor(this.bl);
                graphics.fillRect(this.aH.x, this.aH.y, this.aH.width, this.aH.height);
                graphics.setColor(Color.black);
                this.a(graphics, this.am, this.b != null && this.c(this.b), false);
            }
        }
    }
    
    protected void e(final Graphics graphics) {
        graphics.setColor(Color.white);
        if ((this.R == null && this.B) || (this.R != null && this.R == this.ah)) {
            graphics.drawImage(this.ai.c, this.ai.a.x, this.ai.a.y, null);
            com.daysofwonder.applet.aL.a(graphics, this.ai.a, this.r.b("closedgames") + " (" + this.F + ")", this.h, 1);
            graphics.drawImage(((this.R != null && this.cP) || this.R == null) ? this.ah.b : this.ah.c, this.ah.a.x, this.ah.a.y, null);
            com.daysofwonder.applet.aL.a(graphics, this.ah.a, this.r.b("opengames") + " (" + ((this.ca != null) ? this.ca.size() : "0") + ")", this.h, 1);
        }
        else {
            com.daysofwonder.util.t.a("displayTabs(): currentTab: closedTag " + this.cP);
            graphics.drawImage(this.ah.c, this.ah.a.x, this.ah.a.y, null);
            com.daysofwonder.applet.aL.a(graphics, this.ah.a, this.r.b("opengames") + " (" + ((this.ca != null) ? this.ca.size() : "0") + ")", this.h, 1);
            graphics.drawImage(((this.R != null && this.cP) || this.R == null) ? this.ai.b : this.ai.c, this.ai.a.x, this.ai.a.y, null);
            com.daysofwonder.applet.aL.a(graphics, this.ai.a, this.r.b("closedgames") + " (" + this.F + ")", this.h, 1);
        }
        graphics.setColor(Color.black);
    }
    
    protected void f(final Graphics graphics) {
        if (this.bk == 4 || this.bk == 5) {
            graphics.setColor(Color.white);
            if ((this.S == null && !this.C) || (this.S != null && this.S == this.aq)) {
                com.daysofwonder.util.t.a("displayTabs(): currentTab: openTag " + this.S);
                graphics.drawImage(this.ap.c, this.ap.a.x, this.ap.a.y, null);
                com.daysofwonder.applet.aL.a(graphics, this.ap.a, this.r.b("buddylist"), this.h, 1);
                graphics.drawImage(((this.S != null && this.cQ) || this.S == null) ? this.aq.b : this.aq.c, this.aq.a.x, this.aq.a.y, null);
                com.daysofwonder.applet.aL.a(graphics, this.aq.a, this.r.b("ignorelist"), this.h, 1);
            }
            else {
                com.daysofwonder.util.t.a("displayTabs(): currentTab: closedTag " + this.cQ);
                graphics.drawImage(this.aq.c, this.aq.a.x, this.aq.a.y, null);
                com.daysofwonder.applet.aL.a(graphics, this.aq.a, this.r.b("ignorelist"), this.h, 1);
                graphics.drawImage(((this.S != null && this.cQ) || this.S == null) ? this.ap.b : this.ap.c, this.ap.a.x, this.ap.a.y, null);
                com.daysofwonder.applet.aL.a(graphics, this.ap.a, this.r.b("buddylist"), this.h, 1);
            }
            graphics.setColor(Color.black);
        }
    }
    
    protected void a(final Graphics graphics, final String s, final Rectangle rectangle) {
        graphics.setColor(Color.black);
        final Font font = graphics.getFont();
        graphics.setFont(this.v);
        graphics.drawString(s, rectangle.x + 2, rectangle.y + rectangle.height - 2);
        graphics.setFont(font);
    }
    
    protected void a(final Graphics graphics, final String s, final Rectangle rectangle, final Font font) {
        graphics.setColor(Color.black);
        final Font font2 = graphics.getFont();
        graphics.setFont(font);
        graphics.drawString(s, rectangle.x + 2, rectangle.y + rectangle.height - 2);
        graphics.setFont(font2);
    }
    
    protected void b(final Graphics graphics, final String s, final Rectangle rectangle) {
        graphics.setColor(this.bm);
        graphics.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        final Font font = graphics.getFont();
        graphics.setFont(this.A);
        graphics.setColor(Color.white);
        final String b = this.r.b(s);
        if (b != null) {
            graphics.drawString(b, rectangle.x + 2, rectangle.y + rectangle.height - 2);
        }
        else if (this.r.b(s + ".text.1") != null) {
            int n = 1;
            for (String s2 = this.r.b(s + ".text." + n); s2 != null; s2 = this.r.b(s + ".text." + n)) {
                graphics.drawString(s2, rectangle.x + Integer.parseInt(this.r.b(s + ".pos." + n)), rectangle.y + rectangle.height - 2);
                ++n;
            }
        }
        graphics.setColor(Color.black);
        graphics.setFont(font);
    }
    
    public void a(final Graphics graphics, final B b, final boolean b2, final boolean b3) {
        if (b != null) {
            final Image image = b2 ? ((this.Q != null) ? ((this.Q == b && this.cO) ? b.b : b.c) : (b3 ? b.b : b.c)) : b.d;
            if (image != null) {
                if (b == this.U || b == this.V || b == this.aj || b == this.am || b == this.ao || b == this.an || b == this.ak || b == this.al || b == this.ar) {
                    graphics.setColor(this.bl);
                    graphics.fillRect(b.a.x, b.a.y, b.a.width, b.a.height);
                    graphics.setColor(Color.black);
                }
                else if (b.e != null) {
                    graphics.drawImage(b.e, b.a.x, b.a.y, null);
                }
                graphics.drawImage(image, b.a.x, b.a.y, this);
            }
        }
    }
    
    protected void a(final Graphics graphics, final Rectangle rectangle) {
        graphics.drawImage(this.m, rectangle.x, rectangle.y, rectangle.x + rectangle.width, rectangle.y + rectangle.height, rectangle.x, rectangle.y, rectangle.x + rectangle.width, rectangle.y + rectangle.height, this);
        if (this.t != null) {
            graphics.drawString(this.t, rectangle.x, rectangle.y + rectangle.height - graphics.getFontMetrics().getMaxDescent() - 1);
        }
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() == 2) {
            if (mouseEvent.getSource() == this.ch || mouseEvent.getSource() == this.ci) {
                if (this.q()) {
                    this.a(this.M, null);
                }
                else if (this.o()) {
                    this.a(this.N, null);
                }
            }
            else if (mouseEvent.getSource() == this.bs && this.a != null) {
                com.daysofwonder.util.t.a("Double click Buddy on " + this.a);
                if (this.a != null && this.a.d.a() == 3) {
                    this.a(this.M, this.a.d.b());
                }
            }
        }
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        if (this.E) {
            this.j = mouseEvent.getPoint();
            if (mouseEvent.getComponent() != this) {
                final Point location = mouseEvent.getComponent().getLocation();
                this.j.translate(location.x, location.y);
            }
            if (this.cI == null) {
                if (this.by != null) {
                    this.by.b();
                    this.by = null;
                }
                this.cI = this.a(this.j);
                if (this.cI != null && this.cI.f != null) {
                    (this.by = new m(this.cI.f, (JComponent)mouseEvent.getComponent(), this.h)).a(this.j.x, this.j.y, this.cI.a);
                }
            }
            else if (!this.cI.a.contains(this.j)) {
                if (this.by != null) {
                    this.by.b();
                    this.by = null;
                }
                this.cI = null;
            }
            else {
                final B a;
                if ((a = this.a(this.j)) != this.cI) {
                    if (this.by != null) {
                        this.by.b();
                        this.by = null;
                    }
                    this.cI = a;
                    if (this.cI != null && this.cI.f != null) {
                        (this.by = new m(this.cI.f, (JComponent)mouseEvent.getComponent(), this.h)).a(this.j.x, this.j.y, this.cI.a);
                    }
                }
            }
        }
    }
    
    public B a(final Point point) {
        B b = null;
        final Enumeration<B> elements = this.bz.elements();
        while (elements.hasMoreElements()) {
            final B b2 = elements.nextElement();
            if (b2 != null && b2.a != null && b2.a.contains(point)) {
                boolean b3 = false;
                if (b2.g != 0) {
                    if (b2.g == this.bk) {
                        b3 = true;
                    }
                }
                else if (b2.h instanceof R) {
                    if (((R)b2.h).n()) {
                        b3 = true;
                    }
                }
                else if (b2.h instanceof Component) {
                    if (((Component)b2.h).isVisible()) {
                        b3 = true;
                    }
                }
                else {
                    b3 = true;
                }
                if (!b3 || (b != null && (b == null || !a(b.a, b2.a)))) {
                    continue;
                }
                b = b2;
            }
        }
        return b;
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        if (mouseEvent.getComponent() != this) {
            return;
        }
        this.j = mouseEvent.getPoint();
        if (this.by != null) {
            this.by.b();
            this.cI = null;
            this.by = null;
        }
        if (this.L.a.contains(this.j) && this.k()) {
            this.Q = this.L;
        }
        else if (this.M.a.contains(this.j) && this.q()) {
            this.Q = this.M;
        }
        else if (this.N.a.contains(this.j) && this.o()) {
            this.Q = this.N;
        }
        else if (this.O.a.contains(this.j) && this.r()) {
            this.Q = this.O;
        }
        else if (this.ac != null && this.ac.a.contains(this.j)) {
            this.Q = this.ac;
        }
        else if (this.aa.a.contains(this.j)) {
            this.Q = this.aa;
        }
        else if (this.ab != null && this.ab.a.contains(this.j)) {
            this.Q = this.ab;
        }
        else if (this.ad != null && this.ad.a.contains(this.j)) {
            this.Q = this.ad;
        }
        else if (this.af != null && this.af.a.contains(this.j)) {
            this.Q = this.af;
        }
        else if (this.ag.a.contains(this.j) && this.i.aC()) {
            this.Q = this.ag;
        }
        else if (this.Y.a.contains(this.j)) {
            this.Q = this.Y;
        }
        else if (this.Z.a.contains(this.j) && this.p()) {
            this.Q = this.Z;
        }
        else if (this.W.a.contains(this.j)) {
            this.Q = this.W;
        }
        else if (this.X.a.contains(this.j)) {
            this.Q = this.X;
        }
        else if (this.ae.a.contains(this.j)) {
            this.Q = this.ae;
        }
        else if (this.U.a.contains(this.j) && this.s()) {
            this.Q = this.U;
        }
        else if (this.V != null && this.V.a.contains(this.j) && this.bk == 3) {
            this.Q = this.V;
        }
        else if (this.ak != null && this.ak.a.contains(this.j) && this.bk == 2 && !this.a(this.cs) && !this.b(this.cs)) {
            this.Q = this.ak;
        }
        else if (this.al != null && this.al.a.contains(this.j) && this.bk == 2 && !this.a(this.cs) && !this.b(this.cs)) {
            this.Q = this.al;
        }
        else if (this.an != null && this.an.a.contains(this.j) && this.bk == 2 && this.bS.q() && this.cs != null) {
            this.Q = this.an;
        }
        else if (this.aj != null && this.aj.a.contains(this.j) && this.bk == 4 && this.a != null && this.d(this.a)) {
            this.Q = this.aj;
        }
        else if (this.am != null && this.am.a.contains(this.j) && this.bk == 5 && this.b != null && this.c(this.b)) {
            this.Q = this.am;
        }
        else if (this.ao != null && this.ao.a.contains(this.j) && this.bk == 4 && this.a != null) {
            this.Q = this.ao;
        }
        else if (this.ar != null && this.ar.a.contains(this.j) && this.bk == 4 && this.a != null && this.b(this.a)) {
            this.Q = this.ar;
        }
        else if (this.bw.n() && this.as.contains(this.j)) {
            this.at.show(this, mouseEvent.getPoint().x, mouseEvent.getPoint().y);
        }
        else if (!this.bw.n() && this.P.a.contains(this.j)) {
            this.at.show(this, mouseEvent.getPoint().x, mouseEvent.getPoint().y);
        }
        else if (this.bW != null && this.bW.a.contains(this.j)) {
            this.bV.show(this, mouseEvent.getPoint().x, mouseEvent.getPoint().y);
        }
        else if (this.ah != null && this.ah.a.contains(this.j)) {
            this.R = this.ah;
        }
        else if (this.ai != null && this.ai.a.contains(this.j)) {
            this.R = this.ai;
        }
        else if (this.ap != null && this.ap.a.contains(this.j)) {
            this.S = this.ap;
        }
        else if (this.aq != null && this.aq.a.contains(this.j)) {
            this.S = this.aq;
        }
        if (this.Q != null) {
            this.cO = true;
            this.repaint(this.Q.a.x, this.Q.a.y, this.Q.a.width, this.Q.a.height);
        }
        else if (this.R != null) {
            this.cP = true;
            this.repaint(this.ah.a);
            this.repaint(this.ai.a);
        }
        else if (this.S != null) {
            this.cQ = true;
            this.repaint(this.ap.a);
            this.repaint(this.aq.a);
        }
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        if (mouseEvent.getComponent() != this) {
            return;
        }
        this.k = mouseEvent.getPoint();
        if (this.Q != null && !this.Q.a.contains(this.k)) {
            this.cO = false;
            this.repaint(this.Q.a.x, this.Q.a.y, this.Q.a.width, this.Q.a.height);
        }
        else if (this.Q != null && this.Q.a.contains(this.k)) {
            this.cO = true;
            this.repaint(this.Q.a.x, this.Q.a.y, this.Q.a.width, this.Q.a.height);
        }
        if (this.R != null && !this.R.a.contains(this.k)) {
            this.cP = true;
            this.repaint(this.ah.a);
            this.repaint(this.ai.a);
        }
        else if (this.R != null && this.R.a.contains(this.k)) {
            this.cP = true;
            this.repaint(this.ah.a);
            this.repaint(this.ai.a);
        }
        if (this.S != null && !this.S.a.contains(this.k)) {
            this.cQ = true;
            this.repaint(this.ap.a);
            this.repaint(this.aq.a);
        }
        else if (this.S != null && this.S.a.contains(this.k)) {
            this.cQ = true;
            this.repaint(this.ap.a);
            this.repaint(this.aq.a);
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        if (mouseEvent.getComponent() != this) {
            return;
        }
        if (this.bw.n() && !this.bw.u().contains(mouseEvent.getPoint())) {
            this.bw.b((FocusEvent)null);
        }
        if (this.Q == this.Y || this.Q == this.aa) {
            this.a(this.Q, mouseEvent);
            this.Q = null;
        }
        else if (this.Q != null && this.Q.a.contains(mouseEvent.getPoint())) {
            this.cO = false;
            this.repaint(this.Q.a.x, this.Q.a.y, this.Q.a.width, this.Q.a.height);
            this.a(this.Q, mouseEvent);
            this.Q = null;
        }
        else if (this.Q != null && !this.Q.a.contains(mouseEvent.getPoint())) {
            this.cO = false;
            this.repaint(this.Q.a.x, this.Q.a.y, this.Q.a.width, this.Q.a.height);
            this.Q = null;
        }
        if (this.R != null && this.R.a.contains(mouseEvent.getPoint())) {
            this.cP = true;
            this.repaint(this.ah.a);
            this.repaint(this.ai.a);
            this.a(this.R, mouseEvent);
            this.R = null;
        }
        else if (this.R != null && !this.R.a.contains(mouseEvent.getPoint())) {
            this.cP = true;
            this.repaint(this.ah.a);
            this.repaint(this.ai.a);
            this.R = null;
        }
        if (this.S != null && this.S.a.contains(mouseEvent.getPoint())) {
            this.cQ = true;
            this.repaint(this.ap.a);
            this.repaint(this.aq.a);
            this.a(this.S, mouseEvent);
            this.R = null;
        }
        else if (this.S != null && !this.S.a.contains(mouseEvent.getPoint())) {
            this.cQ = true;
            this.repaint(this.ap.a);
            this.repaint(this.aq.a);
            this.S = null;
        }
    }
    
    public int h() {
        com.daysofwonder.util.t.a("performTableAction: " + this.cE);
        int a = -1;
        if (this.cE == 1) {
            if (!this.i.am()) {
                boolean b = false;
                for (int i = 0; i < this.cD.g.length; ++i) {
                    if (this.cD.g[i] && this.cD.c[i] == this.i.aD()) {
                        b = true;
                        break;
                    }
                }
                if (this.cD.j && !b) {
                    final Frame l = this.l();
                    com.daysofwonder.util.t.a("dialog frame: " + l);
                    final D a2 = com.daysofwonder.applet.aO.a(l, this.r);
                    com.daysofwonder.util.t.a("win go");
                    a2.a();
                    com.daysofwonder.util.t.a("win endd go");
                    if (!a2.b()) {
                        com.daysofwonder.util.t.a("repaint");
                        this.repaint();
                        return -1;
                    }
                    this.cF = a2.c();
                }
                if (b) {
                    this.i.b(true);
                }
                this.cE = 2;
            }
            else {
                a = 1;
            }
        }
        if (this.cE == 2 && this.cC != null) {
            com.daysofwonder.util.t.a("SELECT: " + this.cC);
            a = this.i.a(this.cC, this.cF);
            this.cC = null;
            this.cF = null;
            if (a <= 0) {
                com.daysofwonder.util.t.a("result: " + a);
                this.b(-a, null);
            }
        }
        if (this.cE == 3 && this.cC != null && this.cD.m) {
            a = (this.i.b(this.cC) ? 1 : -1);
            this.cC = null;
            this.cF = null;
            if (a <= 0) {
                this.b(1, null);
            }
        }
        if (this.cE == 4) {
            a = 1;
        }
        com.daysofwonder.util.t.a("performTableAction: return " + a);
        return a;
    }
    
    private void a(final B b, final Object o) {
        if (b == this.L && this.k()) {
            if (this.u >= 0 && ((this.bZ > 0 && this.u < this.bZ) || (this.bZ == 0 && this.u < this.au))) {
                this.i.m(this.u);
                this.u = this.au;
                this.bw.a("");
                this.bw.c(true);
                this.bw.b(true);
                this.bw.b((FocusEvent)null);
                this.repaint();
            }
            else if (this.u == this.au || (this.bZ > 0 && this.u >= this.bZ)) {
                String s = this.bw.i().trim();
                int b2 = -1;
                com.daysofwonder.util.t.a("chat:" + s);
                if (s.startsWith("/")) {
                    final String upperCase = s.substring(1, s.indexOf(32)).toUpperCase();
                    final String upperCase2 = s.toUpperCase();
                    com.daysofwonder.util.t.a("whisper login:" + upperCase);
                    this.cc.b().b().a();
                    try {
                        for (final k k : this.cc) {
                            final String upperCase3 = k.a().toUpperCase();
                            if (upperCase3.startsWith(upperCase)) {
                                com.daysofwonder.util.t.a("found whisper" + k.a());
                                if (upperCase2.indexOf(k.a()) >= 0) {
                                    s = s.substring(upperCase2.indexOf(upperCase3) + upperCase3.length());
                                }
                                else {
                                    s = s.substring(upperCase.length() + 1);
                                }
                                com.daysofwonder.util.t.a("chat result" + s);
                                b2 = k.b();
                                break;
                            }
                        }
                    }
                    finally {
                        this.cc.b().b().b();
                    }
                    if (b2 == -1) {
                        return;
                    }
                }
                if (b2 == -1 && s != null && !this.cG.containsKey(s)) {
                    this.cG.put(s, null);
                    this.m();
                }
                this.i.a(s, b2);
                this.bw.a("");
                this.bw.b((FocusEvent)null);
                this.repaint();
            }
        }
        else if (b == this.O && this.r()) {
            this.bk = 1;
            this.i.aj();
            this.cx.setVisible(false);
            this.cB.setVisible(false);
            this.bq.clearSelection();
            if (this.bb.isSelected()) {
                this.aJ.setEnabled(true);
                this.aJ.setVisible(true);
            }
            this.aw.setEnabled(true);
            this.aw.setVisible(true);
            this.aP.setVisible(true);
            this.aP.setEnabled(true);
            this.aR.setVisible(true);
            this.aR.setEnabled(true);
            this.aT.setVisible(true);
            this.aT.setEnabled(true);
            if (this.aV.getItemCount() == 5 && this.e >= 0) {
                for (int i = 4; i > b(this.e); --i) {
                    this.aV.removeItemAt(i);
                }
            }
            this.aV.setVisible(true);
            this.aV.setEnabled(true);
            this.aY.setVisible(true);
            this.aY.setEnabled(true);
            this.bb.setVisible(true);
            this.bb.setEnabled(true);
            this.aL.setVisible(true);
            this.aL.setEnabled(true);
            this.bh.setVisible(true);
            this.bh.setEnabled(true);
            this.bj.setVisible(true);
            this.bj.setEnabled(true);
            this.bf.setVisible(true);
            this.bf.setEnabled(true);
            this.bd.setVisible(true);
            this.bd.setEnabled(true);
            this.repaint();
        }
        else if (b == this.U && this.s()) {
            String s2 = this.aJ.getText();
            if (!this.bb.isSelected()) {
                s2 = "";
            }
            final boolean b3 = !this.aL.isSelected();
            final boolean b4 = true;
            final a ak = this.i.aK();
            String s3 = null;
            int j = 0;
            int n = 0;
            while (j < y.c.length) {
                if (ak.a(y.c[j])) {
                    if (n == this.aY.getSelectedIndex()) {
                        s3 = y.c[j];
                        break;
                    }
                    ++n;
                }
                ++j;
            }
            if (s3 == null) {
                return;
            }
            final int selectedIndex = this.aP.getSelectedIndex();
            final int int1 = Integer.parseInt((String)this.aR.getSelectedItem());
            final int int2 = Integer.parseInt((String)this.aT.getSelectedItem());
            final int selectedIndex2 = this.bd.getSelectedIndex();
            final boolean selected = this.bf.isSelected();
            final boolean selected2 = this.bh.isSelected();
            final boolean selected3 = this.bj.isSelected();
            final String text = this.aw.getText();
            final String substring = text.substring(0, Math.min(text.length(), 25));
            if (s2.length() > 0) {
                s2 = s2.substring(0, Math.min(s2.length(), 25));
            }
            if (((G)this.i).a(substring, s2, b3, b4, selectedIndex, y.cR[this.aV.getSelectedIndex()] - 20 + 1, new com.daysofwonder.tt.m(s3, int2, selectedIndex2, selected2, selected, int1, selected3), s3) < 0) {
                this.b(2, null);
            }
            else {
                this.cE = 4;
                this.bx.b();
            }
        }
        else if (b == this.M && this.q()) {
            com.daysofwonder.util.t.a("fJoinButton");
            this.cD = this.cl;
            this.cC = ((this.cD != null) ? Integer.valueOf(this.cD.n) : this.cD.n);
            if (this.cC != null && this.cD != null) {
                String c = null;
                if (this.cD.j && this.cD.a.equals("Resumed Game")) {
                    this.cE = 1;
                    this.bx.b();
                    return;
                }
                boolean b5 = false;
                for (int l = 0; l < this.cD.g.length; ++l) {
                    if (this.cD.g[l] && this.cD.c[l] == this.i.aD()) {
                        b5 = true;
                        break;
                    }
                }
                if (this.cD.j && !b5) {
                    final Frame m = this.l();
                    com.daysofwonder.util.t.a("dialog frame: " + m);
                    final D a = com.daysofwonder.applet.aO.a(m, this.r);
                    com.daysofwonder.util.t.a("win go");
                    a.a();
                    com.daysofwonder.util.t.a("win endd go");
                    if (!a.b()) {
                        com.daysofwonder.util.t.a("repaint");
                        this.repaint();
                        return;
                    }
                    c = a.c();
                }
                if (b5) {
                    this.i.b(true);
                }
                this.cF = c;
                this.cE = 2;
                this.bx.b();
            }
            else {
                this.cC = null;
                this.cD = null;
                this.cF = null;
                this.cE = 0;
            }
        }
        else if (b == this.ar && this.a != null && this.b(this.a)) {
            this.cC = this.a(this.a);
            this.cD = this.b(this.cC);
            if (this.cC != null && this.cD != null && this.cD.m) {
                this.cE = 3;
                this.bx.b();
            }
            else {
                this.cC = null;
                this.cD = null;
                this.cE = 0;
            }
        }
        else if (b == this.N && this.a(o)) {
            if (o == null || !(o instanceof Integer)) {
                if (!this.B) {
                    this.cD = this.cl;
                    this.cC = ((this.cD != null) ? Integer.valueOf(this.cD.n) : this.cD.n);
                }
            }
            else {
                this.cC = (Integer)o;
                this.cD = this.b(this.cC);
            }
            if (this.cC != null && this.cD != null && this.cD.m) {
                this.cE = 3;
                this.bx.b();
            }
            else {
                this.cC = null;
                this.cD = null;
                this.cE = 0;
            }
        }
        else if (b == this.W) {
            ++this.co;
            if (this.co >= this.cp) {
                this.co = this.cp - 1;
            }
            this.j();
        }
        else if (b == this.X) {
            --this.co;
            if (this.co < 0) {
                this.co = 0;
            }
            this.j();
        }
        else if (b == this.ac) {
            this.bS.n();
        }
        else if (b == this.V) {
            this.bS.v();
        }
        else if (b == this.aa) {
            if (this.E && this.by != null) {
                this.by.b();
                this.by = null;
            }
            this.E = !this.E;
            this.repaint();
        }
        else if (b == this.ab) {
            this.bS.o();
        }
        else if (b == this.ad) {
            this.bS.s();
        }
        else if (b == this.Y) {
            this.bS.a(!this.bS.y());
            this.repaint();
        }
        else if (b == this.Z) {
            if (this.cs != null) {
                this.i.k(this.cs.b());
            }
        }
        else if (b == this.af) {
            if (this.bS.u()) {
                this.bS.t();
            }
            else {
                this.bS.o();
            }
        }
        else if (b == this.ag) {
            com.daysofwonder.util.t.a("resurrect: " + this.bS.A());
            com.daysofwonder.util.t.a("resurrect: " + this.bS.C());
            com.daysofwonder.util.t.a("resurrect: " + this.bS.D());
            if (this.bS.A()) {
                this.i.a(this.bS.C(), this.bS.D(), this.bS.B());
            }
            else {
                boolean b6 = false;
                Integer value = null;
                this.cb.b().b().a();
                try {
                    for (final ar cd : this.cb) {
                        if (!cd.l) {
                            com.daysofwonder.util.t.a("Analysing: " + cd.n);
                            for (int n2 = 0; n2 < cd.g.length; ++n2) {
                                if (cd.g[n2] && cd.c[n2] == this.i.aD()) {
                                    com.daysofwonder.util.t.a("Candidate to resurrect: " + cd.n);
                                    value = cd.n;
                                    this.cD = cd;
                                    b6 = true;
                                }
                            }
                        }
                    }
                }
                finally {
                    this.cb.b().b().b();
                }
                if (b6 && value != null) {
                    com.daysofwonder.util.t.a("hotswap Using last candidate: " + value);
                    this.i.b(true);
                    this.cC = value;
                    this.cE = 2;
                }
                else {
                    this.cE = 1;
                }
            }
            this.bx.b();
        }
        else if (b == this.ah) {
            this.a(true);
        }
        else if (b == this.ai) {
            this.a(false);
        }
        else if (b == this.ap && this.bk == 5) {
            this.b(true);
        }
        else if (b == this.aq && this.bk == 4) {
            this.b(false);
        }
        else if (b == this.ae) {
            this.bq.clearSelection();
            this.aw.setEnabled(false);
            this.aw.setVisible(false);
            this.aJ.setEnabled(false);
            this.aJ.setVisible(false);
            this.aP.setVisible(false);
            this.aP.setEnabled(false);
            this.aR.setVisible(false);
            this.aR.setEnabled(false);
            this.aT.setVisible(false);
            this.aT.setEnabled(false);
            this.aV.setVisible(false);
            this.aV.setEnabled(false);
            this.aY.setVisible(false);
            this.aY.setEnabled(false);
            this.bb.setVisible(false);
            this.bb.setEnabled(false);
            this.aL.setVisible(false);
            this.aL.setEnabled(false);
            this.bh.setVisible(false);
            this.bh.setEnabled(false);
            this.bj.setVisible(false);
            this.bj.setEnabled(false);
            this.bf.setVisible(false);
            this.bf.setEnabled(false);
            this.bd.setVisible(false);
            this.bd.setEnabled(false);
            if (this.C) {
                this.i.ai();
                this.cx.setVisible(true);
                this.cB.setVisible(false);
                this.bk = 4;
            }
            else {
                this.i.aj();
                this.cx.setVisible(false);
                this.cB.setVisible(true);
                this.bk = 5;
            }
            this.repaint();
        }
        else if (b == this.ak) {
            com.daysofwonder.util.t.a("AddBuddy");
            if (this.cs != null) {
                this.i.a(this.cs.b(), this.cs.a());
            }
        }
        else if (b == this.aj) {
            com.daysofwonder.util.t.a("RemoveBuddy");
            if (this.a != null) {
                com.daysofwonder.util.t.a("Remove Buddy " + this.a);
                this.i.a(this.a);
            }
        }
        else if (b == this.al) {
            com.daysofwonder.util.t.a("AddIgnore");
            if (this.cs != null) {
                com.daysofwonder.util.t.a("AddIgnore: " + this.cs.b());
                this.i.b(this.cs.b(), this.cs.a());
            }
        }
        else if (b == this.am) {
            com.daysofwonder.util.t.a("RemoveIgnore");
            if (this.b != null) {
                com.daysofwonder.util.t.a("Remove Ignore on " + this.b);
                this.i.b(this.b);
            }
        }
        else if (b == this.an) {
            com.daysofwonder.util.t.a("NewPM");
            if (this.cs != null) {
                com.daysofwonder.util.t.a("NewPM to: " + this.cs.a());
                this.bS.f(this.cs.a());
            }
        }
        else if (b == this.ao) {
            this.bk = 2;
            this.i.aj();
            this.cx.setVisible(false);
            this.cB.setVisible(false);
            this.aw.setEnabled(false);
            this.aw.setVisible(false);
            this.aJ.setEnabled(false);
            this.aJ.setVisible(false);
            this.aP.setVisible(false);
            this.aP.setEnabled(false);
            this.aR.setVisible(false);
            this.aR.setEnabled(false);
            this.aT.setVisible(false);
            this.aT.setEnabled(false);
            this.aV.setVisible(false);
            this.aV.setEnabled(false);
            this.aY.setVisible(false);
            this.aY.setEnabled(false);
            this.bb.setVisible(false);
            this.bb.setEnabled(false);
            this.aL.setVisible(false);
            this.aL.setEnabled(false);
            this.bh.setVisible(false);
            this.bh.setEnabled(false);
            this.bj.setVisible(false);
            this.bj.setEnabled(false);
            this.bf.setVisible(false);
            this.bf.setEnabled(false);
            this.bd.setVisible(false);
            this.bd.setEnabled(false);
            this.G = false;
            if (this.a != null) {
                this.i.l(this.a.b);
                this.bE = this.a.e;
            }
            this.repaint();
        }
    }
    
    protected synchronized void a(final boolean b) {
        com.daysofwonder.util.t.a("switchTableList: " + this.B + " && " + b);
        if ((this.B && b) || (!this.B && !b)) {
            com.daysofwonder.util.t.a("switchTableList: " + this.B + " && " + b + " == nothing to do");
        }
        else {
            com.daysofwonder.util.t.a("switchTableList: fActiveTableList " + (b ? " Open " : " Closed "));
            this.B = b;
            this.ck.setVisible(b);
            this.cj.setVisible(!b);
            this.repaint(this.ck.getBounds());
            Toolkit.getDefaultToolkit().sync();
        }
    }
    
    private void n() {
        this.repaint();
    }
    
    protected synchronized void b(final boolean c) {
        com.daysofwonder.util.t.a("switchBuddyTableList: " + this.C + " && " + c);
        if ((this.C && c) || (!this.C && !c)) {
            com.daysofwonder.util.t.a("switchBuddyTableList: " + this.C + " && " + c + " == nothing to do");
        }
        else {
            com.daysofwonder.util.t.a("switchBuddyTableList: fBuddyActiveTableList " + (c ? " Open " : " Closed "));
            this.C = c;
            if (c) {
                this.bk = 4;
                this.i.ai();
                this.cx.setVisible(true);
                this.cB.setVisible(false);
            }
            else {
                this.bk = 5;
                this.i.aj();
                this.cx.setVisible(false);
                this.cB.setVisible(true);
            }
            this.a(this.ap);
            this.a(this.aq);
            this.a(this.aj);
            this.a(this.an);
            this.a(this.ao);
            this.cx.repaint();
            this.cB.repaint();
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.bw && this.bw.n() && this.k() && this.bw.a()) {
            this.b(this.L);
            this.a(this.L, null);
        }
        else if (actionEvent.getSource() == this.aT && this.aT.getSelectedItem() != null) {
            final String selectedItem = (String)this.aR.getSelectedItem();
            this.aR.removeAllItems();
            for (int i = 2; i <= Integer.parseInt((String)this.aT.getSelectedItem()); ++i) {
                this.aR.addItem(Integer.toString(i));
            }
            this.aR.setSelectedItem(selectedItem);
        }
        else if (actionEvent.getSource() == this.aR && this.aR.getSelectedItem() != null) {
            final String selectedItem2 = (String)this.aT.getSelectedItem();
            this.aT.removeAllItems();
            for (int j = Integer.parseInt((String)this.aR.getSelectedItem()); j <= 5; ++j) {
                this.aT.addItem(Integer.toString(j));
            }
            this.aT.setSelectedItem(selectedItem2);
        }
    }
    
    public void i() {
        synchronized (this.d) {
            if (!this.D) {
                return;
            }
        }
        synchronized (this) {
            while (!this.cN) {
                try {
                    this.wait();
                }
                catch (InterruptedException ex) {}
            }
        }
    }
    
    public String a(final String s, final Object[] array) {
        if (s != null) {
            final StringBuffer sb = new StringBuffer();
            for (int i = 0; i < s.length(); ++i) {
                final char char1 = s.charAt(i);
                if (char1 == '%') {
                    if (i < s.length() - 1) {
                        final char char2 = s.charAt(++i);
                        if (Character.isDigit(char2)) {
                            final char c = (char)(char2 - '0');
                            if (array.length > c) {
                                sb.append(array[c].toString());
                            }
                        }
                        else {
                            sb.append(char1).append(char2);
                        }
                    }
                    else {
                        sb.append(char1);
                    }
                }
                else {
                    sb.append(char1);
                }
            }
            return sb.toString();
        }
        return "";
    }
    
    public synchronized void a(final String s, final int n, final int n2) {
        this.a(s, this.r.b("chat." + n), n2);
    }
    
    public synchronized void a(final String s, final String s2, final int n) {
        if (s2 != null) {
            if (n == -1) {
                this.cn.a(s + ": " + s2);
            }
            else {
                this.cn.a("*" + s + "*: " + s2);
            }
            if (this.cn.c() > 75) {
                this.cn.b();
            }
        }
    }
    
    public synchronized void a(final String s) {
        if (s != null) {
            this.cn.a(s);
            if (this.cn.c() > 75) {
                this.cn.b();
            }
        }
    }
    
    public synchronized void g(final Graphics graphics) {
        this.i(graphics);
    }
    
    public synchronized void h(final Graphics graphics) {
        if (this.o == null) {
            this.T.e = this.c(this.T);
            this.n = this.bS.createImage(this.T.a.width, this.T.a.height);
            (this.o = this.n.getGraphics()).setFont(this.v);
        }
        if (this.o != null) {
            this.o.drawImage(this.T.e, 0, 0, null);
            this.o.setColor(Color.red);
        }
        final FontMetrics fontMetrics = this.o.getFontMetrics();
        int n = this.T.a.height - fontMetrics.getMaxDescent() - 1;
        final int n2 = fontMetrics.getMaxDescent() + fontMetrics.getMaxAscent() + 1;
        int cp = 0;
        final com.daysofwonder.util.y e = this.cn.e();
        while (e.a()) {
            final com.daysofwonder.util.y e2 = this.a((String)e.b(), this.T.a.width, fontMetrics).e();
            while (e2.a()) {
                final String s = (String)e2.b();
                if (cp >= this.co) {
                    this.a(this.o, s, 1, n, this.w, this.v);
                    n -= n2;
                }
                ++cp;
            }
            this.o.setColor(Color.black);
        }
        this.cp = cp;
        if (this.m != null) {
            graphics.drawImage(this.n, this.T.a.x, this.T.a.y, this.T.a.x + this.T.a.width, this.T.a.y + this.T.a.height, 0, 0, this.T.a.width, this.T.a.height, null);
            if (this.bS.d()) {
                Toolkit.getDefaultToolkit().sync();
            }
        }
    }
    
    public synchronized void i(final Graphics graphics) {
        this.h(graphics);
    }
    
    public void j() {
        this.repaint(this.T.a.x, this.T.a.y, this.T.a.width, this.T.a.height);
    }
    
    public void a(final int n, final Object[] array) {
        final String b = this.r.b("help." + n);
        if (b != null) {
            this.a(this.a(b, array));
        }
    }
    
    public void b(final int n, final Object[] array) {
        com.daysofwonder.util.t.a("alert: " + n);
        try {
            final Frame l = this.l();
            if (this.r.b("alert.title." + n) != null && this.r.b("alert.text." + n) != null) {
                com.daysofwonder.util.t.a("alert: " + this.r.b("alert.title." + n));
                com.daysofwonder.util.t.a("alert: " + this.r.b("alert.text." + n));
                com.daysofwonder.util.t.a("alert: " + this.a(this.r.b("alert.text." + n), array));
                new g(l, this.r.b("alert.title." + n), true, new String[] { this.a(this.r.b("alert.text." + n), array) }, 1).a();
            }
        }
        catch (Exception ex) {
            com.daysofwonder.util.t.a(ex);
        }
        com.daysofwonder.util.t.a("alert: end of alert");
    }
    
    public void a(final KeyEvent keyEvent) {
        final char keyChar = keyEvent.getKeyChar();
        keyEvent.getKeyCode();
        if (this.bw.n() && this.bw.a() && keyChar == '\n') {
            this.bw.b((FocusEvent)null);
            this.bw.l();
        }
        else if (this.bw.n() && !this.bw.a() && keyChar != '\n') {
            this.bw.a((FocusEvent)null);
            this.bw.b(keyEvent);
            keyEvent.consume();
        }
    }
    
    public void b(final KeyEvent keyEvent) {
        keyEvent.getKeyChar();
        switch (keyEvent.getKeyCode()) {
            case 38: {
                ++this.co;
                if (this.co >= this.cp) {
                    this.co = this.cp - 1;
                }
                this.repaint(this.T.a.x, this.T.a.y, this.T.a.width, this.T.a.height);
                break;
            }
            case 40: {
                --this.co;
                if (this.co < 0) {
                    this.co = 0;
                }
                this.repaint(this.T.a.x, this.T.a.y, this.T.a.width, this.T.a.height);
                break;
            }
        }
    }
    
    private void b(final B b) {
        this.repaint(0, 0, 0, 0);
        try {
            Thread.sleep(200L);
        }
        catch (InterruptedException ex) {}
        this.repaint(0, 0, 0, 0);
    }
    
    private synchronized boolean o() {
        return this.a((Object)null);
    }
    
    private synchronized Integer a(final d d) {
        if (d != null && d.d.a() == 3) {
            final Integer n = new Integer(d.d.b());
            if (this.b(n) != null) {
                return n;
            }
        }
        return null;
    }
    
    private synchronized boolean b(final d d) {
        return this.a(d) != null;
    }
    
    private synchronized boolean a(final Object o) {
        boolean b = false;
        if (!this.B) {
            Integer value = null;
            ar ar = null;
            if (o != null && o instanceof Integer) {
                value = (Integer)o;
                ar = this.b(value);
            }
            else if (this.cl != null) {
                ar = this.cl;
                value = ar.n;
            }
            if (value != null && ar != null) {
                b = (!ar.l && ar.m);
            }
        }
        return b;
    }
    
    private synchronized boolean p() {
        return this.cs != null && this.cs.b() != this.i.aD();
    }
    
    private synchronized boolean q() {
        if (this.i.aL()) {
            return false;
        }
        boolean b = false;
        if (this.cl != null) {
            final ar cl = this.cl;
            if (((cl != null) ? cl.n : null) != null && cl != null) {
                if (!cl.l) {
                    for (int i = 0; i < cl.g.length; ++i) {
                        if (cl.g[i] && cl.c[i] == this.i.aD()) {
                            b = true;
                            break;
                        }
                    }
                }
                else {
                    b = true;
                }
                final int a = a((com.daysofwonder.tt.m)cl.q);
                b = (a >= 0 && b && this.i.aK().a(y.c[a]));
            }
        }
        return b;
    }
    
    private boolean r() {
        return !this.i.aL() && this.i.aK().b();
    }
    
    private boolean s() {
        boolean b = false;
        if (this.aw.isVisible()) {
            b = (this.aw.getText() != null && this.aw.getText().length() != 0);
        }
        return b;
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        if (itemEvent.getItemSelectable() == this.bb && this.r() && this.bk == 1) {
            if (itemEvent.getStateChange() == 1) {
                this.aJ.setEnabled(true);
                this.aJ.setVisible(true);
            }
            else {
                this.aJ.setEnabled(false);
                this.aJ.setVisible(false);
            }
            this.repaint();
        }
        else if (itemEvent.getItemSelectable() == this.aT) {
            final String selectedItem = (String)this.aR.getSelectedItem();
            this.aR.removeAllItems();
            for (int i = 2; i <= Integer.parseInt((String)this.aT.getSelectedItem()); ++i) {
                this.aR.addItem(Integer.toString(i));
            }
            this.aR.setSelectedItem(selectedItem);
        }
        else if (itemEvent.getItemSelectable() == this.aR) {
            final String selectedItem2 = (String)this.aT.getSelectedItem();
            this.aT.removeAllItems();
            for (int j = Integer.parseInt((String)this.aR.getSelectedItem()); j <= 5; ++j) {
                this.aT.addItem(Integer.toString(j));
            }
            this.aT.setSelectedItem(selectedItem2);
        }
        else if (itemEvent.getSource() instanceof CheckboxMenuItem && ((CheckboxMenuItem)itemEvent.getSource()).getParent() == this.bV) {
            this.n();
        }
    }
    
    public void textValueChanged(final TextEvent textEvent) {
        this.repaint(this.L.a.x, this.L.a.y, this.L.a.width, this.L.a.height);
        this.repaint(this.U.a.x, this.U.a.y, this.U.a.width, this.U.a.height);
    }
    
    public K a(final String s, final int n, final FontMetrics fontMetrics) {
        final K k = new K();
        final StringTokenizer stringTokenizer = new StringTokenizer(s, " ");
        final int countTokens = stringTokenizer.countTokens();
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < countTokens; ++i) {
            final String nextToken = stringTokenizer.nextToken();
            if (fontMetrics.stringWidth(sb.toString() + nextToken) > n - 15) {
                k.a(sb.toString());
                sb.setLength(0);
            }
            sb.append(nextToken).append(' ');
        }
        final String string = sb.toString();
        if (fontMetrics.stringWidth(string) >= n - 15) {
            sb.setLength(0);
            for (int length = string.length(), j = 0; j < length; ++j) {
                sb.append(string.charAt(j));
                if (fontMetrics.stringWidth(sb.toString()) >= n - 15) {
                    k.a(sb.toString());
                    sb.setLength(0);
                }
            }
        }
        k.a(sb.toString());
        return k;
    }
    
    public void a(final Graphics graphics, final String s, int n, final int n2, final Font font, final Font font2) {
        int n3 = 0;
        int n4 = 0;
        int n5 = 0;
        int n6 = 0;
        Color color = null;
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); ++i) {
            final char char1 = s.charAt(i);
            if (char1 == '%') {
                if (i < s.length() - 1) {
                    final char char2 = s.charAt(++i);
                    if (char2 == 'b') {
                        if (n6 == 0) {
                            graphics.setFont(font);
                            n6 = 1;
                        }
                        else {
                            n = this.a(graphics, n, n2, sb);
                            graphics.setFont(font2);
                            n6 = 0;
                        }
                    }
                    else if (char2 == 'd') {
                        if (n4 == 0) {
                            color = graphics.getColor();
                            graphics.setColor(Color.blue);
                            n4 = 1;
                        }
                        else {
                            n = this.a(graphics, n, n2, sb);
                            graphics.setColor(color);
                            n4 = 0;
                        }
                    }
                    else if (char2 == 'r') {
                        if (n3 == 0) {
                            color = graphics.getColor();
                            graphics.setColor(Color.red);
                            n3 = 1;
                        }
                        else {
                            n = this.a(graphics, n, n2, sb);
                            graphics.setColor(color);
                            n3 = 0;
                        }
                    }
                    else if (char2 == 'g') {
                        if (n5 == 0) {
                            color = graphics.getColor();
                            graphics.setColor(new Color(72, 160, 67));
                            n5 = 1;
                        }
                        else {
                            n = this.a(graphics, n, n2, sb);
                            graphics.setColor(color);
                            n5 = 0;
                        }
                    }
                    else {
                        sb.append(char1).append(char2);
                    }
                }
                else {
                    sb.append(char1);
                }
            }
            else {
                sb.append(char1);
            }
        }
        this.a(graphics, n, n2, sb);
    }
    
    private int a(final Graphics graphics, int n, final int n2, final StringBuffer sb) {
        final String string = sb.toString();
        graphics.drawString(string, n, n2);
        n += graphics.getFontMetrics().stringWidth(string);
        sb.setLength(0);
        return n;
    }
    
    public boolean k() {
        boolean b = false;
        if (this.bw.n() && this.bw.i().length() > 0) {
            b = true;
        }
        else if (!this.bw.n()) {
            b = true;
        }
        return b;
    }
    
    public static boolean a(final Rectangle rectangle, final Rectangle rectangle2) {
        return a(rectangle.x, rectangle.y, rectangle.width, rectangle.height, rectangle2.x, rectangle2.y, rectangle2.width, rectangle2.height);
    }
    
    public static boolean a(final int n, final int n2, int n3, int n4, final int n5, final int n6, int n7, int n8) {
        if ((n3 | n4 | n7 | n8) < 0) {
            return false;
        }
        if (n5 < n || n6 < n2) {
            return false;
        }
        n3 += n;
        n7 += n5;
        if (n7 <= n5) {
            if (n3 >= n || n7 > n3) {
                return false;
            }
        }
        else if (n3 >= n && n7 > n3) {
            return false;
        }
        n4 += n2;
        n8 += n6;
        if (n8 <= n6) {
            if (n4 >= n2 || n8 > n4) {
                return false;
            }
        }
        else if (n4 >= n2 && n8 > n4) {
            return false;
        }
        return true;
    }
    
    public synchronized void a(final int n) {
        if (this.bS.y()) {
            final long currentTimeMillis = System.currentTimeMillis();
            com.daysofwonder.util.t.a("tm: " + currentTimeMillis);
            if (currentTimeMillis - this.bA > 1500L) {
                this.bA = currentTimeMillis;
                com.daysofwonder.util.t.a("beep");
                this.getToolkit().beep();
                if (this.cc != null) {
                    this.cc.b().b().a();
                    try {
                        for (final k k : this.cc) {
                            if (k.b() == n) {
                                com.daysofwonder.util.t.a("beep from: " + k.a());
                                this.a(9, new Object[] { k.a() });
                                this.repaint(this.T.a.x, this.T.a.y, this.T.a.width, this.T.a.height);
                                break;
                            }
                        }
                    }
                    finally {
                        this.cc.b().b().b();
                    }
                }
            }
        }
    }
    
    private Image c(final B b) {
        if (b == null) {
            return null;
        }
        return this.a(b.a);
    }
    
    private Image a(final Rectangle rectangle) {
        final Image image = this.bS.createImage(rectangle.width, rectangle.height);
        final Graphics graphics = image.getGraphics();
        graphics.drawImage(this.m, 0, 0, rectangle.width, rectangle.height, rectangle.x, rectangle.y, rectangle.x + rectangle.width, rectangle.y + rectangle.height, null);
        graphics.dispose();
        return image;
    }
    
    protected static int b(final int n) {
        for (int i = 0; i < y.cR.length; ++i) {
            if (n <= y.cR[i]) {
                return i;
            }
        }
        return 0;
    }
    
    protected static String c(final int n) {
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i <= n; ++i) {
            sb.append(y.cT);
        }
        return sb.toString();
    }
    
    public Frame l() {
        Container container;
        for (container = this.getParent(); !(container instanceof Frame); container = ((Frame)container).getParent()) {}
        return (Frame)container;
    }
    
    public boolean a(final com.daysofwonder.a.e e) {
        this.cu.b().a().a();
        try {
            if (this.a != null) {
                this.a = e.b(this.a.b);
            }
            GlazedLists.a(this.cu, Collections.list((Enumeration<Object>)e.a()), true, new ay(this));
        }
        finally {
            this.cu.b().a().b();
        }
        return true;
    }
    
    public synchronized boolean b(final com.daysofwonder.a.e e) {
        this.cy.b().a().a();
        try {
            if (this.b != null) {
                this.b = e.b(this.b.b);
            }
            GlazedLists.a(this.cy, Collections.list((Enumeration<Object>)e.a()), true, new ax(this));
        }
        finally {
            this.cy.b().a().b();
        }
        return true;
    }
    
    private boolean c(final d d) {
        boolean b = false;
        if (d != null) {
            b = (d.b != -1);
        }
        return b;
    }
    
    private boolean d(final d d) {
        boolean b = false;
        if (d != null) {
            b = (d.a != -1);
        }
        return b;
    }
    
    private boolean a(final k k) {
        final boolean b = true;
        if (k != null) {
            return this.i.r(k.b());
        }
        return b;
    }
    
    private boolean b(final k k) {
        final boolean b = true;
        if (k != null) {
            return this.i.q(k.b());
        }
        return b;
    }
    
    public void b(final String s) {
        this.a((s != null) ? 701 : 702, new Object[] { s });
        this.getToolkit().beep();
    }
    
    private void a(final EventList list) {
        list.b().a().a();
        try {
            (this.cM = new M(false, this.bX)).a(this.bV);
            this.cK = new FilterList(list, this.cM);
            final SortedList list2 = new SortedList(this.cK, null);
            final r r = new r(this.r, false);
            this.cf = new EventTableModel(list2, r);
            (this.cg = new EventSelectionModel(list2)).addListSelectionListener(new g(this));
            this.cg.setSelectionMode(0);
            (this.ch = new V(this.cf)).setSelectionModel(this.cg);
            this.cj = new JScrollPane(this.ch);
            this.cj.getViewport().setBackground(this.cm);
            this.ch.setColumnSelectionAllowed(false);
            this.ch.setRowSelectionAllowed(true);
            this.ch.setShowHorizontalLines(false);
            this.ch.setShowVerticalLines(true);
            this.ch.setGridColor(Color.gray);
            this.ch.getTableHeader().setBackground(this.bm);
            this.ch.getTableHeader().setForeground(Color.white);
            TableComparatorChooser.a(this.ch, list2, TableComparatorChooser.c);
            this.cj.setBounds(this.bp.x, this.bp.y, this.bp.width, this.bp.height);
            this.ch.setBackground(this.bl);
            for (int i = 0; i < r.a(); ++i) {
                final TableColumn column = this.ch.getColumnModel().getColumn(i);
                column.setPreferredWidth(r.e(i));
                column.setCellRenderer(new Z(r.d(i)));
            }
            this.ch.setFont(this.v);
            this.ch.addMouseListener(this);
            this.cj.setVisible(false);
            this.add(this.cj);
        }
        finally {
            list.b().a().b();
        }
    }
    
    private void b(final EventList list) {
        list.b().a().a();
        try {
            (this.cL = new M(true, this.bX)).a(this.bV);
            this.cJ = new FilterList(list, this.cL);
            final SortedList list2 = new SortedList(this.cJ, null);
            final r r = new r(this.r, true);
            this.cd = new EventTableModel(list2, r);
            (this.ce = new EventSelectionModel(list2)).setSelectionMode(0);
            this.ce.addListSelectionListener(new g(this));
            (this.ci = new V(this.cd)).setSelectionModel(this.ce);
            this.ci.setBorder(BorderFactory.createLineBorder(Color.gray));
            this.ck = new JScrollPane(this.ci);
            this.ck.getViewport().setBackground(this.cm);
            this.ci.setColumnSelectionAllowed(false);
            this.ci.setRowSelectionAllowed(true);
            this.ci.setShowHorizontalLines(false);
            this.ci.setShowVerticalLines(true);
            this.ci.setGridColor(Color.gray);
            this.ci.getTableHeader().setBackground(this.bm);
            this.ci.getTableHeader().setForeground(Color.white);
            TableComparatorChooser.a(this.ci, list2, TableComparatorChooser.c);
            this.ck.setBounds(this.bp.x, this.bp.y, this.bp.width, this.bp.height);
            this.ci.setBackground(this.bl);
            for (int i = 0; i < r.a(); ++i) {
                final TableColumn column = this.ci.getColumnModel().getColumn(i);
                column.setPreferredWidth(r.e(i));
                column.setCellRenderer(new Z(r.d(i)));
            }
            this.ci.setFont(this.v);
            this.ci.addMouseListener(this);
            this.ck.setVisible(true);
            this.add(this.ck);
        }
        finally {
            list.b().a().b();
        }
    }
    
    private void t() {
        this.cu.b().a().a();
        try {
            final SortedList list = new SortedList(this.cu, null);
            final P p = new P(this, this.r);
            this.cv = new EventTableModel(list, p);
            (this.cw = new EventSelectionModel(list)).addListSelectionListener(new aA(this));
            this.cw.setSelectionMode(0);
            (this.bs = new V(this.cv)).setSelectionModel(this.cw);
            this.cx = new JScrollPane(this.bs);
            this.cx.getViewport().setBackground(this.cm);
            this.bs.setColumnSelectionAllowed(false);
            this.bs.setRowSelectionAllowed(true);
            this.bs.setShowHorizontalLines(false);
            this.bs.setShowVerticalLines(true);
            this.bs.setGridColor(Color.gray);
            this.bs.getTableHeader().setBackground(this.bm);
            this.bs.getTableHeader().setForeground(Color.white);
            TableComparatorChooser.a(this.bs, list, TableComparatorChooser.c);
            this.cx.setBounds(this.bt.x, this.bt.y, this.bt.width, this.bt.height);
            this.bs.setBackground(this.bl);
            for (int i = 0; i < p.a(); ++i) {
                final TableColumn column = this.bs.getColumnModel().getColumn(i);
                column.setPreferredWidth(p.e(i));
                column.setCellRenderer(new Z(p.d(i)));
            }
            this.bs.setFont(this.v);
            this.bs.addMouseListener(this);
            this.cx.setVisible(false);
            this.add(this.cx);
        }
        finally {
            this.cu.b().a().b();
        }
    }
    
    private void u() {
        this.cy.b().a().a();
        try {
            final SortedList list = new SortedList(this.cy, null);
            final X x = new X(this.r);
            this.cz = new EventTableModel(list, x);
            (this.cA = new EventSelectionModel(list)).addListSelectionListener(new Q(this));
            this.cA.setSelectionMode(0);
            (this.bu = new V(this.cz)).setSelectionModel(this.cA);
            this.cB = new JScrollPane(this.bu);
            this.cB.getViewport().setBackground(this.cm);
            this.bu.setColumnSelectionAllowed(false);
            this.bu.setRowSelectionAllowed(true);
            this.bu.setShowHorizontalLines(false);
            this.bu.setShowVerticalLines(true);
            this.bu.setGridColor(Color.gray);
            this.bu.getTableHeader().setBackground(this.bm);
            this.bu.getTableHeader().setForeground(Color.white);
            TableComparatorChooser.a(this.bu, list, TableComparatorChooser.c);
            this.cB.setBounds(this.bv.x, this.bv.y, this.bv.width, this.bv.height);
            this.bu.setBackground(this.bl);
            for (int i = 0; i < x.a(); ++i) {
                final TableColumn column = this.bu.getColumnModel().getColumn(i);
                column.setPreferredWidth(x.e(i));
                column.setCellRenderer(new Z(x.d(i)));
            }
            this.bu.setFont(this.v);
            this.bu.addMouseListener(this);
            this.cB.setVisible(false);
            this.add(this.cB);
        }
        finally {
            this.cy.b().a().b();
        }
    }
    
    private void v() {
        this.cc.b().a().a();
        try {
            final at at = new at(this.r);
            this.cq = new EventTableModel(this.cc, at);
            (this.cr = new EventSelectionModel(this.cc)).addListSelectionListener(new m(this));
            (this.bq = new V(this.cq)).setSelectionModel(this.cr);
            this.ct = new JScrollPane(this.bq);
            this.ct.getViewport().setBackground(this.cm);
            this.bq.setColumnSelectionAllowed(false);
            this.bq.setRowSelectionAllowed(true);
            this.bq.setShowHorizontalLines(false);
            this.bq.setShowVerticalLines(true);
            this.bq.setGridColor(Color.gray);
            this.bq.getTableHeader().setBackground(this.bm);
            this.bq.getTableHeader().setForeground(Color.white);
            this.ct.setBounds(this.br.x, this.br.y, this.br.width, this.br.height);
            this.bq.setBackground(this.bl);
            for (int i = 0; i < at.a(); ++i) {
                final TableColumn column = this.bq.getColumnModel().getColumn(i);
                column.setPreferredWidth(at.e(i));
                column.setCellRenderer(new t(at.d(i), this.i, this.r.b("lurking")));
            }
            this.bq.setFont(this.v);
            this.bq.addMouseListener(this);
            this.ct.setVisible(true);
            this.add(this.ct);
        }
        finally {
            this.cc.b().a().b();
        }
    }
    
    protected ar a(final Integer n) {
        return this.a(n, this.ca);
    }
    
    protected ar b(final Integer n) {
        return this.a(n, this.cb);
    }
    
    private ar a(final Integer n, final EventList list) {
        list.b().b().a();
        try {
            for (final ar ar : list) {
                if (ar.n == n) {
                    return ar;
                }
            }
        }
        finally {
            list.b().b().b();
        }
        return null;
    }
    
    protected static Color d(final int n) {
        return y.cS[b(n)];
    }
    
    public void a(final JComponent component) {
        if (this.bS.E()) {
            component.putClientProperty("JComponent.sizeVariant", "small");
        }
    }
    
    static {
        c = new String[] { "us", "eu", "ch", "u1", "u2", "u3" };
        cR = new int[] { 20, 40, 60, 80, 100 };
        cS = new Color[] { new Color(255, 0, 0), new Color(218, 146, 0), new Color(0, 0, 0), new Color(0, 0, 255), new Color(0, 255, 0) };
        cT = new Character('\u2022');
    }
}
