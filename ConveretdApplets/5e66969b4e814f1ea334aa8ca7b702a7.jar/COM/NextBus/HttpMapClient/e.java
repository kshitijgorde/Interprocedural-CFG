// 
// Decompiled by Procyon v0.5.30
// 

package COM.NextBus.HttpMapClient;

import COM.NextBus.Predictor2.rmi.VehicleInfo;
import java.util.Collections;
import java.util.Map;
import COM.NextBus.DBModel.AdherenceRange;
import COM.NextBus.Predictor2Comm.TimeReport;
import COM.NextBus.Predictor2Comm.TitleInfo;
import java.util.List;

public final class e
{
    private final int a;
    private final List b;
    private final TitleInfo c;
    private final TitleInfo d;
    private final TimeReport e;
    private final List f;
    private final AdherenceRange g;
    private final List h;
    private final boolean i;
    private final double j;
    private final double k;
    private final double l;
    private final double m;
    private final String n;
    private final double o;
    private final Map p;
    private final boolean q;
    
    public e() {
        this.a = -1;
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = null;
        this.g = null;
        this.h = null;
        this.i = false;
        this.j = 0.0;
        this.k = 0.0;
        this.l = 0.0;
        this.m = 0.0;
        this.n = "mph";
        this.o = 2.23693629;
        this.p = null;
        this.q = false;
    }
    
    public e(final int a, final List list, final TitleInfo c, final TitleInfo d, final TimeReport e, final List list2, final AdherenceRange g, final List h, final boolean i, final double j, final double k, final double l, final double m, final String n, final double o, final Map map, final boolean q) {
        this.a = a;
        this.b = Collections.unmodifiableList((List<?>)list);
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = Collections.unmodifiableList((List<?>)list2);
        this.g = g;
        this.h = h;
        this.i = i;
        this.j = j;
        this.k = k;
        this.l = l;
        this.m = m;
        this.n = n;
        this.o = o;
        this.p = ((map == null) ? null : Collections.unmodifiableMap((Map<?, ?>)map));
        this.q = q;
    }
    
    public final int a() {
        return this.a;
    }
    
    public final List b() {
        return this.b;
    }
    
    public final TitleInfo c() {
        return this.c;
    }
    
    public final TitleInfo d() {
        return this.d;
    }
    
    public final TimeReport e() {
        return this.e;
    }
    
    public final List f() {
        return this.f;
    }
    
    public final AdherenceRange g() {
        return this.g;
    }
    
    public final List h() {
        return this.h;
    }
    
    public final boolean i() {
        return this.i;
    }
    
    public final boolean j() {
        return this.q;
    }
    
    public final double k() {
        return this.j;
    }
    
    public final double l() {
        return this.k;
    }
    
    public final double m() {
        return this.l;
    }
    
    public final double n() {
        return this.m;
    }
    
    public final String o() {
        return this.n;
    }
    
    public final double p() {
        return this.o;
    }
    
    public final VehicleInfo a(final String s) {
        if (this.p == null) {
            return null;
        }
        return this.p.get(s);
    }
    
    public final String toString() {
        return "AgencyData [_rev=" + this.a + ", _sortedRouteTags=" + this.b + ", _defaultGroupTitleInfo=" + this.c + ", _wirelessTitleInfo=" + this.d + ", _timeReport=" + this.e + ", _yardZones=" + this.f + ", _adherenceRange=" + this.g + ", _sortedVehicleIds=" + this.h + ", _isSilentAlarmEnabled=" + this.i + ", _minLatitude=" + this.j + ", _maxLatitude=" + this.k + ", _minLongitude=" + this.l + ", _maxLongitude=" + this.m + ", _speedUnitDisplay=" + this.n + ", _speedUnitConversionFactorFromMPS=" + this.o + ", _vehicleInfo=" + this.p + ", _isEngineDiagnosticsEnabled=" + this.q + "]";
    }
}
