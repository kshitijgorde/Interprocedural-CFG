// 
// Decompiled by Procyon v0.5.30
// 

package com.otnip.irig106.chapter10;

import com.otnip.irig106.chapter10.packets.AnalogPacket_Format1;
import com.otnip.irig106.chapter10.packets.PCMPacket_Format1;
import com.otnip.irig106.chapter10.packets.TimePacket_Format1;
import com.otnip.irig106.chapter10.packets.ComputerGeneratedDataPacket_Format1;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.io.DataInputStream;
import java.io.RandomAccessFile;
import java.io.InputStream;

public class Packet
{
    private final PacketHeader packetHeader;
    private byte[] packetBodyData;
    private int checksum;
    
    public Packet() {
        this.packetHeader = new PacketHeader();
    }
    
    public Packet(final InputStream is) throws Exception {
        this.packetHeader = new PacketHeader();
        this.read(is);
    }
    
    public Packet(final RandomAccessFile ras) throws Exception {
        this.packetHeader = new PacketHeader();
        this.read(ras);
    }
    
    public void read(final InputStream is) throws Exception {
        final DataInputStream dis = new DataInputStream(is);
        this.packetHeader.read(is);
        final int packetLength = this.packetHeader.getPacketLength();
        final int dataLength = this.packetHeader.getDataLength();
        this.packetBodyData = new byte[dataLength];
        int fillerBytes = packetLength - dataLength - this.packetHeader.getDataCRCLength() - 24;
        if (this.packetHeader.hasSecondaryHeader()) {
            fillerBytes -= 12;
            throw new Exception("SECONDARY HEADER NOT SUPPORTEED");
        }
        try {
            dis.readFully(this.packetBodyData);
        }
        catch (Exception e) {
            throw new Exception("Error Reading Packet Body");
        }
        if (dis.skipBytes(fillerBytes) != fillerBytes) {
            throw new Exception("Could Not Skip Header");
        }
        switch (this.packetHeader.getDataCRC()) {
            case 0: {
                break;
            }
            case 1: {
                this.checksum = (is.read() & 0xFF);
                break;
            }
            case 2: {
                this.checksum |= (is.read() & 0xFF) << 8;
                this.checksum |= (is.read() & 0xFF);
                break;
            }
            case 3: {
                this.checksum |= (is.read() & 0xFF) << 24;
                this.checksum |= (is.read() & 0xFF) << 16;
                this.checksum |= (is.read() & 0xFF) << 8;
                this.checksum |= (is.read() & 0xFF);
                break;
            }
            default: {
                throw new Exception("Checksum Specification Error");
            }
        }
    }
    
    public void read(final ByteBuffer byteBuffer) throws Exception {
        this.packetHeader.read(byteBuffer);
        final int packetLength = this.packetHeader.getPacketLength();
        final int dataLength = this.packetHeader.getDataLength();
        this.packetBodyData = new byte[dataLength];
        int fillerBytes = packetLength - dataLength - this.packetHeader.getDataCRCLength() - 24;
        if (this.packetHeader.hasSecondaryHeader()) {
            fillerBytes -= 12;
            throw new Exception("SECONDARY HEADER NOT SUPPORTEED");
        }
        byteBuffer.get(this.packetBodyData);
        byteBuffer.position(byteBuffer.position() + fillerBytes);
        switch (this.packetHeader.getDataCRC()) {
            case 0: {
                break;
            }
            case 1: {
                this.checksum = (byteBuffer.get() & 0xFF);
                break;
            }
            case 2: {
                this.checksum |= (byteBuffer.get() & 0xFF) << 8;
                this.checksum |= (byteBuffer.get() & 0xFF);
                break;
            }
            case 3: {
                this.checksum |= (byteBuffer.get() & 0xFF) << 24;
                this.checksum |= (byteBuffer.get() & 0xFF) << 16;
                this.checksum |= (byteBuffer.get() & 0xFF) << 8;
                this.checksum |= (byteBuffer.get() & 0xFF);
                break;
            }
            default: {
                throw new Exception("Checksum Specification Error");
            }
        }
    }
    
