// 
// Decompiled by Procyon v0.5.30
// 

package sexy.res;

import java.applet.AudioClip;
import java.util.Vector;

public class SoundThread implements Runnable
{
    public static final int COMMAND_PLAY = 0;
    public static final int COMMAND_LOOP = 1;
    public static final int COMMAND_STOP = 2;
    boolean mShutdown;
    Vector mCmdVector;
    
    public synchronized void Shutdown() {
        this.mShutdown = true;
        this.notify();
    }
    
    public synchronized void PushCommand(final AudioClip audioClip, final int n) {
        final int[] array = { n };
        this.mCmdVector.addElement(audioClip);
        this.mCmdVector.addElement(array);
        this.notify();
    }
    
    public SoundThread() {
        this.mCmdVector = new Vector();
        new Thread(this).start();
    }
    
    public void run() {
        while (!this.mShutdown) {
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
                    if (this.mCmdVector.size() == 0) {
                        // monitorexit(this)
                        break;
                    }
                    audioClip = this.mCmdVector.firstElement();
                    this.mCmdVector.removeElementAt(0);
                    n = ((int[])this.mCmdVector.firstElement())[0];
                    this.mCmdVector.removeElementAt(0);
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
