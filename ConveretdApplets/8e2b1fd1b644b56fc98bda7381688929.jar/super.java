import java.util.zip.InflaterInputStream;
import java.io.UnsupportedEncodingException;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.net.URLEncoder;
import java.awt.Frame;
import java.awt.Cursor;
import java.awt.TextField;
import java.awt.Container;
import java.awt.Font;
import java.util.Vector;
import java.util.Enumeration;
import java.awt.Color;
import java.awt.SystemColor;
import java.util.Observer;
import java.awt.event.FocusListener;
import java.awt.event.MouseListener;
import java.awt.event.KeyListener;
import java.awt.event.ItemListener;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.Component;
import java.awt.Insets;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.util.Hashtable;
import java.awt.Button;
import java.awt.PopupMenu;
import java.awt.MenuItem;
import java.awt.List;
import java.awt.Label;
import java.awt.Choice;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

public class super extends Panel implements Runnable
{
    public static final boolean exa = false;
    public static final boolean fxa = false;
    private static final int gxa = 3;
    private static final String hxa = "btnMaximize,btnUseBrowserCache,btnAutoRefreshSwitch,btnAutoRefreshBeepSwitch,Separator6,btnRefresh,btnMoveLeft,btnZoomIn,btnZoomOut,btnMoveRight,selectRange,Separator6,btnLine,btnOhlc,btnCandle,Separator6,btnScaleLogCor,btnScaleLogUncor,btnScaleLinCor,btnScaleLinUncor,btnScalePercent,Separator6,selectDataCompression,Separator6,btnSetTimeFrame,btnChartProperties,btnVolumeSwitch,btnSymbolEntryVisibilitySwitch";
    private static final String ixa = "selectIndicator1,selectIndicator2,Separator2,txtParam1,txtParam2,txtParam3,Separator6,btnParabolicSAR,Separator6,btnBol,Separator2,txtBolParam,Separator6,btnEma,Separator2,txtEmaParam,Separator6,btnSma1,Separator2,txtSma1Param,Separator6,btnSma2,Separator2,txtSma2Param,Separator6,btnSma3,Separator2,txtSma3Param,Separator6,btnVolEma,Separator2,txtVolEmaParam";
    public static final int jxa = 1;
    public static final int kxa = 2;
    public static final int lxa = 3;
    public static final int mxa = 4;
    public static final int nxa = 5;
    private final int oxa;
    private jea pxa;
    private String qxa;
    private kea rxa;
    private String sxa;
    private transient txa;
    private transient uxa;
    private transient vxa;
    private transient wxa;
    private transient xxa;
    private transient yxa;
    private transient zxa;
    private transient Axa;
    private transient Bxa;
    private transient Cxa;
    private transient Dxa;
    private transient Exa;
    private Choice Fxa;
    private transient Gxa;
    private transient Hxa;
    private transient Ixa;
    private transient[] Jxa;
    private transient Kxa;
    private transient Lxa;
    private transient Mxa;
    private transient Nxa;
    private transient Oxa;
    private transient Pxa;
    private transient Qxa;
    private transient Rxa;
    private Label info;
    protected lea I;
    private Panel Sxa;
    private Panel Txa;
    private List Uxa;
    private const Vxa;
    private const Wxa;
    private MenuItem Xxa;
    private MenuItem Yxa;
    private MenuItem Zxa;
    private PopupMenu _ya;
    private MenuItem aya;
    private MenuItem bya;
    private MenuItem cya;
    private PopupMenu dya;
    private mea eya;
    private continue fya;
    private Panel gya;
    private String[] hya;
    private String iya;
    private String jya;
    private boolean kya;
    protected transient Ia;
    private transient lya;
    private transient mya;
    private transient nya;
    private transient oya;
    private transient pya;
    private Choice Ta;
    private Button qya;
    private Button rya;
    private const[] Oa;
    private const[] sya;
    private Choice tya;
    private Choice uya;
    private Choice vya;
    private nea B;
    private while wya;
    private boolean xya;
    private boolean yya;
    private FnChartsApplet Zwa;
    private continue zya;
    private final String Aya;
    private final String Bya;
    private final String Cya;
    private final String Dya;
    private int Eya;
    private int Fya;
    private String Gya;
    private boolean Hya;
    private boolean Iya;
    private boolean Jya;
    private final byte[] Kya;
    private final byte[] Lya;
    private oea Mya;
    private final String[] Nya;
    
    public super(final FnChartsApplet zwa, final switch switch1, final String s, final String s2, final int n, final String s3, final String s4, final String s5, final String s6, final String s7, final boolean kya, final boolean b, final boolean b2, final boolean xya, final String gya, final Hashtable hashtable, final String s8, final int oxa, final String s9, final String s10, final boolean b3, final boolean iya) {
        this.qxa = null;
        this.rxa = new kea();
        this.sxa = null;
        this.hya = null;
        this.iya = "";
        this.jya = "";
        this.kya = true;
        this.xya = true;
        this.yya = false;
        this.Zwa = null;
        this.Eya = 760;
        this.Fya = 540;
        this.Gya = "c_";
        this.Hya = true;
        this.Jya = true;
        this.Kya = new byte[] { 0, 0, 0, 0, 0, 0, 115, -53, 115, -50, 72, 44, 42, 41, 86, 8, 40, -54, 79, 75, 45, 46, -50, -52, -49, 75, -52, 1, 0, -75, 60, 100, -66, 21, 0, 0, 0 };
        this.Lya = new byte[] { 0, 0, 0, 0, 0, 0, 51, 53, 53, 0, 0, 115, 12, 9, 89, 3, 0, 0, 0 };
        this.Mya = new oea();
        this.Nya = new String[] { "Price", "BullishCandle", "BearishCandle", "BgPrice", "Volume", "VOLEMA", "OpenInterest", "ParabolicSAR", "BOL", "EMA", "SMA1", "SMA2", "SMA3", "TrendLine", "Grid", "Crosshair" };
        this.Gya = gya;
        this.Zwa = zwa;
        this.xya = xya;
        this.Aya = ("".equals(s4) ? null : s4);
        this.Bya = ("".equals(s5) ? null : s5);
        this.Cya = ("".equals(s6) ? null : s6);
        this.Dya = ("".equals(s7) ? null : s7);
        this.oxa = oxa;
        this.Iya = iya;
        this.wya = new while(zwa.C(), zwa.b(), switch1, s, s2, n, s3, hashtable, s8);
        this.B = new nea(this.wya);
        this.setLayout(new BorderLayout());
        final continue continue1 = new continue(new FlowLayout(0, 0, 0), 1, new Insets(1, 2, 1, 2));
        continue1.setLayout(new BorderLayout());
        continue1.add(this.info = new Label("", 0), "Center");
        if (b2) {
            continue1.add(this.I = new lea(4, new Insets(4, 4, 4, 4)), "East");
        }
        final pea pea = new pea(this);
        final continue continue2 = new continue(new FlowLayout(1, 0, 1), 4, new Insets(1, 2, 1, 2));
        if (zwa != null && s3 != null) {
            if (s4 != null) {
                (this.Lxa = new transient("/images/" + gya + "loadconfig.gif", "btnLoadConfig")).addActionListener(pea);
                continue2.add(this.Lxa);
            }
            if (s5 != null) {
                (this.Kxa = new transient("/images/" + gya + "saveconfig.gif", "btnSaveConfig")).addActionListener(pea);
                continue2.add(this.Kxa);
            }
        }
        if (b3) {
            (this.Mxa = new transient("/images/" + gya + "maximizechart.gif", "btnMaximizeChart", false)).addActionListener(pea);
            continue2.add(this.Mxa);
        }
        this.Pxa = new transient("/images/" + gya + "autoloadstudies.gif", "btnAutoLoadStudies", false);
        if (zwa != null && s3 != null) {
            if (s7 != null) {
                (this.Nxa = new transient("/images/" + gya + "savestudies.gif", "btnSaveStudies")).addActionListener(pea);
                continue2.add(this.Nxa);
            }
            if (s6 != null) {
                (this.Oxa = new transient("/images/" + gya + "loadstudies.gif", "btnLoadStudies")).addActionListener(pea);
                continue2.add(this.Oxa);
                continue2.add(this.Pxa);
            }
        }
        String s12;
        final String s11 = s12 = System.getProperty("java.version");
        if (s11.length() >= 3) {
            s12 = s11.substring(0, 3);
        }
        if ("1.4".compareTo(s12) <= 0) {
            (this.Rxa = new transient("/images/" + gya + "printchart.gif", "btnPrintChart")).addActionListener(new qea(this));
            continue2.add(this.Rxa);
        }
        continue2.add(new rea(4));
        continue1.add(continue2, "West");
        this.add(continue1, "South");
        this.Sxa = new continue(new FlowLayout(0, 0, 3));
        this.Txa = new continue(new FlowLayout(0, 0, 3));
        int n2 = 0;
        if (s9 == null || s9.length() > 0) {
            ++n2;
        }
        if (s10 == null || s10.length() > 0) {
            ++n2;
        }
        this.gya = new Panel(new GridLayout(n2, 1));
        if (s9 == null || s9.length() > 0) {
            this.gya.add(this.Sxa);
        }
        if (s10 == null || s10.length() > 0) {
            this.gya.add(this.Txa);
        }
        if (n2 > 0) {
            this.add(this.gya, "North");
        }
        (this.Uxa = new List()).setMultipleMode(false);
        this.Uxa.addItemListener(this.eya = new mea(this));
        this.Uxa.addKeyListener(this.eya);
        this.dya = new PopupMenu();
        (this.Xxa = new MenuItem(this.wya.a().a("menuClearSymbolSelection"))).addActionListener(this.eya);
        this.dya.add(this.Xxa);
        this.dya.addSeparator();
        final sea sea = new sea(this);
        (this.Yxa = new MenuItem(this.wya.a().a("menuRemoveFromFavorites"))).addActionListener(sea);
        this.dya.add(this.Yxa);
        (this.Zxa = new MenuItem(this.wya.a().a("menuClearFavorites"))).addActionListener(sea);
        this.dya.add(this.Zxa);
        this.Uxa.add(this.dya);
        this.Uxa.addMouseListener(this.eya);
        final tea tea = new tea(this);
        (this.Vxa = new const(oxa, false)).setText(this.wya.a().a("strEnterSymbolHere"));
        this.Vxa.addKeyListener(tea);
        this.Vxa.addFocusListener(tea);
        (this.Wxa = new const(oxa, false)).addKeyListener(tea);
        this.Wxa.addFocusListener(tea);
        (this.fya = new continue(new BorderLayout(1, 1), 1, new Insets(2, 2, 2, 2))).add(this.Uxa, "Center");
        this.fya.add(this.Wxa, "South");
        Panel panel;
        if (zwa != null && zwa.V()) {
            panel = new Panel(new GridLayout(2, 1, 1, 2));
        }
        else {
            panel = new Panel(new GridLayout(1, 1, 1, 2));
        }
        panel.add(this.Vxa);
        if (zwa != null && zwa.V()) {
            final continue continue3 = new continue(new GridLayout(1, 2, 0, 0), 2, new Insets(1, 1, 1, 1));
            this.qya = new Button(switch1.a("btnBuy"));
            this.rya = new Button(switch1.a("btnSell"));
            continue3.add(this.qya);
            continue3.add(this.rya);
            panel.add(continue3);
        }
        this.fya.add(panel, "North");
        this.add(this.fya, "West");
        this.kya = kya;
        this.fya.setVisible(this.kya);
        this.add(this.zya = new continue(new BorderLayout(), 3, new Insets(3, 3, 3, 3)), "Center");
        this.zya.add(this.B.b(), "Center");
        this.wya._().b(this.B.b());
        this.b(b2, b, s9, s10);
        this.db();
        this.a(this, SystemColor.control);
        this.pxa = jea.a(this, this.B, this.wya);
    }
    
