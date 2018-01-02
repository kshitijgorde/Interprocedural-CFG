// 
// Decompiled by Procyon v0.5.30
// 

package com.fluendo.jheora;

import com.jcraft.jogg.Buffer;

class ExtractMVectorComponentB implements ExtractMVectorComponent
{
    public int extract(final Buffer buffer) {
        return buffer.readB(5) * -((buffer.readB(1) << 1) - 1);
    }
}
