import java.net.URLConnection;
import java.io.DataInputStream;
import java.util.StringTokenizer;
import java.awt.Graphics;
import java.net.URL;
import java.awt.Event;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Color;
import com.smartmoney.util.CookieUtil;
import java.awt.Font;
import java.awt.TextField;
import java.awt.Button;
import java.awt.Choice;
import com.smartmoney.gui.ClickableScale;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class DatabaseComparison extends Applet implements Runnable
{
    String[] criteria;
    String[] items;
    double[][] data;
    int[] formats;
    int dataSet;
    ClickableScale scale;
    Choice criterionChoice;
    Button submitButton;
    TextField tickerField;
    int space;
    int choiceLeft;
    int choiceW;
    int choiceH;
    Font standardFont;
    String ticker;
    String helpMessage;
    String errorMessage;
    String message;
    int y1;
    int y2;
    int x1;
    int x2;
    int x3;
    
    public void init() {
        String lowerCase = "none";
        if (System.getProperty("browser") != null) {
            lowerCase = System.getProperty("browser").toLowerCase();
        }
        if (lowerCase.indexOf("netscape") == -1) {
            CookieUtil.setCookieHeader(this.getParameter("setcookieheader"));
        }
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
        this.x1 = width / 2;
        this.x2 = this.x1 + 10;
        this.x3 = width - 10;
        this.criterionChoice = new Choice();
        for (int k = 0; k < this.criteria.length; ++k) {
            this.criterionChoice.addItem(this.criteria[k]);
        }
        this.add(this.criterionChoice);
        this.criterionChoice.reshape(this.x2, this.y1, this.x3 - this.x2, 25);
        this.criterionChoice.setFont(this.standardFont);
        this.criterionChoice.setBackground(Color.white);
        final int n = (this.x3 - this.x2) / 2 - 5;
        final int n2 = 20;
        this.add(this.tickerField);
        this.tickerField.reshape(this.x2, this.y2, n, n2);
        this.tickerField.setFont(this.standardFont);
        this.tickerField.setBackground(Color.white);
        this.add(this.submitButton);
        this.submitButton.reshape(this.x3 - n, this.y2, n, n2);
        this.submitButton.setFont(this.standardFont);
        (this.scale = new ClickableScale()).setClickable(false);
        this.add(this.scale);
        this.scale.setBackground(this.readColor("scale bgcolor", new Color(16777164)));
        this.scale.reshape(0, 0, this.x1, height);
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
    
    public boolean action(final Event event, final Object o) {
        if (event.target == this.criterionChoice) {
            final int selectedIndex = this.criterionChoice.getSelectedIndex();
            if (selectedIndex != this.dataSet) {
                this.moveToCriterion(selectedIndex);
            }
        }
        if (event.target == this.scale) {
            final int intValue = ((Number)event.arg).intValue();
            try {
                this.getAppletContext().showDocument(new URL(this.getDocumentBase(), String.valueOf(this.getParameter("click url")) + this.items[intValue]), "_self");
            }
            catch (Exception ex) {
                System.out.println("trouble clicking: " + ex + "; " + event);
            }
        }
        if (event.target == this.submitButton || event.target == this.tickerField) {
            this.ticker = this.tickerField.getText().trim().toUpperCase();
            this.submitButton.disable();
            new Thread(this).start();
        }
        return true;
    }
    
    public void paint(final Graphics graphics) {
        graphics.setColor(Color.black);
        graphics.setFont(this.standardFont);
        graphics.drawString("Rank by:", this.x2, this.y1 - 5);
        graphics.drawString("Compare with another stock:", this.x2, this.y2 - 5);
        graphics.drawString(this.message, this.x2, this.y2 + 40);
        graphics.setColor(Color.white);
        graphics.drawLine(this.x1, 0, this.x1, this.size().height);
    }
    
    synchronized void moveToCriterion(final int dataSet) {
        this.dataSet = dataSet;
        this.scale.moveTo(this.getDataForCriterion(dataSet), this.formats[dataSet]);
    }
    
    synchronized void switchToCriterion(final int dataSet) {
        this.dataSet = dataSet;
        this.scale.display(this.getDataForCriterion(dataSet), this.items, this.formats[dataSet]);
    }
    
    double[] getDataForCriterion(final int n) {
        final int length = this.items.length;
        final double[] array = new double[length];
        for (int i = 0; i < length; ++i) {
            array[i] = this.data[i][n];
        }
        return array;
    }
    
    String[] stringToArray(final String s, final String s2) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, s2);
        final int countTokens = stringTokenizer.countTokens();
        final String[] array = new String[countTokens];
        for (int i = 0; i < countTokens; ++i) {
            array[i] = stringTokenizer.nextToken().trim();
        }
        return array;
    }
    
    String[] paramToArray(final String s, final String s2) {
        final String parameter = this.getParameter(s);
        if (parameter == null) {
            System.out.println("No param: " + s);
        }
        return this.stringToArray(parameter, s2);
    }
    
    double[] parseNumbers(final String[] array) {
        final int length = array.length;
        final double[] array2 = new double[length];
        for (int i = 0; i < length; ++i) {
            try {
                array2[i] = Double.valueOf(array[i]);
            }
            catch (NumberFormatException ex) {
                array2[i] = Double.NaN;
            }
        }
        return array2;
    }
    
    double[] paramToNumberArray(final String s, final String s2) {
        return this.parseNumbers(this.paramToArray(s, s2));
    }
    
    Color readColor(final String s, final Color color) {
        final String parameter = this.getParameter(s);
        if (parameter == null) {
            return color;
        }
        return new Color(Integer.parseInt(parameter, 16));
    }
    
    synchronized void addData(final String s, final double[] array) {
        final int length = this.items.length;
        final String[] items = new String[length + 1];
        System.arraycopy(this.items, 0, items, 0, length);
        items[length] = s;
        final double[][] data = new double[length + 1][];
        System.arraycopy(this.data, 0, data, 0, length);
        data[length] = array;
        this.items = items;
        this.data = data;
        this.switchToCriterion(this.dataSet);
    }
    
    public void run() {
        if (this.ticker == null) {
            return;
        }
        boolean b = false;
        for (int i = 0; i < this.items.length; ++i) {
            b |= this.items[i].equalsIgnoreCase(this.ticker);
        }
        if (b) {
            this.message = "Already loaded.";
            this.tickerField.setText("");
        }
        else {
            try {
                String s = this.getParameter("middleware url");
                if (s.indexOf("?") != -1) {
                    s = String.valueOf(s) + this.ticker;
                }
                this.addData(this.ticker, this.arrayFromFile(s));
                this.message = this.helpMessage;
                this.tickerField.setText("");
            }
            catch (Exception ex) {
                this.message = this.errorMessage;
                System.out.println(ex);
            }
        }
        this.repaint();
        this.submitButton.enable();
    }
    
    double[] arrayFromFile(final String s) throws Exception {
        final URL url = new URL(this.getCodeBase(), s);
        System.out.println("calling " + url);
        final URLConnection openConnection = url.openConnection();
        CookieUtil.setCookieHeader(openConnection);
        final DataInputStream dataInputStream = new DataInputStream(openConnection.getInputStream());
        final StringBuffer sb = new StringBuffer();
        String line;
        while ((line = dataInputStream.readLine()) != null) {
            sb.append(line);
        }
        final double[] numbers = this.parseNumbers(this.stringToArray(sb.toString(), ","));
        if (numbers.length != this.criteria.length) {
            throw new IllegalArgumentException("Wrong # data points: " + (Object)sb);
        }
        return numbers;
    }
    
    public DatabaseComparison() {
        this.submitButton = new Button("Compare");
        this.tickerField = new TextField();
        this.space = 6;
        this.choiceLeft = 50 + this.space;
        this.choiceH = 25;
        this.standardFont = new Font("Helvetica", 0, 11);
        this.helpMessage = "Enter ticker symbol to compare.";
        this.errorMessage = "Couldn't contact server.";
        this.message = this.helpMessage;
        this.y1 = 25;
        this.y2 = 100;
    }
}