    private void db() {
        this.Mya.put(this.txa, this.wya.a().a("btnLogCor_Info"));
        this.Mya.put(this.uxa, this.wya.a().a("btnLogUncor_Info"));
        this.Mya.put(this.vxa, this.wya.a().a("btnLinCor_Info"));
        this.Mya.put(this.wxa, this.wya.a().a("btnLinUncor_Info"));
        this.Mya.put(this.xxa, this.wya.a().a("btnPercentCor_Info"));
        this.Mya.put(this.Axa, this.wya.a().a("btnSetTimeframe_Info"));
        this.Mya.put(this.zxa, this.wya.a().a("btnChartProperties_Info"));
        this.Mya.put(this.yxa, this.wya.a().a("btnStockListVisible_Info"));
        this.Mya.put(this.Bxa, this.wya.a().a("btnVolumeVisible_Info"));
        this.Mya.put(this.Cxa, this.wya.a().a("btnUseBrowserCache_Info"));
        this.Mya.put(this.Dxa, this.wya.a().a("btnAutoRefreshActive_Info"));
        this.Mya.put(this.Exa, this.wya.a().a("btnAutoRefreshBeep_Info"));
        this.Mya.put(this.Fxa, this.wya.a().a("selectQuotType_Info"));
        this.Mya.put(this.Gxa, this.wya.a().a("btnLine_Info"));
        this.Mya.put(this.Hxa, this.wya.a().a("btnOhlc_Info"));
        this.Mya.put(this.Ixa, this.wya.a().a("btnCandle_Info"));
        this.Mya.put(this.Kxa, this.wya.a().a("btnSaveConfig_Info"));
        this.Mya.put(this.Lxa, this.wya.a().a("btnLoadConfig_Info"));
        this.Mya.put(this.Nxa, this.wya.a().a("btnSaveStudies_Info"));
        this.Mya.put(this.Oxa, this.wya.a().a("btnLoadStudies_Info"));
        this.Mya.put(this.Pxa, this.wya.a().a("btnAutoLoadStudies_Info"));
        this.Mya.put(this.Qxa, this.wya.a().a("btnCopyChart_Info"));
        this.Mya.put(this.Rxa, this.wya.a().a("btnPrintChart_Info"));
        this.Mya.put(this.Mxa, this.wya.a().a("btnMaximizeChart_Info"));
        this.Mya.put(this.Uxa, this.wya.a().a("selectStock_Info"));
        this.Mya.put(this.Vxa, this.wya.a().a("enterPrimarySymbol_Info"));
        this.Mya.put(this.Wxa, this.wya.a().a("enterSecondarySymbol_Info"));
        this.Mya.put(this.Ia, this.wya.a().a("btnMaximize_Info"));
        this.Mya.put(this.lya, this.wya.a().a("btnRefresh_Info"));
        this.Mya.put(this.mya, this.wya.a().a("btnZoomOut_Info"));
        this.Mya.put(this.nya, this.wya.a().a("btnZoomIn_Info"));
        this.Mya.put(this.oya, this.wya.a().a("btnMoveChart_Info"));
        this.Mya.put(this.pya, this.wya.a().a("btnMoveChart_Info"));
        this.Mya.put(this.Ta, this.wya.a().a("selectRange_Info"));
        this.Mya.put(this.qya, this.wya.a().a("btnBuy_Info"));
        this.Mya.put(this.rya, this.wya.a().a("btnSell_Info"));
        for (int i = 0; i < this.Oa.length; ++i) {
            this.Mya.put(this.Oa[i], this.wya.a().a("txtIndicatorParam_Info"));
        }
        for (int j = 0; j < this.sya.length; ++j) {
            if (this.sya[j] != null) {
                this.Mya.put(this.sya[j], this.wya.a().a("txtIndicatorParam_Info"));
            }
        }
        for (int k = 0; k < this.Jxa.length; ++k) {
            this.Mya.put(this.Jxa[k], this.wya.a().a("btnIndicatorOnOff_Info"));
        }
        this.Mya.put(this.uya, this.wya.a().a("selectIndicator_Info"));
        this.Mya.put(this.vya, this.wya.a().a("selectIndicator_Info"));
        this.Mya.put(this.B.b(), this.wya.a().a("strPopupMenu_Info"));
        final uea uea = new uea(this, null);
        final Enumeration<Component> keys = (Enumeration<Component>)this.Mya.keys();
        while (keys.hasMoreElements()) {
            final Component nextElement = keys.nextElement();
            if (nextElement instanceof Component) {
                nextElement.addMouseListener(uea);
            }
        }
    }
    
    private synchronized Hashtable _() {
        final Hashtable<String, String> hashtable = new Hashtable<String, String>();
        try {
            if (this.xya) {
                this.wya._().F(this.wya.J());
            }
            hashtable.put("UseBrowserCache", String.valueOf(this.Cxa.getState()));
            if (this.Dxa.isVisible()) {
                hashtable.put("AutoRefreshActive", String.valueOf(this.Dxa.getState()));
            }
            if (this.Exa.isVisible()) {
                hashtable.put("AutoRefreshBeep", String.valueOf(this.Exa.getState()));
            }
            if (this.Gxa.getState()) {
                hashtable.put("ChartType", String.valueOf(0));
            }
            else if (this.Hxa.getState()) {
                hashtable.put("ChartType", String.valueOf(1));
            }
            else if (this.Ixa.getState()) {
                hashtable.put("ChartType", String.valueOf(2));
            }
            if (this.txa.getState()) {
                hashtable.put("Scale", String.valueOf(1));
            }
            else if (this.uxa.getState()) {
                hashtable.put("Scale", String.valueOf(2));
            }
            else if (this.vxa.getState()) {
                hashtable.put("Scale", String.valueOf(3));
            }
            else if (this.wxa.getState()) {
                hashtable.put("Scale", String.valueOf(4));
            }
            else if (this.xxa.getState()) {
                hashtable.put("Scale", String.valueOf(5));
            }
            int a = c.a(this.wya.a(), this.Fxa.getSelectedItem());
            if (a == -1) {
                a = 100001;
            }
            hashtable.put("DataPeriod", String.valueOf(a));
            if (this.Bxa != null) {
                hashtable.put("VolumeVisible", String.valueOf(this.Bxa.getState()));
            }
            hashtable.put("GridVisible", String.valueOf(this.B.b().N()));
            hashtable.put("TitleVisible", String.valueOf(this.B.b().Q()));
            hashtable.put("LegendVisible", String.valueOf(this.B.b().R()));
            hashtable.put("CrosshairVisible", String.valueOf(this.B.b().O()));
            hashtable.put("BuySellSignalsVisible", String.valueOf(this.B.b().P()));
            hashtable.put("AutoLoadStudies", String.valueOf(this.b()));
            final String[] b = this.wya.b();
            for (int i = 0; i < b.length; ++i) {
                final public b2 = this.wya.b(b[i]);
                if (b2 != null && !(b2 instanceof protected)) {
                    hashtable.put(b2.toString(), b2.h());
                }
            }
            final String[] n = this.wya.n();
            for (int j = 0; j < n.length; ++j) {
                final public _ = this.wya._(n[j]);
                if (_ != null) {
                    hashtable.put(_.toString(), _.h());
                }
            }
            final public _2 = this.wya._();
            if (_2 != null) {
                hashtable.put("Indicator1", _2.toString());
            }
            final public a2 = this.wya.a();
            if (a2 != null) {
                hashtable.put("Indicator2", a2.toString());
            }
            final StringBuffer sb = new StringBuffer();
            final public[] a3 = this.wya.a();
            if (a3 != null) {
                for (int k = 0; k < a3.length; ++k) {
                    sb.append(a3[k].toString());
                    if (k < a3.length - 1) {
                        sb.append(",");
                    }
                }
            }
            hashtable.put("PriceIndicators", sb.toString());
            hashtable.put("Timeframe", String.valueOf(this.wya._().h()));
            hashtable.put("IntradayTimeframe", String.valueOf(this.wya._().e()));
            final String[] array = new String[this.Nya.length + 1];
            for (int l = 0; l < this.Nya.length; ++l) {
                array[l] = this.Nya[l];
            }
            array[array.length - 1] = "Indicator";
            for (int n2 = 0; n2 < array.length; ++n2) {
                final String s = array[n2];
                String string = "";
                String string2 = "";
                final Color[] b3 = this.wya.b(s);
                if (b3 != null) {
                    for (int n3 = 0; n3 < b3.length; ++n3) {
                        string = string + "0x" + Integer.toHexString(b3[n3].getRGB() & 0xFFFFFF).toUpperCase() + ",";
                    }
                    hashtable.put(s + "Color", string);
                }
                final int[] _3 = this.wya._(s);
                if (_3 != null) {
                    for (int n4 = 0; n4 < _3.length; ++n4) {
                        string2 = string2 + String.valueOf(_3[n4]) + ",";
                    }
                    hashtable.put(s + "LineWidth", string2);
                }
            }
            final String s2 = "ExtraPrice";
            String string3 = "";
            String string4 = "";
            final Color[] b4 = this.wya.b(s2);
            if (b4 != null) {
                for (int n5 = 0; n5 < b4.length; ++n5) {
                    string3 = string3 + "0x" + Integer.toHexString(b4[n5].getRGB() & 0xFFFFFF).toUpperCase() + ",";
                }
                hashtable.put(s2 + "Color", string3);
            }
            final int[] _4 = this.wya._(s2);
            if (_4 != null) {
                for (int n6 = 0; n6 < _4.length; ++n6) {
                    string4 = string4 + String.valueOf(_4[n6]) + ",";
                }
                hashtable.put(s2 + "LineWidth", string4);
            }
            final String text = this.Vxa.getText();
            if (text.equals(this.wya.a().a("strEnterSymbolHere")) || text.length() == 0) {
                hashtable.put("ActiveStock", "");
            }
            else {
                hashtable.put("ActiveStock", this.wya._().b(text) + "/" + text);
            }
            final String text2 = this.Wxa.getText();
            if (text2.equals(this.wya.a().a("strEnterSymbolHere")) || text2.length() == 0) {
                hashtable.put("ActiveBgStock", "");
            }
            else {
                hashtable.put("ActiveBgStock", this.wya._().b(text2) + "/" + text2);
            }
            final StringBuffer sb2 = new StringBuffer();
            final String[] items = this.Uxa.getItems();
            for (int n7 = 0; n7 < items.length; ++n7) {
                sb2.append(this.wya._().b(items[n7]) + "/" + items[n7]);
                sb2.append(',');
            }
            hashtable.put("Favorites", sb2.toString());
            hashtable.put("DisplayRange", String.valueOf(this.B.l()));
        }
        finally {}
        return hashtable;
    }
    
