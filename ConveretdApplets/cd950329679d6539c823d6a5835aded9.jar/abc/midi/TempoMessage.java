// 
// Decompiled by Procyon v0.5.30
// 

package abc.midi;

import javax.sound.midi.InvalidMidiDataException;
import abc.notation.Tempo;
import javax.sound.midi.MetaMessage;

public class TempoMessage extends MetaMessage
{
    private static final int MICRO_SECOND_NB_IN_ONE_MINUTE = 60000000;
    
    public TempoMessage(final Tempo tempo) {
        final int nbOfQuarterNotesPerMinute = tempo.getNotesNumberPerMinute((short)48);
        final int microsecondsPerQuarterNote = 60000000 / nbOfQuarterNotesPerMinute;
        final byte[] buffer = { (byte)((microsecondsPerQuarterNote & 0xFF0000) >> 16), (byte)((microsecondsPerQuarterNote & 0xFF00) >> 8), (byte)(microsecondsPerQuarterNote & 0xFF) };
        try {
            this.setMessage(81, buffer, buffer.length);
        }
        catch (InvalidMidiDataException e) {
            e.printStackTrace();
        }
    }
    
    public static Tempo getTempo(final byte[] bytes) {
        Tempo tempo = null;
        try {
            final int a = (bytes[0] & 0xFF) << 16;
            final int b = (bytes[1] & 0xFF) << 8;
            final int c = bytes[2] & 0xFF;
            final int microsecondsPerQuarterNote = a + b + c;
            final int nbOfQuarterNotesPerMinute = 60000000 / microsecondsPerQuarterNote;
            tempo = new Tempo(nbOfQuarterNotesPerMinute);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return tempo;
    }
}
