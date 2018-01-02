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
import com.island.clients.ds.bv.DataPanel;
import com.island.clients.ds.bv.DataLabel;
import java.awt.Font;
import java.awt.Button;
import java.awt.TextField;
import java.awt.Checkbox;
import java.awt.Panel;
import java.awt.Label;
import java.net.URL;
import com.island.clients.ds.bv.BookViewPane;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class BookView extends Applet implements BookViewPane
{
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
    Label labelHelp;
    Panel panelLine1;
    Panel panelLine2;
    Label labelBShares;
    Label labelBPrice;
    Label labelSShares;
    Label labelSPrice;
    Label labelSellOrders;
    Label labelBuyOrders;
    Label labelLast;
    Label labelHotStock;
    Checkbox cbAggregated;
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
    DataLabel[] dataBuyShares;
    DataLabel[] dataSellShares;
    DataLabel[] dataBuyPrice;
    DataLabel[] dataSellPrice;
    DataLabel dataBuyFooter;
    DataLabel dataSellFooter;
    Image logo;
    volatile ReaderThread activeReaderThread;
    final Color[] colors;
    String activeStock;
    
    public BookView() {
        this.colors = new Color[] { new Color(11184810), new Color(11167334), new Color(10066278), new Color(6728345), new Color(6724010), new Color(6723942), new Color(6723993), new Color(13421772), new Color(16751001), new Color(13421721), new Color(10092492), new Color(10079487), new Color(10079385), new Color(10079436), new Color(6728447) };
        this.version = "3.4";
        this.labelLastPrice = new Label();
        this.labelLastTime = new Label();
        this.labelTodayOrders = new Label();
        this.labelTodayVolume = new Label();
        this.panelHeader = new Panel();
        this.labelHelp = new Label();
        this.panelLine1 = new Panel();
        this.panelLine2 = new Panel();
        this.labelBShares = new Label();
        this.labelBPrice = new Label();
        this.labelSShares = new Label();
        this.labelSPrice = new Label();
        this.labelSellOrders = new Label();
        this.labelBuyOrders = new Label();
        this.labelLast = new Label();
        this.labelHotStock = new Label();
        this.cbAggregated = new Checkbox("Aggregate by Price");
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
        this.activeReaderThread = null;
        this.activeStock = null;
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
        final String[][] as = { { "host", "url", "host to connect to for data" }, { "debug", "boolean", "enable printing debug message to the console (T or F)" }, { "stock", "String", "default stock to to come up with" }, { "aggregate", "boolean", "default to aggregate by price" }, { "target", "String", "target browser window for links" } };
        return as;
    }
    
    public void init() {
        System.out.println(this.getAppletInfo());
        Debug.x("Initing...");
        this.setLayout(null);
        this.setBackground(Color.white);
        this.setSize(244, 508);
        this.labelLastPrice.setText("Price");
        this.add(this.labelLastPrice);
        this.labelLastPrice.setBounds(4, 122, 35, 16);
        this.labelLastTime.setText("Time");
        this.add(this.labelLastTime);
        this.labelLastTime.setBounds(3, 140, 35, 16);
        this.labelTodayOrders.setText("Orders");
        this.add(this.labelTodayOrders);
        this.labelTodayOrders.setBounds(127, 122, 50, 16);
        this.labelTodayVolume.setText("Volume");
        this.add(this.labelTodayVolume);
        this.labelTodayVolume.setBounds(127, 140, 50, 16);
        this.panelHeader.setLayout(null);
        this.add(this.panelHeader);
        this.panelHeader.setBackground(Color.decode("#1D5296"));
        this.panelHeader.setBounds(2, 2, 240, 16);
        this.labelHelp.setText("help");
        this.labelHelp.setCursor(Cursor.getPredefinedCursor(12));
        this.panelHeader.add(this.labelHelp);
        this.labelHelp.setForeground(Color.white);
        this.labelHelp.setFont(new Font("Dialog", 0, 10));
        this.labelHelp.setBounds(203, 1, 30, 12);
        this.labelBShares.setText("SHARES");
        this.labelBShares.setAlignment(2);
        this.add(this.labelBShares);
        this.labelBShares.setBackground(new Color(229, 229, 229));
        this.labelBShares.setFont(new Font("Dialog", 0, 9));
        this.labelBShares.setBounds(2, 182, 46, 16);
        this.labelBPrice.setText("PRICE");
        this.labelBPrice.setAlignment(2);
        this.add(this.labelBPrice);
        this.labelBPrice.setBackground(new Color(229, 229, 229));
        this.labelBPrice.setFont(new Font("Dialog", 0, 9));
        this.labelBPrice.setBounds(50, 182, 67, 16);
        this.labelSShares.setText("SHARES");
        this.labelSShares.setAlignment(2);
        this.add(this.labelSShares);
        this.labelSShares.setBackground(new Color(229, 229, 229));
        this.labelSShares.setFont(new Font("Dialog", 0, 9));
        this.labelSShares.setBounds(127, 182, 46, 16);
        this.labelSPrice.setText("PRICE");
        this.labelSPrice.setAlignment(2);
        this.add(this.labelSPrice);
        this.labelSPrice.setBackground(new Color(229, 229, 229));
        this.labelSPrice.setFont(new Font("Dialog", 0, 9));
        this.labelSPrice.setBounds(175, 182, 66, 16);
        this.labelSellOrders.setText("SELL ORDERS");
        this.labelSellOrders.setAlignment(1);
        this.add(this.labelSellOrders);
        this.labelSellOrders.setBackground(Color.lightGray);
        this.labelSellOrders.setFont(new Font("Dialog", 1, 12));
        this.labelSellOrders.setBounds(127, 164, 115, 16);
        this.labelBuyOrders.setText("BUY ORDERS");
        this.labelBuyOrders.setAlignment(1);
        this.add(this.labelBuyOrders);
        this.labelBuyOrders.setBackground(Color.lightGray);
        this.labelBuyOrders.setFont(new Font("Dialog", 1, 12));
        this.labelBuyOrders.setBounds(2, 164, 115, 16);
        this.labelLast.setText("LAST MATCH");
        this.labelLast.setAlignment(1);
        this.add(this.labelLast);
        this.labelLast.setBackground(Color.lightGray);
        this.labelLast.setFont(new Font("Dialog", 1, 12));
        this.labelLast.setBounds(3, 101, 113, 16);
        this.labelHotStock.setText("GET STOCK");
        this.labelHotStock.setAlignment(1);
        this.add(this.labelHotStock);
        this.labelHotStock.setBackground(Color.lightGray);
        this.labelHotStock.setFont(new Font("Dialog", 0, 9));
        this.labelHotStock.setBounds(129, 27, 111, 14);
        this.panelNewStock.setLayout(null);
        this.add(this.panelNewStock);
        this.panelNewStock.setBackground(new Color(229, 229, 229));
        this.panelNewStock.setBounds(127, 25, 115, 62);
        this.panelNewStock.add(this.textFieldNewStockText);
        this.textFieldNewStockText.setBackground(Color.white);
        this.textFieldNewStockText.setForeground(Color.black);
        this.textFieldNewStockText.setFont(new Font("Dialog", 0, 12));
        this.textFieldNewStockText.setBounds(3, 19, 70, 23);
        this.buttonNewStockGo.setLabel("go");
        this.buttonNewStockGo.setBackground(Color.lightGray);
        this.buttonNewStockGo.setBounds(79, 19, 34, 23);
        this.panelNewStock.add(this.buttonNewStockGo);
        this.cbAggregated.setFont(new Font("Dialog", 0, 10));
        this.cbAggregated.setBounds(2, 45, 113, 20);
        this.panelNewStock.add(this.cbAggregated);
        this.labelToday.setText("TODAY'S ACTIVITY");
        this.labelToday.setAlignment(1);
        this.add(this.labelToday);
        this.labelToday.setBackground(Color.lightGray);
        this.labelToday.setFont(new Font("Dialog", 1, 12));
        this.labelToday.setBounds(127, 101, 115, 16);
        this.defaultFont = new Font("Dialog", 0, 12);
        this.labelMessage.setText("Please type a symbol and hit \"go\"...");
        this.labelMessage.setFont(new Font("Dialog", 0, 10));
        this.labelMessage.setBounds(1, 490, 120, 18);
        this.labelMessage.setVisible(true);
        this.dataPanel.add(this.labelHalted);
        this.labelHalted.setColor(Color.red);
        this.labelHalted.setText("Halted");
        this.labelHalted.setFont(new Font("Dialog", 0, 12));
        this.labelHalted.setBounds(2, 200, 240, 16);
        this.labelHalted.setAlignment(1);
        this.labelHalted.setVisible(false);
        this.dataPanel.setVisible(false);
        this.dataPanel.setBounds(0, 0, 244, 488);
        this.dataVolume.setAlignment(2);
        this.dataPanel.add(this.dataVolume);
        this.dataVolume.setBounds(170, 140, 72, 16);
        this.dataVolume.setFont(this.defaultFont);
        this.dataVolume.setVisible(true);
        this.labelPendingStock.setColor(new Color(196, 196, 0));
        this.labelPendingStock.setFont(new Font("Dialog", 1, 24));
        this.labelPendingStock.setBounds(2, 54, 115, 38);
        this.labelPendingStock.setAlignment(1);
        this.labelPendingStock.setVisible(false);
        this.dataStock.setFont(new Font("Dialog", 1, 24));
        this.dataStock.setBounds(2, 54, 115, 38);
        this.dataStock.setVisible(true);
        this.dataStock.setAlignment(1);
        this.dataPanel.add(this.dataStock);
        this.dataOrders.setAlignment(2);
        this.dataPanel.add(this.dataOrders);
        this.dataOrders.setBounds(170, 122, 72, 16);
        this.dataOrders.setFont(this.defaultFont);
        this.dataOrders.setVisible(true);
        this.dataTime.setAlignment(2);
        this.dataPanel.add(this.dataTime);
        this.dataTime.setBounds(52, 140, 66, 16);
        this.dataTime.setFont(this.defaultFont);
        this.dataTime.setVisible(true);
        this.dataPrice.setAlignment(2);
        this.dataPrice.setBackground(Color.white);
        this.dataPanel.add(this.dataPrice);
        this.dataPrice.setBounds(52, 122, 66, 16);
        this.dataPrice.setFont(this.defaultFont);
        this.dataPrice.setVisible(true);
        this.dataNoneBuy.setText("(none)");
        this.dataNoneBuy.setAlignment(1);
        this.dataNoneBuy.setBackground(Color.white);
        this.dataPanel.add(this.dataNoneBuy);
        this.dataNoneBuy.setBounds(19, 203, 68, 18);
        this.dataNoneBuy.setFont(this.defaultFont);
        this.dataNoneSell.setText("(none)");
        this.dataNoneSell.setAlignment(1);
        this.dataNoneSell.setBackground(Color.white);
        this.dataPanel.add(this.dataNoneSell);
        this.dataNoneSell.setBounds(146, 203, 68, 18);
        this.dataNoneSell.setFont(this.defaultFont);
        for (int i = 0; i < 15; ++i) {
            this.dataBuyShares[i] = this.newDataOrderCell(this.dataPanel, this.labelBShares, i);
            this.dataSellShares[i] = this.newDataOrderCell(this.dataPanel, this.labelSShares, i);
            this.dataBuyPrice[i] = this.newDataOrderCell(this.dataPanel, this.labelBPrice, i);
            this.dataSellPrice[i] = this.newDataOrderCell(this.dataPanel, this.labelSPrice, i);
        }
        this.dataBuyFooter = this.newDataFootCell(this.dataPanel, this.labelBShares.getBounds().union(this.labelBPrice.getBounds()), 15);
        this.dataSellFooter = this.newDataFootCell(this.dataPanel, this.labelSShares.getBounds().union(this.labelSPrice.getBounds()), 15);
        final SymMouse symmouse = new SymMouse();
        this.labelHelp.addMouseListener(symmouse);
        final NewStockAction newstockaction = new NewStockAction();
        this.buttonNewStockGo.addActionListener(newstockaction);
        this.cbAggregated.addItemListener(newstockaction);
        this.textFieldNewStockText.addActionListener(newstockaction);
        final EscKeyListener esckeylistener = new EscKeyListener();
        this.textFieldNewStockText.addKeyListener(esckeylistener);
        Debug.setEnabled("T".equals(this.getParameter("DEBUG")));
        this.cbAggregated.setState("T".equals(this.getParameter("AGGREGATE")));
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
        this.logo = this.getImage(this.getCodeBase(), "Totalview_small.jpg");
        final int delay = 3600000;
        final ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                synchronized (BookView.this) {
                    BookView.this.setStock(BookView.this.activeStock);
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
        super.paint(g);
        if (this.labelPendingStock.getVisible()) {
            this.labelPendingStock.paint(g);
        }
        if (this.labelMessage.getVisible()) {
            this.labelMessage.paint(g);
        }
        g.drawImage(this.logo, 2, 25, 120, 30, Color.white, this);
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
        final String aggregate = this.cbAggregated.getState() ? "Y" : "N";
        try {
            final String refHost = this.getParameter("REFHOST");
            final String key = this.getParameter("KEY");
            final URL url = new URL(this.codebase, "/SERVICE/SQUOTE?STOCK=" + this.activeStock + "&AGGREGATE=" + aggregate + "&REFHOST=" + refHost + "&KEY=" + key + " ");
            (this.activeReaderThread = new ReaderThread(url, this)).start();
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
            this.dataPrice.setText(Utils.priceString(readablestockrecord.lastTradePrice));
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
                this.dataBuyPrice[k].setText(Utils.priceString(readablestockrecord.buys[k].price));
                this.dataBuyPrice[k].setBackground(this.colors[i]);
                this.dataBuyShares[k].setText(Utils.commaFormat(readablestockrecord.buys[k].leaves));
                this.dataBuyShares[k].setBackground(this.colors[i]);
                this.dataBuyPrice[k].setVisible(true);
                this.dataBuyShares[k].setVisible(true);
            }
            else {
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
                this.dataSellPrice[k].setText(Utils.priceString(readablestockrecord.sells[k].price));
                this.dataSellPrice[k].setBackground(this.colors[j]);
                this.dataSellShares[k].setText(Utils.commaFormat(readablestockrecord.sells[k].leaves));
                this.dataSellShares[k].setBackground(this.colors[j]);
                this.dataSellPrice[k].setVisible(true);
                this.dataSellShares[k].setVisible(true);
            }
            else {
                this.dataSellPrice[k].setVisible(false);
                this.dataSellShares[k].setVisible(false);
            }
        }
        if (!this.cbAggregated.getState()) {
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
        if (this.labelPendingStock.getVisible()) {
            this.labelPendingStock.setVisible(false);
        }
        this.repaint();
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
                BookView.this.textFieldNewStockText.setText("");
            }
        }
    }
    
    class NewStockAction implements ActionListener, ItemListener
    {
        public void itemStateChanged(final ItemEvent arg0) {
            BookView.this.newStockTrigger();
        }
        
        public void actionPerformed(final ActionEvent actionevent) {
            BookView.this.newStockTrigger();
        }
    }
    
    class SymMouse extends MouseAdapter
    {
        public void mouseClicked(final MouseEvent mouseevent) {
            final Object obj = mouseevent.getSource();
            if (obj == BookView.this.labelHelp) {
                BookView.this.showStatus("Jumping to help page");
                BookView.this.labelHelp.setEnabled(false);
                BookView.this.showDocument("https://data.nasdaq.com/pdf/bookviewer.pdf");
                BookView.this.labelHelp.setEnabled(true);
            }
        }
    }
}
