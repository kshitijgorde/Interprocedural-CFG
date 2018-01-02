// 
// Decompiled by Procyon v0.5.30
// 

package irc.com.messages;

public class ChannelMessage
{
    private int _action;
    private String[] _message;
    
    public ChannelMessage(final int action) {
        this._action = action;
        this._message = null;
    }
    
    public ChannelMessage(final int action, final String s) {
        this._action = action;
        (this._message = new String[1])[0] = s;
    }
    
    public ChannelMessage(final int action, final String s, final String s2) {
        this._action = action;
        (this._message = new String[2])[0] = s;
        this._message[1] = s2;
    }
    
    public int getAction() {
        return this._action;
    }
    
    public String getMessagePart1() {
        return this._message[0];
    }
    
    public String getMessagePart2() {
        return this._message[1];
    }
}
