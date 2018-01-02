import javax.swing.JComponent;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Frame;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ByteArrayInputStream;
import java.awt.Window;
import javax.swing.tree.TreePath;
import java.awt.event.ActionEvent;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.swing.SwingUtilities;
import java.awt.Container;
import java.awt.Image;
import javax.swing.JSplitPane;
import javax.swing.ButtonGroup;
import javax.swing.Box;
import java.awt.Dimension;
import javax.swing.plaf.DimensionUIResource;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreeModel;
import javax.swing.JTree;
import java.awt.Component;
import java.awt.BorderLayout;
import java.awt.LayoutManager;
import javax.swing.UIManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.awt.CardLayout;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

// 
// Decompiled by Procyon v0.5.30
// 

public final class rp_au extends JPanel implements ActionListener, rp_ax
{
    public static rp_aJ a;
    public static rp_fx a;
    public rp_aD a;
    private rp_z a;
    private rp_bY b;
    public rp_bY a;
    private CardLayout a;
    private JPanel a;
    private rp_f a;
    private Vector a;
    private int a;
    private rp_fB a;
    public rp_fF a;
    private static rp_au a;
    private List a;
    private String a;
    private boolean a;
    
    private rp_au(final rp_aJ a) {
        this.b = null;
        this.a = null;
        this.a = new rp_f();
        this.a = null;
        this.a = 0;
        this.a = new rp_fB();
        this.a = new rp_fF();
        this.a = new ArrayList();
        this.a = false;
        (rp_au.a = a).a(new rp_fb(a.a(), "BasicMessages.properties"));
        i();
        rp_au.a = new rp_fx(this);
        a.c();
    }
    
    private rp_au(final rp_aJ a, final String s, final String s2) {
        this.b = null;
        this.a = null;
        this.a = new rp_f();
        this.a = null;
        this.a = 0;
        this.a = new rp_fB();
        this.a = new rp_fF();
        this.a = new ArrayList();
        this.a = false;
        rp_C.a(10, "MainPanel [" + s + ";" + s2 + "]");
        rp_au.a = a;
        i();
        if (s.equals("en") && s2.equals("US")) {
            a.a(new rp_fb(a.a(), "BasicMessages.properties"));
        }
        else {
            a.a(new rp_fb(s, s2));
        }
        a.a().a(true);
        if (s.equalsIgnoreCase("en")) {
            a.a().a(0);
        }
        else {
            a.a().a(2);
        }
        rp_au.a = new rp_fx(this);
        a.c();
    }
    
    public static rp_au a() {
        return rp_au.a;
    }
    
    public static synchronized rp_au a(final rp_aJ rp_aJ) {
        if (rp_au.a != null) {
            throw new RuntimeException("Attempt to instantiate panel more than once!");
        }
        return rp_au.a = new rp_au(rp_aJ);
    }
    
    public static synchronized rp_au a(final rp_aJ rp_aJ, final String s, final String s2) {
        if (rp_au.a != null) {
            throw new RuntimeException("Attempt to instantiate panel more than once!");
        }
        return rp_au.a = new rp_au(rp_aJ, s, s2);
    }
    
    public static synchronized void a() {
        rp_au.a = null;
    }
    
    private static void i() {
        UIManager.put("TabbedPane.selected", rp_aJ.i);
        UIManager.put("TabbedPane.contentAreaColor", rp_aJ.i);
        UIManager.put("ComboBox.background", rp_aJ.o);
        UIManager.put("ComboBox.foreground", rp_aJ.r);
        UIManager.put("ComboBox.selectionForeground", rp_aJ.r);
        UIManager.put("Label.background", rp_aJ.c);
        UIManager.put("Label.foreground", rp_aJ.d);
        UIManager.put("CheckBox.background", rp_aJ.c);
        UIManager.put("CheckBox.foreground", rp_aJ.d);
        UIManager.put("RadioButton.background", rp_aJ.c);
        UIManager.put("RadioButton.foreground", rp_aJ.d);
        UIManager.put("Panel.background", rp_aJ.c);
        UIManager.put("Panel.foreground", rp_aJ.d);
    }
    
