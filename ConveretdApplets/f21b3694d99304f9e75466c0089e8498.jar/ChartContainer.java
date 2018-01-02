import java.awt.Graphics;
import java.awt.Event;
import java.awt.Font;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.Date;
import java.awt.MediaTracker;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.Checkbox;
import java.awt.Component;
import java.applet.Applet;
import java.awt.TextField;
import java.awt.Choice;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

public class ChartContainer extends Panel
{
    Panel[] controls;
    public Chart chart;
    public LabelBar lb;
    Choice cboTools;
    AppFrame myFrame;
    ImageButton detach;
    TextField txtSymbol;
    Panel container;
    Applet applet;
    Component comp;
    Checkbox chartColors;
    boolean hostOK;
    boolean hostsLoaded;
    ImageButton barButton;
    ImageButton lineButton;
    ImageButton areaButton;
    ImageButton detachButtonOptions;
    Image detachImage;
    Image attachImage;
    Color bgColor;
    Color txtColor;
    Color baseColor;
    int selectedType;
    
    ChartContainer(final Applet applet1, final Component component, final boolean flag) {
        this.bgColor = new Color(254, 254, 254);
        this.txtColor = new Color(100, 149, 237);
        this.baseColor = new Color(255, 255, 255);
        this.selectedType = 0;
        this.controls = new Panel[3];
        this.hostOK = false;
        this.hostsLoaded = false;
        this.applet = applet1;
        this.comp = component;
        final boolean flag2 = System.getProperty("os.name").indexOf("Mac") >= 0;
        (this.chart = new Chart(this.applet)).hideRight();
        this.lb = new LabelBar(this.applet);
        for (int i = 0; i < this.controls.length; ++i) {
            (this.controls[i] = new Panel()).setLayout(new GridBagLayout());
            this.controls[i].setBackground(this.baseColor);
            this.controls[i].setForeground(Color.black);
        }
        this.txtSymbol = new TextField("", 3);
        final Image image = this.applet.getImage(this.applet.getCodeBase(), "images/bar1.gif");
        final Image image2 = this.applet.getImage(this.applet.getCodeBase(), "images/line1.gif");
        final Image image3 = this.applet.getImage(this.applet.getCodeBase(), "images/area1.gif");
        this.detachImage = this.applet.getImage(this.applet.getCodeBase(), "images/detach1.gif");
        this.attachImage = this.applet.getImage(this.applet.getCodeBase(), "images/attach1.gif");
        final MediaTracker mediatracker = new MediaTracker(this);
        mediatracker.addImage(image, 1);
        mediatracker.addImage(image2, 1);
        mediatracker.addImage(image3, 1);
        mediatracker.addImage(this.attachImage, 0);
        mediatracker.addImage(this.detachImage, 0);
        if (this.applet.getParameter("watermark") != null) {
            System.out.println(String.valueOf("Watermark: ").concat(String.valueOf(this.applet.getParameter("watermark"))));
            mediatracker.addImage(this.chart.watermark = this.applet.getImage(this.applet.getCodeBase(), "images/log2.gif"), 0);
        }
        try {
            mediatracker.waitForAll();
        }
        catch (Exception ex) {}
        (this.chartColors = new Checkbox(flag2 ? "Color Indicator" : "Color Indicator")).setState(true);
        this.chart.setColorChart(true);
        this.chart.optionsChart = true;
        final Date date = new Date();
        final Panel panel = new Panel();
        panel.setLayout(new GridLayout(13, 1));
        panel.add("North", this.detachButtonOptions = new ImageButton(this.detachImage));
        final ImageButton imagebutton = new ImageButton(30, 30);
        final ImageButton imagebutton2 = new ImageButton(20, 10);
        (this.cboTools = new Choice()).addItem("..TrendLines..");
        this.cboTools.addItem("Add Trend");
        this.cboTools.addItem("Del Sel.Trend");
        this.cboTools.addItem("Del All Trd");
        this.cboTools.addItem("Unzoom");
        this.chart.setType(1);
        this.barButton = new ImageButton(image);
        this.lineButton = new ImageButton(image2);
        this.areaButton = new ImageButton(image3);
        this.barButton.setBackground(this.baseColor);
        this.lineButton.setBackground(this.baseColor);
        this.areaButton.setBackground(this.baseColor);
        imagebutton.setBackground(this.baseColor);
        final Panel panel2 = new Panel();
        panel2.setLayout(new BorderLayout());
        panel2.add("West", new ImageButton(20, 8));
        if (flag) {
            this.detach = new ImageButton(this.detachImage);
            final Panel panel3 = new Panel();
            panel3.setLayout(new BorderLayout());
            panel3.add("North", this.detach);
            panel3.add("South", new ImageButton(20, 3));
            panel2.add("South", panel3);
        }
        final MediaTracker mediatracker2 = new MediaTracker(this);
        this.controls[0].setLayout(new FlowLayout(7));
        this.controls[0].add(new ImageButton(22, 10));
        this.controls[0].add(this.barButton);
        this.controls[0].add(this.lineButton);
        this.controls[0].add(this.areaButton);
        this.controls[0].add(this.cboTools);
        final Panel panel4 = new Panel();
        panel4.setLayout(new BorderLayout());
        panel4.add("Center", this.chart);
        (this.container = new Panel()).setLayout(new BorderLayout());
        this.container.add("East", panel);
        this.container.add("Center", panel4);
        this.container.add("South", this.lb);
        this.container.add("North", this.controls[0]);
        this.setLayout(new GridLayout(1, 1));
        this.add(this.container);
        this.chart.requestFocus();
        this.txtSymbol.requestFocus();
        this.cboTools.setBackground(Color.white);
        this.cboTools.setForeground(Color.black);
        this.txtSymbol.setBackground(Color.white);
        this.txtSymbol.setForeground(Color.black);
        this.chart.colorChart = true;
        this.chartColors.setState(true);
        this.chart.setColorChart(true);
        final Font font = new Font("sanserif", 0, 10);
        this.chartColors.setFont(font);
        this.validate();
        if (this.applet.getParameter("autoDetach") != null && this.applet.getParameter("autoDetach").equalsIgnoreCase("true")) {
            this.detach();
        }
    }
    
