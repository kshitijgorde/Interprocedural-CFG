// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.dsp.diff;

import org.xmodel.diff.DefaultXmlMatcher;

public class DefaultMatcher extends DefaultXmlMatcher
{
    public DefaultMatcher() {
        final String[] ignoreAttributes = { "id", "referenced", "sticky", "mutable" };
        this.ignoreAttributes(ignoreAttributes);
        final String[] ignoreElements = { "en:name", "en:description" };
        this.ignoreElements(ignoreElements);
    }
}
