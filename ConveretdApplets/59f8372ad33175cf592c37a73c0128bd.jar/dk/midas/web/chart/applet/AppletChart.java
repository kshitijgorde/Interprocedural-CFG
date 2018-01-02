// 
// Decompiled by Procyon v0.5.30
// 

package dk.midas.web.chart.applet;

import dk.midas.web.chart.applet.d.e;
import java.awt.Event;
import java.awt.AWTEvent;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.awt.Canvas;
import java.awt.FlowLayout;
import java.awt.event.ItemListener;
import java.awt.MenuItem;
import java.awt.Menu;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.StringTokenizer;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import dk.midas.web.chart.applet.d.c;
import java.awt.Image;
import dk.midas.web.chart.applet.c.a;
import dk.midas.web.chart.applet.d.d;
import java.awt.event.ActionListener;
import java.applet.Applet;

public class AppletChart extends Applet implements ActionListener, aq, d
{
    private static final String gl = "Verdana;11;PLAIN";
    private static final String gr = "Verdana;10;PLAIN";
    private static final String gh = "http://www.lycos.com";
    private static final int f3 = 50;
    ChartManager f7;
    a gt;
    private a1 f9;
    private ad gi;
    private m[] gs;
    private an f4;
    private g gu;
    private a5 gd;
    private au gc;
    private t gn;
    private Image[] gg;
    private String gp;
    private String f8;
    private int gq;
    private bi gk;
    private l gm;
    public boolean f6;
    private boolean ge;
    public int gb;
    public String gj;
    private int f5;
    protected boolean gf;
    private c go;
    private int ga;
    private int gv;
    
    public AppletChart() {
        this.gt = null;
        this.gq = 0;
        this.f6 = false;
        this.ge = true;
        this.gb = 50;
        this.gj = null;
        this.f5 = 3000;
        this.gf = false;
    }
    
    public void setApplication() {
        this.gf = true;
    }
    
    public void readProperties(final String s) {
        this.go = new dk.midas.web.chart.applet.d.a(s);
    }
    
    public void init() {
        this.f6 = false;
        if (!this.getHostInfo().int()) {
            this.gp = "Invalid HTML host.";
        }
        this.f5 = this.getHostInfo().byte();
        this.setLayout(new BorderLayout());
        this.gp = this.getProperties().a("AppletDetachedText", this.getVersion());
        this.f8 = this.getProperties().a("DetachWindowTitle", this.getVersion());
        this.f7 = new ChartManager();
        this.ap();
        if (this.av()) {
            this.aq();
        }
        if (this.as()) {
            this.au();
        }
        this.add("Center", this.f7);
        this.f7.eF = this.getProperties().a("LinkIcon", "");
        this.f7.eA = this.getProperties().a("LinkIconURL", "");
        this.f7.eT = this.getProperties().a("LinkTarget", "_blank");
        this.f7.V();
        this.validate();
    }
    
    public Dimension getMinimumSize() {
        return new Dimension(250, 350);
    }
    
    public void setInitialInstrument(final a gt) {
        this.gt = gt;
    }
    
