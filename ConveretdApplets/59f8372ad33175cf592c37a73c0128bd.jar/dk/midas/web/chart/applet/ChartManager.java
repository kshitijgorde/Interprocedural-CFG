// 
// Decompiled by Procyon v0.5.30
// 

package dk.midas.web.chart.applet;

import java.awt.Rectangle;
import java.awt.MenuComponent;
import java.awt.ItemSelectable;
import java.awt.event.ItemEvent;
import dk.midas.web.chart.applet.c.a;
import java.awt.Cursor;
import java.net.MalformedURLException;
import java.awt.event.ActionEvent;
import java.awt.MenuItem;
import java.awt.Menu;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.net.URL;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Container;
import java.text.MessageFormat;
import java.util.StringTokenizer;
import java.awt.Frame;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import netscape.javascript.JSObject;
import java.applet.Applet;
import java.awt.Dimension;
import dk.midas.web.chart.applet.d.d;
import java.awt.event.ItemListener;
import java.awt.event.ActionListener;

public class ChartManager extends b implements ActionListener, aq, ItemListener, d
{
    public static final int eB = 20;
    a5 eC;
    t eU;
    s eR;
    static final int e0 = 6;
    public int eH;
    be eS;
    private DataSource eM;
    ChartBody eV;
    private bd eQ;
    private ax e3;
    private a3 eD;
    private a0 ey;
    private az e2;
    private az e5;
    private ag eK;
    Dimension eX;
    boolean e1;
    protected int eJ;
    protected boolean eO;
    protected boolean eW;
    public boolean eN;
    public aa eZ;
    private int eI;
    public String eF;
    public String eA;
    public String eT;
    public bf eP;
    public boolean eE;
    private a7 ez;
    private int eL;
    private boolean eY;
    private Applet eG;
    private JSObject e4;
    
    ChartManager() {
        this.eC = null;
        this.eU = null;
        this.eQ = null;
        this.e3 = null;
        this.eD = null;
        this.ey = null;
        this.e2 = null;
        this.e5 = null;
        this.eK = null;
        this.e1 = true;
        this.eJ = 300;
        this.eO = false;
        this.eW = true;
        this.eN = true;
        this.eZ = new aa();
        this.eI = 50;
        this.eF = "";
        this.eA = "";
        this.eT = "_blank";
        this.eP = null;
        this.eE = true;
        this.ez = new a7("ChartManagerLock");
        this.eL = 20;
        this.eY = false;
        this.eS = null;
    }
    
    ChartManager(final be be, final s s) {
        this.eC = null;
        this.eU = null;
        this.eQ = null;
        this.e3 = null;
        this.eD = null;
        this.ey = null;
        this.e2 = null;
        this.e5 = null;
        this.eK = null;
        this.e1 = true;
        this.eJ = 300;
        this.eO = false;
        this.eW = true;
        this.eN = true;
        this.eZ = new aa();
        this.eI = 50;
        this.eF = "";
        this.eA = "";
        this.eT = "_blank";
        this.eP = null;
        this.eE = true;
        this.ez = new a7("ChartManagerLock");
        this.eL = 20;
        this.eY = false;
        this.a(be, s);
    }
    
    public void a(final be es, final s er) {
        this.eS = es;
        this.eR = er;
        this.eV = es.dO.int();
        final FlowLayout layout = new FlowLayout();
        layout.setVgap(1);
        this.setLayout(layout);
        this.if(es);
        es.parent = this;
        for (int i = 0; i < er.if(); ++i) {
            final Analyse a = er.a(i);
            if (a != null) {
                this.if(a);
                a.parent = this;
            }
        }
        this.eX = this.getSize();
    }
    
    public void S() {
        this.if(this.W(), this.eH);
    }
    
