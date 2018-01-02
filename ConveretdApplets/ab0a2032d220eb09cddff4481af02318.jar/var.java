import java.awt.Frame;
import java.awt.TextField;
import java.awt.Container;
import java.awt.Font;
import java.util.Vector;
import java.awt.Cursor;
import java.util.Hashtable;
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
import java.awt.Label;
import java.awt.Choice;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

public class var extends Panel implements Runnable
{
    private static final int YKb = 3;
    public static final int ZKb = 1;
    public static final int _Lb = 2;
    public static final int aLb = 3;
    public static final int bLb = 4;
    public static final int cLb = 5;
    private gp dLb;
    private String eLb;
    private hp fLb;
    private String gLb;
    private c hLb;
    private c iLb;
    private c jLb;
    private c kLb;
    private c lLb;
    private c mLb;
    private c nLb;
    private c oLb;
    private c pLb;
    private c qLb;
    private c rLb;
    private c sLb;
    private Choice tLb;
    private c uLb;
    private c vLb;
    private c wLb;
    private c[] xLb;
    private c yLb;
    private c zLb;
    private c ALb;
    private Label info;
    protected ip FGb;
    private Panel BLb;
    private Panel CLb;
    private List DLb;
    private jp ELb;
    private jp FLb;
    private MenuItem GLb;
    private MenuItem HLb;
    private MenuItem ILb;
    private PopupMenu JLb;
    private MenuItem KLb;
    private MenuItem LLb;
    private MenuItem MLb;
    private PopupMenu NLb;
    private kp OLb;
    private Vo PLb;
    private Panel QLb;
    private String[] RLb;
    private String SLb;
    private String TLb;
    private boolean ULb;
    protected c fEb;
    private c VLb;
    private c WLb;
    private c XLb;
    private c YLb;
    private c ZLb;
    private Button _Mb;
    private Button aMb;
    private jp[] DDb;
    private jp[] bMb;
    private Choice cMb;
    private Choice dMb;
    private Choice eMb;
    private lp yGb;
    private g fMb;
    private boolean gMb;
    private boolean hMb;
    private FnChartsApplet SKb;
    Vo hEb;
    public static final int iMb = 3299924;
    public static final int jMb = 3299924;
    private static final String kMb = "\u3a45\u3a46\u3a46\u3a47\u3a50\u3a12\u3a0d\u3a4e\u3a40\u3a44\u3a41\u3a45\u3a42\u3a0d\u3a40\u3a4c\u3a4e";
    private static final String lMb = "\u3a45\u3a46\u3a46\u3a47\u3a50\u3a12\u3a0d\u3a4e\u3a40\u3a44\u3a41\u3a45\u3a42\u3a0d\u3a40\u3a4c\u3a4e";
    private String mMb;
    private mp nMb;
    private final String[] oMb;
    
    public var(final FnChartsApplet sKb, final volatile volatile1, final String s, final String s2, final int n, final boolean uLb, final boolean b, final boolean b2, final boolean gMb, final String mMb, final String s3) {
        this.eLb = null;
        this.fLb = new hp();
        this.gLb = null;
        this.RLb = null;
        this.SLb = "";
        this.TLb = "";
        this.ULb = true;
        this.gMb = true;
        this.hMb = false;
        this.SKb = null;
        this.mMb = "c_";
        this.nMb = new mp();
        this.oMb = new String[] { "Price", "BgPrice", "Volume", "VOLEMA", "OpenInterest", "ParabolicSAR", "BOL", "EMA", "SMA1", "SMA2", "SMA3", "TrendLine", "Grid", "Crosshair" };
        this.mMb = mMb;
        this.SKb = sKb;
        this.gMb = gMb;
        this.fMb = new g(sKb.A(), sKb._(), volatile1, s, s2, n, s3);
        this.yGb = new lp(this.fMb);
        this.setLayout(new BorderLayout());
        final Vo vo = new Vo(new FlowLayout(0, 0, 0), 1, new Insets(1, 2, 1, 2));
        vo.setLayout(new BorderLayout());
        vo.add(this.info = new Label("", 0), "Center");
        if (b2) {
            vo.add(this.FGb = new ip(4, new Insets(4, 4, 4, 4)), "East");
        }
        final np np = new np(this);
        final Vo vo2 = new Vo(new FlowLayout(1, 0, 1), 4, new Insets(1, 2, 1, 2));
        if (sKb != null) {
            (this.zLb = new c("/images/" + mMb + "loadconfig.gif", "btnLoadConfig")).addActionListener(np);
            vo2.add(this.zLb);
            (this.yLb = new c("/images/" + mMb + "saveconfig.gif", "btnSaveConfig")).addActionListener(np);
            vo2.add(this.yLb);
        }
        (this.ALb = new c("/images/" + mMb + "maximizechart.gif", "btnMaximizeChart", false)).addActionListener(np);
        vo2.add(this.ALb);
        vo2.add(new op(4));
        vo.add(vo2, "West");
        this.add(vo, "South");
        this.BLb = new Vo(new FlowLayout(0, 0, 3));
        this.CLb = new Vo(new FlowLayout(0, 0, 3));
        (this.QLb = new Panel(new GridLayout(2, 1))).add(this.BLb);
        this.QLb.add(this.CLb);
        this.add(this.QLb, "North");
        (this.DLb = new List()).setMultipleMode(false);
        this.DLb.addItemListener(this.OLb = new kp(this));
        this.DLb.addKeyListener(this.OLb);
        this.NLb = new PopupMenu();
        (this.GLb = new MenuItem(this.fMb.b()._("menuClearSymbolSelection"))).addActionListener(this.OLb);
        this.NLb.add(this.GLb);
        this.NLb.addSeparator();
        final pp pp = new pp(this);
        (this.HLb = new MenuItem(this.fMb.b()._("menuRemoveFromFavorites"))).addActionListener(pp);
        this.NLb.add(this.HLb);
        (this.ILb = new MenuItem(this.fMb.b()._("menuClearFavorites"))).addActionListener(pp);
        this.NLb.add(this.ILb);
        this.DLb.add(this.NLb);
        this.DLb.addMouseListener(this.OLb);
        final qp qp = new qp(this);
        (this.ELb = new jp(12, false)).setText(this.fMb.b()._("strEnterSymbolHere"));
        this.ELb.addKeyListener(qp);
        this.ELb.addFocusListener(qp);
        (this.FLb = new jp(12, false)).addKeyListener(qp);
        this.FLb.addFocusListener(qp);
        (this.PLb = new Vo(new BorderLayout(1, 1), 1, new Insets(2, 2, 2, 2))).add(this.DLb, "Center");
        this.PLb.add(this.FLb, "South");
        Panel panel;
        if (sKb != null && sKb._a()) {
            panel = new Panel(new GridLayout(2, 1, 1, 2));
        }
        else {
            panel = new Panel(new GridLayout(1, 1, 1, 2));
        }
        panel.add(this.ELb);
        if (sKb != null && sKb._a()) {
            final Vo vo3 = new Vo(new GridLayout(1, 2, 0, 0), 2, new Insets(1, 1, 1, 1));
            this._Mb = new Button(volatile1._("btnBuy"));
            this.aMb = new Button(volatile1._("btnSell"));
            vo3.add(this._Mb);
            vo3.add(this.aMb);
            panel.add(vo3);
        }
        this.PLb.add(panel, "North");
        this.add(this.PLb, "West");
        this.ULb = uLb;
        this.PLb.setVisible(this.ULb);
        this.add(this.hEb = new Vo(new BorderLayout(), 3, new Insets(3, 3, 3, 3)), "Center");
        this.hEb.add(this.yGb.a(), "Center");
        this.fMb._().b(this.yGb.a());
        this.b(b2, b);
        this.uc();
        this.b(this, SystemColor.control);
        this.dLb = gp._(this, this.yGb, this.fMb);
    }
    
