import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.util.Date;
import java.awt.Choice;
import java.awt.Button;
import java.awt.Event;
import java.awt.TextField;
import java.awt.Component;
import java.awt.Panel;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class MktView extends Applet
{
    static boolean debug;
    static int BAR_GRAPH;
    static int CANDLESTICK_GRAPH;
    static int LINE_GRAPH;
    static String[] gnameTab;
    MktModel mktModel;
    int styleNr;
    Panel westPanel;
    Component eastMenu;
    TextField textField;
    int imouse;
    int xmouse;
    int ymouse0;
    int ymouse1;
    int selectedQuoteNr;
    QuoteView quoteView;
    
    public boolean action(final Event event, final Object o) {
        if (event.target instanceof Button) {
            this.setSymbol();
        }
        else if (event.target instanceof Choice) {
            final String s = (String)o;
            if (s.equals(MktView.gnameTab[MktView.BAR_GRAPH])) {
                this.styleNr = MktView.BAR_GRAPH;
            }
            else if (s.equals(MktView.gnameTab[MktView.CANDLESTICK_GRAPH])) {
                this.styleNr = MktView.CANDLESTICK_GRAPH;
            }
            else if (s.equals(MktView.gnameTab[MktView.LINE_GRAPH])) {
                this.styleNr = MktView.LINE_GRAPH;
            }
        }
        this.repaint();
        return true;
    }
    
    public void init() {
        String parameter = this.getParameter("stockName");
        int int1;
        try {
            int1 = Integer.parseInt(this.getParameter("nrDays"), 10);
        }
        catch (NumberFormatException ex) {
            int1 = 60;
        }
        if (parameter == null) {
            parameter = "FAKE";
        }
        if (MktView.debug) {
            System.out.println("MktView.init(): stockName=" + parameter + ", nrDays=" + int1);
        }
        this.init_view();
        this.quoteView.setQuote(null);
        this.textField.setText(parameter);
        this.init_model(this.getCodeBase().getHost(), int1);
        this.setSymbol();
        try {
            this.setQuote(int1 - 1);
        }
        catch (Exception ex2) {}
    }
    
    private void init_model(final String s, final int n) {
        final Date date = new Date();
        date.setHours(0);
        date.setMinutes(0);
        date.setSeconds(0);
        this.mktModel = new MktModel(s, new Date(date.getTime() - (n - 1) * 24L * 60L * 60L * 1000L), n, MktModel.DAY_INTERVAL);
    }
    
    private void init_view() {
        final boolean b = true;
        final boolean b2 = false;
        (this.westPanel = new Panel()).setLayout(new StackLayout(2));
        this.westPanel.add(new Button("UPDATE"));
        this.textField = new TextField();
        this.westPanel.add(this.textField);
        final Panel panel = new Panel();
        panel.setLayout(new GridLayout(0, 2, (int)(b ? 1 : 0), (int)(b2 ? 1 : 0)));
        this.quoteView = new QuoteView(panel);
        this.westPanel.add(panel);
        final Choice eastMenu = new Choice();
        ((Choice)(this.eastMenu = eastMenu)).addItem(MktView.gnameTab[MktView.LINE_GRAPH]);
        eastMenu.addItem(MktView.gnameTab[MktView.BAR_GRAPH]);
        this.styleNr = MktView.BAR_GRAPH;
        eastMenu.select(MktView.gnameTab[this.styleNr]);
        this.add("Graph Style", eastMenu);
        this.setLayout(new BorderLayout());
        this.add("West", this.westPanel);
        this.add("East", eastMenu);
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        if (n >= this.xmouse && n2 >= this.ymouse0 && n2 <= this.ymouse1) {
            final int quote = (n - this.xmouse) / this.imouse;
            if (quote < this.mktModel.countQuotes()) {
                if (MktView.debug) {
                    System.out.println("MktView.mouseUp(): you selected quote number " + quote);
                }
                try {
                    this.setQuote(quote);
                    this.repaint();
                }
                catch (Exception ex) {}
            }
        }
        return true;
    }
    
    public void paint(final Graphics graphics) {
        final int n = 25;
        final int n2 = 25;
        final int n3 = 8;
        final int n4 = 4;
        final int n5 = this.westPanel.size().width + n3 + n;
        final int n6 = 30;
        final int n7 = this.size().width - (n5 + n3 + n2);
        final int n8 = this.size().height - (n6 + n3);
        if (MktView.debug) {
            System.out.println("MktView.paint(): xg=" + n5 + ", yg=" + n6);
        }
        if (this.mktModel.isEmpty()) {
            graphics.drawString("NO MKT DATA FOR " + this.mktModel.getName() + ", TRY 'FAKE'", n5, n6);
            return;
        }
        try {
            final int n9 = n8 - this.paint_graph_dates_axis(graphics, n5 + n4, n6, n6 + n8, n7 - n4);
            this.paint_graph_border(graphics, n5, n6, n7, n9, n4);
            this.paint_graph_volumes(graphics, n5, n6, n7, n9, n4, n, this.paint_graph_guidelines(graphics, n5, n6, n7, n9, n4, this.paint_graph_prices(graphics, n5, n6, n7, n9, n2)));
            if (MktView.debug) {
                System.out.println("........PAINTING is done");
            }
        }
        catch (Exception ex) {}
    }
    
    private void paint_graph_border(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final int n5) {
        if (MktView.debug) {
            System.out.println("-------.paint_graph_border(): xg=" + n + ", yg=" + n2 + "wg=" + n3 + ", hg=" + n4 + "tb2=" + n5);
        }
        final Color color = graphics.getColor();
        final int n6 = n5 / 2;
        graphics.setColor(Color.black);
        graphics.fillRect(n, n2, n3, n6);
        graphics.fillRect(n, n2, n6, n4);
        graphics.fillRect(n, n2 + n4 - n6, n3, n6);
        graphics.fillRect(n + n3 - n6, n2, n6, n4);
        graphics.setColor(Color.white);
        graphics.fillRect(n + n5, n2 + n4 - n5, n3 - 2 * n5, n6);
        graphics.fillRect(n + n3 - n5, n2 + n5, n6, n4 - n5 - n6);
        graphics.setColor(color);
    }
    
    private int paint_graph_dates_axis(final Graphics graphics, final int n, final int n2, final int n3, final int n4) throws Exception {
        final int n5 = 2;
        final int n6 = 4;
        final int n7 = 2;
        final Font font = new Font("Courier", 0, 8);
        final int n8 = n3 - new Font("Courier", 0, 10).getSize();
        final int n9 = n8 - (n7 + font.getSize());
        final int n10 = n9 - n6;
        final int countQuotes = this.mktModel.countQuotes();
        final int n11 = n4 / countQuotes;
        final int n12 = n4 - countQuotes * n11;
        int n14;
        final int n13 = n14 = n + n12;
        if (MktView.debug) {
            System.out.println("-------.paint_dates_axis(): xd=" + n + ", yd0=" + n10 + ", wd2=" + n4 + ", ns=" + countQuotes + ", ds=" + n11 + ", dd=" + n12);
        }
        for (int i = 0; i < countQuotes; ++i, n14 += n11) {
            final Color color = graphics.getColor();
            if (i == this.selectedQuoteNr) {
                graphics.setColor(Color.yellow);
            }
            graphics.drawLine(n14, n10, n14, n9);
            final Date date = this.mktModel.getQuote(i).getDate();
            if (date.getDay() == 1) {
                graphics.drawString(new Integer(date.getDate()).toString(), n14 - n5, n8);
            }
            if (i == this.selectedQuoteNr) {
                graphics.setColor(color);
            }
            if (date.getDate() == 15) {
                graphics.drawString((new String[] { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" })[date.getMonth()], n14, n3);
            }
        }
        this.setClickableDates(n13, n11, n2, n8);
        return n3 - n10;
    }
    
    private int paint_graph_prices(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final int n5) throws Exception {
        final int n6 = 15;
        final int n7 = 1;
        int i = 1;
        final int maxPrice = this.mktModel.maxPrice();
        final int minPrice = this.mktModel.minPrice();
        if (MktView.debug) {
            System.out.println("*** maxPrice=" + Pretty.udollars(maxPrice) + ", minPrice=" + Pretty.udollars(minPrice));
        }
        int n8 = 0;
        int n9 = 0;
        int n10 = 0;
        while (i < 800) {
            for (int j = n6; j >= n7; --j) {
                final int n11 = i * j;
                if (n11 > maxPrice) {
                    final int n12 = 3 * Pretty.metric(minPrice, maxPrice, n11) + 2 * -(n4 - j * (n4 / j)) + j;
                    if (n12 > n8) {
                        n9 = i;
                        n10 = j;
                        n8 = n12;
                    }
                }
            }
            if (i < 8) {
                i *= 2;
            }
            else {
                i += 8;
            }
        }
        if (MktView.debug) {
            System.out.println("***UNITS " + n10 + " @ " + Pretty.udollars(n9) + " => " + Pretty.udollars(n10 * n9) + " (" + n8 + ")");
        }
        final int n13 = 4;
        final int n14 = 4;
        final int n15 = n + n3;
        final int n16 = n15 + n14;
        final int n17 = n16 + 2;
        final int n18 = n10;
        final int n19 = n4 / n18;
        int n21;
        final int n20 = n21 = n2 + n4 - 1;
        if (MktView.debug) {
            System.out.println("-------.paint_graph_prices_axis(): xp0=" + n15 + ", yp1=" + n20 + ", np=" + n18);
        }
        for (int k = 0; k < n18 + 1; ++k, n21 -= n19) {
            graphics.drawLine(n15, n21, n16, n21);
            graphics.drawString(Pretty.udollars(k * n9).toString(), n17, n21 + n13);
        }
        final int n22 = 2;
        final int n23 = n22 / 2;
        final int n24 = n10 * n9;
        int n25 = 0;
        int n26 = 0;
        for (int l = 0; l < this.mktModel.countQuotes(); ++l) {
            final int lastPrice = this.mktModel.getQuote(l).getLastPrice();
            final int n27 = this.xmouse + l * this.imouse;
            final int n28 = n20 - lastPrice * n4 / n24;
            final Color color = graphics.getColor();
            if (l == this.selectedQuoteNr) {
                graphics.setColor(Color.yellow);
            }
            if (this.styleNr == MktView.BAR_GRAPH) {
                graphics.drawLine(n27, n20 - this.mktModel.getQuote(l).getHigh() * n4 / n24, n27, n20 - this.mktModel.getQuote(l).getLow() * n4 / n24);
                graphics.drawLine(n27 - n22, n28, n27 + n22, n28);
            }
            else if (this.styleNr == MktView.LINE_GRAPH) {
                graphics.drawOval(n27 - n23, n28 - n23, n22, n22);
            }
            if (l == this.selectedQuoteNr) {
                graphics.setColor(color);
            }
            if (this.styleNr == MktView.LINE_GRAPH && l > 0) {
                graphics.drawLine(n25, n26, n27, n28);
            }
            n25 = n27;
            n26 = n28;
        }
        return n10;
    }
    
    private int paint_graph_guidelines(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final int n5, final int n6) throws Exception {
        int n7 = 1;
        for (int i = 1; i < n6; ++i) {
            if (n6 % i == 0) {
                n7 = i;
            }
        }
        final int n8 = n4 / n6 * (n6 / n7);
        int n9 = n2 + n4 - n8;
        if (MktView.debug) {
            System.out.println("***GUIDES " + n7 + "(units=" + n6 + ", ppg=" + n8 + ")");
        }
        for (int j = 1; j < n7; ++j) {
            graphics.drawLine(n + n5, n9, n + n3 - 2 * n5, n9);
            n9 -= n8;
        }
        return n7;
    }
    
    private void paint_graph_volumes(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7) throws Exception {
        final Color color = graphics.getColor();
        graphics.setColor(Color.magenta);
        final int n8 = 10;
        final int n9 = n - 4;
        final int n10 = n9 - n8;
        final int n11 = n4 / n7;
        final int n12 = n2 + n4;
        final int n13 = n12 - n5;
        int n14 = n12;
        if (MktView.debug) {
            System.out.println("-------.paint_graph_volumes_axis(): xv0=" + n + ", yv2=" + n12 + ", nv=" + n7);
        }
        for (int i = 0; i <= n7; ++i, n14 -= n11) {
            if (i != n7) {
                graphics.drawLine(n, n14, n9, n14);
            }
            if (i != 0) {
                graphics.drawString(new Integer(i).toString(), n10, n14);
            }
        }
        int n15 = 1;
        final int n16 = n7 / 2;
        final int maxVolume = this.mktModel.maxVolume();
        for (int j = 10000000; j > 1; j /= 10) {
            if (j * n16 > maxVolume) {
                n15 = j;
            }
        }
        int xmouse = this.xmouse;
        for (int k = 0; k <= this.mktModel.countQuotes(); ++k) {
            if (k == this.selectedQuoteNr) {
                graphics.setColor(Color.yellow);
            }
            graphics.drawLine(xmouse, n13, xmouse, n13 - this.mktModel.getQuote(k).getVolume() * n11 / n15);
            if (k == this.selectedQuoteNr) {
                graphics.setColor(Color.magenta);
            }
            xmouse += this.imouse;
        }
        graphics.setColor(color);
    }
    
    private void setClickableDates(final int xmouse, final int imouse, final int ymouse0, final int ymouse2) {
        this.xmouse = xmouse;
        this.imouse = imouse;
        this.ymouse0 = ymouse0;
        this.ymouse1 = ymouse2;
    }
    
    private void setQuote(final int selectedQuoteNr) {
        try {
            this.quoteView.setQuote(this.mktModel.getQuote(selectedQuoteNr));
            this.selectedQuoteNr = selectedQuoteNr;
        }
        catch (Exception ex) {
            this.quoteView.setQuote(null);
        }
    }
    
    private void setSymbol() {
        String s = this.textField.getText().trim();
        if (s.equals("")) {
            s = this.mktModel.getName();
        }
        if (MktView.debug) {
            System.out.println("MktView.setSymbol(): stockName=" + s + "\n");
        }
        this.textField.setText(s);
        this.mktModel.setSymbol(s);
        this.setQuote(this.selectedQuoteNr);
        if (MktView.debug) {
            System.out.println("MktView.setSymbol(): DONE\n");
        }
    }
    
    static {
        MktView.CANDLESTICK_GRAPH = 1;
        MktView.LINE_GRAPH = 2;
        MktView.gnameTab = new String[] { "Bar Graph", "Candlesquote Graph", "Line Graph", "Line Graph with Moving Average" };
    }
}
