// 
// Decompiled by Procyon v0.5.30
// 

package com.otnip.irig106.chapter10viewer;

import java.io.RandomAccessFile;
import com.otnip.irig106.chapter10.Packet;

public class PacketViewerWrapper extends Packet
{
    public long packetIndex;
    public double irigTime;
    
    public PacketViewerWrapper(final RandomAccessFile ras, final long packetIndex) throws Exception {
        super(ras);
        this.packetIndex = packetIndex;
    }
}
