// 
// Decompiled by Procyon v0.5.30
// 

package irc;

class EventItem
{
    public Object target;
    public String method;
    public Object[] params;
    public Object endLock;
    public Object result;
    public Throwable resultException;
    public boolean resultAvailable;
    
    public EventItem(final Object target, final String method, final Object[] params) {
        this.target = target;
        this.method = method;
        this.params = params;
        this.endLock = new Object();
        this.resultAvailable = false;
        this.result = null;
    }
}
