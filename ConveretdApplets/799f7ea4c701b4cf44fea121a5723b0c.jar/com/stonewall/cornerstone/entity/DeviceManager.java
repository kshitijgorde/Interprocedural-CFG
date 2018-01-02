// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity;

import com.stonewall.cornerstone.entity.db.QueueDbAccess;
import com.stonewall.cornerstone.utility.Server;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import com.stonewall.cornerstone.db.DbException;
import com.stonewall.cornerstone.entity.db.DeviceManagerDbAccess;
import com.stonewall.cornerstone.component.ComponentServer;
import org.xmodel.IModelObject;

public class DeviceManager extends RemoteServer
{
    public static final String tag;
    
    static {
        tag = EntityFactory.EntityType.deviceManager.getQualifiedName();
    }
    
    public DeviceManager(final IModelObject root) {
        super(root);
    }
    
    public DeviceManager() {
        super(DeviceManager.tag);
        this.init();
    }
    
    public DeviceManager(final String id) {
        super(DeviceManager.tag, id);
        this.init();
    }
    
    @Override
    protected void init() {
        super.init();
        this.setType(ComponentServer.Type.dm);
        this.root.getCreateChild("en:interfaces");
    }
    
    @Override
    public Entity fetch(final Label label) throws DbException {
        return new DeviceManagerDbAccess().fetchById(this.getId());
    }
    
    @Override
    public void insert(final Label label) throws DbException {
        new DeviceManagerDbAccess().insert(this);
    }
    
    @Override
    public void update(final Label label) throws DbException {
        new DeviceManagerDbAccess().update(this);
    }
    
    @Override
    public void delete(final Label label) throws DbException {
        new DeviceManagerDbAccess().delete(this);
    }
    
    public List<IPInterface> getInterfaces() {
        final IModelObject parent = this.root.getCreateChild("en:interfaces");
        final List<IPInterface> result = new ArrayList<IPInterface>();
        for (final Object o : parent.getChildren(IPInterface.tag)) {
            result.add(new IPInterface((IModelObject)o));
        }
        return result;
    }
    
    public IPInterface getInterface(final com.stonewall.cornerstone.utility.Server.IntRole role) {
        IPInterface result = null;
        for (final IPInterface intf : this.getInterfaces()) {
            if (intf.getName().equals(role.name())) {
                result = intf;
                break;
            }
        }
        return result;
    }
    
    public IPInterface getDeviceMgmtInterface() {
        IPInterface source = this.getInterface(com.stonewall.cornerstone.utility.Server.IntRole.communication);
        if (source == null) {
            source = this.getInterface(com.stonewall.cornerstone.utility.Server.IntRole.management);
        }
        return source;
    }
    
    public void replaceInterfaces(final List<IPInterface> intfs) {
        final IModelObject parent = this.root.getCreateChild("en:interfaces");
        parent.removeChildren();
        for (final IPInterface intf : intfs) {
            intf.setParentId(this.getId());
            parent.addChild(intf.getRoot());
        }
    }
    
    public void reconcileInterfaces(final List<IPInterface> intfs) {
        final DeviceManager m = new DeviceManager();
        m.replaceInterfaces(intfs);
        com.stonewall.cornerstone.utility.Server.IntRole[] values;
        for (int length = (values = com.stonewall.cornerstone.utility.Server.IntRole.values()).length, i = 0; i < length; ++i) {
            final com.stonewall.cornerstone.utility.Server.IntRole r = values[i];
            final IPInterface newIntf = m.getInterface(r);
            if (newIntf == null) {
                this.removeInterface(r);
            }
            else {
                IPInterface currentIntf = this.getInterface(r);
                if (currentIntf == null) {
                    currentIntf = new IPInterface();
                    currentIntf.setParentId(this.getId());
                    this.addInterface(currentIntf);
                }
                currentIntf.setIpAddress(newIntf.getIpAddress());
                currentIntf.setIdentifier(newIntf.getIdentifier());
                currentIntf.setName(r.name());
            }
        }
    }
    
    public void addInterface(final IPInterface intf) {
        intf.setParentId(this.getId());
        this.root.getCreateChild("en:interfaces").addChild(intf.getRoot());
    }
    
    public void removeInterface(final com.stonewall.cornerstone.utility.Server.IntRole role) {
        final IModelObject parent = this.root.getCreateChild("en:interfaces");
        for (final IPInterface intf : this.getInterfaces()) {
            if (intf.getName().equals(role.name())) {
                parent.removeChild(intf.getRoot());
                break;
            }
        }
    }
    
    @Override
    public QueueDbAccess.Pool getPoolType() {
        return QueueDbAccess.Pool.DM;
    }
}
