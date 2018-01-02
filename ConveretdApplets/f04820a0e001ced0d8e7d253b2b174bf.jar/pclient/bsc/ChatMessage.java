// 
// Decompiled by Procyon v0.5.30
// 

package pclient.bsc;

import com.pchat.sc.MsgOptions;

public class ChatMessage
{
    public String fromUser;
    public String message;
    public String timestamp;
    public boolean isModerated;
    public boolean isHost;
    public MsgOptions mop;
    
    public ChatMessage() {
        this.fromUser = null;
        this.timestamp = null;
        this.isModerated = false;
        this.isHost = false;
        this.mop = null;
    }
    
    public String toString() {
        return "user is:" + this.fromUser + " " + "message is:" + this.message + " " + "stamp is:" + this.timestamp + "isMod: " + this.isModerated;
    }
}
