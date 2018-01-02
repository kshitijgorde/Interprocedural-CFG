// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.runtime;

import org.jruby.runtime.marshal.UnmarshalStream;
import java.io.IOException;
import org.jruby.runtime.marshal.MarshalStream;
import org.jruby.RubyClass;
import org.jruby.Ruby;

public interface ObjectMarshal
{
    public static final ObjectMarshal NOT_MARSHALABLE_MARSHAL = new ObjectMarshal() {
        public void marshalTo(final Ruby runtime, final Object obj, final RubyClass type, final MarshalStream marshalStream) throws IOException {
            throw runtime.newTypeError("no marshal_dump is defined for class " + type.getName());
        }
        
        public Object unmarshalFrom(final Ruby runtime, final RubyClass type, final UnmarshalStream unmarshalStream) throws IOException {
            throw runtime.newTypeError("no marshal_load is defined for class " + type.getName());
        }
    };
    
    void marshalTo(final Ruby p0, final Object p1, final RubyClass p2, final MarshalStream p3) throws IOException;
    
    Object unmarshalFrom(final Ruby p0, final RubyClass p1, final UnmarshalStream p2) throws IOException;
}
