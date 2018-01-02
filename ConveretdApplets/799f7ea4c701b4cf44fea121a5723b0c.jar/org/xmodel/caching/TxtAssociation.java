// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.caching;

import org.xmodel.external.CachingException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import org.xmodel.IModelObject;

public class TxtAssociation extends AbstractFileAssociation
{
    private static final String[] E;
    
    static {
        E = new String[] { ".txt", ".css", ".html", ".htm", ".java", ".rtf", ".pl", ".py" };
    }
    
    @Override
    public String[] getExtensions() {
        return TxtAssociation.E;
    }
    
    @Override
    public void apply(final IModelObject modelObject, final String s, final InputStream inputStream) throws CachingException {
        try {
            final char[] array = new char[65536];
            final StringBuilder sb = new StringBuilder();
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            while (bufferedReader.ready()) {
                final int read = bufferedReader.read(array, 0, array.length);
                if (read > 0) {
                    sb.append(array, 0, read);
                }
            }
            modelObject.setValue(sb.toString());
        }
        catch (Exception ex) {
            throw new CachingException("Unable read text file: " + s, ex);
        }
    }
}
