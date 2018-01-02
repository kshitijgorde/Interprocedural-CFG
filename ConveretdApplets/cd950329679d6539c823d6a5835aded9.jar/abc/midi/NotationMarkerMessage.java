// 
// Decompiled by Procyon v0.5.30
// 

package abc.midi;

import javax.sound.midi.InvalidMidiDataException;
import scanner.PositionableInCharStream;
import javax.sound.midi.MetaMessage;

public class NotationMarkerMessage extends MetaMessage
{
    public NotationMarkerMessage(final PositionableInCharStream pos) {
        final int offsetBegin = pos.getPosition().getCharactersOffset();
        final int offsetEnd = offsetBegin + pos.getLength() - 1;
        final byte[] buffer = { (byte)((offsetBegin & 0xFF0000) >> 16), (byte)((offsetBegin & 0xFF00) >> 8), (byte)(offsetBegin & 0xFF), (byte)((offsetEnd & 0xFF0000) >> 16), (byte)((offsetEnd & 0xFF00) >> 8), (byte)(offsetEnd & 0xFF) };
        try {
            this.setMessage(48, buffer, buffer.length);
        }
        catch (InvalidMidiDataException e) {
            e.printStackTrace();
        }
    }
    
    public static int getBeginOffset(final byte[] bytes) {
        final int a = (bytes[0] & 0xFF) << 16;
        final int b = (bytes[1] & 0xFF) << 8;
        final int c = bytes[2] & 0xFF;
        if (a + b + c < 0) {
            System.out.println("ca va p\u00e9ter !");
        }
        return a + b + c;
    }
    
    public static int getEndOffset(final byte[] bytes) {
        final int a = (bytes[3] & 0xFF) << 16;
        final int b = (bytes[4] & 0xFF) << 8;
        final int c = bytes[5] & 0xFF;
        return a + b + c;
    }
}
