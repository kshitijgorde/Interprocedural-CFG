// 
// Decompiled by Procyon v0.5.30
// 

package pclient.adv;

import java.awt.Dimension;
import javax.swing.event.CaretListener;
import java.awt.event.KeyListener;
import javax.swing.border.Border;
import java.awt.ComponentOrientation;
import java.awt.Color;
import java.awt.Font;
import java.awt.Component;
import pclient.shd.Config;
import javax.swing.JTextArea;
import java.awt.TextArea;

public class DualTextArea
{
    private TextArea oldInput;
    private JTextArea newInput;
    private boolean isOld;
    
    public DualTextArea(final Config config, final boolean b) {
        this.oldInput = null;
        this.newInput = null;
        this.isOld = true;
        this.isOld = config.getBool("Op.Inputs.Old", true);
        this.createAreas(b);
    }
    
    public DualTextArea(final boolean isOld, final boolean b) {
        this.oldInput = null;
        this.newInput = null;
        this.isOld = true;
        this.isOld = isOld;
        this.createAreas(b);
    }
    
    private void createAreas(final boolean b) {
        this.newInput = new JTextArea();
        if (b) {
            this.oldInput = new TextArea();
        }
        else {
            this.oldInput = new TextArea(" ", 1, 30, 3);
        }
    }
    
    public boolean isOldVersion() {
        return this.isOld;
    }
    
    public Component getBox() {
        if (this.isOld) {
            return this.oldInput;
        }
        return this.newInput;
    }
    
    public int getCaretPosition() {
        if (this.isOld) {
            return this.oldInput.getCaretPosition();
        }
        return this.newInput.getCaretPosition();
    }
    
    public void setFont(final Font font) {
        if (this.isOld) {
            this.oldInput.setFont(font);
        }
        else {
            this.newInput.setFont(font);
        }
    }
    
    public void insert(final String s, final int n) {
        if (this.isOld) {
            this.oldInput.insert(s, n);
        }
        else {
            this.newInput.insert(s, n);
        }
    }
    
    public void setForeground(final Color color) {
        if (this.isOld) {
            this.oldInput.setForeground(color);
        }
        else {
            this.newInput.setForeground(color);
        }
    }
    
    public void setBackground(final Color color) {
        if (this.isOld) {
            this.oldInput.setBackground(color);
        }
        else {
            this.newInput.setBackground(color);
        }
    }
    
    public void setComponentOrientation(final ComponentOrientation componentOrientation) {
        if (this.isOld) {
            this.oldInput.setComponentOrientation(componentOrientation);
        }
        else {
            this.newInput.setComponentOrientation(componentOrientation);
        }
    }
    
    public void setColumns(final int n) {
        if (this.isOld) {
            this.oldInput.setColumns(n);
        }
        else {
            this.newInput.setColumns(n);
        }
    }
    
    public int getRows() {
        if (this.isOld) {
            return this.oldInput.getRows();
        }
        return this.newInput.getRows();
    }
    
    public void setRows(final int n) {
        if (this.isOld) {
            this.oldInput.setRows(n);
        }
        else {
            this.newInput.setRows(n);
        }
    }
    
    public void invalidate() {
        if (this.isOld) {
            this.oldInput.invalidate();
        }
        else {
            this.newInput.invalidate();
        }
    }
    
    public Font getFont() {
        if (this.isOld) {
            return this.oldInput.getFont();
        }
        return this.newInput.getFont();
    }
    
    public void setText(final String s) {
        if (this.isOld) {
            this.oldInput.setText(s);
        }
        else {
            this.newInput.setText(s);
        }
    }
    
    public String getText() {
        if (this.isOld) {
            return this.oldInput.getText();
        }
        return this.newInput.getText();
    }
    
    public void setBorder(final Border border) {
        if (!this.isOld) {
            this.newInput.setBorder(border);
        }
    }
    
    public void setEditable(final boolean b) {
        if (this.isOld) {
            this.oldInput.setEditable(b);
        }
        else {
            this.newInput.setEditable(b);
        }
    }
    
    public boolean isEditable() {
        if (this.isOld) {
            return this.oldInput.isEditable();
        }
        return this.newInput.isEditable();
    }
    
    public void setEnabled(final boolean b) {
        if (this.isOld) {
            this.oldInput.setEnabled(b);
        }
        else {
            this.newInput.setEnabled(b);
        }
    }
    
    public boolean isEnabled() {
        if (this.isOld) {
            return this.oldInput.isEnabled();
        }
        return this.newInput.isEnabled();
    }
    
    public void setLineWrap(final boolean lineWrap) {
        if (!this.isOld) {
            this.newInput.setLineWrap(lineWrap);
        }
    }
    
    public void setWrapStyleWord(final boolean wrapStyleWord) {
        if (!this.isOld) {
            this.newInput.setWrapStyleWord(wrapStyleWord);
        }
    }
    
    public void addKeyListener(final KeyListener keyListener) {
        if (this.isOld) {
            this.oldInput.addKeyListener(keyListener);
        }
        else {
            this.newInput.addKeyListener(keyListener);
        }
    }
    
    public void addCaretListener(final CaretListener caretListener) {
        if (!this.isOld) {
            this.newInput.addCaretListener(caretListener);
        }
    }
    
    public void setMaximumSize(final Dimension maximumSize) {
        if (!this.isOld) {
            this.newInput.setMaximumSize(maximumSize);
        }
    }
    
    public void requestFocus() {
        if (this.isOld) {
            this.oldInput.requestFocus();
        }
        else {
            this.newInput.requestFocus();
        }
    }
    
    public boolean requestFocusInWindow() {
        if (this.isOld) {
            return this.oldInput.requestFocusInWindow();
        }
        return this.newInput.requestFocusInWindow();
    }
}
