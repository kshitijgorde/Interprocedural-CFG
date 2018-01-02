import java.util.Hashtable;
import java.util.StringTokenizer;
import java.io.BufferedInputStream;
import java.net.URL;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.awt.event.WindowListener;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Insets;
import java.awt.Component;
import java.awt.event.ItemListener;
import java.awt.event.MouseListener;
import java.awt.event.ActionListener;
import java.awt.Frame;
import java.awt.LayoutManager;
import au.com.rocketdog.project.awc.applet.images.ImageRes;
import au.com.rocketdog.project.awc.applet.Main;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Vector;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JTextArea;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JFrame;

// 
// Decompiled by Procyon v0.5.30
// 

public class bm extends JFrame
{
    public boolean a;
    public boolean b;
    private JTabbedPane c;
    private t d;
    private JPanel e;
    private JPanel f;
    private JPanel g;
    private JPanel h;
    private JPanel i;
    private JPanel j;
    private a2 k;
    private JComboBox l;
    private JComboBox m;
    private JComboBox n;
    private JComboBox o;
    private bn p;
    private JLabel q;
    private JLabel r;
    private JLabel s;
    private JLabel t;
    private JComboBox u;
    private JLabel v;
    private JCheckBox w;
    private al x;
    public bl y;
    public JLabel z;
    private JLabel aa;
    private JTextArea ab;
    private JLabel ac;
    private JCheckBox ad;
    public JCheckBox ae;
    private JComboBox af;
    private BorderLayout ag;
    private BorderLayout ah;
    private JLabel ai;
    private JComboBox aj;
    private JButton ak;
    private JLabel al;
    private JTextField am;
    private JList an;
    private JButton ao;
    private JLabel ap;
    private JTextField aq;
    private JList ar;
    private JButton as;
    private JButton at;
    private JButton au;
    private JLabel av;
    private bo aw;
    private JCheckBox ax;
    private JLabel ay;
    private JComboBox az;
    private static Vector a0;
    private GridBagLayout a1;
    private GridBagConstraints a2;
    
