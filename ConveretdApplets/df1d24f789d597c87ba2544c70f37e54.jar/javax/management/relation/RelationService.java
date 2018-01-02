// 
// Decompiled by Procyon v0.5.30
// 

package javax.management.relation;

import javax.management.MBeanNotificationInfo;
import javax.management.MBeanServerNotification;
import javax.management.Notification;
import javax.management.NotificationFilter;
import javax.management.InvalidAttributeValueException;
import javax.management.AttributeNotFoundException;
import javax.management.Attribute;
import javax.management.ReflectionException;
import javax.management.MBeanException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import javax.management.InstanceNotFoundException;
import java.util.Stack;
import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.util.HashMap;
import org.jboss.logging.Logger;
import javax.management.NotificationListener;
import javax.management.MBeanRegistration;
import javax.management.NotificationBroadcasterSupport;

public class RelationService extends NotificationBroadcasterSupport implements RelationServiceMBean, MBeanRegistration, NotificationListener
{
    private static final long serialVersionUID = 5434016005679159613L;
    private static final Logger log;
    private HashMap idsByRelation;
    private ObjectName relationService;
    private long notificationSequence;
    private boolean purgeFlag;
    private HashMap relationsById;
    private MBeanServer server;
    private HashMap typesByName;
    private HashMap typeNamesById;
    private MBeanServerNotificationFilter filter;
    private Stack unregistered;
    private HashMap idRolesMapByMBean;
    private ObjectName delegate;
    
    public RelationService(final boolean purgeFlag) {
        this.idsByRelation = new HashMap();
        this.notificationSequence = 0L;
        this.relationsById = new HashMap();
        this.typesByName = new HashMap();
        this.typeNamesById = new HashMap();
        this.unregistered = new Stack();
        this.idRolesMapByMBean = new HashMap();
        this.setPurgeFlag(purgeFlag);
    }
    
    public synchronized void addRelation(final ObjectName relation) throws IllegalArgumentException, NoSuchMethodException, RelationServiceNotRegisteredException, InvalidRelationIdException, InvalidRelationServiceException, RelationTypeNotFoundException, InvalidRoleValueException, RoleNotFoundException, InstanceNotFoundException {
        if (relation == null) {
            throw new IllegalArgumentException("null relation");
        }
        this.isActive();
        ObjectName otherService = null;
        String relationId = null;
        String relationTypeName = null;
        RoleList roleList = null;
        try {
            this.server.isInstanceOf(relation, Relation.class.getName());
            otherService = (ObjectName)this.server.getAttribute(relation, "RelationServiceName");
            relationId = (String)this.server.getAttribute(relation, "RelationId");
            relationTypeName = (String)this.server.getAttribute(relation, "RelationTypeName");
            roleList = (RoleList)this.server.invoke(relation, "retrieveAllRoles", new Object[0], new String[0]);
        }
        catch (InstanceNotFoundException e) {
            throw e;
        }
        catch (Exception e2) {
            throw new NoSuchMethodException("Not a relation or not registered");
        }
        if (relationId == null) {
            throw new InvalidRelationIdException("Null relation id");
        }
        if (otherService == null || !otherService.equals(this.relationService)) {
            throw new InvalidRelationServiceException(otherService + " != " + this.relationService);
        }
        final RoleList copy = new RoleList(roleList);
        this.createMissingRoles(relationTypeName, copy);
        RoleValidator.validateRoles(this.relationService, this.server, relationTypeName, copy, false);
        this.validateAndAddRelation(relationId, relation, relationTypeName);
        this.filter.enableObjectName(relation);
    }
    
    public synchronized void addRelationType(final RelationType relationType) throws IllegalArgumentException, InvalidRelationTypeException {
        if (relationType == null) {
            throw new IllegalArgumentException("null relation type");
        }
        synchronized (this.typesByName) {
            final String name = relationType.getRelationTypeName();
            if (name == null) {
                throw new IllegalArgumentException("Null relation type name in relation type");
            }
            if (this.typesByName.containsKey(name)) {
                throw new InvalidRelationTypeException("duplicate relation id: " + name);
            }
            this.validateRelationType(relationType);
            this.typesByName.put(name, relationType);
        }
    }
    
    public Integer checkRoleReading(final String roleName, final String relationTypeName) throws IllegalArgumentException, RelationTypeNotFoundException {
        if (roleName == null) {
            throw new IllegalArgumentException("Null role name");
        }
        final RelationType relationType = this.retrieveRelationTypeForName(relationTypeName);
        RoleInfo roleInfo = null;
        try {
            roleInfo = relationType.getRoleInfo(roleName);
        }
        catch (RoleInfoNotFoundException e) {
            return new Integer(1);
        }
        if (!roleInfo.isReadable()) {
            return new Integer(2);
        }
        return new Integer(0);
    }
    
