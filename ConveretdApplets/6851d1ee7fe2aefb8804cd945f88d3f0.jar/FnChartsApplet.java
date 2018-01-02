import java.io.OutputStream;
import java.io.ByteArrayOutputStream;
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
    public static final boolean Gua = true;
    public static final boolean Hua = true;
    private String bsa;
    private Object Ana;
    private String Iua;
    private String Jua;
    private String Kua;
    private boolean Qpa;
    static final String[] Lua;
    static final String[] Mua;
    static final String[] Nua;
    private static final String Oua;
    private static final String Pua;
    private static final String Qua;
    private static final String Rua;
    private static final String Sua;
    boolean Tua;
    Color Uua;
    private n Osa;
    private String Vua;
    private String Wua;
    private final byte[] Xua;
    private final String Yua = "";
    private final String Zua = "";
    private final byte[] _va;
    private final byte[] ava;
    private final byte[] bva;
    private final byte[] cva;
    private final byte[] dva;
    private final byte[] eva;
    private final byte[] fva;
    private String gva;
    private String hva;
    private o m;
    private Panel Psa;
    p iva;
    private Insets hb;
    private boolean jva;
    private static final int kva = 1;
    private static final int lva = 2;
    private static final int mva = 3;
    private static final int nva = 4;
    private static final int ova = 5;
    private static final int pva = 6;
    private static final int qva = 7;
    private double[] rva;
    
    public FnChartsApplet() {
        this.bsa = null;
        this.Ana = null;
        this.Iua = null;
        this.Jua = null;
        this.Kua = null;
        this.Qpa = false;
        this.Tua = true;
        this.Uua = null;
        this.Vua = null;
        this.Wua = null;
        this.Xua = new byte[] { 0, 0, 0, 0, 0, 0, -13, -55, 76, 78, -51, 43, 78, -11, 78, -83, 4, 0, -30, -69, -50, -35, 10, 0, 0, 0 };
        this._va = new byte[] { 0, 0, 0, 0, 0, 0, 115, -50, 47, -88, 44, -54, 76, -49, 40, 1, 0, 80, -105, 112, -70, 9, 0, 0, 0 };
        this.ava = new byte[] { 0, 0, 0, 0, 0, 0, 115, -50, 47, -88, 44, -54, 76, -49, 40, 81, -48, 72, -42, 84, 48, -76, -76, -76, -44, 53, 50, 48, -80, 80, 8, 119, 81, 8, -10, 119, 11, 9, 119, 12, 114, 85, -56, 40, 41, 41, -80, -46, -41, 47, 47, 47, -41, 43, 79, 41, -50, 79, 43, 41, 79, 44, 74, -43, 75, -50, -49, 5, 0, 105, 75, -106, 109, 61, 0, 0, 0 };
        this.bva = new byte[] { 0, 0, 0, 0, 0, 0, 69, -52, 49, 14, -128, 32, 12, 70, -31, -85, -4, 27, -101, 7, 112, 117, 114, -9, 2, 24, 43, -112, 0, 37, 109, -47, 120, 123, 113, 114, 124, 121, -55, -73, -42, -53, -25, 116, -128, 5, 37, -87, -90, 26, -32, 22, 110, -113, -92, 16, -51, -95, 121, -15, -123, -116, 100, -62, 22, -23, 79, -108, -82, -122, -99, -96, 100, 48, -122, -115, 121, 114, -50, 124, 127, -62, 32, 59, -51, 47, 101, -53, -111, 27, 91, 0, 0, 0 };
        this.cva = new byte[] { 0, 0, 0, 0, 0, 0, 13, -54, -69, 9, -64, 48, 12, 5, -64, 85, -34, 4, 94, 34, -112, 58, 77, 6, -112, -111, -116, 5, 65, 2, 125, -102, 76, -97, 92, 125, -89, 29, -101, -94, 18, 87, -8, -110, 76, 117, -93, 103, -32, 54, -22, -38, 30, -6, 10, -93, 83, -32, 1, -42, -84, -48, -39, -11, 31, 104, 98, 121, 76, 101, 22, 27, 31, -23, 116, 121, 94, 69, 0, 0, 0 };
        this.dva = new byte[] { 0, 0, 0, 0, 0, 0, 43, 47, 47, -41, 43, 79, 41, -50, 79, 43, 41, 79, 44, 74, -43, 75, -50, -49, 5, 0, 81, -68, -126, -85, 18, 0, 0, 0 };
        this.eva = new byte[] { 0, 0, 0, 0, 0, 0, -53, 40, 41, 41, -80, -46, -41, 47, 47, 47, -41, 43, 79, 41, -50, 79, 43, 41, 79, 44, 74, -43, 75, -50, -49, 5, 0, 81, -24, 124, 63, 25, 0, 0, 0 };
        this.fva = new byte[] { 0, 0, 0, 0, 0, 0, -13, -52, 43, 73, 45, 74, 76, 46, -55, 44, 75, 85, 8, 46, -55, 79, -50, 86, 112, -50, 72, 44, 42, 41, 86, 40, 40, -54, 47, -53, 76, 73, 77, 81, 72, -86, 84, 8, 119, 81, 8, -10, 119, 11, 9, 119, 12, 114, -83, 9, 74, 77, -52, 81, 8, -55, -52, 37, 78, 57, 0, 57, 115, -11, 79, 95, 0, 0, 0 };
        this.gva = null;
        this.iva = null;
        this.hb = new Insets(2, 2, 2, 2);
        this.jva = false;
    }
    
    public Object _() {
        return this.Ana;
    }
    
    private void i(final String text, final String s) {
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
        q.M(this.getParameter("Encoding"));
        this.hva = this.getParameter("UserConfigurationCookie");
        if (this.hva == null || this.hva.length() == 0) {
            this.hva = "fncharts_cookie";
        }
        q.R(Boolean.valueOf(this.getParameter("DisplayDataRequests")));
        this.bsa = this.getParameter("AppletName");
        try {
            this.Ana = JSObject.getWindow((Applet)this);
            final String parameter = this.getParameter("BuySellAction");
            if (parameter != null && parameter.length() > 0) {
                this.Iua = parameter;
            }
            final String parameter2 = this.getParameter("AppletInitializedCallback");
            if (parameter2 != null && parameter2.length() > 0) {
                this.Jua = parameter2;
            }
            final String parameter3 = this.getParameter("SymbolSelectionCallback");
            if (parameter3 != null && parameter3.length() > 0) {
                this.Kua = parameter3;
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        final String parameter4 = this.getParameter("BorderColor");
        if (parameter4 != null) {
            try {
                this.Uua = Color.decode(parameter4);
            }
            catch (NumberFormatException ex2) {
                this.Uua = Color.black;
            }
        }
        System.out.println(n._(this.cva) + "\n" + n._(this.ava));
        this.m = new o();
        final String[] n = this.m.n();
        for (int i = 0; i < n.length; ++i) {
            String s = this.getParameter(n[i]);
            if (s == null) {
                if (n[i].equals("btnStockListSwitch_Info")) {
                    s = this.getParameter("btnStockListVisible_Info");
                }
                else if (n[i].equals("btnVolumeSwitch_Info")) {
                    s = this.getParameter("btnVolumeVisible_Info");
                }
                else if (n[i].equals("btnAutoRefreshSwitch_Info")) {
                    s = this.getParameter("btnAutoRefreshActive_Info");
                }
            }
            this.m._(n[i], s);
        }
        if (this.getParameter("EnableWeekends") != null) {
            r.Q(Boolean.valueOf(this.getParameter("EnableWeekends")));
        }
        final Color[] b = this.b("UIBg");
        final String parameter5 = this.getParameter(n._(this._va));
        if (parameter5 == null || !parameter5.equals(n._(this.ava))) {
            this.i(n._(this.bva), n._(this.ava));
            return;
        }
        if (b != null) {
            this.setBackground(b[0]);
        }
        else {
            this.setBackground(SystemColor.control);
        }
        this.setLayout(new BorderLayout());
        this.Osa = this.b(this.m);
        final Color[] b2 = this.b("UIBg");
        Color control;
        if (b2 != null) {
            control = b2[0];
        }
        else {
            control = SystemColor.control;
        }
        final Color[] b3 = this.b("UIText");
        Color controlText;
        if (b3 != null) {
            controlText = b3[0];
        }
        else {
            controlText = SystemColor.controlText;
        }
        (this.Psa = new s(this, this.m, control, controlText)).setBackground(control);
        this.Psa.setForeground(controlText);
        this.Osa.setEnabled(false);
        this.add(this.Osa, "Center");
        this.Osa.Rb();
    }
    
    protected void k() {
        synchronized (this.Osa) {
            if (this.iva != null) {
                return;
            }
            this.Osa.setVisible(false);
            this.setVisible(false);
            this.remove(this.Osa);
            this.add(this.Psa, "North");
            this.Psa.setVisible(true);
            this.setVisible(true);
            this.validate();
            this.iva = new p(this.m.b("Caption"), this, this.Osa, this.Psa);
        }
    }
    
    protected void l() {
        synchronized (this.Osa) {
            if (this.iva == null) {
                return;
            }
            this.Osa.setVisible(false);
            this.iva.setVisible(false);
            this.iva.dispose();
            this.setVisible(false);
            this.remove(this.Psa);
            this.Osa.Na.setState(false);
            this.add(this.Osa, "Center");
            this.Osa.setVisible(true);
            this.setVisible(true);
            this.invalidate();
            this.validate();
            this.Osa.Kb();
            this.iva = null;
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (this.iva == null) {
            return;
        }
        if (actionEvent.getSource() instanceof Button) {
            final Button button = (Button)actionEvent.getSource();
            if (button.getLabel().equals(this.m.b("btnShowFnChartsWindow"))) {
                this.iva.setVisible(true);
            }
            else if (button.getLabel().equals(this.m.b("btnCloseFnChartsWindow"))) {
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
        return this.hb;
    }
    
    public void paint(final Graphics graphics) {
        graphics.setColor(this.Uua);
        graphics.drawRoundRect(0, 0, this.getSize().width - 1, this.getSize().height - 1, 2, 2);
        super.paint(graphics);
    }
    
    private Color[] b(final String s) {
        final String parameter = this.getParameter(s + "Color");
        if (parameter == null) {
            return null;
        }
        final u u = new u(",");
        u.m(parameter);
        if (u.a() == 0) {
            return null;
        }
        final Color[] array = new Color[u.a()];
        try {
            for (int i = 0; i < array.length; ++i) {
                array[i] = Color.decode(u.b(i));
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
        final u u = new u(",");
        u.m(parameter);
        if (u.a() == 0) {
            return null;
        }
        final int[] array = new int[u.a()];
        try {
            for (int i = 0; i < array.length; ++i) {
                array[i] = Integer.parseInt(u.b(i));
            }
        }
        catch (NumberFormatException ex) {
            return null;
        }
        return array;
    }
    
    private v b(final String[] array) {
        final v v = new v();
        if (array == null) {
            return v;
        }
        for (int i = 0; i < array.length; ++i) {
            final Color[] b = this.b(array[i]);
            if (b != null) {
                v.b(array[i], b);
            }
        }
        return v;
    }
    
    private abstract _(final String[] array) {
        final abstract abstract1 = new abstract();
        if (array == null) {
            return abstract1;
        }
        for (int i = 0; i < array.length; ++i) {
            final int[] b = this.b(array[i]);
            if (b != null) {
                abstract1.a(array[i], b);
            }
        }
        return abstract1;
    }
    
    private void a(final break break1) {
        break1._(this.b(FnChartsApplet.Lua));
        break1._(this.b(break1._()));
        break1._(this.b(break1.e()));
    }
    
    private void b(final break break1) {
        break1.b(this._(FnChartsApplet.Mua));
        break1.b(this._(break1._()));
        break1.b(this._(break1.e()));
    }
    
    private void a(final case case1) {
        for (int i = 0; i < FnChartsApplet.Nua.length; ++i) {
            final String parameter = this.getParameter(FnChartsApplet.Nua[i]);
            if (parameter != null && parameter.length() > 0) {
                case1.b(FnChartsApplet.Nua[i], parameter);
            }
        }
    }
    
    private n b(final o o) {
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
        int int2 = Integer.MAX_VALUE;
        final String parameter2 = this.getParameter("MaxAutoRefreshCount");
        if (parameter2 != null) {
            try {
                int2 = Integer.parseInt(parameter2);
            }
            catch (Exception ex2) {
                int2 = Integer.MAX_VALUE;
            }
        }
        String s = "c_";
        final String parameter3 = this.getParameter("IconType");
        if (parameter3 != null) {
            if (parameter3.equals("COLOR")) {
                s = "c_";
            }
            else if (parameter3.equals("BW")) {
                s = "bw_";
            }
        }
        int int3 = 0;
        final String parameter4 = this.getParameter("UnusedCacheRecycleTime");
        if (parameter4 != null) {
            try {
                int3 = Integer.parseInt(parameter4);
                if (int3 < 0) {
                    int3 = 0;
                }
            }
            catch (Exception ex3) {
                int3 = 0;
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
        catch (Exception ex4) {}
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
            final q q = new q();
            q.f(s4);
            final StringBuffer sb = new StringBuffer(q.l() * 128);
            for (int i = 0; i < q.l(); ++i) {
                sb.append(q._(i));
                sb.append("\n");
            }
            string2 = sb.toString();
        }
        String parameter5 = this.getParameter("UserID");
        if ("".equals(parameter5)) {
            parameter5 = null;
        }
        String s5 = this.getParameter("UserConfigurationLoadURL");
        if ("".equals(s5)) {
            s5 = null;
        }
        if (s5 != null) {
            s5 = catch.a(s5, "{UserID}", parameter5);
            if (!s5.startsWith("http:") && !s5.startsWith("ftp:") && !s5.startsWith("https:") && !s5.startsWith("file:")) {
                s5 = string + s5;
            }
        }
        String s6 = this.getParameter("UserConfigurationSaveURL");
        if ("".equals(s6)) {
            s6 = null;
        }
        if (s6 != null) {
            s6 = catch.a(s6, "{UserID}", parameter5);
            if (!s6.startsWith("http:") && !s6.startsWith("ftp:") && !s6.startsWith("https:") && !s6.startsWith("file:")) {
                s6 = string + s6;
            }
        }
        String s7 = this.getParameter("UserStudiesLoadURL");
        if ("".equals(s7)) {
            s7 = null;
        }
        if (s7 != null) {
            s7 = catch.a(s7, "{UserID}", parameter5);
            if (!s7.startsWith("http:") && !s7.startsWith("ftp:") && !s7.startsWith("https:") && !s7.startsWith("file:")) {
                s7 = string + s7;
            }
        }
        String s8 = this.getParameter("UserStudiesSaveURL");
        if ("".equals(s8)) {
            s8 = null;
        }
        if (s8 != null) {
            s8 = catch.a(s8, "{UserID}", parameter5);
            if (!s8.startsWith("http:") && !s8.startsWith("ftp:") && !s8.startsWith("https:") && !s8.startsWith("file:")) {
                s8 = string + s8;
            }
        }
        int int4 = 12;
        final String parameter6 = this.getParameter("MaxSymbolLength");
        if (parameter6 != null) {
            try {
                int4 = Integer.parseInt(parameter6);
            }
            catch (Exception ex5) {
                int4 = 12;
            }
        }
        if (int4 < 4 || int4 > 50) {
            int4 = 12;
        }
        final Hashtable<String, Boolean> hashtable = new Hashtable<String, Boolean>();
        final String parameter7 = this.getParameter("DisabledIndicators");
        if (parameter7 != null && parameter7.length() > 0) {
            final u u = new u(",");
            u.m(parameter7);
            for (int j = 0; j < u.a(); ++j) {
                final String b = u.b(j);
                if (b != null) {
                    hashtable.put(b, Boolean.TRUE);
                }
            }
        }
        final n n = new n(this, o, s2, s3, int3, parameter5, s5, s6, s7, s8, !Boolean.valueOf(this.getParameter("HideSymbolEntryForm")), Boolean.valueOf(this.getParameter("AutoHideVolumePanel")), !Boolean.valueOf(this.getParameter("HideVolumePanel")), int1 > 0, Boolean.valueOf(this.getParameter("EnableUserIndicatorDefinition")), s, hashtable, string2, int4, this.getParameter("Toolbar1"), this.getParameter("Toolbar2"), !Boolean.valueOf(this.getParameter("DisableChartAreaMaximization")), !Boolean.valueOf(this.getParameter("DisableUpperCaseSymbolConversion")), this.getParameter("FirstTransactionTime"), this.getParameter("Categories"));
        if (Boolean.valueOf(this.getParameter("DisableAppletDetachment"))) {
            n.bc();
        }
        if (Boolean.valueOf(this.getParameter("DisableSymbolEntryFormVisibilitySwitch"))) {
            n.cc();
        }
        final String parameter8 = this.getParameter("Timeframe");
        if (parameter8 != null) {
            int int5;
            try {
                int5 = Integer.parseInt(parameter8);
            }
            catch (NumberFormatException ex6) {
                int5 = 0;
            }
            n.a()._().b(int5);
        }
        final String parameter9 = this.getParameter("IntradayTimeframe");
        if (parameter9 != null) {
            int int6;
            try {
                int6 = Integer.parseInt(parameter9);
            }
            catch (NumberFormatException ex7) {
                int6 = 0;
            }
            n.a()._().a(int6);
        }
        n.setUseCache(Boolean.valueOf(this.getParameter("UseBrowserCache")));
        int int7 = 760;
        final String parameter10 = this.getParameter("ClipboardImageWidth");
        if (parameter10 != null) {
            try {
                int7 = Integer.parseInt(parameter10);
            }
            catch (NumberFormatException ex8) {}
        }
        int int8 = 540;
        final String parameter11 = this.getParameter("ClipboardImageHeight");
        if (parameter11 != null) {
            try {
                int8 = Integer.parseInt(parameter11);
            }
            catch (NumberFormatException ex9) {}
        }
        n.k(int7, int8);
        final String parameter12 = this.getParameter("SymbolEntryFormWidth");
        if (parameter12 != null) {
            try {
                final int int9 = Integer.parseInt(parameter12);
                if (int9 > 0) {
                    n.o(int9);
                }
            }
            catch (NumberFormatException ex10) {}
        }
        this.a(n.a());
        this.b(n.a());
        this.a(n.a().toa);
        n._(4, 1);
        n.w();
        final Color[] b2 = this.b("UIBg");
        if (b2 != null) {
            n._(n, b2[0]);
        }
        final Color[] b3 = this.b("UIText");
        if (b3 != null) {
            n.a(n, b3[0]);
        }
        final Color[] b4 = this.b("ImageButtonBg");
        if (b4 != null) {
            n.m(n, b4[0]);
        }
        final Color[] b5 = this.b("ImageButtonFrame");
        if (b5 != null) {
            n.n(n, b5[0]);
        }
        n._(n, n.a());
        n.Ob();
        n.n(this.getParameter("Indicator1"));
        n.c(this.getParameter("Indicator2"));
        n.O(this.getParameter("PriceIndicators"));
        n.ha(int2);
        n.ga(int1);
        n.a((boolean)Boolean.valueOf(this.getParameter("AutoRefreshBeep")));
        final String parameter13 = this.getParameter("DateDisplayFormat");
        if (parameter13 != null && parameter13.length() > 0) {
            final StringBuffer sb2 = new StringBuffer(parameter13.length());
            for (int k = 0; k < parameter13.length(); ++k) {
                if (parameter13.charAt(k) == 'm') {
                    sb2.append(Character.toUpperCase(parameter13.charAt(k)));
                }
                else {
                    sb2.append(parameter13.charAt(k));
                }
            }
            n.B(sb2.toString());
        }
        final String parameter14 = this.getParameter("Scale");
        if (parameter14 != null) {
            if (parameter14.equals("LogCorrelated")) {
                n.da(1);
            }
            else if (parameter14.equals("LogUncorrelated")) {
                n.da(2);
            }
            else if (parameter14.equals("LinCorrelated")) {
                n.da(3);
            }
            else if (parameter14.equals("LinUncorrelated")) {
                n.da(4);
            }
            else if (parameter14.equals("Percent")) {
                n.da(5);
            }
        }
        final String parameter15 = this.getParameter("ChartType");
        if (parameter15 != null) {
            if (parameter15.equals("line")) {
                n.c(0);
            }
            else if (parameter15.equals("ohlc")) {
                n.c(1);
            }
            else if (parameter15.equals("candle")) {
                n.c(2);
            }
        }
        String s9 = this.getParameter("TimeCompressionOptions");
        if (s9 == null) {
            s9 = this.getParameter("VisibleDataPeriods");
        }
        if (s9 != null) {
            n.Q(s9);
        }
        n.O(Boolean.valueOf(this.getParameter("DataIsTimeCompressed")));
        String s10 = this.getParameter("TimeCompression");
        if (s10 == null) {
            s10 = this.getParameter("DataPeriod");
        }
        if (s10 != null) {
            final int l = const.l(s10);
            if (l != -1) {
                n.ea(l);
            }
        }
        String s11 = this.getParameter("UserIndicatorsSaveURL");
        if (s11 != null && s11.length() > 0) {
            s11 = catch.a(s11, "{UserID}", parameter5);
            if (!s11.startsWith("http:") && !s11.startsWith("ftp:") && !s11.startsWith("https:") && !s11.startsWith("file:")) {
                s11 = string + s11;
            }
        }
        n.a()._().H(s11);
        String s12 = this.getParameter("UserIndicatorsLoadURL");
        if (s12 != null && s12.length() > 0) {
            s12 = catch.a(s12, "{UserID}", parameter5);
            if (!s12.startsWith("http:") && !s12.startsWith("ftp:") && !s12.startsWith("https:") && !s12.startsWith("file:")) {
                s12 = string + s12;
            }
        }
        n.a()._().I(s12);
        final String parameter16 = this.getParameter("DateFormat");
        if (parameter16 != null) {
            n.a()._().x(parameter16);
        }
        final String parameter17 = this.getParameter("DecimalPlaces");
        if (parameter17 != null) {
            int int10 = -1;
            try {
                int10 = Integer.parseInt(parameter17);
            }
            catch (NumberFormatException ex11) {}
            n.a()._().aa(int10);
        }
        String parameter18 = "";
        if (parameter18 == null || parameter18.length() == 0) {
            parameter18 = this.getParameter(n._(this.Xua) + 1);
        }
        if (parameter18 != null && parameter18.length() > 2) {
            int int11 = -1;
            try {
                int11 = Integer.parseInt(parameter18.substring(0, 2), 16);
                if (int11 > 200) {
                    int11 = -1;
                }
                else {
                    final int round = Math.round(Integer.parseInt(n.M()) / 100.0f);
                    if (round >= 7) {
                        int11 -= round;
                    }
                }
            }
            catch (NumberFormatException ex12) {}
            n._().D(parameter18.substring(2));
            if (int11 == -1) {
                n._().F(hostAddress);
            }
            else if (int11 > host.length() || int11 < 4) {
                n._().F(host);
            }
            else {
                n._().F(host.substring(host.length() - int11));
            }
        }
        String parameter19 = "";
        if (parameter19 == null || parameter19.length() == 0) {
            parameter19 = this.getParameter(n._(this.Xua) + 2);
        }
        if (parameter19 != null && parameter19.length() > 2) {
            int int12 = -1;
            try {
                int12 = Integer.parseInt(parameter19.substring(0, 2), 16);
                if (int12 > 200) {
                    int12 = -1;
                }
                else {
                    final int round2 = Math.round(Integer.parseInt(n.M()) / 100.0f);
                    if (round2 >= 7) {
                        int12 -= round2;
                    }
                }
            }
            catch (NumberFormatException ex13) {}
            n._().E(parameter19.substring(2));
            if (int12 == -1) {
                n._().G(hostAddress);
            }
            else if (int12 > host.length() || int12 < 4) {
                n._().G(host);
            }
            else {
                n._().G(host.substring(host.length() - int12));
            }
        }
        n._().L(this.Qpa);
        final String parameter20 = this.getParameter("DisplayRange");
        if (parameter20 != null) {
            if (parameter20.equals("1month")) {
                n.fa(10);
            }
            else if (parameter20.equals("3months")) {
                n.fa(11);
            }
            else if (parameter20.equals("6months")) {
                n.fa(12);
            }
            else if (parameter20.equals("1year")) {
                n.fa(13);
            }
            else if (parameter20.equals("2years")) {
                n.fa(14);
            }
            else if (parameter20.equals("3years")) {
                n.fa(15);
            }
            else if (parameter20.equals("5years")) {
                n.fa(16);
            }
            else if (parameter20.equals("10years")) {
                n.fa(17);
            }
            else if (parameter20.equals("all")) {
                n.fa(0);
            }
        }
        if (this.getParameter("ShowGrid") != null) {
            n._().H(Boolean.valueOf(this.getParameter("ShowGrid")));
        }
        if (this.getParameter("ShowTitle") != null) {
            n._().E(Boolean.valueOf(this.getParameter("ShowTitle")));
        }
        if (this.getParameter("ShowLegend") != null) {
            n._().F(Boolean.valueOf(this.getParameter("ShowLegend")));
        }
        if (this.getParameter("ShowAnnotations") != null) {
            n._().G(Boolean.valueOf(this.getParameter("ShowAnnotations")));
        }
        if (this.getParameter("ShowCrosshair") != null) {
            n._().D(Boolean.valueOf(this.getParameter("ShowCrosshair")));
        }
        if (this.getParameter("ShowBuySellSignals") != null) {
            n._().I(Boolean.valueOf(this.getParameter("ShowBuySellSignals")));
        }
        if (Boolean.valueOf(this.getParameter("HideBuySellSignalsMenu"))) {
            n._().P();
        }
        n._().C(catch.a(this.getParameter("FooterText"), "\\n", "\n"));
        n._().M(Boolean.valueOf(this.getParameter("RestrictToolsToChartFg")));
        n._().N(Boolean.valueOf(this.getParameter("EnableMargins")));
        n.b().S(this.b("MaxCandleWidth", 6, 1, 1000));
        n.b().R(this.b("MaxOhlcWidth", 6, 0, 1000));
        n.b().T(this.b("MaxVolumeWidth", 5, 0, 1000));
        n.b().V(this.b("MinCandleWidth", 1, 0, n.b().Q()));
        n.b().U(this.b("MinOhlcWidth", 0, 0, n.b().P()));
        n.b().W(this.b("MinVolumeWidth", 0, 0, n.b().R()));
        n.b().C(Boolean.valueOf(this.getParameter("DoubleVolumeWidth")));
        n.S(Boolean.valueOf(this.getParameter("AutoLoadStudies")));
        final String parameter21 = this.getParameter("EnableSymbolListModification");
        if (parameter21 != null) {
            n.W(Boolean.valueOf(parameter21));
        }
        n.V(Boolean.valueOf(this.getParameter("LoadUserConfigurationOnStartup")));
        n._(this.getParameter("Symbol"), this.getParameter("SecondarySymbol"), this.getParameter("FavoriteSymbols"));
        n.X(Boolean.valueOf(this.getParameter("HalfPagePortraitPrintMode")));
        return n;
    }
    
    private int b(final String s, final int n, final int n2, final int n3) {
        int int1 = n;
        final String parameter = this.getParameter(s);
        if (parameter != null) {
            try {
                int1 = Integer.parseInt(parameter);
            }
            catch (NumberFormatException ex) {
                System.out.println("Illegal parameter value for: " + s);
            }
        }
        if (int1 < n2) {
            int1 = n2;
        }
        if (int1 > n3) {
            int1 = n3;
        }
        return int1;
    }
    
    public boolean _a() {
        if (this.Vua == null) {
            this.Vua = this.getParameter("BuyURL");
        }
        if (this.Wua == null) {
            this.Wua = this.getParameter("SellURL");
        }
        return !this.j(this.Vua) && !this.j(this.Wua);
    }
    
    private boolean j(final String s) {
        return s == null || s.length() == 0;
    }
    
    public void f(final String s) {
        if (this.Vua == null) {
            this.Vua = this.getParameter("BuyURL");
        }
        if (this.j(this.Vua)) {
            return;
        }
        this.j(this.Vua, s);
    }
    
    public void g(final String s) {
        if (this.Wua == null) {
            this.Wua = this.getParameter("SellURL");
        }
        if (this.j(this.Wua)) {
            return;
        }
        this.j(this.Wua, s);
    }
    
    public void j(String s, final String s2) {
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
        if (this.gva == null) {
            this.gva = this.getParameter("target");
        }
        if (this.j(this.gva)) {
            this.gva = "_blank";
        }
        try {
            if (this.Ana != null && this.Iua != null) {
                ((JSObject)this.Ana).call(this.Iua, (Object[])new String[] { s, this.gva });
            }
            else {
                this.getAppletContext().showDocument(new URL(s), this.gva);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void Pb() {
        try {
            if (this.Ana != null && this.Jua != null) {
                ((JSObject)this.Ana).call(this.Jua, (Object[])null);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void _(final String s, final String s2, final String[] array) {
        try {
            if (this.Ana != null && this.Kua != null) {
                final StringBuffer sb = new StringBuffer(256);
                sb.append((s == null) ? "" : s);
                sb.append(",");
                sb.append((s2 == null) ? "" : s2);
                sb.append(",");
                if (array != null) {
                    for (int i = 0; i < array.length; ++i) {
                        sb.append((array[i] == null) ? "" : array[i]);
                        sb.append(",");
                    }
                }
                ((JSObject)this.Ana).call(this.Kua, (Object[])new String[] { sb.toString() });
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
        return catch.a(catch.a(catch.a(s, s2, encode.toUpperCase()), s3, encode.toLowerCase()), s4, encode);
    }
    
    public void destroy() {
        if (this.Osa != null) {
            this.Osa.Qb();
            this.Osa._((Component)this.Osa);
        }
    }
    
    public void start() {
        if (this.Osa != null && this.jva) {
            this.Osa.Yb();
            this.jva = false;
        }
    }
    
    public void stop() {
        if (this.Osa != null) {
            this.Osa.Xb();
            this.jva = true;
        }
    }
    
    public void selectSymbol(final String s) {
        if (this.Osa != null) {
            this.Osa.V(s);
        }
    }
    
    public void selectSecondarySymbol(final String s) {
        if (this.Osa != null) {
            this.Osa.W(s);
        }
    }
    
    public void setFavoriteSymbols(final String s) {
        if (this.Osa != null) {
            this.Osa.a(s, true);
        }
    }
    
    public void addFavoriteSymbols(final String s) {
        if (this.Osa != null) {
            this.Osa.a(s, false);
        }
    }
    
    public void setAvailableSymbolsAndNames(final String availableSymbolsAndNames) {
        if (this.Osa != null) {
            this.Osa.setAvailableSymbolsAndNames(availableSymbolsAndNames);
        }
    }
    
    public String getSelectedStockName() {
        if (this.Osa == null) {
            return "";
        }
        synchronized (this.Osa) {
            return this.Osa.a()._()._();
        }
    }
    
    public int getParam(final int n) {
        return this.Osa.a().n(n - 1);
    }
    
    public int getValuesLength(final int n) {
        if (n == 1) {
            return this.Osa.a().o().length;
        }
        if (n == 2) {
            return this.Osa.a().p().length;
        }
        if (n == 3) {
            return this.Osa.a().q().length;
        }
        if (n == 4) {
            return this.Osa.a().r().length;
        }
        if (n == 5) {
            return this.Osa.a().s().length;
        }
        if (n == 6) {
            return this.Osa.a().t().length;
        }
        if (n == 7) {
            return this.Osa.a().u().length;
        }
        return 0;
    }
    
    public double[] getValues(final int n) {
        if (n == 1) {
            return this.Osa.a().o();
        }
        if (n == 2) {
            return this.Osa.a().p();
        }
        if (n == 3) {
            return this.Osa.a().q();
        }
        if (n == 4) {
            return this.Osa.a().r();
        }
        if (n == 5) {
            return this.Osa.a().s();
        }
        if (n == 6) {
            return this.Osa.a().t();
        }
        if (n == 7) {
            return this.Osa.a().u();
        }
        return new double[0];
    }
    
    public double getValue(final int n, final int n2) {
        if (n == 1) {
            return this.Osa.a().o()[n2];
        }
        if (n == 2) {
            return this.Osa.a().p()[n2];
        }
        if (n == 3) {
            return this.Osa.a().q()[n2];
        }
        if (n == 4) {
            return this.Osa.a().r()[n2];
        }
        if (n == 5) {
            return this.Osa.a().s()[n2];
        }
        if (n == 6) {
            return this.Osa.a().t()[n2];
        }
        if (n == 7) {
            return this.Osa.a().u()[n2];
        }
        return 0.0;
    }
    
    public void addGraphData(final double[] array, final int n) {
        for (int i = 0; i < array.length; ++i) {
            if (Double.isNaN(array[i])) {
                array[i] = 0.0;
            }
        }
        this.Osa.a()._(array, n);
    }
    
    public void addHorizLine(final double n) {
        if (!Double.isNaN(n)) {
            this.Osa.a().a(n);
        }
    }
    
    public void addBuySignal(final int n) {
        this.Osa.a().ba(n);
    }
    
    public void addSellSignal(final int n) {
        this.Osa.a().ca(n);
    }
    
    public void setJSExceptionMessage(final String s) {
        this.Osa.a().l(s);
    }
    
    public void beginGraphData(final int n) {
        this.rva = new double[n];
    }
    
    public void addGraphDataPoint(final int n, double n2) {
        if (Double.isNaN(n2)) {
            n2 = 0.0;
        }
        this.rva[n] = n2;
    }
    
    public void endGraphData(final int n) {
        this.Osa.a()._(this.rva, n);
        this.rva = null;
    }
    
    public double[] calculateMax(final double[] array, final int n) {
        final double[] array2 = new double[array.length];
        do.calculateMax(array, array2, 0, n);
        return array2;
    }
    
    public double[] calculateMin(final double[] array, final int n) {
        final double[] array2 = new double[array.length];
        do.calculateMin(array, array2, 0, n);
        return array2;
    }
    
    public double[] calculateStdDev(final double[] array, final int n) {
        final double[] array2 = new double[array.length];
        do.calculateStdDev(array, array2, 0, n);
        return array2;
    }
    
    public double[] calculateSimpleAvg(final double[] array, final int n) {
        final double[] array2 = new double[array.length];
        do.calculateSimpleAvg(array, array2, 0, n);
        return array2;
    }
    
    public double[] calculateExpAvg(final double[] array, final int n) {
        final double[] array2 = new double[array.length];
        do.calculateExpAvg(array, array2, 0, n);
        return array2;
    }
    
    public void setBrowserType(final String s) {
        if (this.Osa != null) {
            this.Osa.a().b().w(s != null && s.equals("msie"));
        }
    }
    
    public String H() {
        return this.bsa;
    }
    
    private byte[] b(String lowerCase, int n, int n2) {
        if (this.Osa == null || lowerCase == null) {
            return null;
        }
        n = Math.max(200, n);
        n = Math.min(2000, n);
        n2 = Math.max(100, n2);
        n2 = Math.min(2000, n2);
        lowerCase = lowerCase.toLowerCase();
        if (!"png".equals(lowerCase) && !"gif".equals(lowerCase) && !"jpg".equals(lowerCase)) {
            return null;
        }
        byte[] byteArray = null;
        synchronized (this.Osa) {
            this.Osa.b(false);
            try {
                this.Osa.b(this.Osa.a().a().b("msgGeneratingChartImage"));
                final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(40960);
                try {
                    this.Osa._().a(byteArrayOutputStream, lowerCase, n, n2);
                    if (byteArrayOutputStream.size() > 0) {
                        byteArray = byteArrayOutputStream.toByteArray();
                        this.Osa.b(this.Osa.a().a().b("msgChartImageGenerated"));
                    }
                    else {
                        this.Osa.b(this.Osa.a().a().b("msgErrorGeneratingChartImage"));
                    }
                }
                catch (Exception ex) {
                    this.Osa.b(this.Osa.a().a().b("msgErrorGeneratingChartImage"));
                    ex.printStackTrace();
                }
            }
            finally {
                this.Osa.b(true);
            }
        }
        return byteArray;
    }
    
    public String getChartAsBase64EncodedPNG(final int n, final int n2) {
        final byte[] b = this.b("png", n, n2);
        if (b == null) {
            return "";
        }
        return extends.b(b);
    }
    
    public String getChartAsBase64EncodedGIF(final int n, final int n2) {
        final byte[] b = this.b("gif", n, n2);
        if (b == null) {
            return "";
        }
        return extends.b(b);
    }
    
    static String R() {
        return FnChartsApplet.Pua;
    }
    
    static String S() {
        return FnChartsApplet.Qua;
    }
    
    static {
        Lua = new String[] { "ChartBg", "ChartFg", "ChartFrame", "Grid", "ChartTitle", "ChartTitleBg", "ChartTitleFrame", "Axis", "AxisLabel", "AxisLabelBg", "AxisLabelFrame", "Price", "BullishCandle", "BearishCandle", "BgPrice", "ExtraPrice", "Volume", "OpenInterest", "InfoLine", "InfoLineBg", "InfoLineFrame", "Crosshair", "ChartTopLeftFrame", "ChartBottomRightFrame", "IndicatorSpecificLine", "Indicator", "TrendLine", "ImageButtonBg", "ImageButtonFrame", "UIText", "ProgressBar", "UIBg", "BuySignal", "SellSignal", "FooterText", "FooterTextBg", "FooterTextFrame" };
        Mua = new String[] { "Price", "BgPrice", "ExtraPrice", "OpenInterest", "Indicator", "TrendLine" };
        Nua = new String[] { "InfoLineFontName", "InfoLineFontSize", "InfoLineFontStyle", "AxisLabelFontName", "AxisLabelFontSize", "AxisLabelFontStyle", "ChartTitleFontName", "ChartTitleFontSize", "ChartTitleFontStyle", "StatusBarFontName", "StatusBarFontSize", "StatusBarFontStyle", "UIFontName", "UIFontSize", "UIFontStyle", "LegendFontName", "LegendFontSize", "LegendFontStyle", "FooterFontName", "FooterFontSize", "FooterFontStyle" };
        Oua = catch.i("emantsoh");
        Pua = catch.i("ferh");
        Qua = catch.i("denifednu");
        Rua = catch.i("tnemucod");
        Sua = catch.i("sknil");
    }
}
