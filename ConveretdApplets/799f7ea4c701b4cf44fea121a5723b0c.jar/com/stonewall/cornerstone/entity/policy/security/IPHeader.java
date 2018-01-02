// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity.policy.security;

import com.stonewall.cornerstone.entity.ServiceSet;
import java.util.Iterator;
import com.stonewall.cornerstone.entity.IPService;
import org.xmodel.ModelAlgorithms;
import org.xmodel.Xlate;
import java.util.List;
import com.stonewall.cornerstone.entity.EntityReference;
import com.stonewall.cornerstone.entity.policy.Endpoint;
import org.xmodel.IModelObject;
import org.xmodel.Element;
import com.stonewall.cornerstone.entity.policy.Selector;

public class IPHeader extends Selector
{
    public static final String tag = "en:ipHeader";
    
    public IPHeader() {
        super("en:ipHeader");
        this.root.addChild(new Element("en:source"));
        this.root.addChild(new Element("en:destination"));
        this.root.addChild(new Element("en:services"));
        this.root.getCreateChild("en:negate").setValue("false");
    }
    
    public IPHeader(final IModelObject root) {
        super(root);
    }
    
    public IPHeader(final Endpoint source, final Endpoint destination) {
        this();
        this.setSource(source);
        this.setDestination(destination);
    }
    
    public IPHeader(final Endpoint source, final Endpoint destination, final EntityReference service, final Direction direction, final boolean negate) {
        this();
        this.setSource(source);
        this.setDestination(destination);
        this.setDirection(direction);
        this.setNegate(negate);
        this.getServiceSet().addService(service);
    }
    
    public IPHeader(final Endpoint source, final Endpoint destination, final EntityReference service, final boolean negate) {
        this();
        this.setSource(source);
        this.setDestination(destination);
        this.setNegate(negate);
        this.getServiceSet().addService(service);
    }
    
    @Override
    public void setSource(final Endpoint ep) {
        final IModelObject source = this.root.getFirstChild("en:source");
        source.removeChildren();
        source.addChild(ep.getRoot().cloneTree());
    }
    
    @Override
    public Endpoint getSource() {
        final List<IModelObject> children = this.root.getFirstChild("en:source").getChildren();
        final IModelObject ref = children.get(0);
        return Endpoint.createEndpoint(ref);
    }
    
    @Override
    public Endpoint getDestination() {
        final List<IModelObject> children = this.root.getFirstChild("en:destination").getChildren();
        final IModelObject ref = children.get(0);
        return Endpoint.createEndpoint(ref);
    }
    
    @Override
    public void setDestination(final Endpoint ep) {
        final IModelObject destination = this.root.getFirstChild("en:destination");
        destination.removeChildren();
        destination.addChild(ep.getRoot().cloneTree());
    }
    
    public void setNegate(final boolean value) {
        this.root.getFirstChild("en:negate").setValue(String.valueOf(value));
    }
    
    public boolean getNegate() {
        return new Boolean(Xlate.get(this.root.getFirstChild("en:negate"), (String)null));
    }
    
    public void setDirection(final String value) {
        final IModelObject direction = this.root.getCreateChild("en:direction");
        direction.setValue(value);
    }
    
    public String getDirection() {
        final IModelObject e = this.root.getFirstChild("en:direction");
        if (e != null) {
            return Xlate.get(e, (String)null);
        }
        return null;
    }
    
    @Override
    public void reverse() {
        final IModelObject src = this.root.getFirstChild("en:source");
        final IModelObject dst = this.root.getFirstChild("en:destination");
        final IModelObject srcClone = src.cloneTree();
        src.removeChildren();
        ModelAlgorithms.moveChildren(dst, src);
        ModelAlgorithms.moveChildren(srcClone, dst);
        final IModelObject dir = this.root.getFirstChild("en:direction");
        if (dir != null) {
            dir.setValue(Xlate.get(dir, (String)null).equalsIgnoreCase("inbound") ? "outbound" : "inbound");
        }
    }
    
    @Override
    public boolean isInverse(final Selector s) {
        final IPHeader h = (IPHeader)s;
        return this.getSource().equals(h.getDestination()) && this.getDestination().equals(h.getSource()) && !this.getDirection().equals(h.getDirection()) && this.getServiceSet().equals(h.getServiceSet());
    }
    
    public void reverseServices() {
        final List<IModelObject> l = this.root.getFirstChild("en:services").getChildren("en:ipService");
        for (final IModelObject e : l) {
            final IPService s = new IPService(e);
            s.reverse();
        }
    }
    
    @Override
    public void setDirection(final Direction value) {
        this.setDirection(value.name());
    }
    
    @Override
    public void setEgress(final EntityReference ref) {
        final IModelObject egress = this.root.getCreateChild("en:egress");
        egress.addChild(ref.getRoot());
    }
    
    @Override
    public EntityReference getEgress() {
        final IModelObject egress = this.root.getFirstChild("en:egress");
        if (egress == null) {
            return null;
        }
        return new EntityReference(egress.getChild(0));
    }
    
    @Override
    public void setIngress(final EntityReference ref) {
        final IModelObject ingress = this.root.getCreateChild("en:ingress");
        ingress.addChild(ref.getRoot());
    }
    
    @Override
    public EntityReference getIngress() {
        final IModelObject ingress = this.root.getFirstChild("en:ingress");
        if (ingress == null) {
            return null;
        }
        return new EntityReference(ingress.getChild(0));
    }
    
    @Override
    public Selector clone() {
        final IModelObject e = this.getRoot().cloneTree();
        return new IPHeader(e);
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o instanceof IPHeader) {
            final IPHeader header = (IPHeader)o;
            if (this.getSource().equals(header.getSource()) && this.getDestination().equals(header.getDestination())) {
                final ServiceSet set1 = this.getServiceSet();
                final ServiceSet set2 = header.getServiceSet();
                return set1.equals(set2);
            }
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        return this.getSource().hashCode();
    }
}
