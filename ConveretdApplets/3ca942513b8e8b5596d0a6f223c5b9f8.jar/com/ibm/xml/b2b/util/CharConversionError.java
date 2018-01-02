// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xml.b2b.util;

import java.io.IOException;
import java.io.CharConversionException;

public class CharConversionError
{
    private static void doThrow(final String s) {
        throw new IOExceptionWrapper(new CharConversionException(s));
    }
    
    public static void unableToConvertOutOfRangeUnicodeCharacter() {
        doThrow(ImplementationMessages.formatMessage(0, null));
    }
    
    public static void insufficientInputToDecodeCharacter() {
        doThrow(ImplementationMessages.formatMessage(1, null));
    }
    
    public static void missingSecondHalfOfSurrogatePair() {
        doThrow(ImplementationMessages.formatMessage(2, null));
    }
    
    public static void invalidSecondHalfOfSurrogatePair() {
        doThrow(ImplementationMessages.formatMessage(3, null));
    }
    
    public static void invalidFirstHalfOfSurrogatePair() {
        doThrow(ImplementationMessages.formatMessage(4, null));
    }
    
    public static void byteOrderMarkRequired() {
        doThrow(ImplementationMessages.formatMessage(5, null));
    }
    
    public static void invalidUTF8SurrogateEncoding() {
        doThrow(ImplementationMessages.formatMessage(6, null));
    }
    
    public static void partialMultiPartCharacterSequence() {
        doThrow(ImplementationMessages.formatMessage(7, null));
    }
    
    public static void inconsistentEncoding() {
        doThrow(ImplementationMessages.formatMessage(8, null));
    }
    
    public static void invalidUTF8CharacterEncoding(final int n, final int n2, final int n3, final int n4, final int n5) {
        doThrow(ImplementationMessages.formatMessage(9, new String[] { Integer.toString(n), Integer.toString(n2), Integer.toString(n3), Integer.toString(n4), Integer.toString(n5) }));
    }
}
