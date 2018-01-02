// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.compress;

import java.io.InputStream;
import java.io.OutputStream;
import org.xmodel.IModelObject;
import org.xmodel.IModelObjectFactory;

public interface ICompressor
{
    void setFactory(final IModelObjectFactory p0);
    
    void setSerializer(final ISerializer p0);
    
    byte[] compress(final IModelObject p0) throws CompressorException;
    
    IModelObject decompress(final byte[] p0, final int p1) throws CompressorException;
    
    void compress(final IModelObject p0, final OutputStream p1) throws CompressorException;
    
    IModelObject decompress(final InputStream p0) throws CompressorException;
}
