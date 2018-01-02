// 
// Decompiled by Procyon v0.5.30
// 

package abc.midi;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MetaMessage;

public class MetaMessageWA extends MetaMessage
{
    byte[] m_realData;
    
    public MetaMessageWA(final MetaMessage rootMessage) throws InvalidMidiDataException {
        this.m_realData = null;
        this.m_realData = rootMessage.getData();
        final byte[] newData = new byte[this.m_realData.length + 1];
        System.arraycopy(this.m_realData, 0, newData, 1, this.m_realData.length);
        newData[0] = (byte)rootMessage.getType();
        super.setMessage(6, newData, newData.length);
    }
    
    public int getType() {
        return super.getData()[0];
    }
    
    public byte[] getData() {
        return this.m_realData;
    }
    
    public static boolean isTempoMessage(final MetaMessage message) {
        return message.getType() == 6 && message.getData()[0] == 81;
    }
    
    public static boolean isNotationMarker(final MetaMessage message) {
        return message.getType() == 6 && message.getData()[0] == 48;
    }
    
    public static boolean isNoteIndexMessage(final MetaMessage message) {
        return message.getType() == 6 && message.getData()[0] == 64;
    }
}
