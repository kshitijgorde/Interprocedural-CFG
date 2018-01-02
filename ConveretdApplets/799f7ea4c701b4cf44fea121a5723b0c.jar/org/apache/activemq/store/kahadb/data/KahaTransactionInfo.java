// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.store.kahadb.data;

import java.io.InputStream;
import org.apache.activemq.protobuf.Buffer;
import org.apache.activemq.protobuf.InvalidProtocolBufferException;
import org.apache.activemq.protobuf.CodedOutputStream;
import java.io.IOException;
import org.apache.activemq.protobuf.CodedInputStream;
import org.apache.activemq.protobuf.BaseMessage;
import org.apache.activemq.protobuf.UninitializedMessageException;
import java.util.Collection;
import java.util.ArrayList;

public final class KahaTransactionInfo extends KahaTransactionInfoBase<KahaTransactionInfo>
{
    @Override
    public ArrayList<String> missingFields() {
        final ArrayList<String> missingFields = super.missingFields();
        if (this.hasLocalTransacitonId()) {
            try {
                this.getLocalTransacitonId().assertInitialized();
            }
            catch (UninitializedMessageException e) {
                missingFields.addAll(this.prefix(e.getMissingFields(), "local_transaciton_id."));
            }
        }
        if (this.hasXaTransacitonId()) {
            try {
                this.getXaTransacitonId().assertInitialized();
            }
            catch (UninitializedMessageException e) {
                missingFields.addAll(this.prefix(e.getMissingFields(), "xa_transaciton_id."));
            }
        }
        if (this.hasPreviousEntry()) {
            try {
                this.getPreviousEntry().assertInitialized();
            }
            catch (UninitializedMessageException e) {
                missingFields.addAll(this.prefix(e.getMissingFields(), "previous_entry."));
            }
        }
        return missingFields;
    }
    
    @Override
    public void clear() {
        super.clear();
        this.clearLocalTransacitonId();
        this.clearXaTransacitonId();
        this.clearPreviousEntry();
    }
    
    @Override
    public KahaTransactionInfo clone() {
        return new KahaTransactionInfo().mergeFrom(this);
    }
    
    @Override
    public KahaTransactionInfo mergeFrom(final KahaTransactionInfo other) {
        if (other.hasLocalTransacitonId()) {
            if (this.hasLocalTransacitonId()) {
                this.getLocalTransacitonId().mergeFrom(other.getLocalTransacitonId());
            }
            else {
                this.setLocalTransacitonId(other.getLocalTransacitonId().clone());
            }
        }
        if (other.hasXaTransacitonId()) {
            if (this.hasXaTransacitonId()) {
                this.getXaTransacitonId().mergeFrom(other.getXaTransacitonId());
            }
            else {
                this.setXaTransacitonId(other.getXaTransacitonId().clone());
            }
        }
        if (other.hasPreviousEntry()) {
            if (this.hasPreviousEntry()) {
                this.getPreviousEntry().mergeFrom(other.getPreviousEntry());
            }
            else {
                this.setPreviousEntry(other.getPreviousEntry().clone());
            }
        }
        return this;
    }
    
    @Override
    public int serializedSizeUnframed() {
        if (this.memoizedSerializedSize != -1) {
            return this.memoizedSerializedSize;
        }
        int size = 0;
        if (this.hasLocalTransacitonId()) {
            size += BaseMessage.computeMessageSize(1, (BaseMessage<Object>)this.getLocalTransacitonId());
        }
        if (this.hasXaTransacitonId()) {
            size += BaseMessage.computeMessageSize(2, (BaseMessage<Object>)this.getXaTransacitonId());
        }
        if (this.hasPreviousEntry()) {
            size += BaseMessage.computeMessageSize(3, (BaseMessage<Object>)this.getPreviousEntry());
        }
        return this.memoizedSerializedSize = size;
    }
    
    @Override
    public KahaTransactionInfo mergeUnframed(final CodedInputStream input) throws IOException {
        while (true) {
            final int tag = input.readTag();
            if ((tag & 0x7) == 0x4) {
                return this;
            }
            switch (tag) {
                case 0: {
                    return this;
                }
                default: {
                    continue;
                }
                case 10: {
                    if (this.hasLocalTransacitonId()) {
                        this.getLocalTransacitonId().mergeFramed(input);
                        continue;
                    }
                    this.setLocalTransacitonId(new KahaLocalTransactionId().mergeFramed(input));
                    continue;
                }
                case 18: {
                    if (this.hasXaTransacitonId()) {
                        this.getXaTransacitonId().mergeFramed(input);
                        continue;
                    }
                    this.setXaTransacitonId(new KahaXATransactionId().mergeFramed(input));
                    continue;
                }
                case 26: {
                    if (this.hasPreviousEntry()) {
                        this.getPreviousEntry().mergeFramed(input);
                        continue;
                    }
                    this.setPreviousEntry(new KahaLocation().mergeFramed(input));
                    continue;
                }
            }
        }
    }
    
