// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.util;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.Executor;

public final class Join
{
    public static final Executor TRIVIAL_EXECUTOR;
    private final Executor executor;
    private final LinkedList[] writes;
    private final long asyncMask;
    private long mask;
    private final Reaction[][] reactionsPerChannel;
    
    private Join(final long asyncMask, final Reaction[][] reactionsPerChannel, final Executor executor) {
        this.mask = 0L;
        final LinkedList[] writes = new LinkedList[reactionsPerChannel.length];
        for (int i = 0; i < writes.length; ++i) {
            if (reactionsPerChannel[i] != null) {
                writes[i] = new LinkedList();
            }
        }
        this.asyncMask = asyncMask;
        this.reactionsPerChannel = reactionsPerChannel;
        this.writes = writes;
        this.executor = executor;
    }
    
    private void sendRaw(final int index, final Object message) {
        Reaction selectedReaction = null;
        Object[] args = null;
        synchronized (this) {
            final LinkedList writing = this.writes[index];
            if (writing == null) {
                throw new IndexOutOfBoundsException();
            }
            writing.addLast(message);
            this.mask |= 1L << index;
            final Reaction[] arr$;
            final Reaction[] reactions = arr$ = this.reactionsPerChannel[index];
            for (final Reaction reaction : arr$) {
                if ((reaction.mask & this.mask) == reaction.mask) {
                    final int[] indices = reaction.indices;
                    args = new Object[indices.length];
                    for (int i = 0; i < indices.length; ++i) {
                        final int readIndex = indices[i];
                        final LinkedList reading = this.writes[readIndex];
                        args[i] = reading.removeFirst();
                        if (reading.isEmpty()) {
                            this.mask &= ~(1L << readIndex);
                        }
                    }
                    selectedReaction = reaction;
                    break;
                }
            }
        }
        if (selectedReaction != null) {
            selectedReaction.dispatch(this, args);
        }
    }
    
    public boolean isAsync(final int channel) {
        return (1L << channel & this.asyncMask) != 0x0L;
    }
    
    public void send(final int channel, final Object message) {
        if (this.isAsync(channel)) {
            this.sendRaw(channel, message);
        }
        else {
            this.sendRaw(channel, new AsyncCall(message));
        }
    }
    
    public void send(final Enum<?> channel, final Object message) {
        this.send(channel.ordinal(), message);
    }
    
    public Object call(final int channel, final Object message) {
        if (this.isAsync(channel)) {
            this.sendRaw(channel, message);
            return null;
        }
        final SyncCall request = new SyncCall(message);
        this.sendRaw(channel, request);
        return request.call();
    }
    
    public Object call(final Enum<?> channel, final Object message) {
        return this.call(channel.ordinal(), message);
    }
    
    static {
        TRIVIAL_EXECUTOR = new Executor() {
            public void execute(final Runnable command) {
                new Thread(command).start();
            }
        };
    }
    
    public static class Spec
    {
        private ArrayList<ArrayList<Reaction>> reactionsPerChannel;
        private long asyncMask;
        private long mask;
        private volatile Reaction[][] cachedReactionsPerChannel;
        private static final Reaction[] EMPTY_REACTIONS;
        
        public Spec() {
            this.reactionsPerChannel = new ArrayList<ArrayList<Reaction>>();
            this.asyncMask = 0L;
            this.mask = 0L;
            this.cachedReactionsPerChannel = null;
        }
        
        public void addReaction(final Reaction reaction) {
            if ((this.mask & ~this.asyncMask & reaction.asyncMask) != 0x0L) {
                throw new IllegalArgumentException("Cannot use a synchronous channel in a non-head position");
            }
            if ((reaction.mask & ~reaction.asyncMask & this.asyncMask) != 0x0L) {
                throw new IllegalArgumentException("Cannot use an asynchronous channel in the head position of a synchronous reaction");
            }
            this.cachedReactionsPerChannel = null;
            final int[] indices = reaction.indices;
            for (int i = 0; i < indices.length; ++i) {
                final int index = indices[i];
                if (this.reactionsPerChannel.size() <= index) {
                    this.reactionsPerChannel.ensureCapacity(index + 1);
                    while (this.reactionsPerChannel.size() <= index) {
                        this.reactionsPerChannel.add(null);
                    }
                }
                ArrayList<Reaction> reactions = this.reactionsPerChannel.get(index);
                if (reactions == null) {
                    reactions = new ArrayList<Reaction>();
                    this.reactionsPerChannel.set(index, reactions);
                }
                reactions.add(reaction);
            }
            this.asyncMask |= reaction.asyncMask;
            this.mask |= reaction.mask;
        }
        
        public Join createJoin() {
            return this.createJoin(Join.TRIVIAL_EXECUTOR);
        }
        
