// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xaction;

import org.xmodel.CanonicalPath;
import org.xmodel.ModelAlgorithms;
import org.xmodel.Xlate;
import org.xmodel.IModelObject;
import org.xmodel.log.Log;

public class Conventions
{
    private static Log A;
    
    static {
        Conventions.A = Log.getLog(Conventions.class.getName());
    }
    
    public static String getVarName(final IModelObject modelObject, final boolean b, final String... array) {
        final String value = Xlate.get(modelObject, "var", (String)null);
        if (value != null) {
            return value;
        }
        for (final String s : array) {
            final String value2 = Xlate.get(modelObject, s, (String)null);
            if (value2 != null) {
                Conventions.A.warnf("Deprecated use of '%s' attribute, use 'var' instead at %s", s, ModelAlgorithms.createIdentityPath(modelObject));
                return value2;
            }
        }
        if (b) {
            throw createException(modelObject, "Attribute 'var' is required");
        }
        return null;
    }
    
    public static XActionException createException(final IModelObject modelObject, final String s) {
        final CanonicalPath identityPath = ModelAlgorithms.createIdentityPath(modelObject);
        final StringBuilder sb = new StringBuilder();
        sb.append(s);
        sb.append(" at ");
        sb.append(identityPath);
        return new XActionException(sb.toString());
    }
}