    public final void a(final boolean b, final String a) {
        this.setLayout(this.a = new CardLayout());
        (this.a = new JPanel()).setForeground(rp_aJ.d);
        this.setForeground(rp_aJ.d);
        this.a.setLayout(new BorderLayout());
        final rp_aJ a3;
        final rp_fK a2 = (a3 = rp_au.a).a();
        final String s = "res/tb_";
        final Image a4;
        if ((a4 = a2.a(s + "room.png", true)) != null) {
            a3.a("room.png", a4);
        }
        final Image a5;
        if ((a5 = a2.a(s + "arrow.png", true)) != null) {
            a3.a("arrow.png", a5);
        }
        final Image a6;
        if ((a6 = a2.a(s + "tape.png", true)) != null) {
            a3.a("tape.png", a6);
        }
        final Image a7;
        if ((a7 = a2.a(s + "text.png", true)) != null) {
            a3.a("text.png", a7);
        }
        final Image a8;
        if ((a8 = a2.a(s + "z_in.png", true)) != null) {
            a3.a("z_in.png", a8);
        }
        final Image a9;
        if ((a9 = a2.a(s + "z_out.png", true)) != null) {
            a3.a("z_out.png", a9);
        }
        final Image a10;
        if ((a10 = a2.a(s + "new.png", true)) != null) {
            a3.a("new.png", a10);
        }
        final Image a11;
        if ((a11 = a2.a(s + "open.png", true)) != null) {
            a3.a("open.png", a11);
        }
        final Image a12;
        if ((a12 = a2.a(s + "save.png", true)) != null) {
            a3.a("save.png", a12);
        }
        final Image a13;
        if ((a13 = a2.a(s + "prn.png", true)) != null) {
            a3.a("prn.png", a13);
        }
        final Image a14;
        if ((a14 = a2.a(s + "mail.png", true)) != null) {
            a3.a("mail.png", a14);
        }
        final rp_aJ a16;
        final Image a15;
        if ((a15 = (a16 = rp_au.a).a().a("res/wait-cursor-transparent.gif", true)) != null) {
            a16.a("wait-cursor-transparent.gif", a15);
        }
        final rp_aJ a18;
        final Image a17;
        if ((a17 = (a18 = rp_au.a).a().a("res/folder_big.png", true)) != null) {
            a18.a("folder_big.png", a17);
        }
        final rp_aJ a20;
        final rp_fK a19 = (a20 = rp_au.a).a();
        final String s2 = "res/tp_";
        final Image a21;
        if ((a21 = a19.a(s2 + "bold.png", true)) != null) {
            a20.a("bold.png", a21);
        }
        final Image a22;
        if ((a22 = a19.a(s2 + "bolds.png", true)) != null) {
            a20.a("bolds.png", a22);
        }
        final Image a23;
        if ((a23 = a19.a(s2 + "ital.png", true)) != null) {
            a20.a("ital.png", a23);
        }
        final Image a24;
        if ((a24 = a19.a(s2 + "itals.png", true)) != null) {
            a20.a("itals.png", a24);
        }
        final Image a25;
        if ((a25 = a19.a(s2 + "under.png", true)) != null) {
            a20.a("under.png", a25);
        }
        final Image a26;
        if ((a26 = a19.a(s2 + "unders.png", true)) != null) {
            a20.a("unders.png", a26);
        }
        final Image a27;
        if ((a27 = a19.a(s2 + "left.png", true)) != null) {
            a20.a("left.png", a27);
        }
        final Image a28;
        if ((a28 = a19.a(s2 + "lefts.png", true)) != null) {
            a20.a("lefts.png", a28);
        }
        final Image a29;
        if ((a29 = a19.a(s2 + "right.png", true)) != null) {
            a20.a("right.png", a29);
        }
        final Image a30;
        if ((a30 = a19.a(s2 + "rights.png", true)) != null) {
            a20.a("rights.png", a30);
        }
        final Image a31;
        if ((a31 = a19.a(s2 + "center.png", true)) != null) {
            a20.a("center.png", a31);
        }
        final Image a32;
        if ((a32 = a19.a(s2 + "centers.png", true)) != null) {
            a20.a("centers.png", a32);
        }
        final rp_fK a34;
        final Image a33;
        if ((a33 = (a34 = rp_au.a.a()).a("res/pl_r_arrow.png", true)) != null) {
            rp_au.a.a("pl_r_arrow.png", a33);
        }
        final Image a35;
        if ((a35 = a34.a("res/pl_l_arrow.png", true)) != null) {
            rp_au.a.a("pl_l_arrow.png", a35);
        }
        rp_au.a.a("tr2_exp.png", a34.a("res/tr2_exp.png", true));
        rp_au.a.a("tr2_col.png", a34.a("res/tr2_col.png", true));
        rp_au.a.a("tr2_cl.png", a34.a("res/tr2_cl.png", true));
        rp_au.a.a("tr2_op.png", a34.a("res/tr2_op.png", true));
        rp_au.a.a("tr2_pl.png", a34.a("res/tr2_pl.png", true));
        rp_au.a.a("folder_up.png", a34.a("res/folder_up.png", true));
        rp_au.a.a("b_new.png", a34.a("res/b_new.png", true));
        rp_au.a.a("b_del.png", a34.a("res/b_del.png", true));
        rp_au.a.a("b_ren.png", a34.a("res/b_ren.png", true));
        rp_au.a.a("b_all.png", a34.a("res/b_all.png", true));
        rp_au.a.a("b_none.png", a34.a("res/b_none.png", true));
        rp_au.a.a("b_move.png", a34.a("res/b_move.png", true));
        rp_C.a(10, "Loading BasicTemplates");
        this.a.a(rp_au.a.a());
        this.a = new rp_aD(rp_au.a);
        this.b = new rp_bY(rp_au.a);
        final rp_bY b2 = this.b;
        final rp_f a36 = this.a;
        final rp_bY rp_bY = b2;
        b2.add(rp_ap.a(rp_bY.a, "OPT_1", "OPT_2", null, "tt_opt", "cmOpt", this, false));
        rp_bY.add(new rp_j(5));
        rp_bY.add(rp_ap.a(rp_bY.a, "", null, "new.png", "tt_new", "cmPlanClose", this, false));
        rp_bY.add(rp_ap.a(rp_bY.a, "", null, "open.png", "tt_ld", "cmLoad", this, false));
        rp_bY.add(rp_ap.a(rp_bY.a, "", null, "save.png", "tt_save", "cmSaveAs", this, false));
        rp_bY.add(new rp_j(5));
        rp_bY.add(rp_ap.a(rp_bY.a, "", null, "prn.png", "tt_pr", "cmPrint", this, false));
        rp_bY.add(rp_ap.a(rp_bY.a, "", null, "mail.png", "tt_em", "cmEmail", this, false));
        if (!rp_bY.a.a().a()) {
            rp_bY.add(new rp_j(5));
            rp_bY.add(rp_ap.a(rp_bY.a, "M_RP", null, null, "tt_ld", "", null, false));
            final rp_bY rp_bY2 = rp_bY;
            final rp_aJ a37 = rp_bY.a;
            final rp_f rp_f = a36;
            final String s3 = "ldt";
            final String s4 = "tt_ld";
            final String actionCommand = s3;
            final rp_f rp_f2 = rp_f;
            final rp_aJ rp_aJ = a37;
            final rp_U a38;
            (a38 = new rp_U(new JTree(rp_f2), null, true, rp_aJ)).setActionCommand(actionCommand);
            if (this != null) {
                a38.addActionListener(this);
            }
            a38.setToolTipText(rp_aJ.a(rp_aJ.a().a(0, s4)));
            rp_bY2.a = a38;
            rp_bY.a.setMaximumSize(new DimensionUIResource(300, 22));
            rp_bY.add(rp_bY.a);
        }
        if (!"no-btns".equalsIgnoreCase(rp_bY.a.c("account_mgmt"))) {
            rp_bY.add(Box.createRigidArea(new Dimension(6, 1)));
            rp_bY.add(new rp_j(5));
            if (!"custom".equalsIgnoreCase(rp_bY.a.c("account_mgmt"))) {
                rp_bY.add(rp_ap.a(rp_bY.a, "ACC_1", "ACC_2", null, "tt_ac", "cmAccount", this, false));
            }
            rp_bY.add(rp_bY.d = rp_ap.a(rp_bY.a, "LOUT", null, null, "tt_lo", "cmLogOut", this, false));
        }
        this.a.add(this.b, "North");
        final JPanel panel = new JPanel(new BorderLayout());
        this.a = new rp_bY(rp_au.a);
        final rp_bY a39 = this.a;
        final rp_fx a40 = rp_au.a;
        final rp_bY rp_bY3 = a39;
        a39.add(rp_ap.a(rp_bY3.a, "", null, "room.png", "tt_rs", "cmRoomW", a40, false));
        rp_bY3.add(new rp_j(5));
        final ButtonGroup buttonGroup = new ButtonGroup();
        (rp_bY3.a = rp_ap.a(rp_bY3.a, "M_S", null, "arrow.png", "tt_arrow", "cmArrow", a40, true)).setSelected(true);
        rp_bY3.a.addActionListener(rp_bY3);
        buttonGroup.add(rp_bY3.a);
        rp_bY3.add(rp_bY3.a);
        (rp_bY3.b = rp_ap.a(rp_bY3.a, "TAPE", null, "tape.png", "tt_tape", "cmTape", a40, true)).setSelected(false);
        rp_bY3.b.addActionListener(rp_bY3);
        rp_bY3.b.setBorderPainted(false);
        buttonGroup.add(rp_bY3.b);
        rp_bY3.add(rp_bY3.b);
        (rp_bY3.c = rp_ap.a(rp_bY3.a, "TEXT", null, "text.png", "tt_text", "cmText", a40, true)).setSelected(false);
        rp_bY3.c.addActionListener(rp_bY3);
        rp_bY3.c.setBorderPainted(false);
        buttonGroup.add(rp_bY3.c);
        rp_bY3.add(rp_bY3.c);
        rp_bY3.add(new rp_j(5));
        rp_bY3.add(rp_ap.a(rp_bY3.a, "+", null, "z_in.png", "tt_zi", "cmZoomIn", a40, false));
        rp_bY3.add(rp_ap.a(rp_bY3.a, "-", null, "z_out.png", "tt_zo", "cmZoomOut", a40, false));
        rp_bY3.add(rp_ap.a(rp_bY3.a, "M_F", "M_W", null, "tt_zf", "cmZoomRoom", a40, false));
        rp_bY3.add(new rp_j(5));
        rp_bY3.add(rp_ap.a(rp_bY3.a, "M_C", null, null, "tt_dup", "cmDup", a40, false));
        rp_bY3.add(new rp_j(5));
        rp_bY3.add(rp_ap.a(rp_bY3.a, "M_U", null, null, "tt_undo", "cmUndo", a40, false));
        rp_bY3.add(new rp_j(5));
        rp_bY3.add(rp_ap.a(rp_bY3.a, "M_R", null, null, "tt_redo", "cmRedo", a40, false));
        rp_bY3.add(new rp_j(5));
        rp_bY3.add(rp_ap.a(rp_bY3.a, "M_D", null, null, "tt_del", "cmDel", a40, false));
        rp_bY3.add(new rp_j(5));
        rp_bY3.add(rp_ap.a(rp_bY3.a, "M_RM", "M_A", null, "tt_delall", "cmStartOver", a40, false));
        panel.add(this.a, "North");
        (this.a = new rp_z(rp_au.a)).setMinimumSize(new Dimension(100, 100));
        panel.add(this.a, "Center");
        this.a.a();
        this.a.invalidate();
        this.a.validate();
        final JSplitPane splitPane;
        (splitPane = new JSplitPane(1, this.a, panel)).setOneTouchExpandable(true);
        splitPane.setContinuousLayout(true);
        splitPane.setDividerSize(7);
        this.a.add(splitPane, "Center");
        this.a.invalidate();
        this.a.validate();
        this.add(this.a, "MAIN");
        this.add(new JPanel(), "FKAE");
        if (b) {
            this.a("cmTmpOpen");
            this.a = "";
            if (a != null) {
                this.a = a;
            }
            return;
        }
        final String c;
        if ((c = rp_au.a.c("start_with_name")) != null && c.length() > 0) {
            this.a("cmIntOpen");
            return;
        }
        if (rp_au.a.a("sw_rw") > 0) {
            this.a("cmRWWL");
        }
    }
    
