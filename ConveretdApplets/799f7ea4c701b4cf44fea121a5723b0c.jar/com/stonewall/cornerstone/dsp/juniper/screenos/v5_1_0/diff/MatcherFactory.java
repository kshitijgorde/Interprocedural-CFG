// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.dsp.juniper.screenos.v5_1_0.diff;

import org.xmodel.xpath.expression.IExpression;
import org.xmodel.xpath.XPath;
import java.util.List;
import org.xmodel.IModelObject;
import org.xmodel.diff.IXmlMatcher;
import com.stonewall.cornerstone.entity.EntityFactory;
import com.stonewall.cornerstone.dsp.diff.DefaultMatcher;

public class MatcherFactory extends com.stonewall.cornerstone.dsp.diff.MatcherFactory
{
    private DefaultMatcher tunnelMatcher;
    private DefaultMatcher p2Matcher;
    private ServiceMatcher serviceMatcher;
    
    public MatcherFactory() {
        this.tunnelMatcher = new DefaultMatcher();
        this.p2Matcher = new DefaultMatcher();
        this.serviceMatcher = new ServiceMatcher();
        this.tunnelMatcher.ignoreElement("en:saOptions");
        this.register(EntityFactory.EntityType.securityTunnel, this.tunnelMatcher);
        final String[] ignoreElements = { "en:pfs", "en:lifesize" };
        this.p2Matcher.ignoreElements(ignoreElements);
        this.register(EntityFactory.EntityType.p2Proposal, this.p2Matcher);
        this.register(EntityFactory.EntityType.ipService, this.serviceMatcher);
    }
    
    protected class ServiceMatcher extends DefaultMatcher
    {
        ServiceMatcher() {
            final String[] ignoreElements = { "en:group" };
            this.ignoreElements(ignoreElements);
        }
        
        @Override
        public boolean shouldDiff(final IModelObject object, final boolean lhs) {
            return super.shouldDiff(object, lhs) && (!object.isType("en:start") || !object.getParent().isType("en:srcPort")) && (!object.isType("en:end") || !object.getParent().isType("en:srcPort"));
        }
        
        @Override
        public int findMatch(final List<IModelObject> children, final IModelObject child) {
            if (child.isType("en:srcPort")) {
                for (int i = 0; i < children.size(); ++i) {
                    final IModelObject candidate = children.get(i);
                    if (candidate.isType("en:srcPort")) {
                        final IExpression samePort = XPath.createExpression("$lhs/en:srcPort/en:start = $lhs/en:dstPort/en:start and \n$lhs/en:srcPort/en:end = $lhs/en:dstPort/en:end");
                        final IExpression matchPort = XPath.createExpression("$lhs/en:start = $rhs/en:start or \n$lhs/en:start = 0 and $lhs/en:end = 65535");
                        samePort.setVariable("lhs", child.getParent());
                        if (samePort.evaluateBoolean()) {
                            matchPort.setVariable("lhs", candidate);
                            matchPort.setVariable("rhs", child);
                            if (matchPort.evaluateBoolean()) {
                                return children.indexOf(candidate);
                            }
                        }
                        samePort.setVariable("lhs", candidate.getParent());
                        if (samePort.evaluateBoolean()) {
                            matchPort.setVariable("lhs", child);
                            matchPort.setVariable("rhs", candidate);
                            if (matchPort.evaluateBoolean()) {
                                return children.indexOf(candidate);
                            }
                        }
                    }
                }
            }
            return super.findMatch(children, child);
        }
    }
}