    protected void T() {
        Container container;
        for (container = this.getParent(); !(container instanceof Frame); container = ((Frame)container).getParent()) {}
        final Frame frame = (Frame)container;
        (this.eQ = new bd(frame, new ak[] { new ak(bd.else, "Integer", 0.0f, 1200.0f, this.eJ), new ak(bd.long, "Integer", 0.0f, 1200.0f, 20.0f) }, new ba.a() {
            public boolean a(final Object[] array) {
                return (int)array[0] <= 1 || (int)array[0] > (int)array[1];
            }
        })).pack();
        this.eQ.setVisible(false);
        final ag ag = new ag(frame, new ak[] { new ak(dk.midas.web.chart.applet.ag.null, "Integer", 1.0f, 1024.0f, 10.0f) });
        ag.pack();
        ag.setVisible(false);
        dk.midas.web.chart.applet.o.a(0, ag);
        dk.midas.web.chart.applet.o.a(11, ag);
        dk.midas.web.chart.applet.o.a(21, ag);
        dk.midas.web.chart.applet.o.a(31, ag);
        dk.midas.web.chart.applet.o.a(41, ag);
        dk.midas.web.chart.applet.o.a(44, ag);
        dk.midas.web.chart.applet.o.a(45, ag);
        dk.midas.web.chart.applet.o.a(46, ag);
        final ak[] array = new ak[3];
        final AppletChart appletChart = (AppletChart)this.eG;
        String nextToken = "Long ({0}) :";
        String nextToken2 = "Short ({0}) :";
        String nextToken3 = "Trigger ({0}) :";
        String nextToken4 = "Values < 1.0 are percentages, values > 1 are periods";
        final StringTokenizer stringTokenizer = new StringTokenizer(appletChart.getProperties().a("Dialog_MACD", nextToken + ";" + nextToken2 + ";" + nextToken3 + ";" + nextToken4), ";");
        final Object[] array2 = { "0.0-250" };
        if (stringTokenizer.hasMoreTokens()) {
            nextToken = stringTokenizer.nextToken();
        }
        if (stringTokenizer.hasMoreTokens()) {
            nextToken2 = stringTokenizer.nextToken();
        }
        if (stringTokenizer.hasMoreTokens()) {
            nextToken3 = stringTokenizer.nextToken();
        }
        if (stringTokenizer.hasMoreTokens()) {
            nextToken4 = stringTokenizer.nextToken();
        }
        array[0] = new ak(MessageFormat.format(nextToken, array2), "Float", 0.0f, 250.0f, 26.0f);
        array[1] = new ak(MessageFormat.format(nextToken2, array2), "Float", 0.0f, 250.0f, 12.0f);
        array[2] = new ak(MessageFormat.format(nextToken3, array2), "Float", 0.0f, 250.0f, 9.0f);
        final ag ag2 = new ag(frame, array, nextToken4);
        ag2.pack();
        ag2.setVisible(false);
        dk.midas.web.chart.applet.o.a(51, ag2);
        array2[0] = "2-250";
        String nextToken5 = "Long Average ({0}):";
        String nextToken6 = "Short Average ({0}):";
        final StringTokenizer stringTokenizer2 = new StringTokenizer(appletChart.getProperties().a("Dialog_OSC", nextToken5 + ";" + nextToken6), ";");
        if (stringTokenizer2.hasMoreElements()) {
            nextToken5 = stringTokenizer2.nextToken();
        }
        if (stringTokenizer2.hasMoreElements()) {
            nextToken6 = stringTokenizer2.nextToken();
        }
        final ag ag3 = new ag(frame, new ak[] { new ak(MessageFormat.format(nextToken5, array2), "Integer", 2.0f, 250.0f, 28.0f), new ak(MessageFormat.format(nextToken6, array2), "Integer", 1.0f, 250.0f, 14.0f) }, new ba.a() {
            public boolean a(final Object[] array) {
                return (int)array[0] >= (int)array[1];
            }
        });
        ag3.pack();
        ag3.setVisible(false);
        dk.midas.web.chart.applet.o.a(61, ag3);
        array2[0] = "1-250";
        String nextToken7 = "Observation period ({0}):";
        String nextToken8 = "Averaging period Slow ({0}):";
        String nextToken9 = "Averaging period Fast ({0}):";
        final StringTokenizer stringTokenizer3 = new StringTokenizer(appletChart.getProperties().a("Dialog_PKS", nextToken7 + ";" + nextToken8 + ";" + nextToken9), ";");
        if (stringTokenizer3.hasMoreElements()) {
            nextToken7 = stringTokenizer3.nextToken();
        }
        if (stringTokenizer3.hasMoreElements()) {
            nextToken8 = stringTokenizer3.nextToken();
        }
        if (stringTokenizer3.hasMoreElements()) {
            nextToken9 = stringTokenizer3.nextToken();
        }
        final ag ag4 = new ag(frame, new ak[] { new ak(MessageFormat.format(nextToken7, array2), "Integer", 1.0f, 250.0f, 5.0f), new ak(MessageFormat.format(nextToken8, array2), "Integer", 1.0f, 250.0f, 5.0f), new ak(MessageFormat.format(nextToken9, array2), "Integer", 1.0f, 250.0f, 5.0f) });
        ag4.pack();
        ag4.setVisible(false);
        dk.midas.web.chart.applet.o.a(71, ag4);
        array2[0] = "0.0 -250.0";
        final ag ag5 = new ag(frame, new ak[] { new ak(MessageFormat.format(appletChart.getProperties().a("Dialog_MAE", "Smoothing factor or period ({0}):"), array2), "Float", 0.0f, 250.0f, 5.0f) });
        ag5.pack();
        ag5.setVisible(false);
        dk.midas.web.chart.applet.o.a(81, ag5);
        String nextToken10 = "Average ({0}):";
        String nextToken11 = "#Standard deviations ({0}):";
        final StringTokenizer stringTokenizer4 = new StringTokenizer(appletChart.getProperties().a("Dialog_BOLL", nextToken10 + ";" + nextToken11), ";");
        if (stringTokenizer4.hasMoreElements()) {
            nextToken10 = stringTokenizer4.nextToken();
        }
        if (stringTokenizer4.hasMoreElements()) {
            nextToken11 = stringTokenizer4.nextToken();
        }
        final ak[] array3 = new ak[2];
        array2[0] = "2 - 250";
        array3[0] = new ak(MessageFormat.format(nextToken10, array2), "Integer", 2.0f, 250.0f, 20.0f);
        array2[0] = "0.0 - 10.0";
        array3[1] = new ak(MessageFormat.format(nextToken11, array2), "Float", 0.0f, 10.0f, 2.0f);
        final ag ag6 = new ag(frame, array3);
        ag6.pack();
        ag6.setVisible(false);
        dk.midas.web.chart.applet.o.a(91, ag6);
        array2[0] = "1-250";
        String nextToken12 = "Long ({0}) :";
        String nextToken13 = "Short ({0}) :";
        String nextToken14 = "Trigger ({0}) :";
        final StringTokenizer stringTokenizer5 = new StringTokenizer(appletChart.getProperties().a("Dialog_MACD_P", nextToken12 + ";" + nextToken13 + ";" + nextToken14), ";");
        if (stringTokenizer5.hasMoreElements()) {
            nextToken12 = stringTokenizer5.nextToken();
        }
        if (stringTokenizer5.hasMoreElements()) {
            nextToken13 = stringTokenizer5.nextToken();
        }
        if (stringTokenizer5.hasMoreElements()) {
            nextToken14 = stringTokenizer5.nextToken();
        }
        final ag ag7 = new ag(frame, new ak[] { new ak(MessageFormat.format(nextToken12, array2), "Integer", 1.0f, 250.0f, 26.0f), new ak(MessageFormat.format(nextToken13, array2), "Integer", 1.0f, 250.0f, 12.0f), new ak(MessageFormat.format(nextToken14, array2), "Integer", 1.0f, 250.0f, 9.0f) });
        ag7.pack();
        ag7.setVisible(false);
        dk.midas.web.chart.applet.o.a(52, ag7);
        final String s = "Period ({0}):";
        array2[0] = "1 - 250";
        final ag ag8 = new ag(frame, new ak[] { new ak(MessageFormat.format(appletChart.getProperties().a("Dialog_MAE_P", s), array2), "Integer", 1.0f, 250.0f, 3.0f) });
        ag8.pack();
        ag8.setVisible(false);
        dk.midas.web.chart.applet.o.a(82, ag8);
        array2[0] = "0.0 - 0.2";
        String nextToken15 = "Start Factor ({0}):";
        String nextToken16 = "Acceleration Step ({0}):";
        String nextToken17 = "Acceleration Limit ({0}):";
        final StringTokenizer stringTokenizer6 = new StringTokenizer(appletChart.getProperties().a("Dialog_PSAR", nextToken15 + ";" + nextToken16 + ";" + nextToken17), ";");
        if (stringTokenizer6.hasMoreElements()) {
            nextToken15 = stringTokenizer6.nextToken();
        }
        if (stringTokenizer6.hasMoreElements()) {
            nextToken16 = stringTokenizer6.nextToken();
        }
        if (stringTokenizer6.hasMoreElements()) {
            nextToken17 = stringTokenizer6.nextToken();
        }
        final ag ag9 = new ag(frame, new ak[] { new ak(MessageFormat.format(nextToken15, array2), "Float", 0.0f, 0.2f, 0.02f), new ak(MessageFormat.format(nextToken16, array2), "Float", 0.0f, 0.2f, 0.02f), new ak(MessageFormat.format(nextToken17, array2), "Float", 0.0f, 0.2f, 0.2f) });
        ag9.pack();
        ag9.setVisible(false);
        dk.midas.web.chart.applet.o.a(92, ag9);
        String nextToken18 = "Percentage ({0}):";
        String nextToken19 = "Averaging period ({0}):";
        final StringTokenizer stringTokenizer7 = new StringTokenizer(appletChart.getProperties().a("Dialog_RNG", nextToken18 + ";" + nextToken19), ";");
        if (stringTokenizer7.hasMoreElements()) {
            nextToken18 = stringTokenizer7.nextToken();
        }
        if (stringTokenizer7.hasMoreElements()) {
            nextToken19 = stringTokenizer7.nextToken();
        }
        final ak[] array4 = new ak[2];
        array2[0] = "0.0 - 50.0";
        array4[0] = new ak(MessageFormat.format(nextToken18, array2), "Float", 0.0f, 50.0f, 1.0f);
        array2[0] = "2 - 250";
        array4[1] = new ak(MessageFormat.format(nextToken19, array2), "Integer", 2.0f, 250.0f, 20.0f);
        final ag ag10 = new ag(frame, array4);
        ag10.pack();
        ag10.setVisible(false);
        dk.midas.web.chart.applet.o.a(93, ag10);
        final ag ag11 = new ag(frame, new ak[] { new ak(dk.midas.web.chart.applet.ag.null, "Integer", 1.0f, 1024.0f, 14.0f) });
        ag11.pack();
        ag11.setVisible(false);
        dk.midas.web.chart.applet.o.a(42, ag11);
        dk.midas.web.chart.applet.o.a(47, ag11);
        String nextToken20 = "Box size / max price percent ({0}):";
        String nextToken21 = "Reverse amount ({0}):";
        final StringTokenizer stringTokenizer8 = new StringTokenizer(appletChart.getProperties().a("Dialog_PF", nextToken20 + ";" + nextToken21), ";");
        if (stringTokenizer8.hasMoreElements()) {
            nextToken20 = stringTokenizer8.nextToken();
        }
        if (stringTokenizer8.hasMoreElements()) {
            nextToken21 = stringTokenizer8.nextToken();
        }
        final ak[] array5 = new ak[2];
        array2[0] = "0.0001 - 1.0";
        array5[0] = new ak(MessageFormat.format(nextToken20, array2), "Float", 1.0E-4f, 1.0f, 0.001f);
        array2[0] = "1 - 1000";
        array5[1] = new ak(MessageFormat.format(nextToken21, array2), "Integer", 1.0f, 1000.0f, 3.0f);
        final ag ag12 = new ag(frame, array5);
        ag12.pack();
        ag12.setVisible(false);
        dk.midas.web.chart.applet.o.a(43, ag12);
        (this.ey = new k(frame, appletChart.getProperties().a("DataInfoTitle", "Information"), this)).setVisible(false);
        String nextToken22 = "Fibonacci";
        String nextToken23 = "Retracement percentages";
        final StringTokenizer stringTokenizer9 = new StringTokenizer(appletChart.getProperties().a("Dialog_Fib", nextToken22 + ";" + nextToken23), ";");
        if (stringTokenizer9.hasMoreElements()) {
            nextToken22 = stringTokenizer9.nextToken();
        }
        if (stringTokenizer9.hasMoreElements()) {
            nextToken23 = stringTokenizer9.nextToken();
        }
        (this.e2 = new az(frame, nextToken22, nextToken23, 1)).pack();
        this.e2.setVisible(false);
        String nextToken24 = "Support/Resistance";
        String nextToken25 = "Retracement percentages";
        final StringTokenizer stringTokenizer10 = new StringTokenizer(appletChart.getProperties().a("Dialog_Res", nextToken24 + ";" + nextToken25), ";");
        if (stringTokenizer10.hasMoreElements()) {
            nextToken24 = stringTokenizer10.nextToken();
        }
        if (stringTokenizer10.hasMoreElements()) {
            nextToken25 = stringTokenizer10.nextToken();
        }
        (this.e5 = new az(frame, nextToken24, nextToken25, 2)).pack();
        this.e5.setVisible(false);
        String nextToken26 = "Cell size:";
        String nextToken27 = "Multiplier:";
        final StringTokenizer stringTokenizer11 = new StringTokenizer(appletChart.getProperties().a("Dialog_Gann", nextToken26 + ";" + nextToken27), ";");
        if (stringTokenizer11.hasMoreElements()) {
            nextToken26 = stringTokenizer11.nextToken();
        }
        if (stringTokenizer11.hasMoreElements()) {
            nextToken27 = stringTokenizer11.nextToken();
        }
        (this.eK = new ag(frame, new ak[] { new dk.midas.web.chart.applet.d(nextToken26, dk.midas.web.chart.applet.ao.s, dk.midas.web.chart.applet.ao.v), new ak(nextToken27, "Integer", 10.0f, 100.0f, 10.0f) })).pack();
        this.eK.setVisible(false);
        (this.e3 = new ax("Line Information", this.eZ)).setSize(500, 400);
        this.e3.pack();
        this.e3.setVisible(false);
        (this.eD = new a3(frame, "Chart Applet v" + bh.a / 10 + "." + bh.a % 10 + "\nProvided by Saxo Bank (c) 2002\nData Provider : " + bh.int, 30)).pack();
        this.eD.setVisible(false);
        this.eS.dT = this.ey;
    }
    
