// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.runtime.marshal.UnmarshalStream;
import java.util.List;
import org.jruby.runtime.builtin.Variable;
import org.jruby.runtime.component.VariableEntry;
import org.jruby.runtime.marshal.MarshalStream;
import java.io.IOException;
import java.util.HashMap;
import org.jruby.runtime.Visibility;
import org.jruby.anno.JRubyMethod;
import org.jruby.platform.Platform;
import com.kenai.constantine.platform.Errno;
import org.jruby.runtime.Arity;
import org.jruby.runtime.Block;
import org.jruby.runtime.ObjectMarshal;
import org.jruby.runtime.ObjectAllocator;
import java.util.Map;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.anno.JRubyClass;

@JRubyClass(name = { "SystemCallError" }, parent = "StandardError")
public class RubySystemCallError extends RubyException
{
    private IRubyObject errno;
    private static final Map<String, String> defaultMessages;
    private static ObjectAllocator SYSTEM_CALL_ERROR_ALLOCATOR;
    private static final ObjectMarshal SYSTEM_CALL_ERROR_MARSHAL;
    
    protected RubySystemCallError(final Ruby runtime, final RubyClass rubyClass) {
        super(runtime, rubyClass, null);
        this.errno = this.getRuntime().getNil();
    }
    
    public RubySystemCallError(final Ruby runtime, final RubyClass rubyClass, final String message, final int errno) {
        super(runtime, rubyClass, message);
        this.errno = this.getRuntime().getNil();
        this.errno = runtime.newFixnum(errno);
    }
    
    public static RubyClass createSystemCallErrorClass(final Ruby runtime, final RubyClass standardError) {
        final RubyClass exceptionClass = runtime.defineClass("SystemCallError", standardError, RubySystemCallError.SYSTEM_CALL_ERROR_ALLOCATOR);
        exceptionClass.setMarshal(RubySystemCallError.SYSTEM_CALL_ERROR_MARSHAL);
        runtime.callbackFactory(RubyClass.class);
        exceptionClass.defineAnnotatedMethods(RubySystemCallError.class);
        return exceptionClass;
    }
    
    @JRubyMethod(optional = 2, required = 0, visibility = Visibility.PRIVATE)
    public IRubyObject initialize(final IRubyObject[] args, final Block block) {
        final Ruby runtime = this.getRuntime();
        final RubyClass sCallErorrClass = runtime.getSystemCallError();
        RubyClass klass = this.getMetaClass().getRealClass();
        IRubyObject msg = runtime.getNil();
        IRubyObject err = runtime.getNil();
        boolean isErrnoClass = !klass.equals(sCallErorrClass);
        if (!isErrnoClass) {
            Arity.checkArgumentCount(runtime, args, 1, 2);
            msg = args[0];
            if (args.length == 2) {
                err = args[1];
            }
            if (args.length == 1 && msg instanceof RubyFixnum) {
                err = msg;
                msg = runtime.getNil();
            }
        }
        else {
            Arity.checkArgumentCount(runtime, args, 0, 1);
            if (args.length == 1) {
                msg = args[0];
            }
            err = klass.fastGetConstant("Errno");
        }
        String val = null;
        if (!err.isNil()) {
            this.errno = err.convertToInteger();
            final int errnoVal = RubyNumeric.num2int(this.errno);
            if (Errno.valueOf(errnoVal) != Errno.__UNKNOWN_CONSTANT__) {
                isErrnoClass = true;
                this.setMetaClass(runtime.getErrno(errnoVal));
                klass = this.getMetaClass().getRealClass();
                if (!Platform.IS_WINDOWS) {
                    val = Errno.valueOf(errnoVal).description();
                }
            }
        }
        if (val == null) {
            val = RubySystemCallError.defaultMessages.get(klass.getName());
            if (val == null) {
                val = "Unknown error";
            }
        }
        if (!this.errno.isNil() && !isErrnoClass) {
            val = val + " " + this.errno.toString();
        }
        if (!msg.isNil()) {
            val = val + " - " + msg.convertToString();
        }
        this.message = runtime.newString(val);
        return this;
    }
    
    @JRubyMethod
    public IRubyObject errno() {
        return this.errno;
    }
    