    public bm(final a2 k) {
        super(Main.p.a("settings.heading"));
        this.a = true;
        this.b = false;
        this.c = new JTabbedPane();
        this.e = new JPanel();
        this.f = new JPanel();
        this.g = new JPanel();
        this.h = new JPanel();
        this.i = new JPanel();
        this.j = new JPanel();
        this.l = new JComboBox();
        this.m = new JComboBox();
        this.n = new JComboBox();
        this.o = new JComboBox();
        this.p = new bn();
        this.q = new JLabel();
        this.r = new JLabel();
        this.s = new JLabel();
        this.t = new JLabel();
        this.u = new JComboBox();
        this.v = new JLabel();
        this.w = new JCheckBox();
        this.z = new JLabel();
        this.aa = new JLabel();
        this.ab = new JTextArea();
        this.ac = new JLabel();
        this.ad = new JCheckBox();
        this.ae = new JCheckBox();
        this.af = new JComboBox();
        this.ag = new BorderLayout();
        this.ah = new BorderLayout();
        this.ai = new JLabel();
        this.aj = new JComboBox();
        this.ak = new JButton();
        this.al = new JLabel();
        this.am = new JTextField();
        this.an = new JList();
        this.ao = new JButton();
        this.ap = new JLabel();
        this.aq = new JTextField();
        this.ar = new JList();
        this.as = new JButton();
        this.at = new JButton();
        this.au = new JButton();
        this.av = new JLabel();
        this.aw = new bo();
        this.ax = new JCheckBox();
        this.ay = new JLabel();
        this.az = new JComboBox();
        this.a1 = new GridBagLayout();
        this.a2 = new GridBagConstraints();
        this.k = k;
        this.setIconImage(ImageRes.a4);
        this.d = t.a();
        this.getContentPane().setLayout(this.ag);
        this.x = new al(this, Main.p.a("dialog.savesettings"), 300, 100);
        this.y = new bl(this, Main.p.a("dialog.langrestart1"), Main.p.a("dialog.langrestart2"));
        this.x.a.addActionListener(new bq(this));
        this.x.b.addActionListener(new br(this));
        this.au.setText(Main.p.a("settings.cam.setpath"));
        this.av.setText(Main.p.a("settings.cam.conntype"));
        this.au.addMouseListener(new bs(this));
        this.ai.setText(Main.p.a("settings.cam.dispcams"));
        this.aj.addItem(this.b(this.d.a("cam.disp")));
        this.aj.addItem(Main.p.a("settings.cam.local"));
        this.aj.addItem(Main.p.a("settings.cam.all"));
        this.aj.addItemListener(new bt(this));
        this.f.setLayout(this.a1);
        this.a2.anchor = 17;
        this.a2.gridwidth = -1;
        this.f.add(this.aa, this.a2);
        this.a2.gridwidth = 0;
        this.f.add(this.z, this.a2);
        this.a2.insets = new Insets(5, 5, 50, 5);
        this.a2.gridwidth = 0;
        this.f.add(this.au, this.a2);
        this.a2.gridwidth = -1;
        this.f.add(this.ac, this.a2);
        this.a2.gridwidth = 0;
        this.f.add(this.af, this.a2);
        this.a2.gridwidth = -1;
        this.f.add(this.ai, this.a2);
        this.a2.gridwidth = 0;
        this.f.add(this.aj, this.a2);
        this.a2.gridwidth = 0;
        this.f.add(this.ad, this.a2);
        this.ax.addItemListener(new bt(this));
        this.ax.setBounds(new Rectangle(5, 110, 181, 21));
        this.ax.setText(Main.p.a("settings.chat.showjoinpart"));
        this.aa.setText(Main.p.a("settings.cam.capturepath"));
        this.ac.setText(Main.p.a("settings.cam.defaultcamsize"));
        this.ad.setText(Main.p.a("settings.cam.closeondisc"));
        this.g.setLayout(this.ah);
        this.h.setLayout(null);
        this.ak.setText(Main.p.a("settings.ignorelist.removeignore"));
        this.an.setBounds(new Rectangle(12, 35, 151, 200));
        this.ak.setBounds(new Rectangle(12, 240, 151, 21));
        this.al.setBounds(new Rectangle(12, 8, 138, 21));
        this.am.setBounds(new Rectangle(168, 38, 220, 21));
        this.ao.setBounds(new Rectangle(168, 62, 220, 21));
        this.ak.addActionListener(new bu(this));
        this.al.setText(Main.p.a("settings.ignorelist.ignoredusers"));
        this.ao.setText(Main.p.a("settings.ignorelist.ignoreuser"));
        this.ao.addActionListener(new bv(this));
        this.i.setLayout(null);
        this.at.setText(Main.p.a("settings.allowpmlist.removeallow"));
        this.ar.setBounds(new Rectangle(12, 35, 151, 200));
        this.at.setBounds(new Rectangle(12, 240, 151, 21));
        this.ap.setBounds(new Rectangle(12, 8, 138, 21));
        this.aq.setBounds(new Rectangle(168, 38, 220, 21));
        this.as.setBounds(new Rectangle(168, 62, 220, 21));
        this.at.addActionListener(new bw(this));
        this.ap.setText(Main.p.a("settings.allowpmlist.allowedpms"));
        this.as.setText(Main.p.a("settings.allowpmlist.allowpmuser"));
        this.as.addActionListener(new bx(this));
        this.g.add(this.ab, "Center");
        this.l.addItem(this.d.a("chat.font.name"));
        final String[] availableFontFamilyNames = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        for (int i = 0; i < availableFontFamilyNames.length; ++i) {
            this.l.addItem(availableFontFamilyNames[i]);
        }
        this.n.addItem(this.d.a("chat.font.color"));
        this.n.addItem(Main.p.a("settings.chat.color.black"));
        this.n.addItem(Main.p.a("settings.chat.color.blue"));
        this.n.addItem(Main.p.a("settings.chat.color.cyan"));
        this.n.addItem(Main.p.a("settings.chat.color.darkgray"));
        this.n.addItem(Main.p.a("settings.chat.color.gray"));
        this.n.addItem(Main.p.a("settings.chat.color.green"));
        this.n.addItem(Main.p.a("settings.chat.color.lightgray"));
        this.n.addItem(Main.p.a("settings.chat.color.magenta"));
        this.n.addItem(Main.p.a("settings.chat.color.orange"));
        this.n.addItem(Main.p.a("settings.chat.color.pink"));
        this.n.addItem(Main.p.a("settings.chat.color.red"));
        this.n.addItem(Main.p.a("settings.chat.color.white"));
        this.n.addItem(Main.p.a("settings.chat.color.yellow"));
        this.o.addItem(this.d.a("chat.font.backgroundcolor"));
        this.o.addItem(Main.p.a("settings.chat.color.black"));
        this.o.addItem(Main.p.a("settings.chat.color.blue"));
        this.o.addItem(Main.p.a("settings.chat.color.cyan"));
        this.o.addItem(Main.p.a("settings.chat.color.darkgray"));
        this.o.addItem(Main.p.a("settings.chat.color.gray"));
        this.o.addItem(Main.p.a("settings.chat.color.green"));
        this.o.addItem(Main.p.a("settings.chat.color.lightgray"));
        this.o.addItem(Main.p.a("settings.chat.color.magenta"));
        this.o.addItem(Main.p.a("settings.chat.color.orange"));
        this.o.addItem(Main.p.a("settings.chat.color.pink"));
        this.o.addItem(Main.p.a("settings.chat.color.red"));
        this.o.addItem(Main.p.a("settings.chat.color.white"));
        this.o.addItem(Main.p.a("settings.chat.color.yellow"));
        if (this.d.a("cam.closewindow").equalsIgnoreCase("true")) {
            this.ad.setSelected(true);
        }
        else {
            this.ad.setSelected(false);
        }
        if (this.d.a("chat.allowpms").equalsIgnoreCase("true")) {
            this.w.setSelected(true);
        }
        else {
            this.w.setSelected(false);
        }
        if (this.d.a("chat.showjoins").equalsIgnoreCase("true")) {
            this.ax.setSelected(true);
        }
        else {
            this.ax.setSelected(false);
        }
        if (this.d.a("chat.sortcount").equalsIgnoreCase("true")) {
            this.ae.setSelected(true);
        }
        else {
            this.ae.setSelected(false);
        }
        this.z.setText(this.d.a("cam.capturepath"));
        this.u.addItem(this.d.a("chat.frame.size"));
        this.u.addItem("320x240");
        this.u.addItem("640x480");
        this.u.addItem("800x600");
        this.af.addItem(this.d.a("cam.frame.size"));
        this.af.addItem("160x120");
        this.af.addItem("224x168");
        this.af.addItem("240x180");
        this.af.addItem("320x240");
        this.af.addItem("400x300");
        this.af.addItem("480x360");
        this.af.addItem("560x420");
        this.af.addItem("640x480");
        this.m.addItem(this.d.a("chat.font.size"));
        this.m.addItem("8");
        this.m.addItem("9");
        this.m.addItem("10");
        this.m.addItem("11");
        for (int j = 12; j < 29; j += 2) {
            this.m.addItem(Integer.toString(j));
        }
        this.m.addItem("36");
        this.m.addItem("48");
        this.m.addItem("72");
        this.t.setText(Main.p.a("settings.chat.fontsize"));
        this.q.setText(Main.p.a("settings.chat.bgcolour"));
        this.r.setText(Main.p.a("settings.chat.fonttype"));
        this.s.setText(Main.p.a("settings.chat.fontcolour"));
        this.v.setText(Main.p.a("settings.chat.opensize"));
        this.w.setText(Main.p.a("settings.chat.allowpm"));
        this.ae.setText(Main.p.a("settings.chat.sortroomsbypartic"));
        this.r.setBounds(new Rectangle(6, 14, 100, 21));
        this.l.setBounds(new Rectangle(82, 12, 125, 21));
        this.ad.setBounds(new Rectangle(12, 110, 244, 21));
        this.o.setBounds(new Rectangle(319, 38, 125, 21));
        this.q.setBounds(new Rectangle(215, 41, 100, 21));
        this.s.setBounds(new Rectangle(6, 41, 100, 21));
        this.n.setBounds(new Rectangle(82, 38, 125, 21));
        this.t.setBounds(new Rectangle(215, 14, 100, 21));
        this.m.setBounds(new Rectangle(319, 15, 125, 21));
        this.ae.setBounds(new Rectangle(6, 137, 250, 21));
        this.u.setBounds(new Rectangle(227, 169, 129, 21));
        this.v.setBounds(new Rectangle(131, 169, 90, 21));
        this.w.setBounds(new Rectangle(6, 169, 115, 21));
        this.p.setBounds(new Rectangle(6, 201, 424, 121));
        this.ay.setText(Main.p.a("settings.language.currentlang") + ":");
        bm.a0 = this.m();
        this.az.addItem(this.a(this.d.a("lang.def")));
        for (int l = 0; l < bm.a0.size(); ++l) {
            this.az.addItem(((String[])bm.a0.elementAt(l))[0]);
        }
        this.ay.setBounds(new Rectangle(180, 20, 120, 21));
        this.az.setBounds(new Rectangle(310, 20, 100, 21));
        this.az.setName("langchange");
        this.az.addItemListener(new bt(this));
        this.e.add(this.l, null);
        this.e.add(this.t, null);
        this.e.add(this.n, null);
        this.e.add(this.q, null);
        this.e.add(this.s, null);
        this.e.add(this.r, null);
        this.e.add(this.o, null);
        this.e.add(this.m, null);
        this.e.add(this.p);
        this.e.add(this.v, null);
        this.e.add(this.w, null);
        this.e.add(this.u, null);
        this.e.add(this.ax, null);
        this.e.add(this.ae, null);
        this.e.setLayout(null);
        this.h.add(this.an, null);
        this.h.add(this.al, null);
        this.h.add(this.am, null);
        this.h.add(this.ak, null);
        this.h.add(this.ao, null);
        this.i.add(this.ar, null);
        this.i.add(this.ap, null);
        this.i.add(this.aq, null);
        this.i.add(this.at, null);
        this.i.add(this.as, null);
        this.j.setLayout(null);
        this.j.add(this.ay, null);
        this.j.add(this.az);
        this.addWindowListener(new by(this));
        this.m.addItemListener(new bz(this));
        this.l.addItemListener(new b0(this));
        this.n.addItemListener(new b1(this));
        this.o.addItemListener(new b2(this));
        this.u.addItemListener(new b3(this));
        this.af.addItemListener(new bt(this));
        this.ad.addItemListener(new bt(this));
        this.w.addItemListener(new bt(this));
        this.ae.addItemListener(new bt(this));
        this.ax.addItemListener(new bt(this));
        this.ae.addItemListener(new b4(this));
        this.f();
        this.g();
        this.d();
        this.h();
        this.a = true;
        this.c.add(Main.p.a("settings.gender.genderheading"), k);
        this.c.add(Main.p.a("settings.chat.chatheading"), this.e);
        this.c.add(Main.p.a("settings.shortcut.shortcutheading"), this.aw);
        this.c.add(Main.p.a("settings.cam.camheading"), this.f);
        this.c.add(Main.p.a("settings.log.logheading"), this.g);
        this.c.add(Main.p.a("settings.ignorelist.ignorelistheading"), this.h);
        this.c.add(Main.p.a("settings.allowpmlist.allowpmlistheading"), this.i);
        this.c.add(Main.p.a("settings.language.langheading"), this.j);
        this.getContentPane().add(this.c, "Center");
    }
    
