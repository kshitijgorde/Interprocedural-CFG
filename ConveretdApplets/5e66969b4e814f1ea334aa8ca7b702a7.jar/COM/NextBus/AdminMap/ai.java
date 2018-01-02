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

final class ai
{
    private int a;
    
    private ai(final byte b) {
        this.a = af.i;
    }
    
    private synchronized boolean a() {
        if (this.a != af.i) {
            this.a = af.i;
            return true;
        }
        return false;
    }
    
    private synchronized void a(final boolean b) {
        if (b) {
            this.a = af.j;
            return;
        }
        this.a = af.i();
    }
    
    private synchronized boolean b() {
        return this.a == af.j;
    }
}
