// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity.policy;

import java.util.ArrayList;
import org.xmodel.Xlate;
import org.xmodel.IModelObject;
import org.xmodel.Element;
import com.stonewall.cornerstone.entity.EntityReference;
import java.util.List;
import com.stonewall.cornerstone.entity.Entity;

public abstract class PolicyRule extends Entity
{
    protected List<String> namesToCombine;
    private EntityReference parent;
    
    public PolicyRule(final String tag, final String id) {
        super(tag, id);
    }
    
    public PolicyRule(final String tag) {
        super(tag);
        this.root.setAttribute("type", Type.user.name());
        this.root.addChild(new Element("en:name"));
    }
    
    public PolicyRule(final IModelObject root) {
        super(root);
    }
    
    public PolicyRule(final String tag, final String name, final int priority) {
        this(tag);
        this.setName(name);
        this.setPriority(priority);
    }
    
    public void setName(final String value) {
        this.root.getCreateChild("en:name").setValue(value);
    }
    
    @Override
    public String getName() {
        return Xlate.get(this.root.getFirstChild("en:name"), (String)null);
    }
    
    public void setPriority(final int value) {
        this.root.getCreateChild("en:priority").setValue(String.valueOf(value));
    }
    
    public int getPriority() {
        if (this.root.getFirstChild("en:priority") == null || Xlate.get(this.root.getFirstChild("en:priority"), (String)null).equals("")) {
            return 0;
        }
        return new Integer(Xlate.get(this.root.getFirstChild("en:priority"), (String)null));
    }
    
    public void setDescription(final String value) {
        this.root.getCreateChild("en:description").setValue(value);
    }
    
    public String getDescription() {
        return Xlate.get(this.root.getFirstChild("en:description"), (String)null);
    }
    
    public abstract Object clone();
    
    public void addNameToCombine(final String name) {
        if (this.namesToCombine == null) {
            this.namesToCombine = new ArrayList<String>();
        }
        this.namesToCombine.add(name);
    }
    
    public void completeOptimization() {
    }
    
    public void setRuleType(final Type type) {
        this.root.setAttribute("type", type.name());
    }
    
    public Type getRuleType() {
        final String type = Xlate.get(this.root, "type", (String)null);
        if (type == null) {
            return Type.user;
        }
        return Type.valueOf(type);
    }
    
    public boolean isUser() {
        return this.getRuleType().equals(Type.user);
    }
    
    public void setParent(final EntityReference parent) {
        this.parent = parent;
    }
    
    public EntityReference getParent() {
        if (this.parent == null) {
            return null;
        }
        return this.parent.clone();
    }
    
    public enum Type
    {
        user("user", 0), 
        autoACL("autoACL", 1), 
        autoDenyAll("autoDenyAll", 2), 
        adminACL("adminACL", 3), 
        adminTerminating("adminTerminating", 4);
        
        private Type(final String s, final int n) {
        }
    }
}
