// 
// Decompiled by Procyon v0.5.30
// 

package edu.wise.correl.gui;

import java.awt.image.ImageObserver;
import edu.wise.exceptions.InvalidRegressionException;
import edu.wise.utils.FormatUtils;
import java.awt.Color;
import java.awt.LayoutManager;
import edu.wise.utils.TableFormat;
import edu.wise.correl.Cor_app;
import edu.wise.correl.CorrelData;
import edu.wise.graph.regBarChart;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;

public class Output extends Panel implements CorrelOutput
{
    private int prec;
    private int row;
    public Image image;
    public Graphics graphics;
    private regBarChart rbcSS;
    private regBarChart rbcMS;
    private static CorrelData cd;
    private static Cor_app ca;
    private static TableFormat tf;
    private static TableFormat anova_tf;
    
    public Output() {
        this.prec = 3;
        this.row = 0;
    }
    
    public Output(final int n, final int n2, final Image image, final Cor_app ca) {
        this.prec = 3;
        this.row = 0;
        this.setLayout(null);
        Output.ca = ca;
        this.image = image;
        this.graphics = this.image.getGraphics();
        this.setBackground(StyleSheet.BACKGROUND);
        this.graphics.setColor(StyleSheet.BACKGROUND);
        this.graphics.fillRect(0, 0, this.getSize().width, this.getSize().height);
        Output.cd = Cor_app.cd;
        this.setSize(n, n2);
        (Output.tf = new TableFormat(n - 50, 4, 0)).setColWidth(1, 70);
        Output.tf.setColWidth(2, 70);
        (Output.anova_tf = new TableFormat(235, 5, 177)).setColWidth(0, 80);
        final int n3 = n - 45;
        this.rbcSS = new regBarChart(17, 200, n3, 5, Cor_app.cd, true, "Sum of Squares");
        this.rbcMS = new regBarChart(17, 200, n3 + 21, 5, Cor_app.cd, false, "Mean Squares");
        this.repaint();
    }
    
    public void update(final Graphics graphics) {
        this.graphics.setColor(StyleSheet.BACKGROUND);
        this.graphics.clearRect(0, 0, this.getSize().width, this.getSize().height);
        this.graphics.fillRect(0, 0, this.getSize().width, this.getSize().height);
        this.paint(graphics);
    }
    
    public void update() {
        this.repaint();
    }
    