    public Integer checkRoleWriting(final Role role, final String relationTypeName, final Boolean initFlag) throws IllegalArgumentException, RelationTypeNotFoundException {
        if (role == null) {
            throw new IllegalArgumentException("Null role name");
        }
        if (initFlag == null) {
            throw new IllegalArgumentException("Null init flag");
        }
        final RelationType relationType = this.retrieveRelationTypeForName(relationTypeName);
        RoleInfo roleInfo = null;
        try {
            roleInfo = relationType.getRoleInfo(role.getRoleName());
        }
        catch (RoleInfoNotFoundException e) {
            return new Integer(1);
        }
        if (!initFlag && !roleInfo.isWritable()) {
            return new Integer(3);
        }
        final ArrayList mbeans = (ArrayList)role.getRoleValue();
        final int beanCount = mbeans.size();
        final int minimum = roleInfo.getMinDegree();
        if (minimum != RoleInfo.ROLE_CARDINALITY_INFINITY && minimum > beanCount) {
            return new Integer(4);
        }
        final int maximum = roleInfo.getMaxDegree();
        if (maximum != RoleInfo.ROLE_CARDINALITY_INFINITY && maximum < beanCount) {
            return new Integer(5);
        }
        final String className = roleInfo.getRefMBeanClassName();
        final Iterator iterator = mbeans.iterator();
        while (iterator.hasNext()) {
            try {
                final ObjectName objectName = iterator.next();
                if (!this.server.isInstanceOf(objectName, className)) {
                    return new Integer(6);
                }
                continue;
            }
            catch (Exception e2) {
                return new Integer(7);
            }
            break;
        }
        return new Integer(0);
    }
    
    public synchronized void createRelation(final String relationId, final String relationTypeName, final RoleList roleList) throws IllegalArgumentException, RelationServiceNotRegisteredException, InvalidRelationIdException, RelationTypeNotFoundException, InvalidRoleValueException, RoleNotFoundException {
        RoleList copy = null;
        if (roleList != null) {
            copy = new RoleList(roleList);
        }
        else {
            copy = new RoleList();
        }
        this.isActive();
        final RelationSupport relation = new RelationSupport(relationId, this.relationService, this.server, relationTypeName, copy);
        this.createMissingRoles(relationTypeName, copy);
        RoleValidator.validateRoles(this.relationService, this.server, relationTypeName, copy, false);
        this.validateAndAddRelation(relationId, relation, relationTypeName);
    }
    
    public synchronized void createRelationType(final String relationTypeName, final RoleInfo[] roleInfos) throws IllegalArgumentException, InvalidRelationTypeException {
        if (relationTypeName == null) {
            throw new IllegalArgumentException("null relation type name");
        }
        synchronized (this.typesByName) {
            if (this.typesByName.containsKey(relationTypeName)) {
                throw new InvalidRelationTypeException("duplicate relation id: " + relationTypeName);
            }
            final RelationType relationType = new RelationTypeSupport(relationTypeName, roleInfos);
            this.typesByName.put(relationTypeName, relationType);
        }
    }
    
    public Map findAssociatedMBeans(final ObjectName mbeanName, final String relationTypeName, final String roleName) throws IllegalArgumentException {
        final HashMap referencing = (HashMap)this.findReferencingRelations(mbeanName, relationTypeName, roleName);
        final HashMap result = new HashMap();
        for (final Map.Entry referencingEntry : referencing.entrySet()) {
            final String relationId = referencingEntry.getKey();
            HashMap referenced = null;
            try {
                referenced = (HashMap)this.getReferencedMBeans(relationId);
            }
            catch (RelationNotFoundException e) {
                throw new RuntimeException(e.toString());
            }
            for (final Map.Entry referencedEntry : referenced.entrySet()) {
                final ObjectName objectName = referencedEntry.getKey();
                if (!objectName.equals(mbeanName)) {
                    ArrayList resultList = result.get(objectName);
                    if (resultList == null) {
                        resultList = new ArrayList();
                        resultList.add(relationId);
                        result.put(objectName, resultList);
                    }
                    else {
                        if (resultList.contains(relationId)) {
                            continue;
                        }
                        resultList.add(relationId);
                    }
                }
            }
        }
        return result;
    }
    
