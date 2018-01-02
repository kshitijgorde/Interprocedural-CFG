import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.awt.event.MouseEvent;
import java.awt.Label;
import java.awt.LayoutManager;
import java.awt.Component;
import java.awt.FontMetrics;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.io.IOException;
import java.net.URLConnection;
import java.io.Reader;
import java.io.InputStreamReader;
import java.net.URL;
import java.awt.MediaTracker;
import java.awt.Image;
import java.io.BufferedReader;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.MouseListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class BMbanner extends Applet implements Runnable, MouseListener
{
    private static final String version = "1.0.2";
    private static final int APPLET_WIDTH = 360;
    private static final int APPLET_HEIGHT = 110;
    private static final int HEADER_HEIGHT = 32;
    private static final int CONTENT_HEIGHT = 78;
    private static final String AA_ICON_NAME = "/java/aa_logo_sm.gif";
    private static final int HEADER_MARGIN = 7;
    private static final int AA_ICON_BORDER = 4;
    private static final int AA_ICON_WIDTH = 24;
    private static final int PIC_BORDER = 4;
    private static final Color BLUE_DARK;
    private static final Color BLUE;
    private static final Color BLUE_LIGHT;
    private static final Color GRAY;
    private static final Color BLUE_GRAY;
    private static final Color CREAM;
    private static final Color ORANGE;
    private static final Color WHITE;
    private static final long BANNER_DELAY = 10000L;
    private static final String DATA_HANDLER = "/bannerData";
    private static final String ALERTS_URL = "/bm?p=Alerts";
    private String host;
    private String protocol;
    private int port;
    private int errorLinesTotal;
    private Font[] errorFonts;
    private String[] errorLines;
    private BufferedReader bufferedReader;
    private int iconX;
    private Image iconImage;
    private String noPicName;
    private Image noPicImage;
    private MediaTracker mediaTracker;
    private int totalRecords;
    private int currentRecord;
    private String[] childID;
    private String[] childFullName;
    private String[] childDateMissing;
    private String[] childLocation;
    private String[] childImageName;
    private String[] childPhone;
    private Image[] childImage;
    private Font contentFont;
    private boolean needInit;
    private boolean isFirstLoop;
    private boolean running;
    private Thread threadEngine;
    
    public BMbanner() {
        this.needInit = true;
        this.isFirstLoop = true;
        this.running = false;
        this.threadEngine = null;
        this.iconImage = null;
        this.noPicName = null;
        this.noPicImage = null;
        this.totalRecords = 0;
        this.currentRecord = 0;
    }
    
    boolean readData() {
        boolean b = false;
        this.bufferedReader = null;
        try {
            final String parameter = this.getParameter("state");
            String upperCase;
            if (parameter == null) {
                upperCase = "";
            }
            else {
                upperCase = parameter.toUpperCase();
            }
            final URLConnection openConnection = new URL(this.protocol, this.host, this.port, "/bannerData?state=" + urlEncode(upperCase) + "&ver=" + "1.0.2" + "&src=" + urlEncode(this.getDocumentBase().toString())).openConnection();
            openConnection.setUseCaches(false);
            openConnection.setDoInput(true);
            this.bufferedReader = new BufferedReader(new InputStreamReader(openConnection.getInputStream(), "UTF8"));
            this.noPicName = this.readDataString();
            this.totalRecords = this.readDataInt();
            this.childID = new String[this.totalRecords];
            this.childFullName = new String[this.totalRecords];
            this.childDateMissing = new String[this.totalRecords];
            this.childLocation = new String[this.totalRecords];
            this.childImageName = new String[this.totalRecords];
            this.childPhone = new String[this.totalRecords];
            this.childImage = new Image[this.totalRecords];
            this.currentRecord = 0;
            if (this.totalRecords == 0) {
                this.isFirstLoop = false;
            }
            if (this.totalRecords > 0) {
                for (int i = 0; i < this.totalRecords; ++i) {
                    this.childID[i] = this.readDataString();
                    this.childFullName[i] = this.readDataString();
                    this.childDateMissing[i] = this.readDataString();
                    this.childLocation[i] = this.readDataString();
                    this.childImageName[i] = this.readDataString();
                    this.childPhone[i] = this.readDataString();
                    this.childImage[i] = null;
                }
            }
            else {
                final int dataInt = this.readDataInt();
                final Font[] array = new Font[dataInt];
                for (int j = 0; j < dataInt; ++j) {
                    array[j] = new Font(this.readDataString(), this.readDataInt(), this.readDataInt());
                }
                this.errorLinesTotal = this.readDataInt();
                this.errorFonts = new Font[this.errorLinesTotal];
                this.errorLines = new String[this.errorLinesTotal];
                for (int k = 0; k < this.errorLinesTotal; ++k) {
                    this.errorFonts[k] = array[this.readDataInt()];
                    this.errorLines[k] = this.readDataString();
                }
            }
            b = true;
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        if (this.bufferedReader != null) {
            try {
                this.bufferedReader.close();
            }
            catch (Exception ex2) {}
            this.bufferedReader = null;
        }
        return b;
    }
    
    private String readDataString() throws IOException {
        final String line = this.bufferedReader.readLine();
        if (line == null) {
            throw new IOException("bad data file");
        }
        return line;
    }
    
    private int readDataInt() throws IOException {
        final String dataString = this.readDataString();
        int int1;
        try {
            int1 = Integer.parseInt(dataString);
        }
        catch (NumberFormatException ex) {
            throw new IOException(ex.getMessage());
        }
        return int1;
    }
    
    private Image loadImage(final String s, final int n) {
        Image image;
        try {
            image = this.getImage(new URL(this.protocol, this.host, this.port, s));
            this.mediaTracker.addImage(image, n);
            this.mediaTracker.waitForID(n);
            if (this.mediaTracker.isErrorID(n)) {
                image = null;
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
            image = null;
        }
        return image;
    }
    
    public void paint(final Graphics graphics) {
        graphics.setColor(BMbanner.BLUE);
        graphics.fillRect(0, 0, 360, 32);
        graphics.setColor(BMbanner.BLUE_LIGHT);
        graphics.fillRect(0, 32, 360, 78);
        if (this.iconImage != null) {
            final int height = this.iconImage.getHeight(this);
            this.iconImage.getWidth(this);
            graphics.drawImage(this.iconImage, this.iconX + 4, (32 - height) / 2, this);
        }
        if (this.totalRecords == 0) {
            graphics.setColor(BMbanner.BLUE_DARK);
            int n = 0;
            for (int i = 0; i < this.errorLinesTotal; ++i) {
                graphics.setFont(this.errorFonts[i]);
                n += graphics.getFontMetrics().getHeight();
            }
            int n2 = 32 + (78 - n) / 2;
            for (int j = 0; j < this.errorLinesTotal; ++j) {
                final Font font = this.errorFonts[j];
                final String s = this.errorLines[j];
                graphics.setFont(font);
                final FontMetrics fontMetrics = graphics.getFontMetrics();
                final int n3 = (360 - fontMetrics.stringWidth(s)) / 2;
                final int n4 = n2 + (fontMetrics.getLeading() + fontMetrics.getAscent());
                graphics.drawString(s, n3, n4);
                n2 = n4 + fontMetrics.getDescent();
            }
        }
        else {
            int width = 0;
            if (this.childImage[this.currentRecord] != null) {
                final int height2 = this.childImage[this.currentRecord].getHeight(this);
                width = this.childImage[this.currentRecord].getWidth(this);
                graphics.drawImage(this.childImage[this.currentRecord], 4, 32 + (78 - height2) / 2, this);
            }
            graphics.setFont(this.contentFont);
            graphics.setColor(BMbanner.BLUE_DARK);
            final int height3 = graphics.getFontMetrics().getHeight();
            final int n5 = width + 12;
            final int n6 = 36 + height3 - 4;
            final int n7 = 100;
            graphics.drawString("Name:", n5 + 4, n6);
            graphics.drawString(this.childFullName[this.currentRecord], n5 + 4 + n7, n6);
            graphics.drawString("Date Missing:", n5 + 4, n6 + height3 + 1);
            graphics.drawString(this.childDateMissing[this.currentRecord], n5 + 4 + n7, n6 + height3 + 1);
            graphics.drawString("Location:", n5 + 4, n6 + 2 * height3 + 2);
            graphics.drawString(this.childLocation[this.currentRecord], n5 + 4 + n7, n6 + 2 * height3 + 2);
            graphics.drawString("If you have information, contact " + this.childPhone[this.currentRecord], n5 + 4, n6 + 3 * height3 + 3 + 6);
            graphics.setColor(BMbanner.BLUE_GRAY);
            graphics.fillRect(n5, n6 + 2 * height3 + 2 + 5, 352 - n5, 3);
        }
    }
    
    public synchronized void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public String getAppletInfo() {
        return "BeyondMissing.com Banner";
    }
    
    public void init() {
        super.init();
        final URL codeBase = this.getCodeBase();
        this.host = codeBase.getHost();
        this.protocol = codeBase.getProtocol();
        this.port = codeBase.getPort();
        this.mediaTracker = new MediaTracker(this);
        this.threadEngine = null;
        if (this.needInit) {
            if (!this.readData()) {
                this.showStatus("Java banner applet fails to initialize");
            }
            else {
                this.needInit = false;
            }
        }
        this.addMouseListener(this);
        this.setLayout(null);
        this.addNotify();
        this.setVisible(false);
        final String s = "BeyondMissing";
        final Font font = new Font("Serif", 0, 22);
        final Label label = new Label(s, 0);
        final FontMetrics fontMetrics = label.getFontMetrics(font);
        final int n = 2 + fontMetrics.stringWidth(s);
        label.setFont(font);
        label.setForeground(BMbanner.WHITE);
        label.setBackground(BMbanner.BLUE);
        this.add(label);
        final String s2 = ".com";
        final Label label2 = new Label(s2, 0);
        final int n2 = 10 + fontMetrics.stringWidth(s2);
        label2.setFont(font);
        label2.setForeground(BMbanner.GRAY);
        label2.setBackground(BMbanner.BLUE);
        this.add(label2);
        final int n3 = 32;
        final String s3 = "AMBER";
        final Font font2 = new Font("Arial", 0, 20);
        final Label label3 = new Label(s3, 0);
        final FontMetrics fontMetrics2 = label3.getFontMetrics(font2);
        final int n4 = 3 + fontMetrics2.stringWidth(s3);
        label3.setFont(font2);
        label3.setForeground(BMbanner.ORANGE);
        label3.setBackground(BMbanner.BLUE);
        this.add(label3);
        final String s4 = "Alert";
        final Label label4 = new Label(s4, 0);
        final int n5 = 2 + fontMetrics2.stringWidth(s4);
        label4.setFont(font2);
        label4.setForeground(BMbanner.CREAM);
        label4.setBackground(BMbanner.BLUE);
        this.add(label4);
        final int n6 = 7;
        label.setBounds(n6, 0, n, 32);
        final int n7 = n6 + n;
        label2.setBounds(n7, 0, n2, 32);
        final int iconX = n7 + n2;
        this.iconX = iconX;
        final int n8 = iconX + n3;
        label3.setBounds(n8, 0, n4, 32);
        label4.setBounds(n8 + n4, 0, n5, 32);
        this.contentFont = new Font("Arial", 0, 12);
    }
    
    public void start() {
        if (this.needInit || this.threadEngine != null) {
            return;
        }
        this.iconImage = this.loadImage("/java/aa_logo_sm.gif", this.totalRecords);
        this.noPicImage = this.loadImage(this.noPicName, this.totalRecords + 1);
        if (this.totalRecords > 0) {
            this.threadEngine = new Thread(this);
            this.running = true;
            this.threadEngine.start();
        }
    }
    
    public void stop() {
        System.out.println("Java Banner Stopped");
        this.running = false;
        this.threadEngine = null;
    }
    
    public void run() {
        while (this.running) {
            if (this.isFirstLoop && this.childImage[this.currentRecord] == null) {
                this.childImage[this.currentRecord] = ((this.childImageName[this.currentRecord].length() > 0) ? this.loadImage(this.childImageName[this.currentRecord], this.currentRecord) : this.noPicImage);
            }
            this.repaint();
            try {
                Thread.sleep(10000L);
            }
            catch (InterruptedException ex) {}
            ++this.currentRecord;
            if (this.currentRecord == this.totalRecords) {
                this.isFirstLoop = false;
                this.currentRecord = 0;
            }
        }
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        try {
            this.getAppletContext().showDocument(new URL(this.protocol, this.host, this.port, "/bm?p=Alerts"), "BMbanner");
        }
        catch (Exception ex) {}
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    static String urlEncode(String encode) {
        try {
            encode = URLEncoder.encode(encode, "UTF-8");
        }
        catch (UnsupportedEncodingException ex) {}
        return encode;
    }
    
    static {
        BLUE_DARK = new Color(0, 0, 51);
        BLUE = new Color(34, 34, 119);
        BLUE_LIGHT = new Color(204, 204, 221);
        GRAY = new Color(153, 153, 153);
        BLUE_GRAY = new Color(153, 153, 170);
        CREAM = new Color(238, 238, 238);
        ORANGE = new Color(255, 119, 0);
        WHITE = new Color(255, 255, 255);
    }
}
