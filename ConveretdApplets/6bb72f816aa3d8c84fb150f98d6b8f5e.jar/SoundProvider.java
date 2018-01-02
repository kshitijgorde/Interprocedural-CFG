import java.io.IOException;
import javax.sound.midi.InvalidMidiDataException;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;

// 
// Decompiled by Procyon v0.5.30
// 

public class SoundProvider
{
    public Sequencer midiSequencer;
    private Sequence midiSequence;
    private static SoundProvider instance;
    static boolean hasDriver;
    
    protected SoundProvider() {
        try {
            this.midiSequencer = MidiSystem.getSequencer();
            SoundProvider.hasDriver = true;
        }
        catch (MidiUnavailableException ex) {
            ex.printStackTrace();
        }
    }
    
    public static SoundProvider getSingleton() {
        return SoundProvider.instance = ((SoundProvider.instance == null) ? new SoundProvider() : SoundProvider.instance);
    }
    
    public void playMIDI(final byte[] array) {
        if (!SoundProvider.hasDriver) {
            return;
        }
        if (!this.midiSequencer.isOpen()) {
            try {
                this.midiSequencer.open();
            }
            catch (MidiUnavailableException ex) {}
        }
        if (this.midiSequencer.isRunning()) {
            this.midiSequencer.stop();
        }
        try {
            this.midiSequence = MidiSystem.getSequence(new BufferedInputStream(new ByteArrayInputStream(array)));
            this.midiSequencer.setLoopCount(-1);
            this.midiSequencer.setSequence(this.midiSequence);
        }
        catch (InvalidMidiDataException ex2) {}
        catch (IOException ex3) {}
        this.midiSequencer.start();
    }
    
    public void stopMIDI() {
        this.midiSequencer.stop();
    }
    
    static {
        SoundProvider.instance = null;
        SoundProvider.hasDriver = false;
    }
}
