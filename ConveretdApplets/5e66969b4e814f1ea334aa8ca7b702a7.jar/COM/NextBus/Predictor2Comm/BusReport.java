// 
// Decompiled by Procyon v0.5.30
// 

package COM.NextBus.Predictor2Comm;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.io.InputStream;
import COM.NextBus.util.d;
import java.io.DataInputStream;
import COM.NextBus.util.c;
import COM.NextBus.DBModel.a;
import COM.NextBus.util.b;
import java.util.Date;
import java.text.DecimalFormat;
import java.util.Arrays;
import COM.NextBus.AdminMap.x;
import java.text.DateFormat;
import java.io.Serializable;

public class BusReport implements Serializable
{
    private static final long serialVersionUID = -7538546965643475515L;
    private final String _source;
    private final String _busID;
    private final Location _location;
    private final long _evTime;
    private final int _speed;
    private final int _heading;
    private final String _jobTuple;
    private final String _routeTag;
    private final String _pathTag;
    private final int _position;
    private final BusPrediction[] _predictions;
    private final boolean _predictable;
    private final boolean _breakpointing;
    private final int _adherence;
    private final String _leadingVehicle;
    private final long _gpsTime;
    private final int _numPassengers;
    private final boolean _silentAlarmActivated;
    private final int _headwayMillis;
    private final int _scheduledHeadwaySecs;
    private final int _headwayStopDirMillis;
    private final String _tripTag;
    private final String _driverId;
    private final double[] _rawRateIos;
    private final Integer _latenessMillis;
    private static final DateFormat a;
    
    public BusReport(final String s, final String s2, final double n, final double n2, final long evTime, final int speed, final int n3, final String s3, final String s4, final String s5, final int position, final BusPrediction[] array, final boolean predictable, final boolean breakpointing, final Integer latenessMillis, final long gpsTime, final String s6, final Integer n4, final boolean silentAlarmActivated, final Integer n5, final Integer n6, final Integer n7, final String s7, final String s8, final double[] rawRateIos) {
        this._source = a(s);
        this._busID = a(s2);
        this._location = new Location(n, n2);
        this._evTime = evTime;
        this._speed = speed;
        this._heading = ((n3 > 360) ? (n3 % 360) : n3);
        this._jobTuple = a(s3);
        this._routeTag = a(s4);
        this._pathTag = a(s5);
        if (position < 0) {
            this._position = -1;
        }
        else {
            this._position = position;
        }
        if (array == null) {
            this._predictions = new BusPrediction[0];
        }
        else {
            this._predictions = new BusPrediction[array.length];
            for (int i = 0; i < array.length; ++i) {
                this._predictions[i] = array[i];
            }
        }
        this._adherence = ((latenessMillis == null) ? 0 : latenessMillis);
        this._latenessMillis = latenessMillis;
        this._predictable = predictable;
        this._breakpointing = breakpointing;
        this._leadingVehicle = a(s6);
        this._gpsTime = gpsTime;
        this._numPassengers = ((n4 == null) ? Integer.MIN_VALUE : n4);
        this._silentAlarmActivated = silentAlarmActivated;
        this._headwayMillis = ((n5 == null) ? Integer.MIN_VALUE : n5);
        this._headwayStopDirMillis = ((n6 == null) ? Integer.MIN_VALUE : n6);
        this._scheduledHeadwaySecs = ((n7 == null) ? Integer.MIN_VALUE : n7);
        this._tripTag = a(s7);
        this._driverId = a(s8);
        boolean b = true;
        for (int n8 = 0; rawRateIos != null && n8 < rawRateIos.length; ++n8) {
            if (rawRateIos[n8] != 0.0) {
                b = false;
                break;
            }
        }
        if (rawRateIos == null || b) {
            this._rawRateIos = new double[0];
            return;
        }
        this._rawRateIos = rawRateIos;
    }
    
