// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.deployment;

import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Iterator;
import java.util.Collection;

public class IncompleteDeploymentException extends DeploymentException
{
    private static final long serialVersionUID = 1428860525880893167L;
    private final transient Collection mbeansWaitingForClasses;
    private final transient Collection mbeansWaitingForDepends;
    private final transient Collection rootCause;
    private final transient Collection incompletePackages;
    private final transient Collection waitingForDeployer;
    private String string;
    
    public IncompleteDeploymentException(final Collection mbeansWaitingForClasses, final Collection mbeansWaitingForDepends, final Collection rootCause, final Collection incompletePackages, final Collection waitingForDeployer) {
        if (mbeansWaitingForClasses == null || mbeansWaitingForDepends == null || rootCause == null || incompletePackages == null || waitingForDeployer == null) {
            throw new IllegalArgumentException("All lists in IncompleteDeploymentException constructor must be supplied");
        }
        this.mbeansWaitingForClasses = mbeansWaitingForClasses;
        this.mbeansWaitingForDepends = mbeansWaitingForDepends;
        this.rootCause = rootCause;
        this.incompletePackages = incompletePackages;
        this.waitingForDeployer = waitingForDeployer;
    }
    
    public Collection getMbeansWaitingForClasses() {
        return this.mbeansWaitingForClasses;
    }
    
    public Collection getMbeansWaitingForDepends() {
        return this.mbeansWaitingForDepends;
    }
    
    public Collection getIncompletePackages() {
        return this.incompletePackages;
    }
    
    public Collection getWaitingForDeployer() {
        return this.waitingForDeployer;
    }
    
    public boolean isEmpty() {
        return this.mbeansWaitingForClasses.size() == 0 && this.mbeansWaitingForDepends.size() == 0 && this.rootCause.size() == 0 && this.incompletePackages.size() == 0 && this.waitingForDeployer.size() == 0;
    }
    
    public String toString() {
        if (this.string != null) {
            return this.string;
        }
        final StringBuffer result = new StringBuffer("Incomplete Deployment listing:\n\n");
        if (this.waitingForDeployer.size() != 0) {
            result.append("--- Packages waiting for a deployer ---\n");
            this.appendCollection(result, this.waitingForDeployer);
        }
        if (this.incompletePackages.size() != 0) {
            result.append("--- Incompletely deployed packages ---\n");
            this.appendCollection(result, this.incompletePackages);
        }
        if (this.mbeansWaitingForClasses.size() != 0) {
            result.append("--- MBeans waiting for classes ---\n");
            this.appendCollection(result, this.mbeansWaitingForClasses);
        }
        if (this.mbeansWaitingForDepends.size() != 0) {
            result.append("--- MBeans waiting for other MBeans ---\n");
            this.appendCollection(result, this.mbeansWaitingForDepends);
        }
        if (this.rootCause.size() != 0) {
            result.append("--- MBEANS THAT ARE THE ROOT CAUSE OF THE PROBLEM ---\n");
            this.appendCollection(result, this.rootCause);
        }
        return this.string = result.toString();
    }
    
    private void appendCollection(final StringBuffer result, final Collection c) {
        final Iterator i = c.iterator();
        while (i.hasNext()) {
            result.append(i.next().toString()).append('\n');
        }
    }
    
    private void readObject(final ObjectInputStream s) throws IOException, ClassNotFoundException {
        s.defaultReadObject();
    }
    
    private void writeObject(final ObjectOutputStream s) throws IOException {
        this.toString();
        s.defaultWriteObject();
    }
}
