// 
// Decompiled by Procyon v0.5.30
// 

package KJEgui;

import java.awt.GridLayout;
import java.awt.Dimension;
import javax.swing.JComponent;
import java.awt.Font;
import javax.swing.JLabel;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.Component;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JPanel;

public class DataPanel extends JPanel
{
    GridBagLayout gridbag;
    GridBagConstraints c;
    public int iAlign;
    public int iHeight;
    private Color cFor;
    
    public DataPanel(final int n, final int n2, final Color cFor, final Color background, final Component component, final int iAlign) {
        this.iAlign = 4;
        this.iHeight = 30;
        this.cFor = Color.black;
        this.setBackground(background);
        this.setForeground(this.cFor = cFor);
        this.iHeight = component.getPreferredSize().height;
        this.iAlign = iAlign;
        this.gridbag = new GridBagLayout();
        this.c = new GridBagConstraints();
        this.setLayout(this.gridbag);
        this.c.anchor = 10;
        this.c.fill = 1;
        this.c.ipadx = 1 + n;
        this.c.ipady = 1 + n2;
        this.c.insets = new Insets(0, 0, 0, 0);
    }
    
    public DataPanel(final Color color, final Color color2, final Component component) {
        this(0, 0, color, color2, component, 4);
    }
    
    public JLabel JOutputLabel(final String s, final String toolTipText, final int n) {
        JLabel label;
        if (toolTipText == null) {
            label = new JLabel(s, n);
        }
        else {
            label = new ClickLabel(s, n);
            label.setToolTipText(toolTipText);
        }
        return label;
    }
    
    private void addJLabel(final String s, final Font font, final int n) {
        final JLabel label = new JLabel(s, n);
        label.setFont(font);
        this.add(label);
        this.gridbag.setConstraints(label, this.c);
    }
    
    private void addJLabel(final String s, final Font font, final int n, final Color foreground) {
        final JLabel label = new JLabel(s, n);
        label.setFont(font);
        label.setForeground(foreground);
        this.add(label);
        this.gridbag.setConstraints(label, this.c);
    }
    
    public void addRow(final Nbr nbr, final Nbr nbr2, final Font font, final int n) {
        this.addRow(nbr.getLabel(), nbr, nbr2.getLabel(), nbr2, font, n);
    }
    
    public void addRow(final Nbr nbr, final Nbr nbr2, final JLabel label, final Font font, final int n) {
        this.addRow(nbr.getLabel(), nbr, nbr2.getLabel(), nbr2, label, font, n);
    }
    
    public void addRow(final Nbr nbr, final Slider slider, final Font font, final int n) {
        final JLabel label = new JLabel(String.valueOf(nbr.getName()) + " ", this.iAlign);
        this.c.gridx = 0;
        this.c.gridy = n;
        this.c.weightx = 1.0;
        this.c.gridwidth = 3;
        this.c.anchor = 13;
        label.setFont(font);
        this.add(label);
        this.gridbag.setConstraints(label, this.c);
        label.resize(label.size().width / 2, label.size().height);
        this.c.gridx = 3;
        this.c.gridy = n;
        this.c.weightx = 1.0;
        this.c.gridwidth = 2;
        this.c.anchor = 17;
        nbr.setFont(font);
        this.add(nbr);
        this.gridbag.setConstraints(nbr, this.c);
        this.c.gridx = 5;
        this.c.gridy = n;
        this.c.weightx = 1.0;
        this.c.gridwidth = 5;
        this.c.anchor = 13;
        slider.setFont(font);
        this.add(slider);
        this.gridbag.setConstraints(slider, this.c);
        this.c.gridx = 10;
        this.c.gridy = n;
        this.c.weightx = 1.0;
        this.c.gridwidth = 1;
        this.c.anchor = 17;
        this.addJLabel(" ", font, this.iAlign);
    }
    
    public void addRow(final Nbr nbr, final Font font, final int n) {
        this.addRow(this.JOutputLabel(nbr.getLabel(), nbr.getHover(), this.iAlign), nbr, font, false, n);
    }
    
    public void addRow(final Nbr nbr, final String s, final JComponent component, final Font font, final int n) {
        this.addRow(nbr.getLabel(), nbr, s, component, font, n);
    }
    
    public void addRow(final Nbr nbr, final JComponent component, final Font font, final int n) {
        this.addRow(nbr.getLabel(), font, nbr, component, font, n);
    }
    
    public void addRow(final Nbr nbr, final JLabel label, final Nbr nbr2, final JLabel label2, final Font font, final int n) {
        this.addRow(nbr.getLabel(), nbr, label, nbr2.getLabel(), nbr2, label2, font, n);
    }
    
