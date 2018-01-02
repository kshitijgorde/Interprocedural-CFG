// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.mina.core.filterchain;

import org.slf4j.LoggerFactory;
import com.masystem.beergame.debug.Log;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.write.WriteRequest;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IdleStatus;
import java.util.Iterator;
import java.util.Collection;
import java.util.ArrayList;
import org.apache.mina.core.session.IoSession;
import java.util.concurrent.ConcurrentHashMap;
import org.slf4j.Logger;
import java.util.Map;
import org.apache.mina.core.session.AbstractIoSession;
import org.apache.mina.core.session.AttributeKey;

public class DefaultIoFilterChain implements IoFilterChain
{
    public static final AttributeKey SESSION_CREATED_FUTURE;
    private final AbstractIoSession session;
    private final Map<String, Entry> name2entry;
    private final EntryImpl head;
    private final EntryImpl tail;
    private static final Logger LOGGER;
    
    public DefaultIoFilterChain(final AbstractIoSession session) {
        this.name2entry = new ConcurrentHashMap<String, Entry>();
        if (session == null) {
            throw new IllegalArgumentException("session");
        }
        this.session = session;
        this.head = new EntryImpl((EntryImpl)null, (EntryImpl)null, "head", (IoFilter)new HeadFilter());
        this.tail = new EntryImpl(this.head, (EntryImpl)null, "tail", (IoFilter)new TailFilter());
        this.head.nextEntry = this.tail;
    }
    
    @Override
    public final IoSession getSession() {
        return this.session;
    }
    
    @Override
    public final synchronized void addLast(String s, IoFilter ioFilter) {
        final String s2 = s;
        if (this.name2entry.containsKey(s2)) {
            throw new IllegalArgumentException("Other filter is using the same name '" + s2 + "'");
        }
        final EntryImpl access$400 = this.tail.prevEntry;
        final String s3 = s;
        ioFilter = ioFilter;
        s = s3;
        final EntryImpl entryImpl = access$400;
        final EntryImpl entryImpl2 = new EntryImpl(entryImpl, entryImpl.nextEntry, s, ioFilter);
        try {
            final IoFilter ioFilter2 = ioFilter;
            entryImpl2.getNextFilter();
            ioFilter2.onPreAdd$64777341(this);
        }
        catch (Exception ex) {
            throw new IoFilterLifeCycleException("onPreAdd(): " + s + ':' + ioFilter + " in " + this.session, ex);
        }
        entryImpl.nextEntry.prevEntry = entryImpl2;
        entryImpl.nextEntry = entryImpl2;
        this.name2entry.put(s, entryImpl2);
        try {
            entryImpl2.getNextFilter();
        }
        catch (Exception ex2) {
            this.deregister0(entryImpl2);
            throw new IoFilterLifeCycleException("onPostAdd(): " + s + ':' + ioFilter + " in " + this.session, ex2);
        }
    }
    
    @Override
    public final synchronized void clear() throws Exception {
        for (final Entry entry : new ArrayList<Object>(this.name2entry.values())) {
            try {
                final EntryImpl entryImpl = (EntryImpl)entry;
                final IoFilter filter = entryImpl.getFilter();
                try {
                    entryImpl.getName();
                    entryImpl.getNextFilter();
                }
                catch (Exception ex) {
                    throw new IoFilterLifeCycleException("onPreRemove(): " + entryImpl.getName() + ':' + filter + " in " + this.session, ex);
                }
                this.deregister0(entryImpl);
                try {
                    final IoFilter ioFilter = filter;
                    entryImpl.getName();
                    entryImpl.getNextFilter();
                    ioFilter.onPostRemove$64777341(this);
                }
                catch (Exception ex2) {
                    throw new IoFilterLifeCycleException("onPostRemove(): " + entryImpl.getName() + ':' + filter + " in " + this.session, ex2);
                }
            }
            catch (Exception ex3) {
                throw new IoFilterLifeCycleException("clear(): " + entry.getName() + " in " + this.session, ex3);
            }
        }
    }
    
