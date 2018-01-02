// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.ui;

import org.jfree.ui.InsetsChooserPanel;
import javax.swing.JOptionPane;
import org.jfree.ui.StrokeChooserPanel;
import javax.swing.JColorChooser;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.Paint;
import org.jfree.chart.axis.ColorBar;
import org.jfree.chart.axis.Axis;
import org.jfree.chart.plot.ContourPlot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.plot.CategoryPlot;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import java.awt.Component;
import javax.swing.JLabel;
import org.jfree.layout.LCBLayout;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import java.awt.Stroke;
import java.awt.BasicStroke;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import org.jfree.chart.plot.Plot;
import java.util.ResourceBundle;
import org.jfree.ui.InsetsTextField;
import java.awt.Insets;
import org.jfree.ui.StrokeSample;
import org.jfree.ui.PaintSample;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

public class PlotPropertyEditPanel extends JPanel implements ActionListener
{
    private PaintSample backgroundPaintSample;
    private StrokeSample outlineStrokeSample;
    private PaintSample outlinePaintSample;
    private AxisPropertyEditPanel domainAxisPropertyPanel;
    private AxisPropertyEditPanel rangeAxisPropertyPanel;
    private ColorBarPropertyEditPanel colorBarAxisPropertyPanel;
    private StrokeSample[] availableStrokeSamples;
    private Insets plotInsets;
    private InsetsTextField insetsTextField;
    protected static ResourceBundle localizationResources;
    
