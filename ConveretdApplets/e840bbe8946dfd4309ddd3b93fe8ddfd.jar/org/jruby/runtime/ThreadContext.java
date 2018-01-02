// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.runtime;

import org.jruby.runtime.profile.ProfileData;
import org.jruby.parser.BlockStaticScope;
import org.jruby.RubyClass;
import java.util.List;
import java.util.ArrayList;
import org.jruby.runtime.backtrace.TraceType;
import org.jruby.RubyString;
import org.jruby.runtime.backtrace.RubyStackTraceElement;
import org.jruby.RubyArray;
import org.jruby.lexer.yacc.ISourcePosition;
import org.jruby.exceptions.JumpException;
import org.jruby.runtime.scope.ManyVarsDynamicScope;
import org.jruby.parser.StaticScope;
import org.jruby.parser.LocalStaticScope;
import org.jruby.runtime.profile.IProfileData;
import org.jruby.RubyContinuation;
import org.jruby.runtime.backtrace.BacktraceElement;
import org.jruby.RubyModule;
import org.jruby.libraries.FiberLibrary;
import org.jruby.RubyThread;
import org.jruby.ast.executable.RuntimeCache;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.Ruby;

public final class ThreadContext
{
    private static final int INITIAL_SIZE = 10;
    private static final int INITIAL_FRAMES_SIZE = 10;
    private static final int CALL_POLL_COUNT = 4095;
    public final Ruby runtime;
    public final IRubyObject nil;
    public final RuntimeCache runtimeCache;
    private boolean isWithinTrace;
    private boolean isWithinDefined;
    private RubyThread thread;
    private FiberLibrary.Fiber fiber;
    private RubyModule[] parentStack;
    private int parentIndex;
    private Frame[] frameStack;
    private int frameIndex;
    private BacktraceElement[] backtrace;
    private int backtraceIndex;
    private DynamicScope[] scopeStack;
    private int scopeIndex;
    private static final RubyContinuation.Continuation[] EMPTY_CATCHTARGET_STACK;
    private RubyContinuation.Continuation[] catchStack;
    private int catchIndex;
    private boolean isProfiling;
    private IProfileData profileData;
    private int rubyFrameDelta;
    private boolean eventHooksEnabled;
    CallType lastCallType;
    Visibility lastVisibility;
    IRubyObject lastExitStatus;
    public int callNumber;
    private int currentMethodSerial;
    
    public static ThreadContext newContext(final Ruby runtime) {
        final ThreadContext context = new ThreadContext(runtime);
        return context;
    }
    
    private ThreadContext(final Ruby runtime) {
        this.parentStack = new RubyModule[10];
        this.parentIndex = -1;
        this.frameStack = new Frame[10];
        this.frameIndex = -1;
        this.backtrace = new BacktraceElement[10];
        this.backtraceIndex = -1;
        this.scopeStack = new DynamicScope[10];
        this.scopeIndex = -1;
        this.catchStack = ThreadContext.EMPTY_CATCHTARGET_STACK;
        this.catchIndex = -1;
        this.isProfiling = false;
        this.rubyFrameDelta = 0;
        this.eventHooksEnabled = true;
        this.callNumber = 0;
        this.currentMethodSerial = 0;
        this.runtime = runtime;
        this.nil = runtime.getNil();
        if (runtime.getInstanceConfig().isProfilingEntireRun()) {
            this.startProfiling();
        }
        this.runtimeCache = runtime.getRuntimeCache();
        final StaticScope topStaticScope = new LocalStaticScope(null);
        this.pushScope(new ManyVarsDynamicScope(topStaticScope, null));
        final Frame[] stack = this.frameStack;
        for (int length = stack.length, i = 0; i < length; ++i) {
            stack[i] = new Frame();
        }
        final BacktraceElement[] stack2 = this.backtrace;
        for (int length2 = stack2.length, j = 0; j < length2; ++j) {
            stack2[j] = new BacktraceElement();
        }
        pushBacktrace(this, "", "", "", 0);
        pushBacktrace(this, "", "", "", 0);
    }
    
    protected void finalize() throws Throwable {
        this.thread.dispose();
    }
    
    public final Ruby getRuntime() {
        return this.runtime;
    }
    
    public IRubyObject getErrorInfo() {
        return this.thread.getErrorInfo();
    }
    
    public IRubyObject setErrorInfo(final IRubyObject errorInfo) {
        this.thread.setErrorInfo(errorInfo);
        return errorInfo;
    }
    