    private void deregister0(final EntryImpl entryImpl) {
        final EntryImpl access$400 = entryImpl.prevEntry;
        final EntryImpl access$401 = entryImpl.nextEntry;
        access$400.nextEntry = access$401;
        access$401.prevEntry = access$400;
        this.name2entry.remove(entryImpl.name);
    }
    
    @Override
    public final void fireSessionCreated() {
        this.callNextSessionCreated(this.head, this.session);
    }
    
    private void callNextSessionCreated(final Entry entry, final IoSession ioSession) {
        try {
            entry.getFilter().sessionCreated(entry.getNextFilter(), ioSession);
        }
        catch (Throwable t) {
            this.fireExceptionCaught(t);
        }
    }
    
    @Override
    public final void fireSessionOpened() {
        this.callNextSessionOpened(this.head, this.session);
    }
    
    private void callNextSessionOpened(final Entry entry, final IoSession ioSession) {
        try {
            entry.getFilter().sessionOpened(entry.getNextFilter(), ioSession);
        }
        catch (Throwable t) {
            this.fireExceptionCaught(t);
        }
    }
    
    @Override
    public final void fireSessionClosed() {
        try {
            this.session.getCloseFuture().setClosed();
        }
        catch (Throwable t) {
            this.fireExceptionCaught(t);
        }
        this.callNextSessionClosed(this.head, this.session);
    }
    
    private void callNextSessionClosed(final Entry entry, final IoSession ioSession) {
        try {
            entry.getFilter().sessionClosed(entry.getNextFilter(), ioSession);
        }
        catch (Throwable t) {
            this.fireExceptionCaught(t);
        }
    }
    
    @Override
    public final void fireSessionIdle(final IdleStatus idleStatus) {
        this.session.increaseIdleCount(idleStatus, System.currentTimeMillis());
        this.callNextSessionIdle(this.head, this.session, idleStatus);
    }
    
    private void callNextSessionIdle(final Entry entry, final IoSession ioSession, final IdleStatus idleStatus) {
        try {
            entry.getFilter().sessionIdle(entry.getNextFilter(), ioSession, idleStatus);
        }
        catch (Throwable t) {
            this.fireExceptionCaught(t);
        }
    }
    
    @Override
    public final void fireMessageReceived(final Object o) {
        if (o instanceof IoBuffer) {
            this.session.increaseReadBytes(((IoBuffer)o).remaining(), System.currentTimeMillis());
        }
        this.callNextMessageReceived(this.head, this.session, o);
    }
    
    private void callNextMessageReceived(final Entry entry, final IoSession ioSession, final Object o) {
        try {
            entry.getFilter().messageReceived(entry.getNextFilter(), ioSession, o);
        }
        catch (Throwable t) {
            this.fireExceptionCaught(t);
        }
    }
    
    @Override
    public final void fireMessageSent(final WriteRequest writeRequest) {
        this.session.increaseWrittenMessages(writeRequest, System.currentTimeMillis());
        try {
            writeRequest.getFuture().setWritten();
        }
        catch (Throwable t) {
            this.fireExceptionCaught(t);
        }
        final EntryImpl head = this.head;
        if (!writeRequest.isEncoded()) {
            this.callNextMessageSent(head, this.session, writeRequest);
        }
    }
    
    private void callNextMessageSent(final Entry entry, final IoSession ioSession, final WriteRequest writeRequest) {
        try {
            entry.getFilter().messageSent(entry.getNextFilter(), ioSession, writeRequest);
        }
        catch (Throwable t) {
            this.fireExceptionCaught(t);
        }
    }
    
    @Override
    public final void fireExceptionCaught(final Throwable t) {
        callNextExceptionCaught(this.head, this.session, t);
    }
    
