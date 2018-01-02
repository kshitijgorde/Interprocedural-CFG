// 
// Decompiled by Procyon v0.5.30
// 

package javax.management;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.Collection;
import java.util.TreeSet;
import java.security.Permission;

public class MBeanPermission extends Permission
{
    private static final long serialVersionUID = -2416928705275160661L;
    private static ObjectName ANY_NAME;
    private static TreeSet VALID_ACTIONS;
    private transient String className;
    private transient boolean prefixMatch;
    private transient String member;
    private transient ObjectName objectName;
    private transient TreeSet actionSet;
    private String actions;
    
    public MBeanPermission(final String name, final String actions) {
        super(name);
        this.parseName(name);
        this.parseActions(actions);
    }
    
    public MBeanPermission(final String className, final String member, final ObjectName objectName, final String actions) {
        super(((className == null) ? "-" : className) + "#" + ((member == null) ? "-" : member) + "[" + ((objectName == null) ? "-" : objectName.toString()) + "]");
        this.className = className;
        this.member = member;
        this.objectName = objectName;
        this.parseActions(actions);
    }
    
    public String getActions() {
        return this.actions;
    }
    
    public int hashCode() {
        int hashCode = this.getName().hashCode();
        if (this.actionSet != null) {
            hashCode += this.actionSet.hashCode();
        }
        return hashCode;
    }
    
    public boolean implies(final Permission p) {
        if (p == null || !(p instanceof MBeanPermission)) {
            return false;
        }
        final MBeanPermission perm = (MBeanPermission)p;
        boolean implies = false;
        if (perm.className == null) {
            implies = true;
        }
        else if (this.className == null) {
            implies = false;
        }
        else if (this.className.length() == 0) {
            implies = true;
        }
        else if (this.prefixMatch && perm.className != null) {
            implies = perm.className.startsWith(this.className);
        }
        else {
            implies = this.className.equals(perm.className);
        }
        if (implies) {
            implies = (perm.member == null || (this.member != null && (this.member.length() == 0 || this.member.equals(perm.member))));
        }
        if (implies) {
            if (perm.objectName == null) {
                implies = true;
            }
            else if (this.objectName == null) {
                implies = false;
            }
            else {
                implies = (this.objectName == perm.objectName || this.objectName.apply(perm.objectName));
                if (!implies && perm.objectName.isPattern()) {
                    implies = this.objectName.equals(perm.objectName);
                }
            }
        }
        if (this.actionSet != null && implies) {
            implies = (perm.actionSet != null && this.actionSet.containsAll(perm.actionSet));
        }
        return implies;
    }
    
    public boolean equals(final Object p) {
        if (p == null || !(p instanceof MBeanPermission)) {
            return false;
        }
        final MBeanPermission perm = (MBeanPermission)p;
        boolean equals = this.getName().equals(perm.getName());
        if (equals) {
            equals = (this.actionSet == perm.actionSet);
            if (!equals && this.actionSet != null) {
                equals = this.actionSet.equals(perm.actionSet);
            }
        }
        return equals;
    }
    
