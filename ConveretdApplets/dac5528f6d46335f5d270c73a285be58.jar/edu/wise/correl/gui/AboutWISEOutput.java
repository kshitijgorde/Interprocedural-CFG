// 
// Decompiled by Procyon v0.5.30
// 

package edu.wise.correl.gui;

import java.awt.Graphics;
import java.awt.Label;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Button;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.Choice;
import java.awt.Panel;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Image;
import edu.wise.correl.Cor_app;
import edu.wise.correl.AboutCorrel;
import java.awt.Container;

public class AboutWISEOutput extends Container implements CorrelOutput
{
    private static AboutCorrel ac;
    private static AboutWiseOutputControls awoc;
    Cor_app ca;
    private int lastPanel;
    String aboutText;
    String controlsText;
    String meanText;
    String regText;
    
    public AboutWISEOutput(final int n, final int n2, final Image image, final Cor_app ca) {
        this.lastPanel = 1;
        this.aboutText = "Claremont Graduate University's\nWeb Interface for Statistics Education (WISE)\nCorrelation and Regression Applet\n\n  This program is designed to graphically and dynamically illustrate basic concepts about the arithmatic mean, correlation, regression and analysis of variance.  The applet contains several lesson panels which can be selected via the choice menu below.\n\nFor more information about how to use this program select the various help topics located in the above choice menu\n\nPress the Exit Help button to begin using the program\n\nCredits:\nProject Director: Dale.Berger@cgu.edu\nAvailable at: http://wise.cgu.edu\nProgrammer: Michael.Healy@cgu.edu\nProject Team: Christopher L. Aberson, Victoria L. Romero, Michael Healy\nUpdated: October, 2000 beta 1.0";
        this.controlsText = "Configuration options:\n\nReset:\n  Centers the data in the middle of the scatterplot\n\nZoom In:\n  Decreases the scale of the scatterplot\n\nZoom Out:\n  Increases the scale of the scatterplot\n\nShow Error Deviations:\n  Shows the sum of squares error which is the sum of (Y-Y predicted) squared.\n\nShow Predicted Deviations:\n  Shows the sum of squares predicted which is the sum of (Y predicted - the mean of Y) squared.\n\nShow Total Deviations:\n  Shows the sum of squares predicted which is the sum of (Y - the mean of Y) squared.\n\nShow Regression Line:\n  Turns the regression line on or off.\n\nShow Adj. Mean Line:\n  Turns the adjustable mean line on or off.  .\n\nShow Mean of Y:\n  Draws a horizontal line on the scatterplot representing the mean of the Y variable\n\nShow Squared Deviations:\n  The error components can be displayed as single lines (spikes) or as squares representing their squared values\n\nSelect a Lesson:\n  Use this choice menu to select the lesson to be displayed.\n\nHelp...:\n  Activates the online help system\n\nTo return to the tutorial click on the Exit Help button.";
        this.meanText = "Learning about the Mean:\n\n  The arithmatic mean is the most often used measure of central tendency.  The mean represents the point in a variable where the sum of each case in a variable minus this point is equal to zero.   To display this property of the mean, this scatterplot on the left has been shows the mean of the Y variable, and lets users compare that value to an alternate value for the mean (designated Alt.Mean in the applet).  To designate an alternate mean value, click-and-drag on horizontal line showing the mean of Y. \n\n   The output panel (currently covered by this help section) displays the following information.  The values for the mean and the alternate mean are shown below the title.  The label Sums= represent the sum of each Y value minus the mean or the alternate mean.  The important point to note here is that the for the first sum, the mean of Y, the value never changes from 0.  Below this section is a listing of the data.  Column 1 is an arbitary case number, columns two and three are the X, Y data points for that case.  The column labeled (Y-M), is that particular case's Y value minus the mean of Y.  The last column labeled (Y-AM) is that case's Y value minus the alternate mean.\n\nThe scatterplot can be manipulated in several ways:\n\n-  Data points can be moved by clicking and dragging a data point around the graph.\n\n-  To add a new data point, double-click with the mouse on the scatterplot where you would like the new point to be added.\n\n-  Double-clicking on a data point will delete that value.\n\n-  The alternate mean line can be moved by single-clicking on it and moving the mouse up or down.\n\nTo return to the tutorial click on the Exit Help button.";
        this.regText = "Learning about Regression:\n\n   The graph to your left is a scatterplot which is a useful tool for summarizing the relationship between two variables.   The scatterplot also contains a regression line which describes the linear relationship between these two variables. \n\nThe output panel (currently covered by this help section) contains descriptive and inferntial statistics relating to a regression analysis.  The output panel contains two bar charts which show the Sum of Squares and the Mean Squares from the regression.  The red area of the bar charts represents how much unexplained variability, referred to as error, exists between the two variables.  The blue part of the bar charts represents how much variability is explained by the relationship between the two variables.  To further see the relationship between explained and unexplained variability, click on the Show SS error and Show SS predicted check boxes on the applets control panel.  The red and blue error spikes (or squares) are the values being graphed in this bar chart.\n\nThe scatterplot can be manipulated in several ways:\n\n-  Data points can be moved by clicking and dragging a data point around the graph. \n\n-  To add a new data point, double-click with the mouse on the scatterplot where you would like the new point to be added.\n\n-  Double-clicking on a data point will delete that value.\n\n-  The regression line can be moved by single-clicking on it and moving the mouse up or down.\n\nTo return to the tutorial click on the Exit Help button.";
        this.ca = ca;
        this.setSize(n, n2);
        this.setBackground(StyleSheet.BACKGROUND);
        this.setLayout(new BorderLayout());
        AboutWISEOutput.awoc = new AboutWiseOutputControls(n, 30);
        AboutWISEOutput.ac = new AboutCorrel(n - 3, n2);
        this.add(AboutWISEOutput.awoc, "North");
        this.add(AboutWISEOutput.ac, "Center");
        AboutWISEOutput.ac.setText(this.aboutText);
        this.repaint();
        this.repaint();
    }
    