    public JumpException.ReturnJump returnJump(final IRubyObject value) {
        return new JumpException.ReturnJump(this.getFrameJumpTarget(), value);
    }
    
    public void setLastCallStatus(final CallType callType) {
        this.lastCallType = callType;
    }
    
    public CallType getLastCallType() {
        return this.lastCallType;
    }
    
    public void setLastVisibility(final Visibility visibility) {
        this.lastVisibility = visibility;
    }
    
    public Visibility getLastVisibility() {
        return this.lastVisibility;
    }
    
    public void setLastCallStatusAndVisibility(final CallType callType, final Visibility visibility) {
        this.lastCallType = callType;
        this.lastVisibility = visibility;
    }
    
    public IRubyObject getLastExitStatus() {
        return this.lastExitStatus;
    }
    
    public void setLastExitStatus(final IRubyObject lastExitStatus) {
        this.lastExitStatus = lastExitStatus;
    }
    
    public void printScope() {
        System.out.println("SCOPE STACK:");
        for (int i = 0; i <= this.scopeIndex; ++i) {
            System.out.println(this.scopeStack[i]);
        }
    }
    
    public DynamicScope getCurrentScope() {
        return this.scopeStack[this.scopeIndex];
    }
    
    public DynamicScope getPreviousScope() {
        return this.scopeStack[this.scopeIndex - 1];
    }
    
    private void expandFramesIfNecessary() {
        final int newSize = this.frameStack.length * 2;
        this.frameStack = this.fillNewFrameStack(new Frame[newSize], newSize);
    }
    
    private Frame[] fillNewFrameStack(final Frame[] newFrameStack, final int newSize) {
        System.arraycopy(this.frameStack, 0, newFrameStack, 0, this.frameStack.length);
        for (int i = this.frameStack.length; i < newSize; ++i) {
            newFrameStack[i] = new Frame();
        }
        return newFrameStack;
    }
    
    private void expandParentsIfNecessary() {
        final int newSize = this.parentStack.length * 2;
        final RubyModule[] newParentStack = new RubyModule[newSize];
        System.arraycopy(this.parentStack, 0, newParentStack, 0, this.parentStack.length);
        this.parentStack = newParentStack;
    }
    
    public void pushScope(final DynamicScope scope) {
        final int index = ++this.scopeIndex;
        final DynamicScope[] stack = this.scopeStack;
        stack[index] = scope;
        if (index + 1 == stack.length) {
            this.expandScopesIfNecessary();
        }
    }
    
    public void popScope() {
        this.scopeStack[this.scopeIndex--] = null;
    }
    
    private void expandScopesIfNecessary() {
        final int newSize = this.scopeStack.length * 2;
        final DynamicScope[] newScopeStack = new DynamicScope[newSize];
        System.arraycopy(this.scopeStack, 0, newScopeStack, 0, this.scopeStack.length);
        this.scopeStack = newScopeStack;
    }
    
    public RubyThread getThread() {
        return this.thread;
    }
    
    public void setThread(final RubyThread thread) {
        this.thread = thread;
        if (thread != null) {
            thread.setContext(this);
        }
    }
    
    public FiberLibrary.Fiber getFiber() {
        return this.fiber;
    }
    
    public void setFiber(final FiberLibrary.Fiber fiber) {
        this.fiber = fiber;
    }
    
    private void expandCatchIfNecessary() {
        int newSize = this.catchStack.length * 2;
        if (newSize == 0) {
            newSize = 1;
        }
        final RubyContinuation.Continuation[] newCatchStack = new RubyContinuation.Continuation[newSize];
        System.arraycopy(this.catchStack, 0, newCatchStack, 0, this.catchStack.length);
        this.catchStack = newCatchStack;
    }
    
    public void pushCatch(final RubyContinuation.Continuation catchTarget) {
        final int index = ++this.catchIndex;
        if (index == this.catchStack.length) {
            this.expandCatchIfNecessary();
        }
        this.catchStack[index] = catchTarget;
    }
    
    public void popCatch() {
        --this.catchIndex;
    }
    
    public RubyContinuation.Continuation getActiveCatch(final Object tag) {
        for (int i = this.catchIndex; i >= 0; --i) {
            final RubyContinuation.Continuation c = this.catchStack[i];
            if (this.runtime.is1_9()) {
                if (c.tag == tag) {
                    return c;
                }
            }
            else if (c.tag.equals(tag)) {
                return c;
            }
        }
        return null;
    }
    
    private void pushFrameCopy() {
        final int index = ++this.frameIndex;
        final Frame[] stack = this.frameStack;
        final Frame currentFrame = stack[index - 1];
        stack[index].updateFrame(currentFrame);
        if (index + 1 == stack.length) {
            this.expandFramesIfNecessary();
        }
    }
    
