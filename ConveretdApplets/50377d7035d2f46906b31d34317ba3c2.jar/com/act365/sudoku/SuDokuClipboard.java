// 
// Decompiled by Procyon v0.5.30
// 

package com.act365.sudoku;

import java.awt.Font;
import java.awt.Component;
import java.awt.TextArea;
import com.act365.awt.Frame;

public class SuDokuClipboard extends Frame
{
    TextArea textArea;
    
    public SuDokuClipboard() {
        super("Su Doku Clipboard");
        this.add(this.textArea = new TextArea());
        this.setSize(400, 400);
        this.setFont(Font.decode("Monospaced"));
    }
    
    public void setText(final String s) {
        this.textArea.setText(s);
    }
    
    public String getText() {
        return this.textArea.getText();
    }
}
