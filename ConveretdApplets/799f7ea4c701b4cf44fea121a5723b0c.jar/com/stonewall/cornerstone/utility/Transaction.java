// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.utility;

import java.util.Iterator;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Stack;
import org.xmodel.log.Log;
import java.util.Map;
import com.stonewall.cornerstone.jms.msg.event.Event;
import java.util.List;

public class Transaction
{
    private final String id;
    private final String identity;
    private Thread thread;
    private final List<Event> events;
    private final Map<String, Object> userData;
    private Transaction parent;
    protected static final IdentityFactory identityFactory;
    static final Log log;
    private static final ThreadLocal<Stack<Transaction>> stack;
    
    static {
        identityFactory = new IdentityFactory();
        log = Log.getLog(Transaction.class);
        stack = new ThreadLocal<Stack<Transaction>>();
    }
    
    public Transaction() {
        this(Transaction.identityFactory.next());
    }
    
    public Transaction(final String id) {
        this.identity = Transaction.identityFactory.next();
        this.thread = Thread.currentThread();
        this.events = new ArrayList<Event>();
        this.userData = new HashMap<String, Object>();
        this.parent = null;
        this.id = id;
    }
    
    public String getId() {
        return this.id;
    }
    
    public String getIdentity() {
        return this.identity;
    }
    
    public void begin() {
        Transaction.log.debug(String.valueOf(this.identity()) + " - begin");
        if (getStack().contains(this)) {
            Transaction.log.warn(String.valueOf(this.identity()) + " already started - <begin> ignored.");
            return;
        }
        this.parent = getCurrent();
        push(this);
        Transaction.log.debug(String.valueOf(this.identity()) + " - started");
    }
    
    public void commit() {
        Transaction.log.debug(String.valueOf(this.identity()) + " - commit");
        this.end();
        for (final Event e : this.events) {
            e.send();
        }
    }
    
    public void end() {
        final Transaction current = getCurrent();
        if (current == null) {
            Transaction.log.debug(String.valueOf(this.identity()) + " already ended - ignored.");
            return;
        }
        if (current != this) {
            Transaction.log.debug(String.valueOf(this.identity()) + " has nested - ignored.");
            return;
        }
        pop();
        Transaction.log.debug(String.valueOf(this.identity()) + " - end");
    }
    
    public void add(final Event event) {
        this.events.add(event);
        Transaction.log.debug(String.valueOf(this.identity()) + " add event:\n" + event);
    }
    
    public List<Event> getEvents() {
        return this.events;
    }
    
    public boolean nested() {
        return this.parent != null;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(this.identity());
        sb.append(" parent=" + this.parent.id);
        sb.append(" thread:" + this.thread);
        if (Transaction.log.isLevelEnabled(32)) {
            sb.append("\nevents:\n");
            for (final Event e : this.events) {
                sb.append(e);
                sb.append('\n');
            }
        }
        return sb.toString();
    }
    
    public Map<String, Object> userData() {
        return this.userData;
    }
    
    public static Transaction getCurrent() {
        final Stack<Transaction> stack = getStack();
        return stack.isEmpty() ? null : stack.peek();
    }
    
    public static void commitAll() {
        for (Transaction tr = getCurrent(); tr != null; tr = getCurrent()) {
            tr.commit();
        }
    }
    
    public static void clearAll() {
        for (Transaction current = getCurrent(); current != null; current = getCurrent()) {
            current.end();
        }
    }
    
    protected static Stack<Transaction> getStack() {
        Stack<Transaction> result = Transaction.stack.get();
        if (result == null) {
            result = new Stack<Transaction>();
            Transaction.stack.set(result);
        }
        return result;
    }
    
    protected String identity() {
        return "Transaction(" + this.identity.toLowerCase() + "): " + this.getId();
    }
    
    private static void push(final Transaction tx) {
        getStack().push(tx);
    }
    
    private static void pop() {
        getStack().pop();
    }
    
    private static String stackTrace() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Transaction (stack trace):\n");
        for (final Transaction tx : getStack()) {
            sb.append(tx);
            sb.append("\n\n");
        }
        return sb.toString();
    }
}
