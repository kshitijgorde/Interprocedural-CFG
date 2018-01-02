// 
// Decompiled by Procyon v0.5.30
// 

package jclass.bwt;

public class JCTextCursorEvent extends JCAWTEvent
{
    int current_pos;
    int new_pos;
    public boolean doit;
    
    public int getCurrentPosition() {
        return this.current_pos;
    }
    
    public int getNewPosition() {
        return this.new_pos;
    }
    
    public void setNewPosition(final int new_pos) {
        this.new_pos = new_pos;
    }
    
    public boolean getAllowMovement() {
        return this.doit;
    }
    
    public void setAllowMovement(final boolean doit) {
        this.doit = doit;
    }
    
    public JCTextCursorEvent(final JCTextComponent jcTextComponent, final int current_pos, final int new_pos) {
        super(jcTextComponent, 0);
        this.doit = true;
        this.current_pos = current_pos;
        this.new_pos = new_pos;
    }
    
    public String paramString() {
        return "current=" + this.current_pos + " new=" + this.new_pos;
    }
}
