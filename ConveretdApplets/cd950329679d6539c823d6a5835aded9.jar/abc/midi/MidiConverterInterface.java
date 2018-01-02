// 
// Decompiled by Procyon v0.5.30
// 

package abc.midi;

import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Instrument;
import javax.sound.midi.Sequence;
import abc.notation.Tune;

public interface MidiConverterInterface
{
    Sequence toMidiSequence(final Tune p0);
    
    Instrument getInstrument();
    
    void setInstrument(final Instrument p0) throws MidiUnavailableException;
}
