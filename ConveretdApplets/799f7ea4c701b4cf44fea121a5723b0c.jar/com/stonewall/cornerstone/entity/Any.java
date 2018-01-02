// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity;

import java.util.Collections;
import java.util.List;
import com.stonewall.cornerstone.entity.policy.AnyEndpoint;
import com.stonewall.cornerstone.entity.policy.Endpoint;
import com.stonewall.cornerstone.db.DbException;
import org.xmodel.IModelObject;
import com.stonewall.cornerstone.entity.util.IpAddr;
import com.stonewall.cornerstone.entity.policy.IEndpoint;

public class Any extends Entity implements IEndpoint, IAddress
{
    public static final IpAddr ADDR;
    public static final String ID = "ANY";
    public static final String tag;
    
    static {
        ADDR = IpAddr.ipAny();
        tag = EntityFactory.EntityType.any.getQualifiedName();
    }
    
    public Any() {
        this("ANY");
    }
    
    public Any(final String id) {
        super(Any.tag, id);
        this.root.getCreateChild("en:name").setValue("Any");
    }
    
    public Any(final IModelObject root) {
        super(root);
    }
    
    @Override
    public Entity fetch(final Label label) throws DbException {
        return this;
    }
    
    @Override
    public boolean equals(final Object o) {
        return o instanceof Any;
    }
    
    @Override
    public Endpoint createEndpoint() {
        final AnyEndpoint ep = new AnyEndpoint();
        return ep;
    }
    
    @Override
    public IpAddr getIpAddress() {
        try {
            return new IpAddr(0, 0);
        }
        catch (Exception e) {
            Any.log.error(this, e);
            return null;
        }
    }
    
    @Override
    public List<IpAddr> getIpAddresses() {
        return Collections.singletonList(this.getIpAddress());
    }
}
