// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xml.b2b.util.msg;

public final class ImplementationMessagesBundle_es extends ErrorMessageBundle
{
    private static final Object[][] CONTENTS;
    private static final String[] MESSAGE_KEYS;
    
    protected Object[][] getContents() {
        return ImplementationMessagesBundle_es.CONTENTS;
    }
    
    public String getString(final int n) {
        return this.getString(ImplementationMessagesBundle_es.MESSAGE_KEYS[n]);
    }
    
    static {
        CONTENTS = new Object[][] { { "BadMessageKey", "The error message corresponding to the message key can not be found." }, { "FormatFailed", "An internal error occurred while formatting the following message:\n  " }, { "Message0", "no se puede convertir el car\u00e1cter Unicode fuera-de-rango" }, { "Message1", "entrada insuficiente para decodificar el car\u00e1cter" }, { "Message2", "falta la segunda mitad del par sustituto" }, { "Message3", "la segunda mitad del par sustituto no es v\u00e1lida" }, { "Message4", "la primera mitad del par sustituto no es v\u00e1lida" }, { "Message5", "se necesita la marca orden-de-bytes" }, { "Message6", "la codificaci\u00f3n sustituta UTF8 no es v\u00e1lida" }, { "Message7", "la secuencia de caracteres multi-parte es parcial" }, { "Message8", "el nombre de codificaci\u00f3n y el contenido de la corriente de bytes son incoherentes" }, { "Message9", "la codificaci\u00f3n del car\u00e1cter UTF8 en el byte {0} en [{1},{2},{3},{4}] no es v\u00e1lida" } };
        MESSAGE_KEYS = new String[] { "Message0", "Message1", "Message2", "Message3", "Message4", "Message5", "Message6", "Message7", "Message8", "Message9", "Message0", "Message1", "Message2", "Message3", "Message4", "Message5", "Message6", "Message7", "Message8", "Message9", "Message0", "Message1", "Message2", "Message3", "Message4", "Message5", "Message6", "Message7", "Message8", "Message9", "Message0", "Message1", "Message2", "Message3", "Message4", "Message5", "Message6", "Message7", "Message8", "Message9", "Message0", "Message1", "Message2", "Message3", "Message4", "Message5", "Message6", "Message7", "Message8", "Message9" };
    }
}
