// 
// Decompiled by Procyon v0.5.30
// 

package anon.util;

public class XMLParseException extends Exception
{
    private static final long serialVersionUID = 1L;
    public static final String ROOT_TAG = "##__root__##";
    public static final String NODE_NULL_TAG = "##__null__##";
    
    public XMLParseException(final String s, final String s2) {
        super(parseTagName(s) + getMessage(s2));
    }
    
    public XMLParseException(final String s) {
        this(s, (String)null);
    }
    
    private static String getMessage(final String s) {
        if (s == null) {
            return "";
        }
        return s;
    }
    
    private static String parseTagName(final String s) {
        final String s2 = "Error while parsing XML ";
        String s3;
        if (s == null) {
            s3 = "";
        }
        else if (s.equals("##__root__##")) {
            s3 = s2 + "document root! ";
        }
        else if (s.endsWith("##__null__##")) {
            s3 = s2 + "- node is null! ";
        }
        else {
            s3 = s2 + "node '" + s + "'! ";
        }
        return s3;
    }
}
