// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

import java.util.Vector;

public class bx
{
    volatile Vector a;
    TWViewer b;
    Thread c;
    public static byte[] d;
    
    static {
        bx.d = new byte[] { 25, 64, -103, 48, 64, 12, 8, -121, -59, 36, 50, 73, -108, 49, -97, -48, -88, -108, -23, -124, 46, -87, 71, -91, 48, 8, 0, 33, -7, 4, 5, 50, 0, 50, 0, 44, -10, 0, 93, 0, 10, 0, 10, 0, 0, 6, 25, 64, -103, 48, 64, 12, 8, -121, -59, 36, 50, 73, -108, 49, -97, -48, -88, -108, -23, -124, 46, -87, 71, -91, 48, 8, 0, 33, -7, 4, 5, 50, 0, 50, 0, 44, -19, 0, -126, 0, 10, 0, 10, 0, 0, 6, 25, 64, -103, 48, 64, 12, 8, -121, -59, 36, 50, 73, -108, 49, -97, -48, -88, -108, -23, -124, 46, -87, 71, -91, 48, 8, 0, 33, -7, 4, 5, 50, 0, 50, 0, 44, 99, 0, 98, 0, 122, 0, 70, 0, 0, 6, -111, 64, -104, 108, 72, 44, 26, -113, -56, -92, 114, -55, 108, 58, -97, -48, -88, 116, 74, -83, 90, -81, -40, -84, 118, -53, -19, 122, -65, -32, -80, 120, 76, 46, -101, -49, -24, -76, 122, -51, 110, -69, -33, -16, -72, 124, 78, -81, -37, -17, -8, -68, 126, -49, -17, -5, -1, -128, -127, -126, -125, -124, -123, -122, -121, -120, -119, -118, -117, -116, -115, -114, -113, -112, -111, -110, -109, -108, -107, -106, -105, -104, -103, -102, -101, -100, -99, -98, -97, -96, -95, -94, -93, -92, -91, -90, -89, -88, -87, -86, -85, -84, -83, -82, -81, -80, -79, -78, -77, -76, -75, -74, -73, -72, -71, -70, 118, 1, -67, 1, -75, -66, -63, -65, -78, -62, -66, -60, -59, -58, -80, -56, -55, -81, -53, -67, -79, -50, -61, -54, -56, -77, -53, -64, -52, 65, 0, 33, -7, 4, 5, 50, 0, 50, 0, 44, 99, 0, 98, 0, 85, 0, 81, 0, 0, 6, -122, -64, -104, 108, 72, 44, 26, -113, -56, -92, 114, -55, 108, 58, -97, -48, -88, 116, 74, -83, 90, -81, -40, -84, 118, -53, -19, 122, -65, -32, -80, 120, 76, 46, -101, -49, -24, -76, 122, -51, 110, -69, -33, -16, -72, 124, 78, -81, -37, -17, -8, -68, 126, -49, -17, -5, -1, -128, -127, -126, -125, -124, -123, -122, -121, -120, -119, -118, -117, -116, -115, -114, -113, -112, -111, -110, -109, -108, -107, -106, -105, -104, -103, -102, -101, -100, -99, -98, -97, -96, -95, -94, -93, -92, -91, -90, -89, -88, -87, -86, -85, -84, -83, -82, -81, 72, 1, -78, 1, -112, -77, -74, -76, -115, -73, -77, -71, -70, -69, -117, -67, -66, -118, -64, -78, -116, -61, -72, -65, -67, -114, -64, -75, -63, 65, 0, 33, -7, 4, 5, 50, 0, 50, 0, 44, -118, 0, -96, 0, 10, 0, 10, 0, 0, 6, 25, 64, -103, 48, 64, 12, 8, -121, -59, 36, 50, 73, -108, 49, -97, -48, -88, -108, -23, -124, 46, -87, 71, -91, 48, 8, 0, 33, -7, 4, 5, 50, 0, 50, 0, 44, 110, 0, -123, 0, 10, 0, 10, 0, 0, 6, 25, 64, -103, 48, 64, 12, 8, -121, -59, 36, 50, 73, -108, 49, -97, -48, -88, -108, -23, -124, 46, -87, 71, -91, 48, 8, 0, 33, -7, 4, 5, 50, 0, 50, 0, 44, 99, 0, 95, 0, 10, 0, 10, 0, 0, 6, 25, 64, -103, 48, 64, 12, 8, -121, -59, 36, 50, 73, -108, 49, -97, -48, -88, -108, -23, -124, 46, -87, 71, -91, 48, 8, 0, 33, -7, 4, 5, 50, 0, 50, 0, 44, 108, 0, 59, 0, 10, 0, 10, 0, 0, 6, 25, 64, -103, 48, 64, 12, 8, -121, -59, 36, 50, 73, -108, 49, -97, -48, -88, -108, -23, -124, 46, -87, 71, -91, 48, 8, 0, 33, -7, 4, 5, 50, 0, 50, 0, 44, -122, 0, 31, 0, 10, 0, 10, 0, 0, 6, 25, 64, -103, 48, 64, 12, 8, -121, -59, 36, 50, 73, -108, 49, -97, -48, -88, -108, -23, -124, 46, -87, 71, -91, 48, 8, 0, 59 };
    }
    
