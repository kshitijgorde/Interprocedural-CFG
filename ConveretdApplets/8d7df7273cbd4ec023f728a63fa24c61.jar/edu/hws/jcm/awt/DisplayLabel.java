// 
// Decompiled by Procyon v0.5.30
// 

package edu.hws.jcm.awt;

import java.awt.FontMetrics;
import java.awt.Dimension;
import edu.hws.jcm.data.NumUtils;
import edu.hws.jcm.data.Value;
import java.awt.Label;

public class DisplayLabel extends Label implements Computable
{
    protected String text;
    protected int numSize;
    protected Value[] values;
    
    public DisplayLabel() {
        this(null, (Value[])null);
    }
    
    public DisplayLabel(final String s, final Value value) {
        final Value[] array;
        if (value != null) {
            array = new Value[] { value };
        }
        this(s, array);
    }
    
    public DisplayLabel(final String s, final Value[] values) {
        this.numSize = 10;
        this.text = ((s == null) ? "#" : s);
        this.setValues(values);
    }
    
    public void compute() {
        super.setText(this.getSubstitutedText());
    }
    
    public Value[] getValuess() {
        return this.values;
    }
    
    public void setValues(final Value[] array) {
        if (array == null) {
            this.values = null;
        }
        else {
            System.arraycopy(array, 0, this.values = new Value[array.length], 0, array.length);
        }
        super.setText(this.getSubstitutedText());
    }
    
    public void setNumSize(final int n) {
        this.numSize = Math.min(Math.max(n, 6), 25);
    }
    
    public int getNumSize() {
        return this.numSize;
    }
    
    public String getBaseText() {
        return this.text;
    }
    
    private String getSubstitutedText() {
        final StringBuffer sb = new StringBuffer();
        int n = 0;
        for (int i = 0; i < this.text.length(); ++i) {
            if (this.text.charAt(i) == '#') {
                if (i != this.text.length() - 1 && this.text.charAt(i + 1) == '#') {
                    sb.append('#');
                    ++i;
                }
                else if (this.values == null || n >= this.values.length) {
                    sb.append("undefined");
                }
                else {
                    try {
                        sb.append(NumUtils.realToString(this.values[n].getVal(), this.numSize));
                    }
                    catch (JCMError jcmError) {
                        sb.append("undefined");
                    }
                    ++n;
                }
            }
            else {
                sb.append(this.text.charAt(i));
            }
        }
        return sb.toString();
    }
    
    public void setText(final String text) {
        this.text = text;
        super.setText(this.getSubstitutedText());
    }
    
    public Dimension getPreferredSize() {
        final Dimension preferredSize = super.getPreferredSize();
        int n = 0;
        if (this.text == null || this.text.length() == 0) {
            n = 1;
        }
        else {
            for (int i = 0; i < this.text.length(); ++i) {
                if (this.text.charAt(i) == '#') {
                    if (i < this.text.length() - 1 && this.text.charAt(i + 1) == '#') {
                        ++i;
                    }
                    else {
                        ++n;
                    }
                }
            }
        }
        final FontMetrics fontMetrics = this.getFontMetrics(this.getFont());
        return new Dimension(10 + (fontMetrics.charWidth('0') * Math.max(8, this.numSize) * n + fontMetrics.stringWidth(this.text)), preferredSize.height);
    }
}
