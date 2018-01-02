// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity;

import org.xmodel.xpath.expression.IExpression;
import org.xmodel.xpath.XPath;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.xmodel.Xlate;
import com.stonewall.cornerstone.db.DbException;
import com.stonewall.cornerstone.entity.db.OpsetDbAccess;
import java.util.Date;
import org.xmodel.IModelObject;

public class Opset extends Entity
{
    public static final String tag;
    
    static {
        tag = EntityFactory.EntityType.opset.getQualifiedName();
    }
    
    public Opset() {
        super(Opset.tag);
        this.init();
    }
    
    public Opset(final Type type) {
        this();
        this.setType(type);
        this.setCorrelation("");
    }
    
    public Opset(final Type type, final String correlation, final String deviceId) {
        this();
        this.setType(type);
        this.setCorrelation(correlation);
        this.setParentId(deviceId);
    }
    
    public Opset(final String id) {
        super(Opset.tag, id);
    }
    
    public Opset(final IModelObject root) {
        super(root);
    }
    
    private void init() {
        this.root.getCreateChild("en:correlation");
        this.setTimestamp(new Date().getTime());
        this.root.getCreateChild("en:opIndex").setValue("0");
        this.root.getCreateChild("en:operations");
    }
    
    @Override
    public Entity fetch(final Label label) throws DbException {
        return new OpsetDbAccess(label).fetchById(this.getId());
    }
    
    @Override
    public Entity fetchByParent(final String parent, final Label label) throws DbException {
        return new OpsetDbAccess(label).fetchByDeviceAndType(parent, this.getType(), true);
    }
    
    @Override
    public Entity fetchRefByParent(final String parent, final Label label) throws DbException {
        return new OpsetDbAccess(label).fetchByDeviceAndType(parent, this.getType(), false);
    }
    
    @Override
    public void insert(final Label label) throws DbException {
        new OpsetDbAccess(label).insert(this);
    }
    
    @Override
    public void update(final Label label) throws DbException {
        new OpsetDbAccess(label).update(this);
    }
    
    @Override
    public void delete(final Label label) throws DbException {
        new OpsetDbAccess(label).delete(this);
    }
    
    public void reset(final Task task) {
        this.setCorrelation(task.getjid());
        this.setOpIndex(0);
        this.setOpCount(0);
    }
    
    @Override
    public void trim() {
        super.trim();
        this.root.getCreateChild("en:operations").removeChildren();
    }
    
    public void setType(final Type value) {
        this.root.setAttribute("type", value.name());
    }
    
    public Type getType() {
        return Type.valueOf(Xlate.get(this.root, "type", (String)null));
    }
    
    public void setCorrelation(final String value) {
        this.setChild(this.root, "en:correlation", value);
    }
    
    public String getCorrelation() {
        return Xlate.get(this.root.getFirstChild("en:correlation"), (String)null);
    }
    
    public int getOpCount() {
        return Xlate.get(this.root, "opCount", 0);
    }
    
    public void setOpCount(final int index) {
        this.root.setAttribute("opCount", String.valueOf(index));
    }
    
    private void incrementOpCount() {
        int index = this.getOpCount();
        ++index;
        this.setOpCount(index);
    }
    
    public void addOperation(final DeviceOperation entry) {
        entry.setParentId(this.getId());
        this.root.getCreateChild("en:operations").addChild(entry.getRoot());
        this.incrementOpCount();
    }
    
    public void setOperations(final List<DeviceOperation> operations) {
        this.trim();
        for (final DeviceOperation operation : operations) {
            this.addOperation(operation);
        }
    }
    
    public boolean hasOperations() {
        return this.getOpCount() > 0;
    }
    
    public List<DeviceOperation> getOperations() {
        final List<DeviceOperation> ops = new ArrayList<DeviceOperation>();
        final List<IModelObject> children = this.root.getCreateChild("en:operations").getChildren("en:deviceOperation");
        for (final IModelObject o : children) {
            ops.add(new DeviceOperation(o));
        }
        return ops;
    }
    
    public int getOpIndex() {
        return Xlate.childGet(this.root, "en:opIndex", 0);
    }
    
    private void setOpIndex(final int index) {
        this.root.getCreateChild("en:opIndex").setValue(String.valueOf(index));
    }
    
    private void incrementOpIndex() {
        int index = this.getOpIndex();
        ++index;
        this.setOpIndex(index);
    }
    
    public DeviceOperation fetchNextOperation() throws DbException {
        return new OpsetDbAccess().fetchByIndex(this.getId(), this.getOpIndex());
    }
    
    public DeviceOperation fetchFailedOperation() {
        final IExpression path = XPath.createExpression("en:operations/en:deviceOperation[en:commands/*/en:result/en:status='failed']");
        return new DeviceOperation(path.queryFirst(this.root));
    }
    
    public void updateOperation(final DeviceOperation operation) throws DbException {
        new OpsetDbAccess().update(operation);
        this.incrementOpIndex();
    }
    
    @Override
    public EntityReference getReference() {
        final EntityReference ref = super.getReference();
        ref.setAttribute("type", this.getType().name());
        ref.setAttribute("opCount", String.valueOf(this.getOpCount()));
        return ref;
    }
    
    public enum Type
    {
        preview("preview", 0), 
        deploy("deploy", 1), 
        audit("audit", 2);
        
        private Type(final String s, final int n) {
        }
    }
}
