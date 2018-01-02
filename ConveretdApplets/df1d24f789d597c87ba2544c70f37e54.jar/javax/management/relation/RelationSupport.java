// 
// Decompiled by Procyon v0.5.30
// 

package javax.management.relation;

import org.jboss.mx.util.MBeanProxyExt;
import java.util.Collection;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Iterator;
import java.util.HashMap;
import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.management.MBeanRegistration;

public class RelationSupport implements RelationSupportMBean, MBeanRegistration
{
    String relationId;
    ObjectName relationService;
    private RelationServiceMBean serviceProxy;
    MBeanServer server;
    String relationTypeName;
    HashMap roles;
    Boolean registered;
    
    public RelationSupport(final String relationId, final ObjectName relationService, final String relationTypeName, final RoleList roleList) throws IllegalArgumentException, InvalidRoleValueException {
        this.init(relationId, relationService, null, relationTypeName, roleList);
    }
    
    public RelationSupport(final String relationId, final ObjectName relationService, final MBeanServer mbeanServer, final String relationTypeName, final RoleList roleList) throws IllegalArgumentException, InvalidRoleValueException {
        this.init(relationId, relationService, mbeanServer, relationTypeName, roleList);
    }
    
    public RoleResult getAllRoles() throws RelationServiceNotRegisteredException {
        final RoleList resolvedResult = new RoleList();
        final RoleUnresolvedList unresolvedResult = new RoleUnresolvedList();
        final RoleResult result = new RoleResult(resolvedResult, unresolvedResult);
        synchronized (this.roles) {
            for (final Role role : this.roles.values()) {
                final int status = this.checkRoleReadable(role);
                if (status == 0) {
                    resolvedResult.add(role);
                }
                else {
                    unresolvedResult.add(new RoleUnresolved(role.getRoleName(), role.getRoleValue(), status));
                }
            }
        }
        return result;
    }
    
    public Map getReferencedMBeans() {
        final HashMap result = new HashMap();
        synchronized (this.roles) {
            for (final Role role : this.roles.values()) {
                final String roleName = role.getRoleName();
                final ArrayList mbeans = (ArrayList)role.getRoleValue();
                for (final ObjectName mbean : mbeans) {
                    ArrayList resultRoles = result.get(mbean);
                    if (resultRoles == null) {
                        resultRoles = new ArrayList();
                        result.put(mbean, resultRoles);
                    }
                    resultRoles.add(roleName);
                }
            }
        }
        return result;
    }
    
    public String getRelationId() {
        return this.relationId;
    }
    
    public ObjectName getRelationServiceName() {
        return this.relationService;
    }
    
    public String getRelationTypeName() {
        return this.relationTypeName;
    }
    
    public List getRole(final String roleName) throws IllegalArgumentException, RoleNotFoundException, RelationServiceNotRegisteredException {
        if (roleName == null) {
            throw new IllegalArgumentException("null role name");
        }
        this.validateRoleReadable(roleName);
        final Role role = this.validateRoleFound(roleName);
        return role.getRoleValue();
    }
    
    public Integer getRoleCardinality(final String roleName) throws IllegalArgumentException, RoleNotFoundException {
        if (roleName == null) {
            throw new IllegalArgumentException("null role name");
        }
        final Role role = this.validateRoleFound(roleName);
        return new Integer(role.getRoleValue().size());
    }
    
    public RoleResult getRoles(final String[] roleNames) throws IllegalArgumentException, RelationServiceNotRegisteredException {
        final RoleList resolvedResult = new RoleList();
        final RoleUnresolvedList unresolvedResult = new RoleUnresolvedList();
        final RoleResult result = new RoleResult(resolvedResult, unresolvedResult);
        for (int i = 0; i < roleNames.length; ++i) {
            int status = 1;
            final Role role = this.roles.get(roleNames[i]);
            List roleValue;
            if (role != null) {
                roleValue = role.getRoleValue();
                status = this.checkRoleReadable(role);
            }
            else {
                roleValue = new ArrayList();
            }
            if (status == 0) {
                resolvedResult.add(role);
            }
            else {
                unresolvedResult.add(new RoleUnresolved(roleNames[i], roleValue, status));
            }
        }
        return result;
    }
    
    public void handleMBeanUnregistration(final ObjectName objectName, final String roleName) throws IllegalArgumentException, RoleNotFoundException, InvalidRoleValueException, RelationServiceNotRegisteredException, RelationTypeNotFoundException, RelationNotFoundException {
        this.checkRegistered();
        final Role role = this.validateRoleFound(roleName);
        final ArrayList values = (ArrayList)role.getRoleValue();
        final ArrayList oldRoleValue = new ArrayList(values);
        if (!values.remove(objectName)) {
            throw new InvalidRoleValueException(roleName + " " + objectName.toString());
        }
        role.setRoleValue(values);
        this.updateRole(role, oldRoleValue);
    }
    
    public RoleList retrieveAllRoles() {
        final RoleList result = new RoleList(this.roles.size());
        synchronized (this.roles) {
            final Iterator iterator = this.roles.values().iterator();
            while (iterator.hasNext()) {
                result.add(iterator.next());
            }
        }
        return result;
    }
    