    private void j() {
        if (!rp_au.a.a().a((String)null, true)) {
            this.a.a();
            return;
        }
        new rp_bT(this).c();
    }
    
    private Container a() {
        if (this.a.size() > 0) {
            return this.a.get(this.a.size() - 1);
        }
        return this.a;
    }
    
    public final void a(final rp_bW rp_bW) {
        this.b();
        this.a().add(rp_bW);
    }
    
    public final void b() {
        final rp_aO rp_aO;
        (rp_aO = new rp_aO(this.a(), this.a.getWidth(), this.a.getHeight())).setLayout(null);
        this.a.add(rp_aO);
        this.add(rp_aO, "FAKEPANEL." + this.a.size());
        this.a.last(this);
    }
    
    public final void c() {
        if (this.a.size() > 0) {
            final rp_aO rp_aO = this.a.get(this.a.size() - 1);
            this.a.remove(this.a.size() - 1);
            this.remove(rp_aO);
            this.a.removeLayoutComponent(rp_aO);
            String string;
            if (this.a.size() > 0) {
                string = "FAKEPANEL." + this.a.size();
            }
            else {
                string = "MAIN";
            }
            this.a.show(this, string);
        }
    }
    
    public final void d() {
        this.c();
    }
    
    public final void e() {
        for (int i = 0; i < this.a.size(); ++i) {
            ((rp_aO)this.a.get(i)).a();
        }
    }
    
