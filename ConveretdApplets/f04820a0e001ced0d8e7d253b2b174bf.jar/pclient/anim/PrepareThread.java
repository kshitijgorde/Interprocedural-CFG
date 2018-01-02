// 
// Decompiled by Procyon v0.5.30
// 

package pclient.anim;

public class PrepareThread extends Thread
{
    private AnimRenderer render;
    
    public PrepareThread(final AnimRenderer render) {
        this.render = null;
        this.render = render;
    }
    
    public void run() {
        this.render.prepare(this);
        this.render.notifyAnimation();
    }
}
