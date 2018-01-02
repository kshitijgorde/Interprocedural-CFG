// 
// Decompiled by Procyon v0.5.30
// 

package com.fluendo.plugin;

import com.fluendo.utils.MemUtils;
import com.fluendo.jst.Buffer;
import com.fluendo.jst.Sink;

public class FakeSink extends Sink
{
    protected int preroll(final Buffer buf) {
        System.out.println("received preroll buffer " + buf);
        return 0;
    }
    
    protected int render(final Buffer buf) {
        System.out.println("received buffer " + buf);
        if (buf.object != null) {
            System.out.println("object " + buf.object);
        }
        else {
            MemUtils.dump(buf.data, 0, buf.length);
        }
        return 0;
    }
    
    public String getFactoryName() {
        return "fakesink";
    }
}