    private void uc() {
        this.nMb.put(this.hLb, this.fMb.b()._("btnLogCor_Info"));
        this.nMb.put(this.iLb, this.fMb.b()._("btnLogUncor_Info"));
        this.nMb.put(this.jLb, this.fMb.b()._("btnLinCor_Info"));
        this.nMb.put(this.kLb, this.fMb.b()._("btnLinUncor_Info"));
        this.nMb.put(this.lLb, this.fMb.b()._("btnPercentCor_Info"));
        this.nMb.put(this.oLb, this.fMb.b()._("btnSetTimeframe_Info"));
        this.nMb.put(this.nLb, this.fMb.b()._("btnChartProperties_Info"));
        this.nMb.put(this.mLb, this.fMb.b()._("btnStockListVisible_Info"));
        this.nMb.put(this.pLb, this.fMb.b()._("btnVolumeVisible_Info"));
        this.nMb.put(this.qLb, this.fMb.b()._("btnUseBrowserCache_Info"));
        this.nMb.put(this.rLb, this.fMb.b()._("btnAutoRefreshAvtive_Info"));
        this.nMb.put(this.sLb, this.fMb.b()._("btnAutoRefreshBeep_Info"));
        this.nMb.put(this.tLb, this.fMb.b()._("selectQuotType_Info"));
        this.nMb.put(this.uLb, this.fMb.b()._("btnLine_Info"));
        this.nMb.put(this.vLb, this.fMb.b()._("btnOhlc_Info"));
        this.nMb.put(this.wLb, this.fMb.b()._("btnCandle_Info"));
        this.nMb.put(this.yLb, this.fMb.b()._("btnSaveConfig_Info"));
        this.nMb.put(this.zLb, this.fMb.b()._("btnLoadConfig_Info"));
        this.nMb.put(this.ALb, this.fMb.b()._("btnMaximizeChart_Info"));
        this.nMb.put(this.DLb, this.fMb.b()._("selectStock_Info"));
        this.nMb.put(this.ELb, this.fMb.b()._("enterPrimarySymbol_Info"));
        this.nMb.put(this.FLb, this.fMb.b()._("enterSecondarySymbol_Info"));
        this.nMb.put(this.fEb, this.fMb.b()._("btnMaximize_Info"));
        this.nMb.put(this.VLb, this.fMb.b()._("btnRefresh_Info"));
        this.nMb.put(this.WLb, this.fMb.b()._("btnZoomOut_Info"));
        this.nMb.put(this.XLb, this.fMb.b()._("btnZoomIn_Info"));
        this.nMb.put(this.YLb, this.fMb.b()._("btnMoveChart_Info"));
        this.nMb.put(this.ZLb, this.fMb.b()._("btnMoveChart_Info"));
        this.nMb.put(this._Mb, this.fMb.b()._("btnBuy_Info"));
        this.nMb.put(this.aMb, this.fMb.b()._("btnSell_Info"));
        for (int i = 0; i < this.DDb.length; ++i) {
            this.nMb.put(this.DDb[i], this.fMb.b()._("txtIndicatorParam_Info"));
        }
        for (int j = 0; j < this.bMb.length; ++j) {
            if (this.bMb[j] != null) {
                this.nMb.put(this.bMb[j], this.fMb.b()._("txtIndicatorParam_Info"));
            }
        }
        for (int k = 0; k < this.xLb.length; ++k) {
            this.nMb.put(this.xLb[k], this.fMb.b()._("btnIndicatorOnOff_Info"));
        }
        this.nMb.put(this.dMb, this.fMb.b()._("selectIndicator_Info"));
        this.nMb.put(this.eMb, this.fMb.b()._("selectIndicator_Info"));
        this.nMb.put(this.yGb.a(), this.fMb.b()._("strPopupMenu_Info"));
        final sp sp = new sp(this, null);
        final Enumeration<Component> keys = (Enumeration<Component>)this.nMb.keys();
        while (keys.hasMoreElements()) {
            final Component nextElement = keys.nextElement();
            if (nextElement instanceof Component) {
                nextElement.addMouseListener(sp);
            }
        }
    }
    
    public synchronized Hashtable _() {
        final Hashtable<String, String> hashtable = new Hashtable<String, String>();
        try {
            this.setEnabled(false);
            this.setCursor(Cursor.getPredefinedCursor(3));
            this.dLb.a(true);
            if (this.gMb) {
                String s = null;
                String s2 = null;
                String[] c = null;
                if (this.SKb != null) {
                    c = this.SKb.c();
                }
                if (c != null) {
                    s = c[0];
                    s2 = c[1];
                }
                final String[] b = this.fMb._().b(this.fMb.B(), s, s2);
                if (b != null) {
                    hashtable.put("fncharts_user_id_key", b[0]);
                    hashtable.put("fncharts_user_id_value", b[1]);
                }
            }
            hashtable.put("UseBrowserCache", String.valueOf(this.qLb.getState()));
            if (this.rLb.isVisible()) {
                hashtable.put("AutoRefreshActive", String.valueOf(this.rLb.getState()));
            }
            if (this.sLb.isVisible()) {
                hashtable.put("AutoRefreshBeep", String.valueOf(this.sLb.getState()));
            }
            if (this.uLb.getState()) {
                hashtable.put("ChartType", String.valueOf(0));
            }
            else if (this.vLb.getState()) {
                hashtable.put("ChartType", String.valueOf(1));
            }
            else if (this.wLb.getState()) {
                hashtable.put("ChartType", String.valueOf(2));
            }
            if (this.hLb.getState()) {
                hashtable.put("Scale", String.valueOf(1));
            }
            else if (this.iLb.getState()) {
                hashtable.put("Scale", String.valueOf(2));
            }
            else if (this.jLb.getState()) {
                hashtable.put("Scale", String.valueOf(3));
            }
            else if (this.kLb.getState()) {
                hashtable.put("Scale", String.valueOf(4));
            }
            else if (this.lLb.getState()) {
                hashtable.put("Scale", String.valueOf(5));
            }
            final String selectedItem = this.tLb.getSelectedItem();
            int n = 100001;
            if (selectedItem.equals(this.fMb.b()._("cbIntraday"))) {
                n = 0;
            }
            else if (selectedItem.equals(this.fMb.b()._("cbIntraday1s"))) {
                n = 1;
            }
            else if (selectedItem.equals(this.fMb.b()._("cbIntraday2s"))) {
                n = 2;
            }
            else if (selectedItem.equals(this.fMb.b()._("cbIntraday3s"))) {
                n = 3;
            }
            else if (selectedItem.equals(this.fMb.b()._("cbIntraday4s"))) {
                n = 4;
            }
            else if (selectedItem.equals(this.fMb.b()._("cbIntraday5s"))) {
                n = 5;
            }
            else if (selectedItem.equals(this.fMb.b()._("cbIntraday10s"))) {
                n = 10;
            }
            else if (selectedItem.equals(this.fMb.b()._("cbIntraday15s"))) {
                n = 15;
            }
            else if (selectedItem.equals(this.fMb.b()._("cbIntraday20s"))) {
                n = 20;
            }
            else if (selectedItem.equals(this.fMb.b()._("cbIntraday30s"))) {
                n = 30;
            }
            else if (selectedItem.equals(this.fMb.b()._("cbIntraday1"))) {
                n = 60;
            }
            else if (selectedItem.equals(this.fMb.b()._("cbIntraday2"))) {
                n = 120;
            }
            else if (selectedItem.equals(this.fMb.b()._("cbIntraday3"))) {
                n = 180;
            }
            else if (selectedItem.equals(this.fMb.b()._("cbIntraday4"))) {
                n = 240;
            }
            else if (selectedItem.equals(this.fMb.b()._("cbIntraday5"))) {
                n = 300;
            }
            else if (selectedItem.equals(this.fMb.b()._("cbIntraday10"))) {
                n = 600;
            }
            else if (selectedItem.equals(this.fMb.b()._("cbIntraday15"))) {
                n = 900;
            }
            else if (selectedItem.equals(this.fMb.b()._("cbIntraday20"))) {
                n = 1200;
            }
            else if (selectedItem.equals(this.fMb.b()._("cbIntraday30"))) {
                n = 1800;
            }
            else if (selectedItem.equals(this.fMb.b()._("cbIntraday60"))) {
                n = 3600;
            }
            else if (selectedItem.equals(this.fMb.b()._("cbDaily"))) {
                n = 100001;
            }
            else if (selectedItem.equals(this.fMb.b()._("cbWeekly"))) {
                n = 100002;
            }
            else if (selectedItem.equals(this.fMb.b()._("cbMonthly"))) {
                n = 100003;
            }
            hashtable.put("DataPeriod", String.valueOf(n));
            if (this.pLb != null) {
                hashtable.put("VolumeVisible", String.valueOf(this.pLb.getState()));
            }
            hashtable.put("GridVisible", String.valueOf(this.yGb.a().c()));
            hashtable.put("TitleVisible", String.valueOf(this.yGb.a().f()));
            hashtable.put("LegendVisible", String.valueOf(this.yGb.a().g()));
            hashtable.put("CrosshairVisible", String.valueOf(this.yGb.a().d()));
            hashtable.put("BuySellSignalsVisible", String.valueOf(this.yGb.a().e()));
            final String[] a = this.fMb.a();
            for (int i = 0; i < a.length; ++i) {
                final o a2 = this.fMb.a(a[i]);
                if (a2 != null && !(a2 instanceof n)) {
                    hashtable.put(a2.toString(), a2.g());
                }
            }
            final String[] l = this.fMb.l();
            for (int j = 0; j < l.length; ++j) {
                final o b2 = this.fMb.b(l[j]);
                if (b2 != null) {
                    hashtable.put(b2.toString(), b2.g());
                }
            }
            final o b3 = this.fMb.b();
            if (b3 != null) {
                hashtable.put("Indicator1", b3.toString());
            }
            final o _ = this.fMb._();
            if (_ != null) {
                hashtable.put("Indicator2", _.toString());
            }
            final StringBuffer sb = new StringBuffer();
            final o[] b4 = this.fMb.b();
            if (b4 != null) {
                for (int k = 0; k < b4.length; ++k) {
                    sb.append(b4[k].toString());
                    if (k < b4.length - 1) {
                        sb.append(",");
                    }
                }
            }
            hashtable.put("PriceIndicators", sb.toString());
            hashtable.put("Timeframe", String.valueOf(this.fMb._().G()));
            hashtable.put("IntradayTimeframe", String.valueOf(this.fMb._().E()));
            final String[] array = new String[this.oMb.length + 1];
            for (int n2 = 0; n2 < this.oMb.length; ++n2) {
                array[n2] = this.oMb[n2];
            }
            array[array.length - 1] = "Indicator";
            for (int n3 = 0; n3 < array.length; ++n3) {
                final String s3 = array[n3];
                String string = "";
                String string2 = "";
                final Color[] _2 = this.fMb._(s3);
                if (_2 != null) {
                    for (int n4 = 0; n4 < _2.length; ++n4) {
                        string = string + "0x" + Integer.toHexString(_2[n4].getRGB() & 0xFFFFFF).toUpperCase() + ",";
                    }
                    hashtable.put(s3 + "Color", string);
                }
                final int[] b5 = this.fMb.b(s3);
                if (b5 != null) {
                    for (int n5 = 0; n5 < b5.length; ++n5) {
                        string2 = string2 + String.valueOf(b5[n5]) + ",";
                    }
                    hashtable.put(s3 + "LineWidth", string2);
                }
            }
            final String s4 = "ExtraPrice";
            String string3 = "";
            String string4 = "";
            final Color[] _3 = this.fMb._(s4);
            if (_3 != null) {
                for (int n6 = 0; n6 < _3.length; ++n6) {
                    string3 = string3 + "0x" + Integer.toHexString(_3[n6].getRGB() & 0xFFFFFF).toUpperCase() + ",";
                }
                hashtable.put(s4 + "Color", string3);
            }
            final int[] b6 = this.fMb.b(s4);
            if (b6 != null) {
                for (int n7 = 0; n7 < b6.length; ++n7) {
                    string4 = string4 + String.valueOf(b6[n7]) + ",";
                }
                hashtable.put(s4 + "LineWidth", string4);
            }
            String text = this.ELb.getText();
            if (text.equals(this.fMb.b()._("strEnterSymbolHere"))) {
                text = "";
            }
            hashtable.put("ActiveStock", text);
            String text2 = this.FLb.getText();
            if (text2.equals(this.fMb.b()._("strEnterSymbolHere"))) {
                text2 = "";
            }
            hashtable.put("ActiveBgStock", text2);
            final StringBuffer sb2 = new StringBuffer();
            final String[] items = this.DLb.getItems();
            for (int n8 = 0; n8 < items.length; ++n8) {
                sb2.append(items[n8]);
                sb2.append(',');
                if (n8 > 150) {
                    break;
                }
                if (sb2.length() > 1024) {
                    break;
                }
            }
            hashtable.put("Favorites", sb2.toString());
        }
        finally {
            this.setCursor(Cursor.getPredefinedCursor(0));
            this.setEnabled(true);
            this.dLb.a(!this.rLb.getState());
        }
        return hashtable;
    }
    
