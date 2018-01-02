// 
// Decompiled by Procyon v0.5.30
// 

package com.fluendo.plugin;

import com.fluendo.utils.MemUtils;
import com.fluendo.jst.Buffer;
import com.fluendo.jst.Sink;

public class FakeSink extends Sink
{
    protected int preroll(final Buffer buffer) {
        System.out.println("received preroll buffer " + buffer);
        return 0;
    }
    
    protected int render(final Buffer buffer) {
        System.out.println("received buffer " + buffer);
        if (buffer.object != null) {
            System.out.println("object " + buffer.object);
        }
        else {
            MemUtils.dump(buffer.data, 0, buffer.length);
        }
        return 0;
    }
    
    public String getFactoryName() {
        return "fakesink";
    }
}
