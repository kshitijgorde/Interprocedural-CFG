// 
// Decompiled by Procyon v0.5.30
// 

package powersoft.powerj.util;

import java.util.Date;
import java.io.OutputStream;
import java.util.Hashtable;
import java.net.Socket;
import java.io.PrintStream;

public final class Debug implements Runnable
{
    public static final byte LOG_NONE = 0;
    public static final byte LOG_GENERAL = 1;
    public static final byte LOG_GROUPS = 2;
    public static final byte LOG_ALL = 3;
    public static final byte LOG_FORCEALL = 4;
    protected static byte _log = 3;
    protected static PrintStream _logStream;
    protected static boolean _detailedLog = false;
    protected static boolean _firstLog = true;
    protected static String _socketHost;
    protected static int _socketPort = 7777;
    protected static Socket _socket;
    protected static DebugPrintStream _socketStream;
    protected static boolean _streamToSocket = true;
    protected static boolean _streamToErr = false;
    protected static Hashtable _groups;
    protected static Throwable _socketException;
    protected static boolean _tryOnThread = true;
    
    public static final boolean getDetailedLog() {
        return Debug._detailedLog;
    }
    
    public static final void setDetailedLog(final boolean detailedLog) {
        Debug._detailedLog = detailedLog;
    }
    
    public static final boolean getGroupLog(final String group) {
        boolean ok = false;
        if (group != null) {
            ok = (Debug._groups.get(group) != null);
        }
        return ok;
    }
    
    public static final void setGroupLog(final String group, final boolean log) {
        if (group != null) {
            if (log) {
                Debug._groups.put(group, group);
            }
            else {
                Debug._groups.remove(group);
            }
        }
    }
    
    public static final String getLogURL() {
        if (Debug._socketHost == null) {
            Debug._socketHost = "localhost";
        }
        return String.valueOf(Debug._socketHost) + ":" + String.valueOf(Debug._socketPort);
    }
    
    public static final void setLogURL(String url) {
        if (url == null) {
            Debug._streamToSocket = false;
            return;
        }
        Debug._streamToSocket = true;
        url = url.trim();
        Debug._socketHost = "localhost";
        Debug._socketPort = 7777;
        final int index = url.indexOf(58);
        if (index < 0) {
            if (url.length() > 0) {
                Debug._socketHost = url;
            }
        }
        else if (index == 0) {
            if (url.length() > 1) {
                Debug._socketPort = Integer.parseInt(url.substring(1));
            }
        }
        else {
            Debug._socketHost = url.substring(0, index);
            if (url.length() >= index) {
                Debug._socketPort = Integer.parseInt(url.substring(index + 1));
            }
        }
    }
    
    public static final byte getLogMode() {
        return Debug._log;
    }
    
    public static final void setLogMode(final byte log) {
        Debug._log = log;
    }
    
    public static final PrintStream getLogStream() {
        firstLog();
        return Debug._logStream;
    }
    
    public static final void setLogStream(final PrintStream stream) {
        Debug._logStream = stream;
    }
    
    public static final boolean getLogToSocket() {
        return Debug._streamToSocket;
    }
    
    public static final void setLogToSocket(final boolean log) {
        if (log != Debug._streamToSocket) {
            close();
            Debug._streamToSocket = log;
        }
    }
    
    public static final boolean getLogToError() {
        return Debug._streamToErr;
    }
    
    public static final void setLogToError(final boolean log) {
        if (log != Debug._streamToErr) {
            close();
            Debug._streamToErr = log;
        }
    }
    
    public static final void assert(final boolean cond) {
        if (!cond) {
            abort(">> Debug.assert failed");
        }
    }
    
    public static final void assert(final boolean cond, final String msg) {
        if (!cond) {
            abort(">> Debug.assert " + msg + " failed");
        }
    }
    
    public static final void verify(final boolean cond) {
        if (!cond) {
            abort(">> Debug.Verify failed");
        }
    }
    
    public static final void verify(final boolean cond, final String msg) {
        if (!cond) {
            abort(">> Debug.Verify " + msg + " (run-time assertion) failed");
        }
    }
    
    public static final void checkArgument(final boolean cond) {
        checkArgument(cond, null);
    }
    
    public static final void checkArgument(final boolean cond, final String msg) {
        if (cond) {
            return;
        }
        logSystem("Debug.checkArgument failed:", msg, "throwing IllegalArgumentException");
        throw new IllegalArgumentException(msg);
    }
    
    public static final void checkState(final boolean cond) {
        checkState(cond, null);
    }
    
    public static final void checkState(final boolean cond, final String msg) {
        if (cond) {
            return;
        }
        logSystem("Debug.checkState failed:", msg, "throwing InvalidStateException");
        throw new InvalidStateException(msg);
    }
    
    public static final void checkCreated(final boolean cond) {
        checkCreated(cond, null);
    }
    
    public static final void checkCreated(final boolean cond, final String msg) {
        if (cond) {
            return;
        }
        logSystem("Debug.checkCreated failed:", msg, "throwing NotCreatedException");
        throw new NotCreatedException(msg);
    }
    
