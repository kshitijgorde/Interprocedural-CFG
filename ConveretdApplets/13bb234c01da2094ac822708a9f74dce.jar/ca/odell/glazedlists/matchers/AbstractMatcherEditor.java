// 
// Decompiled by Procyon v0.5.30
// 

package ca.odell.glazedlists.matchers;

import javax.swing.event.EventListenerList;

public abstract class AbstractMatcherEditor implements MatcherEditor
{
    private EventListenerList b;
    protected Matcher a;
    
    public AbstractMatcherEditor() {
        this.b = new EventListenerList();
        this.a = Matchers.a();
    }
    
    public Matcher a() {
        return this.a;
    }
    
    public final void a(final MatcherEditor$Listener matcherEditor$Listener) {
        this.b.add(MatcherEditor$Listener.class, matcherEditor$Listener);
    }
    
    protected final void b() {
        this.a = Matchers.a();
        this.a(new MatcherEditor$Event(this, 0, this.a));
    }
    
    protected final void a(final Matcher a) {
        if (a == null) {
            throw new NullPointerException();
        }
        this.a = a;
        this.a(new MatcherEditor$Event(this, 4, this.a));
    }
    
    protected final void c() {
        this.a = Matchers.b();
        this.a(new MatcherEditor$Event(this, 1, this.a));
    }
    
    protected final void a(final MatcherEditor$Event matcherEditor$Event) {
        final Object[] listenerList = this.b.getListenerList();
        for (int i = listenerList.length - 2; i >= 0; i -= 2) {
            ((MatcherEditor$Listener)listenerList[i + 1]).a(matcherEditor$Event);
        }
    }
}