    public Map findReferencingRelations(final ObjectName mbeanName, final String relationTypeName, final String roleName) throws IllegalArgumentException {
        if (mbeanName == null) {
            throw new IllegalArgumentException("null object name");
        }
        final HashMap result = new HashMap();
        final HashMap idRolesMap = this.idRolesMapByMBean.get(mbeanName);
        if (idRolesMap == null) {
            return result;
        }
        for (final Map.Entry entry : idRolesMap.entrySet()) {
            final String relationId = entry.getKey();
            final HashSet roleNames = entry.getValue();
            if (relationTypeName == null || this.typeNamesById.get(relationId).equals(relationTypeName)) {
                final ArrayList resultRoleNames = new ArrayList();
                if (roleName == null) {
                    resultRoleNames.addAll(roleNames);
                }
                else if (roleNames.contains(roleName) && !resultRoleNames.contains(roleName)) {
                    resultRoleNames.add(roleName);
                }
                if (resultRoleNames.size() <= 0) {
                    continue;
                }
                result.put(relationId, resultRoleNames);
            }
        }
        return result;
    }
    
    public List findRelationsOfType(final String relationTypeName) throws IllegalArgumentException, RelationTypeNotFoundException {
        if (relationTypeName == null) {
            throw new IllegalArgumentException("null relation type name");
        }
        if (!this.typesByName.containsKey(relationTypeName)) {
            throw new RelationTypeNotFoundException("relation type name not found");
        }
        final ArrayList result = new ArrayList();
        for (final Map.Entry entry : this.typeNamesById.entrySet()) {
            final String typeName = entry.getValue();
            if (typeName.equals(relationTypeName)) {
                result.add(entry.getKey());
            }
        }
        return result;
    }
    
    public List getAllRelationIds() {
        final ArrayList result = new ArrayList(this.relationsById.size());
        synchronized (this.relationsById) {
            final Iterator iterator = this.relationsById.keySet().iterator();
            while (iterator.hasNext()) {
                result.add(iterator.next());
            }
        }
        return result;
    }
    
    public List getAllRelationTypeNames() {
        final ArrayList result = new ArrayList(this.typesByName.size());
        synchronized (this.typesByName) {
            final Iterator iterator = this.typesByName.keySet().iterator();
            while (iterator.hasNext()) {
                result.add(iterator.next());
            }
        }
        return result;
    }
    
    public RoleResult getAllRoles(final String relationId) throws IllegalArgumentException, RelationNotFoundException, RelationServiceNotRegisteredException {
        this.isActive();
        final Object relation = this.retrieveRelationForId(relationId);
        if (relation instanceof RelationSupport) {
            return ((RelationSupport)relation).getAllRoles();
        }
        final ObjectName objectName = (ObjectName)relation;
        try {
            return (RoleResult)this.server.getAttribute(objectName, "AllRoles");
        }
        catch (InstanceNotFoundException e2) {
            throw new RelationNotFoundException(objectName.toString());
        }
        catch (Exception e) {
            throw new RuntimeException(e.toString());
        }
    }
    
    public boolean getPurgeFlag() {
        return this.purgeFlag;
    }
    
    public Map getReferencedMBeans(final String relationId) throws IllegalArgumentException, RelationNotFoundException {
        final Object relation = this.retrieveRelationForId(relationId);
        if (relation instanceof RelationSupport) {
            return ((RelationSupport)relation).getReferencedMBeans();
        }
        final ObjectName objectName = (ObjectName)relation;
        try {
            return (Map)this.server.getAttribute(objectName, "ReferencedMBeans");
        }
        catch (InstanceNotFoundException e2) {
            throw new RelationNotFoundException(objectName.toString());
        }
        catch (Exception e) {
            throw new RuntimeException(e.toString());
        }
    }
    
    public String getRelationTypeName(final String relationId) throws IllegalArgumentException, RelationNotFoundException {
        return this.retrieveTypeNameForId(relationId);
    }
    
    public List getRole(final String relationId, final String roleName) throws IllegalArgumentException, RelationNotFoundException, RelationServiceNotRegisteredException, RoleNotFoundException {
        if (roleName == null) {
            throw new IllegalArgumentException("null role");
        }
        this.isActive();
        final Object relation = this.retrieveRelationForId(relationId);
        if (relation instanceof RelationSupport) {
            return ((RelationSupport)relation).getRole(roleName);
        }
        final ObjectName objectName = (ObjectName)relation;
        try {
            final List result = (List)this.server.invoke(objectName, "getRole", new Object[] { roleName }, new String[] { "java.lang.String" });
            return result;
        }
        catch (InstanceNotFoundException e3) {
            throw new RelationNotFoundException(objectName.toString());
        }
        catch (MBeanException mbe) {
            final Exception e = mbe.getTargetException();
            if (e instanceof RoleNotFoundException) {
                throw (RoleNotFoundException)e;
            }
            throw new RuntimeException(e.toString());
        }
        catch (ReflectionException e2) {
            throw new RuntimeException(e2.toString());
        }
    }
    
