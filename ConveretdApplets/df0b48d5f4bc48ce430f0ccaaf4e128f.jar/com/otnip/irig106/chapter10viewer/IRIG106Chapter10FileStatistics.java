// 
// Decompiled by Procyon v0.5.30
// 

package com.otnip.irig106.chapter10viewer;

import java.awt.LayoutManager;
import java.awt.GridLayout;
import javax.swing.JPanel;
import org.jfree.chart.JFreeChart;
import java.awt.Font;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.PieDataset;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.data.general.DefaultPieDataset;
import java.util.Iterator;
import com.otnip.irig106.chapter10.tools.IRIGChapter10Tools;
import java.nio.channels.FileChannel;
import com.otnip.irig106.chapter10.packets.TimePacket_Format1;
import com.otnip.irig106.chapter10.PacketHeader;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import com.otnip.tools.StopWatch;
import java.awt.Component;
import java.text.DecimalFormat;
import com.otnip.irig106.chapter10.Packet;
import java.util.HashMap;
import java.io.File;

public class IRIG106Chapter10FileStatistics
{
    private File sourceFile;
    private long totalPackets;
    private HashMap<Packet.PacketType, SourceInfo> packetTypeInfo;
    private HashMap<Integer, SourceInfo> channelTypeInfo;
    private DecimalFormat decimalFormat;
    private ProgressIndicator progress;
    private double firstTimePacketTime;
    private double lastTimePacketTime;
    
    public IRIG106Chapter10FileStatistics(final File sourceFile) {
        this.packetTypeInfo = new HashMap<Packet.PacketType, SourceInfo>();
        this.channelTypeInfo = new HashMap<Integer, SourceInfo>();
        this.decimalFormat = new DecimalFormat();
        this.firstTimePacketTime = Double.NaN;
        this.lastTimePacketTime = Double.NaN;
        this.sourceFile = sourceFile;
        this.progress = new ProgressIndicator("Anlayzing " + sourceFile.getName(), false, true);
    }
    
    public static IRIG106Chapter10FileStatistics analyze(final File file, final boolean showProgressIndicator) throws Exception {
        final IRIG106Chapter10FileStatistics irig106Chapter10FileStatistics = new IRIG106Chapter10FileStatistics(file);
        if (showProgressIndicator) {
            irig106Chapter10FileStatistics.getProgressIndicator().display(null);
        }
        irig106Chapter10FileStatistics.analyze();
        return irig106Chapter10FileStatistics;
    }
    
    public void analyze() throws Exception {
        final StopWatch s = new StopWatch();
        s.start();
        try {
            final double progressScale = 1.0 / this.sourceFile.length();
            final FileInputStream fis = new FileInputStream(this.sourceFile);
            final BufferedInputStream bis = new BufferedInputStream(fis);
            final FileChannel channel = fis.getChannel();
            final Packet packet = new Packet();
            PacketHeader header = new PacketHeader();
            while (bis.available() > 0) {
                packet.read(bis);
                header = packet.getHeader();
                ++this.totalPackets;
                if (header.getDataType() == Packet.PacketType.Time_Format1) {
                    final TimePacket_Format1 timePacket = (TimePacket_Format1)packet.getPacketBody();
                    if (Double.isNaN(this.firstTimePacketTime)) {
                        this.firstTimePacketTime = timePacket.getIRIGTime();
                    }
                    this.lastTimePacketTime = timePacket.getIRIGTime();
                }
                SourceInfo sourceInfo = this.packetTypeInfo.get(header.getDataType());
                if (sourceInfo == null) {
                    sourceInfo = new SourceInfo();
                    this.packetTypeInfo.put(header.getDataType(), sourceInfo);
                }
                sourceInfo.totalPackets++;
                sourceInfo.totalBytes += header.getPacketLength();
                sourceInfo = this.channelTypeInfo.get(header.getChannelID());
                if (sourceInfo == null) {
                    sourceInfo = new SourceInfo();
                    sourceInfo.packetType = header.getDataType();
                    this.channelTypeInfo.put(header.getChannelID(), sourceInfo);
                }
                sourceInfo.totalPackets++;
                sourceInfo.totalBytes += header.getPacketLength();
                if (this.progress != null) {
                    this.progress.setProgress(channel.position() * progressScale);
                }
            }
            fis.close();
        }
        catch (Exception e) {
            if (this.progress != null) {
                this.progress.dispose();
            }
            throw e;
        }
        if (this.progress != null) {
            this.progress.dispose();
        }
        s.stop();
    }
    
    public ProgressIndicator getProgressIndicator() {
        return this.progress;
    }
    
