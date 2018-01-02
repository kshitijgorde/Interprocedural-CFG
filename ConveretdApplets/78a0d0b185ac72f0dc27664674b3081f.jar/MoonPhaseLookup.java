import java.io.IOException;
import java.io.DataInputStream;
import java.net.URL;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class MoonPhaseLookup
{
    public static final int UNKNOWN = -2;
    public static final int IN_BETWEEN = -1;
    public static final int NEW_MOON = 0;
    public static final int FIRST_QUARTER = 1;
    public static final int FULL_MOON = 2;
    public static final int THIRD_QUARTER = 3;
    protected short firstYear;
    protected short lastYear;
    protected byte[] phaseData;
    
    public MoonPhaseLookup(final Applet anApplet) {
        this.firstYear = -1;
        this.lastYear = -2;
        this.MoonPhaseLookupX(anApplet, "phases.dat");
    }
    
    public MoonPhaseLookup(final Applet anApplet, final String fileName) {
        this.firstYear = -1;
        this.lastYear = -2;
        this.MoonPhaseLookupX(anApplet, fileName);
    }
    
    private void MoonPhaseLookupX(final Applet anApplet, final String fileName) {
        final String fileURL = String.valueOf(anApplet.getCodeBase().toString()) + fileName;
        URL phaseFileURL = null;
        DataInputStream phaseFile = null;
        try {
            phaseFileURL = new URL(fileURL);
            phaseFile = new DataInputStream(phaseFileURL.openStream());
            this.firstYear = phaseFile.readShort();
            this.lastYear = phaseFile.readShort();
            final int dataCount = ((this.lastYear - this.firstYear + 1) * 12 + 2) * 16;
            final int packedDataCount = ((this.lastYear - this.firstYear + 1) * 12 + 2) * 10 + 1;
            final byte[] packedPhaseData = new byte[packedDataCount];
            phaseFile.readFully(packedPhaseData);
            phaseFile.close();
            this.phaseData = new byte[dataCount];
            byte phase = packedPhaseData[0];
            for (int packedOffset = 1, offset = 0; packedOffset < packedDataCount; packedOffset += 10, offset += 16) {
                this.phaseData[offset] = phase;
                for (int phaseDay = 0; phaseDay < 5; ++phaseDay) {
                    final int minutes = (packedPhaseData[packedOffset + phaseDay * 2] << 8 & 0xFF00) | (packedPhaseData[packedOffset + phaseDay * 2 + 1] & 0xFF);
                    if (minutes == 65535) {
                        this.phaseData[offset + phaseDay * 3 + 1] = -1;
                        this.phaseData[offset + phaseDay * 3 + 2] = -1;
                        this.phaseData[offset + phaseDay * 3 + 3] = -1;
                    }
                    else {
                        this.phaseData[offset + phaseDay * 3 + 1] = (byte)(minutes / 1440);
                        this.phaseData[offset + phaseDay * 3 + 2] = (byte)(minutes / 60 % 24);
                        this.phaseData[offset + phaseDay * 3 + 3] = (byte)(minutes % 60);
                        ++phase;
                        if (phase == 4) {
                            phase = 0;
                        }
                    }
                }
            }
        }
        catch (IOException ex) {
            this.firstYear = -1;
            this.lastYear = -2;
        }
    }
    
    public short getFirstYear() {
        return this.firstYear;
    }
    
    public short getLastYear() {
        return this.lastYear;
    }
    
    protected int dataOffset(final int month, final int year) {
        return ((year - this.firstYear) * 12 + month) * 16;
    }
    
    public double momentaryPhase(final int month, final int day, final int year, final int hour, final int minute) {
        if (year < this.firstYear || year > this.lastYear) {
            return -2.0;
        }
        final int offset = this.dataOffset(month, year);
        final int phase = this.phaseData[offset];
        final double t = CalendarUtil.julianDate(month, day, year) + (hour + minute / 60.0) / 24.0;
        int lookBack;
        for (lookBack = offset - 3; this.phaseData[lookBack] < 0; lookBack -= 3) {}
        double lowT = CalendarUtil.julianDate(month - 1, this.phaseData[lookBack], year) + (this.phaseData[lookBack + 1] + this.phaseData[lookBack + 2] / 60.0) / 24.0;
        int lowPhase = phase - 1;
        double highT = CalendarUtil.julianDate(month + 1, this.phaseData[offset + 17], year) + (this.phaseData[offset + 18] + this.phaseData[offset + 19] / 60.0) / 24.0;
        int phase2 = phase;
        for (int j = offset + 1; j < offset + 16 && this.phaseData[j] > 0; j += 3) {
            final double t2 = CalendarUtil.julianDate(month, this.phaseData[j], year) + (this.phaseData[j + 1] + this.phaseData[j + 2] / 60.0) / 24.0;
            if (t2 == t) {
                return phase2 * 90.0;
            }
            if (t2 >= t) {
                highT = t2;
                break;
            }
            lowT = t2;
            lowPhase = phase2;
            phase2 = ++phase2 % 4;
        }
        double result = (lowPhase + (t - lowT) / (highT - lowT)) * 90.0;
        if (result >= 360.0) {
            result -= 360.0;
        }
        else if (result < 0.0) {
            result += 360.0;
        }
        return result;
    }
    
    public int enumeratedPhase(final int month, final int day, final int year, final int[] hourAndMinute) {
        if (year < this.firstYear || year > this.lastYear) {
            return -2;
        }
        final int offset = this.dataOffset(month, year);
        int phase = this.phaseData[offset];
        for (int j = offset + 1; j < offset + 16 && this.phaseData[j] > 0; j += 3) {
            if (this.phaseData[j] == day) {
                if (hourAndMinute != null && hourAndMinute.length == 2) {
                    hourAndMinute[0] = this.phaseData[j + 1];
                    hourAndMinute[1] = this.phaseData[j + 2];
                }
                return phase;
            }
            phase = ++phase % 4;
        }
        return -1;
    }
}