    private void ap() {
        this.setBackground(this.getColorSettings().gY);
        this.setForeground(this.getColorSettings().gW);
        final int width = this.getSize().width;
        a0.f = this.getProperties().do("AxisFonts", "Verdana;10;PLAIN");
        final Graphics graphics = this.getGraphics();
        graphics.setFont(a0.f);
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        graphics.dispose();
        this.ga = fontMetrics.getHeight();
        this.gv = fontMetrics.stringWidth("4.44444");
        int n;
        if (this.getHostInfo().a()) {
            n = this.getProperties().if("AnalyseHeight", 150);
            this.gb = n;
        }
        else {
            n = this.getProperties().if("AnalyseHeight", 0);
            this.gb = n;
        }
        final String parameter = this.getProperties().getParameter("User");
        if (parameter != null) {
            bh.if = parameter;
        }
        bh.for = this.getProperties().if("RGId", -1);
        bh.do = this.getProperties().if("CandlestickScaling", 7);
        long n2 = this.getProperties().a("Points", 0);
        final int a = this.getProperties().a("LeadingSpace", 20);
        final int a2 = this.getProperties().a("ChartType", 0);
        this.ge = false;
        long n3 = 0L;
        switch (a2) {
            case 1:
            case 8: {
                n3 = width;
                break;
            }
            case 6: {
                n3 = width / bh.do;
                break;
            }
            default: {
                n3 = width / 3;
                break;
            }
        }
        if (n2 == 0L) {
            n2 = n3;
            this.ge = true;
        }
        this.f7.for(this.ge);
        this.f7.n(a);
        final dk.midas.web.chart.applet.a a3 = new dk.midas.web.chart.applet.a(this);
        a3.for(1);
        final a a4 = dk.midas.web.chart.applet.c.a.a(this.getProperties());
        final dk.midas.web.chart.applet.b.a a5 = dk.midas.web.chart.applet.b.a.a(this.getProperties());
        final DataSource dataSource = new DataSource(a3, a4, a5, n2);
        dataSource.if(this);
        dataSource.do(n3);
        int a6 = this.getProperties().a("ChartType", 0);
        if (a5.do()) {
            a6 = 1;
        }
        int n4 = 0;
        int n5 = 0;
        final s s = new s();
        dk.midas.web.chart.applet.s.if = n;
        for (int i = 0; i < 9; ++i) {
            final int if1 = this.getProperties().if(i + 1);
            if (if1 != -1) {
                final Study do1 = dk.midas.web.chart.applet.o.do(if1);
                if (do1 != null && do1.for()) {
                    ++n5;
                    n4 += n;
                }
            }
        }
        int height = this.getSize().height;
        final int n6 = 45;
        if (this.gd != null) {
            height -= this.getMenuSettings().int();
        }
        final int n7 = height - this.getFooterSettings().new();
        if (n4 > n7 * n6 / 100) {
            n = n7 * n6 / 100 * n5;
        }
        if (n < 50) {
            n = 50;
        }
        dk.midas.web.chart.applet.s.if = n;
        int n8 = 0;
        for (int j = 0; j < 9; ++j) {
            final int if2 = this.getProperties().if(j);
            if (if2 != -1) {
                final Study do2 = dk.midas.web.chart.applet.o.do(if2);
                if (do2 != null && do2.for()) {
                    final Analyse analyse = (Analyse)do2.a(this.f7, dataSource);
                    analyse.setSize(width, n);
                    if (analyse != null) {
                        analyse.a(this.getProperties().a(j));
                        analyse.a(this.getColorSettings());
                        n8 += analyse.char();
                        s.a(analyse);
                    }
                }
            }
        }
        if (dk.midas.web.chart.applet.s.if == 0) {
            dk.midas.web.chart.applet.s.if = 60;
        }
        int n9 = 0;
        for (int k = 1; k < 5; ++k) {
            if (!this.getProperties().a("SecondInstr" + k, "").equals("")) {
                n9 = 1;
            }
        }
        be.ea = ((this.gv + 10 > be.ea) ? this.gv : be.ea);
        be.d4 = 2 * this.ga + 10;
        be.ei = (1 + n9) * this.ga + 4;
        if (n7 - n8 < 50) {}
        final be be = new be(this.f7, dataSource, this.getProperties().a("Normalized", false), this.getProperties().a("Performance", false));
        be.setSize(width, n7);
        final ChartBody a7 = dk.midas.web.chart.applet.h.a(a6, dataSource, be);
        be.a(this.getColorSettings());
        be.dO.if[0] = this.getColorSettings().g9;
        be.dO.a(a7);
        for (int l = 1; l < 5; ++l) {
            String if3 = a4.if();
            String s2 = a4.do();
            final String a8 = this.getProperties().a("SecondInstr" + l, "");
            if (!a8.equals("")) {
                final StringTokenizer stringTokenizer = new StringTokenizer(a8, ";");
                String nextToken = "0";
                if (stringTokenizer.hasMoreTokens()) {
                    if3 = (s2 = stringTokenizer.nextToken());
                }
                if (stringTokenizer.hasMoreTokens()) {
                    s2 = stringTokenizer.nextToken();
                }
                if (stringTokenizer.hasMoreTokens()) {
                    nextToken = stringTokenizer.nextToken();
                }
                if (stringTokenizer.hasMoreTokens()) {
                    be.dO.if[l] = try(stringTokenizer.nextToken());
                }
                final a a9 = new a(s2, if3, a4.int());
                final dk.midas.web.chart.applet.a a10 = new dk.midas.web.chart.applet.a(this);
                final DataSource dataSource2 = new DataSource(a10, a9, a5, n2);
                dataSource2.if(this);
                a10.for(1);
                dataSource2.do(n3);
                final ChartBody a11 = dk.midas.web.chart.applet.h.a(a6, dataSource2, be);
                be.dO.a(a11);
                a11.fn = nextToken.equals("1");
            }
        }
        dk.midas.web.chart.applet.aj.r = this.getColorSettings().gX;
        dk.midas.web.chart.applet.be.ej = this.getProperties().a("LoadText", "Loading Data. Please wait");
        final StringTokenizer stringTokenizer2 = new StringTokenizer(this.getProperties().a("delayInfoStrings", "REAL-TIME data;Delayed;min."), ";");
        if (stringTokenizer2.hasMoreTokens()) {
            dk.midas.web.chart.applet.be.el = stringTokenizer2.nextToken();
        }
        if (stringTokenizer2.hasMoreTokens()) {
            dk.midas.web.chart.applet.be.dW = stringTokenizer2.nextToken();
        }
        if (stringTokenizer2.hasMoreTokens()) {
            dk.midas.web.chart.applet.be.em = stringTokenizer2.nextToken();
        }
        String a12 = this.getProperties().a("HelpAddress", "http://www.lycos.com");
        if (a12.startsWith("javascript:")) {
            a12 = a12.substring(11);
            a8.do = true;
        }
        else {
            a8.do = false;
        }
        a8.a = a12;
        this.an();
        this.a(be);
        final ap ed = new ap();
        ed.do(this.getProperties().getParameter("Initialize_0"));
        for (int n10 = 0; n10 < 9; ++n10) {
            final int if4 = this.getProperties().if(n10);
            if (if4 != -1) {
                final Study do3 = dk.midas.web.chart.applet.o.do(if4);
                if (do3 != null && do3.do()) {
                    final CompositeAnalysis compositeAnalysis = (CompositeAnalysis)do3.a(a7);
                    if (compositeAnalysis != null) {
                        compositeAnalysis.a(this.getProperties().a(n10));
                        ed.a(compositeAnalysis);
                    }
                }
            }
        }
        be.ed = ed;
        this.f7.a(be, s);
        this.f7.eH = a6;
        this.f7.int(dataSource);
        this.f7.eJ = (int)n2;
        this.f7.setBackground(this.getColorSettings().gY);
        this.f7.setForeground(this.getColorSettings().gW);
        final String parameter2 = this.getProperties().getParameter("Decimals");
        if (parameter2 != null) {
            this.f7.eZ.a(Integer.parseInt(parameter2));
            this.f7.eZ.for = this.f7.eZ.do;
        }
        a8.if = this.getProperties().a("SaveLines", false);
        if (a8.if) {
            this.if(be);
        }
    }
    
