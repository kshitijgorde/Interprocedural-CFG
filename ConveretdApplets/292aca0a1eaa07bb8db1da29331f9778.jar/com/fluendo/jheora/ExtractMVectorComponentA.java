// 
// Decompiled by Procyon v0.5.30
// 

package com.fluendo.jheora;

import com.jcraft.jogg.Buffer;

class ExtractMVectorComponentA implements ExtractMVectorComponent
{
    public int extract(final Buffer opb) {
        switch (opb.readB(3)) {
            case 0: {
                return 0;
            }
            case 1: {
                return 1;
            }
            case 2: {
                return -1;
            }
            case 3: {
                return 2 - 4 * opb.readB(1);
            }
            case 4: {
                return 3 - 6 * opb.readB(1);
            }
            case 5: {
                return (4 + opb.readB(2)) * -((opb.readB(1) << 1) - 1);
            }
            case 6: {
                return (8 + opb.readB(3)) * -((opb.readB(1) << 1) - 1);
            }
            case 7: {
                return (16 + opb.readB(4)) * -((opb.readB(1) << 1) - 1);
            }
            default: {
                return 0;
            }
        }
    }
}
