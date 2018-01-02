// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.jms;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.Iterator;
import java.util.Collections;
import javax.jms.Topic;
import javax.jms.ConnectionConsumer;
import javax.jms.ServerSessionPool;
import javax.jms.Destination;
import java.util.Collection;
import javax.jms.ConnectionMetaData;
import javax.jms.Session;
import javax.jms.JMSException;
import java.util.ArrayList;
import org.xmodel.log.Log;
import java.util.concurrent.locks.ReentrantLock;
import java.util.List;
import javax.jms.ExceptionListener;
import javax.jms.Connection;

public class JmsConnection implements Connection, ExceptionListener
{
    private final JmsClient jms;
    private Connection connection;
    private List<JmsSession> sessionList;
    private final List<ExceptionListener> listenerList;
    private final Reinit reinitThread;
    private final ReentrantLock lock;
    static final Log log;
    
    static {
        log = Log.getLog(JmsConnection.class);
    }
    
    JmsConnection(final JmsClient jms, final Connection connection) throws JMSException {
        this.sessionList = new ArrayList<JmsSession>();
        this.listenerList = new ArrayList<ExceptionListener>();
        this.reinitThread = new Reinit();
        this.lock = new ReentrantLock();
        this.jms = jms;
        (this.connection = connection).setExceptionListener(this);
        connection.start();
    }
    
    @Override
    public void onException(final JMSException e) {
        this.jms.closed(this);
        JmsConnection.log.error(this, e);
        this.notifyListeners(e);
        this.reinitThread.queue.add(e);
    }
    
    @Override
    public Session createSession(final boolean arg0, final int arg1) throws JMSException {
        this.lock.lock();
        try {
            final Session session = this.connection.createSession(arg0, arg1);
            final JmsSession result = new JmsSession(this, session);
            this.sessionList.add(result);
            return result;
        }
        finally {
            this.lock.unlock();
        }
    }
    
    @Override
    public String getClientID() throws JMSException {
        this.lock.lock();
        try {
            return this.connection.getClientID();
        }
        finally {
            this.lock.unlock();
        }
    }
    
    @Override
    public void setClientID(final String arg0) throws JMSException {
        this.lock.lock();
        try {
            this.connection.setClientID(arg0);
        }
        finally {
            this.lock.unlock();
        }
        this.lock.unlock();
    }
    
    @Override
    public ConnectionMetaData getMetaData() throws JMSException {
        this.lock.lock();
        try {
            return this.connection.getMetaData();
        }
        finally {
            this.lock.unlock();
        }
    }
    
    @Override
    public ExceptionListener getExceptionListener() throws JMSException {
        this.lock.lock();
        try {
            return (this.listenerList.size() > 0) ? this.listenerList.get(0) : null;
        }
        finally {
            this.lock.unlock();
        }
    }
    
    public List<ExceptionListener> getExceptionListeners() throws JMSException {
        final List<ExceptionListener> result = new ArrayList<ExceptionListener>();
        this.lock.lock();
        try {
            result.addAll(this.listenerList);
            return result;
        }
        finally {
            this.lock.unlock();
        }
    }
    
    @Override
    public void setExceptionListener(final ExceptionListener arg0) throws JMSException {
        this.listenerList.clear();
        this.listenerList.add(arg0);
    }
    
    public void addExceptionListener(final ExceptionListener arg0) throws JMSException {
        this.listenerList.add(arg0);
    }
    
    @Override
    public void start() throws JMSException {
        this.lock.lock();
        try {
            this.connection.start();
        }
        finally {
            this.lock.unlock();
        }
        this.lock.unlock();
    }
    
    @Override
    public void stop() throws JMSException {
        this.lock.lock();
        try {
            this.connection.stop();
        }
        finally {
            this.lock.unlock();
        }
        this.lock.unlock();
    }
    
    @Override
    public void close() throws JMSException {
        this.lock.lock();
        try {
            this.reinitThread.halt();
            this.connection.close();
        }
        finally {
            this.lock.unlock();
        }
        this.lock.unlock();
        this.closeSessions();
        this.jms.closed(this);
    }
    
    @Override
    public ConnectionConsumer createConnectionConsumer(final Destination arg0, final String arg1, final ServerSessionPool arg2, final int arg3) throws JMSException {
        this.lock.lock();
        try {
            return this.connection.createConnectionConsumer(arg0, arg1, arg2, arg3);
        }
        finally {
            this.lock.unlock();
        }
    }
    
    @Override
    public ConnectionConsumer createDurableConnectionConsumer(final Topic arg0, final String arg1, final String arg2, final ServerSessionPool arg3, final int arg4) throws JMSException {
        this.lock.lock();
        try {
            return this.connection.createDurableConnectionConsumer(arg0, arg1, arg2, arg3, arg4);
        }
        finally {
            this.lock.unlock();
        }
    }
    
    void closed(final JmsSession session) {
        this.lock.lock();
        try {
            this.sessionList.remove(session);
        }
        finally {
            this.lock.unlock();
        }
        this.lock.unlock();
    }
    
    void sleep(final int ms) {
        try {
            Thread.sleep(ms);
        }
        catch (Exception ex) {}
    }
    
    Connection connection() {
        return this.connection;
    }
    
    private void closeSessions() throws JMSException {
        final List<JmsSession> list = this.sessionList;
        this.lock.lock();
        try {
            this.sessionList = Collections.emptyList();
        }
        finally {
            this.lock.unlock();
        }
        this.lock.unlock();
        for (final JmsSession session : list) {
            session.close();
        }
    }
    
    private void reinit() throws JMSException {
        JmsConnection.log.info(this + ": Reinit()");
        int i = 0;
        while (true) {
            this.lock.lock();
            try {
                this.close(this.connection);
                (this.connection = this.jms.createRawConnection()).setExceptionListener(this);
                this.connection.start();
                for (final JmsSession session : this.sessionList) {
                    session.reinit();
                }
                break;
            }
            catch (Exception e) {
                JmsConnection.log.error(this, e);
                this.sleep(3000);
            }
            finally {
                this.lock.unlock();
            }
            ++i;
        }
    }
    
    private void notifyListeners(final JMSException e) {
        this.lock.lock();
        try {
            for (final ExceptionListener lnr : this.listenerList) {
                this.notifyListener(lnr, e);
            }
        }
        finally {
            this.lock.unlock();
        }
        this.lock.unlock();
    }
    
    private void notifyListener(final ExceptionListener lnr, final JMSException e) {
        try {
            lnr.onException(e);
        }
        catch (Exception ne) {
            JmsConnection.log.error(this, ne);
        }
    }
    
    private void close(final Connection c) {
        try {
            c.close();
        }
        catch (Exception e) {
            JmsConnection.log.warn(e);
        }
    }
    
    class Reinit extends Thread
    {
        boolean run;
        private LinkedBlockingQueue<JMSException> queue;
        
        Reinit() {
            super("jms reinit");
            this.queue = new LinkedBlockingQueue<JMSException>();
            this.start();
        }
        
        @Override
        public void run() {
            this.run = true;
            while (this.run) {
                try {
                    final JMSException qE = this.queue.take();
                    if (qE == null) {
                        continue;
                    }
                    JmsConnection.this.reinit();
                }
                catch (InterruptedException ex) {}
                catch (Exception e) {
                    JmsConnection.log.error(this, e);
                }
            }
        }
        
        void halt() {
            this.run = false;
            this.interrupt();
        }
    }
}
