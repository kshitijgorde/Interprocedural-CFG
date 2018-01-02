// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xml.b2b.util.msg;

public final class ImplementationMessagesBundle_cs extends ErrorMessageBundle
{
    private static final Object[][] CONTENTS;
    private static final String[] MESSAGE_KEYS;
    
    protected Object[][] getContents() {
        return ImplementationMessagesBundle_cs.CONTENTS;
    }
    
    public String getString(final int n) {
        return this.getString(ImplementationMessagesBundle_cs.MESSAGE_KEYS[n]);
    }
    
    static {
        CONTENTS = new Object[][] { { "BadMessageKey", "The error message corresponding to the message key can not be found." }, { "FormatFailed", "An internal error occurred while formatting the following message:\n  " }, { "Message0", "nelze p\u0159ev\u00e9st znak Unicode, kter\u00fd je mimo platn\u00fd rozsah" }, { "Message1", "nedostate\u010dn\u00fd vstup pro dek\u00f3dov\u00e1n\u00ed znaku" }, { "Message2", "chyb\u00ed druh\u00e1 polovina n\u00e1hradn\u00edho p\u00e1ru" }, { "Message3", "druh\u00e1 polovina n\u00e1hradn\u00edho p\u00e1ru je neplatn\u00e1" }, { "Message4", "prvn\u00ed polovina n\u00e1hradn\u00edho p\u00e1ru je neplatn\u00e1" }, { "Message5", "je vy\u017eadov\u00e1na zna\u010dka po\u0159ad\u00ed bajt\u016f" }, { "Message6", "neplatn\u00e9 n\u00e1hradn\u00ed k\u00f3dov\u00e1n\u00ed UTF8" }, { "Message7", "\u010d\u00e1ste\u010dn\u00e1 posloupnost znak\u016f s v\u00edce \u010d\u00e1stmi" }, { "Message8", "n\u00e1zev k\u00f3dov\u00e1n\u00ed a proud bajt\u016f jsou nekonzistentn\u00ed" }, { "Message9", "neplatn\u00e9 k\u00f3dov\u00e1n\u00ed znak\u016f UTF8 v bajtu {0} v [{1},{2},{3},{4}]" } };
        MESSAGE_KEYS = new String[] { "Message0", "Message1", "Message2", "Message3", "Message4", "Message5", "Message6", "Message7", "Message8", "Message9", "Message0", "Message1", "Message2", "Message3", "Message4", "Message5", "Message6", "Message7", "Message8", "Message9", "Message0", "Message1", "Message2", "Message3", "Message4", "Message5", "Message6", "Message7", "Message8", "Message9" };
    }
}
