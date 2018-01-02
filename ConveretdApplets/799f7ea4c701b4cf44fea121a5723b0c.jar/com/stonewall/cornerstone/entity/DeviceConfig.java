// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity;

import com.stonewall.cornerstone.entity.policy.nat.NatPolicy;
import com.stonewall.cornerstone.entity.policy.security.DevicePolicy;
import com.stonewall.cornerstone.entity.policy.security.SecurityPolicy;
import java.util.Collection;
import java.util.Iterator;
import java.util.ArrayList;
import org.xmodel.Xlate;
import java.util.List;
import com.stonewall.cornerstone.db.DbException;
import com.stonewall.cornerstone.entity.db.DeviceConfigDbAccess;
import com.stonewall.cornerstone.entity.policy.ReferenceData;
import org.xmodel.IModelObject;

public class DeviceConfig extends Entity
{
    public static final String tag;
    
    static {
        tag = EntityFactory.EntityType.deviceConfig.getQualifiedName();
    }
    
    public DeviceConfig() {
        super(DeviceConfig.tag);
        this.init();
    }
    
    public DeviceConfig(final String id) {
        super(DeviceConfig.tag, id);
        this.init();
    }
    
    public DeviceConfig(final IModelObject root) {
        super(root);
    }
    
    public DeviceConfig(final EntityReference ref) {
        super(ref.getRoot());
        this.init();
    }
    
    private void init() {
        final IModelObject base = this.root.getCreateChild("en:base");
        base.getCreateChild("en:interfaces");
        final ReferenceData data = new ReferenceData();
        base.addChild(data.getRoot());
        final IModelObject ancillary = this.root.getCreateChild("en:ancillary");
        ancillary.getCreateChild("en:ties");
        this.root.getCreateChild("en:raw");
    }
    
    @Override
    public Entity fetch(final Label label) throws DbException {
        return new DeviceConfigDbAccess(label).fetchById(this.getId());
    }
    
    @Override
    public Entity fetchByParent(final String parent, final Label label) throws DbException {
        return new DeviceConfigDbAccess(label).fetchByParent(parent);
    }
    
    @Override
    public Entity fetchRefByParent(final String parent, final Label label) throws DbException {
        return new DeviceConfigDbAccess(label).fetchByParent(parent, true);
    }
    
    @Override
    public void insert(final Label label) throws DbException {
        new DeviceConfigDbAccess(label).insert(this);
    }
    
    @Override
    public void delete(final Label label) throws DbException {
        new DeviceConfigDbAccess(label).delete(this);
    }
    
    @Override
    public void update(final Label label) throws DbException {
        new DeviceConfigDbAccess(label).update(this);
    }
    
    public boolean hasContent() {
        return !this.getBaseElement().getChildren().isEmpty();
    }
    
    public IModelObject getAncillaryElement() {
        return this.root.getCreateChild("en:ancillary");
    }
    
    public IModelObject getBaseElement() {
        return this.root.getCreateChild("en:base");
    }
    
    public void replaceBaseElement(final IModelObject e) {
        this.root.removeChildren("en:base");
        this.root.addChild(e.cloneTree());
    }
    
    public void replaceAncillaryElement(final IModelObject e) {
        this.root.removeChildren("en:ancillary");
        this.root.addChild(e.cloneTree());
    }
    
    public void replaceContent(final IModelObject attachment) {
        final IModelObject clone = attachment.cloneTree();
        this.replaceContent(clone.getChildren());
    }
    
    public void replaceContent(final List<IModelObject> l) {
        this.root.removeChildren();
        IModelObject[] array;
        for (int length = (array = l.toArray(new IModelObject[0])).length, i = 0; i < length; ++i) {
            final IModelObject o = array[i];
            this.root.addChild(o);
        }
    }
    
    public String getChecksum() {
        return Xlate.get(this.getBaseElement().getCreateChild("en:checksum"), "");
    }
    
    public void setChecksum(final String value) {
        this.getBaseElement().getCreateChild("en:checksum").setValue(value);
    }
    
    public List<IPInterface> getInterfaces() {
        final List<IPInterface> l = new ArrayList<IPInterface>();
        final IModelObject interfaces = this.getBaseElement().getCreateChild("en:interfaces");
        final List<IModelObject> ifaces = interfaces.getChildren();
        for (final IModelObject e : ifaces) {
            final IPInterface i = new IPInterface(e.cloneTree());
            l.add(i);
        }
        return l;
    }
    
    public void addIPInterface(final IPInterface iface) {
        final IModelObject interfaces = this.getBaseElement().getCreateChild("en:interfaces");
        interfaces.addChild(iface.getRoot().cloneTree());
    }
    
    public void addIPInterfaces(final Collection<IPInterface> ifaces) {
        final IModelObject interfaces = this.getBaseElement().getCreateChild("en:interfaces");
        for (final IPInterface iface : ifaces) {
            interfaces.addChild(iface.getRoot().cloneTree());
        }
    }
    
    public void setSecurityPolicy(final SecurityPolicy p) {
        final IModelObject e = this.getBaseElement().getFirstChild("en:securityPolicy");
        if (e != null) {
            e.removeFromParent();
        }
        this.getBaseElement().addChild(p.getRoot().cloneTree());
    }
    
    public SecurityPolicy getSecurityPolicy() {
        final IModelObject pE = this.getBaseElement().getFirstChild("en:securityPolicy");
        final DevicePolicy p = new DevicePolicy(pE);
        return p;
    }
    
    public void setNatPolicy(final NatPolicy p) {
        final IModelObject e = this.getBaseElement().getFirstChild("en:natPolicy");
        if (e != null) {
            e.removeFromParent();
        }
        this.getBaseElement().addChild(p.getRoot().cloneTree());
    }
    
    public NatPolicy getNatPolicy() {
        final IModelObject e = this.getBaseElement().getFirstChild("en:natPolicy");
        return new NatPolicy(e);
    }
    
    public DeviceConfig clone() {
        final IModelObject e = this.getRoot().cloneTree();
        return new DeviceConfig(e);
    }
    
    public void setRaw(final String raw) {
        this.setChild(this.root, "en:raw", raw);
    }
    
    public String getRaw() {
        return Xlate.get(this.root.getFirstChild("en:raw"), (String)null);
    }
    
    public void clearRaw() {
        this.root.removeChildren("en:raw");
    }
}
