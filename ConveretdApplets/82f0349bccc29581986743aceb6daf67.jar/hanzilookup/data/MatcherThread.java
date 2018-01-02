// 
// Decompiled by Procyon v0.5.30
// 

package hanzilookup.data;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

public class MatcherThread extends Thread
{
    private StrokesMatcher strokesMatcher;
    private Object matcherLock;
    private Set resultsHandlers;
    private boolean running;
    
    public MatcherThread() {
        this.matcherLock = new Object();
        this.resultsHandlers = new LinkedHashSet();
        this.running = true;
    }
    
    public void addResultsHandler(final ResultsHandler resultsHandler) {
        synchronized (this.resultsHandlers) {
            this.resultsHandlers.add(resultsHandler);
        }
        // monitorexit(this.resultsHandlers)
    }
    
    public boolean removeResultsHandler(final ResultsHandler resultsHandler) {
        synchronized (this.resultsHandlers) {
            // monitorexit(this.resultsHandlers)
            return this.resultsHandlers.remove(resultsHandler);
        }
    }
    
    public void kill() {
        this.running = false;
        synchronized (this.matcherLock) {
            this.matcherLock.notify();
        }
        // monitorexit(this.matcherLock)
    }
    
    public void run() {
        while (this.running) {
            StrokesMatcher matcher = null;
            synchronized (this.matcherLock) {
                try {
                    if (this.strokesMatcher == null) {
                        this.matcherLock.wait();
                    }
                }
                catch (InterruptedException ex) {}
                if (this.strokesMatcher != null) {
                    matcher = this.strokesMatcher;
                    this.strokesMatcher = null;
                }
            }
            // monitorexit(this.matcherLock)
            Character[] results = null;
            if (matcher != null) {
                results = matcher.doMatching();
                if (results == null) {
                    continue;
                }
                synchronized (this.resultsHandlers) {
                    for (final ResultsHandler handler : this.resultsHandlers) {
                        handler.handleResults(results);
                    }
                }
                // monitorexit(this.resultsHandlers)
            }
        }
    }
    
    public void setStrokesMatcher(final StrokesMatcher strokesMatcher) {
        synchronized (this.matcherLock) {
            if (this.strokesMatcher != null) {
                this.strokesMatcher.stop();
            }
            this.strokesMatcher = strokesMatcher;
            this.matcherLock.notify();
        }
        // monitorexit(this.matcherLock)
    }
    
    public interface ResultsHandler
    {
        void handleResults(final Character[] p0);
    }
}
