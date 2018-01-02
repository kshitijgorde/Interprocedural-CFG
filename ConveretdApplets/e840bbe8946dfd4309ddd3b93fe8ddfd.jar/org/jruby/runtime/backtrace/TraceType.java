// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.runtime.backtrace;

import org.jruby.RubyString;
import java.util.List;
import java.util.ArrayList;
import org.jruby.RubyClass;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.Ruby;
import org.jruby.RubyArray;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.ByteArrayOutputStream;
import org.jruby.RubyException;
import org.jruby.runtime.ThreadContext;

public class TraceType
{
    private final Gather gather;
    private final Format format;
    private static final String FIRST_COLOR = "\u001b[0;31m";
    private static final String KERNEL_COLOR = "\u001b[0;36m";
    private static final String EVAL_COLOR = "\u001b[0;33m";
    private static final String CLEAR_COLOR = "\u001b[0m";
    
    public TraceType(final Gather gather, final Format format) {
        this.gather = gather;
        this.format = format;
    }
    
    public BacktraceData getBacktrace(final ThreadContext context, final boolean nativeException) {
        return this.gather.getBacktraceData(context, nativeException);
    }
    
    public String printBacktrace(final RubyException exception) {
        return this.format.printBacktrace(exception);
    }
    
    public static TraceType traceTypeFor(final String style) {
        if (style.equalsIgnoreCase("raw")) {
            return new TraceType(Gather.RAW, Format.JRUBY);
        }
        if (style.equalsIgnoreCase("ruby_framed")) {
            return new TraceType(Gather.NORMAL, Format.JRUBY);
        }
        if (style.equalsIgnoreCase("rubinius")) {
            return new TraceType(Gather.NORMAL, Format.RUBINIUS);
        }
        if (style.equalsIgnoreCase("full")) {
            return new TraceType(Gather.FULL, Format.JRUBY);
        }
        if (style.equalsIgnoreCase("mri")) {
            return new TraceType(Gather.NORMAL, Format.MRI);
        }
        return new TraceType(Gather.NORMAL, Format.JRUBY);
    }
    
    protected static String printBacktraceMRI(final RubyException exception) {
        final Ruby runtime = exception.getRuntime();
        final ThreadContext context = runtime.getCurrentContext();
        final IRubyObject backtrace = exception.callMethod(context, "backtrace");
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final PrintStream errorStream = new PrintStream(baos);
        boolean printedPosition = false;
        if (backtrace.isNil() || !(backtrace instanceof RubyArray)) {
            if (context.getFile() != null && context.getFile().length() > 0) {
                errorStream.print(context.getFile() + ":" + context.getLine());
                printedPosition = true;
            }
            else {
                errorStream.print(context.getLine());
                printedPosition = true;
            }
        }
        else if (((RubyArray)backtrace).getLength() == 0) {
            printErrorPos(context, errorStream);
        }
        else {
            final IRubyObject mesg = ((RubyArray)backtrace).first();
            if (mesg.isNil()) {
                printErrorPos(context, errorStream);
            }
            else {
                errorStream.print(mesg);
                printedPosition = true;
            }
        }
        final RubyClass type = exception.getMetaClass();
        String info = exception.toString();
        if (printedPosition) {
            errorStream.print(": ");
        }
        if (type == runtime.getRuntimeError() && (info == null || info.length() == 0)) {
            errorStream.print(": unhandled exception\n");
        }
        else {
            String path = type.getName();
            if (info.length() == 0) {
                errorStream.print(path + '\n');
            }
            else {
                if (path.startsWith("#")) {
                    path = null;
                }
                String tail = null;
                if (info.indexOf("\n") != -1) {
                    tail = info.substring(info.indexOf("\n") + 1);
                    info = info.substring(0, info.indexOf("\n"));
                }
                errorStream.print(info);
                if (path != null) {
                    errorStream.print(" (" + path + ")\n");
                }
                if (tail != null) {
                    errorStream.print(tail + '\n');
                }
            }
        }
        exception.printBacktrace(errorStream);
        return new String(baos.toByteArray());
    }
    
