// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.templates;

import org.apache.xpath.XPath;

public class WhiteSpaceInfo extends ElemTemplate
{
    static final long serialVersionUID = 6389208261999943836L;
    private boolean m_shouldStripSpace;
    
    public boolean getShouldStripSpace() {
        return this.m_shouldStripSpace;
    }
    
    public WhiteSpaceInfo(final Stylesheet thisSheet) {
        this.setStylesheet(thisSheet);
    }
    
    public WhiteSpaceInfo(final XPath matchPattern, final boolean shouldStripSpace, final Stylesheet thisSheet) {
        this.m_shouldStripSpace = shouldStripSpace;
        this.setMatch(matchPattern);
        this.setStylesheet(thisSheet);
    }
    
    public void recompose(final StylesheetRoot root) {
        root.recomposeWhiteSpaceInfo(this);
    }
}