    private Frame pushFrame(final Frame frame) {
        final int index = ++this.frameIndex;
        final Frame[] stack = this.frameStack;
        stack[index] = frame;
        if (index + 1 == stack.length) {
            this.expandFramesIfNecessary();
        }
        return frame;
    }
    
    private void pushCallFrame(final RubyModule clazz, final String name, final IRubyObject self, final Block block) {
        final int index = ++this.frameIndex;
        final Frame[] stack = this.frameStack;
        stack[index].updateFrame(clazz, self, name, block, this.callNumber);
        if (index + 1 == stack.length) {
            this.expandFramesIfNecessary();
        }
    }
    
    private void pushEvalFrame(final IRubyObject self) {
        final int index = ++this.frameIndex;
        final Frame[] stack = this.frameStack;
        stack[index].updateFrameForEval(self, this.callNumber);
        if (index + 1 == stack.length) {
            this.expandFramesIfNecessary();
        }
    }
    
    private void pushFrame(final String name) {
        final int index = ++this.frameIndex;
        final Frame[] stack = this.frameStack;
        stack[index].updateFrame(name);
        if (index + 1 == stack.length) {
            this.expandFramesIfNecessary();
        }
    }
    
    public void pushFrame() {
        final int index = ++this.frameIndex;
        final Frame[] stack = this.frameStack;
        if (index + 1 == stack.length) {
            this.expandFramesIfNecessary();
        }
    }
    
    public void popFrame() {
        final Frame frame = this.frameStack[this.frameIndex--];
        frame.clear();
    }
    
    private void popFrameReal(final Frame oldFrame) {
        this.frameStack[this.frameIndex--] = oldFrame;
    }
    
    public Frame getCurrentFrame() {
        return this.frameStack[this.frameIndex];
    }
    
    public int getRubyFrameDelta() {
        return this.rubyFrameDelta;
    }
    
    public void setRubyFrameDelta(final int newDelta) {
        this.rubyFrameDelta = newDelta;
    }
    
    public Frame getCurrentRubyFrame() {
        return this.frameStack[this.frameIndex - this.rubyFrameDelta];
    }
    
    public Frame getNextFrame() {
        final int index = this.frameIndex;
        final Frame[] stack = this.frameStack;
        if (index + 1 == stack.length) {
            this.expandFramesIfNecessary();
        }
        return stack[index + 1];
    }
    
    public Frame getPreviousFrame() {
        final int index = this.frameIndex;
        return (index < 1) ? null : this.frameStack[index - 1];
    }
    
    public int getFrameCount() {
        return this.frameIndex + 1;
    }
    
    public Frame[] getFrames(final int delta) {
        final int top = this.frameIndex + delta;
        final Frame[] frames = new Frame[top + 1];
        for (int i = 0; i <= top; ++i) {
            frames[i] = this.frameStack[i].duplicateForBacktrace();
        }
        return frames;
    }
    
    private static void expandBacktraceIfNecessary(final ThreadContext context) {
        final int newSize = context.backtrace.length * 2;
        context.backtrace = fillNewBacktrace(context, new BacktraceElement[newSize], newSize);
    }
    
    private static BacktraceElement[] fillNewBacktrace(final ThreadContext context, final BacktraceElement[] newBacktrace, final int newSize) {
        System.arraycopy(context.backtrace, 0, newBacktrace, 0, context.backtrace.length);
        for (int i = context.backtrace.length; i < newSize; ++i) {
            newBacktrace[i] = new BacktraceElement();
        }
        return newBacktrace;
    }
    
    public static void pushBacktrace(final ThreadContext context, final String klass, final String method, final ISourcePosition position) {
        final int index = ++context.backtraceIndex;
        final BacktraceElement[] stack = context.backtrace;
        BacktraceElement.update(stack[index], klass, method, position);
        if (index + 1 == stack.length) {
            expandBacktraceIfNecessary(context);
        }
    }
    
    public static void pushBacktrace(final ThreadContext context, final String klass, final String method, final String file, final int line) {
        final int index = ++context.backtraceIndex;
        final BacktraceElement[] stack = context.backtrace;
        BacktraceElement.update(stack[index], klass, method, file, line);
        if (index + 1 == stack.length) {
            expandBacktraceIfNecessary(context);
        }
    }
    
    public static void popBacktrace(final ThreadContext context) {
        --context.backtraceIndex;
    }
    
