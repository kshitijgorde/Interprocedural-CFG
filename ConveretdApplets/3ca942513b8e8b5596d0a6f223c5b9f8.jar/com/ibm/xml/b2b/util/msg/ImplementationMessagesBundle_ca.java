// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xml.b2b.util.msg;

public final class ImplementationMessagesBundle_ca extends ErrorMessageBundle
{
    private static final Object[][] CONTENTS;
    private static final String[] MESSAGE_KEYS;
    
    protected Object[][] getContents() {
        return ImplementationMessagesBundle_ca.CONTENTS;
    }
    
    public String getString(final int n) {
        return this.getString(ImplementationMessagesBundle_ca.MESSAGE_KEYS[n]);
    }
    
    static {
        CONTENTS = new Object[][] { { "BadMessageKey", "The error message corresponding to the message key can not be found." }, { "FormatFailed", "An internal error occurred while formatting the following message:\n  " }, { "Message0", "no es pot convertir un car\u00e0cter Unicode fora de rang" }, { "Message1", "entrada insuficient per descodificar el car\u00e0cter" }, { "Message2", "falta la segona meitat del parell de suplents" }, { "Message3", "segona meitat de parell de suplents no v\u00e0lida" }, { "Message4", "primera meitat de parell de suplents no v\u00e0lida" }, { "Message5", "marca d'ordre d'octets obligat\u00f2ria" }, { "Message6", "codificaci\u00f3 de suplents d'UTF8 no v\u00e0lida" }, { "Message7", "seq\u00fc\u00e8ncia de car\u00e0cters multi-part parcial" }, { "Message8", "el contingut del nom de codificaci\u00f3 i del corrent d'octets no \u00e9s coherent" }, { "Message9", "codificaci\u00f3 de car\u00e0cters UTF8 no v\u00e0lida a l''octet {0} a [{1},{2},{3},{4}]" } };
        MESSAGE_KEYS = new String[] { "Message0", "Message1", "Message2", "Message3", "Message4", "Message5", "Message6", "Message7", "Message8", "Message9", "Message0", "Message1", "Message2", "Message3", "Message4", "Message5", "Message6", "Message7", "Message8", "Message9" };
    }
}
