// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.jms.msg;

import org.xmodel.IModelObject;
import org.xmodel.log.Log;
import org.xmodel.xpath.expression.IExpression;

public class DocumentSelector extends RegistrySelector
{
    private IExpression keyPath;
    static final Log log;
    
    static {
        log = Log.getLog(DocumentSelector.class);
    }
    
    public DocumentSelector(final IExpression xpath, final String methodName) {
        super(methodName);
        this.keyPath = xpath;
    }
    
    @Override
    public boolean select(final JmsMessage message, final IModelObject content) {
        try {
            if (this.keyPath.queryFirst(content) != null) {
                return true;
            }
        }
        catch (Exception e) {
            DocumentSelector.log.error(message, e);
        }
        return false;
    }
}