    private void if(final be be) {
        int n = 0;
        double doubleValue = 0.0;
        double doubleValue2 = 0.0;
        float floatValue = 0.0f;
        float floatValue2 = 0.0f;
        int intValue = -1;
        String if1;
        while ((if1 = this.getProperties().if("Line_" + n, null)) != null) {
            final StringTokenizer stringTokenizer = new StringTokenizer(if1, ";");
            if (stringTokenizer.hasMoreTokens()) {
                doubleValue = Double.valueOf(stringTokenizer.nextToken());
            }
            if (stringTokenizer.hasMoreTokens()) {
                floatValue = Float.valueOf(stringTokenizer.nextToken());
            }
            if (stringTokenizer.hasMoreTokens()) {
                doubleValue2 = Double.valueOf(stringTokenizer.nextToken());
            }
            if (stringTokenizer.hasMoreTokens()) {
                floatValue2 = Float.valueOf(stringTokenizer.nextToken());
            }
            if (stringTokenizer.hasMoreTokens()) {
                intValue = Integer.valueOf(stringTokenizer.nextToken());
            }
            if (intValue != -1) {
                be.null().bO.a(new aj(doubleValue, floatValue, doubleValue2, floatValue2, intValue));
            }
            ++n;
        }
    }
    
    private void an() {
        a0.void = this.getProperties().a("Bid_Str", "Bid");
        a0.for = this.getProperties().a("Ask_Str", "Ask");
        final String a = this.getProperties().a("Dialog_Information", "ID:;Date:;Time (GMT):;Tick Price:;Open Price:;Close Price:;High Price:;Low Price:");
        if (a != null) {
            final StringTokenizer stringTokenizer = new StringTokenizer(a, ";");
            if (stringTokenizer.hasMoreTokens()) {
                a0.goto = stringTokenizer.nextToken();
            }
            if (stringTokenizer.hasMoreTokens()) {
                a0.j = stringTokenizer.nextToken();
            }
            if (stringTokenizer.hasMoreTokens()) {
                a0.case = stringTokenizer.nextToken();
            }
            if (stringTokenizer.hasMoreTokens()) {
                a0.null = stringTokenizer.nextToken();
            }
            if (stringTokenizer.hasMoreTokens()) {
                a0.try = stringTokenizer.nextToken();
            }
            if (stringTokenizer.hasMoreTokens()) {
                a0.new = stringTokenizer.nextToken();
            }
            if (stringTokenizer.hasMoreTokens()) {
                a0.a = stringTokenizer.nextToken();
            }
            if (stringTokenizer.hasMoreTokens()) {
                a0.i = stringTokenizer.nextToken();
            }
        }
        a0.if = this.getProperties().do("MenuFonts", "Verdana;11;PLAIN");
        final String a2 = this.getProperties().a("Buttons_Ok_Cancel", "OK;Cancel");
        if (a2 != null) {
            final StringTokenizer stringTokenizer2 = new StringTokenizer(a2, ";");
            if (stringTokenizer2.hasMoreTokens()) {
                ba.for = stringTokenizer2.nextToken();
                a3.a = ba.for;
            }
            if (stringTokenizer2.hasMoreTokens()) {
                ba.int = stringTokenizer2.nextToken();
            }
        }
        final String a3 = this.getProperties().a("Dialog_View_Period", "Input Period;Periods:;Leading space:");
        if (a3 != null) {
            final StringTokenizer stringTokenizer3 = new StringTokenizer(a3, ";");
            if (stringTokenizer3.hasMoreTokens()) {
                bd.goto = stringTokenizer3.nextToken();
            }
            if (stringTokenizer3.hasMoreTokens()) {
                bd.else = stringTokenizer3.nextToken();
            }
            if (stringTokenizer3.hasMoreTokens()) {
                bd.long = stringTokenizer3.nextToken();
            }
        }
        final String a4 = this.getProperties().a("Dialog_Study_Period", "Input for [?];Period:");
        if (a4 != null) {
            final StringTokenizer stringTokenizer4 = new StringTokenizer(a4, ";");
            if (stringTokenizer4.hasMoreTokens()) {
                dk.midas.web.chart.applet.ag.void = stringTokenizer4.nextToken();
            }
            if (stringTokenizer4.hasMoreTokens()) {
                dk.midas.web.chart.applet.ag.null = stringTokenizer4.nextToken();
            }
        }
    }
    
