// 
// Decompiled by Procyon v0.5.30
// 

package edu.hws.GA;

import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Label;
import java.awt.Font;
import java.awt.Component;
import java.awt.TextArea;
import java.awt.Frame;

class ReportWin extends Frame
{
    private int nextAvg;
    private double[] avgList;
    private double best;
    private String[] stats;
    private int statsCt;
    private TextArea list;
    private static String blanks;
    
    ReportWin() {
        super("Statistics");
        this.nextAvg = 100;
        this.avgList = new double[101];
        this.stats = new String[500];
        this.add(this.list = new TextArea("", 20, 50, 1), "Center");
        final Font font = new Font("Monospaced", 0, 12);
        final Label label = new Label("*  YEAR   AVERAGE SCORE   HIGH SCORE   100-YEAR AVERAGE  *");
        label.setFont(font);
        this.add(label, "North");
        this.list.setFont(font);
        this.list.setEditable(false);
        this.list.setBackground(Color.white);
        this.setBackground(Color.lightGray);
        this.pack();
        this.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width - 10 - this.getSize().width, 25);
        this.show();
    }
    
    void doReport(final int n, final double best, final int n2) {
        boolean b = false;
        if (best > this.best) {
            b = true;
            this.best = best;
        }
        String value = "";
        if (n < 100) {
            this.avgList[n] = best;
        }
        else {
            this.avgList[this.nextAvg] = best;
            ++this.nextAvg;
            if (this.nextAvg > 100) {
                this.nextAvg = 1;
            }
            double n3 = 0.0;
            for (int i = 1; i <= 100; ++i) {
                n3 += this.avgList[i];
            }
            value = String.valueOf((int)(n3 * 100.0) / 10000.0);
        }
        if (b || n <= 100 || (n <= 1000 && n % 10 == 0) || n % 100 == 0) {
            final String value2 = String.valueOf(n);
            final String value3 = String.valueOf((int)(best * 1000.0) / 1000.0);
            final String value4 = String.valueOf(n2);
            final String string = String.valueOf(ReportWin.blanks.substring(0, 6 - value2.length())) + value2;
            final String string2 = String.valueOf(ReportWin.blanks.substring(0, 14 - value3.length())) + value3;
            String s;
            if (b) {
                s = " *" + ReportWin.blanks.substring(0, 11 - value4.length()) + value4;
            }
            else {
                s = String.valueOf(ReportWin.blanks.substring(0, 13 - value4.length())) + value4;
            }
            this.list.append(String.valueOf(new StringBuffer(String.valueOf(string)).append(string2).append(s).append("            ").append(value).toString()) + "\n");
        }
    }
    
    void clear() {
        this.list.setText("");
        this.nextAvg = 100;
        this.best = 0.0;
    }
    
    static {
        ReportWin.blanks = "                    ";
    }
}