    public void read(final RandomAccessFile ras) throws Exception {
        this.packetHeader.read(ras);
        final int packetLength = this.packetHeader.getPacketLength();
        final int dataLength = this.packetHeader.getDataLength();
        this.packetBodyData = new byte[dataLength];
        int fillerBytes = packetLength - dataLength - this.packetHeader.getDataCRCLength() - 24;
        if (this.packetHeader.hasSecondaryHeader()) {
            fillerBytes -= 12;
            throw new Exception("SECONDARY HEADER NOT SUPPORTEED");
        }
        ras.readFully(this.packetBodyData);
        if (ras.skipBytes(fillerBytes) != fillerBytes) {
            throw new Exception("Could Not Skip Right Amount Of Bytes");
        }
        switch (this.packetHeader.getDataCRC()) {
            case 0: {
                break;
            }
            case 1: {
                this.checksum = (ras.read() & 0xFF);
                break;
            }
            case 2: {
                this.checksum |= (ras.read() & 0xFF) << 8;
                this.checksum |= (ras.read() & 0xFF);
                break;
            }
            case 3: {
                this.checksum |= (ras.read() & 0xFF) << 24;
                this.checksum |= (ras.read() & 0xFF) << 16;
                this.checksum |= (ras.read() & 0xFF) << 8;
                this.checksum |= (ras.read() & 0xFF);
                break;
            }
            default: {
                throw new Exception("Checksum Specification Error");
            }
        }
    }
    
    public void write(final OutputStream os) throws Exception {
        this.packetHeader.write(os);
        os.write(this.packetBodyData);
        for (int fillerBytes = this.packetHeader.getPacketLength() - this.packetHeader.getDataLength() - this.packetHeader.getDataCRCLength() - 24, i = 0; i < fillerBytes; ++i) {
            os.write(0);
        }
        switch (this.packetHeader.getDataCRC()) {
            case 0: {
                break;
            }
            case 1: {
                os.write(this.checksum & 0xFF);
                break;
            }
            case 2: {
                os.write(this.checksum >> 8 & 0xFF);
                os.write(this.checksum & 0xFF);
                break;
            }
            case 3: {
                os.write(this.checksum >> 24 & 0xFF);
                os.write(this.checksum >> 16 & 0xFF);
                os.write(this.checksum >> 8 & 0xFF);
                os.write(this.checksum & 0xFF);
                break;
            }
            default: {
                throw new Exception("Checksum Specification Error");
            }
        }
    }
    
    public PacketHeader getHeader() {
        return this.packetHeader;
    }
    
    public Object getPacketBody() throws Exception {
        switch (this.packetHeader.getDataType()) {
            case ComputerGenerated_Format1: {
                return new ComputerGeneratedDataPacket_Format1(this.packetBodyData);
            }
            case Time_Format1: {
                return new TimePacket_Format1(this.packetBodyData);
            }
            case PCM_Format1: {
                return new PCMPacket_Format1(this.packetBodyData);
            }
            case Analog_Format1: {
                return new AnalogPacket_Format1(this.packetBodyData);
            }
            default: {
                throw new Exception("Cannot Parse Data Type:  " + this.packetHeader.getDataType());
            }
        }
    }
    
    public byte[] getPacketBodyData() {
        return this.packetBodyData;
    }
    
    public void setPacketBodyData(final byte[] packetBodyData) throws Exception {
        this.packetHeader.setDataLength(packetBodyData.length);
        this.packetHeader.setPacketLength(this.packetHeader.getPacketLength() - this.packetBodyData.length + packetBodyData.length);
        this.packetHeader.setDataCRC(0);
        this.packetBodyData = packetBodyData;
    }
    
