// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.util.io;

import java.nio.channels.Channel;
import java.nio.channels.spi.SelectorProvider;
import java.util.Iterator;
import java.nio.channels.CancelledKeyException;
import java.nio.channels.SelectionKey;
import org.jruby.RubyFixnum;
import org.jruby.RubyFloat;
import java.nio.channels.SelectableChannel;
import java.io.IOException;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import java.nio.channels.Selector;
import org.jruby.RubyIO;
import org.jruby.RubyArray;
import org.jruby.Ruby;

public class SelectBlob
{
    Ruby runtime;
    RubyArray readArray;
    int readSize;
    RubyIO[] readIOs;
    boolean[] unselectableReads;
    boolean[] pendingReads;
    Boolean[] readBlocking;
    int selectedReads;
    RubyArray writeArray;
    int writeSize;
    RubyIO[] writeIOs;
    boolean[] unselectableWrites;
    Boolean[] writeBlocking;
    int selectedWrites;
    Selector selector;
    RubyArray readResults;
    RubyArray writeResults;
    RubyArray errorResults;
    
    public SelectBlob() {
        this.readArray = null;
        this.readSize = 0;
        this.readIOs = null;
        this.unselectableReads = null;
        this.pendingReads = null;
        this.readBlocking = null;
        this.selectedReads = 0;
        this.writeArray = null;
        this.writeSize = 0;
        this.writeIOs = null;
        this.unselectableWrites = null;
        this.writeBlocking = null;
        this.selectedWrites = 0;
        this.selector = null;
        this.readResults = null;
        this.writeResults = null;
        this.errorResults = null;
    }
    
    public IRubyObject goForIt(final ThreadContext context, final Ruby runtime, final IRubyObject[] args) {
        this.runtime = runtime;
        try {
            this.processReads(runtime, args, context);
            this.processWrites(runtime, args, context);
            if (args.length > 2 && !args[2].isNil()) {
                checkArrayType(runtime, args[2]);
            }
            final boolean has_timeout = args.length > 3 && !args[3].isNil();
            final long timeout = has_timeout ? getTimeoutFromArg(args[3], runtime) : 0L;
            this.doSelect(has_timeout, timeout);
            this.processSelectedKeys(runtime);
            this.processPendingAndUnselectable();
            this.tidyUp();
            if (this.readResults == null && this.writeResults == null && this.errorResults == null) {
                return runtime.getNil();
            }
            return this.constructResults(runtime);
        }
        catch (BadDescriptorException e2) {
            throw runtime.newErrnoEBADFError();
        }
        catch (IOException e) {
            throw runtime.newIOErrorFromException(e);
        }
        finally {
            if (this.selector != null) {
                try {
                    this.selector.close();
                }
                catch (Exception ex) {}
            }
        }
    }
    
    private void processReads(final Ruby runtime, final IRubyObject[] args, final ThreadContext context) throws BadDescriptorException, IOException {
        if (!args[0].isNil()) {
            checkArrayType(runtime, args[0]);
            this.readArray = (RubyArray)args[0];
            this.readSize = this.readArray.size();
            if (this.readSize == 0) {
                this.readArray = null;
            }
            else {
                this.readIOs = new RubyIO[this.readSize];
                for (int i = 0; i < this.readSize; ++i) {
                    final RubyIO ioObj = this.saveReadIO(i, context);
                    this.saveReadBlocking(ioObj, i);
                    this.saveBufferedRead(ioObj, i);
                    this.trySelectRead(context, i, ioObj);
                }
            }
        }
    }
    
    private RubyIO saveReadIO(final int i, final ThreadContext context) {
        final IRubyObject obj = this.readArray.eltOk(i);
        final RubyIO ioObj = RubyIO.convertToIO(context, obj);
        return this.readIOs[i] = ioObj;
    }
    
    private void saveReadBlocking(final RubyIO ioObj, final int i) {
        if (ioObj.getChannel() instanceof SelectableChannel) {
            this.getReadBlocking()[i] = ((SelectableChannel)ioObj.getChannel()).isBlocking();
        }
    }
    
    private void saveBufferedRead(final RubyIO ioObj, final int i) throws BadDescriptorException {
        if (ioObj.getOpenFile().getMainStreamSafe().readDataBuffered()) {
            this.getUnselectableReads()[i] = true;
        }
    }
    
    private void trySelectRead(final ThreadContext context, final int i, final RubyIO ioObj) throws IOException {
        if (ioObj.getChannel() instanceof SelectableChannel && registerSelect(context, this.getSelector(context), i, ioObj, 17)) {
            ++this.selectedReads;
            if (ioObj.writeDataBuffered()) {
                this.getPendingReads()[i] = true;
            }
        }
        else if ((ioObj.getOpenFile().getMode() & 0x1) != 0x0) {
            this.getUnselectableReads()[i] = true;
        }
    }
    
