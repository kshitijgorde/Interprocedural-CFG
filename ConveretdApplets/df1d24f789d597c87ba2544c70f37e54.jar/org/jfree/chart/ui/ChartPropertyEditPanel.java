// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.ui;

import javax.swing.JColorChooser;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.Paint;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.Legend;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Component;
import org.jfree.layout.LCBLayout;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import org.jfree.chart.JFreeChart;
import java.util.ResourceBundle;
import org.jfree.ui.PaintSample;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

public class ChartPropertyEditPanel extends JPanel implements ActionListener
{
    private LegendPropertyEditPanel legendPropertiesPanel;
    private PlotPropertyEditPanel plotPropertiesPanel;
    private JCheckBox antialias;
    private PaintSample background;
    protected static ResourceBundle localizationResources;
    
    public ChartPropertyEditPanel(final JFreeChart chart) {
        this.setLayout(new BorderLayout());
        final JPanel other = new JPanel(new BorderLayout());
        other.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
        final JPanel general = new JPanel(new BorderLayout());
        general.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), ChartPropertyEditPanel.localizationResources.getString("General")));
        final JPanel interior = new JPanel(new LCBLayout(6));
        interior.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
        (this.antialias = new JCheckBox(ChartPropertyEditPanel.localizationResources.getString("Draw_anti-aliased"))).setSelected(chart.getAntiAlias());
        interior.add(this.antialias);
        interior.add(new JLabel(""));
        interior.add(new JLabel(""));
        interior.add(new JLabel(ChartPropertyEditPanel.localizationResources.getString("Background_paint")));
        interior.add(this.background = new PaintSample(chart.getBackgroundPaint()));
        JButton button = new JButton(ChartPropertyEditPanel.localizationResources.getString("Select..."));
        button.setActionCommand("BackgroundPaint");
        button.addActionListener(this);
        interior.add(button);
        interior.add(new JLabel(ChartPropertyEditPanel.localizationResources.getString("Series_Paint")));
        JTextField info = new JTextField(ChartPropertyEditPanel.localizationResources.getString("No_editor_implemented"));
        info.setEnabled(false);
        interior.add(info);
        button = new JButton(ChartPropertyEditPanel.localizationResources.getString("Edit..."));
        button.setEnabled(false);
        interior.add(button);
        interior.add(new JLabel(ChartPropertyEditPanel.localizationResources.getString("Series_Stroke")));
        info = new JTextField(ChartPropertyEditPanel.localizationResources.getString("No_editor_implemented"));
        info.setEnabled(false);
        interior.add(info);
        button = new JButton(ChartPropertyEditPanel.localizationResources.getString("Edit..."));
        button.setEnabled(false);
        interior.add(button);
        interior.add(new JLabel(ChartPropertyEditPanel.localizationResources.getString("Series_Outline_Paint")));
        info = new JTextField(ChartPropertyEditPanel.localizationResources.getString("No_editor_implemented"));
        info.setEnabled(false);
        interior.add(info);
        button = new JButton(ChartPropertyEditPanel.localizationResources.getString("Edit..."));
        button.setEnabled(false);
        interior.add(button);
        interior.add(new JLabel(ChartPropertyEditPanel.localizationResources.getString("Series_Outline_Stroke")));
        info = new JTextField(ChartPropertyEditPanel.localizationResources.getString("No_editor_implemented"));
        info.setEnabled(false);
        interior.add(info);
        button = new JButton(ChartPropertyEditPanel.localizationResources.getString("Edit..."));
        button.setEnabled(false);
        interior.add(button);
        general.add(interior, "North");
        other.add(general, "North");
        final JPanel parts = new JPanel(new BorderLayout());
        final Legend legend = chart.getLegend();
        final Plot plot = chart.getPlot();
        final JTabbedPane tabs = new JTabbedPane();
        if (legend != null) {
            (this.legendPropertiesPanel = new LegendPropertyEditPanel(legend)).setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
            tabs.addTab(ChartPropertyEditPanel.localizationResources.getString("Legend"), this.legendPropertiesPanel);
        }
        (this.plotPropertiesPanel = new PlotPropertyEditPanel(plot)).setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
        tabs.addTab(ChartPropertyEditPanel.localizationResources.getString("Plot"), this.plotPropertiesPanel);
        tabs.add(ChartPropertyEditPanel.localizationResources.getString("Other"), other);
        parts.add(tabs, "North");
        this.add(parts);
    }
    
    public TitlePropertyEditPanel getTitlePropertyEditPanel() {
        return null;
    }
    
    public LegendPropertyEditPanel getLegendPropertyEditPanel() {
        return this.legendPropertiesPanel;
    }
    
    public PlotPropertyEditPanel getPlotPropertyEditPanel() {
        return this.plotPropertiesPanel;
    }
    
    public boolean getAntiAlias() {
        return this.antialias.isSelected();
    }
    
    public Paint getBackgroundPaint() {
        return this.background.getPaint();
    }
    
    public void actionPerformed(final ActionEvent event) {
        final String command = event.getActionCommand();
        if (command.equals("BackgroundPaint")) {
            this.attemptModifyBackgroundPaint();
        }
    }
    
    private void attemptModifyBackgroundPaint() {
        final Color c = JColorChooser.showDialog(this, ChartPropertyEditPanel.localizationResources.getString("Background_Color"), Color.blue);
        if (c != null) {
            this.background.setPaint(c);
        }
    }
    
    public void updateChartProperties(final JFreeChart chart) {
        if (this.legendPropertiesPanel != null) {
            this.legendPropertiesPanel.setLegendProperties(chart.getLegend());
        }
        this.plotPropertiesPanel.updatePlotProperties(chart.getPlot());
        chart.setAntiAlias(this.getAntiAlias());
        chart.setBackgroundPaint(this.getBackgroundPaint());
    }
    
    static {
        ChartPropertyEditPanel.localizationResources = ResourceBundle.getBundle("org.jfree.chart.ui.LocalizationBundle");
    }
}