    protected static String printBacktraceRubinius(final RubyException exception) {
        final Ruby runtime = exception.getRuntime();
        RubyStackTraceElement[] frames = exception.getBacktraceElements();
        if (frames == null) {
            frames = new RubyStackTraceElement[0];
        }
        final ArrayList firstParts = new ArrayList();
        int longestFirstPart = 0;
        for (final RubyStackTraceElement frame : frames) {
            final String firstPart = frame.getClassName() + "#" + frame.getMethodName();
            if (firstPart.length() > longestFirstPart) {
                longestFirstPart = firstPart.length();
            }
            firstParts.add(firstPart);
        }
        final int center = longestFirstPart + 2 + 1;
        final StringBuilder buffer = new StringBuilder();
        buffer.append("An exception has occurred:\n").append("    ");
        if (exception.getMetaClass() == runtime.getRuntimeError() && exception.message(runtime.getCurrentContext()).toString().length() == 0) {
            buffer.append("No current exception (RuntimeError)");
        }
        else {
            buffer.append(exception.message(runtime.getCurrentContext()).toString());
        }
        buffer.append('\n').append('\n').append("Backtrace:\n");
        int i = 0;
        for (final RubyStackTraceElement frame2 : frames) {
            final String firstPart2 = firstParts.get(i);
            final String secondPart = frame2.getFileName() + ":" + frame2.getLineNumber();
            if (i == 0) {
                buffer.append("\u001b[0;31m");
            }
            else if (frame2.isBinding() || frame2.getFileName().equals("(eval)")) {
                buffer.append("\u001b[0;33m");
            }
            else if (frame2.getFileName().indexOf(".java") != -1) {
                buffer.append("\u001b[0;36m");
            }
            buffer.append("  ");
            for (int j = 0; j < center - firstPart2.length(); ++j) {
                buffer.append(' ');
            }
            buffer.append(firstPart2);
            buffer.append(" at ");
            buffer.append(secondPart);
            buffer.append("\u001b[0m");
            buffer.append('\n');
            ++i;
        }
        return buffer.toString();
    }
    
    protected static String printBacktraceJRuby(final RubyException exception) {
        final Ruby runtime = exception.getRuntime();
        RubyStackTraceElement[] frames = exception.getBacktraceElements();
        if (frames == null) {
            frames = new RubyStackTraceElement[0];
        }
        int longestMethod = 0;
        for (final RubyStackTraceElement frame : frames) {
            longestMethod = Math.max(longestMethod, frame.getMethodName().length());
        }
        final StringBuilder buffer = new StringBuilder();
        String message = exception.message(runtime.getCurrentContext()).toString();
        if (exception.getMetaClass() == runtime.getRuntimeError() && message.length() == 0) {
            message = "No current exception";
        }
        buffer.append(exception.getMetaClass().getName()).append(": ").append(message).append('\n');
        for (final RubyStackTraceElement frame2 : frames) {
            buffer.append("  ");
            final String methodName = frame2.getMethodName();
            for (int j = 0; j < longestMethod - methodName.length(); ++j) {
                buffer.append(' ');
            }
            buffer.append(methodName).append(" at ").append(frame2.getFileName()).append(':').append(frame2.getLineNumber()).append('\n');
        }
        return buffer.toString();
    }
    
