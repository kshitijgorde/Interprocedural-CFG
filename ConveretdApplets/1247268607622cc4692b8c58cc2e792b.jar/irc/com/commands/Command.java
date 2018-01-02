// 
// Decompiled by Procyon v0.5.30
// 

package irc.com.commands;

public class Command
{
    private int required;
    private int accepted;
    private String tag;
    
    public Command(final String s, final int n) {
        this(s, n, n);
    }
    
    public Command(final String tag, final int required, final int accepted) {
        this.tag = tag;
        this.required = required;
        this.accepted = accepted;
    }
    
    public int getAcceptedParameters() {
        return this.accepted;
    }
    
    public int getRequiredParameters() {
        return this.required;
    }
    
    public String getTag() {
        return this.tag;
    }
}
