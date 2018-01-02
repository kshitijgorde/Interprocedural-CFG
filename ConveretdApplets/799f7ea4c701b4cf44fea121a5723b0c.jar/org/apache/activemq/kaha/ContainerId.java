// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.kaha;

import java.io.ObjectInput;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.Externalizable;

public class ContainerId implements Externalizable
{
    private static final long serialVersionUID = -8883779541021821943L;
    private Object key;
    private String dataContainerName;
    
    public ContainerId() {
    }
    
    public ContainerId(final Object key, final String dataContainerName) {
        this.key = key;
        this.dataContainerName = dataContainerName;
    }
    
    public String getDataContainerName() {
        return this.dataContainerName;
    }
    
    public Object getKey() {
        return this.key;
    }
    
    @Override
    public int hashCode() {
        return this.key.hashCode() ^ this.dataContainerName.hashCode();
    }
    
    @Override
    public boolean equals(final Object obj) {
        if (obj == null || obj.getClass() != ContainerId.class) {
            return false;
        }
        final ContainerId other = (ContainerId)obj;
        return other.key.equals(this.key) && other.dataContainerName.equals(this.dataContainerName);
    }
    
    @Override
    public void writeExternal(final ObjectOutput out) throws IOException {
        out.writeUTF(this.getDataContainerName());
        out.writeObject(this.key);
    }
    
    @Override
    public void readExternal(final ObjectInput in) throws IOException, ClassNotFoundException {
        this.dataContainerName = in.readUTF();
        this.key = in.readObject();
    }
    
    @Override
    public String toString() {
        return "CID{" + this.dataContainerName + ":" + this.key + "}";
    }
}
