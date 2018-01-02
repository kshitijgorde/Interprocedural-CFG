// 
// Decompiled by Procyon v0.5.30
// 

package mindbright.terminal;

import java.awt.Toolkit;
import java.awt.event.KeyListener;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Container;
import java.awt.event.WindowListener;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;
import java.awt.Panel;
import java.awt.Component;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.MenuItem;
import java.awt.event.ItemListener;
import java.awt.CheckboxMenuItem;
import java.awt.Button;
import java.awt.Label;
import java.awt.TextField;
import java.awt.CheckboxGroup;
import java.awt.Checkbox;
import java.awt.Choice;
import java.awt.Dialog;
import java.awt.Menu;

public final class TerminalMenuHandlerFull extends p
{
    public h d4;
    public Object[] d3;
    public Menu d2;
    public bl cp;
    public static final String[] d1;
    public static final String[] d0;
    public Dialog d_;
    public Choice dz;
    public Choice dy;
    public Choice dx;
    public Choice dw;
    public Choice dv;
    public Checkbox du;
    public Checkbox dt;
    public Checkbox ds;
    public Checkbox dr;
    public Checkbox dq;
    public CheckboxGroup dp;
    public TextField do;
    public TextField dn;
    public TextField dm;
    public TextField dl;
    public TextField dk;
    public TextField dj;
    public TextField di;
    public Label dh;
    public static final String[] dg;
    public static final String[] df;
    public Dialog de;
    public Choice dd;
    public Choice dc;
    public Checkbox db;
    public Checkbox da;
    public TextField c9;
    public TextField c8;
    public Label c7;
    public static final String[] c6;
    public static final String[] c5;
    public Dialog c4;
    public TextField c3;
    public Label c2;
    public Checkbox c1;
    public Checkbox c0;
    public Button c_;
    public Button cz;
    public int cy;
    public int cx;
    public int cw;
    
    public final void c5(final h d4) {
        this.d4 = d4;
    }
    
    public final void c4(final bl cp) {
        this.cp = cp;
    }
    
    public final void c3() {
        if (this.cp != null) {
            this.cp.c3();
        }
    }
    
    public final void c2(final int n, final boolean enabled) {
        ((CheckboxMenuItem)this.d3[n]).setEnabled(enabled);
    }
    
    public final void c1(final int n, final boolean state) {
        ((CheckboxMenuItem)this.d3[n]).setState(state);
    }
    
    public final Menu c0() {
        if (this.d2 != null) {
            return this.d2;
        }
        this.d2 = new Menu(TerminalMenuHandlerFull.d1[0]);
        final o o = new o(this);
        this.d3 = new Object[TerminalMenuHandlerFull.d1.length - 1];
        for (int i = 1; i < TerminalMenuHandlerFull.d1.length; ++i) {
            final CheckboxMenuItem checkboxMenuItem = new CheckboxMenuItem(TerminalMenuHandlerFull.d1[i], this.d4.an[i - 1]);
            ((CheckboxMenuItem)(this.d3[i - 1] = checkboxMenuItem)).addItemListener(o);
            this.d2.add(checkboxMenuItem);
        }
        ((CheckboxMenuItem)this.d3[12]).setEnabled(false);
        return this.d2;
    }
    
