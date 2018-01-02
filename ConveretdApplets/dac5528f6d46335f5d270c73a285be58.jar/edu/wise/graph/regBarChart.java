// 
// Decompiled by Procyon v0.5.30
// 

package edu.wise.graph;

import edu.wise.exceptions.InvalidRegressionException;
import java.awt.Color;
import java.awt.Graphics;
import edu.wise.correl.gui.StyleSheet;
import java.awt.Font;
import edu.wise.correl.CorrelData;

public class regBarChart extends barChart
{
    CorrelData cd;
    boolean SS;
    String label;
    Font f;
    
    regBarChart() {
        this.f = StyleSheet.f_reg;
    }
    
    public regBarChart(final int n, final int n2, final int n3, final int n4, final CorrelData cd, final boolean ss, final String label) {
        super(n, n2, n3, n4);
        this.f = StyleSheet.f_reg;
        this.cd = cd;
        this.SS = ss;
        this.label = label;
    }
    
    public void paint(final Graphics graphics) {
        try {
            double n;
            double n2;
            if (this.SS) {
                final double ssPred = CorrelData.SSPred();
                final double ssErr = CorrelData.SSErr();
                n = ssPred / CorrelData.SSTot();
                n2 = ssErr / CorrelData.SSTot();
            }
            else {
                final double msPred = CorrelData.MSPred();
                final double msErr = CorrelData.MSErr();
                n = msPred / CorrelData.MSTot();
                n2 = msErr / CorrelData.MSTot();
            }
            final int n3 = (int)Math.round(super.y_start + (super.height - n * super.height));
            final int n4 = (int)Math.round(n * super.height);
            final int n5 = super.height - n4;
            final int n6 = super.y_start + 1;
            final int n7 = (int)Math.round(n2 * super.height);
            final int n8 = (int)Math.round(n * super.height);
            graphics.setColor(StyleSheet.ERR);
            graphics.fillRect(super.x_start + 1, n6, super.width - 1, n5);
            graphics.setColor(StyleSheet.PRED);
            graphics.fillRect(super.x_start + 1, n3, super.width - 1, n4);
            graphics.setColor(Color.black);
        }
        catch (InvalidRegressionException ex) {
            graphics.setColor(Color.lightGray);
            graphics.fillRect(super.x_start + 1, super.y_start, super.width - 1, super.height);
        }
        finally {
            graphics.setColor(Color.black);
            graphics.drawRect(super.x_start, super.y_start, super.width, super.height);
            graphics.setColor(Color.black);
            graphics.setFont(StyleSheet.labelFont);
            final int n9 = graphics.getFontMetrics().getHeight() - 1;
            this.label.length();
            final int height = graphics.getFontMetrics().getHeight();
            final int n10 = (super.height - (this.label.length() - 2) * height) / 2;
            int n11 = 0;
            do {
                final char char1 = this.label.charAt(n11);
                graphics.drawString(String.valueOf(char1), super.x_start + (1 + (super.width - graphics.getFontMetrics().charWidth(char1)) / 2), n10 + height * n11);
            } while (++n11 < this.label.length());
            graphics.setFont(StyleSheet.f_reg);
        }
    }
}