    public Integer getRoleCardinality(final String relationId, final String roleName) throws IllegalArgumentException, RelationNotFoundException, RoleNotFoundException {
        if (roleName == null) {
            throw new IllegalArgumentException("null role");
        }
        final Object relation = this.retrieveRelationForId(relationId);
        if (relation instanceof RelationSupport) {
            return ((RelationSupport)relation).getRoleCardinality(roleName);
        }
        final ObjectName objectName = (ObjectName)relation;
        try {
            final Integer result = (Integer)this.server.invoke(objectName, "getRoleCardinality", new Object[] { roleName }, new String[] { "java.lang.String" });
            return result;
        }
        catch (InstanceNotFoundException e3) {
            throw new RelationNotFoundException(objectName.toString());
        }
        catch (MBeanException mbe) {
            final Exception e = mbe.getTargetException();
            if (e instanceof RoleNotFoundException) {
                throw (RoleNotFoundException)e;
            }
            throw new RuntimeException(e.toString());
        }
        catch (ReflectionException e2) {
            throw new RuntimeException(e2.toString());
        }
    }
    
    public RoleInfo getRoleInfo(final String relationTypeName, final String roleInfoName) throws IllegalArgumentException, RelationTypeNotFoundException, RoleInfoNotFoundException {
        final RelationType relationType = this.retrieveRelationTypeForName(relationTypeName);
        return relationType.getRoleInfo(roleInfoName);
    }
    
    public List getRoleInfos(final String relationTypeName) throws IllegalArgumentException, RelationTypeNotFoundException {
        final RelationType relationType = this.retrieveRelationTypeForName(relationTypeName);
        return relationType.getRoleInfos();
    }
    
    public RoleResult getRoles(final String relationId, final String[] roleNames) throws IllegalArgumentException, RelationNotFoundException, RelationServiceNotRegisteredException {
        if (roleNames == null) {
            throw new IllegalArgumentException("null role names");
        }
        this.isActive();
        final Object relation = this.retrieveRelationForId(relationId);
        if (relation instanceof RelationSupport) {
            return ((RelationSupport)relation).getRoles(roleNames);
        }
        final ObjectName objectName = (ObjectName)relation;
        try {
            final RoleResult result = (RoleResult)this.server.invoke(objectName, "getRoles", new Object[] { roleNames }, new String[] { new String[0].getClass().getName() });
            return result;
        }
        catch (InstanceNotFoundException e3) {
            throw new RelationNotFoundException(objectName.toString());
        }
        catch (MBeanException e) {
            throw new RuntimeException(e.toString());
        }
        catch (ReflectionException e2) {
            throw new RuntimeException(e2.toString());
        }
    }
    
    public Boolean hasRelation(final String relationId) throws IllegalArgumentException {
        if (relationId == null) {
            throw new IllegalArgumentException("null relation id");
        }
        return new Boolean(this.relationsById.get(relationId) != null);
    }
    
    public void isActive() throws RelationServiceNotRegisteredException {
        if (this.server == null) {
            throw new RelationServiceNotRegisteredException("Not registered");
        }
    }
    
    public String isRelation(final ObjectName objectName) throws IllegalArgumentException {
        if (objectName == null) {
            throw new IllegalArgumentException("null object name");
        }
        return this.idsByRelation.get(objectName);
    }
    
    public ObjectName isRelationMBean(final String relationId) throws IllegalArgumentException, RelationNotFoundException {
        if (relationId == null) {
            throw new IllegalArgumentException("null relation id");
        }
        final Object result = this.relationsById.get(relationId);
        if (result == null) {
            throw new RelationNotFoundException(relationId);
        }
        if (result instanceof ObjectName) {
            return (ObjectName)result;
        }
        return null;
    }
    
