// 
// Decompiled by Procyon v0.5.30
// 

package com.magelang.editors;

import java.awt.event.MouseEvent;
import java.awt.Insets;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.awt.Color;
import java.beans.PropertyChangeSupport;
import java.awt.event.MouseListener;

public class BasicColorSelector extends ColorSelector implements MouseListener
{
    protected transient PropertyChangeSupport propertyChange;
    private ColorToggle selectedToggle;
    private static String[] colorNames;
    private static Color[] colors;
    
    public BasicColorSelector() {
        this.propertyChange = new PropertyChangeSupport(this);
        this.selectedToggle = null;
        this.setLayout(new GridLayout(0, 2));
        this.setBackground(Color.lightGray);
        for (int i = 0; i < 14; ++i) {
            final ColorToggle colorToggle = new ColorToggle((BasicColorSelector.colorNames[i] == null) ? "null" : (String.valueOf("java.awt.Color.") + BasicColorSelector.colorNames[i]), BasicColorSelector.colors[i]);
            colorToggle.addMouseListener(this);
            this.add(colorToggle);
        }
    }
    
    public Insets getInsets() {
        return new Insets(5, 5, 5, 5);
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void setSelectedColor(final Color selectedColor) {
        super.setSelectedColor(selectedColor);
        if (this.selectedToggle != null) {
            this.selectedToggle.setState(false);
        }
        final Component[] components = this.getComponents();
        for (int i = this.getComponentCount() - 1; i > -1; --i) {
            if ((selectedColor == null && ((ColorToggle)components[i]).getColor() == null) || (selectedColor != null && selectedColor.equals(((ColorToggle)components[i]).getColor()))) {
                this.selectedToggle = (ColorToggle)components[i];
            }
        }
        this.setInitString(this.selectedToggle.getColorInit());
        this.selectedToggle.setState(true);
        this.invalidate();
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        this.setSelectedColor(((ColorToggle)mouseEvent.getSource()).getColor());
    }
    
    static {
        BasicColorSelector.colorNames = new String[] { "white", "lightGray", "gray", "darkGray", "black", "red", "pink", "orange", "yellow", "green", "magenta", "cyan", "blue", null };
        BasicColorSelector.colors = new Color[] { Color.white, Color.lightGray, Color.gray, Color.darkGray, Color.black, Color.red, Color.pink, Color.orange, Color.yellow, Color.green, Color.magenta, Color.cyan, Color.blue, null };
    }
}
