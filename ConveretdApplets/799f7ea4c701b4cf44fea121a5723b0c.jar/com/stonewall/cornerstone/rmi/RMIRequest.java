// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.rmi;

import org.xmodel.Xlate;
import com.stonewall.cornerstone.jms.msg.Reply;
import org.xmodel.IModelObject;
import com.stonewall.cornerstone.jms.msg.JmsMessage;
import com.stonewall.cornerstone.utility.Transaction;
import com.stonewall.cornerstone.security.User;
import javax.jms.JMSException;
import javax.jms.MessageListener;
import com.stonewall.cornerstone.jms.msg.Request;

public class RMIRequest extends Request
{
    public static final String rootTag = "rmi:rmiRequest";
    private String destId;
    
    public static final void setListener(final MessageListener listener, final String qn, final boolean reset) throws JMSException {
        Request.addListener(qn, listener, reset);
    }
    
    public RMIRequest(final Destination dest) {
        super("rmi:rmiRequest");
        this.destId = dest.getValue();
        this.root.getCreateChild("rmi:userId").setValue(User.getCurrent().getId());
        this.root.getCreateChild("rmi:sessionId").setValue(User.getCurrent().getSessionId());
        final Transaction transaction = Transaction.getCurrent();
        if (transaction != null) {
            this.root.getCreateChild("rmi:transactionId").setValue(transaction.getId());
        }
    }
    
    public RMIRequest(final JmsMessage message, final IModelObject o) throws Exception {
        super(message, o);
    }
    
    public RMIRequest(final IModelObject root) {
        super(root);
    }
    
    @Override
    public Reply createReply() {
        return new RMIReply(this);
    }
    
    @Override
    protected String getDestinationId() {
        return this.destId;
    }
    
    public void setTarget(final IModelObject target) {
        this.root.addChild(target);
    }
    
    public Target getTarget() {
        return new Target(this.root.getFirstChild("rmi:target"));
    }
    
    public void setMethod(final IModelObject method) {
        this.root.addChild(method);
    }
    
    public Method getMethod() {
        return new Method(this.root.getFirstChild("rmi:method"));
    }
    
    public String getUserId() {
        return Xlate.get(this.root.getFirstChild("rmi:userId"), (String)null);
    }
    
    public String getSessionId() {
        return Xlate.get(this.root.getFirstChild("rmi:sessionId"), (String)null);
    }
    
    public String getTransactionId() {
        return Xlate.get(this.root.getFirstChild("rmi:transactionId"), (String)null);
    }
}
