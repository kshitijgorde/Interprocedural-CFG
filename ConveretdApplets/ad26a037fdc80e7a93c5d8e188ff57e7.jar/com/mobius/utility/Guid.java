// 
// Decompiled by Procyon v0.5.30
// 

package com.mobius.utility;

import java.util.Random;

public final class Guid
{
    private static final int GUID_NUM_BYTES = 16;
    private static final int GUID_NUM_CHARACTERS = 36;
    private String guid;
    private static final char[] HEX;
    private static Random rnd;
    
    public Guid() {
        this(getRandomGuidByteArray());
    }
    
    public Guid(final String guid) throws MalformedGuidException {
        if (guid == null) {
            throw new IllegalArgumentException("Parameter must be a non-null String");
        }
        validateGuid(guid);
        this.guid = guid;
    }
    
    public Guid(final byte[] array) {
        if (array == null || array.length != 16) {
            throw new IllegalArgumentException("Parameter must be a non-null 16-byte array");
        }
        this.guid = this.formatGuid(array);
    }
    
    public void validateGuid() throws MalformedGuidException {
        validateGuid(this.guid);
    }
    
    private static void validateGuid(final String s) throws MalformedGuidException {
        if (s == null) {
            throw new MalformedGuidException("GUIDs can't be null");
        }
        final char[] charArray = s.toCharArray();
        final int length = charArray.length;
        if (length != 36) {
            throw new MalformedGuidException("GUID " + s + " has " + length + " characters, instead of " + 36);
        }
        for (int i = 1; i <= length; ++i) {
            final char c = charArray[i - 1];
            if (shouldGuidCharBeDash(i)) {
                if (c != '-') {
                    throw new MalformedGuidException("Illegal character [" + c + "] at position " + i + " instead of dash.");
                }
            }
            else if (!isCharValidHex(c)) {
                throw new MalformedGuidException("Illegal character [" + c + "] at position " + i + " instead of hexadecimal.");
            }
        }
    }
    
    private static boolean isCharValidHex(final char c) {
        return (c >= '0' && c <= '9') || (c >= 'A' && c <= 'F') || (c >= 'a' && c <= 'f');
    }
    
    private static boolean shouldGuidCharBeDash(final int n) {
        return n == 9 || n == 14 || n == 19 || n == 24;
    }
    
    private static byte[] getRandomGuidByteArray() {
        final byte[] array = new byte[16];
        Guid.rnd.nextBytes(array);
        return array;
    }
    
    private String formatGuid(final byte[] array) {
        final StringBuffer sb = new StringBuffer(20);
        appendHexString(array, sb, 0, 4);
        sb.append('-');
        appendHexString(array, sb, 4, 2);
        sb.append('-');
        appendHexString(array, sb, 6, 2);
        sb.append('-');
        appendHexString(array, sb, 8, 2);
        sb.append('-');
        appendHexString(array, sb, 10, 6);
        return sb.toString();
    }
    
    private static void appendHexString(final byte[] array, final StringBuffer sb, final int n, final int n2) {
        for (int i = n; i < n + n2; ++i) {
            final byte b = array[i];
            sb.append(Guid.HEX[b >>> 4 & 0xF]);
            sb.append(Guid.HEX[b & 0xF]);
        }
    }
    
    public boolean equals(final Object o) {
        return o instanceof Guid && ((Guid)o).guid.equalsIgnoreCase(this.guid);
    }
    
    public int hashCode() {
        return this.guid.toLowerCase().hashCode();
    }
    
    public String toString() {
        return this.guid;
    }
    
    static {
        HEX = "0123456789ABCDEF".toCharArray();
        Guid.rnd = new Random();
    }
}