    public String toString() {
        final double scalePackets = 100.0 / this.totalPackets;
        final double scaleBytes = 100.0 / this.sourceFile.length();
        final StringBuilder string = new StringBuilder();
        string.append("IRIG 106 Chapter 10 File Information\n");
        string.append("\tFile Name:  " + this.sourceFile.getAbsolutePath() + "\n");
        string.append("\tFirst Time Packet Time (1s Tolerance):  " + IRIGChapter10Tools.getIRIGString(this.firstTimePacketTime) + "\n");
        string.append("\tLast Time Packet Time (1s Tolerance):  " + IRIGChapter10Tools.getIRIGString(this.lastTimePacketTime) + "\n");
        string.append("\tDuration (1s Tolerance):  " + (this.lastTimePacketTime - this.firstTimePacketTime) + " s (IRIG Format:  " + IRIGChapter10Tools.getIRIGString(this.lastTimePacketTime - this.firstTimePacketTime) + ")\n");
        string.append("\tTotal Packets:  " + this.totalPackets + "\n");
        string.append("\tTotal Bytes:  " + this.sourceFile.length() + "\n");
        string.append("\n");
        string.append("\tPacket-Type Information\n");
        for (final Packet.PacketType packetType : this.packetTypeInfo.keySet()) {
            final SourceInfo sourceInfo = this.packetTypeInfo.get(packetType);
            string.append("\t\tPacket-Type:  " + packetType + "\n");
            string.append("\t\t\tTotal Packets:  " + sourceInfo.totalPackets + " (" + this.decimalFormat.format(sourceInfo.totalPackets * scalePackets) + " %)\n");
            string.append("\t\t\tTotal Bytes:  " + sourceInfo.totalBytes + " (" + this.decimalFormat.format(sourceInfo.totalBytes * scaleBytes) + " %)\n");
        }
        string.append("\n");
        string.append("\tChannel Information\n");
        for (final Integer channelID : this.channelTypeInfo.keySet()) {
            final SourceInfo sourceInfo = this.channelTypeInfo.get(channelID);
            string.append("\t\tChannel ID:  " + channelID + " (" + sourceInfo.packetType + ")\n");
            string.append("\t\t\tTotal Packets:  " + sourceInfo.totalPackets + " (" + this.decimalFormat.format(sourceInfo.totalPackets * scalePackets) + " %)\n");
            string.append("\t\t\tTotal Bytes:  " + sourceInfo.totalBytes + " (" + this.decimalFormat.format(sourceInfo.totalBytes * scaleBytes) + " %)\n");
        }
        return string.toString();
    }
    
    private ChartPanel getPlot(final String title, final DefaultPieDataset dataset) {
        final JFreeChart chart = ChartFactory.createPieChart(title, dataset, false, true, false);
        final PiePlot plot = (PiePlot)chart.getPlot();
        plot.setSectionOutlinesVisible(false);
        plot.setLabelFont(new Font("SansSerif", 0, 12));
        plot.setNoDataMessage("No data available");
        plot.setCircular(false);
        plot.setLabelGap(0.02);
        return new ChartPanel(chart);
    }
    
    public JPanel getCharts() {
        final JPanel chartPanel = new JPanel();
        chartPanel.setLayout(new GridLayout(2, 2));
        DefaultPieDataset dataset = new DefaultPieDataset();
        for (final Packet.PacketType packetType : this.packetTypeInfo.keySet()) {
            dataset.setValue(packetType, new Double(this.packetTypeInfo.get(packetType).totalPackets));
        }
        chartPanel.add(this.getPlot("Packet Types (Occurrences)", dataset));
        dataset = new DefaultPieDataset();
        for (final Packet.PacketType packetType : this.packetTypeInfo.keySet()) {
            dataset.setValue(packetType, new Double(this.packetTypeInfo.get(packetType).totalBytes * 1.0E-6));
        }
        chartPanel.add(this.getPlot("Packet Types (MB)", dataset));
        dataset = new DefaultPieDataset();
        for (final Integer channelID : this.channelTypeInfo.keySet()) {
            dataset.setValue(channelID + " (" + this.channelTypeInfo.get(channelID).packetType + ")", new Double(this.channelTypeInfo.get(channelID).totalPackets));
        }
        chartPanel.add(this.getPlot("Channels (Occurrences)", dataset));
        dataset = new DefaultPieDataset();
        for (final Integer channelID : this.channelTypeInfo.keySet()) {
            dataset.setValue(channelID + " (" + this.channelTypeInfo.get(channelID).packetType + ")", new Double(this.channelTypeInfo.get(channelID).totalBytes * 1.0E-6));
        }
        chartPanel.add(this.getPlot("Channel ID (Bytes)", dataset));
        return chartPanel;
    }
    
    private static class SourceInfo
    {
        private long totalPackets;
        private long totalBytes;
        private Packet.PacketType packetType;
    }
}