    public void purgeRelations() throws RelationServiceNotRegisteredException {
        this.isActive();
        while (!this.unregistered.empty()) {
            final ObjectName mbean = this.unregistered.pop();
            final HashMap relationRoles = new HashMap();
            final HashMap idRolesMap = this.idRolesMapByMBean.get(mbean);
            for (final Map.Entry entry : idRolesMap.entrySet()) {
                final String relationId = entry.getKey();
                final HashSet roleNames = entry.getValue();
                for (final String roleName : roleNames) {
                    relationRoles.put(relationId, roleName);
                }
            }
            for (final Map.Entry entry : relationRoles.entrySet()) {
                final String relationId = entry.getKey();
                final String roleName2 = entry.getValue();
                final Object relation = this.relationsById.get(relationId);
                final String typeName = this.typeNamesById.get(relationId);
                final RelationType relationType = this.typesByName.get(typeName);
                if (relation instanceof RelationSupport) {
                    final RelationSupport support = (RelationSupport)relation;
                    try {
                        final Integer cardinality = support.getRoleCardinality(roleName2);
                        final RoleInfo roleInfo = relationType.getRoleInfo(roleName2);
                        final int minDegree = roleInfo.getMinDegree();
                        if (cardinality == minDegree) {
                            this.removeRelation(relationId);
                        }
                        else {
                            support.handleMBeanUnregistration(mbean, roleName2);
                        }
                    }
                    catch (Exception e) {
                        RelationService.log.debug("Error during purge", e);
                    }
                }
                else {
                    try {
                        final ObjectName objectName = (ObjectName)relation;
                        final Integer cardinality = (Integer)this.server.invoke(objectName, "getRoleCardinality", new Object[] { roleName2 }, new String[] { "java.lang.String" });
                        final RoleInfo roleInfo = relationType.getRoleInfo(roleName2);
                        final int minDegree = roleInfo.getMinDegree();
                        if (cardinality == minDegree) {
                            this.removeRelation(relationId);
                        }
                        else {
                            this.server.invoke(objectName, "handleMBeanUnregistration", new Object[] { mbean, roleName2 }, new String[] { "java.lang.String", "java.lang.String " });
                        }
                    }
                    catch (MBeanException mbe) {
                        RelationService.log.debug("Error during purge", mbe.getTargetException());
                    }
                    catch (Exception e2) {
                        RelationService.log.debug("Error during purge", e2);
                    }
                }
            }
        }
    }
    
    public synchronized void removeRelation(final String relationId) throws IllegalArgumentException, RelationNotFoundException, RelationServiceNotRegisteredException {
        this.isActive();
        final ArrayList unregMBeans = new ArrayList(this.getReferencedMBeans(relationId).keySet());
        final Iterator iterator = unregMBeans.iterator();
        while (iterator.hasNext()) {
            final ObjectName mbean = iterator.next();
            final HashMap idRolesMap = this.idRolesMapByMBean.get(mbean);
            idRolesMap.remove(relationId);
            if (idRolesMap.size() == 0) {
                this.idRolesMapByMBean.remove(mbean);
            }
            if (!this.idsByRelation.containsKey(mbean)) {
                iterator.remove();
            }
        }
        this.sendRelationRemovalNotification(relationId, unregMBeans);
        final Object relation = this.retrieveRelationForId(relationId);
        this.relationsById.remove(relationId);
        this.idsByRelation.remove(relation);
        this.typeNamesById.remove(relationId);
        if (relation instanceof ObjectName) {
            try {
                final Attribute attribute = new Attribute("RelationServiceManagementFlag", new Boolean(false));
                this.server.setAttribute((ObjectName)relation, attribute);
            }
            catch (Exception ex) {}
            this.filter.disableObjectName((ObjectName)relation);
        }
    }
    
    public synchronized void removeRelationType(final String relationTypeName) throws IllegalArgumentException, RelationTypeNotFoundException, RelationServiceNotRegisteredException {
        if (relationTypeName == null) {
            throw new IllegalArgumentException("null relation type name");
        }
        this.isActive();
        if (!this.typesByName.containsKey(relationTypeName)) {
            throw new RelationTypeNotFoundException("relation type name not found");
        }
        final ArrayList ids = new ArrayList();
        for (final Map.Entry entry : this.typeNamesById.entrySet()) {
            if (entry.getValue().equals(relationTypeName)) {
                ids.add(entry.getKey());
            }
        }
        final Iterator iterator = ids.iterator();
        while (iterator.hasNext()) {
            try {
                this.removeRelation(iterator.next());
            }
            catch (RelationNotFoundException ignored) {}
        }
        this.typesByName.remove(relationTypeName);
    }
    
    public void sendRelationCreationNotification(final String relationId) throws IllegalArgumentException, RelationNotFoundException {
        String type = null;
        String description = null;
        if (this.relationsById.get(relationId) instanceof RelationSupport) {
            type = "jmx.relation.creation.basic";
            description = "Creation of internal relation.";
        }
        else {
            type = "jmx.relation.creation.mbean";
            description = "Creation of external relation.";
        }
        this.sendNotification(type, description, relationId, null, null, null);
    }
    
