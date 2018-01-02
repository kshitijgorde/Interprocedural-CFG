// 
// Decompiled by Procyon v0.5.30
// 

package teletext;

public class TeletextReply
{
    char[] contents;
    String command;
    String reply;
    
    public void finalize() throws Exception {
        this.contents = null;
        this.command = null;
        this.reply = null;
    }
    
    public void setContents(final char[] contents) {
        this.contents = contents;
    }
    
    public char[] getContents() {
        return this.contents;
    }
    
    public void clearContents() {
        this.contents = null;
    }
    
    public boolean hasContents() {
        return this.contents != null;
    }
    
    public void setCommand(final String command) {
        this.command = command;
    }
    
    public String getCommand() {
        return this.command;
    }
    
    public void setReply(final String reply) {
        this.reply = reply;
    }
    
    public String getReply() {
        return this.reply;
    }
    
    public String toString() {
        return "TeletextReply <" + this.command + ", " + this.reply + ", " + this.hasContents() + ">";
    }
    
    public TeletextReply() {
        this.contents = null;
        this.command = null;
        this.reply = null;
    }
}
