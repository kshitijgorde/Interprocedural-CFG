import java.applet.AudioClip;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class s implements Runnable
{
    public boolean a;
    public Vector b;
    
    public synchronized void a(final AudioClip audioClip, final int n) {
        final int[] array = { n };
        this.b.addElement(audioClip);
        this.b.addElement(array);
        this.notify();
    }
    
    public s() {
        this.b = new Vector();
        new Thread(this).start();
    }
    
    public void run() {
        while (!this.a) {
            synchronized (this) {
                Label_0015: {
                    try {
                        this.wait();
                        break Label_0015;
                    }
                    catch (InterruptedException ex) {
                    }
                    // monitorexit(this)
                }
            }
            while (true) {
                final AudioClip audioClip;
                final int n;
                synchronized (this) {
                    if (this.b.size() == 0) {
                        // monitorexit(this)
                        break;
                    }
                    audioClip = this.b.firstElement();
                    this.b.removeElementAt(0);
                    n = ((int[])this.b.firstElement())[0];
                    this.b.removeElementAt(0);
                }
                if (n == 0) {
                    audioClip.play();
                }
                else if (n == 1) {
                    audioClip.loop();
                }
                else {
                    if (n != 2) {
                        continue;
                    }
                    audioClip.stop();
                }
            }
        }
        System.out.println("Sound thread exiting");
    }
}
