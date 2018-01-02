// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.caching;

import org.xmodel.external.CachingException;
import org.xmodel.ModelObject;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import org.xmodel.IModelObject;

public class CsvAssociation extends AbstractFileAssociation
{
    private static final String[] B;
    
    static {
        B = new String[] { ".csv" };
    }
    
    @Override
    public String[] getExtensions() {
        return CsvAssociation.B;
    }
    
    @Override
    public void apply(final IModelObject modelObject, final String s, final InputStream inputStream) throws CachingException {
        try {
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            int n = 1;
            while (bufferedReader.ready()) {
                final String line = bufferedReader.readLine();
                final ModelObject modelObject2 = new ModelObject("entry", Integer.toString(n++));
                this.A(line, modelObject2);
                modelObject.addChild(modelObject2);
            }
        }
        catch (Exception ex) {
            throw new CachingException("Unable read text file: " + s, ex);
        }
    }
    
    private void A(final String s, final IModelObject modelObject) {
        boolean b = false;
        ModelObject modelObject2 = new ModelObject("field");
        int n = 0;
        for (int i = 0; i < s.length(); ++i) {
            if (!b && s.charAt(i) == ',') {
                if (n < i) {
                    modelObject2.setValue(s.substring(n, i));
                }
                modelObject.addChild(modelObject2);
                modelObject2 = new ModelObject("field");
                n = i + 1;
            }
            else if (s.charAt(i) == '\"') {
                b = !b;
            }
        }
        if (n != s.length() - 1) {
            modelObject2.setValue(s.substring(n));
            modelObject.addChild(modelObject2);
        }
    }
}
