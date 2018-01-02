// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity.policy.security;

import com.stonewall.cornerstone.entity.policy.IPInterfaceEndpoint;
import com.stonewall.cornerstone.entity.policy.Endpoint;
import com.stonewall.cornerstone.tp.query.TunnelQuery;
import org.xmodel.xpath.expression.IExpression;
import org.xmodel.xpath.XPath;
import com.stonewall.cornerstone.db.DbException;
import java.util.Collections;
import com.stonewall.cornerstone.entity.db.SegmentDbAccess;
import java.util.ArrayList;
import org.xmodel.Xlate;
import java.util.Iterator;
import org.xmodel.Element;
import com.stonewall.cornerstone.entity.Segment;
import java.util.List;
import com.stonewall.cornerstone.entity.IPInterface;
import com.stonewall.cornerstone.entity.EntityReference;
import org.xmodel.IModelObject;
import com.stonewall.cornerstone.entity.EntityFactory;
import com.stonewall.cornerstone.entity.Entity;

public class SecurityTunnel extends Entity
{
    public static final String tag;
    
    static {
        tag = EntityFactory.EntityType.securityTunnel.getQualifiedName();
    }
    
    public SecurityTunnel() {
        super(SecurityTunnel.tag);
        this.init();
    }
    
    public SecurityTunnel(final IModelObject root) {
        super(root);
    }
    
    protected SecurityTunnel(final String id) {
        super(SecurityTunnel.tag, id);
    }
    
    public SecurityTunnel(final EntityReference if1Id, final EntityReference if2Id) {
        this();
        this.setInterfaces(new Interface(if1Id), new Interface(if2Id));
    }
    
    public SecurityTunnel(final String name, final TunnelDef def, final IPInterface if1, final IPInterface if2, final List<Segment> route) {
        this();
        this.setName(name);
        this.setDef(def);
        this.setInterfaces(if1, if2);
        final IModelObject r = this.root.getCreateChild("en:route");
        if (!route.isEmpty()) {
            for (final Segment s : route) {
                final IModelObject o = new Element("en:segment");
                o.setValue(s.getId());
                r.addChild(o);
            }
        }
    }
    
    private void init() {
        this.root.addChild(new Element("en:name"));
        this.root.addChild(new Element("en:interface"));
        this.root.addChild(new Element("en:interface"));
    }
    
    private void setDef(final TunnelDef def) {
        final IModelObject action = this.root.getCreateChild("en:definition");
        action.addChild(def.getRoot().cloneTree());
    }
    
    public TunnelDef getDef() {
        return this.createDef(this.root.getFirstChild("en:definition").getChildren().get(0));
    }
    
    public TunnelDef createDef(final IModelObject root) {
        return null;
    }
    
    public void setName(final String name) {
        this.setChild(this.root, "en:name", name);
    }
    
    @Override
    public String getName() {
        return Xlate.get(this.root.getFirstChild("en:name"), (String)null);
    }
    
    public void setDescription(final String desc) {
        this.setChild(this.root, "en:desc", desc);
    }
    
    public String getDescription() {
        return Xlate.get(this.root.getFirstChild("en:description"), (String)null);
    }
    
    public List<Segment> getSegments() {
        final List<IModelObject> segEs = this.root.getFirstChild("en:route").getChildren("en:segment");
        final List<String> ids = new ArrayList<String>();
        for (final IModelObject e : segEs) {
            ids.add(Xlate.get(e, (String)null));
        }
        try {
            return new SegmentDbAccess().fetchById(ids);
        }
        catch (DbException e2) {
            SecurityTunnel.log.error(this, e2);
            return Collections.emptyList();
        }
    }
    
    public void removeSegment(final String segment) {
        final IExpression path = XPath.createExpression("./en:route/en:segment[text() == $id]");
        path.setVariable("id", segment);
        final IModelObject seg = path.queryFirst(this.root);
        seg.removeFromParent();
    }
    
    public Interface[] getInterfaces() {
        int i = 0;
        final Interface[] result = new Interface[2];
        final List<IModelObject> eps = this.root.getChildren("en:interface");
        for (final IModelObject ep : eps) {
            if (i >= result.length) {
                break;
            }
            result[i++] = new Interface(ep);
        }
        return result;
    }
    
    public Interface getInterface(final String id) {
        final List<IModelObject> interfaces = this.root.getChildren("en:interface");
        for (final IModelObject o : interfaces) {
            final IModelObject ipIface = o.getChild("en:ipInterface", id);
            if (ipIface != null) {
                return new Interface(o);
            }
        }
        return null;
    }
    
    public EntityReference[] getInterfaceRefs() {
        int i = 0;
        final EntityReference[] result = new EntityReference[2];
        final List<IModelObject> eps = this.root.getChildren("en:interface");
        for (final IModelObject ep : eps) {
            if (i >= result.length) {
                break;
            }
            final IModelObject e = ep.getChild(0);
            result[i++] = new EntityReference(e);
        }
        return result;
    }
    
    public void setInterfaces(final IPInterface endA, final IPInterface endB) {
        this.root.removeChildren("en:interface");
        IModelObject end = new Element("en:interface");
        end.addChild(endA.getReference().cloneContent());
        this.root.addChild(end);
        end = new Element("en:interface");
        end.addChild(endB.getReference().cloneContent());
        this.root.addChild(end);
    }
    
    public void setInterfaces(final Interface endA, final Interface endB) {
        this.root.removeChildren("en:interface");
        this.root.addChild(endA.root);
        this.root.addChild(endB.root);
    }
    
    public Interface otherEnd(final Interface iface) {
        Interface[] interfaces;
        for (int length = (interfaces = this.getInterfaces()).length, i = 0; i < length; ++i) {
            final Interface other = interfaces[i];
            if (!other.equals(iface)) {
                return other;
            }
        }
        return null;
    }
    
    public void setGateway(final LocalGateway local, final RemoteGateway remote) {
        final IModelObject gateways = this.root.getCreateChild("en:gateways");
        gateways.removeChildren();
        gateways.addChild(local.getRoot());
        gateways.addChild(remote.getRoot());
    }
    
    @Override
    public boolean exists() {
        final TunnelQuery tQuery = new TunnelQuery();
        return tQuery.getTunnel(this.getId()) != null;
    }
    
    @Override
    protected List<IModelObject> idsToInvalidate(final IModelObject e) {
        final IExpression path = XPath.createExpression("./@id");
        final List<IModelObject> ids = path.query(e, null);
        return ids;
    }
    
    public SecurityTunnel clone() {
        return new SecurityTunnel(this.getRoot().cloneTree());
    }
    
    public class Interface
    {
        private IModelObject root;
        
        public Interface(final EntityReference ref) {
            (this.root = new Element("en:interface")).addChild(ref.getRoot().cloneTree());
        }
        
        protected Interface(final IModelObject e) {
            this.root = e;
        }
        
        public void setLocal(final boolean value) {
            this.root.setAttribute("local", Boolean.toString(value));
        }
        
        public boolean isLocal() {
            return Boolean.parseBoolean(Xlate.get(this.root, "local", (String)null));
        }
        
        public Endpoint getEndpoint() {
            return new IPInterfaceEndpoint(this.root.getCreateChild("en:ipInterface"));
        }
        
        @Override
        public boolean equals(final Object o) {
            if (!(o instanceof Interface)) {
                return false;
            }
            final Interface iface = (Interface)o;
            return this.getEndpoint().equals(iface.getEndpoint());
        }
    }
}
