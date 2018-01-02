// 
// Decompiled by Procyon v0.5.30
// 

package com.otnip.irig106.chapter10.packets;

public class AnalogPacket_Format1
{
    private byte[] packetBodyData;
    private ChannelSpecificDataWord[] channelSpecificDataWords;
    
    public AnalogPacket_Format1(final byte[] packetBodyData) throws Exception {
        this.packetBodyData = packetBodyData;
    }
    
    public int getData(final double[] channelData, final int subchannelID, final boolean signed) throws Exception {
        int totalSamples = 0;
        final ChannelSpecificDataWord[] csdw = this.getChannelSpecificDataWords();
        if (csdw.length > 1) {
            throw new Exception("Don't know how to handle more than 1 CSDW");
        }
        final int totalNumberOfChannels = csdw[0].getTotalChannels();
        if (subchannelID > totalNumberOfChannels) {
            throw new Exception("Request Channel ID Out Of Bounds");
        }
        if (totalSamples % totalNumberOfChannels != 0) {
            throw new Exception("Should Be A Modulos of Two");
        }
        final int bits = csdw[0].getLength();
        final Mode mode = csdw[0].getMode();
        if (bits == 8 && mode == Mode.PACKED) {
            totalSamples = this.packetBodyData.length - 4;
            int currentSampleIndex = 0;
            for (int i = subchannelID + 4; i < this.packetBodyData.length; i += totalNumberOfChannels) {
                final int temp = this.packetBodyData[i] & 0xFF;
                channelData[currentSampleIndex++] = (signed ? ((byte)temp) : temp);
            }
            totalSamples = currentSampleIndex;
        }
        else {
            if (bits != 16 || mode != Mode.PACKED) {
                throw new Exception("Don't know how to handle Format");
            }
            totalSamples = (this.packetBodyData.length - 4) / 2;
            int currentSampleIndex = 0;
            for (int increment = totalNumberOfChannels * 2, j = subchannelID * 2 + 4; j < this.packetBodyData.length; j += increment) {
                int temp2 = (this.packetBodyData[j] & 0xFF) << 8;
                temp2 |= (this.packetBodyData[j + 1] & 0xFF);
                channelData[currentSampleIndex++] = (signed ? ((short)temp2) : temp2);
            }
            totalSamples = currentSampleIndex;
        }
        return totalSamples;
    }
    
    public int getNumberOfSamplingSchedules() throws Exception {
        int currentIndex = 0;
        int samplingScheduleCount = 0;
        while (currentIndex < this.packetBodyData.length) {
            ++samplingScheduleCount;
            final ChannelSpecificDataWord[] csdw = this.getChannelSpecificDataWords();
            currentIndex += 5;
        }
        return samplingScheduleCount;
    }
    
    private int getNumberOfBytesForSamplingSchedule(final ChannelSpecificDataWord[] csdw) throws Exception {
        int totalBits = 0;
        final boolean packed = csdw[0].getMode() == Mode.PACKED;
        for (int i = 0; i < csdw.length; ++i) {
            if (packed) {
                totalBits += csdw[i].getLength();
            }
        }
        int words = 0;
        if (packed) {
            words = totalBits / 16;
            if (totalBits % 16 != 0) {
                ++words;
            }
            return words * 2;
        }
        throw new Exception("NOT HANLDING UNPACKED SAMPLING SCHEDULES");
    }
    
    public ChannelSpecificDataWord[] getChannelSpecificDataWords() throws Exception {
        if (this.channelSpecificDataWords == null) {
            final int offset = 0;
            final ChannelSpecificDataWord csdw = new ChannelSpecificDataWord(0);
            int N = 1;
            if (csdw.getTotalChannels() > 1 && !csdw.isSame()) {
                N = csdw.getTotalChannels();
            }
            (this.channelSpecificDataWords = new ChannelSpecificDataWord[N])[0] = csdw;
            for (int i = 1; i < N; ++i) {
                this.channelSpecificDataWords[i] = new ChannelSpecificDataWord(i);
            }
        }
        return this.channelSpecificDataWords;
    }
    
    public String toString() {
        final StringBuilder string = new StringBuilder();
        string.append("PCM Data, Format 1\n");
        try {
            final ChannelSpecificDataWord[] arr$;
            final ChannelSpecificDataWord[] csdws = arr$ = this.getChannelSpecificDataWords();
            for (final ChannelSpecificDataWord csdw : arr$) {
                try {
                    string.append(csdw.toString().replace("\n", "\n\t"));
                }
                catch (Exception e) {
                    string.append("Error Parsing CSDW\n");
                    e.printStackTrace();
                }
            }
        }
        catch (Exception e2) {
            string.append("EROOR Generating CSDW\n");
            e2.printStackTrace();
        }
        return string.toString();
    }
    
    public enum Mode
    {
        PACKED(0, "Data is packed"), 
        UNPACKED_LSB_PADDED(1, "Data is unpacked, LSB Padded"), 
        UNPACKED_MSB_PADDED(3, "Data is unpacked, MSB Padded");
        
        private final int id;
        private final String description;
        
        private Mode(final int id, final String description) {
            this.id = id;
            this.description = description;
        }
        
        public static Mode valueOf(final int id) {
            for (final Mode mode : values()) {
                if (mode.id == id) {
                    return mode;
                }
            }
            return null;
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
        private int wordNumber;
        
        private ChannelSpecificDataWord(final int wordNumber) {
            this.wordNumber = wordNumber;
        }
        
        public Mode getMode() {
            return Mode.values()[AnalogPacket_Format1.this.packetBodyData[this.wordNumber * 4] & 0x3];
        }
        
        public int getLength() {
            int bits = (AnalogPacket_Format1.this.packetBodyData[this.wordNumber * 4] & 0xFC) >> 2;
            if (bits == 0) {
                bits = 64;
            }
            return bits;
        }
        
        public int getSubchannel() {
            return AnalogPacket_Format1.this.packetBodyData[1 + this.wordNumber * 4] & 0xFF;
        }
        
        public int getTotalChannels() {
            return AnalogPacket_Format1.this.packetBodyData[2 + this.wordNumber * 4] & 0xFF;
        }
        
        public int getFactor() {
            final int exponent = AnalogPacket_Format1.this.packetBodyData[4 + this.wordNumber * 4] & 0xF;
            return (int)Math.pow(2.0, exponent);
        }
        
        public boolean isSame() {
            return (AnalogPacket_Format1.this.packetBodyData[4 + this.wordNumber * 4] & 0x10) != 0x0;
        }
        
        public String toString() {
            final StringBuilder string = new StringBuilder();
            string.append("Analog Channel Specific Data Word\n");
            string.append("\tWord Number:  " + this.wordNumber + "\n");
            string.append("\tMode:  " + this.getMode() + "\n");
            string.append("\tLength:  " + this.getLength() + "\n");
            string.append("\tSubChannel:  " + this.getSubchannel() + "\n");
            string.append("\tTotal Channels: " + this.getTotalChannels() + "\n");
            string.append("\tFactor:  " + this.getFactor() + "\n");
            string.append("\tSame:  " + this.isSame() + "\n");
            return string.toString();
        }
    }
}
