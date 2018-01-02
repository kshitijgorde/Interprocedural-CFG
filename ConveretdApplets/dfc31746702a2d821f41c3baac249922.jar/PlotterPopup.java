import java.awt.Component;
import java.awt.Event;
import java.awt.CheckboxMenuItem;
import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.PopupMenu;

// 
// Decompiled by Procyon v0.5.30
// 

class PlotterPopup extends Plotter
{
    private PopupMenu popup;
    
    PlotterPopup(final String s) {
        this.initPlotter(s);
        try {
            (this.popup = new PopupMenu("Chart")).add(new MenuItem("Unzoom"));
            this.popup.add(new MenuItem("Refresh"));
            final Menu menu = new Menu("Chart Type");
            final CheckboxMenuItem chartTypeMenuItem = new CheckboxMenuItem("Bar");
            chartTypeMenuItem.setState(true);
            menu.add(chartTypeMenuItem);
            super.chartTypeMenuItem = chartTypeMenuItem;
            final CheckboxMenuItem checkboxMenuItem = new CheckboxMenuItem("Candle");
            checkboxMenuItem.setState(false);
            menu.add(checkboxMenuItem);
            final CheckboxMenuItem checkboxMenuItem2 = new CheckboxMenuItem("Line");
            checkboxMenuItem2.setState(false);
            menu.add(checkboxMenuItem2);
            this.popup.add(menu);
            final Menu menu2 = new Menu("Trend");
            this.popup.add(menu2);
            menu2.add(new MenuItem("Remove All"));
            menu2.add(new MenuItem("Remove Last"));
            final Menu menu3 = new Menu("Indicators");
            this.popup.add(menu3);
            menu3.add(new MenuItem("Moving Average"));
            menu3.add(new MenuItem("EMA"));
            menu3.add(new MenuItem("ROC"));
            menu3.add(new MenuItem("RSI"));
            menu3.add(new MenuItem("MACD"));
            menu3.add(new MenuItem("Bollinger Band"));
            menu3.add(new MenuItem("On Balance Volume"));
            menu3.add(new MenuItem("Accumulation Distribution"));
            menu3.add(new MenuItem("Commodity Channel Index"));
            menu3.add(new MenuItem("Stochastic Oscillator"));
            menu3.add(new MenuItem("Momentum Indicator"));
            menu3.add(new MenuItem("Remove All"));
            menu3.add(new MenuItem("Remove Last"));
            final Menu menu4 = new Menu("Cursor");
            this.popup.add(menu4);
            menu4.add(new MenuItem(" +"));
            menu4.add(new MenuItem(" |"));
            menu4.add(new MenuItem(" -"));
            menu4.add(new MenuItem("None"));
            this.add(this.popup);
        }
        catch (Exception ex) {
            Parameters.PopupOK = false;
        }
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        this.moveCursor(n, n2);
        if ((event.modifiers & 0x4) != 0x0 && this.popup != null) {
            this.popup.show(this, n, n2);
        }
        return super.mouseDown(event, n, n2);
    }
}
