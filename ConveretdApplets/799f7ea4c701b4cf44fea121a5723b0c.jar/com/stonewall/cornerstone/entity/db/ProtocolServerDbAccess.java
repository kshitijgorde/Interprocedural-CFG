// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity.db;

import com.stonewall.cornerstone.db.DbException;
import com.stonewall.cornerstone.component.ComponentServer;
import java.util.List;
import com.stonewall.cornerstone.entity.RemoteServer;

public class ProtocolServerDbAccess extends RemoteServerDbAccess
{
    @Override
    public <T extends RemoteServer> List<T> fetchAll(final boolean stub) throws DbException {
        return super.fetchAll(ComponentServer.Type.cp, stub);
    }
}