    private static void callNextExceptionCaught(final Entry entry, final IoSession ioSession, final Throwable exception) {
        final ConnectFuture connectFuture;
        if ((connectFuture = (ConnectFuture)ioSession.removeAttribute(DefaultIoFilterChain.SESSION_CREATED_FUTURE)) == null) {
            try {
                entry.getFilter().exceptionCaught(entry.getNextFilter(), ioSession, exception);
                return;
            }
            catch (Throwable t) {
                final Logger logger = DefaultIoFilterChain.LOGGER;
                Log.warn("Unexpected exception from exceptionCaught handler.", t);
                return;
            }
        }
        ioSession.close(true);
        connectFuture.setException(exception);
    }
    
    @Override
    public final void fireFilterWrite(final WriteRequest writeRequest) {
        this.callPreviousFilterWrite(this.tail, this.session, writeRequest);
    }
    
    private void callPreviousFilterWrite(final Entry entry, final IoSession ioSession, final WriteRequest writeRequest) {
        try {
            entry.getFilter().filterWrite(entry.getNextFilter(), ioSession, writeRequest);
        }
        catch (Throwable exception) {
            writeRequest.getFuture().setException(exception);
            this.fireExceptionCaught(exception);
        }
    }
    
    @Override
    public final void fireFilterClose() {
        this.callPreviousFilterClose(this.tail, this.session);
    }
    
    private void callPreviousFilterClose(final Entry entry, final IoSession ioSession) {
        try {
            entry.getFilter().filterClose(entry.getNextFilter(), ioSession);
        }
        catch (Throwable t) {
            this.fireExceptionCaught(t);
        }
    }
    
    @Override
    public final boolean contains(final IoFilter ioFilter) {
        for (EntryImpl entryImpl = this.head.nextEntry; entryImpl != this.tail; entryImpl = entryImpl.nextEntry) {
            if (entryImpl.getFilter() == ioFilter) {
                final EntryImpl entryImpl2 = entryImpl;
                return entryImpl2 != null;
            }
        }
        final EntryImpl entryImpl2 = null;
        return entryImpl2 != null;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb;
        (sb = new StringBuilder()).append("{ ");
        int n = 1;
        for (EntryImpl entryImpl = this.head.nextEntry; entryImpl != this.tail; entryImpl = entryImpl.nextEntry) {
            if (n == 0) {
                sb.append(", ");
            }
            else {
                n = 0;
            }
            sb.append('(');
            sb.append(entryImpl.getName());
            sb.append(':');
            sb.append(entryImpl.getFilter());
            sb.append(')');
        }
        if (n != 0) {
            sb.append("empty");
        }
        sb.append(" }");
        return sb.toString();
    }
    
    static /* synthetic */ void access$1100(final DefaultIoFilterChain defaultIoFilterChain, final Entry entry, final IoSession ioSession, final Throwable t) {
        callNextExceptionCaught(entry, ioSession, t);
    }
    
    static {
        SESSION_CREATED_FUTURE = new AttributeKey(DefaultIoFilterChain.class, "connectFuture");
        LOGGER = LoggerFactory.getLogger$4ecaad6a();
    }
    
    final class EntryImpl implements Entry
    {
        private EntryImpl prevEntry;
        private EntryImpl nextEntry;
        private final String name;
        private IoFilter filter;
        private final IoFilter.NextFilter nextFilter;
        
