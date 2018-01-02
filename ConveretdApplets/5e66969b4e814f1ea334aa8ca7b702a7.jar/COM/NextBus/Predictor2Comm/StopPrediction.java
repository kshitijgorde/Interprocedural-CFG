// 
// Decompiled by Procyon v0.5.30
// 

package COM.NextBus.Predictor2Comm;

import java.io.IOException;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import COM.NextBus.AdminMap.x;
import java.text.DateFormat;
import java.io.Serializable;

public class StopPrediction implements Serializable
{
    private static final long serialVersionUID = 5060809631206077672L;
    private final String _routeTag;
    private final String _stopTag;
    private final String _dirTag;
    private final int _stopID;
    private final StopPredictionInfo[] _predictions;
    private final long _passTimeDirStop;
    private final long _passTimeRouteDirStop;
    
    private StopPrediction(String routeTag, String stopTag, String dirTag, final int stopID, final long passTimeDirStop, final long passTimeRouteDirStop, final StopPredictionInfo[] predictions) {
        if (routeTag == null) {
            routeTag = "";
        }
        if (stopTag == null) {
            stopTag = "";
        }
        if (dirTag == null) {
            dirTag = "";
        }
        this._routeTag = routeTag;
        this._stopTag = stopTag;
        this._dirTag = dirTag;
        this._stopID = stopID;
        this._passTimeDirStop = passTimeDirStop;
        this._passTimeRouteDirStop = passTimeRouteDirStop;
        if (predictions == null) {
            this._predictions = new StopPredictionInfo[0];
            return;
        }
        this._predictions = predictions;
    }
    
