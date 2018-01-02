// 
// Decompiled by Procyon v0.5.30
// 

package com.otnip.irig106.chapter10.tools;

import com.otnip.irig106.chapter10.PacketHeader;
import java.nio.channels.FileChannel;
import java.awt.Component;
import javax.swing.SwingUtilities;
import java.io.OutputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import com.otnip.irig106.chapter10.Packet;
import java.io.File;
import com.otnip.tools.progress.Progress;

public class IRIGChapter10Filter implements Runnable
{
    private Progress progress;
    private File sourceFile;
    private File destFile;
    private String tmatsString;
    private Packet.PacketType[] packetType;
    private int[] channelID;
    
    public IRIGChapter10Filter(final File sourceFile, final File destFile, final String tmatsString, final Packet.PacketType[] packetTypes, final int[] channelID) {
        this.sourceFile = sourceFile;
        this.destFile = destFile;
        this.tmatsString = tmatsString;
        this.packetType = this.packetType;
        this.channelID = ((channelID == null) ? new int[0] : channelID);
    }
    
    public void setProgress(final Progress progress) {
        this.progress = progress;
    }
    
    public void run() {
        try {
            final FileInputStream fis = new FileInputStream(this.sourceFile);
            final BufferedInputStream bis = new BufferedInputStream(fis);
            final FileChannel fileChannel = fis.getChannel();
            final double progressScale = 1.0 / this.sourceFile.length();
            final FileOutputStream fos = new FileOutputStream(this.destFile);
            final BufferedOutputStream bos = new BufferedOutputStream(fos);
            final Packet packet = new Packet(bis);
            final byte[] oldData = packet.getPacketBodyData();
            final byte[] tmatsData = this.tmatsString.getBytes();
            final byte[] newData = new byte[tmatsData.length + 4];
            System.arraycopy(oldData, 0, newData, 0, 4);
            System.arraycopy(tmatsData, 0, newData, 4, tmatsData.length);
            packet.setPacketBodyData(newData);
            packet.write(bos);
            while (bis.available() > 0) {
                packet.read(bis);
                final PacketHeader header = packet.getHeader();
                final int channelID = header.getChannelID();
                boolean writePacket = header.getDataType() == Packet.PacketType.Time_Format1;
                for (final int id : this.channelID) {
                    writePacket |= (id == channelID);
                }
                writePacket |= (this.channelID.length == 0);
                if (writePacket) {
                    packet.write(bos);
                }
                if (this.progress != null) {
                    this.progress.setProgress(fileChannel.position() * progressScale);
                }
            }
            bos.flush();
            fos.flush();
            fos.close();
            fis.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        if (this.progress != null) {
            SwingUtilities.getWindowAncestor(this.progress).dispose();
        }
    }
    
    public static void main(final String[] args) {
        try {
            final File in = new File("/home/dlpinto/Development/oTnip/resources/data/chapter 10/Heim/METSAnalogVoice.C10");
            final File out = new File("/home/dlpinto/out.c10");
            final String tmatsString = IRIGChapter10Tools.getTMATS(in);
            final IRIGChapter10Filter filter = new IRIGChapter10Filter(in, out, tmatsString, Packet.PacketType.values(), new int[] { 22, 23 });
            filter.run();
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
