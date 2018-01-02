// 
// Decompiled by Procyon v0.5.30
// 

package ru.zhuk.graphics.codec;

public class c
{
    private int c;
    private int b;
    private int a;
    
    public c() {
        this.a(null);
    }
    
    public boolean a(final byte[] array) {
        this.c = 0;
        final boolean b = true;
        this.a = (b ? 1 : 0);
        this.b = (b ? 1 : 0);
        if (array == null) {
            return false;
        }
        if (array.length < 20) {
            return false;
        }
        if (array[0] != -1 || array[1] != -40) {
            return false;
        }
        if (array[2] != -1 || array[3] != -32) {
            return true;
        }
        if (a(array[4], array[5]) < 16) {
            return true;
        }
        final byte[] array2 = { 74, 70, 73, 70, 0, 1 };
        for (int i = 0; i < array2.length; ++i) {
            if (array2[i] != array[i + 6]) {
                return true;
            }
        }
        final byte c = array[13];
        if (c == 0 || c == 1 || c == 2) {
            this.c = c;
            this.b = a(array[14], array[15]);
            this.a = a(array[16], array[17]);
            return true;
        }
        return true;
    }
    
    public int b() {
        return this.b;
    }
    
    public int c() {
        return this.a;
    }
    
    public int a() {
        return this.c;
    }
    
    public static int a(final byte b, final byte b2) {
        return ((b & 0xFF) << 8) + (b2 & 0xFF);
    }
}
