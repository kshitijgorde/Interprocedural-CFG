import java.util.Enumeration;
import java.util.Hashtable;
import java.net.URLEncoder;
import java.net.URL;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.Cursor;
import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.awt.Label;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.TextArea;
import netscape.javascript.JSObject;
import java.awt.Insets;
import java.awt.Panel;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class FnChartsApplet extends Applet implements ActionListener
{
    public static final boolean pMb = true;
    public static final boolean qMb = true;
    private String YIb;
    private Object NIb;
    private String rMb;
    public static final String sMb = "fncharts_user_id_key";
    public static final String tMb = "fncharts_user_id_value";
    static final String[] uMb;
    static final String[] vMb;
    static final String[] wMb;
    boolean xMb;
    Color yMb;
    private var TKb;
    private String zMb;
    private String AMb;
    private String BMb;
    private String CMb;
    private String DMb;
    private String EMb;
    private String FMb;
    private String GMb;
    private int HMb;
    private String IMb;
    private String JMb;
    private String KMb;
    private String LMb;
    private String MMb;
    private String NMb;
    private boolean OMb;
    private volatile PMb;
    private Panel UKb;
    private String QMb;
    while RMb;
    private Insets wGb;
    private static final int SMb = 1;
    private static final int TMb = 2;
    private static final int UMb = 3;
    private static final int VMb = 4;
    private static final int WMb = 5;
    private static final int XMb = 6;
    private double[] YMb;
    
    public FnChartsApplet() {
        this.YIb = null;
        this.NIb = null;
        this.rMb = null;
        this.xMb = true;
        this.yMb = null;
        this.zMb = null;
        this.AMb = null;
        this.BMb = null;
        this.CMb = null;
        this.DMb = null;
        this.EMb = null;
        this.FMb = null;
        this.GMb = "\u3a60\u3a4c\u3a53\u3a5a\u3a51\u3a4a\u3a44\u3a4b\u3a57\u3a03\u3a0b\u3a40\u3a0a\u3a03\u3a12\u3a1a\u3a1a\u3a1a\u3a0e\u3a11\u3a13\u3a13\u3a17\u3a03\u3a74\u3a67\u3a03\u3a70\u3a6c\u3a65\u3a77\u3a74\u3a62\u3a71\u3a66\u3a03\u3a4b\u3a57\u3a57\u3a53\u3a19\u3a0c\u3a0c\u3a54\u3a54\u3a54\u3a0d\u3a54\u3a47\u3a50\u3a4c\u3a45\u3a57\u3a54\u3a42\u3a51\u3a46\u3a0d\u3a40\u3a4c\u3a4e";
        this.HMb = 5790255;
        this.IMb = "\u3a60\u3a4c\u3a53\u3a5a\u3a51\u3a4a\u3a44\u3a4b\u3a57";
        this.JMb = "\u3a6a\u3a4d\u3a55\u3a42\u3a4f\u3a4a\u3a47\u3a03\u3a6f\u3a4a\u3a40\u3a46\u3a4d\u3a50\u3a46\u3a02";
        this.KMb = "\u3a6a\u3a4d\u3a55\u3a42\u3a4f\u3a4a\u3a47\u3a03\u3a60\u3a4c\u3a53\u3a5a\u3a51\u3a4a\u3a44\u3a4b\u3a57\u3a0d\u3a29\u3a77\u3a4b\u3a46\u3a03\u3a04\u3a60\u3a4c\u3a53\u3a5a\u3a51\u3a4a\u3a44\u3a4b\u3a57\u3a04\u3a03\u3a53\u3a42\u3a51\u3a42\u3a4e\u3a46\u3a57\u3a46\u3a51\u3a03\u3a4e\u3a56\u3a50\u3a57\u3a03\u3a41\u3a46\u3a03\u3a50\u3a46\u3a57\u3a03\u3a57\u3a4c\u3a03\u3a57\u3a4b\u3a46\u3a03\u3a45\u3a4c\u3a4f\u3a4f\u3a4c\u3a54\u3a4a\u3a4d\u3a44\u3a03\u3a55\u3a42\u3a4f\u3a56\u3a46\u3a19\u3a29";
        this.LMb = "\u3a65\u3a4d\u3a60\u3a4b\u3a42\u3a51\u3a57\u3a50\u3a03\u3a73\u3a51\u3a4c\u3a45\u3a46\u3a50\u3a50\u3a4a\u3a4c\u3a4d\u3a42\u3a4f\u3a03\u3a10\u3a0d\u3a13\u3a29\u3a60\u3a4c\u3a53\u3a5a\u3a51\u3a4a\u3a44\u3a4b\u3a57\u3a03\u3a0b\u3a40\u3a0a\u3a03\u3a12\u3a1a\u3a1a\u3a1a\u3a0e\u3a11\u3a13\u3a13\u3a17\u3a03\u3a74\u3a67\u3a03\u3a70\u3a6c\u3a65\u3a77\u3a74\u3a62\u3a71\u3a66\u3a29\u3a76\u3a4d\u3a42\u3a56\u3a57\u3a4b\u3a4c\u3a51\u3a4a\u3a59\u3a46\u3a47\u3a03\u3a47\u3a4a\u3a50\u3a57\u3a51\u3a4a\u3a41\u3a56\u3a57\u3a4a\u3a4c\u3a4d\u3a03\u3a4a\u3a50\u3a03\u3a45\u3a4c\u3a51\u3a41\u3a4a\u3a47\u3a47\u3a46\u3a4d\u3a0d\u3a29\u3a65\u3a4c\u3a51\u3a03\u3a4e\u3a4c\u3a51\u3a46\u3a03\u3a4a\u3a4d\u3a45\u3a4c\u3a51\u3a4e\u3a42\u3a57\u3a4a\u3a4c\u3a4d\u3a03\u3a55\u3a4a\u3a50\u3a4a\u3a57\u3a19\u3a03\u3a4b\u3a57\u3a57\u3a53\u3a19\u3a0c\u3a0c\u3a54\u3a54\u3a54\u3a0d\u3a54\u3a47\u3a50\u3a4c\u3a45\u3a57\u3a54\u3a42\u3a51\u3a46\u3a0d\u3a40\u3a4c\u3a4e";
        this.MMb = null;
        this.QMb = null;
        this.RMb = null;
        this.wGb = new Insets(2, 2, 2, 2);
        this.BMb = var.E();
        this.CMb = var.F();
    }
    
    public Object _() {
        return this.NIb;
    }
    
    public void init() {
        _.I(this.getParameter("Encoding"));
        this.NMb = this.getParameter("UserConfigurationCookie");
        if (this.NMb == null || this.NMb.length() == 0) {
            this.NMb = "fncharts_cookie";
        }
        _.H(Boolean.valueOf(this.getParameter("DisplayDataRequests")));
        this.YIb = this.getParameter("AppletName");
        this.DMb = var.j(this.GMb);
        this.QMb = var.j(this.IMb);
        try {
            this.NIb = JSObject.getWindow((Applet)this);
            final String parameter = this.getParameter("BuySellAction");
            if (parameter != null && parameter.length() > 0) {
                this.rMb = parameter;
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        this.EMb = var.j(this.KMb) + this.DMb;
        this.FMb = var.j(this.LMb);
        final String parameter2 = this.getParameter("BorderColor");
        if (parameter2 != null) {
            try {
                this.yMb = Color.decode(parameter2);
            }
            catch (NumberFormatException ex3) {
                this.yMb = Color.black;
            }
        }
        try {
            final String parameter3 = this.getParameter(this.QMb);
            if (parameter3 == null) {
                throw new SecurityException(this.EMb);
            }
            if (!parameter3.equals(this.DMb)) {
                throw new SecurityException(this.EMb);
            }
        }
        catch (Exception ex2) {
            final TextArea textArea = new TextArea();
            this.setLayout(new BorderLayout());
            this.add(textArea, "Center");
            textArea.setText(ex2.getMessage());
            textArea.setEditable(false);
            return;
        }
        final Color[] a = this.a("UIBg");
        String host = this.getDocumentBase().getHost();
        if (host == null) {
            host = "";
        }
        String host2 = this.getCodeBase().getHost();
        if (host2 == null) {
            host2 = "";
        }
        if (host.length() > 0 && host2.length() > 0 && ((!host.endsWith(this.BMb) && !host.endsWith(this.CMb)) || (!host2.endsWith(this.BMb) && !host2.endsWith(this.CMb)))) {
            this.add(new Label(var.j(this.JMb)));
            return;
        }
        final int b = a.b(this.DMb);
        this.PMb = new volatile(this.getParameter("Encoding"));
        final String[] m = this.PMb.m();
        for (int i = 0; i < m.length; ++i) {
            this.PMb.a(m[i], this.getParameter(m[i]));
        }
        if (6599848L - (a.b(this.BMb) + a.b(this.CMb)) != 0L) {
            return;
        }
        if (a != null) {
            this.setBackground(a[0]);
        }
        else {
            this.setBackground(SystemColor.control);
        }
        this.setLayout(new BorderLayout());
        if (b != this.HMb) {
            throw new RuntimeException(this.EMb);
        }
        this.TKb = this.a(this.PMb);
        final Color[] a2 = this.a("UIBg");
        Color control;
        if (a2 != null) {
            control = a2[0];
        }
        else {
            control = SystemColor.control;
        }
        final Color[] a3 = this.a("UIText");
        Color controlText;
        if (a3 != null) {
            controlText = a3[0];
        }
        else {
            controlText = SystemColor.controlText;
        }
        (this.UKb = new b(this, this.PMb, control, controlText)).setBackground(control);
        this.UKb.setForeground(controlText);
        System.out.println(this.FMb);
        this.TKb.setEnabled(false);
        this.add(this.TKb, "Center");
        this.TKb.yc();
    }
    
    protected void Y() {
        synchronized (this.TKb) {
            if (this.RMb != null) {
                return;
            }
            this.TKb.setVisible(false);
            this.setVisible(false);
            this.remove(this.TKb);
            this.add(this.UKb, "North");
            this.UKb.setVisible(true);
            this.setVisible(true);
            this.validate();
            this.RMb = new while(this.PMb._("f r a m e T i t l e"), this, this.TKb, this.UKb);
        }
    }
    
    protected void Z() {
        synchronized (this.TKb) {
            if (this.RMb == null) {
                return;
            }
            this.TKb.setVisible(false);
            this.RMb.setVisible(false);
            this.RMb.dispose();
            this.setVisible(false);
            this.remove(this.UKb);
            this.TKb.fEb.setState(false);
            this.add(this.TKb, "Center");
            this.TKb.setVisible(true);
            this.setVisible(true);
            this.invalidate();
            this.validate();
            this.TKb.tc();
            this.RMb = null;
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (this.RMb == null) {
            return;
        }
        if (actionEvent.getSource() instanceof Button) {
            final Button button = (Button)actionEvent.getSource();
            if (button.getLabel().equals(this.PMb._("btnShowFnChartsWindow"))) {
                this.RMb.setVisible(true);
            }
            else if (button.getLabel().equals(this.PMb._("btnCloseFnChartsWindow"))) {
                if (this.getCursor().equals(Cursor.getPredefinedCursor(3))) {
                    Toolkit.getDefaultToolkit().beep();
                }
                else {
                    this.Z();
                }
            }
        }
    }
    
    public Insets getInsets() {
        return this.wGb;
    }
    
    public void paint(final Graphics graphics) {
        graphics.setColor(this.yMb);
        graphics.drawRoundRect(0, 0, this.getSize().width - 1, this.getSize().height - 1, 2, 2);
        super.paint(graphics);
    }
    
    public String getAppletInfo() {
        return "FnCharts Professional 3.0\nCopyright (c) 1999-2004 WD SOFTWARE http://www.wdsoftware.com\n";
    }
    
    private Color[] a(final String s) {
        final String parameter = this.getParameter(s + "Color");
        if (parameter == null) {
            return null;
        }
        final d d = new d(",");
        d.e(parameter);
        if (d.z() == 0) {
            return null;
        }
        final Color[] array = new Color[d.z()];
        try {
            for (int i = 0; i < array.length; ++i) {
                array[i] = Color.decode(d._(i));
            }
        }
        catch (NumberFormatException ex) {
            return null;
        }
        return array;
    }
    
    private int[] a(final String s) {
        final String parameter = this.getParameter(s + "LineWidth");
        if (parameter == null) {
            return null;
        }
        final d d = new d(",");
        d.e(parameter);
        if (d.z() == 0) {
            return null;
        }
        final int[] array = new int[d.z()];
        try {
            for (int i = 0; i < array.length; ++i) {
                array[i] = Integer.parseInt(d._(i));
            }
        }
        catch (NumberFormatException ex) {
            return null;
        }
        return array;
    }
    
    private e a(final String[] array) {
        final e e = new e();
        if (array == null) {
            return e;
        }
        for (int i = 0; i < array.length; ++i) {
            final Color[] a = this.a(array[i]);
            if (a != null) {
                e._(array[i], a);
            }
        }
        return e;
    }
    
    private f _(final String[] array) {
        final f f = new f();
        if (array == null) {
            return f;
        }
        for (int i = 0; i < array.length; ++i) {
            final int[] a = this.a(array[i]);
            if (a != null) {
                f.b(array[i], a);
            }
        }
        return f;
    }
    
    private void _(final g g) {
        g.b(this.a(FnChartsApplet.uMb));
        g.b(this.a(g.a()));
        g.b(this.a(g.l()));
    }
    
    private void a(final g g) {
        g.a(this._(FnChartsApplet.vMb));
        g.a(this._(g.a()));
        g.a(this._(g.l()));
    }
    
    private void b(final h h) {
        for (int i = 0; i < FnChartsApplet.wMb.length; ++i) {
            final String parameter = this.getParameter(FnChartsApplet.wMb[i]);
            if (parameter != null && parameter.length() > 0) {
                h._(FnChartsApplet.wMb[i], parameter);
            }
        }
    }
    
    private var a(final volatile volatile1) {
        int int1 = 0;
        final String parameter = this.getParameter("AutoRefreshTime");
        if (parameter != null) {
            try {
                int1 = Integer.parseInt(parameter);
            }
            catch (Exception ex) {
                int1 = 120;
            }
        }
        String s = "c_";
        final String parameter2 = this.getParameter("IconType");
        if (parameter2 != null) {
            if (parameter2.equals("COLOR")) {
                s = "c_";
            }
            else if (parameter2.equals("BW")) {
                s = "bw_";
            }
        }
        int int2 = 0;
        final String parameter3 = this.getParameter("UnusedCacheRecycleTime");
        if (parameter3 != null) {
            try {
                int2 = Integer.parseInt(parameter3);
                if (int2 < 0) {
                    int2 = 0;
                }
            }
            catch (Exception ex2) {
                int2 = 0;
            }
        }
        final String string = this.getCodeBase().toString();
        String s2 = this.getParameter("DataSource");
        if (s2 == null || s2.length() == 0) {
            s2 = "{symbol}.txt";
        }
        if (s2 != null && !s2.startsWith("http:") && !s2.startsWith("ftp:") && !s2.startsWith("https:") && !s2.startsWith("file:")) {
            s2 = string + s2;
        }
        String s3 = this.getParameter("IntradayDataSource");
        if (s3 == null || s3.length() == 0) {
            s3 = "{symbol}_intraday.txt";
        }
        if (s3 != null && !s3.startsWith("http:") && !s3.startsWith("ftp:") && !s3.startsWith("https:") && !s3.startsWith("file:")) {
            s3 = string + s3;
        }
        String s4 = this.getParameter("PredefinedIndicatorsURL");
        if (s4 != null && !s4.startsWith("http:") && !s4.startsWith("ftp:") && !s4.startsWith("https:") && !s4.startsWith("file:")) {
            s4 = string + s4;
        }
        String string2 = "";
        if (s4 != null && s4.length() > 0) {
            final _ _ = new _();
            _.d(s4);
            final StringBuffer sb = new StringBuffer(_.L() * 128);
            for (int i = 0; i < _.L(); ++i) {
                sb.append(_.l(i));
                sb.append("\n");
            }
            string2 = sb.toString();
        }
        final var var = new var(this, volatile1, s2, s3, int2, !Boolean.valueOf(this.getParameter("HideSymbolEntryForm")), Boolean.valueOf(this.getParameter("AutoHideVolumePanel")), int1 > 0, Boolean.valueOf(this.getParameter("EnableUserIndicatorDefinition")), s, string2);
        final String parameter4 = this.getParameter("Timeframe");
        if (parameter4 != null) {
            int int3;
            try {
                int3 = Integer.parseInt(parameter4);
            }
            catch (NumberFormatException ex3) {
                int3 = 0;
            }
            var._()._().L(int3);
        }
        final String parameter5 = this.getParameter("IntradayTimeframe");
        if (parameter5 != null) {
            int int4;
            try {
                int4 = Integer.parseInt(parameter5);
            }
            catch (NumberFormatException ex4) {
                int4 = 0;
            }
            var._()._().K(int4);
        }
        var.g(Boolean.valueOf(this.getParameter("UseBrowserCache")));
        final String parameter6 = this.getParameter("SymbolEntryFormWidth");
        if (parameter6 != null) {
            try {
                final int int5 = Integer.parseInt(parameter6);
                if (int5 > 0) {
                    var.o(int5);
                }
            }
            catch (NumberFormatException ex5) {}
        }
        this._(var._());
        this.a(var._());
        this.b(var._().tGb);
        var.e(4, 1);
        var.p();
        final Color[] a = this.a("UIBg");
        if (a != null) {
            var._(var, a[0]);
        }
        final Color[] a2 = this.a("UIText");
        if (a2 != null) {
            var.a(var, a2[0]);
        }
        final Color[] a3 = this.a("ImageButtonBg");
        if (a3 != null) {
            var.m(var, a3[0]);
        }
        final Color[] a4 = this.a("ImageButtonFrame");
        if (a4 != null) {
            var.n(var, a4[0]);
        }
        var._(var, var.a());
        var.wc();
        var.i(this.getParameter("Indicator1"));
        var.j(this.getParameter("Indicator2"));
        var.K(this.getParameter("PriceIndicators"));
        var.ha(int1);
        var.h(Boolean.valueOf(this.getParameter("AutoRefreshBeep")));
        final String parameter7 = this.getParameter("DateDisplayFormat");
        if (parameter7 != null && parameter7.length() > 0) {
            final StringBuffer sb2 = new StringBuffer(parameter7.length());
            for (int j = 0; j < parameter7.length(); ++j) {
                if (parameter7.charAt(j) == 'm') {
                    sb2.append(Character.toUpperCase(parameter7.charAt(j)));
                }
                else {
                    sb2.append(parameter7.charAt(j));
                }
            }
            var.w(sb2.toString());
        }
        final String parameter8 = this.getParameter("Scale");
        if (parameter8 != null) {
            if (parameter8.equals("LogCorrelated")) {
                var.fa(1);
            }
            else if (parameter8.equals("LogUncorrelated")) {
                var.fa(2);
            }
            else if (parameter8.equals("LinCorrelated")) {
                var.fa(3);
            }
            else if (parameter8.equals("LinUncorrelated")) {
                var.fa(4);
            }
            else if (parameter8.equals("Percent")) {
                var.fa(5);
            }
        }
        final String parameter9 = this.getParameter("ChartType");
        if (parameter9 != null) {
            if (parameter9.equals("line")) {
                var.P(0);
            }
            else if (parameter9.equals("ohlc")) {
                var.P(1);
            }
            else if (parameter9.equals("candle")) {
                var.P(2);
            }
        }
        String s5 = this.getParameter("TimeCompressionOptions");
        if (s5 == null) {
            s5 = this.getParameter("VisibleDataPeriods");
        }
        if (s5 != null) {
            var.M(s5);
        }
        String s6 = this.getParameter("TimeCompression");
        if (s6 == null) {
            s6 = this.getParameter("DataPeriod");
        }
        if (s6 != null) {
            if (s6.equals("intraday")) {
                var.ga(0);
            }
            else if (s6.equals("intraday_1s")) {
                var.ga(1);
            }
            else if (s6.equals("intraday_2s")) {
                var.ga(2);
            }
            else if (s6.equals("intraday_3s")) {
                var.ga(3);
            }
            else if (s6.equals("intraday_4s")) {
                var.ga(4);
            }
            else if (s6.equals("intraday_5s")) {
                var.ga(5);
            }
            else if (s6.equals("intraday_10s")) {
                var.ga(10);
            }
            else if (s6.equals("intraday_15s")) {
                var.ga(15);
            }
            else if (s6.equals("intraday_20s")) {
                var.ga(20);
            }
            else if (s6.equals("intraday_30s")) {
                var.ga(30);
            }
            else if (s6.equals("intraday_1")) {
                var.ga(60);
            }
            else if (s6.equals("intraday_2")) {
                var.ga(120);
            }
            else if (s6.equals("intraday_3")) {
                var.ga(180);
            }
            else if (s6.equals("intraday_4")) {
                var.ga(240);
            }
            else if (s6.equals("intraday_5")) {
                var.ga(300);
            }
            else if (s6.equals("intraday_10")) {
                var.ga(600);
            }
            else if (s6.equals("intraday_15")) {
                var.ga(900);
            }
            else if (s6.equals("intraday_20")) {
                var.ga(1200);
            }
            else if (s6.equals("intraday_30")) {
                var.ga(1800);
            }
            else if (s6.equals("intraday_60")) {
                var.ga(3600);
            }
            else if (s6.equals("day")) {
                var.ga(100001);
            }
            else if (s6.equals("week")) {
                var.ga(100002);
            }
            else if (s6.equals("month")) {
                var.ga(100003);
            }
        }
        String s7 = this.getParameter("UserIndicatorsSaveURL");
        if (s7 != null && s7.length() > 0 && !s7.startsWith("http:") && !s7.startsWith("ftp:") && !s7.startsWith("https:") && !s7.startsWith("file:")) {
            s7 = string + s7;
        }
        var._()._().G(s7);
        String s8 = this.getParameter("UserIndicatorsLoadURL");
        if (s8 != null && s8.length() > 0 && !s8.startsWith("http:") && !s8.startsWith("ftp:") && !s8.startsWith("https:") && !s8.startsWith("file:")) {
            s8 = string + s8;
        }
        var._()._().H(s8);
        final String parameter10 = this.getParameter("DateFormat");
        if (parameter10 != null) {
            var._()._().c(parameter10);
        }
        final String parameter11 = this.getParameter("DecimalPlaces");
        if (parameter11 != null) {
            int int6 = -1;
            try {
                int6 = Integer.parseInt(parameter11);
            }
            catch (NumberFormatException ex6) {}
            var._()._().ea(int6);
        }
        var.K(Boolean.valueOf(this.getParameter("LoadUserConfigurationOnStartup")));
        var._(this.getParameter("Symbol"), this.getParameter("SecondarySymbol"), this.getParameter("FavoriteSymbols"));
        return var;
    }
    
    public boolean _a() {
        if (this.zMb == null) {
            this.zMb = this.getParameter("BuyURL");
        }
        if (this.AMb == null) {
            this.AMb = this.getParameter("SellURL");
        }
        return !this.e(this.zMb) && !this.e(this.AMb);
    }
    
    private boolean e(final String s) {
        return s == null || s.length() == 0;
    }
    
    public void u(final String s) {
        if (this.zMb == null) {
            this.zMb = this.getParameter("BuyURL");
        }
        if (this.e(this.zMb)) {
            return;
        }
        this.l(this.zMb, s);
    }
    
    public void v(final String s) {
        if (this.AMb == null) {
            this.AMb = this.getParameter("SellURL");
        }
        if (this.e(this.AMb)) {
            return;
        }
        this.l(this.AMb, s);
    }
    
    public void l(String s, final String s2) {
        if (!s.startsWith("http")) {
            final URL documentBase = this.getDocumentBase();
            String s3;
            if (s.startsWith("/")) {
                final int port = documentBase.getPort();
                s3 = documentBase.getProtocol() + "://" + documentBase.getHost();
                if (port != -1) {
                    s3 += port;
                }
            }
            else {
                s3 = documentBase.toString();
                final int lastIndex = s3.lastIndexOf("/");
                if (lastIndex != -1) {
                    s3 = s3.substring(0, lastIndex + 1);
                }
            }
            s = s3 + s;
        }
        s = this.b(s, s2);
        if (this.MMb == null) {
            this.MMb = this.getParameter("target");
        }
        if (this.e(this.MMb)) {
            this.MMb = "_blank";
        }
        try {
            if (this.NIb != null && this.rMb != null) {
                ((JSObject)this.NIb).call(this.rMb, (Object[])new String[] { s, this.MMb });
            }
            else {
                this.getAppletContext().showDocument(new URL(s), this.MMb);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private String b(final String s, String encode) {
        final String s2 = "{SYMBOL}";
        final String s3 = "{symbol}";
        final String s4 = "{Symbol}";
        if (encode == null) {
            encode = "";
        }
        encode = URLEncoder.encode(encode);
        return i.b(i.b(i.b(s, s2, encode.toUpperCase()), s3, encode.toLowerCase()), s4, encode.toUpperCase());
    }
    
    private long b(final String s) {
        long long1 = 0L;
        final String parameter = this.getParameter(s);
        if (parameter != null) {
            try {
                long1 = Long.parseLong(parameter);
            }
            catch (NumberFormatException ex) {}
        }
        if (long1 < 0L) {
            long1 = 0L;
        }
        return long1;
    }
    
    public void destroy() {
        if (this.TKb != null) {
            this.TKb.xc();
            this.TKb._((Component)this.TKb);
        }
    }
    
    public String getSelectedStockName() {
        if (this.TKb == null) {
            return "";
        }
        synchronized (this.TKb) {
            return this.TKb._()._().i();
        }
    }
    
    public String[] c() {
        synchronized (this.TKb) {
            String string = "";
            try {
                String s = ((JSObject)this.NIb).eval("document.cookie").toString();
                if (s != null && s.equalsIgnoreCase("undefined")) {
                    s = ((JSObject)this.NIb).call("eval", (Object[])new String[] { "document.cookie" }).toString();
                }
                final String string2 = this.NMb + "=";
                if (s.length() > 0) {
                    final int index = s.indexOf(string2);
                    if (index != -1) {
                        final int n = index + string2.length();
                        int n2 = s.indexOf(";", n);
                        if (n2 == -1) {
                            n2 = s.length();
                        }
                        string = ((JSObject)this.NIb).call("unescape", (Object[])new String[] { s.substring(n, n2) }).toString();
                    }
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            if (this.e(string)) {
                return null;
            }
            final d d = new d(";");
            d.e(string);
            String _ = null;
            String _2 = null;
            for (int i = 0; i < d.z(); ++i) {
                final d d2 = new d("=");
                d2.e(d._(i));
                if (d2.z() == 2 && "fncharts_user_id_key".equals(d2._(0))) {
                    _ = d2._(1);
                }
                else if (d2.z() == 2 && "fncharts_user_id_value".equals(d2._(0))) {
                    _2 = d2._(1);
                }
                if (_ != null && _2 != null) {
                    break;
                }
            }
            if (_ != null && _2 != null) {
                return new String[] { _, _2 };
            }
        }
        return null;
    }
    
    public void doLoadConfigAction() {
        synchronized (this.TKb) {
            this.TKb.b(this.PMb._("msgLoadingConfiguration"));
            String string = "";
            try {
                String s = ((JSObject)this.NIb).eval("document.cookie").toString();
                if (s != null && s.equalsIgnoreCase("undefined")) {
                    s = ((JSObject)this.NIb).call("eval", (Object[])new String[] { "document.cookie" }).toString();
                }
                final String string2 = this.NMb + "=";
                if (s.length() > 0) {
                    final int index = s.indexOf(string2);
                    if (index != -1) {
                        final int n = index + string2.length();
                        int n2 = s.indexOf(";", n);
                        if (n2 == -1) {
                            n2 = s.length();
                        }
                        string = ((JSObject)this.NIb).call("unescape", (Object[])new String[] { s.substring(n, n2) }).toString();
                    }
                }
            }
            catch (Exception ex) {
                this.TKb.b(this.PMb._("msgConfigurationLoadError"));
                ex.printStackTrace();
            }
            if (this.e(string)) {
                this.TKb.b(this.PMb._("msgConfigurationLoadError"));
                return;
            }
            final d d = new d(";");
            d.e(string);
            final Hashtable<String, String> hashtable = new Hashtable<String, String>();
            for (int i = 0; i < d.z(); ++i) {
                final d d2 = new d("=");
                d2.e(d._(i));
                if (d2.z() == 2) {
                    hashtable.put(d2._(0), d2._(1));
                }
            }
            this.TKb.b(hashtable);
            this.TKb.b(this.PMb._("msgConfigurationLoaded"));
        }
    }
    
    public void doSaveConfigAction() {
        synchronized (this.TKb) {
            this.TKb.b(this.PMb._("msgSavingConfiguration"));
            final Hashtable _ = this.TKb._();
            final StringBuffer sb = new StringBuffer();
            final Enumeration<String> keys = _.keys();
            while (keys.hasMoreElements()) {
                final String s = keys.nextElement();
                sb.append(s);
                sb.append("=");
                sb.append(_.get(s));
                sb.append(";");
            }
            final String string = "var today = new Date();var expires = new Date();expires.setTime(today.getTime() + 1000*60*60*24*365*3);" + "document.cookie = \"" + this.NMb + "\" + \"=\" + escape(\"" + sb.toString() + "\") + \"; expires=\" + expires.toGMTString();";
            try {
                ((JSObject)this.NIb).eval(string);
                this.TKb.b(this.PMb._("msgConfigurationSaved"));
            }
            catch (Exception ex) {
                this.TKb.b(this.PMb._("msgConfigurationSaveError"));
                ex.printStackTrace();
            }
        }
    }
    
    public int getParam(final int n) {
        return this.TKb._().l(n - 1);
    }
    
    public int getValuesLength(final int n) {
        if (n == 1) {
            return this.TKb._().w().length;
        }
        if (n == 2) {
            return this.TKb._().x().length;
        }
        if (n == 3) {
            return this.TKb._().y().length;
        }
        if (n == 4) {
            return this.TKb._().z().length;
        }
        if (n == 5) {
            return this.TKb._().A().length;
        }
        if (n == 6) {
            return this.TKb._().B().length;
        }
        return 0;
    }
    
    public double[] getValues(final int n) {
        if (n == 1) {
            return this.TKb._().w();
        }
        if (n == 2) {
            return this.TKb._().x();
        }
        if (n == 3) {
            return this.TKb._().y();
        }
        if (n == 4) {
            return this.TKb._().z();
        }
        if (n == 5) {
            return this.TKb._().A();
        }
        if (n == 6) {
            return this.TKb._().B();
        }
        return new double[0];
    }
    
    public double getValue(final int n, final int n2) {
        if (n == 1) {
            return this.TKb._().w()[n2];
        }
        if (n == 2) {
            return this.TKb._().x()[n2];
        }
        if (n == 3) {
            return this.TKb._().y()[n2];
        }
        if (n == 4) {
            return this.TKb._().z()[n2];
        }
        if (n == 5) {
            return this.TKb._().A()[n2];
        }
        if (n == 6) {
            return this.TKb._().B()[n2];
        }
        return 0.0;
    }
    
    public void addGraphData(final double[] array, final int n) {
        for (int i = 0; i < array.length; ++i) {
            if (Double.isNaN(array[i])) {
                array[i] = 0.0;
            }
        }
        this.TKb._()._(array, n);
    }
    
    public void addHorizLine(final double n) {
        if (!Double.isNaN(n)) {
            this.TKb._().b(n);
        }
    }
    
    public void addBuySignal(final int n) {
        this.TKb._().ca(n);
    }
    
    public void addSellSignal(final int n) {
        this.TKb._().da(n);
    }
    
    public void setJSExceptionMessage(final String s) {
        this.TKb._().C(s);
    }
    
    public void beginGraphData(final int n) {
        this.YMb = new double[n];
    }
    
    public void addGraphDataPoint(final int n, double n2) {
        if (Double.isNaN(n2)) {
            n2 = 0.0;
        }
        this.YMb[n] = n2;
    }
    
    public void endGraphData(final int n) {
        this.TKb._()._(this.YMb, n);
        this.YMb = null;
    }
    
    public double[] calculateMax(final double[] array, final int n) {
        final double[] array2 = new double[array.length];
        j.calculateMax(array, array2, 0, n);
        return array2;
    }
    
    public double[] calculateMin(final double[] array, final int n) {
        final double[] array2 = new double[array.length];
        j.calculateMin(array, array2, 0, n);
        return array2;
    }
    
    public double[] calculateStdDev(final double[] array, final int n) {
        final double[] array2 = new double[array.length];
        j.calculateStdDev(array, array2, 0, n);
        return array2;
    }
    
    public double[] calculateSimpleAvg(final double[] array, final int n) {
        final double[] array2 = new double[array.length];
        j.calculateSimpleAvg(array, array2, 0, n);
        return array2;
    }
    
    public double[] calculateExpAvg(final double[] array, final int n) {
        final double[] array2 = new double[array.length];
        j.calculateExpAvg(array, array2, 0, n);
        return array2;
    }
    
    public void setBrowserType(final String s) {
        if (this.TKb != null) {
            this.TKb._().b().G(s != null && s.equals("msie"));
        }
    }
    
    public String A() {
        return this.YIb;
    }
    
    static {
        uMb = new String[] { "ChartBg", "ChartFg", "Grid", "ChartTitle", "ChartTitleBg", "ChartTitleFrame", "Axis", "AxisLabel", "AxisLabelBg", "AxisLabelFrame", "Price", "BgPrice", "ExtraPrice", "Volume", "OpenInterest", "InfoLine", "InfoLineBg", "InfoLineFrame", "Crosshair", "ChartTopLeftFrame", "ChartBottomRightFrame", "IndicatorSpecificLine", "Indicator", "TrendLine", "ImageButtonBg", "ImageButtonFrame", "UIText", "ProgressBar", "UIBg", "BuySignal", "SellSignal" };
        vMb = new String[] { "Price", "BgPrice", "ExtraPrice", "OpenInterest", "Indicator", "TrendLine" };
        wMb = new String[] { "InfoLineFontName", "InfoLineFontSize", "InfoLineFontStyle", "AxisLabelFontName", "AxisLabelFontSize", "AxisLabelFontStyle", "ChartTitleFontName", "ChartTitleFontSize", "ChartTitleFontStyle", "StatusBarFontName", "StatusBarFontSize", "StatusBarFontStyle", "UIFontName", "UIFontSize", "UIFontStyle", "LegendFontName", "LegendFontSize", "LegendFontStyle" };
    }
}