    public final void c_() {
        if (this.d_ == null) {
            this.d_ = new Dialog(this.d4.ci, TerminalMenuHandlerFull.d0[0], true);
            final GridBagLayout layout = new GridBagLayout();
            final GridBagConstraints gridBagConstraints = new GridBagConstraints();
            this.d_.setLayout(layout);
            gridBagConstraints.insets = new Insets(4, 4, 4, 4);
            gridBagConstraints.fill = 0;
            gridBagConstraints.anchor = 17;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.gridwidth = 6;
            final Label label = new Label("Terminal type:");
            layout.setConstraints(label, gridBagConstraints);
            this.d_.add(label);
            layout.setConstraints(this.dz = new Choice(), gridBagConstraints);
            this.d_.add(this.dz);
            gridBagConstraints.gridy = 1;
            gridBagConstraints.gridwidth = 4;
            final Label label2 = new Label("Columns:");
            layout.setConstraints(label2, gridBagConstraints);
            this.d_.add(label2);
            gridBagConstraints.gridwidth = 2;
            layout.setConstraints(this.dj = new TextField("", 3), gridBagConstraints);
            this.d_.add(this.dj);
            final Label label3 = new Label("Rows:");
            layout.setConstraints(label3, gridBagConstraints);
            this.d_.add(label3);
            layout.setConstraints(this.dk = new TextField("", 3), gridBagConstraints);
            this.d_.add(this.dk);
            gridBagConstraints.gridy = 2;
            gridBagConstraints.gridwidth = 2;
            final Label label4 = new Label("Font:");
            layout.setConstraints(label4, gridBagConstraints);
            this.d_.add(label4);
            gridBagConstraints.gridwidth = 6;
            layout.setConstraints(this.dy = new Choice(), gridBagConstraints);
            this.d_.add(this.dy);
            gridBagConstraints.gridwidth = 2;
            layout.setConstraints(this.do = new TextField("", 3), gridBagConstraints);
            this.d_.add(this.do);
            gridBagConstraints.gridy = 3;
            gridBagConstraints.gridwidth = 10;
            final Label label5 = new Label("Foreground color:");
            layout.setConstraints(label5, gridBagConstraints);
            this.d_.add(label5);
            gridBagConstraints.gridy = 4;
            gridBagConstraints.gridwidth = 6;
            layout.setConstraints(this.dx = new Choice(), gridBagConstraints);
            this.d_.add(this.dx);
            final n n;
            this.dx.addItemListener(n = new n(this));
            layout.setConstraints(this.dn = new TextField("", 10), gridBagConstraints);
            this.d_.add(this.dn);
            gridBagConstraints.gridy = 5;
            final Label label6 = new Label("Background color:");
            layout.setConstraints(label6, gridBagConstraints);
            this.d_.add(label6);
            gridBagConstraints.gridy = 6;
            layout.setConstraints(this.dw = new Choice(), gridBagConstraints);
            this.d_.add(this.dw);
            this.dw.addItemListener(n);
            layout.setConstraints(this.dm = new TextField("", 10), gridBagConstraints);
            this.d_.add(this.dm);
            gridBagConstraints.gridy = 7;
            final Label label7 = new Label("Cursor color:");
            layout.setConstraints(label7, gridBagConstraints);
            this.d_.add(label7);
            gridBagConstraints.gridy = 8;
            layout.setConstraints(this.dv = new Choice(), gridBagConstraints);
            this.d_.add(this.dv);
            this.dv.addItemListener(n);
            layout.setConstraints(this.dl = new TextField("", 10), gridBagConstraints);
            this.d_.add(this.dl);
            final Panel panel = new Panel();
            final GridBagLayout layout2 = new GridBagLayout();
            final GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
            panel.setLayout(layout2);
            gridBagConstraints2.gridy = 0;
            gridBagConstraints2.gridwidth = 4;
            gridBagConstraints2.anchor = 17;
            layout2.setConstraints(this.du = new Checkbox("Window position:"), gridBagConstraints2);
            panel.add(this.du);
            this.dp = new CheckboxGroup();
            gridBagConstraints2.gridwidth = 1;
            layout2.setConstraints(this.dt = new Checkbox("", true, this.dp), gridBagConstraints2);
            panel.add(this.dt);
            layout2.setConstraints(this.ds = new Checkbox("", false, this.dp), gridBagConstraints2);
            panel.add(this.ds);
            gridBagConstraints2.gridy = 1;
            gridBagConstraints2.gridwidth = 4;
            gridBagConstraints2.anchor = 10;
            layout2.setConstraints(this.di = new TextField("", 10), gridBagConstraints2);
            panel.add(this.di);
            gridBagConstraints2.gridwidth = 1;
            gridBagConstraints2.anchor = 17;
            layout2.setConstraints(this.dr = new Checkbox("", false, this.dp), gridBagConstraints2);
            panel.add(this.dr);
            layout2.setConstraints(this.dq = new Checkbox("", false, this.dp), gridBagConstraints2);
            panel.add(this.dq);
            final m m;
            this.du.addItemListener(m = new m(this));
            this.dt.addItemListener(m);
            this.ds.addItemListener(m);
            this.dr.addItemListener(m);
            this.dq.addItemListener(m);
            gridBagConstraints.gridy = 9;
            gridBagConstraints.insets = new Insets(8, 4, 0, 0);
            gridBagConstraints.anchor = 10;
            gridBagConstraints.gridwidth = 0;
            layout.setConstraints(panel, gridBagConstraints);
            this.d_.add(panel);
            this.dh = new Label("", 1);
            gridBagConstraints.insets = new Insets(0, 0, 0, 0);
            gridBagConstraints.gridy = 10;
            gridBagConstraints.fill = 2;
            gridBagConstraints.gridwidth = 0;
            gridBagConstraints.anchor = 10;
            layout.setConstraints(this.dh, gridBagConstraints);
            this.d_.add(this.dh);
            final Panel panel2 = new Panel(new FlowLayout());
            final Button button;
            panel2.add(button = new Button("OK"));
            button.addActionListener(new l(this));
            final Button button2;
            panel2.add(button2 = new Button("Cancel"));
            button2.addActionListener(new f(this.d_));
            gridBagConstraints.gridy = 11;
            layout.setConstraints(panel2, gridBagConstraints);
            this.d_.add(panel2);
            this.cu();
            this.d_.addWindowListener(new e(button2));
            a.b(this.d_);
            this.d_.setResizable(true);
            this.d_.pack();
        }
        this.dz.select(this.d4.ce("te"));
        this.dy.select(this.d4.ce("fn"));
        this.do.setText(this.d4.ce("fs"));
        this.dj.setText(String.valueOf(this.d4.b1()));
        this.dk.setText(String.valueOf(this.d4.b2()));
        this.cz(this.dx, this.dn, this.d4.ce("fg"));
        this.cz(this.dw, this.dm, this.d4.ce("bg"));
        this.cz(this.dv, this.dl, this.d4.ce("cc"));
        this.cx();
        final String cc = this.d4.cc;
        if (cc.length() > 0) {
            this.du.setState(true);
            this.cv();
            if (cc.equals("+0+0")) {
                this.dt.setState(true);
            }
            else if (cc.equals("-0+0")) {
                this.ds.setState(true);
            }
            else if (cc.equals("+0-0")) {
                this.dr.setState(true);
            }
            else if (cc.equals("-0-0")) {
                this.dq.setState(true);
            }
            this.di.setText(cc);
        }
        else {
            this.du.setState(false);
            this.cv();
        }
        this.dh.setText("");
        a.c(this.d_);
        this.dz.requestFocus();
        this.d_.setVisible(true);
    }
    