    private void a(final be be) {
        int n;
        if (this.getHostInfo().a()) {
            n = this.getProperties().if("ZoomMode", 1);
        }
        else {
            n = this.getProperties().if("ZoomMode", 0);
        }
        if (n == 1) {
            final String a = this.getProperties().a("ZoomFillColors", "000000,000000,C0C0C0");
            final Color[] g8 = new Color[3];
            final StringTokenizer stringTokenizer = new StringTokenizer(a, ",");
            g8[0] = try(stringTokenizer.nextToken());
            g8[1] = try(stringTokenizer.nextToken());
            g8[2] = try(stringTokenizer.nextToken());
            this.getColorSettings().g8 = g8;
        }
        else if (this.getHostInfo().a()) {
            this.getColorSettings().gC = this.getProperties().for("ZoomArrowColor", "000000");
        }
        else {
            this.getColorSettings().gC = this.getProperties().for("ZoomArrowColor", "000000");
        }
    }
    
    static final Color try(final String s) {
        return new Color(Integer.decode("0x" + s.substring(0, 2)), Integer.decode("0x" + s.substring(2, 4)), Integer.decode("0x" + s.substring(4, 6)));
    }
    
    public void start() {
        if (this.getProperties().a("autoStart", true)) {
            this.manualStart();
        }
        if (this.getProperties().a("persistInitialValues", false)) {
            this.f7.S();
        }
    }
    
