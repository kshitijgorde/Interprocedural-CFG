// 
// Decompiled by Procyon v0.5.30
// 

package scanner;

public interface TokenType
{
    public static final TokenType UNKNOWN = new TokenType() {
        private static final String UNKNOWN_TYPE = "UNKNOWN";
        
        public String toString() {
            return "UNKNOWN";
        }
    };
}