    public String toString() {
        this = this;
        final StringBuffer sb = new StringBuffer();
        sb.append("stop=").append(this._routeTag + "-" + this._stopTag + "-" + this._dirTag);
        sb.append(",stopID=");
        sb.append(this._stopID);
        sb.append(",lastPassTimeDirStop=").append(this._passTimeDirStop);
        sb.append(",lastPassTimeRouteDirStop=").append(this._passTimeRouteDirStop);
        sb.append(",preds=");
        if (this._predictions == null) {
            sb.append("null");
        }
        else if (this._predictions.length == 0) {
            sb.append("[]");
        }
        else {
            for (int i = 0; i < this._predictions.length; ++i) {
                sb.append("\n  " + this._predictions[i].a(null));
            }
        }
        return sb.toString();
    }
    
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o != null && this.getClass() == o.getClass()) {
            final StopPrediction stopPrediction = (StopPrediction)o;
            if (x.a(this._routeTag, stopPrediction._routeTag) && x.a(this._stopTag, stopPrediction._stopTag) && x.a(this._dirTag, stopPrediction._dirTag) && Arrays.equals(this._predictions, stopPrediction._predictions) && this._stopID == stopPrediction._stopID && this._passTimeDirStop == stopPrediction._passTimeDirStop && this._passTimeRouteDirStop == stopPrediction._passTimeRouteDirStop) {
                return true;
            }
        }
        return false;
    }
    
    public final int hashCode() {
        if (this._stopTag == null) {
            return -1;
        }
        return this._stopTag.hashCode();
    }
    
    public final String a() {
        return this._routeTag;
    }
    
    public final String b() {
        return this._stopTag;
    }
    
    public final String c() {
        return this._dirTag;
    }
    
    public final Long d() {
        if (this._passTimeRouteDirStop == Long.MAX_VALUE) {
            return null;
        }
        return this._passTimeRouteDirStop;
    }
    
    public final List a(final long n) {
        final ArrayList<StopPredictionInfo> list = new ArrayList<StopPredictionInfo>(this._predictions.length);
        for (int i = 0; i < this._predictions.length; ++i) {
            final StopPredictionInfo stopPredictionInfo;
            if ((stopPredictionInfo = this._predictions[i]).i() - n > -60L) {
                list.add(stopPredictionInfo);
            }
        }
        return list;
    }
    
    public final StopPredictionInfo[] b(final long n) {
        if (this._predictions.length == 0) {
            return new StopPredictionInfo[0];
        }
        final List a = this.a(n);
        return a.toArray(new StopPredictionInfo[a.size()]);
    }
    
    public final void a(final DataOutputStream dataOutputStream) {
        dataOutputStream.writeLong(1250801764255L);
        dataOutputStream.writeByte(3);
        dataOutputStream.writeUTF(this._routeTag);
        dataOutputStream.writeUTF(this._stopTag);
        dataOutputStream.writeUTF(this._dirTag);
        dataOutputStream.writeInt(this._stopID);
        dataOutputStream.writeLong(this._passTimeDirStop);
        dataOutputStream.writeLong(this._passTimeRouteDirStop);
        int length;
        if ((length = this._predictions.length) > 255) {
            length = 255;
        }
        dataOutputStream.writeByte(length);
        for (int i = 0; i < length; ++i) {
            final StopPredictionInfo stopPredictionInfo;
            String s;
            if ((s = (stopPredictionInfo = this._predictions[i]).b()) == null) {
                s = this._routeTag;
            }
            String s2;
            if ((s2 = stopPredictionInfo.c()) == null) {
                s2 = this._stopTag;
            }
            String s3;
            if ((s3 = stopPredictionInfo.e()) == null) {
                s3 = this._dirTag;
            }
            String f;
            if ((f = stopPredictionInfo.f()) == null) {
                f = "";
            }
            dataOutputStream.writeUTF(stopPredictionInfo.a());
            dataOutputStream.writeUTF(s);
            dataOutputStream.writeUTF(s2);
            dataOutputStream.writeUTF(stopPredictionInfo.d());
            dataOutputStream.writeUTF(s3);
            dataOutputStream.writeUTF(f);
            dataOutputStream.writeInt(stopPredictionInfo.g());
            String h;
            if ((h = stopPredictionInfo.h()) == null) {
                h = "";
            }
            dataOutputStream.writeUTF(h);
            dataOutputStream.writeLong(stopPredictionInfo.i());
            dataOutputStream.writeBoolean(stopPredictionInfo.l());
            dataOutputStream.writeBoolean(stopPredictionInfo.j());
            dataOutputStream.writeBoolean(stopPredictionInfo.k());
            dataOutputStream.writeDouble(stopPredictionInfo.m());
        }
    }
    
    public static StopPrediction a(final DataInputStream dataInputStream) {
        try {
            dataInputStream.mark(8);
            if (dataInputStream.readLong() != 1250801764255L) {
                throw new IOException("could not read magic number");
            }
        }
        catch (IOException ex) {
            dataInputStream.reset();
            return b(dataInputStream);
        }
        final byte byte1;
        if ((byte1 = dataInputStream.readByte()) < 2) {
            return null;
        }
        final String utf = dataInputStream.readUTF();
        final String utf2 = dataInputStream.readUTF();
        final String utf3 = dataInputStream.readUTF();
        final int int1 = dataInputStream.readInt();
        final long long1 = dataInputStream.readLong();
        final long long2 = dataInputStream.readLong();
        final int unsignedByte;
        final StopPredictionInfo[] array = new StopPredictionInfo[unsignedByte = dataInputStream.readUnsignedByte()];
        for (int i = 0; i < unsignedByte; ++i) {
            final String utf4 = dataInputStream.readUTF();
            final String utf5 = dataInputStream.readUTF();
            final String utf6 = dataInputStream.readUTF();
            final String utf7 = dataInputStream.readUTF();
            final String utf8 = dataInputStream.readUTF();
            final String utf9 = dataInputStream.readUTF();
            final int int2 = dataInputStream.readInt();
            final String utf10 = dataInputStream.readUTF();
            final long long3 = dataInputStream.readLong();
            final boolean boolean1 = dataInputStream.readBoolean();
            final boolean boolean2 = dataInputStream.readBoolean();
            final boolean boolean3 = dataInputStream.readBoolean();
            final double double1 = dataInputStream.readDouble();
            int int3 = -1;
            if (byte1 >= 4) {
                int3 = dataInputStream.readInt();
            }
            array[i] = new StopPredictionInfo(utf4, utf5, utf6, utf8, utf7, utf9, int2, utf10, long3, boolean1, boolean2, boolean3, double1, int3);
        }
        return new StopPrediction(utf, utf2, utf3, int1, long1, long2, array);
    }
    
    private static StopPrediction b(final DataInputStream dataInputStream) {
        final String utf = dataInputStream.readUTF();
        final String utf2 = dataInputStream.readUTF();
        final String utf3 = dataInputStream.readUTF();
        final int int1 = dataInputStream.readInt();
        long long1 = Long.MAX_VALUE;
        long long2 = Long.MAX_VALUE;
        final int unsignedByte;
        final StopPredictionInfo[] array = new StopPredictionInfo[unsignedByte = dataInputStream.readUnsignedByte()];
        final long[] array2 = new long[unsignedByte];
        for (int i = 0; i < unsignedByte; ++i) {
            array2[i] = (dataInputStream.readInt() & 0xFFFFFFFFL) * 1000L + dataInputStream.readUnsignedShort();
        }
        dataInputStream.readShort();
        for (int j = 0; j < unsignedByte; ++j) {
            array[j] = new StopPredictionInfo(dataInputStream.readUTF(), utf, utf2, utf3, dataInputStream.readUTF(), null, -1, null, array2[j], dataInputStream.readBoolean(), false, false, 1.0, -1);
        }
        long long3 = 0L;
        try {
            dataInputStream.mark(8);
            if ((long3 = dataInputStream.readLong()) != 1250801764255L) {
                throw new IOException("could not read magic number");
            }
        }
        catch (IOException ex) {
            dataInputStream.reset();
        }
        if (long3 == 1250801764255L) {
            final byte byte1 = dataInputStream.readByte();
            int int2 = dataInputStream.readInt();
            if (byte1 >= 1) {
                long1 = dataInputStream.readLong();
                long2 = dataInputStream.readLong();
                int2 -= 16;
            }
            for (int k = 0; k < int2; ++k) {
                dataInputStream.readByte();
            }
        }
        return new StopPrediction(utf, utf2, utf3, int1, long1, long2, array);
    }
}
