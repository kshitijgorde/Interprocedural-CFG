// 
// Decompiled by Procyon v0.5.30
// 

package y;

import java.applet.AudioClip;
import java.util.Hashtable;
import java.util.Vector;

final class dd extends Thread
{
    private Vector a;
    private Hashtable a;
    private i a;
    private boolean a;
    private dh a;
    
    dd(final dh a) {
        this.a = new Vector();
        this.a = new Hashtable();
        this.a = new i();
        this.a = a;
    }
    
    final synchronized void a(final String s) {
        this.a.addElement(s);
        this.notify();
    }
    
    public final void run() {
        final dd dd;
        while (!dd.a) {
            int a = 0;
            final String s;
            synchronized (dd) {
                while (dd.a.size() == 0 && !dd.a) {
                    try {
                        dd.wait();
                    }
                    catch (InterruptedException ex) {}
                }
                if (dd.a) {
                    return;
                }
                s = dd.a.elementAt(0);
                dd.a.removeElementAt(0);
                if (s == null) {
                    a = dd.a.a(0);
                    final i a2 = dd.a;
                    if (0 >= a2.a) {
                        throw new ArrayIndexOutOfBoundsException(0 + " >= " + a2.a);
                    }
                    final int n;
                    if ((n = a2.a - 1) > 0) {
                        System.arraycopy(a2.a, 1, a2.a, 0, n);
                    }
                    final i i = a2;
                    --i.a;
                }
            }
            if (s != null) {
                AudioClip audioClip;
                if ((audioClip = dd.a.get(s)) == null) {
                    audioClip = dd.a.getAudioClip(dd.a.getCodeBase(), s);
                    dd.a.put(s, audioClip);
                }
                audioClip.play();
            }
            else {
                try {
                    Thread.sleep(a);
                }
                catch (InterruptedException ex2) {}
            }
        }
    }
    
    public final synchronized void a() {
        this.a = true;
        this.notify();
    }
}