    private void processWrites(final Ruby runtime, final IRubyObject[] args, final ThreadContext context) throws IOException {
        if (args.length > 1 && !args[1].isNil()) {
            checkArrayType(runtime, args[1]);
            this.writeArray = (RubyArray)args[1];
            this.writeSize = this.writeArray.size();
            if (this.writeArray.size() == 0) {
                this.writeArray = null;
            }
            else {
                this.writeIOs = new RubyIO[this.writeSize];
                for (int i = 0; i < this.writeSize; ++i) {
                    final RubyIO ioObj = this.saveWriteIO(i, context);
                    this.saveWriteBlocking(ioObj, i);
                    this.trySelectWrite(context, i, ioObj);
                }
            }
        }
    }
    
    private RubyIO saveWriteIO(final int i, final ThreadContext context) {
        final IRubyObject obj = this.writeArray.eltOk(i);
        final RubyIO ioObj = RubyIO.convertToIO(context, obj);
        return this.writeIOs[i] = ioObj;
    }
    
    private void saveWriteBlocking(final RubyIO ioObj, final int i) {
        if (ioObj.getChannel() instanceof SelectableChannel) {
            if (this.readBlocking != null) {
                final int readIndex = this.fastSearch(this.readIOs, ioObj);
                if (readIndex == -1) {
                    this.getWriteBlocking()[i] = ((SelectableChannel)ioObj.getChannel()).isBlocking();
                }
            }
            else {
                this.getWriteBlocking()[i] = ((SelectableChannel)ioObj.getChannel()).isBlocking();
            }
        }
    }
    
    private void trySelectWrite(final ThreadContext context, final int i, final RubyIO ioObj) throws IOException {
        if (!registerSelect(context, this.getSelector(context), i, ioObj, 4)) {
            ++this.selectedReads;
            if ((ioObj.getOpenFile().getMode() & 0x2) != 0x0) {
                this.getUnselectableWrites()[i] = true;
            }
        }
    }
    
    private static long getTimeoutFromArg(final IRubyObject timeArg, final Ruby runtime) {
        long timeout = 0L;
        if (timeArg instanceof RubyFloat) {
            timeout = Math.round(((RubyFloat)timeArg).getDoubleValue() * 1000.0);
        }
        else {
            if (!(timeArg instanceof RubyFixnum)) {
                throw runtime.newTypeError("can't convert " + timeArg.getMetaClass().getName() + " into time interval");
            }
            timeout = Math.round(((RubyFixnum)timeArg).getDoubleValue() * 1000.0);
        }
        if (timeout < 0L) {
            throw runtime.newArgumentError("negative timeout given");
        }
        return timeout;
    }
    
    private void doSelect(final boolean has_timeout, final long timeout) throws IOException {
        if (this.selector != null) {
            if (this.pendingReads == null && this.unselectableReads == null && this.unselectableWrites == null) {
                if (has_timeout) {
                    if (timeout == 0L) {
                        this.selector.selectNow();
                    }
                    else {
                        this.selector.select(timeout);
                    }
                }
                else {
                    this.selector.select();
                }
            }
            else {
                this.selector.selectNow();
            }
        }
    }
    
    private void processSelectedKeys(final Ruby runtime) {
        if (this.selector != null) {
            for (final SelectionKey key : this.selector.selectedKeys()) {
                final int ioIndex = (int)key.attachment();
                try {
                    final int interestAndReady = key.interestOps() & key.readyOps();
                    if (this.readArray != null && (interestAndReady & 0x19) != 0x0) {
                        this.getReadResults().append(this.readArray.eltOk(ioIndex));
                        if (this.pendingReads != null) {
                            this.pendingReads[ioIndex] = false;
                        }
                    }
                    if (this.writeArray == null || (interestAndReady & 0x4) == 0x0) {
                        continue;
                    }
                    this.getWriteResults().append(this.writeArray.eltOk(ioIndex));
                }
                catch (CancelledKeyException cke) {
                    final int interest = key.interestOps();
                    if (this.readArray != null && (interest & 0x19) != 0x0) {
                        if (this.pendingReads != null) {
                            this.pendingReads[ioIndex] = false;
                        }
                        if (this.errorResults != null) {
                            this.errorResults = RubyArray.newArray(runtime, this.readArray.size() + this.writeArray.size());
                        }
                        if (this.fastSearch(this.errorResults.toJavaArrayUnsafe(), this.readIOs[ioIndex]) == -1) {
                            this.getErrorResults().append(this.readArray.eltOk(ioIndex));
                        }
                    }
                    if (this.writeArray == null || (interest & 0x4) == 0x0 || this.fastSearch(this.errorResults.toJavaArrayUnsafe(), this.writeIOs[ioIndex]) != -1) {
                        continue;
                    }
                    this.errorResults.append(this.writeArray.eltOk(ioIndex));
                }
            }
        }
    }
    
