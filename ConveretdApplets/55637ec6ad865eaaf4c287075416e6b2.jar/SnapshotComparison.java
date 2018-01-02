import java.util.StringTokenizer;
import java.awt.Graphics;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Color;
import java.net.URL;
import java.awt.Event;
import java.awt.Font;
import java.awt.Choice;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class SnapshotComparison extends Applet
{
    String[] criteria;
    String[] items;
    double[][] data;
    int[] formats;
    int dataSet;
    ClickableScale scale;
    Choice criterionChoice;
    int space;
    int choiceLeft;
    int choiceW;
    int choiceH;
    Font standardFont;
    
    public SnapshotComparison() {
        this.dataSet = 0;
        this.space = 6;
        this.choiceLeft = 50 + this.space;
        this.choiceH = 25;
        this.standardFont = new Font("Helvetica", 0, 11);
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target == this.criterionChoice) {
            final int selectedIndex = this.criterionChoice.getSelectedIndex();
            if (selectedIndex != this.dataSet) {
                this.dataSet = selectedIndex;
                this.scale.moveTo(this.getDataForCriterion(this.dataSet), this.formats[this.dataSet]);
            }
        }
        if (event.target == this.scale) {
            final int intValue = ((Number)event.arg).intValue();
            try {
                System.out.println(this.getDocumentBase());
                System.out.println(String.valueOf(this.getParameter("click url")) + this.items[intValue]);
                this.getAppletContext().showDocument(new URL(this.getDocumentBase(), String.valueOf(this.getParameter("click url")) + this.items[intValue]), "_self");
            }
            catch (Exception ex) {
                System.out.println("trouble clicking: " + ex + "; " + event);
            }
        }
        return true;
    }
    
    double[] getDataForCriterion(final int n) {
        final int length = this.items.length;
        final double[] array = new double[length];
        for (int i = 0; i < length; ++i) {
            array[i] = this.data[i][n];
        }
        return array;
    }
    
    public void init() {
        this.setBackground(this.readColor("bgcolor", new Color(13421823)));
        this.setLayout(null);
        this.criteria = this.paramToArray("criteria", "|");
        this.items = this.paramToArray("items", "|");
        final String[] paramToArray = this.paramToArray("formats", "|");
        this.formats = new int[paramToArray.length];
        for (int i = 0; i < this.formats.length; ++i) {
            final String s = paramToArray[i];
            if ("percent".equals(s)) {
                this.formats[i] = 1;
            }
            else if ("dollars".equals(s)) {
                this.formats[i] = 2;
            }
        }
        this.data = new double[this.items.length][];
        for (int j = 0; j < this.items.length; ++j) {
            this.data[j] = this.paramToNumberArray("data, " + this.items[j], ",");
        }
        final int width = this.size().width;
        final int height = this.size().height;
        this.choiceW = width - this.choiceLeft - this.space;
        this.criterionChoice = new Choice();
        for (int k = 0; k < this.criteria.length; ++k) {
            this.criterionChoice.addItem(this.criteria[k]);
        }
        this.add(this.criterionChoice);
        this.criterionChoice.reshape(width - this.space - this.choiceW, this.space, this.choiceW, this.choiceH);
        this.criterionChoice.setFont(this.standardFont);
        this.criterionChoice.setBackground(Color.white);
        (this.scale = new ClickableScale()).setClickable(false);
        this.add(this.scale);
        this.scale.setBackground(this.readColor("scale bgcolor", new Color(16777164)));
        this.scale.reshape(this.space, 2 * this.space + this.choiceH, width - 2 * this.space, height - 3 * this.space - this.choiceH);
        this.scale.setFont(this.standardFont);
        this.scale.setNumberFont(new Font("Helvetica", 1, 12));
        this.scale.display(this.getDataForCriterion(this.dataSet), this.items, this.formats[this.dataSet]);
        this.scale.setVerticalMargin(7);
        this.scale.setRulerX(50);
        this.scale.setLabelX(85);
        this.scale.setSmallTicDivisor(5);
        this.scale.setBigTicDivisor(1.0);
        this.scale.setRulerColor(this.readColor("ruler color", new Color(6710886)));
        this.scale.setLeftMarginColor(this.readColor("left margin", new Color(10066278)));
        this.scale.setDotColor(this.readColor("dot color", new Color(10027008)));
        this.scale.setConnectorColor(this.readColor("connector color", new Color(13421721)));
        this.scale.setClickable(this.getParameter("click url") != null);
    }
    
    public void paint(final Graphics graphics) {
        graphics.setColor(Color.black);
        graphics.setFont(this.standardFont);
        graphics.drawString("Rank by:", this.space, this.space + this.choiceH - 9);
    }
    
    String[] paramToArray(final String s, final String s2) {
        final String parameter = this.getParameter(s);
        if (parameter == null) {
            System.out.println("No param: " + s);
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(parameter, s2);
        final int countTokens = stringTokenizer.countTokens();
        final String[] array = new String[countTokens];
        for (int i = 0; i < countTokens; ++i) {
            array[i] = stringTokenizer.nextToken().trim();
        }
        return array;
    }
    
    double[] paramToNumberArray(final String s, final String s2) {
        final String[] paramToArray = this.paramToArray(s, s2);
        final int length = paramToArray.length;
        final double[] array = new double[length];
        for (int i = 0; i < length; ++i) {
            try {
                array[i] = Double.valueOf(paramToArray[i]);
            }
            catch (NumberFormatException ex) {
                array[i] = Double.NaN;
            }
        }
        return array;
    }
    
    Color readColor(final String s, final Color color) {
        final String parameter = this.getParameter(s);
        return (parameter == null) ? color : new Color(Integer.parseInt(parameter, 16));
    }
    
    void switchToCriterion(final int dataSet) {
        this.dataSet = dataSet;
        this.scale.display(this.getDataForCriterion(dataSet), this.items, this.formats[dataSet]);
    }
}
