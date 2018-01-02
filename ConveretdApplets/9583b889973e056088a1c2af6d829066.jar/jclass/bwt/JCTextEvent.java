// 
// Decompiled by Procyon v0.5.30
// 

package jclass.bwt;

import java.awt.event.TextEvent;

public class JCTextEvent extends TextEvent
{
    int start;
    int end;
    String text;
    boolean doit;
    boolean is_deletion;
    
    public int getStartPosition() {
        return this.start;
    }
    
    public void setStartPosition(final int start) {
        this.start = start;
    }
    
    public int getEndPosition() {
        return this.end;
    }
    
    public void setEndPosition(final int end) {
        this.end = end;
    }
    
    public String getText() {
        return this.text;
    }
    
    public void setText(final String text) {
        this.text = text;
    }
    
    public boolean isDeletion() {
        return this.is_deletion;
    }
    
    public boolean getAllowChange() {
        return this.doit;
    }
    
    public void setAllowChange(final boolean doit) {
        this.doit = doit;
    }
    
    public JCTextEvent(final JCTextComponent jcTextComponent, final int start, final int end, final String text) {
        super(jcTextComponent, 900);
        this.doit = true;
        this.start = start;
        this.end = end;
        this.text = text;
    }
    
    public String paramString() {
        return "text=" + this.text + " start=" + this.start + " end=" + this.end;
    }
}