    public boolean isJumpTargetAlive(final int target, final int skipFrames) {
        for (int i = this.frameIndex - skipFrames; i >= 0; --i) {
            if (this.frameStack[i].getJumpTarget() == target) {
                return true;
            }
        }
        return false;
    }
    
    public String getFrameName() {
        return this.getCurrentFrame().getName();
    }
    
    public IRubyObject getFrameSelf() {
        return this.getCurrentFrame().getSelf();
    }
    
    public int getFrameJumpTarget() {
        return this.getCurrentFrame().getJumpTarget();
    }
    
    public RubyModule getFrameKlazz() {
        return this.getCurrentFrame().getKlazz();
    }
    
    public Block getFrameBlock() {
        return this.getCurrentFrame().getBlock();
    }
    
    public String getFile() {
        return this.backtrace[this.backtraceIndex].filename;
    }
    
    public int getLine() {
        return this.backtrace[this.backtraceIndex].line;
    }
    
    public void setFile(final String file) {
        this.backtrace[this.backtraceIndex].filename = file;
    }
    
    public void setLine(final int line) {
        this.backtrace[this.backtraceIndex].line = line;
    }
    
    public void setFileAndLine(final String file, final int line) {
        this.backtrace[this.backtraceIndex].filename = file;
        this.backtrace[this.backtraceIndex].line = line;
    }
    
    public void setFileAndLine(final ISourcePosition position) {
        this.backtrace[this.backtraceIndex].filename = position.getFile();
        this.backtrace[this.backtraceIndex].line = position.getStartLine();
    }
    
    public Visibility getCurrentVisibility() {
        return this.getCurrentFrame().getVisibility();
    }
    
    public Visibility getPreviousVisibility() {
        return this.getPreviousFrame().getVisibility();
    }
    
    public void setCurrentVisibility(final Visibility visibility) {
        this.getCurrentFrame().setVisibility(visibility);
    }
    
    public void pollThreadEvents() {
        this.thread.pollThreadEvents(this);
    }
    
    public int getCurrentTarget() {
        return this.callNumber;
    }
    
    public void callThreadPoll() {
        if ((this.callNumber++ & 0xFFF) == 0x0) {
            this.pollThreadEvents();
        }
    }
    
    public static void callThreadPoll(final ThreadContext context) {
        if ((context.callNumber++ & 0xFFF) == 0x0) {
            context.pollThreadEvents();
        }
    }
    
    public void trace(final RubyEvent event, final String name, final RubyModule implClass) {
        this.trace(event, name, implClass, this.backtrace[this.backtraceIndex].filename, this.backtrace[this.backtraceIndex].line);
    }
    
    public void trace(final RubyEvent event, final String name, final RubyModule implClass, final String file, final int line) {
        this.runtime.callEventHooks(this, event, file, line, name, implClass);
    }
    
    public void pushRubyClass(final RubyModule currentModule) {
        final int index = ++this.parentIndex;
        final RubyModule[] stack = this.parentStack;
        stack[index] = currentModule;
        if (index + 1 == stack.length) {
            this.expandParentsIfNecessary();
        }
    }
    
    public RubyModule popRubyClass() {
        final int index = this.parentIndex;
        final RubyModule[] stack = this.parentStack;
        final RubyModule ret = stack[index];
        stack[index] = null;
        this.parentIndex = index - 1;
        return ret;
    }
    
    public RubyModule getRubyClass() {
        assert this.parentIndex != -1 : "Trying to get RubyClass from empty stack";
        final RubyModule parentModule = this.parentStack[this.parentIndex];
        return parentModule.getNonIncludedClass();
    }
    
    public RubyModule getPreviousRubyClass() {
        assert this.parentIndex != 0 : "Trying to get RubyClass from too-shallow stack";
        final RubyModule parentModule = this.parentStack[this.parentIndex - 1];
        return parentModule.getNonIncludedClass();
    }
    
    public boolean getConstantDefined(final String internedName) {
        final IRubyObject value = this.getConstant(internedName);
        return value != null;
    }
    
    public IRubyObject getConstant(final String internedName) {
        return this.getCurrentScope().getStaticScope().getConstant(this.runtime, internedName, this.runtime.getObject());
    }
    
    public IRubyObject setConstantInCurrent(final String internedName, final IRubyObject result) {
        final RubyModule module;
        if ((module = this.getCurrentScope().getStaticScope().getModule()) != null) {
            module.fastSetConstant(internedName, result);
            return result;
        }
        throw this.runtime.newTypeError("no class/module to define constant");
    }
    
