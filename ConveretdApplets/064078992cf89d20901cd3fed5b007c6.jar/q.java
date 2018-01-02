import java.util.Hashtable;
import java.util.Stack;

// 
// Decompiled by Procyon v0.5.30
// 

public class q
{
    private static final int goto = 1;
    private static final int a = 2;
    private static final int int = 3;
    private static final int byte = 4;
    private static final int else = 5;
    private static final int void = 6;
    private static final int do = 9;
    private static final int case = 10;
    private static final int char = 7;
    private static final int new = 8;
    private static final int if = 12;
    private static final int b = 13;
    private static final int for = 11;
    private static final int c = 14;
    private static final int long = 15;
    private static final int try = 16;
    
    private static int a(final Stack stack) {
        if (!stack.empty()) {
            return stack.pop();
        }
        return 15;
    }
    
    public static void a(final ae ae, final byte[] array, final int n) throws Exception {
        a(ae, new String(array, 0, n).toCharArray(), n);
    }
    
    public static void a(final ae ae, final char[] array, final int n) throws Exception {
        final Stack stack = new Stack();
        int i = 0;
        int n2 = 15;
        int n3 = 34;
        int n4 = 0;
        final StringBuffer sb = new StringBuffer();
        final StringBuffer sb2 = new StringBuffer();
        String s = null;
        String s2 = null;
        Hashtable<String, String> hashtable = null;
        final Stack<Integer> stack2 = new Stack<Integer>();
        ae.if();
        int n5 = 1;
        int n6 = 0;
        int n7 = 0;
        int n8 = 0;
        while (i < array.length) {
            int n9 = array[i];
            ++i;
            if (n9 == 10 && n7 != 0) {
                n7 = 0;
            }
            else {
                if (n7 != 0) {
                    n7 = 0;
                }
                else if (n9 == 10) {
                    ++n5;
                    n6 = 0;
                }
                else if (n9 == 13) {
                    n7 = 1;
                    n9 = 10;
                    ++n5;
                    n6 = 0;
                }
                else {
                    ++n6;
                }
                if (n2 == 11) {
                    ae.a();
                    return;
                }
                if (n2 == 1) {
                    if (n9 == 60) {
                        stack2.push(new Integer(n2));
                        n2 = 5;
                        if (sb.length() <= 0) {
                            continue;
                        }
                        ae.if(sb.toString());
                        sb.setLength(0);
                    }
                    else if (n9 == 38) {
                        stack2.push(new Integer(n2));
                        n2 = 2;
                        sb2.setLength(0);
                    }
                    else {
                        sb.append((char)n9);
                    }
                }
                else if (n2 == 4) {
                    if (n9 == 62) {
                        n2 = a(stack2);
                        s = sb.toString();
                        sb.setLength(0);
                        if (--n4 == 0) {
                            n2 = 11;
                        }
                        ae.a(s);
                    }
                    else {
                        sb.append((char)n9);
                    }
                }
                else if (n2 == 16) {
                    if (n9 == 62 && sb.toString().endsWith("]]")) {
                        sb.setLength(sb.length() - 2);
                        ae.if(sb.toString());
                        sb.setLength(0);
                        n2 = a(stack2);
                    }
                    else {
                        sb.append((char)n9);
                    }
                }
                else if (n2 == 13) {
                    if (n9 == 62 && sb.toString().endsWith("--")) {
                        ae.a(sb.toString(), n8 - 1);
                        sb.setLength(0);
                        n2 = a(stack2);
                    }
                    else {
                        sb.append((char)n9);
                    }
                }
                else if (n2 == 15) {
                    if (n9 != 60) {
                        continue;
                    }
                    stack2.push(new Integer(1));
                    n2 = 5;
                }
                else if (n2 == 14) {
                    if (n9 != 62) {
                        continue;
                    }
                    n2 = a(stack2);
                    if (n2 != 1) {
                        continue;
                    }
                    n2 = 15;
                }
                else if (n2 == 5) {
                    final int a = a(stack2);
                    if (n9 == 47) {
                        stack2.push(new Integer(a));
                        n2 = 4;
                    }
                    else if (n9 == 63) {
                        n2 = 14;
                    }
                    else {
                        stack2.push(new Integer(a));
                        n2 = 3;
                        s = null;
                        hashtable = new Hashtable<String, String>();
                        sb.append((char)n9);
                    }
                }
                else if (n2 == 2) {
                    if (n9 == 59) {
                        n2 = a(stack2);
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
                            sb.append((char)(int)Integer.getInteger(string.substring(1)));
                        }
                        else {
                            a("Unknown entity: &" + string + ";", n5, n6);
                        }
                    }
                    else {
                        sb2.append((char)n9);
                    }
                }
                else if (n2 == 12) {
                    if (s == null) {
                        s = sb.toString();
                    }
                    if (n9 != 62) {
                        a("Expected > for tag: <" + s + "/>", n5, n6);
                    }
                    ae.a(s, hashtable);
                    ae.a(s);
                    if (n4 == 0) {
                        ae.a();
                        return;
                    }
                    sb.setLength(0);
                    hashtable = new Hashtable<String, String>();
                    s = null;
                    n2 = a(stack2);
                }
                else if (n2 == 3) {
                    if (n9 == 62) {
                        if (s == null) {
                            s = sb.toString();
                        }
                        sb.setLength(0);
                        ++n4;
                        ae.a(s, hashtable);
                        s = null;
                        hashtable = new Hashtable<String, String>();
                        n2 = a(stack2);
                    }
                    else if (n9 == 47) {
                        n2 = 12;
                    }
                    else if (n9 == 45 && sb.toString().equals("!-")) {
                        n8 = n5;
                        n2 = 13;
                    }
                    else if (n9 == 91 && sb.toString().equals("![CDATA")) {
                        n2 = 16;
                        sb.setLength(0);
                    }
                    else if (n9 == 69 && sb.toString().equals("!DOCTYP")) {
                        sb.setLength(0);
                        n2 = 14;
                    }
                    else if (Character.isWhitespace((char)n9)) {
                        s = sb.toString();
                        sb.setLength(0);
                        n2 = 8;
                    }
                    else {
                        sb.append((char)n9);
                    }
                }
                else if (n2 == 7) {
                    if (n9 == n3) {
                        final String string2 = sb.toString();
                        sb.setLength(0);
                        hashtable.put(s2, string2);
                        n2 = 8;
                    }
                    else if (" \r\n\t".indexOf(n9) >= 0) {
                        sb.append(' ');
                    }
                    else if (n9 == 38) {
                        stack2.push(new Integer(n2));
                        n2 = 2;
                        sb2.setLength(0);
                    }
                    else {
                        sb.append((char)n9);
                    }
                }
                else if (n2 == 10) {
                    if (n9 == 34 || n9 == 39) {
                        n3 = n9;
                        n2 = 7;
                    }
                    else {
                        if (Character.isWhitespace((char)n9)) {
                            continue;
                        }
                        a("Error in attribute processing", n5, n6);
                    }
                }
                else if (n2 == 6) {
                    if (Character.isWhitespace((char)n9)) {
                        s2 = sb.toString();
                        sb.setLength(0);
                        n2 = 9;
                    }
                    else if (n9 == 61) {
                        s2 = sb.toString();
                        sb.setLength(0);
                        n2 = 10;
                    }
                    else {
                        sb.append((char)n9);
                    }
                }
                else if (n2 == 9) {
                    if (n9 == 61) {
                        n2 = 10;
                    }
                    else {
                        if (Character.isWhitespace((char)n9)) {
                            continue;
                        }
                        a("Error in attribute processing.", n5, n6);
                    }
                }
                else {
                    if (n2 != 8) {
                        continue;
                    }
                    if (n9 == 62) {
                        n2 = a(stack2);
                        ae.a(s, hashtable);
                        ++n4;
                        s = null;
                        hashtable = new Hashtable<String, String>();
                    }
                    else if (n9 == 47) {
                        n2 = 12;
                    }
                    else {
                        if (Character.isWhitespace((char)n9)) {
                            continue;
                        }
                        n2 = 6;
                        sb.append((char)n9);
                    }
                }
            }
        }
        if (n2 == 11) {
            ae.a();
        }
        else {
            a("missing end tag", n5, n6);
        }
    }
    
    private static void a(final String s, final int n, final int n2) throws Exception {
        System.out.println(String.valueOf(s) + " at line " + n + ", column " + n2);
        throw new Exception(String.valueOf(s) + " at line " + n + ", column " + n2);
    }
}
