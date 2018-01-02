// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity;

import com.stonewall.cornerstone.entity.policy.NetworkEndpoint;
import com.stonewall.cornerstone.entity.policy.Endpoint;
import com.stonewall.cornerstone.tp.query.GraphQuery;
import java.util.Collections;
import java.util.List;
import org.xmodel.Xlate;
import com.stonewall.cornerstone.db.DbException;
import com.stonewall.cornerstone.entity.db.NetworkDbAccess;
import org.xmodel.Element;
import com.stonewall.cornerstone.entity.util.IpAddr;
import com.stonewall.cornerstone.entity.util.InternetAddress;
import org.xmodel.IModelObject;
import com.stonewall.cornerstone.entity.policy.IEndpoint;

public class Network extends Entity implements IEndpoint, IAddress
{
    public static final String tag;
    public static final String INTERNET_ID = "INTERNET";
    
    static {
        tag = EntityFactory.EntityType.network.getQualifiedName();
    }
    
    public Network() {
        super(Network.tag);
        this.init();
    }
    
    public Network(final String id) {
        super(Network.tag, id);
        this.init();
    }
    
    public Network(final IModelObject root) {
        super(root);
    }
    
    private void init() {
        this.root.setAttribute("toplevel", "true");
        this.root.setAttribute("managed", "true");
        if (this.isInternet()) {
            this.root.setAttribute("managed", String.valueOf(true));
            this.root.setAttribute("public", String.valueOf(true));
            this.setName("Internet");
            this.setIpAddress(InternetAddress.getInstance());
        }
        this.root.addChild(new Element("en:tags"));
    }
    
    @Override
    public Entity fetch(final Label label) throws DbException {
        return new NetworkDbAccess(label).fetchById(this.getId());
    }
    
    @Override
    public void insert(final Label label) throws DbException {
        new NetworkDbAccess(label).insert(this);
    }
    
    @Override
    public void update(final Label label) throws DbException {
        new NetworkDbAccess(label).update(this);
    }
    
    @Override
    public void delete(final Label label) throws DbException {
        new NetworkDbAccess(label).delete(this);
    }
    
    public void setName(final String value) {
        this.setChild(this.root, "en:name", value);
    }
    
    @Override
    public String getName() {
        return Xlate.get(this.root.getFirstChild("en:name"), (String)null);
    }
    
    public void setIpAddress(final IpAddr value) {
        this.setChild(this.root, "en:address", value.toString());
    }
    
    public void setIpAddress(final String value) {
        this.setChild(this.root, "en:address", value);
    }
    
    @Override
    public IpAddr getIpAddress() {
        if (this.isInternet()) {
            return InternetAddress.getInstance();
        }
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
    
    public Network getParent() {
        final String parentId = this.getParentId();
        if (parentId == null) {
            return null;
        }
        final Network network = new Network(this.getParentId());
        try {
            return (Network)network.fetch();
        }
        catch (Exception dbe) {
            return null;
        }
    }
    
    public boolean hasParent() {
        return this.getParentId() != null && !this.getParentId().equals("");
    }
    
    public boolean isToplevel() {
        return Boolean.parseBoolean(Xlate.get(this.root, "toplevel", (String)null));
    }
    
    public void setTopLevel(final boolean value) {
        this.root.setAttribute("toplevel", Boolean.toString(value));
    }
    
    public boolean isManaged() {
        return Boolean.parseBoolean(Xlate.get(this.root, "managed", (String)null));
    }
    
    public boolean isPublic() {
        return this.root.getAttribute("public") != null && Boolean.parseBoolean(Xlate.get(this.root, "public", (String)null));
    }
    
    public boolean isInternet() {
        return this.getId().equals("INTERNET");
    }
    
    public void setManaged(final boolean flag) {
        if (this.isInternet()) {
            return;
        }
        this.root.setAttribute("managed", String.valueOf(flag));
        if (flag) {
            this.setPublic(false);
        }
    }
    
    public void setPublic(final boolean flag) {
        if (this.isInternet()) {
            return;
        }
        this.root.setAttribute("public", String.valueOf(flag));
        if (flag) {
            this.setManaged(false);
        }
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
        if (!(o instanceof Network)) {
            return false;
        }
        if (super.equals(o)) {
            return true;
        }
        final Network net = (Network)o;
        return this.getIpAddress().equals((Object)net.getIpAddress());
    }
    
    @Override
    public Endpoint createEndpoint() {
        final NetworkEndpoint nep = new NetworkEndpoint(this.getReference());
        return nep;
    }
}
