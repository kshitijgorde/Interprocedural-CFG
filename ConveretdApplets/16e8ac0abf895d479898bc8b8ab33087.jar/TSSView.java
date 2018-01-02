import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Insets;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.GridBagConstraints;
import java.awt.Color;
import java.awt.image.ImageObserver;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Panel;
import java.awt.Button;
import java.awt.Label;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class TSSView extends Applet
{
    private String c;
    public h a;
    private x a;
    public String a;
    public String b;
    private l[] a;
    private boolean a;
    private Label a;
    private Button a;
    private Button b;
    private Button c;
    private Label b;
    private Label c;
    private Panel a;
    private Button d;
    private Label d;
    private Button e;
    private Label e;
    private Button f;
    private Button g;
    private Button h;
    private Button i;
    private Button j;
    private Button k;
    private Button l;
    private Button m;
    private Button n;
    private Button o;
    private Button p;
    private Button q;
    
    public TSSView() {
        this.c = "";
        this.a = null;
        this.a = new l[20];
        this.a = false;
    }
    
    public void init() {
        final String host = this.getDocumentBase().getHost();
        System.err.println("version=1.0.2.5");
        System.err.println("owner=" + host);
        System.err.println("name=" + this.getName());
        if (!host.equals("") && !host.equals("127.0.0.1") && !host.equals("localhost") && !host.equals("www.test.tien.tv") && !host.equals("wm.test.tien.tv") && !host.equals("www.tien.tv") && !host.equals("wm.tien.tv") && !host.equals("www.teletekst.test.tien.tv") && !host.equals("teletekst.test.tien.tv") && !host.equals("www.teletekst.tien.tv") && !host.equals("teletekst.tien.tv") && !host.equals("192.168.100.102") && !host.equals("darwin.txt.prosiebensat1.net") && !host.equals("192.168.100.13") && !host.equals("ttviewer.71im.de") && !host.equals("www.kabeleins.de") && !host.equals("www.kabeleins.at") && !host.equals("www.kabeleins.ch") && !host.equals("www.prosieben.de") && !host.equals("www.prosieben.at") && !host.equals("www.prosieben.ch") && !host.equals("www.sat1.de") && !host.equals("ww.sat1.at") && !host.equals("www.sat1.ch") && !host.equals("www.n24.de") && !host.equals("www.n24.at") && !host.equals("www.n24.ch") && !host.equals("www.das-vierte.de") && !host.equals("webedit.71im.de") && !host.equals("datafeed.sbstext.nl") && !host.equals("www.sbstext.nl") && !host.equals("www.l1.nl") && !host.equals("l1.nl") && !host.equals("www.clubtxt.nl")) {
            System.err.println("Tss Viewer not correctly coded for this url. (" + host + ")");
            return;
        }
        this.a();
        String parameter;
        if ((parameter = this.getParameter("font")) == null) {
            parameter = "vtx15x18.gif";
        }
        System.err.println("font:" + parameter);
        this.c = this.getParameter("notifyurl");
        if (this.c == null) {
            this.c = "";
            System.err.println("not notifying");
        }
        final MediaTracker mediaTracker = new MediaTracker(this);
        final Image image = this.getImage(this.getCodeBase(), parameter);
        mediaTracker.addImage(image, 0);
        try {
            mediaTracker.waitForID(0);
        }
        catch (InterruptedException ex) {
            System.err.println("unable to load font:" + parameter + " " + ex.toString());
            return;
        }
        final int width = image.getWidth(this);
        final int height = image.getHeight(this);
        this.a = new h(image, width, height);
        this.b = this.getParameter("channel");
        this.a = this.getParameter("name");
        String parameter2 = this.getParameter("txtpage");
        String parameter3 = this.getParameter("txtsub");
        String parameter4 = this.getParameter("language");
        final String parameter5 = this.getParameter("pagenotfound");
        final String parameter6 = this.getParameter("addtimestamp");
        if (parameter4 == null) {
            parameter4 = "nl";
        }
        boolean b = false;
        if (parameter4.equalsIgnoreCase("nl")) {
            b = false;
        }
        else if (parameter4.equalsIgnoreCase("en")) {
            b = true;
        }
        if (parameter2 == null) {
            parameter2 = "100";
        }
        if (parameter3 == null) {
            parameter3 = "00";
        }
        if (this.b == null) {
            this.b = new String("tss");
        }
        System.err.println("Channel:" + this.b);
        System.err.println("Page:" + parameter2 + "s" + parameter3);
        if (this.b.equals("")) {
            System.out.println("must give at least one channel with the \"channels\" attribute");
            return;
        }
        if (this.a == null) {
            this.a = this.b;
        }
        String parameter7;
        if ((parameter7 = this.getParameter("bgcolor")) == null) {
            parameter7 = "999999";
        }
        final String substring = parameter7.substring(0, 2);
        final String substring2 = parameter7.substring(2, 4);
        final String substring3 = parameter7.substring(4);
        System.err.println("background r:" + substring + " g:" + substring2 + " b:" + substring3);
        final Integer value = Integer.valueOf(substring, 16);
        final Integer value2 = Integer.valueOf(substring2, 16);
        final Integer value3 = Integer.valueOf(substring3, 16);
        this.UpdateCurrentSubLabel(Integer.valueOf(parameter2), 0, 1);
        final Color foreground = new Color(value, value2, value3);
        this.setBackground(foreground);
        this.a.a = foreground;
        (this.a = new x(this, this.a, parameter2, parameter3, b ? 1 : 0)).setBackground(foreground);
        if (parameter5 != null) {
            this.a.a = parameter5;
        }
        if (parameter6 != null) {
            this.a.b = parameter6.toLowerCase().equals("true");
        }
        this.a.setSize(width / 32 * 40, height / 8 * 25);
        final GridBagConstraints gridBagConstraints;
        (gridBagConstraints = new GridBagConstraints()).gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 12;
        gridBagConstraints.gridheight = 100;
        gridBagConstraints.anchor = 18;
        gridBagConstraints.fill = 1;
        this.add(this.a, gridBagConstraints);
        String parameter8;
        if ((parameter8 = this.getParameter("showbuttons")) == null) {
            parameter8 = "true";
        }
        if (parameter8.compareTo("false") == 0) {
            this.q.setVisible(false);
            this.p.setVisible(false);
            this.o.setVisible(false);
            this.n.setVisible(false);
            this.m.setVisible(false);
            this.l.setVisible(false);
            this.j.setVisible(false);
            this.h.setVisible(false);
            this.g.setVisible(false);
            this.f.setVisible(false);
            this.k.setVisible(false);
            this.a.setVisible(false);
            this.i.setVisible(false);
            this.d.setVisible(false);
            this.b.setVisible(false);
            this.e.setVisible(false);
            this.c.setVisible(false);
            this.a.setVisible(false);
            this.d.setVisible(false);
            this.a.setVisible(false);
        }
        this.initButtons();
        this.e.setBackground(foreground);
        this.c.setBackground(foreground);
        this.b.setBackground(foreground);
        this.b.setForeground(foreground);
        this.a.a();
        this.a.requestFocus();
    }
    
    private void a() {
        this.q = new Button();
        this.p = new Button();
        this.o = new Button();
        this.n = new Button();
        this.m = new Button();
        this.l = new Button();
        this.j = new Button();
        this.h = new Button();
        this.g = new Button();
        this.f = new Button();
        this.b = new Button();
        this.e = new Button();
        this.k = new Button();
        this.d = new Button();
        this.i = new Button();
        this.a = new Button();
        this.a = new Label();
        this.d = new Label();
        this.c = new Button();
        this.a = new Panel();
        this.c = new Label();
        this.e = new Label();
        this.b = new Label();
        this.setLayout(new GridBagLayout());
        this.setBackground(Color.white);
        this.q.setLabel("0");
        this.q.addActionListener(new i(this));
        final GridBagConstraints gridBagConstraints;
        (gridBagConstraints = new GridBagConstraints()).gridx = 15;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = 1;
        this.add(this.q, gridBagConstraints);
        this.p.setLabel("1");
        this.p.addActionListener(new e(this));
        final GridBagConstraints gridBagConstraints2;
        (gridBagConstraints2 = new GridBagConstraints()).gridx = 14;
        gridBagConstraints2.gridy = 1;
        gridBagConstraints2.fill = 1;
        this.add(this.p, gridBagConstraints2);
        this.o.setLabel("2");
        this.o.addActionListener(new b(this));
        final GridBagConstraints gridBagConstraints3;
        (gridBagConstraints3 = new GridBagConstraints()).gridx = 15;
        gridBagConstraints3.gridy = 1;
        gridBagConstraints3.fill = 1;
        this.add(this.o, gridBagConstraints3);
        this.n.setLabel("3");
        this.n.addActionListener(new f(this));
        final GridBagConstraints gridBagConstraints4;
        (gridBagConstraints4 = new GridBagConstraints()).gridx = 16;
        gridBagConstraints4.gridy = 1;
        gridBagConstraints4.fill = 1;
        this.add(this.n, gridBagConstraints4);
        this.m.setLabel("4");
        this.m.addActionListener(new aa(this));
        final GridBagConstraints gridBagConstraints5;
        (gridBagConstraints5 = new GridBagConstraints()).gridx = 14;
        gridBagConstraints5.gridy = 2;
        gridBagConstraints5.fill = 1;
        this.add(this.m, gridBagConstraints5);
        this.l.setLabel("5");
        this.l.addActionListener(new m(this));
        final GridBagConstraints gridBagConstraints6;
        (gridBagConstraints6 = new GridBagConstraints()).gridx = 15;
        gridBagConstraints6.gridy = 2;
        gridBagConstraints6.fill = 1;
        this.add(this.l, gridBagConstraints6);
        this.j.setLabel("6");
        this.j.addActionListener(new r(this));
        final GridBagConstraints gridBagConstraints7;
        (gridBagConstraints7 = new GridBagConstraints()).gridx = 16;
        gridBagConstraints7.gridy = 2;
        gridBagConstraints7.fill = 1;
        this.add(this.j, gridBagConstraints7);
        this.h.setLabel("7");
        this.h.addActionListener(new v(this));
        final GridBagConstraints gridBagConstraints8;
        (gridBagConstraints8 = new GridBagConstraints()).gridx = 14;
        gridBagConstraints8.gridy = 3;
        gridBagConstraints8.fill = 1;
        this.add(this.h, gridBagConstraints8);
        this.g.setLabel("8");
        this.g.addActionListener(new j(this));
        final GridBagConstraints gridBagConstraints9;
        (gridBagConstraints9 = new GridBagConstraints()).gridx = 15;
        gridBagConstraints9.gridy = 3;
        gridBagConstraints9.fill = 1;
        this.add(this.g, gridBagConstraints9);
        this.f.setLabel("9");
        this.f.addActionListener(new p(this));
        final GridBagConstraints gridBagConstraints10;
        (gridBagConstraints10 = new GridBagConstraints()).gridx = 16;
        gridBagConstraints10.gridy = 3;
        gridBagConstraints10.fill = 1;
        this.add(this.f, gridBagConstraints10);
        this.b.setLabel("<<");
        this.b.addActionListener(new k(this));
        final GridBagConstraints gridBagConstraints11;
        (gridBagConstraints11 = new GridBagConstraints()).gridx = 14;
        gridBagConstraints11.gridy = 6;
        gridBagConstraints11.fill = 1;
        this.add(this.b, gridBagConstraints11);
        this.e.setLabel(">>");
        this.e.addActionListener(new w(this));
        final GridBagConstraints gridBagConstraints12;
        (gridBagConstraints12 = new GridBagConstraints()).gridx = 16;
        gridBagConstraints12.gridy = 6;
        gridBagConstraints12.fill = 1;
        this.add(this.e, gridBagConstraints12);
        this.k.setLabel("?");
        this.k.addActionListener(new q(this));
        final GridBagConstraints gridBagConstraints13;
        (gridBagConstraints13 = new GridBagConstraints()).gridx = 16;
        gridBagConstraints13.gridy = 4;
        gridBagConstraints13.fill = 1;
        this.add(this.k, gridBagConstraints13);
        this.d.setLabel("<");
        this.d.addActionListener(new n(this));
        final GridBagConstraints gridBagConstraints14;
        (gridBagConstraints14 = new GridBagConstraints()).gridx = 14;
        gridBagConstraints14.gridy = 5;
        gridBagConstraints14.fill = 1;
        this.add(this.d, gridBagConstraints14);
        this.i.setLabel(">");
        this.i.addActionListener(new ab(this));
        final GridBagConstraints gridBagConstraints15;
        (gridBagConstraints15 = new GridBagConstraints()).gridx = 16;
        gridBagConstraints15.gridy = 5;
        gridBagConstraints15.fill = 1;
        this.add(this.i, gridBagConstraints15);
        this.a.setLabel("Text");
        this.a.addActionListener(new g(this));
        final GridBagConstraints gridBagConstraints16;
        (gridBagConstraints16 = new GridBagConstraints()).gridx = 14;
        gridBagConstraints16.gridy = 4;
        gridBagConstraints16.fill = 1;
        this.add(this.a, gridBagConstraints16);
        this.a.setFont(new Font("Dialog", 1, 10));
        this.a.setBackground(Color.lightGray);
        this.a.setText("100");
        this.a.setAlignment(1);
        final GridBagConstraints gridBagConstraints17;
        (gridBagConstraints17 = new GridBagConstraints()).gridx = 15;
        gridBagConstraints17.gridy = 5;
        gridBagConstraints17.fill = 1;
        this.add(this.a, gridBagConstraints17);
        this.d.setFont(new Font("Dialog", 1, 10));
        this.d.setBackground(Color.lightGray);
        this.d.setText("0");
        this.d.setAlignment(1);
        final GridBagConstraints gridBagConstraints18;
        (gridBagConstraints18 = new GridBagConstraints()).gridx = 15;
        gridBagConstraints18.gridy = 6;
        gridBagConstraints18.fill = 1;
        this.add(this.d, gridBagConstraints18);
        this.c.setLabel("Auto refresh");
        this.c.setActionCommand("btnRefresh");
        this.c.addActionListener(new u(this));
        final GridBagConstraints gridBagConstraints19;
        (gridBagConstraints19 = new GridBagConstraints()).gridx = 14;
        gridBagConstraints19.gridy = 7;
        gridBagConstraints19.gridwidth = 3;
        gridBagConstraints19.fill = 1;
        this.add(this.c, gridBagConstraints19);
        this.a.setBackground(Color.lightGray);
        final GridBagConstraints gridBagConstraints20;
        (gridBagConstraints20 = new GridBagConstraints()).gridx = 13;
        gridBagConstraints20.gridy = 0;
        gridBagConstraints20.gridwidth = 10;
        gridBagConstraints20.gridheight = 7;
        gridBagConstraints20.fill = 1;
        gridBagConstraints20.insets = new Insets(1, 0, 2, 0);
        this.add(this.a, gridBagConstraints20);
        final GridBagConstraints gridBagConstraints21;
        (gridBagConstraints21 = new GridBagConstraints()).gridx = 12;
        gridBagConstraints21.gridy = 2;
        gridBagConstraints21.fill = 1;
        this.add(this.c, gridBagConstraints21);
        this.e.setFont(new Font("Arial", 1, 8));
        this.e.setText("Powered by TSS");
        final GridBagConstraints gridBagConstraints22;
        (gridBagConstraints22 = new GridBagConstraints()).gridx = 10;
        gridBagConstraints22.gridy = 101;
        this.add(this.e, gridBagConstraints22);
        final GridBagConstraints gridBagConstraints23;
        (gridBagConstraints23 = new GridBagConstraints()).gridx = 14;
        gridBagConstraints23.gridy = 8;
        gridBagConstraints23.gridwidth = 3;
        gridBagConstraints23.fill = 1;
        this.add(this.b, gridBagConstraints23);
    }
    
    private void b() {
        this.a = !this.a;
        if (this.a) {
            this.c.setLabel("no refresh");
            this.a.a = true;
        }
        else {
            this.c.setLabel("auto refresh");
            this.a.a = false;
        }
        this.a.requestFocus();
    }
    
    private void c() {
        if (!this.a.a()) {
            this.a.setLabel("Graphics");
        }
        else {
            this.a.setLabel("Text");
        }
        this.a.requestFocus();
    }
    
    private void d() {
        this.a.g();
        this.a.requestFocus();
    }
    
    private void e() {
        this.a.f();
        this.a.requestFocus();
    }
    
    private void f() {
        this.a.b();
        this.a.requestFocus();
    }
    
    private void g() {
        this.a.e();
        this.a.requestFocus();
    }
    
    private void h() {
        this.a.d();
        this.a.requestFocus();
    }
    
    private void i() {
        this.a.b(57);
        this.a.requestFocus();
    }
    
    private void j() {
        this.a.b(56);
        this.a.requestFocus();
    }
    
    private void k() {
        this.a.b(55);
        this.a.requestFocus();
    }
    
    private void l() {
        this.a.b(54);
        this.a.requestFocus();
    }
    
    private void m() {
        this.a.b(52);
        this.a.requestFocus();
    }
    
    private void n() {
        this.a.b(51);
        this.a.requestFocus();
    }
    
    private void o() {
        this.a.b(50);
        this.a.requestFocus();
    }
    
    private void p() {
        this.a.b(49);
        this.a.requestFocus();
    }
    
    private void q() {
        this.a.b(53);
        this.a.requestFocus();
    }
    
    private void r() {
        this.a.b(48);
        this.a.requestFocus();
    }
    
    public String currentChannelName() {
        String a = null;
        try {
            a = this.a;
        }
        catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println("index out of bounds in currentChannel");
        }
        return a;
    }
    
    public String currentChannelDir() {
        String b = null;
        try {
            b = this.b;
        }
        catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println("index out of bounds in currentChannel");
        }
        return b;
    }
    
    public void UpdateCurrentSubLabel(final int n, final int n2, final int n3) {
        this.a.setText(String.valueOf(n));
        this.d.setText(String.valueOf(String.valueOf(n2 + 1)) + "/" + String.valueOf(n3));
    }
    
    public void UpdatePrevNext(final int n, final int n2) {
        String string = "<";
        if (n >= 100 && n <= 899) {
            string = "<" + String.valueOf(n);
        }
        String string2 = ">";
        if (n2 >= 100 && n2 <= 899) {
            string2 = String.valueOf(String.valueOf(n2)) + ">";
        }
        this.d.setLabel(string);
        this.i.setLabel(string2);
    }
    
    public void initButtons() {
        URL url;
        try {
            url = new URL(this.getCodeBase(), String.valueOf(this.currentChannelDir()) + "/buttons.txt");
        }
        catch (MalformedURLException ex) {
            System.err.println("excep url:");
            return;
        }
        System.err.println(url);
        try {
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
            int n = 9;
            int n2 = 0;
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.length() >= 5) {
                    final String substring = line.substring(0, 3);
                    final String substring2 = line.substring(3);
                    if (substring.equals("chn")) {
                        final int index = line.indexOf(61, 3);
                        final int index2 = line.indexOf(32, index);
                        final String substring3 = line.substring(1 + index, index2);
                        final int index3 = line.indexOf(61, index2);
                        final int index4 = line.indexOf(32, index3);
                        final String substring4 = line.substring(1 + index3, index4);
                        final String substring5 = line.substring(line.indexOf(61, index4) + 1);
                        if (this.a[n2] == null) {
                            this.a[n2] = new l(this.getCodeBase(), substring5);
                        }
                        System.err.println("Channel:" + substring3 + " name:" + substring4 + " url:" + substring5);
                        this.a[n2].a = substring3;
                        this.a[n2].b = substring4;
                        this.a[n2].a = 0;
                    }
                    else {
                        final Integer value = Integer.valueOf(substring);
                        if (this.a[n2] == null) {
                            this.a[n2] = new l(this.getCodeBase(), substring2);
                        }
                        this.a[n2].a = value;
                        this.a[n2].a = this.b;
                        this.a[n2].b = this.a;
                    }
                    this.a(n2, 13, n);
                    ++n;
                    ++n2;
                }
            }
        }
        catch (FileNotFoundException ex2) {
            System.err.println("buttons.txt not found. proceeding..");
        }
        catch (IOException ex3) {
            System.err.println("buttons.txt not found. proceeding..");
        }
        catch (Exception ex4) {
            System.err.println("buttons.txt not found. proceeding..");
        }
    }
    
    private void a(final int n, final int gridx, final int gridy) {
        if (this.a[n] == null) {
            System.err.println("button:" + n + "=null");
            return;
        }
        this.a[n].b = this.a[n].b;
        final GridBagConstraints gridBagConstraints;
        (gridBagConstraints = new GridBagConstraints()).gridx = gridx;
        gridBagConstraints.gridy = gridy;
        gridBagConstraints.gridwidth = 13;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.fill = 1;
        this.add(this.a[n], gridBagConstraints);
    }
    
    public void OnImageButtonClicked(final Object o) {
        final l l;
        if ((l = (l)o) != null) {
            final int a;
            if ((a = l.a) >= 100 && a <= 899) {
                if (this.a.a(a)) {
                    this.a.repaint();
                }
            }
            else {
                final Integer value = Integer.valueOf(this.getParameter("txtpage"));
                this.b = l.a;
                this.a = l.b;
                System.err.println("dir:" + this.b + " name:" + this.a);
                final boolean a2;
                if (a2 = this.a.a((int)value)) {
                    this.a.repaint();
                }
            }
        }
        this.a.requestFocus();
    }
    
    public void OnUpdateAutoRefreshButton(final int n) {
        if (this.a) {
            String label = "";
            switch (n) {
                case 10: {
                    label = "-------------------";
                    break;
                }
                case 9: {
                    label = " ----------------- ";
                    break;
                }
                case 8: {
                    label = "  ---------------  ";
                    break;
                }
                case 7: {
                    label = "   -------------   ";
                    break;
                }
                case 6: {
                    label = "    -----------    ";
                    break;
                }
                case 5: {
                    label = "     ---------     ";
                    break;
                }
                case 4: {
                    label = "      -------      ";
                    break;
                }
                case 3: {
                    label = "       -----       ";
                    break;
                }
                case 2: {
                    label = "        ---        ";
                    break;
                }
                case 1: {
                    label = "         -         ";
                    break;
                }
            }
            this.c.setLabel(label);
        }
    }
    
    public void OnNumberClicked(final int n) {
        switch (n) {
            case 0: {
                this.a.b(48);
                break;
            }
            case 9: {
                this.a.b(57);
                break;
            }
            case 8: {
                this.a.b(56);
                break;
            }
            case 7: {
                this.a.b(55);
                break;
            }
            case 6: {
                this.a.b(54);
                break;
            }
            case 5: {
                this.a.b(53);
                break;
            }
            case 4: {
                this.a.b(52);
                break;
            }
            case 3: {
                this.a.b(51);
                break;
            }
            case 2: {
                this.a.b(50);
                break;
            }
            case 1: {
                this.a.b(49);
                break;
            }
        }
        this.a.requestFocus();
    }
    
    public void SwitchViewMethod() {
        this.a.a();
        this.a.requestFocus();
    }
    
    public void SwitchConcealMethod() {
        this.a.b();
        this.a.requestFocus();
    }
    
    public void SetPageNumber(final int n, final int n2) {
        this.a.a(n, n2);
        this.a.requestFocus();
    }
    
    public int GetPageNumber() {
        return this.a.a;
    }
    
    public int GetSubPageNumber() {
        return this.a.b;
    }
    
    public int GetSubPageCount() {
        return this.a.c;
    }
    
    public int GetPreviousPageNr() {
        return this.a.d;
    }
    
    public int GetNextPageNr() {
        return this.a.e;
    }
    
    public void SetChannelName(final String a) {
        this.a = a;
    }
    
    public void SetChannel(final String b) {
        this.b = b;
        final Integer value = Integer.valueOf(this.getParameter("txtpage"));
        System.err.println("dir:" + this.b + " name:" + this.a);
        if (this.a.a((int)value)) {
            this.a.repaint();
        }
    }
    
    public void SwitchAutoRefresh() {
        this.a = !this.a;
        if (this.a) {
            this.c.setLabel("no refresh");
            this.a.a = true;
        }
        else {
            this.c.setLabel("auto refresh");
            this.a.a = false;
        }
        this.a.requestFocus();
    }
    
    public String NotifyUrl() {
        return this.c;
    }
    
    public static void a(final TSSView tssView, final ActionEvent actionEvent) {
        tssView.r();
    }
    
    public static void b(final TSSView tssView, final ActionEvent actionEvent) {
        tssView.p();
    }
    
    public static void c(final TSSView tssView, final ActionEvent actionEvent) {
        tssView.o();
    }
    
    public static void d(final TSSView tssView, final ActionEvent actionEvent) {
        tssView.n();
    }
    
    public static void e(final TSSView tssView, final ActionEvent actionEvent) {
        tssView.m();
    }
    
    public static void f(final TSSView tssView, final ActionEvent actionEvent) {
        tssView.q();
    }
    
    public static void g(final TSSView tssView, final ActionEvent actionEvent) {
        tssView.l();
    }
    
    public static void h(final TSSView tssView, final ActionEvent actionEvent) {
        tssView.k();
    }
    
    public static void i(final TSSView tssView, final ActionEvent actionEvent) {
        tssView.j();
    }
    
    public static void j(final TSSView tssView, final ActionEvent actionEvent) {
        tssView.i();
    }
    
    public static void k(final TSSView tssView, final ActionEvent actionEvent) {
        tssView.h();
    }
    
    public static void l(final TSSView tssView, final ActionEvent actionEvent) {
        tssView.g();
    }
    
    public static void m(final TSSView tssView, final ActionEvent actionEvent) {
        tssView.f();
    }
    
    public static void n(final TSSView tssView, final ActionEvent actionEvent) {
        tssView.e();
    }
    
    public static void o(final TSSView tssView, final ActionEvent actionEvent) {
        tssView.d();
    }
    
    public static void p(final TSSView tssView, final ActionEvent actionEvent) {
        tssView.c();
    }
    
    public static void q(final TSSView tssView, final ActionEvent actionEvent) {
        tssView.b();
    }
}