    public final void cz(final Choice choice, final TextField textField, final String text) {
        if (Character.isDigit(text.charAt(0))) {
            choice.select("custom rgb");
            textField.setText(text);
        }
        else {
            textField.setText("");
            textField.setEnabled(false);
            choice.select(text);
        }
    }
    
    public final void cy(final Choice choice, final TextField textField) {
        final int selectedIndex = choice.getSelectedIndex();
        if (selectedIndex == 0) {
            if (!textField.isEnabled()) {
                textField.setEditable(true);
                textField.setEnabled(true);
                textField.setBackground(SystemColor.text);
                textField.requestFocus();
            }
        }
        else {
            textField.setText("");
            textField.setEditable(false);
            textField.setEnabled(false);
            textField.setBackground(h.ay[selectedIndex - 1]);
        }
    }
    
    public final void cx() {
        this.cy(this.dx, this.dn);
        this.cy(this.dw, this.dm);
        this.cy(this.dv, this.dl);
    }
    
    public final String cw(final Choice choice, final TextField textField) {
        String s;
        if (choice.getSelectedIndex() == 0) {
            s = textField.getText();
        }
        else {
            s = choice.getSelectedItem();
        }
        return s;
    }
    
    public final void cv() {
        if (this.du.getState()) {
            this.di.setEnabled(true);
            this.dt.setEnabled(true);
            this.ds.setEnabled(true);
            this.dr.setEnabled(true);
            this.dq.setEnabled(true);
            if (this.dt.getState()) {
                this.di.setText("+0+0");
            }
            else if (this.ds.getState()) {
                this.di.setText("-0+0");
            }
            else if (this.dr.getState()) {
                this.di.setText("+0-0");
            }
            else if (this.dq.getState()) {
                this.di.setText("-0-0");
            }
        }
        else {
            this.di.setText("");
            this.di.setEnabled(false);
            this.dt.setEnabled(false);
            this.ds.setEnabled(false);
            this.dr.setEnabled(false);
            this.dq.setEnabled(false);
        }
    }
    
