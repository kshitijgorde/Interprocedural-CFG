// 
// Decompiled by Procyon v0.5.30
// 

package mindbright.ssh;

import java.io.File;
import java.awt.event.MouseMotionListener;
import java.awt.Toolkit;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.net.InetAddress;
import java.awt.event.KeyListener;
import java.awt.Container;
import java.awt.event.WindowListener;
import java.awt.FlowLayout;
import java.awt.Component;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.PopupMenu;
import java.awt.MenuBar;
import mindbright.terminal.TerminalMenuHandlerFull;
import java.awt.event.ItemEvent;
import java.awt.event.ActionEvent;
import java.awt.MenuShortcut;
import java.awt.MenuItem;
import java.awt.CheckboxMenuItem;
import java.awt.Menu;
import java.awt.TextArea;
import java.awt.List;
import java.awt.Panel;
import java.awt.Button;
import java.awt.FileDialog;
import java.awt.Label;
import java.awt.TextField;
import java.awt.Checkbox;
import java.awt.Choice;
import java.awt.Dialog;
import mindbright.application.MindTerm;
import java.awt.Frame;
import java.awt.event.ItemListener;
import java.awt.event.ActionListener;

public final class SSHMenuHandlerFull extends bl implements ActionListener, ItemListener
{
    public String kf;
    public bq e9;
    public Frame a;
    public h d4;
    public MindTerm ke;
    public static final String[][] kd;
    public static final int[][] kc;
    public Object[][] kb;
    public int ka;
    public Dialog d_;
    public Choice j9;
    public Choice j8;
    public Checkbox j7;
    public Checkbox j6;
    public Checkbox j5;
    public Checkbox j4;
    public Checkbox j3;
    public Checkbox j2;
    public Checkbox j1;
    public Checkbox j0;
    public Checkbox j_;
    public TextField jz;
    public TextField jy;
    public TextField jx;
    public TextField jw;
    public TextField jv;
    public TextField ju;
    public TextField jt;
    public TextField js;
    public TextField jr;
    public TextField jq;
    public Label dh;
    public String[] hz;
    public String[] jp;
    public FileDialog jo;
    public Button jn;
    public Button jm;
    public boolean jl;
    public boolean jk;
    public Panel jj;
    public Dialog ji;
    public List jh;
    public a2 jg;
    public a2 jf;
    public Dialog je;
    public Dialog jd;
    public List jc;
    public boolean jb;
    public FileDialog ja;
    public FileDialog i9;
    public FileDialog i8;
    public b9 i7;
    public FileDialog i6;
    public Dialog i5;
    public FileDialog i4;
    public TextField i3;
    public TextField i2;
    public TextField i1;
    public TextField i0;
    public TextField i_;
    public TextArea iz;
    public Checkbox iy;
    public co ix;
    public Label iw;
    public Button iv;
    public int iu;
    public int it;
    public byte[] is;
    
    public final void i3(final MindTerm ke, final bq e9, final Frame a, final h d4) {
        this.ke = ke;
        this.e9 = e9;
        this.a = a;
        this.d4 = d4;
    }
    
    public final void ca(final int ka) {
        this.d4.ca(ka);
        this.ka = ka;
    }
    
    public final int i2() {
        return this.ka;
    }
    
    public final Menu i1(final int n) {
        final Menu menu = new Menu(SSHMenuHandlerFull.kd[n][0]);
        final int length = SSHMenuHandlerFull.kd[n].length;
        if (this.kb == null) {
            this.kb = new Object[SSHMenuHandlerFull.kd.length][];
        }
        if (this.kb[n] == null) {
            this.kb[n] = new Object[SSHMenuHandlerFull.kd[n].length];
        }
        for (int i = 1; i < length; ++i) {
            final String s = SSHMenuHandlerFull.kd[n][i];
            if (s == null) {
                menu.addSeparator();
            }
            else {
                MenuItem menuItem;
                if (s.charAt(0) == '_') {
                    menuItem = new CheckboxMenuItem(s.substring(1));
                    ((CheckboxMenuItem)menuItem).addItemListener(this);
                }
                else {
                    menuItem = new MenuItem(s);
                    menuItem.addActionListener(this);
                }
                if (SSHMenuHandlerFull.kc[n][i] != -1) {
                    menuItem.setShortcut(new MenuShortcut(SSHMenuHandlerFull.kc[n][i], true));
                }
                menu.add((MenuItem)(this.kb[n][i] = menuItem));
            }
        }
        return menu;
    }
    
