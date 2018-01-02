// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity;

import com.stonewall.cornerstone.component.ComponentServer;
import com.stonewall.cornerstone.entity.db.QueueDbAccess;
import com.stonewall.cornerstone.db.DbException;
import com.stonewall.cornerstone.entity.db.ProtocolServerDbAccess;
import org.xmodel.IModelObject;

public class ProtocolServer extends RemoteServer
{
    public static final String tag;
    
    static {
        tag = EntityFactory.EntityType.protocolServer.getQualifiedName();
    }
    
    public ProtocolServer() {
        super(ProtocolServer.tag);
        this.init();
    }
    
    public ProtocolServer(final IModelObject root) {
        super(root);
    }
    
    public ProtocolServer(final String id) {
        super(ProtocolServer.tag, id);
        this.init();
    }
    
    @Override
    public Entity fetch(final Label label) throws DbException {
        return new ProtocolServerDbAccess().fetchById(this.getId());
    }
    
    @Override
    public void insert(final Label label) throws DbException {
        new ProtocolServerDbAccess().insert(this);
    }
    
    @Override
    public void update(final Label label) throws DbException {
        new ProtocolServerDbAccess().update(this);
    }
    
    @Override
    public void delete(final Label label) throws DbException {
        new ProtocolServerDbAccess().delete(this);
    }
    
    @Override
    public QueueDbAccess.Pool getPoolType() {
        switch (this.getType()) {
            case cp: {
                return QueueDbAccess.Pool.CP;
            }
            default: {
                return null;
            }
        }
    }
}
