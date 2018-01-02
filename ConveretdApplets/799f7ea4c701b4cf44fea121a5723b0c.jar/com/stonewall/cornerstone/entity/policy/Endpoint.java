// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity.policy;

import org.xmodel.xml.IXmlIO;
import com.stonewall.cornerstone.utility.ModelBuilder;
import org.xmodel.Xlate;
import com.stonewall.cornerstone.entity.IComposite;
import java.util.Collections;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import com.stonewall.cornerstone.entity.Entity;
import com.stonewall.cornerstone.entity.EntityReference;
import com.stonewall.cornerstone.entity.EntityFactory;
import org.xmodel.log.Log;
import org.xmodel.IModelObject;

public abstract class Endpoint
{
    protected IModelObject root;
    static final Log log;
    
    static {
        log = Log.getLog(Endpoint.class);
    }
    
    public static Endpoint createEndpoint(final IModelObject e) {
        Endpoint ep = null;
        final EntityFactory.EntityType t = EntityFactory.EntityType.valueOf(e);
        switch (t) {
            case any: {
                ep = new AnyEndpoint(e);
                break;
            }
            case network: {
                ep = new NetworkEndpoint(e);
                break;
            }
            case addressGroup: {
                ep = new AddressGroupEndpoint(e);
                break;
            }
            case host: {
                ep = new HostEndpoint(e);
                break;
            }
            case ipInterface: {
                ep = new IPInterfaceEndpoint(e);
                break;
            }
            default: {
                Endpoint.log.error("Unknown endpoint type: " + t.name());
                break;
            }
        }
        return ep;
    }
    
    public Endpoint(final EntityReference ref) {
        this.root = ref.clone().getRoot();
    }
    
    public Endpoint(final IModelObject root) {
        this.root = root;
    }
    
    public String getId() {
        return this.getReference().getId();
    }
    
    public EntityFactory.EntityType getType() {
        return this.getReference().getEntityType();
    }
    
    public EntityReference getReference() {
        return new EntityReference(this.root);
    }
    
    public Entity getReferent() {
        final EntityReference er = this.getReference();
        return er.getReferent();
    }
    
    public List<String> getPartIds() {
        final List<String> ids = new ArrayList<String>();
        for (final EntityReference ref : this.getParts()) {
            ids.add(ref.getId());
        }
        return ids;
    }
    
    public List<EntityReference> getParts() {
        final Entity e = this.getReferent();
        if (e == null) {
            return Collections.emptyList();
        }
        if (e instanceof IComposite) {
            return ((IComposite)e).getParts();
        }
        return Collections.singletonList(e.getReference());
    }
    
    public IModelObject getRoot() {
        return this.root;
    }
    
    public boolean isInternet() {
        return false;
    }
    
    public boolean isAny() {
        return false;
    }
    
    public String getTerminationPoint() {
        return Xlate.get(this.root.getFirstChild("en:termination"), (String)null);
    }
    
    public void setTerminationPoint(final EntityReference ref) {
        this.root.getCreateChild("en:termination").setValue(ref.getId());
    }
    
    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof Endpoint)) {
            return false;
        }
        final Endpoint ep = (Endpoint)o;
        return this.getId().equals(ep.getId());
    }
    
    public abstract String toErrorString();
    
    public Endpoint clone() {
        return createEndpoint(this.getRoot().cloneTree());
    }
    
    @Override
    public String toString() {
        final ModelBuilder builder = new ModelBuilder();
        return builder.writeModel(this.getRoot(), IXmlIO.Style.printable);
    }
}
