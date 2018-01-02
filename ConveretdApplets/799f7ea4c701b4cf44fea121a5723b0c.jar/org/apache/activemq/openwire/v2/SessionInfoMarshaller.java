// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.openwire.v2;

import java.io.DataOutput;
import java.io.IOException;
import org.apache.activemq.command.SessionId;
import org.apache.activemq.openwire.BooleanStream;
import java.io.DataInput;
import org.apache.activemq.openwire.OpenWireFormat;
import org.apache.activemq.command.SessionInfo;
import org.apache.activemq.command.DataStructure;

public class SessionInfoMarshaller extends BaseCommandMarshaller
{
    @Override
    public byte getDataStructureType() {
        return 4;
    }
    
    @Override
    public DataStructure createObject() {
        return new SessionInfo();
    }
    
    @Override
    public void tightUnmarshal(final OpenWireFormat wireFormat, final Object o, final DataInput dataIn, final BooleanStream bs) throws IOException {
        super.tightUnmarshal(wireFormat, o, dataIn, bs);
        final SessionInfo info = (SessionInfo)o;
        info.setSessionId((SessionId)this.tightUnmarsalCachedObject(wireFormat, dataIn, bs));
    }
    
    @Override
    public int tightMarshal1(final OpenWireFormat wireFormat, final Object o, final BooleanStream bs) throws IOException {
        final SessionInfo info = (SessionInfo)o;
        int rc = super.tightMarshal1(wireFormat, o, bs);
        rc += this.tightMarshalCachedObject1(wireFormat, info.getSessionId(), bs);
        return rc + 0;
    }
    
    @Override
    public void tightMarshal2(final OpenWireFormat wireFormat, final Object o, final DataOutput dataOut, final BooleanStream bs) throws IOException {
        super.tightMarshal2(wireFormat, o, dataOut, bs);
        final SessionInfo info = (SessionInfo)o;
        this.tightMarshalCachedObject2(wireFormat, info.getSessionId(), dataOut, bs);
    }
    
    @Override
    public void looseUnmarshal(final OpenWireFormat wireFormat, final Object o, final DataInput dataIn) throws IOException {
        super.looseUnmarshal(wireFormat, o, dataIn);
        final SessionInfo info = (SessionInfo)o;
        info.setSessionId((SessionId)this.looseUnmarsalCachedObject(wireFormat, dataIn));
    }
    
    @Override
    public void looseMarshal(final OpenWireFormat wireFormat, final Object o, final DataOutput dataOut) throws IOException {
        final SessionInfo info = (SessionInfo)o;
        super.looseMarshal(wireFormat, o, dataOut);
        this.looseMarshalCachedObject(wireFormat, info.getSessionId(), dataOut);
    }
}
