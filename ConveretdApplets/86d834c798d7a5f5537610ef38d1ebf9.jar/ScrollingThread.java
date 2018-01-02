// 
// Decompiled by Procyon v0.5.30
// 

class ScrollingThread extends Thread
{
    int delay;
    PuzzleArea controller;
    int scrollingTime;
    
    public ScrollingThread(final PuzzleArea controller, final int delay, final int scrollingTime) {
        this.delay = 100;
        this.controller = controller;
        this.delay = delay;
        this.scrollingTime = scrollingTime;
    }
    
    public void run() {
        long currentTimeMillis = System.currentTimeMillis();
        this.controller.threadProgress = 0;
        while (this.controller.threadProgress < this.controller.totalFrames) {
            this.controller.scrollRepaint();
            try {
                currentTimeMillis += this.delay;
                Thread.sleep(Math.max(0L, currentTimeMillis - System.currentTimeMillis()));
            }
            catch (InterruptedException ex) {
                break;
            }
            final PuzzleArea controller = this.controller;
            ++controller.threadProgress;
        }
        this.controller.threadProgress = -1;
        this.controller.scrollRepaint();
    }
}
