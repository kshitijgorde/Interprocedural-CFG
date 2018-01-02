// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a;

import javax.swing.event.DocumentEvent;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.Vector;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.awt.event.MouseListener;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.Action;
import javax.swing.KeyStroke;
import java.awt.Insets;
import java.awt.BorderLayout;
import b.a.d.b;
import b.a.e.m;
import javax.swing.JPanel;
import b.a.e.e;
import b.a.e.o;
import java.awt.LayoutManager;
import b.a.e.f;
import java.awt.Window;
import java.awt.Frame;
import java.awt.Dialog;
import java.awt.Component;
import b.a.d.d;
import b.a.c.q;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JComponent;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import b.a.e.n;
import javax.swing.event.ListSelectionListener;
import java.awt.event.FocusListener;
import javax.swing.event.DocumentListener;
import b.a.e.k;

public class j extends k implements DocumentListener, FocusListener, ListSelectionListener, Runnable
{
    private static final int q;
    private a r;
    private n s;
    private JTextField t;
    private a.a.a.k u;
    private JButton v;
    private JLabel w;
    private JComboBox x;
    private JComponent y;
    private JButton z;
    private volatile boolean A;
    private boolean B;
    private volatile h C;
    private Font D;
    private Font E;
    private static String[] F;
    private static final ImageIcon G;
    private static final ImageIcon H;
    private static final ImageIcon I;
    private static final ImageIcon J;
    private static final ImageIcon K;
    private static final Color L;
    static Class M;
    
    public static q a(final JComponent locationRelativeTo, final a a) {
        final Window a2 = d.a(locationRelativeTo);
        final String c = a.c("ATLAS_DIALOG_TITLE");
        j j;
        if (a2 instanceof Dialog) {
            j = new j((Dialog)a2, c, a);
        }
        else {
            if (!(a2 instanceof Frame)) {
                return null;
            }
            j = new j((Frame)a2, c, a);
        }
        j.pack();
        j.setLocationRelativeTo(locationRelativeTo);
        j.setVisible(true);
        if (j.b() == 2) {
            return null;
        }
        return j.n();
    }
    
    public j(final Dialog dialog, final String s, final a r) {
        super(dialog, s);
        this.A = false;
        this.B = false;
        this.D = new Font("Dialog", 0, b.a.d.d.a() ? 10 : 11);
        this.E = new Font("Monospaced", 0, b.a.d.d.a() ? 10 : 11);
        this.r = r;
    }
    
    public j(final Frame frame, final String s, final a r) {
        super(frame, s);
        this.A = false;
        this.B = false;
        this.D = new Font("Dialog", 0, b.a.d.d.a() ? 10 : 11);
        this.E = new Font("Monospaced", 0, b.a.d.d.a() ? 10 : 11);
        this.r = r;
    }
    
    protected void d() {
        this.a(true);
        this.i.setEnabled(false);
        this.m.setLayout(new f(9, 4));
        this.m.add(new o(this.r.c("ATLAS_PROMPT"), 550));
        final JPanel panel = new JPanel(new e(3, 4, 7, 5, 2, "xcol=0,1"));
        panel.add("b;fh", new n(this.r.c("ATLAS_CITY_CAPTION")));
        panel.add("b;l", new n(this.r.c("ATLAS_STATE_CAPTION")));
        panel.add(new m(1, 1));
        b.a.d.b.a(this.t = new a.a.a.o(this));
        this.t.getDocument().addDocumentListener(this);
        this.t.addFocusListener(this);
        final m m = new m(new BorderLayout());
        if (b.a.d.b.a()) {
            m.a(new Insets(1, 0, 0, 0));
        }
        m.setOpaque(false);
        m.add("Center", this.t);
        panel.add(m);
        if (j.F == null) {
            m();
        }
        b.a.d.b.a(this.x = new JComboBox((E[])j.F));
        this.x.setEditable(true);
        this.x.addFocusListener(this);
        final Component editorComponent = this.x.getEditor().getEditorComponent();
        if (editorComponent instanceof JComponent) {
            (this.y = (JComponent)editorComponent).addFocusListener(this);
            this.y.getInputMap().put(KeyStroke.getKeyStroke(10, 0), "do.search");
            this.y.getActionMap().put("do.search", new i(this, "do.search"));
        }
        panel.add(this.x);
        (this.v = new b.a.e.j(this.r.c("ATLAS_SEARCH_BUTTON"), j.G)).setDisabledIcon(j.H);
        this.v.putClientProperty("JButton.buttonType", "text");
        this.v.setEnabled(false);
        this.v.setDefaultCapable(true);
        this.v.addActionListener(this);
        panel.add(this.v);
        (this.s = new n(this.r.c("ATLAS_FIELD_REQUIRED"))).setForeground(j.L);
        panel.add("t;l", this.s);
        panel.add("t;l", new n(this.r.c("ATLAS_FIELD_OPTIONAL")));
        (this.z = new b.a.e.j(this.r.c("ATLAS_STOP_BUTTON"), j.I)).setDisabledIcon(j.J);
        this.z.setEnabled(false);
        this.z.putClientProperty("JButton.buttonType", "text");
        this.z.addActionListener(this);
        panel.add(this.z);
        this.m.add(panel);
        this.m.add(new JSeparator());
        final JPanel panel2 = new JPanel(new f(9, 4, 2));
        (this.w = new JLabel(this.r.c("ATLAS_LIST_HEADER"))).setEnabled(false);
        panel2.add(this.w);
        final JPanel panel3 = new JPanel(new f(4, 9));
        (this.u = new a.a.a.k(this)).addFocusListener(this);
        this.u.addListSelectionListener(this);
        this.u.addMouseListener(new c(this));
        panel3.add("*", this.u.a());
        panel2.add("*", panel3);
        this.m.add("*", panel2);
        this.t.setNextFocusableComponent(this.x);
        this.x.setNextFocusableComponent(this.v);
        this.v.setNextFocusableComponent(this.z);
        this.z.setNextFocusableComponent(this.u);
        this.u.setNextFocusableComponent(this.i);
        this.i.setNextFocusableComponent(this.k);
        this.k.setNextFocusableComponent(this.t);
    }
    
