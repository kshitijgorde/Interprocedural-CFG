// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.commons.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;

public final class DaytimeUDPClient extends DatagramSocketClient
{
    public static final int DEFAULT_PORT = 13;
    private byte[] __dummyData;
    private byte[] __timeData;
    
    public DaytimeUDPClient() {
        this.__dummyData = new byte[1];
        this.__timeData = new byte[256];
    }
    
    public String getTime(final InetAddress host, final int port) throws IOException {
        final DatagramPacket sendPacket = new DatagramPacket(this.__dummyData, this.__dummyData.length, host, port);
        final DatagramPacket receivePacket = new DatagramPacket(this.__timeData, this.__timeData.length);
        this._socket_.send(sendPacket);
        this._socket_.receive(receivePacket);
        return new String(receivePacket.getData(), 0, receivePacket.getLength());
    }
    
    public String getTime(final InetAddress host) throws IOException {
        return this.getTime(host, 13);
    }
}