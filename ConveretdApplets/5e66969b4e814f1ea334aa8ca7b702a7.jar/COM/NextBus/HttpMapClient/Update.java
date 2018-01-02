// 
// Decompiled by Procyon v0.5.30
// 

package COM.NextBus.HttpMapClient;

import java.util.Iterator;
import java.io.ObjectOutputStream;
import COM.NextBus.Predictor2Comm.StopPrediction;
import java.io.InputStream;
import java.io.DataInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import COM.NextBus.Predictor2Comm.BusReport;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.ObjectInputStream;
import java.util.List;
import java.util.Map;
import java.io.Serializable;

public class Update implements Serializable
{
    private static final long serialVersionUID = -2328554568047295084L;
    private Map _stopPredictionsByRouteTag;
    private List _busReports;
    private List _busEvents;
    private transient Map a;
    private transient List b;
    private transient List c;
    private int _rev;
    
    public final Map a() {
        if (this.a == null) {
            throw new IllegalStateException("object not deserialized");
        }
        return this.a;
    }
    
    public final List b() {
        if (this.b == null) {
            throw new IllegalStateException("object not deserialized");
        }
        return this.b;
    }
    
    public final List c() {
        if (this.c == null) {
            throw new IllegalStateException("object not deserialized");
        }
        return this.c;
    }
    
    public final int d() {
        return this._rev;
    }
    
    private void readObject(final ObjectInputStream objectInputStream) {
        this._rev = objectInputStream.readInt();
        final int int1 = objectInputStream.readInt();
        this.a = new HashMap(int1 + 1);
        for (int i = 0; i < int1; ++i) {
            this.a.put(objectInputStream.readUTF(), a(objectInputStream, false));
        }
        this.b = a(objectInputStream, true);
        this.c = a(objectInputStream, true);
    }
    
    private static List a(final ObjectInputStream objectInputStream, final boolean b) {
        final int int1 = objectInputStream.readInt();
        final ArrayList list = new ArrayList<BusReport>(int1);
        for (int i = 0; i < int1; ++i) {
            int int2;
            byte[] array;
            int j;
            for (array = new byte[int2 = objectInputStream.readInt()], j = 0; j < int2; j += objectInputStream.read(array, j, int2 - j)) {}
            if (j != int2) {
                throw new IOException("Update.readEventContents() read " + j + " bytes, but expected " + int2 + " bytes .");
            }
            final DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(array));
            Serializable s;
            if (b) {
                s = BusReport.a(dataInputStream);
            }
            else {
                s = StopPrediction.a(dataInputStream);
            }
            dataInputStream.close();
            list.add((BusReport)s);
        }
        return list;
    }
    
    private void writeObject(final ObjectOutputStream objectOutputStream) {
        objectOutputStream.writeInt(this._rev);
        final int n = (this._stopPredictionsByRouteTag == null) ? 0 : this._stopPredictionsByRouteTag.size();
        objectOutputStream.writeInt(n);
        if (n > 0) {
            for (final String s : this._stopPredictionsByRouteTag.keySet()) {
                objectOutputStream.writeUTF(s);
                a((List)this._stopPredictionsByRouteTag.get(s), objectOutputStream);
            }
        }
        a(this._busReports, objectOutputStream);
        a(this._busEvents, objectOutputStream);
    }
    
    private static void a(List list, final ObjectOutputStream objectOutputStream) {
        if (list == null) {
            list = new ArrayList<c>();
        }
        objectOutputStream.writeInt(list.size());
        final Iterator<Object> iterator = list.iterator();
        while (iterator.hasNext()) {
            final byte[] a;
            final int length = (a = iterator.next().a()).length;
            objectOutputStream.writeInt(length);
            objectOutputStream.write(a, 0, length);
        }
    }
    
    public String toString() {
        return "rev=" + this._rev + ",StopPredictions=" + this.a + ",BusReports=" + this.b + ",BusEvents=" + this.c + ".";
    }
}
