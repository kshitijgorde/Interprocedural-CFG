// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext;

import org.jruby.RubyClass;
import org.jruby.exceptions.RaiseException;
import com.kenai.constantine.platform.Errno;
import org.jruby.Ruby;

public class JRubyPOSIXHelper
{
    public static void checkErrno(final Ruby runtime, final int result) {
        if (result < 0) {
            final Errno errno = Errno.valueOf(runtime.getPosix().errno());
            final String name = errno.name();
            final String msg = errno.toString();
            final RubyClass errnoClass = runtime.getErrno().fastGetClass(name);
            if (errnoClass != null) {
                throw new RaiseException(runtime, errnoClass, msg, true);
            }
        }
    }
}