    private synchronized wea b() {
        final StringBuffer sb = new StringBuffer(128);
        final public[] a = this.wya.a();
        if (a != null) {
            for (int i = 0; i < a.length; ++i) {
                sb.append(a[i].toString());
                sb.append("(");
                sb.append(a[i].h());
                sb.append(")");
                if (i < a.length - 1) {
                    sb.append(";");
                }
            }
        }
        final String string = sb.toString();
        final StringBuffer sb2 = new StringBuffer(32);
        final public _ = this.wya._();
        if (_ != null) {
            sb2.append(_.toString());
            sb2.append("(");
            sb2.append(_.h());
            sb2.append(")");
        }
        final String string2 = sb2.toString();
        final StringBuffer sb3 = new StringBuffer(32);
        final public a2 = this.wya.a();
        if (a2 != null) {
            sb3.append(a2.toString());
            sb3.append("(");
            sb3.append(a2.h());
            sb3.append(")");
        }
        return new wea(this.B.j(), this.p(), this.B.l(), this.wya._().ia(), this.Bxa != null && this.Bxa.getState(), string, string2, sb3.toString(), this.B.b()._());
    }
    
    private synchronized void a(final Hashtable hashtable) {
        boolean b = false;
        final boolean a = this.wya._().a();
        boolean b2 = false;
        try {
            if (this.xya) {
                final String h = this.wya._().H();
                if (h != null) {
                    this.wya.G(h);
                    b2 = true;
                }
            }
            if (hashtable.get("UseBrowserCache") != null) {
                this._(Boolean.valueOf(hashtable.get("UseBrowserCache")));
            }
            if (this.Dxa.isVisible() && hashtable.get("AutoRefreshActive") != null) {
                this.Dxa.setState(Boolean.valueOf(hashtable.get("AutoRefreshActive")));
            }
            if (this.Exa.isVisible() && hashtable.get("AutoRefreshBeep") != null) {
                this.Exa.setState(Boolean.valueOf(hashtable.get("AutoRefreshBeep")));
                this.pxa.n(this.Exa.getState());
            }
            try {
                this.e(Integer.parseInt(hashtable.get("ChartType")));
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            try {
                this.aa(Integer.parseInt(hashtable.get("Scale")));
            }
            catch (Exception ex2) {
                ex2.printStackTrace();
            }
            try {
                this.ba(Integer.parseInt(hashtable.get("DataPeriod")));
            }
            catch (Exception ex3) {
                ex3.printStackTrace();
            }
            if (a != this.wya._().a()) {
                this.B.a();
                b = true;
            }
            if (this.Bxa != null && hashtable.get("VolumeVisible") != null) {
                this.Bxa.setState(Boolean.valueOf(hashtable.get("VolumeVisible")));
                int n = 0;
                if (this.wya._() != null) {
                    ++n;
                }
                if (this.wya.a() != null) {
                    ++n;
                }
                if (this.Bxa.getState()) {
                    this.B.f(1);
                    this._(2 + n, this.p());
                }
                else {
                    this.B.f(2);
                    this._(1 + n, this.p());
                }
                this.B.d();
                for (int i = 1; i < this.B.b().m(); ++i) {
                    this.B.b().i(i);
                }
                this.B.e();
            }
            if (hashtable.get("GridVisible") != null) {
                this.B.b().P(Boolean.valueOf(hashtable.get("GridVisible")));
            }
            if (hashtable.get("TitleVisible") != null) {
                this.B.b().N(Boolean.valueOf(hashtable.get("TitleVisible")));
            }
            if (hashtable.get("LegendVisible") != null) {
                this.B.b().O(Boolean.valueOf(hashtable.get("LegendVisible")));
            }
            if (hashtable.get("CrosshairVisible") != null) {
                this.B.b().M(Boolean.valueOf(hashtable.get("CrosshairVisible")));
            }
            if (hashtable.get("BuySellSignalsVisible") != null) {
                this.B.b().Q(Boolean.valueOf(hashtable.get("BuySellSignalsVisible")));
            }
            if (hashtable.get("AutoLoadStudies") != null) {
                this.Y(Boolean.valueOf(hashtable.get("AutoLoadStudies")));
            }
            final String[] b3 = this.wya.b();
            for (int j = 0; j < b3.length; ++j) {
                final public b4 = this.wya.b(b3[j]);
                if (b4 != null && hashtable.get(b4.toString()) != null) {
                    b4.r(hashtable.get(b4.toString()));
                }
            }
            final String[] n2 = this.wya.n();
            for (int k = 0; k < n2.length; ++k) {
                final public _ = this.wya._(n2[k]);
                if (_ != null && hashtable.get(_.toString()) != null) {
                    _.r(hashtable.get(_.toString()));
                }
            }
            this.m(hashtable.get("Indicator1"));
            this.n(hashtable.get("Indicator2"));
            if (hashtable.get("PriceIndicators") != null) {
                this.K(hashtable.get("PriceIndicators").replace(',', ';'));
            }
            else {
                this.K(null);
            }
            try {
                this.wya._().a(Integer.parseInt(hashtable.get("Timeframe")));
            }
            catch (Exception ex4) {
                ex4.printStackTrace();
            }
            try {
                this.wya._()._(Integer.parseInt(hashtable.get("IntradayTimeframe")));
            }
            catch (Exception ex5) {
                ex5.printStackTrace();
            }
            final String[] array = new String[this.Nya.length + 1];
            for (int l = 0; l < this.Nya.length; ++l) {
                array[l] = this.Nya[l];
            }
            array[array.length - 1] = "Indicator";
            for (int n3 = 0; n3 < array.length; ++n3) {
                final String s = array[n3];
                final try try1 = new try(",");
                final String s2 = hashtable.get(s + "Color");
                if (s2 != null) {
                    try1.l(s2);
                    final Color[] array2 = new Color[try1.g()];
                    try {
                        for (int n4 = 0; n4 < try1.g(); ++n4) {
                            array2[n4] = Color.decode(try1.a(n4));
                        }
                        this.wya._(s, array2);
                        if (s.equals("Indicator")) {
                            final String[] b5 = this.wya.b();
                            for (int n5 = 0; n5 < b5.length; ++n5) {
                                this.wya._(b5[n5], array2);
                            }
                        }
                    }
                    catch (NumberFormatException ex6) {
                        ex6.printStackTrace();
                    }
                }
                final String s3 = hashtable.get(s + "LineWidth");
                if (s3 != null) {
                    try1.l(s3);
                    final int[] array3 = new int[try1.g()];
                    try {
                        for (int n6 = 0; n6 < try1.g(); ++n6) {
                            array3[n6] = Integer.parseInt(try1.a(n6));
                        }
                        this.wya._(s, array3);
                        if (s.equals("Indicator")) {
                            final String[] b6 = this.wya.b();
                            for (int n7 = 0; n7 < b6.length; ++n7) {
                                this.wya._(b6[n7], array3);
                            }
                        }
                    }
                    catch (NumberFormatException ex7) {
                        ex7.printStackTrace();
                    }
                }
            }
            final String s4 = "ExtraPrice";
            final String s5 = hashtable.get(s4 + "Color");
            if (s5 != null) {
                final try try2 = new try(",");
                try2.l(s5);
                final Color[] array4 = new Color[try2.g()];
                try {
                    for (int n8 = 0; n8 < try2.g(); ++n8) {
                        array4[n8] = Color.decode(try2.a(n8));
                    }
                    this.wya._(s4, array4);
                }
                catch (NumberFormatException ex8) {
                    ex8.printStackTrace();
                }
            }
            final String s6 = "ExtraPrice";
            final String s7 = hashtable.get(s6 + "LineWidth");
            if (s7 != null) {
                final try try3 = new try(",");
                try3.l(s7);
                final int[] array5 = new int[try3.g()];
                try {
                    for (int n9 = 0; n9 < try3.g(); ++n9) {
                        array5[n9] = Integer.parseInt(try3.a(n9));
                    }
                    this.wya._(s6, array5);
                }
                catch (NumberFormatException ex9) {
                    ex9.printStackTrace();
                }
            }
            final import import1 = new import(this, hashtable.get("ActiveStock"));
            if ((import1.eqa && !import1.name.equals(this.qxa)) || !import1.name.equals(this.wya._()._())) {
                this.B.a();
            }
            this.Vxa.setText(import1.eqa ? import1.name : "");
            this.qxa = (import1.eqa ? import1.name : null);
            this.wya._().b(import1);
            final import import2 = new import(this, hashtable.get("ActiveBgStock"));
            this.Wxa.setText(import2.eqa ? import2.name : "");
            this.sxa = (import2.eqa ? import2.name : null);
            this.wya._().b(import2);
            this.wya._().b(this.sxa);
            final String s8 = hashtable.get("Favorites");
            String[] array6 = null;
            if (s8 != null && s8.length() > 0) {
                final try try4 = new try(",");
                try4.l(s8);
                int n10 = 0;
                if (this.qxa != null && this.qxa.length() > 0) {
                    ++n10;
                }
                if (this.sxa != null && this.sxa.length() > 0) {
                    ++n10;
                }
                array6 = new String[try4.g() + n10];
                int n11;
                for (n11 = 0; n11 < try4.g(); ++n11) {
                    array6[n11] = try4.a(n11);
                }
                if (this.qxa != null && this.qxa.length() > 0) {
                    array6[n11++] = import1.dqa + "/" + import1.name;
                }
                if (this.sxa != null && this.sxa.length() > 0) {
                    array6[n11++] = import2.dqa + "/" + import2.name;
                }
            }
            this.rxa.removeAll();
            this.wya._().a(this.rxa._());
            this.wya._()._(this.qxa);
            try {
                this.ca(Integer.parseInt(hashtable.get("DisplayRange")));
            }
            catch (NumberFormatException ex10) {
                ex10.printStackTrace();
            }
            this.Uxa.setVisible(false);
            for (int n12 = 0; n12 < this.Uxa.getItemCount(); ++n12) {
                this.Uxa.deselect(n12);
            }
            if (this.Hya) {
                this.Uxa.removeAll();
                this._(array6);
            }
            if (this.qxa != null && this.qxa.length() > 0) {
                this.L(this.qxa);
            }
            this.Uxa.setVisible(true);
        }
        finally {
            if (b2) {
                this.eb();
            }
            this.B.m();
            if (b) {
                this.a(this.wya._()._() + ": " + this.wya.a().a("msgLoadingData"));
            }
            boolean w = false;
            if (this.b()) {
                w = this.W();
            }
            if (!w) {
                this.B.b();
            }
            this.B.repaint();
            this.a(this.wya._().getMessage());
            this.Z(this.p() != 5);
            if (b) {
                this.pxa._();
            }
        }
    }
    
    private synchronized void _(final wea wea) {
        try {
            this.B.a();
            this.e(wea.j());
            this.aa(wea.k());
            this.m(wea.k());
            this.n(wea.l());
            this.K(wea.m());
            this.ca(wea.l());
            if (this.Bxa != null) {
                this.Bxa.setState(wea.l());
                int n = 0;
                if (this.wya._() != null) {
                    ++n;
                }
                if (this.wya.a() != null) {
                    ++n;
                }
                if (this.Bxa.getState()) {
                    this.B.f(1);
                    this._(2 + n, this.p());
                }
                else {
                    this.B.f(2);
                    this._(1 + n, this.p());
                }
            }
            this.B.b().b(wea._());
        }
        finally {
            this.B.h(true);
            this.B.b();
            this.B.h(false);
            this.B.repaint();
        }
    }
    
    public synchronized void cb() {
        new vea(this).start();
    }
    
    public void M(final String s) {
        if (s == null || s.length() == 0) {
            return;
        }
        final try try1 = new try(",");
        try1.l(s);
        final Vector vector = new Vector<String>(try1.g());
        for (int i = 0; i < try1.g(); ++i) {
            final int e = c.e(try1.a(i));
            if (e != -1) {
                vector.addElement(c.k(e));
            }
        }
        if (vector.size() == 0) {
            vector.addElement(c.k(100001));
        }
        this.Fxa.removeAll();
        for (int j = 0; j < vector.size(); ++j) {
            this.Fxa.add(this.wya.a().a(vector.elementAt(j)));
        }
        this.Fxa.select(0);
    }
    
    public void fb() {
        String _ = null;
        int _2 = -1;
        int a = -1;
        final Font font = new Font("SansSerif", 0, 12);
        if (this.wya != null) {
            _ = this.wya.u._("StatusBarFontName");
            _2 = this.wya.u._("StatusBarFontSize");
            a = this.wya.u.a("StatusBarFontStyle");
        }
        this.info.setFont(this.a(font, _, _2, a));
    }
    
    public Font a() {
        String _ = null;
        int _2 = -1;
        int a = -1;
        final Font font = new Font("SansSerif", 0, 11);
        if (this.wya != null) {
            _ = this.wya.u._("UIFontName");
            _2 = this.wya.u._("UIFontSize");
            a = this.wya.u.a("UIFontStyle");
        }
        return this.a(font, _, _2, a);
    }
    
    public void a(final Component component, final Font font) {
        component.setFont(font);
        if (component instanceof Container) {
            final Component[] components = ((Container)component).getComponents();
            for (int i = 0; i < components.length; ++i) {
                this.a(components[i], font);
            }
        }
    }
    
    public void b(final String s, final String s2, String s3) {
        final import import1 = new import(this, s);
        if (!import1.eqa) {
            this.iya = null;
        }
        else {
            this.iya = import1.name;
            this.wya._().b(import1);
        }
        final import import2 = new import(this, s2);
        if (!import2.eqa) {
            this.jya = null;
        }
        else {
            this.jya = import2.name;
            this.wya._().b(import2);
        }
        if (s3 == null) {
            s3 = "";
        }
        if (this.iya != null) {
            s3 = s3 + "," + s;
        }
        if (this.jya != null) {
            s3 = s3 + "," + s2;
        }
        final try try1 = new try(",");
        try1.l(s3);
        this.hya = try1.e();
        new Thread(this).start();
    }
    
    public void run() {
        try {
            Thread.sleep(50L);
            while (!this.isShowing()) {
                Thread.sleep(50L);
            }
            this.a(false);
            this.a(this.wya.a().a("msgStartingProgram"));
            this.wya._()._a();
            this.fya.setVisible(false);
            this.Uxa.removeAll();
            this._(this.hya);
            this.B.a();
            this.a(this.wya._().getMessage());
            this.fya.setVisible(this.kya);
            if (this.iya != null && this.iya.length() > 0) {
                this.L(this.iya);
            }
            if (this.jya != null && this.jya.length() > 0) {
                this.N(this.jya);
            }
            if (this.yya) {
                this.doLoadConfigAction();
            }
        }
        catch (InterruptedException ex) {}
        finally {
            this.invalidate();
            this.validate();
            this.wya._()._(this.qxa);
            this.wya._().b(this.sxa);
            if (this.qxa != null) {
                this.a(this.wya.a().a("msgLoadingData"));
            }
            boolean w = false;
            if (this.b()) {
                w = this.W();
            }
            if (!w) {
                this.B.b();
            }
            if (this.qxa != null) {
                this.a(this.wya._().getMessage());
            }
            this.B.repaint();
            this.pxa._();
            this.a(true);
        }
    }
    
    public void _(final boolean state) {
        this.wya._()._(state);
        this.Cxa.setState(state);
    }
    
    public void da(final int n) {
        this.pxa.y(n);
        this.Dxa.setState(n > 0);
    }
    
    public void n(final boolean state) {
        this.pxa.n(state);
        this.Exa.setState(state);
    }
    
    public void gb() {
        if (this.pxa != null) {
            this.pxa.B();
        }
    }
    
    public void hb() {
        if (this.pxa != null) {
            this.pxa.start();
        }
    }
    
    public while b() {
        return this.wya;
    }
    
    public void c() {
        this.B.d();
        this.B.c();
        this.B.e();
    }
    
    public synchronized void _(final int n, final int n2) {
        this.B._(n, n2);
    }
    
    public void aa(final int n) {
        this.ea(n);
        this.B.g(n);
    }
    
    public void ea(final int n) {
        if (n == 1) {
            this.txa.setState(true);
        }
        else if (n == 2) {
            this.uxa.setState(true);
        }
        else if (n == 3) {
            this.vxa.setState(true);
        }
        else if (n == 4) {
            this.wxa.setState(true);
        }
        else if (n == 5) {
            this.xxa.setState(true);
        }
        this.Uxa.setMultipleMode(n == 5);
        if (this.Nxa != null) {
            this.Nxa.setEnabled(n != 5);
        }
        this.Z(this.p() != 5);
        this.L(this.qxa);
        if (n != 5) {
            this.rxa.removeAll();
        }
        this.wya._().a(this.rxa._());
    }
    
    public void e(final int n) {
        this.B.e(n);
        if (n == 0) {
            this.Gxa.setState(true);
        }
        else if (n == 1) {
            this.Hxa.setState(true);
        }
        else if (n == 2) {
            this.Ixa.setState(true);
        }
    }
    
    public void j(final String s) {
        this.B.j(s);
    }
    
    public void b(final Component component, final Color background) {
        component.setBackground(background);
        if (component instanceof transient) {
            final Color[] b = this.wya.b("ImageButtonBg");
            if (b != null) {
                component.setBackground(b[0]);
            }
        }
        if (component instanceof Container) {
            final Component[] components = ((Container)component).getComponents();
            for (int i = 0; i < components.length; ++i) {
                if (components[i] instanceof transient || components[i] instanceof TextField || components[i] instanceof Panel || components[i] instanceof List || components[i] instanceof Button || components[i] instanceof Choice) {
                    this.b(components[i], background);
                }
            }
        }
    }
    
    public void i(final Component component, final Color background) {
        if (component instanceof transient && background != null) {
            component.setBackground(background);
        }
        if (component instanceof Container) {
            final Component[] components = ((Container)component).getComponents();
            for (int i = 0; i < components.length; ++i) {
                if (components[i] instanceof transient || components[i] instanceof TextField || components[i] instanceof Panel || components[i] instanceof List || components[i] instanceof Button || components[i] instanceof Choice) {
                    this.i(components[i], background);
                }
            }
        }
    }
    
    public void j(final Component component, final Color color) {
        if (component instanceof transient && color != null) {
            ((transient)component).e(color);
        }
        if (component instanceof Container) {
            final Component[] components = ((Container)component).getComponents();
            for (int i = 0; i < components.length; ++i) {
                if (components[i] instanceof transient || components[i] instanceof TextField || components[i] instanceof Panel || components[i] instanceof List || components[i] instanceof Button || components[i] instanceof Choice) {
                    this.j(components[i], color);
                }
            }
        }
    }
    
    public void _(final Component component, final Color foreground) {
        component.setForeground(foreground);
        if (component instanceof Container) {
            final Component[] components = ((Container)component).getComponents();
            for (int i = 0; i < components.length; ++i) {
                if (components[i] instanceof TextField || components[i] instanceof Panel || components[i] instanceof List || components[i] instanceof Button || components[i] instanceof Choice) {
                    this._(components[i], foreground);
                }
            }
        }
    }
    
    public void setCursor(final Cursor cursor) {
        final Container parent = this.getParent();
        if (parent != null) {
            parent.setCursor(cursor);
        }
        if (parent != null && !(parent instanceof FnChartsApplet) && this.Zwa != null) {
            this.Zwa.setCursor(cursor);
        }
    }
    
    private int p() {
        if (this.txa.getState()) {
            return 1;
        }
        if (this.uxa.getState()) {
            return 2;
        }
        if (this.vxa.getState()) {
            return 3;
        }
        if (this.wxa.getState()) {
            return 4;
        }
        if (this.txa.getState()) {
            return 1;
        }
        return 5;
    }
    
    public void m(String substring) {
        if (substring == null || substring.equals("")) {
            this.uya.select(this.wya.a().a("cbNoIndicator"));
            int n = 0;
            if (this.wya._() != null) {
                ++n;
            }
            if (this.wya.a() != null) {
                ++n;
            }
            this._(2 + n, this.p());
            this.tya = null;
            this.wya.m(null);
            return;
        }
        final String[] b = this.wya.b();
        String s = null;
        if (substring.indexOf("(") != -1) {
            s = substring.substring(substring.indexOf("(") + 1);
            if (s.indexOf(")") != -1) {
                s = s.substring(0, s.indexOf(")"));
            }
            substring = substring.substring(0, substring.indexOf("("));
        }
        boolean b2 = false;
        for (int i = 0; i < b.length; ++i) {
            if (b[i].equals(substring)) {
                this.uya.select(substring);
                this.wya.m(substring);
                if (s != null && this.wya._() != null) {
                    this.wya._().r(s);
                }
                this.tya = this.uya;
                this.ib();
                b2 = true;
                break;
            }
        }
        if (!b2) {
            this.uya.select(this.wya.a().a("cbNoIndicator"));
            this.wya.m(null);
        }
        int n2 = 0;
        if (this.wya._() != null) {
            ++n2;
        }
        if (this.wya.a() != null) {
            ++n2;
        }
        this._(2 + n2, this.p());
    }
    
    public void n(String substring) {
        if (substring == null || substring.equals("")) {
            this.vya.select(this.wya.a().a("cbNoIndicator"));
            int n = 0;
            if (this.wya._() != null) {
                ++n;
            }
            if (this.wya.a() != null) {
                ++n;
            }
            this._(2 + n, this.p());
            this.wya.n(null);
            return;
        }
        final String[] b = this.wya.b();
        String s = null;
        if (substring.indexOf("(") != -1) {
            s = substring.substring(substring.indexOf("(") + 1);
            if (s.indexOf(")") != -1) {
                s = s.substring(0, s.indexOf(")"));
            }
            substring = substring.substring(0, substring.indexOf("("));
        }
        boolean b2 = false;
        for (int i = 0; i < b.length; ++i) {
            if (b[i].equals(substring)) {
                this.vya.select(substring);
                this.wya.n(substring);
                if (s != null && this.wya.a() != null) {
                    this.wya.a().r(s);
                }
                this.tya = this.vya;
                this.ib();
                b2 = true;
                break;
            }
        }
        if (!b2) {
            this.vya.select(this.wya.a().a("cbNoIndicator"));
            this.wya.n(null);
        }
        int n2 = 0;
        if (this.wya._() != null) {
            ++n2;
        }
        if (this.wya.a() != null) {
            ++n2;
        }
        this._(2 + n2, this.p());
    }
    
    public void K(final String s) {
        this.wya._b();
        final try try1 = new try(";");
        if (s != null) {
            try1.l(s);
        }
        final Hashtable hashtable = new Hashtable<String, String>();
        for (int i = 0; i < try1.g(); ++i) {
            String s2 = try1.a(i);
            if (s2 != null) {
                if (s2.length() != 0) {
                    String s3 = "";
                    if (s2.indexOf("(") != -1) {
                        s3 = s2.substring(s2.indexOf("(") + 1);
                        if (s3.indexOf(")") != -1) {
                            s3 = s3.substring(0, s3.indexOf(")"));
                        }
                        s2 = s2.substring(0, s2.indexOf("("));
                    }
                    hashtable.put(s2, s3);
                }
            }
        }
        final String[] array = new String[this.Jxa.length];
        if (array == null) {
            return;
        }
        for (int j = 0; j < array.length; ++j) {
            array[j] = this.Jxa[j].a();
        }
        for (int k = 0; k < array.length; ++k) {
            this.Jxa[k].setState(hashtable.containsKey(array[k]));
            if (hashtable.containsKey(array[k])) {
                this.wya.c(array[k]);
                if (this.wya._(array[k]) != null) {
                    this.wya._(array[k]).r(hashtable.get(array[k]));
                }
            }
            else {
                this.wya.d(array[k]);
            }
        }
        this.jb();
    }
    
    public void a(final String text) {
        this.info.setText(text);
        this.info.repaint();
    }
    
    private void O(String upperCase) {
        if (upperCase == null || upperCase.length() == 0) {
            return;
        }
        if (this.Iya) {
            upperCase = upperCase.toUpperCase();
        }
        boolean b = false;
        for (int i = 0; i < this.Uxa.getItemCount(); ++i) {
            final int compareTo = upperCase.compareTo(this.Uxa.getItem(i));
            if (compareTo < 0) {
                this.Uxa.add(upperCase, i);
                b = true;
                break;
            }
            if (compareTo == 0) {
                b = true;
                break;
            }
        }
        if (!b) {
            this.Uxa.add(upperCase);
        }
    }
    
    private boolean e(String upperCase) {
        if (upperCase == null || upperCase.length() == 0) {
            return false;
        }
        if (this.Iya) {
            upperCase = upperCase.toUpperCase();
        }
        for (int i = 0; i < this.Uxa.getItemCount(); ++i) {
            if (upperCase.equals(this.Uxa.getItem(i))) {
                return true;
            }
        }
        return false;
    }
    
    private void P(final String s) {
        if (s == null) {
            return;
        }
        for (int i = 0; i < this.Uxa.getItemCount(); ++i) {
            if (s.equals(this.Uxa.getItem(i))) {
                this.Uxa.deselect(i);
                this.Uxa.remove(i);
                break;
            }
        }
    }
    
    private void g(final String[] array) {
        for (int i = 0; i < array.length; ++i) {
            this.P(array[i]);
        }
    }
    
    private void _(final String[] array) {
        if (array != null) {
            for (int i = 0; i < array.length; ++i) {
                final import import1 = new import(this, array[i]);
                if (import1.eqa) {
                    this.wya._().b(import1);
                    this.O(import1.name);
                }
            }
        }
    }
    
    private synchronized void kb() {
        this.a(false);
        try {
            public public1 = null;
            if (this.tya == this.uya) {
                public1 = this.wya._();
            }
            else if (this.tya == this.vya) {
                public1 = this.wya.a();
            }
            if (public1 != null && public1 instanceof protected) {
                final default default1 = new default(this.a(), this.wya.a().a("strConfirmIndicatorRemovalTitle"), this.wya.a().a("strConfirmIndicatorRemovalMessage") + ": " + public1.toString() + " ?", this.wya.a().a("btnYes"), this.wya.a().a("btnNo"));
                default1.show();
                if (default1._()) {
                    this.wya.a(public1);
                    this.Q(public1.toString());
                    this.ib();
                    this.B.b();
                    this.B.repaint();
                }
            }
        }
        finally {
            this.a(true);
        }
    }
    
    private synchronized void _a(final boolean b) {
        this.a(false);
        public public1 = null;
        if (this.tya == this.uya) {
            public1 = this.wya._();
        }
        else if (this.tya == this.vya) {
            public1 = this.wya.a();
        }
        String string = null;
        if (public1 != null) {
            string = public1.toString();
        }
        gea b2 = null;
        if (b && public1 != null && public1 instanceof protected) {
            b2 = ((protected)public1).b();
        }
        final xea xea = new xea(this.a(), this.wya.a().a("strJSIndicatorDefinition"), b2, this.wya.d(), this.wya.a());
        xea.show();
        if (xea._()) {
            final public _ = this.wya._(xea.a());
            String string2 = null;
            if (_ != null) {
                string2 = _.toString();
            }
            if (this.tya == this.uya) {
                if (_ != null) {
                    this.wya.m(_.toString());
                }
                else {
                    this.wya.m(null);
                }
            }
            else if (this.tya == this.vya) {
                if (_ != null) {
                    this.wya.n(_.toString());
                }
                else {
                    this.wya.n(null);
                }
            }
            this.i(string, string2);
            this.ib();
            new yea(this).start();
        }
        else {
            this.a(true);
        }
    }
    
    public void ca(final int n) {
        this.B.c(n);
        this.lb();
    }
    
    private Container b() {
        Container container;
        for (container = this.getParent(); container != null && !(container instanceof FnChartsApplet) && !(container instanceof synchronized); container = container.getParent()) {}
        return container;
    }
    
    private Frame a() {
        Container container;
        for (container = this.getParent(); container != null && !(container instanceof Frame); container = container.getParent()) {}
        if (container instanceof Frame) {
            return (Frame)container;
        }
        return null;
    }
    
    private synchronized void doLoadConfigAction() {
        if (this.Aya == null || this.Aya.length() == 0) {
            return;
        }
        try {
            this.a(false);
            this.a(this.wya.a().a("msgLoadingConfiguration"));
            String text = "";
            final this this2 = new this();
            this2._(false);
            try {
                this2.addObserver(this.B.b());
                if (this2.d(this.Aya)) {
                    text = this2.getText();
                }
            }
            finally {
                this2.deleteObserver(this.B.b());
            }
            if (text == null || text.length() == 0) {
                this.a(this.wya.a().a("msgConfigurationLoadError"));
            }
            else {
                final try try1 = new try(";");
                try1.l(text);
                final Hashtable<String, String> hashtable = new Hashtable<String, String>();
                for (int i = 0; i < try1.g(); ++i) {
                    final try try2 = new try("=");
                    try2.l(try1.a(i));
                    if (try2.g() == 2) {
                        hashtable.put(try2.a(0), try2.a(1));
                    }
                }
                this.a(hashtable);
                this.a(this.wya.a().a("msgConfigurationLoaded"));
            }
        }
        finally {
            this.a(true);
        }
    }
    
    private synchronized void doSaveConfigAction() {
        try {
            this.a(false);
            this.a(this.wya.a().a("msgSavingConfiguration"));
            final Hashtable _ = this._();
            final StringBuffer sb = new StringBuffer();
            final Enumeration keys = _.keys();
            while (keys.hasMoreElements()) {
                final String s = keys.nextElement();
                sb.append(s);
                sb.append("=");
                sb.append(_.get(s));
                sb.append(";");
            }
            final this this2 = new this();
            this2._(false);
            this2.setText(sb.toString());
            if (this2._(this.Bya, "configuration")) {
                this.a(this.wya.a().a("msgConfigurationSaved"));
            }
            else {
                this.a(this.wya.a().a("msgConfigurationSaveError"));
            }
        }
        finally {
            this.a(true);
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
    
    private synchronized boolean W() {
        if (this.qxa == null) {
            return false;
        }
        boolean b = false;
        String text = "";
        this.a(this.wya.a().a("msgLoadingStudies"));
        final this this2 = new this();
        this2._(false);
        try {
            this2.addObserver(this.B.b());
            if (this2.d(a.b(a.b(this._(this.Cya, this.wya._().b(this.wya._()._())), "{DataType}", this.wya._().a() ? "INTRA" : "EOD"), "{Periodicity}", c.j(this.wya._().ia())))) {
                text = this2.getText();
            }
        }
        finally {
            this2.deleteObserver(this.B.b());
        }
        if ((this.qxa != null && text == null) || text.length() == 0) {
            this.a(this.wya.a().a("msgStudiesLoadError"));
        }
        else {
            try {
                final wea wea = new wea(text);
                if (wea.i() == this.wya._().ia()) {
                    this._(wea);
                    b = true;
                    this.a(this.wya.a().a("msgStudiesLoaded"));
                }
                else {
                    this.a(this.wya.a().a("msgStudiesLoadError"));
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
                this.a(this.wya.a().a("msgStudiesLoadError"));
            }
        }
        return b;
    }
    
    private synchronized void mb() {
        this.a(this.wya.a().a("msgSavingStudies"));
        final wea b = this.b();
        final this this2 = new this();
        this2._(false);
        this2.setText(b.toString());
        if (this2._(a.b(a.b(this._(this.Dya, this.wya._().b(this.wya._()._())), "{DataType}", this.wya._().a() ? "INTRA" : "EOD"), "{Periodicity}", c.j(this.wya._().ia())), "studies")) {
            this.a(this.wya.a().a("msgStudiesSaved"));
        }
        else {
            this.a(this.wya.a().a("msgStudiesSaveError"));
        }
    }
    
    public void a(final boolean b) {
        if (!b) {
            this.pxa.m(true);
            this.setEnabled(false);
            this.setCursor(Cursor.getPredefinedCursor(3));
        }
        else {
            this.setCursor(Cursor.getPredefinedCursor(0));
            this.setEnabled(true);
            this.pxa.m(!this.Dxa.getState());
        }
    }
    
    private Font a(final Font font, String name, int size, int style) {
        if (name == null || name.length() == 0 || name.equals("null")) {
            name = font.getName();
        }
        if (size < 0) {
            size = font.getSize();
        }
        if (style < 0) {
            style = font.getStyle();
        }
        return new Font(name, style, size);
    }
    
    public void ba(final int n) {
        this.wya._().b(n);
        this.Fxa.select(this.wya.a().a(c.k(n)));
    }
    
    private void L(String upperCase) {
        this.qxa = null;
        for (int i = 0; i < this.Uxa.getItemCount(); ++i) {
            this.Uxa.deselect(i);
        }
        if (upperCase != null && this.Iya) {
            upperCase = upperCase.toUpperCase();
        }
        for (int j = 0; j < this.Uxa.getItemCount(); ++j) {
            if (this.Uxa.getItem(j).equals(upperCase)) {
                this.Uxa.select(j);
                this.qxa = this.Uxa.getItem(j);
                this.Vxa.setText(this.qxa);
                break;
            }
        }
        if (this.Uxa.isMultipleMode()) {
            for (int k = 0; k < this.Uxa.getItemCount(); ++k) {
                if (this.rxa.a(this.Uxa.getItem(k))) {
                    this.Uxa.select(k);
                }
            }
        }
    }
    
    private void N(String upperCase) {
        if (upperCase != null) {
            if (this.Iya) {
                upperCase = upperCase.toUpperCase();
            }
            this.Wxa.setText(upperCase);
        }
        else {
            this.Wxa.setText("");
        }
        this.sxa = upperCase;
    }
    
    private void a(final Component component, final Color background) {
        component.setBackground(background);
        if (component instanceof Container) {
            final Component[] components = ((Container)component).getComponents();
            for (int i = 0; i < components.length; ++i) {
                if (components[i] instanceof TextField || components[i] instanceof Panel || components[i] instanceof List || components[i] instanceof Choice) {
                    this.a(components[i], background);
                }
            }
        }
    }
    
    private Hashtable _(final zea zea) {
        final Hashtable<String, const> hashtable = new Hashtable<String, const>();
        (this.Ia = new transient("/images/" + this.Gya + "minimaxi.gif", "btnMaximize", false)).addActionListener(zea);
        hashtable.put("btnMaximize".toUpperCase(), (const)this.Ia);
        (this.Cxa = new transient("/images/" + this.Gya + "usecache.gif", "bUseBrowserCache", false)).addActionListener(zea);
        hashtable.put("btnUseBrowserCache".toUpperCase(), (const)this.Cxa);
        (this.Dxa = new transient("/images/" + this.Gya + "autorefresh.gif", "bAutoRefreshActive", true)).addActionListener(zea);
        hashtable.put("btnAutoRefreshSwitch".toUpperCase(), (const)this.Dxa);
        (this.Exa = new transient("/images/" + this.Gya + "refreshbeep.gif", "bAutoRefreshBeep", true)).addActionListener(zea);
        hashtable.put("btnAutoRefreshBeepSwitch".toUpperCase(), (const)this.Exa);
        (this.lya = new transient("/images/" + this.Gya + "refresh.gif", "btnRefresh")).addActionListener(zea);
        hashtable.put("btnRefresh".toUpperCase(), (const)this.lya);
        (this.oya = new transient("/images/" + this.Gya + "moveleft.gif", "btnLeft")).addActionListener(zea);
        hashtable.put("btnMoveLeft".toUpperCase(), (const)this.oya);
        (this.nya = new transient("/images/" + this.Gya + "decreaserange.gif", "btnLess")).addActionListener(zea);
        hashtable.put("btnZoomIn".toUpperCase(), (const)this.nya);
        (this.mya = new transient("/images/" + this.Gya + "increaserange.gif", "btnMore")).addActionListener(zea);
        hashtable.put("btnZoomOut".toUpperCase(), (const)this.mya);
        (this.pya = new transient("/images/" + this.Gya + "moveright.gif", "btnRight")).addActionListener(zea);
        hashtable.put("btnMoveRight".toUpperCase(), (const)this.pya);
        (this.Ta = new Choice()).add(this.wya.a().a("cbRange_free"));
        this.Ta.add(this.wya.a().a("cbRange_1month"));
        this.Ta.add(this.wya.a().a("cbRange_3months"));
        this.Ta.add(this.wya.a().a("cbRange_6months"));
        this.Ta.add(this.wya.a().a("cbRange_1year"));
        this.Ta.add(this.wya.a().a("cbRange_2years"));
        this.Ta.add(this.wya.a().a("cbRange_3years"));
        this.Ta.add(this.wya.a().a("cbRange_5years"));
        this.Ta.add(this.wya.a().a("cbRange_10years"));
        this.Ta.add(this.wya.a().a("cbRange_all"));
        this.lb();
        this.Ta.addItemListener(new Aea(this));
        hashtable.put("selectRange".toUpperCase(), (const)this.Ta);
        final hea hea = new hea();
        (this.Gxa = new transient("/images/" + this.Gya + "line.gif", "btnLine", hea, true)).addActionListener(zea);
        hashtable.put("btnLine".toUpperCase(), (const)this.Gxa);
        (this.Hxa = new transient("/images/" + this.Gya + "ohlc.gif", "btnOhlc", hea, false)).addActionListener(zea);
        hashtable.put("btnOhlc".toUpperCase(), (const)this.Hxa);
        (this.Ixa = new transient("/images/" + this.Gya + "candle.gif", "btnCandle", hea, false)).addActionListener(zea);
        hashtable.put("btnCandle".toUpperCase(), (const)this.Ixa);
        final hea hea2 = new hea();
        (this.txa = new transient("/images/" + this.Gya + "scalelogcor.gif", "btnLogCor", hea2, true)).addActionListener(zea);
        hashtable.put("btnScaleLogCor".toUpperCase(), (const)this.txa);
        (this.uxa = new transient("/images/" + this.Gya + "scaleloguncor.gif", "btnLogUncor", hea2, false)).addActionListener(zea);
        hashtable.put("btnScaleLogUncor".toUpperCase(), (const)this.uxa);
        (this.vxa = new transient("/images/" + this.Gya + "scalelincor.gif", "btnLinCor", hea2, false)).addActionListener(zea);
        hashtable.put("btnScaleLinCor".toUpperCase(), (const)this.vxa);
        (this.wxa = new transient("/images/" + this.Gya + "scalelinuncor.gif", "btnLinUncor", hea2, false)).addActionListener(zea);
        hashtable.put("btnScaleLinUncor".toUpperCase(), (const)this.wxa);
        (this.xxa = new transient("/images/" + this.Gya + "scalepercent.gif", "btnScalePercent", hea2, false)).addActionListener(zea);
        hashtable.put("btnScalePercent".toUpperCase(), (const)this.xxa);
        final Bea bea = new Bea(this);
        this.Fxa = new Choice();
        for (int i = 0; i < c.Lua.length; ++i) {
            this.Fxa.add(this.wya.a().a(c.Lua[i]));
        }
        this.Fxa.select(this.wya.a().a(c.k(100001)));
        this.Fxa.addItemListener(bea);
        hashtable.put("selectDataCompression".toUpperCase(), (const)this.Fxa);
        (this.Axa = new transient("/images/" + this.Gya + "timeframe.gif", "bSetTimeframe")).addActionListener(zea);
        hashtable.put("btnSetTimeframe".toUpperCase(), (const)this.Axa);
        (this.zxa = new transient("/images/" + this.Gya + "chartproperties.gif", "bChartProperties")).addActionListener(zea);
        hashtable.put("btnChartProperties".toUpperCase(), (const)this.zxa);
        (this.Bxa = new transient("/images/" + this.Gya + "volume.gif", "bVolumeVisible", true)).addActionListener(zea);
        hashtable.put("btnVolumeSwitch".toUpperCase(), (const)this.Bxa);
        (this.yxa = new transient("/images/" + this.Gya + "stocklist.gif", "bStockListVisible", this.kya)).addActionListener(zea);
        hashtable.put("btnSymbolEntryVisibilitySwitch".toUpperCase(), (const)this.yxa);
        final Cea cea = new Cea(this);
        (this.uya = new Choice()).addItemListener(cea);
        this.uya.addFocusListener(cea);
        this.uya.addMouseListener(cea);
        (this.vya = new Choice()).addItemListener(cea);
        this.vya.addFocusListener(cea);
        this.vya.addMouseListener(cea);
        this.nb();
        hashtable.put("selectIndicator1".toUpperCase(), (const)this.uya);
        hashtable.put("selectIndicator2".toUpperCase(), (const)this.vya);
        this._ya = new PopupMenu();
        (this.aya = new MenuItem(this.wya.a().a("menuDefineIndicator"))).addActionListener(cea);
        this._ya.add(this.aya);
        (this.bya = new MenuItem(this.wya.a().a("menuEditIndicator"))).addActionListener(cea);
        this._ya.add(this.bya);
        (this.cya = new MenuItem(this.wya.a().a("menuDeleteIndicator"))).addActionListener(cea);
        this._ya.add(this.cya);
        this.uya.add(this._ya);
        this.vya.add(this._ya);
        final Dea dea = new Dea(this);
        this.Oa = new const[3];
        for (int j = 0; j < this.Oa.length; ++j) {
            (this.Oa[j] = new const(3, true)).addKeyListener(dea);
            this.Oa[j].addFocusListener(dea);
            hashtable.put(("txtParam" + (j + 1)).toUpperCase(), this.Oa[j]);
        }
        final String[] n = this.wya.n();
        this.Jxa = new transient[n.length];
        this.sya = new const[n.length];
        final Eea eea = new Eea(this);
        for (int k = 0; k < n.length; ++k) {
            hashtable.put(("btn" + n[k]).toUpperCase(), (const)(this.Jxa[k] = new transient("/images/" + this.Gya + n[k].toLowerCase() + ".gif", n[k], false)));
            this.Jxa[k].addActionListener(eea);
            this.sya[k] = null;
            if (this.wya._(n[k]).L() > 0) {
                (this.sya[k] = new const(3, true)).addKeyListener(dea);
                this.sya[k].addFocusListener(dea);
                hashtable.put(("txt" + n[k] + "Param").toUpperCase(), this.sya[k]);
            }
        }
        this.jb();
        return hashtable;
    }
    
    private Component _(final Hashtable hashtable, String upperCase) {
        if (upperCase == null) {
            return null;
        }
        upperCase = upperCase.toUpperCase();
        Component component = hashtable.get(upperCase);
        if (component == null) {
            int int1 = -1;
            final String s = "SEPARATOR";
            if (upperCase.startsWith(s)) {
                try {
                    int n;
                    for (n = upperCase.lastIndexOf(s) + s.length(); n < upperCase.length() && !Character.isDigit(upperCase.charAt(n)); ++n) {}
                    final int n2 = n;
                    int n3;
                    for (n3 = n2 + 1; n3 < upperCase.length() && Character.isDigit(upperCase.charAt(n3)); ++n3) {}
                    final int n4 = n3;
                    if (n2 < upperCase.length()) {
                        int1 = Integer.parseInt(upperCase.substring(n2, n4));
                    }
                }
                catch (NumberFormatException ex) {}
                if (int1 > 0) {
                    component = new rea(int1);
                }
            }
        }
        return component;
    }
    
    private void b(final boolean b, final boolean b2, String s, String s2) {
        final zea zea = new zea(this);
        final Hashtable _ = this._(zea);
        if (s == null) {
            s = "btnMaximize,btnUseBrowserCache,btnAutoRefreshSwitch,btnAutoRefreshBeepSwitch,Separator6,btnRefresh,btnMoveLeft,btnZoomIn,btnZoomOut,btnMoveRight,selectRange,Separator6,btnLine,btnOhlc,btnCandle,Separator6,btnScaleLogCor,btnScaleLogUncor,btnScaleLinCor,btnScaleLinUncor,btnScalePercent,Separator6,selectDataCompression,Separator6,btnSetTimeFrame,btnChartProperties,btnVolumeSwitch,btnSymbolEntryVisibilitySwitch";
        }
        if (s2 == null) {
            s2 = "selectIndicator1,selectIndicator2,Separator2,txtParam1,txtParam2,txtParam3,Separator6,btnParabolicSAR,Separator6,btnBol,Separator2,txtBolParam,Separator6,btnEma,Separator2,txtEmaParam,Separator6,btnSma1,Separator2,txtSma1Param,Separator6,btnSma2,Separator2,txtSma2Param,Separator6,btnSma3,Separator2,txtSma3Param,Separator6,btnVolEma,Separator2,txtVolEmaParam";
        }
        final try try1 = new try(",");
        try1.l(s);
        for (int i = 0; i < try1.g(); ++i) {
            final Component _2 = this._(_, try1.a(i));
            if (_2 != null) {
                if (_2 == this.Dxa || _2 == this.Dxa) {
                    if (b) {
                        this.Sxa.add(_2);
                    }
                }
                else if (_2 == this.Bxa) {
                    if (!b2) {
                        this.Sxa.add(_2);
                    }
                }
                else if (_2 == this.uya || _2 == this.vya) {
                    this.Sxa.add(_2);
                    this.tya = (Choice)_2;
                }
                else {
                    this.Sxa.add(_2);
                }
            }
        }
        try1.l(s2);
        for (int j = 0; j < try1.g(); ++j) {
            final Component _3 = this._(_, try1.a(j));
            if (_3 != null) {
                if (_3 == this.Dxa || _3 == this.Dxa) {
                    if (b) {
                        this.Txa.add(_3);
                    }
                }
                else if (_3 == this.Bxa) {
                    if (!b2) {
                        this.Txa.add(_3);
                    }
                }
                else if (_3 == this.uya || _3 == this.vya) {
                    this.Txa.add(_3);
                    this.tya = (Choice)_3;
                }
                else {
                    this.Txa.add(_3);
                }
            }
        }
        this.ib();
        if (this.qya != null) {
            this.qya.addActionListener(zea);
        }
        if (this.rya != null) {
            this.rya.addActionListener(zea);
        }
        if (b2) {
            this.B.f(0);
        }
        else if (this.Bxa.getState()) {
            this.B.f(1);
        }
        else {
            this.B.f(2);
        }
    }
    
    private void lb() {
        switch (this.B.l()) {
            case 10: {
                this.Ta.select(this.wya.a().a("cbRange_1month"));
                break;
            }
            case 11: {
                this.Ta.select(this.wya.a().a("cbRange_3months"));
                break;
            }
            case 12: {
                this.Ta.select(this.wya.a().a("cbRange_6months"));
                break;
            }
            case 13: {
                this.Ta.select(this.wya.a().a("cbRange_1year"));
                break;
            }
            case 14: {
                this.Ta.select(this.wya.a().a("cbRange_2years"));
                break;
            }
            case 15: {
                this.Ta.select(this.wya.a().a("cbRange_3years"));
                break;
            }
            case 16: {
                this.Ta.select(this.wya.a().a("cbRange_5years"));
                break;
            }
            case 17: {
                this.Ta.select(this.wya.a().a("cbRange_10years"));
                break;
            }
            case 0: {
                this.Ta.select(this.wya.a().a("cbRange_all"));
                break;
            }
            case -1: {
                this.Ta.select(this.wya.a().a("cbRange_free"));
                break;
            }
        }
    }
    
    private void eb() {
        this.uya.setVisible(false);
        this.uya.select(0);
        this.uya.setEnabled(false);
        while (this.uya.getItemCount() > 1) {
            this.uya.remove(this.uya.getItemCount() - 1);
        }
        this.vya.setVisible(false);
        this.vya.select(0);
        this.vya.setEnabled(false);
        while (this.vya.getItemCount() > 1) {
            this.vya.remove(this.vya.getItemCount() - 1);
        }
        this.nb();
        this.uya.setVisible(true);
        this.uya.setEnabled(true);
        this.vya.setVisible(true);
        this.vya.setEnabled(true);
        this.uya.invalidate();
        this.vya.invalidate();
        this.Txa.invalidate();
        this.gya.validate();
    }
    
    private void i(final String s, final String s2) {
        if (s != null && s2 != null && s.equals(s2) && this.wya.b(s) != null) {
            return;
        }
        this.uya.setVisible(false);
        this.vya.setVisible(false);
        this.uya.setEnabled(false);
        this.vya.setEnabled(false);
        this.uya.select(0);
        this.vya.select(0);
        if (s != null && this.wya.b(s) == null) {
            this.uya.remove(s);
            this.vya.remove(s);
        }
        this.uya.select(0);
        this.vya.select(0);
        if (s2 != null) {
            boolean b = false;
            for (int i = 0; i < this.uya.getItemCount(); ++i) {
                if (s2.equals(this.uya.getItem(i))) {
                    b = true;
                    break;
                }
            }
            if (!b) {
                boolean b2 = false;
                for (int j = 0; j < this.uya.getItemCount(); ++j) {
                    if (s2.compareTo(this.uya.getItem(j)) < 0) {
                        this.uya.insert(s2, j);
                        b2 = true;
                        break;
                    }
                }
                if (!b2) {
                    this.uya.add(s2);
                }
            }
            final public _ = this.wya._();
            if (_ != null) {
                this.uya.select(_.toString());
            }
            boolean b3 = false;
            this.vya.select(0);
            for (int k = 0; k < this.vya.getItemCount(); ++k) {
                if (s2.equals(this.vya.getItem(k))) {
                    b3 = true;
                    break;
                }
            }
            if (!b3) {
                boolean b4 = false;
                for (int l = 0; l < this.vya.getItemCount(); ++l) {
                    if (s2.compareTo(this.vya.getItem(l)) < 0) {
                        this.vya.insert(s2, l);
                        b4 = true;
                        break;
                    }
                }
                if (!b4) {
                    this.vya.add(s2);
                }
            }
            final public a = this.wya.a();
            if (a != null) {
                this.vya.select(a.toString());
            }
        }
        this.uya.setEnabled(true);
        this.vya.setEnabled(true);
        this.uya.setVisible(true);
        this.vya.setVisible(true);
        this.uya.invalidate();
        this.vya.invalidate();
        this.Txa.invalidate();
        this.gya.validate();
    }
    
    private void Q(final String s) {
        this.uya.setVisible(false);
        this.vya.setVisible(false);
        this.uya.setEnabled(false);
        this.vya.setEnabled(false);
        this.uya.select(0);
        this.vya.select(0);
        this.uya.remove(s);
        this.vya.remove(s);
        final public _ = this.wya._();
        if (_ != null) {
            this.uya.select(_.toString());
        }
        else {
            this.uya.select(0);
        }
        final public a = this.wya.a();
        if (a != null) {
            this.vya.select(a.toString());
        }
        else {
            this.vya.select(0);
        }
        this.uya.setEnabled(true);
        this.vya.setEnabled(true);
        this.uya.setVisible(true);
        this.vya.setVisible(true);
        this.uya.invalidate();
        this.vya.invalidate();
        this.Txa.invalidate();
        this.gya.validate();
    }
    
    private void nb() {
        String string = null;
        final public _ = this.wya._();
        if (_ != null) {
            string = _.toString();
        }
        String string2 = null;
        final public a = this.wya.a();
        if (a != null) {
            string2 = a.toString();
        }
        final String[] b = this.wya.b();
        a.b(b);
        for (int i = 0; i < b.length; ++i) {
            this.uya.add(b[i]);
        }
        if (!this.uya.getItem(0).equals(this.wya.a().a("cbNoIndicator"))) {
            this.uya.insert(this.wya.a().a("cbNoIndicator"), 0);
        }
        for (int j = 0; j < b.length; ++j) {
            this.vya.add(b[j]);
        }
        if (!this.vya.getItem(0).equals(this.wya.a().a("cbNoIndicator"))) {
            this.vya.insert(this.wya.a().a("cbNoIndicator"), 0);
        }
        this.m(string);
        this.n(string2);
    }
    
    private void ib() {
        public public1 = null;
        if (this.tya == this.uya) {
            public1 = this.wya._();
        }
        else if (this.tya == this.vya) {
            public1 = this.wya.a();
        }
        for (int i = 0; i < this.Oa.length; ++i) {
            this.Oa[i].setText("");
            this.Oa[i].setEnabled(false);
        }
        if (public1 == null || this.tya.getSelectedItem().equals(this.wya.a().a("cbNoIndicator"))) {
            return;
        }
        for (int j = 0; j < public1.L(); ++j) {
            this.Oa[j].setText(String.valueOf(public1.getParam(j)));
            this.Oa[j].setEnabled(true);
        }
    }
    
    private void Z(final boolean b) {
        for (int i = 0; i < this.Jxa.length; ++i) {
            this.Jxa[i].setEnabled(b);
        }
        for (int j = 0; j < this.sya.length; ++j) {
            if (this.sya[j] != null) {
                this.sya[j].setEnabled(b);
            }
        }
    }
    
    private void jb() {
        final String[] array = new String[this.Jxa.length];
        if (array == null) {
            return;
        }
        for (int i = 0; i < array.length; ++i) {
            array[i] = this.Jxa[i].a();
        }
        for (int j = 0; j < this.sya.length; ++j) {
            if (this.sya[j] != null) {
                this.sya[j].setText("");
                this.sya[j].setEnabled(false);
            }
        }
        for (int k = 0; k < this.sya.length; ++k) {
            final public _ = this.wya._(array[k]);
            if (_ != null && _.L() > 0 && this.sya[k] != null) {
                this.sya[k].setText(String.valueOf(_.getParam(0)));
                this.sya[k].setEnabled(true);
            }
        }
    }
    
    private void ob() {
        public public1 = null;
        if (this.tya == this.uya) {
            public1 = this.wya._();
        }
        else if (this.tya == this.vya) {
            public1 = this.wya.a();
        }
        if (public1 == null || this.tya.getSelectedItem().equals(this.wya.a().a("cbNoIndicator"))) {
            return;
        }
        for (int n = 0; n < this.Oa.length && n < public1.L(); ++n) {
            int n2;
            try {
                n2 = Integer.parseInt(this.Oa[n].getText());
            }
            catch (NumberFormatException ex) {
                n2 = public1.getParam(n);
            }
            if (n2 < 2 || n2 > 999) {
                n2 = public1.getParam(n);
            }
            this.Oa[n].setText(String.valueOf(n2));
            public1.a(n, n2);
        }
    }
    
    private void pb() {
        final String[] array = new String[this.Jxa.length];
        if (array == null) {
            return;
        }
        for (int i = 0; i < array.length; ++i) {
            array[i] = this.Jxa[i].a();
        }
        for (int j = 0; j < this.sya.length; ++j) {
            if (this.sya[j] != null) {
                final public _ = this.wya._(array[j]);
                int n;
                try {
                    n = Integer.parseInt(this.sya[j].getText());
                }
                catch (NumberFormatException ex) {
                    n = _.getParam(0);
                }
                if (n < 2 || n > 999) {
                    n = _.getParam(0);
                }
                this.sya[j].setText(String.valueOf(n));
                _.a(0, n);
            }
        }
    }
    
    public static String a(final byte[] array) {
        if (array == null) {
            return null;
        }
        if (array.length == 0) {
            return "";
        }
        final byte[] array2 = new byte[array.length + 4];
        array2[0] = 31;
        array2[1] = -117;
        array2[2] = 8;
        array2[3] = 0;
        System.arraycopy(array, 0, array2, 4, array.length);
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(512);
        InflaterInputStream inflaterInputStream = null;
        try {
            inflaterInputStream = new GZIPInputStream(new ByteArrayInputStream(array2));
            int read;
            while ((read = inflaterInputStream.read()) != -1) {
                byteArrayOutputStream.write(read);
            }
        }
        catch (IOException ex) {
            return null;
        }
        finally {
            if (inflaterInputStream != null) {
                try {
                    ((GZIPInputStream)inflaterInputStream).close();
                }
                catch (IOException ex2) {}
            }
        }
        try {
            return new String(byteArrayOutputStream.toByteArray(), "UTF8");
        }
        catch (UnsupportedEncodingException ex3) {
            return null;
        }
    }
    
    public d b() {
        return this.B.b();
    }
    
    public void fa(final int n) {
        this.fya.H(Math.max(50, n));
    }
    
    public void aa(final boolean yya) {
        this.yya = yya;
    }
    
    public void qb() {
        this.Ia.setVisible(false);
    }
    
    public void rb() {
        this.yxa.setVisible(false);
    }
    
    public void U(final boolean b) {
        this.wya._().U(b);
    }
    
    public void b(final Component component) {
        if (component instanceof transient) {
            ((transient)component).dispose();
        }
        if (component instanceof Container) {
            final Component[] components = ((Container)component).getComponents();
            for (int i = 0; i < components.length; ++i) {
                this.b(components[i]);
            }
        }
    }
    
    public synchronized void g(final int eya, final int fya) {
        this.Eya = eya;
        this.Fya = fya;
    }
    
    public String getName() {
        return a(this.Kya);
    }
    
    public String G() {
        return a(this.Lya);
    }
    
    public void ba(final boolean hya) {
        this.Hya = hya;
    }
    
    public void Y(final boolean state) {
        this.Pxa.setState(state);
    }
    
    public boolean b() {
        return this.Pxa.isVisible() && this.Pxa.getState();
    }
    
    public void ca(final boolean jya) {
        this.Jya = jya;
    }
    
    static oea _(final super super1) {
        return super1.Mya;
    }
    
    static List b(final super super1) {
        return super1.Uxa;
    }
    
    static while b(final super super1) {
        return super1.wya;
    }
    
    static boolean l(final super super1) {
        return super1.xya;
    }
    
    static Choice b(final super super1) {
        return super1.uya;
    }
    
    static Choice _(final super super1) {
        return super1.vya;
    }
    
    static Label b(final super super1) {
        return super1.info;
    }
    
    static continue _(final super super1) {
        return super1.zya;
    }
    
    static nea _(final super super1) {
        return super1.B;
    }
    
    static boolean b(final super super1) {
        return super1.Iya;
    }
    
    static const a(final super super1) {
        return super1.Vxa;
    }
    
    static void e(final super super1) {
        super1.ib();
    }
    
    static void f(final super super1) {
        super1.jb();
    }
    
    static void g(final super super1) {
        super1.ob();
    }
    
    static void h(final super super1) {
        super1.pb();
    }
    
    static int _(final super super1) {
        return super1.p();
    }
    
    static Choice b(final super super1, final Choice tya) {
        return super1.tya = tya;
    }
    
    static Choice n(final super super1) {
        return super1.tya;
    }
    
    static MenuItem a(final super super1) {
        return super1.cya;
    }
    
    static void i(final super super1) {
        super1.kb();
    }
    
    static MenuItem b(final super super1) {
        return super1.aya;
    }
    
    static void a(final super super1, final boolean b) {
        super1._a(b);
    }
    
    static MenuItem _(final super super1) {
        return super1.bya;
    }
    
    static PopupMenu b(final super super1) {
        return super1._ya;
    }
    
    static Choice c(final super super1) {
        return super1.Ta;
    }
    
    static Choice a(final super super1) {
        return super1.Fxa;
    }
    
    static boolean a(final super super1) {
        return super1.W();
    }
    
    static jea _(final super super1) {
        return super1.pxa;
    }
    
    static kea b(final super super1) {
        return super1.rxa;
    }
    
    static String a(final super super1) {
        return super1.qxa;
    }
    
    static MenuItem g(final super super1) {
        return super1.Yxa;
    }
    
    static void a(final super super1, final String[] array) {
        super1.g(array);
    }
    
    static MenuItem h(final super super1) {
        return super1.Zxa;
    }
    
    static String _(final super super1, final String qxa) {
        return super1.qxa = qxa;
    }
    
    static boolean _(final super super1) {
        return super1.Hya;
    }
    
    static void b(final super super1, final String s) {
        super1.O(s);
    }
    
    static void _(final super super1, final String s) {
        super1.L(s);
    }
    
    static const b(final super super1) {
        return super1.Wxa;
    }
    
    static boolean _(final super super1, final String s) {
        return super1.e(s);
    }
    
    static void a(final super super1, final String s) {
        super1.N(s);
    }
    
    static MenuItem i(final super super1) {
        return super1.Xxa;
    }
    
    static PopupMenu _(final super super1) {
        return super1.dya;
    }
    
    static boolean n(final super super1) {
        return super1.Jya;
    }
    
    static transient W(final super super1) {
        return super1.Lxa;
    }
    
    static void b(final super super1) {
        super1.doLoadConfigAction();
    }
    
    static transient X(final super super1) {
        return super1.Kxa;
    }
    
    static void a(final super super1) {
        super1.doSaveConfigAction();
    }
    
    static transient Y(final super super1) {
        return super1.Oxa;
    }
    
    static transient U(final super super1) {
        return super1.Nxa;
    }
    
    static void _(final super super1) {
        super1.mb();
    }
    
    static transient Z(final super super1) {
        return super1.Mxa;
    }
    
    static Panel a(final super super1) {
        return super1.gya;
    }
    
    static continue b(final super super1) {
        return super1.fya;
    }
    
    static boolean m(final super super1) {
        return super1.kya;
    }
    
    static Container a(final super super1) {
        return super1.b();
    }
    
    static FnChartsApplet a(final super super1) {
        return super1.Zwa;
    }
    
    static Button b(final super super1) {
        return super1.qya;
    }
    
    static Button _(final super super1) {
        return super1.rya;
    }
    
    static transient b(final super super1) {
        return super1.mya;
    }
    
    static void j(final super super1) {
        super1.lb();
    }
    
    static transient _(final super super1) {
        return super1.nya;
    }
    
    static transient g(final super super1) {
        return super1.oya;
    }
    
    static transient h(final super super1) {
        return super1.pya;
    }
    
    static transient i(final super super1) {
        return super1.Gxa;
    }
    
    static transient j(final super super1) {
        return super1.Hxa;
    }
    
    static transient k(final super super1) {
        return super1.Ixa;
    }
    
    static transient l(final super super1) {
        return super1.Axa;
    }
    
    static Frame b(final super super1) {
        return super1.a();
    }
    
    static transient m(final super super1) {
        return super1.zxa;
    }
    
    static String[] b(final super super1) {
        return super1.Nya;
    }
    
    static transient n(final super super1) {
        return super1.yxa;
    }
    
    static boolean a(final super super1, final boolean kya) {
        return super1.kya = kya;
    }
    
    static transient c(final super super1) {
        return super1.Bxa;
    }
    
    static transient a(final super super1) {
        return super1.Cxa;
    }
    
    static transient d(final super super1) {
        return super1.Dxa;
    }
    
    static transient e(final super super1) {
        return super1.Exa;
    }
    
    static transient f(final super super1) {
        return super1.txa;
    }
    
    static transient Q(final super super1) {
        return super1.uxa;
    }
    
    static transient R(final super super1) {
        return super1.vxa;
    }
    
    static transient S(final super super1) {
        return super1.wxa;
    }
    
    static transient T(final super super1) {
        return super1.xxa;
    }
    
    static void b(final super super1, final boolean b) {
        super1.Z(b);
    }
    
    static transient V(final super super1) {
        return super1.lya;
    }
    
    static String b(final super super1) {
        return super1.sxa;
    }
}
