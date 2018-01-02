// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.libraries;

import java.io.IOException;
import org.jruby.ext.NetProtocolBufferedIO;
import org.jruby.Ruby;
import org.jruby.runtime.load.Library;

public class NetProtocolBufferedIOLibrary implements Library
{
    public void load(final Ruby runtime, final boolean wrap) throws IOException {
        runtime.getLoadService().removeBuiltinLibrary("net/protocol.rb");
        runtime.getLoadService().removeInternalLoadedFeature("net/protocol.rb");
        runtime.getLoadService().require("net/protocol");
        NetProtocolBufferedIO.create(runtime);
    }
}