    ChartContainer(final Applet applet1, final Component component, final boolean flag, final int i) {
        this(applet1, component, flag);
    }
    
    public void attach() {
        if (this.myFrame == null) {
            return;
        }
        this.myFrame.hide();
        this.myFrame.removeAll();
        this.setLayout(new GridLayout(1, 1));
        this.add(this.container);
        this.myFrame = null;
        this.detach.setImage(this.detachImage);
        this.detachButtonOptions.setImage(this.detachImage);
        this.validate();
        this.chart.updateChart();
    }
    
    public void destroy() {
        this.chart.destroy();
    }
    
    public void detach() {
        if (this.myFrame == null) {
            this.myFrame = new AppFrame(this.comp);
            this.removeAll();
            if (this.detach != null) {
                this.detach.setImage(this.attachImage);
            }
            this.detachButtonOptions.setImage(this.attachImage);
            this.myFrame.add("Center", this.container);
            this.myFrame.show();
            this.chart.updateChart();
        }
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 13100) {
            this.hostsLoaded = true;
            System.out.println(String.valueOf("hostOK: ").concat(String.valueOf(this.hostOK)));
            this.chart.updateChart();
            return true;
        }
        if (event.id == 1001 && (event.target == this.detach || event.target == this.detachButtonOptions)) {
            if (this.myFrame == null) {
                this.detach();
            }
            else {
                this.attach();
            }
            return true;
        }
        if (event.id == 1001 && event.target == this.chartColors) {
            this.chart.setColorChart(this.chartColors.getState());
            return true;
        }
        if (event.id == 1001 && event.target == this.barButton) {
            this.selectedType = 1;
            this.chart.setType(1);
            return true;
        }
        if (event.id == 1001 && event.target == this.lineButton) {
            this.selectedType = 2;
            this.chart.setType(2);
            return true;
        }
        if (event.id == 1001 && event.target == this.areaButton) {
            this.selectedType = 3;
            this.chart.setType(3);
            return true;
        }
        if (event.id == 12322) {
            this.cboTools.select(0);
            return true;
        }
        if (event.id == 1001 && event.target == this.cboTools) {
            if (this.cboTools.getSelectedIndex() == 0) {
                this.chart.trend = false;
                this.cboTools.select(0);
            }
            if (this.cboTools.getSelectedIndex() == 1) {
                this.chart.easyDragging = true;
                this.cboTools.select(0);
            }
            else if (this.cboTools.getSelectedIndex() == 2) {
                this.chart.selected = true;
                this.cboTools.select(0);
            }
            else if (this.cboTools.getSelectedIndex() == 3) {
                this.chart.removeAllTrendlines();
                this.cboTools.select(0);
            }
            else if (this.cboTools.getSelectedIndex() == 4) {
                this.chart.drawcnt = 0;
                this.chart.unZoom();
                this.cboTools.select(0);
            }
            return true;
        }
        if (event.id == 9001) {
            this.txtSymbol.requestFocus();
            final String s = (String)event.arg;
            this.txtSymbol.setText(s);
            System.out.println(String.valueOf("Event 9001: Symbol is ").concat(String.valueOf(s)));
            if (s.length() == 1) {
                this.txtSymbol.select(s.length(), s.length());
            }
            else {
                this.txtSymbol.select(0, s.length());
            }
            return true;
        }
        if (event.id == 9003) {
            final StockDetail stockdetail = (StockDetail)event.arg;
            this.lb.setRightDetail(stockdetail);
            return true;
        }
        if (event.id == 9002) {
            this.lb.setDetail(this.chart.currentDetail, this.chart.currentValue);
            return true;
        }
        if (event.id == 9006) {
            this.lb.showMessage((String)event.arg);
            return true;
        }
        if (event.id == 9007) {
            this.lb.hideMessage();
            return true;
        }
        if (event.id == 9004) {
            this.lb.removeRightDetail();
            return true;
        }
        if (event.id == 9101) {
            if (this.detach != null) {
                this.attach();
            }
            return true;
        }
        return true;
    }
    
    public void paint(final Graphics g) {
    }
}