    public enum PacketType
    {
        ComputerGenerated_Format0(0, "Computer Generated Data, Format 0", "User Defined"), 
        ComputerGenerated_Format1(1, "Computer Generated Data, Format 1", "Setup Record"), 
        ComputerGenerated_Format2(2, "Computer Generated Data, Format 2", "Recording Events"), 
        ComputerGenerated_Format3(3, "Computer Generated Data, Format 3", "Recording Index"), 
        ComputerGenerated_Format4(4, "Computer Generated Data, Format 4", "Reserved for future use"), 
        ComputerGenerated_Format5(5, "Computer Generated Data, Format 5", "Reserved for future use"), 
        ComputerGenerated_Format6(6, "Computer Generated Data, Format 6", "Reserved for future use"), 
        ComputerGenerated_Format7(7, "Computer Generated Data, Format 7", "Reserved for future use"), 
        PCM_Format0(8, "PCM Data, Format 0", "Reserved for future use"), 
        PCM_Format1(9, "PCM Data, Format 1", "IRIG 106 Chapter 4"), 
        PCM_Format2(10, "PCM Data, Format 2", "Reserved For Future Use"), 
        PCM_Format3(11, "PCM Data, Format 3", "Reserved For Future Use"), 
        PCM_Format4(12, "PCM Data, Format 4", "Reserved For Future Use"), 
        PCM_Format5(13, "PCM Data, Format 5", "Reserved For Future Use"), 
        PCM_Format6(14, "PCM Data, Format 6", "Reserved For Future Use"), 
        PCM_Format7(15, "PCM Data, Format 7", "Reserved For Future Use"), 
        Time_Format0(16, "Time Data, Format 0", "Reserved For Future Use"), 
        Time_Format1(17, "Time Data, Format 1", "IRIG/GPS/RTC"), 
        Time_Format2(18, "Time Data, Format 2", "Reserved For Future Use"), 
        Time_Format3(19, "Time Data, Format 3", "Reserved For Future Use"), 
        Time_Format4(20, "Time Data, Format 4", "Reserved For Future Use"), 
        Time_Format5(21, "Time Data, Format 5", "Reserved For Future Use"), 
        Time_Format6(22, "Time Data, Format 6", "Reserved For Future Use"), 
        Time_Format7(23, "Time Data, Format 7", "Reserved For Future Use"), 
        MILSTD1553_Format0(24, "MIL-STD-1553 Data, Format 0", "Reserved For Future Use"), 
        MILSTD1553_Format1(25, "MIL-STD-1553 Data, Format 1", "Mil-Std-1553B Data"), 
        MILSTD1553_Format2(26, "MIL-STD-1553 Data, Format 2", "Reserved For Future Use"), 
        MILSTD1553_Format3(27, "MIL-STD-1553 Data, Format 3", "Reserved For Future Use"), 
        MILSTD1553_Format4(28, "MIL-STD-1553 Data, Format 4", "Reserved For Future Use"), 
        MILSTD1553_Format5(29, "MIL-STD-1553 Data, Format 5", "Reserved For Future Use"), 
        MILSTD1553_Format6(30, "MIL-STD-1553 Data, Format 6", "Reserved For Future Use"), 
        MILSTD1553_Format7(31, "MIL-STD-1553 Data, Format 7", "Reserved For Future Use"), 
        Analog_Format0(32, "Analog Data, Format 0", "Reserved For Future Use"), 
        Analog_Format1(33, "Analog Data, Format 1", "Analog Data"), 
        Analog_Format2(34, "Analog Data, Format 2", "Reserved For Future Use"), 
        Analog_Format3(35, "Analog Data, Format 3", "Reserved For Future Use"), 
        Analog_Format4(36, "Analog Data, Format 4", "Reserved For Future Use"), 
        Analog_Format5(37, "Analog Data, Format 5", "Reserved For Future Use"), 
        Analog_Format6(38, "Analog Data, Format 6", "Reserved For Future Use"), 
        Analog_Format7(39, "Analog Data, Format 7", "Reserved For Future Use"), 
        Discrete_Format0(40, "Discrete Data, Format 0", "Reserved For Future Use"), 
        Discrete_Format1(41, "Discrete Data, Format 1", "Discrete Data"), 
        Discrete_Format2(42, "Discrete Data, Format 2", "Reserved For Future Use"), 
        Discrete_Format3(43, "Discrete Data, Format 3", "Reserved For Future Use"), 
        Discrete_Format4(44, "Discrete Data, Format 4", "Reserved For Future Use"), 
        Discrete_Format5(45, "Discrete Data, Format 5", "Reserved For Future Use"), 
        Discrete_Format6(46, "Discrete Data, Format 6", "Reserved For Future Use"), 
        Discrete_Format7(47, "Discrete Data, Format 7", "Reserved For Future Use"), 
        Message_Format0(48, "Message Data, Format 0", "Generic Message Data"), 
        Message_Format1(49, "Message Data, Format 1", "Reserved For Future Use"), 
        Message_Format2(50, "Message Data, Format 2", "Reserved For Future Use"), 
        Message_Format3(51, "Message Data, Format 3", "Reserved For Future Use"), 
        Message_Format4(52, "Message Data, Format 4", "Reserved For Future Use"), 
        Message_Format5(53, "Message Data, Format 5", "Reserved For Future Use"), 
        Message_Format6(54, "Message Data, Format 6", "Reserved For Future Use"), 
        Message_Format7(55, "Message Data, Format 7", "Reserved For Future Use"), 
        ARINC429_Format0(56, "ARINC 429 Data, Format 0", "ARINC429 Data"), 
        ARINC429_Format1(57, "ARINC 429 Data, Format 1", "Reserved For Future Use"), 
        ARINC429_Format2(58, "ARINC 429 Data, Format 2", "Reserved For Future Use"), 
        ARINC429_Format3(59, "ARINC 429 Data, Format 3", "Reserved For Future Use"), 
        ARINC429_Format4(60, "ARINC 429 Data, Format 4", "Reserved For Future Use"), 
        ARINC429_Format5(61, "ARINC 429 Data, Format 5", "Reserved For Future Use"), 
        ARINC429_Format6(62, "ARINC 429 Data, Format 6", "Reserved For Future Use"), 
        ARINC429_Format7(63, "ARINC 429 Data, Format 7", "Reserved For Future Use"), 
        Video_Format0(64, "Video Data, Format 0", "MPEG-2 Video"), 
        Video_Format1(65, "Video Data, Format 1", "ISO 13818-1 MPEG-2 Bit Stream"), 
        Video_Format2(66, "Video Data, Format 2", "Reserved For Future Use"), 
        Video_Format3(67, "Video Data, Format 3", "Reserved For Future Use"), 
        Video_Format4(68, "Video Data, Format 4", "Reserved For Future Use"), 
        Video_Format5(69, "Video Data, Format 5", "Reserved For Future Use"), 
        Video_Format6(70, "Video Data, Format 6", "Reserved For Future Use"), 
        Video_Format7(71, "Video Data, Format 7", "Reserved For Future Use"), 
        Image_Format0(72, "Image Data, Format 0", "Image Data"), 
        Image_Format1(73, "Image Data, Format 1", "Reserved For Future Use"), 
        Image_Format2(74, "Image Data, Format 2", "Reserved For Future Use"), 
        Image_Format3(75, "Image Data, Format 3", "Reserved For Future Use"), 
        Image_Format4(76, "Image Data, Format 4", "Reserved For Future Use"), 
        Image_Format5(77, "Image Data, Format 5", "Reserved For Future Use"), 
        Image_Format6(78, "Image Data, Format 6", "Reserved For Future Use"), 
        Image_Format7(79, "Image Data, Format 7", "Reserved For Future Use"), 
        UART_Format0(80, "UART Data, Format 0", "UART Data"), 
        UART_Format1(81, "UART Data, Format 1", "Reserved For Future Use"), 
        UART_Format2(82, "UART Data, Format 2", "Reserved For Future Use"), 
        UART_Format3(83, "UART Data, Format 3", "Reserved For Future Use"), 
        UART_Format4(84, "UART Data, Format 4", "Reserved For Future Use"), 
        UART_Format5(85, "UART Data, Format 5", "Reserved For Future Use"), 
        UART_Format6(86, "UART Data, Format 6", "Reserved For Future Use"), 
        UART_Format7(87, "UART Data, Format 7", "Reserved For Future Use"), 
        IEEE1394_Format0(88, "IEEE-1394 Fire Wire Data, Format 0", "IEEE-1394 Data"), 
        IEEE1394_Format1(89, "IEEE-1394 Fire Wire Data, Format 1", "Reserved For Future Use"), 
        IEEE1394_Format2(90, "IEEE-1394 Fire Wire Data, Format 2", "Reserved For Future Use"), 
        IEEE1394_Format3(91, "IEEE-1394 Fire Wire Data, Format 3", "Reserved For Future Use"), 
        IEEE1394_Format4(92, "IEEE-1394 Fire Wire Data, Format 4", "Reserved For Future Use"), 
        IEEE1394_Format5(93, "IEEE-1394 Fire Wire Data, Format 5", "Reserved For Future Use"), 
        IEEE1394_Format6(94, "IEEE-1394 Fire Wire Data, Format 6", "Reserved For Future Use"), 
        IEEE1394_Format7(95, "IEEE-1394 Fire Wire Data, Format 7", "Reserved For Future Use"), 
        Parallel_Format0(96, "Parallel Data, Format 0", "Parallel Data"), 
        Parallel_Format1(97, "Parallel Data, Format 1", "Reserved For Future Use"), 
        Parallel_Format2(98, "Parallel Data, Format 2", "Reserved For Future Use"), 
        Parallel_Format3(99, "Parallel Data, Format 3", "Reserved For Future Use"), 
        Parallel_Format4(100, "Parallel Data, Format 4", "Reserved For Future Use"), 
        Parallel_Format5(101, "Parallel Data, Format 5", "Reserved For Future Use"), 
        Parallel_Format6(102, "Parallel Data, Format 6", "Reserved For Future Use"), 
        Parallel_Format7(103, "Parallel Data, Format 7", "Reserved For Future Use");
        
        private final int id;
        private final String name;
        private final String description;
        
        private PacketType(final int id, final String name, final String description) {
            this.id = id;
            this.name = name;
            this.description = description;
        }
        
        public static PacketType valueOf(final int id) {
            PacketType result = null;
            for (final PacketType packetType : values()) {
                if (packetType.id == id) {
                    result = packetType;
                }
            }
            return result;
        }
        
        public String getName() {
            return this.name;
        }
        
        public String getDescription() {
            return this.description;
        }
    }
}
