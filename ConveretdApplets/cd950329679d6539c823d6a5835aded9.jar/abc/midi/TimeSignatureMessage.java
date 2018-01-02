// 
// Decompiled by Procyon v0.5.30
// 

package abc.midi;

import javax.sound.midi.InvalidMidiDataException;
import abc.notation.TimeSignature;
import javax.sound.midi.MetaMessage;

public class TimeSignatureMessage extends MetaMessage
{
    public TimeSignatureMessage(final TimeSignature meter) {
        final byte nn = (byte)meter.getNumerator();
        final byte dd = (byte)(Math.log(meter.getDenominator()) / Math.log(2.0));
        final byte midiClocksPerMetronomeTick = 50;
        final byte thirySecondNotesPer24MidiClocks = 50;
        final byte[] buffer = { nn, dd, midiClocksPerMetronomeTick, thirySecondNotesPer24MidiClocks };
        try {
            this.setMessage(88, buffer, 4);
        }
        catch (InvalidMidiDataException e) {
            e.printStackTrace();
        }
    }
}