        private EntryImpl(final EntryImpl prevEntry, final EntryImpl nextEntry, final String name, final IoFilter filter, final byte b) {
            if (filter == null) {
                throw new IllegalArgumentException("filter");
            }
            if (name == null) {
                throw new IllegalArgumentException("name");
            }
            this.prevEntry = prevEntry;
            this.nextEntry = nextEntry;
            this.name = name;
            this.filter = filter;
            this.nextFilter = new IoFilter.NextFilter(DefaultIoFilterChain.this) {
                @Override
                public final void sessionCreated(final IoSession ioSession) {
                    DefaultIoFilterChain.this.callNextSessionCreated(EntryImpl.this.nextEntry, ioSession);
                }
                
                @Override
                public final void sessionOpened(final IoSession ioSession) {
                    DefaultIoFilterChain.this.callNextSessionOpened(EntryImpl.this.nextEntry, ioSession);
                }
                
                @Override
                public final void sessionClosed(final IoSession ioSession) {
                    DefaultIoFilterChain.this.callNextSessionClosed(EntryImpl.this.nextEntry, ioSession);
                }
                
                @Override
                public final void sessionIdle(final IoSession ioSession, final IdleStatus idleStatus) {
                    DefaultIoFilterChain.this.callNextSessionIdle(EntryImpl.this.nextEntry, ioSession, idleStatus);
                }
                
                @Override
                public final void exceptionCaught(final IoSession ioSession, final Throwable t) {
                    DefaultIoFilterChain.access$1100(DefaultIoFilterChain.this, EntryImpl.this.nextEntry, ioSession, t);
                }
                
                @Override
                public final void messageReceived(final IoSession ioSession, final Object o) {
                    DefaultIoFilterChain.this.callNextMessageReceived(EntryImpl.this.nextEntry, ioSession, o);
                }
                
                @Override
                public final void messageSent(final IoSession ioSession, final WriteRequest writeRequest) {
                    DefaultIoFilterChain.this.callNextMessageSent(EntryImpl.this.nextEntry, ioSession, writeRequest);
                }
                
                @Override
                public final void filterWrite(final IoSession ioSession, final WriteRequest writeRequest) {
                    DefaultIoFilterChain.this.callPreviousFilterWrite(EntryImpl.this.prevEntry, ioSession, writeRequest);
                }
                
                @Override
                public final void filterClose(final IoSession ioSession) {
                    DefaultIoFilterChain.this.callPreviousFilterClose(EntryImpl.this.prevEntry, ioSession);
                }
                
                @Override
                public final String toString() {
                    return EntryImpl.this.nextEntry.name;
                }
            };
        }
        
        @Override
        public final String getName() {
            return this.name;
        }
        
        @Override
        public final IoFilter getFilter() {
            return this.filter;
        }
        
        @Override
        public final IoFilter.NextFilter getNextFilter() {
            return this.nextFilter;
        }
        
        @Override
        public final String toString() {
            final StringBuilder sb;
            (sb = new StringBuilder()).append("('").append(this.name).append('\'');
            sb.append(", prev: '");
            if (this.prevEntry != null) {
                sb.append(this.prevEntry.name);
                sb.append(':');
                sb.append(this.prevEntry.filter.getClass().getSimpleName());
            }
            else {
                sb.append("null");
            }
            sb.append("', next: '");
            if (this.nextEntry != null) {
                sb.append(this.nextEntry.name);
                sb.append(':');
                sb.append(this.nextEntry.filter.getClass().getSimpleName());
            }
            else {
                sb.append("null");
            }
            sb.append("')");
            return sb.toString();
        }
    }
    
    static final class TailFilter extends IoFilterAdapter
    {
        private TailFilter(final byte b) {
        }
        
        @Override
        public final void sessionCreated(final IoFilter.NextFilter nextFilter, final IoSession session) throws Exception {
            try {
                session.getHandler();
            }
            finally {
                final ConnectFuture connectFuture;
                if ((connectFuture = (ConnectFuture)session.removeAttribute(DefaultIoFilterChain.SESSION_CREATED_FUTURE)) != null) {
                    connectFuture.setSession(session);
                }
            }
        }
        
        @Override
        public final void sessionOpened(final IoFilter.NextFilter nextFilter, final IoSession ioSession) throws Exception {
            ioSession.getHandler();
        }
        
        @Override
        public final void sessionClosed(IoFilter.NextFilter nextFilter, final IoSession ioSession) throws Exception {
            nextFilter = (IoFilter.NextFilter)ioSession;
            try {
                ((AbstractIoSession)nextFilter).getHandler().sessionClosed(ioSession);
            }
            finally {
                try {
                    ((AbstractIoSession)nextFilter).getWriteRequestQueue().dispose(ioSession);
                }
                finally {
                    try {
                        ((AbstractIoSession)nextFilter).getAttributeMap();
                    }
                    finally {
                        try {
                            ioSession.getFilterChain().clear();
                        }
                        finally {
                            if (((AbstractIoSession)nextFilter).getConfig().isUseReadOperation()) {
                                ((AbstractIoSession)nextFilter).offerClosedReadFuture();
                            }
                        }
                    }
                }
            }
        }
        