    public void addRow(final String s, final Font font, final JComponent component, final Font font2, final int n) {
        this.c.gridx = 0;
        this.c.gridy = n;
        this.c.weightx = 1.0;
        this.c.gridwidth = 1;
        this.addJLabel(String.valueOf(s) + " ", font, this.iAlign);
        this.c.gridx = 2;
        this.c.gridy = n;
        this.c.weightx = 1.0;
        this.c.gridwidth = 0;
        component.setFont(font2);
        this.add(component);
        this.gridbag.setConstraints(component, this.c);
    }
    
    public void addRow(final String s, final Font font, final JComponent component, final JComponent component2, final Font font2, final int n) {
        this.addRow(new JLabel(String.valueOf(s) + " ", this.iAlign), font, component, component2, font2, n);
    }
    
    public void addRow(final String s, final Font font, final JComponent component, final JComponent component2, final JComponent component3, final Font font2, final int gridy) {
        this.c.gridx = 0;
        this.c.gridy = gridy;
        this.c.weightx = 1.0;
        this.c.gridwidth = 4;
        this.addJLabel(String.valueOf(s) + " ", font, this.iAlign);
        this.c.gridx = 4;
        this.c.gridy = gridy;
        this.c.weightx = 1.0;
        this.c.gridwidth = 2;
        component.setFont(font2);
        this.add(component);
        this.gridbag.setConstraints(component, this.c);
        this.c.gridx = 6;
        this.c.gridy = gridy;
        this.c.weightx = 1.0;
        this.c.gridwidth = 2;
        component2.setFont(font2);
        this.add(component2);
        this.gridbag.setConstraints(component2, this.c);
        this.c.gridx = 8;
        this.c.gridy = gridy;
        this.c.weightx = 1.0;
        this.c.gridwidth = 2;
        component3.setFont(font2);
        this.add(component3);
        this.gridbag.setConstraints(component3, this.c);
        this.c.gridx = 10;
        this.c.gridy = gridy;
        this.c.weightx = 1.0;
        this.c.gridwidth = 1;
        this.addJLabel(" ", font2, this.iAlign);
    }
    
    public void addRow(final String s, final Font font, final JComponent component, final JComponent component2, final JComponent component3, final JComponent component4, final Font font2, final int n) {
        this.c.gridx = 0;
        this.c.gridy = n;
        this.c.weightx = 1.0;
        this.c.gridwidth = 4;
        this.addJLabel(String.valueOf(s) + " ", font, this.iAlign);
        this.c.gridx = 4;
        this.c.gridy = n;
        this.c.weightx = 1.0;
        this.c.gridwidth = 2;
        component.setFont(font2);
        this.add(component);
        this.gridbag.setConstraints(component, this.c);
        this.c.gridx = 6;
        this.c.gridy = n;
        this.c.weightx = 1.0;
        this.c.gridwidth = 2;
        component2.setFont(font2);
        this.add(component2);
        this.gridbag.setConstraints(component2, this.c);
        this.c.gridx = 8;
        this.c.gridy = n;
        this.c.weightx = 1.0;
        this.c.gridwidth = 2;
        component3.setFont(font2);
        this.add(component3);
        this.gridbag.setConstraints(component3, this.c);
        this.c.gridx = 10;
        this.c.gridy = n;
        this.c.weightx = 1.0;
        this.c.gridwidth = 2;
        component4.setFont(font2);
        this.add(component4);
        this.gridbag.setConstraints(component4, this.c);
        this.c.gridx = 12;
        this.c.gridy = n;
        this.c.weightx = 1.0;
        this.c.gridwidth = 1;
        this.addJLabel(" ", font2, this.iAlign);
    }
    
    public void addRow(final String s, final String s2, final Font font, final int gridy) {
        this.c.gridx = 0;
        this.c.gridy = gridy;
        this.c.weightx = 1.0;
        this.c.gridwidth = 5;
        this.addJLabel(String.valueOf(s) + " ", font, 0);
        this.c.gridx = 5;
        this.c.gridy = gridy;
        this.c.weightx = 1.0;
        this.c.gridwidth = 5;
        this.addJLabel(s2, font, 0);
        this.c.gridx = 10;
        this.c.gridy = gridy;
        this.c.weightx = 1.0;
        this.c.gridwidth = 1;
        this.addJLabel(" ", font, this.iAlign);
    }
    