    private void processPendingAndUnselectable() {
        if (this.pendingReads != null) {
            for (int i = 0; i < this.pendingReads.length; ++i) {
                if (this.pendingReads[i]) {
                    this.getReadResults().append(this.readArray.eltOk(i));
                }
            }
        }
        if (this.unselectableReads != null) {
            for (int i = 0; i < this.unselectableReads.length; ++i) {
                if (this.unselectableReads[i]) {
                    this.getReadResults().append(this.readArray.eltOk(i));
                }
            }
        }
        if (this.unselectableWrites != null) {
            for (int i = 0; i < this.unselectableWrites.length; ++i) {
                if (this.unselectableWrites[i]) {
                    this.getWriteResults().append(this.writeArray.eltOk(i));
                }
            }
        }
    }
    
    private void tidyUp() throws IOException {
        if (this.selector != null) {
            this.selector.close();
        }
        if (this.readBlocking != null) {
            for (int i = 0; i < this.readBlocking.length; ++i) {
                if (this.readBlocking[i] != null) {
                    ((SelectableChannel)this.readIOs[i].getChannel()).configureBlocking(this.readBlocking[i]);
                }
            }
        }
        if (this.writeBlocking != null) {
            for (int i = 0; i < this.writeBlocking.length; ++i) {
                if (this.writeBlocking[i] != null) {
                    ((SelectableChannel)this.writeIOs[i].getChannel()).configureBlocking(this.writeBlocking[i]);
                }
            }
        }
    }
    
    private RubyArray getReadResults() {
        if (this.readResults == null) {
            this.readResults = RubyArray.newArray(this.runtime, this.readArray.size());
        }
        return this.readResults;
    }
    
    private RubyArray getWriteResults() {
        if (this.writeResults == null) {
            this.writeResults = RubyArray.newArray(this.runtime, this.writeArray.size());
        }
        return this.writeResults;
    }
    
    private RubyArray getErrorResults() {
        if (this.errorResults != null) {
            this.errorResults = RubyArray.newArray(this.runtime, this.readArray.size() + this.writeArray.size());
        }
        return this.errorResults;
    }
    
    private Selector getSelector(final ThreadContext context) throws IOException {
        if (this.selector == null) {
            this.selector = SelectorFactory.openWithRetryFrom(context.getRuntime(), SelectorProvider.provider());
        }
        return this.selector;
    }
    
    private Boolean[] getReadBlocking() {
        if (this.readBlocking == null) {
            this.readBlocking = new Boolean[this.readSize];
        }
        return this.readBlocking;
    }
    
    private Boolean[] getWriteBlocking() {
        if (this.writeBlocking == null) {
            this.writeBlocking = new Boolean[this.writeSize];
        }
        return this.writeBlocking;
    }
    
    private boolean[] getUnselectableReads() {
        if (this.unselectableReads == null) {
            this.unselectableReads = new boolean[this.readSize];
        }
        return this.unselectableReads;
    }
    
    private boolean[] getUnselectableWrites() {
        if (this.unselectableWrites == null) {
            this.unselectableWrites = new boolean[this.readSize];
        }
        return this.unselectableWrites;
    }
    
    private boolean[] getPendingReads() {
        if (this.pendingReads == null) {
            this.pendingReads = new boolean[this.readSize];
        }
        return this.pendingReads;
    }
    
    private IRubyObject constructResults(final Ruby runtime) {
        return RubyArray.newArrayLight(runtime, (this.readResults == null) ? RubyArray.newEmptyArray(runtime) : this.readResults, (this.writeResults == null) ? RubyArray.newEmptyArray(runtime) : this.writeResults, (this.errorResults == null) ? RubyArray.newEmptyArray(runtime) : this.errorResults);
    }
    
    private int fastSearch(final Object[] ary, final Object obj) {
        for (int i = 0; i < ary.length; ++i) {
            if (ary[i] == obj) {
                return i;
            }
        }
        return -1;
    }
    
    private static void checkArrayType(final Ruby runtime, final IRubyObject obj) {
        if (!(obj instanceof RubyArray)) {
            throw runtime.newTypeError("wrong argument type " + obj.getMetaClass().getName() + " (expected Array)");
        }
    }
    
    private static boolean registerSelect(final ThreadContext context, final Selector selector, final Object obj, final RubyIO ioObj, final int ops) throws IOException {
        final Channel channel = ioObj.getChannel();
        if (channel == null || !(channel instanceof SelectableChannel)) {
            return false;
        }
        ((SelectableChannel)channel).configureBlocking(false);
        final int real_ops = ((SelectableChannel)channel).validOps() & ops;
        final SelectionKey key = ((SelectableChannel)channel).keyFor(selector);
        if (key == null) {
            ((SelectableChannel)channel).register(selector, real_ops, obj);
        }
        else {
            key.interestOps(key.interestOps() | real_ops);
        }
        return true;
    }
}
