// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.log;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.net.DatagramSocket;
import java.net.SocketAddress;

public final class SyslogSink implements ILogSink
{
    private SocketAddress B;
    private DatagramSocket A;
    
    public SyslogSink() throws SocketException {
        this("127.0.0.1");
    }
    
    public SyslogSink(final String s) throws SocketException {
        this.A = new DatagramSocket();
        this.B = new InetSocketAddress(s, 514);
    }
    
    @Override
    public void log(final Log log, final int n, final Object o) {
        this.A(String.format("<%d>%s", this.A(n), o));
    }
    
    @Override
    public void log(final Log log, final int n, final Throwable t) {
        this.A(String.format("<%d>%s", this.A(n), t.toString()));
    }
    
    @Override
    public void log(final Log log, final int n, final Object o, final Throwable t) {
        final int a = this.A(n);
        this.A(String.format("<%d>%s", a, o));
        this.A(String.format("<%d>%s", a, t.toString()));
    }
    
    private final int A(final int n) {
        int n2 = 0;
        switch (n) {
            case 128: {
                n2 = 9;
                break;
            }
            case 64: {
                n2 = 15;
                break;
            }
            case 32: {
                n2 = 15;
                break;
            }
            case 16: {
                n2 = 14;
                break;
            }
            case 8: {
                n2 = 12;
                break;
            }
            case 4: {
                n2 = 11;
                break;
            }
            case 2: {
                n2 = 10;
                break;
            }
            case 1: {
                n2 = 8;
                break;
            }
        }
        return n2;
    }
    
    private final void A(final String s) {
        try {
            final byte[] bytes = s.getBytes();
            this.A.send(new DatagramPacket(bytes, bytes.length, this.B));
        }
        catch (IOException ex) {
            System.err.println(s);
        }
    }
}
