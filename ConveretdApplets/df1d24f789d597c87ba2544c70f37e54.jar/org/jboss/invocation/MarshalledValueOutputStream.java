// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.invocation;

import java.security.AccessController;
import java.security.PrivilegedAction;
import java.rmi.server.RemoteObject;
import java.rmi.server.RemoteStub;
import java.rmi.Remote;
import java.io.IOException;
import java.io.OutputStream;
import java.io.ObjectOutputStream;

public class MarshalledValueOutputStream extends ObjectOutputStream
{
    public MarshalledValueOutputStream(final OutputStream os) throws IOException {
        super(os);
        EnableReplaceObjectAction.enableReplaceObject(this);
    }
    
    protected void annotateClass(final Class cl) throws IOException {
        super.annotateClass(cl);
    }
    
    protected void annotateProxyClass(final Class cl) throws IOException {
        super.annotateProxyClass(cl);
    }
    
    protected Object replaceObject(Object obj) throws IOException {
        if (obj instanceof Remote && !(obj instanceof RemoteStub)) {
            final Remote remote = (Remote)obj;
            try {
                obj = RemoteObject.toStub(remote);
            }
            catch (IOException ex) {}
        }
        return obj;
    }
    
    private static class EnableReplaceObjectAction implements PrivilegedAction
    {
        MarshalledValueOutputStream os;
        
        EnableReplaceObjectAction(final MarshalledValueOutputStream os) {
            this.os = os;
        }
        
        public Object run() {
            this.os.enableReplaceObject(true);
            return null;
        }
        
        static void enableReplaceObject(final MarshalledValueOutputStream os) {
            final EnableReplaceObjectAction action = new EnableReplaceObjectAction(os);
            AccessController.doPrivileged((PrivilegedAction<Object>)action);
        }
    }
}
