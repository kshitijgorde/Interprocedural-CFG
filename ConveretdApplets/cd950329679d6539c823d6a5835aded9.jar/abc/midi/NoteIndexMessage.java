// 
// Decompiled by Procyon v0.5.30
// 

package abc.midi;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MetaMessage;

public class NoteIndexMessage extends MetaMessage
{
    public NoteIndexMessage(final int indexInScore) {
        final byte[] buffer = { (byte)((indexInScore & 0xFF0000) >> 16), (byte)((indexInScore & 0xFF00) >> 8), (byte)(indexInScore & 0xFF) };
        try {
            this.setMessage(64, buffer, buffer.length);
        }
        catch (InvalidMidiDataException e) {
            e.printStackTrace();
        }
    }
    
    public static int getIndex(final byte[] bytes) {
        final int a = (bytes[0] & 0xFF) << 16;
        final int b = (bytes[1] & 0xFF) << 8;
        final int c = bytes[2] & 0xFF;
        if (a + b + c < 0) {
            System.err.println("ca va p\u00e9ter !");
        }
        return a + b + c;
    }
}
