// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.invocation;

import java.io.ObjectOutput;
import java.io.ObjectInput;
import java.util.Arrays;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.ByteArrayOutputStream;
import java.io.Externalizable;

public class MarshalledValue implements Externalizable
{
    private static final long serialVersionUID = -1527598981234110311L;
    private byte[] serializedForm;
    private int hashCode;
    
    public MarshalledValue() {
    }
    
    public MarshalledValue(final Object obj) throws IOException {
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final MarshalledValueOutputStream mvos = new MarshalledValueOutputStream(baos);
        mvos.writeObject(obj);
        mvos.flush();
        this.serializedForm = baos.toByteArray();
        mvos.close();
        int hash = 0;
        for (int i = 0; i < this.serializedForm.length; ++i) {
            hash = 31 * hash + this.serializedForm[i];
        }
        this.hashCode = hash;
    }
    
    public Object get() throws IOException, ClassNotFoundException {
        if (this.serializedForm == null) {
            return null;
        }
        final ByteArrayInputStream bais = new ByteArrayInputStream(this.serializedForm);
        final MarshalledValueInputStream mvis = new MarshalledValueInputStream(bais);
        final Object retValue = mvis.readObject();
        mvis.close();
        return retValue;
    }
    
    public byte[] toByteArray() {
        return this.serializedForm;
    }
    
    public int size() {
        final int size = (this.serializedForm != null) ? this.serializedForm.length : 0;
        return size;
    }
    
    public int hashCode() {
        return this.hashCode;
    }
    
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        boolean equals = false;
        if (obj instanceof MarshalledValue) {
            final MarshalledValue mv = (MarshalledValue)obj;
            equals = (this.serializedForm == mv.serializedForm || Arrays.equals(this.serializedForm, mv.serializedForm));
        }
        return equals;
    }
    
    public void readExternal(final ObjectInput in) throws IOException, ClassNotFoundException {
        final int length = in.readInt();
        this.serializedForm = null;
        if (length > 0) {
            in.readFully(this.serializedForm = new byte[length]);
        }
        this.hashCode = in.readInt();
    }
    
    public void writeExternal(final ObjectOutput out) throws IOException {
        final int length = (this.serializedForm != null) ? this.serializedForm.length : 0;
        out.writeInt(length);
        if (length > 0) {
            out.write(this.serializedForm);
        }
        out.writeInt(this.hashCode);
    }
}
