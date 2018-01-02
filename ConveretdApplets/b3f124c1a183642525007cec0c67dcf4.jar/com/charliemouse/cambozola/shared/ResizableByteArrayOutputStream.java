// 
// Decompiled by Procyon v0.5.30
// 

package com.charliemouse.cambozola.shared;

import java.io.ByteArrayOutputStream;

class ResizableByteArrayOutputStream extends ByteArrayOutputStream
{
    public void resize(final int count) {
        super.count = count;
    }
}
