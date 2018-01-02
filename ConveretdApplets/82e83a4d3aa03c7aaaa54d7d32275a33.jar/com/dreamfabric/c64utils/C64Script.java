// 
// Decompiled by Procyon v0.5.30
// 

package com.dreamfabric.c64utils;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class C64Script
{
    int pos;
    
    public void test() {
        System.out.println("Test was called!!!");
    }
    
    public void test2(final String s) {
        System.out.println("Test2 was called with arg:" + s);
    }
    
    public void enterText(final String s) {
        System.out.println("enterText was called with arg:" + s);
    }
    
    private String getString(final String s, final char c) {
        final StringBuffer sb = new StringBuffer();
        while (this.pos < s.length()) {
            final char char1 = s.charAt(this.pos++);
            if (char1 == '\\') {
                sb.append(s.charAt(this.pos++));
            }
            else {
                if (char1 == c) {
                    return sb.toString();
                }
                sb.append(char1);
            }
        }
        throw new IllegalArgumentException("Illegal string syntax at: " + this.pos);
    }
    
    public void interpretCall(final String s, final Object o) {
        System.out.println("Parsing: " + s);
        this.pos = 0;
        final int length = s.length();
        s.trim();
        char char1;
        while (this.pos < length && (char1 = s.charAt(this.pos++)) != '(') {}
        final String substring = s.substring(0, this.pos - 1);
        System.out.println("function name: " + substring);
        final ArrayList list = new ArrayList<String>();
        String s2 = "";
        while (this.pos < length) {
            final char char2 = s.charAt(this.pos++);
            switch (char2) {
                case 44: {
                    if (s2 != "") {
                        list.add(s2);
                        s2 = "";
                        continue;
                    }
                    throw new IllegalArgumentException("unexpected ',' at " + this.pos);
                }
                case 34:
                case 39: {
                    s2 = this.getString(s, char2);
                }
                case 41: {
                    if (s2 != "") {
                        list.add(s2);
                        s2 = "";
                    }
                    final Method[] methods = o.getClass().getMethods();
                    for (int i = 0; i < methods.length; ++i) {
                        if (substring.equals(methods[i].getName())) {
                            final Method method = methods[i];
                            System.out.println("Method found: " + method);
                            if (list.size() == method.getParameterTypes().length) {
                                System.out.println("Correct param number, calling method!");
                                try {
                                    method.invoke(o, list.toArray());
                                }
                                catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                            }
                        }
                    }
                    break;
                }
            }
            s2 += char2;
        }
    }
    
    public static void main(final String[] array) {
        final C64Script c64Script = new C64Script();
        c64Script.interpretCall(array[0], c64Script);
    }
}
