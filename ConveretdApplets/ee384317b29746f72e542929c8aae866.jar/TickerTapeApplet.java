import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class TickerTapeApplet extends Applet implements Runnable
{
    String[] dataArray1;
    String[] dataArray2;
    int sleepTime;
    Thread animator;
    public TickerTape tickerTape;
    public TickerTapeDataLoader dataLoader;
    
    public TickerTapeApplet() {
        this.dataArray1 = new String[] { "", "" };
        this.dataArray2 = new String[] { "", "" };
        this.sleepTime = 25;
        this.animator = null;
        this.dataLoader = new TickerTapeDataLoader();
    }
    
    public void init() {
        (this.tickerTape = new TickerTape(this)).setImages(this.getValue("upImage", ""), this.getValue("downImage", ""), this.getValue("noChgImage", ""));
        this.tickerTape.setYPositions(this.getValue("yPosition1", "13"), this.getValue("yPosition2", "26"));
        this.tickerTape.setFonts(this.getValue("font1", "arial"), this.getValue("font2", "arial"), this.getValue("fontSize", "12"));
        this.tickerTape.setColors(this.getValue("foreColor", "white"), this.getValue("backColor", "black"), this.getValue("timeColor", "red"), this.getValue("positiveColor", "green"), this.getValue("negativeColor", "red"), this.getValue("noChgColor", "yellow"));
        this.tickerTape.setHorizontalSpacing(this.getValue("horizontalSpacing", "24"));
        this.tickerTape.setMoveIncrement(this.getValue("moveIncrement", "6"));
        this.dataLoader.setURL(this.getValue("URL", ""));
        this.dataLoader.setDataStream(this.getValue("dataStream", "No Record;.;.;.;"));
        this.dataLoader.setRefreshTime(this.getValue("refreshTime", "300"));
        this.dataLoader.refreshData();
        this.dataLoader.start();
        while (!this.dataLoader.dataAvailable()) {}
    }
    
    private String getValueUpper(String value, final String s) {
        value = this.getValue(value, s);
        return value.toUpperCase();
    }
    
    private String getValue(String parameter, final String s) {
        try {
            parameter = this.getParameter(parameter);
            if (parameter.equals("")) {
                parameter = s;
            }
        }
        catch (Exception ex) {
            parameter = s;
        }
        return parameter;
    }
    
    public String[][] getParameterInfo() {
        Convert.string2Color(this.getParameter("negativeColor"));
        return new String[][] { { "URL", "string", "URL output used as data for Ticker" }, { "dataStream", "string", "test data if URL is not specified" }, { "refreshTime", "int", "time interval to refresh in seconds" }, { "yPosition1", "int", "Y coord for ticker info line 1" }, { "yPosition2", "int", "Y coord for ticker info line 2" }, { "horizontalSpacing", "int", "spacing between columns of data(in pixels)" }, { "moveIncrement", "int", "Fast move increment(in pixels)" }, { "foreColor", "string", "Applet foreground color" }, { "backColor", "string", "Applet background color" }, { "timeColor", "string", "Text color of the time display" }, { "positiveColor", "string", "Up(positive change of price) color" }, { "noChgColor", "string", "No change color" }, { "upImage", "string", "GIF/JPG image to indicate positive change in price value" }, { "downImage", "string", "GIF/JPG image to indicate negative change in price value" }, { "noChgImage", "string", "GIF/JPG image to indicate no change in price value" }, { "font1", "string", "Font used to display Security Symbol" }, { "font2", "string", "Font used to display Price/Volume values" }, { "fontSize", "int", "Size of the font used" } };
    }
    
    public void start() {
        if (this.animator == null) {
            (this.animator = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.animator != null) {
            this.animator.stop();
        }
        this.animator = null;
        this.dataLoader.stop();
        this.dataLoader = null;
    }
    
    public void run() {
        while (this.animator != null) {
            try {
                Thread.sleep(this.sleepTime);
            }
            catch (InterruptedException ex) {
                break;
            }
            this.refresh();
            this.tickerTape.drawMoMen();
            this.repaint();
        }
    }
    
    public void refresh() {
        if (this.dataLoader.dataAvailable()) {
            this.dataArray1 = this.dataLoader.getDataStream1();
            this.dataArray2 = this.dataLoader.getDataStream2();
            this.tickerTape.setDataStream(this.dataArray1, this.dataArray2);
        }
    }
    
    public void forceRefresh() {
        if (!this.dataLoader.fetchingData()) {
            this.dataLoader.refreshData();
            while (!this.dataLoader.dataAvailable()) {}
            this.refresh();
        }
    }
    
    public void startMove() {
        this.tickerTape.startMove();
    }
    
    public void stopMove() {
        this.tickerTape.stopMove();
    }
    
    public void fastMove() {
        this.sleepTime = 20;
        this.tickerTape.fastMove();
    }
    
    public void slowMove() {
        this.sleepTime = 25;
        this.tickerTape.slowMove();
    }
    
    public void moveForward() {
        this.tickerTape.moveForward();
    }
    
    public void moveBackward() {
        this.tickerTape.moveBackward();
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawImage(this.tickerTape.scratchImage, 0, 0, null);
    }
}
