// 
// Decompiled by Procyon v0.5.30
// 

package DatePickerBean;

import java.awt.event.TextEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.TextListener;
import java.awt.TextField;

class NumericTextField extends TextField implements TextListener, KeyListener
{
    int len;
    int k;
    int pos;
    String ptxt;
    yearpanel yp;
    boolean flag;
    
    public NumericTextField() {
        this.len = 4;
        this.pos = 0;
        this.flag = false;
        this.addTextListener(this);
        this.addKeyListener(this);
    }
    
    public NumericTextField(final yearpanel yp, final String text, final int len) {
        this.len = 4;
        this.pos = 0;
        this.flag = false;
        this.addTextListener(this);
        this.addKeyListener(this);
        this.yp = yp;
        this.setText(text);
        this.len = len;
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        this.flag = true;
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
    }
    
    public void textValueChanged(final TextEvent textEvent) {
        if (!this.flag) {
            return;
        }
        this.flag = false;
        final String text = this.getText();
        try {
            Integer.parseInt(text);
            if (text.length() > 4) {
                final String substring = text.substring(0, 4);
                this.flag = true;
                this.setText(substring);
            }
            else {
                this.yp.setYearThroughEdit();
            }
        }
        catch (NumberFormatException ex) {
            this.setText("0");
        }
    }
}
