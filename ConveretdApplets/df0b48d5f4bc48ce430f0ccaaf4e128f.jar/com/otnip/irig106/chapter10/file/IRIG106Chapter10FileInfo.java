// 
// Decompiled by Procyon v0.5.30
// 

package com.otnip.irig106.chapter10.file;

import com.otnip.irig106.chapter10.Packet;
import java.util.ArrayList;
import java.io.RandomAccessFile;
import java.io.File;
import java.io.Serializable;

class IRIG106Chapter10FileInfo implements Serializable
{
    private static final long serialVersionUID = 0L;
    private static final int BLOCK_SIZE = 1;
    final File file;
    final long lastModified;
    final int numberOfPackets;
    final long[] fileIndexMap;
    
    IRIG106Chapter10FileInfo(final File file) throws Exception {
        this.file = file;
        this.lastModified = file.lastModified();
        final RandomAccessFile ras = new RandomAccessFile(file, "r");
        final long fileLength = file.length();
        final ArrayList<Long> position = new ArrayList<Long>();
        position.add(0L);
        int packetIndex = 0;
        final Packet packet = new Packet();
        try {
            while (ras.getFilePointer() < fileLength) {
                for (int i = 0; i < 1; ++i) {
                    packet.read(ras);
                    ++packetIndex;
                }
                position.add(ras.getFilePointer());
            }
        }
        catch (Exception ex) {
            System.err.println("Closed:  " + ras.getFilePointer() + " vs " + file.length());
        }
        ras.close();
        this.numberOfPackets = packetIndex;
        this.fileIndexMap = new long[position.size()];
        for (int i = 0; i < this.fileIndexMap.length; ++i) {
            this.fileIndexMap[i] = position.get(i);
        }
    }
}
