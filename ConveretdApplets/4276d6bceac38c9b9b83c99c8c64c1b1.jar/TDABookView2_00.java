import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import com.island.clients.ds.bv.Utils;
import com.island.clients.ds.bv.ReadableStockRecord;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.net.MalformedURLException;
import java.awt.event.KeyListener;
import java.awt.event.ItemListener;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.Cursor;
import java.awt.Component;
import java.awt.LayoutManager;
import com.island.clients.ds.bv.Debug;
import java.awt.Color;
import com.island.clients.ds.bv.ReaderThread;
import java.awt.Image;
import java.text.DecimalFormat;
import com.island.clients.ds.bv.DataPanel;
import com.island.clients.ds.bv.DataLabel;
import java.awt.Font;
import java.awt.Button;
import java.awt.TextField;
import java.awt.Checkbox;
import javax.swing.JLabel;
import java.awt.Panel;
import java.awt.Label;
import java.net.URL;
import com.island.clients.ds.bv.BookViewPane;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class TDABookView2_00 extends Applet implements BookViewPane
{
    public static final String AGGREGATE_BY_PRICE = "Y";
    public static final String AGGREGATE_BY_MPID = "M";
    String version;
    static final int MAX_ORDERS = 15;
    URL codebase;
    String target;
    static final int ORDER_CELL_GAP = 2;
    Label labelLastPrice;
    Label labelLastTime;
    Label labelTodayOrders;
    Label labelTodayVolume;
    Panel panelHeader;
    JLabel labelHelp;
    Panel panelLine1;
    Panel panelLine2;
    Label labelBShares;
    Label labelBPrice;
    Label labelBTime;
    Label labelBMPID;
    Label labelSShares;
    Label labelSPrice;
    Label labelSTime;
    Label labelSMPID;
    Label labelSellOrders;
    Label labelBuyOrders;
    Label labelLast;
    Label labelHotStock;
    Label labelAggregate;
    Checkbox cbAggregated;
    Checkbox cbAggregatedByMPID;
    Panel panelNewStock;
    TextField textFieldNewStockText;
    Button buttonNewStockGo;
    Label labelToday;
    Font defaultFont;
    DataLabel labelMessage;
    DataLabel labelHalted;
    DataLabel labelPendingStock;
    DataPanel dataPanel;
    DataLabel dataVolume;
    DataLabel dataStock;
    DataLabel dataOrders;
    DataLabel dataTime;
    DataLabel dataPrice;
    DataLabel dataNoneBuy;
    DataLabel dataNoneSell;
    DataLabel[] dataBuyTime;
    DataLabel[] dataSellTime;
    DataLabel[] dataBuyMPID;
    DataLabel[] dataSellMPID;
    DataLabel[] dataBuyShares;
    DataLabel[] dataSellShares;
    DataLabel[] dataBuyPrice;
    DataLabel[] dataSellPrice;
    DataLabel dataBuyFooter;
    DataLabel dataSellFooter;
    DecimalFormat decimalFormatter;
    DecimalFormat decimalFormatter4Places;
    Image logo;
    Image tdaLogo;
    Image tdaHelpLogo;
    volatile ReaderThread activeReaderThread;
    final Color[] colors;
    String activeStock;
    
    public TDABookView2_00() {
        this.colors = new Color[] { new Color(11184810), new Color(11167334), new Color(10066278), new Color(6728345), new Color(6724010), new Color(6723942), new Color(6723993), new Color(13421772), new Color(16751001), new Color(13421721), new Color(10092492), new Color(10079487), new Color(10079385), new Color(10079436), new Color(6728447) };
        this.version = "3.4";
        this.labelLastPrice = new Label();
        this.labelLastTime = new Label();
        this.labelTodayOrders = new Label();
        this.labelTodayVolume = new Label();
        this.panelHeader = new Panel();
        this.labelHelp = new JLabel();
        this.panelLine1 = new Panel();
        this.panelLine2 = new Panel();
        this.labelBShares = new Label();
        this.labelBPrice = new Label();
        this.labelBTime = new Label();
        this.labelBMPID = new Label();
        this.labelSShares = new Label();
        this.labelSPrice = new Label();
        this.labelSTime = new Label();
        this.labelSMPID = new Label();
        this.labelSellOrders = new Label();
        this.labelBuyOrders = new Label();
        this.labelLast = new Label();
        this.labelHotStock = new Label();
        this.labelAggregate = new Label();
        this.cbAggregated = new Checkbox("by Price");
        this.cbAggregatedByMPID = new Checkbox("by MPID");
        this.panelNewStock = new Panel();
        this.textFieldNewStockText = new TextField(8);
        this.buttonNewStockGo = new Button();
        this.labelToday = new Label();
        this.labelMessage = new DataLabel();
        this.labelHalted = new DataLabel();
        this.labelPendingStock = new DataLabel();
        this.dataPanel = new DataPanel();
        this.dataVolume = new DataLabel();
        this.dataStock = new DataLabel();
        this.dataOrders = new DataLabel();
        this.dataTime = new DataLabel();
        this.dataPrice = new DataLabel();
        this.dataNoneBuy = new DataLabel();
        this.dataNoneSell = new DataLabel();
        this.dataBuyShares = new DataLabel[15];
        this.dataSellShares = new DataLabel[15];
        this.dataBuyPrice = new DataLabel[15];
        this.dataSellPrice = new DataLabel[15];
        this.dataBuyTime = new DataLabel[15];
        this.dataSellTime = new DataLabel[15];
        this.dataBuyMPID = new DataLabel[15];
        this.dataSellMPID = new DataLabel[15];
        this.activeReaderThread = null;
        this.activeStock = null;
        this.decimalFormatter = new DecimalFormat("#,###.00");
        this.decimalFormatter4Places = new DecimalFormat("#,###.0000");
    }
    
    public synchronized void blankStockRecord(final String s) {
        if (Thread.currentThread() != this.activeReaderThread) {
            Debug.x("Zombie tried to blankStockRecord()");
            return;
        }
        this.showBlankStock(s);
    }
    
    public String getAppletInfo() {
        return "INET BookViewer (c)2003 V" + this.version + " - Bob Kenny";
    }
    
    public String[][] getParameterInfo() {
        final String[][] as = { { "host", "url", "host to connect to for data" }, { "debug", "boolean", "enable printing debug message to the console (T or F)" }, { "stock", "String", "default stock to to come up with" }, { "aggregate", "boolean", "default to aggregate by price" }, { "aggregateByMPID", "boolean", "aggregate by MPID" }, { "target", "String", "target browser window for links" } };
        return as;
    }
    
    public void init() {
        System.out.println(this.getAppletInfo());
        Debug.x("Initing...");
        this.setLayout(null);
        this.setBackground(Color.WHITE);
        this.setSize(478, 508);
        this.labelLastPrice.setText("Price");
        this.add(this.labelLastPrice);
        this.labelLastPrice.setBounds(12, 166, 35, 16);
        this.labelLastTime.setText("Time");
        this.add(this.labelLastTime);
        this.labelLastTime.setBounds(11, 184, 35, 16);
        this.labelTodayOrders.setText("Orders");
        this.add(this.labelTodayOrders);
        this.labelTodayOrders.setBounds(229, 166, 50, 16);
        this.labelTodayVolume.setText("Volume");
        this.add(this.labelTodayVolume);
        this.labelTodayVolume.setBounds(229, 184, 50, 16);
        final Panel greenPanel = new Panel();
        greenPanel.setLayout(null);
        greenPanel.setBackground(Color.decode("#00B105"));
        greenPanel.setBounds(0, 48, 450, 6);
        this.add(greenPanel);
        final Panel darkGrayPanel = new Panel();
        darkGrayPanel.setLayout(null);
        darkGrayPanel.setBackground(Color.decode("#CCCCCC"));
        darkGrayPanel.setBounds(0, 54, 450, 1);
        this.add(darkGrayPanel);
        final Panel grayPanel = new Panel();
        grayPanel.setLayout(null);
        grayPanel.setBackground(Color.decode("#EEEEEE"));
        grayPanel.setBounds(0, 55, 450, 4);
        this.add(grayPanel);
        this.labelHelp.setText("<HTML><u>Help</u></HTMLL>");
        this.labelHelp.setCursor(Cursor.getPredefinedCursor(12));
        this.add(this.labelHelp);
        this.labelHelp.setForeground(Color.decode("#1D5296"));
        this.labelHelp.setFont(new Font("Arial", 1, 11));
        this.labelHelp.setBounds(414, 19, 30, 12);
        this.labelBTime.setText("Time");
        this.labelBTime.setAlignment(2);
        this.add(this.labelBTime);
        this.labelBTime.setBackground(new Color(229, 229, 229));
        this.labelBTime.setFont(new Font("Dialog", 0, 9));
        this.labelBTime.setBounds(10, 226, 54, 16);
        this.labelBMPID.setText("MPID");
        this.labelBMPID.setAlignment(2);
        this.add(this.labelBMPID);
        this.labelBMPID.setBackground(new Color(229, 229, 229));
        this.labelBMPID.setFont(new Font("Dialog", 0, 9));
        this.labelBMPID.setBounds(66, 226, 46, 16);
        this.labelBShares.setText("SHARES");
        this.labelBShares.setAlignment(2);
        this.add(this.labelBShares);
        this.labelBShares.setBackground(new Color(229, 229, 229));
        this.labelBShares.setFont(new Font("Dialog", 0, 9));
        this.labelBShares.setBounds(114, 226, 46, 16);
        this.labelBPrice.setText("BID");
        this.labelBPrice.setAlignment(2);
        this.add(this.labelBPrice);
        this.labelBPrice.setBackground(new Color(229, 229, 229));
        this.labelBPrice.setFont(new Font("Dialog", 0, 9));
        this.labelBPrice.setBounds(162, 226, 57, 16);
        this.labelSPrice.setText("ASK");
        this.labelSPrice.setAlignment(2);
        this.add(this.labelSPrice);
        this.labelSPrice.setBackground(new Color(229, 229, 229));
        this.labelSPrice.setFont(new Font("Dialog", 0, 9));
        this.labelSPrice.setBounds(229, 226, 57, 16);
        this.labelSShares.setText("SHARES");
        this.labelSShares.setAlignment(2);
        this.add(this.labelSShares);
        this.labelSShares.setBackground(new Color(229, 229, 229));
        this.labelSShares.setFont(new Font("Dialog", 0, 9));
        this.labelSShares.setBounds(289, 226, 46, 16);
        this.labelSMPID.setText("MPID");
        this.labelSMPID.setAlignment(2);
        this.add(this.labelSMPID);
        this.labelSMPID.setBackground(new Color(229, 229, 229));
        this.labelSMPID.setFont(new Font("Dialog", 0, 9));
        this.labelSMPID.setBounds(336, 226, 46, 16);
        this.labelSTime.setText("Time");
        this.labelSTime.setAlignment(2);
        this.add(this.labelSTime);
        this.labelSTime.setBackground(new Color(229, 229, 229));
        this.labelSTime.setFont(new Font("Dialog", 0, 9));
        this.labelSTime.setBounds(384, 226, 54, 16);
        this.labelSellOrders.setText("SELL ORDERS");
        this.labelSellOrders.setAlignment(1);
        this.add(this.labelSellOrders);
        this.labelSellOrders.setBackground(Color.lightGray);
        this.labelSellOrders.setFont(new Font("Dialog", 1, 12));
        this.labelSellOrders.setBounds(229, 208, 209, 16);
        this.labelBuyOrders.setText("BUY ORDERS");
        this.labelBuyOrders.setAlignment(1);
        this.add(this.labelBuyOrders);
        this.labelBuyOrders.setBackground(Color.lightGray);
        this.labelBuyOrders.setFont(new Font("Dialog", 1, 12));
        this.labelBuyOrders.setBounds(10, 208, 209, 16);
        this.labelLast.setText("LAST MATCH");
        this.labelLast.setAlignment(1);
        this.add(this.labelLast);
        this.labelLast.setBackground(Color.lightGray);
        this.labelLast.setFont(new Font("Dialog", 1, 12));
        this.labelLast.setBounds(10, 145, 209, 16);
        this.labelHotStock.setText("GET STOCK");
        this.labelHotStock.setAlignment(1);
        this.add(this.labelHotStock);
        this.labelHotStock.setBackground(Color.lightGray);
        this.labelHotStock.setFont(new Font("Dialog", 0, 9));
        this.labelHotStock.setBounds(231, 71, 205, 14);
        this.panelNewStock.setLayout(null);
        this.add(this.panelNewStock);
        this.panelNewStock.setBackground(new Color(229, 229, 229));
        this.panelNewStock.setBounds(229, 69, 209, 67);
        this.panelNewStock.add(this.textFieldNewStockText);
        this.textFieldNewStockText.setBackground(Color.white);
        this.textFieldNewStockText.setForeground(Color.black);
        this.textFieldNewStockText.setFont(new Font("Dialog", 0, 12));
        this.textFieldNewStockText.setBounds(11, 30, 70, 23);
        this.buttonNewStockGo.setLabel("go");
        this.buttonNewStockGo.setBackground(Color.lightGray);
        this.buttonNewStockGo.setBounds(87, 30, 34, 23);
        this.panelNewStock.add(this.buttonNewStockGo);
        this.labelAggregate.setText("Aggregate");
        this.labelAggregate.setAlignment(0);
        this.labelAggregate.setFont(new Font("Dialog", 0, 10));
        this.labelAggregate.setBounds(135, 19, 55, 14);
        this.panelNewStock.add(this.labelAggregate);
        this.cbAggregated.setFont(new Font("Dialog", 0, 10));
        this.cbAggregated.setBounds(148, 31, 113, 20);
        this.panelNewStock.add(this.cbAggregated);
        this.cbAggregatedByMPID.setFont(new Font("Dialog", 0, 10));
        this.cbAggregatedByMPID.setBounds(148, 48, 113, 20);
        this.panelNewStock.add(this.cbAggregatedByMPID);
        this.labelToday.setText("TODAY'S ACTIVITY");
        this.labelToday.setAlignment(1);
        this.add(this.labelToday);
        this.labelToday.setBackground(Color.lightGray);
        this.labelToday.setFont(new Font("Dialog", 1, 12));
        this.labelToday.setBounds(229, 145, 209, 16);
        this.defaultFont = new Font("Dialog", 0, 12);
        this.labelMessage.setText("Please type a symbol and hit \"go\"...");
        this.labelMessage.setFont(new Font("Dialog", 0, 10));
        this.labelMessage.setBounds(9, 534, 120, 18);
        this.labelMessage.setVisible(true);
        this.dataPanel.add(this.labelHalted);
        this.labelHalted.setColor(Color.red);
        this.labelHalted.setText("Halted");
        this.labelHalted.setFont(new Font("Dialog", 0, 12));
        this.labelHalted.setBounds(10, 244, 240, 16);
        this.labelHalted.setAlignment(1);
        this.labelHalted.setVisible(false);
        this.dataPanel.setVisible(false);
        this.dataPanel.setBounds(8, 44, 244, 488);
        this.dataVolume.setAlignment(2);
        this.dataPanel.add(this.dataVolume);
        this.dataVolume.setBounds(360, 184, 72, 16);
        this.dataVolume.setFont(this.defaultFont);
        this.dataVolume.setVisible(true);
        this.labelPendingStock.setColor(new Color(196, 196, 0));
        this.labelPendingStock.setFont(new Font("Dialog", 1, 24));
        this.labelPendingStock.setBounds(10, 103, 209, 38);
        this.labelPendingStock.setAlignment(1);
        this.labelPendingStock.setVisible(false);
        this.dataStock.setFont(new Font("Dialog", 1, 24));
        this.dataStock.setBounds(10, 103, 209, 38);
        this.dataStock.setVisible(true);
        this.dataStock.setAlignment(1);
        this.dataPanel.add(this.dataStock);
        this.dataOrders.setAlignment(2);
        this.dataPanel.add(this.dataOrders);
        this.dataOrders.setBounds(360, 166, 72, 16);
        this.dataOrders.setFont(this.defaultFont);
        this.dataOrders.setVisible(true);
        this.dataTime.setAlignment(2);
        this.dataPanel.add(this.dataTime);
        this.dataTime.setBounds(148, 184, 66, 16);
        this.dataTime.setFont(this.defaultFont);
        this.dataTime.setVisible(true);
        this.dataPrice.setAlignment(2);
        this.dataPrice.setBackground(Color.white);
        this.dataPanel.add(this.dataPrice);
        this.dataPrice.setBounds(148, 166, 66, 16);
        this.dataPrice.setFont(this.defaultFont);
        this.dataPrice.setVisible(true);
        this.dataNoneBuy.setText("(none)");
        this.dataNoneBuy.setAlignment(1);
        this.dataNoneBuy.setBackground(Color.white);
        this.dataPanel.add(this.dataNoneBuy);
        this.dataNoneBuy.setBounds(76, 247, 68, 18);
        this.dataNoneBuy.setFont(this.defaultFont);
        this.dataNoneSell.setText("(none)");
        this.dataNoneSell.setAlignment(1);
        this.dataNoneSell.setBackground(Color.white);
        this.dataPanel.add(this.dataNoneSell);
        this.dataNoneSell.setBounds(303, 247, 68, 18);
        this.dataNoneSell.setFont(this.defaultFont);
        for (int i = 0; i < 15; ++i) {
            this.dataBuyShares[i] = this.newDataOrderCell(this.dataPanel, this.labelBShares, i);
            this.dataSellShares[i] = this.newDataOrderCell(this.dataPanel, this.labelSShares, i);
            this.dataBuyPrice[i] = this.newDataOrderCell(this.dataPanel, this.labelBPrice, i);
            this.dataSellPrice[i] = this.newDataOrderCell(this.dataPanel, this.labelSPrice, i);
            this.dataBuyTime[i] = this.newDataOrderCell(this.dataPanel, this.labelBTime, i);
            this.dataSellTime[i] = this.newDataOrderCell(this.dataPanel, this.labelSTime, i);
            this.dataBuyMPID[i] = this.newDataOrderCell(this.dataPanel, this.labelBMPID, i);
            this.dataSellMPID[i] = this.newDataOrderCell(this.dataPanel, this.labelSMPID, i);
        }
        this.dataBuyFooter = this.newDataFootCell(this.dataPanel, this.labelBShares.getBounds().union(this.labelBPrice.getBounds()), 15);
        this.dataSellFooter = this.newDataFootCell(this.dataPanel, this.labelSShares.getBounds().union(this.labelSPrice.getBounds()), 15);
        final SymMouse symmouse = new SymMouse();
        this.labelHelp.addMouseListener(symmouse);
        final NewStockAction newstockaction = new NewStockAction();
        this.buttonNewStockGo.addActionListener(newstockaction);
        this.cbAggregated.addItemListener(newstockaction);
        this.cbAggregatedByMPID.addItemListener(newstockaction);
        this.textFieldNewStockText.addActionListener(newstockaction);
        final EscKeyListener esckeylistener = new EscKeyListener();
        this.textFieldNewStockText.addKeyListener(esckeylistener);
        Debug.setEnabled("T".equals(this.getParameter("DEBUG")));
        this.cbAggregated.setState("T".equals(this.getParameter("AGGREGATE")));
        this.cbAggregatedByMPID.setState("T".equals(this.getParameter("AGGREGATE_BY_MPID")));
        final String s1 = this.getParameter("STOCK");
        if (s1 != null) {
            this.activeStock = s1.toUpperCase().trim();
            Debug.x("Default stock set to " + s1);
        }
        final String s2 = this.getParameter("host");
        if (s2 != null) {
            Debug.x("HOST param specified as " + s2);
            try {
                this.codebase = new URL(s2);
            }
            catch (MalformedURLException _ex) {
                this.codebase = this.getCodeBase();
                Debug.x("Malformed HOST specified. using default codebase");
            }
        }
        else {
            this.codebase = this.getCodeBase();
            Debug.x("No HOST specified, using default codebase of " + this.codebase.toString());
        }
        this.target = this.getParameter("target");
        this.logo = this.getImage(this.getCodeBase(), "totalview_logo.jpg");
        this.tdaLogo = this.getImage(this.getCodeBase(), "TDA_logo.gif");
        this.tdaHelpLogo = this.getImage(this.getCodeBase(), "TDA_help.gif");
        final int delay = 3600000;
        final ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                synchronized (TDABookView2_00.this) {
                    TDABookView2_00.this.setStock(TDABookView2_00.this.activeStock);
                }
                // monitorexit(this.this$0)
            }
        };
        new Timer(delay, taskPerformer).start();
        Debug.x("Done with init.");
    }
    
    DataLabel newDataFootCell(final DataPanel datapanel, final Rectangle rectangle, final int i) {
        final DataLabel datalabel = new DataLabel();
        datapanel.add(datalabel);
        datalabel.setBounds(rectangle.x, rectangle.y + (i + 1) * (rectangle.height + 2), rectangle.width, rectangle.height);
        datalabel.setAlignment(1);
        datalabel.setFont(this.defaultFont);
        return datalabel;
    }
    
    DataLabel newDataOrderCell(final DataPanel datapanel, final Label label, final int i) {
        final DataLabel datalabel = new DataLabel();
        datapanel.add(datalabel);
        final Rectangle rectangle = label.getBounds();
        datalabel.setBounds(rectangle.x, rectangle.y + (i + 1) * (rectangle.height + 2), rectangle.width, rectangle.height);
        datalabel.setAlignment(2);
        datalabel.setFont(this.defaultFont);
        return datalabel;
    }
    
    synchronized void newStockTrigger() {
        this.setStock(this.textFieldNewStockText.getText());
    }
    
    public void paint(final Graphics g) {
        if (this.dataPanel.isVisible()) {
            this.dataPanel.paint(g);
        }
        if (this.panelHeader.isVisible()) {
            this.panelHeader.paint(g);
        }
        super.paint(g);
        if (this.labelPendingStock.getVisible()) {
            this.labelPendingStock.paint(g);
        }
        if (this.labelMessage.getVisible()) {
            this.labelMessage.paint(g);
        }
        g.drawImage(this.logo, 10, 69, Color.white, this);
        g.drawImage(this.tdaLogo, 0, 0, Color.white, this);
        g.drawImage(this.tdaHelpLogo, 398, 19, Color.white, this);
    }
    
    public synchronized void setStock(final String s) {
        this.activeStock = s.toUpperCase().trim();
        this.textFieldNewStockText.setText(this.activeStock);
        this.textFieldNewStockText.selectAll();
        this.textFieldNewStockText.requestFocus();
        if (this.activeReaderThread != null) {
            this.activeReaderThread.kill();
            this.activeReaderThread = null;
        }
        this.labelPendingStock.setText(this.activeStock);
        this.showBlankStock("Connecting...");
        this.dataStock.setText(this.activeStock);
        final String aggregate = this.cbAggregatedByMPID.getState() ? "M" : (this.cbAggregated.getState() ? "Y" : "N");
        try {
            final String refHost = this.getParameter("REFHOST");
            final String key = this.getParameter("KEY");
            final URL url = new URL(this.codebase, "/SERVICE/MPIDQUOTE?STOCK=" + this.activeStock + "&AGGREGATE=" + aggregate + "&REFHOST=" + refHost + "&KEY=" + key + " ");
            (this.activeReaderThread = new ReaderThread(url, this, true)).start();
        }
        catch (MalformedURLException _ex) {
            this.showBlankStock("Malformed URL");
        }
    }
    
    private void showBlankStock(final String s) {
        Debug.x("Starting to clear");
        this.dataPanel.setVisible(false);
        this.labelMessage.setText(s);
        this.labelPendingStock.setVisible(true);
        this.repaint();
        Debug.x("Done clearing");
    }
    
    void showDocument(final String s) {
        try {
            this.showDocument(new URL(s));
        }
        catch (MalformedURLException _ex) {
            this.showStatus("Bad URL specified!");
        }
    }
    
    void showDocument(final URL url) {
        if (this.target == null) {
            this.getAppletContext().showDocument(url);
        }
        else {
            this.getAppletContext().showDocument(url, this.target);
        }
    }
    
    public void showMessage(final String s) {
        if (Thread.currentThread() != this.activeReaderThread) {
            Debug.x("Zombie tried to showMessage()");
            return;
        }
        this.labelMessage.setText(s);
        this.repaint();
    }
    
    public synchronized void showStockRecord(final ReadableStockRecord readablestockrecord) {
        if (Thread.currentThread() != this.activeReaderThread) {
            Debug.x("Zombie tried to showStockRecord()");
            return;
        }
        this.dataVolume.setText(Utils.commaFormat(readablestockrecord.volume));
        this.dataOrders.setText(Utils.commaFormat(readablestockrecord.totalOrderCount));
        if (readablestockrecord.volume > 0L) {
            if (readablestockrecord.lastTradePrice < 10000L) {
                this.dataPrice.setText(this.decimalFormatter4Places.format(readablestockrecord.lastTradePrice / 10000.0));
            }
            else {
                this.dataPrice.setText(this.decimalFormatter.format(readablestockrecord.lastTradePrice / 10000.0));
            }
            this.dataTime.setText(Utils.timeString((int)readablestockrecord.lastTradeTime));
        }
        else {
            this.dataPrice.setText("n/a");
            this.dataTime.setText("n/a");
        }
        int i = this.colors.length;
        long l = 0L;
        int j = this.colors.length;
        long l2 = 0L;
        if (readablestockrecord.buys.length == 0) {
            this.dataNoneBuy.setVisible(true);
        }
        else {
            this.dataNoneBuy.setVisible(false);
        }
        if (readablestockrecord.sells.length == 0) {
            this.dataNoneSell.setVisible(true);
        }
        else {
            this.dataNoneSell.setVisible(false);
        }
        if (readablestockrecord.isHalted()) {
            this.labelHalted.setVisible(true);
            this.dataNoneSell.setVisible(false);
            this.dataNoneBuy.setVisible(false);
        }
        else {
            this.labelHalted.setVisible(false);
        }
        for (int k = 0; k < 15; ++k) {
            if (k < readablestockrecord.buys.length) {
                if (readablestockrecord.buys[k].price != l) {
                    l = readablestockrecord.buys[k].price;
                    if (i == 0) {
                        i = this.colors.length;
                    }
                    --i;
                }
                final String time = (readablestockrecord.buys[k].timestamp == 0L) ? "" : Utils.timeString((int)readablestockrecord.buys[k].timestamp / 1000);
                this.dataBuyTime[k].setText(time);
                this.dataBuyTime[k].setBackground(this.colors[i]);
                this.dataBuyMPID[k].setText(readablestockrecord.buys[k].mpid);
                this.dataBuyMPID[k].setBackground(this.colors[i]);
                if (readablestockrecord.buys[k].price < 10000L) {
                    this.dataBuyPrice[k].setText(this.decimalFormatter4Places.format(readablestockrecord.buys[k].price / 10000.0));
                }
                else {
                    this.dataBuyPrice[k].setText(this.decimalFormatter.format(readablestockrecord.buys[k].price / 10000.0));
                }
                this.dataBuyPrice[k].setBackground(this.colors[i]);
                this.dataBuyShares[k].setText(Utils.commaFormat(readablestockrecord.buys[k].leaves));
                this.dataBuyShares[k].setBackground(this.colors[i]);
                this.dataBuyTime[k].setVisible(true);
                this.dataBuyMPID[k].setVisible(true);
                this.dataBuyPrice[k].setVisible(true);
                this.dataBuyShares[k].setVisible(true);
            }
            else {
                this.dataBuyTime[k].setVisible(false);
                this.dataBuyMPID[k].setVisible(false);
                this.dataBuyPrice[k].setVisible(false);
                this.dataBuyShares[k].setVisible(false);
            }
            if (k < readablestockrecord.sells.length) {
                if (readablestockrecord.sells[k].price != l2) {
                    l2 = readablestockrecord.sells[k].price;
                    if (j == 0) {
                        j = this.colors.length;
                    }
                    --j;
                }
                final String time = (readablestockrecord.sells[k].timestamp == 0L) ? "" : Utils.timeString((int)readablestockrecord.sells[k].timestamp / 1000);
                this.dataSellTime[k].setText(time);
                this.dataSellTime[k].setBackground(this.colors[j]);
                this.dataSellMPID[k].setText(readablestockrecord.sells[k].mpid);
                this.dataSellMPID[k].setBackground(this.colors[j]);
                if (readablestockrecord.sells[k].price < 10000L) {
                    this.dataSellPrice[k].setText(this.decimalFormatter4Places.format(readablestockrecord.sells[k].price / 10000.0));
                }
                else {
                    this.dataSellPrice[k].setText(this.decimalFormatter.format(readablestockrecord.sells[k].price / 10000.0));
                }
                this.dataSellPrice[k].setBackground(this.colors[j]);
                this.dataSellShares[k].setText(Utils.commaFormat(readablestockrecord.sells[k].leaves));
                this.dataSellShares[k].setBackground(this.colors[j]);
                this.dataSellTime[k].setVisible(true);
                this.dataSellMPID[k].setVisible(true);
                this.dataSellPrice[k].setVisible(true);
                this.dataSellShares[k].setVisible(true);
            }
            else {
                this.dataSellTime[k].setVisible(false);
                this.dataSellMPID[k].setVisible(false);
                this.dataSellPrice[k].setVisible(false);
                this.dataSellShares[k].setVisible(false);
            }
        }
        if (!this.cbAggregated.getState() && !this.cbAggregatedByMPID.getState()) {
            if (readablestockrecord.buyCount > 15L) {
                this.dataBuyFooter.setText("(" + Utils.commaFormat(readablestockrecord.buyCount - 15L) + " more)");
                this.dataBuyFooter.setVisible(true);
            }
            else {
                this.dataBuyFooter.setVisible(false);
            }
            if (readablestockrecord.sellCount > 15L) {
                this.dataSellFooter.setText("(" + Utils.commaFormat(readablestockrecord.sellCount - 15L) + " more)");
                this.dataSellFooter.setVisible(true);
            }
            else {
                this.dataSellFooter.setVisible(false);
            }
        }
        else {
            this.dataBuyFooter.setVisible(false);
            this.dataSellFooter.setVisible(false);
        }
        this.dataPanel.setVisible(true);
        if (this.cbAggregatedByMPID.getState()) {
            this.mpidView();
        }
        else if (this.cbAggregated.getState()) {
            this.priceView();
        }
        else {
            this.defaultView();
        }
        if (this.labelPendingStock.getVisible()) {
            this.labelPendingStock.setVisible(false);
        }
        this.repaint();
    }
    
    private void priceView() {
        this.cbAggregated.setEnabled(true);
        this.labelBTime.setVisible(false);
        this.labelSTime.setVisible(false);
        this.labelBMPID.setVisible(false);
        this.labelSMPID.setVisible(false);
        this.labelBShares.setBounds(10, 226, 102, 16);
        this.labelBPrice.setBounds(114, 226, 105, 16);
        this.labelSPrice.setBounds(229, 226, 102, 16);
        this.labelSShares.setBounds(333, 226, 105, 16);
        for (int i = 0; i < 15; ++i) {
            this.dataBuyTime[i].setVisible(false);
            this.dataBuyMPID[i].setVisible(false);
            this.dataSellTime[i].setVisible(false);
            this.dataSellMPID[i].setVisible(false);
            this.dataBuyShares[i].changeBounds(this.labelBShares, i);
            this.dataBuyPrice[i].changeBounds(this.labelBPrice, i);
            this.dataSellShares[i].changeBounds(this.labelSShares, i);
            this.dataSellPrice[i].changeBounds(this.labelSPrice, i);
        }
    }
    
    private void mpidView() {
        this.cbAggregated.setEnabled(false);
        this.labelBTime.setVisible(false);
        this.labelSTime.setVisible(false);
        this.labelBMPID.setVisible(true);
        this.labelSMPID.setVisible(true);
        this.labelBMPID.setBounds(10, 226, 68, 16);
        this.labelBShares.setBounds(80, 226, 68, 16);
        this.labelBPrice.setBounds(150, 226, 69, 16);
        this.labelSPrice.setBounds(229, 226, 69, 16);
        this.labelSShares.setBounds(300, 226, 68, 16);
        this.labelSMPID.setBounds(370, 226, 68, 16);
        for (int i = 0; i < 15; ++i) {
            this.dataBuyMPID[i].changeBounds(this.labelBMPID, i);
            this.dataBuyShares[i].changeBounds(this.labelBShares, i);
            this.dataBuyPrice[i].changeBounds(this.labelBPrice, i);
            this.dataSellMPID[i].changeBounds(this.labelSMPID, i);
            this.dataSellShares[i].changeBounds(this.labelSShares, i);
            this.dataSellPrice[i].changeBounds(this.labelSPrice, i);
        }
    }
    
    private void defaultView() {
        this.cbAggregated.setEnabled(true);
        this.labelBTime.setVisible(true);
        this.labelSTime.setVisible(true);
        this.labelBMPID.setVisible(true);
        this.labelSMPID.setVisible(true);
        this.labelBTime.setBounds(10, 226, 54, 16);
        this.labelBMPID.setBounds(66, 226, 46, 16);
        this.labelBShares.setBounds(114, 226, 46, 16);
        this.labelBPrice.setBounds(162, 226, 57, 16);
        this.labelSPrice.setBounds(229, 226, 57, 16);
        this.labelSShares.setBounds(289, 226, 46, 16);
        this.labelSMPID.setBounds(336, 226, 46, 16);
        this.labelSTime.setBounds(384, 226, 54, 16);
        for (int i = 0; i < 15; ++i) {
            this.dataBuyShares[i].changeBounds(this.labelBShares, i);
            this.dataSellShares[i].changeBounds(this.labelSShares, i);
            this.dataBuyPrice[i].changeBounds(this.labelBPrice, i);
            this.dataSellPrice[i].changeBounds(this.labelSPrice, i);
            this.dataBuyTime[i].changeBounds(this.labelBTime, i);
            this.dataSellTime[i].changeBounds(this.labelSTime, i);
            this.dataBuyMPID[i].changeBounds(this.labelBMPID, i);
            this.dataSellMPID[i].changeBounds(this.labelSMPID, i);
        }
    }
    
    public synchronized void start() {
        this.showStatus("Starting...");
        Debug.x("Starting");
        super.start();
        this.textFieldNewStockText.requestFocus();
        if (this.activeStock != null) {
            Debug.x("Starting with activeStock=" + this.activeStock);
            this.setStock(this.activeStock);
        }
        else {
            this.showStatus("Ready");
        }
        Debug.x("Started.");
    }
    
    public synchronized void stop() {
        this.showBlankStock("Stopped");
        Debug.x("Stopping...");
        if (this.activeReaderThread != null) {
            this.activeReaderThread.kill();
            this.activeReaderThread = null;
        }
        super.stop();
        Debug.x("Stopped.");
    }
    
    public void update(final Graphics g) {
        final Image image = this.createImage(this.getSize().width, this.getSize().height);
        final Graphics g2 = image.getGraphics();
        this.paint(g2);
        g.drawImage(image, 0, 0, this);
    }
    
    class EscKeyListener extends KeyAdapter
    {
        public void keyPressed(final KeyEvent keyevent) {
            if (keyevent.getKeyCode() == 27) {
                TDABookView2_00.this.textFieldNewStockText.setText("");
            }
        }
    }
    
    class NewStockAction implements ActionListener, ItemListener
    {
        public void itemStateChanged(final ItemEvent arg0) {
            TDABookView2_00.this.newStockTrigger();
        }
        
        public void actionPerformed(final ActionEvent actionevent) {
            TDABookView2_00.this.newStockTrigger();
        }
    }
    
    class SymMouse extends MouseAdapter
    {
        public void mouseClicked(final MouseEvent mouseevent) {
            final Object obj = mouseevent.getSource();
            if (obj == TDABookView2_00.this.labelHelp || obj == TDABookView2_00.this.tdaHelpLogo) {
                TDABookView2_00.this.showStatus("Jumping to help page");
                TDABookView2_00.this.labelHelp.setEnabled(false);
                TDABookView2_00.this.showDocument("https://data.nasdaq.com/pdf/bookviewer.pdf");
                TDABookView2_00.this.labelHelp.setEnabled(true);
            }
        }
    }
}
