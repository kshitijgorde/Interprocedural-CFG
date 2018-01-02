// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.diff;

import org.xmodel.IModelObject;
import org.xmodel.diff.ExactXmlMatcher;

public class EntityContentMatcher extends ExactXmlMatcher
{
    @Override
    public boolean shouldDiff(final IModelObject object, final String attrName, final boolean lhs) {
        return attrName == null || !attrName.equals("id");
    }
}