    public final void f() {
        final rp_bZ rp_bZ = (rp_bZ)rp_au.a.a();
        if (rp_au.a.a.a(rp_bZ)) {
            rp_bZ.a(true);
            this.b.a(true);
            this.j();
            this.a.c();
            this.a.a();
            return;
        }
        this.k();
    }
    
    private void k() {
        ((rp_bZ)rp_au.a.a()).a(false);
        final rp_E a;
        (a = rp_au.a.a).a = "";
        a.a.removeAllElements();
        this.b.a(false);
        this.j();
        this.a.d();
        final rp_fZ a2;
        if (this.a.a() != null && (((a2 = this.a.a()).a != null) ? a2.a.b() : 0) == 0) {
            this.a.b();
        }
    }
    
    public final void g() {
        this.invalidate();
        this.validateTree();
        rp_au.a.d();
        rp_dd.a(rp_au.a);
    }
    
    public final boolean a() {
        rp_C.a(10, "mainPanel.onExit()");
        if (this.a(true)) {
            return false;
        }
        final rp_fx a;
        if (!rp_au.a.a.a && (a = rp_au.a).b != null) {
            rp_au.a.a().a(rp_aw.h, new rp_v[] { a.b }, null);
        }
        return true;
    }
    
    public final void a(final Object o) {
        if (!this.isVisible() || this.getSize() == null || this.getSize().getWidth() == 0.0) {
            new rp_eQ(this, 100, o).start();
            return;
        }
        final rp_bU rp_bU = new rp_bU(this, o);
        try {
            SwingUtilities.invokeAndWait(rp_bU);
        }
        catch (Exception ex) {
            throw new RuntimeException("Error firing event due to: " + ex.getMessage(), ex);
        }
    }
    
