// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xml.b2b.util.msg;

public final class ImplementationMessagesBundle extends ErrorMessageBundle
{
    private static final Object[][] CONTENTS;
    private static final String[] MESSAGE_KEYS;
    
    protected Object[][] getContents() {
        return ImplementationMessagesBundle.CONTENTS;
    }
    
    public String getString(final int n) {
        return this.getString(ImplementationMessagesBundle.MESSAGE_KEYS[n]);
    }
    
    static {
        CONTENTS = new Object[][] { { "BadMessageKey", "The error message corresponding to the message key can not be found." }, { "FormatFailed", "An internal error occurred while formatting the following message:\n  " }, { "Message0", "unable to convert out-of-range Unicode character" }, { "Message1", "insufficient input to decode character" }, { "Message2", "missing second half of surrogate pair" }, { "Message3", "invalid second half of surrogate pair" }, { "Message4", "invalid first half of surrogate pair" }, { "Message5", "byte-order mark required" }, { "Message6", "invalid UTF8 surrogate encoding" }, { "Message7", "partial multi-part character sequence" }, { "Message8", "encoding name and byte stream contents are inconsistent" }, { "Message9", "invalid UTF8 character encoding at byte {0} in [{1},{2},{3},{4}]" } };
        MESSAGE_KEYS = new String[] { "Message0", "Message1", "Message2", "Message3", "Message4", "Message5", "Message6", "Message7", "Message8", "Message9" };
    }
}
