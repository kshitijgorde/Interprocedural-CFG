// 
// Decompiled by Procyon v0.5.30
// 

package pclient.adx;

import com.pchat.sc.MsgOptions;

public class PrivItem
{
    public int type;
    public String nickTo;
    public String nickFrom;
    public String message;
    public String roomname;
    public MsgOptions mop;
    public String sound;
    
    public PrivItem() {
        this.nickTo = null;
        this.nickFrom = null;
        this.message = null;
        this.roomname = null;
        this.mop = null;
        this.sound = null;
    }
    
    public String toString() {
        return "privItem=" + this.nickFrom;
    }
}
