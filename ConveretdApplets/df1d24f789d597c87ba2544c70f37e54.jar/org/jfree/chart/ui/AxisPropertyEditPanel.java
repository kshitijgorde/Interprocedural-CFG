// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.ui;

import org.jfree.ui.InsetsChooserPanel;
import javax.swing.JColorChooser;
import java.awt.Color;
import javax.swing.JOptionPane;
import org.jfree.ui.FontChooserPanel;
import java.awt.event.ActionEvent;
import java.awt.Paint;
import javax.swing.JButton;
import org.jfree.ui.FontDisplayField;
import java.awt.Component;
import javax.swing.JLabel;
import org.jfree.layout.LCBLayout;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.Axis;
import java.util.ResourceBundle;
import javax.swing.JTabbedPane;
import java.awt.Insets;
import org.jfree.ui.InsetsTextField;
import javax.swing.JCheckBox;
import org.jfree.ui.PaintSample;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

public class AxisPropertyEditPanel extends JPanel implements ActionListener
{
    private JTextField label;
    private Font labelFont;
    private PaintSample labelPaintSample;
    private JTextField labelFontField;
    private Font tickLabelFont;
    private JTextField tickLabelFontField;
    private PaintSample tickLabelPaintSample;
    private JPanel slot1;
    private JPanel slot2;
    private JCheckBox showTickLabelsCheckBox;
    private JCheckBox showTickMarksCheckBox;
    private InsetsTextField tickLabelInsetsTextField;
    private InsetsTextField labelInsetsTextField;
    private Insets tickLabelInsets;
    private Insets labelInsets;
    private JTabbedPane otherTabs;
    protected static ResourceBundle localizationResources;
    
    public static AxisPropertyEditPanel getInstance(final Axis axis) {
        if (axis == null) {
            return null;
        }
        if (axis instanceof NumberAxis) {
            return new NumberAxisPropertyEditPanel((NumberAxis)axis);
        }
        return new AxisPropertyEditPanel(axis);
    }
    
