// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.commons.net.tftp;

import java.net.DatagramPacket;
import java.net.InetAddress;

public final class TFTPWriteRequestPacket extends TFTPRequestPacket
{
    public TFTPWriteRequestPacket(final InetAddress destination, final int port, final String filename, final int mode) {
        super(destination, port, 2, filename, mode);
    }
    
    TFTPWriteRequestPacket(final DatagramPacket datagram) throws TFTPPacketException {
        super(2, datagram);
    }
}
