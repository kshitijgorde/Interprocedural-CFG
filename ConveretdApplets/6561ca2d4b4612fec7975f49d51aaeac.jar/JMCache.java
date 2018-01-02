import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class JMCache
{
    Vector messages;
    
    public JMCache() {
        this.messages = new Vector();
    }
    
    public JMessage getMessage(final String s) {
        final int size = this.messages.size();
        boolean b = false;
        JMessage message = null;
        for (int n = 0; n < size && !b; ++n) {
            message = (JMessage)this.messages.elementAt(n);
            if (message.getUidl().equals(s)) {
                b = true;
            }
        }
        if (b) {
            return message;
        }
        return null;
    }
    
    public boolean isInCache(final String s) {
        final int size = this.messages.size();
        boolean b = false;
        for (int n = 0; n < size && !b; ++n) {
            if (((JMessage)this.messages.elementAt(n)).getUidl().equals(s)) {
                b = true;
            }
        }
        return b;
    }
    
    public void addMessage(final JMessage message) {
        if (!this.isInCache(message.getUidl())) {
            this.messages.addElement(message);
        }
    }
    
    public int size() {
        return this.messages.size();
    }
}
