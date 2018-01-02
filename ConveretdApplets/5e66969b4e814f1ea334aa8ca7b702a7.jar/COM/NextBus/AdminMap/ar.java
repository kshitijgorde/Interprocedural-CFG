// 
// Decompiled by Procyon v0.5.30
// 

package COM.NextBus.AdminMap;

import COM.NextBus.HttpMapClient.ConnectionException;
import COM.NextBus.HttpMapClient.ResponseComponent;
import COM.NextBus.HttpMapClient.Update;
import COM.NextBus.Predictor2Comm.TitleInfo;
import COM.NextBus.HttpMapClient.e;
import java.util.Hashtable;
import COM.NextBus.util.c;
import java.util.Iterator;
import COM.NextBus.HttpMapClient.l;
import java.util.Collections;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import COM.NextBus.HttpMapClient.b;
import COM.NextBus.HttpMapClient.a;

final class ar implements Runnable
{
    private /* synthetic */ af a;
    
    ar(final af a) {
        this.a = a;
    }
    
    public final void run() {
        af.a(this.a);
    }
}