    public void paint(final Graphics graphics) {
        this.graphics.setColor(Color.black);
        this.graphics.drawRect(0, 0, this.getSize().width - 1, this.getSize().height - 1);
        this.graphics.setFont(StyleSheet.f_reg);
        this.graphics.setColor(StyleSheet.FOREGROUND);
        Output.tf.setColWidth(0, 85);
        this.row = 1;
        if (Output.cd.getXName().length() < 9) {
            Output.tf.drawString(String.valueOf(Output.cd.getXName()) + ":", 1, this.row, this.graphics, true, true);
        }
        else {
            Output.tf.drawString(String.valueOf(Output.cd.getXName().substring(0, 8)) + ":", 1, this.row, this.graphics, true, true);
        }
        if (Output.cd.getYName().length() < 9) {
            Output.tf.drawString(String.valueOf(Output.cd.getYName()) + ":", 2, this.row, this.graphics, true, true);
        }
        else {
            Output.tf.drawString(String.valueOf(Output.cd.getYName().substring(0, 8)) + ":", 2, this.row, this.graphics, true, true);
        }
        this.row = 2;
        Output.tf.drawString("Mean = ", 0, this.row, this.graphics, false, true);
        Output.tf.drawString(FormatUtils.rounder_str(CorrelData.xbar(), this.prec), 1, this.row, this.graphics, false, false);
        Output.tf.drawString(FormatUtils.rounder_str(CorrelData.Ybar(), this.prec), 2, this.row, this.graphics, false, false);
        this.row = 3;
        Output.tf.drawString("Std dev = ", 0, this.row, this.graphics, false, true);
        Output.tf.drawString(FormatUtils.rounder_str(CorrelData.sd_x(), this.prec), 1, this.row, this.graphics, false, false);
        Output.tf.drawString(FormatUtils.rounder_str(CorrelData.sd_y(), this.prec), 2, this.row, this.graphics, false, false);
        this.row = 5;
        Output.tf.drawString("r = ", 0, this.row, this.graphics, false, true);
        try {
            Output.tf.drawString(FormatUtils.rounder_str(CorrelData.r(), this.prec), 2, this.row, this.graphics, false, false);
        }
        catch (InvalidRegressionException ex) {
            Output.tf.drawString("-", 2, this.row, this.graphics);
        }
        this.row = 6;
        Output.tf.drawString("r squared = ", 0, this.row, this.graphics, false, true);
        try {
            Output.tf.drawString(FormatUtils.rounder_str(CorrelData.rSqr(), this.prec), 2, this.row, this.graphics);
        }
        catch (InvalidRegressionException ex2) {
            Output.tf.drawString("-", 2, this.row, this.graphics);
        }
        this.row = 7;
        Output.tf.drawString("Standard error = ", 0, this.row, this.graphics, false, true);
        try {
            Output.tf.drawString(FormatUtils.rounder_str(CorrelData.stErrOfPred(), this.prec), 2, this.row, this.graphics);
        }
        catch (InvalidRegressionException ex3) {
            Output.tf.drawString("-", 2, this.row, this.graphics);
        }
        this.row = 8;
        Output.tf.drawString("Slope (b) = ", 0, this.row, this.graphics, false, true);
        Output.tf.drawString(FormatUtils.rounder_str(CorrelData.slope(), this.prec), 2, this.row, this.graphics);
        this.row = 9;
        Output.tf.drawString("Intercept (a) = ", 0, this.row, this.graphics, false, true);
        Output.tf.drawString(FormatUtils.rounder_str(CorrelData.intercept(), this.prec), 2, this.row, this.graphics);
        this.row = 11;
        if (Output.cd.getXName().length() < 17) {
            Output.tf.drawString("Y' = " + FormatUtils.rounder_str(CorrelData.slope(), this.prec) + " (" + Output.cd.getXName() + ") + " + FormatUtils.rounder_str(CorrelData.intercept(), this.prec), 0, this.row, this.graphics, false, true);
        }
        else {
            Output.tf.drawString("Y' = " + FormatUtils.rounder_str(CorrelData.slope(), this.prec) + " (" + Output.cd.getXName().substring(0, 16) + ") + " + FormatUtils.rounder_str(CorrelData.intercept(), this.prec), 0, this.row, this.graphics, false, true);
        }
        Output.anova_tf.setColWidth(0, 60);
        Output.anova_tf.setColWidth(1, 80);
        Output.anova_tf.setColWidth(2, 20);
        Output.anova_tf.drawString("Analysis of Variance", 0, 0, this.graphics, true, true);
        Output.anova_tf.drawString("SS", 1, 1, this.graphics, true, true);
        Output.anova_tf.drawString("df", 2, 1, this.graphics, true, true);
        Output.anova_tf.drawString("MS", 3, 1, this.graphics, true, true);
        try {
            Output.anova_tf.drawString("Predicted", 0, 2, this.graphics, false, true);
            Output.anova_tf.drawString(String.valueOf(FormatUtils.rounder_str(CorrelData.SSPred(), this.prec)), 1, 2, this.graphics);
            Output.anova_tf.drawString(String.valueOf(FormatUtils.rounder_str(CorrelData.dfPred(), 0)), 2, 2, this.graphics);
            Output.anova_tf.drawString(String.valueOf(FormatUtils.rounder_str(CorrelData.MSPred(), this.prec)), 3, 2, this.graphics);
            Output.anova_tf.drawString("Error", 0, 3, this.graphics, false, true);
            Output.anova_tf.drawString(String.valueOf(FormatUtils.rounder_str(CorrelData.SSErr(), this.prec)), 1, 3, this.graphics);
            Output.anova_tf.drawString(String.valueOf(FormatUtils.rounder_str(CorrelData.dfErr(), 0)), 2, 3, this.graphics);
            Output.anova_tf.drawString(String.valueOf(FormatUtils.rounder_str(CorrelData.MSErr(), this.prec)), 3, 3, this.graphics);
            Output.anova_tf.drawString("Total", 0, 4, this.graphics, false, true);
            Output.anova_tf.drawString(String.valueOf(FormatUtils.rounder_str(CorrelData.SSTot(), this.prec)), 1, 4, this.graphics);
            Output.anova_tf.drawString(String.valueOf(FormatUtils.rounder_str(CorrelData.dfTot(), 0)), 2, 4, this.graphics);
            Output.anova_tf.drawString("F = ", 0, 5, this.graphics, false, true);
            Output.anova_tf.drawString(String.valueOf(FormatUtils.rounder_str(CorrelData.F(), this.prec)), 1, 5, this.graphics, false, false);
            Output.anova_tf.drawString("p = ", 0, 6, this.graphics, false, true);
            Output.anova_tf.drawString(String.valueOf(FormatUtils.rounder_str(CorrelData.P(), this.prec)), 1, 6, this.graphics, false, false);
        }
        catch (InvalidRegressionException ex4) {
            Output.anova_tf.drawString("Predicted", 0, 2, this.graphics, false, true);
            Output.anova_tf.drawString("-", 1, 2, this.graphics);
            Output.anova_tf.drawString("-", 2, 2, this.graphics);
            Output.anova_tf.drawString("-", 3, 2, this.graphics);
            Output.anova_tf.drawString("Error", 0, 3, this.graphics, false, true);
            Output.anova_tf.drawString("-", 1, 3, this.graphics);
            Output.anova_tf.drawString("-", 2, 3, this.graphics);
            Output.anova_tf.drawString("-", 3, 3, this.graphics);
            Output.anova_tf.drawString("Total", 0, 4, this.graphics, false, true);
            Output.anova_tf.drawString("-", 1, 4, this.graphics);
            Output.anova_tf.drawString("-", 2, 4, this.graphics);
            Output.anova_tf.drawString("F = -", 0, 5, this.graphics, false, true);
            Output.anova_tf.drawString("p = -", 0, 6, this.graphics, false, true);
        }
        this.drawBarChart(this.graphics);
        graphics.drawImage(this.image, 0, 0, this);
    }
    
    public void drawBarChart(final Graphics graphics) {
        this.rbcSS.paint(graphics);
        this.rbcMS.paint(graphics);
    }
}
