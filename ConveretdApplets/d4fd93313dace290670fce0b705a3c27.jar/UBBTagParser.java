import java.util.StringTokenizer;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

class UBBTagParser
{
    private char tagStartChar;
    private char tagEndChar;
    private char quoteChar;
    private String textToParse;
    private int parseStartPosition;
    private UBBErrorHandler error;
    
    public synchronized UBBTag[] parse(final String s) {
        Object[] array = null;
        Label_0202: {
            if (s != null) {
                final int length = s.length();
                final StringBuffer sb = new StringBuffer(length);
                int index = 0;
                while (true) {
                    do {
                        final int index2 = s.indexOf(this.tagStartChar + "!--", index);
                        if (index2 >= 0) {
                            sb.append(s.substring(index, index2));
                            index = s.indexOf("--" + this.tagEndChar, index2);
                            if (index >= 0) {
                                continue;
                            }
                        }
                        else {
                            sb.append(s.substring(index));
                        }
                        this.textToParse = sb.toString();
                        this.parseStartPosition = 0;
                        final Vector vector = new Vector<UBBTag>(10, 10);
                        UBBTag tag;
                        while ((tag = this.getTag()) != null) {
                            vector.addElement(tag);
                        }
                        array = new UBBTag[vector.size()];
                        vector.copyInto(array);
                        vector.removeAllElements();
                        break Label_0202;
                    } while (++index < length);
                    continue;
                }
            }
        }
        this.textToParse = null;
        return (UBBTag[])array;
    }
    
    public UBBTagParser(final String s, final UBBErrorHandler error) {
        this.error = error;
        try {
            this.tagStartChar = s.charAt(0);
            this.tagEndChar = s.charAt(1);
            this.quoteChar = s.charAt(2);
        }
        catch (Exception ex) {
            this.tagStartChar = '<';
            this.tagEndChar = '>';
            this.quoteChar = '\"';
        }
    }
    
    private UBBTag getTag() {
        UBBTag ubbTag = new UBBTag();
        int n = 0;
        final String string = " \t\n\r" + this.quoteChar;
        final String string2 = "" + this.quoteChar;
        final int index = this.textToParse.indexOf(this.tagStartChar, this.parseStartPosition);
        if (index >= 0) {
            final int n2 = index + 1;
            n = this.textToParse.indexOf(this.tagEndChar, n2);
            int n4;
            for (int n3 = this.textToParse.indexOf(this.quoteChar, n2); n3 >= 0 && n3 < n; n = this.textToParse.indexOf(this.tagEndChar, n4), n3 = this.textToParse.indexOf(this.quoteChar, n4)) {
                n4 = this.textToParse.indexOf(this.quoteChar, n3 + 1) + 1;
            }
            this.parseStartPosition = n + 1;
        }
        try {
            if (index >= 0 && n > 0) {
                int index2 = this.textToParse.indexOf(32, index);
                int index3 = this.textToParse.indexOf(this.tagEndChar, index);
                if (index2 == -1) {
                    index2 = this.textToParse.length() - 1;
                }
                if (index3 == -1) {
                    index3 = this.textToParse.length() - 1;
                }
                final int n5 = (index2 < index3) ? index2 : index3;
                if (n5 > 0) {
                    ubbTag.tag = this.textToParse.substring(index + 1, n5).toLowerCase().trim();
                }
                final Vector vector = new Vector<String>(10, 10);
                final Vector<String> vector2 = new Vector<String>(10, 10);
                final StringTokenizer stringTokenizer = new StringTokenizer(this.textToParse.substring(index, n), string, true);
                if (n > index) {
                    stringTokenizer.nextToken();
                }
                while (stringTokenizer.hasMoreTokens()) {
                    String s = stringTokenizer.nextToken();
                    final char char1 = s.charAt(0);
                    if (char1 != ' ' && char1 != '\t' && char1 != '\n' && char1 != '\r') {
                        if (char1 == this.quoteChar) {
                            if (stringTokenizer.hasMoreTokens()) {
                                s = stringTokenizer.nextToken(string2);
                                if (s.charAt(0) == '\"') {
                                    s = " ";
                                }
                            }
                            String s2 = null;
                            if (vector2.size() > 0) {
                                s2 = vector2.elementAt(vector2.size() - 1);
                            }
                            if (s2 != null && !s2.equals("-")) {
                                vector2.setElementAt(s, vector2.size() - 1);
                            }
                            else {
                                vector.addElement(s);
                                vector2.addElement("-");
                            }
                            if (!stringTokenizer.hasMoreTokens()) {
                                continue;
                            }
                            stringTokenizer.nextToken(string);
                        }
                        else {
                            final int index4 = s.indexOf(61);
                            if (index4 >= 0) {
                                vector.addElement(s.substring(0, index4).toLowerCase());
                                vector2.addElement(s.substring(index4 + 1).toLowerCase());
                            }
                            else {
                                vector.addElement(s.toLowerCase());
                                vector2.addElement("-");
                            }
                        }
                    }
                }
                vector.trimToSize();
                vector2.trimToSize();
                ubbTag.attribute = new String[vector.size()][2];
                for (int i = 0; i < ubbTag.attribute.length; ++i) {
                    ubbTag.attribute[i][0] = vector.elementAt(i);
                    ubbTag.attribute[i][1] = vector2.elementAt(i);
                }
                final int length = this.textToParse.length();
                if (n > 0 && n < length - 1) {
                    int n6 = this.textToParse.indexOf(this.tagStartChar, n + 1);
                    if (n6 == -1) {
                        n6 = this.textToParse.length();
                    }
                    if (n6 == length) {
                        ubbTag.text = this.textToParse.substring(n + 1);
                    }
                    else {
                        ubbTag.text = this.textToParse.substring(n + 1, n6);
                    }
                }
                else {
                    ubbTag.text = "";
                }
            }
            else {
                ubbTag = null;
            }
        }
        catch (Exception ex) {
            this.error.notify(null, 0, "Malformed tag: check for missing quote", null);
            return null;
        }
        return ubbTag;
    }
}