    public IRubyObject setConstantInModule(final String internedName, final IRubyObject target, final IRubyObject result) {
        if (!(target instanceof RubyModule)) {
            throw this.runtime.newTypeError(target.toString() + " is not a class/module");
        }
        final RubyModule module = (RubyModule)target;
        module.fastSetConstant(internedName, result);
        return result;
    }
    
    public IRubyObject setConstantInObject(final String internedName, final IRubyObject result) {
        this.runtime.getObject().fastSetConstant(internedName, result);
        return result;
    }
    
    private static void addBackTraceElement(final Ruby runtime, final RubyArray backtrace, final RubyStackTraceElement element) {
        final RubyString str = RubyString.newString(runtime, element.getFileName() + ":" + element.getLineNumber() + ":in `" + element.getMethodName() + "'");
        backtrace.append(str);
    }
    
    public IRubyObject createCallerBacktrace(final Ruby runtime, final int level) {
        runtime.incrementCallerCount();
        final RubyStackTraceElement[] trace = this.gatherCallerBacktrace(level);
        final RubyArray backtrace = runtime.newArray(trace.length - level);
        for (int i = level; i < trace.length; ++i) {
            addBackTraceElement(runtime, backtrace, trace[i]);
        }
        return backtrace;
    }
    
    public RubyStackTraceElement[] gatherCallerBacktrace(final int level) {
        final Thread nativeThread = this.thread.getNativeThread();
        if (nativeThread == null) {
            return new RubyStackTraceElement[0];
        }
        final BacktraceElement[] copy = new BacktraceElement[this.backtraceIndex + 1];
        System.arraycopy(this.backtrace, 0, copy, 0, this.backtraceIndex + 1);
        final RubyStackTraceElement[] trace = TraceType.Gather.CALLER.getBacktraceData(this, false).getBacktrace(this.runtime);
        return trace;
    }
    
    public Frame[] createBacktrace(final int level, final boolean nativeException) {
        final int traceSize = this.frameIndex - level + 1;
        if (traceSize <= 0) {
            return null;
        }
        Frame[] traceFrames;
        if (nativeException) {
            traceFrames = new Frame[traceSize + 1];
            traceFrames[traceSize] = this.frameStack[this.frameIndex];
        }
        else {
            traceFrames = new Frame[traceSize];
        }
        System.arraycopy(this.frameStack, 0, traceFrames, 0, traceSize);
        return traceFrames;
    }
    
    public boolean isEventHooksEnabled() {
        return this.eventHooksEnabled;
    }
    
    public void setEventHooksEnabled(final boolean flag) {
        this.eventHooksEnabled = flag;
    }
    
    public BacktraceElement[] createBacktrace2(final int level, final boolean nativeException) {
        final BacktraceElement[] newTrace = new BacktraceElement[this.backtraceIndex + 1];
        for (int i = 0; i <= this.backtraceIndex; ++i) {
            newTrace[i] = this.backtrace[i].clone();
        }
        return newTrace;
    }
    
    private static String createRubyBacktraceString(final StackTraceElement element) {
        return element.getFileName() + ":" + element.getLineNumber() + ":in `" + element.getMethodName() + "'";
    }
    
    public static String createRawBacktraceStringFromThrowable(final Throwable t) {
        final StackTraceElement[] javaStackTrace = t.getStackTrace();
        final StringBuffer buffer = new StringBuffer();
        if (javaStackTrace != null && javaStackTrace.length > 0) {
            StackTraceElement element = javaStackTrace[0];
            buffer.append(createRubyBacktraceString(element)).append(": ").append(t.toString()).append("\n");
            for (int i = 1; i < javaStackTrace.length; ++i) {
                element = javaStackTrace[i];
                buffer.append("\tfrom ").append(createRubyBacktraceString(element));
                if (i + 1 < javaStackTrace.length) {
                    buffer.append("\n");
                }
            }
        }
        return buffer.toString();
    }
    
    public static RubyStackTraceElement[] gatherRawBacktrace(final Ruby runtime, final StackTraceElement[] stackTrace) {
        final List trace = new ArrayList(stackTrace.length);
        for (int i = 0; i < stackTrace.length; ++i) {
            final StackTraceElement element = stackTrace[i];
            trace.add(new RubyStackTraceElement(element));
        }
        final RubyStackTraceElement[] rubyStackTrace = new RubyStackTraceElement[trace.size()];
        return trace.toArray(rubyStackTrace);
    }
    
    private Frame pushFrameForBlock(final Binding binding) {
        final Frame lastFrame = this.getNextFrame();
        final Frame f = this.pushFrame(binding.getFrame());
        f.setVisibility(binding.getVisibility());
        return lastFrame;
    }
    
