// 
// Decompiled by Procyon v0.5.30
// 

public class TokenStream
{
    private static final String CLASS_NAME = "TokenStream";
    private TokenList tList;
    
    public TokenStream(final TokenList tList) {
        this.tList = tList;
    }
    
    public Token currentToken() {
        Token token = null;
        if (this.tList != null) {
            token = this.tList.getToken();
        }
        return token;
    }
    
    public Token nextToken() {
        Token token = null;
        if (this.tList != null) {
            this.tList = this.tList.getNext();
            if (this.tList != null) {
                token = this.tList.getToken();
            }
        }
        return token;
    }
}
