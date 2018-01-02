// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.openwire.v3;

import java.io.DataOutput;
import java.io.IOException;
import org.apache.activemq.openwire.BooleanStream;
import java.io.DataInput;
import org.apache.activemq.openwire.OpenWireFormat;
import org.apache.activemq.command.DataResponse;
import org.apache.activemq.command.DataStructure;

public class DataResponseMarshaller extends ResponseMarshaller
{
    @Override
    public byte getDataStructureType() {
        return 32;
    }
    
    @Override
    public DataStructure createObject() {
        return new DataResponse();
    }
    
    @Override
    public void tightUnmarshal(final OpenWireFormat wireFormat, final Object o, final DataInput dataIn, final BooleanStream bs) throws IOException {
        super.tightUnmarshal(wireFormat, o, dataIn, bs);
        final DataResponse info = (DataResponse)o;
        info.setData(this.tightUnmarsalNestedObject(wireFormat, dataIn, bs));
    }
    
    @Override
    public int tightMarshal1(final OpenWireFormat wireFormat, final Object o, final BooleanStream bs) throws IOException {
        final DataResponse info = (DataResponse)o;
        int rc = super.tightMarshal1(wireFormat, o, bs);
        rc += this.tightMarshalNestedObject1(wireFormat, info.getData(), bs);
        return rc + 0;
    }
    
    @Override
    public void tightMarshal2(final OpenWireFormat wireFormat, final Object o, final DataOutput dataOut, final BooleanStream bs) throws IOException {
        super.tightMarshal2(wireFormat, o, dataOut, bs);
        final DataResponse info = (DataResponse)o;
        this.tightMarshalNestedObject2(wireFormat, info.getData(), dataOut, bs);
    }
    
    @Override
    public void looseUnmarshal(final OpenWireFormat wireFormat, final Object o, final DataInput dataIn) throws IOException {
        super.looseUnmarshal(wireFormat, o, dataIn);
        final DataResponse info = (DataResponse)o;
        info.setData(this.looseUnmarsalNestedObject(wireFormat, dataIn));
    }
    
    @Override
    public void looseMarshal(final OpenWireFormat wireFormat, final Object o, final DataOutput dataOut) throws IOException {
        final DataResponse info = (DataResponse)o;
        super.looseMarshal(wireFormat, o, dataOut);
        this.looseMarshalNestedObject(wireFormat, info.getData(), dataOut);
    }
}
