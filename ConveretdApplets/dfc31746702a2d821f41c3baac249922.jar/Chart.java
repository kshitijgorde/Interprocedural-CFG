import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.PopupMenu;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Checkbox;
import java.awt.Event;
import java.awt.Panel;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Chart extends Applet implements Runnable
{
    private volatile Thread plotterThread;
    Parameters param;
    Plotter currentPlotter;
    PlotterPopup currentPlotterPopup;
    public Panel dataPanel;
    
    public Chart() {
        this.dataPanel = null;
    }
    
    public boolean action(final Event event, final Object o) {
        event.toString();
        if (event.target instanceof Checkbox) {
            ((Checkbox)event.target).getLabel().toString().trim().toUpperCase();
        }
        else if (event.target instanceof Choice) {
            final String upperCase = event.arg.toString().trim().toUpperCase();
            if (upperCase.equals("UNZOOM")) {
                this.currentPlotter.doUNZOOM();
            }
            else if (upperCase.equals("REFRESH")) {
                this.currentPlotter.doREFRESH();
            }
            else if (upperCase.equals("MOVING AVERAGE")) {
                this.currentPlotter.doMovingAverage();
            }
            else if (upperCase.equals("REMOVE ALL")) {
                this.currentPlotter.removeAllTrendLine();
            }
            else if (upperCase.equals("REMOVE LAST")) {
                this.currentPlotter.removeLastTrendLine();
            }
            else if (upperCase.equals("BAR")) {
                this.currentPlotter.doBAR();
            }
            else if (upperCase.equals("ERASE ALL")) {
                this.currentPlotter.removeAllIndicators();
            }
            else if (upperCase.equals("ERASE LAST")) {
                this.currentPlotter.removeLastIndicators();
            }
            else if (upperCase.equals("RSI")) {
                this.currentPlotter.doRSI();
            }
            else if (upperCase.equals("ROC")) {
                this.currentPlotter.doROC();
            }
            else if (upperCase.equals("MACD")) {
                this.currentPlotter.doMACD();
            }
            else if (upperCase.equals("EMA")) {
                this.currentPlotter.doEMA();
            }
            else if (upperCase.equals("BOLLINGER BAND")) {
                this.currentPlotter.doBollinger();
            }
            else if (upperCase.equals("COMMODITY CHANNEL INDEX")) {
                this.currentPlotter.doCci();
            }
            else if (upperCase.equals("ACCUMULATION DISTRIBUTION")) {
                this.currentPlotter.doAccumulation();
            }
            else if (upperCase.equals("ON BALANCE VOLUME")) {
                this.currentPlotter.doOBV();
            }
            else if (upperCase.equals("CANDLE")) {
                this.currentPlotter.doCANDLE();
            }
            else if (upperCase.equals("LINE")) {
                this.currentPlotter.doLINE();
            }
            else if (upperCase.equals("+")) {
                this.currentPlotter.doCursor(true, true);
            }
            else if (upperCase.equals("|")) {
                this.currentPlotter.doCursor(true, false);
            }
            else if (upperCase.equals("-")) {
                this.currentPlotter.doCursor(false, true);
            }
            else if (upperCase.equals("NONE")) {
                this.currentPlotter.doCursor(false, false);
            }
            ((Choice)event.target).select(0);
        }
        return true;
    }
    
    public String getAppletInfo() {
        return this.param.Message[0];
    }
    
    public String[][] getParameterInfo() {
        return new String[][] { { "defaultFgColor", "RRGGBB", "Default Foreground Color" } };
    }
    
    public void init() {
        this.param = new Parameters(this);
        this.setBackground(Parameters.chartBGColor);
        this.setForeground(Parameters.chartFGColor);
        this.repaint();
        this.setLayout(new BorderLayout(3, 3));
        if (Parameters.PopupOK) {
            try {
                final PopupMenu popupMenu = new PopupMenu();
                Parameters.PopupOK = true;
            }
            catch (Exception ex) {
                Parameters.PopupOK = false;
            }
        }
        if (!Parameters.PopupOK) {
            final Panel panel = new Panel();
            panel.setLayout(new FlowLayout(1));
            panel.setBackground(Parameters.chartFGColor);
            panel.setForeground(Parameters.chartBGColor);
            final Choice choice = new Choice();
            choice.setForeground(Parameters.chartFGColor);
            choice.addItem("Chart Type");
            choice.addItem("----------");
            choice.addItem("Bar");
            choice.addItem("Candle");
            choice.addItem("Line");
            panel.add(choice);
            final Choice choice2 = new Choice();
            choice2.setForeground(Parameters.chartFGColor);
            choice2.addItem("Cursor");
            choice2.addItem("------");
            choice2.addItem("+");
            choice2.addItem("|");
            choice2.addItem("-");
            choice2.addItem("none");
            panel.add(choice2);
            final Choice choice3 = new Choice();
            choice3.setForeground(Parameters.chartFGColor);
            choice3.addItem("Tools");
            choice3.addItem("-----");
            choice3.addItem("Unzoom");
            choice3.addItem("Refresh");
            panel.add(choice3);
            final Choice choice4 = new Choice();
            choice4.setForeground(Parameters.chartFGColor);
            choice4.addItem("Trend Line");
            choice4.addItem("----------");
            choice4.addItem("Remove All");
            choice4.addItem("Remove Last");
            panel.add(choice4);
            final Choice choice5 = new Choice();
            choice5.setForeground(Parameters.chartFGColor);
            choice5.addItem("Indicators");
            choice5.addItem("----------");
            choice5.addItem("Moving Average");
            choice5.addItem("EMA");
            choice5.addItem("RSI");
            choice5.addItem("ROC");
            choice5.addItem("MACD");
            choice5.addItem("Bollinger Band");
            choice5.addItem("On Balance Volume");
            choice5.addItem("Accumulation Distribution");
            choice5.addItem("Commodity Channel Index");
            choice5.addItem("Erase All");
            choice5.addItem("Erase Last");
            panel.add(choice5);
            this.add("North", panel);
            if (Parameters.applet.getParameter("icode") != null) {
                this.add("Center", this.currentPlotter = new Plotter(Parameters.paramString));
            }
        }
        else if (Parameters.applet.getParameter("icode") != null) {
            this.add("Center", this.currentPlotterPopup = new PlotterPopup(Parameters.paramString));
        }
    }
    
    public void run() {
        while (this.plotterThread == Thread.currentThread()) {
            this.repaint();
            try {
                Thread.sleep(500L);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public void start() {
        if (this.plotterThread == null) {
            (this.plotterThread = new Thread(this, "Chart")).start();
        }
    }
    
    public void stop() {
        this.plotterThread = null;
    }
}
