// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.security;

import java.lang.reflect.Method;
import java.lang.reflect.Constructor;
import java.util.StringTokenizer;
import java.util.HashSet;
import java.util.Collections;
import java.util.Set;
import org.apache.activemq.filter.DestinationMapEntry;

public class AuthorizationEntry extends DestinationMapEntry
{
    private Set<Object> readACLs;
    private Set<Object> writeACLs;
    private Set<Object> adminACLs;
    private String adminRoles;
    private String readRoles;
    private String writeRoles;
    private String groupClass;
    
    public AuthorizationEntry() {
        this.readACLs = this.emptySet();
        this.writeACLs = this.emptySet();
        this.adminACLs = this.emptySet();
        this.groupClass = "org.apache.activemq.jaas.GroupPrincipal";
    }
    
    public String getGroupClass() {
        return this.groupClass;
    }
    
    private Set<Object> emptySet() {
        return (Set<Object>)Collections.EMPTY_SET;
    }
    
    public void setGroupClass(final String groupClass) {
        this.groupClass = groupClass;
    }
    
    public Set<Object> getAdminACLs() {
        return this.adminACLs;
    }
    
    public void setAdminACLs(final Set<Object> adminACLs) {
        this.adminACLs = adminACLs;
    }
    
    public Set<Object> getReadACLs() {
        return this.readACLs;
    }
    
    public void setReadACLs(final Set<Object> readACLs) {
        this.readACLs = readACLs;
    }
    
    public Set<Object> getWriteACLs() {
        return this.writeACLs;
    }
    
    public void setWriteACLs(final Set<Object> writeACLs) {
        this.writeACLs = writeACLs;
    }
    
    public void setAdmin(final String roles) throws Exception {
        this.adminRoles = roles;
        this.setAdminACLs(this.parseACLs(this.adminRoles));
    }
    
    public void setRead(final String roles) throws Exception {
        this.readRoles = roles;
        this.setReadACLs(this.parseACLs(this.readRoles));
    }
    
    public void setWrite(final String roles) throws Exception {
        this.writeRoles = roles;
        this.setWriteACLs(this.parseACLs(this.writeRoles));
    }
    
    protected Set<Object> parseACLs(final String roles) throws Exception {
        final Set<Object> answer = new HashSet<Object>();
        final StringTokenizer iter = new StringTokenizer(roles, ",");
        while (iter.hasMoreTokens()) {
            final String name = iter.nextToken().trim();
            final Class[] paramClass = { String.class };
            final Object[] param = { name };
            try {
                final Class cls = Class.forName(this.groupClass);
                Constructor[] constructors;
                int i;
                Class[] paramTypes;
                for (constructors = cls.getConstructors(), i = 0; i < constructors.length; ++i) {
                    paramTypes = constructors[i].getParameterTypes();
                    if (paramTypes.length != 0 && paramTypes[0].equals(paramClass[0])) {
                        break;
                    }
                }
                if (i < constructors.length) {
                    final Object instance = constructors[i].newInstance(param);
                    answer.add(instance);
                }
                else {
                    final Object instance = cls.newInstance();
                    Method[] methods;
                    Class[] paramTypes2;
                    for (methods = cls.getMethods(), i = 0, i = 0; i < methods.length; ++i) {
                        paramTypes2 = methods[i].getParameterTypes();
                        if (paramTypes2.length != 0 && methods[i].getName().equals("setName") && paramTypes2[0].equals(paramClass[0])) {
                            break;
                        }
                    }
                    if (i >= methods.length) {
                        throw new NoSuchMethodException();
                    }
                    methods[i].invoke(instance, param);
                    answer.add(instance);
                }
            }
            catch (Exception e) {
                throw e;
            }
        }
        return answer;
    }
    
    @Override
    public void afterPropertiesSet() throws Exception {
        super.afterPropertiesSet();
        if (this.adminRoles != null) {
            this.setAdminACLs(this.parseACLs(this.adminRoles));
        }
        if (this.writeRoles != null) {
            this.setWriteACLs(this.parseACLs(this.writeRoles));
        }
        if (this.readRoles != null) {
            this.setReadACLs(this.parseACLs(this.readRoles));
        }
    }
}