    public bx(final TWViewer b) {
        this.a = new Vector();
        this.c = new eb(this);
        this.b = b;
    }
    
    public void a(final by by, final cg cg) {
        final int e = dt.e(by.a(a("]~\u007f\tp\u0012en\r")));
        try {
            int n = 0;
            while (true) {
                Label_0075: {
                    if (!g.q) {
                        break Label_0075;
                    }
                    this.a.addElement(new ct(this.b, dt.j(by.a("a" + n + a("\u0012{z\u0014w")))));
                    ++n;
                }
                if (n < e) {
                    continue;
                }
                break;
            }
        }
        catch (Exception ex) {}
        this.c.start();
    }
    
    public int a() {
        return this.a.size();
    }
    
    public ct a(final int n) {
        int n2 = n;
        if (!g.q) {
            if (n < 0) {
                return null;
            }
            n2 = n;
        }
        if (n2 < this.a.size()) {
            return this.a.elementAt(n);
        }
        return null;
    }
    
    public void destroyResource() {
        dt.stopThread(this.c, a("}~\u007f\tp\u001cgt\u0001{\u001c\u007fs\u0012z]o;\u0011jU\u007f"), 100, 0);
        this.c = null;
        for (int size = this.a.size(), i = 0; i < size; ++i) {
            this.a(i).destroyResource();
        }
        this.a.removeAllElements();
        this.a = null;
        this.b = null;
    }
    
    private static String a(final String s) {
        final char[] charArray = s.toCharArray();
        final int length = charArray.length;
        int n = 0;
        while (true) {
            Label_0089: {
                if (length > 1) {
                    break Label_0089;
                }
                char[] array2;
                char[] array = array2 = charArray;
                int n3;
                int n2 = n3 = n;
                while (true) {
                    final char c = array2[n3];
                    char c2 = '\0';
                    switch (n % 5) {
                        case 0: {
                            c2 = '<';
                            break;
                        }
                        case 1: {
                            c2 = '\u000b';
                            break;
                        }
                        case 2: {
                            c2 = '\u001b';
                            break;
                        }
                        case 3: {
                            c2 = '`';
                            break;
                        }
                        default: {
                            c2 = '\u001f';
                            break;
                        }
                    }
                    array[n2] = (char)(c ^ c2);
                    ++n;
                    if (length != 0) {
                        break;
                    }
                    array = (array2 = charArray);
                    n2 = (n3 = length);
                }
            }
            if (n >= length) {
                return new String(charArray);
            }
            continue;
        }
    }
}