    protected void l() {
        this.x.setFont(this.t.getFont());
        this.t.requestFocus();
    }
    
    private static void m() {
        try {
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new b.a.b.a("$/atlas_query_states_@R.txt", (j.M == null) ? (j.M = a("a.a.a.j")) : j.M), "ISO-8859-1"));
            final Vector vector = new Vector<String>();
            vector.add("");
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                vector.add(line);
            }
            bufferedReader.close();
            vector.toArray(j.F = new String[vector.size()]);
        }
        catch (IOException ex) {
            j.F = new String[] { "---" };
        }
    }
    
    public q n() {
        final q selectedValue = this.u.getSelectedValue();
        if (selectedValue instanceof q) {
            return selectedValue;
        }
        return null;
    }
    
    private boolean o() {
        return this.t.getText() != null && this.t.getText().length() > 0;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final Object source = actionEvent.getSource();
        if (source == this.v) {
            this.p();
        }
        else if (source == this.z) {
            this.A = true;
        }
        else {
            super.actionPerformed(actionEvent);
        }
    }
    
    public void focusGained(final FocusEvent focusEvent) {
        final Object source = focusEvent.getSource();
        boolean b = false;
        if (source == this.x && !this.x.isPopupVisible()) {
            b = true;
            if (this.y != null) {
                this.y.requestFocus();
            }
        }
        if (source == this.t || source == this.y || b) {
            if (this.o()) {
                this.a(this.v);
            }
            else {
                this.a(null);
            }
        }
        else if (source == this.u && this.n() != null) {
            this.a(this.i);
        }
    }
    
    public void focusLost(final FocusEvent focusEvent) {
    }
    
    public void valueChanged(final ListSelectionEvent listSelectionEvent) {
        if (this.n() == null) {
            this.i.setEnabled(false);
            if (!this.o()) {
                this.a(null);
            }
            else {
                this.a(this.v);
            }
        }
        else {
            this.i.setEnabled(true);
            this.a(this.i);
        }
    }
    
    private void p() {
        String s = this.t.getText().trim();
        String substring = (String)this.x.getEditor().getItem();
        if (substring != null && substring.length() > 0) {
            final int index = substring.indexOf(" - ");
            if (index >= 0) {
                substring = substring.substring(index + 3);
                this.x.setSelectedItem(substring);
            }
            else if (substring.indexOf("---") >= 0) {
                substring = "";
                this.x.setSelectedItem(substring);
            }
            if (substring.length() > 0) {
                s = s + ", " + substring;
            }
        }
        if (s.length() > 0 && this.C == null) {
            this.u.setListData(new Object[] { " " });
            this.u.setForeground(Color.black);
            this.i.setEnabled(false);
            this.z.setEnabled(true);
            this.w.setEnabled(false);
            this.a(null);
            this.A = false;
            this.C = new h(this.r, s, this.B);
            if (this.B) {
                this.C.run();
                this.b(false);
            }
            else {
                this.C.start();
                new Thread(this).start();
            }
        }
    }
    
    public void run() {
        int n = -1;
        boolean b = false;
        final String c = this.r.c("ATLAS_PLEASE_WAIT");
        while (this.C != null && this.C.isAlive() && !this.A) {
            if (++n % 4 == 0 || n % 4 == 2) {
                SwingUtilities.invokeLater(new a.a.a.e(this, 0, (n % 4 == 0) ? Boolean.TRUE : Boolean.FALSE));
                if (n % 4 == 0) {
                    SwingUtilities.invokeLater(new a.a.a.e(this, 1, c + (480 - n) * 250 / 1000));
                }
            }
            if (n == 480) {
                b = true;
                break;
            }
            b.a.d.d.a(250L);
        }
        SwingUtilities.invokeLater(new a.a.a.e(this, 2, b ? Boolean.TRUE : Boolean.FALSE));
    }
    
    private void b(final boolean b) {
        final Vector<Object> listData = new Vector<Object>();
        int length = -1;
        this.z.setEnabled(false);
        this.u.setForeground(Color.black);
        this.v.setIcon(j.G);
        if (!this.A && this.C != null) {
            final q[] a = this.C.a();
            final String[] c = this.C.c();
            if (a != null) {
                if (a.length == 0) {
                    this.u.setForeground(Color.red);
                    listData.addElement(this.r.c("ATLAS_NO_MATCH"));
                }
                if (c != null) {
                    length = c.length;
                    for (int i = 0; i < c.length; ++i) {
                        listData.addElement(c[i]);
                    }
                }
                else {
                    length = 0;
                }
                for (int j = 0; j < a.length; ++j) {
                    listData.addElement(a[j]);
                }
                if (a.length > 0) {
                    this.w.setEnabled(true);
                    this.u.requestFocus();
                }
            }
            else {
                String s;
                if (b) {
                    s = this.r.c("ATLAS_TIMEOUT");
                }
                else {
                    final String b2 = this.C.b();
                    s = this.r.c("ATLAS_SERVER_ERROR") + ((b2 == null) ? "" : (" [" + b2 + "]"));
                }
                this.u.setForeground(Color.red);
                listData.addElement(s);
                listData.addElement(this.r.c("ATLAS_INTERNET_REQUIRED"));
                if (c != null) {
                    for (int k = 0; k < c.length; ++k) {
                        listData.addElement(c[k]);
                    }
                }
            }
        }
        this.u.setListData(listData);
        this.u.setSelectedIndex(length);
        this.C = null;
        this.A = false;
    }
    
    public void changedUpdate(final DocumentEvent documentEvent) {
        if (!this.o()) {
            this.s.setForeground(j.L);
            this.v.setEnabled(false);
            this.a(null);
        }
        else {
            this.s.setForeground(b.a.e.n.e);
            this.v.setEnabled(true);
            this.a(this.v);
        }
    }
    
    public void insertUpdate(final DocumentEvent documentEvent) {
        this.changedUpdate(documentEvent);
    }
    
    public void removeUpdate(final DocumentEvent documentEvent) {
        this.changedUpdate(documentEvent);
    }
    
    static Class a(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static JComboBox a(final j j) {
        return j.x;
    }
    
    static ImageIcon q() {
        return j.G;
    }
    
    static ImageIcon r() {
        return j.K;
    }
    
    static JButton b(final j j) {
        return j.v;
    }
    
    static a.a.a.k c(final j j) {
        return j.u;
    }
    
    static void a(final j j, final boolean b) {
        j.b(b);
    }
    
    static Font d(final j j) {
        return j.D;
    }
    
    static Font e(final j j) {
        return j.E;
    }
    
    static int s() {
        return j.q;
    }
    
    static h f(final j j) {
        return j.C;
    }
    
    static boolean g(final j j) {
        return j.o();
    }
    
    static void h(final j j) {
        j.p();
    }
    
    static {
        q = (d.a() ? 12 : 10);
        G = new ImageIcon(b.a.e.a.a("$/search_icon.gif", (j.M == null) ? (j.M = a("a.a.a.j")) : j.M));
        H = new ImageIcon(b.a.e.a.a("$/search_icon_disabled.gif", (j.M == null) ? (j.M = a("a.a.a.j")) : j.M));
        I = new ImageIcon(b.a.e.a.a("$/stop_icon.gif", (j.M == null) ? (j.M = a("a.a.a.j")) : j.M));
        J = new ImageIcon(b.a.e.a.a("$/stop_icon_disabled.gif", (j.M == null) ? (j.M = a("a.a.a.j")) : j.M));
        K = new ImageIcon(b.a.e.a.a("$/blank_icon.gif", (j.M == null) ? (j.M = a("a.a.a.j")) : j.M));
        L = new Color(13369344);
    }
}
