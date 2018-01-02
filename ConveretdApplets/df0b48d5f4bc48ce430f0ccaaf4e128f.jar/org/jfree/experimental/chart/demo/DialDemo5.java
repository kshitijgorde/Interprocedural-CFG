// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.experimental.chart.demo;

import javax.swing.event.ChangeEvent;
import java.awt.Container;
import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.JLabel;
import java.awt.LayoutManager;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.Dimension;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.JFreeChart;
import org.jfree.experimental.chart.plot.dial.DialCap;
import org.jfree.experimental.chart.plot.dial.DialPointer;
import org.jfree.experimental.chart.plot.dial.DialScale;
import java.awt.Font;
import org.jfree.experimental.chart.plot.dial.StandardDialScale;
import org.jfree.experimental.chart.plot.dial.DialLayer;
import org.jfree.ui.GradientPaintTransformer;
import org.jfree.ui.StandardGradientPaintTransformer;
import org.jfree.ui.GradientPaintTransformType;
import org.jfree.experimental.chart.plot.dial.DialBackground;
import org.jfree.experimental.chart.plot.dial.DialFrame;
import java.awt.Paint;
import java.awt.Color;
import org.jfree.experimental.chart.plot.dial.SimpleDialFrame;
import org.jfree.data.general.ValueDataset;
import org.jfree.experimental.chart.plot.dial.DialPlot;
import javax.swing.JSlider;
import org.jfree.data.general.DefaultValueDataset;
import javax.swing.event.ChangeListener;
import javax.swing.JFrame;

public class DialDemo5 extends JFrame implements ChangeListener
{
    DefaultValueDataset hoursDataset;
    DefaultValueDataset dataset2;
    JSlider slider1;
    JSlider slider2;
    
    public DialDemo5(final String title) {
        super(title);
        this.hoursDataset = new DefaultValueDataset(6.0);
        this.dataset2 = new DefaultValueDataset(15.0);
        final DialPlot plot = new DialPlot();
        plot.setView(0.0, 0.0, 1.0, 1.0);
        plot.setDataset(0, this.hoursDataset);
        plot.setDataset(1, this.dataset2);
        final SimpleDialFrame dialFrame = new SimpleDialFrame();
        dialFrame.setBackgroundPaint(Color.lightGray);
        dialFrame.setForegroundPaint(Color.darkGray);
        plot.setDialFrame(dialFrame);
        final DialBackground db = new DialBackground(Color.white);
        db.setGradientPaintTransformer(new StandardGradientPaintTransformer(GradientPaintTransformType.VERTICAL));
        plot.setBackground(db);
        final StandardDialScale hourScale = new StandardDialScale(0.0, 12.0, 90.0, -360.0);
        hourScale.setFirstTickLabelVisible(false);
        hourScale.setMajorTickIncrement(1.0);
        hourScale.setTickRadius(0.88);
        hourScale.setTickLabelOffset(0.15);
        hourScale.setTickLabelFont(new Font("Dialog", 0, 14));
        plot.addScale(0, hourScale);
        final StandardDialScale scale2 = new StandardDialScale(0.0, 60.0, 90.0, -360.0);
        scale2.setVisible(false);
        scale2.setMajorTickIncrement(5.0);
        scale2.setTickRadius(0.68);
        scale2.setTickLabelOffset(0.15);
        scale2.setTickLabelFont(new Font("Dialog", 0, 14));
        plot.addScale(1, scale2);
        final DialPointer needle2 = new DialPointer.Pointer(0);
        needle2.setRadius(0.55);
        plot.addLayer(needle2);
        plot.mapDatasetToScale(1, 1);
        final DialPointer needle3 = new DialPointer.Pointer(1);
        plot.addLayer(needle3);
        final DialCap cap = new DialCap();
        cap.setRadius(0.1);
        plot.setCap(cap);
        final JFreeChart chart1 = new JFreeChart(plot);
        chart1.setTitle("Dial Demo 5");
        final ChartPanel cp1 = new ChartPanel(chart1);
        cp1.setPreferredSize(new Dimension(400, 400));
        final JPanel sliderPanel = new JPanel(new GridLayout(2, 2));
        sliderPanel.add(new JLabel("Hours:"));
        sliderPanel.add(new JLabel("Minutes:"));
        (this.slider1 = new JSlider(0, 12)).setMajorTickSpacing(2);
        this.slider1.setPaintTicks(true);
        this.slider1.setPaintLabels(true);
        this.slider1.addChangeListener(this);
        sliderPanel.add(this.slider1);
        sliderPanel.add(this.slider1);
        (this.slider2 = new JSlider(0, 60)).setValue(15);
        this.slider2.setMajorTickSpacing(10);
        this.slider2.setPaintTicks(true);
        this.slider2.setPaintLabels(true);
        this.slider2.addChangeListener(this);
        sliderPanel.add(this.slider2);
        final JPanel content = new JPanel(new BorderLayout());
        content.add(cp1);
        content.add(sliderPanel, "South");
        this.setDefaultCloseOperation(3);
        this.setContentPane(content);
    }
    
    public void stateChanged(final ChangeEvent e) {
        this.hoursDataset.setValue(new Integer(this.slider1.getValue()));
        this.dataset2.setValue(new Integer(this.slider2.getValue()));
    }
    
    public static void main(final String[] args) {
        final DialDemo5 app = new DialDemo5("JFreeChart - Dial Demo 5");
        app.pack();
        app.setVisible(true);
    }
}
