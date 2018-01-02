// 
// Decompiled by Procyon v0.5.30
// 

package edu.wise.correl.gui;

import edu.wise.exceptions.DataNotFoundException;
import java.awt.Color;
import edu.wise.correl.InnerDataView;
import edu.wise.utils.FormatUtils;
import VisualNumerics.math.Statistics;
import java.awt.Graphics;
import java.awt.Image;
import edu.wise.utils.TableFormat;
import edu.wise.correl.Cor_app;
import edu.wise.correl.CorrelData;
import java.awt.Canvas;

public class MeanLessonPanel extends Canvas
{
    private static CorrelData cd;
    private static Cor_app ca;
    private static TableFormat tf;
    
    public MeanLessonPanel() {
    }
    
    public MeanLessonPanel(final int n, final int n2, final Image image, final Cor_app ca) {
        this.setSize(n, n2);
        this.setBackground(StyleSheet.BACKGROUND);
        MeanLessonPanel.cd = Cor_app.cd;
        MeanLessonPanel.ca = ca;
        (MeanLessonPanel.tf = new TableFormat(n - 40, 6, 10)).setColWidth(0, 25);
        MeanLessonPanel.tf.setColWidth(1, 60);
        MeanLessonPanel.tf.setColWidth(2, 60);
        MeanLessonPanel.tf.setColWidth(3, 60);
        MeanLessonPanel.tf.setColWidth(4, 60);
        this.repaint();
    }
    
    public void paint(final Graphics graphics) {
        MeanLessonPanel.tf.reset();
        final int n = 2;
        graphics.setFont(StyleSheet.f_sm);
        graphics.setColor(StyleSheet.FOREGROUND);
        int n2 = 0;
        MeanLessonPanel.tf.drawString("Properties of the Mean", 0, n2, graphics, true, true);
        ++n2;
        MeanLessonPanel.tf.setColWidth(0, 20);
        MeanLessonPanel.tf.drawString("Mean of Y=", 1, n2, graphics, false, true);
        MeanLessonPanel.tf.setColWidth(0, 25);
        MeanLessonPanel.tf.drawString(String.valueOf(FormatUtils.rounder_str(Statistics.average(CorrelData.getYArr()), n)), 2, n2, graphics, false, false);
        MeanLessonPanel.tf.drawString("Alt.Mean=", 3, n2, graphics, false, true);
        n2 += 2;
        MeanLessonPanel.tf.drawString("X", 1, n2, graphics, true, true);
        MeanLessonPanel.tf.drawString("Y", 2, n2, graphics, true, true);
        MeanLessonPanel.tf.drawString("(Y-M)", 3, n2, graphics, true, true);
        MeanLessonPanel.tf.drawString("(Y-M)", 4, n2, graphics, true, true);
        int i = 0;
        double n3 = 0.0;
        double n4 = 0.0;
        Cor_app.datav.getIDV();
        final double mean = InnerDataView.getManualMeanLine().getMean();
        while (i < CorrelData.N()) {
            ++n2;
            try {
                final int n5 = i;
                Cor_app.datav.getIDV();
                final boolean b = n5 == InnerDataView.getActivePt();
                graphics.setColor(Color.black);
                MeanLessonPanel.tf.drawString(String.valueOf(i + 1) + ".", 0, n2, graphics, false, b);
                graphics.setColor(StyleSheet.randomColor(i));
                MeanLessonPanel.tf.drawString(String.valueOf(FormatUtils.rounder_str(CorrelData.getXi(i), n)), 1, n2, graphics, false, b);
                MeanLessonPanel.tf.drawString(String.valueOf(FormatUtils.rounder_str(CorrelData.getYi(i), n)), 2, n2, graphics, false, b);
                try {
                    MeanLessonPanel.tf.drawString(String.valueOf(FormatUtils.rounder_str(CorrelData.getYi(i) - CorrelData.getYbar(), n)), 3, n2, graphics, false, b);
                }
                catch (DataNotFoundException ex) {}
                MeanLessonPanel.tf.drawString(FormatUtils.rounder_str(CorrelData.getYi(i) - mean, n), 4, n2, graphics, false, b);
                n3 += CorrelData.getYi(i) - CorrelData.getYbar();
                n4 += CorrelData.getYi(i) - mean;
                ++i;
            }
            catch (DataNotFoundException ex2) {}
        }
        graphics.setColor(Color.black);
        int n6 = 1;
        MeanLessonPanel.tf.drawString(String.valueOf(FormatUtils.rounder_str(mean, n)), 4, n6, graphics, false, false);
        ++n6;
        MeanLessonPanel.tf.drawString("Sum=", 2, n6, graphics, false, true);
        MeanLessonPanel.tf.drawString(String.valueOf(FormatUtils.rounder_str(n3, n)), 3, n6, graphics, false, false);
        MeanLessonPanel.tf.drawString(String.valueOf(FormatUtils.rounder_str(n4, n)), 4, n6, graphics, false, false);
        ++n6;
        if (MeanLessonPanel.tf.getPreferredHeight() > this.getSize().height) {
            this.invalidate();
            this.setSize(this.getSize().width, MeanLessonPanel.tf.getPreferredHeight());
            this.doLayout();
            this.validate();
        }
        else if (MeanLessonPanel.tf.getPreferredHeight() < this.getSize().height) {
            this.setSize(this.getSize().width, this.getSize().height);
        }
    }
}