    public final void cu() {
        for (int i = 0; i < TerminalMenuHandlerFull.dg.length; ++i) {
            this.dz.add(TerminalMenuHandlerFull.dg[i]);
        }
        for (int j = 0; j < TerminalMenuHandlerFull.df.length; ++j) {
            this.dy.add(TerminalMenuHandlerFull.df[j]);
        }
        this.dw.add("custom rgb");
        this.dx.add("custom rgb");
        this.dv.add("custom rgb");
        for (int k = 0; k < h.ax.length; ++k) {
            this.dw.add(h.ax[k]);
            this.dx.add(h.ax[k]);
            this.dv.add(h.ax[k]);
        }
    }
    
    public final void ct() {
        if (this.de == null) {
            this.de = new Dialog(this.d4.ci, "Terminal Miscellaneous Settings", true);
            final GridBagLayout layout = new GridBagLayout();
            final GridBagConstraints gridBagConstraints = new GridBagConstraints();
            this.de.setLayout(layout);
            gridBagConstraints.insets = new Insets(4, 4, 0, 0);
            gridBagConstraints.fill = 0;
            gridBagConstraints.anchor = 17;
            gridBagConstraints.gridwidth = 4;
            gridBagConstraints.gridy = 0;
            final Label label = new Label("Savelines:");
            layout.setConstraints(label, gridBagConstraints);
            this.de.add(label);
            layout.setConstraints(this.c9 = new TextField("", 4), gridBagConstraints);
            this.de.add(this.c9);
            gridBagConstraints.gridy = 1;
            final Label label2 = new Label("Scrollbar:");
            layout.setConstraints(label2, gridBagConstraints);
            this.de.add(label2);
            layout.setConstraints(this.dd = new Choice(), gridBagConstraints);
            this.de.add(this.dd);
            for (int i = 0; i < TerminalMenuHandlerFull.c6.length; ++i) {
                this.dd.add(TerminalMenuHandlerFull.c6[i]);
            }
            gridBagConstraints.gridy = 2;
            final Label label3 = new Label("Resize gravity:");
            layout.setConstraints(label3, gridBagConstraints);
            this.de.add(label3);
            layout.setConstraints(this.dc = new Choice(), gridBagConstraints);
            this.de.add(this.dc);
            for (int j = 0; j < TerminalMenuHandlerFull.c5.length; ++j) {
                this.dc.add(TerminalMenuHandlerFull.c5[j]);
            }
            gridBagConstraints.gridy = 3;
            final Label label4 = new Label("Select delim.:");
            layout.setConstraints(label4, gridBagConstraints);
            this.de.add(label4);
            layout.setConstraints(this.c8 = new TextField("", 4), gridBagConstraints);
            this.de.add(this.c8);
            gridBagConstraints.gridy = 4;
            gridBagConstraints.gridwidth = 8;
            gridBagConstraints.insets = new Insets(4, 16, 0, 0);
            layout.setConstraints(this.da = new Checkbox("Backspace sends Delete"), gridBagConstraints);
            this.de.add(this.da);
            gridBagConstraints.gridy = 5;
            layout.setConstraints(this.db = new Checkbox("Delete sends Backspace"), gridBagConstraints);
            this.de.add(this.db);
            this.c7 = new Label("", 1);
            gridBagConstraints.insets = new Insets(0, 0, 0, 0);
            gridBagConstraints.gridy = 6;
            gridBagConstraints.fill = 2;
            gridBagConstraints.gridwidth = 0;
            gridBagConstraints.anchor = 10;
            layout.setConstraints(this.c7, gridBagConstraints);
            this.de.add(this.c7);
            final Panel panel = new Panel(new FlowLayout());
            final Button button;
            panel.add(button = new Button("OK"));
            button.addActionListener(new k(this));
            final Button button2;
            panel.add(button2 = new Button("Cancel"));
            button2.addActionListener(new f(this.de));
            gridBagConstraints.gridy = 7;
            layout.setConstraints(panel, gridBagConstraints);
            this.de.add(panel);
            this.de.addWindowListener(new e(button2));
            a.b(this.de);
            this.de.setResizable(true);
            this.de.pack();
        }
        this.dd.select(this.d4.ce("sb"));
        this.dc.select(this.d4.ce("rg"));
        this.c9.setText(this.d4.ce("sl"));
        String text = this.d4.ce("sd");
        if (text.charAt(0) == '\"' && text.charAt(text.length() - 1) == '\"') {
            text = text.substring(1, text.length() - 1);
        }
        this.c8.setText(text);
        if (this.d4.ce("bs").equals("DEL")) {
            this.da.setState(true);
        }
        else {
            this.da.setState(false);
        }
        if (this.d4.ce("de").equals("BS")) {
            this.db.setState(true);
        }
        else {
            this.db.setState(false);
        }
        this.c7.setText("");
        a.c(this.de);
        this.c9.requestFocus();
        this.de.setVisible(true);
    }
    
