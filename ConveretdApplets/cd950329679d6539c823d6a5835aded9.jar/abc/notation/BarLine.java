// 
// Decompiled by Procyon v0.5.30
// 

package abc.notation;

public class BarLine implements MusicElement
{
    public static final byte SIMPLE = 0;
    public static final byte REPEAT_OPEN = 1;
    public static final byte REPEAT_CLOSE = 2;
    private byte m_type;
    
    public BarLine() {
        this.m_type = 0;
    }
    
    public BarLine(final byte type) {
        this.m_type = 0;
        this.m_type = type;
    }
    
    public byte getType() {
        return this.m_type;
    }
    
    public static byte[] convertToBarLine(final String barLine) {
        byte[] barlineTypes = null;
        if (barLine.equals("::")) {
            barlineTypes = new byte[] { 2, 1 };
            return barlineTypes;
        }
        if (barLine.equals("|")) {
            barlineTypes = new byte[] { 0 };
        }
        else if (barLine.equals("||")) {
            barlineTypes = new byte[] { 0 };
        }
        else if (barLine.equals("[|")) {
            barlineTypes = new byte[] { 0 };
        }
        else if (barLine.equals("|]")) {
            barlineTypes = new byte[] { 0 };
        }
        else if (barLine.equals(":|")) {
            barlineTypes = new byte[] { 2 };
        }
        else if (barLine.equals("|:")) {
            barlineTypes = new byte[] { 1 };
        }
        return barlineTypes;
    }
    
    public String toString() {
        if (this.m_type == 0) {
            return new String("|");
        }
        if (this.m_type == 1) {
            return new String("|:");
        }
        if (this.m_type == 2) {
            return new String(":|");
        }
        return null;
    }
}