    protected static String printBacktraceJRuby2(final RubyException exception) {
        final Ruby runtime = exception.getRuntime();
        RubyStackTraceElement[] frames = exception.getBacktraceData().getBacktrace(runtime);
        if (frames == null) {
            frames = new RubyStackTraceElement[0];
        }
        final List<String> lineNumbers = new ArrayList<String>(frames.length);
        int longestFileName = 0;
        int longestLineNumber = 0;
        for (final RubyStackTraceElement frame : frames) {
            final String lineNumber = String.valueOf(frame.getLineNumber());
            lineNumbers.add(lineNumber);
            longestFileName = Math.max(longestFileName, frame.getFileName().length());
            longestLineNumber = Math.max(longestLineNumber, String.valueOf(frame.getLineNumber()).length());
        }
        final StringBuilder buffer = new StringBuilder();
        String message = exception.message(runtime.getCurrentContext()).toString();
        if (exception.getMetaClass() == runtime.getRuntimeError() && message.length() == 0) {
            message = "No current exception";
        }
        buffer.append(exception.getMetaClass().getName()).append(": ").append(message).append('\n');
        int i = 0;
        for (final RubyStackTraceElement frame2 : frames) {
            buffer.append("  ");
            final String fileName = frame2.getFileName();
            final String lineNumber2 = lineNumbers.get(i);
            for (int j = 0; j < longestFileName - fileName.length(); ++j) {
                buffer.append(' ');
            }
            buffer.append(fileName).append(":").append(lineNumber2);
            for (int l = 0; l < longestLineNumber - lineNumber2.length(); ++l) {
                buffer.append(' ');
            }
            buffer.append(' ').append("in ").append(frame2.getMethodName()).append('\n');
            ++i;
        }
        return buffer.toString();
    }
    
    public static IRubyObject generateMRIBacktrace(final Ruby runtime, final RubyStackTraceElement[] trace) {
        if (trace == null) {
            return runtime.getNil();
        }
        final RubyArray traceArray = RubyArray.newArray(runtime);
        for (int i = 0; i < trace.length; ++i) {
            final RubyStackTraceElement element = trace[i];
            final RubyString str = RubyString.newString(runtime, element.getFileName() + ":" + element.getLineNumber() + ":in `" + element.getMethodName() + "'");
            traceArray.append(str);
        }
        return traceArray;
    }
    
    protected static BacktraceData getBacktrace(final ThreadContext context, final Gather gather, final boolean nativeException, final boolean full, final boolean maskNative) {
        return new BacktraceData(Thread.currentThread().getStackTrace(), context.createBacktrace2(0, nativeException), full, maskNative, gather);
    }
    
    private static void printErrorPos(final ThreadContext context, final PrintStream errorStream) {
        if (context.getFile() != null && context.getFile().length() > 0) {
            if (context.getFrameName() != null) {
                errorStream.print(context.getFile() + ":" + context.getLine());
                errorStream.print(":in '" + context.getFrameName() + '\'');
            }
            else if (context.getLine() != 0) {
                errorStream.print(context.getFile() + ":" + context.getLine());
            }
            else {
                errorStream.print(context.getFile());
            }
        }
    }
    
    public enum Gather
    {
        RAW {
            public BacktraceData getBacktraceData(final ThreadContext context, final boolean nativeException) {
                return new BacktraceData(Thread.currentThread().getStackTrace(), new BacktraceElement[0], true, false, this);
            }
        }, 
        FULL {
            public BacktraceData getBacktraceData(final ThreadContext context, final boolean nativeException) {
                return new BacktraceData(Thread.currentThread().getStackTrace(), context.createBacktrace2(0, nativeException), true, false, this);
            }
        }, 
        NORMAL {
            public BacktraceData getBacktraceData(final ThreadContext context, final boolean nativeException) {
                final BacktraceData bd = new BacktraceData(Thread.currentThread().getStackTrace(), context.createBacktrace2(0, nativeException), false, false, this);
                return bd;
            }
        }, 
        CALLER {
            public BacktraceData getBacktraceData(final ThreadContext context, final boolean nativeException) {
                return new BacktraceData(Thread.currentThread().getStackTrace(), context.createBacktrace2(0, nativeException), false, true, this);
            }
        };
        
        public abstract BacktraceData getBacktraceData(final ThreadContext p0, final boolean p1);
    }
    
    public enum Format
    {
        MRI {
            public String printBacktrace(final RubyException exception) {
                return TraceType.printBacktraceMRI(exception);
            }
        }, 
        JRUBY {
            public String printBacktrace(final RubyException exception) {
                return TraceType.printBacktraceJRuby(exception);
            }
        }, 
        RUBINIUS {
            public String printBacktrace(final RubyException exception) {
                return TraceType.printBacktraceRubinius(exception);
            }
        };
        
        public abstract String printBacktrace(final RubyException p0);
    }
}
