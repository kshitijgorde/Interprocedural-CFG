// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xml.b2b.util.msg;

public final class ImplementationMessagesBundle_fr extends ErrorMessageBundle
{
    private static final Object[][] CONTENTS;
    private static final String[] MESSAGE_KEYS;
    
    protected Object[][] getContents() {
        return ImplementationMessagesBundle_fr.CONTENTS;
    }
    
    public String getString(final int n) {
        return this.getString(ImplementationMessagesBundle_fr.MESSAGE_KEYS[n]);
    }
    
    static {
        CONTENTS = new Object[][] { { "BadMessageKey", "The error message corresponding to the message key can not be found." }, { "FormatFailed", "An internal error occurred while formatting the following message:\n  " }, { "Message0", "impossible de convertir un caract\u00e8re Unicode hors plage de valeurs" }, { "Message1", "entr\u00e9e insuffisante pour d\u00e9coder le caract\u00e8re" }, { "Message2", "seconde moiti\u00e9 de la paire de substitution manquante" }, { "Message3", "seconde moiti\u00e9 de la paire de substitution non valide" }, { "Message4", "premi\u00e8re moiti\u00e9 de la paire de substitution non valide" }, { "Message5", "marque de l'ordre des octets requise" }, { "Message6", "encodage de substitution UTF non valide" }, { "Message7", "s\u00e9quence de caract\u00e8res multi-parties partielle" }, { "Message8", "nom d'encodage et contenu de flux d'octets incoh\u00e9rents" }, { "Message9", "encodage de caract\u00e8res UTF8 non valide \u00e0 l''octet {0} dans [{1},{2},{3},{4}]" } };
        MESSAGE_KEYS = new String[] { "Message0", "Message1", "Message2", "Message3", "Message4", "Message5", "Message6", "Message7", "Message8", "Message9", "Message0", "Message1", "Message2", "Message3", "Message4", "Message5", "Message6", "Message7", "Message8", "Message9", "Message0", "Message1", "Message2", "Message3", "Message4", "Message5", "Message6", "Message7", "Message8", "Message9", "Message0", "Message1", "Message2", "Message3", "Message4", "Message5", "Message6", "Message7", "Message8", "Message9", "Message0", "Message1", "Message2", "Message3", "Message4", "Message5", "Message6", "Message7", "Message8", "Message9", "Message0", "Message1", "Message2", "Message3", "Message4", "Message5", "Message6", "Message7", "Message8", "Message9" };
    }
}