    public PlotPropertyEditPanel(final Plot plot) {
        this.plotInsets = plot.getInsets();
        this.backgroundPaintSample = new PaintSample(plot.getBackgroundPaint());
        this.outlineStrokeSample = new StrokeSample(plot.getOutlineStroke());
        this.outlinePaintSample = new PaintSample(plot.getOutlinePaint());
        this.setLayout(new BorderLayout());
        (this.availableStrokeSamples = new StrokeSample[3])[0] = new StrokeSample(new BasicStroke(1.0f));
        this.availableStrokeSamples[1] = new StrokeSample(new BasicStroke(2.0f));
        this.availableStrokeSamples[2] = new StrokeSample(new BasicStroke(3.0f));
        final JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), plot.getPlotType() + PlotPropertyEditPanel.localizationResources.getString(":")));
        final JPanel general = new JPanel(new BorderLayout());
        general.setBorder(BorderFactory.createTitledBorder(PlotPropertyEditPanel.localizationResources.getString("General")));
        final JPanel interior = new JPanel(new LCBLayout(4));
        interior.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
        interior.add(new JLabel(PlotPropertyEditPanel.localizationResources.getString("Insets")));
        JButton button = new JButton(PlotPropertyEditPanel.localizationResources.getString("Edit..."));
        button.setActionCommand("Insets");
        button.addActionListener(this);
        (this.insetsTextField = new InsetsTextField(this.plotInsets)).setEnabled(false);
        interior.add(this.insetsTextField);
        interior.add(button);
        interior.add(new JLabel(PlotPropertyEditPanel.localizationResources.getString("Outline_stroke")));
        button = new JButton(PlotPropertyEditPanel.localizationResources.getString("Select..."));
        button.setActionCommand("OutlineStroke");
        button.addActionListener(this);
        interior.add(this.outlineStrokeSample);
        interior.add(button);
        interior.add(new JLabel(PlotPropertyEditPanel.localizationResources.getString("Outline_Paint")));
        button = new JButton(PlotPropertyEditPanel.localizationResources.getString("Select..."));
        button.setActionCommand("OutlinePaint");
        button.addActionListener(this);
        interior.add(this.outlinePaintSample);
        interior.add(button);
        interior.add(new JLabel(PlotPropertyEditPanel.localizationResources.getString("Background_paint")));
        button = new JButton(PlotPropertyEditPanel.localizationResources.getString("Select..."));
        button.setActionCommand("BackgroundPaint");
        button.addActionListener(this);
        interior.add(this.backgroundPaintSample);
        interior.add(button);
        general.add(interior, "North");
        final JPanel appearance = new JPanel(new BorderLayout());
        appearance.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
        appearance.add(general, "North");
        final JTabbedPane tabs = new JTabbedPane();
        tabs.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
        Axis domainAxis = null;
        if (plot instanceof CategoryPlot) {
            domainAxis = ((CategoryPlot)plot).getDomainAxis();
        }
        else if (plot instanceof XYPlot) {
            domainAxis = ((XYPlot)plot).getDomainAxis();
        }
        this.domainAxisPropertyPanel = AxisPropertyEditPanel.getInstance(domainAxis);
        if (this.domainAxisPropertyPanel != null) {
            this.domainAxisPropertyPanel.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
            tabs.add(PlotPropertyEditPanel.localizationResources.getString("Domain_Axis"), this.domainAxisPropertyPanel);
        }
        Axis rangeAxis = null;
        if (plot instanceof CategoryPlot) {
            rangeAxis = ((CategoryPlot)plot).getRangeAxis();
        }
        else if (plot instanceof XYPlot) {
            rangeAxis = ((XYPlot)plot).getRangeAxis();
        }
        this.rangeAxisPropertyPanel = AxisPropertyEditPanel.getInstance(rangeAxis);
        if (this.rangeAxisPropertyPanel != null) {
            this.rangeAxisPropertyPanel.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
            tabs.add(PlotPropertyEditPanel.localizationResources.getString("Range_Axis"), this.rangeAxisPropertyPanel);
        }
        ColorBar colorBar = null;
        if (plot instanceof ContourPlot) {
            colorBar = ((ContourPlot)plot).getColorBar();
        }
        this.colorBarAxisPropertyPanel = ColorBarPropertyEditPanel.getInstance(colorBar);
        if (this.colorBarAxisPropertyPanel != null) {
            this.colorBarAxisPropertyPanel.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
            tabs.add(PlotPropertyEditPanel.localizationResources.getString("Color_Bar"), this.colorBarAxisPropertyPanel);
        }
        tabs.add(PlotPropertyEditPanel.localizationResources.getString("Appearance"), appearance);
        panel.add(tabs);
        this.add(panel);
    }
    
    public Insets getPlotInsets() {
        if (this.plotInsets == null) {
            this.plotInsets = new Insets(0, 0, 0, 0);
        }
        return this.plotInsets;
    }
    
    public Paint getBackgroundPaint() {
        return this.backgroundPaintSample.getPaint();
    }
    
    public Stroke getOutlineStroke() {
        return this.outlineStrokeSample.getStroke();
    }
    
    public Paint getOutlinePaint() {
        return this.outlinePaintSample.getPaint();
    }
    
    public AxisPropertyEditPanel getDomainAxisPropertyEditPanel() {
        return this.domainAxisPropertyPanel;
    }
    
    public AxisPropertyEditPanel getRangeAxisPropertyEditPanel() {
        return this.rangeAxisPropertyPanel;
    }
    
    public void actionPerformed(final ActionEvent event) {
        final String command = event.getActionCommand();
        if (command.equals("BackgroundPaint")) {
            this.attemptBackgroundPaintSelection();
        }
        else if (command.equals("OutlineStroke")) {
            this.attemptOutlineStrokeSelection();
        }
        else if (command.equals("OutlinePaint")) {
            this.attemptOutlinePaintSelection();
        }
        else if (command.equals("Insets")) {
            this.editInsets();
        }
    }
    
    private void attemptBackgroundPaintSelection() {
        final Color c = JColorChooser.showDialog(this, PlotPropertyEditPanel.localizationResources.getString("Background_Color"), Color.blue);
        if (c != null) {
            this.backgroundPaintSample.setPaint(c);
        }
    }
    
    private void attemptOutlineStrokeSelection() {
        final StrokeChooserPanel panel = new StrokeChooserPanel(null, this.availableStrokeSamples);
        final int result = JOptionPane.showConfirmDialog(this, panel, PlotPropertyEditPanel.localizationResources.getString("Stroke_Selection"), 2, -1);
        if (result == 0) {
            this.outlineStrokeSample.setStroke(panel.getSelectedStroke());
        }
    }
    
    private void attemptOutlinePaintSelection() {
        final Color c = JColorChooser.showDialog(this, PlotPropertyEditPanel.localizationResources.getString("Outline_Color"), Color.blue);
        if (c != null) {
            this.outlinePaintSample.setPaint(c);
        }
    }
    
    private void editInsets() {
        final InsetsChooserPanel panel = new InsetsChooserPanel(this.plotInsets);
        final int result = JOptionPane.showConfirmDialog(this, panel, PlotPropertyEditPanel.localizationResources.getString("Edit_Insets"), 2, -1);
        if (result == 0) {
            this.plotInsets = panel.getInsets();
            this.insetsTextField.setInsets(this.plotInsets);
        }
    }
    
    public void updatePlotProperties(final Plot plot) {
        plot.setOutlinePaint(this.getOutlinePaint());
        plot.setOutlineStroke(this.getOutlineStroke());
        plot.setBackgroundPaint(this.getBackgroundPaint());
        plot.setInsets(this.getPlotInsets());
        if (this.domainAxisPropertyPanel != null) {
            Axis domainAxis = null;
            if (plot instanceof CategoryPlot) {
                final CategoryPlot p = (CategoryPlot)plot;
                domainAxis = p.getDomainAxis();
            }
            else if (plot instanceof XYPlot) {
                final XYPlot p2 = (XYPlot)plot;
                domainAxis = p2.getDomainAxis();
            }
            if (domainAxis != null) {
                this.domainAxisPropertyPanel.setAxisProperties(domainAxis);
            }
        }
        if (this.rangeAxisPropertyPanel != null) {
            Axis rangeAxis = null;
            if (plot instanceof CategoryPlot) {
                final CategoryPlot p = (CategoryPlot)plot;
                rangeAxis = p.getRangeAxis();
            }
            else if (plot instanceof XYPlot) {
                final XYPlot p2 = (XYPlot)plot;
                rangeAxis = p2.getRangeAxis();
            }
            if (rangeAxis != null) {
                this.rangeAxisPropertyPanel.setAxisProperties(rangeAxis);
            }
        }
        if (this.colorBarAxisPropertyPanel != null) {
            ColorBar colorBar = null;
            if (plot instanceof ContourPlot) {
                final ContourPlot p3 = (ContourPlot)plot;
                colorBar = p3.getColorBar();
            }
            if (colorBar != null) {
                this.colorBarAxisPropertyPanel.setAxisProperties(colorBar);
            }
        }
    }
    
    static {
        PlotPropertyEditPanel.localizationResources = ResourceBundle.getBundle("org.jfree.chart.ui.LocalizationBundle");
    }
}
