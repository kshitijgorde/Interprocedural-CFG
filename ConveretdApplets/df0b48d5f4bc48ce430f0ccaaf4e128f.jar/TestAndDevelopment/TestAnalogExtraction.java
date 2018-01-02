// 
// Decompiled by Procyon v0.5.30
// 

package TestAndDevelopment;

import com.otnip.datavisualization.plot1d.PlotPane1D;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.File;
import com.otnip.irig106.chapter10.PacketHeader;
import com.otnip.irig106.chapter10.tools.IRIGChapter10Tools;
import com.otnip.irig106.chapter10.packets.AnalogPacket_Format1;
import com.otnip.irig106.chapter10.packets.TimePacket_Format1;
import com.otnip.tools.ArrayListDouble;
import com.otnip.irig106.chapter10.tools.RelativeTimeCounter;
import java.io.InputStream;
import com.otnip.irig106.chapter10.Packet;

public class TestAnalogExtraction
{
    private Packet packet;
    private InputStream is;
    private int channelID;
    private int subchannelID;
    private boolean signed;
    private double startTime;
    private double endTime;
    private double offset;
    private RelativeTimeCounter counter;
    private ArrayListDouble data;
    
    public TestAnalogExtraction(final InputStream is, final int channelID, final int subchannelID, final boolean signed, final double startTime, final double endTime) {
        this.offset = Double.NaN;
        this.counter = new RelativeTimeCounter();
        this.data = new ArrayListDouble();
        this.is = is;
        this.channelID = channelID;
        this.subchannelID = subchannelID;
        this.signed = signed;
        this.startTime = startTime;
        this.endTime = endTime;
    }
    
    public void run() {
        try {
            final double[] buffer = new double[1000000];
            final int maxCounter = 50;
            int COUNTER = 0;
            while (this.is.available() > 0 && COUNTER < maxCounter) {
                ++COUNTER;
                this.packet = new Packet(this.is);
                final PacketHeader header = this.packet.getHeader();
                if (header.getDataType() == Packet.PacketType.Time_Format1 && header.getChannelID() == 1) {
                    final TimePacket_Format1 timePacket = (TimePacket_Format1)this.packet.getPacketBody();
                    final double time = timePacket.getIRIGTime();
                    this.counter.setReference(time, header.getRelativeTimeCounter());
                    if (!Double.isNaN(this.offset)) {
                        continue;
                    }
                    this.offset = time;
                }
                else {
                    if (header.getDataType() != Packet.PacketType.Analog_Format1 || header.getChannelID() != this.channelID) {
                        continue;
                    }
                    final AnalogPacket_Format1 analogPacket = (AnalogPacket_Format1)this.packet.getPacketBody();
                    final double initialTime = this.counter.getTime(header.getRelativeTimeCounter());
                    System.out.println(IRIGChapter10Tools.getIRIGString(initialTime));
                    final int N = analogPacket.getData(buffer, 0, this.signed);
                    this.data.add(buffer, 0, N);
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void main(final String[] args) {
        try {
            final File file = new File("/home/dlpinto/development/otnip/otnip/resources/data/chapter 10/tmdude/Heim_AnalogV02.C10");
            final FileInputStream fis = new FileInputStream(file);
            final BufferedInputStream bis = new BufferedInputStream(fis);
            final TestAnalogExtraction ae = new TestAnalogExtraction(bis, 2, 0, true, 0.0, 0.0);
            ae.run();
            final PlotPane1D pp = new PlotPane1D();
            pp.plot(ae.data.getArrayCopy());
            pp.display();
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