    private Frame pushFrameForEval(final Binding binding) {
        final Frame lastFrame = this.getNextFrame();
        final Frame f = this.pushFrame(binding.getFrame());
        f.setVisibility(binding.getVisibility());
        return lastFrame;
    }
    
    public void preAdoptThread() {
        this.pushFrame();
        this.pushRubyClass(this.runtime.getObject());
        this.getCurrentFrame().setSelf(this.runtime.getTopSelf());
    }
    
    public void preExtensionLoad(final IRubyObject self) {
        this.pushFrame();
        this.pushRubyClass(this.runtime.getObject());
        this.getCurrentFrame().setSelf(self);
        this.getCurrentFrame().setVisibility(Visibility.PUBLIC);
    }
    
    public void postExtensionLoad() {
        this.popFrame();
        this.popRubyClass();
    }
    
    public void preCompiledClass(final RubyModule type, final StaticScope staticScope) {
        this.pushRubyClass(type);
        this.pushFrameCopy();
        this.getCurrentFrame().setSelf(type);
        this.getCurrentFrame().setVisibility(Visibility.PUBLIC);
        staticScope.setModule(type);
        this.pushScope(DynamicScope.newDynamicScope(staticScope));
    }
    
    public void preCompiledClassDummyScope(final RubyModule type, final StaticScope staticScope) {
        this.pushRubyClass(type);
        this.pushFrameCopy();
        this.getCurrentFrame().setSelf(type);
        this.getCurrentFrame().setVisibility(Visibility.PUBLIC);
        staticScope.setModule(type);
        this.pushScope(staticScope.getDummyScope());
    }
    
    public void postCompiledClass() {
        this.popScope();
        this.popRubyClass();
        this.popFrame();
    }
    
    public void preScopeNode(final StaticScope staticScope) {
        this.pushScope(DynamicScope.newDynamicScope(staticScope, this.getCurrentScope()));
    }
    
    public void postScopeNode() {
        this.popScope();
    }
    
    public void preClassEval(final StaticScope staticScope, final RubyModule type) {
        this.pushRubyClass(type);
        this.pushFrameCopy();
        this.getCurrentFrame().setSelf(type);
        this.getCurrentFrame().setVisibility(Visibility.PUBLIC);
        this.pushScope(DynamicScope.newDynamicScope(staticScope, null));
    }
    
    public void postClassEval() {
        this.popScope();
        this.popRubyClass();
        this.popFrame();
    }
    
    public void preBsfApply(final String[] names) {
        final LocalStaticScope staticScope = new LocalStaticScope(null);
        staticScope.setVariables(names);
        this.pushFrame();
    }
    
    public void postBsfApply() {
        this.popFrame();
    }
    
    public void preMethodFrameAndScope(final RubyModule clazz, final String name, final IRubyObject self, final Block block, final StaticScope staticScope) {
        RubyModule implementationClass = staticScope.getModule();
        if (implementationClass == null) {
            implementationClass = clazz;
        }
        this.pushCallFrame(clazz, name, self, block);
        this.pushScope(DynamicScope.newDynamicScope(staticScope));
        this.pushRubyClass(implementationClass);
    }
    
    public void preMethodFrameAndDummyScope(final RubyModule clazz, final String name, final IRubyObject self, final Block block, final StaticScope staticScope) {
        RubyModule implementationClass = staticScope.getModule();
        if (implementationClass == null) {
            implementationClass = clazz;
        }
        this.pushCallFrame(clazz, name, self, block);
        this.pushScope(staticScope.getDummyScope());
        this.pushRubyClass(implementationClass);
    }
    
    public void preMethodNoFrameAndDummyScope(final RubyModule clazz, final StaticScope staticScope) {
        RubyModule implementationClass = staticScope.getModule();
        if (implementationClass == null) {
            implementationClass = clazz;
        }
        this.pushScope(staticScope.getDummyScope());
        this.pushRubyClass(implementationClass);
    }
    
    public void postMethodFrameAndScope() {
        this.popRubyClass();
        this.popScope();
        this.popFrame();
    }
    
    public void preMethodFrameOnly(final RubyModule clazz, final String name, final IRubyObject self, final Block block) {
        this.pushRubyClass(clazz);
        this.pushCallFrame(clazz, name, self, block);
    }
    
    public void postMethodFrameOnly() {
        this.popFrame();
        this.popRubyClass();
    }
    
