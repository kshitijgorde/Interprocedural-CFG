// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.ui;

import java.awt.event.MouseEvent;
import java.awt.GridLayout;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

public class Spinner extends JPanel implements MouseListener
{
    private int value;
    private JTextField textField;
    private JPanel buttonPanel;
    private ArrowPanel upButton;
    private ArrowPanel downButton;
    
    public Spinner(final int value) {
        super(new BorderLayout());
        this.value = value;
        (this.textField = new JTextField(Integer.toString(this.value))).setHorizontalAlignment(4);
        this.add(this.textField);
        this.buttonPanel = new JPanel(new GridLayout(2, 1, 0, 1));
        (this.upButton = new ArrowPanel(0)).addMouseListener(this);
        (this.downButton = new ArrowPanel(1)).addMouseListener(this);
        this.buttonPanel.add(this.upButton);
        this.buttonPanel.add(this.downButton);
        this.add(this.buttonPanel, "East");
    }
    
    public int getValue() {
        return this.value;
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        if (mouseEvent.getSource() == this.upButton) {
            ++this.value;
            this.textField.setText(Integer.toString(this.value));
            this.firePropertyChange("value", this.value - 1, this.value);
        }
        else if (mouseEvent.getSource() == this.downButton) {
            --this.value;
            this.textField.setText(Integer.toString(this.value));
            this.firePropertyChange("value", this.value + 1, this.value);
        }
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
}
