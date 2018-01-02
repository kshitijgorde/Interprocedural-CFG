// 
// Decompiled by Procyon v0.5.30
// 

package com.otnip.irig106.chapter10.packets;

import com.otnip.irig106.chapter10.tools.IRIGChapter10Tools;
import java.util.GregorianCalendar;
import java.util.Date;

public class TimePacket_Format1
{
    private ChannelSpecificDataWord channelSpecificDataWord;
    private byte[] packetBodyData;
    
    public TimePacket_Format1(final byte[] packetBodyData) throws Exception {
        this.channelSpecificDataWord = new ChannelSpecificDataWord();
        this.packetBodyData = packetBodyData;
    }
    
    public double getIRIGTime() {
        long time = 0L;
        time += (this.packetBodyData[5] >> 4 & 0x7) * 10000L;
        time += (this.packetBodyData[5] & 0xF) * 1000L;
        time += (this.packetBodyData[4] >> 4 & 0xF) * 100L;
        time += (this.packetBodyData[4] & 0xF) * 10L;
        time += (this.packetBodyData[7] >> 4 & 0x3) * 36000000L;
        time += (this.packetBodyData[7] & 0xF) * 3600000L;
        time += (this.packetBodyData[6] >> 4 & 0x7) * 600000L;
        time += (this.packetBodyData[6] & 0xF) * 60000L;
        time += (this.packetBodyData[9] & 0x3) * 8640000000L;
        time += (this.packetBodyData[8] >> 4 & 0xF) * 864000000L;
        time += (this.packetBodyData[8] & 0xF) * 86400000L;
        return time * 0.001;
    }
    
    public Date getDate() throws Exception {
        int second = (this.packetBodyData[5] >> 4 & 0x7) * 10;
        second += (this.packetBodyData[5] & 0xF);
        int millisecond = (this.packetBodyData[4] >> 4 & 0xF) * 100;
        millisecond += (this.packetBodyData[4] & 0xF) * 10;
        int hourOfDay = (this.packetBodyData[7] >> 4 & 0xF) * 10;
        hourOfDay += (this.packetBodyData[7] & 0xF);
        int minute = (this.packetBodyData[6] >> 4 & 0xF) * 10;
        minute += (this.packetBodyData[6] & 0xF);
        int month = this.packetBodyData[9] & 0xF;
        month += (this.packetBodyData[9] >> 4 & 0x1) * 10;
        int dayOfMonth = (this.packetBodyData[8] >> 4 & 0xF) * 10;
        dayOfMonth += (this.packetBodyData[8] & 0xF);
        int year = (this.packetBodyData[11] & 0xF) * 100;
        year += (this.packetBodyData[11] >> 4 & 0x3) * 1000;
        year += (this.packetBodyData[10] & 0xF);
        year += (this.packetBodyData[10] >> 4 & 0xF) * 10;
        final GregorianCalendar calendar = new GregorianCalendar(year, month, dayOfMonth, hourOfDay, minute, second);
        final Date date = calendar.getTime();
        date.setTime(date.getTime() + millisecond);
        return date;
    }
    
    public String toString() {
        final StringBuilder string = new StringBuilder();
        string.append("Time Data, Format 1\n");
        string.append(this.channelSpecificDataWord.toString().replace("\n", "\n\t"));
        return string.toString();
    }
    
    public enum TimeSource
    {
        Internal(0, "Internal", "Time derived from the Clock in the Recorder"), 
        External(1, "External", "Time derived from a Clock not in the Recorder"), 
        InternalRMM(2, "Internal from RMM", "Internal Time derived from the Clock in the RMM"), 
        None(15, "None", "None");
        
        private final int id;
        private final String name;
        private final String description;
        
        private TimeSource(final int id, final String name, final String description) {
            this.id = id;
            this.name = name;
            this.description = description;
        }
        
        public static TimeSource valueOf(final int id) {
            TimeSource result = null;
            for (final TimeSource timeSource : values()) {
                if (timeSource.id == id) {
                    result = timeSource;
                }
            }
            return result;
        }
        
        public int getID() {
            return this.id;
        }
        
        public String getName() {
            return this.name;
        }
        
        public String getDescription() {
            return this.description;
        }
    }
    
    public enum TimeFormat
    {
        IRIG_B(0, "IRIG-B", "IRIG-B"), 
        IRIG_A(1, "IRIG-A", "IRIG-A"), 
        IRIG_G(2, "IRIG-G", "IRIG-G"), 
        InteralRealTimeClock(3, "Internal Real-Time Clock", "Internal Real-Time Clock"), 
        UTC_GPS(4, "UTC Time from GPS", "UTC Time from GPS"), 
        NativeGPSTime(5, "Native GPS Time", "Native GPS Time"), 
        None(15, "None", "Time packet payload inactive");
        
        private final int id;
        private final String name;
        private final String description;
        
        private TimeFormat(final int id, final String name, final String description) {
            this.id = id;
            this.name = name;
            this.description = description;
        }
        
        public static TimeFormat valueOf(final int id) {
            TimeFormat result = null;
            for (final TimeFormat timeFormat : values()) {
                if (timeFormat.id == id) {
                    result = timeFormat;
                }
            }
            return result;
        }
        
        public int getID() {
            return this.id;
        }
        
        public String getName() {
            return this.name;
        }
        
        public String getDescription() {
            return this.description;
        }
    }
    
    public class ChannelSpecificDataWord
    {
        public TimeSource getTimeSource() {
            return TimeSource.valueOf(TimePacket_Format1.this.packetBodyData[0] & 0xF);
        }
        
        public TimeFormat getTimeFormat() {
            return TimeFormat.valueOf((TimePacket_Format1.this.packetBodyData[0] & 0xF0) >> 4);
        }
        
        public int getDateFormat() {
            return TimePacket_Format1.this.packetBodyData[1] & 0xF;
        }
        
        public boolean isMonthAndYearAvailable() {
            return (TimePacket_Format1.this.packetBodyData[1] & 0x2) != 0x0;
        }
        
        public String toString() {
            final StringBuilder string = new StringBuilder();
            string.append("Channel Specific Data Word (Time Data Format 1)\n");
            string.append("\tTime Source:  " + this.getTimeSource() + "\n");
            string.append("\tTime Format:  " + this.getTimeFormat() + "\n");
            string.append("\tMonth & Year Available:  " + this.isMonthAndYearAvailable() + "\n");
            string.append("\tIRIG Time:  " + IRIGChapter10Tools.getIRIGString(TimePacket_Format1.this.getIRIGTime()) + "\n");
            return string.toString();
        }
    }
}
