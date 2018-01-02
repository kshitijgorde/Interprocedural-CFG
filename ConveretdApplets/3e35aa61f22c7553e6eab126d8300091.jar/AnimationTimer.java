// 
// Decompiled by Procyon v0.5.30
// 

class AnimationTimer extends Thread
{
    Animation animation;
    public int delay;
    
    public AnimationTimer(final Animation animation, final int delay) {
        this.animation = animation;
        this.delay = delay;
    }
    
    public void start_animation() {
        if (this.isAlive()) {
            super.resume();
            return;
        }
        this.start();
    }
    
    public void pause_animation() {
        this.suspend();
    }
    
    public void run() {
        while (true) {
            this.animation.animate();
            try {
                Thread.sleep(this.delay);
            }
            catch (InterruptedException ex) {}
        }
    }
}
