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
import java.awt.Button;
import java.awt.PopupMenu;
import java.awt.MenuItem;
import java.awt.List;
import java.util.Hashtable;
import java.awt.Label;
import java.awt.Choice;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

public class n extends Panel implements Runnable
{
    public static final boolean Tsa = false;
    public static final boolean Usa = false;
    private static final int Vsa = 3;
    private static final String Wsa = "btnMaximize,btnUseBrowserCache,btnAutoRefreshSwitch,btnAutoRefreshBeepSwitch,Separator6,btnRefresh,btnMoveLeft,btnZoomIn,btnZoomOut,btnMoveRight,selectRange,Separator6,btnLine,btnOhlc,btnCandle,Separator6,btnScaleLogCor,btnScaleLogUncor,btnScaleLinCor,btnScaleLinUncor,btnScalePercent,Separator6,selectDataCompression,Separator6,btnSetTimeFrame,btnChartProperties,btnVolumeSwitch,btnSymbolEntryVisibilitySwitch";
    private static final String Xsa = "selectIndicator1,selectIndicator2,Separator2,txtParam1,txtParam2,txtParam3,Separator6,btnParabolicSAR,Separator3,btnPivotPoints,Separator3,btnDMA,Separator6,btnBol,Separator2,txtBolParam,Separator6,btnEma1,Separator2,txtEma1Param,Separator6,btnSma1,Separator2,txtSma1Param,Separator6,btnSma2,Separator2,txtSma2Param,Separator6,btnSma3,Separator2,txtSma3Param,Separator6,btnVolEma,Separator2,txtVolEmaParam";
    public static final int Ysa = 1;
    public static final int Zsa = 2;
    public static final int _ta = 3;
    public static final int ata = 4;
    public static final int bta = 5;
    private static final String cta = "";
    private gj dta;
    private String eta;
    private hj fta;
    private String gta;
    private t hta;
    private t ita;
    private t jta;
    private t kta;
    private t lta;
    private t mta;
    private t nta;
    private t ota;
    private t pta;
    private t qta;
    private t rta;
    private t sta;
    private Choice tta;
    private t uta;
    private t vta;
    private t wta;
    private t[] xta;
    private t yta;
    private t zta;
    private t Ata;
    private t Bta;
    private t Cta;
    private t Dta;
    private t Eta;
    private t Fta;
    private Label info;
    protected ij _;
    private Panel Gta;
    private Panel Hta;
    private Hashtable Ita;
    private Choice Jta;
    private List Kta;
    private c Lta;
    private c Mta;
    private MenuItem Nta;
    private MenuItem Ota;
    private MenuItem Pta;
    private MenuItem Qta;
    private PopupMenu Rta;
    private MenuItem Sta;
    private MenuItem Tta;
    private MenuItem Uta;
    private PopupMenu Vta;
    private jj Wta;
    private a Xta;
    private Panel Yta;
    private String[] Zta;
    private Hashtable _ua;
    private String aua;
    private String bua;
    private boolean cua;
    protected t Na;
    private t dua;
    private t eua;
    private t fua;
    private t gua;
    private t hua;
    private Choice Za;
    private Button iua;
    private Button jua;
    private c[] Ua;
    private c[] kua;
    private Choice lua;
    private Choice mua;
    private Choice nua;
    private default lb;
    private break oua;
    private boolean pua;
    private boolean qua;
    private FnChartsApplet Nsa;
    private a rua;
    private final String sua;
    private final String tua;
    private final String uua;
    private final String vua;
    private int wua;
    private int xua;
    private String yua;
    private boolean zua;
    private boolean Aua;
    private boolean Bua;
    private final byte[] Cua;
    private static final byte[] Dua;
    private kj Eua;
    private final String[] Fua;
    
    public n(final FnChartsApplet nsa, final o o, final String s, final String s2, final int n, final String s3, final String s4, final String s5, final String s6, final String s7, final boolean cua, final boolean b, final boolean b2, final boolean b3, final boolean pua, final String yua, final Hashtable hashtable, final String s8, final int n2, final String s9, final String s10, final boolean b4, final boolean aua, final String s11, final String s12) {
        this.eta = null;
        this.fta = new hj();
        this.gta = null;
        this.Ita = new kj();
        this.Zta = null;
        this._ua = new kj();
        this.aua = "";
        this.bua = "";
        this.cua = true;
        this.pua = true;
        this.qua = false;
        this.Nsa = null;
        this.wua = 760;
        this.xua = 540;
        this.yua = "c_";
        this.zua = true;
        this.Bua = true;
        this.Cua = new byte[] { 0, 0, 0, 0, 0, 0, 115, -53, 115, -50, 72, 44, 42, 41, 86, 8, 40, -54, 79, 75, 45, 46, -50, -52, -49, 75, -52, 1, 0, -75, 60, 100, -66, 21, 0, 0, 0 };
        this.Eua = new kj();
        this.Fua = new String[] { "Price", "BullishCandle", "BearishCandle", "BgPrice", "Volume", "VOLEMA", "OpenInterest", "ParabolicSAR", "DMA", "BOL", "EMA1", "EMA2", "EMA3", "SMA1", "SMA2", "SMA3", "TrendLine", "Grid", "Crosshair" };
        this.yua = yua;
        this.Nsa = nsa;
        this.pua = pua;
        this.sua = ("".equals(s4) ? null : s4);
        this.tua = ("".equals(s5) ? null : s5);
        this.uua = ("".equals(s6) ? null : s6);
        this.vua = ("".equals(s7) ? null : s7);
        this.Aua = aua;
        this.oua = new break(nsa.H(), nsa._(), o, s, s2, n, s3, hashtable, s8);
        if (s11 != null) {
            final u u = new u(":");
            u.m(s11);
            if (u.a() == 3) {
                try {
                    this.oua._()._a(Integer.parseInt(u.b(2)) + Integer.parseInt(u.b(1)) * 60 + Integer.parseInt(u.b(0)) * 60 * 60);
                }
                catch (NumberFormatException ex) {}
            }
        }
        this.lb = new default(this.oua);
        this.setLayout(new BorderLayout());
        final a a = new a(new FlowLayout(0, 0, 0), 1, new Insets(1, 2, 1, 2));
        a.setLayout(new BorderLayout());
        a.add(this.info = new Label("", 0), "Center");
        if (b3) {
            a.add(this._ = new ij(4, new Insets(4, 4, 4, 4)), "East");
        }
        final lj lj = new lj(this);
        final a a2 = new a(new FlowLayout(1, 0, 1), 4, new Insets(1, 2, 1, 2));
        if (nsa != null && s3 != null) {
            if (s4 != null) {
                (this.zta = new t("/images/" + yua + "loadconfig.gif", "btnLoadConfig")).addActionListener(lj);
                a2.add(this.zta);
            }
            if (s5 != null) {
                (this.yta = new t("/images/" + yua + "saveconfig.gif", "btnSaveConfig")).addActionListener(lj);
                a2.add(this.yta);
            }
        }
        if (b4) {
            (this.Ata = new t("/images/" + yua + "maximizechart.gif", "btnMaximizeChart", false)).addActionListener(lj);
            a2.add(this.Ata);
        }
        this.Dta = new t("/images/" + yua + "autoloadstudies.gif", "btnAutoLoadStudies", false);
        if (nsa != null && s3 != null) {
            if (s7 != null) {
                (this.Bta = new t("/images/" + yua + "savestudies.gif", "btnSaveStudies")).addActionListener(lj);
                a2.add(this.Bta);
            }
            if (s6 != null) {
                (this.Cta = new t("/images/" + yua + "loadstudies.gif", "btnLoadStudies")).addActionListener(lj);
                a2.add(this.Cta);
                a2.add(this.Dta);
            }
        }
        String s14;
        final String s13 = s14 = System.getProperty("java.version");
        if (s13.length() >= 3) {
            s14 = s13.substring(0, 3);
        }
        if ("1.4".compareTo(s14) <= 0) {
            (this.Fta = new t("/images/" + yua + "printchart.gif", "btnPrintChart")).addActionListener(new mj(this));
            a2.add(this.Fta);
        }
        a2.add(new nj(4));
        a.add(a2, "West");
        this.add(a, "South");
        this.Gta = new a(new FlowLayout(0, 0, 3));
        this.Hta = new a(new FlowLayout(0, 0, 3));
        int n3 = 0;
        if (s9 == null || s9.length() > 0) {
            ++n3;
        }
        if (s10 == null || s10.length() > 0) {
            ++n3;
        }
        this.Yta = new Panel(new GridLayout(n3, 1));
        if (s9 == null || s9.length() > 0) {
            this.Yta.add(this.Gta);
        }
        if (s10 == null || s10.length() > 0) {
            this.Yta.add(this.Hta);
        }
        if (n3 > 0) {
            this.add(this.Yta, "North");
        }
        this.Jta = new Choice();
        if (s12 != null && s12.length() > 0) {
            final u u2 = new u(",");
            u2.m(s12);
            for (int i = 0; i < u2.a(); ++i) {
                String s15 = null;
                String b5 = null;
                final u u3 = new u("/");
                if (u2.b(i) != null) {
                    u3.m(u2.b(i));
                }
                if (u3.a() == 1) {
                    b5 = (s15 = u3.b(0));
                }
                else if (u3.a() > 1) {
                    s15 = u3.b(0);
                    b5 = u3.b(1);
                }
                if (s15 != null && b5 != null && b5.length() > 0) {
                    this.Ita.put(b5, s15);
                    this.Jta.add(b5);
                }
            }
        }
        if (!"".equals(this.Ita.get(this.oua.a().b("strFavorites")))) {
            this.Ita.put(this.oua.a().b("strFavorites"), "");
            this.Jta.add(this.oua.a().b("strFavorites"));
        }
        this.Jta.select(0);
        (this.Kta = new List()).setMultipleMode(false);
        this.Kta.addItemListener(this.Wta = new jj(this));
        this.Kta.addKeyListener(this.Wta);
        this.Vta = new PopupMenu();
        (this.Nta = new MenuItem(this.oua.a().b("menuClearSymbolSelection"))).addActionListener(this.Wta);
        this.Vta.add(this.Nta);
        this.Vta.addSeparator();
        final oj oj = new oj(this);
        (this.Ota = new MenuItem(this.oua.a().b("menuAddToFavorites"))).addActionListener(oj);
        this.Vta.add(this.Ota);
        (this.Pta = new MenuItem(this.oua.a().b("menuRemoveFromFavorites"))).addActionListener(oj);
        this.Vta.add(this.Pta);
        (this.Qta = new MenuItem(this.oua.a().b("menuClearFavorites"))).addActionListener(oj);
        this.Vta.add(this.Qta);
        this.Kta.add(this.Vta);
        this.Kta.addMouseListener(this.Wta);
        final pj pj = new pj(this);
        (this.Lta = new c(n2, false)).setText(this.oua.a().b("strEnterSymbolHere"));
        this.Lta.addKeyListener(pj);
        this.Lta.addFocusListener(pj);
        (this.Mta = new c(n2, false)).addKeyListener(pj);
        this.Mta.addFocusListener(pj);
        (this.Xta = new a(new BorderLayout(1, 1), 1, new Insets(2, 2, 2, 2))).add(this.Kta, "Center");
        this.Xta.add(this.Mta, "South");
        Panel panel;
        if (nsa != null && nsa._a()) {
            if (this.Jta.getItemCount() > 1) {
                panel = new Panel(new GridLayout(3, 1, 1, 2));
            }
            else {
                panel = new Panel(new GridLayout(2, 1, 1, 2));
            }
        }
        else if (this.Jta.getItemCount() > 1) {
            panel = new Panel(new GridLayout(2, 1, 1, 2));
        }
        else {
            panel = new Panel(new GridLayout(1, 1, 1, 2));
        }
        panel.add(this.Lta);
        if (nsa != null && nsa._a()) {
            final a a3 = new a(new GridLayout(1, 2, 0, 0), 2, new Insets(1, 1, 1, 1));
            this.iua = new Button(o.b("btnBuy"));
            this.jua = new Button(o.b("btnSell"));
            a3.add(this.iua);
            a3.add(this.jua);
            panel.add(a3);
        }
        if (this.Jta.getItemCount() > 1) {
            panel.add(this.Jta);
        }
        this.Xta.add(panel, "North");
        this.add(this.Xta, "West");
        this.cua = cua;
        this.Xta.setVisible(this.cua);
        this.add(this.rua = new a(new BorderLayout(), 3, new Insets(3, 3, 3, 3)), "Center");
        this.rua.add(this.lb._(), "Center");
        this.oua._().b(this.lb._());
        this.a(b3, b, b2, s9, s10);
        this.Lb();
        this.b(this, SystemColor.control);
        this.dta = gj.a(this, this.lb, this.oua);
    }
    
