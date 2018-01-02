// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing;

import java.awt.event.ActionEvent;
import java.util.EventListener;
import java.awt.event.ActionListener;
import javax.swing.event.EventListenerList;
import java.io.Serializable;

public class Timer implements Serializable
{
    protected EventListenerList listenerList;
    boolean eventQueued;
    int initialDelay;
    int delay;
    boolean repeats;
    boolean coalesce;
    Runnable doPostEvent;
    private static boolean logTimers;
    long expirationTime;
    Timer nextTimer;
    boolean running;
    static /* synthetic */ Class class$java$awt$event$ActionListener;
    
    public Timer(final int n, final ActionListener actionListener) {
        this.listenerList = new EventListenerList();
        this.eventQueued = false;
        this.repeats = true;
        this.coalesce = true;
        this.doPostEvent = null;
        this.delay = n;
        this.initialDelay = n;
        this.doPostEvent = new DoPostEvent();
        if (actionListener != null) {
            this.addActionListener(actionListener);
        }
    }
    
    public void addActionListener(final ActionListener actionListener) {
        this.listenerList.add((Timer.class$java$awt$event$ActionListener != null) ? Timer.class$java$awt$event$ActionListener : (Timer.class$java$awt$event$ActionListener = class$("java.awt.event.ActionListener")), actionListener);
    }
    
    synchronized void cancelEvent() {
        this.eventQueued = false;
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    protected void fireActionPerformed(final ActionEvent actionEvent) {
        final Object[] listenerList = this.listenerList.getListenerList();
        for (int i = listenerList.length - 2; i >= 0; i -= 2) {
            if (listenerList[i] == ((Timer.class$java$awt$event$ActionListener != null) ? Timer.class$java$awt$event$ActionListener : (Timer.class$java$awt$event$ActionListener = class$("java.awt.event.ActionListener")))) {
                ((ActionListener)listenerList[i + 1]).actionPerformed(actionEvent);
            }
        }
    }
    
    public int getDelay() {
        return this.delay;
    }
    
    public int getInitialDelay() {
        return this.initialDelay;
    }
    
    public static boolean getLogTimers() {
        return Timer.logTimers;
    }
    
    public boolean isCoalesce() {
        return this.coalesce;
    }
    
    public boolean isRepeats() {
        return this.repeats;
    }
    
    public boolean isRunning() {
        return this.timerQueue().containsTimer(this);
    }
    
    synchronized void post() {
        if (!this.eventQueued) {
            this.eventQueued = true;
            SwingUtilities.invokeLater(this.doPostEvent);
        }
    }
    
    public void removeActionListener(final ActionListener actionListener) {
        this.listenerList.remove((Timer.class$java$awt$event$ActionListener != null) ? Timer.class$java$awt$event$ActionListener : (Timer.class$java$awt$event$ActionListener = class$("java.awt.event.ActionListener")), actionListener);
    }
    
    public void restart() {
        this.stop();
        this.start();
    }
    
    public void setCoalesce(final boolean coalesce) {
        this.coalesce = coalesce;
    }
    
    public void setDelay(final int delay) {
        if (delay < 0) {
            throw new IllegalArgumentException("Invalid delay: " + delay);
        }
        this.delay = delay;
    }
    
    public void setInitialDelay(final int initialDelay) {
        if (initialDelay < 0) {
            throw new IllegalArgumentException("Invalid initial delay: " + initialDelay);
        }
        this.initialDelay = initialDelay;
    }
    
    public static void setLogTimers(final boolean logTimers) {
        Timer.logTimers = logTimers;
    }
    
    public void setRepeats(final boolean repeats) {
        this.repeats = repeats;
    }
    
    public void start() {
        this.timerQueue().addTimer(this, System.currentTimeMillis() + this.getInitialDelay());
    }
    
    public void stop() {
        this.timerQueue().removeTimer(this);
        this.cancelEvent();
    }
    
    TimerQueue timerQueue() {
        return TimerQueue.sharedInstance();
    }
    
    class DoPostEvent implements Runnable, Serializable
    {
        Timer getTimer() {
            return Timer.this;
        }
        
        public void run() {
            if (Timer.logTimers) {
                System.out.println("Timer ringing: " + Timer.this);
            }
            if (Timer.this.eventQueued) {
                Timer.this.fireActionPerformed(new ActionEvent(Timer.this, 0, null));
                Timer.this.cancelEvent();
            }
        }
    }
}
