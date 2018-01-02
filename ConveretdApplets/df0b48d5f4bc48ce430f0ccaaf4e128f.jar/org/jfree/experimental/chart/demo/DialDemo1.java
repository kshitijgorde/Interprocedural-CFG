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
import org.jfree.experimental.chart.plot.dial.DialCap;
import org.jfree.experimental.chart.plot.dial.DialPointer;
import org.jfree.experimental.chart.plot.dial.StandardDialRange;
import org.jfree.experimental.chart.plot.dial.DialScale;
import org.jfree.experimental.chart.plot.dial.StandardDialScale;
import org.jfree.experimental.chart.plot.dial.DialValueIndicator;
import java.awt.Font;
import org.jfree.experimental.chart.plot.dial.DialTextAnnotation;
import org.jfree.experimental.chart.plot.dial.DialLayer;
import org.jfree.ui.GradientPaintTransformer;
import org.jfree.ui.StandardGradientPaintTransformer;
import org.jfree.ui.GradientPaintTransformType;
import org.jfree.experimental.chart.plot.dial.DialBackground;
import java.awt.geom.Point2D;
import java.awt.GradientPaint;
import java.awt.Point;
import org.jfree.experimental.chart.plot.dial.DialFrame;
import java.awt.Paint;
import java.awt.Color;
import org.jfree.experimental.chart.plot.dial.SimpleDialFrame;
import org.jfree.data.general.ValueDataset;
import org.jfree.experimental.chart.plot.dial.DialPlot;
import org.jfree.data.general.DefaultValueDataset;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;
import javax.swing.JFrame;

public class DialDemo1 extends JFrame implements ChangeListener
{
    JSlider slider;
    DefaultValueDataset dataset;
    
    public DialDemo1(final String title) {
        super(title);
        this.dataset = new DefaultValueDataset(10.0);
        final DialPlot plot = new DialPlot();
        plot.setView(0.0, 0.0, 1.0, 1.0);
        plot.setDataset(this.dataset);
        final SimpleDialFrame dialFrame = new SimpleDialFrame();
        dialFrame.setBackgroundPaint(Color.lightGray);
        dialFrame.setForegroundPaint(Color.darkGray);
        plot.setDialFrame(dialFrame);
        final GradientPaint gp = new GradientPaint(new Point(), new Color(255, 255, 255), new Point(), new Color(170, 170, 220));
        final DialBackground db = new DialBackground(gp);
        db.setGradientPaintTransformer(new StandardGradientPaintTransformer(GradientPaintTransformType.VERTICAL));
        plot.setBackground(db);
        final DialTextAnnotation annotation1 = new DialTextAnnotation("Temperature");
        annotation1.setFont(new Font("Dialog", 1, 14));
        annotation1.setRadius(0.7);
        plot.addLayer(annotation1);
        final DialValueIndicator dvi = new DialValueIndicator(0, "c");
        plot.addLayer(dvi);
        final StandardDialScale scale = new StandardDialScale(-40.0, 60.0, -120.0, -300.0);
        scale.setTickRadius(0.88);
        scale.setTickLabelOffset(0.15);
        scale.setTickLabelFont(new Font("Dialog", 0, 14));
        plot.addScale(0, scale);
        final StandardDialRange range = new StandardDialRange(40.0, 60.0, Color.red);
        range.setInnerRadius(0.52);
        range.setOuterRadius(0.55);
        plot.addLayer(range);
        final StandardDialRange range2 = new StandardDialRange(10.0, 40.0, Color.orange);
        range2.setInnerRadius(0.52);
        range2.setOuterRadius(0.55);
        plot.addLayer(range2);
        final StandardDialRange range3 = new StandardDialRange(-40.0, 10.0, Color.green);
        range3.setInnerRadius(0.52);
        range3.setOuterRadius(0.55);
        plot.addLayer(range3);
        final DialPointer needle = new DialPointer.Pointer();
        plot.addLayer(needle);
        final DialCap cap = new DialCap();
        cap.setRadius(0.1);
        plot.setCap(cap);
        final JFreeChart chart1 = new JFreeChart(plot);
        chart1.setTitle("Demo Dial 1");
        final ChartPanel cp1 = new ChartPanel(chart1);
        cp1.setPreferredSize(new Dimension(400, 400));
        (this.slider = new JSlider(-40, 60)).setMajorTickSpacing(10);
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
        final DialDemo1 app = new DialDemo1("JFreeChart - Demo Dial 1");
        app.pack();
        app.setVisible(true);
    }
}
