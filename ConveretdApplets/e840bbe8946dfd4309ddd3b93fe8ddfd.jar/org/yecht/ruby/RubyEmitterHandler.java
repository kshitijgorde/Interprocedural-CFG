// 
// Decompiled by Procyon v0.5.30
// 

package org.yecht.ruby;

import org.yecht.KindTag;
import org.yecht.MapPart;
import org.yecht.Data;
import org.jruby.runtime.builtin.IRubyObject;
import org.yecht.Node;
import org.yecht.Emitter;
import org.jruby.Ruby;
import org.yecht.EmitterHandler;

public class RubyEmitterHandler implements EmitterHandler
{
    private Ruby runtime;
    
    public RubyEmitterHandler(final Ruby runtime) {
        this.runtime = runtime;
    }
    
    public void handle(final Emitter e, final Object data) {
        final Node n = (Node)((IRubyObject)data).dataGetStructChecked();
        switch (n.kind) {
            case Map: {
                final Data.Map dm = (Data.Map)n.data;
                e.emitMap(n.type_id, dm.style);
                for (int i = 0; i < dm.idx; ++i) {
                    e.emitItem(n.mapRead(MapPart.Key, i));
                    e.emitItem(n.mapRead(MapPart.Value, i));
                }
                e.emitEnd();
                break;
            }
            case Seq: {
                final Data.Seq ds = (Data.Seq)n.data;
                e.emitSeq(n.type_id, ds.style);
                for (int j = 0; j < ds.idx; ++j) {
                    e.emitItem(n.seqRead(j));
                }
                e.emitEnd();
                break;
            }
            case Str: {
                final Data.Str dss = (Data.Str)n.data;
                e.emitScalar(n.type_id, dss.style, 0, 0, 0, dss.ptr, dss.len);
                break;
            }
        }
    }
}
