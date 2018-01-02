import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class MarketGraphApplet extends Applet implements ImageObserver, Runnable
{
    public MarketGraph graph;
    public MarketGraphDataLoader dataLoader;
    private int width;
    private int height;
    private int refreshTime;
    private String subElements;
    Thread refresher;
    
    public MarketGraphApplet() {
        this.refresher = null;
    }
    
    public void init() {
        (this.dataLoader = new MarketGraphDataLoader()).setURL(this.getValue("url", ""));
        this.dataLoader.setDataStream(this.getValue("data", "10:00|1;?10:00|1;?"));
        this.width = this.getSize().width;
        this.height = this.getSize().height;
        this.refreshTime = Integer.valueOf(this.getValue("refreshTime", "5")) * 1000;
        this.subElements = this.getValue("subElements", "0");
        this.graph = new MarketGraph(this, this.width, this.height);
        final String trim = this.getValue("origin2Zero", "true").toUpperCase().trim();
        this.graph.setOrigin(trim);
        this.dataLoader.setOrigin(trim);
        this.graph.setImages(this.getValue("title", ""), this.getValue("xCaption", ""), this.getValue("yCaption", ""), this.getValue("background", ""), this.getValueUpper("alignment", "center"));
        this.graph.setMargins(this.getValue("topMargin", "0"), this.getValue("leftMargin", "0"));
        this.graph.setchartAdjustment(this.getValue("chartAdjust", "0"));
        this.graph.setDivisions(this.getValue("divisions", "4"), this.getValue("subDivision1", "0"), this.getValue("subDivision2", "0"));
        this.graph.set3dValues(this.getValue("margin3d", "1"), this.getValue("depth3d", "10"));
        this.graph.setColors(this.getValue("foreColor", "black"), this.getValue("backColor", "white"), this.getValue("lineColor", "darkgray"), this.getValue("subColor", "gray"), this.getValue("chartColor", "white"), this.getValue("barColor", "black"), this.getValue("lineGraph1Color", "blue"));
        this.graph.setFonts(this.getValue("font1", "arial"), this.getValue("fontSize", "10"));
    }
    
    private String getValueUpper(String value, final String s) {
        value = this.getValue(value, s);
        return value.toUpperCase();
    }
    
    private String getValue(String parameter, final String s) {
        try {
            this.getParameter(parameter).toUpperCase();
            parameter = this.getParameter(parameter);
        }
        catch (Exception ex) {
            parameter = s;
        }
        return parameter;
    }
    
    public String[][] getParameterInfo() {
        return new String[][] { { "background", "string", "Background - Graphix" }, { "title", "string", "title - Graphix" }, { "xcaption", "string", "X Caption - Graphix" }, { "ycaption", "string", "Y Caption - Graphix" }, { "alignment", "string", "Graphix Alignment" }, { "topmargin", "int", "Top and Bottom margins" }, { "leftmargin", "int", "Left and Right margins" }, { "adjustMaxMin", "string", "Flag for MAx/Min Recalculation" }, { "url", "string", "URL input" }, { "data", "string", "Graph/chart Data" }, { "divisions", "int", "Vertical divisions" }, { "forecolor", "string", "Foreground color" }, { "backcolor", "string", "Background color" }, { "linecolor", "string", "Line color" }, { "chartcolor", "string", "Chart Background color" }, { "colorstyle", "string", "Color style" }, { "font1", "string", "Chart Font" }, { "fontSize", "int", "Size of font" }, { "margin3d", "int", "margin for 3d" }, { "depth3d", "int", "depth for 3d" }, { "adjustChart", "int", "maximum and minimum adjustment" } };
    }
    
    public void drawAll() {
        this.graph.drawBackground();
        this.graph.drawCartesianPlane();
        this.graph.plotData();
        this.repaint();
    }
    
    public void refresh() {
        this.graph.plotData();
        this.repaint();
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawImage(this.graph.scratchImage, 0, 0, null);
    }
    
    public void start() {
        if (this.refresher != null) {
            return;
        }
        (this.refresher = new Thread(this)).start();
    }
    
    public void stop() {
        if (this.refresher != null) {
            this.refresher.stop();
        }
        this.refresher = null;
    }
    
    public void run() {
        while (this.refresher != null) {
            if (!this.dataLoader.fetchingData()) {
                this.dataLoader.refreshData();
                if (this.dataLoader.getGraphStream1().size() == this.dataLoader.getGraphStream2().size()) {
                    this.graph.setGraphVector(this.dataLoader.getGraphStream1(), this.dataLoader.getGraphStream2(), this.dataLoader.getGraphDataNames(), this.dataLoader.getMaxValue(), this.dataLoader.getMinValue(), this.subElements, this.dataLoader.getCloseTime());
                    this.drawAll();
                }
            }
            try {
                Thread.sleep(this.refreshTime);
            }
            catch (InterruptedException ex) {
                break;
            }
        }
    }
}
