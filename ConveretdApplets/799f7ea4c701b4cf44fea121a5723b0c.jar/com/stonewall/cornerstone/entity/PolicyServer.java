// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity;

import com.stonewall.cornerstone.component.ComponentServer;
import com.stonewall.cornerstone.rmi.Destination;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import org.xmodel.Xlate;
import com.stonewall.cornerstone.db.DbException;
import com.stonewall.cornerstone.entity.db.PolicyServerDbAccess;
import org.xmodel.Element;
import org.xmodel.IModelObject;

public class PolicyServer extends Server
{
    public static final String tag;
    
    static {
        tag = EntityFactory.EntityType.policyServer.getQualifiedName();
    }
    
    public PolicyServer() {
        super(PolicyServer.tag);
    }
    
    public PolicyServer(final String id) {
        super(PolicyServer.tag, id);
    }
    
    public PolicyServer(final IModelObject root) {
        super(root);
    }
    
    @Override
    protected void init() {
        this.root.addChild(new Element("en:name"));
        this.root.addChild(new Element("en:interfaces"));
        super.init();
    }
    
    @Override
    public Entity fetch(final Label label) throws DbException {
        return new PolicyServerDbAccess().fetchById(this.getId());
    }
    
    @Override
    public void insert(final Label label) throws DbException {
        new PolicyServerDbAccess().insert(this);
    }
    
    @Override
    public void delete(final Label label) throws DbException {
        new PolicyServerDbAccess().delete(this);
    }
    
    public void setName(final String name) {
        this.setChild(this.root, "en:name", name);
    }
    
    @Override
    public String getName() {
        return Xlate.get(this.root.getFirstChild("en:name"), (String)null);
    }
    
    public List<IPInterface> getInterfaces() {
        final IModelObject parent = this.root.getCreateChild("en:interfaces");
        final List<IPInterface> result = new ArrayList<IPInterface>();
        for (final IModelObject o : parent.getChildren(IPInterface.tag)) {
            result.add(new IPInterface(o));
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
        if (result == null) {
            result = new IPInterface();
            result.setParentId(this.getId());
            result.setName(role.name());
            result.setIdentifier(new com.stonewall.cornerstone.utility.Server().identifier(role));
            this.addInterface(result);
        }
        return result;
    }
    
    public void addInterface(final IPInterface intf) {
        intf.setParentId(this.getId());
        this.root.getCreateChild("en:interfaces").addChild(intf.getRoot());
    }
    
    @Override
    public Destination getRMIDestination() {
        return new Destination(Destination.Type.ps);
    }
    
    @Override
    public String getSerialNumber() {
        return "";
    }
    
    @Override
    public boolean isLocal(final ComponentServer server) {
        return server.getClass().getName().equals("com.stonewall.cornerstone.ps.PolicyServer");
    }
}
