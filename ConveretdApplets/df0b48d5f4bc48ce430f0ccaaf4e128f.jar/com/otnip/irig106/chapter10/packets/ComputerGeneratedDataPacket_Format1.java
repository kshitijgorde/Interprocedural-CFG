// 
// Decompiled by Procyon v0.5.30
// 

package com.otnip.irig106.chapter10.packets;

public class ComputerGeneratedDataPacket_Format1
{
    private ChannelSpecificDataWord channelSpecificDataWord;
    private byte[] packetBodyData;
    
    public ComputerGeneratedDataPacket_Format1(final byte[] packetBodyData) throws Exception {
        this.channelSpecificDataWord = new ChannelSpecificDataWord();
        this.packetBodyData = packetBodyData;
    }
    
    public ChannelSpecificDataWord getChannelSpecificDataWord() {
        return this.channelSpecificDataWord;
    }
    
    public String toString() {
        final StringBuilder string = new StringBuilder();
        string.append("Computer Generated Data, Format 1\n");
        string.append(this.channelSpecificDataWord.toString().replace("\n", "\n\t"));
        return string.toString();
    }
    
    public enum Chapter10Version
    {
        IRIG_106_07(7, "IRIG-107 2007"), 
        RESERVED(255, "Reserved");
        
        private final int id;
        private final String description;
        
        private Chapter10Version(final int id, final String description) {
            this.id = id;
            this.description = description;
        }
        
        public static Chapter10Version valueOf(final int id) {
            for (final Chapter10Version version : values()) {
                if (version.id == id) {
                    return version;
                }
            }
            return Chapter10Version.RESERVED;
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
        public boolean getSetupRecordConfigurationChange() {
            return (ComputerGeneratedDataPacket_Format1.this.packetBodyData[1] & 0x1) == 0x1;
        }
        
        public Chapter10Version getChapter10Version() {
            return Chapter10Version.valueOf(ComputerGeneratedDataPacket_Format1.this.packetBodyData[3] & 0xFF);
        }
        
        public String toString() {
            final StringBuilder string = new StringBuilder();
            string.append("Channel Specific Data Word (Computer Generated Data Format 1)\n");
            string.append("\tSetup Record Configuration Change:  " + this.getSetupRecordConfigurationChange() + "\n");
            string.append("\tChapter 10 Version:  " + this.getChapter10Version() + "\n");
            return string.toString();
        }
    }
}