    public void update() {
    }
    
    public void lastPanel(final int lastPanel) {
        this.lastPanel = lastPanel;
    }
    
    class AboutWiseOutputControls extends Panel
    {
        AboutWiseOutputControls(final int n, final int n2) {
            this.setBackground(StyleSheet.BACKGROUND);
            this.setSize(n, n2);
            final Choice choice = new Choice();
            choice.add("About...");
            choice.add("Controls");
            choice.add("Regression");
            choice.add("The Mean");
            choice.addItemListener(new ItemListener() {
                public void itemStateChanged(final ItemEvent itemEvent) {
                    if (itemEvent.getSource() instanceof Choice) {
                        final String s = (String)itemEvent.getItem();
                        if (s.equals("Regression")) {
                            AboutWISEOutput.ac.setText(AboutWISEOutput.this.regText);
                        }
                        else if (s.equals("The Mean")) {
                            AboutWISEOutput.ac.setText(AboutWISEOutput.this.meanText);
                        }
                        else if (s.equals("About...")) {
                            AboutWISEOutput.ac.setText(AboutWISEOutput.this.aboutText);
                        }
                        else if (s.equals("Controls")) {
                            AboutWISEOutput.ac.setText(AboutWISEOutput.this.controlsText);
                        }
                        else {
                            System.err.println("Choice assignment error");
                        }
                    }
                }
            });
            choice.select(0);
            final Button button = new Button("Exit Help");
            button.addActionListener(new ActionListener() {
                public void actionPerformed(final ActionEvent actionEvent) {
                    AboutWISEOutput.this.ca.setPanels(AboutWISEOutput.this.lastPanel);
                }
            });
            this.setLayout(new FlowLayout());
            this.add(new Label("Topics:"));
            this.add(choice);
            this.add(button);
        }
        
        public void paint(final Graphics graphics) {
            graphics.drawRect(0, 0, this.getSize().width - 1, this.getSize().height - 1);
        }
    }
}