    public void preMethodScopeOnly(final RubyModule clazz, final StaticScope staticScope) {
        RubyModule implementationClass = staticScope.getModule();
        if (implementationClass == null) {
            implementationClass = clazz;
        }
        this.pushScope(DynamicScope.newDynamicScope(staticScope));
        this.pushRubyClass(implementationClass);
    }
    
    public void postMethodScopeOnly() {
        this.popRubyClass();
        this.popScope();
    }
    
    public void preMethodBacktraceAndScope(final String name, final RubyModule clazz, final StaticScope staticScope) {
        this.preMethodScopeOnly(clazz, staticScope);
    }
    
    public void postMethodBacktraceAndScope() {
        this.postMethodScopeOnly();
    }
    
    public void preMethodBacktraceOnly(final String name) {
    }
    
    public void preMethodBacktraceDummyScope(final RubyModule clazz, final String name, final StaticScope staticScope) {
        RubyModule implementationClass = staticScope.getModule();
        if (implementationClass == null) {
            implementationClass = clazz;
        }
        this.pushScope(staticScope.getDummyScope());
        this.pushRubyClass(implementationClass);
    }
    
    public void postMethodBacktraceOnly() {
    }
    
    public void postMethodBacktraceDummyScope() {
        this.popRubyClass();
        this.popScope();
    }
    
    public void prepareTopLevel(final RubyClass objectClass, final IRubyObject topSelf) {
        this.pushFrame();
        this.setCurrentVisibility(Visibility.PRIVATE);
        this.pushRubyClass(objectClass);
        final Frame frame = this.getCurrentFrame();
        frame.setSelf(topSelf);
        this.getCurrentScope().getStaticScope().setModule(objectClass);
    }
    
    public void preNodeEval(final RubyModule rubyClass, final IRubyObject self, final String name) {
        this.pushRubyClass(rubyClass);
        this.pushEvalFrame(self);
    }
    
    public void preNodeEval(final RubyModule rubyClass, final IRubyObject self) {
        this.pushRubyClass(rubyClass);
        this.pushEvalFrame(self);
    }
    
    public void postNodeEval() {
        this.popFrame();
        this.popRubyClass();
    }
    
    public void preExecuteUnder(final RubyModule executeUnderClass, final Block block) {
        final Frame frame = this.getCurrentFrame();
        this.pushRubyClass(executeUnderClass);
        final DynamicScope scope = this.getCurrentScope();
        final StaticScope sScope = new BlockStaticScope(scope.getStaticScope());
        sScope.setModule(executeUnderClass);
        this.pushScope(DynamicScope.newDynamicScope(sScope, scope));
        this.pushCallFrame(frame.getKlazz(), frame.getName(), frame.getSelf(), block);
        this.getCurrentFrame().setVisibility(this.getPreviousFrame().getVisibility());
    }
    
    public void postExecuteUnder() {
        this.popFrame();
        this.popScope();
        this.popRubyClass();
    }
    
    public void preMproc() {
        this.pushFrame();
    }
    
    public void postMproc() {
        this.popFrame();
    }
    
    public void preRunThread(final Frame[] currentFrames) {
        for (final Frame frame : currentFrames) {
            this.pushFrame(frame);
        }
    }
    
    public void preTrace() {
        this.setWithinTrace(true);
        this.pushFrame();
    }
    
    public void postTrace() {
        this.popFrame();
        this.setWithinTrace(false);
    }
    
    public Frame preForBlock(final Binding binding, final RubyModule klass) {
        final Frame lastFrame = this.preYieldNoScope(binding, klass);
        this.pushScope(binding.getDynamicScope());
        return lastFrame;
    }
    
    public Frame preYieldSpecificBlock(final Binding binding, final StaticScope scope, final RubyModule klass) {
        final Frame lastFrame = this.preYieldNoScope(binding, klass);
        this.pushScope(DynamicScope.newDynamicScope(scope, binding.getDynamicScope()));
        return lastFrame;
    }
    
    public Frame preYieldLightBlock(final Binding binding, final DynamicScope emptyScope, final RubyModule klass) {
        final Frame lastFrame = this.preYieldNoScope(binding, klass);
        this.pushScope(emptyScope);
        return lastFrame;
    }
    
    public Frame preYieldNoScope(final Binding binding, final RubyModule klass) {
        this.pushRubyClass((klass != null) ? klass : binding.getKlass());
        return this.pushFrameForBlock(binding);
    }
    
    public void preEvalScriptlet(final DynamicScope scope) {
        this.pushScope(scope);
    }
    
    public void postEvalScriptlet() {
        this.popScope();
    }
    
