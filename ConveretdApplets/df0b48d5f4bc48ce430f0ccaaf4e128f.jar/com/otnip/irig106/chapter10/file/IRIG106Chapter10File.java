// 
// Decompiled by Procyon v0.5.30
// 

package com.otnip.irig106.chapter10.file;

import com.otnip.tools.StopWatch;
import com.otnip.irig106.chapter10.packets.TimePacket_Format1;
import com.otnip.irig106.chapter10.tools.RelativeTimeCounter;
import com.otnip.irig106.chapter10.Packet;
import java.io.RandomAccessFile;
import java.io.File;

public class IRIG106Chapter10File
{
    private final File sourceFile;
    private final RandomAccessFile randomAccessFile;
    private IRIG106Chapter10FileInfo info;
    private int currentPacketIndex;
    
    public IRIG106Chapter10File(final File sourceFile) throws Exception {
        this.currentPacketIndex = -1;
        this.sourceFile = sourceFile;
        this.randomAccessFile = new RandomAccessFile(sourceFile, "r");
        this.info = IRIG106Chapter10FileInfoMap.getInfo(sourceFile);
        if (this.info == null) {
            IRIG106Chapter10FileInfoMap.setInfo(sourceFile, this.info = new IRIG106Chapter10FileInfo(sourceFile));
        }
    }
    
    public File getFile() {
        return this.sourceFile;
    }
    
    public int getNumberOfPackets() {
        return this.info.numberOfPackets;
    }
    
    public long getPacketPosition(final int packetIndex) {
        return this.info.fileIndexMap[packetIndex];
    }
    
    public int getCurrentPacketIndex() {
        return this.currentPacketIndex;
    }
    
    public Packet getPacket(final int packetIndex) throws Exception {
        this.randomAccessFile.seek(this.info.fileIndexMap[packetIndex]);
        this.currentPacketIndex = packetIndex;
        return new Packet(this.randomAccessFile);
    }
    
    public RelativeTimeCounter getRelativeTimerCounter(int packetIndex) throws Exception {
        final RelativeTimeCounter timer = new RelativeTimeCounter();
        Packet packet;
        do {
            packet = this.getPacket(packetIndex--);
            if (packet.getHeader().getDataType() == Packet.PacketType.Time_Format1) {
                final TimePacket_Format1 timePacket = (TimePacket_Format1)packet.getPacketBody();
                timer.setReference(timePacket.getIRIGTime(), packet.getHeader().getRelativeTimeCounter());
            }
        } while (packetIndex >= 0 && packet.getHeader().getDataType() != Packet.PacketType.Time_Format1);
        return timer;
    }
    
    public Packet getNextPacket(int packetIndex, final int channelID) throws Exception {
        Packet result;
        Packet packet;
        for (result = null; ++packetIndex < this.info.numberOfPackets && result == null; result = packet) {
            packet = this.getPacket(packetIndex);
            if (packet.getHeader().getChannelID() == channelID) {}
        }
        this.currentPacketIndex = packetIndex;
        return result;
    }
    
    public Packet getPreviousPacket(int packetIndex, final int channelID) throws Exception {
        Packet result;
        Packet packet;
        for (result = null; --packetIndex >= 0 && result == null; result = packet) {
            packet = this.getPacket(packetIndex);
            if (packet.getHeader().getChannelID() == channelID) {}
        }
        this.currentPacketIndex = packetIndex;
        return result;
    }
    
    public Packet getNextPacket(int packetIndex, final Packet.PacketType packetType) throws Exception {
        Packet result;
        Packet packet;
        for (result = null; ++packetIndex < this.info.numberOfPackets && result == null; result = packet) {
            packet = this.getPacket(packetIndex);
            if (packet.getHeader().getDataType() == packetType) {}
        }
        this.currentPacketIndex = packetIndex;
        return result;
    }
    
    public Packet getPreviousPacket(int packetIndex, final Packet.PacketType packetType) throws Exception {
        Packet result;
        Packet packet;
        for (result = null; --packetIndex >= 0 && result == null; result = packet) {
            packet = this.getPacket(packetIndex);
            if (packet.getHeader().getDataType() == packetType) {}
        }
        this.currentPacketIndex = packetIndex;
        return result;
    }
    
    public static void main(final String[] args) {
        try {
            final File file = new File("/home/dlpinto/Desktop/file0004_07022008_23451882_23461801.CH10");
            final IRIG106Chapter10File ch10File = new IRIG106Chapter10File(file);
            System.out.println("{ACKETS:  " + ch10File.info.numberOfPackets);
            final StopWatch sw = new StopWatch();
            sw.start();
            for (int i = 0; i < 8192; ++i) {
                final Packet packet = ch10File.getPacket(i);
            }
            sw.stop();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
