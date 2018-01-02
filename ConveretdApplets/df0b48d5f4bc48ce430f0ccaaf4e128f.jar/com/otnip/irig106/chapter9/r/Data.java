// 
// Decompiled by Procyon v0.5.30
// 

package com.otnip.irig106.chapter9.r;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Data
{
    public String trackNumber;
    public String recordingTechnique;
    public String dataSourceID;
    public String dataDirection;
    public String channelEnable;
    public ChannelDataType channelDataType;
    public Object channelDataTypeAttributes;
    
    public Data() {
        this.trackNumber = "";
        this.recordingTechnique = "";
        this.dataSourceID = "";
        this.dataDirection = "";
        this.channelEnable = "";
        this.channelDataType = ChannelDataType.PCM;
        this.channelDataTypeAttributes = null;
    }
    
    void set(final String input, final String rIndex, final String dataIndex) throws Exception {
        Matcher matcher = Pattern.compile("R-" + rIndex + "\\\\TK1-" + dataIndex + ":(.*);").matcher(input);
        if (matcher.find()) {
            this.trackNumber = matcher.group(1);
        }
        matcher = Pattern.compile("R-" + rIndex + "\\\\TK2-" + dataIndex + ":(.*);").matcher(input);
        if (matcher.find()) {
            this.recordingTechnique = matcher.group(1);
        }
        matcher = Pattern.compile("R-" + rIndex + "\\\\DSI-" + dataIndex + ":(.*);").matcher(input);
        if (matcher.find()) {
            this.dataSourceID = matcher.group(1);
        }
        matcher = Pattern.compile("R-" + rIndex + "\\\\TK3-" + dataIndex + ":(.*);").matcher(input);
        if (matcher.find()) {
            this.dataDirection = matcher.group(1);
        }
        matcher = Pattern.compile("R-" + rIndex + "\\\\CHE-" + dataIndex + ":(.*);").matcher(input);
        if (matcher.find()) {
            this.channelEnable = matcher.group(1);
        }
        matcher = Pattern.compile("R-" + rIndex + "\\\\CDT-" + dataIndex + ":(.*);").matcher(input);
        if (matcher.find()) {
            this.channelDataType = ChannelDataType.valueOfTMATS(matcher.group(1));
        }
        if (this.channelDataType != null) {
            switch (this.channelDataType) {
                case PCM: {
                    final PCMDataTypeAttributes pcmDataTypeAttributes = new PCMDataTypeAttributes();
                    pcmDataTypeAttributes.set(input, rIndex, dataIndex);
                    this.channelDataTypeAttributes = pcmDataTypeAttributes;
                    break;
                }
                case ANALOG: {
                    final AnalogDataTypeAttributes analogTypeAttributes = new AnalogDataTypeAttributes();
                    ((AnalogDataTypeAttributes)(this.channelDataTypeAttributes = analogTypeAttributes)).set(input, rIndex, dataIndex);
                    break;
                }
            }
        }
    }
    
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("DATA\n");
        stringBuilder.append("\tTrack Number/Channel ID:  " + this.trackNumber + "\n");
        stringBuilder.append("\tRecording Technique:  " + this.recordingTechnique + "\n");
        stringBuilder.append("\tData Source ID:  " + this.dataSourceID + "\n");
        stringBuilder.append("\tData Direction:  " + this.dataDirection + "\n");
        stringBuilder.append("\tChannel Enable:  " + this.channelEnable + "\n");
        stringBuilder.append("\tChannelDataType:  " + this.channelDataType);
        return stringBuilder.toString();
    }
    
    public enum ChannelDataType
    {
        PCM("PCMIN", "PCM"), 
        ANALOG("ANAIN", "Analog"), 
        DISCRETE("DISIN", "Discrete"), 
        IRIG_TIME("TIMEIN", "IRIG Time"), 
        VIDEO("VIDIN", "Video"), 
        UART("UARTIN", "UART"), 
        BUS_1553("1553IN", "1553"), 
        ARINC_429("429IN", "ARINC 429"), 
        MESSAGE("MSGIN", "Message"), 
        IMAGE("IMGIN", "Image");
        
        private String tmatsName;
        private String userName;
        
        public static ChannelDataType valueOfTMATS(final String input) {
            for (final ChannelDataType channelDataType : values()) {
                if (channelDataType.tmatsName.equals(input)) {
                    return channelDataType;
                }
            }
            return null;
        }
        
        private ChannelDataType(final String tmatsName, final String userName) {
            this.tmatsName = tmatsName;
            this.userName = userName;
        }
        
        public String toString() {
            return this.userName;
        }
    }
}
