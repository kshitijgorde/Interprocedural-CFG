// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.util;

import org.xidget.ifeature.tree.ITreeWidgetFeature;
import org.xidget.IXidget;

public class XidgetUtil
{
    public static IXidget findTreeRoot(IXidget xidget) {
        for (IXidget xidget2 = xidget.getParent(); xidget2 != null; xidget2 = xidget2.getParent()) {
            if (xidget2.getFeature(ITreeWidgetFeature.class) == null) {
                return xidget;
            }
            xidget = xidget2;
        }
        return xidget;
    }
}
