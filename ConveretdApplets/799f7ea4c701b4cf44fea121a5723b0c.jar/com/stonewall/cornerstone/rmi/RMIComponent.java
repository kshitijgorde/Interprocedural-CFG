// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.rmi;

import com.stonewall.cornerstone.utility.Transaction;
import com.stonewall.cornerstone.security.User;
import org.xmodel.IModelObject;
import com.stonewall.cornerstone.thread.ExceptionListener;
import javax.jms.JMSException;
import com.stonewall.cornerstone.jms.msg.Request;
import javax.jms.MessageListener;
import com.stonewall.cornerstone.component.ComponentServer;
import com.stonewall.cornerstone.jms.msg.RegistrySelector;
import com.stonewall.cornerstone.jms.msg.JMSPropertySelector;
import java.util.HashMap;
import org.xmodel.log.Log;
import java.util.Map;
import com.stonewall.cornerstone.thread.ThreadPool;
import com.stonewall.cornerstone.component.Component;
import com.stonewall.cornerstone.jms.msg.Dispatcher;

public class RMIComponent extends Dispatcher implements Component
{
    private ThreadPool threadPool;
    private boolean reset;
    private int threadCount;
    private static int MAX_THREADS;
    private DataFactory factory;
    private Map<String, Object> objects;
    public static final Log log;
    
    static {
        RMIComponent.MAX_THREADS = 10;
        log = Log.getLog(RMIComponent.class);
    }
    
    public RMIComponent(final boolean reset) {
        this(reset, new ThreadPool("RMI", RMIComponent.MAX_THREADS));
    }
    
    public RMIComponent(final boolean reset, final int threadCount) {
        this(reset, new ThreadPool("RMI", threadCount));
    }
    
    public RMIComponent(final boolean reset, final ThreadPool pool) {
        super(pool);
        this.threadCount = RMIComponent.MAX_THREADS;
        this.factory = new DataFactory(new TypedDataAdaptor());
        this.objects = new HashMap<String, Object>();
        final JMSPropertySelector selector = new JMSPropertySelector("messageName", "rmi:rmiRequest", "invokeRemote");
        try {
            this.register(selector, this);
        }
        catch (Exception e) {
            RMIComponent.log.error(this, e);
        }
        this.reset = reset;
        this.threadPool = pool;
    }
    
    @Override
    public void init(final ComponentServer container) throws Exception {
        final String qn = new Destination().getLocal();
        RMIRequest.setListener(this, qn, this.reset);
    }
    
    @Override
    public void shutdown() {
        RMIComponent.log.info("RMIComponent: shutdown");
        try {
            Request.removeListener(new Destination().getLocal());
        }
        catch (JMSException e) {
            RMIComponent.log.error(this, e);
        }
    }
    
    @Override
    public void trace() {
        RMIComponent.log.info(this);
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("\nRMIComponent:\n");
        sb.append(this.threadPool);
        return sb.toString();
    }
    
    public IModelObject invokeLocal(final ExceptionListener listener, final Target target, final Method method, final boolean wait) throws Exception {
        if (!wait) {
            final InvokeMethod im = new InvokeMethod(this.threadPool);
            im.setListener(listener);
            im.invoke(this, target, method);
            return null;
        }
        final Object result = this.invoke(target, method);
        if (result == null) {
            return null;
        }
        return this.factory.createData(result).get(0).getRoot();
    }
    
    public void invokeDispatched(final Target target, final Method method) throws Exception {
        this.invoke(target, method);
    }
    
    public RMIReply invokeRemote(final RMIRequest request) {
        final User threadUser = new User(request.getUserId(), request.getSessionId());
        User.setCurrent(threadUser);
        final String transactionId = request.getTransactionId();
        Transaction transaction = null;
        if (transactionId != null) {
            transaction = new Transaction(transactionId);
        }
        else {
            transaction = new Transaction();
        }
        transaction.begin();
        final RMIReply reply = new RMIReply(request);
        final Method method = request.getMethod();
        try {
            final Object result = this.invoke(request.getTarget(), method);
            if (result != null) {
                final IData data = this.factory.createData(result).get(0);
                reply.setReturnValue(data.getRoot());
            }
        }
        catch (Exception ex) {
            RMIComponent.log.error("Exception while executing method:" + method.getRoot(), ex);
            reply.setException(ex);
            return reply;
        }
        finally {
            if (transaction != null) {
                transaction.commit();
            }
        }
        if (transaction != null) {
            transaction.commit();
        }
        return reply;
    }
    
    private Object invoke(final Target target, final Method method) throws Exception {
        Object methodTarget = this.getRegistered(target.getClassName());
        if (methodTarget == null) {
            methodTarget = target.construct(this.factory);
            if (methodTarget == null) {
                RMIComponent.log.error("Cannot construct target:" + target.getClassName());
            }
        }
        return method.invoke(this.factory, methodTarget);
    }
    
    public void register(final Object object) {
        this.objects.put(object.getClass().getName(), object);
    }
    
    Object getRegistered(final String name) {
        final Component component = ComponentServer.getInstance().getComponent(name);
        if (component != null) {
            return component;
        }
        return this.objects.get(name);
    }
    
    protected int getThreadCount() {
        return this.threadCount;
    }
}