    public final void cs() {
        if (this.c4 == null) {
            this.c4 = new Dialog(this.d4.ci, "MindTerm - Find", false);
            final GridBagLayout layout = new GridBagLayout();
            final GridBagConstraints gridBagConstraints = new GridBagConstraints();
            this.c4.setLayout(layout);
            gridBagConstraints.fill = 0;
            gridBagConstraints.anchor = 17;
            gridBagConstraints.gridwidth = 1;
            gridBagConstraints.gridy = 0;
            layout.setConstraints(this.c2 = new Label("Find:"), gridBagConstraints);
            this.c4.add(this.c2);
            gridBagConstraints.fill = 2;
            gridBagConstraints.gridwidth = 5;
            layout.setConstraints(this.c3 = new TextField("", 26), gridBagConstraints);
            this.c4.add(this.c3);
            gridBagConstraints.gridwidth = 4;
            gridBagConstraints.ipadx = 4;
            gridBagConstraints.ipady = 4;
            gridBagConstraints.insets = new Insets(6, 3, 3, 6);
            layout.setConstraints(this.c_ = new Button("Find"), gridBagConstraints);
            this.c4.add(this.c_);
            gridBagConstraints.insets = new Insets(0, 0, 0, 0);
            gridBagConstraints.ipadx = 0;
            gridBagConstraints.ipady = 0;
            gridBagConstraints.gridwidth = 3;
            gridBagConstraints.gridy = 1;
            gridBagConstraints.fill = 0;
            layout.setConstraints(this.c0 = new Checkbox("Case sensitive"), gridBagConstraints);
            this.c4.add(this.c0);
            layout.setConstraints(this.c1 = new Checkbox("Find backwards"), gridBagConstraints);
            this.c4.add(this.c1);
            gridBagConstraints.gridwidth = 4;
            gridBagConstraints.ipadx = 4;
            gridBagConstraints.ipady = 4;
            gridBagConstraints.insets = new Insets(3, 3, 6, 6);
            gridBagConstraints.fill = 2;
            layout.setConstraints(this.cz = new Button("Cancel"), gridBagConstraints);
            this.c4.add(this.cz);
            this.cz.addActionListener(new j(this));
            this.c_.addActionListener(new i(this));
            this.c4.addWindowListener(new e(this.cz));
            a.b(this.c4);
            a.a(this.c4, new d(this.c_, this.cz), null);
            this.c4.setResizable(true);
            this.c4.pack();
        }
        a.c(this.c4);
        this.c3.requestFocus();
        this.c4.setVisible(true);
    }
    