    public final int[] i0(final String s) {
        final int[] array = new int[2];
        for (int i = 0; i < SSHMenuHandlerFull.kd.length; ++i) {
            for (int j = 1; j < SSHMenuHandlerFull.kd[i].length; ++j) {
                final String s2 = SSHMenuHandlerFull.kd[i][j];
                if (s2 != null && s.equals(s2)) {
                    array[0] = i;
                    array[1] = j;
                    i = SSHMenuHandlerFull.kd.length;
                    break;
                }
            }
        }
        return array;
    }
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        this.i_(this.i0(((MenuItem)actionEvent.getSource()).getLabel()));
    }
    
    public final void itemStateChanged(final ItemEvent itemEvent) {
        this.i_(this.i0("_" + (String)itemEvent.getItem()));
    }
    
    public final void i_(final int[] array) {
        Label_1069: {
            switch (array[0]) {
                case 0: {
                    switch (array[1]) {
                        case 1: {
                            this.ke.n2();
                            break Label_1069;
                        }
                        case 2: {
                            this.ke.n3();
                            break Label_1069;
                        }
                        case 3: {
                            this.ir();
                            break Label_1069;
                        }
                        case 4: {
                            if (this.ke.n5()) {
                                this.e9.lf();
                                this.e9.ko = this.e9.kn;
                                break Label_1069;
                            }
                            break Label_1069;
                        }
                        case 6: {
                            this.iq();
                            break Label_1069;
                        }
                        case 7: {
                            try {
                                if (this.e9.f.hj && this.e9.f.he()) {
                                    final String ih = this.ih("Please set password for alias " + this.e9.f.hm, "MindTerm - Set File Password");
                                    if (ih == null) {
                                        return;
                                    }
                                    this.e9.f.hf(ih);
                                }
                                this.e9.f.gz();
                            }
                            catch (Throwable t) {
                                this.ij("Error saving settings: " + t.getMessage());
                            }
                            break Label_1069;
                        }
                        case 8: {
                            this.ip();
                            break Label_1069;
                        }
                        case 10: {
                            this.il();
                            break Label_1069;
                        }
                        case 12: {
                            this.e9.ko = true;
                            a.d("MindTerm - File Transfer", this.a, this.e9.f, this.e9);
                            break Label_1069;
                        }
                        case 13: {
                            if (!((CheckboxMenuItem)this.kb[0][13]).getState()) {
                                this.im();
                                break Label_1069;
                            }
                            if (!this.in()) {
                                ((CheckboxMenuItem)this.kb[0][13]).setState(false);
                                break Label_1069;
                            }
                            break Label_1069;
                        }
                        case 14: {
                            this.io();
                            break Label_1069;
                        }
                        case 16: {
                            this.ke.ed();
                            break Label_1069;
                        }
                        case 17: {
                            this.ke.n1();
                            break Label_1069;
                        }
                    }
                    break;
                }
                case 1: {
                    switch (array[1]) {
                        case 1: {
                            this.d4.t();
                            break Label_1069;
                        }
                        case 2: {
                            this.d4.s();
                            break Label_1069;
                        }
                        case 3: {
                            this.d4.t();
                            this.d4.s();
                            break Label_1069;
                        }
                        case 4: {
                            this.d4.ad();
                            break Label_1069;
                        }
                        case 5: {
                            ((TerminalMenuHandlerFull)this.d4.co()).cs();
                            break Label_1069;
                        }
                        case 7: {
                            this.d4.a_();
                            this.d4.bc(0, 0, false);
                            break Label_1069;
                        }
                        case 8: {
                            this.d4.b5();
                            break Label_1069;
                        }
                        case 9: {
                            this.d4.bh();
                            break Label_1069;
                        }
                    }
                    break;
                }
                case 2: {
                    switch (array[1]) {
                        case 1: {
                            this.ix();
                            break Label_1069;
                        }
                        case 2: {
                            ((TerminalMenuHandlerFull)this.d4.co()).c_();
                            break Label_1069;
                        }
                        case 3: {
                            ((TerminalMenuHandlerFull)this.d4.co()).ct();
                            break Label_1069;
                        }
                        case 4: {
                            aq.d4("MindTerm - Proxy Settings", this.a, this.e9.f);
                            break Label_1069;
                        }
                        case 6: {
                            this.e9.km.er("");
                            this.e9.km.er("** hit a key to enter local command-shell **");
                            this.e9.kp.e_();
                            break Label_1069;
                        }
                        case 8: {
                            this.e9.f.hi(((CheckboxMenuItem)this.kb[2][8]).getState());
                            this.c3();
                            break Label_1069;
                        }
                        case 9: {
                            this.e9.f.hj(((CheckboxMenuItem)this.kb[2][9]).getState());
                            this.c3();
                            break Label_1069;
                        }
                        case 10: {
                            this.e9.f.hh(((CheckboxMenuItem)this.kb[2][10]).getState());
                            if (!this.e9.f.hj || !this.e9.f.he() || this.e9.f.gv() == null) {
                                break Label_1069;
                            }
                            final String ih2 = this.ih("Please set password for alias " + this.e9.f.hm, "MindTerm - Set File Password");
                            if (ih2 == null) {
                                this.e9.f.hh(false);
                                this.c3();
                                return;
                            }
                            this.e9.f.hf(ih2);
                            break Label_1069;
                        }
                    }
                    break;
                }
                case 3: {
                    switch (array[1]) {
                        case 1: {
                            x.d4("MindTerm - Basic Tunnels Setup", this.e9, this.e9.f, this.a);
                            break Label_1069;
                        }
                        case 2: {
                            this.it();
                            break Label_1069;
                        }
                        case 4: {
                            this.iv();
                            break Label_1069;
                        }
                    }
                    break;
                }
                case 4: {
                    switch (array[1]) {
                        case 1: {
                            this.ij("No help available yet, be patient :-)");
                            break Label_1069;
                        }
                        case 2: {
                            this.if("About MindTerm v1.2.1", this.kf, 15, 60, true);
                            break Label_1069;
                        }
                    }
                    break;
                }
            }
        }
    }
    
    public final void c3() {
        final boolean kp = this.e9.kp();
        final boolean e5 = this.e9.e5();
        ((MenuItem)this.kb[2][6]).setEnabled(kp && this.e9.kp.e1());
        ((MenuItem)this.kb[0][12]).setEnabled(kp);
        ((MenuItem)this.kb[0][14]).setEnabled(kp);
        ((MenuItem)this.kb[0][8]).setEnabled(kp && this.e9.f.hc() != null);
        ((MenuItem)this.kb[3][4]).setEnabled(kp);
        ((MenuItem)this.kb[3][1]).setEnabled(kp);
        ((MenuItem)this.kb[3][2]).setEnabled(kp);
        ((MenuItem)this.kb[0][3]).setEnabled(!e5);
        ((MenuItem)this.kb[0][4]).setEnabled(e5);
        ((MenuItem)this.kb[0][6]).setEnabled(!e5);
        ((MenuItem)this.kb[0][7]).setEnabled(this.e9.f.g0());
        ((CheckboxMenuItem)this.kb[2][8]).setState(this.e9.f.hl);
        ((CheckboxMenuItem)this.kb[2][9]).setState(this.e9.f.hk);
        ((CheckboxMenuItem)this.kb[2][10]).setState(this.e9.f.hj);
        final boolean f5 = this.e9.kp.f5;
        ((MenuItem)this.kb[1][1]).setEnabled(f5);
        ((MenuItem)this.kb[1][3]).setEnabled(f5);
    }
    
    public final void iz(final MenuBar menuBar) {
        menuBar.add(this.i1(0));
        menuBar.add(this.i1(1));
        menuBar.add(this.i1(2));
        menuBar.add(((TerminalMenuHandlerFull)this.d4.co()).c0());
        menuBar.add(this.i1(3));
        menuBar.setHelpMenu(this.i1(4));
        this.c3();
    }
    
    public final void iy(final PopupMenu popupMenu) {
        popupMenu.add(this.i1(0));
        popupMenu.add(this.i1(1));
        popupMenu.add(this.i1(2));
        popupMenu.add(((TerminalMenuHandlerFull)this.d4.co()).c0());
        popupMenu.add(this.i1(3));
        popupMenu.addSeparator();
        popupMenu.add(this.i1(4));
        this.c3();
    }
    
    public final void ix() {
        if (this.d_ == null) {
            this.hz = ca.ml();
            final String[] jp = new String[ca.ml.length];
            for (int i = 1; i < ca.ml.length; ++i) {
                jp[i - 1] = ca.ml[i];
            }
            jp[ca.ml.length - 1] = "custom...";
            this.jp = jp;
            this.d_ = new Dialog(this.a, true);
            final GridBagLayout layout = new GridBagLayout();
            final GridBagConstraints gridBagConstraints = new GridBagConstraints();
            this.d_.setLayout(layout);
            gridBagConstraints.fill = 2;
            gridBagConstraints.anchor = 17;
            gridBagConstraints.gridwidth = 1;
            gridBagConstraints.insets = new Insets(4, 4, 0, 4);
            gridBagConstraints.gridy = 0;
            final Label label = new Label("Server:");
            layout.setConstraints(label, gridBagConstraints);
            this.d_.add(label);
            gridBagConstraints.gridwidth = 3;
            layout.setConstraints(this.jt = new TextField("", 16), gridBagConstraints);
            this.d_.add(this.jt);
            gridBagConstraints.gridwidth = 1;
            final Label label2 = new Label("Port:");
            layout.setConstraints(label2, gridBagConstraints);
            this.d_.add(label2);
            layout.setConstraints(this.jz = new TextField("", 4), gridBagConstraints);
            this.d_.add(this.jz);
            gridBagConstraints.gridy = 1;
            gridBagConstraints.gridwidth = 2;
            final Label label3 = new Label("Username:");
            layout.setConstraints(label3, gridBagConstraints);
            this.d_.add(label3);
            layout.setConstraints(this.jy = new TextField("", 10), gridBagConstraints);
            this.d_.add(this.jy);
            final Label label4 = new Label("Cipher:");
            gridBagConstraints.gridwidth = 1;
            layout.setConstraints(label4, gridBagConstraints);
            this.d_.add(label4);
            this.j9 = new Choice();
            for (int j = 0; j < this.hz.length; ++j) {
                this.j9.add(this.hz[j]);
            }
            layout.setConstraints(this.j9, gridBagConstraints);
            this.d_.add(this.j9);
            gridBagConstraints.gridy = 4;
            gridBagConstraints.gridwidth = 2;
            final Label label5 = new Label("Authentication:");
            layout.setConstraints(label5, gridBagConstraints);
            this.d_.add(label5);
            this.j8 = new Choice();
            for (int k = 0; k < this.jp.length; ++k) {
                this.j8.add(this.jp[k]);
            }
            gridBagConstraints.gridwidth = 1;
            layout.setConstraints(this.j8, gridBagConstraints);
            this.d_.add(this.j8);
            final ba ba;
            this.j8.addItemListener(ba = new ba(this));
            gridBagConstraints.gridwidth = 3;
            layout.setConstraints(this.jr = new TextField("", 10), gridBagConstraints);
            this.d_.add(this.jr);
            gridBagConstraints.gridy = 5;
            gridBagConstraints.gridwidth = 2;
            final Label label6 = new Label("Identity:");
            layout.setConstraints(label6, gridBagConstraints);
            this.d_.add(label6);
            gridBagConstraints.gridwidth = 3;
            layout.setConstraints(this.jx = new TextField("", 16), gridBagConstraints);
            this.d_.add(this.jx);
            (this.jn = new Button("...")).addActionListener(new a9(this));
            gridBagConstraints.fill = 0;
            gridBagConstraints.gridwidth = 1;
            layout.setConstraints(this.jn, gridBagConstraints);
            this.d_.add(this.jn);
            gridBagConstraints.gridy = 6;
            gridBagConstraints.gridwidth = 0;
            gridBagConstraints.fill = 0;
            gridBagConstraints.anchor = 13;
            gridBagConstraints.insets = new Insets(4, 0, 4, 8);
            final Panel panel = new Panel(new FlowLayout());
            final Button button = new Button("Configure Proxy");
            button.addActionListener(new a8(this));
            panel.add(button);
            (this.jm = new Button("More options...")).addActionListener(new a7(this));
            panel.add(this.jm);
            layout.setConstraints(panel, gridBagConstraints);
            this.d_.add(panel);
            this.jj = new Panel();
            final GridBagLayout layout2 = new GridBagLayout();
            final GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
            gridBagConstraints2.fill = 0;
            gridBagConstraints2.gridwidth = 2;
            gridBagConstraints2.anchor = 17;
            gridBagConstraints2.insets = new Insets(4, 4, 0, 4);
            this.jj.setLayout(layout2);
            gridBagConstraints2.gridy = 0;
            gridBagConstraints2.gridwidth = 1;
            layout2.setConstraints(this.j7 = new Checkbox("X11 forward"), gridBagConstraints2);
            this.jj.add(this.j7);
            this.j7.addItemListener(ba);
            final Label label7 = new Label("Local X11-display:");
            gridBagConstraints2.gridwidth = 3;
            layout2.setConstraints(label7, gridBagConstraints2);
            this.jj.add(label7);
            this.jw = new TextField("", 12);
            gridBagConstraints2.gridwidth = 1;
            layout2.setConstraints(this.jw, gridBagConstraints2);
            this.jj.add(this.jw);
            gridBagConstraints2.gridy = 1;
            this.j1 = new Checkbox("Set MTU");
            gridBagConstraints2.gridwidth = 1;
            layout2.setConstraints(this.j1, gridBagConstraints2);
            this.jj.add(this.j1);
            final Label label8 = new Label("Max. packet size:");
            gridBagConstraints2.gridwidth = 3;
            layout2.setConstraints(label8, gridBagConstraints2);
            this.jj.add(label8);
            this.jv = new TextField("", 12);
            gridBagConstraints2.gridwidth = 1;
            layout2.setConstraints(this.jv, gridBagConstraints2);
            this.jj.add(this.jv);
            this.j1.addItemListener(ba);
            gridBagConstraints2.gridy = 2;
            this.j0 = new Checkbox("Send keep-alive");
            gridBagConstraints2.gridwidth = 1;
            layout2.setConstraints(this.j0, gridBagConstraints2);
            this.jj.add(this.j0);
            final Label label9 = new Label("Interval (seconds):");
            gridBagConstraints2.gridwidth = 3;
            layout2.setConstraints(label9, gridBagConstraints2);
            this.jj.add(label9);
            this.ju = new TextField("", 12);
            gridBagConstraints2.gridwidth = 1;
            layout2.setConstraints(this.ju, gridBagConstraints2);
            this.jj.add(this.ju);
            this.j0.addItemListener(ba);
            gridBagConstraints2.gridy = 3;
            this.j3 = new Checkbox("Enable ftp PORT");
            gridBagConstraints2.gridwidth = 1;
            layout2.setConstraints(this.j3, gridBagConstraints2);
            this.jj.add(this.j3);
            final Label label10 = new Label("Real sshd address:");
            gridBagConstraints2.gridwidth = 3;
            layout2.setConstraints(label10, gridBagConstraints2);
            this.jj.add(label10);
            this.js = new TextField("", 12);
            gridBagConstraints2.gridwidth = 1;
            layout2.setConstraints(this.js, gridBagConstraints2);
            this.jj.add(this.js);
            this.j3.addItemListener(ba);
            gridBagConstraints2.gridy = 4;
            this.j2 = new Checkbox("Set localhost");
            gridBagConstraints2.gridwidth = 1;
            layout2.setConstraints(this.j2, gridBagConstraints2);
            this.jj.add(this.j2);
            final Label label11 = new Label("Localhost address:");
            gridBagConstraints2.gridwidth = 3;
            layout2.setConstraints(label11, gridBagConstraints2);
            this.jj.add(label11);
            this.jq = new TextField("", 12);
            gridBagConstraints2.gridwidth = 1;
            layout2.setConstraints(this.jq, gridBagConstraints2);
            this.jj.add(this.jq);
            this.j2.addItemListener(ba);
            gridBagConstraints2.gridy = 5;
            gridBagConstraints2.gridwidth = 2;
            gridBagConstraints2.insets = new Insets(16, 4, 0, 4);
            layout2.setConstraints(this.j4 = new Checkbox("Verify server key"), gridBagConstraints2);
            this.jj.add(this.j4);
            layout2.setConstraints(this.j_ = new Checkbox("Allocate PTY"), gridBagConstraints2);
            this.jj.add(this.j_);
            gridBagConstraints2.gridy = 6;
            layout2.setConstraints(this.j6 = new Checkbox("Priv. source port"), gridBagConstraints2);
            this.jj.add(this.j6);
            gridBagConstraints2.gridwidth = 3;
            layout2.setConstraints(this.j5 = new Checkbox("Allow remote connects"), gridBagConstraints2);
            this.jj.add(this.j5);
            gridBagConstraints.gridy = 7;
            gridBagConstraints.insets = new Insets(0, 0, 0, 0);
            gridBagConstraints.anchor = 10;
            gridBagConstraints.fill = 0;
            gridBagConstraints.gridwidth = 0;
            layout.setConstraints(this.jj, gridBagConstraints);
            gridBagConstraints.insets = new Insets(0, 0, 0, 0);
            this.dh = new Label("", 1);
            gridBagConstraints.gridy = 12;
            gridBagConstraints.fill = 2;
            layout.setConstraints(this.dh, gridBagConstraints);
            this.d_.add(this.dh);
            final Panel panel2 = new Panel(new FlowLayout());
            final Button button2;
            panel2.add(button2 = new Button("OK"));
            button2.addActionListener(new a6(this));
            final Button button3;
            panel2.add(button3 = new Button("Cancel"));
            button3.addActionListener(new f(this.d_));
            gridBagConstraints.gridy = 13;
            layout.setConstraints(panel2, gridBagConstraints);
            this.d_.add(panel2);
            this.d_.addWindowListener(new e(button3));
            a.b(this.d_);
            a.a(this.d_, new d(button2, button3), null);
            this.d_.setResizable(true);
            this.d_.pack();
        }
        final String ce = this.e9.f.ce("server");
        if ((ce != null && ce.length() == 0) || !this.e9.fz) {
            this.jt.setEnabled(true);
            this.d_.setTitle("MindTerm - New Server");
            this.jl = true;
            this.e9.f.g2();
        }
        else {
            this.jt.setEnabled(false);
            this.jl = false;
            this.d_.setTitle("MindTerm - SSH Settings: " + this.e9.f.hm);
        }
        this.jt.setText(ce);
        this.jz.setText(this.e9.f.ce("port"));
        this.jy.setText(this.e9.f.ce("usrname"));
        this.j9.select(this.e9.f.ce("cipher"));
        final String ce2 = this.e9.f.ce("authtyp");
        if (ce2.indexOf(44) == -1) {
            this.j8.select(ce2);
        }
        else {
            this.j8.select("custom...");
            this.jr.setText(ce2);
        }
        this.jx.setText(this.e9.f.ce("idfile"));
        this.jw.setText(this.e9.f.ce("display"));
        this.jv.setText(this.e9.f.ce("mtu"));
        this.ju.setText(this.e9.f.ce("alive"));
        final InetAddress ln = this.e9.ln();
        if (ln != null) {
            this.js.setText(ln.getHostAddress());
        }
        this.j7.setState(Boolean.valueOf(this.e9.f.ce("x11fwd")));
        this.j1.setState(!this.e9.f.ce("mtu").equals("0"));
        this.j0.setState(!this.e9.f.ce("alive").equals("0"));
        this.j2.setState(!this.e9.f.ce("localhst").equals("0.0.0.0"));
        this.jq.setEnabled(false);
        this.j6.setState(Boolean.valueOf(this.e9.f.ce("prvport")));
        this.j5.setState(Boolean.valueOf(this.e9.f.ce("remfwd")));
        this.j4.setState(Boolean.valueOf(this.e9.f.ce("idhost")));
        this.j3.setState(Boolean.valueOf(this.e9.f.ce("portftp")));
        this.j_.setState(Boolean.valueOf(this.e9.f.ce("forcpty")));
        this.iw();
        this.dh.setText("");
        a.c(this.d_);
        if (this.jt.isEnabled()) {
            this.jt.requestFocus();
        }
        else {
            this.jy.requestFocus();
        }
        this.d_.setVisible(true);
    }
    
    public final void iw() {
        final String selectedItem = this.j8.getSelectedItem();
        if (selectedItem.equals("rsa") || selectedItem.equals("rhostsrsa") || selectedItem.equals("custom...")) {
            this.jx.setEnabled(true);
            this.jn.setEnabled(true);
        }
        else {
            this.jx.setEnabled(false);
            this.jn.setEnabled(false);
        }
        if (selectedItem.equals("custom...")) {
            this.jr.setEnabled(true);
        }
        else {
            this.jr.setEnabled(false);
            this.jr.setText("");
        }
        this.jw.setEnabled(this.j7.getState());
        this.jv.setEnabled(this.j1.getState());
        if (!this.j1.getState()) {
            this.jv.setText("0");
        }
        if (!this.ju.isEnabled() && this.j0.getState()) {
            this.ju.setText("10");
        }
        else if (!this.ju.isEnabled()) {
            this.ju.setText("0");
        }
        this.ju.setEnabled(this.j0.getState());
        this.js.setEnabled(this.j3.getState());
        if (this.j2.getState()) {
            if (!this.jq.isEnabled()) {
                this.jq.setText(this.e9.f.ce("localhst"));
            }
        }
        else {
            this.jq.setText("0.0.0.0");
        }
        this.jq.setEnabled(this.j2.getState());
    }
    
    public final void iv() {
        if (this.ji == null) {
            this.ji = new Dialog(this.a, "MindTerm - Currently Open Tunnels", false);
            final GridBagLayout layout = new GridBagLayout();
            final GridBagConstraints gridBagConstraints = new GridBagConstraints();
            this.ji.setLayout(layout);
            gridBagConstraints.fill = 0;
            gridBagConstraints.anchor = 17;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.insets = new Insets(10, 10, 0, 10);
            gridBagConstraints.gridwidth = 2;
            final Label label = new Label("Currently open tunnels:");
            layout.setConstraints(label, gridBagConstraints);
            this.ji.add(label);
            gridBagConstraints.fill = 1;
            gridBagConstraints.anchor = 17;
            gridBagConstraints.insets = new Insets(10, 10, 10, 10);
            gridBagConstraints.gridy = 1;
            gridBagConstraints.gridwidth = 10;
            layout.setConstraints(this.jh = new List(8), gridBagConstraints);
            this.ji.add(this.jh);
            final Panel panel = new Panel(new FlowLayout());
            final Button button;
            panel.add(button = new Button("Close Tunnel"));
            button.addActionListener(new a5(this));
            final Button button2;
            panel.add(button2 = new Button("Refresh"));
            button2.addActionListener(new a4(this));
            final Button button3;
            panel.add(button3 = new Button("Close Dialog"));
            button3.addActionListener(new f(this.ji));
            gridBagConstraints.gridy = 2;
            gridBagConstraints.gridwidth = 0;
            gridBagConstraints.anchor = 10;
            layout.setConstraints(panel, gridBagConstraints);
            this.ji.add(panel);
            this.ji.addWindowListener(new e(button3));
            a.b(this.ji);
            this.ji.setResizable(true);
            this.ji.pack();
        }
        this.iu();
        a.c(this.ji);
        this.jh.requestFocus();
        this.ji.setVisible(true);
    }
    
    public final void iu() {
        this.jh.removeAll();
        final String[] l1 = this.e9.fq.l1();
        for (int i = 0; i < l1.length; ++i) {
            this.jh.add(l1[i]);
        }
        if (l1.length > 0) {
            this.jh.select(0);
        }
    }
    
    public final void it() {
        if (this.je == null) {
            this.je = new Dialog(this.a, "MindTerm - Advanced Tunnels Setup", true);
            final GridBagLayout layout = new GridBagLayout();
            final GridBagConstraints gridBagConstraints = new GridBagConstraints();
            this.je.setLayout(layout);
            gridBagConstraints.fill = 1;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.weighty = 1.0;
            gridBagConstraints.anchor = 17;
            gridBagConstraints.insets = new Insets(4, 8, 4, 8);
            gridBagConstraints.gridy = 0;
            layout.setConstraints(this.jg = new a2(this, "Local: ([/plug/][<loc-host>]:<loc-port>:<rem-host>:<rem-port>)", new a3(this), new bj(this)), gridBagConstraints);
            this.je.add(this.jg);
            gridBagConstraints.gridy = 1;
            layout.setConstraints(this.jf = new a2(this, "Remote: ([/plug/]<rem-port>:<loc-host>:<loc-port>)", new bi(this), new bh(this)), gridBagConstraints);
            this.je.add(this.jf);
            final Button button = new Button("Close Dialog");
            button.addActionListener(new f(this.je));
            gridBagConstraints.gridy = 2;
            gridBagConstraints.anchor = 10;
            gridBagConstraints.fill = 0;
            gridBagConstraints.weighty = 0.0;
            layout.setConstraints(button, gridBagConstraints);
            this.je.add(button);
            this.je.addWindowListener(new e(button));
            a.b(this.je);
            this.je.setResizable(true);
            this.je.pack();
        }
        this.is();
        a.c(this.je);
        this.je.setVisible(true);
    }
    
    public final void is() {
        this.jg.removeAll();
        this.jf.removeAll();
        for (int i = 0; i < this.e9.lq.size(); ++i) {
            final b1 b1 = this.e9.lq.elementAt(i);
            this.jg.id(String.valueOf(b1.lb.equals("general") ? "" : new StringBuffer("/").append(b1.lb).append("/").toString()) + b1.lc + ":" + b1.fe + ":" + b1.fg + ":" + b1.ff);
        }
        for (int j = 0; j < this.e9.lp.size(); ++j) {
            final b0 b2 = this.e9.lp.elementAt(j);
            this.jf.id(String.valueOf(b2.lb.equals("general") ? "" : new StringBuffer("/").append(b2.lb).append("/").toString()) + b2.ff + ":" + b2.lc + ":" + b2.fe);
        }
    }
    
    public final void ir() {
        if (this.jd == null) {
            this.jd = new Dialog(this.a, "MindTerm - Connect", true);
            final GridBagLayout layout = new GridBagLayout();
            final GridBagConstraints gridBagConstraints = new GridBagConstraints();
            this.jd.setLayout(layout);
            gridBagConstraints.fill = 0;
            gridBagConstraints.anchor = 17;
            gridBagConstraints.gridwidth = 2;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.insets = new Insets(8, 8, 0, 8);
            final Label label = new Label("Available hosts/aliases:");
            layout.setConstraints(label, gridBagConstraints);
            this.jd.add(label);
            gridBagConstraints.gridy = 1;
            final Label label2 = new Label("(dir: " + this.e9.f.hc() + ")");
            layout.setConstraints(label2, gridBagConstraints);
            this.jd.add(label2);
            gridBagConstraints.fill = 1;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.weighty = 1.0;
            gridBagConstraints.anchor = 17;
            gridBagConstraints.insets = new Insets(8, 8, 8, 8);
            gridBagConstraints.gridy = 2;
            layout.setConstraints(this.jc = new List(8), gridBagConstraints);
            this.jd.add(this.jc);
            final bg bg;
            this.jc.addActionListener(bg = new bg(this));
            final Panel panel = new Panel(new FlowLayout());
            final Button button;
            panel.add(button = new Button("Connect"));
            button.addActionListener(bg);
            final Button button2;
            panel.add(button2 = new Button("New Server"));
            button2.addActionListener(new bf(this));
            final Button button3;
            panel.add(button3 = new Button("Cancel"));
            button3.addActionListener(new f(this.jd));
            gridBagConstraints.gridy = 4;
            gridBagConstraints.gridwidth = 0;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.anchor = 10;
            layout.setConstraints(panel, gridBagConstraints);
            this.jd.add(panel);
            this.jd.addWindowListener(new e(button3));
            a.b(this.jd);
            this.jd.setResizable(true);
            this.jd.pack();
        }
        this.jc.removeAll();
        final String[] gt = this.e9.f.gt();
        if (gt != null) {
            for (int i = 0; i < gt.length; ++i) {
                this.jc.add(gt[i]);
            }
        }
        this.jc.select(0);
        this.jd.pack();
        a.c(this.jd);
        this.jc.requestFocus();
        this.jd.setVisible(true);
        if (this.jb) {
            this.jb = false;
            this.ix();
        }
    }
    
    public final void iq() {
        if (this.ja == null) {
            this.ja = new FileDialog(this.a, "MindTerm - Select file to load settings from", 0);
        }
        this.ja.setDirectory(this.e9.f.hc());
        this.ja.setVisible(true);
        final String file = this.ja.getFile();
        final String directory = this.ja.getDirectory();
        if (file != null && file.length() > 0) {
            try {
                String ii = "";
                while (true) {
                    try {
                        this.e9.f.hf(ii);
                        this.e9.f.gx(String.valueOf(directory) + file, false);
                        this.e9.ko = true;
                        this.e9.kp.ez("Loaded new settings: " + file);
                    }
                    catch (b4 b4) {
                        if ((ii = this.ii("Please give password for " + file, "MindTerm - File Password")) != null) {
                            continue;
                        }
                    }
                    break;
                }
            }
            catch (Throwable t) {
                this.ij("Error loading settings: " + t.getMessage());
            }
        }
    }
    
    public final void ip() {
        if (this.i9 == null) {
            this.i9 = new FileDialog(this.a, "MindTerm - Select file to save settings to", 1);
        }
        this.i9.setDirectory(this.e9.f.hc());
        String s = this.e9.f.hm;
        if (s == null) {
            s = this.e9.f.ce("server");
        }
        this.i9.setFile(String.valueOf(s) + ".mtp");
        this.i9.setVisible(true);
        final String file = this.i9.getFile();
        final String directory = this.i9.getDirectory();
        if (file != null && file.length() > 0) {
            try {
                if (this.e9.f.hj) {
                    final String ih = this.ih("Please set password for " + file, "MindTerm - Set File Password");
                    if (ih == null) {
                        return;
                    }
                    this.e9.f.hf(ih);
                }
                this.e9.f.gy(String.valueOf(directory) + file);
            }
            catch (Throwable t) {
                this.ij("Error saving settings: " + t.getMessage());
            }
        }
    }
    
    public final void io() {
        if (this.i8 == null) {
            this.i8 = new FileDialog(this.a, "MindTerm - Select ASCII-file to send", 0);
        }
        this.i8.setVisible(true);
        final String file = this.i8.getFile();
        final String directory = this.i8.getDirectory();
        if (file != null && file.length() > 0) {
            try {
                final FileInputStream fileInputStream = new FileInputStream(String.valueOf(directory) + file);
                final byte[] array = new byte[fileInputStream.available()];
                fileInputStream.read(array);
                this.e9.kn(new String(array));
            }
            catch (Throwable t) {
                this.ij("Error sending file: " + t.getMessage());
            }
        }
    }
    
    public final boolean in() {
        if (this.i6 == null) {
            this.i6 = new FileDialog(this.a, "MindTerm - Select file to capture to", 1);
        }
        this.i6.setVisible(true);
        final String file = this.i6.getFile();
        final String directory = this.i6.getDirectory();
        if (file != null && file.length() > 0) {
            try {
                this.i7 = new b9(this.e9, new FileOutputStream(String.valueOf(directory) + file, true));
                return true;
            }
            catch (Throwable t) {
                this.ij("Error in capture: " + t.getMessage());
            }
        }
        return false;
    }
    
    public final void im() {
        if (this.i7 != null) {
            this.i7.im();
            this.i7 = null;
        }
    }
    
    public final void il() {
        if (this.i5 == null) {
            this.i5 = new Dialog(this.a, "MindTerm - RSA Key Generation", true);
            final GridBagLayout layout = new GridBagLayout();
            final GridBagConstraints gridBagConstraints = new GridBagConstraints();
            this.i5.setLayout(layout);
            gridBagConstraints.fill = 1;
            gridBagConstraints.anchor = 17;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.weighty = 1.0;
            gridBagConstraints.gridwidth = 12;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.insets = new Insets(8, 8, 8, 8);
            int n = 18;
            if (Toolkit.getDefaultToolkit().getScreenSize().height < 600) {
                n = 8;
            }
            (this.iz = new TextArea("This will create a RSA identity which can be used with the RSA authentication method. Your identity will consist of two parts: public and private keys. Your private key will be saved in the location which you specify; the corresponding public key is saved in a file with an identical name but with an extension of '.pub' added to it.\n\nYour private key is protected by encryption, if you enter a password. If you leave the password field blank, the key will not be encrypted. This should only be used in protected environments where unattended logins are desired. The contents of the 'comment' field are stored with your key, and displayed each time you are prompted for the key's password.\n\nThe key is generated using a random number generator, which is seeded by mouse movement in the field containing this text. Please move the mouse around in here until the progress bar below registers 100%.\n\nTo use the key, you must transfer the '.pub' public key file to an SSH server and add the contents of it to the file 'authorized_keys' in your ssh directory (e.g. ~/.ssh) on the server. For convenience, your public key is also copied to the clipboard.", n, 48, 1)).addMouseMotionListener(new be(this));
            layout.setConstraints(this.iz, gridBagConstraints);
            this.i5.add(this.iz);
            this.iz.setEditable(false);
            gridBagConstraints.gridy = 1;
            gridBagConstraints.fill = 0;
            gridBagConstraints.anchor = 10;
            layout.setConstraints(this.ix = new co(256, 150, 20), gridBagConstraints);
            this.i5.add(this.ix);
            gridBagConstraints.anchor = 17;
            gridBagConstraints.insets = new Insets(4, 4, 0, 0);
            gridBagConstraints.gridwidth = 4;
            gridBagConstraints.gridy = 2;
            gridBagConstraints.weightx = 0.0;
            final Label label = new Label("Keylength (bits):");
            layout.setConstraints(label, gridBagConstraints);
            this.i5.add(label);
            this.i3 = new TextField("", 5);
            gridBagConstraints.weightx = 1.0;
            layout.setConstraints(this.i3, gridBagConstraints);
            this.i5.add(this.i3);
            gridBagConstraints.gridy = 3;
            gridBagConstraints.fill = 2;
            gridBagConstraints.weightx = 0.0;
            final Label label2 = new Label("Identity file:");
            layout.setConstraints(label2, gridBagConstraints);
            this.i5.add(label2);
            this.i2 = new TextField("", 18);
            gridBagConstraints.weightx = 1.0;
            layout.setConstraints(this.i2, gridBagConstraints);
            this.i5.add(this.i2);
            final Button button = new Button("...");
            button.addActionListener(new bd(this));
            gridBagConstraints.fill = 0;
            layout.setConstraints(button, gridBagConstraints);
            this.i5.add(button);
            gridBagConstraints.gridy = 4;
            gridBagConstraints.fill = 2;
            gridBagConstraints.weightx = 0.0;
            final Label label3 = new Label("Password:");
            layout.setConstraints(label3, gridBagConstraints);
            this.i5.add(label3);
            (this.i1 = new TextField("", 18)).setEchoChar('*');
            gridBagConstraints.weightx = 1.0;
            layout.setConstraints(this.i1, gridBagConstraints);
            this.i5.add(this.i1);
            gridBagConstraints.gridy = 5;
            gridBagConstraints.weightx = 0.0;
            final Label label4 = new Label("Password again:");
            layout.setConstraints(label4, gridBagConstraints);
            this.i5.add(label4);
            (this.i0 = new TextField("", 18)).setEchoChar('*');
            gridBagConstraints.weightx = 1.0;
            layout.setConstraints(this.i0, gridBagConstraints);
            this.i5.add(this.i0);
            gridBagConstraints.gridy = 6;
            gridBagConstraints.weightx = 0.0;
            final Label label5 = new Label("Comment:");
            layout.setConstraints(label5, gridBagConstraints);
            this.i5.add(label5);
            this.i_ = new TextField("", 18);
            gridBagConstraints.weightx = 1.0;
            layout.setConstraints(this.i_, gridBagConstraints);
            this.i5.add(this.i_);
            gridBagConstraints.gridy = 7;
            gridBagConstraints.fill = 0;
            gridBagConstraints.gridwidth = 2;
            gridBagConstraints.insets = new Insets(8, 4, 0, 0);
            layout.setConstraints(this.iy = new Checkbox("Use key in current session"), gridBagConstraints);
            this.i5.add(this.iy);
            gridBagConstraints.gridy = 8;
            gridBagConstraints.fill = 2;
            gridBagConstraints.insets = new Insets(0, 0, 0, 0);
            gridBagConstraints.gridwidth = 4;
            this.iw = new Label("", 1);
            gridBagConstraints.fill = 2;
            gridBagConstraints.gridwidth = 0;
            gridBagConstraints.anchor = 10;
            layout.setConstraints(this.iw, gridBagConstraints);
            this.i5.add(this.iw);
            gridBagConstraints.gridy = 9;
            final Panel panel = new Panel(new FlowLayout());
            panel.add(this.iv = new Button("Generate"));
            this.iv.addActionListener(new bc(this));
            final Button button2;
            panel.add(button2 = new Button("Close"));
            button2.addActionListener(new bb(this));
            gridBagConstraints.gridwidth = 0;
            gridBagConstraints.anchor = 10;
            layout.setConstraints(panel, gridBagConstraints);
            this.i5.add(panel);
            this.i5.addWindowListener(new e(button2));
            a.b(this.i5);
            this.i5.setResizable(true);
            this.i5.pack();
        }
        this.iy.setState(true);
        this.iw.setText("");
        this.iv.setEnabled(false);
        this.i3.setText("1024");
        this.ix.n0(0);
        this.ik();
        a.c(this.i5);
        this.i3.requestFocus();
        this.i5.setVisible(true);
    }
    
    public final void ik() {
        try {
            File file;
            int n;
            for (file = new File(String.valueOf(this.e9.f.hc()) + "identity"), n = 0; file.exists(); file = new File(String.valueOf(this.e9.f.hc()) + "identity" + n), ++n) {}
            --n;
            this.i2.setText("identity" + ((n >= 0) ? String.valueOf(n) : ""));
        }
        catch (Throwable t) {}
    }
    
    public final void ij(final String s) {
        a1.h9("MindTerm - Alert", s, this.a);
    }
    
    public final String ii(final String s, final String s2) {
        return a1.h8(s2, s, this.a);
    }
    
    public final String ih(final String s, final String s2) {
        return a1.h7(s2, s, this.a);
    }
    
    public final boolean ig(final String s, final boolean b) {
        return a1.h6("MindTerm - Confirmation", s, b, this.a);
    }
    
    public final void if(final String s, final String s2, final int n, final int n2, final boolean b) {
        a1.h5(s, s2, n, n2, b, this.a);
    }
    
    public SSHMenuHandlerFull() {
        this.kf = "Copyright (c) 1998,99 by Mindbright Technology AB, Stockholm, Sweden.\n\nThis program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation; either version 2 of the License, or (at your option) any later version.\n\nThis program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.\n\n\tAuthor:\tMats Andersson (mats@mindbright.se)\n\tWeb:\thttp://www.mindbright.se/mindterm/\n\tInfo:\tmindterm@mindbright.se\n\tCVS $Name: rel1-2-1 $\n\tCVS $Date: 2000/08/01 22:30:52 $\nRunning on:\n\tJava vendor:\t" + MindTerm.ow + "\n" + "\tJava version:\t" + MindTerm.ox + "\n" + "\tOS name:\t" + MindTerm.ov + "\n" + "\tOS architecture:\t" + MindTerm.ou + "\n" + "\tOS version:\t" + MindTerm.ot + "\n";
        this.ka = 3;
        this.jk = false;
        this.jb = false;
        this.is = new byte[512];
    }
    
    static {
        kd = new String[][] { { "File", "New Terminal", "Clone Terminal", "Connect...", "Disconnect", null, "Load Settings...", "Save Settings", "Save Settings As...", null, "Create RSA Identity...", null, "SCP File Transfer...", "_Capture To File...", "Send ASCII File...", null, "Close", "Exit" }, { "Edit", "Copy Ctrl+Ins", "Paste Shift+Ins", "Copy & Paste", "Select All", "Find...", null, "Clear Screen", "Clear Scrollback", "VT Reset" }, { "Settings", "SSH Connection...", "Terminal...", "Terminal Misc...", "Proxy...", null, "Local Command-Shell", null, "_Auto Save Settings", "_Auto Load Settings", "_Save Passwords" }, { "Tunnels", "Basic...", "Advanced...", null, "Current Connections..." }, { "Help", "Help Topics...", "About MindTerm" } };
        kc = new int[][] { { -1, 78, 79, 67, -1, -1, -1, 83, -1, -1, -1, -1, -1, -1, -1, -1, 69, 88 }, { -1, -1, -1, -1, 65, 70, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 }, { -1, 72, 84, 77, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 }, { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 }, { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 } };
    }
}
