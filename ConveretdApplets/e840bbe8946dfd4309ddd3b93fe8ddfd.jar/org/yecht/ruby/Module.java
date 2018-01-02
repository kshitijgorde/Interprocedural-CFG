// 
// Decompiled by Procyon v0.5.30
// 

package org.yecht.ruby;

import org.jruby.anno.JRubyMethod;
import org.jruby.RubyString;
import org.jruby.util.ByteList;
import org.yecht.Bytestring;
import org.yecht.ErrorHandler;
import org.yecht.NodeHandler;
import org.yecht.BytecodeNodeHandler;
import org.yecht.Parser;
import org.jruby.runtime.builtin.IRubyObject;

public class Module
{
    @JRubyMethod(name = { "compile" }, required = 1, module = true)
    public static IRubyObject compile(final IRubyObject self, final IRubyObject port) {
        final Parser parser = Parser.newParser();
        final boolean taint = YParser.assignIO(self.getRuntime(), parser, new IRubyObject[] { port });
        parser.handler(new BytecodeNodeHandler());
        parser.errorHandler(null);
        parser.implicitTyping(false);
        parser.taguriExpansion(false);
        final Bytestring sav = (Bytestring)parser.parse();
        final int len = Bytestring.strlen(sav.buffer);
        final ByteList bl = new ByteList(new byte[len + 2], false);
        bl.append(sav.buffer, 0, len);
        bl.append(68);
        bl.append(10);
        final IRubyObject iro = RubyString.newStringLight(self.getRuntime(), bl);
        if (taint) {
            iro.setTaint(true);
        }
        return iro;
    }
}