    public static final void abort() {
        abort((String)null);
    }
    
    public static final void abort(final Throwable err) {
        abort(err.toString());
    }
    
    public static final void abort(String msg) {
        if (msg == null) {
            msg = ">> Debug.abort: ";
        }
        else if (!msg.startsWith(">> ")) {
            msg = ">> Debug.abort: " + msg;
        }
        log(String.valueOf(msg) + " (thread: " + Thread.currentThread().getName() + ")");
    }
    
    public static final void close() {
        if (Debug._logStream != null) {
            Debug._logStream.flush();
            Debug._logStream.close();
            Debug._logStream = null;
        }
        Debug._socketStream = null;
        if (Debug._socket != null) {
            try {
                Debug._socket.close();
            }
            catch (Exception ex) {}
            Debug._socket = null;
        }
        Debug._firstLog = true;
    }
    
    public static final void printStackTrace() {
        if (!firstLog()) {
            return;
        }
        printStackTrace(Debug._logStream);
    }
    
    public static final void printStackTrace(final Throwable t) {
        if (!firstLog()) {
            return;
        }
        printStackTrace(t, Debug._logStream);
    }
    
    public static final void printStackTrace(final PrintStream stream) {
        printStackTrace(new Throwable("printStackTrace"), stream);
    }
    
    public static final void printStackTrace(final Throwable t, final PrintStream stream) {
        synchronized (stream) {
            if (stream instanceof DebugPrintStream) {
                ((DebugPrintStream)stream).writeEscapeCode(false);
            }
            t.printStackTrace(stream);
            if (stream instanceof DebugPrintStream) {
                ((DebugPrintStream)stream).writeEscapeCode(true);
            }
            stream.println("\t[end of trace]");
            stream.flush();
        }
    }
    
    public static final synchronized void log(final String message) {
        if (!testLog() || !firstLog()) {
            return;
        }
        synchronized (Debug._logStream) {
            Debug._logStream.println(message);
            if (Debug._detailedLog || message.startsWith(">> ")) {
                printStackTrace();
            }
            Debug._logStream.flush();
        }
        // monitorexit(Debug._logStream)
    }
    
    public static final synchronized void log(final byte[] message) {
        if (!testLog() || !firstLog()) {
            return;
        }
        synchronized (Debug._logStream) {
            try {
                Debug._logStream.write(message);
            }
            catch (Exception ex) {}
            Debug._logStream.println();
            if (Debug._detailedLog) {
                printStackTrace();
            }
            Debug._logStream.flush();
        }
        // monitorexit(Debug._logStream)
    }
    
    public static final synchronized void log(final Throwable err) {
        if (!testLog() || !firstLog()) {
            return;
        }
        synchronized (Debug._logStream) {
            Debug._logStream.println(">> Exception stack trace follows");
            printStackTrace(err);
            Debug._logStream.flush();
        }
        // monitorexit(Debug._logStream)
    }
    
    public static final synchronized void log(final String group, final String message) {
        if (!testGroupLog(group) || !firstLog()) {
            return;
        }
        synchronized (Debug._logStream) {
            Debug._logStream.print(group);
            Debug._logStream.print(": ");
            Debug._logStream.println(message);
            if (Debug._detailedLog) {
                printStackTrace();
            }
            Debug._logStream.flush();
        }
        // monitorexit(Debug._logStream)
    }
    
    public static final synchronized void error(final String group, final String message) {
        if (!testGroupLog(group) || !firstLog()) {
            return;
        }
        synchronized (Debug._logStream) {
            Debug._logStream.print(">> ");
            Debug._logStream.print(group);
            Debug._logStream.print(": ");
            Debug._logStream.println(message);
            if (Debug._detailedLog) {
                printStackTrace();
            }
            Debug._logStream.flush();
        }
        // monitorexit(Debug._logStream)
    }
    
    public static final synchronized void log(final String group, final byte[] message) {
        if (!testGroupLog(group) || !firstLog()) {
            return;
        }
        synchronized (Debug._logStream) {
            Debug._logStream.print(group);
            Debug._logStream.print(": ");
            try {
                Debug._logStream.write(message);
            }
            catch (Exception ex) {}
            Debug._logStream.println();
            if (Debug._detailedLog) {
                printStackTrace();
            }
            Debug._logStream.flush();
        }
        // monitorexit(Debug._logStream)
    }
    
    public static final synchronized void log(final String group, final Throwable err) {
        if (!testGroupLog(group) || !firstLog()) {
            return;
        }
        synchronized (Debug._logStream) {
            Debug._logStream.print(">> ");
            Debug._logStream.print(group);
            Debug._logStream.print(": ");
            Debug._logStream.println("Exception stack trace follows");
            printStackTrace(err);
            Debug._logStream.flush();
        }
        // monitorexit(Debug._logStream)
    }
    