    public void V() {
        Container parent;
        for (parent = this; parent != null && !(parent instanceof Applet); parent = parent.getParent()) {}
        if (parent != null) {
            this.eG = (Applet)parent;
        }
        if (this.eG != null && !((AppletChart)this.eG).gf) {
            try {
                this.e4 = JSObject.getWindow(this.eG);
            }
            catch (Exception ex) {
                System.out.println(ex);
            }
        }
        this.if("" + (100 + this.eH), true);
        this.if("" + this.W().C().a(), true);
        this.if("303", true);
        if (!this.eF.equals("")) {
            this.eP = new bf(this.eS, this.eF, this.eA, this.eT, this.eG);
        }
    }
    
    public Insets getInsets() {
        return new Insets(1, 1, 1, 1);
    }
    
    public void paint(final Graphics graphics) {
        this.ez.do();
        try {
            if (this.N()) {
                this.aa();
            }
            super.paint(graphics);
        }
        finally {
            this.ez.a();
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public boolean X() {
        return this.eP != null;
    }
    
    public void b(final Graphics graphics) {
        final String string = this.eG.getCodeBase().toString();
        final String substring = string.substring(0, string.lastIndexOf("/"));
        URL url = null;
        try {
            url = new URL(substring + "/SAXO_LOGO.gif");
        }
        catch (Exception ex) {
            System.out.println("Couldn't create URL for " + url + "  " + ex);
        }
        if (url != null) {
            final Image image = this.eG.getImage(url);
            graphics.drawImage(image, this.eX.getSize().width - image.getWidth(this), this.eX.getSize().height - (image.getHeight(this) + 5), this);
        }
    }
    
    public void a(final av av, final int n, final int n2, final boolean b) {
        final av[] m = this.M();
        if (this.eS.d8 == 1) {
            if (this.e1 || b) {
                for (int i = 0; i < m.length; ++i) {
                    final av av2 = m[i];
                    av2.bR.x = n;
                    if (av2 != av) {
                        av2.bR.y = -1;
                    }
                    if (b) {
                        av2.bR.x = -1;
                        av2.bR.y = -1;
                    }
                    av2.repaint();
                }
            }
        }
        else if (this.eS.d8 == 8) {
            if (this.eO && !b) {
                return;
            }
            for (int j = 0; j < m.length; ++j) {
                final av av3 = m[j];
                av3.bR.x = n;
                if (j == 0) {
                    ((be)av3).dS = true;
                }
                else {
                    av3.bR.y = -1;
                }
                av3.repaint();
            }
        }
        else {
            av.repaint();
        }
    }
    
    private String a(final Object o, final String s) {
        final Menu a = this.a(o);
        for (int i = 0; i < a.getItemCount(); ++i) {
            final MenuItem item = a.getItem(i);
            if (item.getActionCommand().compareTo(s) == 0) {
                return item.getLabel();
            }
        }
        return null;
    }
    
    private Menu a(final Object o) {
        Object parent;
        for (parent = o; !(parent instanceof Menu); parent = ((MenuItem)parent).getParent()) {}
        return (Menu)parent;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (this.eQ == null) {
            this.T();
        }
        if (this.ey.isVisible()) {
            this.ey.setVisible(false);
        }
        if (this.e3.isVisible()) {
            this.e3.setVisible(false);
        }
        final al al = new al(actionEvent);
        if (!al.for()) {
            return;
        }
        if (al.new() / 100 == 8) {
            this.a(al);
            return;
        }
        if (al.new() == 301) {
            this.do(al);
            return;
        }
        if (al.new() == 302) {
            this.int(al);
            return;
        }
        if (al.new() / 100 == 4) {
            this.case(al);
            return;
        }
        if (al.new() / 100 == 6) {
            this.new(al);
            return;
        }
        if (al.new() == 701) {
            this.for(al);
            return;
        }
        if (al.new() == 702) {
            this.if(al);
            return;
        }
        if (al.new() == 710) {
            this.byte(al);
            return;
        }
        this.try(al);
    }
    
    private void try(final al al) {
        final Menu a = this.a(al.if());
        final int n = al.new() % 1000;
        final ag a2 = dk.midas.web.chart.applet.o.a(n);
        if (!this.a(a2, a.getLabel())) {
            return;
        }
        final Study if1 = dk.midas.web.chart.applet.o.if(n);
        final String if2 = if1.if(a2.do);
        q q = null;
        if (if1.for()) {
            if (this.eR.a(if2) < 0) {
                q = if1.a(this, this.W());
                if (q != null) {
                    q.a(a2.do);
                }
            }
        }
        else if (if1.do() && this.eS.ed.a(if2) < 0) {
            q = if1.a(this.eV);
            if (q != null) {
                q.a(a2.do);
            }
        }
        if (q instanceof CompositeAnalysis) {
            if (this.eS.ed.a((CompositeAnalysis)q)) {
                this.a(a, if2);
                this.eS.a(true);
                this.if(this.W(), this.eH);
                this.eS.repaint();
            }
            return;
        }
        if (q instanceof Analyse) {
            final Analyse analyse = (Analyse)q;
            if (this.eR.a(analyse)) {
                this.a(a, if2);
                int n2 = dk.midas.web.chart.applet.s.if;
                if (n2 < this.eI) {
                    n2 = this.eI;
                }
                analyse.a(this.eS.bK);
                analyse.parent = this;
                analyse.setSize(this.getSize().width, n2);
                analyse.m();
                this.if(this.W(), this.eH);
                this.if(analyse);
                this.doLayout();
            }
        }
        this.getParent().setVisible(true);
        this.getParent().requestFocus();
    }
    
    private void a(final Menu menu, final String s) {
        final ai ai = new ai(s);
        ai.addItemListener(this);
        ai.setActionCommand("-1");
        menu.add(ai);
        ai.a(true);
    }
    
    private boolean a(final ag ag, final String title) {
        if (ag == null || ag.isVisible()) {
            return false;
        }
        ag.setTitle(title);
        ag.setVisible(true);
        this.getParent().requestFocus();
        return ag.do[0] != null;
    }
    
    private void byte(final al al) {
        final Applet eg = this.eG;
        if (al.a()) {
            try {
                eg.getAppletContext().showDocument(new URL(al.do()), this.eT);
            }
            catch (MalformedURLException ex) {}
        }
    }
    
    private void if(final al al) {
        this.eD.a("Chart Applet v" + bh.a / 10 + "." + bh.a % 10 + "\nProvided by Saxo Bank (c) 2001\nData Provider : " + bh.int);
        if (!this.eD.isVisible()) {
            this.eD.setTitle(this.a(al.if(), al.int()));
            this.eD.setVisible(true);
        }
    }
    
    private void for(final al al) {
        if (this.eG != null) {
            if (a8.do) {
                this.e4.call(a8.a, new Object[] { a8.a });
            }
            else {
                try {
                    this.eG.getAppletContext().showDocument(new URL(a8.a), "Online Help");
                }
                catch (MalformedURLException ex) {}
            }
        }
    }
    
    private void new(final al al) {
        switch (al.new() % 10) {
            case 1: {
                this.eS.d8 = 2;
                this.eS.setCursor(new Cursor(1));
                this.eS.dP = false;
                break;
            }
            case 2: {
                this.eS.d8 = 3;
                this.eS.dP = true;
                this.eS.dQ = false;
                break;
            }
            case 3: {
                this.eS.d8 = 3;
                this.eS.dP = true;
                this.eS.dQ = false;
                this.eS.dO.void();
                break;
            }
        }
    }
    
    private void case(final al al) {
        final int n = al.new() % 100;
        this.eS.d9 = null;
        switch (n / 10) {
            case 1: {
                this.char(al);
                break;
            }
            case 2: {
                this.U();
                break;
            }
            case 3: {
                this.Y();
                break;
            }
            case 4: {
                this.Z();
                break;
            }
            case 5: {
                this.Q();
                break;
            }
            case 6: {
                this.P();
                break;
            }
        }
    }
    
    private void P() {
        this.eS.d8 = 10;
        this.eS.dR = -1;
        this.eS.dQ = false;
        this.eS.dP = false;
        this.eS.dR = -1;
    }
    
    private void Q() {
        this.eS.d8 = 9;
        this.eS.dR = -1;
        this.eS.dQ = false;
        this.eS.dP = false;
        this.eS.dR = -1;
    }
    
    private void Z() {
        this.eS.d8 = 7;
        this.e3.a();
        this.eS.en = this.e3;
        this.eS.dQ = false;
        this.eS.dP = false;
        this.eS.dR = -1;
    }
    
    private void Y() {
        this.eS.d8 = 6;
        this.eS.dQ = false;
        this.eS.dP = false;
        this.eS.dR = -1;
    }
    
    private void U() {
        this.eS.d8 = 5;
        this.eS.dR = -1;
        this.eS.dQ = false;
        this.eS.dP = false;
        this.eS.dR = -1;
    }
    
    private void char(final al al) {
        this.eS.ee = al.new() % 10;
        if (this.eS.ee == 5) {
            this.eS.d9 = this.e2;
            this.eS.ee = 2;
        }
        else if (this.eS.ee == 6) {
            this.eS.d9 = this.e5;
            this.eS.ee = 2;
        }
        if (this.eS.ee == 2 && this.eS.dO.int() instanceof w) {
            this.eS.d8 = 1;
            this.eS.setCursor(new Cursor(0));
        }
        else {
            this.eS.d8 = 4;
        }
        this.eS.dQ = false;
        this.eS.dP = false;
        this.eS.dR = -1;
    }
    
    private void int(final al al) {
        this.eS.d8 = 8;
        this.ey.else = true;
        this.ey.setTitle(this.a(al.if(), al.int()));
        this.eS.dT = this.ey;
        this.eS.dQ = false;
        this.eS.dP = false;
        this.eS.dR = -1;
        this.eS.dI = -1;
    }
    
    private void do(final al al) {
        if (this.eQ.isVisible()) {
            return;
        }
        this.eQ.do[0] = new Integer((int)this.W().B());
        this.eQ.do[1] = new Integer(this.O());
        this.eQ.setVisible(true);
        this.getParent().requestFocus();
        if (this.eQ.do[0] == null) {
            return;
        }
        this.a((int)this.eQ.do[0], (int)this.eQ.do[1]);
        this.eS.a(true);
        this.if(this.W(), this.eH);
    }
    
    private void a(final al al) {
        final String do1 = al.do();
        final String do2 = al.do();
        String do3 = "";
        if (al.a()) {
            this.eZ.a(Integer.parseInt(al.do()));
        }
        else {
            this.eZ.a();
        }
        if (al.a()) {
            do3 = al.do();
        }
        this.a(new dk.midas.web.chart.applet.c.a(do1, do2, do3));
        this.if(this.W(), this.eH);
    }
    
    public void new(final String actionCommand) {
        MenuItem do1 = null;
        if (this.eC != null) {
            do1 = this.eC.do(actionCommand);
        }
        ai ai;
        if (do1 != null && do1 instanceof ai) {
            ai = (ai)do1;
        }
        else {
            ai = new ai("Anonymous");
            ai.setActionCommand(actionCommand);
            ai.a(this.eU.new("-" + ai.getActionCommand()));
        }
        if (ai.isEnabled()) {
            ai.a(!ai.a());
            this.itemStateChanged(new ItemEvent(ai, -1, ai, 1));
        }
        else {
            this.eU.do("-" + ai.getActionCommand(), ai.a());
        }
    }
    
    private void a(final int n, final int n2, final boolean b) {
        for (int i = n; i <= n2; ++i) {
            this.if("" + i, b);
        }
    }
    
    private void if(final String s, final boolean b) {
        if (this.eC != null) {
            this.eC.if(s, b);
        }
        if (this.eU != null) {
            this.eU.do("-" + s, b);
        }
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        final ai ai = (ai)itemEvent.getItemSelectable();
        final String label = ai.getLabel();
        final int int1 = Integer.parseInt(new StringTokenizer(ai.getActionCommand(), ";").nextToken());
        if (int1 / 100 == 1) {
            this.a(101, 109, false);
            this.if(ai.getActionCommand(), true);
            final int n = int1 % 100;
            this.o(n);
            this.if(this.W(), n);
            return;
        }
        if (int1 / 100 == 2) {
            this.a(200, 213, false);
            this.if(ai.getActionCommand(), true);
            this.a(dk.midas.web.chart.applet.b.a.a(int1));
            this.if(this.W(), this.eH);
            return;
        }
        if (int1 == 303) {
            ai.a(this.e1 = !this.e1);
            if (itemEvent.getID() > 0 && this.eU != null) {
                this.eU.do("-" + ai.getActionCommand(), ai.a());
            }
            return;
        }
        if (int1 == 304) {
            final boolean b = !this.eS.C();
            ai.a(b);
            this.eS.if(b);
            if (itemEvent.getID() > 0 && this.eU != null) {
                this.eU.do("-" + ai.getActionCommand(), ai.a());
            }
            return;
        }
        if (int1 == 350) {
            if (itemEvent.getID() > 0 && this.eU != null) {
                this.eU.do("-" + ai.getActionCommand(), ai.a());
            }
            this.eS.bE = ai.a();
            this.eV.e8 = ai.a();
            this.eS.a(true);
            this.eS.repaint();
            return;
        }
        if (int1 != 330 && int1 != 340) {
            ai.getParent().remove(ai);
        }
        if (ai.a()) {
            Analyse analyse;
            if (int1 == 330) {
                analyse = new v(this, this.W());
                analyse.setSize(this.eS.getSize().width, dk.midas.web.chart.applet.s.if);
            }
            else {
                analyse = new e(this, this.W());
                analyse.setSize(this.eS.getSize().width, dk.midas.web.chart.applet.s.if);
            }
            if (this.eR.a(analyse)) {
                if (int1 != 330 && int1 != 340) {
                    analyse.int(ai.getLabel());
                }
                else {
                    analyse.int("");
                }
                analyse.a(this.eS.bK);
                analyse.parent = this;
                analyse.m();
                this.if(analyse);
            }
            else {
                ai.a(false);
            }
        }
        else {
            final Analyse if1 = this.eR.if(label);
            if (if1 != null) {
                this.a(if1);
                this.if(this.W(), this.eH);
            }
            else {
                this.eS.ed.if(label);
                this.eS.a(true);
                this.if(this.W(), this.eH);
                this.eS.repaint();
            }
        }
    }
    
    protected synchronized void int(final boolean ey) {
        this.eY = ey;
    }
    
    protected synchronized boolean N() {
        return this.eY;
    }
    
    void aa() {
        this.int(false);
        this.eR.a(this.W());
        int i = 0;
        while (i < this.eS.ed.if()) {
            final CompositeAnalysis if1 = this.eS.ed.if(i);
            if1.b3 = this.eS.dO.int();
            if (!if1.byte(this.W().D())) {
                if (this.eC != null) {
                    this.eC.int(if1.g());
                }
                this.eS.ed.a(i);
            }
            else {
                if1.j();
                ++i;
            }
        }
        if (this.W().b4 == null) {
            if (this.eC != null) {
                this.eC.if("330", false);
                this.eC.a("330", false);
            }
        }
        else if (this.eC != null) {
            this.eC.a("330", true);
        }
        if (this.W().bS == null) {
            if (this.eC != null) {
                this.eC.if("340", false);
                this.eC.a("340", false);
            }
        }
        else if (this.eC != null) {
            this.eC.a("340", true);
        }
        int j = 0;
        while (j < this.eR.if()) {
            final Analyse a = this.eR.a(j);
            if (a != null && ((a instanceof v && this.W().b4 == null) || (a instanceof e && this.W().bS == null))) {
                this.eR.if(j);
                this.a(a);
            }
            else {
                ++j;
            }
        }
        int k = 0;
        while (k < this.eR.if()) {
            final Analyse a2 = this.eR.a(k);
            if (a2 != null && this.W().D() != 0 && !a2.char(this.W().D())) {
                if (this.eC != null) {
                    this.eC.int(a2.g());
                }
                this.eR.if(k);
                this.a(a2);
            }
            else {
                ++k;
            }
        }
    }
    
    void a(final dk.midas.web.chart.applet.c.a a) {
        this.ez.do();
        try {
            this.eS.a(true);
            this.W().a(a);
            this.eZ.a(this.W().p(), this.W().A());
        }
        finally {
            this.ez.a();
        }
    }
    
    private void o(final int eh) {
        this.ez.do();
        try {
            if (eh == this.eH) {
                return;
            }
            this.eH = eh;
            dk.midas.web.chart.applet.b.a a = null;
            if (this.eH != 1 && this.W().C().do()) {
                this.if("" + this.W().C().a(), false);
                a = dk.midas.web.chart.applet.b.a.a(1L);
                this.if("" + a.a(), true);
            }
            this.eV = null;
            final int do1 = this.eS.dO.do();
            for (int i = 0; i < this.eS.dO.int.size(); ++i) {
                final DataSource al = this.eS.dO.int(i).al();
                if (a != null) {
                    al.a(a);
                }
                else {
                    al.s();
                }
                this.eS.dO.a(dk.midas.web.chart.applet.h.a(this.eH, al, this.eS), i);
            }
            this.eS.dO.do(do1);
            this.eV = this.eS.dO.int();
        }
        finally {
            this.ez.a();
        }
    }
    
    private void a(final dk.midas.web.chart.applet.b.a a) {
        this.ez.do();
        try {
            for (int i = 0; i < this.eS.dO.int.size(); ++i) {
                this.eS.dO.int(i).al().a(a);
            }
        }
        finally {
            this.ez.a();
        }
    }
    
    private void a(final int n, final int n2) {
        this.ez.do();
        this.n(n2);
        this.for(n <= 1);
        try {
            for (int i = 0; i < this.eS.dO.int.size(); ++i) {
                this.eS.dO.int(i).al().for(n);
            }
        }
        finally {
            this.ez.a();
        }
    }
    
    public void a(final Rectangle rectangle, final boolean b) {
        final av[] m = this.M();
        for (int i = 0; i < m.length; ++i) {
            final av av = m[i];
            final Graphics graphics = av.getGraphics();
            av.a(av.else(), rectangle);
            if (!(av instanceof be) && b) {
                av.paint(graphics);
            }
            graphics.dispose();
        }
    }
    
    public void if(final DataSource dataSource, final int n) {
        this.a(dataSource, n);
    }
    
    public void a(final DataSource dataSource, final int n) {
        final AppletChart appletChart = (AppletChart)this.eG;
        Container parent;
        for (parent = this; parent != null && !(parent instanceof AppletChart); parent = parent.getParent()) {}
        if (parent == null) {
            return;
        }
        final AppletChart appletChart2 = (AppletChart)parent;
        if (!this.eN) {
            return;
        }
        final String s = "|";
        final StringBuffer sb = new StringBuffer();
        final String do1 = dataSource.r().do();
        final String if1 = dataSource.r().if();
        final String int1 = dataSource.r().int();
        sb.append("InstrumentName").append(",").append(do1).append(s);
        sb.append("Instrument").append(",").append(if1).append(s);
        if (!int1.equals("")) {
            sb.append("InstrumentType").append(",").append(int1).append(s);
        }
        sb.append("ChartType").append(",").append(n).append(s);
        sb.append("TimeScale").append(",").append(dataSource.C().int()).append(s);
        sb.append("Decimals").append(",").append(this.eZ.do);
        if (!this.eE) {
            sb.append(s).append("Points").append(",").append(dataSource.B());
        }
        sb.append(s).append("LeadingSpace").append(",").append(this.eL);
        if (a8.if) {
            for (int i = 0; i < dataSource.bO.a.size(); ++i) {
                final aj aj = dataSource.bO.a.elementAt(i);
                sb.append(s).append("Line_" + i + ",").append(aj.try());
                sb.append(";").append(aj.do());
                sb.append(";").append(aj.else());
                sb.append(";").append(aj.new());
                sb.append(";").append(aj.c());
            }
        }
        int n2 = 1;
        final ap ed = this.eS.ed;
        int n3 = -1;
        for (int n4 = 0; n4 < ed.if() && n3 < 0; ++n4) {
            final Object element = ed.do.elementAt(n4);
            if (element instanceof MovAvg) {
                n3 = n4;
                sb.append(s).append("Analyse_0,").append(((MovAvg)element).bW);
            }
        }
        for (int j = 0; j < ed.if(); ++j) {
            if (j != n3) {
                sb.append(s).append(this.a(ed.if(j), n2++, s));
            }
        }
        for (int if2 = this.eR.if(), k = 0; k < if2; ++k) {
            sb.append(s).append(this.a(this.eR.a[k], n2++, s));
        }
        appletChart2.getProperties().setPersistentString(sb.toString());
    }
    
    private String a(final q q, int n, final String s) {
        final StringBuffer sb = new StringBuffer();
        if (q.g().equals("Volume") || q.g().equals("Open Interest")) {
            return "";
        }
        final String substring = q.g().substring(q.f().int().length() + 1);
        sb.append("Analyse_" + n + ",").append(q.f().a());
        sb.append(s).append("Initialize_" + n++ + ",").append(substring);
        return sb.toString();
    }
    
    void for(final boolean ee) {
        this.eE = ee;
    }
    
    ag R() {
        if (this.eK == null) {
            this.T();
        }
        return this.eK;
    }
    
    public void int(final DataSource em) {
        if (this.eM != null) {
            this.eM.a(this);
        }
        this.eM = em;
        this.eS.if(em);
        if (this.eM != null) {
            this.eM.if(this);
        }
    }
    
    public DataSource W() {
        return this.eM;
    }
    
    public void n(final int n) {
        this.eL = Math.max(0, n);
    }
    
    public int O() {
        return this.eL;
    }
    
    public void dataSourceChanged(final a2 a2) {
        if (a2.new()) {
            this.int(true);
        }
    }
}
