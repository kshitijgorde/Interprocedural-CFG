// 
// Decompiled by Procyon v0.5.30
// 

package medusa;

import javax.swing.border.Border;
import javax.swing.border.SoftBevelBorder;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.event.ItemEvent;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.event.ItemListener;
import javax.swing.JPanel;

public class ShowEdgePanel extends JPanel implements ItemListener
{
    private Color labelColor;
    private String text;
    private int labelWidth;
    private int labelHeight;
    private int number;
    private ShowEdgeMultiPanel parent;
    private JLabel edgeColorLabel;
    private JCheckBox edgeNameCheckBox;
    private JLabel numberLabel;
    
    public ShowEdgePanel() {
        this.labelWidth = 40;
        this.labelHeight = 40;
        this.number = 0;
        this.labelColor = new Color(200, 200, 200);
        this.text = "empty";
        this.initComponents();
    }
    
    public ShowEdgePanel(final Color labelColor, final String text, final int labelWidth, final int labelHeight, final int number, final ShowEdgeMultiPanel parent) {
        this.labelWidth = 40;
        this.labelHeight = 40;
        this.number = 0;
        this.parent = parent;
        this.labelColor = labelColor;
        this.text = text;
        this.labelWidth = labelWidth;
        this.labelHeight = labelHeight;
        this.number = number;
        this.initComponents();
    }
    
    @Override
    public void itemStateChanged(final ItemEvent itemEvent) {
        this.parent.handleEdgeEvent(this.getNumber(), this.edgeNameCheckBox.isSelected());
    }
    
    private void initComponents() {
        this.numberLabel = new JLabel();
        (this.edgeColorLabel = new JLabel()).setBackground(this.labelColor);
        final Dimension preferredSize = new Dimension(this.labelWidth, this.labelHeight);
        this.edgeColorLabel.setMaximumSize(preferredSize);
        this.edgeColorLabel.setMinimumSize(preferredSize);
        this.edgeColorLabel.setOpaque(true);
        this.edgeColorLabel.setPreferredSize(preferredSize);
        this.edgeNameCheckBox = new JCheckBox();
        this.setLayout(new FlowLayout(0, 5, 0));
        this.numberLabel.setText("jLabel1");
        if (this.number > 0) {
            this.numberLabel.setText(Integer.toString(this.number));
        }
        else {
            this.numberLabel.setText(" ");
        }
        this.add(this.numberLabel);
        this.edgeColorLabel.setBorder(new SoftBevelBorder(0));
        this.add(this.edgeColorLabel);
        this.edgeNameCheckBox.setOpaque(false);
        this.edgeNameCheckBox.setSelected(true);
        this.edgeNameCheckBox.setText(this.text);
        this.edgeNameCheckBox.addItemListener(this);
        this.add(this.edgeNameCheckBox);
    }
    
    @Override
    public String toString() {
        return this.text;
    }
    
    public int getNumber() {
        return this.number;
    }
}
