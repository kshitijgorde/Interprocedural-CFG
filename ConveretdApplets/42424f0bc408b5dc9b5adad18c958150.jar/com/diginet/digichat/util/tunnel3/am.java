// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.util.tunnel3;

import java.net.MalformedURLException;
import java.net.URLConnection;
import java.io.FileNotFoundException;
import java.io.DataInputStream;
import java.net.URL;
import java.io.OutputStream;
import java.io.DataOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Vector;

public class am implements an, Runnable
{
    private String a;
    private boolean b;
    private Vector c;
    private long d;
    private long e;
    
    public bv a() throws IOException {
        synchronized (this) {
            if (!this.b) {
                this.c();
            }
        }
        while (this.b) {
            synchronized (this.c) {
                if (this.c.size() > 0) {
                    if (this.e != -1L) {
                        System.out.println(this.e + " got message");
                    }
                    final bv bv = this.c.elementAt(0);
                    this.c.removeElementAt(0);
                    // monitorexit(this.c)
                    return bv;
                }
            }
            // monitorexit(this.c)
            try {
                synchronized (this.c) {
                    this.c.wait();
                }
                // monitorexit(this.c)
            }
            catch (InterruptedException ex) {}
        }
        throw new IOException("HttpTunnelingClientModule: receive failed due to thread stop");
    }
    
    public void a(final bv bv) throws IOException {
        if (bv == null) {
            throw new NullPointerException("HttpTunnelingClientModule: send called with null Message.");
        }
        if (this.e != -1L) {
            System.out.println(this.e + " sent message");
        }
        synchronized (this) {
            if (!this.b) {
                this.c();
            }
        }
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        dataOutputStream.writeLong(this.d);
        dataOutputStream.writeInt(1);
        final byte[] a = bv.a();
        dataOutputStream.writeInt(a.length);
        dataOutputStream.write(a);
        final byte[] byteArray = byteArrayOutputStream.toByteArray();
        boolean b = false;
        while (!b) {
            try {
                final URLConnection openConnection = new URL(this.a).openConnection();
                openConnection.setDoOutput(true);
                openConnection.setUseCaches(false);
                openConnection.setRequestProperty("Content-type", "application/octet-stream");
                openConnection.setRequestProperty("Content-length", String.valueOf(byteArray.length));
                final DataOutputStream dataOutputStream2 = new DataOutputStream(openConnection.getOutputStream());
                dataOutputStream2.write(byteArray);
                dataOutputStream2.flush();
                dataOutputStream2.close();
                try {
                    new DataInputStream(openConnection.getInputStream()).close();
                }
                catch (Exception ex) {}
            }
            catch (FileNotFoundException ex2) {
                continue;
            }
            b = true;
        }
    }
    
    public void run() {
        while (this.b) {
            try {
                new Double(Math.random()).toString();
                final URLConnection openConnection = new URL(this.a + "?clientID=" + this.d).openConnection();
                openConnection.setDoInput(true);
                openConnection.setUseCaches(false);
                final DataInputStream dataInputStream = new DataInputStream(openConnection.getInputStream());
                for (int int1 = dataInputStream.readInt(), i = 0; i < int1; ++i) {
                    if (this.e != -1L) {
                        System.out.println(this.e + " queued message");
                    }
                    final byte[] array = new byte[dataInputStream.readInt()];
                    dataInputStream.readFully(array);
                    this.c.addElement(new bv(array));
                    synchronized (this.c) {
                        this.c.notify();
                    }
                    // monitorexit(this.c)
                }
                dataInputStream.close();
            }
            catch (FileNotFoundException ex2) {
                continue;
            }
            catch (IOException ex) {
                ex.printStackTrace();
                this.b();
                continue;
            }
            try {
                Thread.sleep(2000L);
            }
            catch (InterruptedException ex3) {}
        }
        if (this.e != -1L) {
            System.out.println("Network module (http) cancelled");
        }
    }
    
    public synchronized void b() {
        this.b = false;
        synchronized (this.c) {
            this.c.notifyAll();
        }
        // monitorexit(this.c)
    }
    
    private final void c() {
        this.b = true;
        new Thread(this, "HttpTunnelingClientModule Thread").start();
    }
    
    public am(final String a, final long d) throws MalformedURLException {
        this.a = null;
        this.b = false;
        this.c = null;
        this.d = -1L;
        this.e = -1L;
        if (a == null) {
            throw new NullPointerException("HttpTunnelingClientModule: constructor called with null host name.");
        }
        this.d = d;
        this.c = new Vector();
        this.a = a;
        new URL(a);
    }
}