    public void sendRelationRemovalNotification(final String relationId, final List unregMBeans) throws IllegalArgumentException, RelationNotFoundException {
        String type = null;
        String description = null;
        if (this.relationsById.get(relationId) instanceof RelationSupport) {
            type = "jmx.relation.removal.basic";
            description = "Removal of internal relation.";
        }
        else {
            type = "jmx.relation.removal.mbean";
            description = "Removal of external relation.";
        }
        this.sendNotification(type, description, relationId, unregMBeans, null, null);
    }
    
    public void sendRoleUpdateNotification(final String relationId, final Role newRole, final List oldRoleValue) throws IllegalArgumentException, RelationNotFoundException {
        String type = null;
        String description = null;
        if (this.relationsById.get(relationId) instanceof RelationSupport) {
            type = "jmx.relation.update.basic";
            description = "Update of internal relation.";
        }
        else {
            type = "jmx.relation.update.mbean";
            description = "Update of external relation.";
        }
        if (newRole == null) {
            throw new IllegalArgumentException("null role");
        }
        if (oldRoleValue == null) {
            throw new IllegalArgumentException("null old role value");
        }
        this.sendNotification(type, description, relationId, null, newRole, oldRoleValue);
    }
    
    public void setPurgeFlag(final boolean value) {
        this.purgeFlag = value;
    }
    
    public void setRole(final String relationId, final Role role) throws RelationServiceNotRegisteredException, IllegalArgumentException, RelationNotFoundException, RoleNotFoundException, InvalidRoleValueException {
        if (role == null) {
            throw new IllegalArgumentException("null role");
        }
        this.isActive();
        final Object relation = this.retrieveRelationForId(relationId);
        if (relation instanceof RelationSupport) {
            try {
                ((RelationSupport)relation).setRole(role);
                return;
            }
            catch (RelationTypeNotFoundException e) {
                final RelationNotFoundException rnfe = new RelationNotFoundException(e.getMessage());
                rnfe.initCause(e);
                throw rnfe;
            }
        }
        final ObjectName objectName = (ObjectName)relation;
        try {
            this.server.setAttribute(objectName, new Attribute("Role", role));
        }
        catch (InstanceNotFoundException e6) {
            throw new RelationNotFoundException(objectName.toString());
        }
        catch (MBeanException mbe) {
            final Exception e2 = mbe.getTargetException();
            if (e2 instanceof RoleNotFoundException) {
                throw (RoleNotFoundException)e2;
            }
            if (e2 instanceof InvalidRoleValueException) {
                throw (InvalidRoleValueException)e2;
            }
            throw new RuntimeException(e2.toString());
        }
        catch (AttributeNotFoundException e3) {
            throw new RuntimeException(e3.toString());
        }
        catch (InvalidAttributeValueException e4) {
            throw new RuntimeException(e4.toString());
        }
        catch (ReflectionException e5) {
            throw new RuntimeException(e5.toString());
        }
    }
    
    public RoleResult setRoles(final String relationId, final RoleList roles) throws IllegalArgumentException, RelationServiceNotRegisteredException, RelationNotFoundException {
        if (roles == null) {
            throw new IllegalArgumentException("null roles");
        }
        this.isActive();
        final Object relation = this.retrieveRelationForId(relationId);
        if (relation instanceof RelationSupport) {
            try {
                return ((RelationSupport)relation).setRoles(roles);
            }
            catch (RelationTypeNotFoundException e) {
                throw new RuntimeException(e.toString());
            }
        }
        final ObjectName objectName = (ObjectName)relation;
        try {
            final RoleResult result = (RoleResult)this.server.invoke(objectName, "setRoles", new Object[] { roles }, new String[] { "javax.management.relation.RoleList" });
            return result;
        }
        catch (InstanceNotFoundException e4) {
            throw new RelationNotFoundException(objectName.toString());
        }
        catch (MBeanException e2) {
            throw new RuntimeException(e2.getTargetException().toString());
        }
        catch (ReflectionException e3) {
            throw new RuntimeException(e3.toString());
        }
    }
    
