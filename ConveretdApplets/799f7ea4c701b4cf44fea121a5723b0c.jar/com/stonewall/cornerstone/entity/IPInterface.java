// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity;

import com.stonewall.cornerstone.entity.policy.IPInterfaceEndpoint;
import com.stonewall.cornerstone.entity.policy.Endpoint;
import com.stonewall.cornerstone.tp.query.GraphQuery;
import java.util.Collections;
import java.util.List;
import com.stonewall.cornerstone.entity.util.IpAddr;
import org.xmodel.Xlate;
import org.xmodel.Element;
import com.stonewall.cornerstone.db.DbException;
import com.stonewall.cornerstone.entity.db.IPInterfaceDbAccess;
import org.xmodel.IModelObject;
import com.stonewall.cornerstone.entity.policy.IEndpoint;

public class IPInterface extends Entity implements IEndpoint, IAddress
{
    public static final String tag;
    
    static {
        tag = EntityFactory.EntityType.ipInterface.getQualifiedName();
    }
    
    public IPInterface() {
        super(IPInterface.tag);
        this.init();
    }
    
    public IPInterface(final String id) {
        super(IPInterface.tag, id);
    }
    
    public IPInterface(final IModelObject o) {
        super(o);
    }
    
    @Override
    public Entity fetch(final Label label) throws DbException {
        return new IPInterfaceDbAccess().fetchById(this.getId());
    }
    
    @Override
    public void insert(final Label label) throws DbException {
        new IPInterfaceDbAccess().insert(this);
    }
    
    @Override
    public void delete(final Label label) throws DbException {
        new IPInterfaceDbAccess().delete(this);
    }
    
    private void init() {
        this.root.setAttribute("traffic", "true");
        this.root.addChild(new Element("en:identifier"));
        this.root.addChild(new Element("en:address"));
    }
    
    public boolean hasIpAddress() {
        final String ip = Xlate.get(this.root.getFirstChild("en:address"), (String)null);
        return ip != null && !ip.equals("");
    }
    
    public void setIpAddress(final IpAddr addr) {
        if (addr == null) {
            return;
        }
        this.setChild(this.root, "en:address", addr.toString());
    }
    
    @Override
    public IpAddr getIpAddress() {
        if (!this.hasIpAddress()) {
            return null;
        }
        try {
            return new IpAddr(Xlate.get(this.root.getFirstChild("en:address"), (String)null));
        }
        catch (Exception e) {
            IPInterface.log.error(e);
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
    
    public String getFQDN() {
        return Xlate.get(this.root.getFirstChild("en:fqdn"), (String)null);
    }
    
    public void setFQDN(final String value) {
        if (value == null) {
            return;
        }
        this.setChild(this.root, "en:fqdn", value);
    }
    
    public String getZone() {
        return Xlate.get(this.root.getFirstChild("en:zone"), (String)null);
    }
    
    public void setZone(final String value) {
        if (value == null) {
            return;
        }
        this.setChild(this.root, "en:zone", value);
    }
    
    public String getIdentifier() {
        return Xlate.get(this.root.getFirstChild("en:identifier"), (String)null);
    }
    
    public void setIdentifier(final String value) {
        this.setChild(this.root, "en:identifier", value);
    }
    
    @Override
    public String getName() {
        return Xlate.get(this.root.getFirstChild("en:name"), (String)null);
    }
    
    public void setName(final String value) {
        this.setChild(this.root, "en:name", value);
    }
    
    public boolean update(final IPInterface iface) {
        boolean changed = false;
        if (this.getIpAddress() != null && iface.getIpAddress() != null && ((this.getIpAddress() == null && iface.getIpAddress() != null) || (this.getIpAddress() != null && iface.getIpAddress() == null) || !this.getIpAddress().equals((Object)iface.getIpAddress()))) {
            changed = true;
        }
        this.setIpAddress(iface.getIpAddress());
        this.setIdentifier(iface.getIdentifier());
        if (iface.getFQDN() != null) {
            this.setFQDN(iface.getFQDN());
        }
        if (iface.getZone() != null) {
            this.setZone(iface.getZone());
        }
        if (iface.getName() != null) {
            this.setName(iface.getName());
        }
        this.setTraffic(iface.isTraffic());
        return changed;
    }
    
    public void setTraffic(final boolean flag) {
        this.root.setAttribute("traffic", String.valueOf(flag));
    }
    
    public boolean isTraffic() {
        final String traffic = Xlate.get(this.root, "traffic", (String)null);
        return traffic == null || Boolean.parseBoolean(traffic);
    }
    
    @Override
    public boolean exists() {
        final GraphQuery query = new GraphQuery();
        return query.endpointExists(this.getId());
    }
    
    @Override
    public String getDisplayName() {
        String s = this.getName();
        if (s == null) {
            s = this.getIdentifier();
        }
        return s;
    }
    
    @Override
    public Endpoint createEndpoint() {
        final IPInterfaceEndpoint ep = new IPInterfaceEndpoint(this.getReference());
        return ep;
    }
}
