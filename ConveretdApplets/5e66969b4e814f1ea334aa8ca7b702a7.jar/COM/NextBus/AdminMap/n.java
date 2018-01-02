// 
// Decompiled by Procyon v0.5.30
// 

package COM.NextBus.AdminMap;

import COM.NextBus.Predictor2.rmi.VehicleInfo;
import java.text.DecimalFormat;
import java.util.Date;
import java.awt.Point;
import COM.NextBus.DBModel.AdherenceRange;
import COM.NextBus.util.e;
import java.awt.Graphics2D;
import COM.NextBus.HttpMapClient.ConnectionException;
import COM.NextBus.Predictor2Comm.BusPrediction;
import COM.NextBus.util.c;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.awt.Font;
import java.awt.Color;
import COM.NextBus.Predictor2Comm.BusReport;
import java.util.List;
import java.util.Map;
import java.awt.Image;
import java.text.SimpleDateFormat;
import COM.NextBus.Predictor2Comm.Location;

final class n
{
    private int a;
    private int b;
    private int c;
    private int d;
    private /* synthetic */ aj e;
    
    n(final aj e) {
        this.e = e;
    }
    
    public final void a(final Location location, final Location location2) {
        this.a = location.c();
        this.b = location.d();
        this.c = ((location2 != null) ? location2.c() : this.a);
        this.d = ((location2 != null) ? location2.d() : this.b);
    }
    
    public final Location a() {
        return new Location(this.a, this.b);
    }
    
    public final Location b() {
        return new Location(this.c, this.d);
    }
    
    public final int c() {
        if (this.e.b.C().p()) {
            return this.c;
        }
        return this.a;
    }
    
    public final int d() {
        if (this.e.b.C().p()) {
            return this.d;
        }
        return this.b;
    }
}
