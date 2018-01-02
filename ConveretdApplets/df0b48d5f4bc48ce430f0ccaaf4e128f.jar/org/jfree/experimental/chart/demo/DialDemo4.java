// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.experimental.chart.demo;

import javax.swing.event.ChangeEvent;
import java.awt.Container;
import java.awt.Component;
import java.awt.LayoutManager;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.JFreeChart;
import org.jfree.experimental.chart.plot.dial.DialPointer;
import org.jfree.experimental.chart.plot.dial.DialScale;
import java.awt.Font;
import org.jfree.experimental.chart.plot.dial.StandardDialScale;
import org.jfree.experimental.chart.plot.dial.DialLayer;
import org.jfree.ui.GradientPaintTransformer;
import org.jfree.ui.StandardGradientPaintTransformer;
import org.jfree.ui.GradientPaintTransformType;
import org.jfree.experimental.chart.plot.dial.DialBackground;
import java.awt.geom.Point2D;
import java.awt.GradientPaint;
import java.awt.Point;
import org.jfree.experimental.chart.plot.dial.DialFrame;
import java.awt.Stroke;
import java.awt.BasicStroke;
import java.awt.Paint;
import java.awt.Color;
import org.jfree.experimental.chart.plot.dial.StandardDialFrame;
import org.jfree.data.general.ValueDataset;
import org.jfree.experimental.chart.plot.dial.DialPlot;
import org.jfree.data.general.DefaultValueDataset;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;
import javax.swing.JFrame;

public class DialDemo4 extends JFrame implements ChangeListener
{
    JSlider slider;
    DefaultValueDataset dataset;
    
    public DialDemo4(final String title) {
        super(title);
        this.dataset = new DefaultValueDataset(50.0);
        final DialPlot plot = new DialPlot();
        plot.setView(0.78, 0.37, 0.22, 0.26);
        plot.setDataset(this.dataset);
        final StandardDialFrame dialFrame = new StandardDialFrame(-10.0, 20.0);
        dialFrame.setInnerRadius(0.7);
        dialFrame.setOuterRadius(0.9);
        dialFrame.setForegroundPaint(Color.darkGray);
        dialFrame.setStroke(new BasicStroke(3.0f));
        plot.setDialFrame(dialFrame);
        final GradientPaint gp = new GradientPaint(new Point(), new Color(255, 255, 255), new Point(), new Color(240, 240, 240));
        final DialBackground sdb = new DialBackground(gp);
        sdb.setGradientPaintTransformer(new StandardGradientPaintTransformer(GradientPaintTransformType.VERTICAL));
        plot.addLayer(sdb);
        final StandardDialScale scale = new StandardDialScale(0.0, 100.0, -8.0, 16.0);
        scale.setTickRadius(0.82);
        scale.setTickLabelOffset(-0.04);
        scale.setMajorTickIncrement(25.0);
        scale.setTickLabelFont(new Font("Dialog", 0, 14));
        plot.addScale(0, scale);
        final DialPointer needle = new DialPointer.Pin();
        needle.setRadius(0.84);
        plot.addLayer(needle);
        final JFreeChart chart1 = new JFreeChart(plot);
        chart1.setTitle("Dial Demo 4");
        final ChartPanel cp1 = new ChartPanel(chart1);
        cp1.setPreferredSize(new Dimension(400, 250));
        (this.slider = new JSlider(0, 100)).setMajorTickSpacing(10);
        this.slider.setPaintLabels(true);
        this.slider.addChangeListener(this);
        final JPanel content = new JPanel(new BorderLayout());
        content.add(cp1);
        content.add(this.slider, "South");
        this.setDefaultCloseOperation(3);
        this.setContentPane(content);
    }
    
    public void stateChanged(final ChangeEvent e) {
        this.dataset.setValue(new Integer(this.slider.getValue()));
    }
    
    public static void main(final String[] args) {
        final DialDemo4 app = new DialDemo4("JFreeChart - Demo Dial 4");
        app.pack();
        app.setVisible(true);
    }
}