    public void updateRoleMap(final String relationId, final Role newRole, final List oldRoleValue) throws IllegalArgumentException, RelationServiceNotRegisteredException, RelationNotFoundException {
        if (relationId == null) {
            throw new IllegalArgumentException("null relation id");
        }
        if (newRole == null) {
            throw new IllegalArgumentException("null role");
        }
        if (oldRoleValue == null) {
            throw new IllegalArgumentException("null old role value");
        }
        this.isActive();
        if (!this.relationsById.containsKey(relationId)) {
            throw new RelationNotFoundException("Invalid relation id: " + relationId);
        }
        final String roleName = newRole.getRoleName();
        final ArrayList newRoleValue = (ArrayList)newRole.getRoleValue();
        for (final ObjectName objectName : oldRoleValue) {
            if (!newRoleValue.contains(objectName)) {
                final HashMap idRolesMap = this.idRolesMapByMBean.get(objectName);
                final HashSet roleNames = idRolesMap.get(relationId);
                roleNames.remove(roleName);
                if (roleNames.size() != 0) {
                    continue;
                }
                idRolesMap.remove(relationId);
                if (idRolesMap.size() != 0) {
                    continue;
                }
                this.idRolesMapByMBean.remove(objectName);
                this.filter.disableObjectName(objectName);
            }
        }
        for (final ObjectName objectName : newRoleValue) {
            HashMap idRolesMap = this.idRolesMapByMBean.get(objectName);
            if (idRolesMap == null) {
                idRolesMap = new HashMap();
                this.idRolesMapByMBean.put(objectName, idRolesMap);
                this.filter.enableObjectName(objectName);
            }
            HashSet roleNames = idRolesMap.get(relationId);
            if (roleNames == null) {
                roleNames = new HashSet();
                idRolesMap.put(relationId, roleNames);
            }
            if (!roleNames.contains(roleName)) {
                roleNames.add(roleName);
            }
        }
    }
    
    public ObjectName preRegister(final MBeanServer server, final ObjectName objectName) throws Exception {
        this.server = server;
        this.relationService = objectName;
        (this.filter = new MBeanServerNotificationFilter()).enableType("JMX.mbean.unregistered");
        this.filter.disableAllObjectNames();
        server.addNotificationListener(this.delegate = new ObjectName("JMImplementation:type=MBeanServerDelegate"), this, this.filter, null);
        return objectName;
    }
    
    public void postRegister(final Boolean registered) {
    }
    
    public void preDeregister() throws Exception {
    }
    
    public void postDeregister() {
        try {
            this.server.removeNotificationListener(this.delegate, this);
        }
        catch (Exception ex) {}
        this.server = null;
    }
    
    public void handleNotification(final Notification notification, final Object handback) {
        if (notification == null || !(notification instanceof MBeanServerNotification)) {
            return;
        }
        final MBeanServerNotification mbsn = (MBeanServerNotification)notification;
        if (!mbsn.getType().equals("JMX.mbean.unregistered")) {
            return;
        }
        final ObjectName objectName = mbsn.getMBeanName();
        this.unregistered.push(objectName);
        try {
            if (this.purgeFlag) {
                this.purgeRelations();
            }
        }
        catch (Exception ex) {}
        try {
            final String relationId = this.idsByRelation.get(objectName);
            if (relationId != null) {
                this.removeRelation(relationId);
            }
        }
        catch (Exception ex2) {}
    }
    
    public MBeanNotificationInfo[] getNotificationInfo() {
        final MBeanNotificationInfo[] result = { null };
        final String[] types = { "jmx.relation.creation.basic", "jmx.relation.removal.basic", "jmx.relation.update.basic", "jmx.relation.creation.mbean", "jmx.relation.removal.mbean", "jmx.relation.update.mbean" };
        result[0] = new MBeanNotificationInfo(types, "javax.management.relation.RelationNotification", "Notifications sent by the Relation Service MBean");
        return result;
    }
    
    private void createMissingRoles(final String relationTypeName, final RoleList roleList) throws RelationTypeNotFoundException {
        if (relationTypeName == null) {
            throw new RelationTypeNotFoundException("RelationType name null not found");
        }
        final RelationType relationType = this.retrieveRelationTypeForName(relationTypeName);
        final ArrayList roleInfos = (ArrayList)relationType.getRoleInfos();
        for (final RoleInfo roleInfo : roleInfos) {
            boolean found = false;
            for (final Role role : roleList) {
                if (role.getRoleName().equals(roleInfo.getName())) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                roleList.add(new Role(roleInfo.getName(), new RoleList()));
            }
        }
    }
    
    private Object retrieveRelationForId(final String relationId) throws IllegalArgumentException, RelationNotFoundException {
        if (relationId == null) {
            throw new IllegalArgumentException("null relation id");
        }
        final Object result = this.relationsById.get(relationId);
        if (result == null) {
            throw new RelationNotFoundException(relationId);
        }
        return result;
    }
    
