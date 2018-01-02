// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.caching;

import org.xmodel.external.CachingException;
import org.xmodel.compress.TabularCompressor;
import java.io.InputStream;
import org.xmodel.IModelObject;

public class XipAssociation extends AbstractFileAssociation
{
    private static final String[] C;
    
    static {
        C = new String[] { ".xip" };
    }
    
    @Override
    public String[] getExtensions() {
        return XipAssociation.C;
    }
    
    @Override
    public void apply(final IModelObject modelObject, final String s, final InputStream inputStream) throws CachingException {
        try {
            modelObject.addChild(new TabularCompressor().decompress(inputStream));
        }
        catch (Exception ex) {
            throw new CachingException("Unable to parse xml in compressed file: " + s, ex);
        }
    }
}
