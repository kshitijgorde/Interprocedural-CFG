// 
// Decompiled by Procyon v0.5.30
// 

package com.screencastomatic.play.a;

import java.util.concurrent.LinkedBlockingQueue;
import javax.sound.sampled.SourceDataLine;

public class a implements Runnable
{
    private SourceDataLine a;
    private LinkedBlockingQueue b;
    private byte[] c;
    private Runnable d;
    private boolean e;
    private boolean f;
    private int g;
    private long h;
    private int i;
    
    protected a(final SourceDataLine a) {
        this.b = new LinkedBlockingQueue();
        this.c = new byte[0];
        this.e = false;
        this.f = false;
        this.g = 0;
        this.h = 0L;
        this.i = 0;
        this.a = a;
    }
    
    public void a(final byte[] array) {
        if (array == null) {
            return;
        }
        if (array.length == 0) {
            System.out.println("Ignoring audio with 0 bytes.");
            return;
        }
        this.g += array.length;
        this.b.add(array);
    }
    
    public void a(final int i) {
        this.i = i;
    }
    
    public long a() {
        return this.h;
    }
    
    public int b() {
        return this.g;
    }
    
    public int c() {
        return this.b.size();
    }
    
    public void a(final boolean e) {
        this.e = e;
    }
    
    public void d() {
        this.b.clear();
        this.b.add(this.c);
    }
    
    public synchronized void a(final Runnable d) {
        this.d = d;
        if (!this.f) {
            System.out.println("Not done so adding kill for audio.");
            this.b.add(this.c);
            this.e = false;
        }
        else {
            System.out.println("Done already so running callback now.");
            d.run();
        }
    }
    
    public void run() {
        try {
            int n = 1;
            byte[] array;
            while (this.a.isOpen() && (array = this.b.take()) != this.c) {
                int n2 = 0;
                if (this.i > 1 || this.i < -1) {
                    int n3;
                    for (n3 = ((this.i > 0) ? (array.length / this.i) : (array.length * -this.i)); n3 % Math.abs(this.i) != 0; --n3) {}
                    n2 = array.length - n3;
                    final byte[] array2 = new byte[n3];
                    if (this.i > 0) {
                        for (int i = 0, n4 = 0; i < array2.length; i += this.i, n4 += this.i * 2) {
                            for (int j = 0; j < this.i; ++j) {
                                array2[i + j] = array[n4 + j];
                            }
                        }
                    }
                    else {
                        int n5 = 0;
                        for (int k = 0; k < array.length; k += 2) {
                            array2[n5++] = array[k];
                            array2[n5++] = array[k + 1];
                            array2[n5++] = array[k];
                            array2[n5++] = array[k + 1];
                        }
                    }
                    array = array2;
                }
                this.g -= array.length;
                if (n == 0 && this.a.getBufferSize() - this.a.available() == 0) {
                    System.out.println("Audio Clipping!");
                }
                n = 0;
                int n6 = 0;
                while (this.a.isOpen() && n6 < array.length) {
                    n6 += this.a.write(array, n6, array.length - n6);
                    if (n6 < array.length) {
                        Thread.sleep(10L);
                    }
                }
                this.h += n2;
                if (n6 < array.length) {
                    System.out.println("Failed to write all buffer: " + array.length + " (written: " + n6 + ")");
                }
                while (this.a.isOpen() && this.e) {
                    Thread.sleep(10L);
                }
            }
            synchronized (this) {
                this.f = true;
                if (this.d != null) {
                    if (this.a.isRunning()) {
                        this.a.drain();
                    }
                    this.d.run();
                }
            }
        }
        catch (InterruptedException ex) {
            System.out.println("Audio play thread inturupted.");
        }
        System.out.println("Audio play thread is exiting.");
    }
}
