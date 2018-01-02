// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.internal.runtime;

import org.jruby.runtime.profile.IProfileData;
import org.jruby.runtime.ThreadContext;
import org.jruby.exceptions.ThreadKill;
import org.jruby.RubyThreadGroup;
import org.jruby.exceptions.MainExitException;
import org.jruby.exceptions.RaiseException;
import org.jruby.exceptions.JumpException;
import org.jruby.runtime.Block;
import org.jruby.runtime.Frame;
import org.jruby.RubyThread;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.RubyProc;
import org.jruby.Ruby;

public class RubyRunnable implements Runnable
{
    private Ruby runtime;
    private RubyProc proc;
    private IRubyObject[] arguments;
    private RubyThread rubyThread;
    private Frame[] currentFrames;
    private Thread javaThread;
    private static boolean warnedAboutTC;
    
    public RubyRunnable(final RubyThread rubyThread, final IRubyObject[] args, final Frame[] frames, final Block currentBlock) {
        this.rubyThread = rubyThread;
        this.runtime = rubyThread.getRuntime();
        this.proc = this.runtime.newProc(Block.Type.THREAD, currentBlock);
        this.currentFrames = frames;
        this.arguments = args;
    }
    
    public RubyThread getRubyThread() {
        return this.rubyThread;
    }
    
    public Thread getJavaThread() {
        return this.javaThread;
    }
    
    public void run() {
        this.javaThread = Thread.currentThread();
        final ThreadContext context = this.runtime.getThreadService().registerNewThread(this.rubyThread);
        ClassLoader oldContextClassLoader = null;
        try {
            oldContextClassLoader = this.javaThread.getContextClassLoader();
            this.javaThread.setContextClassLoader(this.runtime.getJRubyClassLoader());
        }
        catch (SecurityException se) {
            if (!RubyRunnable.warnedAboutTC && this.runtime.getInstanceConfig().isVerbose()) {
                System.err.println("WARNING: Security restrictions disallowed setting context classloader for Ruby threads.");
            }
        }
        context.preRunThread(this.currentFrames);
        try {
            try {
                final IRubyObject result = this.proc.call(context, this.arguments);
                this.rubyThread.cleanTerminate(result);
            }
            catch (JumpException.ReturnJump rj) {
                if (this.runtime.is1_9()) {
                    this.rubyThread.exceptionRaised(rj.buildException(this.runtime));
                }
                else {
                    this.rubyThread.exceptionRaised(this.runtime.newThreadError("return can't jump across threads"));
                }
            }
            catch (RaiseException e) {
                this.rubyThread.exceptionRaised(e);
            }
            catch (MainExitException mee) {
                this.runtime.getThreadService().getMainThread().kill();
            }
            finally {
                this.rubyThread.beDead();
                this.runtime.getThreadService().setCritical(false);
                this.runtime.getThreadService().unregisterThread(this.rubyThread);
                ((RubyThreadGroup)this.rubyThread.group()).remove(this.rubyThread);
                try {
                    this.javaThread.setContextClassLoader(oldContextClassLoader);
                }
                catch (SecurityException se2) {
                    if (!RubyRunnable.warnedAboutTC && this.runtime.getInstanceConfig().isVerbose()) {
                        System.err.println("WARNING: Security restrictions disallowed setting context classloader for Ruby threads.");
                    }
                }
                if (this.runtime.getInstanceConfig().isProfilingEntireRun()) {
                    final IProfileData profileData = context.getProfileData();
                    this.runtime.getInstanceConfig().makeDefaultProfilePrinter(profileData).printProfile(System.err);
                }
            }
        }
        catch (ThreadKill threadKill) {}
    }
    
    static {
        RubyRunnable.warnedAboutTC = false;
    }
}
