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
import javax.swing.JSlider;
import org.jfree.data.general.DefaultValueDataset;
import javax.swing.event.ChangeListener;
import javax.swing.JFrame;

public class DialDemo2 extends JFrame implements ChangeListener
{
    DefaultValueDataset dataset1;
    DefaultValueDataset dataset2;
    JSlider slider1;
    JSlider slider2;
    
    public DialDemo2(final String title) {
        super(title);
        this.dataset1 = new DefaultValueDataset(10.0);
        this.dataset2 = new DefaultValueDataset(50.0);
        final DialPlot plot = new DialPlot();
        plot.setView(0.0, 0.0, 1.0, 1.0);
        plot.setDataset(0, this.dataset1);
        plot.setDataset(1, this.dataset2);
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
        dvi.setFont(new Font("Dialog", 0, 10));
        dvi.setOutlinePaint(Color.darkGray);
        dvi.setRadius(0.6);
        dvi.setAngle(-103.0);
        plot.addLayer(dvi);
        final DialValueIndicator dvi2 = new DialValueIndicator(1, "c");
        dvi2.setFont(new Font("Dialog", 0, 10));
        dvi2.setOutlinePaint(Color.red);
        dvi2.setRadius(0.6);
        dvi2.setAngle(-77.0);
        plot.addLayer(dvi2);
        final StandardDialScale scale = new StandardDialScale(-40.0, 60.0, -120.0, -300.0);
        scale.setTickRadius(0.88);
        scale.setTickLabelOffset(0.15);
        scale.setTickLabelFont(new Font("Dialog", 0, 14));
        plot.addScale(0, scale);
        final StandardDialScale scale2 = new StandardDialScale(0.0, 100.0, -120.0, -300.0);
        scale2.setTickRadius(0.5);
        scale2.setTickLabelOffset(0.15);
        scale2.setTickLabelFont(new Font("Dialog", 0, 10));
        scale2.setMajorTickPaint(Color.red);
        plot.addScale(1, scale2);
        plot.mapDatasetToScale(1, 1);
        final DialPointer needle2 = new DialPointer.Pin(1);
        needle2.setRadius(0.55);
        plot.addLayer(needle2);
        final DialPointer needle3 = new DialPointer.Pointer(0);
        plot.addLayer(needle3);
        final DialCap cap = new DialCap();
        cap.setRadius(0.1);
        plot.setCap(cap);
        final JFreeChart chart1 = new JFreeChart(plot);
        chart1.setTitle("Dial Demo 2");
        final ChartPanel cp1 = new ChartPanel(chart1);
        cp1.setPreferredSize(new Dimension(400, 400));
        final JPanel sliderPanel = new JPanel(new GridLayout(2, 2));
        sliderPanel.add(new JLabel("Outer Needle:"));
        sliderPanel.add(new JLabel("Inner Needle:"));
        (this.slider1 = new JSlider(-40, 60)).setMajorTickSpacing(20);
        this.slider1.setPaintTicks(true);
        this.slider1.setPaintLabels(true);
        this.slider1.addChangeListener(this);
        sliderPanel.add(this.slider1);
        sliderPanel.add(this.slider1);
        (this.slider2 = new JSlider(0, 100)).setMajorTickSpacing(20);
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
        this.dataset1.setValue(new Integer(this.slider1.getValue()));
        this.dataset2.setValue(new Integer(this.slider2.getValue()));
    }
    
    public static void main(final String[] args) {
        final DialDemo2 app = new DialDemo2("JFreeChart - Dial Demo 2");
        app.pack();
        app.setVisible(true);
    }
}
