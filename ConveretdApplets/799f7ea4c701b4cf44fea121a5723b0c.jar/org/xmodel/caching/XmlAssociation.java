// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.caching;

import org.xmodel.external.CachingException;
import org.xmodel.xml.XmlIO;
import java.io.InputStream;
import org.xmodel.IModelObject;

public class XmlAssociation extends AbstractFileAssociation
{
    private static final String[] D;
    
    static {
        D = new String[] { ".xml", ".xsd", ".dtd" };
    }
    
    @Override
    public String[] getExtensions() {
        return XmlAssociation.D;
    }
    
    @Override
    public void apply(final IModelObject modelObject, final String s, final InputStream inputStream) throws CachingException {
        int available = 0;
        try {
            available = inputStream.available();
            modelObject.addChild(new XmlIO().read(inputStream));
        }
        catch (Exception ex) {
            if (available == 0) {
                return;
            }
            throw new CachingException("Unable to parse xml in file: " + s, ex);
        }
    }
}
