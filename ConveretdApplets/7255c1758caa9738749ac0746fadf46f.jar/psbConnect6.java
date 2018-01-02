import java.util.GregorianCalendar;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.Date;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

// 
// Decompiled by Procyon v0.5.30
// 

public class psbConnect6
{
    private long correction;
    private long uncertainty;
    private long gmtDiff;
    private long serverLong;
    private boolean timeValid;
    private boolean connectError;
    private String servername;
    private String serverString;
    
    public psbConnect6(final String servername) {
        this.correction = 0L;
        this.uncertainty = 0L;
        this.timeValid = false;
        this.servername = servername;
        this.serverString = "";
        this.connectError = false;
        this.gmtDiff = 0L;
    }
    
    private void timeDiff() {
        this.sockConnect();
        final TimeZone default1 = TimeZone.getDefault();
        TimeZone.setDefault(new SimpleTimeZone(0, "GMT"));
        final Date date = new Date();
        if (!this.connectError) {
            this.gmtDiff = this.serverLong - date.getTime();
            date.getTime();
        }
        TimeZone.setDefault(default1);
    }
    
    private void sockConnect() {
        try {
            final Socket socket = new Socket(this.servername, 9001);
            socket.setSoTimeout(5000);
            this.serverLong = new ObjectInputStream(socket.getInputStream()).readLong();
            final String string = Long.toString(this.serverLong);
            socket.close();
            if (string.equals("error")) {
                this.connectError = true;
                return;
            }
            this.connectError = false;
        }
        catch (IOException ex) {
            this.serverString = "";
            this.connectError = true;
        }
    }
    
    public boolean isValid() {
        return this.timeValid;
    }
    
    public void getData() {
        this.timeValid = false;
        long gmtDiff = 0L;
        long uncertainty = 0L;
        int n = 0;
        final int n2 = 12;
        final long n3 = 500L;
        do {
            final GregorianCalendar gregorianCalendar = new GregorianCalendar();
            this.timeDiff();
            final GregorianCalendar gregorianCalendar2 = new GregorianCalendar();
            this.timeDiff();
            final GregorianCalendar gregorianCalendar3 = new GregorianCalendar();
            this.timeDiff();
            final GregorianCalendar gregorianCalendar4 = new GregorianCalendar();
            final long n4 = gregorianCalendar2.getTime().getTime() - gregorianCalendar.getTime().getTime();
            final long n5 = gregorianCalendar3.getTime().getTime() - gregorianCalendar2.getTime().getTime();
            final long n6 = gregorianCalendar4.getTime().getTime() - gregorianCalendar3.getTime().getTime();
            if (!this.connectError) {
                final long n7 = n4 - n5;
                final long n8 = n5 - n6;
                if (Math.abs(n7) < n3 && Math.abs(n8) < n3) {
                    gmtDiff = this.gmtDiff;
                    uncertainty = n6;
                    break;
                }
                continue;
            }
        } while (++n < n2);
        if (n < n2) {
            this.connectError = false;
            this.correction = gmtDiff;
            this.uncertainty = uncertainty;
            this.timeValid = true;
            return;
        }
        this.connectError = true;
        this.correction = 0L;
        this.uncertainty = 0L;
        this.timeValid = false;
    }
    
    public long accuracy() {
        return this.uncertainty;
    }
    
    public long value() {
        return this.correction;
    }
}
