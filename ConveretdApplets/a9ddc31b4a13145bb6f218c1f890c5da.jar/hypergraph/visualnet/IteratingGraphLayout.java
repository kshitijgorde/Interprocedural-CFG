// 
// Decompiled by Procyon v0.5.30
// 

package hypergraph.visualnet;

public abstract class IteratingGraphLayout extends AbstractGraphLayout
{
    long totalTime;
    long numberOfIter;
    private IterationThread thread;
    
    public IteratingGraphLayout() {
        this.thread = new IterationThread();
    }
    
    protected IterationThread getThread() {
        return this.thread;
    }
    
    protected abstract void iteration(final IterationThread p0);
    
    public void invalidate() {
        super.invalidate();
        this.thread.startIterating();
    }
    
    public void layout() {
        if (this.thread != null && !this.thread.isRunning()) {
            this.thread.start();
            this.thread.startIterating();
        }
    }
    
    public class IterationThread extends Thread
    {
        private boolean iterating;
        private boolean running;
        
        public IterationThread() {
            this.iterating = false;
            this.running = false;
        }
        
        public void startIterating() {
            this.iterating = true;
        }
        
        public void stopIterating() {
            this.iterating = false;
        }
        
        public boolean isIterating() {
            return this.iterating;
        }
        
        public void stopThread() {
            this.running = false;
        }
        
        public boolean isRunning() {
            return this.running;
        }
        
        public void run() {
            this.running = true;
            while (this.running) {
                if (this.isIterating()) {
                    final IteratingGraphLayout this$0 = IteratingGraphLayout.this;
                    ++this$0.numberOfIter;
                    final long currentTimeMillis = System.currentTimeMillis();
                    synchronized (IteratingGraphLayout.this.getGraph()) {
                        IteratingGraphLayout.this.iteration(this);
                    }
                    final long n = System.currentTimeMillis() - currentTimeMillis;
                    final IteratingGraphLayout this$2 = IteratingGraphLayout.this;
                    this$2.totalTime += n;
                }
                try {
                    Thread.sleep(10L);
                }
                catch (Exception ex) {}
            }
        }
    }
}
