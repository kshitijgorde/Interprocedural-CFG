// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xml.b2b.util;

import java.io.InputStream;

public interface EncodingSupport
{
    boolean isASCIITransparent();
    
    boolean isSingleByte();
    
    void convertCharsToBytes(final char[] p0, final int p1, final int p2, final byte[][] p3, final int[] p4);
    
    int encodeCharacter(final int p0, final byte[][] p1, final int p2);
    
    int normalizeLineBreaks(final byte[] p0, final int p1, final int p2, final boolean[] p3);
    
    void readCharacters(final InputStream p0, final char[][] p1, final int[] p2, final boolean[] p3, final byte[] p4, final boolean p5);
    
    int lengthAsCharacters(final byte[] p0, final int p1, final int p2);
    
    void convertBytesToChars(final byte[] p0, final int p1, final int p2, final char[][] p3, final int[] p4);
    
    int decodeCharacter(final byte[] p0, final int p1, final int p2, final int[] p3);
}
