// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.templates;

import org.apache.xpath.XPath;

public class WhiteSpaceInfo extends ElemTemplate
{
    private boolean m_shouldStripSpace;
    
    public WhiteSpaceInfo(final XPath matchPattern, final boolean shouldStripSpace, final Stylesheet thisSheet) {
        this.m_shouldStripSpace = shouldStripSpace;
        this.setMatch(matchPattern);
        this.setStylesheet(thisSheet);
    }
    
    public boolean getShouldStripSpace() {
        return this.m_shouldStripSpace;
    }
    
    public void recompose(final StylesheetRoot root) {
        root.recomposeWhiteSpaceInfo(this);
    }
}
