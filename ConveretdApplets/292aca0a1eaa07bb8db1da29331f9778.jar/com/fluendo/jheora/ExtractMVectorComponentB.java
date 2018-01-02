// 
// Decompiled by Procyon v0.5.30
// 

package com.fluendo.jheora;

import com.jcraft.jogg.Buffer;

class ExtractMVectorComponentB implements ExtractMVectorComponent
{
    public int extract(final Buffer opb) {
        return opb.readB(5) * -((opb.readB(1) << 1) - 1);
    }
}