    private void Lb() {
        this.Eua.put(this.hta, this.oua.a().b("btnLogCor_Info"));
        this.Eua.put(this.ita, this.oua.a().b("btnLogUncor_Info"));
        this.Eua.put(this.jta, this.oua.a().b("btnLinCor_Info"));
        this.Eua.put(this.kta, this.oua.a().b("btnLinUncor_Info"));
        this.Eua.put(this.lta, this.oua.a().b("btnPercentCor_Info"));
        this.Eua.put(this.ota, this.oua.a().b("btnSetTimeframe_Info"));
        this.Eua.put(this.nta, this.oua.a().b("btnChartProperties_Info"));
        this.Eua.put(this.mta, this.oua.a().b("btnStockListSwitch_Info"));
        this.Eua.put(this.pta, this.oua.a().b("btnVolumeSwitch_Info"));
        this.Eua.put(this.qta, this.oua.a().b("btnUseBrowserCache_Info"));
        this.Eua.put(this.rta, this.oua.a().b("btnAutoRefreshSwitch_Info"));
        this.Eua.put(this.sta, this.oua.a().b("btnAutoRefreshBeep_Info"));
        this.Eua.put(this.tta, this.oua.a().b("selectQuotType_Info"));
        this.Eua.put(this.uta, this.oua.a().b("btnLine_Info"));
        this.Eua.put(this.vta, this.oua.a().b("btnOhlc_Info"));
        this.Eua.put(this.wta, this.oua.a().b("btnCandle_Info"));
        this.Eua.put(this.yta, this.oua.a().b("btnSaveConfig_Info"));
        this.Eua.put(this.zta, this.oua.a().b("btnLoadConfig_Info"));
        this.Eua.put(this.Bta, this.oua.a().b("btnSaveStudies_Info"));
        this.Eua.put(this.Cta, this.oua.a().b("btnLoadStudies_Info"));
        this.Eua.put(this.Dta, this.oua.a().b("btnAutoLoadStudies_Info"));
        this.Eua.put(this.Eta, this.oua.a().b("btnCopyChart_Info"));
        this.Eua.put(this.Fta, this.oua.a().b("btnPrintChart_Info"));
        this.Eua.put(this.Ata, this.oua.a().b("btnMaximizeChart_Info"));
        this.Eua.put(this.Kta, this.oua.a().b("selectStock_Info"));
        this.Eua.put(this.Jta, this.oua.a().b("selectCategory_Info"));
        this.Eua.put(this.Lta, this.oua.a().b("enterPrimarySymbol_Info"));
        this.Eua.put(this.Mta, this.oua.a().b("enterSecondarySymbol_Info"));
        this.Eua.put(this.Na, this.oua.a().b("btnMaximize_Info"));
        this.Eua.put(this.dua, this.oua.a().b("btnRefresh_Info"));
        this.Eua.put(this.eua, this.oua.a().b("btnZoomOut_Info"));
        this.Eua.put(this.fua, this.oua.a().b("btnZoomIn_Info"));
        this.Eua.put(this.gua, this.oua.a().b("btnMoveChart_Info"));
        this.Eua.put(this.hua, this.oua.a().b("btnMoveChart_Info"));
        this.Eua.put(this.Za, this.oua.a().b("selectRange_Info"));
        this.Eua.put(this.iua, this.oua.a().b("btnBuy_Info"));
        this.Eua.put(this.jua, this.oua.a().b("btnSell_Info"));
        for (int i = 0; i < this.Ua.length; ++i) {
            this.Eua.put(this.Ua[i], this.oua.a().b("txtIndicatorParam_Info"));
        }
        for (int j = 0; j < this.kua.length; ++j) {
            if (this.kua[j] != null) {
                this.Eua.put(this.kua[j], this.oua.a().b("txtIndicatorParam_Info"));
            }
        }
        for (int k = 0; k < this.xta.length; ++k) {
            this.Eua.put(this.xta[k], this.oua.a().b("btnIndicatorOnOff_Info"));
        }
        this.Eua.put(this.mua, this.oua.a().b("selectIndicator_Info"));
        this.Eua.put(this.nua, this.oua.a().b("selectIndicator_Info"));
        this.Eua.put(this.lb._(), this.oua.a().b("strPopupMenu_Info"));
        final qj qj = new qj(this, null);
        final Enumeration<Component> keys = (Enumeration<Component>)this.Eua.keys();
        while (keys.hasMoreElements()) {
            final Component nextElement = keys.nextElement();
            if (nextElement instanceof Component) {
                nextElement.addMouseListener(qj);
            }
        }
    }
    
    private synchronized Hashtable a() {
        final Hashtable<String, String> hashtable = new Hashtable<String, String>();
        try {
            if (this.pua) {
                this.oua._().J(this.oua.P());
            }
            hashtable.put("UseBrowserCache", String.valueOf(this.qta.getState()));
            if (this.rta.isVisible()) {
                hashtable.put("AutoRefreshActive", String.valueOf(this.rta.getState()));
            }
            if (this.sta.isVisible()) {
                hashtable.put("AutoRefreshBeep", String.valueOf(this.sta.getState()));
            }
            if (this.uta.getState()) {
                hashtable.put("ChartType", String.valueOf(0));
            }
            else if (this.vta.getState()) {
                hashtable.put("ChartType", String.valueOf(1));
            }
            else if (this.wta.getState()) {
                hashtable.put("ChartType", String.valueOf(2));
            }
            if (this.hta.getState()) {
                hashtable.put("Scale", String.valueOf(1));
            }
            else if (this.ita.getState()) {
                hashtable.put("Scale", String.valueOf(2));
            }
            else if (this.jta.getState()) {
                hashtable.put("Scale", String.valueOf(3));
            }
            else if (this.kta.getState()) {
                hashtable.put("Scale", String.valueOf(4));
            }
            else if (this.lta.getState()) {
                hashtable.put("Scale", String.valueOf(5));
            }
            int b = const.b(this.oua.a(), this.tta.getSelectedItem());
            if (b == -1) {
                b = 100001;
            }
            hashtable.put("DataPeriod", String.valueOf(b));
            if (this.pta != null) {
                hashtable.put("VolumeVisible", String.valueOf(this.pta.getState()));
            }
            hashtable.put("GridVisible", String.valueOf(this.lb._().T()));
            hashtable.put("TitleVisible", String.valueOf(this.lb._().W()));
            hashtable.put("LegendVisible", String.valueOf(this.lb._().X()));
            hashtable.put("AnnotationsVisible", String.valueOf(this.lb._().Y()));
            hashtable.put("CrosshairVisible", String.valueOf(this.lb._().U()));
            hashtable.put("BuySellSignalsVisible", String.valueOf(this.lb._().V()));
            hashtable.put("AutoLoadStudies", String.valueOf(this._()));
            final String[] _ = this.oua._();
            for (int i = 0; i < _.length; ++i) {
                final implements a = this.oua.a(_[i]);
                if (a != null && !(a instanceof di)) {
                    hashtable.put(a.toString(), a.k());
                }
            }
            final String[] e = this.oua.e();
            for (int j = 0; j < e.length; ++j) {
                final implements b2 = this.oua.b(e[j]);
                if (b2 != null) {
                    hashtable.put(b2.toString(), b2.k());
                }
            }
            final implements a2 = this.oua.a();
            if (a2 != null) {
                hashtable.put("Indicator1", a2.toString());
            }
            final implements b3 = this.oua.b();
            if (b3 != null) {
                hashtable.put("Indicator2", b3.toString());
            }
            final StringBuffer sb = new StringBuffer();
            final implements[] a3 = this.oua.a();
            if (a3 != null) {
                for (int k = 0; k < a3.length; ++k) {
                    sb.append(a3[k].toString());
                    if (k < a3.length - 1) {
                        sb.append(",");
                    }
                }
            }
            hashtable.put("PriceIndicators", sb.toString());
            hashtable.put("Timeframe", String.valueOf(this.oua._().g()));
            hashtable.put("IntradayTimeframe", String.valueOf(this.oua._().b()));
            final String[] array = { "PivotPoints", "R1", "S1", "R2", "S2" };
            final String[] array2 = new String[this.Fua.length + array.length + 1];
            for (int l = 0; l < this.Fua.length; ++l) {
                array2[l] = this.Fua[l];
            }
            for (int n = 0; n < array.length; ++n) {
                array2[this.Fua.length + n] = array[n];
            }
            array2[array2.length - 1] = "Indicator";
            for (int n2 = 0; n2 < array2.length; ++n2) {
                final String s = array2[n2];
                String string = "";
                String string2 = "";
                final Color[] a4 = this.oua.a(s);
                if (a4 != null) {
                    for (int n3 = 0; n3 < a4.length; ++n3) {
                        string = string + "0x" + Integer.toHexString(a4[n3].getRGB() & 0xFFFFFF).toUpperCase() + ",";
                    }
                    hashtable.put(s + "Color", string);
                }
                final int[] _2 = this.oua._(s);
                if (_2 != null) {
                    for (int n4 = 0; n4 < _2.length; ++n4) {
                        string2 = string2 + String.valueOf(_2[n4]) + ",";
                    }
                    hashtable.put(s + "LineWidth", string2);
                }
            }
            final String s2 = "ExtraPrice";
            String string3 = "";
            String string4 = "";
            final Color[] a5 = this.oua.a(s2);
            if (a5 != null) {
                for (int n5 = 0; n5 < a5.length; ++n5) {
                    string3 = string3 + "0x" + Integer.toHexString(a5[n5].getRGB() & 0xFFFFFF).toUpperCase() + ",";
                }
                hashtable.put(s2 + "Color", string3);
            }
            final int[] _3 = this.oua._(s2);
            if (_3 != null) {
                for (int n6 = 0; n6 < _3.length; ++n6) {
                    string4 = string4 + String.valueOf(_3[n6]) + ",";
                }
                hashtable.put(s2 + "LineWidth", string4);
            }
            final String text = this.Lta.getText();
            if (text.equals(this.oua.a().b("strEnterSymbolHere")) || text.length() == 0) {
                hashtable.put("ActiveStock", "");
            }
            else {
                hashtable.put("ActiveStock", this.oua._()._(text) + "/" + text);
            }
            final String text2 = this.Mta.getText();
            if (text2.equals(this.oua.a().b("strEnterSymbolHere")) || text2.length() == 0) {
                hashtable.put("ActiveBgStock", "");
            }
            else {
                hashtable.put("ActiveBgStock", this.oua._()._(text2) + "/" + text2);
            }
            if (this.Jta.getSelectedItem() != null) {
                hashtable.put("ActiveCategory", this.Jta.getSelectedItem());
            }
            hashtable.put("Favorites", this.Q());
            hashtable.put("DisplayRange", String.valueOf(this.lb.k()));
        }
        finally {}
        return hashtable;
    }
    
