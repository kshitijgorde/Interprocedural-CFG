// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.dsp.diff;

import com.stonewall.cornerstone.entity.policy.ReferenceData;
import java.util.HashMap;
import org.xmodel.diff.IXmlMatcher;
import com.stonewall.cornerstone.entity.EntityFactory;
import java.util.Map;

public class MatcherFactory
{
    private Map<EntityFactory.EntityType, IXmlMatcher> matchers;
    
    public MatcherFactory() {
        this.matchers = new HashMap<EntityFactory.EntityType, IXmlMatcher>();
    }
    
    protected void register(final EntityFactory.EntityType type, final IXmlMatcher matcher) {
        this.matchers.put(type, matcher);
    }
    
    public IXmlMatcher getMatcher(final ReferenceData.RefData ref) {
        return this.getMatcher(ref.getEntityReference().getEntityType());
    }
    
    public IXmlMatcher getMatcher(final EntityFactory.EntityType type) {
        final IXmlMatcher matcher = this.matchers.get(type);
        if (matcher != null) {
            return matcher;
        }
        return new DefaultMatcher();
    }
}
