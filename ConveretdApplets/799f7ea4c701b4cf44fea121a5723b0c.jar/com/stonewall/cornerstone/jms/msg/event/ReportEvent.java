// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.jms.msg.event;

import com.stonewall.cornerstone.entity.EntityReference;
import java.util.Collection;
import com.stonewall.cornerstone.jms.msg.JmsMessage;
import org.xmodel.IModelObjectFactory;
import org.xmodel.ModelAlgorithms;
import org.xmodel.Element;
import java.util.List;
import java.util.Collections;
import org.xmodel.IModelObject;

public class ReportEvent extends ServiceEvent
{
    public static void send(final Action a, final Status s, final String reportType, final IModelObject query) {
        new ReportEvent(a, s, reportType, query, null).send();
    }
    
    public static void send(final Action a, final Status s, final String reportType, final IModelObject query, final String reason) {
        new ReportEvent(a, s, reportType, query, reason).send();
    }
    
    public ReportEvent(final Action a, final Status s, final String reportType, final IModelObject query, final String reason) {
        super(s, reason, Subtype.report, Collections.EMPTY_LIST, a);
        this.root.getCreateChild("evt:reportType").setValue(reportType);
        if (query != null) {
            final IModelObject queryClone = new Element("evt:query");
            ModelAlgorithms.copyChildren(query, queryClone, (IModelObjectFactory)null);
            this.root.addChild(queryClone);
        }
    }
    
    public ReportEvent(final Action a, final String reportType, final IModelObject query) {
        super(a, Subtype.report, Collections.EMPTY_LIST);
        this.root.getCreateChild("evt:reportType").setValue(reportType);
        if (query != null) {
            final IModelObject queryClone = new Element("evt:query");
            ModelAlgorithms.copyChildren(query, queryClone, (IModelObjectFactory)null);
            this.root.addChild(queryClone);
        }
    }
    
    public ReportEvent(final JmsMessage message, final IModelObject root) throws Exception {
        super(message, root);
    }
    
    public ReportEvent(final IModelObject e) {
        super(e);
    }
    
    public Collection<EntityReference> getEntityReferences() {
        return (Collection<EntityReference>)Collections.EMPTY_LIST;
    }
}
