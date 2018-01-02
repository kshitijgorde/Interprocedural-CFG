// 
// Decompiled by Procyon v0.5.30
// 

package COM.NextBus.HttpMapClient;

import java.net.MalformedURLException;
import java.net.URLConnection;
import java.io.ByteArrayOutputStream;
import java.net.URL;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ByteArrayInputStream;
import java.util.Date;
import java.text.SimpleDateFormat;
import COM.NextBus.AdminMap.O;
import java.util.Hashtable;
import COM.NextBus.Predictor2Comm.BusReport;
import java.util.Set;
import COM.NextBus.Predictor2Comm.PathInfo;
import java.util.ArrayList;
import COM.NextBus.Predictor2Comm.RouteInfoBase;
import COM.NextBus.Predictor2Comm.RouteInfo;
import java.util.Enumeration;
import COM.NextBus.DBModel.AdherenceRange;
import COM.NextBus.Predictor2Comm.TimeReport;
import COM.NextBus.Predictor2Comm.TitleInfo;
import java.util.Iterator;
import java.util.HashMap;
import java.util.Random;
import java.util.Map;
import java.util.List;

final class f implements Runnable
{
    private /* synthetic */ String a;
    private /* synthetic */ String b;
    private /* synthetic */ k c;
    private /* synthetic */ a d;
    
    f(final a d, final String a, final String b, final k c) {
        this.d = d;
        this.a = a;
        this.b = b;
        this.c = c;
    }
    
    public final void run() {
        try {
            final float n = this.d.n * COM.NextBus.HttpMapClient.a.e.nextFloat();
            try {
                Thread.sleep((int)(n * 1000.0f));
            }
            catch (InterruptedException ex) {}
            ++COM.NextBus.HttpMapClient.a.a;
            final Response a;
            if (((ResponseComponent)(a = this.d.b(this.a, this.b, this.c)).a().nextElement()).c() == 0) {
                ++COM.NextBus.HttpMapClient.a.b;
                return;
            }
            ++COM.NextBus.HttpMapClient.a.c;
            System.out.println(a);
        }
        catch (ConnectionException ex2) {
            ++COM.NextBus.HttpMapClient.a.d;
        }
    }
}
