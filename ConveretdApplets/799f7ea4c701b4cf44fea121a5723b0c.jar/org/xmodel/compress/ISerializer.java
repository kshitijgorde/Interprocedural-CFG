// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.compress;

import java.io.DataOutput;
import java.io.IOException;
import java.io.DataInput;

public interface ISerializer
{
    Object readObject(final DataInput p0) throws IOException, ClassNotFoundException, CompressorException;
    
    int writeObject(final DataOutput p0, final Object p1) throws IOException, CompressorException;
}
