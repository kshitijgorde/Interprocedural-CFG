// 
// Decompiled by Procyon v0.5.30
// 

package COM.NextBus.DBModel;

import java.util.ArrayList;
import COM.NextBus.Predictor2Comm.Location;
import java.io.Serializable;

public class Polygon implements Serializable
{
    private static final long serialVersionUID = 1487028937285778544L;
    private String _stringRepresentation;
    private transient Location[] a;
    
    public int hashCode() {
        return 31 + ((this._stringRepresentation == null) ? 0 : this._stringRepresentation.hashCode());
    }
    
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (this.getClass() != o.getClass()) {
            return false;
        }
        final Polygon polygon = (Polygon)o;
        if (this._stringRepresentation == null) {
            if (polygon._stringRepresentation != null) {
                return false;
            }
        }
        else if (!this._stringRepresentation.equals(polygon._stringRepresentation)) {
            return false;
        }
        return true;
    }
    
    public final Location[] a() {
        if (this.a == null) {
            this.a = a(this._stringRepresentation);
        }
        return this.a;
    }
    
    public String toString() {
        return this._stringRepresentation;
    }
    
    private static Location[] a(final String s) {
        try {
            final ArrayList<Double> list = new ArrayList<Double>();
            final ArrayList<Double> list2 = (ArrayList<Double>)new ArrayList<Object>();
            int index = 1;
            while (true) {
                final int index2 = s.indexOf(40, index);
                final int index3 = s.indexOf(44, index2);
                index = s.indexOf(41, index3);
                if (index2 < 0 || index3 < 0 || index < 0) {
                    break;
                }
                final String substring = s.substring(index2 + 1, index3);
                final String substring2 = s.substring(index3 + 1, index);
                list.add(Double.valueOf(substring));
                list2.add(Double.valueOf(substring2));
            }
            final int size;
            final Location[] array = new Location[size = list.size()];
            for (int i = 0; i < size; ++i) {
                array[i] = new Location((double)list.get(i), (double)list2.get(i));
            }
            return array;
        }
        catch (NumberFormatException ex) {
            return new Location[0];
        }
    }
}
