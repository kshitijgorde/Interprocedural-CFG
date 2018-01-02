// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.swing;

import org.xmodel.external.CachingException;
import org.xmodel.compress.TabularCompressor;
import org.xmodel.compress.ISerializer;
import org.xmodel.compress.serial.JavaSerializer;
import javax.swing.ImageIcon;
import org.xmodel.compress.DefaultSerializer;
import java.io.InputStream;
import org.xmodel.IModelObject;
import org.xmodel.caching.AbstractFileAssociation;

public class SwingXipAssociation extends AbstractFileAssociation
{
    private static final String[] extensions;
    
    static {
        extensions = new String[] { ".xip" };
    }
    
    @Override
    public String[] getExtensions() {
        return SwingXipAssociation.extensions;
    }
    
    @Override
    public void apply(final IModelObject modelObject, final String s, final InputStream inputStream) throws CachingException {
        try {
            final DefaultSerializer serializer = new DefaultSerializer();
            serializer.register(ImageIcon.class, new JavaSerializer());
            final TabularCompressor tabularCompressor = new TabularCompressor();
            tabularCompressor.setSerializer(serializer);
            modelObject.addChild(tabularCompressor.decompress(inputStream));
        }
        catch (Exception ex) {
            throw new CachingException("Unable to parse xml in compressed file: " + s, ex);
        }
    }
}
