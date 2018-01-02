// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

import java.util.Hashtable;
import java.util.Stack;

public class y
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
    
    public static void a(final bf bf, final char[] array) throws Exception {
        final byte[] array2 = new byte[array.length];
        for (int i = 0; i < array.length; ++i) {
            array2[i] = (byte)array[i];
        }
        a(bf, array2);
    }
    
    public static void a(final bf bf, final byte[] array) throws Exception {
        final Stack stack = new Stack();
        int i = 0;
        int n = 15;
        int n2 = 34;
        int n3 = 0;
        final StringBuffer sb = new StringBuffer();
        final StringBuffer sb2 = new StringBuffer();
        String s = null;
        String s2 = null;
        Hashtable<String, String> hashtable = null;
        final Stack<Integer> stack2 = new Stack<Integer>();
        bf.if();
        int n4 = 1;
        int n5 = 0;
        int n6 = 0;
        while (i < array.length) {
            int n7 = array[i];
            ++i;
            if (n7 == 10 && n6 != 0) {
                n6 = 0;
            }
            else {
                if (n6 != 0) {
                    n6 = 0;
                }
                else if (n7 == 10) {
                    ++n4;
                    n5 = 0;
                }
                else if (n7 == 13) {
                    n6 = 1;
                    n7 = 10;
                    ++n4;
                    n5 = 0;
                }
                else {
                    ++n5;
                }
                if (n == 11) {
                    bf.a();
                    return;
                }
                if (n == 1) {
                    if (n7 == 60) {
                        stack2.push(new Integer(n));
                        n = 5;
                        if (sb.length() <= 0) {
                            continue;
                        }
                        bf.if(sb.toString());
                        sb.setLength(0);
                    }
                    else if (n7 == 38) {
                        stack2.push(new Integer(n));
                        n = 2;
                        sb2.setLength(0);
                    }
                    else {
                        sb.append((char)n7);
                    }
                }
                else if (n == 4) {
                    if (n7 == 62) {
                        n = a(stack2);
                        s = sb.toString();
                        sb.setLength(0);
                        if (--n3 == 0) {
                            n = 11;
                        }
                        bf.a(s);
                    }
                    else {
                        sb.append((char)n7);
                    }
                }
                else if (n == 16) {
                    if (n7 == 62 && sb.toString().endsWith("]]")) {
                        sb.setLength(sb.length() - 2);
                        bf.if(sb.toString());
                        sb.setLength(0);
                        n = a(stack2);
                    }
                    else {
                        sb.append((char)n7);
                    }
                }
                else if (n == 13) {
                    if (n7 == 62 && sb.toString().endsWith("--")) {
                        bf.do(sb.toString());
                        sb.setLength(0);
                        n = a(stack2);
                    }
                    else {
                        sb.append((char)n7);
                    }
                }
                else if (n == 15) {
                    if (n7 != 60) {
                        continue;
                    }
                    stack2.push(new Integer(1));
                    n = 5;
                }
                else if (n == 14) {
                    if (n7 != 62) {
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
                    if (n7 == 47) {
                        stack2.push(new Integer(a));
                        n = 4;
                    }
                    else if (n7 == 63) {
                        n = 14;
                    }
                    else {
                        stack2.push(new Integer(a));
                        n = 3;
                        s = null;
                        hashtable = new Hashtable<String, String>();
                        sb.append((char)n7);
                    }
                }
                else if (n == 2) {
                    if (n7 == 59) {
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
                            a("Unknown entity: &" + string + ";", n4, n5);
                        }
                    }
                    else {
                        sb2.append((char)n7);
                    }
                }
                else if (n == 12) {
                    if (s == null) {
                        s = sb.toString();
                    }
                    if (n7 != 62) {
                        a("Expected > for tag: <" + s + "/>", n4, n5);
                    }
                    bf.a(s, hashtable);
                    bf.a(s);
                    if (n3 == 0) {
                        bf.a();
                        return;
                    }
                    sb.setLength(0);
                    hashtable = new Hashtable<String, String>();
                    s = null;
                    n = a(stack2);
                }
                else if (n == 3) {
                    if (n7 == 62) {
                        if (s == null) {
                            s = sb.toString();
                        }
                        sb.setLength(0);
                        ++n3;
                        bf.a(s, hashtable);
                        s = null;
                        hashtable = new Hashtable<String, String>();
                        n = a(stack2);
                    }
                    else if (n7 == 47) {
                        n = 12;
                    }
                    else if (n7 == 45 && sb.toString().equals("!-")) {
                        n = 13;
                    }
                    else if (n7 == 91 && sb.toString().equals("![CDATA")) {
                        n = 16;
                        sb.setLength(0);
                    }
                    else if (n7 == 69 && sb.toString().equals("!DOCTYP")) {
                        sb.setLength(0);
                        n = 14;
                    }
                    else if (Character.isWhitespace((char)n7)) {
                        s = sb.toString();
                        sb.setLength(0);
                        n = 8;
                    }
                    else {
                        sb.append((char)n7);
                    }
                }
                else if (n == 7) {
                    if (n7 == n2) {
                        final String string2 = sb.toString();
                        sb.setLength(0);
                        hashtable.put(s2, string2);
                        n = 8;
                    }
                    else if (" \r\n\t".indexOf(n7) >= 0) {
                        sb.append(' ');
                    }
                    else if (n7 == 38) {
                        stack2.push(new Integer(n));
                        n = 2;
                        sb2.setLength(0);
                    }
                    else {
                        sb.append((char)n7);
                    }
                }
                else if (n == 10) {
                    if (n7 == 34 || n7 == 39) {
                        n2 = n7;
                        n = 7;
                    }
                    else {
                        if (Character.isWhitespace((char)n7)) {
                            continue;
                        }
                        a("Error in attribute processing", n4, n5);
                    }
                }
                else if (n == 6) {
                    if (Character.isWhitespace((char)n7)) {
                        s2 = sb.toString();
                        sb.setLength(0);
                        n = 9;
                    }
                    else if (n7 == 61) {
                        s2 = sb.toString();
                        sb.setLength(0);
                        n = 10;
                    }
                    else {
                        sb.append((char)n7);
                    }
                }
                else if (n == 9) {
                    if (n7 == 61) {
                        n = 10;
                    }
                    else {
                        if (Character.isWhitespace((char)n7)) {
                            continue;
                        }
                        a("Error in attribute processing.", n4, n5);
                    }
                }
                else {
                    if (n != 8) {
                        continue;
                    }
                    if (n7 == 62) {
                        n = a(stack2);
                        bf.a(s, hashtable);
                        ++n3;
                        s = null;
                        hashtable = new Hashtable<String, String>();
                    }
                    else if (n7 == 47) {
                        n = 12;
                    }
                    else {
                        if (Character.isWhitespace((char)n7)) {
                            continue;
                        }
                        n = 6;
                        sb.append((char)n7);
                    }
                }
            }
        }
        if (n == 11) {
            bf.a();
        }
        else {
            a("missing end tag", n4, n5);
        }
    }
    
    private static void a(final String s, final int n, final int n2) throws Exception {
        System.out.println(String.valueOf(s) + " at line " + n + ", column " + n2);
        throw new Exception(String.valueOf(s) + " at line " + n + ", column " + n2);
    }
}
