// 
// Decompiled by Procyon v0.5.30
// 

package rocketmania;

import java.util.Vector;

public class DelayedSounds
{
    Vector mSounds;
    
    void Update(final RocketManiaApplet rocketManiaApplet, final double n) {
        for (int i = 0; i < this.mSounds.size(); ++i) {
            final DelayedSound delayedSound2;
            final DelayedSound delayedSound = delayedSound2 = this.mSounds.elementAt(i);
            delayedSound2.mDelay -= n;
            if (delayedSound.mDelay <= 0.0) {
                rocketManiaApplet.PlaySound(delayedSound.mSound);
                this.mSounds.removeElementAt(i);
                --i;
            }
        }
    }
    
    public DelayedSounds() {
        this.mSounds = new Vector();
    }
    
    void Add(final int n, final double n2) {
        this.mSounds.addElement(new DelayedSound(n, n2));
    }
    
    class DelayedSound
    {
        double mDelay;
        int mSound;
        
        DelayedSound(final int mSound, final double mDelay) {
            DelayedSounds.this.getClass();
            this.mSound = mSound;
            this.mDelay = mDelay;
        }
    }
}
