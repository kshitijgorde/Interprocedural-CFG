// 
// Decompiled by Procyon v0.5.30
// 

package com.magelang.tabsplitter;

import java.beans.PropertyChangeEvent;
import java.awt.event.ActionEvent;
import java.awt.Rectangle;
import java.awt.Graphics;
import com.magelang.editors.ColorSelector;
import com.magelang.editors.BasicColorSelector;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Component;
import java.beans.PropertyChangeSupport;
import java.awt.Button;
import java.beans.PropertyEditor;
import java.beans.PropertyChangeListener;
import java.awt.event.ActionListener;
import java.awt.Panel;

public class TabColorEditor extends Panel implements ActionListener, PropertyChangeListener, PropertyEditor
{
    private Button addButton;
    private Button removeButton;
    private Panel buttonPanel;
    private TabPanel tabPanel;
    protected transient PropertyChangeSupport propertyChange;
    
    public Component getCustomEditor() {
        return this;
    }
    
    public TabColorEditor() {
        this.addButton = new Button("Add Tab Color");
        this.removeButton = new Button("Remove Tab Color");
        this.buttonPanel = new Panel();
        this.tabPanel = new TabPanel();
        this.propertyChange = new PropertyChangeSupport(this);
        this.setName("TabColorEditor");
        this.setLayout(new BorderLayout());
        this.add("Center", this.tabPanel);
        this.buttonPanel.setLayout(new FlowLayout());
        this.buttonPanel.add(this.addButton, this.addButton.getName());
        this.buttonPanel.add(this.removeButton, this.removeButton.getName());
        this.add("South", this.buttonPanel);
        this.addButton.addActionListener(this);
        this.removeButton.addActionListener(this);
    }
    
    public void firePropertyChange(final String s, final Object o, final Object o2) {
        this.propertyChange.firePropertyChange(s, o, o2);
    }
    
    public void setAsText(final String s) throws IllegalArgumentException {
    }
    
    public boolean isPaintable() {
        return true;
    }
    
    public String getAsText() {
        return null;
    }
    
    protected void addTabColor(final Color selectedColor) {
        final BasicColorSelector basicColorSelector = new BasicColorSelector();
        basicColorSelector.setSelectedColor(selectedColor);
        basicColorSelector.addPropertyChangeListener(this);
        this.tabPanel.add("  ", basicColorSelector);
        this.tabPanel.show(basicColorSelector);
        final int componentCount = this.tabPanel.getComponentCount();
        if (componentCount > this.tabPanel.getTabColors().length) {
            this.tabPanel.setTabColors(componentCount - 1, selectedColor);
            this.firePropertyChange("tabColors", null, this.tabPanel.getTabColors());
        }
    }
    
    protected void removeCurrentTabColor() {
        if (this.tabPanel.getComponentCount() > 1) {
            final int selectedTabNum = this.tabPanel.getSelectedTabNum();
            this.tabPanel.remove(selectedTabNum);
            final Color[] tabColors = this.tabPanel.getTabColors();
            final Color[] tabColors2 = new Color[tabColors.length - 1];
            if (selectedTabNum > 0) {
                System.arraycopy(tabColors, 0, tabColors2, 0, selectedTabNum);
            }
            if (selectedTabNum < tabColors.length - 1) {
                System.arraycopy(tabColors, selectedTabNum + 1, tabColors2, selectedTabNum, tabColors.length - selectedTabNum - 1);
            }
            this.tabPanel.setTabColors(tabColors2);
        }
    }
    
    public void setValue(final Object o) {
        this.setTabColors((Color[])o);
    }
    
    public void removePropertyChangeListener(final PropertyChangeListener propertyChangeListener) {
        this.propertyChange.removePropertyChangeListener(propertyChangeListener);
    }
    
    public String getJavaInitializationString() {
        String string = "new java.awt.Color[] {";
        final Color[] tabColors = this.tabPanel.getTabColors();
        final Component[] components = this.tabPanel.getComponents();
        for (int i = 0; i < tabColors.length; ++i) {
            string = String.valueOf(string) + ((ColorSelector)components[i]).getJavaInitializationString() + ",";
        }
        return String.valueOf(string) + "}";
    }
    
    public void paintValue(final Graphics graphics, final Rectangle rectangle) {
        final Color[] tabColors = this.tabPanel.getTabColors();
        final int n = rectangle.width / tabColors.length;
        int x = rectangle.x;
        for (int i = 0; i < tabColors.length; ++i) {
            final Color color = tabColors[i];
            graphics.setColor((color == null) ? Color.lightGray : color);
            graphics.fill3DRect(x, rectangle.y, n - 1, rectangle.height - 1, true);
            x += n;
        }
    }
    
    public Object getValue() {
        return this.tabPanel.getTabColors();
    }
    
    public String[] getTags() {
        return null;
    }
    
    public void addPropertyChangeListener(final PropertyChangeListener propertyChangeListener) {
        this.propertyChange.addPropertyChangeListener(propertyChangeListener);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.addButton) {
            this.addTabColor(null);
        }
        else if (actionEvent.getSource() == this.removeButton) {
            this.removeCurrentTabColor();
        }
    }
    
    public void propertyChange(final PropertyChangeEvent propertyChangeEvent) {
        this.tabPanel.getTabColors();
        this.tabPanel.setTabColors(this.tabPanel.getSelectedTabNum(), ((ColorSelector)propertyChangeEvent.getSource()).getSelectedColor());
        this.firePropertyChange("tabColors", null, this.tabPanel.getTabColors());
        this.invalidate();
        if (this.tabPanel.isVisible()) {
            this.tabPanel.repaint();
        }
    }
    
    public void setTabColors(final Color[] tabColors) {
        final Color[] tabColors2 = this.tabPanel.getTabColors();
        this.tabPanel.setTabColors(tabColors);
        for (int i = this.tabPanel.getComponentCount(); i < tabColors.length; ++i) {
            this.addTabColor(tabColors[i]);
        }
        this.tabPanel.show(this.tabPanel.getComponent(0));
        this.firePropertyChange("tabColors", tabColors2, tabColors);
    }
    
    public boolean supportsCustomEditor() {
        return true;
    }
}