    public static final synchronized void logThreadInfo(ThreadGroup group) {
        if (!firstLog()) {
            return;
        }
        if (group == null) {
            group = Thread.currentThread().getThreadGroup();
        }
        final int estimatedThreadCount = group.activeCount();
        final int estimatedGroupCount = group.activeGroupCount();
        final Thread[] threads = new Thread[estimatedThreadCount];
        final ThreadGroup[] groups = new ThreadGroup[estimatedGroupCount];
        final int threadCount = group.enumerate(threads, false);
        final int groupCount = group.enumerate(groups, false);
        synchronized (Debug._logStream) {
            Debug._logStream.println("Thread group: " + group.getName() + ", Max Priority=" + group.getMaxPriority() + (group.isDaemon() ? ", Daemon" : ""));
            for (final Thread t : threads) {
                Debug._logStream.println("  Thread: " + t.getName() + ", Priority=" + t.getPriority() + (t.isDaemon() ? ", Daemon" : "") + (t.isAlive() ? "" : ", Not Alive"));
            }
            for (int i = 0; i < groupCount; ++i) {
                Debug._logStream.println("  Group: " + groups[i].getName());
            }
            Debug._logStream.flush();
        }
        // monitorexit(Debug._logStream)
    }
    
    protected static final boolean testLog() {
        return Debug._log == 1 || Debug._log == 3 || Debug._log == 4;
    }
    
    protected static final boolean testGroupLog(final String group) {
        return Debug._log == 4 || ((Debug._log == 2 || Debug._log == 3) && Debug._groups.get(group) != null);
    }
    
    public final void run() {
        try {
            Debug._socket = new Socket(Debug._socketHost, Debug._socketPort);
        }
        catch (Exception e) {
            Debug._socketException = e;
        }
    }
    
    protected static final synchronized boolean firstLog() {
        if (!Debug._streamToSocket && !Debug._streamToErr) {
            return false;
        }
        if (Debug._logStream == null) {
            if (Debug._streamToSocket && Debug._socket == null) {
                if (Debug._socketHost == null) {
                    Debug._socketHost = "localhost";
                }
                try {
                    System.err.println("Debug log connecting to " + Debug._socketHost + ":" + Debug._socketPort);
                    if (Debug._tryOnThread) {
                        try {
                            Debug._socket = new Socket(Debug._socketHost, Debug._socketPort);
                        }
                        catch (Throwable e) {
                            final Debug d = new Debug();
                            final Thread t = new Thread(d);
                            Debug._socketException = null;
                            t.start();
                            try {
                                t.join();
                            }
                            catch (Exception ex) {}
                            if (Debug._socketException != null) {
                                final Throwable se = Debug._socketException;
                                Debug._socketException = null;
                                throw se;
                            }
                        }
                    }
                    else {
                        Debug._socket = new Socket(Debug._socketHost, Debug._socketPort);
                    }
                }
                catch (Throwable e) {
                    System.err.println("Exception trying to connect to debug log: " + e);
                    Debug._socket = null;
                }
                if (Debug._socket != null) {
                    try {
                        (Debug._socketStream = new DebugPrintStream(Debug._socket.getOutputStream())).writeEscapeCode(true);
                    }
                    catch (Exception e2) {
                        Debug._socket = null;
                        Debug._socketStream = null;
                        Debug._streamToSocket = false;
                        Debug._streamToErr = true;
                        System.err.println("Exception trying to write to debug log: " + e2);
                    }
                }
            }
            if (Debug._socketStream != null && Debug._streamToSocket && Debug._streamToErr && Debug._socketStream != System.err) {
                Debug._logStream = new PrintStream(new TeeStream(Debug._socketStream, System.err));
            }
            else if (Debug._socketStream != null && Debug._streamToSocket) {
                Debug._logStream = Debug._socketStream;
            }
            else if (Debug._streamToErr) {
                Debug._logStream = System.err;
            }
        }
        if (Debug._logStream == null) {
            System.err.println("Logging to System.err");
            Debug._logStream = System.err;
        }
        if (Debug._firstLog) {
            final Date d2 = new Date();
            Debug._firstLog = false;
            synchronized (Debug._logStream) {
                Debug._logStream.println("-- Logging started " + d2.toLocaleString());
                Debug._logStream.println("PowerJ component library (PJClass) version " + 3 + "." + 0 + "." + 58 + " (debug)");
                Debug._logStream.flush();
            }
            // monitorexit(Debug._logStream)
        }
        return true;
    }
    
    protected static final void logSystem(final String prefix, final String msg, final String suffix) {
        final Thread t = Thread.currentThread();
        String s = ">> " + prefix + " ";
        if (msg != null) {
            s = String.valueOf(s) + msg;
            if (suffix != null) {
                s = String.valueOf(s) + ", ";
            }
        }
        if (suffix != null) {
            s = String.valueOf(s) + suffix + " ";
        }
        s = String.valueOf(s) + "(thread: " + t.getName() + ")";
        log(s);
    }
    
    static {
        Debug._logStream = null;
        Debug._socketHost = null;
        Debug._socket = null;
        Debug._socketStream = null;
        Debug._groups = new Hashtable();
        Debug._socketException = null;
    }
}
