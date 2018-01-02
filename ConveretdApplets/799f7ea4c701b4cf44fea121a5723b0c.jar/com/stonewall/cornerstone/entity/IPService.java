// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity;

import java.util.Iterator;
import java.util.List;
import org.xmodel.Xlate;
import com.stonewall.cornerstone.db.DbException;
import com.stonewall.cornerstone.entity.db.IPServiceDbAccess;
import com.stonewall.cornerstone.entity.util.IpPortRange;
import org.xmodel.IModelObject;

public class IPService extends Entity implements Service
{
    public static final String tag;
    public static final String ALL_ID = "SERVICE00";
    public static final EntityReference AllRef;
    public static final EntityReference JmsRef;
    
    static {
        tag = EntityFactory.EntityType.ipService.getQualifiedName();
        AllRef = new EntityReference(EntityFactory.EntityType.ipService, "SERVICE00");
        JmsRef = new EntityReference(EntityFactory.EntityType.ipService, "SERVICE76");
    }
    
    public IPService() {
        super(IPService.tag);
        this.init();
    }
    
    public IPService(final IModelObject e) {
        super(e);
    }
    
    public IPService(final String id) {
        super(IPService.tag, id);
    }
    
    public IPService(final IPProtocol protocol, final IpPortRange src, final IpPortRange dst) {
        super(IPService.tag);
        this.init();
        this.setProtocol(protocol);
        this.setSource(src);
        this.setDestination(dst);
    }
    
    private void init() {
        this.root.setAttribute("mutable", "true");
        this.root.getCreateChild("en:name");
    }
    
    @Override
    public Entity fetch(final Label label) throws DbException {
        return new IPServiceDbAccess(label).fetchById(this.getId());
    }
    
    @Override
    public void insert(final Label label) throws DbException {
        new IPServiceDbAccess(label).insert(this);
    }
    
    @Override
    public void delete(final Label label) throws DbException {
        new IPServiceDbAccess(label).delete(this);
    }
    
    @Override
    public void update(final Label label) throws DbException {
        new IPServiceDbAccess(label).update(this);
    }
    
    public boolean isMutable() {
        return Boolean.parseBoolean(Xlate.get(this.root, "mutable", (String)null));
    }
    
    public void setMutable(final boolean flag) {
        this.root.setAttribute("mutable", String.valueOf(flag));
    }
    
    public void setProtocol(final IPProtocol value) {
        this.setChild(this.root, "en:protocol", String.valueOf(value.getNumber()));
    }
    
    public IPProtocol getProtocol() {
        return IPProtocol.get(new Integer(Xlate.get(this.root.getFirstChild("en:protocol"), (String)null)));
    }
    
    public void setSource(final IpPortRange range) {
        this.setSource(range.sp(), range.ep());
    }
    
    public void setSource(final int start, final int end) {
        final IModelObject port = this.root.getCreateChild("en:srcPort");
        port.removeChildren();
        port.getCreateChild("en:start").setValue(String.valueOf(start));
        port.getCreateChild("en:end").setValue(String.valueOf(end));
    }
    
    public IpPortRange getSource() {
        try {
            final int start = Xlate.get(this.root.getFirstChild("en:srcPort").getFirstChild("en:start"), 0);
            final int end = Xlate.get(this.root.getFirstChild("en:srcPort").getFirstChild("en:end"), 0);
            return new IpPortRange(start, end);
        }
        catch (Exception e) {
            IPService.log.error(this, e);
            return null;
        }
    }
    
    public void setDestination(final IpPortRange range) {
        this.setDestination(range.sp(), range.ep());
    }
    
    public void setDestination(final int start, final int end) {
        final IModelObject port = this.root.getCreateChild("en:dstPort");
        port.removeChildren();
        port.getCreateChild("en:start").setValue(String.valueOf(start));
        port.getCreateChild("en:end").setValue(String.valueOf(end));
    }
    
    public IpPortRange getDestination() {
        try {
            final int start = Xlate.get(this.root.getFirstChild("en:dstPort").getFirstChild("en:start"), 0);
            final int end = Xlate.get(this.root.getFirstChild("en:dstPort").getFirstChild("en:end"), 0);
            return new IpPortRange(start, end);
        }
        catch (Exception e) {
            IPService.log.error(this, e);
            return null;
        }
    }
    
    @Override
    public String getName() {
        return Xlate.get(this.root.getFirstChild("en:name"), "");
    }
    
    public void setName(final String value) {
        this.setChild(this.root, "en:name", value);
    }
    
    public String getDescription() {
        return Xlate.get(this.root.getFirstChild("en:description"), (String)null);
    }
    
    public void setDescription(final String value) {
        this.setChild(this.root, "en:description", value);
    }
    
    public String getGroup() {
        return Xlate.get(this.root.getFirstChild("en:group"), (String)null);
    }
    
    public void setGroup(final String value) {
        this.setChild(this.root, "en:group", value);
    }
    
    public void setICMPType(final int value) {
        if (value == -1) {
            return;
        }
        this.setChild(this.root, "en:icmpType", String.valueOf(value));
    }
    
    public int getICMPType() {
        return Xlate.get(this.root.getFirstChild("en:icmpType"), -1);
    }
    
    public void setICMPCode(final int value) {
        if (value == -1) {
            return;
        }
        this.setChild(this.root, "en:icmpCode", String.valueOf(value));
    }
    
    public int getICMPCode() {
        return Xlate.get(this.root.getFirstChild("en:icmpCode"), -1);
    }
    
    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof IPService)) {
            return false;
        }
        final IPService service = (IPService)o;
        return this.getProtocol() == service.getProtocol() && this.getSource().equals(service.getSource()) && this.getDestination().equals(service.getDestination());
    }
    
    @Override
    public boolean contains(final Service svc) {
        if (!(svc instanceof IPService)) {
            return false;
        }
        final IPService service = (IPService)svc;
        if (this.getProtocol().isAny()) {
            return true;
        }
        if (!this.getProtocol().isAny() && this.getProtocol() != service.getProtocol()) {
            return false;
        }
        int result = IpPortRange.compare(this.getSource(), service.getSource());
        if (result != 0 && result != 1) {
            return false;
        }
        result = IpPortRange.compare(this.getDestination(), service.getDestination());
        return result == 0 || result == 1;
    }
    
    public void reverse() {
        final IModelObject src = this.root.getFirstChild("en:srcPort");
        final List<IModelObject> srcContent = src.cloneTree().getChildren();
        final IModelObject dst = this.root.getFirstChild("en:dstPort");
        final List<IModelObject> dstContent = dst.cloneTree().getChildren();
        src.removeChildren();
        for (final IModelObject o : dstContent) {
            src.addChild(o);
        }
        dst.removeChildren();
        for (final IModelObject o : srcContent) {
            dst.addChild(o);
        }
    }
    
    @Override
    public boolean isAny() {
        return this.getProtocol().isAny();
    }
    
    public boolean isInverse(final IPService s) {
        final IPService r = s.clone();
        r.reverse();
        return this.equals(r);
    }
    
    public IPService clone() {
        final IModelObject e = this.getRoot().cloneTree();
        return new IPService(e);
    }
}