    private void parseName(final String name) throws IllegalArgumentException {
        if (name == null || name.length() == 0) {
            throw new IllegalArgumentException("name must not be empty or null");
        }
        final StringTokenizer tokenizer = new StringTokenizer(name, "#[]", true);
        boolean inMember = false;
        boolean inObjectName = false;
        this.className = tokenizer.nextToken();
        if (this.className.equals("#")) {
            this.className = "";
            inMember = true;
        }
        else if (this.className.equals("[")) {
            this.className = "";
            inObjectName = true;
        }
        else if (this.className.equals("*")) {
            this.className = "";
        }
        else if (this.className.equals("-")) {
            this.className = null;
        }
        else if (this.className.endsWith(".*")) {
            this.className = this.className.substring(0, this.className.length() - 2);
            this.prefixMatch = true;
        }
        this.member = "";
        if (!inObjectName) {
            if (inMember) {
                if (tokenizer.hasMoreTokens()) {
                    this.member = tokenizer.nextToken();
                    if (this.member.equals("[")) {
                        inObjectName = true;
                        this.member = "";
                    }
                    else if (this.member.equals("*")) {
                        this.member = "";
                    }
                    else if (this.member.equals("-")) {
                        this.member = null;
                    }
                }
            }
            else if (tokenizer.hasMoreTokens()) {
                this.member = tokenizer.nextToken();
                if (this.member.equals("#")) {
                    if (tokenizer.hasMoreTokens()) {
                        this.member = tokenizer.nextToken();
                        if (this.member.equals("[")) {
                            inObjectName = true;
                            this.member = "";
                        }
                        else if (this.member.equals("*")) {
                            this.member = "";
                        }
                        else if (this.member.equals("-")) {
                            this.member = null;
                        }
                    }
                    else {
                        this.member = "";
                    }
                }
                else {
                    inObjectName = true;
                }
            }
        }
        if (MBeanPermission.ANY_NAME == null) {
            try {
                MBeanPermission.ANY_NAME = new ObjectName("*:*");
            }
            catch (Exception e2) {
                throw new IllegalStateException("Could not create ObjectName(*:*)");
            }
        }
        this.objectName = MBeanPermission.ANY_NAME;
        if (!inObjectName && tokenizer.hasMoreTokens()) {
            inObjectName = true;
            tokenizer.nextToken();
        }
        if (inObjectName) {
            final String token = tokenizer.nextToken("]");
            try {
                if (token.equals("-")) {
                    this.objectName = null;
                }
                else if (token.equals("]")) {
                    this.objectName = MBeanPermission.ANY_NAME;
                }
                else {
                    this.objectName = new ObjectName(token);
                }
            }
            catch (Exception e) {
                final IllegalArgumentException ex = new IllegalArgumentException("Invalid objectName");
                ex.initCause(e);
                throw ex;
            }
        }
    }
    
    private void parseActions(final String actions) throws IllegalArgumentException {
        if (actions == null || actions.length() == 0) {
            throw new IllegalArgumentException("actions must not be empty or null");
        }
        if (actions.equals("*")) {
            this.actionSet = null;
            this.actions = "*";
        }
        else {
            this.actionSet = new TreeSet();
            final StringTokenizer tokenizer = new StringTokenizer(actions, ", ");
            while (tokenizer.hasMoreTokens()) {
                final String action = tokenizer.nextToken();
                if (!MBeanPermission.VALID_ACTIONS.contains(action)) {
                    throw new IllegalArgumentException(action + " is not one of: " + MBeanPermission.VALID_ACTIONS);
                }
                this.actionSet.add(action);
            }
            if (this.actionSet.contains("queryMBeans")) {
                this.actionSet.add("queryNames");
            }
            final StringBuffer tmp = new StringBuffer();
            final Iterator iter = this.actionSet.iterator();
            while (iter.hasNext()) {
                tmp.append(iter.next());
                tmp.append(',');
            }
            tmp.setLength(tmp.length() - 1);
            this.actions = tmp.toString();
        }
    }
    
    private void readObject(final ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.defaultReadObject();
        this.parseName(this.getName());
        this.parseActions(this.getActions());
    }
    
    static {
        (MBeanPermission.VALID_ACTIONS = new TreeSet()).add("addNotificationListener");
        MBeanPermission.VALID_ACTIONS.add("getAttribute");
        MBeanPermission.VALID_ACTIONS.add("getClassLoader");
        MBeanPermission.VALID_ACTIONS.add("getClassLoaderFor");
        MBeanPermission.VALID_ACTIONS.add("getClassLoaderRepository");
        MBeanPermission.VALID_ACTIONS.add("getDomains");
        MBeanPermission.VALID_ACTIONS.add("getMBeanInfo");
        MBeanPermission.VALID_ACTIONS.add("getObjectInstance");
        MBeanPermission.VALID_ACTIONS.add("instantiate");
        MBeanPermission.VALID_ACTIONS.add("invoke");
        MBeanPermission.VALID_ACTIONS.add("isInstanceOf");
        MBeanPermission.VALID_ACTIONS.add("queryMBeans");
        MBeanPermission.VALID_ACTIONS.add("queryNames");
        MBeanPermission.VALID_ACTIONS.add("registerMBean");
        MBeanPermission.VALID_ACTIONS.add("removeNotificationListener");
        MBeanPermission.VALID_ACTIONS.add("setAttribute");
        MBeanPermission.VALID_ACTIONS.add("unregisterMBean");
    }
}
