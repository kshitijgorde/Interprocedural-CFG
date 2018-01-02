// 
// Decompiled by Procyon v0.5.30
// 

package hanzilookup.data;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.DataOutputStream;

public class StrokesIO
{
    public static void writeCharacter(final char character, final DataOutputStream out) throws IOException {
        out.writeChar(character);
    }
    
    public static char readCharacter(final DataInputStream in) throws IOException {
        final char character = in.readChar();
        return character;
    }
    
    public static void writeCharacterType(final int type, final DataOutputStream out) throws IOException {
        out.writeByte(type);
    }
    
    public static int readCharacterType(final DataInputStream in) throws IOException {
        final int type = in.readByte();
        return type;
    }
    
    public static void writeStrokeCount(final int strokeCount, final DataOutputStream out) throws IOException {
        out.writeByte(strokeCount);
    }
    
    public static int readStrokeCount(final DataInputStream in) throws IOException {
        final int strokeCount = in.readByte();
        return strokeCount;
    }
    
    public static void writeSubStrokeCount(final int strokeCount, final DataOutputStream out) throws IOException {
        out.writeByte(strokeCount);
    }
    
    public static int readSubStrokeCount(final DataInputStream in) throws IOException {
        final int strokeCount = in.readByte();
        return strokeCount;
    }
    
    public static void writeDirection(final double direction, final DataOutputStream out) throws IOException {
        final short directionShort = convertDirectionToShort(direction);
        out.writeShort(directionShort);
    }
    
    public static double readDirection(final DataInputStream in) throws IOException {
        final short directionShort = in.readShort();
        final double direction = convertDirectionFromShort(directionShort);
        return direction;
    }
    
    public static void writeLength(final double length, final DataOutputStream out) throws IOException {
        final short lengthShort = convertLengthToShort(length);
        out.writeShort(lengthShort);
    }
    
    public static double readLength(final DataInputStream in) throws IOException {
        final short lengthShort = in.readShort();
        final double length = convertLengthFromShort(lengthShort);
        return length;
    }
    
    private static double convertDirectionFromShort(final short directionShort) {
        final double directionRatio = (directionShort + 32767.0) / 32767.0;
        final double direction = directionRatio * 2.0 * 3.141592653589793;
        return direction;
    }
    
    private static double convertLengthFromShort(final double lengthShort) {
        final double length = (lengthShort + 32767.0) / 32767.0;
        return length;
    }
    
    private static short convertDirectionToShort(final double direction) {
        final double ratio = direction / 6.283185307179586;
        final short directionShort = (short)(ratio * 32767.0 - 32767.0);
        return directionShort;
    }
    
    private static short convertLengthToShort(final double length) {
        final short lengthShort = (short)(length * 32767.0 - 32767.0);
        return lengthShort;
    }
}
