// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.ui;

import org.jfree.chart.title.TextTitle;
import javax.swing.JColorChooser;
import javax.swing.JOptionPane;
import org.jfree.ui.FontChooserPanel;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import org.jfree.ui.FontDisplayField;
import java.awt.Component;
import javax.swing.JLabel;
import org.jfree.layout.LCBLayout;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import java.awt.Paint;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import org.jfree.chart.title.Title;
import java.util.ResourceBundle;
import org.jfree.ui.PaintSample;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

public class TitlePropertyEditPanel extends JPanel implements ActionListener
{
    private JTextField titleField;
    private Font titleFont;
    private JTextField fontfield;
    private PaintSample titlePaint;
    protected static ResourceBundle localizationResources;
    
    public TitlePropertyEditPanel(final Title title) {
        this.setLayout(new BorderLayout());
        this.titlePaint = new PaintSample(Color.black);
        final JPanel general = new JPanel(new BorderLayout());
        general.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), TitlePropertyEditPanel.localizationResources.getString("General")));
        final JPanel interior = new JPanel(new LCBLayout(3));
        interior.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
        final JLabel titleLabel = new JLabel(TitlePropertyEditPanel.localizationResources.getString("Text"));
        this.titleField = new JTextField();
        interior.add(titleLabel);
        interior.add(this.titleField);
        interior.add(new JPanel());
        interior.add(new JLabel(TitlePropertyEditPanel.localizationResources.getString("Font")));
        this.fontfield = new FontDisplayField(this.titleFont);
        JButton b = new JButton(TitlePropertyEditPanel.localizationResources.getString("Select..."));
        b.setActionCommand("SelectFont");
        b.addActionListener(this);
        interior.add(this.fontfield);
        interior.add(b);
        interior.add(new JLabel(TitlePropertyEditPanel.localizationResources.getString("Color")));
        b = new JButton(TitlePropertyEditPanel.localizationResources.getString("Select..."));
        b.setActionCommand("SelectPaint");
        b.addActionListener(this);
        interior.add(this.titlePaint);
        interior.add(b);
        general.add(interior);
        this.add(general, "North");
    }
    
    public String getTitle() {
        return this.titleField.getText();
    }
    
    public Font getTitleFont() {
        return this.titleFont;
    }
    
    public Paint getTitlePaint() {
        return this.titlePaint.getPaint();
    }
    
    public void actionPerformed(final ActionEvent event) {
        final String command = event.getActionCommand();
        if (command.equals("SelectFont")) {
            this.attemptFontSelection();
        }
        else if (command.equals("SelectPaint")) {
            this.attemptPaintSelection();
        }
    }
    
    public void attemptFontSelection() {
        final FontChooserPanel panel = new FontChooserPanel(this.titleFont);
        final int result = JOptionPane.showConfirmDialog(this, panel, TitlePropertyEditPanel.localizationResources.getString("Font_Selection"), 2, -1);
        if (result == 0) {
            this.titleFont = panel.getSelectedFont();
            this.fontfield.setText(this.titleFont.getFontName() + " " + this.titleFont.getSize());
        }
    }
    
    public void attemptPaintSelection() {
        final Color c = JColorChooser.showDialog(this, TitlePropertyEditPanel.localizationResources.getString("Title_Color"), Color.blue);
        if (c != null) {
            this.titlePaint.setPaint(c);
        }
    }
    
    public void setTitleProperties(final Title title) {
        if (title instanceof TextTitle) {}
    }
    
    static {
        TitlePropertyEditPanel.localizationResources = ResourceBundle.getBundle("org.jfree.chart.ui.LocalizationBundle");
    }
}
