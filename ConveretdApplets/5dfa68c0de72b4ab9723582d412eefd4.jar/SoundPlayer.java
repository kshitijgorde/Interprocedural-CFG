import java.util.Iterator;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

class SoundPlayer implements Runnable
{
    static Vector activeSounds;
    static Thread sndThread;
    
    static synchronized Object startSound(final Object o, final Sprite sprite, final LContext lContext) {
        if (!(o instanceof ScratchSound)) {
            Logo.error("argument of startSound must be a ScratchSound", lContext);
            return new Object[0];
        }
        final Object[] array = SoundPlayer.activeSounds.toArray();
        for (int i = 0; i < array.length; ++i) {
            final PlayingSound playingSound = (PlayingSound)array[i];
            if (playingSound.snd == o && playingSound.sprite == sprite) {
                playingSound.closeLine();
                SoundPlayer.activeSounds.remove(playingSound);
            }
        }
        final PlayingSound playingSound2 = new PlayingSound((ScratchSound)o, sprite);
        playingSound2.startPlaying(lContext);
        SoundPlayer.activeSounds.add(playingSound2);
        return playingSound2;
    }
    
    static synchronized boolean isSoundPlaying(final Object o) {
        return o instanceof PlayingSound && ((PlayingSound)o).isPlaying();
    }
    
    static synchronized void stopSound(final Object o) {
        if (!(o instanceof PlayingSound)) {
            return;
        }
        ((PlayingSound)o).closeLine();
        SoundPlayer.activeSounds.remove(o);
    }
    
    static synchronized void stopSoundsForApplet(final LContext lContext) {
        PlayerPrims.stopMIDINotes();
        final Vector<PlayingSound> activeSounds = new Vector<PlayingSound>();
        for (final PlayingSound playingSound : SoundPlayer.activeSounds) {
            if (playingSound.owner == lContext) {
                playingSound.closeLine();
            }
            else {
                activeSounds.addElement(playingSound);
            }
        }
        SoundPlayer.activeSounds = activeSounds;
    }
    
    static synchronized void updateActiveSounds() {
        final Vector<PlayingSound> activeSounds = new Vector<PlayingSound>();
        for (final PlayingSound playingSound : SoundPlayer.activeSounds) {
            if (playingSound.isPlaying()) {
                playingSound.writeSomeSamples();
                activeSounds.addElement(playingSound);
            }
            else {
                playingSound.closeLine();
            }
        }
        SoundPlayer.activeSounds = activeSounds;
    }
    
    static synchronized void startPlayer() {
        (SoundPlayer.sndThread = new Thread(new SoundPlayer(), "SoundPlayer")).setPriority(10);
        SoundPlayer.sndThread.start();
    }
    
    public void run() {
        while (SoundPlayer.sndThread == Thread.currentThread()) {
            updateActiveSounds();
            try {
                Thread.sleep(10L);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    static {
        SoundPlayer.activeSounds = new Vector();
    }
}