    int ao() {
        return this.f5;
    }
    
    public void manualStart() {
        this.f6 = true;
        this.f7.eS.dO.else();
    }
    
    public void stop() {
        this.byte(false);
        if (this.f7 != null) {
            this.f7.eS.dO.for();
        }
        this.f6 = false;
    }
    
    public void destroy() {
        if (this.f7 != null) {
            this.f7.eS.dO.for();
        }
        System.gc();
    }
    
    public String[][] getParameterInfo() {
        return new String[][] { { "background", "String", "Background color, format \"rrggbb\"" }, { "foreground", "String", "Foreground color, format \"rrggbb\"" }, { "author", "String", "Mirel Cosulschi" } };
    }
    
    public String getAppletInfo() {
        return this.getVersion();
    }
    
    private void if(final Menu menu, final String s) {
        if (menu != null) {
            this.do(menu, s);
        }
    }
    
    private void a(final Object o, final String s, final String s2, final int n) {
        a5 a5 = null;
        ar ar = null;
        Menu a6 = null;
        boolean b = false;
        StringTokenizer stringTokenizer = null;
        final String s3 = "|";
        final String a7;
        if ((a7 = this.getProperties().a(s2 + "_*", null)) != null) {
            stringTokenizer = new StringTokenizer(a7, s3);
            b = true;
        }
        int n2 = 1;
        while (true) {
            String a8;
            if (b) {
                a8 = null;
                if (stringTokenizer != null && stringTokenizer.hasMoreElements()) {
                    a8 = (String)stringTokenizer.nextElement();
                }
            }
            else {
                a8 = this.getProperties().a(s2 + "_" + n2, null);
            }
            if (a8 == null) {
                break;
            }
            if (n2 == 1) {
                if (n == 0) {
                    a5 = (a5)o;
                    ar = new ar(s, this.z(this.gq++), this.getProperties().a(s2 + "_Tip", null), a5.d);
                    a6 = ar.a();
                }
                else {
                    a6 = new Menu(s);
                    ((Menu)o).add(a6);
                }
            }
            final StringTokenizer stringTokenizer2 = new StringTokenizer(a8, ";");
            final String nextToken = stringTokenizer2.nextToken();
            if (nextToken.compareTo("POPUP") == 0) {
                this.a(a6, stringTokenizer2.nextToken(), stringTokenizer2.nextToken(), n + 1);
            }
            else if (nextToken.compareTo("-") == 0) {
                if (n != 0) {
                    a6.addSeparator();
                }
            }
            else {
                String actionCommand = stringTokenizer2.nextToken();
                final int int1 = Integer.parseInt(actionCommand);
                if (int1 < 0) {
                    final int n3 = -int1;
                    final ai ai = new ai(nextToken);
                    ai.addItemListener(this.f7);
                    ai.setActionCommand("" + n3);
                    a6.add(ai);
                    if (n3 / 100 == 2) {
                        dk.midas.web.chart.applet.b.a.a(n3, new Long(stringTokenizer2.nextToken()), nextToken);
                    }
                }
                else {
                    final MenuItem menuItem = new MenuItem(nextToken);
                    menuItem.setFont(a0.if);
                    if (stringTokenizer2.hasMoreElements()) {
                        if (actionCommand.compareTo("800") == 0) {
                            actionCommand = actionCommand.concat(";" + nextToken);
                        }
                        while (stringTokenizer2.hasMoreElements()) {
                            actionCommand = actionCommand.concat(";" + stringTokenizer2.nextToken());
                        }
                    }
                    menuItem.setActionCommand(actionCommand);
                    if (actionCommand.compareTo("602") == 0) {
                        menuItem.setEnabled(false);
                    }
                    if (actionCommand.compareTo("603") == 0) {
                        menuItem.setEnabled(false);
                    }
                    menuItem.addActionListener(this.f7);
                    a6.add(menuItem);
                }
            }
            ++n2;
        }
        if (n == 0) {
            a5.a(ar);
        }
        else {
            this.if(a6, s2);
        }
    }
    