    static {
        (defaultMessages = new HashMap<String, String>()).put("Errno::EPERM", "Operation not permitted");
        RubySystemCallError.defaultMessages.put("Errno::ENOENT", "No such file or directory");
        RubySystemCallError.defaultMessages.put("Errno::ESRCH", "No such process");
        RubySystemCallError.defaultMessages.put("Errno::EINTR", "Interrupted system call");
        RubySystemCallError.defaultMessages.put("Errno::EIO", "Input/output error");
        RubySystemCallError.defaultMessages.put("Errno::ENXIO", "Device not configured");
        RubySystemCallError.defaultMessages.put("Errno::E2BIG", "Argument list too long");
        RubySystemCallError.defaultMessages.put("Errno::ENOEXEC", "Exec format error");
        RubySystemCallError.defaultMessages.put("Errno::EBADF", "Bad file descriptor");
        RubySystemCallError.defaultMessages.put("Errno::ECHILD", "No child processes");
        RubySystemCallError.defaultMessages.put("Errno::EDEADLK", "Resource deadlock avoided");
        RubySystemCallError.defaultMessages.put("Errno::ENOMEM", "Cannot allocate memory");
        RubySystemCallError.defaultMessages.put("Errno::EACCES", "Permission denied");
        RubySystemCallError.defaultMessages.put("Errno::EFAULT", "Bad address");
        RubySystemCallError.defaultMessages.put("Errno::ENOTBLK", "Block device required");
        RubySystemCallError.defaultMessages.put("Errno::EBUSY", "Resource busy");
        RubySystemCallError.defaultMessages.put("Errno::EEXIST", "File exists");
        RubySystemCallError.defaultMessages.put("Errno::EXDEV", "Cross-device link");
        RubySystemCallError.defaultMessages.put("Errno::ENODEV", "Operation not supported by device");
        RubySystemCallError.defaultMessages.put("Errno::ENOTDIR", "Not a directory");
        RubySystemCallError.defaultMessages.put("Errno::EISDIR", "Is a directory");
        RubySystemCallError.defaultMessages.put("Errno::EINVAL", "Invalid argument");
        RubySystemCallError.defaultMessages.put("Errno::ENFILE", "Too many open files in system");
        RubySystemCallError.defaultMessages.put("Errno::EMFILE", "Too many open files");
        RubySystemCallError.defaultMessages.put("Errno::ENOTTY", "Inappropriate ioctl for device");
        RubySystemCallError.defaultMessages.put("Errno::ETXTBSY", "Text file busy");
        RubySystemCallError.defaultMessages.put("Errno::EFBIG", "File too large");
        RubySystemCallError.defaultMessages.put("Errno::ENOSPC", "No space left on device");
        RubySystemCallError.defaultMessages.put("Errno::ESPIPE", "Illegal seek");
        RubySystemCallError.defaultMessages.put("Errno::EROFS", "Read-only file system");
        RubySystemCallError.defaultMessages.put("Errno::EMLINK", "Too many links");
        RubySystemCallError.defaultMessages.put("Errno::EPIPE", "Broken pipe");
        RubySystemCallError.defaultMessages.put("Errno::EDOM", "Numerical argument out of domain");
        RubySystemCallError.defaultMessages.put("Errno::ERANGE", "Result too large");
        RubySystemCallError.defaultMessages.put("Errno::EAGAIN", "Resource temporarily unavailable");
        RubySystemCallError.defaultMessages.put("Errno::EWOULDBLOCK", "Resource temporarily unavailable");
        RubySystemCallError.defaultMessages.put("Errno::EINPROGRESS", "Operation now in progress");
        RubySystemCallError.defaultMessages.put("Errno::EALREADY", "Operation already in progress");
        RubySystemCallError.defaultMessages.put("Errno::ENOTSOCK", "Socket operation on non-socket");
        RubySystemCallError.defaultMessages.put("Errno::EDESTADDRREQ", "Destination address required");
        RubySystemCallError.defaultMessages.put("Errno::EMSGSIZE", "Message too long");
        RubySystemCallError.defaultMessages.put("Errno::EPROTOTYPE", "Protocol wrong type for socket");
        RubySystemCallError.defaultMessages.put("Errno::ENOPROTOOPT", "Protocol not available");
        RubySystemCallError.defaultMessages.put("Errno::EPROTONOSUPPORT", "Protocol not supported");
        RubySystemCallError.defaultMessages.put("Errno::ESOCKTNOSUPPORT", "Socket type not supported");
        RubySystemCallError.defaultMessages.put("Errno::EPFNOSUPPORT", "Protocol family not supported");
        RubySystemCallError.defaultMessages.put("Errno::EAFNOSUPPORT", "Address family not supported by protocol family");
        RubySystemCallError.defaultMessages.put("Errno::EADDRINUSE", "Address already in use");
        RubySystemCallError.defaultMessages.put("Errno::EADDRNOTAVAIL", "Can't assign requested address");
        RubySystemCallError.defaultMessages.put("Errno::ENETDOWN", "Network is down");
        RubySystemCallError.defaultMessages.put("Errno::ENETUNREACH", "Network is unreachable");
        RubySystemCallError.defaultMessages.put("Errno::ENETRESET", "Network dropped connection on reset");
        RubySystemCallError.defaultMessages.put("Errno::ECONNABORTED", "Software caused connection abort");
        RubySystemCallError.defaultMessages.put("Errno::ECONNRESET", "Connection reset by peer");
        RubySystemCallError.defaultMessages.put("Errno::ENOBUFS", "No buffer space available");
        RubySystemCallError.defaultMessages.put("Errno::EISCONN", "Socket is already connected");
        RubySystemCallError.defaultMessages.put("Errno::ENOTCONN", "Socket is not connected");
        RubySystemCallError.defaultMessages.put("Errno::ESHUTDOWN", "Can't send after socket shutdown");
        RubySystemCallError.defaultMessages.put("Errno::ETOOMANYREFS", "Too many references: can't splice");
        RubySystemCallError.defaultMessages.put("Errno::ETIMEDOUT", "Operation timed out");
        RubySystemCallError.defaultMessages.put("Errno::ECONNREFUSED", "Connection refused");
        RubySystemCallError.defaultMessages.put("Errno::ELOOP", "Too many levels of symbolic links");
        RubySystemCallError.defaultMessages.put("Errno::ENAMETOOLONG", "File name too long");
        RubySystemCallError.defaultMessages.put("Errno::EHOSTDOWN", "Host is down");
        RubySystemCallError.defaultMessages.put("Errno::EHOSTUNREACH", "No route to host");
        RubySystemCallError.defaultMessages.put("Errno::ENOTEMPTY", "Directory not empty");
        RubySystemCallError.defaultMessages.put("Errno::EUSERS", "Too many users");
        RubySystemCallError.defaultMessages.put("Errno::EDQUOT", "Disc quota exceeded");
        RubySystemCallError.defaultMessages.put("Errno::ESTALE", "Stale NFS file handle");
        RubySystemCallError.defaultMessages.put("Errno::EREMOTE", "Too many levels of remote in path");
        RubySystemCallError.defaultMessages.put("Errno::ENOLCK", "No locks available");
        RubySystemCallError.defaultMessages.put("Errno::ENOSYS", "Function not implemented");
        RubySystemCallError.defaultMessages.put("Errno::EOVERFLOW", "Value too large to be stored in data type");
        RubySystemCallError.defaultMessages.put("Errno::EIDRM", "Identifier removed");
        RubySystemCallError.defaultMessages.put("Errno::ENOMSG", "No message of desired type");
        RubySystemCallError.defaultMessages.put("Errno::EILSEQ", "Illegal byte sequence");
        RubySystemCallError.defaultMessages.put("Errno::EBADMSG", "Bad message");
        RubySystemCallError.defaultMessages.put("Errno::EMULTIHOP", "EMULTIHOP (Reserved)");
        RubySystemCallError.defaultMessages.put("Errno::ENODATA", "No message available on STREAM");
        RubySystemCallError.defaultMessages.put("Errno::ENOLINK", "ENOLINK (Reserved)");
        RubySystemCallError.defaultMessages.put("Errno::ENOSR", "No STREAM resources");
        RubySystemCallError.defaultMessages.put("Errno::ENOSTR", "Not a STREAM");
        RubySystemCallError.defaultMessages.put("Errno::EPROTO", "Protocol error");
        RubySystemCallError.defaultMessages.put("Errno::ETIME", "STREAM ioctl timeout");
        RubySystemCallError.defaultMessages.put("Errno::EOPNOTSUPP", "Operation not supported");
        RubySystemCallError.defaultMessages.put("Errno::EOPNOTSUPP_DARWIN", "Operation not supported");
        RubySystemCallError.SYSTEM_CALL_ERROR_ALLOCATOR = new ObjectAllocator() {
            public IRubyObject allocate(final Ruby runtime, final RubyClass klass) {
                final RubyException instance = new RubySystemCallError(runtime, klass);
                instance.setMetaClass(klass);
                return instance;
            }
        };
        SYSTEM_CALL_ERROR_MARSHAL = new ObjectMarshal() {
            public void marshalTo(final Ruby runtime, final Object obj, final RubyClass type, final MarshalStream marshalStream) throws IOException {
                final RubySystemCallError exc = (RubySystemCallError)obj;
                marshalStream.registerLinkTarget(exc);
                final List<Variable<Object>> attrs = exc.getVariableList();
                attrs.add(new VariableEntry<Object>("mesg", (exc.message == null) ? runtime.getNil() : exc.message));
                attrs.add((Variable<IRubyObject>)new VariableEntry<IRubyObject>("errno", exc.errno));
                attrs.add((Variable<IRubyObject>)new VariableEntry<IRubyObject>("bt", exc.getBacktrace()));
                marshalStream.dumpVariables(attrs);
            }
            
            public Object unmarshalFrom(final Ruby runtime, final RubyClass type, final UnmarshalStream unmarshalStream) throws IOException {
                final RubySystemCallError exc = (RubySystemCallError)type.allocate();
                unmarshalStream.registerLinkTarget(exc);
                unmarshalStream.defaultVariablesUnmarshal(exc);
                exc.message = (IRubyObject)exc.removeInternalVariable("mesg");
                exc.errno = (IRubyObject)exc.removeInternalVariable("errno");
                exc.set_backtrace((IRubyObject)exc.removeInternalVariable("bt"));
                return exc;
            }
        };
    }
}
