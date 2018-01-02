// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.security;

import com.stonewall.cornerstone.entity.EntityFactory;
import org.xmodel.xml.IXmlIO;
import com.stonewall.cornerstone.utility.ModelBuilder;
import org.xmodel.Element;
import java.util.Set;
import java.util.Collections;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Collection;
import org.xmodel.Xlate;
import java.util.HashMap;
import org.xmodel.log.Log;
import java.util.Map;
import java.util.List;
import com.stonewall.cornerstone.utility.IdentityFactory;
import org.xmodel.IModelObject;

public class AccessGroup
{
    private IModelObject root;
    private static IdentityFactory identityFactory;
    private List<Mutex> gatingLocks;
    private List<Mutex> locksToAcquire;
    private List<Permission> permissions;
    private boolean persistent;
    private boolean acquired;
    private Map<String, Object> variables;
    Map<String, Object> data;
    private FeatureAccess.Type type;
    static final Log log;
    
    static {
        AccessGroup.identityFactory = new IdentityFactory();
        log = Log.getLog(AccessGroup.class);
    }
    
    AccessGroup() {
        this.acquired = false;
        this.variables = new HashMap<String, Object>();
        this.data = new HashMap<String, Object>();
    }
    
    public AccessGroup(final IModelObject root) {
        this.acquired = false;
        this.variables = new HashMap<String, Object>();
        this.data = new HashMap<String, Object>();
        this.root = root;
        if (root.getID() == null) {
            root.setID(AccessGroup.identityFactory.next());
        }
        final List<IModelObject> vars = root.getChildren("en:variable");
        for (final IModelObject e : vars) {
            final String key = Xlate.get(e, "key", (String)null);
            if (this.variables.containsKey(key)) {
                final Object value = this.variables.get(key);
                if (value instanceof Collection) {
                    ((Collection)value).add(Xlate.get(e, (String)null));
                }
                else {
                    final List<String> l = new ArrayList<String>();
                    l.add((String)value);
                    l.add(Xlate.get(e, (String)null));
                    this.variables.put(key, l);
                }
            }
            else {
                this.setVariable(key, Xlate.get(e, (String)null));
            }
        }
    }
    
    public void setType(final FeatureAccess.Type type) {
        this.type = type;
    }
    
    public void setVariable(final String name, Object value) {
        if (!(value instanceof Collection)) {
            value = Collections.singletonList(value);
        }
        this.variables.put(name, value);
    }
    
    private Collection<String> getVariable(final String key) {
        final Collection<String> var = this.variables.get(key);
        if (var == null) {
            return (Collection<String>)Collections.singletonList((Object)null);
        }
        return var;
    }
    
    public boolean isFeatureLocked(final Map<Mutex, Integer> locks) {
        for (final Mutex m : this.getGatingLocks()) {
            if (m.isLocked(locks)) {
                return true;
            }
        }
        return false;
    }
    
    public List<Mutex> unlock(final Map<Mutex, Integer> locks) {
        final List<Mutex> l = new ArrayList<Mutex>();
        for (final Mutex m : this.getLocksToAquire()) {
            l.addAll(m.unlock(locks));
        }
        return l;
    }
    
    public List<Mutex> lock(final Map<Mutex, Integer> locks) {
        final List<Mutex> l = new ArrayList<Mutex>();
        for (final Mutex m : this.getLocksToAquire()) {
            l.addAll(m.lock(locks));
        }
        return l;
    }
    
    private List<Mutex> getGatingLocks() {
        if (this.gatingLocks == null) {
            this.gatingLocks = new ArrayList<Mutex>();
            for (final IModelObject e : this.getGatingLockTypes()) {
                final String name = Xlate.get(e, (String)null);
                final FeatureAccess fa = FeatureAccess.Type.valueOf(name).getFeatureAccess();
                final Collection<String> var = this.getVariable(fa.getVariableName());
                for (final String s : var) {
                    this.gatingLocks.add(this.createMutex(fa, s));
                }
            }
            Collections.sort(this.gatingLocks);
        }
        return this.gatingLocks;
    }
    
    private List<Mutex> getLocksToAquire() {
        if (this.locksToAcquire == null) {
            this.locksToAcquire = new ArrayList<Mutex>();
            for (final IModelObject e : this.getLockTypesToAcquire()) {
                final String name = Xlate.get(e, (String)null);
                final FeatureAccess fa = FeatureAccess.Type.valueOf(name).getFeatureAccess();
                final Collection<String> var = this.getVariable(fa.getVariableName());
                for (final String s : var) {
                    this.locksToAcquire.add(this.createMutex(fa, s));
                }
            }
            Collections.sort(this.locksToAcquire);
        }
        return this.locksToAcquire;
    }
    
