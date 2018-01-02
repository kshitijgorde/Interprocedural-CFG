import java.util.StringTokenizer;
import java.awt.event.TextEvent;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.TextListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class DCF extends Applet implements TextListener
{
    TextField EPS;
    Label EPSLabel;
    TextField DiscountRate;
    Label DiscountRateLabel;
    TextField EPSGrowth;
    Label EPSGrowthLabel;
    Label PV;
    Label PVLabel;
    
    public DCF() {
        this.EPS = new TextField("", 20);
        this.EPSLabel = new Label("Cash Flow ($):", 2);
        this.DiscountRate = new TextField("15", 6);
        this.DiscountRateLabel = new Label("Discount Rate (%):", 2);
        this.EPSGrowth = new TextField("10", 20);
        this.EPSGrowthLabel = new Label("Cash Flow Growth (%):", 2);
        this.PV = new Label("                                           ");
        this.PVLabel = new Label("Present Value:", 2);
    }
    
    public void init() {
        super.init();
        this.setLayout(null);
        this.setBackground(Color.white);
        final int n = 5;
        final int n2 = 10;
        this.add(this.EPSGrowthLabel);
        this.add(this.EPSGrowth);
        final Dimension preferredSize = this.EPSGrowthLabel.getPreferredSize();
        final int width = preferredSize.width;
        final int height = preferredSize.height;
        this.add(this.EPSLabel);
        this.add(this.EPS);
        this.EPS.addTextListener(this);
        this.EPSLabel.setBounds(n, n2, width, this.EPSLabel.getPreferredSize().height);
        this.EPS.setBounds(n + width + 5, n2, this.EPS.getPreferredSize().width + 5, height);
        final int n3 = n2 + (height + 10);
        this.add(this.DiscountRateLabel);
        this.add(this.DiscountRate);
        this.DiscountRate.addTextListener(this);
        this.DiscountRateLabel.setBounds(n, n3, width, this.DiscountRateLabel.getPreferredSize().height);
        this.DiscountRate.setBounds(n + width + 5, n3, this.DiscountRate.getPreferredSize().width + 5, height);
        final int n4 = n3 + (height + 10);
        this.EPSGrowth.addTextListener(this);
        this.EPSGrowthLabel.setBounds(n, n4, width, this.EPSGrowthLabel.getPreferredSize().height);
        this.EPSGrowth.setBounds(n + width + 5, n4, this.EPSGrowth.getPreferredSize().width + 5, height);
        final int n5 = n4 + (height + 10);
        this.add(this.PVLabel);
        this.add(this.PV);
        this.PVLabel.setBounds(n, n5, width, this.PVLabel.getPreferredSize().height);
        this.PV.setBounds(n + width + 5, n5, this.PV.getPreferredSize().width + 150, height);
    }
    
    public void textValueChanged(final TextEvent textEvent) {
        this.calcPV();
        this.repaint();
    }
    
    protected void calcPV() {
        double doubleValue = 0.0;
        final String text = this.EPS.getText();
        final String text2 = this.DiscountRate.getText();
        final String text3 = this.EPSGrowth.getText();
        try {
            doubleValue = Double.valueOf(text);
        }
        catch (Exception ex2) {}
        try {
            Double.valueOf(text2);
        }
        catch (Exception ex3) {}
        try {
            Double.valueOf(text3);
        }
        catch (Exception ex4) {}
        try {
            String s = String.valueOf(this.calcAnnuity(doubleValue, 1.0, text2, text3) + 0.005);
            int index = s.indexOf(46);
            final int index2 = s.indexOf(69);
            if (index2 >= 0) {
                final String substring = s.substring(index2 + 1);
                int int1 = 0;
                try {
                    int1 = Integer.parseInt(substring);
                }
                catch (Exception ex5) {}
                final String substring2 = s.substring(0, index2);
                s = substring2.substring(0, index) + substring2.substring(index + 1, index + int1 + 1) + "." + substring2.substring(index + int1 + 1);
                index += int1;
            }
            if (index > 0 && s.length() > index + 3) {
                s = s.substring(0, index + 3);
            }
            String substring3 = s;
            String substring4 = "";
            if (index > 0) {
                substring3 = s.substring(0, index);
                substring4 = s.substring(index, s.length());
            }
            else if (index == 0) {
                substring3 = "";
                substring4 = s;
            }
            if (substring3.length() > 3) {
                String s2 = "";
                for (int i = substring3.length() - 1, n = 0; i >= 0; --i, ++n) {
                    if ((i + 1) % 3 == 0 && n > 0) {
                        s2 += ",";
                    }
                    s2 += s.charAt(n);
                }
                substring3 = s2;
            }
            final String string = substring3 + substring4;
            this.PV.setForeground(Color.BLACK);
            this.PV.setText(string);
        }
        catch (CalculatorException ex) {
            this.PV.setForeground(Color.RED);
            this.PV.setText(ex.getMessage());
        }
    }
    
    protected double calcAnnuity(final double n, final double n2, final String s, final String s2) throws CalculatorException {
        double n3 = 0.0;
        String s3 = "";
        double n4 = 0.0;
        double n5 = n;
        double n6 = 0.0;
        final StringTokenizer stringTokenizer = new StringTokenizer(s.trim(), ":");
        final String nextToken = stringTokenizer.nextToken();
        String nextToken2;
        if (stringTokenizer.countTokens() > 0) {
            nextToken2 = stringTokenizer.nextToken();
        }
        else {
            nextToken2 = nextToken;
        }
        double n7;
        double n8;
        try {
            n7 = Double.valueOf(nextToken) / 100.0;
            n8 = Double.valueOf(nextToken2) / 100.0;
        }
        catch (Exception ex) {
            throw new CalculatorException("Discount rate must be a number");
        }
        if (n7 <= 0.0) {
            throw new CalculatorException("Discount rate must be > 0");
        }
        if (n8 <= 0.0) {
            throw new CalculatorException("Discount rate must be > 0");
        }
        final StringTokenizer stringTokenizer2 = new StringTokenizer(s2.trim(), ":");
        while (stringTokenizer2.hasMoreTokens()) {
            final String nextToken3 = stringTokenizer2.nextToken();
            final StringTokenizer stringTokenizer3 = new StringTokenizer(nextToken3, "|");
            if (stringTokenizer3.countTokens() == 2) {
                double n9 = 0.0;
                double doubleValue = 0.0;
                double n10 = 1.0;
                final String nextToken4 = stringTokenizer3.nextToken();
                String s4 = stringTokenizer3.nextToken();
                final StringTokenizer stringTokenizer4 = new StringTokenizer(s4, "%");
                if (stringTokenizer4.countTokens() == 2) {
                    s4 = stringTokenizer4.nextToken();
                    final String nextToken5 = stringTokenizer4.nextToken();
                    try {
                        n10 = Double.valueOf(nextToken5) / 100.0;
                        if (n10 > 1.0) {
                            n10 = 1.0;
                        }
                        if (n10 < 0.0) {
                            n10 = 0.0;
                        }
                    }
                    catch (Exception ex2) {}
                }
                try {
                    n9 = Double.valueOf(nextToken4) / 100.0;
                    doubleValue = Double.valueOf(s4);
                }
                catch (Exception ex3) {}
                if (doubleValue > 0.0) {
                    for (double n11 = n4; n11 < n4 + doubleValue; ++n11) {
                        n5 *= 1.0 + n9;
                        n6 += n5 / Math.pow(1.0 + n7, n11 + 1.0) * n10;
                    }
                    n4 += doubleValue;
                }
            }
            s3 = nextToken3;
        }
        try {
            n3 = Double.valueOf(s3) / 100.0;
        }
        catch (Exception ex4) {}
        if (n3 >= n8) {
            throw new CalculatorException("Terminal growth rate must be < discount rate");
        }
        double n12 = n5 * (1.0 + n3) * n2 / (n8 - n3) / Math.pow(1.0 + n7, n4);
        if (s3.indexOf("|") != -1) {
            n12 = 0.0;
        }
        return n6 * n2 + n12;
    }
}