    @Override
    public void writeUnframed(final CodedOutputStream output) throws IOException {
        if (this.hasLocalTransacitonId()) {
            BaseMessage.writeMessage(output, 1, (BaseMessage<Object>)this.getLocalTransacitonId());
        }
        if (this.hasXaTransacitonId()) {
            BaseMessage.writeMessage(output, 2, (BaseMessage<Object>)this.getXaTransacitonId());
        }
        if (this.hasPreviousEntry()) {
            BaseMessage.writeMessage(output, 3, (BaseMessage<Object>)this.getPreviousEntry());
        }
    }
    
    public static KahaTransactionInfo parseUnframed(final CodedInputStream data) throws InvalidProtocolBufferException, IOException {
        return new KahaTransactionInfo().mergeUnframed(data).checktInitialized();
    }
    
    public static KahaTransactionInfo parseUnframed(final Buffer data) throws InvalidProtocolBufferException {
        return new KahaTransactionInfo().mergeUnframed(data).checktInitialized();
    }
    
    public static KahaTransactionInfo parseUnframed(final byte[] data) throws InvalidProtocolBufferException {
        return new KahaTransactionInfo().mergeUnframed(data).checktInitialized();
    }
    
    public static KahaTransactionInfo parseUnframed(final InputStream data) throws InvalidProtocolBufferException, IOException {
        return new KahaTransactionInfo().mergeUnframed(data).checktInitialized();
    }
    
    public static KahaTransactionInfo parseFramed(final CodedInputStream data) throws InvalidProtocolBufferException, IOException {
        return new KahaTransactionInfo().mergeFramed(data).checktInitialized();
    }
    
    public static KahaTransactionInfo parseFramed(final Buffer data) throws InvalidProtocolBufferException {
        return new KahaTransactionInfo().mergeFramed(data).checktInitialized();
    }
    
    public static KahaTransactionInfo parseFramed(final byte[] data) throws InvalidProtocolBufferException {
        return new KahaTransactionInfo().mergeFramed(data).checktInitialized();
    }
    
    public static KahaTransactionInfo parseFramed(final InputStream data) throws InvalidProtocolBufferException, IOException {
        return new KahaTransactionInfo().mergeFramed(data).checktInitialized();
    }
    
    @Override
    public String toString() {
        return this.toString(new StringBuilder(), "").toString();
    }
    
    public StringBuilder toString(final StringBuilder sb, final String prefix) {
        if (this.hasLocalTransacitonId()) {
            sb.append(prefix + "local_transaciton_id {\n");
            this.getLocalTransacitonId().toString(sb, prefix + "  ");
            sb.append(prefix + "}\n");
        }
        if (this.hasXaTransacitonId()) {
            sb.append(prefix + "xa_transaciton_id {\n");
            this.getXaTransacitonId().toString(sb, prefix + "  ");
            sb.append(prefix + "}\n");
        }
        if (this.hasPreviousEntry()) {
            sb.append(prefix + "previous_entry {\n");
            this.getPreviousEntry().toString(sb, prefix + "  ");
            sb.append(prefix + "}\n");
        }
        return sb;
    }
    
    @Override
    public boolean equals(final Object obj) {
        return obj == this || (obj != null && obj.getClass() == KahaTransactionInfo.class && this.equals((KahaTransactionInfo)obj));
    }
    
    public boolean equals(final KahaTransactionInfo obj) {
        return !(this.hasLocalTransacitonId() ^ obj.hasLocalTransacitonId()) && (!this.hasLocalTransacitonId() || this.getLocalTransacitonId().equals(obj.getLocalTransacitonId())) && !(this.hasXaTransacitonId() ^ obj.hasXaTransacitonId()) && (!this.hasXaTransacitonId() || this.getXaTransacitonId().equals(obj.getXaTransacitonId())) && !(this.hasPreviousEntry() ^ obj.hasPreviousEntry()) && (!this.hasPreviousEntry() || this.getPreviousEntry().equals(obj.getPreviousEntry()));
    }
    
    @Override
    public int hashCode() {
        int rc = 156129213;
        if (this.hasLocalTransacitonId()) {
            rc ^= (0x1E4002C4 ^ this.getLocalTransacitonId().hashCode());
        }
        if (this.hasXaTransacitonId()) {
            rc ^= (0xB0A26BC6 ^ this.getXaTransacitonId().hashCode());
        }
        if (this.hasPreviousEntry()) {
            rc ^= (0x1E4CCF9B ^ this.getPreviousEntry().hashCode());
        }
        return rc;
    }
}