    private void do(final Menu menu, final String s) {
        final ap ed = this.f7.eS.ed;
        for (int i = 0; i < ed.if(); ++i) {
            final CompositeAnalysis if1 = ed.if(i);
            if (if1.f().int().equals(s)) {
                this.a(menu, if1);
            }
        }
        final s er = this.f7.eR;
        for (int j = 0; j < er.if(); ++j) {
            final Analyse a = er.a(j);
            if (a.f().int().equals(s)) {
                this.a(menu, a);
            }
        }
    }
    
    private void a(final Menu menu, final q q) {
        if (q != null) {
            final ai ai = new ai(q.g());
            ai.addItemListener(this.f7);
            ai.setActionCommand("-1");
            menu.add(ai);
            ai.a(true);
        }
    }
    
    public void dataSourceChanged(final a2 a2) {
        if (a2.try() != this.f7.W()) {
            return;
        }
        if (a2.new()) {
            this.animate(null);
            dk.midas.web.chart.applet.j.a = (System.currentTimeMillis() - dk.midas.web.chart.applet.j.a) / 1000L;
        }
        if (this.gd != null) {
            this.gd.a("602", a2.try().y().do());
            this.gd.a("603", a2.try().y().do());
        }
    }
    
    public DataSource getSource() {
        return this.f7.W();
    }
    
    private boolean as() {
        if (!this.getFooterSettings().bA) {
            return false;
        }
        (this.gn = new t(this.getFooterSettings().bs, this.getFooterSettings().by)).setSize(this.getSize().width, this.getFooterSettings().new());
        this.add("South", this.gn);
        return true;
    }
    
