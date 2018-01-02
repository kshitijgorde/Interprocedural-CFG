// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xml.b2b.util.msg;

public final class ImplementationMessagesBundle_de extends ErrorMessageBundle
{
    private static final Object[][] CONTENTS;
    private static final String[] MESSAGE_KEYS;
    
    protected Object[][] getContents() {
        return ImplementationMessagesBundle_de.CONTENTS;
    }
    
    public String getString(final int n) {
        return this.getString(ImplementationMessagesBundle_de.MESSAGE_KEYS[n]);
    }
    
    static {
        CONTENTS = new Object[][] { { "BadMessageKey", "The error message corresponding to the message key can not be found." }, { "FormatFailed", "An internal error occurred while formatting the following message:\n  " }, { "Message0", "Unicode-Zeichen au\u00dferhalb des g\u00fcltigen Bereichs kann nicht konvertiert werden." }, { "Message1", "Ungen\u00fcgende Eingabe zum Decodieren des Zeichens." }, { "Message2", "Fehlende zweite H\u00e4lfte des Ersatzpaars." }, { "Message3", "Ung\u00fcltige zweite H\u00e4lfte des Ersatzpaars." }, { "Message4", "Ung\u00fcltige erste H\u00e4lfte des Ersatzpaars." }, { "Message5", "Byteanordnungsmarkierung erforderlich." }, { "Message6", "Ung\u00fcltige UTF-8-Ersetzungsverschl\u00fcsselung." }, { "Message7", "Partielle mehrteilige Zeichenfolge." }, { "Message8", "Verschl\u00fcsselungsname und Bytestrominhalt sind inkonsistent." }, { "Message9", "Ung\u00fcltige UTF-8-Zeichencodierung bei Byte {0} in [{1},{2},{3},{4}]" } };
        MESSAGE_KEYS = new String[] { "Message0", "Message1", "Message2", "Message3", "Message4", "Message5", "Message6", "Message7", "Message8", "Message9", "Message0", "Message1", "Message2", "Message3", "Message4", "Message5", "Message6", "Message7", "Message8", "Message9", "Message0", "Message1", "Message2", "Message3", "Message4", "Message5", "Message6", "Message7", "Message8", "Message9", "Message0", "Message1", "Message2", "Message3", "Message4", "Message5", "Message6", "Message7", "Message8", "Message9" };
    }
}
