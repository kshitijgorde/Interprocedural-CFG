// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.compress;

import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.io.OutputStream;
import java.io.ByteArrayOutputStream;
import org.xmodel.IModelObject;
import org.xmodel.IModelObjectFactory;

public abstract class AbstractCompressor implements ICompressor
{
    protected IModelObjectFactory factory;
    protected ISerializer serializer;
    
    protected AbstractCompressor() {
        this.serializer = new DefaultSerializer();
    }
    
    @Override
    public void setFactory(final IModelObjectFactory factory) {
        this.factory = factory;
    }
    
    @Override
    public void setSerializer(final ISerializer serializer) {
        this.serializer = serializer;
    }
    
    @Override
    public byte[] compress(final IModelObject modelObject) throws CompressorException {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        this.compress(modelObject, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }
    
    @Override
    public IModelObject decompress(final byte[] array, final int n) throws CompressorException {
        return this.decompress(new ByteArrayInputStream(array, n, array.length - n));
    }
}
