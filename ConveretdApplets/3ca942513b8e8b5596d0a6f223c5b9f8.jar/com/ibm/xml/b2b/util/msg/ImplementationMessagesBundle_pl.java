// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xml.b2b.util.msg;

public final class ImplementationMessagesBundle_pl extends ErrorMessageBundle
{
    private static final Object[][] CONTENTS;
    private static final String[] MESSAGE_KEYS;
    
    protected Object[][] getContents() {
        return ImplementationMessagesBundle_pl.CONTENTS;
    }
    
    public String getString(final int n) {
        return this.getString(ImplementationMessagesBundle_pl.MESSAGE_KEYS[n]);
    }
    
    static {
        CONTENTS = new Object[][] { { "BadMessageKey", "The error message corresponding to the message key can not be found." }, { "FormatFailed", "An internal error occurred while formatting the following message:\n  " }, { "Message0", "nie mo\u017cna dokona\u0107 konwersji znaku Unicode nie mieszcz\u0105cego si\u0119 w dozwolonym zakresie" }, { "Message1", "niewystarczaj\u0105ce dane wej\u015bciowe do zdekodowania znaku" }, { "Message2", "brakuje drugiej po\u0142owy pary odpowiednika" }, { "Message3", "niepoprawna druga po\u0142owa pary odpowiednika" }, { "Message4", "niepoprawna pierwsza po\u0142owa pary odpowiednika" }, { "Message5", "wymagany jest znacznik kolejno\u015bci bajt\u00f3w" }, { "Message6", "niepoprawne kodowanie odpowiednika UTF8" }, { "Message7", "cz\u0119\u015b\u0107 wielocz\u0119\u015bciowej sekwencji znak\u00f3w" }, { "Message8", "nazwa kodowania i zawarto\u015b\u0107 strumienia bajt\u00f3w s\u0105 niesp\u00f3jne" }, { "Message9", "niepoprawne kodowanie znak\u00f3w UTF8 w bajcie {0} w [{1},{2},{3},{4}]" } };
        MESSAGE_KEYS = new String[] { "Message0", "Message1", "Message2", "Message3", "Message4", "Message5", "Message6", "Message7", "Message8", "Message9", "Message0", "Message1", "Message2", "Message3", "Message4", "Message5", "Message6", "Message7", "Message8", "Message9", "Message0", "Message1", "Message2", "Message3", "Message4", "Message5", "Message6", "Message7", "Message8", "Message9", "Message0", "Message1", "Message2", "Message3", "Message4", "Message5", "Message6", "Message7", "Message8", "Message9", "Message0", "Message1", "Message2", "Message3", "Message4", "Message5", "Message6", "Message7", "Message8", "Message9", "Message0", "Message1", "Message2", "Message3", "Message4", "Message5", "Message6", "Message7", "Message8", "Message9", "Message0", "Message1", "Message2", "Message3", "Message4", "Message5", "Message6", "Message7", "Message8", "Message9", "Message0", "Message1", "Message2", "Message3", "Message4", "Message5", "Message6", "Message7", "Message8", "Message9", "Message0", "Message1", "Message2", "Message3", "Message4", "Message5", "Message6", "Message7", "Message8", "Message9", "Message0", "Message1", "Message2", "Message3", "Message4", "Message5", "Message6", "Message7", "Message8", "Message9", "Message0", "Message1", "Message2", "Message3", "Message4", "Message5", "Message6", "Message7", "Message8", "Message9" };
    }
}
