// 
// Decompiled by Procyon v0.5.30
// 

package com.otnip.irig106.chapter10.examples;

import javax.sound.sampled.SourceDataLine;
import com.otnip.irig106.chapter10.PacketHeader;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.AudioFormat;
import com.otnip.datavisualization.plot1d.PlotPane1D;
import com.otnip.irig106.chapter10.packets.AnalogPacket_Format1;
import com.otnip.irig106.chapter10.packets.TimePacket_Format1;
import com.otnip.irig106.chapter10.Packet;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import com.otnip.tools.ArrayListDouble;
import com.otnip.irig106.chapter10.tools.IRIGChapter10Tools;
import java.io.File;

public class HelloWorldExample
{
    public static void main(final String[] args) {
        try {
            final File sourceFile = new File("/home/dlpinto/Desktop/file0004_07022008_23451882_23461801.CH10");
            System.out.println(IRIGChapter10Tools.getTMATS(sourceFile));
            final ArrayListDouble data = new ArrayListDouble();
            final double[] buffer = new double[100000];
            final FileInputStream fis = new FileInputStream(sourceFile);
            final BufferedInputStream bis = new BufferedInputStream(fis);
            for (int i = 0; i < 20000; ++i) {
                final Packet packet = new Packet(bis);
                final PacketHeader header = packet.getHeader();
                switch (header.getDataType()) {
                    case Time_Format1: {
                        final TimePacket_Format1 timePacket = (TimePacket_Format1)packet.getPacketBody();
                        break;
                    }
                    case Analog_Format1: {
                        if (header.getChannelID() != 51) {
                            break;
                        }
                        final AnalogPacket_Format1 analogPacket = (AnalogPacket_Format1)packet.getPacketBody();
                        if (analogPacket.getChannelSpecificDataWords()[0].getTotalChannels() > 0) {
                            data.add(buffer, 0, analogPacket.getData(buffer, 0, true));
                            break;
                        }
                        break;
                    }
                }
                final int channelID = header.getChannelID();
            }
            final PlotPane1D pp = new PlotPane1D();
            pp.plot(data.getArrayCopy());
            pp.display();
            final double fs = 16000.0;
            final AudioFormat af = new AudioFormat(44100.0f, 16, 1, true, true);
            final SourceDataLine line = AudioSystem.getSourceDataLine(af);
            line.open();
            line.start();
            for (int j = 0; j < data.length(); ++j) {
                final short s = (short)(2.0 * data.get(j));
                final byte[] bytes = { (byte)(s >> 8 & 0xFF), (byte)(s & 0xFF) };
                line.write(bytes, 0, 2);
            }
            System.out.println(data.length() / 16000);
            line.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
