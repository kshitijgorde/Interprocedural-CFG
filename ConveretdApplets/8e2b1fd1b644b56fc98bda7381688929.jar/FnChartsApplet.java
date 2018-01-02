import java.net.URLEncoder;
import java.net.URL;
import java.util.Hashtable;
import java.net.InetAddress;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.Cursor;
import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import netscape.javascript.JSObject;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.TextArea;
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
    public static final boolean Oya = true;
    public static final boolean Pya = true;
    private String rwa;
    private Object bta;
    private String Qya;
    static final String[] Rya;
    static final String[] Sya;
    static final String[] Tya;
    boolean Uya;
    Color Vya;
    private super _xa;
    private String Wya;
    private String Xya;
    private final byte[] Yya;
    private final String Zya = "";
    private final String _za = "";
    private final byte[] aza;
    private final byte[] bza;
    private final byte[] cza;
    private final byte[] dza;
    private String eza;
    private String fza;
    private switch Uqa;
    private Panel axa;
    synchronized gza;
    private Insets y;
    private static final int hza = 1;
    private static final int iza = 2;
    private static final int jza = 3;
    private static final int kza = 4;
    private static final int lza = 5;
    private static final int mza = 6;
    private static final int nza = 7;
    private double[] oza;
    
    public FnChartsApplet() {
        this.rwa = null;
        this.bta = null;
        this.Qya = null;
        this.Uya = true;
        this.Vya = null;
        this.Wya = null;
        this.Xya = null;
        this.Yya = new byte[] { 0, 0, 0, 0, 0, 0, -13, -55, 76, 78, -51, 43, 78, -11, 78, -83, 4, 0, -30, -69, -50, -35, 10, 0, 0, 0 };
        this.aza = new byte[] { 0, 0, 0, 0, 0, 0, 115, -50, 47, -88, 44, -54, 76, -49, 40, 1, 0, 80, -105, 112, -70, 9, 0, 0, 0 };
        this.bza = new byte[] { 0, 0, 0, 0, 0, 0, 115, -50, 47, -88, 44, -54, 76, -49, 40, 81, -48, 72, -42, 84, 48, -76, -76, -76, -44, 53, 50, 48, 48, 83, 8, 119, 81, 8, -10, 119, 11, 9, 119, 12, 114, 85, -56, 40, 41, 41, -80, -46, -41, 47, 47, 47, -41, 43, 79, 41, -50, 79, 43, 41, 79, 44, 74, -43, 75, -50, -49, 5, 0, 85, 35, 10, 8, 61, 0, 0, 0 };
        this.cza = new byte[] { 0, 0, 0, 0, 0, 0, 69, -52, 49, 14, -128, 32, 12, 70, -31, -85, -4, 27, -101, 7, 112, 117, 114, -9, 2, 24, 43, -112, 0, 37, 109, -47, 120, 123, 113, 114, 124, 121, -55, -73, -42, -53, -25, 116, -128, 5, 37, -87, -90, 26, -32, 22, 110, -113, -92, 16, -51, -95, 121, -15, -123, -116, 100, -62, 22, -23, 79, -108, -82, -122, -99, -96, 100, 48, -122, -115, 121, 114, -50, 124, 127, -62, 32, 59, -51, 47, 101, -53, -111, 27, 91, 0, 0, 0 };
        this.dza = new byte[] { 0, 0, 0, 0, 0, 0, 13, -54, -69, 9, -64, 48, 12, 5, -64, 85, -34, 4, 94, 34, -112, 58, 77, 6, -112, -111, -116, 5, 65, 2, 125, -102, 76, -97, 92, 125, -89, 29, -101, -94, 18, 87, -8, -110, 76, 117, -93, 103, -32, 54, -22, -38, 30, -6, 10, -93, 83, -32, 1, -42, -84, -48, -39, -11, 31, 104, 98, 121, 76, 101, 22, 27, 31, -23, 116, 121, 94, 69, 0, 0, 0 };
        this.eza = null;
        this.gza = null;
        this.y = new Insets(2, 2, 2, 2);
    }
    
    public Object b() {
        return this.bta;
    }
    
    private void j(final String text, final String s) {
        final TextArea textArea = new TextArea();
        this.setLayout(new BorderLayout());
        this.add(textArea, "Center");
        if (s != null) {
            textArea.setText(text + System.getProperty("line.separator") + s);
        }
        else {
            textArea.setText(text);
        }
        textArea.setEditable(false);
    }
    
    public void init() {
        this.I(this.getParameter("Encoding"));
        this.fza = this.getParameter("UserConfigurationCookie");
        if (this.fza == null || this.fza.length() == 0) {
            this.fza = "fncharts_cookie";
        }
        this.X(Boolean.valueOf(this.getParameter("DisplayDataRequests")));
        this.rwa = this.getParameter("AppletName");
        try {
            this.bta = JSObject.getWindow((Applet)this);
            final String parameter = this.getParameter("BuySellAction");
            if (parameter != null && parameter.length() > 0) {
                this.Qya = parameter;
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        final String parameter2 = this.getParameter("BorderColor");
        if (parameter2 != null) {
            try {
                this.Vya = Color.decode(parameter2);
            }
            catch (NumberFormatException ex2) {
                this.Vya = Color.black;
            }
        }
        System.out.println(super.a(this.dza) + "\n" + super.a(this.bza));
        this.Uqa = new switch();
        final String[] c = this.Uqa.c();
        for (int i = 0; i < c.length; ++i) {
            this.Uqa._(c[i], this.getParameter(c[i]));
        }
        if (this.getParameter("EnableWeekends") != null) {
            throw.W(Boolean.valueOf(this.getParameter("EnableWeekends")));
        }
        final Color[] _ = this._("UIBg");
        final String parameter3 = this.getParameter(super.a(this.aza));
        if (parameter3 == null || !parameter3.equals(super.a(this.bza))) {
            this.j(super.a(this.cza), super.a(this.bza));
            return;
        }
        if (_ != null) {
            this.setBackground(_[0]);
        }
        else {
            this.setBackground(SystemColor.control);
        }
        this.setLayout(new BorderLayout());
        this._xa = this._(this.Uqa);
        final Color[] _2 = this._("UIBg");
        Color control;
        if (_2 != null) {
            control = _2[0];
        }
        else {
            control = SystemColor.control;
        }
        final Color[] _3 = this._("UIText");
        Color controlText;
        if (_3 != null) {
            controlText = _3[0];
        }
        else {
            controlText = SystemColor.controlText;
        }
        (this.axa = new throws(this, this.Uqa, control, controlText)).setBackground(control);
        this.axa.setForeground(controlText);
        this._xa.setEnabled(false);
        this.add(this._xa, "Center");
        this._xa.hb();
    }
    
    protected void k() {
        synchronized (this._xa) {
            if (this.gza != null) {
                return;
            }
            this._xa.setVisible(false);
            this.setVisible(false);
            this.remove(this._xa);
            this.add(this.axa, "North");
            this.axa.setVisible(true);
            this.setVisible(true);
            this.validate();
            this.gza = new synchronized(this.Uqa.a("Caption"), this, this._xa, this.axa);
        }
    }
    
    protected void l() {
        synchronized (this._xa) {
            if (this.gza == null) {
                return;
            }
            this._xa.setVisible(false);
            this.gza.setVisible(false);
            this.gza.dispose();
            this.setVisible(false);
            this.remove(this.axa);
            this._xa.Ia.setState(false);
            this.add(this._xa, "Center");
            this._xa.setVisible(true);
            this.setVisible(true);
            this.invalidate();
            this.validate();
            this._xa.cb();
            this.gza = null;
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (this.gza == null) {
            return;
        }
        if (actionEvent.getSource() instanceof Button) {
            final Button button = (Button)actionEvent.getSource();
            if (button.getLabel().equals(this.Uqa.a("btnShowFnChartsWindow"))) {
                this.gza.setVisible(true);
            }
            else if (button.getLabel().equals(this.Uqa.a("btnCloseFnChartsWindow"))) {
                if (this.getCursor().equals(Cursor.getPredefinedCursor(3))) {
                    Toolkit.getDefaultToolkit().beep();
                }
                else {
                    this.l();
                }
            }
        }
    }
    
    public Insets getInsets() {
        return this.y;
    }
    
    public void paint(final Graphics graphics) {
        graphics.setColor(this.Vya);
        graphics.drawRoundRect(0, 0, this.getSize().width - 1, this.getSize().height - 1, 2, 2);
        super.paint(graphics);
    }
    
    private Color[] _(final String s) {
        final String parameter = this.getParameter(s + "Color");
        if (parameter == null) {
            return null;
        }
        final try try1 = new try(",");
        try1.l(parameter);
        if (try1.g() == 0) {
            return null;
        }
        final Color[] array = new Color[try1.g()];
        try {
            for (int i = 0; i < array.length; ++i) {
                array[i] = Color.decode(try1.a(i));
            }
        }
        catch (NumberFormatException ex) {
            return null;
        }
        return array;
    }
    
    private int[] b(final String s) {
        final String parameter = this.getParameter(s + "LineWidth");
        if (parameter == null) {
            return null;
        }
        final try try1 = new try(",");
        try1.l(parameter);
        if (try1.g() == 0) {
            return null;
        }
        final int[] array = new int[try1.g()];
        try {
            for (int i = 0; i < array.length; ++i) {
                array[i] = Integer.parseInt(try1.a(i));
            }
        }
        catch (NumberFormatException ex) {
            return null;
        }
        return array;
    }
    
    private var a(final String[] array) {
        final var var = new var();
        if (array == null) {
            return var;
        }
        for (int i = 0; i < array.length; ++i) {
            final Color[] _ = this._(array[i]);
            if (_ != null) {
                var.b(array[i], _);
            }
        }
        return var;
    }
    
    private volatile a(final String[] array) {
        final volatile volatile1 = new volatile();
        if (array == null) {
            return volatile1;
        }
        for (int i = 0; i < array.length; ++i) {
            final int[] b = this.b(array[i]);
            if (b != null) {
                volatile1._(array[i], b);
            }
        }
        return volatile1;
    }
    
    private void a(final while while1) {
        while1.a(this.a(FnChartsApplet.Rya));
        while1.a(this.a(while1.b()));
        while1.a(this.a(while1.n()));
    }
    
    private void b(final while while1) {
        while1._(this.a(FnChartsApplet.Sya));
        while1._(this.a(while1.b()));
        while1._(this.a(while1.n()));
    }
    
    private void b(final _ _) {
        for (int i = 0; i < FnChartsApplet.Tya.length; ++i) {
            final String parameter = this.getParameter(FnChartsApplet.Tya[i]);
            if (parameter != null && parameter.length() > 0) {
                _.b(FnChartsApplet.Tya[i], parameter);
            }
        }
    }
    
    private super _(final switch switch1) {
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
        String host = this.getDocumentBase().getHost();
        if (host == null) {
            host = "";
        }
        String hostAddress = "";
        try {
            hostAddress = InetAddress.getByName(host).getHostAddress();
        }
        catch (Exception ex3) {}
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
            final this this2 = new this();
            this2.n(s4);
            final StringBuffer sb = new StringBuffer(this2.K() * 128);
            for (int i = 0; i < this2.K(); ++i) {
                sb.append(this2.b(i));
                sb.append("\n");
            }
            string2 = sb.toString();
        }
        String parameter4 = this.getParameter("UserID");
        if ("".equals(parameter4)) {
            parameter4 = null;
        }
        String s5 = this.getParameter("UserConfigurationLoadURL");
        if ("".equals(s5)) {
            s5 = null;
        }
        if (s5 != null) {
            s5 = a.b(s5, "{UserID}", parameter4);
            if (!s5.startsWith("http:") && !s5.startsWith("ftp:") && !s5.startsWith("https:") && !s5.startsWith("file:")) {
                s5 = string + s5;
            }
        }
        String s6 = this.getParameter("UserConfigurationSaveURL");
        if ("".equals(s6)) {
            s6 = null;
        }
        if (s6 != null) {
            s6 = a.b(s6, "{UserID}", parameter4);
            if (!s6.startsWith("http:") && !s6.startsWith("ftp:") && !s6.startsWith("https:") && !s6.startsWith("file:")) {
                s6 = string + s6;
            }
        }
        String s7 = this.getParameter("UserStudiesLoadURL");
        if ("".equals(s7)) {
            s7 = null;
        }
        if (s7 != null) {
            s7 = a.b(s7, "{UserID}", parameter4);
            if (!s7.startsWith("http:") && !s7.startsWith("ftp:") && !s7.startsWith("https:") && !s7.startsWith("file:")) {
                s7 = string + s7;
            }
        }
        String s8 = this.getParameter("UserStudiesSaveURL");
        if ("".equals(s8)) {
            s8 = null;
        }
        if (s8 != null) {
            s8 = a.b(s8, "{UserID}", parameter4);
            if (!s8.startsWith("http:") && !s8.startsWith("ftp:") && !s8.startsWith("https:") && !s8.startsWith("file:")) {
                s8 = string + s8;
            }
        }
        int int3 = 12;
        final String parameter5 = this.getParameter("MaxSymbolLength");
        if (parameter5 != null) {
            try {
                int3 = Integer.parseInt(parameter5);
            }
            catch (Exception ex4) {
                int3 = 12;
            }
        }
        if (int3 < 4 || int3 > 30) {
            int3 = 12;
        }
        final Hashtable<String, Boolean> hashtable = new Hashtable<String, Boolean>();
        final String parameter6 = this.getParameter("DisabledIndicators");
        if (parameter6 != null && parameter6.length() > 0) {
            final try try1 = new try(",");
            try1.l(parameter6);
            for (int j = 0; j < try1.g(); ++j) {
                final String a = try1.a(j);
                if (a != null) {
                    hashtable.put(a, Boolean.TRUE);
                }
            }
        }
        final super super1 = new super(this, switch1, s2, s3, int2, parameter4, s5, s6, s7, s8, !Boolean.valueOf(this.getParameter("HideSymbolEntryForm")), Boolean.valueOf(this.getParameter("AutoHideVolumePanel")), int1 > 0, Boolean.valueOf(this.getParameter("EnableUserIndicatorDefinition")), s, hashtable, string2, int3, this.getParameter("Toolbar1"), this.getParameter("Toolbar2"), !Boolean.valueOf(this.getParameter("DisableChartAreaMaximization")), !Boolean.valueOf(this.getParameter("DisableUpperCaseSymbolConversion")));
        if (Boolean.valueOf(this.getParameter("DisableAppletDetachment"))) {
            super1.qb();
        }
        if (Boolean.valueOf(this.getParameter("DisableSymbolEntryFormVisibilitySwitch"))) {
            super1.rb();
        }
        final String parameter7 = this.getParameter("Timeframe");
        if (parameter7 != null) {
            int int4;
            try {
                int4 = Integer.parseInt(parameter7);
            }
            catch (NumberFormatException ex5) {
                int4 = 0;
            }
            super1.b()._().a(int4);
        }
        final String parameter8 = this.getParameter("IntradayTimeframe");
        if (parameter8 != null) {
            int int5;
            try {
                int5 = Integer.parseInt(parameter8);
            }
            catch (NumberFormatException ex6) {
                int5 = 0;
            }
            super1.b()._()._(int5);
        }
        super1._(Boolean.valueOf(this.getParameter("UseBrowserCache")));
        int int6 = 760;
        final String parameter9 = this.getParameter("ClipboardImageWidth");
        if (parameter9 != null) {
            try {
                int6 = Integer.parseInt(parameter9);
            }
            catch (NumberFormatException ex7) {}
        }
        int int7 = 540;
        final String parameter10 = this.getParameter("ClipboardImageHeight");
        if (parameter10 != null) {
            try {
                int7 = Integer.parseInt(parameter10);
            }
            catch (NumberFormatException ex8) {}
        }
        super1.g(int6, int7);
        final String parameter11 = this.getParameter("SymbolEntryFormWidth");
        if (parameter11 != null) {
            try {
                final int int8 = Integer.parseInt(parameter11);
                if (int8 > 0) {
                    super1.fa(int8);
                }
            }
            catch (NumberFormatException ex9) {}
        }
        this.a(super1.b());
        this.b(super1.b());
        this.b(super1.b().u);
        super1._(4, 1);
        super1.c();
        final Color[] _ = this._("UIBg");
        if (_ != null) {
            super1.b(super1, _[0]);
        }
        final Color[] _2 = this._("UIText");
        if (_2 != null) {
            super1._(super1, _2[0]);
        }
        final Color[] _3 = this._("ImageButtonBg");
        if (_3 != null) {
            super1.i(super1, _3[0]);
        }
        final Color[] _4 = this._("ImageButtonFrame");
        if (_4 != null) {
            super1.j(super1, _4[0]);
        }
        super1.a(super1, super1.a());
        super1.fb();
        super1.m(this.getParameter("Indicator1"));
        super1.n(this.getParameter("Indicator2"));
        super1.K(this.getParameter("PriceIndicators"));
        super1.da(int1);
        super1.n(Boolean.valueOf(this.getParameter("AutoRefreshBeep")));
        final String parameter12 = this.getParameter("DateDisplayFormat");
        if (parameter12 != null && parameter12.length() > 0) {
            final StringBuffer sb2 = new StringBuffer(parameter12.length());
            for (int k = 0; k < parameter12.length(); ++k) {
                if (parameter12.charAt(k) == 'm') {
                    sb2.append(Character.toUpperCase(parameter12.charAt(k)));
                }
                else {
                    sb2.append(parameter12.charAt(k));
                }
            }
            super1.j(sb2.toString());
        }
        final String parameter13 = this.getParameter("Scale");
        if (parameter13 != null) {
            if (parameter13.equals("LogCorrelated")) {
                super1.aa(1);
            }
            else if (parameter13.equals("LogUncorrelated")) {
                super1.aa(2);
            }
            else if (parameter13.equals("LinCorrelated")) {
                super1.aa(3);
            }
            else if (parameter13.equals("LinUncorrelated")) {
                super1.aa(4);
            }
            else if (parameter13.equals("Percent")) {
                super1.aa(5);
            }
        }
        final String parameter14 = this.getParameter("ChartType");
        if (parameter14 != null) {
            if (parameter14.equals("line")) {
                super1.e(0);
            }
            else if (parameter14.equals("ohlc")) {
                super1.e(1);
            }
            else if (parameter14.equals("candle")) {
                super1.e(2);
            }
        }
        String s9 = this.getParameter("TimeCompressionOptions");
        if (s9 == null) {
            s9 = this.getParameter("VisibleDataPeriods");
        }
        if (s9 != null) {
            super1.M(s9);
        }
        super1.U(Boolean.valueOf(this.getParameter("DataIsTimeCompressed")));
        String s10 = this.getParameter("TimeCompression");
        if (s10 == null) {
            s10 = this.getParameter("DataPeriod");
        }
        if (s10 != null) {
            final int e = c.e(s10);
            if (e != -1) {
                super1.ba(e);
            }
        }
        String s11 = this.getParameter("UserIndicatorsSaveURL");
        if (s11 != null && s11.length() > 0) {
            s11 = a.b(s11, "{UserID}", parameter4);
            if (!s11.startsWith("http:") && !s11.startsWith("ftp:") && !s11.startsWith("https:") && !s11.startsWith("file:")) {
                s11 = string + s11;
            }
        }
        super1.b()._().D(s11);
        String s12 = this.getParameter("UserIndicatorsLoadURL");
        if (s12 != null && s12.length() > 0) {
            s12 = a.b(s12, "{UserID}", parameter4);
            if (!s12.startsWith("http:") && !s12.startsWith("ftp:") && !s12.startsWith("https:") && !s12.startsWith("file:")) {
                s12 = string + s12;
            }
        }
        super1.b()._().E(s12);
        final String parameter15 = this.getParameter("DateFormat");
        if (parameter15 != null) {
            super1.b()._().x(parameter15);
        }
        final String parameter16 = this.getParameter("DecimalPlaces");
        if (parameter16 != null) {
            int int9 = -1;
            try {
                int9 = Integer.parseInt(parameter16);
            }
            catch (NumberFormatException ex10) {}
            super1.b()._().Y(int9);
        }
        String parameter17 = "";
        if (parameter17 == null || parameter17.length() == 0) {
            parameter17 = this.getParameter(super.a(this.Yya) + 1);
        }
        if (parameter17 != null && parameter17.length() > 2) {
            int int10 = -1;
            try {
                int10 = Integer.parseInt(parameter17.substring(0, 2), 16);
                if (int10 > 200) {
                    int10 = -1;
                }
            }
            catch (NumberFormatException ex11) {}
            super1.b().z(parameter17.substring(2));
            if (int10 == -1) {
                super1.b().B(hostAddress);
            }
            else if (int10 > host.length() || int10 < 4) {
                super1.b().B(host);
            }
            else {
                super1.b().B(host.substring(host.length() - int10));
            }
        }
        String parameter18 = "";
        if (parameter18 == null || parameter18.length() == 0) {
            parameter18 = this.getParameter(super.a(this.Yya) + 2);
        }
        if (parameter18 != null && parameter18.length() > 2) {
            int int11 = -1;
            try {
                int11 = Integer.parseInt(parameter18.substring(0, 2), 16);
                if (int11 > 200) {
                    int11 = -1;
                }
            }
            catch (NumberFormatException ex12) {}
            super1.b().A(parameter18.substring(2));
            if (int11 == -1) {
                super1.b().C(hostAddress);
            }
            else if (int11 > host.length() || int11 < 4) {
                super1.b().C(host);
            }
            else {
                super1.b().C(host.substring(host.length() - int11));
            }
        }
        final String parameter19 = this.getParameter("DisplayRange");
        if (parameter19 != null) {
            if (parameter19.equals("1month")) {
                super1.ca(10);
            }
            else if (parameter19.equals("3months")) {
                super1.ca(11);
            }
            else if (parameter19.equals("6months")) {
                super1.ca(12);
            }
            else if (parameter19.equals("1year")) {
                super1.ca(13);
            }
            else if (parameter19.equals("2years")) {
                super1.ca(14);
            }
            else if (parameter19.equals("3years")) {
                super1.ca(15);
            }
            else if (parameter19.equals("5years")) {
                super1.ca(16);
            }
            else if (parameter19.equals("10years")) {
                super1.ca(17);
            }
            else if (parameter19.equals("all")) {
                super1.ca(0);
            }
        }
        if (this.getParameter("ShowGrid") != null) {
            super1.b().P(Boolean.valueOf(this.getParameter("ShowGrid")));
        }
        if (this.getParameter("ShowTitle") != null) {
            super1.b().N(Boolean.valueOf(this.getParameter("ShowTitle")));
        }
        if (this.getParameter("ShowLegend") != null) {
            super1.b().O(Boolean.valueOf(this.getParameter("ShowLegend")));
        }
        if (this.getParameter("ShowCrosshair") != null) {
            super1.b().M(Boolean.valueOf(this.getParameter("ShowCrosshair")));
        }
        if (this.getParameter("ShowBuySellSignals") != null) {
            super1.b().Q(Boolean.valueOf(this.getParameter("ShowBuySellSignals")));
        }
        super1.Y(Boolean.valueOf(this.getParameter("AutoLoadStudies")));
        final String parameter20 = this.getParameter("EnableSymbolListModification");
        if (parameter20 != null) {
            super1.ba(Boolean.valueOf(parameter20));
        }
        super1.aa(Boolean.valueOf(this.getParameter("LoadUserConfigurationOnStartup")));
        super1.b().T(Boolean.valueOf(this.getParameter("RestrictToolsToChartFg")));
        super1.b(this.getParameter("Symbol"), this.getParameter("SecondarySymbol"), this.getParameter("FavoriteSymbols"));
        super1.ca(Boolean.valueOf(this.getParameter("HalfPagePortraitPrintMode")));
        return super1;
    }
    
    public boolean V() {
        if (this.Wya == null) {
            this.Wya = this.getParameter("BuyURL");
        }
        if (this.Xya == null) {
            this.Xya = this.getParameter("SellURL");
        }
        return !this.f(this.Wya) && !this.f(this.Xya);
    }
    
    private boolean f(final String s) {
        return s == null || s.length() == 0;
    }
    
    public void e(final String s) {
        if (this.Wya == null) {
            this.Wya = this.getParameter("BuyURL");
        }
        if (this.f(this.Wya)) {
            return;
        }
        this.k(this.Wya, s);
    }
    
    public void f(final String s) {
        if (this.Xya == null) {
            this.Xya = this.getParameter("SellURL");
        }
        if (this.f(this.Xya)) {
            return;
        }
        this.k(this.Xya, s);
    }
    
    public void k(String s, final String s2) {
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
        s = this._(s, s2);
        if (this.eza == null) {
            this.eza = this.getParameter("target");
        }
        if (this.f(this.eza)) {
            this.eza = "_blank";
        }
        try {
            if (this.bta != null && this.Qya != null) {
                ((JSObject)this.bta).call(this.Qya, (Object[])new String[] { s, this.eza });
            }
            else {
                this.getAppletContext().showDocument(new URL(s), this.eza);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private String _(final String s, String encode) {
        final String s2 = "{SYMBOL}";
        final String s3 = "{symbol}";
        final String s4 = "{Symbol}";
        if (encode == null) {
            encode = "";
        }
        encode = URLEncoder.encode(encode);
        return a.b(a.b(a.b(s, s2, encode.toUpperCase()), s3, encode.toLowerCase()), s4, encode);
    }
    
    public void destroy() {
        if (this._xa != null) {
            this._xa.gb();
            this._xa.b((Component)this._xa);
        }
    }
    
    public String getSelectedStockName() {
        if (this._xa == null) {
            return "";
        }
        synchronized (this._xa) {
            return this._xa.b()._()._();
        }
    }
    
    public int getParam(final int n) {
        return this._xa.b().g(n - 1);
    }
    
    public int getValuesLength(final int n) {
        if (n == 1) {
            return this._xa.b().ea().length;
        }
        if (n == 2) {
            return this._xa.b().fa().length;
        }
        if (n == 3) {
            return this._xa.b().ga().length;
        }
        if (n == 4) {
            return this._xa.b().ha().length;
        }
        if (n == 5) {
            return this._xa.b().ia().length;
        }
        if (n == 6) {
            return this._xa.b().o().length;
        }
        if (n == 7) {
            return this._xa.b().p().length;
        }
        return 0;
    }
    
    public double[] getValues(final int n) {
        if (n == 1) {
            return this._xa.b().ea();
        }
        if (n == 2) {
            return this._xa.b().fa();
        }
        if (n == 3) {
            return this._xa.b().ga();
        }
        if (n == 4) {
            return this._xa.b().ha();
        }
        if (n == 5) {
            return this._xa.b().ia();
        }
        if (n == 6) {
            return this._xa.b().o();
        }
        if (n == 7) {
            return this._xa.b().p();
        }
        return new double[0];
    }
    
    public double getValue(final int n, final int n2) {
        if (n == 1) {
            return this._xa.b().ea()[n2];
        }
        if (n == 2) {
            return this._xa.b().fa()[n2];
        }
        if (n == 3) {
            return this._xa.b().ga()[n2];
        }
        if (n == 4) {
            return this._xa.b().ha()[n2];
        }
        if (n == 5) {
            return this._xa.b().ia()[n2];
        }
        if (n == 6) {
            return this._xa.b().o()[n2];
        }
        if (n == 7) {
            return this._xa.b().p()[n2];
        }
        return 0.0;
    }
    
    public void addGraphData(final double[] array, final int n) {
        for (int i = 0; i < array.length; ++i) {
            if (Double.isNaN(array[i])) {
                array[i] = 0.0;
            }
        }
        this._xa.b().b(array, n);
    }
    
    public void addHorizLine(final double n) {
        if (!Double.isNaN(n)) {
            this._xa.b().a(n);
        }
    }
    
    public void addBuySignal(final int n) {
        this._xa.b().Z(n);
    }
    
    public void addSellSignal(final int n) {
        this._xa.b()._a(n);
    }
    
    public void setJSExceptionMessage(final String s) {
        this._xa.b().s(s);
    }
    
    public void beginGraphData(final int n) {
        this.oza = new double[n];
    }
    
    public void addGraphDataPoint(final int n, double n2) {
        if (Double.isNaN(n2)) {
            n2 = 0.0;
        }
        this.oza[n] = n2;
    }
    
    public void endGraphData(final int n) {
        this._xa.b().b(this.oza, n);
        this.oza = null;
    }
    
    public double[] calculateMax(final double[] array, final int n) {
        final double[] array2 = new double[array.length];
        e.calculateMax(array, array2, 0, n);
        return array2;
    }
    
    public double[] calculateMin(final double[] array, final int n) {
        final double[] array2 = new double[array.length];
        e.calculateMin(array, array2, 0, n);
        return array2;
    }
    
    public double[] calculateStdDev(final double[] array, final int n) {
        final double[] array2 = new double[array.length];
        e.calculateStdDev(array, array2, 0, n);
        return array2;
    }
    
    public double[] calculateSimpleAvg(final double[] array, final int n) {
        final double[] array2 = new double[array.length];
        e.calculateSimpleAvg(array, array2, 0, n);
        return array2;
    }
    
    public double[] calculateExpAvg(final double[] array, final int n) {
        final double[] array2 = new double[array.length];
        e.calculateExpAvg(array, array2, 0, n);
        return array2;
    }
    
    public void setBrowserType(final String s) {
        if (this._xa != null) {
            this._xa.b()._().L(s != null && s.equals("msie"));
        }
    }
    
    public String C() {
        return this.rwa;
    }
    
    static {
        Rya = new String[] { "ChartBg", "ChartFg", "Grid", "ChartTitle", "ChartTitleBg", "ChartTitleFrame", "Axis", "AxisLabel", "AxisLabelBg", "AxisLabelFrame", "Price", "BullishCandle", "BearishCandle", "BgPrice", "ExtraPrice", "Volume", "OpenInterest", "InfoLine", "InfoLineBg", "InfoLineFrame", "Crosshair", "ChartTopLeftFrame", "ChartBottomRightFrame", "IndicatorSpecificLine", "Indicator", "TrendLine", "ImageButtonBg", "ImageButtonFrame", "UIText", "ProgressBar", "UIBg", "BuySignal", "SellSignal" };
        Sya = new String[] { "Price", "BgPrice", "ExtraPrice", "OpenInterest", "Indicator", "TrendLine" };
        Tya = new String[] { "InfoLineFontName", "InfoLineFontSize", "InfoLineFontStyle", "AxisLabelFontName", "AxisLabelFontSize", "AxisLabelFontStyle", "ChartTitleFontName", "ChartTitleFontSize", "ChartTitleFontStyle", "StatusBarFontName", "StatusBarFontSize", "StatusBarFontStyle", "UIFontName", "UIFontSize", "UIFontStyle", "LegendFontName", "LegendFontSize", "LegendFontStyle" };
    }
}
