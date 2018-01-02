// 
// Decompiled by Procyon v0.5.30
// 

package com.otnip.irig106.chapter10.packets;

public class PCMPacket_Format1
{
    private byte[] packetBodyData;
    private ChannelSpecificDataWord channelSpecificDataWord;
    
    public PCMPacket_Format1(final byte[] packetBodyData) {
        this.channelSpecificDataWord = new ChannelSpecificDataWord();
        this.packetBodyData = packetBodyData;
    }
    
    public String toString() {
        final StringBuilder string = new StringBuilder();
        string.append("PCM Data, Format 1\n");
        string.append("\t" + this.channelSpecificDataWord.toString().replace("\n", "\n\t"));
        return string.toString();
    }
    
    public ChannelSpecificDataWord getChannelSpecificDataWord() {
        return this.channelSpecificDataWord;
    }
    
    public enum MinorFrameStatus
    {
        Reserved0(0, "Reserved 0"), 
        Reserved1(1, "Reserved 1"), 
        MinorFrameCheck(2, "Minor Frame Check"), 
        MinorFrameLock(3, "Minor Frame Lock");
        
        private final int id;
        private final String description;
        
        public static MinorFrameStatus valueOf(final int id) {
            for (final MinorFrameStatus minorFrameStatus : values()) {
                if (minorFrameStatus.id == id) {
                    return minorFrameStatus;
                }
            }
            return null;
        }
        
        private MinorFrameStatus(final int id, final String description) {
            this.id = id;
            this.description = description;
        }
        
        public int getID() {
            return this.id;
        }
        
        public String getDescription() {
            return this.description;
        }
    }
    
    public enum MajorFrameStatus
    {
        MinorFramesOnly(0, "Minor Frames Only"), 
        Reserved1(1, "Reserved 1"), 
        MajorFrameCheck(2, "Major Frame Check"), 
        MajorFrameLock(3, "Major Frame Lock");
        
        private final int id;
        private final String description;
        
        public static MajorFrameStatus valueOf(final int id) {
            for (final MajorFrameStatus majorFrameStatus : values()) {
                if (majorFrameStatus.id == id) {
                    return majorFrameStatus;
                }
            }
            return null;
        }
        
        private MajorFrameStatus(final int id, final String description) {
            this.id = id;
            this.description = description;
        }
        
        public int getID() {
            return this.id;
        }
        
        public String getDescription() {
            return this.description;
        }
    }
    
    public class ChannelSpecificDataWord
    {
        public int getSyncOffset() {
            int syncOffset = PCMPacket_Format1.this.packetBodyData[0] & 0xFF;
            syncOffset |= (PCMPacket_Format1.this.packetBodyData[1] & 0xFF) << 8;
            syncOffset |= (PCMPacket_Format1.this.packetBodyData[2] & 0x3) << 16;
            return syncOffset;
        }
        
        public boolean is16BitAligned() {
            return (PCMPacket_Format1.this.packetBodyData[2] & 0x20) == 0x0;
        }
        
        public boolean is32BitAligned() {
            return !this.is16BitAligned();
        }
        
        public boolean isThroughputDataMode() {
            return (PCMPacket_Format1.this.packetBodyData[2] & 0x10) != 0x0;
        }
        
        public boolean isPackedDataMode() {
            return (PCMPacket_Format1.this.packetBodyData[2] & 0x8) != 0x0;
        }
        
        public boolean isUnpackedDataMode() {
            return (PCMPacket_Format1.this.packetBodyData[2] & 0x4) != 0x0;
        }
        
        public MinorFrameStatus getMinorFrameStatus() {
            return MinorFrameStatus.valueOf((PCMPacket_Format1.this.packetBodyData[3] & 0xC) >> 2);
        }
        
        public MajorFrameStatus getMajorFrameStatus() {
            return MajorFrameStatus.valueOf(PCMPacket_Format1.this.packetBodyData[3] & 0x3);
        }
        
        public String toString() {
            final StringBuilder string = new StringBuilder();
            string.append("Channel Specific Data Word (PCM Data, Format 1)\n");
            string.append("\tSyncOffset:  " + this.getSyncOffset() + "\n");
            string.append("\t16-Bit Aligned:  " + this.is16BitAligned() + "\n");
            string.append("\t32-Bit Aligned:  " + this.is32BitAligned() + "\n");
            string.append("\tIs Throughput Mode:  " + this.isThroughputDataMode() + "\n");
            string.append("\tIs Packed:  " + this.isPackedDataMode() + "\n");
            string.append("\tIs UnPacked:  " + this.isUnpackedDataMode() + "\n");
            string.append("\tMinor Frame Status:  " + this.getMinorFrameStatus().getDescription() + "\n");
            string.append("\tMajor Frame Status:  " + this.getMajorFrameStatus().getDescription() + "\n");
            return string.toString();
        }
    }
}
