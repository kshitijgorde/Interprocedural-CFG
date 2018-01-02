// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity;

import org.xmodel.xml.IXmlIO;
import com.stonewall.cornerstone.utility.ModelBuilder;
import org.xmodel.xpath.expression.IExpression;
import org.xmodel.xpath.XPath;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import org.xmodel.Element;
import org.xmodel.log.Log;
import org.xmodel.IModelObject;

public class ServiceSet
{
    private IModelObject root;
    static final Log log;
    
    static {
        log = Log.getLog(ServiceSet.class);
    }
    
    public ServiceSet() {
        this.root = new Element("en:services");
    }
    
    public ServiceSet(final IModelObject e) {
        this.root = e;
    }
    
    public void addService(final Service svc) {
        final EntityReference ref = svc.getReference();
        this.addService(ref);
    }
    
    public void addService(final EntityReference ref) {
        this.root.addChild(ref.cloneContent());
    }
    
    public Service getFirstService() {
        final EntityReference ref = this.getServices().get(0);
        return (Service)ref.getReferent();
    }
    
    public List<EntityReference> getServices() {
        final List<EntityReference> services = new ArrayList<EntityReference>();
        final List<IModelObject> l = this.root.getChildren();
        for (final IModelObject e : l) {
            final EntityReference ref = new EntityReference(e);
            if (e.getFirstChild("en:protocol") != null) {
                ref.setExpanded(true);
            }
            services.add(ref);
        }
        return services;
    }
    
    public void removeService(final EntityReference service) {
        try {
            final IExpression path = XPath.createExpression("./*[@id=$id]");
            path.setVariable("id", service.getId());
            final IModelObject e = path.queryFirst(this.root);
            e.removeFromParent();
        }
        catch (Exception e2) {
            ServiceSet.log.error(this, e2);
        }
    }
    
    public boolean contains(final ServiceSet set) {
        final List<EntityReference> l1 = set.getServices();
        final List<EntityReference> l2 = this.getServices();
        for (final EntityReference e1 : l1) {
            final Service svc1 = (Service)e1.getReferent();
            boolean found = false;
            for (final EntityReference e2 : l2) {
                final Service svc2 = (Service)e2.getReferent();
                if (svc2.contains(svc1)) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof ServiceSet)) {
            return false;
        }
        final ServiceSet set = (ServiceSet)o;
        final List<EntityReference> l1 = set.getServices();
        final List<EntityReference> l2 = this.getServices();
        if (l1.size() != l2.size()) {
            return false;
        }
        for (final EntityReference e1 : l1) {
            boolean found = false;
            for (final EntityReference e2 : l2) {
                if (e1.equals(e2)) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                return false;
            }
        }
        return true;
    }
    
    public IModelObject getRoot() {
        return this.root;
    }
    
    @Override
    public String toString() {
        final ModelBuilder builder = new ModelBuilder();
        return builder.writeModel(this.getRoot(), IXmlIO.Style.printable);
    }
}