    public void addRow(final String s, final String s2, final String s3, final String s4, final Font font, final int gridy) {
        this.c.gridx = 0;
        this.c.gridy = gridy;
        this.c.weightx = 1.0;
        this.c.gridwidth = 4;
        this.addJLabel(String.valueOf(s) + " ", font, this.iAlign);
        this.c.gridx = 4;
        this.c.gridy = gridy;
        this.c.weightx = 1.0;
        this.c.gridwidth = 2;
        this.addJLabel(s2, font, 0);
        this.c.gridx = 6;
        this.c.gridy = gridy;
        this.c.weightx = 1.0;
        this.c.gridwidth = 2;
        this.addJLabel(s3, font, 0);
        this.c.gridx = 8;
        this.c.gridy = gridy;
        this.c.weightx = 1.0;
        this.c.gridwidth = 2;
        this.addJLabel(s4, font, 0);
        this.c.gridx = 10;
        this.c.gridy = gridy;
        this.c.weightx = 1.0;
        this.c.gridwidth = 1;
        this.addJLabel(" ", font, this.iAlign);
    }
    
    public void addRow(final String s, final String s2, final String s3, final String s4, final String s5, final Font font, final int n) {
        this.c.gridx = 0;
        this.c.gridy = n;
        this.c.weightx = 1.0;
        this.c.gridwidth = 4;
        this.addJLabel(String.valueOf(s) + " ", font, this.iAlign);
        this.c.gridx = 4;
        this.c.gridy = n;
        this.c.weightx = 1.0;
        this.c.gridwidth = 2;
        this.addJLabel(s2, font, 0);
        this.c.gridx = 6;
        this.c.gridy = n;
        this.c.weightx = 1.0;
        this.c.gridwidth = 2;
        this.addJLabel(s3, font, 0);
        this.c.gridx = 8;
        this.c.gridy = n;
        this.c.weightx = 1.0;
        this.c.gridwidth = 2;
        this.addJLabel(s4, font, 0);
        this.c.gridx = 10;
        this.c.gridy = n;
        this.c.weightx = 1.0;
        this.c.gridwidth = 2;
        this.addJLabel(s5, font, 0);
        this.c.gridx = 12;
        this.c.gridy = n;
        this.c.weightx = 1.0;
        this.c.gridwidth = 1;
        this.addJLabel(" ", font, this.iAlign);
    }
    
    public void addRow(final String s, final JComponent component, final Font font, final int n) {
        this.c.gridx = 0;
        this.c.gridy = n;
        this.c.weightx = 1.5;
        this.c.gridwidth = 2;
        this.addJLabel(String.valueOf(s) + " ", font, this.iAlign, Color.black);
        this.c.gridx = 2;
        this.c.gridy = n;
        this.c.weightx = 1.0;
        this.c.gridwidth = 0;
        component.setFont(font);
        component.setPreferredSize(new Dimension(component.getPreferredSize().width, this.iHeight));
        component.setForeground(this.cFor);
        this.add(component);
        this.gridbag.setConstraints(component, this.c);
    }
    
    public void addRow(final String s, final JComponent component, final String s2, final JComponent component2, final Font font, final int n) {
        this.addRow(new JLabel(String.valueOf(s) + " ", this.iAlign), component, new JLabel(String.valueOf(s2) + " ", this.iAlign), component2, font, n);
    }
    