    private boolean b() {
        final rp_gz rp_gz;
        (rp_gz = new rp_gz(rp_au.a.a().a(), rp_au.a.a(), (this = this).a, rp_au.a)).setVisible(true);
        return rp_gz.a;
    }
    
    public static boolean a(final rp_v rp_v) {
        final rp_v[] array = { rp_v };
        try {
            final rp_bg rp_bg;
            if ((rp_bg = (rp_bg)rp_au.a.a().a(rp_aw.a, array, rp_au.a.a(null, rp_fl.b))).a()) {
                return true;
            }
            rp_bg.a(rp_au.a.a());
            return false;
        }
        catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    public static boolean a(final String s) {
        ByteArrayOutputStream a;
        try {
            a = rp_au.a.a(null, rp_fl.b).a();
        }
        catch (IOException ex) {
            System.out.println("PlaneEP.save: " + ex);
            return false;
        }
        final rp_bZ rp_bZ = (rp_bZ)rp_au.a.a();
        return ((rp_bg)rp_bZ.a(a, "plan-save-temp", "&todo=" + rp_C.e(s) + ((rp_bZ instanceof rp_dh) ? "&jnlp=true" : ""), "text/xml", true)).a();
    }
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getActionCommand().equals("ldt")) {
            final Object selectedItem;
            if (actionEvent.getSource() instanceof rp_U && (selectedItem = ((rp_U)actionEvent.getSource()).getSelectedItem()) != null && selectedItem instanceof TreePath) {
                final rp_v rp_v = (rp_v)((TreePath)selectedItem).getLastPathComponent();
                if (this.a(true)) {
                    this.h();
                    return;
                }
                rp_au.a.a(rp_v);
            }
            rp_au.a.requestFocus();
            return;
        }
        this.a(actionEvent.getActionCommand());
    }
    
    public final void a(final String s) {
        if (!rp_au.a.a().b(s, false)) {
            return;
        }
        if (s.equals("cmOpt") || s.equals("cmPlanClose") || s.equals("cmSaveImage") || s.equals("cmPrint") || s.equals("cmEmail") || s.equals("cmRequestinfo") || s.equals("cmLoad") || s.equals("cmSaveAs") || s.equals("cmTmpOpen") || s.equals("cmAccount") || s.equals("cmLogOut") || s.equals("cmIntOpen") || s.equals("cmRWWL")) {
            new rp_eQ(this, 10, s).start();
            return;
        }
        rp_au.a.a(s);
    }
    
    private boolean c() {
        if (rp_au.a.a == null) {
            if (rp_bd.c(rp_au.a.a().a(), rp_au.a.a(), "wrn", "fs", false) == 2) {
                return false;
            }
            this.b();
            if (rp_au.a.a == null) {
                return false;
            }
        }
        return true;
    }
    
    public final rp_f a() {
        return this.a;
    }
    
    public final void h() {
        if (this.b.a != null) {
            final rp_v a;
            if ((a = rp_au.a.a) == null || a.getParent() == null) {
                this.b.a.setSelectedItem(null);
                return;
            }
            this.b.a.setSelectedItem(new TreePath(a.getPath()));
        }
    }
    
    private boolean a(final boolean b) {
        if (rp_au.a.a.a) {
            final rp_fK a;
            final int b2;
            if ((b2 = rp_bd.b((a = rp_au.a.a()).a(), rp_au.a.a(), "wrn", "mp1", b)) == 0) {
                return true;
            }
            if (b2 == 1) {
                if (!a.a("cmSaveAs", false)) {
                    return true;
                }
                this = this;
                final rp_v a2;
                return !(((a2 = rp_au.a.a) == null || rp_au.a.a().a()) ? this.b() : a(a2));
            }
        }
        return false;
    }
    
    public final void b(String c) {
        rp_C.a(10, "Fired openDlg with cmd: " + c + " isopen: " + this.a);
        if (this.a) {
            return;
        }
        final rp_fK a2;
        final Frame a = (a2 = rp_au.a.a()).a();
        try {
            this.a.setEnabled(false);
            this.a.setEnabled(false);
            this.a = true;
            if (c.equals("cmOpt")) {
                new rp_cy().setVisible(true);
                return;
            }
            if (c.equals("cmRWWL")) {
                rp_au.a.a(a, true);
                if (rp_au.a.b()) {
                    this.a((Vector)null);
                }
                return;
            }
            if (c.equals("cmRoomW")) {
                rp_au.a.a(a, false);
                return;
            }
            if (c.equals("cmPlanClose")) {
                if (this.a(false)) {
                    return;
                }
                final rp_fx a3;
                (a3 = rp_au.a).a = null;
                a3.a = null;
                ((ArrayList)(a3.a = null)).clear();
                a3.a = 0;
                a3.a((rp_v)null);
                a3.a.a();
                rp_au.a.repaint();
                rp_au.a.requestFocus();
            }
            else if (c.equals("cmSaveImage")) {
                if (!a2.a("cmSaveImage", false)) {
                    return;
                }
                new rp_eT(rp_au.a, "PrnOptSI").setVisible(true);
            }
            else {
                if (c.equals("cmPrint")) {
                    new rp_eT(rp_au.a, "PrnOpt").setVisible(true);
                    return;
                }
                if (c.equals("cmEmail")) {
                    if (!a2.a("cmEmail", false)) {
                        return;
                    }
                    if (!this.c()) {
                        return;
                    }
                    new rp_gi(a, rp_au.a, 0).setVisible(true);
                }
                else if (c.equals("cmRequestinfo")) {
                    if (!a2.a("cmRequestinfo", false)) {
                        return;
                    }
                    if (!this.c()) {
                        return;
                    }
                    new rp_gi(a, rp_au.a, 1).setVisible(true);
                }
                else {
                    if (c.equals("cmAccount")) {
                        final rp_bZ rp_bZ = (rp_bZ)a2;
                        this.a(rp_bZ, rp_bZ.a((String)null, true), "cmNothing");
                    }
                    if (c.equals("cmLogOut")) {
                        final rp_bZ rp_bZ2;
                        if ((rp_bZ2 = (rp_bZ)a2).a((String)null, true)) {
                            if (rp_bZ2.b()) {
                                this.k();
                            }
                        }
                        else {
                            this.a(rp_bZ2, false, "cmNothing");
                        }
                    }
                    if (c.equals("cmLoad")) {
                        final rp_es rp_es;
                        (rp_es = new rp_es(a2.a(), rp_au.a, this.a, rp_au.a.a)).setVisible(true);
                        this.a.reload();
                        final rp_v a4;
                        if ((a4 = rp_es.a()) != null) {
                            if (this.a(true)) {
                                return;
                            }
                            final byte[] a5;
                            if ((a5 = rp_es.a()) != null) {
                                try {
                                    final rp_eA rp_eA = new rp_eA();
                                    rp_eA.a(new BufferedReader(new InputStreamReader(new ByteArrayInputStream(a5))));
                                    final boolean a6 = rp_au.a.a(rp_eA);
                                    rp_C.a(10, "Plan load from desktop data ok? " + a6);
                                    if (a6) {
                                        rp_au.a.a.a(true);
                                        rp_au.a.a(a4);
                                    }
                                    return;
                                }
                                catch (Throwable t2) {
                                    final Throwable t = t2;
                                    t2.printStackTrace();
                                    throw new RuntimeException("Error loading due to: " + t.getMessage(), t);
                                }
                            }
                            rp_au.a.a(a4);
                        }
                        return;
                    }
                    if (c.equals("cmSaveAs")) {
                        if (!a2.a("cmSaveAs", false)) {
                            return;
                        }
                        this.b();
                    }
                    else {
                        if (c.equals("cmIntOpen")) {
                            final String c2 = rp_au.a.c("start_with_name");
                            int int1 = -1;
                            if ((c = rp_au.a.c("start_with_folder_id")) != null && c.length() > 0) {
                                try {
                                    int1 = Integer.parseInt(c);
                                }
                                catch (NumberFormatException ex) {}
                            }
                            final rp_v a7;
                            if ((a7 = this.a.a().a(int1)) != null) {
                                a7.a(a2);
                                final rp_v a8;
                                if ((a8 = a7.a(c2)) != null) {
                                    rp_au.a.a(a8);
                                }
                            }
                            rp_au.a.a("start_with_name", (String)null);
                            rp_au.a.a("start_with_folder_id", (String)null);
                            return;
                        }
                        if (c.equals("cmTmpOpen")) {
                            rp_au.a.b();
                            this.a(this.a);
                        }
                    }
                }
            }
        }
        finally {
            ((JComponent)(this.a = false)).setEnabled(true);
            this.a.setEnabled(true);
        }
    }
    
    public final void a(final rp_bZ rp_bZ, final boolean b, final String s) {
        if ("custom".equalsIgnoreCase(rp_au.a.c("account_mgmt"))) {
            if (b) {
                return;
            }
            final StringBuffer sb = new StringBuffer();
            if (a(s)) {
                rp_C.a(4, "Hidden save OK. Now redirecting to login page (" + (Object)sb + ").");
                try {
                    final String[] split;
                    if ((split = sb.toString().split("\n")).length > 1) {
                        rp_bZ.a("temp-plan", split[1]);
                    }
                    if (split.length > 0) {
                        rp_bZ.a(new URL(split[0]), "_self");
                        if (rp_bZ instanceof rp_dh) {
                            System.exit(0);
                        }
                    }
                }
                catch (MalformedURLException ex) {
                    System.out.println("AppletToolkit.checkUserLogin: ");
                    System.out.println(ex);
                }
            }
        }
        else {
            final rp_bl rp_bl = new rp_bl(rp_bZ, b);
            if (rp_bZ.d) {
                final rp_bl rp_bl2 = rp_bl;
                rp_bl2.a |= 0x31;
            }
            rp_bl.a();
            rp_bl.setVisible(true);
        }
        if (rp_bZ.a((String)null, true)) {
            this.f();
            return;
        }
        this.k();
    }
    
    public final void a(final Vector a) {
        if (a != null) {
            this.a = a;
        }
        if (a == null && this.a == 0) {
            rp_bd.a(rp_au.a.a().a(), rp_au.a.a(), "inf", "mp3");
            this.a = 1;
        }
        if (this.a != null && this.a == 1) {
            rp_au.a.a(this.a);
            this.a = 2;
            this.a = null;
        }
    }
    
    public static String a(final int n, final String s) {
        return rp_au.a.a().a(0, s);
    }
    
    public static String a(final String s) {
        return rp_au.a.a().a(0, s);
    }
    
    public static String b(final String s) {
        return rp_au.a.a().a(s);
    }
    
    public final rp_fB a() {
        return this.a;
    }
    
    static {
        rp_au.a = null;
        rp_au.a = null;
    }
}
