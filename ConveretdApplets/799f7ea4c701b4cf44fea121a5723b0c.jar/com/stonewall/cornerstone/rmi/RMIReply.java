// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.rmi;

import java.lang.reflect.Constructor;
import org.xmodel.Xlate;
import com.stonewall.cornerstone.jms.msg.JmsMessage;
import org.xmodel.IModelObject;
import com.stonewall.cornerstone.jms.msg.Request;
import com.stonewall.cornerstone.jms.msg.Reply;

public class RMIReply extends Reply
{
    public static final String rootTag = "rmi:rmiReply";
    
    public RMIReply(final String rootTag) {
        super(rootTag);
    }
    
    public RMIReply(final Request request) {
        super("rmi:rmiReply", request);
    }
    
    public RMIReply(final IModelObject root) {
        super(root);
    }
    
    public RMIReply(final JmsMessage message, final IModelObject o) throws Exception {
        super(message, o);
    }
    
    protected void setException(final Exception e) {
        final IModelObject ex = this.root.getCreateChild("rmi:exception");
        ex.setAttribute("type", e.getClass().getName());
        ex.getCreateChild("rmi:message").setValue(e.getMessage());
    }
    
    protected Exception getException() {
        final IModelObject exception = this.root.getFirstChild("rmi:exception");
        if (exception == null) {
            return null;
        }
        final String type = Xlate.get(exception, "type", (String)null);
        try {
            if (exception.getFirstChild("rmi:message") != null) {
                final String msg = Xlate.get(exception.getFirstChild("rmi:message"), (String)null);
                final Constructor con = Class.forName(type).getConstructor(String.class);
                return con.newInstance(msg);
            }
            final Constructor con2 = Class.forName(type).getConstructor(IModelObject.class);
            return con2.newInstance(exception.getChildren().get(0));
        }
        catch (Exception ex) {
            return new RMIException("Unknown exception type: " + type);
        }
    }
    
    protected void setReturnValue(final IModelObject value) {
        if (value == null) {
            return;
        }
        this.root.getCreateChild("rmi:return").addChild(value);
    }
    
    public IModelObject getReturnValue() {
        final IModelObject e = this.root.getFirstChild("rmi:return");
        if (e == null) {
            return null;
        }
        return e.getChildren().get(0);
    }
}
