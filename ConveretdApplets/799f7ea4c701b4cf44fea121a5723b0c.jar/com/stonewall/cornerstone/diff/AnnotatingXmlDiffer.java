// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.diff;

import org.xmodel.IChangeSet;
import org.xmodel.IModelObject;
import org.xmodel.IModelObjectFactory;
import org.xmodel.ModelObjectFactory;
import org.xmodel.diff.IXmlMatcher;
import org.xmodel.diff.DefaultXmlMatcher;
import org.xmodel.log.Log;
import org.xmodel.diff.XmlDiffer;
import org.xmodel.diff.IXmlDiffer;

public class AnnotatingXmlDiffer implements IXmlDiffer
{
    XmlDiffer differ;
    static final Log log;
    
    static {
        log = Log.getLog(AnnotatingXmlDiffer.class);
    }
    
    public AnnotatingXmlDiffer() {
        (this.differ = new XmlDiffer()).setMatcher(new DefaultXmlMatcher(true));
        this.differ.setFactory(new ModelObjectFactory());
    }
    
    @Override
    public void setMatcher(final IXmlMatcher matcher) {
        this.differ.setMatcher(matcher);
    }
    
    @Override
    public IXmlMatcher getMatcher() {
        return this.differ.getMatcher();
    }
    
    @Override
    public boolean diff(final IModelObject lhs, final IModelObject rhs, final IChangeSet changeSet) {
        return this.differ.diff(lhs, rhs, changeSet);
    }
    
    @Override
    public boolean diffAndApply(final IModelObject lhs, final IModelObject rhs) {
        final AnnotatedChangeSet changeSet = new AnnotatedChangeSet();
        final boolean result = this.diff(lhs, rhs, changeSet);
        AnnotatingXmlDiffer.log.debug("Annotated Changes:\n" + changeSet.getRecords());
        changeSet.applyChanges();
        return result;
    }
    
    @Override
    public boolean diffAttributes(final IModelObject lhs, final IModelObject rhs, final IChangeSet changeSet) {
        return this.differ.diffAttributes(lhs, rhs, changeSet);
    }
    
    @Override
    public boolean diffChildren(final IModelObject lhs, final IModelObject rhs, final IChangeSet changeSet) {
        return this.differ.diffChildren(lhs, rhs, changeSet);
    }
}