        @Override
        public final void sessionIdle(final IoFilter.NextFilter nextFilter, final IoSession ioSession, final IdleStatus idleStatus) throws Exception {
            ioSession.getHandler();
        }
        
        @Override
        public final void exceptionCaught(IoFilter.NextFilter nextFilter, final IoSession ioSession, final Throwable t) throws Exception {
            nextFilter = (IoFilter.NextFilter)ioSession;
            try {
                ((AbstractIoSession)nextFilter).getHandler().exceptionCaught((IoSession)nextFilter, t);
            }
            finally {
                if (((AbstractIoSession)nextFilter).getConfig().isUseReadOperation()) {
                    ((AbstractIoSession)nextFilter).offerFailedReadFuture(t);
                }
            }
        }
        
        @Override
        public final void messageReceived(IoFilter.NextFilter nextFilter, final IoSession ioSession, final Object o) throws Exception {
            nextFilter = (IoFilter.NextFilter)ioSession;
            if (!(o instanceof IoBuffer)) {
                ((AbstractIoSession)nextFilter).increaseReadMessages(System.currentTimeMillis());
            }
            else if (!((IoBuffer)o).hasRemaining()) {
                ((AbstractIoSession)nextFilter).increaseReadMessages(System.currentTimeMillis());
            }
            try {
                ioSession.getHandler().messageReceived((IoSession)nextFilter, o);
            }
            finally {
                if (((AbstractIoSession)nextFilter).getConfig().isUseReadOperation()) {
                    ((AbstractIoSession)nextFilter).offerReadFuture(o);
                }
            }
        }
        
        @Override
        public final void messageSent(final IoFilter.NextFilter nextFilter, final IoSession ioSession, final WriteRequest writeRequest) throws Exception {
            ioSession.getHandler();
            writeRequest.getMessage();
        }
        
        @Override
        public final void filterWrite(final IoFilter.NextFilter nextFilter, final IoSession ioSession, final WriteRequest writeRequest) throws Exception {
            nextFilter.filterWrite(ioSession, writeRequest);
        }
        
        @Override
        public final void filterClose(final IoFilter.NextFilter nextFilter, final IoSession ioSession) throws Exception {
            nextFilter.filterClose(ioSession);
        }
    }
    
    final class HeadFilter extends IoFilterAdapter
    {
        private HeadFilter(final DefaultIoFilterChain defaultIoFilterChain, final byte b) {
        }
        
        @Override
        public final void filterWrite(final IoFilter.NextFilter nextFilter, final IoSession ioSession, final WriteRequest writeRequest) throws Exception {
            final AbstractIoSession abstractIoSession = (AbstractIoSession)ioSession;
            if (writeRequest.getMessage() instanceof IoBuffer) {
                final IoBuffer ioBuffer;
                (ioBuffer = (IoBuffer)writeRequest.getMessage()).mark();
                final int remaining;
                if ((remaining = ioBuffer.remaining()) == 0) {
                    abstractIoSession.increaseScheduledWriteMessages();
                }
                else {
                    abstractIoSession.increaseScheduledWriteBytes(remaining);
                }
            }
            else {
                abstractIoSession.increaseScheduledWriteMessages();
            }
            abstractIoSession.getWriteRequestQueue().offer(abstractIoSession, writeRequest);
            if (!abstractIoSession.isWriteSuspended()) {
                abstractIoSession.getProcessor().flush(abstractIoSession);
            }
        }
        
        @Override
        public final void filterClose(final IoFilter.NextFilter nextFilter, final IoSession ioSession) throws Exception {
            ((AbstractIoSession)ioSession).getProcessor().remove(ioSession);
        }
    }
}
