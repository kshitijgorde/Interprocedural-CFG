// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.libraries;

import java.io.IOException;
import org.jruby.RubyModule;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.RubyFixnum;
import org.jruby.Ruby;
import org.jruby.runtime.load.Library;

public class FcntlLibrary implements Library
{
    public static final int FD_CLOEXEC = 1;
    
    public void load(final Ruby runtime, final boolean wrap) throws IOException {
        final RubyModule mFcntl = runtime.defineModule("Fcntl");
        runtime.loadConstantSet(mFcntl, "Fcntl");
        runtime.loadConstantSet(mFcntl, "OpenFlags");
        mFcntl.defineConstant("FD_CLOEXEC", RubyFixnum.newFixnum(runtime, 1L));
    }
}
