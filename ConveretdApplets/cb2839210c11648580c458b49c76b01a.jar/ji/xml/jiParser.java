// 
// Decompiled by Procyon v0.5.30
// 

package ji.xml;

import java.util.Hashtable;
import java.io.Reader;
import java.util.Stack;

public class jiParser
{
    private static int a(final Stack stack) {
        if (!stack.empty()) {
            return stack.pop();
        }
        return 15;
    }
    
    public static void parse(final jiXMLDocHandler jiXMLDocHandler, final Reader reader) throws Exception {
        final Stack stack = new Stack();
        int n = 15;
        int n2 = 34;
        int n3 = 0;
        final StringBuffer sb = new StringBuffer();
        final StringBuffer sb2 = new StringBuffer();
        String s = null;
        String s2 = null;
        Hashtable<String, String> hashtable = null;
        final Stack<Integer> stack2 = new Stack<Integer>();
        jiXMLDocHandler.startDocument();
        int n4 = 1;
        int n5 = 0;
        int n6 = 0;
        int read;
        while ((read = reader.read()) != -1) {
            if (read == 10 && n6 != 0) {
                n6 = 0;
            }
            else {
                if (n6 != 0) {
                    n6 = 0;
                }
                else if (read == 10) {
                    ++n4;
                    n5 = 0;
                }
                else if (read == 13) {
                    n6 = 1;
                    read = 10;
                    ++n4;
                    n5 = 0;
                }
                else {
                    ++n5;
                }
                if (n == 11) {
                    jiXMLDocHandler.endDocument();
                    return;
                }
                if (n == 1) {
                    if (read == 60) {
                        stack2.push(new Integer(n));
                        n = 5;
                        if (sb.length() <= 0) {
                            continue;
                        }
                        jiXMLDocHandler.text(sb.toString());
                        sb.setLength(0);
                    }
                    else if (read == 38) {
                        stack2.push(new Integer(n));
                        n = 2;
                        sb2.setLength(0);
                    }
                    else {
                        sb.append((char)read);
                    }
                }
                else if (n == 4) {
                    if (read == 62) {
                        n = a(stack2);
                        s = sb.toString();
                        sb.setLength(0);
                        if (--n3 == 0) {
                            n = 11;
                        }
                        jiXMLDocHandler.endElement(s);
                    }
                    else {
                        sb.append((char)read);
                    }
                }
                else if (n == 16) {
                    if (read == 62 && sb.toString().endsWith("]]")) {
                        sb.setLength(sb.length() - 2);
                        jiXMLDocHandler.text(sb.toString());
                        sb.setLength(0);
                        n = a(stack2);
                    }
                    else {
                        sb.append((char)read);
                    }
                }
                else if (n == 13) {
                    if (read == 62 && sb.toString().endsWith("--")) {
                        sb.setLength(0);
                        n = a(stack2);
                    }
                    else {
                        sb.append((char)read);
                    }
                }
                else if (n == 15) {
                    if (read != 60) {
                        continue;
                    }
                    stack2.push(new Integer(1));
                    n = 5;
                }
                else if (n == 14) {
                    if (read != 62) {
                        continue;
                    }
                    n = a(stack2);
                    if (n != 1) {
                        continue;
                    }
                    n = 15;
                }
                else if (n == 5) {
                    final int a = a(stack2);
                    if (read == 47) {
                        stack2.push(new Integer(a));
                        n = 4;
                    }
                    else if (read == 63) {
                        n = 14;
                    }
                    else {
                        stack2.push(new Integer(a));
                        n = 3;
                        s = null;
                        hashtable = new Hashtable<String, String>();
                        sb.append((char)read);
                    }
                }
                else if (n == 2) {
                    if (read == 59) {
                        n = a(stack2);
                        final String string = sb2.toString();
                        sb2.setLength(0);
                        if (string.equals("lt")) {
                            sb.append('<');
                        }
                        else if (string.equals("gt")) {
                            sb.append('>');
                        }
                        else if (string.equals("amp")) {
                            sb.append('&');
                        }
                        else if (string.equals("quot")) {
                            sb.append('\"');
                        }
                        else if (string.equals("apos")) {
                            sb.append('\'');
                        }
                        else if (string.startsWith("#")) {
                            sb.append((char)Integer.parseInt(string.substring(1)));
                        }
                        else {
                            a(String.valueOf(String.valueOf(new StringBuffer("Unknown entity: &").append(string).append(";"))), n4, n5);
                        }
                    }
                    else {
                        sb2.append((char)read);
                    }
                }
                else if (n == 12) {
                    if (s == null) {
                        s = sb.toString();
                    }
                    if (read != 62) {
                        a(String.valueOf(String.valueOf(new StringBuffer("Expected > for tag: <").append(s).append("/>"))), n4, n5);
                    }
                    jiXMLDocHandler.startElement(s, hashtable);
                    jiXMLDocHandler.endElement(s);
                    if (n3 == 0) {
                        jiXMLDocHandler.endDocument();
                        return;
                    }
                    sb.setLength(0);
                    hashtable = new Hashtable<String, String>();
                    s = null;
                    n = a(stack2);
                }
                else if (n == 3) {
                    if (read == 62) {
                        if (s == null) {
                            s = sb.toString();
                        }
                        sb.setLength(0);
                        ++n3;
                        jiXMLDocHandler.startElement(s, hashtable);
                        s = null;
                        hashtable = new Hashtable<String, String>();
                        n = a(stack2);
                    }
                    else if (read == 47) {
                        n = 12;
                    }
                    else if (read == 45 && sb.toString().equals("!-")) {
                        n = 13;
                    }
                    else if (read == 91 && sb.toString().equals("![CDATA")) {
                        n = 16;
                        sb.setLength(0);
                    }
                    else if (read == 69 && sb.toString().equals("!DOCTYP")) {
                        sb.setLength(0);
                        n = 14;
                    }
                    else if (Character.isWhitespace((char)read)) {
                        s = sb.toString();
                        sb.setLength(0);
                        n = 8;
                    }
                    else {
                        sb.append((char)read);
                    }
                }
                else if (n == 7) {
                    if (read == n2) {
                        final String string2 = sb.toString();
                        sb.setLength(0);
                        hashtable.put(s2, string2);
                        n = 8;
                    }
                    else if (" \r\n\t".indexOf(read) >= 0) {
                        sb.append(' ');
                    }
                    else if (read == 38) {
                        stack2.push(new Integer(n));
                        n = 2;
                        sb2.setLength(0);
                    }
                    else {
                        sb.append((char)read);
                    }
                }
                else if (n == 10) {
                    if (read == 34 || read == 39) {
                        n2 = read;
                        n = 7;
                    }
                    else {
                        if (Character.isWhitespace((char)read)) {
                            continue;
                        }
                        a("Error in attribute processing", n4, n5);
                    }
                }
                else if (n == 6) {
                    if (Character.isWhitespace((char)read)) {
                        s2 = sb.toString();
                        sb.setLength(0);
                        n = 9;
                    }
                    else if (read == 61) {
                        s2 = sb.toString();
                        sb.setLength(0);
                        n = 10;
                    }
                    else {
                        sb.append((char)read);
                    }
                }
                else if (n == 9) {
                    if (read == 61) {
                        n = 10;
                    }
                    else {
                        if (Character.isWhitespace((char)read)) {
                            continue;
                        }
                        a("Error in attribute processing.", n4, n5);
                    }
                }
                else {
                    if (n != 8) {
                        continue;
                    }
                    if (read == 62) {
                        n = a(stack2);
                        jiXMLDocHandler.startElement(s, hashtable);
                        ++n3;
                        s = null;
                        hashtable = new Hashtable<String, String>();
                    }
                    else if (read == 47) {
                        n = 12;
                    }
                    else {
                        if (Character.isWhitespace((char)read)) {
                            continue;
                        }
                        n = 6;
                        sb.append((char)read);
                    }
                }
            }
        }
        if (n == 11) {
            jiXMLDocHandler.endDocument();
        }
        else {
            a("missing end tag", n4, n5);
        }
    }
    
    private static void a(final String s, final int n, final int n2) throws Exception {
        throw new Exception(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s))).append(" near line ").append(n).append(", column ").append(n2))));
    }
}
