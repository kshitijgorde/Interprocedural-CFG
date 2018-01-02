// 
// Decompiled by Procyon v0.5.30
// 

package de.ts.b.a;

import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.Stack;
import java.io.DataInputStream;

public class i
{
    private static final char new = '#';
    private static final String int = "=";
    private static final String long = ",";
    private static final String b = "BGCOLOR";
    private static final String do = "FGCOLOR";
    private static final String byte = "FONT";
    private static final String if = "TEXT";
    private static final String case = "ACTION";
    private static final String c = "LOOP START";
    private static final String null = "LOOP END";
    private static final String goto = "LINK";
    private static final String else = "NOLINK";
    private static final String char = "IMAGE";
    private static final String try = "EVENT";
    DataInputStream void;
    Stack for;
    Vector a;
    
    public i(final DataInputStream void1) {
        this.void = null;
        this.for = null;
        this.a = null;
        this.void = void1;
    }
    
    public Vector a() {
        try {
            this.if();
        }
        catch (Exception ex) {
            ex.printStackTrace(System.err);
        }
        return this.a;
    }
    
    private void if() throws IOException {
        this.a = new Vector(100, 100);
        this.for = new Stack();
        int n = 0;
        int n2 = 0;
        String line;
        while ((line = this.void.readLine()) != null) {
            ++n;
            if (line.length() > 0 && line.charAt(0) != '#') {
                a a = null;
                final StringTokenizer stringTokenizer = new StringTokenizer(line.substring(line.indexOf("=") + 1), ",");
                if (line.indexOf("BGCOLOR") >= 0) {
                    a = new g(Integer.parseInt(stringTokenizer.nextToken().trim()), Integer.parseInt(stringTokenizer.nextToken().trim()), Integer.parseInt(stringTokenizer.nextToken().trim()));
                }
                else if (line.indexOf("FGCOLOR") >= 0) {
                    a = new m(Integer.parseInt(stringTokenizer.nextToken().trim()), Integer.parseInt(stringTokenizer.nextToken().trim()), Integer.parseInt(stringTokenizer.nextToken().trim()));
                }
                else if (line.indexOf("FONT") >= 0) {
                    a = new c(stringTokenizer.nextToken().trim(), Integer.parseInt(stringTokenizer.nextToken().trim()), Integer.parseInt(stringTokenizer.nextToken().trim()));
                }
                else if (line.indexOf("TEXT") >= 0) {
                    final String trim = stringTokenizer.nextToken().trim();
                    int int1 = 0;
                    if (stringTokenizer.hasMoreTokens()) {
                        int1 = Integer.parseInt(stringTokenizer.nextToken().trim());
                    }
                    a = new l(trim, int1);
                }
                else if (line.indexOf("ACTION") >= 0) {
                    final String trim2 = stringTokenizer.nextToken().trim();
                    long long1 = 0L;
                    short short1 = 0;
                    if (stringTokenizer.hasMoreTokens()) {
                        long1 = Long.parseLong(stringTokenizer.nextToken().trim());
                        if (stringTokenizer.hasMoreTokens()) {
                            short1 = Short.parseShort(stringTokenizer.nextToken().trim());
                        }
                    }
                    a = new d(trim2, long1, short1);
                }
                else if (line.indexOf("LOOP START") >= 0) {
                    a = new f(0, 0);
                    this.for.push(new Integer(n2));
                }
                else if (line.indexOf("LOOP END") >= 0) {
                    int int2 = -1;
                    if (line.indexOf("=") >= 0) {
                        int2 = Integer.parseInt(stringTokenizer.nextToken().trim());
                    }
                    int intValue = 0;
                    if (!this.for.isEmpty()) {
                        intValue = this.for.pop();
                    }
                    else {
                        System.err.println(String.valueOf(String.valueOf(String.valueOf("line ").concat(String.valueOf(n))).concat(String.valueOf(": no matching "))).concat(String.valueOf("LOOP START")));
                    }
                    a = new f(int2, intValue);
                }
                else if (line.indexOf("NOLINK") >= 0) {
                    a = new k(null, null);
                }
                else if (line.indexOf("LINK") >= 0) {
                    final String trim3 = stringTokenizer.nextToken().trim();
                    String trim4 = "_top";
                    if (stringTokenizer.hasMoreTokens()) {
                        trim4 = stringTokenizer.nextToken().trim();
                    }
                    a = new k(trim3, trim4);
                }
                else if (line.indexOf("IMAGE") >= 0) {
                    final String trim5 = stringTokenizer.nextToken().trim();
                    int int3 = 0;
                    if (stringTokenizer.hasMoreTokens()) {
                        int3 = Integer.parseInt(stringTokenizer.nextToken().trim());
                    }
                    a = new b(trim5, int3);
                }
                else if (line.indexOf("EVENT") >= 0) {
                    a = new h(stringTokenizer.nextToken().trim());
                }
                else {
                    System.err.println(String.valueOf(String.valueOf(String.valueOf(String.valueOf("line ").concat(String.valueOf(n))).concat(String.valueOf(": found no pattern  ("))).concat(String.valueOf(line))).concat(String.valueOf(")")));
                }
                if (a != null) {
                    a.a(line);
                    this.a.addElement(a);
                }
                ++n2;
            }
        }
        this.a.trimToSize();
    }
}
