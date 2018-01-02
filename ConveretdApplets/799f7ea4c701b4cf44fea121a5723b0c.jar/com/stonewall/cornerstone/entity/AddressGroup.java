// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity;

import com.stonewall.cornerstone.entity.policy.AddressGroupEndpoint;
import com.stonewall.cornerstone.entity.policy.Endpoint;
import org.xmodel.xpath.expression.IExpression;
import java.util.Iterator;
import org.xmodel.xpath.XPath;
import java.util.ArrayList;
import org.xmodel.Xlate;
import com.stonewall.cornerstone.db.DbException;
import com.stonewall.cornerstone.entity.db.AddressGroupDbAccess;
import org.xmodel.Element;
import org.xmodel.IModelObject;
import com.stonewall.cornerstone.entity.util.IpAddr;
import java.util.List;
import com.stonewall.cornerstone.entity.policy.IEndpoint;

public class AddressGroup extends Entity implements IEndpoint, IComposite, IAddress
{
    private List<IpAddr> cache;
    public static final String tag;
    
    static {
        tag = EntityFactory.EntityType.addressGroup.getQualifiedName();
    }
    
    public AddressGroup() {
        super(AddressGroup.tag);
        this.cache = null;
        this.init();
    }
    
    public AddressGroup(final String id) {
        super(AddressGroup.tag, id);
        this.cache = null;
    }
    
    public AddressGroup(final IModelObject e) {
        super(e);
        this.cache = null;
    }
    
    private void init() {
        this.root.addChild(new Element("en:name"));
        this.root.addChild(new Element("en:entries"));
    }
    
    @Override
    public Entity fetch(final Label label) throws DbException {
        return new AddressGroupDbAccess(label).fetchById(this.getId());
    }
    
    @Override
    public void insert(final Label label) throws DbException {
        new AddressGroupDbAccess(label).insert(this);
    }
    
    @Override
    public void delete(final Label label) throws DbException {
        new AddressGroupDbAccess(label).delete(this);
    }
    
    @Override
    public void update(final Label label) throws DbException {
        new AddressGroupDbAccess(label).update(this);
    }
    
    public void setName(final String value) {
        this.setChild(this.root, "en:name", value);
    }
    
    @Override
    public String getName() {
        return Xlate.get(this.root.getFirstChild("en:name"), (String)null);
    }
    
    public void setDescription(final String value) {
        this.root.getCreateChild("en:description").setValue(value);
    }
    
    public String getDescription() {
        return Xlate.get(this.root.getFirstChild("en:description"), (String)null);
    }
    
    public void removeEntries() {
        this.root.getFirstChild("en:entries").removeChildren();
    }
    
    public void removeEntries(final List<EntityReference> refs) {
        final List<IModelObject> ids = new ArrayList<IModelObject>();
        for (final EntityReference ref : refs) {
            ids.add(ref.getRoot());
        }
        final IExpression path = XPath.createExpression("en:entries/*[@id=$ids/@id]");
        path.setVariable("ids", ids);
        final List<IModelObject> children = path.query(this.getRoot(), null);
        for (final IModelObject child : children) {
            child.removeFromParent();
        }
    }
    
    public boolean contains(final EntityReference reference) {
        boolean result = false;
        for (final EntityReference r : this.getParts()) {
            if (r.equals(reference)) {
                result = true;
                break;
            }
        }
        return result;
    }
    
    public boolean contains(final IpAddr address) {
        boolean result = false;
        for (final IpAddr a : this.getIpAddresses()) {
            if (a.equals((Object)address)) {
                result = true;
                break;
            }
        }
        return result;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof AddressGroup)) {
            return false;
        }
        if (super.equals(o)) {
            return true;
        }
        final AddressGroup ag = (AddressGroup)o;
        final List<IpAddr> lista = this.getIpAddresses();
        final List<IpAddr> listb = ag.getIpAddresses();
        if (lista.size() != listb.size()) {
            return false;
        }
        for (final IpAddr addra : lista) {
            boolean found = false;
            for (final IpAddr addrb : listb) {
                if (addra.equals((Object)addrb)) {
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
    public List<IpAddr> getIpAddresses() {
        return (this.cache == null) ? this.buildCache() : this.cache;
    }
    
    @Override
    public IpAddr getIpAddress() {
        throw new UnsupportedOperationException();
    }
    
    public int getSize() {
        return this.getParts().size();
    }
    
    private List<IpAddr> buildCache() {
        this.cache = new ArrayList<IpAddr>();
        final List<EntityReference> references = this.getParts();
        for (final EntityReference ref : references) {
            try {
                final IModelObject e = ref.getReferent().getRoot();
                this.cache.add(this.ipAddr(e));
            }
            catch (Exception e2) {
                AddressGroup.log.error(this, e2);
            }
        }
        return this.cache;
    }
    
    IpAddr ipAddr(final IModelObject entry) throws Exception {
        final String address = Xlate.get(entry.getFirstChild("en:address"), (String)null);
        return new IpAddr(address);
    }
    
    @Override
    public boolean exists() {
        try {
            return new AddressGroupDbAccess().exists(this.getId());
        }
        catch (DbException e) {
            return false;
        }
    }
    
    @Override
    public Endpoint createEndpoint() {
        final AddressGroupEndpoint ep = new AddressGroupEndpoint(this.getReference());
        return ep;
    }
    
    @Override
    public List<EntityReference> getParts() {
        final List<EntityReference> entries = new ArrayList<EntityReference>();
        final List<IModelObject> l = this.root.getFirstChild("en:entries").getChildren();
        for (final IModelObject e : l) {
            entries.add(new EntityReference(e));
        }
        return entries;
    }
    
    @Override
    public void addPart(final EntityReference entry) {
        final IModelObject entries = this.root.getCreateChild("en:entries");
        entries.addChild(entry.cloneContent());
    }
}
