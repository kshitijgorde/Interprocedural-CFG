import java.util.StringTokenizer;
import java.util.Vector;
import java.util.Date;
import java.awt.Choice;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.FlowLayout;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Container;
import java.util.Enumeration;
import java.util.Hashtable;
import java.awt.Font;
import java.awt.Color;
import java.awt.List;
import java.awt.TextField;
import java.awt.Label;
import java.awt.CardLayout;
import java.awt.Panel;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class ZoomChart extends Applet implements Runnable
{
    private Thread m_RefreshApppletThread;
    private final int REFRESH_INTERVAL = 2500;
    private int m_RefreshInterval;
    private final int MAX_RECORDS = 400;
    private Cache m_Cache;
    private Panel m_PHolder;
    private CardLayout m_CardLayout;
    private final String CHART_TAB = "CHART_TAB";
    private final String SETTINGS_TAB = "SETTINGS_TAB";
    private final String DRAW_BTN = "DRAW_BTN";
    private final String COPYRIGHT = "http://www.uralbeacon.co.uk";
    private boolean m_NoCopyright;
    private Button2 m_Chart_Tab;
    private Button2 m_Settings_Tab;
    private Button2 m_Draw_Btn;
    private WaitAnimator m_WaitAnimator;
    private ListView m_ListView;
    private Label m_Start_Date_Label;
    private Label m_End_Date_Label;
    private Label m_Date_Format_Label;
    private Label m_Group_Label;
    private TextField m_Start_Date_TF;
    private TextField m_End_Date_TF;
    private String m_Timeout_Message;
    private String m_Wait_Message;
    private String m_Empty_Message;
    private JChart m_JChart;
    private Ticker m_Ticker;
    private List m_List;
    private String m_Data_Source;
    private Color m_Top_BG_Color;
    private Color m_Chart_BG_Color;
    private Color m_Ticker_BG_Color;
    private Color m_Text_Color;
    private Color m_Tab_BG_Color;
    private Color m_Tab_Line_Color;
    private Font m_Message_Font;
    private Font m_Chart_Font;
    private Font m_Ticker_Font;
    private int m_Show_Group_Int;
    private String m_First_Shown_Group;
    private Hashtable m_GroupHash;
    private String[] m_SortedName;
    private Hashtable m_Symbol_Link_Hash;
    private int m_Link_Symbols_Int;
    private final String EUROPEAN_DATE_FORMAT = "European";
    private String m_Date_Format;
    private Hashtable m_Group_Limit_Hash;
    private Hashtable m_DateRange_Hash;
    private Choice2 m_DRChoice;
    
    public void stop() {
    }
    
    private void doOnDateRangeChange(final String s) {
        if (this.m_DateRange_Hash.size() == 0) {
            return;
        }
        final Enumeration<Object> keys = (Enumeration<Object>)this.m_DateRange_Hash.keys();
        while (keys.hasMoreElements()) {
            final String[] array = this.m_DateRange_Hash.get(keys.nextElement());
            final String s2 = array[0];
            final String text = array[1];
            final String text2 = array[2];
            if (s.equals(s2)) {
                try {
                    this.m_Start_Date_TF.setText(this.formatDate(HTTPDataSource.getDateFromString(text)));
                }
                catch (Exception ex) {
                    this.m_Start_Date_TF.setText(text);
                }
                try {
                    this.m_End_Date_TF.setText(this.formatDate(HTTPDataSource.getDateFromString(text2)));
                }
                catch (Exception ex2) {
                    this.m_End_Date_TF.setText(text2);
                }
            }
        }
    }
    
    private void selectChartTab(final boolean pressedState) {
        if (pressedState) {
            this.m_CardLayout.show(this.m_PHolder, "CHART_TAB");
        }
        else {
            this.m_CardLayout.show(this.m_PHolder, "SETTINGS_TAB");
            this.m_List.makeVisible(this.m_List.getSelectedIndex());
        }
        this.m_Chart_Tab.setPressedState(pressedState);
        this.m_Settings_Tab.setPressedState(!pressedState);
        if (pressedState) {
            this.m_Chart_Tab.disable();
            this.m_Settings_Tab.enable();
            return;
        }
        this.m_Settings_Tab.disable();
        this.m_Chart_Tab.enable();
    }
    
    private String ReadString(final String s, final String s2) {
        String s3;
        try {
            final String parameter = this.getParameter(s);
            parameter.trim();
            s3 = ((parameter == "") ? s2 : parameter);
        }
        catch (Exception ex) {
            s3 = s2;
        }
        return s3;
    }
    
    private void saveDateRange(final String s, final String s2) {
        if (this.m_DRChoice.countItems() == 0) {
            return;
        }
        final Enumeration<Object> keys = this.m_DateRange_Hash.keys();
        while (keys.hasMoreElements()) {
            final String[] array = this.m_DateRange_Hash.get(keys.nextElement());
            final String s3 = array[0];
            if (s3.equals(this.m_DRChoice.getItem(0))) {
                array[1] = this.m_Start_Date_TF.getText();
                array[2] = this.m_End_Date_TF.getText();
                this.m_DateRange_Hash.put(s3, array);
            }
        }
    }
    
    private void preloadCache(final int n) {
        Hashtable<String, String> hashtable = null;
        if (this.ReadInteger("Preload_Data", 0) == 1) {
            hashtable = new Hashtable<String, String>();
        }
        final Enumeration<Object> keys = this.m_GroupHash.keys();
        while (keys.hasMoreElements()) {
            final Hashtable<String, String> hashtable2 = this.m_GroupHash.get(keys.nextElement());
            for (int i = 0; i < n; ++i) {
                final String readString = this.ReadString("Init_Symbol_" + new Integer(i).toString(), "");
                if (hashtable2.containsKey(readString)) {
                    hashtable2.put(readString, "1");
                    if (hashtable != null) {
                        hashtable.put(readString, this.ReadString("Init_Symbol_Data_" + new Integer(i).toString(), ""));
                    }
                }
            }
        }
        this.updateCache(hashtable);
    }
    
    private void layoutControls2() {
        this.setBackground(this.m_Top_BG_Color);
        this.setLayout(new BorderLayout());
        final Panel panel = new Panel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(this.m_Top_BG_Color);
        final Panel panel2 = new Panel();
        panel2.setBackground(this.m_Top_BG_Color);
        panel2.setLayout(new BorderLayout());
        final Panel2 panel3 = new Panel2();
        panel3.setBackground(this.m_Top_BG_Color);
        panel3.resize(2, 10);
        final Panel2 panel4 = new Panel2();
        panel4.setBackground(this.m_Top_BG_Color);
        panel4.resize(2, 10);
        final Panel2 panel5 = new Panel2();
        panel5.setBackground(this.m_Top_BG_Color);
        panel5.resize(10, 4);
        final Panel2 panel6 = new Panel2();
        panel6.setBackground(this.m_Top_BG_Color);
        panel6.resize(10, 2);
        final Panel panel7 = new Panel();
        panel7.setBackground(this.m_Top_BG_Color);
        panel7.setLayout(new BorderLayout());
        panel2.add("South", panel5);
        panel2.add("North", panel6);
        panel2.add("West", panel3);
        panel2.add("East", panel4);
        panel2.add("Center", panel7);
        final Panel2 panel8 = new Panel2();
        panel8.setBackground(this.m_Tab_BG_Color);
        panel8.resize(1, 10);
        final Panel2 panel9 = new Panel2();
        panel9.setBackground(this.m_Tab_BG_Color);
        panel9.resize(1, 10);
        final Panel2 panel10 = new Panel2();
        panel10.setBackground(this.m_Tab_BG_Color);
        panel10.resize(10, 1);
        final Panel2 panel11 = new Panel2();
        panel11.setBackground(this.m_Tab_BG_Color);
        panel11.resize(10, 1);
        final Panel panel12 = new Panel();
        panel12.setBackground(this.m_Top_BG_Color);
        panel12.setLayout(new BorderLayout());
        this.m_Ticker.setBackground(this.m_Ticker_BG_Color);
        this.m_Ticker.setFont(this.m_Ticker_Font);
        this.m_Ticker.resize(10, 20);
        this.m_Ticker.reset();
        this.m_JChart.setBackground(this.m_Chart_BG_Color);
        this.m_JChart.setFont(this.m_Chart_Font);
        this.m_JChart.setForeground(this.m_Text_Color);
        panel12.add("North", this.m_Ticker);
        panel12.add("Center", this.m_JChart);
        final Panel panel13 = new Panel();
        panel13.setBackground(this.m_Top_BG_Color);
        panel13.setLayout(new BorderLayout());
        final Panel2 panel14 = new Panel2();
        panel14.setBackground(this.m_Top_BG_Color);
        panel14.resize(10, 10);
        final Panel2 panel15 = new Panel2();
        panel15.setBackground(this.m_Top_BG_Color);
        panel15.resize(10, 10);
        final Panel2 panel16 = new Panel2();
        panel16.setBackground(this.m_Top_BG_Color);
        panel16.resize(10, 10);
        final Panel2 panel17 = new Panel2();
        panel17.setBackground(this.m_Top_BG_Color);
        panel17.resize(10, 10);
        final Panel panel18 = new Panel();
        panel18.setBackground(this.m_Top_BG_Color);
        panel18.setLayout(new BorderLayout());
        final Panel panel19 = new Panel();
        panel19.setBackground(this.m_Top_BG_Color);
        panel19.setLayout(new BorderLayout());
        final Panel panel20 = new Panel();
        panel20.setBackground(this.m_Top_BG_Color);
        panel20.setLayout(new BorderLayout());
        final Panel panel21 = new Panel();
        panel21.setBackground(this.m_Top_BG_Color);
        panel21.setLayout(new BorderLayout());
        this.m_ListView.setBackground(Color.white);
        this.m_ListView.setForeground(Color.black);
        final Panel panel22 = new Panel();
        panel22.setBackground(this.m_Top_BG_Color);
        panel22.setLayout(new FlowLayout(0, 0, 2));
        panel22.add(this.m_DRChoice);
        panel22.add(this.m_Start_Date_Label);
        panel22.add(this.m_Start_Date_TF);
        panel22.add(this.m_End_Date_Label);
        panel22.add(this.m_End_Date_TF);
        panel22.add(this.m_Date_Format_Label);
        final Panel2 panel23 = new Panel2();
        panel23.setBackground(this.m_Top_BG_Color);
        panel23.resize(10, 10);
        final Panel panel24 = new Panel();
        panel24.setBackground(this.m_Top_BG_Color);
        panel24.setLayout(new BorderLayout());
        panel24.add("North", panel23);
        panel24.add("Center", panel22);
        final Panel panel25 = new Panel();
        panel25.setBackground(this.m_Top_BG_Color);
        panel25.setLayout(new BorderLayout());
        final String[] array = new String[this.m_GroupHash.size()];
        final Enumeration<String> keys = this.m_GroupHash.keys();
        int n = 0;
        while (keys.hasMoreElements()) {
            array[n++] = keys.nextElement();
        }
        ListView.sort(true, array);
        int n2 = 0;
        for (int i = 0; i < array.length; ++i) {
            this.m_List.addItem(array[i]);
            if (this.m_First_Shown_Group.equals(array[i])) {
                n2 = i;
            }
        }
        this.m_List.select(n2);
        this.m_List.makeVisible(n2);
        final Panel panel26 = new Panel();
        panel26.setBackground(this.m_Top_BG_Color);
        panel26.setLayout(new BorderLayout());
        panel26.show(this.m_Show_Group_Int == 1);
        final Panel2 panel27 = new Panel2();
        panel27.setBackground(this.m_Tab_BG_Color);
        panel27.resize(2, 1);
        panel26.add("North", panel27);
        panel26.add("South", this.m_List);
        panel26.add("Center", this.m_Group_Label);
        panel25.add("North", panel26);
        panel25.add("Center", this.m_ListView);
        panel21.add("South", panel24);
        panel21.add("Center", panel25);
        panel18.add("West", panel19);
        panel18.add("East", panel20);
        panel18.add("Center", panel21);
        panel13.add("South", panel16);
        panel13.add("North", panel17);
        panel13.add("West", panel14);
        panel13.add("East", panel15);
        panel13.add("Center", panel18);
        (this.m_PHolder = new Panel()).setLayout(this.m_CardLayout);
        this.m_PHolder.add("CHART_TAB", panel12);
        this.m_PHolder.add("SETTINGS_TAB", panel13);
        panel7.add("South", panel10);
        panel7.add("North", panel11);
        panel7.add("West", panel8);
        panel7.add("East", panel9);
        panel7.add("Center", this.m_PHolder);
        this.add("South", panel);
        this.add("Center", panel2);
        final Panel2 panel28 = new Panel2();
        panel28.setBackground(this.m_Top_BG_Color);
        panel28.resize(2, 10);
        final Panel2 panel29 = new Panel2();
        panel29.setBackground(this.m_Top_BG_Color);
        panel29.resize(2, 10);
        final Panel2 panel30 = new Panel2();
        panel30.setBackground(this.m_Top_BG_Color);
        panel30.resize(10, 1);
        final Panel panel31 = new Panel();
        panel31.setBackground(this.m_Tab_BG_Color);
        panel31.setLayout(new BorderLayout());
        panel.add("West", panel28);
        panel.add("East", panel29);
        panel.add("South", panel30);
        panel.add("Center", panel31);
        final Panel2 panel32 = new Panel2();
        panel32.setBackground(this.m_Tab_BG_Color);
        panel32.resize(10, 1);
        final Panel2 panel33 = new Panel2();
        panel33.setBackground(this.m_Tab_BG_Color);
        panel33.resize(10, 10);
        panel33.setLayout(new BorderLayout());
        final Panel panel34 = new Panel();
        panel34.setBackground(this.m_Tab_BG_Color);
        panel34.setLayout(new BorderLayout());
        panel31.add("South", panel32);
        panel31.add("West", panel33);
        panel31.add("Center", panel34);
        final Panel2 panel35 = new Panel2();
        panel35.setBackground(this.m_Tab_Line_Color);
        panel35.resize(10, 1);
        panel33.add("North", panel35);
        final Panel panel36 = new Panel();
        panel36.setBackground(this.m_Tab_BG_Color);
        panel36.setLayout(new BorderLayout());
        panel36.add("West", this.m_Chart_Tab);
        panel36.add("Center", this.m_Settings_Tab);
        final Panel panel37 = new Panel();
        panel37.setBackground(this.m_Tab_BG_Color);
        panel37.setLayout(new BorderLayout());
        final Panel2 panel38 = new Panel2();
        panel38.setBackground(this.m_Tab_Line_Color);
        panel38.resize(10, 1);
        final Panel panel39 = new Panel();
        panel39.setBackground(this.m_Tab_BG_Color);
        panel39.setLayout(new FlowLayout(2, 5, 2));
        this.m_WaitAnimator.setBackground(this.m_Top_BG_Color);
        this.m_WaitAnimator.setForeground(this.m_Top_BG_Color);
        this.m_WaitAnimator.resize(120, 20);
        panel39.add(this.m_WaitAnimator);
        panel39.add(this.m_Draw_Btn);
        panel37.add("North", panel38);
        panel37.add("Center", panel39);
        panel34.add("West", panel36);
        panel34.add("Center", panel37);
        this.m_DRChoice.resize(100, 10);
        this.m_CardLayout.show(this.m_PHolder, "CHART_TAB");
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    private boolean isValidDate(final TextField textField) {
        try {
            textField.setText(this.formatDate(this.strToDate(textField.getText())));
            textField.setBackground(Color.white);
        }
        catch (Exception ex) {
            textField.setBackground(Color.red);
            return false;
        }
        return true;
    }
    
    public void start() {
    }
    
    private void resetChartAndTicker() {
        this.m_JChart.clearCurves();
        final Curve[] curveArr = this.m_Cache.getCurveArr();
        this.m_Ticker.removeAllCaptions();
        if (curveArr == null) {
            if (this.getSelectedNameCount() > 0) {
                this.m_JChart.showMessage(this.m_Timeout_Message, this.m_Message_Font);
            }
            else {
                this.m_JChart.showMessage(this.m_Empty_Message, this.m_Message_Font);
            }
        }
        else {
            for (int i = 0; i < curveArr.length; ++i) {
                final Curve curve = curveArr[i];
                this.m_Ticker.addCaption(curve.getName(), curve.getColor());
                this.m_JChart.addCurve(curve);
            }
            this.m_JChart.setZoomRange(this.m_Cache.getStartDate(), this.m_Cache.getEndDate());
            this.m_JChart.reset();
        }
        this.m_Ticker.reset();
    }
    
    private int getGroupLimit(final String s) {
        if (this.m_Group_Limit_Hash.containsKey(s)) {
            return this.m_Group_Limit_Hash.get(s);
        }
        return this.m_GroupHash.get(s).size();
    }
    
    private boolean doAction2(final Event event, final Object o) {
        if (event.target instanceof JChart) {
            this.updateCache(null);
            this.draw(this.m_JChart.getZoomStartDate(), this.m_JChart.getZoomEndDate());
        }
        else {
            if (event.target instanceof Cache) {
                try {
                    this.enableControls(true);
                    this.resetChartAndTicker();
                    return true;
                }
                finally {
                    this.m_WaitAnimator.stop();
                }
            }
            if (event.target instanceof ListView) {
                if (this.m_Link_Symbols_Int == 1) {
                    this.doOnListViewItemSelect();
                }
            }
            else if (event.target instanceof Choice2) {
                this.doOnDateRangeChange((String)event.arg);
            }
            else {
                final String s = (String)event.arg;
                if (s == "CHART_TAB") {
                    this.selectChartTab(true);
                }
                else if (s == "SETTINGS_TAB") {
                    this.selectChartTab(false);
                }
                else if (s == "DRAW_BTN") {
                    if (this.isValidDate(this.m_Start_Date_TF) && this.isValidDate(this.m_End_Date_TF)) {
                        this.saveDateRange(this.m_Start_Date_TF.getText(), this.m_End_Date_TF.getText());
                        this.selectChartTab(true);
                        this.updateCache(null);
                        this.draw(this.strToDate(this.m_Start_Date_TF.getText()), this.strToDate(this.m_End_Date_TF.getText()));
                    }
                    else {
                        this.selectChartTab(false);
                    }
                }
            }
        }
        return true;
    }
    
    private void refreshAll(final Container container) {
        container.repaint();
        final Component[] components = container.getComponents();
        for (int i = 0; i < components.length; ++i) {
            if (components[i] instanceof Container) {
                this.refreshAll((Container)components[i]);
            }
            else if (!(components[i] instanceof Choice) && !(components[i] instanceof List) && !(components[i] instanceof TextField) && !(components[i] instanceof Label)) {
                if (components[i] instanceof JChart) {
                    if (!this.m_JChart.isBusy()) {
                        components[i].repaint();
                    }
                }
                else {
                    components[i].repaint();
                }
            }
        }
    }
    
    private void enableControls(final boolean b) {
        if (b) {
            this.m_Draw_Btn.enable();
            this.m_JChart.enable();
            this.m_Ticker.enable();
            return;
        }
        this.m_Draw_Btn.disable();
        this.m_JChart.disable();
        this.m_Ticker.disable();
    }
    
    public ZoomChart() {
        (this.m_RefreshApppletThread = new Thread(this)).setPriority(1);
        this.m_RefreshApppletThread.start();
    }
    
    public void paint(final Graphics graphics) {
        if (this.m_NoCopyright) {
            graphics.setColor(Color.red);
            graphics.drawString("Invalid copyright parameter", 10, 10);
        }
    }
    
    private String formatDate(final Date date) {
        final String value = String.valueOf(date.getYear() + 1900);
        final String s = (date.getMonth() + 1 > 10) ? String.valueOf(date.getMonth() + 1) : ("0" + String.valueOf(date.getMonth() + 1));
        final String s2 = (date.getDate() > 10) ? String.valueOf(date.getDate()) : ("0" + String.valueOf(date.getDate()));
        return (this.m_Date_Format.equals("European") ? s2 : s) + "/" + (this.m_Date_Format.equals("European") ? s : s2) + "/" + value;
    }
    
    private void draw(final Date date, final Date date2) {
        if (this.m_Cache.isBusy()) {
            return;
        }
        this.m_WaitAnimator.start();
        this.enableControls(false);
        this.m_Ticker.removeAllCaptions();
        this.m_Ticker.addCaption(this.m_Wait_Message, Color.white);
        this.m_Ticker.reset();
        this.m_Cache.start(date, date2);
    }
    
    public int getSelectedNameCount() {
        int n = 0;
        for (int i = 0; i < this.m_SortedName.length; ++i) {
            final String s = this.m_SortedName[i];
            final Enumeration<Object> keys = (Enumeration<Object>)this.m_GroupHash.keys();
            while (keys.hasMoreElements()) {
                final String s2 = this.m_GroupHash.get(keys.nextElement()).get(s);
                if (s2 != null && s2.equals("1")) {
                    ++n;
                }
            }
        }
        return n;
    }
    
    private Date ReadDate(final String s, final Date date) {
        Date dateFromString;
        try {
            dateFromString = HTTPDataSource.getDateFromString(this.getParameter(s));
        }
        catch (Exception ex) {
            dateFromString = date;
        }
        return dateFromString;
    }
    
    private Color ReadColor(final String s, final Color color) {
        Color color2;
        try {
            color2 = new Color(Integer.parseInt(this.getParameter(s), 16));
        }
        catch (Exception ex) {
            color2 = color;
        }
        return color2;
    }
    
    private void initGroupHash(final int n) {
        final Vector vector = new Vector<String>();
        for (int i = 0; i < n; ++i) {
            final String[] splitStr = this.splitStr(this.ReadString("Symbol_" + new Integer(i).toString(), ""), ",");
            final String s = splitStr[0];
            final String s2 = splitStr[1];
            if (splitStr.length > 2) {
                final String[] array = new String[splitStr.length - 2];
                for (int j = 0; j < array.length; ++j) {
                    array[j] = splitStr[j + 2];
                }
                this.m_Symbol_Link_Hash.put(s2, array);
            }
            vector.addElement(s2);
            Hashtable<?, ?> hashtable;
            if (this.m_GroupHash.containsKey(s)) {
                hashtable = this.m_GroupHash.get(s);
            }
            else {
                hashtable = new Hashtable<Object, Object>();
                this.m_GroupHash.put(s, hashtable);
            }
            hashtable.put(s2, "0");
        }
        this.m_SortedName = new String[vector.size()];
        for (int k = 0; k < vector.size(); ++k) {
            this.m_SortedName[k] = vector.elementAt(k);
        }
    }
    
    private Font ReadFont(final String s, final Font font) {
        Font font2;
        try {
            final String readString = this.ReadString(s + "_Name", "");
            final String readString2 = this.ReadString(s + "_Style", "");
            final int readInteger = this.ReadInteger(s + "_Size", 12);
            int n = 0;
            if (readString2.compareTo("Bold") == 0) {
                n = 1;
            }
            else if (readString2.compareTo("Italic") == 0) {
                n = 2;
            }
            font2 = new Font(readString, n, readInteger);
        }
        catch (Exception ex) {
            font2 = font;
        }
        return font2;
    }
    
    private void initApplet2() {
        this.m_RefreshInterval = this.ReadInteger("Refresh_Interval", 2500);
        this.m_Date_Format = this.ReadString("Date_Format", "European");
        this.m_Link_Symbols_Int = this.ReadInteger("Link_Symbols", 0);
        this.m_Symbol_Link_Hash = new Hashtable();
        this.m_Ticker = new Ticker();
        (this.m_Chart_Tab = new Button2("CHART_TAB", this, this.ReadString("Chart_Tab_1", "tab1a.gif"), this.ReadString("Chart_Tab_2", "tab1b.gif"), this.ReadInteger("Chart_Tab_Width", 36), this.ReadInteger("Chart_Tab_Height", 18))).setPressedState(true);
        this.m_Chart_Tab.disable();
        this.m_Settings_Tab = new Button2("SETTINGS_TAB", this, this.ReadString("Settings_Tab_1", "tab2a.gif"), this.ReadString("Settings_Tab_2", "tab2b.gif"), this.ReadInteger("Settings_Tab_Width", 49), this.ReadInteger("Settings_Tab_Height", 18));
        this.m_Draw_Btn = new Button2("DRAW_BTN", this, this.ReadString("Draw_Tab_1", "draw1.gif"), this.ReadString("Draw_Tab_2", "draw2.gif"), this.ReadInteger("Draw_Tab_Width", 36), this.ReadInteger("Draw_Tab_Height", 14));
        this.m_WaitAnimator = new WaitAnimator(this, this.ReadString("Wait_Image", "wait.gif"), this.ReadInteger("Draw_Tab_Width", 7), this.ReadInteger("Draw_Tab_Height", 6));
        this.m_Timeout_Message = this.ReadString("Timeout_Message", "");
        this.m_Wait_Message = this.ReadString("Wait_Message", "");
        this.m_Empty_Message = this.ReadString("Empty_Message", "");
        this.m_Top_BG_Color = this.ReadColor("Top_BG_Color", Color.black);
        this.m_Chart_BG_Color = this.ReadColor("Chart_BG_Color", Color.black);
        this.m_Ticker_BG_Color = this.ReadColor("Ticker_BG_Color", Color.black);
        this.m_Text_Color = this.ReadColor("Text_Color", Color.white);
        this.m_Tab_BG_Color = this.ReadColor("Tab_BG_Color", Color.gray);
        this.m_Tab_Line_Color = this.ReadColor("Tab_Line_Color", Color.black);
        this.m_Data_Source = this.ReadString("Data_Source", "");
        this.m_Message_Font = this.ReadFont("Message_Font", new Font("Dialog", 0, 22));
        this.m_Chart_Font = this.ReadFont("Chart_Font", new Font("Dialog", 0, 12));
        this.m_Ticker_Font = this.ReadFont("Ticker_Font", new Font("Dialog", 0, 12));
        this.m_JChart = new JChart(this.ReadString("License_Key", ""), this.ReadInteger("Show_Prices", 0), this.ReadInteger("Price_Precision", 0), this.m_Empty_Message, this.m_Message_Font, this.ReadColor("Zoom_Color", Color.green), this.ReadColor("XORZoom_Color", Color.red), this.ReadColor("Grid_Color", Color.white), this.ReadInteger("Top_Margin", 20), this.ReadInteger("Bottom_Margin", 20), this.ReadInteger("Left_Margin", 50), this.ReadInteger("Right_Margin", 15), this.ReadInteger("Gap", 3), this.ReadInteger("Vert_Grid_Density", 5));
        this.m_Start_Date_TF = new TextField(10);
        this.m_End_Date_TF = new TextField(10);
        this.m_DateRange_Hash = new Hashtable();
        this.m_DRChoice = new Choice2();
        for (int i = 0; i < this.ReadInteger("Date_Range_Count", 0); ++i) {
            final String readString = this.ReadString("Date_Range_" + new Integer(i).toString(), "");
            try {
                final String[] splitStr = this.splitStr(readString, ",");
                final String s = splitStr[0];
                final String s2 = splitStr[1];
                final String s3 = splitStr[2];
                this.m_DateRange_Hash.put(s, splitStr);
                this.m_DRChoice.addItem(s);
                if (this.m_Start_Date_TF.getText().equals("")) {
                    this.m_Start_Date_TF.setText(this.formatDate(HTTPDataSource.getDateFromString(s2)));
                }
                if (this.m_End_Date_TF.getText().equals("")) {
                    this.m_End_Date_TF.setText(this.formatDate(HTTPDataSource.getDateFromString(s3)));
                }
            }
            catch (Exception ex) {}
        }
        this.m_CardLayout = new CardLayout();
        final Color[] array = new Color[this.ReadInteger("Line_Color_Count", 0)];
        for (int j = 0; j < array.length; ++j) {
            array[j] = this.ReadColor("Line_Color_" + new Integer(j).toString(), Color.red);
        }
        this.m_Cache = new Cache(this, array, this.ReadInteger("Max_Records", 400), this.getCodeBase() + this.m_Data_Source, this.ReadInteger("Show_Request", 1) == 1);
        this.m_Show_Group_Int = this.ReadInteger("Show_Groups", 1);
        this.m_GroupHash = new Hashtable();
        this.initGroupHash(this.ReadInteger("Symbol_Count", 0));
        this.m_Group_Limit_Hash = new Hashtable();
        for (int k = 0; k < this.ReadInteger("Group_Count", 0); ++k) {
            final String readString2 = this.ReadString("Group_" + new Integer(k).toString(), "");
            try {
                final String[] splitStr2 = this.splitStr(readString2, ",");
                if (this.m_GroupHash.containsKey(splitStr2[0])) {
                    this.m_Group_Limit_Hash.put(splitStr2[0], new Integer(splitStr2[1]));
                }
            }
            catch (Exception ex2) {}
        }
        this.preloadCache(this.ReadInteger("Init_Symbol_Count", 0));
        this.m_First_Shown_Group = this.ReadString("First_Shown_Group", "");
        this.m_ListView = new ListView(this.ReadString("Settings_Prompt", ""), this.getHashForName(this.m_First_Shown_Group), this.getGroupLimit(this.m_First_Shown_Group), this.m_Top_BG_Color);
        this.m_Start_Date_Label = new Label(this.ReadString("Start_Date_Label", ""), 1);
        this.m_End_Date_Label = new Label(this.ReadString("End_Date_Label", ""), 1);
        this.m_Date_Format_Label = new Label(this.ReadString("Date_Format_Label", ""), 1);
        this.m_Group_Label = new Label(this.ReadString("Group_Label", ""));
        this.m_List = new List(3, false);
        this.layoutControls2();
        this.draw(this.strToDate(this.m_Start_Date_TF.getText()), this.strToDate(this.m_End_Date_TF.getText()));
    }
    
    private String[] splitStr(final String s, final String s2) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, s2);
        final Vector vector = new Vector<String>();
        while (stringTokenizer.hasMoreElements()) {
            vector.addElement(stringTokenizer.nextToken());
        }
        final String[] array = new String[vector.size()];
        for (int i = 0; i < vector.size(); ++i) {
            array[i] = vector.elementAt(i);
        }
        return array;
    }
    
    public boolean action(final Event event, final Object o) {
        return this.doAction2(event, o);
    }
    
    private void doOnListViewItemSelect() {
        final Vector<String> vector = new Vector<String>();
        for (int i = 0; i < this.m_SortedName.length; ++i) {
            final String s = this.m_SortedName[i];
            final Enumeration<Object> keys = this.m_GroupHash.keys();
            while (keys.hasMoreElements()) {
                final Hashtable<Object, String> hashtable = this.m_GroupHash.get(keys.nextElement());
                final String s2 = hashtable.get(s);
                if (s2 != null && s2.equals("1")) {
                    vector.addElement(s);
                    hashtable.put(s, "0");
                }
            }
        }
        for (int j = 0; j < vector.size(); ++j) {
            final String s3 = vector.elementAt(j);
            if (this.m_Symbol_Link_Hash.containsKey(s3)) {
                final String[] array = this.m_Symbol_Link_Hash.get(s3);
                final String[] array2 = new String[array.length + 1];
                array2[0] = s3;
                for (int k = 1; k < array2.length; ++k) {
                    array2[k] = array[k - 1];
                }
                for (int l = 0; l < array2.length; ++l) {
                    final String s4 = array2[l];
                    final Enumeration<Object> keys2 = this.m_GroupHash.keys();
                    while (keys2.hasMoreElements()) {
                        final Hashtable<Object, String> hashtable2 = this.m_GroupHash.get(keys2.nextElement());
                        if (hashtable2.get(s4) != null) {
                            hashtable2.put(s4, "1");
                        }
                    }
                }
            }
        }
    }
    
    private void updateCache(final Hashtable hashtable) {
        for (int i = 0; i < this.m_SortedName.length; ++i) {
            final String s = this.m_SortedName[i];
            final Enumeration<Object> keys = this.m_GroupHash.keys();
            while (keys.hasMoreElements()) {
                final String s2 = this.m_GroupHash.get(keys.nextElement()).get(s);
                if (s2 != null && s2.equals("1")) {
                    if (hashtable != null) {
                        this.m_Cache.put(s, hashtable.get(s));
                    }
                    else {
                        this.m_Cache.put(s, null);
                    }
                }
            }
        }
    }
    
    private int ReadInteger(final String s, final int n) {
        int int1;
        try {
            int1 = Integer.parseInt(this.getParameter(s));
        }
        catch (Exception ex) {
            int1 = n;
        }
        return int1;
    }
    
    public void run() {
        while (true) {
            try {
                while (true) {
                    this.refreshAll(this);
                    Thread.sleep(this.m_RefreshInterval);
                }
            }
            catch (Exception ex) {
                continue;
            }
            break;
        }
    }
    
    public void init() {
        super.init();
        if (!this.ReadString("copyright", "").equals("http://www.uralbeacon.co.uk")) {
            this.m_NoCopyright = true;
            return;
        }
        this.m_NoCopyright = false;
        this.initApplet2();
    }
    
    public boolean handleEvent(final Event event) {
        if (event.target instanceof List) {
            if (((List)event.target).equals(this.m_List)) {
                final String selectedItem = this.m_List.getSelectedItem();
                if (selectedItem != null) {
                    final Hashtable hashtable = this.m_GroupHash.get(selectedItem);
                    if (hashtable != null) {
                        this.m_ListView.setCaptions(hashtable, this.getGroupLimit(selectedItem));
                        this.m_ListView.reset();
                        return true;
                    }
                }
            }
        }
        else if (event.target instanceof TextField) {
            final String text = this.m_Start_Date_TF.getText();
            final String text2 = this.m_End_Date_TF.getText();
            this.saveDateRange(text, text2);
            if (this.m_DRChoice.countItems() > 0) {
                String item = this.m_DRChoice.getItem(0);
                final Enumeration<Object> keys = (Enumeration<Object>)this.m_DateRange_Hash.keys();
                while (keys.hasMoreElements()) {
                    final String[] array = this.m_DateRange_Hash.get(keys.nextElement());
                    final String s = array[0];
                    if (!s.equals(this.m_DRChoice.getItem(0))) {
                        final String formatDate = this.formatDate(HTTPDataSource.getDateFromString(array[1]));
                        final String formatDate2 = this.formatDate(HTTPDataSource.getDateFromString(array[2]));
                        if (!formatDate.equals(text) || !formatDate2.equals(text2)) {
                            continue;
                        }
                        item = s;
                    }
                }
                this.m_DRChoice.select(item);
            }
        }
        return super.handleEvent(event);
    }
    
    private Date strToDate(final String s) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, "/");
        final int intValue = new Integer((String)stringTokenizer.nextElement());
        final int intValue2 = new Integer((String)stringTokenizer.nextElement());
        return new Date(new Integer((String)stringTokenizer.nextElement()) - 1900, (this.m_Date_Format.equals("European") ? intValue2 : intValue) - 1, this.m_Date_Format.equals("European") ? intValue : intValue2);
    }
    
    private Hashtable getHashForName(String s) {
        if (s.equals("")) {
            final String[] array = new String[this.m_GroupHash.size()];
            final Enumeration<String> keys = this.m_GroupHash.keys();
            int n = 0;
            while (keys.hasMoreElements()) {
                array[n++] = keys.nextElement();
            }
            ListView.sort(true, array);
            s = array[0];
        }
        return this.m_GroupHash.get(s);
    }
}
