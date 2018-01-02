// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xpath.parser.generated;

public class ParseException extends Exception
{
    public Token currentToken;
    public int[][] expectedTokenSequences;
    public String[] tokenImage;
    protected String eol;
    
    public ParseException(final Token currentToken, final int[][] expectedTokenSequences, final String[] tokenImage) {
        super(A(currentToken, expectedTokenSequences, tokenImage));
        this.eol = System.getProperty("line.separator", "\n");
        this.currentToken = currentToken;
        this.expectedTokenSequences = expectedTokenSequences;
        this.tokenImage = tokenImage;
    }
    
    public ParseException() {
        this.eol = System.getProperty("line.separator", "\n");
    }
    
    public ParseException(final String s) {
        super(s);
        this.eol = System.getProperty("line.separator", "\n");
    }
    
    private static String A(final Token token, final int[][] array, final String[] array2) {
        final String property = System.getProperty("line.separator", "\n");
        final StringBuffer sb = new StringBuffer();
        int length = 0;
        for (int i = 0; i < array.length; ++i) {
            if (length < array[i].length) {
                length = array[i].length;
            }
            for (int j = 0; j < array[i].length; ++j) {
                sb.append(array2[array[i][j]]).append(' ');
            }
            if (array[i][array[i].length - 1] != 0) {
                sb.append("...");
            }
            sb.append(property).append("    ");
        }
        String s = "Encountered \"";
        Token token2 = token.next;
        for (int k = 0; k < length; ++k) {
            if (k != 0) {
                s = String.valueOf(s) + " ";
            }
            if (token2.kind == 0) {
                s = String.valueOf(s) + array2[0];
                break;
            }
            s = String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(s)).append(" ").append(array2[token2.kind]).toString())).append(" \"").toString())).append(A(token2.image)).toString()) + " \"";
            token2 = token2.next;
        }
        final String string = String.valueOf(new StringBuilder(String.valueOf(s)).append("\" at line ").append(token.next.beginLine).append(", column ").append(token.next.beginColumn).toString()) + "." + property;
        String s2;
        if (array.length == 1) {
            s2 = String.valueOf(string) + "Was expecting:" + property + "    ";
        }
        else {
            s2 = String.valueOf(string) + "Was expecting one of:" + property + "    ";
        }
        return String.valueOf(s2) + sb.toString();
    }
    
    static String A(final String s) {
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); ++i) {
            switch (s.charAt(i)) {
                case '\0': {
                    break;
                }
                case '\b': {
                    sb.append("\\b");
                    break;
                }
                case '\t': {
                    sb.append("\\t");
                    break;
                }
                case '\n': {
                    sb.append("\\n");
                    break;
                }
                case '\f': {
                    sb.append("\\f");
                    break;
                }
                case '\r': {
                    sb.append("\\r");
                    break;
                }
                case '\"': {
                    sb.append("\\\"");
                    break;
                }
                case '\'': {
                    sb.append("\\'");
                    break;
                }
                case '\\': {
                    sb.append("\\\\");
                    break;
                }
                default: {
                    final char char1;
                    if ((char1 = s.charAt(i)) < ' ' || char1 > '~') {
                        final String string = "0000" + Integer.toString(char1, 16);
                        sb.append("\\u" + string.substring(string.length() - 4, string.length()));
                        break;
                    }
                    sb.append(char1);
                    break;
                }
            }
        }
        return sb.toString();
    }
}