    public AxisPropertyEditPanel(final Axis axis) {
        this.labelFont = axis.getLabelFont();
        this.labelPaintSample = new PaintSample(axis.getLabelPaint());
        this.tickLabelFont = axis.getTickLabelFont();
        this.tickLabelPaintSample = new PaintSample(axis.getTickLabelPaint());
        this.tickLabelInsets = axis.getTickLabelInsets();
        this.labelInsets = axis.getLabelInsets();
        this.setLayout(new BorderLayout());
        final JPanel general = new JPanel(new BorderLayout());
        general.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), AxisPropertyEditPanel.localizationResources.getString("General")));
        final JPanel interior = new JPanel(new LCBLayout(5));
        interior.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
        interior.add(new JLabel(AxisPropertyEditPanel.localizationResources.getString("Label")));
        interior.add(this.label = new JTextField(axis.getLabel()));
        interior.add(new JPanel());
        interior.add(new JLabel(AxisPropertyEditPanel.localizationResources.getString("Font")));
        interior.add(this.labelFontField = new FontDisplayField(this.labelFont));
        JButton b = new JButton(AxisPropertyEditPanel.localizationResources.getString("Select..."));
        b.setActionCommand("SelectLabelFont");
        b.addActionListener(this);
        interior.add(b);
        interior.add(new JLabel(AxisPropertyEditPanel.localizationResources.getString("Paint")));
        interior.add(this.labelPaintSample);
        b = new JButton(AxisPropertyEditPanel.localizationResources.getString("Select..."));
        b.setActionCommand("SelectLabelPaint");
        b.addActionListener(this);
        interior.add(b);
        interior.add(new JLabel(AxisPropertyEditPanel.localizationResources.getString("Label_Insets")));
        b = new JButton(AxisPropertyEditPanel.localizationResources.getString("Edit..."));
        b.setActionCommand("LabelInsets");
        b.addActionListener(this);
        interior.add(this.labelInsetsTextField = new InsetsTextField(this.labelInsets));
        interior.add(b);
        interior.add(new JLabel(AxisPropertyEditPanel.localizationResources.getString("Tick_Label_Insets")));
        b = new JButton(AxisPropertyEditPanel.localizationResources.getString("Edit..."));
        b.setActionCommand("TickLabelInsets");
        b.addActionListener(this);
        interior.add(this.tickLabelInsetsTextField = new InsetsTextField(this.tickLabelInsets));
        interior.add(b);
        general.add(interior);
        this.add(general, "North");
        this.slot1 = new JPanel(new BorderLayout());
        final JPanel other = new JPanel(new BorderLayout());
        other.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), AxisPropertyEditPanel.localizationResources.getString("Other")));
        (this.otherTabs = new JTabbedPane()).setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
        final JPanel ticks = new JPanel(new LCBLayout(3));
        ticks.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
        ticks.add(this.showTickLabelsCheckBox = new JCheckBox(AxisPropertyEditPanel.localizationResources.getString("Show_tick_labels"), axis.isTickLabelsVisible()));
        ticks.add(new JPanel());
        ticks.add(new JPanel());
        ticks.add(new JLabel(AxisPropertyEditPanel.localizationResources.getString("Tick_label_font")));
        ticks.add(this.tickLabelFontField = new FontDisplayField(this.tickLabelFont));
        b = new JButton(AxisPropertyEditPanel.localizationResources.getString("Select..."));
        b.setActionCommand("SelectTickLabelFont");
        b.addActionListener(this);
        ticks.add(b);
        ticks.add(this.showTickMarksCheckBox = new JCheckBox(AxisPropertyEditPanel.localizationResources.getString("Show_tick_marks"), axis.isTickMarksVisible()));
        ticks.add(new JPanel());
        ticks.add(new JPanel());
        this.otherTabs.add(AxisPropertyEditPanel.localizationResources.getString("Ticks"), ticks);
        other.add(this.otherTabs);
        this.slot1.add(other);
        (this.slot2 = new JPanel(new BorderLayout())).add(this.slot1, "North");
        this.add(this.slot2);
    }
    
    public String getLabel() {
        return this.label.getText();
    }
    
    public Font getLabelFont() {
        return this.labelFont;
    }
    
    public Paint getLabelPaint() {
        return this.labelPaintSample.getPaint();
    }
    
    public boolean isTickLabelsVisible() {
        return this.showTickLabelsCheckBox.isSelected();
    }
    
    public Font getTickLabelFont() {
        return this.tickLabelFont;
    }
    
    public Paint getTickLabelPaint() {
        return this.tickLabelPaintSample.getPaint();
    }
    
    public boolean isTickMarksVisible() {
        return this.showTickMarksCheckBox.isSelected();
    }
    
    public Insets getTickLabelInsets() {
        return (this.tickLabelInsets == null) ? new Insets(0, 0, 0, 0) : this.tickLabelInsets;
    }
    
    public Insets getLabelInsets() {
        return (this.labelInsets == null) ? new Insets(0, 0, 0, 0) : this.labelInsets;
    }
    
    public JTabbedPane getOtherTabs() {
        return this.otherTabs;
    }
    
    public void actionPerformed(final ActionEvent event) {
        final String command = event.getActionCommand();
        if (command.equals("SelectLabelFont")) {
            this.attemptLabelFontSelection();
        }
        else if (command.equals("SelectLabelPaint")) {
            this.attemptModifyLabelPaint();
        }
        else if (command.equals("SelectTickLabelFont")) {
            this.attemptTickLabelFontSelection();
        }
        else if (command.equals("LabelInsets")) {
            this.editLabelInsets();
        }
        else if (command.equals("TickLabelInsets")) {
            this.editTickLabelInsets();
        }
    }
    
    private void attemptLabelFontSelection() {
        final FontChooserPanel panel = new FontChooserPanel(this.labelFont);
        final int result = JOptionPane.showConfirmDialog(this, panel, AxisPropertyEditPanel.localizationResources.getString("Font_Selection"), 2, -1);
        if (result == 0) {
            this.labelFont = panel.getSelectedFont();
            this.labelFontField.setText(this.labelFont.getFontName() + " " + this.labelFont.getSize());
        }
    }
    
    private void attemptModifyLabelPaint() {
        final Color c = JColorChooser.showDialog(this, AxisPropertyEditPanel.localizationResources.getString("Label_Color"), Color.blue);
        if (c != null) {
            this.labelPaintSample.setPaint(c);
        }
    }
    
    public void attemptTickLabelFontSelection() {
        final FontChooserPanel panel = new FontChooserPanel(this.tickLabelFont);
        final int result = JOptionPane.showConfirmDialog(this, panel, AxisPropertyEditPanel.localizationResources.getString("Font_Selection"), 2, -1);
        if (result == 0) {
            this.tickLabelFont = panel.getSelectedFont();
            this.tickLabelFontField.setText(this.tickLabelFont.getFontName() + " " + this.tickLabelFont.getSize());
        }
    }
    
    private void editTickLabelInsets() {
        final InsetsChooserPanel panel = new InsetsChooserPanel(this.tickLabelInsets);
        final int result = JOptionPane.showConfirmDialog(this, panel, AxisPropertyEditPanel.localizationResources.getString("Edit_Insets"), -1);
        if (result == 0) {
            this.tickLabelInsets = panel.getInsets();
            this.tickLabelInsetsTextField.setInsets(this.tickLabelInsets);
        }
    }
    
    private void editLabelInsets() {
        final InsetsChooserPanel panel = new InsetsChooserPanel(this.labelInsets);
        final int result = JOptionPane.showConfirmDialog(this, panel, AxisPropertyEditPanel.localizationResources.getString("Edit_Insets"), -1);
        if (result == 0) {
            this.labelInsets = panel.getInsets();
            this.labelInsetsTextField.setInsets(this.labelInsets);
        }
    }
    
    public void setAxisProperties(final Axis axis) {
        axis.setLabel(this.getLabel());
        axis.setLabelFont(this.getLabelFont());
        axis.setLabelPaint(this.getLabelPaint());
        axis.setTickMarksVisible(this.isTickMarksVisible());
        axis.setTickLabelsVisible(this.isTickLabelsVisible());
        axis.setTickLabelFont(this.getTickLabelFont());
        axis.setTickLabelPaint(this.getTickLabelPaint());
        axis.setTickLabelInsets(this.getTickLabelInsets());
        axis.setLabelInsets(this.getLabelInsets());
    }
    
    static {
        AxisPropertyEditPanel.localizationResources = ResourceBundle.getBundle("org.jfree.chart.ui.LocalizationBundle");
    }
}