import java.awt.event.MouseEvent;
import java.util.Hashtable;
import java.io.ObjectInputStream;
import java.awt.Font;
import java.text.DecimalFormat;
import java.awt.Image;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Vector;
import java.applet.AppletContext;
import java.net.URL;
import java.net.URLConnection;
import java.awt.event.MouseListener;
import java.awt.image.ImageObserver;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class MarketInfoApplet extends Applet implements Runnable, ImageObserver, MouseListener
{
    private Thread thread;
    private int yPos;
    private int xPos;
    private int flag;
    private int width;
    private int height;
    private String status;
    private String sectorId;
    private String target;
    private String dateTime;
    private String upImgName;
    private String downImgName;
    private String URLString;
    private String targetURLLink;
    private String time;
    private URLConnection con;
    private URL url;
    private URL codeBase;
    private AppletContext context;
    private Vector sectorCoordinates;
    private Graphics offScr;
    private Color backColor;
    private Color foreColor;
    private Color lineColor;
    private Color upColor;
    private Color headingColor;
    private Color statusColor;
    private Color fillColor;
    private Color unchangedColor;
    private Image img;
    private Image offImg;
    private Image upArrow;
    private Image downArrow;
    private double dblPercentChangeValue;
    private double dblChangeValue;
    public static final double CONSTANT = -1.0;
    DecimalFormat decimalFormat;
    DecimalFormat valueFormat;
    MarketInfo2 info;
    
    public MarketInfoApplet() {
        this.upArrow = null;
        this.downArrow = null;
        this.info = null;
    }
    
    public void init() {
        this.target = "information";
        this.sectorCoordinates = new Vector();
        this.codeBase = this.getCodeBase();
        this.context = this.getAppletContext();
        this.decimalFormat = new DecimalFormat("#,##0.####");
        this.valueFormat = new DecimalFormat("#,##0.00");
        this.basicMarketInfoParameters();
        this.addMouseListener(this);
        this.setFont(new Font("Helvetica", 0, 11));
    }
    
    public void start() {
        if (this.thread == null) {
            this.upArrow = this.loadImage(this.upImgName);
            this.downArrow = this.loadImage(this.downImgName);
            (this.thread = new Thread(this)).start();
        }
    }
    
    public void stop() {
        this.thread = null;
    }
    
    public void run() {
        int n = 0;
        while (this.thread != null) {
            try {
                this.url = new URL(this.getURLString() + "?target=" + this.target);
                (this.con = this.url.openConnection()).setUseCaches(false);
                final ObjectInputStream objectInputStream = new ObjectInputStream(this.con.getInputStream());
                if (this.target.equals("status")) {
                    this.status = (String)objectInputStream.readObject();
                }
                else {
                    this.info = (MarketInfo2)objectInputStream.readObject();
                    this.status = this.info.getStatusCurrencyInfo().getMarketStatus();
                }
                objectInputStream.close();
                if (!this.status.equals("Market Closed")) {
                    if (n == 0) {
                        this.buildSectorPresentation();
                        n = 1;
                    }
                    this.buildOffScrImage();
                    this.repaint();
                    this.target = "information";
                }
                else {
                    if (n == 0) {
                        this.buildSectorPresentation();
                        n = 1;
                    }
                    this.buildOffScrImage();
                    this.repaint();
                    this.target = "status";
                }
            }
            catch (Exception ex2) {
                this.drawErrorMessage();
            }
            try {
                final Thread thread = this.thread;
                Thread.sleep(30000L);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public void paint(final Graphics graphics) {
        if (this.offImg != null) {
            graphics.drawImage(this.offImg, 0, 0, this);
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    private void buildOffScrImage() {
        this.offImg = this.createImage(this.width, this.height);
        this.offScr = this.offImg.getGraphics();
        this.buildInfo();
        this.offScr.dispose();
    }
    
    private void buildInfo() {
        try {
            final Hashtable marketInfo = this.info.getMarketInfo();
            for (int i = 0; i < this.sectorCoordinates.size(); ++i) {
                final SectorPresentation sectorPresentation = this.sectorCoordinates.elementAt(i);
                sectorPresentation.getSectorId();
                final String sectorName = sectorPresentation.getSectorName();
                final int x1Pos = sectorPresentation.getX1Pos();
                final int y1Pos = sectorPresentation.getY1Pos();
                sectorPresentation.getX2Pos();
                final int y2Pos = sectorPresentation.getY2Pos();
                final Sector sector = marketInfo.get(String.valueOf(i));
                final String format = this.valueFormat.format(sector.getIndexPoints());
                final String format2 = this.valueFormat.format(sector.getChange());
                final String format3 = this.decimalFormat.format(sector.getPercentChange());
                this.offScr.setColor(this.foreColor);
                this.offScr.drawString(sectorName, x1Pos, y1Pos);
                this.offScr.drawLine(x1Pos, y2Pos + 12, x1Pos + this.getLength(sectorName), y2Pos + 12);
                final int n = this.xPos + this.getLength(sectorName);
                if (sector.getChange() > 0.0) {
                    this.offScr.setColor(this.upColor);
                    this.offScr.drawImage(this.upArrow, 265, y1Pos - this.img.getHeight(this), null);
                    this.offScr.drawString(format2, 205 - this.getLength(format2), y1Pos);
                    this.offScr.drawString(format3, 260 - this.getLength(format3), y1Pos);
                }
                else if (sector.getChange() == 0.0) {
                    this.offScr.setColor(this.unchangedColor);
                    final String s = "unch";
                    this.offScr.drawString(format2, 205 - this.getLength(format2), y1Pos);
                    this.offScr.drawString(s, 260 - this.getLength(s), y1Pos);
                }
                else {
                    this.offScr.setColor(this.statusColor);
                    this.offScr.drawImage(this.downArrow, 265, y1Pos - this.img.getHeight(this), null);
                    this.dblChangeValue = sector.getChange() * -1.0;
                    this.dblPercentChangeValue = sector.getPercentChange() * -1.0;
                    final String format4 = this.valueFormat.format(this.dblChangeValue);
                    final String format5 = this.decimalFormat.format(this.dblPercentChangeValue);
                    System.out.println("percentChange" + sector.getPercentChange());
                    this.offScr.drawString(format4, 205 - this.getLength(format4), y1Pos);
                    this.offScr.drawString(format5, 260 - this.getLength(format5), y1Pos);
                }
                this.offScr.drawString(format, 140 - this.getLength(format), y1Pos);
            }
            this.readMarketActivityInfo();
            this.readStatusCurrencyInfo();
            this.drawLines();
            this.drawHeader();
        }
        catch (Exception ex) {
            System.out.println("test");
            ex.printStackTrace();
            this.drawErrorMessage();
        }
    }
    
    private void buildSectorPresentation() {
        try {
            this.sectorCoordinates.clear();
            final Hashtable marketInfo = this.info.getMarketInfo();
            final int size = marketInfo.size();
            final int n = 10;
            int n2 = 95;
            int n3 = 97;
            for (int i = 0; i < size; ++i) {
                final Sector sector = marketInfo.get(String.valueOf(i));
                this.sectorId = sector.getSectorId();
                final String sectorName = sector.getSectorName();
                final SectorPresentation sectorPresentation = new SectorPresentation(this.sectorId, sectorName, n, n2, n + this.getLength(sectorName), n3 - 12);
                n3 += 15;
                n2 += 15;
                this.sectorCoordinates.addElement(sectorPresentation);
            }
            System.out.println("size: " + size);
            System.out.println("Sector size: " + this.sectorCoordinates.size());
        }
        catch (Exception ex) {
            ex.printStackTrace();
            this.drawErrorMessage();
        }
    }
    
    private void readMarketActivityInfo() {
        final MarketActivityInfo marketActivityInfo = this.info.getMarketActivityInfo();
        final String format = this.decimalFormat.format(marketActivityInfo.getTotalTrades());
        final String format2 = this.decimalFormat.format(marketActivityInfo.getTotalSharesTraded());
        final String format3 = this.decimalFormat.format(marketActivityInfo.getTotalValue());
        final String format4 = this.decimalFormat.format(marketActivityInfo.getAdvances());
        final String format5 = this.decimalFormat.format(marketActivityInfo.getDeclines());
        final String format6 = this.decimalFormat.format(marketActivityInfo.getNoChange());
        this.offScr.setColor(this.foreColor);
        this.offScr.drawString(format, 260 - this.getLength(format), 237);
        this.offScr.drawString(format2, 260 - this.getLength(format2), 252);
        this.offScr.drawString(format3, 260 - this.getLength(format3), 267);
        this.offScr.drawString(format4, 260 - this.getLength(format4), 282);
        this.offScr.drawString(format5, 260 - this.getLength(format5), 297);
        this.offScr.drawString(format6, 260 - this.getLength(format6), 312);
    }
    
    private void readStatusCurrencyInfo() {
        final StatusCurrencyRateInfo statusCurrencyInfo = this.info.getStatusCurrencyInfo();
        final String dateTime = this.info.getDateTime();
        this.status = statusCurrencyInfo.getMarketStatus();
        final String tradingDate = statusCurrencyInfo.getTradingDate();
        final String closeTime = statusCurrencyInfo.getCloseTime();
        if (this.status.equals("Market Closed")) {
            this.offScr.setColor(this.foreColor);
            this.offScr.drawString("As of  " + tradingDate + ", " + closeTime, 10, 38);
        }
        else {
            this.offScr.setColor(this.foreColor);
            this.offScr.drawString("As of  " + dateTime, 10, 38);
        }
        this.offScr.setColor(this.foreColor);
        this.offScr.setFont(new Font("Helvetica", 1, 11));
        this.offScr.drawString(this.status, 10, 20);
        this.offScr.setFont(new Font("Helvetica", 0, 11));
    }
    
    private void drawErrorMessage() {
        final String s = new String("No data available.");
        this.offImg = this.createImage(this.width, this.height);
        this.offScr = this.offImg.getGraphics();
        this.repaint();
        this.offScr.setColor(this.foreColor);
        this.offScr.drawString(s, this.width / 2 - this.getLength(s) / 2, 60);
    }
    
    private void drawHeader() {
        final int n = 0;
        final int n2 = 299;
        final int n3 = 12;
        final String s = new String("PSE COMPOSITE & SECTORAL INDICES");
        final String s2 = new String("MARKET ACTIVITY");
        this.offScr.setColor(this.foreColor);
        this.offScr.drawString("VALUE", 105, 80);
        this.offScr.drawString("CHANGE", 165, 80);
        this.offScr.drawString("% CHG", 225, 80);
        this.offScr.drawString("Trades", 10, 237);
        this.offScr.drawString("Volume", 10, 252);
        this.offScr.drawString("Value (in thousands)", 10, 267);
        this.offScr.drawString("Advances", 10, 282);
        this.offScr.drawString("Declines", 10, 297);
        this.offScr.drawString("Unchanged", 10, 312);
        this.offScr.setColor(this.fillColor);
        this.offScr.fillRect(n, 50, n2, n3);
        this.offScr.fillRect(n, 210, n2, n3);
        this.offScr.setColor(this.headingColor);
        this.offScr.drawString(s, this.width / 2 - this.getLength(s) / 2, 60);
        this.offScr.drawString(s2, this.width / 2 - this.getLength(s2) / 2, 220);
    }
    
    private int getLength(final String s) {
        return this.getFontMetrics(this.getFont()).stringWidth(s);
    }
    
    private void drawLines() {
        final int n = 0;
        int n2 = 238;
        final int n3 = 300;
        int n4 = 215;
        for (int i = 0; i < 5; ++i) {
            this.offScr.setColor(this.lineColor);
            this.offScr.drawLine(n, n2, n3, n2);
            n2 += 15;
            n4 += 15;
        }
    }
    
    private String getURLString() {
        return this.URLString = this.getParameter("URLString");
    }
    
    private void basicMarketInfoParameters() {
        this.backColor = Convert.string2Color(this.getParameter("backColor"));
        this.headingColor = Convert.string2Color(this.getParameter("headingColor"));
        this.foreColor = Convert.string2Color(this.getParameter("foreColor"));
        this.lineColor = Convert.string2Color(this.getParameter("lineColor"));
        this.statusColor = Convert.string2Color(this.getParameter("statusColor"));
        this.fillColor = Convert.string2Color(this.getParameter("fillColor"));
        this.upColor = Convert.string2Color(this.getParameter("upColor"));
        this.unchangedColor = Convert.string2Color(this.getParameter("unchangedColor"));
        this.upImgName = this.getParameter("upArrow");
        this.downImgName = this.getParameter("downArrow");
        this.width = this.getSize().width;
        this.height = this.getSize().height;
        this.targetURLLink = this.getParameter("targetLink");
        this.setBackground(this.backColor);
    }
    
    private Image loadImage(final String s) {
        try {
            (this.img = this.getImage(this.getDocumentBase(), s)).getHeight(this);
            while ((this.checkImage(this.img, this) & 0x20) == 0x0) {}
            return this.img;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return this.createImage(1, 1);
        }
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        String sectorId = "";
        for (int i = 0; i < this.sectorCoordinates.size(); ++i) {
            final SectorPresentation sectorPresentation = this.sectorCoordinates.elementAt(i);
            if (sectorPresentation.include(x, y)) {
                sectorId = sectorPresentation.getSectorId();
                sectorPresentation.getSectorName();
                break;
            }
        }
        try {
            if (!sectorId.equals("")) {
                this.context.showDocument(new URL(this.targetURLLink + "MarketInformation/indexcomposition.jsp?sector=" + sectorId), "frm_body");
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
}