    public void addRow(final String s, final JComponent component, final String s2, final JComponent component2, final JLabel label, final Font font, final int n) {
        final JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 2));
        panel.setBackground(this.getBackground());
        panel.add(component2);
        panel.add(label);
        this.addRow(s, component, s2, panel, font, n);
    }
    
    public void addRow(final String s, final JComponent component, final JComponent component2, final Font font, final int n) {
        this.addRow(s, font, component, component2, font, n);
    }
    
    public void addRow(final String s, final JComponent component, final JLabel label, final String s2, final JComponent component2, final JLabel label2, final Font font, final int n) {
        final JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 2));
        panel.setBackground(this.getBackground());
        panel.add(component);
        panel.add(label);
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayout(1, 2));
        panel2.setBackground(this.getBackground());
        panel2.add(component2);
        panel2.add(label2);
        this.addRow(s, panel, s2, panel2, font, n);
    }
    
    public void addRow(final JComponent component, final Nbr nbr, final Nbr nbr2, final Font font, final int n) {
        this.addRow(component, nbr, new JLabel(nbr2.getLabel(), this.iAlign), nbr2, font, n);
    }
    
    public void addRow(final JComponent component, final Font font, final int gridy) {
        this.c.gridx = 0;
        this.c.gridy = gridy;
        this.c.weightx = 1.0;
        this.c.gridheight = 1;
        this.c.gridwidth = 0;
        component.setFont(font);
        component.setPreferredSize(new Dimension(component.getPreferredSize().width, this.iHeight));
        this.add(component);
        this.gridbag.setConstraints(component, this.c);
    }
    
    public void addRow(final JComponent component, final Font font, final JComponent component2, final JComponent component3, final Font font2, final int n) {
        this.c.gridx = 0;
        this.c.gridy = n;
        this.c.weightx = 1.0;
        this.c.gridwidth = 4;
        component.setFont(font);
        this.add(component);
        this.gridbag.setConstraints(component, this.c);
        this.c.gridx = 4;
        this.c.gridy = n;
        this.c.weightx = 1.0;
        this.c.gridwidth = 2;
        component2.setFont(font2);
        this.add(component2);
        this.gridbag.setConstraints(component2, this.c);
        this.c.gridx = 6;
        this.c.gridy = n;
        this.c.weightx = 1.0;
        this.c.gridwidth = 2;
        component3.setFont(font2);
        this.add(component3);
        this.gridbag.setConstraints(component3, this.c);
        this.c.gridx = 8;
        this.c.gridy = n;
        this.c.weightx = 1.0;
        this.c.gridwidth = 0;
        this.addJLabel(" ", font2, this.iAlign);
    }
    
    public void addRow(final JComponent component, final JComponent component2, final Font font, final int n) {
        this.addRow(component, component2, font, true, n);
    }
    
    public void addRow(final JComponent component, final JComponent component2, final Font font, final boolean b, final int gridy) {
        this.c.gridx = 0;
        this.c.gridy = gridy;
        this.c.weightx = 1.0;
        this.c.gridwidth = 5;
        component.setFont(font);
        component.setPreferredSize(new Dimension(component.getPreferredSize().width, this.iHeight));
        this.add(component);
        this.gridbag.setConstraints(component, this.c);
        this.c.gridx = 5;
        this.c.gridy = gridy;
        this.c.weightx = 1.0;
        this.c.gridwidth = 5;
        component2.setFont(font);
        component2.setPreferredSize(new Dimension(component2.getPreferredSize().width, this.iHeight));
        this.add(component2);
        this.gridbag.setConstraints(component2, this.c);
        if (b) {
            this.c.gridx = 10;
            this.c.gridy = gridy;
            this.c.weightx = 1.0;
            this.c.gridwidth = 1;
            this.addJLabel(" ", font, this.iAlign);
        }
    }
    
    public void addRow(final JComponent component, final JComponent component2, final JComponent component3, final JComponent component4, final Font font, final int gridy) {
        this.c.gridx = 0;
        this.c.gridy = gridy;
        this.c.weightx = 1.0;
        this.c.gridwidth = 3;
        this.c.anchor = 13;
        component.setFont(font);
        this.add(component);
        this.gridbag.setConstraints(component, this.c);
        component.resize(component.size().width / 2, component.size().height);
        this.c.gridx = 3;
        this.c.gridy = gridy;
        this.c.weightx = 1.0;
        this.c.gridwidth = 2;
        this.c.anchor = 17;
        component2.setFont(font);
        this.add(component2);
        this.gridbag.setConstraints(component2, this.c);
        this.c.gridx = 5;
        this.c.gridy = gridy;
        this.c.weightx = 1.0;
        this.c.gridwidth = 3;
        this.c.anchor = 13;
        component3.setFont(font);
        this.add(component3);
        this.gridbag.setConstraints(component3, this.c);
        this.c.gridx = 8;
        this.c.gridy = gridy;
        this.c.weightx = 1.0;
        this.c.gridwidth = 2;
        this.c.anchor = 17;
        component4.setFont(font);
        this.add(component4);
        this.gridbag.setConstraints(component4, this.c);
        this.c.gridx = 10;
        this.c.gridy = gridy;
        this.c.weightx = 1.0;
        this.c.gridwidth = 1;
        this.c.anchor = 17;
        this.addJLabel(" ", font, this.iAlign);
    }
    
    public void addRow(final JLabel label, final JLabel label2, final Font font, final int n) {
        this.addRow(label, (JComponent)label2, font, n);
    }
    
    public void addSpacer(final int gridy) {
        this.c.gridx = 0;
        this.c.gridy = gridy;
        this.c.weightx = 1.0;
        this.c.gridheight = 1;
        this.c.gridwidth = 0;
        final JLabel label = new JLabel("");
        label.setPreferredSize(new Dimension(1, 7));
        this.add(label);
        this.gridbag.setConstraints(label, this.c);
    }
}