    public static final boolean cr(final String s, final char c, final char[] array, final int n, final boolean b, final int n2) {
        if (b) {
            if (array[n] != c) {
                return false;
            }
            if (new String(array, n, n2).equals(s)) {
                return true;
            }
        }
        else {
            if (Character.toLowerCase(array[n]) != c) {
                return false;
            }
            if (new String(array, n, n2).equalsIgnoreCase(s)) {
                return true;
            }
        }
        return false;
    }
    
    public final void cq() {
        final String text = this.c3.getText();
        final int length = text.length();
        final boolean state = this.c0.getState();
        final boolean state2 = this.c1.getState();
        final int cy = this.d4.as + this.d4.bj;
        boolean b = false;
        int i = 0;
        final char c = state ? text.charAt(0) : Character.toLowerCase(text.charAt(0));
        if (this.cw > 0) {
            this.d4.ab(this.cy, this.cx, this.cy, this.cx + this.cw - 1);
        }
        int j = 0;
        if (state2) {
            int n;
            if (this.cw > 0) {
                n = this.cx - 1;
            }
            else {
                this.cy = cy;
                n = this.d4.bx - length;
            }
        Label_0240:
            for (j = this.cy; j >= 0; --j) {
                for (i = n; i >= 0; --i) {
                    if (this.d4.aw[j][i] != '\0' && cr(text, c, this.d4.aw[j], i, state, length)) {
                        break Label_0240;
                    }
                }
                n = this.d4.bx - length;
            }
            if (j >= 0) {
                b = true;
            }
        }
        else {
            int n2 = this.cx + this.cw;
        Label_0351:
            for (j = this.cy; j < cy; ++j) {
                for (i = n2; i < this.d4.bx - length; ++i) {
                    if (this.d4.aw[j][i] != '\0' && cr(text, c, this.d4.aw[j], i, state, length)) {
                        break Label_0351;
                    }
                }
                n2 = 0;
            }
            if (j < cy) {
                b = true;
            }
        }
        if (b) {
            this.cw = length;
            if (this.d4.as < j) {
                this.d4.at = this.d4.as;
            }
            else if (this.d4.at > j || j - this.d4.at > this.d4.by) {
                this.d4.at = j;
            }
            this.d4.b8();
            this.d4.bz(false);
            this.d4.ac(j, i, j, i + length - 1);
            this.cy = j;
            this.cx = i;
            this.cw = length;
        }
        else {
            this.d4.bs();
            this.cy = 0;
            this.cx = 0;
            this.cw = 0;
        }
    }
    
    static {
        d1 = new String[] { "VT Options", "Reverse Video", "Auto Wraparound", "Reverse Wraparound", "Insert mode", "Auto Linefeed", "Scroll to Bottom On Key Press", "Scroll to Bottom On Tty Output", "Local Page-ctrl Keys", "Copy <CR><NL> Instead Of <CR>", "Visible Cursor", "Use ASCII For Line Draw", "Local Echo", "Scale Font On Resize", "Visual Bell", "Map <CTRL>+<SPC> To ^@ (<NUL>)", "Toggle 80/132 Columns", "Enable 80/132 Switching", "Copy On Select" };
        d0 = new String[] { "Terminal Settings", "Emulation", "Resize gravity", "Font", "Savelines", "Scrollbar", "Colors", "Backspace" };
        dg = q.dt();
        df = Toolkit.getDefaultToolkit().getFontList();
        c6 = new String[] { "left", "right", "none" };
        c5 = new String[] { "bottom", "top" };
    }
}
