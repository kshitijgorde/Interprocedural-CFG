// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity.db;

import com.stonewall.cornerstone.entity.DeviceManager;
import com.stonewall.cornerstone.db.DbException;
import com.stonewall.cornerstone.component.ComponentServer;
import java.util.List;
import com.stonewall.cornerstone.entity.RemoteServer;

public class DeviceManagerDbAccess extends RemoteServerDbAccess
{
    @Override
    public <T extends RemoteServer> List<T> fetchAll(final boolean stub) throws DbException {
        return super.fetchAll(ComponentServer.Type.dm, stub);
    }
    
    public List<String> fetchIds() throws DbException {
        return this.fetchIds(ComponentServer.Type.dm);
    }
    
    public void insert(final DeviceManager manager) throws DbException {
        super.insert(manager);
        new IPInterfaceDbAccess(this.getDb()).insert(manager.getInterfaces());
    }
    
    public void delete(final DeviceManager manager) throws DbException {
        new IPInterfaceDbAccess(this.getDb()).deleteByParent(manager.getId());
        super.delete(manager);
    }
}