    private void au() {
        this.f7.eU = this.gn;
        int ar = this.ar();
        final boolean a = this.getProperties().a("UseStatusPanel", true);
        if (a) {
            if (this.getHostInfo().a()) {
                this.f9 = new a1(this.getProperties().a("StatusPanelDefaultText", "Saxo Bank Datacentre"), this.getProperties().if("StatusPanelTipTimeOut", 3000));
            }
            else {
                this.f9 = new a1(this.getProperties().a("StatusPanelDefaultText", this.getVersion()), this.getProperties().if("StatusPanelTipTimeOut", 3000));
            }
            this.f9.setFont(this.getProperties().do("StatusPanelFonts", "Dialog;11;PLAIN"));
        }
        if (this.getProperties().a("UseWaitPanel", true)) {
            final String a2 = this.getProperties().a("WaitPanelImage", "wait.gif");
            final int if1 = this.getProperties().if("WaitPanelDelay", 35);
            final int if2 = this.getProperties().if("WaitPanelWidth", 80);
            (this.gi = new ad(this, a2)).setSize(if2, this.getFooterSettings().new());
            this.gi.a(if1);
            if (a) {
                ar += if2;
            }
        }
        final int bv = this.getFooterSettings().bv;
        final int n = ar + bv;
        if (n > 0) {
            final ac n2 = new ac();
            n2.setLayout(new FlowLayout(0, 0, 0));
            n2.setSize(n, this.getFooterSettings().new());
            this.gn.add("West", n2);
            this.gn.n = n2;
            if (bv > 0) {
                final Canvas canvas = new Canvas();
                canvas.setSize(bv, 0);
                n2.add(canvas);
            }
            for (int i = 0; i < this.gs.length; ++i) {
                n2.add(this.gs[i]);
            }
            this.gn.o = this.gs;
            n2.add(this.gi);
            this.gn.p = this.gi;
        }
        if (a) {
            this.gn.add("Center", this.f9);
            this.gn.q = this.f9;
        }
    }
    
    private int ar() {
        int n = 0;
        this.gs = new m[this.getFooterSettings().bz.length];
        for (int i = 0; i < this.gs.length; ++i) {
            final StringTokenizer stringTokenizer = new StringTokenizer(this.getFooterSettings().bz[i], ",");
            final String nextToken = stringTokenizer.nextToken();
            final int int1 = Integer.parseInt(stringTokenizer.nextToken());
            final int int2 = Integer.parseInt(stringTokenizer.nextToken());
            final int int3 = Integer.parseInt(stringTokenizer.nextToken());
            final String nextToken2 = stringTokenizer.nextToken();
            final String nextToken3 = stringTokenizer.nextToken();
            (this.gs[i] = new m(this.getImage(this.getCodeBase(), nextToken), int1, int2, this, this.getFooterSettings().bx[i], nextToken2.equalsIgnoreCase("true"))).setSize(int1 + 2 * int3, this.getFooterSettings().new());
            this.gs[i].if(nextToken3);
            n += int1 + 2 * int3;
        }
        return n;
    }
    
    private boolean av() {
        if (!this.getMenuSettings().be) {
            return false;
        }
        (this.gd = new a5(this.getMenuSettings(), this)).setSize(this.getSize().width, this.getMenuSettings().bk);
        this.add("North", this.gd);
        return true;
    }
    
