// 
// Decompiled by Procyon v0.5.30
// 

package a.a.b.a;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.DataOutputStream;
import java.io.Serializable;
import java.awt.image.RGBImageFilter;

public class a extends RGBImageFilter implements Serializable
{
    private int if;
    private int[] for;
    private int[] do;
    private int[] a;
    
    public a() {
        this.if = 0;
        this.for = new int[256];
        this.do = new int[256];
        this.a = new int[256];
    }
    
    public void a() {
        System.out.println("Verifi Lookup Table");
        System.out.println("Size : " + this.if);
        if (this.if > 0) {
            System.out.println("Index\tR\tG\tB\n");
            for (int i = 0; i < 256; ++i) {
                System.out.println(String.valueOf(i) + "\t" + this.for[i] + "\t" + this.do[i] + "\t" + this.a[i]);
            }
        }
    }
    
    public int filterRGB(final int n, final int n2, final int n3) {
        return (this.if == 0) ? n3 : (0xFF000000 | this.for[(n3 & 0xFF0000) >> 16] << 16 | this.do[(n3 & 0xFF00) >> 8] << 8 | this.a[n3 & 0xFF]);
    }
    
    public int if() {
        return this.if;
    }
    
    public void a(final DataOutputStream dataOutputStream) {
        try {
            if (this.if > 0 && this.if <= 256) {
                dataOutputStream.writeInt(this.if);
                for (int i = 0; i < this.if; ++i) {
                    dataOutputStream.writeByte(this.for[i] & 0xFF);
                    dataOutputStream.writeByte(this.do[i] & 0xFF);
                    dataOutputStream.writeByte(this.a[i] & 0xFF);
                }
            }
            else {
                dataOutputStream.writeInt(0);
            }
        }
        catch (IOException ex) {}
    }
    
    public void do() {
        this.a(0);
    }
    
    public void a(final byte[] array, final byte[] array2, final byte[] array3) {
        final int length = array.length;
        this.if = length;
        if (length > 0) {
            for (int i = 0; i < this.if; ++i) {
                this.for[i] = (array[i] & 0xFF);
                this.do[i] = (array2[i] & 0xFF);
                this.a[i] = (array3[i] & 0xFF);
            }
        }
    }
    
    public void a(final int[] array, final int[] array2, final int[] array3) {
        final int length = array.length;
        this.if = length;
        if (length > 0) {
            for (int i = 0; i < this.if; ++i) {
                this.for[i] = (array[i] & 0xFF);
                this.do[i] = (array2[i] & 0xFF);
                this.a[i] = (array3[i] & 0xFF);
            }
        }
    }
    
    public void a(final int if1) {
        this.if = if1;
    }
    
    public int a(final DataInputStream dataInputStream) {
        try {
            this.if = dataInputStream.readInt();
            if (this.if > 0 && this.if <= 256) {
                for (int i = 0; i < this.if; ++i) {
                    this.for[i] = (dataInputStream.readByte() & 0xFF);
                    this.do[i] = (dataInputStream.readByte() & 0xFF);
                    this.a[i] = (dataInputStream.readByte() & 0xFF);
                }
            }
        }
        catch (IOException ex) {
            this.if = 0;
        }
        return this.if;
    }
}