    private String retrieveTypeNameForId(final String relationId) throws IllegalArgumentException, RelationNotFoundException {
        if (relationId == null) {
            throw new IllegalArgumentException("null relation id");
        }
        final String result = this.typeNamesById.get(relationId);
        if (result == null) {
            throw new RelationNotFoundException(relationId);
        }
        return result;
    }
    
    private RelationType retrieveRelationTypeForName(final String relationTypeName) throws IllegalArgumentException, RelationTypeNotFoundException {
        if (relationTypeName == null) {
            throw new IllegalArgumentException("Null relation type name");
        }
        final RelationType result = this.typesByName.get(relationTypeName);
        if (result == null) {
            throw new RelationTypeNotFoundException(relationTypeName);
        }
        return result;
    }
    
    private void sendNotification(final String type, final String description, final String relationId, final List unregMBeans, final Role newRole, final List oldRoleValue) throws IllegalArgumentException, RelationNotFoundException {
        if (type == null) {
            throw new IllegalArgumentException("null notification type");
        }
        final String typeName = this.retrieveTypeNameForId(relationId);
        final Object relation = this.retrieveRelationForId(relationId);
        ObjectName relationName = null;
        if (relation instanceof ObjectName) {
            relationName = (ObjectName)relation;
        }
        final long sequence;
        synchronized (this) {
            final long notificationSequence = this.notificationSequence + 1L;
            this.notificationSequence = notificationSequence;
            sequence = notificationSequence;
        }
        if (type.equals("jmx.relation.update.basic") || type.equals("jmx.relation.update.mbean")) {
            this.sendNotification(new RelationNotification(type, this.relationService, sequence, System.currentTimeMillis(), description, relationId, typeName, relationName, newRole.getRoleName(), newRole.getRoleValue(), oldRoleValue));
        }
        else {
            this.sendNotification(new RelationNotification(type, this.relationService, sequence, System.currentTimeMillis(), description, relationId, typeName, relationName, unregMBeans));
        }
    }
    
    private synchronized void validateAndAddRelation(final String relationId, final Object relation, final String relationTypeName) throws InvalidRelationIdException {
        if (this.relationsById.containsKey(relationId)) {
            throw new InvalidRelationIdException(relationId);
        }
        this.relationsById.put(relationId, relation);
        this.idsByRelation.put(relation, relationId);
        this.typeNamesById.put(relationId, relationTypeName);
        RoleList roles = null;
        if (relation instanceof RelationSupport) {
            final RelationSupport support = (RelationSupport)relation;
            roles = support.retrieveAllRoles();
        }
        else {
            try {
                roles = (RoleList)this.server.invoke((ObjectName)relation, "retrieveAllRoles", new Object[0], new String[0]);
            }
            catch (Exception e) {
                throw new RuntimeException(e.toString());
            }
        }
        for (final Role role : roles) {
            try {
                this.updateRoleMap(relationId, role, role.getRoleValue());
            }
            catch (Exception e2) {
                throw new RuntimeException(e2.toString());
            }
        }
        if (relation instanceof RelationSupport) {
            final RelationSupport support2 = (RelationSupport)relation;
            support2.setRelationServiceManagementFlag(new Boolean(true));
        }
        else {
            try {
                final Attribute attribute = new Attribute("RelationServiceManagementFlag", new Boolean(true));
                this.server.setAttribute((ObjectName)relation, attribute);
            }
            catch (Exception ex) {}
        }
        try {
            this.sendRelationCreationNotification(relationId);
        }
        catch (RelationNotFoundException e3) {
            throw new RuntimeException(e3.toString());
        }
    }
    
    private void validateRelationType(final RelationType relationType) throws InvalidRelationTypeException {
        final HashSet roleNames = new HashSet();
        final ArrayList roleInfos = (ArrayList)relationType.getRoleInfos();
        if (roleInfos == null) {
            throw new InvalidRelationTypeException("Null role infos");
        }
        if (roleInfos.size() == 0) {
            throw new InvalidRelationTypeException("No role infos");
        }
        synchronized (roleInfos) {
            for (final RoleInfo roleInfo : roleInfos) {
                if (roleInfo == null) {
                    throw new InvalidRelationTypeException("Null role");
                }
                if (roleNames.contains(roleInfo.getName())) {
                    throw new InvalidRelationTypeException("Duplicate role name" + roleInfo.getName());
                }
                roleNames.add(roleInfo.getName());
            }
        }
    }
    
    static {
        log = Logger.getLogger(RelationService.class);
    }
}