    public boolean hasPermission(final Set<String> userPermissions) {
        boolean result = false;
        for (final Permission p : this.getPermissions()) {
            result |= p.hasPermission(userPermissions);
        }
        return result;
    }
    
    private List<Permission> getPermissions() {
        if (this.permissions == null) {
            this.permissions = new ArrayList<Permission>();
            for (final IModelObject e : this.getPermissionTypes()) {
                final String name = Xlate.get(e, (String)null);
                final FeatureAccess fa = FeatureAccess.Type.valueOf(name).getFeatureAccess();
                final Collection<String> var = this.getVariable(fa.getVariableName());
                for (final String s : var) {
                    this.permissions.add(this.createPermission(fa, s));
                }
            }
            Collections.sort(this.permissions);
        }
        return this.permissions;
    }
    
    public boolean isPersistent() {
        return this.persistent;
    }
    
    public void setPersistent() {
        this.persistent = true;
    }
    
    public void setAcquired() {
        this.acquired = true;
    }
    
    public boolean isAcquired() {
        return this.acquired;
    }
    
    public String getId() {
        return this.root.getID();
    }
    
    private List<IModelObject> getPermissionTypes() {
        final List<IModelObject> types = this.root.getChildren("en:permission");
        return types;
    }
    
    private List<IModelObject> getGatingLockTypes() {
        final List<IModelObject> types = this.root.getChildren("en:gatingLock");
        return types;
    }
    
    private List<IModelObject> getLockTypesToAcquire() {
        final List<IModelObject> types = this.root.getChildren("en:lockToAcquire");
        return types;
    }
    
    public String getXmlContents() {
        final IModelObject cp = this.root.cloneTree();
        for (final String key : this.variables.keySet()) {
            final Object value = this.variables.get(key);
            if (value instanceof String) {
                final IModelObject e = new Element("en:variable");
                e.setAttribute("key", key);
                e.setValue(value);
                cp.addChild(e);
            }
            else {
                if (!(value instanceof List)) {
                    continue;
                }
                final List<String> ids = (List<String>)value;
                for (final String id : ids) {
                    final IModelObject e2 = new Element("en:variable");
                    e2.setAttribute("key", key);
                    e2.setValue(id);
                    cp.addChild(e2);
                }
            }
        }
        final ModelBuilder builder = new ModelBuilder();
        return builder.writeModel(cp, IXmlIO.Style.compact);
    }
    
    public void validateVariables() {
        final List<IModelObject> accesses = new ArrayList<IModelObject>();
        accesses.addAll(this.getGatingLockTypes());
        accesses.addAll(this.getLockTypesToAcquire());
        accesses.addAll(this.getPermissionTypes());
        for (final IModelObject e : accesses) {
            final String name = Xlate.get(e, (String)null);
            final FeatureAccess fa = FeatureAccess.Type.valueOf(name).getFeatureAccess();
            if (fa.hasVariable() && !this.variables.containsKey(fa.getVariableName())) {
                throw new RuntimeException("Access Group Variable '" + fa.getVariableName() + "' is not set for FA:" + fa);
            }
        }
    }
    
    @Override
    public String toString() {
        final StringBuffer buf = new StringBuffer();
        buf.append("Type: ");
        buf.append(FeatureAccess.Type.valueOf(Xlate.get(this.root, "type", "")).name());
        if (this.isPersistent()) {
            buf.append("(persistent)");
        }
        buf.append(" Variables: ");
        for (final String key : this.variables.keySet()) {
            buf.append(key);
            buf.append(":");
            buf.append(this.variables.get(key));
            buf.append(" ");
        }
        return buf.toString();
    }
    
    private Mutex createMutex(final FeatureAccess fa, final String value) {
        final String[] tokens = fa.getPath().split("[\\[/]");
        if (tokens[0].startsWith(EntityFactory.EntityType.site.name())) {
            return new BranchMutex(fa, value, this.data);
        }
        return new Mutex(fa, value);
    }
    
    public Permission createPermission(final FeatureAccess fa, final String value) {
        final String[] tokens = fa.getPath().split("[:\\[/]");
        if (tokens[1].startsWith(EntityFactory.EntityType.site.name())) {
            return new TreePermission(fa, EntityFactory.EntityType.site, value, this.data);
        }
        return new Permission(fa, value);
    }
}