    private void aq() {
        this.gg = this.at();
        final int bq = this.getMenuSettings().bq;
        if (bq > 0) {
            final ar ar = new ar("Space", null, null, false);
            ar.setSize(bq, 0);
            this.gd.add(ar);
        }
        final String bl = this.getMenuSettings().bl;
        if (bl == null) {
            return;
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(bl, ";");
        while (stringTokenizer.hasMoreElements()) {
            this.a(this.gd, stringTokenizer.nextToken(), stringTokenizer.nextToken(), 0);
        }
        final String bd = this.getMenuSettings().bd;
        if (bd != null) {
            final StringTokenizer stringTokenizer2 = new StringTokenizer(bd, ";");
            while (stringTokenizer2.hasMoreElements()) {
                this.gd.a(stringTokenizer2.nextToken(), false);
            }
        }
        this.f7.eC = this.gd;
    }
    
    private Image[] at() {
        final String bp = this.getMenuSettings().bp;
        Image[] array;
        if (bp != null) {
            final StringTokenizer stringTokenizer = new StringTokenizer(bp, ";");
            array = new Image[stringTokenizer.countTokens()];
            final URL codeBase = this.getCodeBase();
            for (int i = 0; i < array.length; ++i) {
                array[i] = this.getImage(codeBase, stringTokenizer.nextToken());
            }
        }
        else {
            array = new Image[0];
        }
        return array;
    }
    
    private Image z(final int n) {
        if (n > -1 && n < this.gg.length) {
            return this.gg[n];
        }
        return null;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final StringTokenizer stringTokenizer = new StringTokenizer(actionEvent.getActionCommand(), ";");
        final int int1 = Integer.parseInt(stringTokenizer.nextToken());
        if (int1 == 0) {
            return;
        }
        if (int1 == 900) {
            this.byte(this.f4 == null || !this.f4.isVisible());
        }
        else if (int1 == 1000) {
            if (this.f9 != null) {
                this.f9.a(stringTokenizer.nextToken());
            }
        }
        else if (int1 < 0) {
            this.f7.new(new Integer(-int1).toString());
        }
        else {
            this.f7.actionPerformed(actionEvent);
        }
    }
    
    private void byte(final boolean b) {
        try {
            if (b) {
                if (this.f4 == null) {
                    (this.f4 = new an(this, this.f8)).setMinimumSize(new Dimension(250, 150));
                    this.f4.setLayout(new BorderLayout());
                    this.f4.setBackground(this.getBackground());
                    final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                    final int width = this.getSize().width;
                    final int height = this.getSize().height;
                    this.f4.setBounds((screenSize.width - width) / 2, (screenSize.height - height) / 2, width, height);
                }
                if (this.gd != null) {
                    this.f4.add("North", this.gd);
                }
                this.f4.add("Center", this.f7);
                if (this.gn != null) {
                    this.f4.add("South", this.gn);
                }
                this.f4.validate();
                this.f4.setVisible(true);
                this.f7.T();
            }
            else if (this.f4 != null) {
                if (this.gd != null) {
                    this.add("North", this.gd);
                }
                this.add("Center", this.f7);
                if (this.gn != null) {
                    this.add("South", this.gn);
                }
                this.validate();
                this.f7.T();
                for (int i = 0; i < this.gs.length; ++i) {
                    if (this.gs[i].a().startsWith("900")) {
                        this.gs[i].a(false);
                    }
                }
                this.f4.dispose();
                this.f4 = null;
            }
        }
        finally {}
    }
    
    public void processEvent(final AWTEvent awtEvent) {
        if (awtEvent.getSource() instanceof an && awtEvent.getID() == 201) {
            this.byte(false);
            return;
        }
        super.processEvent(awtEvent);
    }
    
    public boolean handleEvent(final Event event) {
        if (event.target instanceof an && event.id == 201) {
            this.byte(false);
            return true;
        }
        return super.handleEvent(event);
    }
    
    public String getVersion() {
        return "FinanceChart 1.1";
    }
    
    public void paint(final Graphics graphics) {
        graphics.setColor(this.getForeground());
        if (this.gp != null) {
            graphics.drawString(this.gp, 10, 20);
        }
    }
    
    public void showStatus(final String s) {
        if (s != null && this.f9 != null) {
            this.f9.if(s);
        }
    }
    
    public void animate(final String s) {
        if (s != null) {
            if (this.gi != null && this.f9 != null) {
                this.gi.do();
            }
        }
        else if (this.gi != null) {
            if (this.f9 != null) {
                this.f9.new();
            }
            this.gi.a();
        }
    }
    
    public String getPersistStr() {
        return this.getProperties().a();
    }
    
    public c getProperties() {
        if (this.go == null) {
            this.go = (this.gf ? new dk.midas.web.chart.applet.d.a() : new e(this));
        }
        return this.go;
    }
    
    public g getMenuSettings() {
        if (this.gu == null) {
            this.gu = dk.midas.web.chart.applet.g.a(this.getHostInfo().a(), this.getProperties());
        }
        return this.gu;
    }
    
    public bi getColorSettings() {
        if (this.gk == null) {
            this.gk = bi.do(this.getHostInfo().a(), this.getProperties());
        }
        return this.gk;
    }
    
    public au getFooterSettings() {
        if (this.gc == null) {
            this.gc = dk.midas.web.chart.applet.au.if(this.getHostInfo().a(), this.getProperties());
        }
        return this.gc;
    }
    
    public l getHostInfo() {
        if (this.gm == null) {
            this.gm = new l(this);
        }
        return this.gm;
    }
}
