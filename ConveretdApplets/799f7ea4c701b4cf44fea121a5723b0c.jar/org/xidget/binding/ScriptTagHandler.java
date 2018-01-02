// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.binding;

import org.xidget.config.TagException;
import org.xmodel.xaction.XActionDocument;
import org.xidget.ifeature.IScriptFeature;
import org.xidget.config.ifeature.IXidgetFeature;
import org.xmodel.IModelObject;
import org.xidget.config.TagProcessor;
import org.xidget.config.ITagHandler;

public class ScriptTagHandler implements ITagHandler
{
    @Override
    public boolean filter(final TagProcessor tagProcessor, final ITagHandler tagHandler, final IModelObject modelObject) {
        final IXidgetFeature xidgetFeature = (tagHandler != null) ? tagHandler.getFeature(IXidgetFeature.class) : null;
        return xidgetFeature != null && xidgetFeature.getXidget().getFeature(IScriptFeature.class) != null;
    }
    
    @Override
    public boolean enter(final TagProcessor tagProcessor, final ITagHandler tagHandler, final IModelObject modelObject) throws TagException {
        final IScriptFeature scriptFeature = ((tagHandler != null) ? tagHandler.getFeature(IXidgetFeature.class) : null).getXidget().getFeature(IScriptFeature.class);
        final XActionDocument xActionDocument = new XActionDocument(modelObject);
        xActionDocument.addPackage("org.xidget.xaction");
        xActionDocument.addPackage("org.xidget.layout.xaction");
        scriptFeature.setScript(modelObject.getType(), xActionDocument.createScript(new String[0]));
        return false;
    }
    
    @Override
    public void exit(final TagProcessor tagProcessor, final ITagHandler tagHandler, final IModelObject modelObject) throws TagException {
    }
    
    @Override
    public <T> T getFeature(final Class<T> clazz) {
        return null;
    }
}
