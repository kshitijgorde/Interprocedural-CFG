// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.libraries;

import org.jruby.anno.JRubyMethod;
import org.jruby.util.io.ChannelDescriptor;
import org.jruby.util.io.OpenFile;
import org.jruby.util.io.BadDescriptorException;
import org.jruby.RubyIO;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.RubyClass;
import org.jruby.Ruby;
import org.jruby.runtime.load.Library;

public class IOWaitLibrary implements Library
{
    public void load(final Ruby runtime, final boolean wrap) {
        final RubyClass ioClass = runtime.getIO();
        ioClass.defineAnnotatedMethods(IOWaitLibrary.class);
    }
    
    @JRubyMethod(name = { "ready?" })
    public static IRubyObject ready(final ThreadContext context, final IRubyObject obj) {
        final RubyIO io = (RubyIO)obj;
        try {
            final OpenFile openFile = io.getOpenFile();
            final ChannelDescriptor descriptor = openFile.getMainStreamSafe().getDescriptor();
            if (!descriptor.isOpen() || !openFile.getMainStreamSafe().getModes().isReadable() || openFile.getMainStreamSafe().feof()) {
                return context.getRuntime().getFalse();
            }
            final int avail = openFile.getMainStreamSafe().ready();
            if (avail > 0) {
                return context.getRuntime().newFixnum(avail);
            }
        }
        catch (BadDescriptorException e) {
            throw context.runtime.newErrnoEBADFError();
        }
        catch (Exception anyEx) {
            return context.getRuntime().getFalse();
        }
        return context.getRuntime().getNil();
    }
    
    @JRubyMethod
    public static IRubyObject io_wait(final ThreadContext context, final IRubyObject obj) {
        final RubyIO io = (RubyIO)obj;
        try {
            final OpenFile openFile = io.getOpenFile();
            if (openFile.getMainStreamSafe().feof()) {
                return context.getRuntime().getNil();
            }
            openFile.getMainStreamSafe().waitUntilReady();
        }
        catch (BadDescriptorException e) {
            throw context.runtime.newErrnoEBADFError();
        }
        catch (Exception anyEx) {
            return context.getRuntime().getNil();
        }
        return obj;
    }
}
