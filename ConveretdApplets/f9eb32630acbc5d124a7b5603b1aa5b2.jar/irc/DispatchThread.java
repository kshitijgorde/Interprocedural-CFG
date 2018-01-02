// 
// Decompiled by Procyon v0.5.30
// 

package irc;

class DispatchThread extends Thread
{
    private LinkedList _list;
    private Object _manageLock;
    private boolean _terminated;
    private boolean _processing;
    
    public DispatchThread(final String s) {
        super(s + " event dispatch thread");
        this._manageLock = new Object();
        this._list = new LinkedList();
        this._terminated = false;
        this._processing = false;
        this.setDaemon(true);
        this.start();
    }
    
    public EventItem addEvent(final Object o, final String s, final Object[] array) {
        if (this._terminated) {
            return null;
        }
        final EventItem eventItem = new EventItem(o, s, array);
        synchronized (this._manageLock) {
            this._list.addLast(eventItem);
            this._manageLock.notify();
        }
        return eventItem;
    }
    
    public void run() {
        int size = 0;
        do {
            EventItem eventItem;
            synchronized (this._manageLock) {
                if (this._list.size() > 0) {
                    eventItem = (EventItem)this._list.removeFirst();
                }
                else {
                    eventItem = null;
                    try {
                        this._manageLock.wait();
                    }
                    catch (InterruptedException ex) {}
                }
                size = this._list.size();
            }
            if (eventItem != null) {
                this._processing = true;
                eventItem.resultException = null;
                try {
                    eventItem.result = EventDispatcher.dispatchEventSyncEx(eventItem.target, eventItem.method, eventItem.params);
                }
                catch (Throwable resultException) {
                    eventItem.resultException = resultException;
                }
                this._processing = false;
                synchronized (eventItem.endLock) {
                    eventItem.resultAvailable = true;
                    eventItem.endLock.notify();
                }
            }
        } while (!this._terminated || size != 0);
    }
    
    public void terminate() {
        this._terminated = true;
        if (!this._processing) {
            this.interrupt();
        }
    }
}