    private synchronized sj b() {
        final StringBuffer sb = new StringBuffer(128);
        final implements[] a = this.oua.a();
        if (a != null) {
            for (int i = 0; i < a.length; ++i) {
                sb.append(a[i].toString());
                sb.append("(");
                sb.append(a[i].k());
                sb.append(")");
                if (i < a.length - 1) {
                    sb.append(";");
                }
            }
        }
        final String string = sb.toString();
        final StringBuffer sb2 = new StringBuffer(32);
        final implements a2 = this.oua.a();
        if (a2 != null) {
            sb2.append(a2.toString());
            sb2.append("(");
            sb2.append(a2.k());
            sb2.append(")");
        }
        final String string2 = sb2.toString();
        final StringBuffer sb3 = new StringBuffer(32);
        final implements b = this.oua.b();
        if (b != null) {
            sb3.append(b.toString());
            sb3.append("(");
            sb3.append(b.k());
            sb3.append(")");
        }
        return new sj(this.lb.i(), this.V(), this.lb.k(), this.oua._().y(), this.pta != null && this.pta.getState(), string, string2, sb3.toString(), this.lb._().b());
    }
    
    private synchronized void _(final Hashtable hashtable) {
        boolean b = false;
        final boolean b2 = this.oua._().b();
        boolean b3 = false;
        try {
            if (this.pua) {
                final String n = this.oua._().N();
                if (n != null) {
                    this.oua.K(n);
                    b3 = true;
                }
            }
            if (hashtable.get("UseBrowserCache") != null) {
                this.setUseCache(Boolean.valueOf(hashtable.get("UseBrowserCache")));
            }
            if (this.rta.isVisible() && hashtable.get("AutoRefreshActive") != null) {
                this.rta.setState(Boolean.valueOf(hashtable.get("AutoRefreshActive")));
            }
            if (this.sta.isVisible() && hashtable.get("AutoRefreshBeep") != null) {
                this.sta.setState(Boolean.valueOf(hashtable.get("AutoRefreshBeep")));
                this.dta.a(this.sta.getState());
            }
            try {
                this.c(Integer.parseInt(hashtable.get("ChartType")));
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            try {
                this.da(Integer.parseInt(hashtable.get("Scale")));
            }
            catch (Exception ex2) {
                ex2.printStackTrace();
            }
            try {
                this.ea(Integer.parseInt(hashtable.get("DataPeriod")));
            }
            catch (Exception ex3) {
                ex3.printStackTrace();
            }
            if (b2 != this.oua._().b()) {
                this.lb.b();
                b = true;
            }
            if (this.pta != null && hashtable.get("VolumeVisible") != null) {
                this.pta.setState(Boolean.valueOf(hashtable.get("VolumeVisible")));
                int n2 = 0;
                if (this.oua.a() != null) {
                    ++n2;
                }
                if (this.oua.b() != null) {
                    ++n2;
                }
                if (this.pta.getState()) {
                    this.lb.d(1);
                    this._(2 + n2, this.V());
                }
                else {
                    this.lb.d(2);
                    this._(1 + n2, this.V());
                }
                this.lb.x();
                for (int i = 1; i < this.lb._().N(); ++i) {
                    this.lb._().M(i);
                }
                this.lb.y();
            }
            if (hashtable.get("GridVisible") != null) {
                this.lb._().H(Boolean.valueOf(hashtable.get("GridVisible")));
            }
            if (hashtable.get("TitleVisible") != null) {
                this.lb._().E(Boolean.valueOf(hashtable.get("TitleVisible")));
            }
            if (hashtable.get("LegendVisible") != null) {
                this.lb._().F(Boolean.valueOf(hashtable.get("LegendVisible")));
            }
            if (hashtable.get("AnnotationsVisible") != null) {
                this.lb._().G(Boolean.valueOf(hashtable.get("AnnotationsVisible")));
            }
            if (hashtable.get("CrosshairVisible") != null) {
                this.lb._().D(Boolean.valueOf(hashtable.get("CrosshairVisible")));
            }
            if (hashtable.get("BuySellSignalsVisible") != null) {
                this.lb._().I(Boolean.valueOf(hashtable.get("BuySellSignalsVisible")));
            }
            if (hashtable.get("AutoLoadStudies") != null) {
                this.S(Boolean.valueOf(hashtable.get("AutoLoadStudies")));
            }
            final String[] _ = this.oua._();
            for (int j = 0; j < _.length; ++j) {
                final implements a = this.oua.a(_[j]);
                if (a != null && hashtable.get(a.toString()) != null) {
                    a.k(hashtable.get(a.toString()));
                }
            }
            final String[] e = this.oua.e();
            for (int k = 0; k < e.length; ++k) {
                final implements b4 = this.oua.b(e[k]);
                if (b4 != null && hashtable.get(b4.toString()) != null) {
                    b4.k(hashtable.get(b4.toString()));
                }
            }
            this.n(hashtable.get("Indicator1"));
            this.c(hashtable.get("Indicator2"));
            if (hashtable.get("PriceIndicators") != null) {
                this.O(hashtable.get("PriceIndicators").replace(',', ';'));
            }
            else {
                this.O(null);
            }
            try {
                this.oua._().b(Integer.parseInt(hashtable.get("Timeframe")));
            }
            catch (Exception ex4) {
                ex4.printStackTrace();
            }
            try {
                this.oua._().a(Integer.parseInt(hashtable.get("IntradayTimeframe")));
            }
            catch (Exception ex5) {
                ex5.printStackTrace();
            }
            final String[] array = { "PivotPoints", "R1", "S1", "R2", "S2" };
            final String[] array2 = new String[this.Fua.length + array.length + 1];
            for (int l = 0; l < this.Fua.length; ++l) {
                array2[l] = this.Fua[l];
            }
            for (int n3 = 0; n3 < array.length; ++n3) {
                array2[this.Fua.length + n3] = array[n3];
            }
            array2[array2.length - 1] = "Indicator";
            for (int n4 = 0; n4 < array2.length; ++n4) {
                final String s = array2[n4];
                final u u = new u(",");
                final String s2 = hashtable.get(s + "Color");
                if (s2 != null) {
                    u.m(s2);
                    final Color[] array3 = new Color[u.a()];
                    try {
                        for (int n5 = 0; n5 < u.a(); ++n5) {
                            array3[n5] = Color.decode(u.b(n5));
                        }
                        this.oua._(s, array3);
                        if (s.equals("Indicator")) {
                            final String[] _2 = this.oua._();
                            for (int n6 = 0; n6 < _2.length; ++n6) {
                                this.oua._(_2[n6], array3);
                            }
                        }
                    }
                    catch (NumberFormatException ex6) {
                        ex6.printStackTrace();
                    }
                }
                final String s3 = hashtable.get(s + "LineWidth");
                if (s3 != null) {
                    u.m(s3);
                    final int[] array4 = new int[u.a()];
                    try {
                        for (int n7 = 0; n7 < u.a(); ++n7) {
                            array4[n7] = Integer.parseInt(u.b(n7));
                        }
                        this.oua.a(s, array4);
                        if (s.equals("Indicator")) {
                            final String[] _3 = this.oua._();
                            for (int n8 = 0; n8 < _3.length; ++n8) {
                                this.oua.a(_3[n8], array4);
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
                final u u2 = new u(",");
                u2.m(s5);
                final Color[] array5 = new Color[u2.a()];
                try {
                    for (int n9 = 0; n9 < u2.a(); ++n9) {
                        array5[n9] = Color.decode(u2.b(n9));
                    }
                    this.oua._(s4, array5);
                }
                catch (NumberFormatException ex8) {
                    ex8.printStackTrace();
                }
            }
            final String s6 = "ExtraPrice";
            final String s7 = hashtable.get(s6 + "LineWidth");
            if (s7 != null) {
                final u u3 = new u(",");
                u3.m(s7);
                final int[] array6 = new int[u3.a()];
                try {
                    for (int n10 = 0; n10 < u3.a(); ++n10) {
                        array6[n10] = Integer.parseInt(u3.b(n10));
                    }
                    this.oua.a(s6, array6);
                }
                catch (NumberFormatException ex9) {
                    ex9.printStackTrace();
                }
            }
            final Wh wh = new Wh(this, hashtable.get("ActiveStock"));
            if ((wh.pja && !wh.name.equals(this.eta)) || !wh.name.equals(this.oua._()._())) {
                this.lb.b();
            }
            this.Lta.setText(wh.pja ? wh.name : "");
            this.eta = (wh.pja ? wh.name : null);
            this.oua._().a(wh);
            final Wh wh2 = new Wh(this, hashtable.get("ActiveBgStock"));
            this.Mta.setText(wh2.pja ? wh2.name : "");
            this.gta = (wh2.pja ? wh2.name : null);
            this.oua._().a(wh2);
            this.oua._()._(this.gta);
            final String s8 = hashtable.get("ActiveCategory");
            if (this.Ita.containsKey(s8)) {
                this.Jta.select(s8);
            }
            final String s9 = hashtable.get("Favorites");
            String[] h = null;
            if (s9 != null && s9.length() > 0) {
                final u u4 = new u(",");
                u4.m(s9);
                h = u4.h();
                this._ua.clear();
                this.a((String[])null, "");
                final String[] zta = this.Zta;
                this.Zta = new String[((zta == null) ? 0 : zta.length) + ((h == null) ? 0 : h.length)];
                int n11 = 0;
                for (int n12 = 0; n12 < zta.length && n11 < this.Zta.length; this.Zta[n11++] = zta[n12], ++n12) {}
                for (int n13 = 0; n13 < h.length && n11 < this.Zta.length; this.Zta[n11++] = h[n13], ++n13) {}
            }
            this.fta.removeAll();
            this.oua._().a(this.fta.a());
            this.oua._().a(this.eta);
            try {
                this.fa(Integer.parseInt(hashtable.get("DisplayRange")));
            }
            catch (NumberFormatException ex10) {
                ex10.printStackTrace();
            }
            this.Kta.setVisible(false);
            for (int n14 = 0; n14 < this.Kta.getItemCount(); ++n14) {
                this.Kta.deselect(n14);
            }
            if ("".equals(this.Ita.get(this.Jta.getSelectedItem()))) {
                if (this.zua) {
                    this.Kta.removeAll();
                    this.b(h, "");
                }
            }
            else {
                this.Kta.removeAll();
                this.b(this.Zta, this.Ita.get(this.Jta.getSelectedItem()));
            }
            if (this.eta != null && this.eta.length() > 0) {
                this.P(this.eta);
            }
            this.Kta.setVisible(true);
        }
        finally {
            this.Mb();
            if (b3) {
                this.Nb();
            }
            this.lb.m();
            if (b) {
                this.b(this.oua._()._() + ": " + this.oua.a().b("msgLoadingData"));
            }
            boolean aa = false;
            if (this._()) {
                aa = this.aa();
            }
            if (!aa) {
                this.lb._();
            }
            this.lb.repaint();
            this.b(this.oua._().getMessage());
            this.T(this.V() != 5);
            if (b) {
                this.dta.a();
            }
        }
    }
    
    private void Mb() {
        if (this.Nsa != null) {
            synchronized (this.oua._()) {
                this.Nsa._(this.oua._()._(), this.oua._().g(), this.oua._().c());
            }
        }
    }
    
    private synchronized void a(final sj sj) {
        try {
            this.lb.b();
            this.c(sj.i());
            this.da(sj.j());
            this.n(sj.n());
            this.c(sj.c());
            this.O(sj.d());
            this.fa(sj.k());
            if (this.pta != null) {
                this.pta.setState(sj.k());
                int n = 0;
                if (this.oua.a() != null) {
                    ++n;
                }
                if (this.oua.b() != null) {
                    ++n;
                }
                if (this.pta.getState()) {
                    this.lb.d(1);
                    this._(2 + n, this.V());
                }
                else {
                    this.lb.d(2);
                    this._(1 + n, this.V());
                }
            }
            this.lb._()._(sj.b());
        }
        finally {
            this.lb.z(true);
            this.lb._();
            this.lb.z(false);
            this.lb.repaint();
        }
    }
    
    public synchronized void Kb() {
        new rj(this).start();
    }
    
    public void Q(final String s) {
        if (s == null || s.length() == 0) {
            return;
        }
        final u u = new u(",");
        u.m(s);
        final Vector vector = new Vector<String>(u.a());
        for (int i = 0; i < u.a(); ++i) {
            final int l = const.l(u.b(i));
            if (l != -1) {
                vector.addElement(const.e(l));
            }
        }
        if (vector.size() == 0) {
            vector.addElement(const.e(100001));
        }
        this.tta.removeAll();
        for (int j = 0; j < vector.size(); ++j) {
            this.tta.add(this.oua.a().b(vector.elementAt(j)));
        }
        this.tta.select(0);
    }
    
    public void Ob() {
        String a = null;
        int b = -1;
        int k = -1;
        final Font font = new Font("SansSerif", 0, 12);
        if (this.oua != null) {
            a = this.oua.toa.a("StatusBarFontName");
            b = this.oua.toa.b("StatusBarFontSize");
            k = this.oua.toa.k("StatusBarFontStyle");
        }
        this.info.setFont(this._(font, a, b, k));
    }
    
    public Font a() {
        String a = null;
        int b = -1;
        int k = -1;
        final Font font = new Font("SansSerif", 0, 11);
        if (this.oua != null) {
            a = this.oua.toa.a("UIFontName");
            b = this.oua.toa.b("UIFontSize");
            k = this.oua.toa.k("UIFontStyle");
        }
        return this._(font, a, b, k);
    }
    
    public void _(final Component component, final Font font) {
        component.setFont(font);
        if (component instanceof Container) {
            final Component[] components = ((Container)component).getComponents();
            for (int i = 0; i < components.length; ++i) {
                this._(components[i], font);
            }
        }
    }
    
    public void _(final String s, final String s2, String s3) {
        final Wh wh = new Wh(this, s);
        if (!wh.pja) {
            this.aua = null;
        }
        else {
            this.aua = wh.name;
            this.oua._().a(wh);
        }
        final Wh wh2 = new Wh(this, s2);
        if (!wh2.pja) {
            this.bua = null;
        }
        else {
            this.bua = wh2.name;
            this.oua._().a(wh2);
        }
        if (s3 == null) {
            s3 = "";
        }
        if (this.aua != null) {
            s3 = s3 + "," + s;
        }
        if (this.bua != null) {
            s3 = s3 + "," + s2;
        }
        final u u = new u(",");
        u.m(s3);
        this.Zta = u.h();
        new Thread(this).start();
    }
    
    public void run() {
        try {
            Thread.sleep(50L);
            while (!this.isShowing()) {
                Thread.sleep(50L);
            }
            this.b(false);
            this.b(this.oua.a().b("msgStartingProgram"));
            this.oua.b().v();
            this.Xta.setVisible(false);
            this.Kta.removeAll();
            this.b(this.Zta, null);
            this.lb.b();
            this.b(this.oua._().getMessage());
            this.Xta.setVisible(this.cua);
            if (this.aua != null && this.aua.length() > 0) {
                this.P(this.aua);
            }
            if (this.bua != null && this.bua.length() > 0) {
                this.R(this.bua);
            }
            if (this.qua) {
                this.doLoadConfigAction();
            }
        }
        catch (InterruptedException ex) {}
        finally {
            this.invalidate();
            this.validate();
            this.oua._().a(this.eta);
            this.oua._()._(this.gta);
            if (this.eta != null) {
                this.b(this.oua.a().b("msgLoadingData"));
            }
            boolean aa = false;
            if (this._()) {
                aa = this.aa();
            }
            if (!aa) {
                this.lb._();
            }
            if (this.eta != null) {
                this.b(this.oua._().getMessage());
            }
            this.lb.repaint();
            this.dta.a();
            this.b(true);
        }
        if (this.Nsa != null) {
            this.Nsa.Pb();
        }
        this.Mb();
    }
    
    public void setUseCache(final boolean b) {
        this.oua._().setUseCache(b);
        this.qta.setState(b);
    }
    
    public void ga(final int n) {
        if (this.dta != null) {
            this.dta.f(n);
        }
        this.rta.setState(n > 0);
    }
    
    public void a(final boolean state) {
        if (this.dta != null) {
            this.dta.a(state);
        }
        this.sta.setState(state);
    }
    
    public void ha(final int n) {
        if (this.dta != null) {
            this.dta.g(n);
        }
    }
    
    public void Qb() {
        if (this.dta != null) {
            this.dta.c();
        }
    }
    
    public void Rb() {
        if (this.dta != null) {
            this.dta.start();
        }
    }
    
    public break a() {
        return this.oua;
    }
    
    public void w() {
        this.lb.x();
        this.lb.w();
        this.lb.y();
    }
    
    public synchronized void _(final int n, final int n2) {
        this.lb._(n, n2);
    }
    
    public void da(final int n) {
        this.ia(n);
        this.lb.e(n);
    }
    
    public void ia(final int n) {
        if (n == 1) {
            this.hta.setState(true);
        }
        else if (n == 2) {
            this.ita.setState(true);
        }
        else if (n == 3) {
            this.jta.setState(true);
        }
        else if (n == 4) {
            this.kta.setState(true);
        }
        else if (n == 5) {
            this.lta.setState(true);
        }
        this.Kta.setMultipleMode(n == 5);
        if (this.Bta != null) {
            this.Bta.setEnabled(n != 5);
        }
        this.T(this.V() != 5);
        this.P(this.eta);
        if (n != 5) {
            this.fta.removeAll();
        }
        this.oua._().a(this.fta.a());
        this.Mb();
    }
    
    public void c(final int n) {
        this.lb.c(n);
        if (n == 0) {
            this.uta.setState(true);
        }
        else if (n == 1) {
            this.vta.setState(true);
        }
        else if (n == 2) {
            this.wta.setState(true);
        }
    }
    
    public void B(final String s) {
        this.lb.B(s);
    }
    
    public void _(final Component component, final Color background) {
        component.setBackground(background);
        if (component instanceof t) {
            final Color[] a = this.oua.a("ImageButtonBg");
            if (a != null) {
                component.setBackground(a[0]);
            }
        }
        if (component instanceof Container) {
            final Component[] components = ((Container)component).getComponents();
            for (int i = 0; i < components.length; ++i) {
                if (components[i] instanceof t || components[i] instanceof TextField || components[i] instanceof Panel || components[i] instanceof List || components[i] instanceof Button || components[i] instanceof Choice) {
                    this._(components[i], background);
                }
            }
        }
    }
    
    public void m(final Component component, final Color background) {
        if (component instanceof t && background != null) {
            component.setBackground(background);
        }
        if (component instanceof Container) {
            final Component[] components = ((Container)component).getComponents();
            for (int i = 0; i < components.length; ++i) {
                if (components[i] instanceof t || components[i] instanceof TextField || components[i] instanceof Panel || components[i] instanceof List || components[i] instanceof Button || components[i] instanceof Choice) {
                    this.m(components[i], background);
                }
            }
        }
    }
    
    public void n(final Component component, final Color color) {
        if (component instanceof t && color != null) {
            ((t)component).f(color);
        }
        if (component instanceof Container) {
            final Component[] components = ((Container)component).getComponents();
            for (int i = 0; i < components.length; ++i) {
                if (components[i] instanceof t || components[i] instanceof TextField || components[i] instanceof Panel || components[i] instanceof List || components[i] instanceof Button || components[i] instanceof Choice) {
                    this.n(components[i], color);
                }
            }
        }
    }
    
    public void a(final Component component, final Color foreground) {
        component.setForeground(foreground);
        if (component instanceof Container) {
            final Component[] components = ((Container)component).getComponents();
            for (int i = 0; i < components.length; ++i) {
                if (components[i] instanceof TextField || components[i] instanceof Panel || components[i] instanceof List || components[i] instanceof Button || components[i] instanceof Choice) {
                    this.a(components[i], foreground);
                }
            }
        }
    }
    
    public void setCursor(final Cursor cursor) {
        final Container parent = this.getParent();
        if (parent != null) {
            parent.setCursor(cursor);
        }
        if (parent != null && !(parent instanceof FnChartsApplet) && this.Nsa != null) {
            this.Nsa.setCursor(cursor);
        }
    }
    
    private int V() {
        if (this.hta.getState()) {
            return 1;
        }
        if (this.ita.getState()) {
            return 2;
        }
        if (this.jta.getState()) {
            return 3;
        }
        if (this.kta.getState()) {
            return 4;
        }
        if (this.hta.getState()) {
            return 1;
        }
        return 5;
    }
    
    public void n(String substring) {
        if (substring == null || substring.equals("")) {
            this.mua.select(this.oua.a().b("cbNoIndicator"));
            int n = 0;
            if (this.oua.a() != null) {
                ++n;
            }
            if (this.oua.b() != null) {
                ++n;
            }
            this._(2 + n, this.V());
            this.lua = null;
            this.oua.n(null);
            return;
        }
        final String[] _ = this.oua._();
        String s = null;
        if (substring.indexOf("(") != -1) {
            s = substring.substring(substring.indexOf("(") + 1);
            if (s.indexOf(")") != -1) {
                s = s.substring(0, s.indexOf(")"));
            }
            substring = substring.substring(0, substring.indexOf("("));
        }
        boolean b = false;
        for (int i = 0; i < _.length; ++i) {
            if (_[i].equals(substring)) {
                this.mua.select(substring);
                this.oua.n(substring);
                if (s != null && this.oua.a() != null) {
                    this.oua.a().k(s);
                }
                this.lua = this.mua;
                this.Sb();
                b = true;
                break;
            }
        }
        if (!b) {
            this.mua.select(this.oua.a().b("cbNoIndicator"));
            this.oua.n(null);
        }
        int n2 = 0;
        if (this.oua.a() != null) {
            ++n2;
        }
        if (this.oua.b() != null) {
            ++n2;
        }
        this._(2 + n2, this.V());
    }
    
    public void c(String substring) {
        if (substring == null || substring.equals("")) {
            this.nua.select(this.oua.a().b("cbNoIndicator"));
            int n = 0;
            if (this.oua.a() != null) {
                ++n;
            }
            if (this.oua.b() != null) {
                ++n;
            }
            this._(2 + n, this.V());
            this.oua.c(null);
            return;
        }
        final String[] _ = this.oua._();
        String s = null;
        if (substring.indexOf("(") != -1) {
            s = substring.substring(substring.indexOf("(") + 1);
            if (s.indexOf(")") != -1) {
                s = s.substring(0, s.indexOf(")"));
            }
            substring = substring.substring(0, substring.indexOf("("));
        }
        boolean b = false;
        for (int i = 0; i < _.length; ++i) {
            if (_[i].equals(substring)) {
                this.nua.select(substring);
                this.oua.c(substring);
                if (s != null && this.oua.b() != null) {
                    this.oua.b().k(s);
                }
                this.lua = this.nua;
                this.Sb();
                b = true;
                break;
            }
        }
        if (!b) {
            this.nua.select(this.oua.a().b("cbNoIndicator"));
            this.oua.c(null);
        }
        int n2 = 0;
        if (this.oua.a() != null) {
            ++n2;
        }
        if (this.oua.b() != null) {
            ++n2;
        }
        this._(2 + n2, this.V());
    }
    
    public void O(final String s) {
        this.oua.Hb();
        final u u = new u(";");
        if (s != null) {
            u.m(s);
        }
        final Hashtable hashtable = new Hashtable<String, String>();
        for (int i = 0; i < u.a(); ++i) {
            String s2 = u.b(i);
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
        final String[] array = new String[this.xta.length];
        if (array == null) {
            return;
        }
        for (int j = 0; j < array.length; ++j) {
            array[j] = this.xta[j].a();
        }
        for (int k = 0; k < array.length; ++k) {
            this.xta[k].setState(hashtable.containsKey(array[k]));
            if (hashtable.containsKey(array[k])) {
                this.oua.d(array[k]);
                if (this.oua.b(array[k]) != null) {
                    this.oua.b(array[k]).k(hashtable.get(array[k]));
                }
            }
            else {
                this.oua.e(array[k]);
            }
        }
        this.Tb();
    }
    
    public void b(final String text) {
        this.info.setText(text);
        this.info.repaint();
    }
    
    private void S(String upperCase) {
        if (upperCase == null || upperCase.length() == 0) {
            return;
        }
        if (this.Aua) {
            upperCase = upperCase.toUpperCase();
        }
        boolean b = false;
        for (int i = 0; i < this.Kta.getItemCount(); ++i) {
            final int compareTo = upperCase.compareTo(this.Kta.getItem(i));
            if (compareTo < 0) {
                this.Kta.add(upperCase, i);
                b = true;
                break;
            }
            if (compareTo == 0) {
                b = true;
                break;
            }
        }
        if (!b) {
            this.Kta.add(upperCase);
        }
    }
    
    private boolean i(String upperCase) {
        if (upperCase == null || upperCase.length() == 0) {
            return false;
        }
        if (this.Aua) {
            upperCase = upperCase.toUpperCase();
        }
        for (int i = 0; i < this.Kta.getItemCount(); ++i) {
            if (upperCase.equals(this.Kta.getItem(i))) {
                return true;
            }
        }
        return false;
    }
    
    private void T(final String s) {
        if (s == null) {
            return;
        }
        for (int i = 0; i < this.Kta.getItemCount(); ++i) {
            if (s.equals(this.Kta.getItem(i))) {
                this.Kta.deselect(i);
                this.Kta.remove(i);
                break;
            }
        }
    }
    
    private void _(final String[] array) {
        for (int i = 0; i < array.length; ++i) {
            this.T(array[i]);
        }
    }
    
    private void b(final String[] array, String s) {
        if (s == null) {
            s = this.Ita.get(this.Jta.getSelectedItem());
        }
        if (array != null) {
            for (int i = 0; i < array.length; ++i) {
                final Wh wh = new Wh(this, array[i]);
                if (wh.pja) {
                    this.oua._().a(wh);
                    if (s == null || s.equals(wh.oja)) {
                        this.S(wh.name);
                    }
                    if ("".equals(wh.oja)) {
                        this._ua.put(wh.name, wh.nja);
                    }
                }
            }
        }
    }
    
    private synchronized void Ub() {
        this.b(false);
        try {
            implements implements1 = null;
            if (this.lua == this.mua) {
                implements1 = this.oua.a();
            }
            else if (this.lua == this.nua) {
                implements1 = this.oua.b();
            }
            if (implements1 != null && implements1 instanceof di) {
                final b b = new b(this.a(), this.oua.a().b("strConfirmIndicatorRemovalTitle"), this.oua.a().b("strConfirmIndicatorRemovalMessage") + ": " + implements1.toString() + " ?", this.oua.a().b("btnYes"), this.oua.a().b("btnNo"));
                b.show();
                if (b.a()) {
                    this.oua.b(implements1);
                    this.U(implements1.toString());
                    this.Sb();
                    this.lb._();
                    this.lb.repaint();
                }
            }
        }
        finally {
            this.b(true);
        }
    }
    
    private synchronized void U(final boolean b) {
        this.b(false);
        implements implements1 = null;
        if (this.lua == this.mua) {
            implements1 = this.oua.a();
        }
        else if (this.lua == this.nua) {
            implements1 = this.oua.b();
        }
        String string = null;
        if (implements1 != null) {
            string = implements1.toString();
        }
        bj _ = null;
        if (b && implements1 != null && implements1 instanceof di) {
            _ = ((di)implements1)._();
        }
        final tj tj = new tj(this.a(), this.oua.a().b("strJSIndicatorDefinition"), _, this.oua.g(), this.oua.a());
        tj.show();
        if (tj.a()) {
            final implements _2 = this.oua._(tj.b());
            String string2 = null;
            if (_2 != null) {
                string2 = _2.toString();
            }
            if (this.lua == this.mua) {
                if (_2 != null) {
                    this.oua.n(_2.toString());
                }
                else {
                    this.oua.n(null);
                }
            }
            else if (this.lua == this.nua) {
                if (_2 != null) {
                    this.oua.c(_2.toString());
                }
                else {
                    this.oua.c(null);
                }
            }
            this.h(string, string2);
            this.Sb();
            new uj(this).start();
        }
        else {
            this.b(true);
        }
    }
    
    public void fa(final int n) {
        this.lb.m(n);
        this.Vb();
    }
    
    private String[] i() {
        final String[] array = new String[this._ua.size()];
        int n = 0;
        String s;
        for (Enumeration keys = this._ua.keys(); keys.hasMoreElements() && n < array.length; array[n++] = this.oua._()._(s) + "/" + s) {
            s = keys.nextElement();
        }
        return array;
    }
    
    private String Q() {
        final String[] i = this.i();
        final StringBuffer sb = new StringBuffer();
        for (int j = 0; j < i.length; ++j) {
            sb.append(i[j]);
            sb.append(',');
        }
        return sb.toString();
    }
    
    private void a(final String[] array, final String s) {
        if (this.Zta == null) {
            return;
        }
        Hashtable hashtable = null;
        if (array != null) {
            hashtable = new Hashtable<String, String>(array.length);
            for (int i = 0; i < array.length; ++i) {
                hashtable.put(array[i], array[i]);
            }
        }
        final Vector vector = new Vector<String>(this.Zta.length);
        for (int j = 0; j < this.Zta.length; ++j) {
            final u u = new u("/");
            String b = "";
            u.m(this.Zta[j]);
            String s2;
            if (u.a() <= 1) {
                s2 = this.Zta[j];
                b = "";
            }
            else {
                s2 = u.b(1);
                if (u.a() > 2) {
                    b = u.b(2);
                }
            }
            if (this.Aua) {
                s2 = s2.toUpperCase();
            }
            if (b.equals(s)) {
                if (hashtable == null) {
                    continue;
                }
                if (hashtable.containsKey(s2)) {
                    continue;
                }
            }
            vector.addElement(this.Zta[j]);
        }
        this.Zta = new String[vector.size()];
        for (int k = 0; k < vector.size(); ++k) {
            this.Zta[k] = vector.elementAt(k);
        }
    }
    
    public synchronized void V(String name) {
        if (name == null) {
            name = "";
        }
        if (!"".equals(name)) {
            final Wh wh = new Wh(this, name);
            if (!wh.pja) {
                name = "";
                return;
            }
            name = wh.name;
            this.oua._().a(wh);
        }
        this.Lta.setText(name);
        this.a(this.Lta);
    }
    
    public synchronized void W(String name) {
        if (name == null) {
            name = "";
        }
        if (!"".equals(name)) {
            final Wh wh = new Wh(this, name);
            if (!wh.pja) {
                name = "";
                return;
            }
            name = wh.name;
            this.oua._().a(wh);
        }
        this.Mta.setText(name);
        this.a(this.Mta);
    }
    
    public synchronized void a(String s, final boolean b) {
        if (s == null) {
            s = "";
        }
        this.b(false);
        final u u = new u(",");
        u.m(s);
        final String[] h = u.h();
        if (b) {
            this.Zta = h;
            this.oua._().Gb();
            this.Kta.removeAll();
            this._ua.clear();
        }
        else {
            final String[] zta = this.Zta;
            this.Zta = new String[((zta == null) ? 0 : zta.length) + ((h == null) ? 0 : h.length)];
            int n = 0;
            for (int n2 = 0; n2 < zta.length && n < this.Zta.length; this.Zta[n++] = zta[n2], ++n2) {}
            for (int n3 = 0; n3 < h.length && n < this.Zta.length; this.Zta[n++] = h[n3], ++n3) {}
        }
        this.b(this.Zta, null);
        this.b(true);
    }
    
    public synchronized void setAvailableSymbolsAndNames(String s) {
        if (s == null) {
            s = "";
        }
        this.b(false);
        final u u = new u(",");
        u.m(s);
        final String[] h = u.h();
        synchronized (this.oua._()) {
            this.oua._().Gb();
            if (h != null) {
                for (int i = 0; i < h.length; ++i) {
                    final Wh wh = new Wh(this, h[i]);
                    if (wh.pja) {
                        this.oua._().b(wh);
                    }
                }
            }
        }
        this.b(true);
    }
    
    public void a(final Object o) {
        synchronized (this) {
            this.b(false);
            new vj(this, o).start();
        }
    }
    
    private Container b() {
        Container container;
        for (container = this.getParent(); container != null && !(container instanceof FnChartsApplet) && !(container instanceof p); container = container.getParent()) {}
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
        if (this.sua == null || this.sua.length() == 0) {
            return;
        }
        try {
            this.b(false);
            this.b(this.oua.a().b("msgLoadingConfiguration"));
            String text = "";
            final q q = new q();
            q.setUseCache(false);
            try {
                q.addObserver(this.lb._());
                if (q.h(this.sua)) {
                    text = q.getText();
                }
            }
            finally {
                q.deleteObserver(this.lb._());
            }
            if (text == null || text.length() == 0) {
                this.b(this.oua.a().b("msgConfigurationLoadError"));
            }
            else {
                final u u = new u(";");
                u.m(text);
                final Hashtable<String, String> hashtable = new Hashtable<String, String>();
                for (int i = 0; i < u.a(); ++i) {
                    final u u2 = new u("=");
                    u2.m(u.b(i));
                    if (u2.a() == 2) {
                        hashtable.put(u2.b(0), u2.b(1));
                    }
                }
                this._(hashtable);
                this.b(this.oua.a().b("msgConfigurationLoaded"));
            }
        }
        finally {
            this.b(true);
        }
    }
    
    private synchronized void doSaveConfigAction() {
        try {
            this.b(false);
            this.b(this.oua.a().b("msgSavingConfiguration"));
            final Hashtable a = this.a();
            final StringBuffer sb = new StringBuffer();
            final Enumeration keys = a.keys();
            while (keys.hasMoreElements()) {
                final String s = keys.nextElement();
                sb.append(s);
                sb.append("=");
                sb.append(a.get(s));
                sb.append(";");
            }
            final q q = new q();
            q.setUseCache(false);
            q.setText(sb.toString());
            if (q._(this.tua, "configuration")) {
                this.b(this.oua.a().b("msgConfigurationSaved"));
            }
            else {
                this.b(this.oua.a().b("msgConfigurationSaveError"));
            }
        }
        finally {
            this.b(true);
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
    
    private synchronized boolean aa() {
        if (this.eta == null) {
            return false;
        }
        boolean b = false;
        String text = "";
        this.b(this.oua.a().b("msgLoadingStudies"));
        final q q = new q();
        q.setUseCache(false);
        try {
            q.addObserver(this.lb._());
            if (q.h(catch.a(catch.a(this._(this.uua, this.oua._()._(this.oua._()._())), "{DataType}", this.oua._().b() ? "INTRA" : "EOD"), "{Periodicity}", const.d(this.oua._().y())))) {
                text = q.getText();
            }
        }
        finally {
            q.deleteObserver(this.lb._());
        }
        if ((this.eta != null && text == null) || text.length() == 0) {
            this.b(this.oua.a().b("msgStudiesLoadError"));
        }
        else {
            try {
                final sj sj = new sj(text);
                if (sj.h() == this.oua._().y()) {
                    this.a(sj);
                    b = true;
                    this.b(this.oua.a().b("msgStudiesLoaded"));
                }
                else {
                    this.b(this.oua.a().b("msgStudiesLoadError"));
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
                this.b(this.oua.a().b("msgStudiesLoadError"));
            }
        }
        return b;
    }
    
    private synchronized void Wb() {
        this.b(this.oua.a().b("msgSavingStudies"));
        final sj b = this.b();
        final q q = new q();
        q.setUseCache(false);
        q.setText(b.toString());
        if (q._(catch.a(catch.a(this._(this.vua, this.oua._()._(this.oua._()._())), "{DataType}", this.oua._().b() ? "INTRA" : "EOD"), "{Periodicity}", const.d(this.oua._().y())), "studies")) {
            this.b(this.oua.a().b("msgStudiesSaved"));
        }
        else {
            this.b(this.oua.a().b("msgStudiesSaveError"));
        }
    }
    
    public void b(final boolean b) {
        if (!b) {
            this.dta._(true);
            this.setEnabled(false);
            this.setCursor(Cursor.getPredefinedCursor(3));
        }
        else {
            this.setCursor(Cursor.getPredefinedCursor(0));
            this.setEnabled(true);
            this.dta._(!this.rta.getState());
        }
    }
    
    public void Xb() {
        if (this.isEnabled()) {
            this.dta._(true);
        }
    }
    
    public void Yb() {
        if (this.isEnabled()) {
            this.dta._(!this.rta.getState());
        }
    }
    
    private Font _(final Font font, String name, int size, int style) {
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
    
    public void ea(final int n) {
        this.oua._()._(n);
        this.tta.select(this.oua.a().b(const.e(n)));
    }
    
    private void P(String upperCase) {
        this.eta = null;
        for (int i = 0; i < this.Kta.getItemCount(); ++i) {
            this.Kta.deselect(i);
        }
        if (upperCase != null && this.Aua) {
            upperCase = upperCase.toUpperCase();
        }
        for (int j = 0; j < this.Kta.getItemCount(); ++j) {
            if (this.Kta.getItem(j).equals(upperCase)) {
                this.Kta.select(j);
                this.eta = this.Kta.getItem(j);
                this.Lta.setText(this.eta);
                break;
            }
        }
        if (this.eta == null) {
            this.eta = upperCase;
            this.Lta.setText(this.eta);
        }
        if (this.Kta.isMultipleMode()) {
            for (int k = 0; k < this.Kta.getItemCount(); ++k) {
                if (this.fta._(this.Kta.getItem(k))) {
                    this.Kta.select(k);
                }
            }
        }
    }
    
    private void R(String upperCase) {
        if (upperCase != null) {
            if (this.Aua) {
                upperCase = upperCase.toUpperCase();
            }
            this.Mta.setText(upperCase);
        }
        else {
            this.Mta.setText("");
        }
        this.gta = upperCase;
    }
    
    private void b(final Component component, final Color background) {
        component.setBackground(background);
        if (component instanceof Container) {
            final Component[] components = ((Container)component).getComponents();
            for (int i = 0; i < components.length; ++i) {
                if (components[i] instanceof TextField || components[i] instanceof Panel || components[i] instanceof List || components[i] instanceof Choice) {
                    this.b(components[i], background);
                }
            }
        }
    }
    
    private Hashtable a(final wj wj) {
        final Hashtable<String, c> hashtable = new Hashtable<String, c>();
        (this.Na = new t("/images/" + this.yua + "minimaxi.gif", "btnMaximize", false)).addActionListener(wj);
        hashtable.put("btnMaximize".toUpperCase(), (c)this.Na);
        (this.qta = new t("/images/" + this.yua + "usecache.gif", "bUseBrowserCache", false)).addActionListener(wj);
        hashtable.put("btnUseBrowserCache".toUpperCase(), (c)this.qta);
        (this.rta = new t("/images/" + this.yua + "autorefresh.gif", "bAutoRefreshActive", true)).addActionListener(wj);
        hashtable.put("btnAutoRefreshSwitch".toUpperCase(), (c)this.rta);
        (this.sta = new t("/images/" + this.yua + "refreshbeep.gif", "bAutoRefreshBeep", true)).addActionListener(wj);
        hashtable.put("btnAutoRefreshBeepSwitch".toUpperCase(), (c)this.sta);
        (this.dua = new t("/images/" + this.yua + "refresh.gif", "btnRefresh")).addActionListener(wj);
        hashtable.put("btnRefresh".toUpperCase(), (c)this.dua);
        (this.gua = new t("/images/" + this.yua + "moveleft.gif", "btnLeft")).addActionListener(wj);
        hashtable.put("btnMoveLeft".toUpperCase(), (c)this.gua);
        (this.fua = new t("/images/" + this.yua + "decreaserange.gif", "btnLess")).addActionListener(wj);
        hashtable.put("btnZoomIn".toUpperCase(), (c)this.fua);
        (this.eua = new t("/images/" + this.yua + "increaserange.gif", "btnMore")).addActionListener(wj);
        hashtable.put("btnZoomOut".toUpperCase(), (c)this.eua);
        (this.hua = new t("/images/" + this.yua + "moveright.gif", "btnRight")).addActionListener(wj);
        hashtable.put("btnMoveRight".toUpperCase(), (c)this.hua);
        (this.Za = new Choice()).add(this.oua.a().b("cbRange_free"));
        this.Za.add(this.oua.a().b("cbRange_1month"));
        this.Za.add(this.oua.a().b("cbRange_3months"));
        this.Za.add(this.oua.a().b("cbRange_6months"));
        this.Za.add(this.oua.a().b("cbRange_1year"));
        this.Za.add(this.oua.a().b("cbRange_2years"));
        this.Za.add(this.oua.a().b("cbRange_3years"));
        this.Za.add(this.oua.a().b("cbRange_5years"));
        this.Za.add(this.oua.a().b("cbRange_10years"));
        this.Za.add(this.oua.a().b("cbRange_all"));
        this.Vb();
        this.Za.addItemListener(new xj(this));
        hashtable.put("selectRange".toUpperCase(), (c)this.Za);
        final dj dj = new dj();
        (this.uta = new t("/images/" + this.yua + "line.gif", "btnLine", dj, true)).addActionListener(wj);
        hashtable.put("btnLine".toUpperCase(), (c)this.uta);
        (this.vta = new t("/images/" + this.yua + "ohlc.gif", "btnOhlc", dj, false)).addActionListener(wj);
        hashtable.put("btnOhlc".toUpperCase(), (c)this.vta);
        (this.wta = new t("/images/" + this.yua + "candle.gif", "btnCandle", dj, false)).addActionListener(wj);
        hashtable.put("btnCandle".toUpperCase(), (c)this.wta);
        final dj dj2 = new dj();
        (this.hta = new t("/images/" + this.yua + "scalelogcor.gif", "btnLogCor", dj2, true)).addActionListener(wj);
        hashtable.put("btnScaleLogCor".toUpperCase(), (c)this.hta);
        (this.ita = new t("/images/" + this.yua + "scaleloguncor.gif", "btnLogUncor", dj2, false)).addActionListener(wj);
        hashtable.put("btnScaleLogUncor".toUpperCase(), (c)this.ita);
        (this.jta = new t("/images/" + this.yua + "scalelincor.gif", "btnLinCor", dj2, false)).addActionListener(wj);
        hashtable.put("btnScaleLinCor".toUpperCase(), (c)this.jta);
        (this.kta = new t("/images/" + this.yua + "scalelinuncor.gif", "btnLinUncor", dj2, false)).addActionListener(wj);
        hashtable.put("btnScaleLinUncor".toUpperCase(), (c)this.kta);
        (this.lta = new t("/images/" + this.yua + "scalepercent.gif", "btnScalePercent", dj2, false)).addActionListener(wj);
        hashtable.put("btnScalePercent".toUpperCase(), (c)this.lta);
        final yj yj = new yj(this);
        this.Jta.addItemListener(yj);
        this.tta = new Choice();
        for (int i = 0; i < const.pqa.length; ++i) {
            this.tta.add(this.oua.a().b(const.pqa[i]));
        }
        this.tta.select(this.oua.a().b(const.e(100001)));
        this.tta.addItemListener(yj);
        hashtable.put("selectDataCompression".toUpperCase(), (c)this.tta);
        (this.ota = new t("/images/" + this.yua + "timeframe.gif", "bSetTimeframe")).addActionListener(wj);
        hashtable.put("btnSetTimeframe".toUpperCase(), (c)this.ota);
        (this.nta = new t("/images/" + this.yua + "chartproperties.gif", "bChartProperties")).addActionListener(wj);
        hashtable.put("btnChartProperties".toUpperCase(), (c)this.nta);
        (this.pta = new t("/images/" + this.yua + "volume.gif", "bVolumeVisible", true)).addActionListener(wj);
        hashtable.put("btnVolumeSwitch".toUpperCase(), (c)this.pta);
        (this.mta = new t("/images/" + this.yua + "stocklist.gif", "bStockListVisible", this.cua)).addActionListener(wj);
        hashtable.put("btnSymbolEntryVisibilitySwitch".toUpperCase(), (c)this.mta);
        final zj zj = new zj(this);
        (this.mua = new Choice()).addItemListener(zj);
        this.mua.addFocusListener(zj);
        this.mua.addMouseListener(zj);
        (this.nua = new Choice()).addItemListener(zj);
        this.nua.addFocusListener(zj);
        this.nua.addMouseListener(zj);
        this.Zb();
        hashtable.put("selectIndicator1".toUpperCase(), (c)this.mua);
        hashtable.put("selectIndicator2".toUpperCase(), (c)this.nua);
        this.Rta = new PopupMenu();
        (this.Sta = new MenuItem(this.oua.a().b("menuDefineIndicator"))).addActionListener(zj);
        this.Rta.add(this.Sta);
        (this.Tta = new MenuItem(this.oua.a().b("menuEditIndicator"))).addActionListener(zj);
        this.Rta.add(this.Tta);
        (this.Uta = new MenuItem(this.oua.a().b("menuDeleteIndicator"))).addActionListener(zj);
        this.Rta.add(this.Uta);
        final Aj aj = new Aj(this);
        this.Ua = new c[3];
        for (int j = 0; j < this.Ua.length; ++j) {
            (this.Ua[j] = new c(3, true)).addKeyListener(aj);
            this.Ua[j].addFocusListener(aj);
            hashtable.put(("txtParam" + (j + 1)).toUpperCase(), this.Ua[j]);
        }
        final String[] e = this.oua.e();
        this.xta = new t[e.length];
        this.kua = new c[e.length];
        final Bj bj = new Bj(this);
        for (int k = 0; k < e.length; ++k) {
            hashtable.put(("btn" + e[k]).toUpperCase(), (c)(this.xta[k] = new t("/images/" + this.yua + e[k].toLowerCase() + ".gif", e[k], false)));
            this.xta[k].addActionListener(bj);
            this.kua[k] = null;
            if (this.oua.b(e[k]).C() > 0) {
                (this.kua[k] = new c(3, true)).addKeyListener(aj);
                this.kua[k].addFocusListener(aj);
                hashtable.put(("txt" + e[k] + "Param").toUpperCase(), this.kua[k]);
            }
        }
        this.Tb();
        return hashtable;
    }
    
    private Component a(final Hashtable hashtable, String upperCase) {
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
                    component = new nj(int1);
                }
            }
        }
        return component;
    }
    
    private void a(final boolean b, final boolean b2, final boolean state, String s, String s2) {
        final wj wj = new wj(this);
        final Hashtable a = this.a(wj);
        if (s == null) {
            s = "btnMaximize,btnUseBrowserCache,btnAutoRefreshSwitch,btnAutoRefreshBeepSwitch,Separator6,btnRefresh,btnMoveLeft,btnZoomIn,btnZoomOut,btnMoveRight,selectRange,Separator6,btnLine,btnOhlc,btnCandle,Separator6,btnScaleLogCor,btnScaleLogUncor,btnScaleLinCor,btnScaleLinUncor,btnScalePercent,Separator6,selectDataCompression,Separator6,btnSetTimeFrame,btnChartProperties,btnVolumeSwitch,btnSymbolEntryVisibilitySwitch";
        }
        if (s2 == null) {
            s2 = "selectIndicator1,selectIndicator2,Separator2,txtParam1,txtParam2,txtParam3,Separator6,btnParabolicSAR,Separator3,btnPivotPoints,Separator3,btnDMA,Separator6,btnBol,Separator2,txtBolParam,Separator6,btnEma1,Separator2,txtEma1Param,Separator6,btnSma1,Separator2,txtSma1Param,Separator6,btnSma2,Separator2,txtSma2Param,Separator6,btnSma3,Separator2,txtSma3Param,Separator6,btnVolEma,Separator2,txtVolEmaParam";
        }
        final u u = new u(",");
        u.m(s);
        for (int i = 0; i < u.a(); ++i) {
            final Component a2 = this.a(a, u.b(i));
            if (a2 != null) {
                if (a2 == this.rta || a2 == this.rta) {
                    if (b) {
                        this.Gta.add(a2);
                    }
                }
                else if (a2 == this.pta) {
                    if (!b2) {
                        this.Gta.add(a2);
                    }
                }
                else if (a2 == this.mua || a2 == this.nua) {
                    this.Gta.add(a2);
                    this.lua = (Choice)a2;
                }
                else {
                    this.Gta.add(a2);
                }
            }
        }
        u.m(s2);
        for (int j = 0; j < u.a(); ++j) {
            final Component a3 = this.a(a, u.b(j));
            if (a3 != null) {
                if (a3 == this.rta || a3 == this.rta) {
                    if (b) {
                        this.Hta.add(a3);
                    }
                }
                else if (a3 == this.pta) {
                    if (!b2) {
                        this.Hta.add(a3);
                    }
                }
                else if (a3 == this.mua || a3 == this.nua) {
                    this.Hta.add(a3);
                    this.lua = (Choice)a3;
                }
                else {
                    this.Hta.add(a3);
                }
            }
        }
        this.Sb();
        if (this.iua != null) {
            this.iua.addActionListener(wj);
        }
        if (this.jua != null) {
            this.jua.addActionListener(wj);
        }
        if (b2) {
            this.lb.d(0);
        }
        else {
            this.pta.setState(state);
            if (this.pta.getState()) {
                this.lb.d(1);
            }
            else {
                this.lb.d(2);
            }
        }
    }
    
    private void Vb() {
        switch (this.lb.k()) {
            case 10: {
                this.Za.select(this.oua.a().b("cbRange_1month"));
                break;
            }
            case 11: {
                this.Za.select(this.oua.a().b("cbRange_3months"));
                break;
            }
            case 12: {
                this.Za.select(this.oua.a().b("cbRange_6months"));
                break;
            }
            case 13: {
                this.Za.select(this.oua.a().b("cbRange_1year"));
                break;
            }
            case 14: {
                this.Za.select(this.oua.a().b("cbRange_2years"));
                break;
            }
            case 15: {
                this.Za.select(this.oua.a().b("cbRange_3years"));
                break;
            }
            case 16: {
                this.Za.select(this.oua.a().b("cbRange_5years"));
                break;
            }
            case 17: {
                this.Za.select(this.oua.a().b("cbRange_10years"));
                break;
            }
            case 0: {
                this.Za.select(this.oua.a().b("cbRange_all"));
                break;
            }
            case -1: {
                this.Za.select(this.oua.a().b("cbRange_free"));
                break;
            }
        }
    }
    
    private void Nb() {
        this.mua.setVisible(false);
        this.mua.select(0);
        this.mua.setEnabled(false);
        while (this.mua.getItemCount() > 1) {
            this.mua.remove(this.mua.getItemCount() - 1);
        }
        this.nua.setVisible(false);
        this.nua.select(0);
        this.nua.setEnabled(false);
        while (this.nua.getItemCount() > 1) {
            this.nua.remove(this.nua.getItemCount() - 1);
        }
        this.Zb();
        this.mua.setVisible(true);
        this.mua.setEnabled(true);
        this.nua.setVisible(true);
        this.nua.setEnabled(true);
        this.mua.invalidate();
        this.nua.invalidate();
        this.Hta.invalidate();
        this.Yta.validate();
    }
    
    private void h(final String s, final String s2) {
        if (s != null && s2 != null && s.equals(s2) && this.oua.a(s) != null) {
            return;
        }
        this.mua.setVisible(false);
        this.nua.setVisible(false);
        this.mua.setEnabled(false);
        this.nua.setEnabled(false);
        this.mua.select(0);
        this.nua.select(0);
        if (s != null && this.oua.a(s) == null) {
            this.mua.remove(s);
            this.nua.remove(s);
        }
        this.mua.select(0);
        this.nua.select(0);
        if (s2 != null) {
            boolean b = false;
            for (int i = 0; i < this.mua.getItemCount(); ++i) {
                if (s2.equals(this.mua.getItem(i))) {
                    b = true;
                    break;
                }
            }
            if (!b) {
                boolean b2 = false;
                for (int j = 0; j < this.mua.getItemCount(); ++j) {
                    if (s2.compareTo(this.mua.getItem(j)) < 0) {
                        this.mua.insert(s2, j);
                        b2 = true;
                        break;
                    }
                }
                if (!b2) {
                    this.mua.add(s2);
                }
            }
            final implements a = this.oua.a();
            if (a != null) {
                this.mua.select(a.toString());
            }
            boolean b3 = false;
            this.nua.select(0);
            for (int k = 0; k < this.nua.getItemCount(); ++k) {
                if (s2.equals(this.nua.getItem(k))) {
                    b3 = true;
                    break;
                }
            }
            if (!b3) {
                boolean b4 = false;
                for (int l = 0; l < this.nua.getItemCount(); ++l) {
                    if (s2.compareTo(this.nua.getItem(l)) < 0) {
                        this.nua.insert(s2, l);
                        b4 = true;
                        break;
                    }
                }
                if (!b4) {
                    this.nua.add(s2);
                }
            }
            final implements b5 = this.oua.b();
            if (b5 != null) {
                this.nua.select(b5.toString());
            }
        }
        this.mua.setEnabled(true);
        this.nua.setEnabled(true);
        this.mua.setVisible(true);
        this.nua.setVisible(true);
        this.mua.invalidate();
        this.nua.invalidate();
        this.Hta.invalidate();
        this.Yta.validate();
    }
    
    private void U(final String s) {
        this.mua.setVisible(false);
        this.nua.setVisible(false);
        this.mua.setEnabled(false);
        this.nua.setEnabled(false);
        this.mua.select(0);
        this.nua.select(0);
        this.mua.remove(s);
        this.nua.remove(s);
        final implements a = this.oua.a();
        if (a != null) {
            this.mua.select(a.toString());
        }
        else {
            this.mua.select(0);
        }
        final implements b = this.oua.b();
        if (b != null) {
            this.nua.select(b.toString());
        }
        else {
            this.nua.select(0);
        }
        this.mua.setEnabled(true);
        this.nua.setEnabled(true);
        this.mua.setVisible(true);
        this.nua.setVisible(true);
        this.mua.invalidate();
        this.nua.invalidate();
        this.Hta.invalidate();
        this.Yta.validate();
    }
    
    private void Zb() {
        String string = null;
        final implements a = this.oua.a();
        if (a != null) {
            string = a.toString();
        }
        String string2 = null;
        final implements b = this.oua.b();
        if (b != null) {
            string2 = b.toString();
        }
        final String[] _ = this.oua._();
        catch.b(_);
        for (int i = 0; i < _.length; ++i) {
            this.mua.add(_[i]);
        }
        if (!this.mua.getItem(0).equals(this.oua.a().b("cbNoIndicator"))) {
            this.mua.insert(this.oua.a().b("cbNoIndicator"), 0);
        }
        for (int j = 0; j < _.length; ++j) {
            this.nua.add(_[j]);
        }
        if (!this.nua.getItem(0).equals(this.oua.a().b("cbNoIndicator"))) {
            this.nua.insert(this.oua.a().b("cbNoIndicator"), 0);
        }
        this.n(string);
        this.c(string2);
    }
    
    private void Sb() {
        implements implements1 = null;
        if (this.lua == this.mua) {
            implements1 = this.oua.a();
        }
        else if (this.lua == this.nua) {
            implements1 = this.oua.b();
        }
        for (int i = 0; i < this.Ua.length; ++i) {
            this.Ua[i].setText("");
            this.Ua[i].setEnabled(false);
        }
        if (implements1 == null || this.lua.getSelectedItem().equals(this.oua.a().b("cbNoIndicator"))) {
            return;
        }
        for (int j = 0; j < implements1.C(); ++j) {
            this.Ua[j].setText(String.valueOf(implements1.getParam(j)));
            this.Ua[j].setEnabled(true);
        }
    }
    
    private void T(final boolean b) {
        for (int i = 0; i < this.xta.length; ++i) {
            this.xta[i].setEnabled(b);
        }
        for (int j = 0; j < this.kua.length; ++j) {
            if (this.kua[j] != null) {
                this.kua[j].setEnabled(b);
            }
        }
    }
    
    private void Tb() {
        final String[] array = new String[this.xta.length];
        if (array == null) {
            return;
        }
        for (int i = 0; i < array.length; ++i) {
            array[i] = this.xta[i].a();
        }
        for (int j = 0; j < this.kua.length; ++j) {
            if (this.kua[j] != null) {
                this.kua[j].setText("");
                this.kua[j].setEnabled(false);
            }
        }
        for (int k = 0; k < this.kua.length; ++k) {
            final implements b = this.oua.b(array[k]);
            if (b != null && b.C() > 0 && this.kua[k] != null) {
                this.kua[k].setText(String.valueOf(b.getParam(0)));
                this.kua[k].setEnabled(true);
            }
        }
    }
    
    private void _c() {
        implements implements1 = null;
        if (this.lua == this.mua) {
            implements1 = this.oua.a();
        }
        else if (this.lua == this.nua) {
            implements1 = this.oua.b();
        }
        if (implements1 == null || this.lua.getSelectedItem().equals(this.oua.a().b("cbNoIndicator"))) {
            return;
        }
        for (int n = 0; n < this.Ua.length && n < implements1.C(); ++n) {
            int n2;
            try {
                n2 = Integer.parseInt(this.Ua[n].getText());
            }
            catch (NumberFormatException ex) {
                n2 = implements1.getParam(n);
            }
            if (n2 < 2 || n2 > 999) {
                n2 = implements1.getParam(n);
            }
            this.Ua[n].setText(String.valueOf(n2));
            implements1.a(n, n2);
        }
    }
    
    private void ac() {
        final String[] array = new String[this.xta.length];
        if (array == null) {
            return;
        }
        for (int i = 0; i < array.length; ++i) {
            array[i] = this.xta[i].a();
        }
        for (int j = 0; j < this.kua.length; ++j) {
            if (this.kua[j] != null) {
                final implements b = this.oua.b(array[j]);
                int n;
                try {
                    n = Integer.parseInt(this.kua[j].getText());
                }
                catch (NumberFormatException ex) {
                    n = b.getParam(0);
                }
                if (n < 2 || n > 999) {
                    n = b.getParam(0);
                }
                this.kua[j].setText(String.valueOf(n));
                b.a(0, n);
            }
        }
    }
    
    public static String _(final byte[] array) {
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
    
    public continue _() {
        return this.lb._();
    }
    
    public default b() {
        return this.lb;
    }
    
    public void o(final int n) {
        this.Xta.v(Math.max(50, n));
    }
    
    public void V(final boolean qua) {
        this.qua = qua;
    }
    
    public void bc() {
        this.Na.setVisible(false);
    }
    
    public void cc() {
        this.mta.setVisible(false);
    }
    
    public void O(final boolean b) {
        this.oua._().O(b);
    }
    
    public void _(final Component component) {
        if (component instanceof t) {
            ((t)component).dispose();
        }
        if (component instanceof Container) {
            final Component[] components = ((Container)component).getComponents();
            for (int i = 0; i < components.length; ++i) {
                this._(components[i]);
            }
        }
    }
    
    public synchronized void k(final int wua, final int xua) {
        this.wua = wua;
        this.xua = xua;
    }
    
    public String getName() {
        return _(this.Cua);
    }
    
    public static String M() {
        return _(n.Dua);
    }
    
    public void W(final boolean zua) {
        this.zua = zua;
    }
    
    public void S(final boolean state) {
        this.Dta.setState(state);
    }
    
    public boolean _() {
        return this.Dta.isVisible() && this.Dta.getState();
    }
    
    public void X(final boolean bua) {
        this.Bua = bua;
    }
    
    static kj a(final n n) {
        return n.Eua;
    }
    
    static List b(final n n) {
        return n.Kta;
    }
    
    static break a(final n n) {
        return n.oua;
    }
    
    static boolean _(final n n) {
        return n.pua;
    }
    
    static Choice _(final n n) {
        return n.mua;
    }
    
    static Choice d(final n n) {
        return n.nua;
    }
    
    static Label _(final n n) {
        return n.info;
    }
    
    static a b(final n n) {
        return n.rua;
    }
    
    static default _(final n n) {
        return n.lb;
    }
    
    static boolean f(final n n) {
        return n.Aua;
    }
    
    static c _(final n n) {
        return n.Lta;
    }
    
    static void n(final n n) {
        n.Sb();
    }
    
    static void c(final n n) {
        n.Tb();
    }
    
    static void d(final n n) {
        n._c();
    }
    
    static void e(final n n) {
        n.ac();
    }
    
    static int _(final n n) {
        return n.V();
    }
    
    static Choice b(final n n, final Choice lua) {
        return n.lua = lua;
    }
    
    static Choice e(final n n) {
        return n.lua;
    }
    
    static MenuItem a(final n n) {
        return n.Uta;
    }
    
    static void f(final n n) {
        n.Ub();
    }
    
    static MenuItem b(final n n) {
        return n.Sta;
    }
    
    static void a(final n n, final boolean b) {
        n.U(b);
    }
    
    static MenuItem _(final n n) {
        return n.Tta;
    }
    
    static PopupMenu _(final n n) {
        return n.Rta;
    }
    
    static Choice f(final n n) {
        return n.Za;
    }
    
    static Choice b(final n n) {
        return n.Jta;
    }
    
    static hj b(final n n) {
        return n.fta;
    }
    
    static Hashtable b(final n n) {
        return n.Ita;
    }
    
    static String[] _(final n n) {
        return n.i();
    }
    
    static void a(final n n, final String[] array, final String s) {
        n.b(array, s);
    }
    
    static String[] a(final n n) {
        return n.Zta;
    }
    
    static boolean b(final n n) {
        return n.cua;
    }
    
    static Choice a(final n n) {
        return n.tta;
    }
    
    static boolean a(final n n) {
        return n.aa();
    }
    
    static gj _(final n n) {
        return n.dta;
    }
    
    static Frame b(final n n) {
        return n.a();
    }
    
    static String a(final n n) {
        return n.eta;
    }
    
    static MenuItem m(final n n) {
        return n.Ota;
    }
    
    static Hashtable _(final n n) {
        return n._ua;
    }
    
    static MenuItem n(final n n) {
        return n.Pta;
    }
    
    static void b(final n n, final String[] array, final String s) {
        n.a(array, s);
    }
    
    static void _(final n n, final String[] array) {
        n._(array);
    }
    
    static MenuItem c(final n n) {
        return n.Qta;
    }
    
    static void _(final n n) {
        n.Mb();
    }
    
    static boolean g(final n n) {
        return n.zua;
    }
    
    static void b(final n n, final String s) {
        n.S(s);
    }
    
    static void a(final n n, final String s) {
        n.P(s);
    }
    
    static c a(final n n) {
        return n.Mta;
    }
    
    static void _(final n n, final String s) {
        n.R(s);
    }
    
    static String _(final n n, final String eta) {
        return n.eta = eta;
    }
    
    static MenuItem d(final n n) {
        return n.Nta;
    }
    
    static PopupMenu a(final n n) {
        return n.Vta;
    }
    
    static boolean h(final n n) {
        return n.Bua;
    }
    
    static t W(final n n) {
        return n.zta;
    }
    
    static void m(final n n) {
        n.doLoadConfigAction();
    }
    
    static t X(final n n) {
        return n.yta;
    }
    
    static void b(final n n) {
        n.doSaveConfigAction();
    }
    
    static t Y(final n n) {
        return n.Cta;
    }
    
    static t U(final n n) {
        return n.Bta;
    }
    
    static void a(final n n) {
        n.Wb();
    }
    
    static t Z(final n n) {
        return n.Ata;
    }
    
    static Panel a(final n n) {
        return n.Yta;
    }
    
    static a a(final n n) {
        return n.Xta;
    }
    
    static Container _(final n n) {
        return n.b();
    }
    
    static FnChartsApplet _(final n n) {
        return n.Nsa;
    }
    
    static Button b(final n n) {
        return n.iua;
    }
    
    static Button _(final n n) {
        return n.jua;
    }
    
    static t b(final n n) {
        return n.eua;
    }
    
    static void g(final n n) {
        n.Vb();
    }
    
    static t _(final n n) {
        return n.fua;
    }
    
    static t k(final n n) {
        return n.gua;
    }
    
    static t l(final n n) {
        return n.hua;
    }
    
    static t m(final n n) {
        return n.uta;
    }
    
    static t n(final n n) {
        return n.vta;
    }
    
    static t c(final n n) {
        return n.wta;
    }
    
    static t d(final n n) {
        return n.ota;
    }
    
    static t e(final n n) {
        return n.nta;
    }
    
    static String[] b(final n n) {
        return n.Fua;
    }
    
    static t f(final n n) {
        return n.mta;
    }
    
    static boolean a(final n n, final boolean cua) {
        return n.cua = cua;
    }
    
    static t g(final n n) {
        return n.pta;
    }
    
    static t a(final n n) {
        return n.qta;
    }
    
    static t h(final n n) {
        return n.rta;
    }
    
    static t i(final n n) {
        return n.sta;
    }
    
    static t j(final n n) {
        return n.hta;
    }
    
    static t Q(final n n) {
        return n.ita;
    }
    
    static t R(final n n) {
        return n.jta;
    }
    
    static t S(final n n) {
        return n.kta;
    }
    
    static t T(final n n) {
        return n.lta;
    }
    
    static void b(final n n, final boolean b) {
        n.T(b);
    }
    
    static t V(final n n) {
        return n.dua;
    }
    
    static String b(final n n) {
        return n.gta;
    }
    
    static {
        Dua = new byte[] { 0, 0, 0, 0, 0, 0, 51, 55, 48, 0, 0, 88, 44, -6, 39, 3, 0, 0, 0 };
    }
}