    public void setRole(final Role role) throws IllegalArgumentException, RoleNotFoundException, RelationTypeNotFoundException, InvalidRoleValueException, RelationServiceNotRegisteredException, RelationNotFoundException {
        if (role == null) {
            throw new IllegalArgumentException("null role");
        }
        final Role copy = (Role)role.clone();
        this.checkRegistered();
        RoleValidator.validateRole(this.relationService, this.server, this.relationTypeName, copy, true);
        final Role oldRole = this.roles.get(role.getRoleName());
        final ArrayList oldRoleValue = (ArrayList)oldRole.getRoleValue();
        this.updateRole(copy, oldRoleValue);
    }
    
    public RoleResult setRoles(final RoleList roleList) throws IllegalArgumentException, RelationServiceNotRegisteredException, RelationTypeNotFoundException, RelationNotFoundException {
        if (roleList == null) {
            throw new IllegalArgumentException("null role list");
        }
        final RoleList copy = new RoleList(roleList);
        this.checkRegistered();
        final RoleResult result = RoleValidator.checkRoles(this.relationService, this.server, this.relationTypeName, copy, true);
        synchronized (result.getRoles()) {
            for (final Role role : result.getRoles()) {
                final Role oldRole = this.roles.get(role.getRoleName());
                final ArrayList oldRoleValue = (ArrayList)oldRole.getRoleValue();
                this.updateRole(role, oldRoleValue);
            }
        }
        return result;
    }
    
    public Boolean isInRelationService() {
        return this.registered;
    }
    
    public void setRelationServiceManagementFlag(final Boolean value) throws IllegalArgumentException {
        synchronized (this.registered) {
            this.registered = new Boolean(value);
        }
    }
    
    public ObjectName preRegister(final MBeanServer server, final ObjectName objectName) throws Exception {
        this.server = server;
        return objectName;
    }
    
    public void postRegister(final Boolean registered) {
    }
    
    public void preDeregister() throws Exception {
    }
    
    public void postDeregister() {
        this.server = null;
    }
    
    private void init(final String relationId, final ObjectName relationService, final MBeanServer mbeanServer, final String relationTypeName, final RoleList roleList) throws IllegalArgumentException {
        if (relationId == null) {
            throw new IllegalArgumentException("null relation id");
        }
        if (relationService == null) {
            throw new IllegalArgumentException("null relation service");
        }
        if (relationTypeName == null) {
            throw new IllegalArgumentException("null relation type name");
        }
        this.relationId = relationId;
        this.relationTypeName = relationTypeName;
        this.relationService = relationService;
        if (mbeanServer != null) {
            this.server = mbeanServer;
        }
        this.registered = new Boolean(false);
        if (roleList == null) {
            this.roles = new HashMap();
        }
        else {
            final Object[] roleArray = roleList.toArray();
            this.roles = new HashMap(roleArray.length);
            for (int i = 0; i < roleArray.length; ++i) {
                final Role role = (Role)roleArray[i];
                if (this.roles.containsKey(role.getRoleName())) {
                    throw new IllegalArgumentException("duplicate role name");
                }
                final Role copy = (Role)role.clone();
                this.roles.put(role.getRoleName(), copy);
            }
        }
    }
    
    private void checkRegistered() throws RelationNotFoundException {
        if (!this.isInRelationService()) {
            throw new RelationNotFoundException("not registered with relation service");
        }
    }
    
    private int checkRoleReadable(final Role role) throws RelationServiceNotRegisteredException {
        this.checkServiceProxy();
        try {
            final String roleName = role.getRoleName();
            final Integer result = this.serviceProxy.checkRoleReading(roleName, this.relationTypeName);
            return result;
        }
        catch (RelationTypeNotFoundException e) {
            throw new RuntimeException(e.toString());
        }
    }
    
    private void updateRole(final Role role, final ArrayList oldRoleValue) {
        this.roles.put(role.getRoleName(), role);
        try {
            this.checkServiceProxy();
            this.serviceProxy.updateRoleMap(this.relationId, role, oldRoleValue);
            this.serviceProxy.sendRoleUpdateNotification(this.relationId, role, oldRoleValue);
        }
        catch (Exception e) {
            throw new RuntimeException(e.toString());
        }
    }
    
    private Role validateRoleFound(final String roleName) throws RoleNotFoundException {
        final Role result = this.roles.get(roleName);
        if (result == null) {
            throw new RoleNotFoundException(roleName);
        }
        return result;
    }
    
    private void validateRoleReadable(final String roleName) throws RoleNotFoundException, RelationServiceNotRegisteredException {
        int status = 0;
        this.checkServiceProxy();
        try {
            status = this.serviceProxy.checkRoleReading(roleName, this.relationTypeName);
        }
        catch (RelationTypeNotFoundException e) {
            throw new RuntimeException(e.toString());
        }
        if (status == 1) {
            throw new RoleNotFoundException(roleName);
        }
        if (status == 2) {
            throw new RoleNotFoundException(roleName + " is not readable");
        }
    }
    
    private void checkServiceProxy() throws RelationServiceNotRegisteredException {
        if (this.serviceProxy == null) {
            try {
                this.serviceProxy = (RelationServiceMBean)MBeanProxyExt.create(RelationServiceMBean.class, this.relationService, this.server);
            }
            catch (Exception e) {
                throw new RelationServiceNotRegisteredException(e.toString());
            }
        }
    }
}
