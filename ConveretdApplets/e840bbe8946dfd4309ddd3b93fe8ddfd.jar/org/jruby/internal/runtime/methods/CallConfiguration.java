// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.internal.runtime.methods;

import org.jruby.parser.StaticScope;
import org.jruby.runtime.Block;
import org.jruby.RubyModule;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.anno.JRubyMethod;

public enum CallConfiguration
{
    FrameFullScopeFull(Framing.Full, Scoping.Full) {
        void pre(final ThreadContext context, final IRubyObject self, final RubyModule implementer, final String name, final Block block, final StaticScope scope) {
            context.preMethodFrameAndScope(implementer, name, self, block, scope);
        }
        
        void post(final ThreadContext context) {
            context.postMethodFrameAndScope();
        }
    }, 
    FrameFullScopeDummy(Framing.Full, Scoping.Dummy) {
        void pre(final ThreadContext context, final IRubyObject self, final RubyModule implementer, final String name, final Block block, final StaticScope scope) {
            context.preMethodFrameAndDummyScope(implementer, name, self, block, scope);
        }
        
        void post(final ThreadContext context) {
            context.postMethodFrameAndScope();
        }
    }, 
    FrameFullScopeNone(Framing.Full, Scoping.None) {
        void pre(final ThreadContext context, final IRubyObject self, final RubyModule implementer, final String name, final Block block, final StaticScope scope) {
            context.preMethodFrameOnly(implementer, name, self, block);
        }
        
        void post(final ThreadContext context) {
            context.postMethodFrameOnly();
        }
    }, 
    FrameBacktraceScopeFull(Framing.Backtrace, Scoping.Full) {
        void pre(final ThreadContext context, final IRubyObject self, final RubyModule implementer, final String name, final Block block, final StaticScope scope) {
            context.preMethodBacktraceAndScope(name, implementer, scope);
        }
        
        void post(final ThreadContext context) {
            context.postMethodBacktraceAndScope();
        }
    }, 
    FrameBacktraceScopeDummy(Framing.Backtrace, Scoping.Dummy) {
        void pre(final ThreadContext context, final IRubyObject self, final RubyModule implementer, final String name, final Block block, final StaticScope scope) {
            context.preMethodBacktraceDummyScope(implementer, name, scope);
        }
        
        void post(final ThreadContext context) {
            context.postMethodBacktraceDummyScope();
        }
    }, 
    FrameBacktraceScopeNone(Framing.Backtrace, Scoping.None) {
        void pre(final ThreadContext context, final IRubyObject self, final RubyModule implementer, final String name, final Block block, final StaticScope scope) {
            context.preMethodBacktraceOnly(name);
        }
        
        void post(final ThreadContext context) {
            context.postMethodBacktraceOnly();
        }
    }, 
    FrameNoneScopeFull(Framing.None, Scoping.Full) {
        void pre(final ThreadContext context, final IRubyObject self, final RubyModule implementer, final String name, final Block block, final StaticScope scope) {
            context.preMethodScopeOnly(implementer, scope);
        }
        
        void post(final ThreadContext context) {
            context.postMethodScopeOnly();
        }
    }, 
    FrameNoneScopeDummy(Framing.None, Scoping.Dummy) {
        void pre(final ThreadContext context, final IRubyObject self, final RubyModule implementer, final String name, final Block block, final StaticScope scope) {
            context.preMethodNoFrameAndDummyScope(implementer, scope);
        }
        
        void post(final ThreadContext context) {
            context.postMethodScopeOnly();
        }
    }, 
    FrameNoneScopeNone(Framing.None, Scoping.None) {
        void pre(final ThreadContext context, final IRubyObject self, final RubyModule implementer, final String name, final Block block, final StaticScope scope) {
        }
        
        void post(final ThreadContext context) {
        }
    };
    
    @Deprecated
    public static final CallConfiguration FRAME_AND_SCOPE;
    @Deprecated
    public static final CallConfiguration FRAME_AND_DUMMY_SCOPE;
    @Deprecated
    public static final CallConfiguration FRAME_ONLY;
    @Deprecated
    public static final CallConfiguration BACKTRACE_AND_SCOPE;
    @Deprecated
    public static final CallConfiguration BACKTRACE_DUMMY_SCOPE;
    @Deprecated
    public static final CallConfiguration BACKTRACE_ONLY;
    @Deprecated
    public static final CallConfiguration SCOPE_ONLY;
    @Deprecated
    public static final CallConfiguration NO_FRAME_DUMMY_SCOPE;
    @Deprecated
    public static final CallConfiguration NO_FRAME_NO_SCOPE;
    private final Framing framing;
    private final Scoping scoping;
    
    public static CallConfiguration getCallConfigByAnno(final JRubyMethod anno) {
        return getCallConfig(anno.frame(), anno.scope(), anno.backtrace());
    }
    
    public static CallConfiguration getCallConfig(final boolean frame, final boolean scope, final boolean backtrace) {
        if (frame) {
            if (scope) {
                return CallConfiguration.FrameFullScopeFull;
            }
            return CallConfiguration.FrameFullScopeNone;
        }
        else {
            if (scope) {
                return CallConfiguration.FrameNoneScopeFull;
            }
            return CallConfiguration.FrameNoneScopeNone;
        }
    }
    
    private CallConfiguration(final Framing framing, final Scoping scoping) {
        this.framing = framing;
        this.scoping = scoping;
    }
    
    public final Framing framing() {
        return this.framing;
    }
    
    public final Scoping scoping() {
        return this.scoping;
    }
    
    abstract void pre(final ThreadContext p0, final IRubyObject p1, final RubyModule p2, final String p3, final Block p4, final StaticScope p5);
    
    abstract void post(final ThreadContext p0);
    
    boolean isNoop() {
        return this.framing == Framing.None && this.scoping == Scoping.None;
    }
    
    static {
        FRAME_AND_SCOPE = CallConfiguration.FrameFullScopeFull;
        FRAME_AND_DUMMY_SCOPE = CallConfiguration.FrameFullScopeDummy;
        FRAME_ONLY = CallConfiguration.FrameFullScopeNone;
        BACKTRACE_AND_SCOPE = CallConfiguration.FrameBacktraceScopeFull;
        BACKTRACE_DUMMY_SCOPE = CallConfiguration.FrameBacktraceScopeNone;
        BACKTRACE_ONLY = CallConfiguration.FrameBacktraceScopeNone;
        SCOPE_ONLY = CallConfiguration.FrameNoneScopeFull;
        NO_FRAME_DUMMY_SCOPE = CallConfiguration.FrameNoneScopeDummy;
        NO_FRAME_NO_SCOPE = CallConfiguration.FrameNoneScopeNone;
    }
}
