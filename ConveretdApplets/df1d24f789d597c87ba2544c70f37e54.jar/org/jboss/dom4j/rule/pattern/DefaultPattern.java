// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.dom4j.rule.pattern;

import org.jboss.dom4j.Node;
import org.jboss.dom4j.NodeFilter;
import org.jboss.dom4j.rule.Pattern;

public class DefaultPattern implements Pattern
{
    private NodeFilter filter;
    
    public DefaultPattern(final NodeFilter filter) {
        this.filter = filter;
    }
    
    public boolean matches(final Node node) {
        return this.filter.matches(node);
    }
    
    public double getPriority() {
        return 0.5;
    }
    
    public Pattern[] getUnionPatterns() {
        return null;
    }
    
    public short getMatchType() {
        return 0;
    }
    
    public String getMatchesNodeName() {
        return null;
    }
}