    public void show() {
        super.show();
        this.an.setListData(Main.d.toArray());
        this.a();
    }
    
    public void a() {
        try {
            this.ab.setText("");
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(Main.i + System.getProperty("file.separator") + "log.txt")));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                this.ab.append(line + "\n");
            }
            bufferedReader.close();
        }
        catch (Exception ex) {
            b.a(ex, 3);
        }
    }
    
    private Vector m() {
        if (bm.a0 == null) {
            bm.a0 = new Vector();
            try {
                String line;
                while ((line = new BufferedReader(new InputStreamReader(new BufferedInputStream(new URL("http://" + Main.b + "/awc/lang").openStream()))).readLine()) != null) {
                    final StringTokenizer stringTokenizer = new StringTokenizer(line, ",");
                    bm.a0.addElement(new String[] { stringTokenizer.nextToken(), stringTokenizer.nextToken() });
                }
            }
            catch (Exception ex) {
                b.a(ex, 3);
            }
        }
        return bm.a0;
    }
    
    private String n() {
        final String string = this.az.getSelectedItem().toString();
        for (int i = 0; i < bm.a0.size(); ++i) {
            final String[] array = bm.a0.elementAt(i);
            if (array[0].equalsIgnoreCase(string)) {
                return array[1];
            }
        }
        return "EN";
    }
    
    private String o() {
        if (this.aj.getSelectedItem().equals("Local")) {
            return "1";
        }
        return "0";
    }
    
    private String a(final String s) {
        for (int i = 0; i < bm.a0.size(); ++i) {
            final String[] array = bm.a0.elementAt(i);
            if (array[1].equalsIgnoreCase(s)) {
                return array[0];
            }
        }
        return "";
    }
    
    private String b(final String s) {
        if (s.equals("1")) {
            return "Local";
        }
        return "All";
    }
    
    public void b() {
        this.a = false;
    }
    
    public void a(final boolean b) {
        if (this.k.i) {
            this.k.i = false;
        }
        if (b) {
            try {
                this.aw.a();
                final Hashtable<String, String> hashtable = new Hashtable<String, String>();
                hashtable.put("chat.font.name", (String)this.l.getSelectedItem());
                hashtable.put("chat.font.size", (String)this.m.getSelectedItem());
                hashtable.put("chat.font.color", (String)this.n.getSelectedItem());
                hashtable.put("chat.font.backgroundcolor", (String)this.o.getSelectedItem());
                hashtable.put("chat.frame.size", (String)this.u.getSelectedItem());
                hashtable.put("chat.allowpms", b(this.w.isSelected()));
                hashtable.put("chat.showjoins", b(this.ax.isSelected()));
                hashtable.put("cam.frame.size", (String)this.af.getSelectedItem());
                hashtable.put("cam.capturepath", t.a("\\", "\\\\", this.z.getText()));
                hashtable.put("cam.gender.male", b(this.k.e));
                hashtable.put("cam.gender.female", b(this.k.f));
                hashtable.put("cam.gender.other", b(this.k.h));
                hashtable.put("cam.gender.group", "true");
                hashtable.put("chat.sortcount", b(this.ae.isSelected()));
                hashtable.put("cam.closewindow", b(this.ad.isSelected()));
                hashtable.put("lang.def", this.n());
                hashtable.put("cam.disp", this.o());
                this.d.a(hashtable);
                h.f().a(this.ae.isSelected());
            }
            catch (Exception ex) {
                b.a(ex, 3);
            }
        }
        this.a = true;
        this.dispose();
    }
    
    public static String b(final boolean b) {
        return b ? "true" : "false";
    }
    
    public void c() {
        if (!this.a | this.k.i) {
            this.x.setVisible(true);
        }
        else {
            this.dispose();
            this.a = true;
        }
    }
    
    public void d() {
        this.p.a(Integer.parseInt(this.m.getSelectedItem().toString()));
        this.a = false;
    }
    
    public void e() {
        this.a = false;
    }
    
    public void f() {
        this.p.b(this.l.getSelectedItem().toString());
        this.a = false;
    }
    
    public void g() {
        this.p.c(this.n.getSelectedItem().toString());
        this.a = false;
    }
    
    public void h() {
        this.p.a(this.o.getSelectedItem().toString());
        this.a = false;
    }
    
    public void i() {
        if (!this.am.getText().equalsIgnoreCase("")) {
            Main.f(this.am.getText());
            this.an.setListData(Main.d.toArray());
            this.am.setText("");
        }
    }
    
    public void j() {
        Main.i(this.ar.getSelectedValue().toString());
        this.ar.remove(this.ar.getSelectedIndex());
    }
    
    public void k() {
        if (!this.aq.getText().equals("")) {
            Main.g(this.aq.getText());
            this.ar.setListData(Main.f.toArray());
            this.aq.setText("");
        }
    }
    
    public void l() {
        Main.h(this.an.getSelectedValue().toString());
        this.an.remove(this.an.getSelectedIndex());
    }
}