    public static BusReport a(final String s, final String s2, final long n, final long n2, final int n3, final String s3) {
        return new BusReport(s, s2, 0.0, 0.0, n, -1006, 0, s3, null, null, -1, null, true, false, 0, n2, null, null, false, null, null, null, null, null, null);
    }
    
    public final boolean a() {
        return this._speed < 0;
    }
    
    public Object clone() {
        return new BusReport(this._source, this._busID, this._location.a(), this._location.b(), this._evTime, this._speed, this._heading, this._jobTuple, this._routeTag, this._pathTag, this._position, this._predictions, this._predictable, this._breakpointing, this._latenessMillis, this._gpsTime, this._leadingVehicle, this.w(), this._silentAlarmActivated, this.x(), this.y(), this.z(), b(this._tripTag), b(this._driverId), this._rawRateIos);
    }
    
    public int hashCode() {
        return this._busID.hashCode();
    }
    
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o != null && this.getClass() == o.getClass()) {
            final BusReport busReport = (BusReport)o;
            if (this._evTime == busReport._evTime && this._gpsTime == busReport._gpsTime && this._speed == busReport._speed && this._heading == busReport._heading && x.a(this._busID, busReport._busID) && x.a(this._source, busReport._source) && x.a(this._location, busReport._location) && x.a(this._jobTuple, busReport._jobTuple) && x.a(this._routeTag, busReport._routeTag) && x.a(this._pathTag, busReport._pathTag) && this._position == busReport._position && Arrays.equals(this._predictions, busReport._predictions) && x.a(this._latenessMillis, busReport._latenessMillis) && this._predictable == busReport._predictable && x.a(this._leadingVehicle, busReport._leadingVehicle) && this._numPassengers == busReport._numPassengers && this._silentAlarmActivated == busReport._silentAlarmActivated && this._headwayMillis == busReport._headwayMillis && this._headwayStopDirMillis == busReport._headwayStopDirMillis && this._scheduledHeadwaySecs == busReport._scheduledHeadwaySecs && x.a(this._tripTag, busReport._tripTag) && x.a(this._driverId, busReport._driverId) && Arrays.equals(this._rawRateIos, busReport._rawRateIos)) {
                return true;
            }
        }
        return false;
    }
    
    public String toString() {
        return this.a(null, true, true);
    }
    
    private String a(DateFormat dateFormat, boolean b, boolean format) {
        if (!this.a()) {
            dateFormat = (DateFormat)new StringBuffer(100);
            b = (boolean)new DecimalFormat("#.0");
            final String format2;
            synchronized (BusReport.a) {
                format = (int)BusReport.a.format(new Date(this._evTime));
                format2 = BusReport.a.format(new Date(this._gpsTime));
            }
            final int n2;
            int n;
            if ((n = (n2 = this._speed / 100) % 100) < 0) {
                n = -n;
            }
            final String s = (n < 10) ? (n2 + ".0" + n) : (n2 + "." + n);
            ((StringBuffer)dateFormat).append("vehicle=");
            ((StringBuffer)dateFormat).append(b(this._busID));
            ((StringBuffer)dateFormat).append(",eventTime=");
            ((StringBuffer)dateFormat).append(this._evTime);
            ((StringBuffer)dateFormat).append("(").append((String)format).append(")");
            ((StringBuffer)dateFormat).append(",gpsTime=");
            ((StringBuffer)dateFormat).append(this._gpsTime);
            ((StringBuffer)dateFormat).append("(").append(format2).append(")");
            ((StringBuffer)dateFormat).append(",source=");
            ((StringBuffer)dateFormat).append(b(this._source));
            ((StringBuffer)dateFormat).append(",location=");
            ((StringBuffer)dateFormat).append(this._location);
            ((StringBuffer)dateFormat).append(",speed=");
            ((StringBuffer)dateFormat).append(s);
            ((StringBuffer)dateFormat).append("m/sec(");
            ((StringBuffer)dateFormat).append(((NumberFormat)b).format(this.i()));
            ((StringBuffer)dateFormat).append("mph)");
            ((StringBuffer)dateFormat).append(",heading=");
            ((StringBuffer)dateFormat).append(this._heading);
            ((StringBuffer)dateFormat).append(",job=");
            ((StringBuffer)dateFormat).append(this.m());
            ((StringBuffer)dateFormat).append(",leadingVehicle=");
            ((StringBuffer)dateFormat).append(b(this._leadingVehicle));
            ((StringBuffer)dateFormat).append(",number of passengers=");
            ((StringBuffer)dateFormat).append(this.w());
            ((StringBuffer)dateFormat).append(",silentAlarmActivated=");
            ((StringBuffer)dateFormat).append(this._silentAlarmActivated);
            ((StringBuffer)dateFormat).append(",headway=");
            ((StringBuffer)dateFormat).append((this.x() == null) ? "unknown" : ((int)this.x()));
            ((StringBuffer)dateFormat).append(",headwayStopDir=");
            ((StringBuffer)dateFormat).append((this.y() == null) ? "unknown" : ((int)this.y()));
            ((StringBuffer)dateFormat).append(",scheduledHeadway=");
            ((StringBuffer)dateFormat).append((this.z() == null) ? "unknown" : ((int)this.z()));
            ((StringBuffer)dateFormat).append(",tripTag=").append(b(this._tripTag));
            ((StringBuffer)dateFormat).append(",driverId=").append(b(this._driverId));
            final BusPrediction[] predictions;
            final int n3 = ((predictions = this._predictions) == null) ? 0 : predictions.length;
            ((StringBuffer)dateFormat).append(",routeTag=");
            ((StringBuffer)dateFormat).append(b(this._routeTag));
            ((StringBuffer)dateFormat).append(",pathTag=");
            ((StringBuffer)dateFormat).append(b(this._pathTag));
            ((StringBuffer)dateFormat).append(",position=");
            ((StringBuffer)dateFormat).append(this._position);
            ((StringBuffer)dateFormat).append("(meters)");
            ((StringBuffer)dateFormat).append(",lateness=");
            ((StringBuffer)dateFormat).append(this._latenessMillis);
            ((StringBuffer)dateFormat).append(",predictable=");
            ((StringBuffer)dateFormat).append(this._predictable);
            ((StringBuffer)dateFormat).append(",numPredictions=").append(n3);
            ((StringBuffer)dateFormat).append(",predictions=[]:");
            for (int i = 0; i < n3; ++i) {
                ((StringBuffer)dateFormat).append("\n  ");
                if (i < 10) {
                    ((StringBuffer)dateFormat).append(" ");
                }
                ((StringBuffer)dateFormat).append(i).append(": ").append(predictions[i]);
            }
            ((StringBuffer)dateFormat).append(",rawRateIos=");
            ((StringBuffer)dateFormat).append(b.a(this._rawRateIos, ","));
            return ((StringBuffer)dateFormat).toString();
        }
        if ((this = this)._speed >= 0) {
            throw new RuntimeException("This is not a LogEvent!  speed=" + this._speed);
        }
        final int n4;
        return new a(this._source, this._busID, this._evTime, ((n4 = -this._speed) == 1001) ? "jobchange" : ((n4 == 1002) ? "timeout" : ((n4 == 1003) ? "offjob" : ((n4 == 1004) ? "predictable" : ((n4 == 1005) ? "unpredictable" : ((n4 == 1006) ? "adherence" : ((n4 == 1007) ? "silentalarm" : null)))))), this._jobTuple, null).toString();
    }
    
    public final String b() {
        return b(this._source);
    }
    
    public final String c() {
        return b(this._busID);
    }
    
    public final Location d() {
        return this._location;
    }
    
    public final long e() {
        return this._evTime;
    }
    
    public final long f() {
        return this._gpsTime;
    }
    
    public final int g() {
        return this._speed;
    }
    
    public final double h() {
        return this._speed / 100.0;
    }
    
    public final double i() {
        return this._speed * 0.022374145431945307;
    }
    
    public final int j() {
        return this._heading;
    }
    
    public final String k() {
        if (this._speed >= 0) {
            return "Position";
        }
        return COM.NextBus.DBModel.a.a(-1 * this._speed);
    }
    
    public final String l() {
        final String m;
        if ((m = this.m()) != null) {
            return m.substring(m.lastIndexOf(45) + 1);
        }
        return null;
    }
    
    public final String m() {
        if (this._speed >= 0) {
            return b(this._jobTuple);
        }
        try {
            final int n;
            if ((n = -1 * this._speed) == 1004) {
                return this._jobTuple.substring(4, this._jobTuple.indexOf(44));
            }
            if (n == 1001) {
                return this._jobTuple;
            }
            if (n == 1005) {
                return this._jobTuple.substring(4, this._jobTuple.indexOf(44));
            }
            if (n == 1006) {
                return this._jobTuple.substring(4, this._jobTuple.indexOf(44));
            }
            if (n == 1003) {
                return null;
            }
            return null;
        }
        catch (Throwable t) {
            return null;
        }
    }
    
    public final String n() {
        return b(this._routeTag);
    }
    
    public final String o() {
        return b(this._pathTag);
    }
    
    public final int p() {
        return this._position;
    }
    
    public final BusPrediction[] q() {
        return this._predictions;
    }
    
    public final BusPrediction r() {
        final BusPrediction[] predictions;
        if ((predictions = (this = this)._predictions) != null) {
            for (int i = 0; i < predictions.length; ++i) {
                final BusPrediction busPrediction;
                if ((busPrediction = predictions[i]).c() - c.a() > 0L) {
                    return busPrediction;
                }
            }
        }
        return null;
    }
    
    public final Integer s() {
        return this._latenessMillis;
    }
    
    public final boolean t() {
        return this._predictable;
    }
    
    public final boolean u() {
        return this._breakpointing;
    }
    
    public final String v() {
        return b(this._leadingVehicle);
    }
    
    public final Integer w() {
        if (this._numPassengers == Integer.MIN_VALUE) {
            return null;
        }
        return new Integer(this._numPassengers);
    }
    
    public final Integer x() {
        if (this._headwayMillis == Integer.MIN_VALUE) {
            return null;
        }
        return new Integer(this._headwayMillis);
    }
    
    public final Integer y() {
        if (this._headwayStopDirMillis == Integer.MIN_VALUE) {
            return null;
        }
        return new Integer(this._headwayStopDirMillis);
    }
    
    public final Integer z() {
        if (this._scheduledHeadwaySecs == Integer.MIN_VALUE) {
            return null;
        }
        return new Integer(this._scheduledHeadwaySecs);
    }
    
    public final boolean A() {
        return this._silentAlarmActivated;
    }
    
    public final String B() {
        return b(this._tripTag);
    }
    
    public final String C() {
        return b(this._driverId);
    }
    
    public final double[] D() {
        return this._rawRateIos;
    }
    
    public static BusReport a(final DataInputStream dataInputStream) {
        return new BusReport(dataInputStream);
    }
    
    private BusReport(final DataInputStream dataInputStream) {
        final String utf = dataInputStream.readUTF();
        int int1 = 0;
        if (utf != null && utf.length() > "Version".length()) {
            final String substring = utf.substring("Version".length());
            try {
                int1 = Integer.parseInt(substring);
            }
            catch (NumberFormatException ex) {}
        }
        final boolean b = int1 == 3;
        this._source = dataInputStream.readUTF();
        this._busID = dataInputStream.readUTF();
        this._location = Location.a(dataInputStream);
        this._evTime = (dataInputStream.readInt() & 0xFFFFFFFFL) * 1000L + dataInputStream.readUnsignedShort();
        this._speed = dataInputStream.readInt();
        this._heading = dataInputStream.readInt();
        if (b) {
            dataInputStream.readUTF();
        }
        this._jobTuple = dataInputStream.readUTF();
        this._routeTag = dataInputStream.readUTF();
        this._pathTag = dataInputStream.readUTF();
        this._position = dataInputStream.readInt();
        final int int2;
        if ((int2 = dataInputStream.readInt()) >= 0) {
            this._predictions = new BusPrediction[int2];
            for (int i = 0; i < int2; ++i) {
                this._predictions[i] = BusPrediction.a(dataInputStream);
            }
        }
        else {
            this._predictions = null;
        }
        this._adherence = dataInputStream.readInt();
        if (int1 >= 5) {
            this._leadingVehicle = dataInputStream.readUTF();
        }
        else {
            this._leadingVehicle = "";
        }
        if (int1 >= 6) {
            this._predictable = dataInputStream.readBoolean();
        }
        else {
            this._predictable = true;
        }
        if (int1 >= 7) {
            this._gpsTime = (dataInputStream.readInt() & 0xFFFFFFFFL) * 1000L + dataInputStream.readUnsignedShort();
        }
        else {
            this._gpsTime = this._evTime;
        }
        if (int1 >= 8) {
            this._breakpointing = dataInputStream.readBoolean();
        }
        else {
            this._breakpointing = false;
        }
        if (int1 >= 9) {
            this._numPassengers = dataInputStream.readInt();
        }
        else {
            this._numPassengers = Integer.MIN_VALUE;
        }
        if (int1 >= 10) {
            this._silentAlarmActivated = dataInputStream.readBoolean();
        }
        else {
            this._silentAlarmActivated = false;
        }
        if (int1 >= 11) {
            this._headwayMillis = dataInputStream.readInt();
            this._scheduledHeadwaySecs = dataInputStream.readInt();
        }
        else {
            this._headwayMillis = Integer.MIN_VALUE;
            this._scheduledHeadwaySecs = Integer.MIN_VALUE;
        }
        int int3 = 0;
        if (int1 >= 12) {
            int3 = dataInputStream.readInt();
        }
        final d d = new d(dataInputStream);
        final DataInputStream dataInputStream2 = new DataInputStream(d);
        if (int1 >= 13) {
            this._headwayStopDirMillis = dataInputStream2.readInt();
        }
        else {
            this._headwayStopDirMillis = Integer.MIN_VALUE;
        }
        if (int1 >= 14) {
            this._tripTag = dataInputStream2.readUTF();
        }
        else {
            this._tripTag = "";
        }
        if (int1 >= 15) {
            this._driverId = dataInputStream2.readUTF();
        }
        else {
            this._driverId = "";
        }
        if (int1 >= 16) {
            final int int4 = dataInputStream2.readInt();
            this._rawRateIos = new double[int4];
            for (int n = 0; int4 >= 0 && n < int4; ++n) {
                this._rawRateIos[n] = dataInputStream2.readDouble();
            }
        }
        else {
            this._rawRateIos = new double[0];
        }
        Integer latenessMillis = this._adherence;
        if (int1 >= 17 && (latenessMillis = dataInputStream2.readInt()) == Integer.MIN_VALUE) {
            latenessMillis = null;
        }
        this._latenessMillis = latenessMillis;
        for (int n2 = (int)d.a(), j = 0; j < int3 - n2; ++j) {
            dataInputStream.readByte();
        }
    }
    
    private static String a(final String s) {
        if (s == null) {
            return "";
        }
        return s;
    }
    
    private static String b(final String s) {
        if (s == null) {
            return null;
        }
        if (s.equals("")) {
            return null;
        }
        return s;
    }
    
    static {
        a = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss z");
    }
}
