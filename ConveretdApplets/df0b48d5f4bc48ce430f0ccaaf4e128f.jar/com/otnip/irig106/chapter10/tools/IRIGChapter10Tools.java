// 
// Decompiled by Procyon v0.5.30
// 

package com.otnip.irig106.chapter10.tools;

import com.otnip.irig106.chapter10.packets.TimePacket_Format1;
import java.io.InputStream;
import com.otnip.irig106.chapter10.Packet;
import java.io.FileInputStream;
import java.io.File;
import java.text.DecimalFormat;

public class IRIGChapter10Tools
{
    private static final DecimalFormat dayFormat;
    private static final DecimalFormat usFormat;
    private static final DecimalFormat middleFormat;
    
    public static String getTMATS(final File chapter10File) throws Exception {
        final FileInputStream fis = new FileInputStream(chapter10File);
        final Packet p = new Packet(fis);
        byte[] b = p.getPacketBodyData();
        final byte[] temp = new byte[b.length - 4];
        System.arraycopy(b, 4, temp, 0, temp.length);
        b = temp;
        final String tmatsString = new String(b);
        fis.close();
        return tmatsString;
    }
    
    public static double getStartTime(final File chapter10File) throws Exception {
        final FileInputStream fis = new FileInputStream(chapter10File);
        final Packet packet = new Packet(fis);
        packet.read(fis);
        fis.close();
        if (packet.getHeader().getDataType() != Packet.PacketType.Analog_Format1) {
            throw new Exception("Second Packet Not A Time Packet");
        }
        return ((TimePacket_Format1)packet.getPacketBody()).getIRIGTime();
    }
    
    public static String getIRIGString(final double irigTime) {
        long us = (long)(irigTime * 1000000.0);
        long sec = 0L;
        long min = 0L;
        long hour = 0L;
        long day = 0L;
        sec = us / 1000000L;
        min = sec / 60L;
        hour = min / 60L;
        day = hour / 24L;
        us -= sec * 1000000L;
        sec -= min * 60L;
        min -= hour * 60L;
        hour -= day * 24L;
        return IRIGChapter10Tools.dayFormat.format(day) + ":" + IRIGChapter10Tools.middleFormat.format(hour) + ":" + IRIGChapter10Tools.middleFormat.format(min) + ":" + IRIGChapter10Tools.middleFormat.format(sec) + "." + IRIGChapter10Tools.usFormat.format(us);
    }
    
    static {
        dayFormat = new DecimalFormat();
        usFormat = new DecimalFormat();
        middleFormat = new DecimalFormat();
        IRIGChapter10Tools.dayFormat.setMinimumFractionDigits(0);
        IRIGChapter10Tools.dayFormat.setMaximumFractionDigits(0);
        IRIGChapter10Tools.dayFormat.setMinimumIntegerDigits(3);
        IRIGChapter10Tools.dayFormat.setMaximumIntegerDigits(3);
        IRIGChapter10Tools.middleFormat.setMinimumFractionDigits(0);
        IRIGChapter10Tools.middleFormat.setMaximumFractionDigits(0);
        IRIGChapter10Tools.middleFormat.setMinimumIntegerDigits(2);
        IRIGChapter10Tools.middleFormat.setMaximumIntegerDigits(2);
        IRIGChapter10Tools.usFormat.setMinimumFractionDigits(0);
        IRIGChapter10Tools.usFormat.setMaximumFractionDigits(0);
        IRIGChapter10Tools.usFormat.setMinimumIntegerDigits(6);
        IRIGChapter10Tools.usFormat.setMaximumIntegerDigits(6);
        IRIGChapter10Tools.usFormat.setGroupingUsed(false);
    }
}