        public Join createJoin(final Executor executor) {
            if (this.cachedReactionsPerChannel == null) {
                final int length = this.reactionsPerChannel.size();
                final Reaction[][] localReactionsPerChannel = new Reaction[length][];
                for (int i = 0; i < length; ++i) {
                    final ArrayList<Reaction> reactions = this.reactionsPerChannel.get(i);
                    if (reactions != null) {
                        localReactionsPerChannel[i] = reactions.toArray(Spec.EMPTY_REACTIONS);
                    }
                }
                this.cachedReactionsPerChannel = localReactionsPerChannel;
            }
            return new Join(this.asyncMask, this.cachedReactionsPerChannel, executor, null);
        }
        
        static {
            EMPTY_REACTIONS = new Reaction[0];
        }
    }
    
    public abstract static class Reaction
    {
        private final int[] indices;
        private final long mask;
        private final long asyncMask;
        
        private static int[] toIndices(final Enum<?> head, final Enum<?>[] channels) {
            final int[] indices = new int[channels.length + 1];
            indices[0] = head.ordinal();
            for (int i = 0; i < channels.length; ++i) {
                indices[i + 1] = channels[i].ordinal();
            }
            return indices;
        }
        
        Reaction(final Enum<?> head, final Enum<?>[] channels, final boolean isAsync) {
            this(toIndices(head, channels), isAsync);
        }
        
        Reaction(final int[] indices, final boolean isAsync) {
            long mask = 0L;
            for (int i = 0; i < indices.length; ++i) {
                final int index = indices[i];
                if (index < 0 || index > 63) {
                    throw new IndexOutOfBoundsException();
                }
                if ((mask & 1L << index) != 0x0L) {
                    throw new IllegalArgumentException("Duplicate channels in reaction");
                }
                mask |= 1L << index;
            }
            this.indices = indices;
            this.mask = mask;
            if (isAsync) {
                this.asyncMask = mask;
            }
            else {
                this.asyncMask = (mask & ~(1L << indices[0]));
            }
        }
        
        abstract void dispatch(final Join p0, final Object[] p1);
    }
    
    public abstract static class FastReaction extends Reaction
    {
        public FastReaction(final int[] indices) {
            super(indices.clone(), true);
        }
        
        public FastReaction(final Enum<?> head, final Enum<?>... channels) {
            super(head, channels, true);
        }
        
        void dispatch(final Join join, final Object[] args) {
            try {
                this.react(join, args);
            }
            catch (Exception ex) {}
        }
        
        public abstract void react(final Join p0, final Object[] p1);
    }
    
    public abstract static class AsyncReaction extends Reaction
    {
        public AsyncReaction(final int[] indices) {
            super(indices.clone(), true);
        }
        
        public AsyncReaction(final Enum<?> head, final Enum<?>... channels) {
            super(head, channels, true);
        }
        
        void dispatch(final Join join, final Object[] args) {
            join.executor.execute(new Runnable() {
                public void run() {
                    AsyncReaction.this.react(join, args);
                }
            });
        }
        
        public abstract void react(final Join p0, final Object[] p1);
    }
    
    public abstract static class SyncReaction extends Reaction
    {
        public SyncReaction(final int[] indices) {
            super(indices.clone(), false);
        }
        
        public SyncReaction(final Enum<?> head, final Enum<?>... channels) {
            super(head, channels, false);
        }
        
        void dispatch(final Join join, final Object[] args) {
            final Call call = (Call)args[0];
            args[0] = call.getMessage();
            call.activate(join, this, args);
        }
        
        public abstract Object react(final Join p0, final Object[] p1);
    }
    
    private abstract static class Call
    {
        private final Object message;
        
        public Call(final Object message) {
            this.message = message;
        }
        
        public Object getMessage() {
            return this.message;
        }
        
        public abstract void activate(final Join p0, final SyncReaction p1, final Object[] p2);
    }
    
    private static class AsyncCall extends Call
    {
        public AsyncCall(final Object message) {
            super(message);
        }
        
        public void activate(final Join join, final SyncReaction reaction, final Object[] args) {
            join.executor.execute(new Runnable() {
                public void run() {
                    reaction.react(join, args);
                }
            });
        }
    }
    
    private static class SyncCall extends Call
    {
        private Join join;
        private SyncReaction reaction;
        private Object[] args;
        
        public SyncCall(final Object message) {
            super(message);
            this.join = null;
            this.reaction = null;
            this.args = null;
        }
        
        public synchronized void activate(final Join join, final SyncReaction reaction, final Object[] args) {
            this.join = join;
            this.reaction = reaction;
            this.args = args;
            this.notifyAll();
        }
        
        public synchronized Object call() {
            boolean interrupted = false;
            try {
                while (this.reaction == null) {
                    try {
                        this.wait();
                    }
                    catch (InterruptedException e) {
                        interrupted = true;
                    }
                }
            }
            finally {
                if (interrupted) {
                    Thread.currentThread().interrupt();
                }
            }
            return this.reaction.react(this.join, this.args);
        }
    }
}
