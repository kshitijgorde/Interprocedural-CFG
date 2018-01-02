// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.ui;

import org.jfree.ui.FontChooserPanel;
import javax.swing.JColorChooser;
import java.awt.Color;
import javax.swing.JOptionPane;
import org.jfree.ui.StrokeChooserPanel;
import java.awt.event.ActionEvent;
import java.awt.Paint;
import javax.swing.JButton;
import java.awt.Component;
import javax.swing.JLabel;
import org.jfree.layout.LCBLayout;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Stroke;
import java.awt.BasicStroke;
import org.jfree.chart.StandardLegend;
import org.jfree.chart.Legend;
import java.util.ResourceBundle;
import org.jfree.ui.FontDisplayField;
import java.awt.Font;
import org.jfree.ui.PaintSample;
import org.jfree.ui.StrokeSample;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

class LegendPropertyEditPanel extends JPanel implements ActionListener
{
    private StrokeSample outlineStroke;
    private PaintSample outlinePaint;
    private PaintSample backgroundPaint;
    private Font seriesFont;
    private PaintSample seriesPaint;
    private StrokeSample[] availableStrokeSamples;
    private FontDisplayField fontDisplayField;
    protected static ResourceBundle localizationResources;
    
    public LegendPropertyEditPanel(final Legend legend) {
        final StandardLegend l = (StandardLegend)legend;
        this.outlineStroke = new StrokeSample(l.getOutlineStroke());
        this.outlinePaint = new PaintSample(l.getOutlinePaint());
        this.backgroundPaint = new PaintSample(l.getBackgroundPaint());
        this.seriesFont = l.getItemFont();
        this.seriesPaint = new PaintSample(l.getItemPaint());
        (this.availableStrokeSamples = new StrokeSample[4])[0] = new StrokeSample(new BasicStroke(1.0f));
        this.availableStrokeSamples[1] = new StrokeSample(new BasicStroke(2.0f));
        this.availableStrokeSamples[2] = new StrokeSample(new BasicStroke(3.0f));
        this.availableStrokeSamples[3] = new StrokeSample(new BasicStroke(4.0f));
        this.setLayout(new BorderLayout());
        final JPanel general = new JPanel(new BorderLayout());
        general.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), LegendPropertyEditPanel.localizationResources.getString("General")));
        final JPanel interior = new JPanel(new LCBLayout(5));
        interior.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
        interior.add(new JLabel(LegendPropertyEditPanel.localizationResources.getString("Outline")));
        interior.add(this.outlineStroke);
        JButton button = new JButton(LegendPropertyEditPanel.localizationResources.getString("Select..."));
        button.setActionCommand("OutlineStroke");
        button.addActionListener(this);
        interior.add(button);
        interior.add(new JLabel(LegendPropertyEditPanel.localizationResources.getString("Outline_Paint")));
        button = new JButton(LegendPropertyEditPanel.localizationResources.getString("Select..."));
        button.setActionCommand("OutlinePaint");
        button.addActionListener(this);
        interior.add(this.outlinePaint);
        interior.add(button);
        interior.add(new JLabel(LegendPropertyEditPanel.localizationResources.getString("Background")));
        button = new JButton(LegendPropertyEditPanel.localizationResources.getString("Select..."));
        button.setActionCommand("BackgroundPaint");
        button.addActionListener(this);
        interior.add(this.backgroundPaint);
        interior.add(button);
        interior.add(new JLabel(LegendPropertyEditPanel.localizationResources.getString("Series_label_font")));
        button = new JButton(LegendPropertyEditPanel.localizationResources.getString("Select..."));
        button.setActionCommand("SeriesFont");
        button.addActionListener(this);
        interior.add(this.fontDisplayField = new FontDisplayField(this.seriesFont));
        interior.add(button);
        interior.add(new JLabel(LegendPropertyEditPanel.localizationResources.getString("Series_label_paint")));
        button = new JButton(LegendPropertyEditPanel.localizationResources.getString("Select..."));
        button.setActionCommand("SeriesPaint");
        button.addActionListener(this);
        interior.add(this.seriesPaint);
        interior.add(button);
        general.add(interior);
        this.add(general, "North");
    }
    
    public Stroke getOutlineStroke() {
        return this.outlineStroke.getStroke();
    }
    
    public Paint getOutlinePaint() {
        return this.outlinePaint.getPaint();
    }
    
    public Paint getBackgroundPaint() {
        return this.backgroundPaint.getPaint();
    }
    
    public Font getSeriesFont() {
        return this.seriesFont;
    }
    
    public Paint getSeriesPaint() {
        return this.seriesPaint.getPaint();
    }
    
    public void actionPerformed(final ActionEvent event) {
        final String command = event.getActionCommand();
        if (command.equals("OutlineStroke")) {
            this.attemptModifyOutlineStroke();
        }
        else if (command.equals("OutlinePaint")) {
            this.attemptModifyOutlinePaint();
        }
        else if (command.equals("BackgroundPaint")) {
            this.attemptModifyBackgroundPaint();
        }
        else if (command.equals("SeriesFont")) {
            this.attemptModifySeriesFont();
        }
        else if (command.equals("SeriesPaint")) {
            this.attemptModifySeriesPaint();
        }
    }
    
    private void attemptModifyOutlineStroke() {
        final StrokeChooserPanel panel = new StrokeChooserPanel(this.outlineStroke, this.availableStrokeSamples);
        final int result = JOptionPane.showConfirmDialog(this, panel, LegendPropertyEditPanel.localizationResources.getString("Pen_Stroke_Selection"), 2, -1);
        if (result == 0) {
            this.outlineStroke.setStroke(panel.getSelectedStroke());
        }
    }
    
    private void attemptModifyOutlinePaint() {
        final Color c = JColorChooser.showDialog(this, LegendPropertyEditPanel.localizationResources.getString("Outline_Color"), Color.blue);
        if (c != null) {
            this.outlinePaint.setPaint(c);
        }
    }
    
    private void attemptModifyBackgroundPaint() {
        final Color c = JColorChooser.showDialog(this, LegendPropertyEditPanel.localizationResources.getString("Background_Color"), Color.blue);
        if (c != null) {
            this.backgroundPaint.setPaint(c);
        }
    }
    
    public void attemptModifySeriesFont() {
        final FontChooserPanel panel = new FontChooserPanel(this.seriesFont);
        final int result = JOptionPane.showConfirmDialog(this, panel, LegendPropertyEditPanel.localizationResources.getString("Font_Selection"), 2, -1);
        if (result == 0) {
            this.seriesFont = panel.getSelectedFont();
            this.fontDisplayField.setText(this.seriesFont.getFontName() + ", " + this.seriesFont.getSize());
        }
    }
    
    private void attemptModifySeriesPaint() {
        final Color c = JColorChooser.showDialog(this, LegendPropertyEditPanel.localizationResources.getString("Series_Label_Color"), Color.blue);
        if (c != null) {
            this.seriesPaint.setPaint(c);
        }
    }
    
    public void setLegendProperties(final Legend legend) {
        if (legend instanceof StandardLegend) {
            final StandardLegend standard = (StandardLegend)legend;
            standard.setOutlineStroke(this.getOutlineStroke());
            standard.setOutlinePaint(this.getOutlinePaint());
            standard.setBackgroundPaint(this.getBackgroundPaint());
            standard.setItemFont(this.getSeriesFont());
            standard.setItemPaint(this.getSeriesPaint());
        }
    }
    
    static {
        LegendPropertyEditPanel.localizationResources = ResourceBundle.getBundle("org.jfree.chart.ui.LocalizationBundle");
    }
}
