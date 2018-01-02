// 
// Decompiled by Procyon v0.5.30
// 

package javax.management.relation;

import java.util.HashSet;
import java.util.Iterator;
import java.util.ArrayList;
import javax.management.MBeanException;
import javax.management.MBeanServer;
import javax.management.ObjectName;

class RoleValidator
{
    public static int checkRole(final ObjectName relationService, final MBeanServer server, final String relationTypeName, final Role role, final boolean write) throws RelationTypeNotFoundException {
        RoleInfo roleInfo = null;
        try {
            roleInfo = (RoleInfo)server.invoke(relationService, "getRoleInfo", new Object[] { relationTypeName, role.getRoleName() }, new String[] { "java.lang.String", "java.lang.String" });
        }
        catch (MBeanException mbe) {
            final Exception e = mbe.getTargetException();
            if (e instanceof RelationTypeNotFoundException) {
                throw (RelationTypeNotFoundException)e;
            }
            if (e instanceof RoleInfoNotFoundException) {
                return 1;
            }
            throw new RuntimeException(e.toString());
        }
        catch (Exception e2) {
            throw new RuntimeException(e2.toString());
        }
        if (write && !roleInfo.isWritable()) {
            return 3;
        }
        final ArrayList mbeans = (ArrayList)role.getRoleValue();
        final int beanCount = mbeans.size();
        final int minimum = roleInfo.getMinDegree();
        if (minimum != RoleInfo.ROLE_CARDINALITY_INFINITY && minimum > beanCount) {
            return 4;
        }
        final int maximum = roleInfo.getMaxDegree();
        if (maximum != RoleInfo.ROLE_CARDINALITY_INFINITY && maximum < beanCount) {
            return 5;
        }
        final String className = roleInfo.getRefMBeanClassName();
        final Iterator iterator = mbeans.iterator();
        while (iterator.hasNext()) {
            try {
                final ObjectName objectName = iterator.next();
                if (!server.isInstanceOf(objectName, className)) {
                    return 6;
                }
                continue;
            }
            catch (Exception e3) {
                return 7;
            }
            break;
        }
        return 0;
    }
    
    public static RoleResult checkRoles(final ObjectName relationService, final MBeanServer server, final String relationTypeName, final RoleList roleList, final boolean write) throws RelationTypeNotFoundException {
        final RoleList resolved = new RoleList();
        final RoleUnresolvedList unresolved = new RoleUnresolvedList();
        final RoleResult result = new RoleResult(resolved, unresolved);
        for (final Role role : roleList) {
            final int status = checkRole(relationService, server, relationTypeName, role, write);
            if (status == 0) {
                resolved.add(role);
            }
            else {
                unresolved.add(new RoleUnresolved(role.getRoleName(), role.getRoleValue(), status));
            }
        }
        return result;
    }
    
    public static void validateRole(final ObjectName relationService, final MBeanServer server, final String relationTypeName, final Role role, final boolean write) throws InvalidRoleValueException, RelationTypeNotFoundException, RoleNotFoundException {
        final int status = checkRole(relationService, server, relationTypeName, role, write);
        if (status == 1) {
            throw new RoleNotFoundException(role.getRoleName());
        }
        if (status == 3) {
            throw new RoleNotFoundException(role.getRoleName() + " not writable");
        }
        if (status != 0) {
            throw new InvalidRoleValueException(role.getRoleName());
        }
    }
    
    public static void validateRoles(final ObjectName relationService, final MBeanServer server, final String relationTypeName, final RoleList roleList, final boolean write) throws InvalidRoleValueException, RelationTypeNotFoundException, RoleNotFoundException {
        final HashSet roleNames = new HashSet();
        for (final Object roleName : roleList) {
            if (roleNames.contains(roleName)) {
                throw new InvalidRoleValueException("Duplicate role " + roleName);
            }
            roleNames.add(roleName);
        }
        final RoleResult result = checkRoles(relationService, server, relationTypeName, roleList, write);
        final RoleUnresolvedList errors = result.getRolesUnresolved();
        final Iterator iterator = errors.iterator();
        if (!iterator.hasNext()) {
            return;
        }
        final RoleUnresolved unresolved = iterator.next();
        final int status = unresolved.getProblemType();
        if (status == 1) {
            throw new RoleNotFoundException(unresolved.getRoleName());
        }
        if (status == 3) {
            throw new RoleNotFoundException(unresolved.getRoleName() + " not writable");
        }
        throw new InvalidRoleValueException(unresolved.getRoleName());
    }
}
