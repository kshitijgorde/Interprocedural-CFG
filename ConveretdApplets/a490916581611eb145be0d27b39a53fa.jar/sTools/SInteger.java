// 
// Decompiled by Procyon v0.5.30
// 

package sTools;

import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.Color;
import java.awt.event.TextEvent;
import java.awt.event.KeyListener;
import java.awt.event.TextListener;
import java.awt.TextField;

public class SInteger extends TextField implements TextListener
{
    private String lastValue;
    private int lastCaretPosition;
    private boolean noColor;
    private int val;
    
    public SInteger(final int n, final int n2) {
        super(String.valueOf("").concat(String.valueOf(n)), n2);
        this.noColor = false;
        this.val = 0;
        this.addTextListener(this);
        this.addKeyListener(new 1());
        this.lastValue = String.valueOf("").concat(String.valueOf(n));
    }
    
    public SInteger() {
        this(0, 3);
    }
    
    public void textValueChanged(final TextEvent textEvent) {
        this.checkValue();
    }
    
    private void checkValue() {
        try {
            Integer.parseInt(String.valueOf(this.getText().trim()).concat(String.valueOf("0")));
            this.lastValue = this.getText();
        }
        catch (NumberFormatException ex) {
            this.setText(this.lastValue);
            this.setCaretPosition(this.lastCaretPosition);
        }
    }
    
    public int getValue() {
        this.checkValue();
        try {
            this.val = Integer.parseInt(this.getText().trim());
            if (this.isEditable() && !this.noColor) {
                this.setBackground(Color.white);
            }
            return this.val;
        }
        catch (NumberFormatException ex) {
            if (!this.noColor) {
                this.setBackground(Color.red);
            }
            return 0;
        }
    }
    
    public void setValue(final int val) {
        this.val = val;
        this.setText(String.valueOf("").concat(String.valueOf(val)));
        if (!this.noColor) {
            this.setBackground(Color.white);
        }
    }
    
    public boolean isNoColor() {
        return this.noColor;
    }
    
    public void setNoColor(final boolean noColor) {
        this.noColor = noColor;
    }
    
    class 1 extends KeyAdapter
    {
        public void keyTyped(final KeyEvent keyEvent) {
            final char keyChar = keyEvent.getKeyChar();
            if (('0' > keyChar || keyChar > '9') && keyChar != '-' && !Character.isISOControl(keyChar)) {
                keyEvent.consume();
            }
            else {
                SInteger.this.lastCaretPosition = SInteger.this.getCaretPosition();
                if (SInteger.this.isEditable() && !SInteger.this.noColor) {
                    SInteger.this.setBackground(Color.yellow);
                }
            }
        }
    }
}