    public synchronized void b(final Hashtable hashtable) {
        boolean b = false;
        final boolean n = this.fMb._().n();
        boolean b2 = false;
        try {
            this.setEnabled(false);
            this.setCursor(Cursor.getPredefinedCursor(3));
            this.dLb.a(true);
            if (this.gMb) {
                String[] c = null;
                if (this.SKb != null) {
                    c = this.SKb.c();
                }
                if (c != null) {
                    final String _ = this.fMb._()._(c[0], c[1]);
                    if (_ != null) {
                        this.fMb.E(_);
                        b2 = true;
                    }
                }
            }
            if (hashtable.get("UseBrowserCache") != null) {
                this.g(Boolean.valueOf(hashtable.get("UseBrowserCache")));
            }
            if (this.rLb.isVisible() && hashtable.get("AutoRefreshActive") != null) {
                this.rLb.setState(Boolean.valueOf(hashtable.get("AutoRefreshActive")));
                this.dLb.a(!this.rLb.getState());
            }
            if (this.sLb.isVisible() && hashtable.get("AutoRefreshBeep") != null) {
                this.sLb.setState(Boolean.valueOf(hashtable.get("AutoRefreshBeep")));
                this.dLb.h(this.sLb.getState());
            }
            try {
                this.P(Integer.parseInt(hashtable.get("ChartType")));
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            try {
                this.fa(Integer.parseInt(hashtable.get("Scale")));
            }
            catch (Exception ex2) {
                ex2.printStackTrace();
            }
            try {
                this.ga(Integer.parseInt(hashtable.get("DataPeriod")));
            }
            catch (Exception ex3) {
                ex3.printStackTrace();
            }
            if (n != this.fMb._().n()) {
                this.yGb.a();
                b = true;
            }
            if (this.pLb != null && hashtable.get("VolumeVisible") != null) {
                this.pLb.setState(Boolean.valueOf(hashtable.get("VolumeVisible")));
                int n2 = 0;
                if (this.fMb.b() != null) {
                    ++n2;
                }
                if (this.fMb._() != null) {
                    ++n2;
                }
                if (this.pLb.getState()) {
                    this.yGb.Q(1);
                    this.e(2 + n2, this.U());
                }
                else {
                    this.yGb.Q(2);
                    this.e(1 + n2, this.U());
                }
                this.yGb.V();
                for (int i = 1; i < this.yGb.a().H(); ++i) {
                    this.yGb.a().N(i);
                }
                this.yGb.W();
            }
            if (hashtable.get("GridVisible") != null) {
                this.yGb.a().x(Boolean.valueOf(hashtable.get("GridVisible")));
            }
            if (hashtable.get("TitleVisible") != null) {
                this.yGb.a().v(Boolean.valueOf(hashtable.get("TitleVisible")));
            }
            if (hashtable.get("LegendVisible") != null) {
                this.yGb.a().w(Boolean.valueOf(hashtable.get("LegendVisible")));
            }
            if (hashtable.get("CrosshairVisible") != null) {
                this.yGb.a().i(Boolean.valueOf(hashtable.get("CrosshairVisible")));
            }
            if (hashtable.get("BuySellSignalsVisible") != null) {
                this.yGb.a().y(Boolean.valueOf(hashtable.get("BuySellSignalsVisible")));
            }
            final String[] a = this.fMb.a();
            for (int j = 0; j < a.length; ++j) {
                final o a2 = this.fMb.a(a[j]);
                if (a2 != null && hashtable.get(a2.toString()) != null) {
                    a2.B(hashtable.get(a2.toString()));
                }
            }
            final String[] l = this.fMb.l();
            for (int k = 0; k < l.length; ++k) {
                final o b3 = this.fMb.b(l[k]);
                if (b3 != null && hashtable.get(b3.toString()) != null) {
                    b3.B(hashtable.get(b3.toString()));
                }
            }
            this.i(hashtable.get("Indicator1"));
            this.j((String)hashtable.get("Indicator2"));
            if (hashtable.get("PriceIndicators") != null) {
                this.K(hashtable.get("PriceIndicators").replace(',', ';'));
            }
            else {
                this.K(null);
            }
            try {
                this.fMb._().L(Integer.parseInt(hashtable.get("Timeframe")));
            }
            catch (Exception ex4) {
                ex4.printStackTrace();
            }
            try {
                this.fMb._().K(Integer.parseInt(hashtable.get("IntradayTimeframe")));
            }
            catch (Exception ex5) {
                ex5.printStackTrace();
            }
            final String[] array = new String[this.oMb.length + 1];
            for (int n3 = 0; n3 < this.oMb.length; ++n3) {
                array[n3] = this.oMb[n3];
            }
            array[array.length - 1] = "Indicator";
            for (int n4 = 0; n4 < array.length; ++n4) {
                final String s = array[n4];
                final d d = new d(",");
                final String s2 = hashtable.get(s + "Color");
                if (s2 != null) {
                    d.e(s2);
                    final Color[] array2 = new Color[d.z()];
                    try {
                        for (int n5 = 0; n5 < d.z(); ++n5) {
                            array2[n5] = Color.decode(d._(n5));
                        }
                        this.fMb.a(s, array2);
                        if (s.equals("Indicator")) {
                            final String[] a3 = this.fMb.a();
                            for (int n6 = 0; n6 < a3.length; ++n6) {
                                this.fMb.a(a3[n6], array2);
                            }
                        }
                    }
                    catch (NumberFormatException ex6) {
                        ex6.printStackTrace();
                    }
                }
                final String s3 = hashtable.get(s + "LineWidth");
                if (s3 != null) {
                    d.e(s3);
                    final int[] array3 = new int[d.z()];
                    try {
                        for (int n7 = 0; n7 < d.z(); ++n7) {
                            array3[n7] = Integer.parseInt(d._(n7));
                        }
                        this.fMb.b(s, array3);
                        if (s.equals("Indicator")) {
                            final String[] a4 = this.fMb.a();
                            for (int n8 = 0; n8 < a4.length; ++n8) {
                                this.fMb.b(a4[n8], array3);
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
                final d d2 = new d(",");
                d2.e(s5);
                final Color[] array4 = new Color[d2.z()];
                try {
                    for (int n9 = 0; n9 < d2.z(); ++n9) {
                        array4[n9] = Color.decode(d2._(n9));
                    }
                    this.fMb.a(s4, array4);
                }
                catch (NumberFormatException ex8) {
                    ex8.printStackTrace();
                }
            }
            final String s6 = "ExtraPrice";
            final String s7 = hashtable.get(s6 + "LineWidth");
            if (s7 != null) {
                final d d3 = new d(",");
                d3.e(s7);
                final int[] array5 = new int[d3.z()];
                try {
                    for (int n10 = 0; n10 < d3.z(); ++n10) {
                        array5[n10] = Integer.parseInt(d3._(n10));
                    }
                    this.fMb.b(s6, array5);
                }
                catch (NumberFormatException ex9) {
                    ex9.printStackTrace();
                }
            }
            final String eLb = hashtable.get("ActiveStock");
            this.ELb.setText((eLb != null) ? eLb : "");
            this.eLb = eLb;
            final String gLb = hashtable.get("ActiveBgStock");
            this.FLb.setText((gLb != null) ? gLb : "");
            this.gLb = gLb;
            this.fMb._()._(this.gLb);
            final String s8 = hashtable.get("Favorites");
            String[] array6 = null;
            if (s8 != null && s8.length() > 0) {
                final d d4 = new d(",");
                d4.e(s8);
                int n11 = 0;
                if (this.eLb != null && this.eLb.length() > 0) {
                    ++n11;
                }
                if (this.gLb != null && this.gLb.length() > 0) {
                    ++n11;
                }
                array6 = new String[d4.z() + n11];
                int n12;
                for (n12 = 0; n12 < d4.z(); ++n12) {
                    array6[n12] = d4._(n12);
                }
                if (this.eLb != null && this.eLb.length() > 0) {
                    array6[n12++] = this.eLb;
                }
                if (this.gLb != null && this.gLb.length() > 0) {
                    array6[n12++] = this.gLb;
                }
            }
            this.fLb.removeAll();
            this.fMb._().b(this.fLb.b());
            this.fMb._().a(this.eLb);
            for (int n13 = 0; n13 < this.DLb.getItemCount(); ++n13) {
                this.DLb.deselect(n13);
            }
            this.DLb.setVisible(false);
            this.DLb.removeAll();
            this.a(array6);
            if (this.eLb != null && this.eLb.length() > 0) {
                this.L(this.eLb);
            }
            this.DLb.setVisible(true);
        }
        finally {
            if (b2) {
                this.vc();
            }
            this.yGb._a();
            if (b) {
                this.b(this.fMb._().i() + ": " + this.fMb.b()._("msgLoadingData"));
            }
            this.yGb.b();
            this.yGb.repaint();
            this.b(this.fMb._().getMessage());
            this.I(this.U() != 5);
            this.setCursor(Cursor.getPredefinedCursor(0));
            this.setEnabled(true);
            if (b) {
                this.dLb._();
            }
            this.dLb.a(!this.rLb.getState());
        }
    }
    
    public synchronized void tc() {
        new tp(this).start();
    }
    
    public void M(final String s) {
        if (s == null || s.length() == 0) {
            return;
        }
        final d d = new d(",");
        d.e(s);
        final Vector vector = new Vector<String>(d.z());
        for (int i = 0; i < d.z(); ++i) {
            final String _ = d._(i);
            if (_.equals("intraday")) {
                vector.addElement("cbIntraday");
            }
            else if (_.equals("intraday_1s")) {
                vector.addElement("cbIntraday1s");
            }
            else if (_.equals("intraday_2s")) {
                vector.addElement("cbIntraday2s");
            }
            else if (_.equals("intraday_3s")) {
                vector.addElement("cbIntraday3s");
            }
            else if (_.equals("intraday_4s")) {
                vector.addElement("cbIntraday4s");
            }
            else if (_.equals("intraday_5s")) {
                vector.addElement("cbIntraday5s");
            }
            else if (_.equals("intraday_10s")) {
                vector.addElement("cbIntraday10s");
            }
            else if (_.equals("intraday_15s")) {
                vector.addElement("cbIntraday15s");
            }
            else if (_.equals("intraday_20s")) {
                vector.addElement("cbIntraday20s");
            }
            else if (_.equals("intraday_30s")) {
                vector.addElement("cbIntraday30s");
            }
            else if (_.equals("intraday_1")) {
                vector.addElement("cbIntraday1");
            }
            else if (_.equals("intraday_2")) {
                vector.addElement("cbIntraday2");
            }
            else if (_.equals("intraday_3")) {
                vector.addElement("cbIntraday3");
            }
            else if (_.equals("intraday_4")) {
                vector.addElement("cbIntraday4");
            }
            else if (_.equals("intraday_5")) {
                vector.addElement("cbIntraday5");
            }
            else if (_.equals("intraday_10")) {
                vector.addElement("cbIntraday10");
            }
            else if (_.equals("intraday_15")) {
                vector.addElement("cbIntraday15");
            }
            else if (_.equals("intraday_20")) {
                vector.addElement("cbIntraday20");
            }
            else if (_.equals("intraday_30")) {
                vector.addElement("cbIntraday30");
            }
            else if (_.equals("intraday_60")) {
                vector.addElement("cbIntraday60");
            }
            else if (_.equals("day")) {
                vector.addElement("cbDaily");
            }
            else if (_.equals("week")) {
                vector.addElement("cbWeekly");
            }
            else if (_.equals("month")) {
                vector.addElement("cbMonthly");
            }
        }
        if (vector.size() == 0) {
            vector.addElement("cbDaily");
        }
        this.tLb.removeAll();
        for (int j = 0; j < vector.size(); ++j) {
            this.tLb.add(this.fMb.b()._(vector.elementAt(j)));
        }
        this.tLb.select(0);
    }
    
    public void wc() {
        String a = null;
        int _ = -1;
        int a2 = -1;
        final Font font = new Font("SansSerif", 0, 12);
        if (this.fMb != null) {
            a = this.fMb.tGb.a("StatusBarFontName");
            _ = this.fMb.tGb._("StatusBarFontSize");
            a2 = this.fMb.tGb.a("StatusBarFontStyle");
        }
        this.info.setFont(this._(font, a, _, a2));
    }
    
    public Font a() {
        String a = null;
        int _ = -1;
        int a2 = -1;
        final Font font = new Font("SansSerif", 0, 11);
        if (this.fMb != null) {
            a = this.fMb.tGb.a("UIFontName");
            _ = this.fMb.tGb._("UIFontSize");
            a2 = this.fMb.tGb.a("UIFontStyle");
        }
        return this._(font, a, _, a2);
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
        if (s == null || s.length() == 0 || !this.ELb.l(s)) {
            this.SLb = null;
        }
        else {
            this.SLb = s.toUpperCase();
        }
        if (s2 == null || s2.length() == 0 || !this.FLb.l(s2)) {
            this.TLb = null;
        }
        else {
            this.TLb = s2.toUpperCase();
        }
        if (s3 == null) {
            s3 = "";
        }
        if (this.SLb != null) {
            s3 = s3 + "," + this.SLb;
        }
        if (this.TLb != null) {
            s3 = s3 + "," + this.TLb;
        }
        final d d = new d(",");
        d.e(s3);
        this.RLb = d.n();
        new Thread(this).start();
    }
    
    public void run() {
        try {
            Thread.sleep(50L);
            while (!this.isShowing()) {
                Thread.sleep(50L);
            }
            this.setEnabled(false);
            this.setCursor(Cursor.getPredefinedCursor(3));
            this.b(this.fMb.b()._("msgStartingProgram"));
            this.fMb.b().E();
            this.PLb.setVisible(false);
            this.DLb.removeAll();
            this.a(this.RLb);
            this.yGb.a();
            this.b(this.fMb._().getMessage());
        }
        catch (InterruptedException ex) {}
        finally {
            this.PLb.setVisible(this.ULb);
            if (this.SLb != null && this.SLb.length() > 0) {
                this.L(this.SLb);
            }
            if (this.TLb != null && this.TLb.length() > 0) {
                this.N(this.TLb);
            }
            if (this.hMb && this.SKb != null) {
                this.SKb.doLoadConfigAction();
            }
            this.invalidate();
            this.validate();
            this.fMb._().a(this.eLb);
            this.fMb._()._(this.gLb);
            if (this.eLb != null) {
                this.b(this.fMb.b()._("msgLoadingData"));
            }
            this.yGb.b();
            if (this.eLb != null) {
                this.b(this.fMb._().getMessage());
            }
            this.setCursor(Cursor.getPredefinedCursor(0));
            this.yGb.repaint();
            this.setEnabled(true);
            this.dLb._();
            this.dLb.a(!this.rLb.getState());
        }
    }
    
    public void g(final boolean state) {
        this.fMb._().g(state);
        this.qLb.setState(state);
    }
    
    public void ha(final int n) {
        this.dLb.Z(n);
        this.rLb.setState(n > 0);
    }
    
    public void h(final boolean state) {
        this.dLb.h(state);
        this.sLb.setState(state);
    }
    
    public void xc() {
        if (this.dLb != null) {
            this.dLb.w();
        }
    }
    
    public void yc() {
        if (this.dLb != null) {
            this.dLb.start();
        }
    }
    
    public g _() {
        return this.fMb;
    }
    
    public void p() {
        this.yGb.V();
        this.yGb.p();
        this.yGb.W();
    }
    
    public synchronized void e(final int n, final int n2) {
        this.yGb.e(n, n2);
    }
    
    public void fa(final int n) {
        this.ia(n);
        this.yGb.R(n);
    }
    
    public void ia(final int n) {
        if (n == 1) {
            this.hLb.setState(true);
        }
        else if (n == 2) {
            this.iLb.setState(true);
        }
        else if (n == 3) {
            this.jLb.setState(true);
        }
        else if (n == 4) {
            this.kLb.setState(true);
        }
        else if (n == 5) {
            this.lLb.setState(true);
        }
        this.DLb.setMultipleMode(n == 5);
        this.L(this.eLb);
        if (n != 5) {
            this.fLb.removeAll();
        }
        this.fMb._().b(this.fLb.b());
    }
    
    public void P(final int n) {
        this.yGb.P(n);
        if (n == 0) {
            this.uLb.setState(true);
        }
        else if (n == 1) {
            this.vLb.setState(true);
        }
        else if (n == 2) {
            this.wLb.setState(true);
        }
    }
    
    public void w(final String s) {
        this.yGb.w(s);
    }
    
    public void _(final Component component, final Color background) {
        component.setBackground(background);
        if (component instanceof c) {
            final Color[] _ = this.fMb._("ImageButtonBg");
            if (_ != null) {
                component.setBackground(_[0]);
            }
        }
        if (component instanceof Container) {
            final Component[] components = ((Container)component).getComponents();
            for (int i = 0; i < components.length; ++i) {
                if (components[i] instanceof c || components[i] instanceof TextField || components[i] instanceof Panel || components[i] instanceof List || components[i] instanceof Button || components[i] instanceof Choice) {
                    this._(components[i], background);
                }
            }
        }
    }
    
    public void m(final Component component, final Color background) {
        if (component instanceof c && background != null) {
            component.setBackground(background);
        }
        if (component instanceof Container) {
            final Component[] components = ((Container)component).getComponents();
            for (int i = 0; i < components.length; ++i) {
                if (components[i] instanceof c || components[i] instanceof TextField || components[i] instanceof Panel || components[i] instanceof List || components[i] instanceof Button || components[i] instanceof Choice) {
                    this.m(components[i], background);
                }
            }
        }
    }
    
    public void n(final Component component, final Color color) {
        if (component instanceof c && color != null) {
            ((c)component).a(color);
        }
        if (component instanceof Container) {
            final Component[] components = ((Container)component).getComponents();
            for (int i = 0; i < components.length; ++i) {
                if (components[i] instanceof c || components[i] instanceof TextField || components[i] instanceof Panel || components[i] instanceof List || components[i] instanceof Button || components[i] instanceof Choice) {
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
    
    public static String E() {
        return j("\u3a45\u3a46\u3a46\u3a47\u3a50\u3a12\u3a0d\u3a4e\u3a40\u3a44\u3a41\u3a45\u3a42\u3a0d\u3a40\u3a4c\u3a4e");
    }
    
    public static String F() {
        return j("\u3a45\u3a46\u3a46\u3a47\u3a50\u3a12\u3a0d\u3a4e\u3a40\u3a44\u3a41\u3a45\u3a42\u3a0d\u3a40\u3a4c\u3a4e");
    }
    
    public void setCursor(final Cursor cursor) {
        final Container parent = this.getParent();
        if (parent != null) {
            parent.setCursor(cursor);
        }
        if (parent != null && !(parent instanceof FnChartsApplet)) {
            this.SKb.setCursor(cursor);
        }
    }
    
    private int U() {
        if (this.hLb.getState()) {
            return 1;
        }
        if (this.iLb.getState()) {
            return 2;
        }
        if (this.jLb.getState()) {
            return 3;
        }
        if (this.kLb.getState()) {
            return 4;
        }
        if (this.hLb.getState()) {
            return 1;
        }
        return 5;
    }
    
    public void i(String substring) {
        if (substring == null || substring.equals("")) {
            this.dMb.select(this.fMb.b()._("cbNoIndicator"));
            int n = 0;
            if (this.fMb.b() != null) {
                ++n;
            }
            if (this.fMb._() != null) {
                ++n;
            }
            this.e(2 + n, this.U());
            this.cMb = null;
            this.fMb.i(null);
            return;
        }
        final String[] a = this.fMb.a();
        String s = null;
        if (substring.indexOf("(") != -1) {
            s = substring.substring(substring.indexOf("(") + 1);
            if (s.indexOf(")") != -1) {
                s = s.substring(0, s.indexOf(")"));
            }
            substring = substring.substring(0, substring.indexOf("("));
        }
        boolean b = false;
        for (int i = 0; i < a.length; ++i) {
            if (a[i].equals(substring)) {
                this.dMb.select(substring);
                this.fMb.i(substring);
                if (s != null && this.fMb.b() != null) {
                    this.fMb.b().B(s);
                }
                this.cMb = this.dMb;
                this.zc();
                b = true;
                break;
            }
        }
        if (!b) {
            this.dMb.select(this.fMb.b()._("cbNoIndicator"));
            this.fMb.i(null);
        }
        int n2 = 0;
        if (this.fMb.b() != null) {
            ++n2;
        }
        if (this.fMb._() != null) {
            ++n2;
        }
        this.e(2 + n2, this.U());
    }
    
    public void j(String substring) {
        if (substring == null || substring.equals("")) {
            this.eMb.select(this.fMb.b()._("cbNoIndicator"));
            int n = 0;
            if (this.fMb.b() != null) {
                ++n;
            }
            if (this.fMb._() != null) {
                ++n;
            }
            this.e(2 + n, this.U());
            this.fMb.j(null);
            return;
        }
        final String[] a = this.fMb.a();
        String s = null;
        if (substring.indexOf("(") != -1) {
            s = substring.substring(substring.indexOf("(") + 1);
            if (s.indexOf(")") != -1) {
                s = s.substring(0, s.indexOf(")"));
            }
            substring = substring.substring(0, substring.indexOf("("));
        }
        boolean b = false;
        for (int i = 0; i < a.length; ++i) {
            if (a[i].equals(substring)) {
                this.eMb.select(substring);
                this.fMb.j(substring);
                if (s != null && this.fMb._() != null) {
                    this.fMb._().B(s);
                }
                this.cMb = this.eMb;
                this.zc();
                b = true;
                break;
            }
        }
        if (!b) {
            this.eMb.select(this.fMb.b()._("cbNoIndicator"));
            this.fMb.j(null);
        }
        int n2 = 0;
        if (this.fMb.b() != null) {
            ++n2;
        }
        if (this.fMb._() != null) {
            ++n2;
        }
        this.e(2 + n2, this.U());
    }
    
    public void K(final String s) {
        this.fMb.F();
        final d d = new d(";");
        if (s != null) {
            d.e(s);
        }
        final Hashtable hashtable = new Hashtable<String, String>();
        for (int i = 0; i < d.z(); ++i) {
            String s2 = d._(i);
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
        final String[] array = new String[this.xLb.length];
        if (array == null) {
            return;
        }
        for (int j = 0; j < array.length; ++j) {
            array[j] = this.xLb[j].j();
        }
        for (int k = 0; k < array.length; ++k) {
            this.xLb[k].setState(hashtable.containsKey(array[k]));
            if (hashtable.containsKey(array[k])) {
                this.fMb.k(array[k]);
                if (this.fMb.b(array[k]) != null) {
                    this.fMb.b(array[k]).B(hashtable.get(array[k]));
                }
            }
            else {
                this.fMb.l(array[k]);
            }
        }
        this.Ac();
    }
    
    public void b(final String text) {
        this.info.setText(text);
        this.info.repaint();
    }
    
    private void O(String upperCase) {
        if (upperCase == null || upperCase.length() == 0) {
            return;
        }
        upperCase = upperCase.toUpperCase();
        boolean b = false;
        for (int i = 0; i < this.DLb.getItemCount(); ++i) {
            final int compareTo = upperCase.compareTo(this.DLb.getItem(i));
            if (compareTo < 0) {
                this.DLb.add(upperCase, i);
                b = true;
                break;
            }
            if (compareTo == 0) {
                b = true;
                break;
            }
        }
        if (!b) {
            this.DLb.add(upperCase);
        }
    }
    
    private void P(final String s) {
        if (s == null) {
            return;
        }
        for (int i = 0; i < this.DLb.getItemCount(); ++i) {
            if (s.equals(this.DLb.getItem(i))) {
                this.DLb.deselect(i);
                this.DLb.remove(i);
                break;
            }
        }
    }
    
    private void h(final String[] array) {
        for (int i = 0; i < array.length; ++i) {
            this.P(array[i]);
        }
    }
    
    private void a(final String[] array) {
        if (array != null) {
            for (int i = 0; i < array.length; ++i) {
                array[i] = array[i].toUpperCase();
                if (this.FLb.l(array[i])) {
                    this.O(array[i]);
                }
            }
        }
    }
    
    private synchronized void Bc() {
        this.dLb.a(true);
        new up(this).start();
    }
    
    private synchronized void J(final boolean b) {
        this.dLb.a(true);
        new vp(this, b).start();
    }
    
    private Container a() {
        Container container;
        for (container = this.getParent(); container != null && !(container instanceof FnChartsApplet) && !(container instanceof while); container = container.getParent()) {}
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
    
    public void ga(final int n) {
        this.fMb._().M(n);
        if (n == 0) {
            this.tLb.select(this.fMb.b()._("cbIntraday"));
        }
        else if (n == 1) {
            this.tLb.select(this.fMb.b()._("cbIntraday1s"));
        }
        else if (n == 2) {
            this.tLb.select(this.fMb.b()._("cbIntraday2s"));
        }
        else if (n == 3) {
            this.tLb.select(this.fMb.b()._("cbIntraday3s"));
        }
        else if (n == 4) {
            this.tLb.select(this.fMb.b()._("cbIntraday4s"));
        }
        else if (n == 5) {
            this.tLb.select(this.fMb.b()._("cbIntraday5s"));
        }
        else if (n == 10) {
            this.tLb.select(this.fMb.b()._("cbIntraday10s"));
        }
        else if (n == 15) {
            this.tLb.select(this.fMb.b()._("cbIntraday15s"));
        }
        else if (n == 20) {
            this.tLb.select(this.fMb.b()._("cbIntraday20s"));
        }
        else if (n == 30) {
            this.tLb.select(this.fMb.b()._("cbIntraday30s"));
        }
        else if (n == 60) {
            this.tLb.select(this.fMb.b()._("cbIntraday1"));
        }
        else if (n == 120) {
            this.tLb.select(this.fMb.b()._("cbIntraday2"));
        }
        else if (n == 180) {
            this.tLb.select(this.fMb.b()._("cbIntraday3"));
        }
        else if (n == 240) {
            this.tLb.select(this.fMb.b()._("cbIntraday4"));
        }
        else if (n == 300) {
            this.tLb.select(this.fMb.b()._("cbIntraday5"));
        }
        else if (n == 600) {
            this.tLb.select(this.fMb.b()._("cbIntraday10"));
        }
        else if (n == 900) {
            this.tLb.select(this.fMb.b()._("cbIntraday15"));
        }
        else if (n == 1200) {
            this.tLb.select(this.fMb.b()._("cbIntraday20"));
        }
        else if (n == 1800) {
            this.tLb.select(this.fMb.b()._("cbIntraday30"));
        }
        else if (n == 3600) {
            this.tLb.select(this.fMb.b()._("cbIntraday60"));
        }
        else if (n == 100001) {
            this.tLb.select(this.fMb.b()._("cbDaily"));
        }
        else if (n == 100002) {
            this.tLb.select(this.fMb.b()._("cbWeekly"));
        }
        else if (n == 100003) {
            this.tLb.select(this.fMb.b()._("cbMonthly"));
        }
    }
    
    private void L(String upperCase) {
        this.eLb = null;
        for (int i = 0; i < this.DLb.getItemCount(); ++i) {
            this.DLb.deselect(i);
        }
        if (upperCase != null) {
            upperCase = upperCase.toUpperCase();
        }
        for (int j = 0; j < this.DLb.getItemCount(); ++j) {
            if (this.DLb.getItem(j).equals(upperCase)) {
                this.DLb.select(j);
                this.eLb = this.DLb.getItem(j);
                this.ELb.setText(this.eLb);
                break;
            }
        }
        if (this.DLb.isMultipleMode()) {
            for (int k = 0; k < this.DLb.getItemCount(); ++k) {
                if (this.fLb.a(this.DLb.getItem(k))) {
                    this.DLb.select(k);
                }
            }
        }
    }
    
    private void N(String upperCase) {
        if (upperCase != null) {
            upperCase = upperCase.toUpperCase();
            this.FLb.setText(upperCase);
        }
        else {
            this.FLb.setText("");
        }
        this.gLb = upperCase;
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
    
    private void b(final boolean b, final boolean b2) {
        final wp wp = new wp(this);
        (this.fEb = new c("/images/" + this.mMb + "minimaxi.gif", "btnMaximize", false)).addActionListener(wp);
        this.BLb.add(this.fEb);
        (this.qLb = new c("/images/" + this.mMb + "usecache.gif", "bUseBrowserCache", false)).addActionListener(wp);
        this.BLb.add(this.qLb);
        (this.rLb = new c("/images/" + this.mMb + "autorefresh.gif", "bAutoRefreshActive", true)).addActionListener(wp);
        if (b) {
            this.BLb.add(this.rLb);
        }
        (this.sLb = new c("/images/" + this.mMb + "refreshbeep.gif", "bAutoRefreshBeep", true)).addActionListener(wp);
        if (b) {
            this.BLb.add(this.sLb);
        }
        this.BLb.add(new op(6));
        if (this._Mb != null) {
            this._Mb.addActionListener(wp);
        }
        if (this.aMb != null) {
            this.aMb.addActionListener(wp);
        }
        (this.VLb = new c("/images/" + this.mMb + "refresh.gif", "btnRefresh")).addActionListener(wp);
        this.BLb.add(this.VLb);
        (this.YLb = new c("/images/" + this.mMb + "moveleft.gif", "btnLeft")).addActionListener(wp);
        this.BLb.add(this.YLb);
        (this.XLb = new c("/images/" + this.mMb + "decreaserange.gif", "btnLess")).addActionListener(wp);
        this.BLb.add(this.XLb);
        (this.WLb = new c("/images/" + this.mMb + "increaserange.gif", "btnMore")).addActionListener(wp);
        this.BLb.add(this.WLb);
        (this.ZLb = new c("/images/" + this.mMb + "moveright.gif", "btnRight")).addActionListener(wp);
        this.BLb.add(this.ZLb);
        this.BLb.add(new op(6));
        final Uo uo = new Uo();
        (this.uLb = new c("/images/" + this.mMb + "line.gif", "btnLine", uo, true)).addActionListener(wp);
        this.BLb.add(this.uLb);
        (this.vLb = new c("/images/" + this.mMb + "ohlc.gif", "btnOhlc", uo, false)).addActionListener(wp);
        this.BLb.add(this.vLb);
        (this.wLb = new c("/images/" + this.mMb + "candle.gif", "btnCandle", uo, false)).addActionListener(wp);
        this.BLb.add(this.wLb);
        this.BLb.add(new op(6));
        final Uo uo2 = new Uo();
        (this.hLb = new c("/images/" + this.mMb + "scalelogcor.gif", "btnLogCor", uo2, true)).addActionListener(wp);
        this.BLb.add(this.hLb);
        (this.iLb = new c("/images/" + this.mMb + "scaleloguncor.gif", "btnLogUncor", uo2, false)).addActionListener(wp);
        this.BLb.add(this.iLb);
        (this.jLb = new c("/images/" + this.mMb + "scalelincor.gif", "btnLinCor", uo2, false)).addActionListener(wp);
        this.BLb.add(this.jLb);
        (this.kLb = new c("/images/" + this.mMb + "scalelinuncor.gif", "btnLinUncor", uo2, false)).addActionListener(wp);
        this.BLb.add(this.kLb);
        (this.lLb = new c("/images/" + this.mMb + "scalepercent.gif", "btnScalePercent", uo2, false)).addActionListener(wp);
        this.BLb.add(this.lLb);
        this.BLb.add(new op(6));
        final xp xp = new xp(this);
        (this.tLb = new Choice()).add(this.fMb.b()._("cbDaily"));
        this.tLb.add(this.fMb.b()._("cbWeekly"));
        this.tLb.add(this.fMb.b()._("cbMonthly"));
        this.tLb.add(this.fMb.b()._("cbIntraday"));
        this.tLb.add(this.fMb.b()._("cbIntraday1s"));
        this.tLb.add(this.fMb.b()._("cbIntraday2s"));
        this.tLb.add(this.fMb.b()._("cbIntraday3s"));
        this.tLb.add(this.fMb.b()._("cbIntraday4s"));
        this.tLb.add(this.fMb.b()._("cbIntraday5s"));
        this.tLb.add(this.fMb.b()._("cbIntraday10s"));
        this.tLb.add(this.fMb.b()._("cbIntraday15s"));
        this.tLb.add(this.fMb.b()._("cbIntraday20s"));
        this.tLb.add(this.fMb.b()._("cbIntraday30s"));
        this.tLb.add(this.fMb.b()._("cbIntraday1"));
        this.tLb.add(this.fMb.b()._("cbIntraday2"));
        this.tLb.add(this.fMb.b()._("cbIntraday3"));
        this.tLb.add(this.fMb.b()._("cbIntraday4"));
        this.tLb.add(this.fMb.b()._("cbIntraday5"));
        this.tLb.add(this.fMb.b()._("cbIntraday10"));
        this.tLb.add(this.fMb.b()._("cbIntraday15"));
        this.tLb.add(this.fMb.b()._("cbIntraday20"));
        this.tLb.add(this.fMb.b()._("cbIntraday30"));
        this.tLb.add(this.fMb.b()._("cbIntraday60"));
        this.tLb.select(this.fMb.b()._("cbDaily"));
        this.tLb.addItemListener(xp);
        this.BLb.add(this.tLb);
        this.BLb.add(new op(6));
        (this.oLb = new c("/images/" + this.mMb + "timeframe.gif", "bSetTimeframe")).addActionListener(wp);
        this.BLb.add(this.oLb);
        (this.nLb = new c("/images/" + this.mMb + "chartproperties.gif", "bChartProperties")).addActionListener(wp);
        this.BLb.add(this.nLb);
        if (b2) {
            this.yGb.Q(0);
        }
        else {
            (this.pLb = new c("/images/" + this.mMb + "volume.gif", "bVolumeVisible", true)).addActionListener(wp);
            this.BLb.add(this.pLb);
            if (this.pLb.getState()) {
                this.yGb.Q(1);
            }
            else {
                this.yGb.Q(2);
            }
        }
        (this.mLb = new c("/images/" + this.mMb + "stocklist.gif", "bStockListVisible", this.ULb)).addActionListener(wp);
        this.BLb.add(this.mLb);
        final yp yp = new yp(this);
        (this.dMb = new Choice()).addItemListener(yp);
        this.dMb.addFocusListener(yp);
        this.dMb.addMouseListener(yp);
        (this.eMb = new Choice()).addItemListener(yp);
        this.eMb.addFocusListener(yp);
        this.eMb.addMouseListener(yp);
        this.Cc();
        this.CLb.add(this.dMb);
        this.CLb.add(this.eMb);
        this.cMb = this.dMb;
        this.JLb = new PopupMenu();
        (this.KLb = new MenuItem(this.fMb.b()._("menuDefineIndicator"))).addActionListener(yp);
        this.JLb.add(this.KLb);
        (this.LLb = new MenuItem(this.fMb.b()._("menuEditIndicator"))).addActionListener(yp);
        this.JLb.add(this.LLb);
        (this.MLb = new MenuItem(this.fMb.b()._("menuDeleteIndicator"))).addActionListener(yp);
        this.JLb.add(this.MLb);
        this.dMb.add(this.JLb);
        this.eMb.add(this.JLb);
        this.CLb.add(new op(2));
        final zp zp = new zp(this);
        this.DDb = new jp[3];
        for (int i = 0; i < this.DDb.length; ++i) {
            (this.DDb[i] = new jp(3, true)).addKeyListener(zp);
            this.DDb[i].addFocusListener(zp);
            this.CLb.add(this.DDb[i]);
        }
        this.zc();
        final String[] l = this.fMb.l();
        this.xLb = new c[l.length];
        this.bMb = new jp[l.length];
        final Ap ap = new Ap(this);
        this.CLb.add(new op(6));
        for (int j = 0; j < l.length; ++j) {
            this.CLb.add(this.xLb[j] = new c("/images/" + this.mMb + l[j].toLowerCase() + ".gif", l[j], false));
            this.xLb[j].addActionListener(ap);
            this.bMb[j] = null;
            if (this.fMb.b(l[j]).S() > 0) {
                this.CLb.add(new op(2));
                this.bMb[j] = new jp(3, true);
                this.CLb.add(this.bMb[j]);
                this.bMb[j].addKeyListener(zp);
                this.bMb[j].addFocusListener(zp);
            }
            this.CLb.add(new op(6));
        }
        this.Ac();
    }
    
    private void vc() {
        this.dMb.setVisible(false);
        this.dMb.select(0);
        this.dMb.setEnabled(false);
        while (this.dMb.getItemCount() > 1) {
            this.dMb.remove(this.dMb.getItemCount() - 1);
        }
        this.eMb.setVisible(false);
        this.eMb.select(0);
        this.eMb.setEnabled(false);
        while (this.eMb.getItemCount() > 1) {
            this.eMb.remove(this.eMb.getItemCount() - 1);
        }
        this.Cc();
        this.dMb.setVisible(true);
        this.dMb.setEnabled(true);
        this.eMb.setVisible(true);
        this.eMb.setEnabled(true);
        this.dMb.invalidate();
        this.eMb.invalidate();
        this.CLb.invalidate();
        this.QLb.validate();
    }
    
    private void k(final String s, final String s2) {
        if (s != null && s2 != null && s.equals(s2) && this.fMb.a(s) != null) {
            return;
        }
        this.dMb.setVisible(false);
        this.eMb.setVisible(false);
        this.dMb.setEnabled(false);
        this.eMb.setEnabled(false);
        this.dMb.select(0);
        this.eMb.select(0);
        if (s != null && this.fMb.a(s) == null) {
            this.dMb.remove(s);
            this.eMb.remove(s);
        }
        this.dMb.select(0);
        this.eMb.select(0);
        if (s2 != null) {
            boolean b = false;
            for (int i = 0; i < this.dMb.getItemCount(); ++i) {
                if (s2.equals(this.dMb.getItem(i))) {
                    b = true;
                    break;
                }
            }
            if (!b) {
                boolean b2 = false;
                for (int j = 0; j < this.dMb.getItemCount(); ++j) {
                    if (s2.compareTo(this.dMb.getItem(j)) < 0) {
                        this.dMb.insert(s2, j);
                        b2 = true;
                        break;
                    }
                }
                if (!b2) {
                    this.dMb.add(s2);
                }
            }
            final o b3 = this.fMb.b();
            if (b3 != null) {
                this.dMb.select(b3.toString());
            }
            boolean b4 = false;
            this.eMb.select(0);
            for (int k = 0; k < this.eMb.getItemCount(); ++k) {
                if (s2.equals(this.eMb.getItem(k))) {
                    b4 = true;
                    break;
                }
            }
            if (!b4) {
                boolean b5 = false;
                for (int l = 0; l < this.eMb.getItemCount(); ++l) {
                    if (s2.compareTo(this.eMb.getItem(l)) < 0) {
                        this.eMb.insert(s2, l);
                        b5 = true;
                        break;
                    }
                }
                if (!b5) {
                    this.eMb.add(s2);
                }
            }
            final o _ = this.fMb._();
            if (_ != null) {
                this.eMb.select(_.toString());
            }
        }
        this.dMb.setEnabled(true);
        this.eMb.setEnabled(true);
        this.dMb.setVisible(true);
        this.eMb.setVisible(true);
        this.dMb.invalidate();
        this.eMb.invalidate();
        this.CLb.invalidate();
        this.QLb.validate();
    }
    
    private void Q(final String s) {
        this.dMb.setVisible(false);
        this.eMb.setVisible(false);
        this.dMb.setEnabled(false);
        this.eMb.setEnabled(false);
        this.dMb.select(0);
        this.eMb.select(0);
        this.dMb.remove(s);
        this.eMb.remove(s);
        final o b = this.fMb.b();
        if (b != null) {
            this.dMb.select(b.toString());
        }
        else {
            this.dMb.select(0);
        }
        final o _ = this.fMb._();
        if (_ != null) {
            this.eMb.select(_.toString());
        }
        else {
            this.eMb.select(0);
        }
        this.dMb.setEnabled(true);
        this.eMb.setEnabled(true);
        this.dMb.setVisible(true);
        this.eMb.setVisible(true);
        this.dMb.invalidate();
        this.eMb.invalidate();
        this.CLb.invalidate();
        this.QLb.validate();
    }
    
    private void Cc() {
        String string = null;
        final o b = this.fMb.b();
        if (b != null) {
            string = b.toString();
        }
        String string2 = null;
        final o _ = this.fMb._();
        if (_ != null) {
            string2 = _.toString();
        }
        final String[] a = this.fMb.a();
        i._(a);
        for (int i = 0; i < a.length; ++i) {
            this.dMb.add(a[i]);
        }
        if (!this.dMb.getItem(0).equals(this.fMb.b()._("cbNoIndicator"))) {
            this.dMb.insert(this.fMb.b()._("cbNoIndicator"), 0);
        }
        for (int j = 0; j < a.length; ++j) {
            this.eMb.add(a[j]);
        }
        if (!this.eMb.getItem(0).equals(this.fMb.b()._("cbNoIndicator"))) {
            this.eMb.insert(this.fMb.b()._("cbNoIndicator"), 0);
        }
        this.i(string);
        this.j(string2);
    }
    
    private void zc() {
        o o = null;
        if (this.cMb == this.dMb) {
            o = this.fMb.b();
        }
        else if (this.cMb == this.eMb) {
            o = this.fMb._();
        }
        for (int i = 0; i < this.DDb.length; ++i) {
            this.DDb[i].setText("");
            this.DDb[i].setEnabled(false);
        }
        if (o == null || this.cMb.getSelectedItem().equals(this.fMb.b()._("cbNoIndicator"))) {
            return;
        }
        for (int j = 0; j < o.S(); ++j) {
            this.DDb[j].setText(String.valueOf(o.getParam(j)));
            this.DDb[j].setEnabled(true);
        }
    }
    
    private void I(final boolean b) {
        for (int i = 0; i < this.xLb.length; ++i) {
            this.xLb[i].setEnabled(b);
        }
        for (int j = 0; j < this.bMb.length; ++j) {
            if (this.bMb[j] != null) {
                this.bMb[j].setEnabled(b);
            }
        }
    }
    
    private void Ac() {
        final String[] array = new String[this.xLb.length];
        if (array == null) {
            return;
        }
        for (int i = 0; i < array.length; ++i) {
            array[i] = this.xLb[i].j();
        }
        for (int j = 0; j < this.bMb.length; ++j) {
            if (this.bMb[j] != null) {
                this.bMb[j].setText("");
                this.bMb[j].setEnabled(false);
            }
        }
        if (array == null) {
            return;
        }
        for (int k = 0; k < this.bMb.length; ++k) {
            final o b = this.fMb.b(array[k]);
            if (b != null && b.S() > 0 && this.bMb[k] != null) {
                this.bMb[k].setText(String.valueOf(b.getParam(0)));
                this.bMb[k].setEnabled(true);
            }
        }
    }
    
    private void Dc() {
        o o = null;
        if (this.cMb == this.dMb) {
            o = this.fMb.b();
        }
        else if (this.cMb == this.eMb) {
            o = this.fMb._();
        }
        if (o == null || this.cMb.getSelectedItem().equals(this.fMb.b()._("cbNoIndicator"))) {
            return;
        }
        for (int n = 0; n < this.DDb.length && n < o.S(); ++n) {
            int n2;
            try {
                n2 = Integer.parseInt(this.DDb[n].getText());
            }
            catch (NumberFormatException ex) {
                n2 = o.getParam(n);
            }
            if (n2 < 2 || n2 > 999) {
                n2 = o.getParam(n);
            }
            this.DDb[n].setText(String.valueOf(n2));
            o.j(n, n2);
        }
    }
    
    private void Ec() {
        final String[] array = new String[this.xLb.length];
        if (array == null) {
            return;
        }
        for (int i = 0; i < array.length; ++i) {
            array[i] = this.xLb[i].j();
        }
        for (int j = 0; j < this.bMb.length; ++j) {
            if (this.bMb[j] != null) {
                final o b = this.fMb.b(array[j]);
                int n;
                try {
                    n = Integer.parseInt(this.bMb[j].getText());
                }
                catch (NumberFormatException ex) {
                    n = b.getParam(0);
                }
                if (n < 2 || n > 999) {
                    n = b.getParam(0);
                }
                this.bMb[j].setText(String.valueOf(n));
                b.j(0, n);
            }
        }
    }
    
    public void o(final int n) {
        this.PLb.ba(Math.max(50, n));
    }
    
    public void K(final boolean hMb) {
        this.hMb = hMb;
    }
    
    public static String j(final String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ '\u3a23');
        }
        return new String(array);
    }
    
    public void _(final Component component) {
        if (component instanceof c) {
            ((c)component).dispose();
        }
        if (component instanceof Container) {
            final Component[] components = ((Container)component).getComponents();
            for (int i = 0; i < components.length; ++i) {
                this._(components[i]);
            }
        }
    }
    
    static mp b(final var var) {
        return var.nMb;
    }
    
    static List a(final var var) {
        return var.DLb;
    }
    
    static g _(final var var) {
        return var.fMb;
    }
    
    static boolean b(final var var) {
        return var.gMb;
    }
    
    static Choice _(final var var) {
        return var.dMb;
    }
    
    static Choice a(final var var) {
        return var.eMb;
    }
    
    static Label a(final var var) {
        return var.info;
    }
    
    static lp b(final var var) {
        return var.yGb;
    }
    
    static gp _(final var var) {
        return var.dLb;
    }
    
    static c a(final var var) {
        return var.rLb;
    }
    
    static void b(final var var) {
        var.zc();
    }
    
    static void _(final var var) {
        var.Ac();
    }
    
    static void a(final var var) {
        var.Dc();
    }
    
    static void l(final var var) {
        var.Ec();
    }
    
    static Choice f(final var var) {
        return var.cMb;
    }
    
    static Frame _(final var var) {
        return var.a();
    }
    
    static void c(final var var, final String s) {
        var.Q(s);
    }
    
    static void a(final var var, final String s, final String s2) {
        var.k(s, s2);
    }
    
    static int b(final var var) {
        return var.U();
    }
    
    static Choice _(final var var, final Choice cMb) {
        return var.cMb = cMb;
    }
    
    static MenuItem _(final var var) {
        return var.MLb;
    }
    
    static void m(final var var) {
        var.Bc();
    }
    
    static MenuItem a(final var var) {
        return var.KLb;
    }
    
    static void _(final var var, final boolean b) {
        var.J(b);
    }
    
    static MenuItem b(final var var) {
        return var.LLb;
    }
    
    static PopupMenu b(final var var) {
        return var.JLb;
    }
    
    static Choice b(final var var) {
        return var.tLb;
    }
    
    static hp _(final var var) {
        return var.fLb;
    }
    
    static String a(final var var) {
        return var.eLb;
    }
    
    static MenuItem j(final var var) {
        return var.HLb;
    }
    
    static void a(final var var, final String[] array) {
        var.h(array);
    }
    
    static MenuItem k(final var var) {
        return var.ILb;
    }
    
    static String a(final var var, final String eLb) {
        return var.eLb = eLb;
    }
    
    static jp _(final var var) {
        return var.ELb;
    }
    
    static void b(final var var, final String s) {
        var.O(s);
    }
    
    static void _(final var var, final String s) {
        var.L(s);
    }
    
    static jp a(final var var) {
        return var.FLb;
    }
    
    static void a(final var var, final String s) {
        var.N(s);
    }
    
    static MenuItem l(final var var) {
        return var.GLb;
    }
    
    static PopupMenu _(final var var) {
        return var.NLb;
    }
    
    static c V(final var var) {
        return var.zLb;
    }
    
    static FnChartsApplet a(final var var) {
        return var.SKb;
    }
    
    static c W(final var var) {
        return var.yLb;
    }
    
    static c X(final var var) {
        return var.ALb;
    }
    
    static Panel a(final var var) {
        return var.QLb;
    }
    
    static Vo _(final var var) {
        return var.PLb;
    }
    
    static boolean _(final var var) {
        return var.ULb;
    }
    
    static Container b(final var var) {
        return var.a();
    }
    
    static Button b(final var var) {
        return var._Mb;
    }
    
    static Button _(final var var) {
        return var.aMb;
    }
    
    static c _(final var var) {
        return var.WLb;
    }
    
    static c g(final var var) {
        return var.XLb;
    }
    
    static c h(final var var) {
        return var.YLb;
    }
    
    static c i(final var var) {
        return var.ZLb;
    }
    
    static c j(final var var) {
        return var.uLb;
    }
    
    static c k(final var var) {
        return var.vLb;
    }
    
    static c l(final var var) {
        return var.wLb;
    }
    
    static c m(final var var) {
        return var.oLb;
    }
    
    static c n(final var var) {
        return var.nLb;
    }
    
    static String[] _(final var var) {
        return var.oMb;
    }
    
    static c c(final var var) {
        return var.mLb;
    }
    
    static boolean b(final var var, final boolean uLb) {
        return var.ULb = uLb;
    }
    
    static c d(final var var) {
        return var.pLb;
    }
    
    static c b(final var var) {
        return var.qLb;
    }
    
    static c e(final var var) {
        return var.sLb;
    }
    
    static c f(final var var) {
        return var.hLb;
    }
    
    static c Q(final var var) {
        return var.iLb;
    }
    
    static c R(final var var) {
        return var.jLb;
    }
    
    static c S(final var var) {
        return var.kLb;
    }
    
    static c T(final var var) {
        return var.lLb;
    }
    
    static void a(final var var, final boolean b) {
        var.I(b);
    }
    
    static c U(final var var) {
        return var.VLb;
    }
    
    static String b(final var var) {
        return var.gLb;
    }
}
