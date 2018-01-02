// 
// Decompiled by Procyon v0.5.30
// 

package COM.NextBus.AdminMap;

import COM.NextBus.Predictor2Comm.StopPrediction;

class m
{
    private StopPrediction a;
    private long b;
    
    public m(final StopPrediction a) {
        this.a = a;
        this.b = System.currentTimeMillis();
    }
    
    public final boolean a() {
        return this.b < System.currentTimeMillis() - 20000L;
    }
}
