// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity;

import com.stonewall.cornerstone.entity.policy.HostEndpoint;
import com.stonewall.cornerstone.entity.policy.Endpoint;
import com.stonewall.cornerstone.tp.query.GraphQuery;
import java.util.Collections;
import java.util.List;
import com.stonewall.cornerstone.entity.util.IpAddr;
import org.xmodel.Xlate;
import com.stonewall.cornerstone.db.DbException;
import com.stonewall.cornerstone.entity.db.HostDbAccess;
import org.xmodel.IModelObject;
import org.xmodel.Element;
import com.stonewall.cornerstone.entity.policy.IEndpoint;

public class Host extends Entity implements IEndpoint, IAddress
{
    public static final String tag;
    
    static {
        tag = EntityFactory.EntityType.host.getQualifiedName();
    }
    
    public Host() {
        super(Host.tag);
        this.root.addChild(new Element("en:tags"));
    }
    
    public Host(final String id) {
        super(Host.tag, id);
        this.root.addChild(new Element("en:tags"));
    }
    
    public Host(final IModelObject root) {
        super(root);
    }
    
    @Override
    public Entity fetch(final Label label) throws DbException {
        return new HostDbAccess(label).fetchById(this.getId());
    }
    
    @Override
    public void insert(final Label label) throws DbException {
        new HostDbAccess(label).insert(this);
    }
    
    @Override
    public void update(final Label label) throws DbException {
        new HostDbAccess(label).update(this);
    }
    
    @Override
    public void delete(final Label label) throws DbException {
        new HostDbAccess(label).delete(this);
    }
    
    public void setName(final String value) {
        this.root.getCreateChild("en:name").setValue(value);
    }
    
    @Override
    public String getName() {
        return Xlate.get(this.root.getFirstChild("en:name"), (String)null);
    }
    
    public void setIpAddress(IpAddr value) {
        if (value.getNetmaskLength() != 32) {
            try {
                value = new IpAddr(value.getAddress(), 32);
            }
            catch (Exception e) {
                Host.log.error(this, e);
            }
        }
        this.root.getCreateChild("en:address").setValue(value.toString());
    }
    
    public void setIpAddress(final String value) {
        this.root.getCreateChild("en:address").setValue(value);
    }
    
    @Override
    public IpAddr getIpAddress() {
        if (this.root.getFirstChild("en:address") == null) {
            return null;
        }
        try {
            return new IpAddr(Xlate.get(this.root.getFirstChild("en:address"), (String)null));
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    @Override
    public List<IpAddr> getIpAddresses() {
        return Collections.singletonList(this.getIpAddress());
    }
    
    public String getAddressString() {
        return Xlate.get(this.root.getFirstChild("en:address"), (String)null);
    }
    
    @Override
    public String getDisplayName() {
        String display = this.getName();
        if (display == null || display.equals("")) {
            final String addr = this.getAddressString();
            if (addr != null) {
                display = addr;
            }
        }
        return display;
    }
    
    @Override
    public boolean exists() {
        final GraphQuery query = new GraphQuery();
        return query.endpointExists(this.getId());
    }
    
    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof Host)) {
            return false;
        }
        if (super.equals(o)) {
            return true;
        }
        final Host host = (Host)o;
        return this.getIpAddress().equals((Object)host.getIpAddress());
    }
    
    @Override
    public Endpoint createEndpoint() {
        final HostEndpoint ep = new HostEndpoint(this.getReference());
        return ep;
    }
}
