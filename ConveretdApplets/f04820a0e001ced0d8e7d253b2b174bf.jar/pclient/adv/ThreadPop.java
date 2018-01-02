// 
// Decompiled by Procyon v0.5.30
// 

package pclient.adv;

public class ThreadPop implements Runnable
{
    private AppletSpice parentApplet;
    private ComInter comInter;
    
    public ThreadPop(final AppletSpice parentApplet, final ComInter comInter) {
        this.parentApplet = parentApplet;
        this.comInter = comInter;
        new Thread(this).start();
    }
    
    public void run() {
        this.comInter.setPara(this.parentApplet);
        this.comInter.restart();
    }
}
