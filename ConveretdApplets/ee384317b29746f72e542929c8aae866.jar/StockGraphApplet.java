import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class StockGraphApplet extends Applet implements ImageObserver, Runnable
{
    public StockGraph graph;
    public StockGraphDataLoader dataLoader;
    private int width;
    private int height;
    private int refreshTime;
    private String subElements;
    Thread refresher;
    
    public StockGraphApplet() {
        this.refresher = null;
    }
    
    public void init() {
        (this.dataLoader = new StockGraphDataLoader()).setURL(this.getValue("url", ""));
        this.dataLoader.setDataStream(this.getValue("data", "JAN|1|2;"));
        this.width = this.getSize().width;
        this.height = this.getSize().height;
        this.refreshTime = Integer.valueOf(this.getValue("refreshTime", "5")) * 1000;
        this.subElements = this.getValue("subElements", "0");
        this.graph = new StockGraph(this, this.width, this.height);
        final String origin2Zero = this.getValue("origin2Zero", "true").toUpperCase().trim();
        this.graph.setOrigin(origin2Zero);
        this.dataLoader.setOrigin(origin2Zero);
        this.graph.setImages(this.getValue("title", ""), this.getValue("xCaption", ""), this.getValue("yCaption", ""), this.getValue("background", ""), this.getValueUpper("alignment", "center"));
        this.graph.setMargins(this.getValue("topMargin", "0"), this.getValue("leftMargin", "0"));
        this.graph.setchartAdjustment(this.getValue("chartAdjust", "0"));
        this.graph.setDivisions(this.getValue("divisions", "4"), this.getValue("subDivision1", "0"), this.getValue("subDivision2", "0"));
        this.graph.set3dValues(this.getValue("margin3d", "1"), this.getValue("depth3d", "10"));
        this.graph.setColors(this.getValue("foreColor", "black"), this.getValue("backColor", "white"), this.getValue("lineColor", "darkgray"), this.getValue("subColor", "gray"), this.getValue("chartColor", "white"), this.getValue("lineGraph1Color", "red"));
        this.graph.setFonts(this.getValue("font1", "arial"), this.getValue("fontSize", "10"));
    }
    
    private String getValueUpper(String param, final String deflt) {
        param = this.getValue(param, deflt);
        return param.toUpperCase();
    }
    
    private String getValue(String param, final String deflt) {
        try {
            final String param2 = this.getParameter(param).toUpperCase();
            param = this.getParameter(param);
        }
        catch (Exception e) {
            param = deflt;
        }
        return param;
    }
    
    public String[][] getParameterInfo() {
        final String[][] info = { { "background", "string", "Background - Graphix" }, { "title", "string", "title - Graphix" }, { "xcaption", "string", "X Caption - Graphix" }, { "ycaption", "string", "Y Caption - Graphix" }, { "alignment", "string", "Graphix Alignment" }, { "topmargin", "int", "Top and Bottom margins" }, { "leftmargin", "int", "Left and Right margins" }, { "adjustMaxMin", "string", "Flag for MAx/Min Recalculation" }, { "url", "string", "URL input" }, { "data", "string", "Graph/chart Data" }, { "refreshTime", "int", "Refresh Interval in seconds" }, { "divisions", "int", "Vertical divisions" }, { "forecolor", "string", "Foreground color" }, { "backcolor", "string", "Background color" }, { "linecolor", "string", "Line color" }, { "chartcolor", "string", "Chart Background color" }, { "colorstyle", "string", "Color style" }, { "font1", "string", "Chart Font" }, { "fontSize", "int", "Size of font" }, { "margin3d", "int", "margin for 3d" }, { "depth3d", "int", "depth for 3d" }, { "adjustChart", "int", "maximum and minimum adjustment" } };
        return info;
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
    
    public void update(final Graphics g) {
        this.paint(g);
    }
    
    public void paint(final Graphics g) {
        g.drawImage(this.graph.scratchImage, 0, 0, null);
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
                this.graph.setGraphVector(this.dataLoader.getGraphStream1(), this.dataLoader.getGraphDataNames(), this.dataLoader.getMaxValue(), this.dataLoader.getMinValue(), this.subElements);
                this.drawAll();
            }
            try {
                Thread.sleep(this.refreshTime);
            }
            catch (InterruptedException e) {
                break;
            }
        }
    }
}