    public Frame preEvalWithBinding(final Binding binding) {
        binding.getFrame().setIsBindingFrame(true);
        final Frame lastFrame = this.pushFrameForEval(binding);
        this.pushRubyClass(binding.getKlass());
        return lastFrame;
    }
    
    public void postEvalWithBinding(final Binding binding, final Frame lastFrame) {
        binding.getFrame().setIsBindingFrame(false);
        this.popFrameReal(lastFrame);
        this.popRubyClass();
    }
    
    public void postYield(final Binding binding, final Frame lastFrame) {
        this.popScope();
        this.popFrameReal(lastFrame);
        this.popRubyClass();
    }
    
    public void postYieldLight(final Binding binding, final Frame lastFrame) {
        this.popScope();
        this.popFrameReal(lastFrame);
        this.popRubyClass();
    }
    
    public void postYieldNoScope(final Frame lastFrame) {
        this.popFrameReal(lastFrame);
        this.popRubyClass();
    }
    
    public void preScopedBody(final DynamicScope scope) {
        this.pushScope(scope);
    }
    
    public void postScopedBody() {
        this.popScope();
    }
    
    public boolean isWithinTrace() {
        return this.isWithinTrace;
    }
    
    public void setWithinTrace(final boolean isWithinTrace) {
        this.isWithinTrace = isWithinTrace;
    }
    
    public boolean isWithinDefined() {
        return this.isWithinDefined;
    }
    
    public void setWithinDefined(final boolean isWithinDefined) {
        this.isWithinDefined = isWithinDefined;
    }
    
    public Binding currentBinding() {
        final Frame frame = this.getCurrentFrame();
        return new Binding(frame, this.getRubyClass(), this.getCurrentScope(), this.backtrace[this.backtraceIndex].clone());
    }
    
    public Binding currentBinding(final IRubyObject self) {
        final Frame frame = this.getCurrentFrame();
        return new Binding(self, frame, frame.getVisibility(), this.getRubyClass(), this.getCurrentScope(), this.backtrace[this.backtraceIndex].clone());
    }
    
    public Binding currentBinding(final IRubyObject self, final Visibility visibility) {
        final Frame frame = this.getCurrentFrame();
        return new Binding(self, frame, visibility, this.getRubyClass(), this.getCurrentScope(), this.backtrace[this.backtraceIndex].clone());
    }
    
    public Binding currentBinding(final IRubyObject self, final DynamicScope scope) {
        final Frame frame = this.getCurrentFrame();
        return new Binding(self, frame, frame.getVisibility(), this.getRubyClass(), scope, this.backtrace[this.backtraceIndex].clone());
    }
    
    public Binding currentBinding(final IRubyObject self, final Visibility visibility, final DynamicScope scope) {
        final Frame frame = this.getCurrentFrame();
        return new Binding(self, frame, visibility, this.getRubyClass(), scope, this.backtrace[this.backtraceIndex].clone());
    }
    
    public Binding previousBinding() {
        final Frame frame = this.getPreviousFrame();
        return new Binding(frame, this.getPreviousRubyClass(), this.getCurrentScope(), this.backtrace[this.backtraceIndex].clone());
    }
    
    public Binding previousBinding(final IRubyObject self) {
        final Frame frame = this.getPreviousFrame();
        return new Binding(self, frame, frame.getVisibility(), this.getPreviousRubyClass(), this.getCurrentScope(), this.backtrace[this.backtraceIndex].clone());
    }
    
    public IProfileData getProfileData() {
        if (this.profileData == null) {
            this.profileData = new ProfileData(this);
        }
        return this.profileData;
    }
    
    public int profileEnter(final int nextMethod) {
        final int previousMethodSerial = this.currentMethodSerial;
        this.currentMethodSerial = nextMethod;
        if (this.isProfiling) {
            this.getProfileData().profileEnter(nextMethod);
        }
        return previousMethodSerial;
    }
    
    public int profileExit(final int nextMethod, final long startTime) {
        final int previousMethodSerial = this.currentMethodSerial;
        this.currentMethodSerial = nextMethod;
        if (this.isProfiling) {
            this.getProfileData().profileExit(nextMethod, startTime);
        }
        return previousMethodSerial;
    }
    
    public void startProfiling() {
        this.isProfiling = true;
    }
    
    public void stopProfiling() {
        this.isProfiling = false;
    }
    
    public boolean isProfiling() {
        return this.isProfiling;
    }
    
    static {
        EMPTY_CATCHTARGET_STACK = new RubyContinuation.Continuation[0];
    }
}
