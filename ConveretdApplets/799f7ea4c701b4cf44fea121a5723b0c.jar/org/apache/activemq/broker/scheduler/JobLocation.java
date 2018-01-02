// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.broker.scheduler;

import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import org.apache.kahadb.util.VariableMarshaller;
import java.util.Date;
import java.io.DataOutput;
import java.io.IOException;
import java.io.DataInput;
import org.apache.kahadb.journal.Location;

class JobLocation
{
    private String jobId;
    private int repeat;
    private long startTime;
    private long delay;
    private long nextTime;
    private long period;
    private String cronEntry;
    private final Location location;
    
    public JobLocation(final Location location) {
        this.location = location;
    }
    
    public JobLocation() {
        this(new Location());
    }
    
    public void readExternal(final DataInput in) throws IOException {
        this.jobId = in.readUTF();
        this.repeat = in.readInt();
        this.startTime = in.readLong();
        this.delay = in.readLong();
        this.nextTime = in.readLong();
        this.period = in.readLong();
        this.cronEntry = in.readUTF();
        this.location.readExternal(in);
    }
    
    public void writeExternal(final DataOutput out) throws IOException {
        out.writeUTF(this.jobId);
        out.writeInt(this.repeat);
        out.writeLong(this.startTime);
        out.writeLong(this.delay);
        out.writeLong(this.nextTime);
        out.writeLong(this.period);
        if (this.cronEntry == null) {
            this.cronEntry = "";
        }
        out.writeUTF(this.cronEntry);
        this.location.writeExternal(out);
    }
    
    public String getJobId() {
        return this.jobId;
    }
    
    public void setJobId(final String jobId) {
        this.jobId = jobId;
    }
    
    public int getRepeat() {
        return this.repeat;
    }
    
    public void setRepeat(final int repeat) {
        this.repeat = repeat;
    }
    
    public long getStartTime() {
        return this.startTime;
    }
    
    public void setStartTime(final long start) {
        this.startTime = start;
    }
    
    public synchronized long getNextTime() {
        return this.nextTime;
    }
    
    public synchronized void setNextTime(final long nextTime) {
        this.nextTime = nextTime;
    }
    
    public long getPeriod() {
        return this.period;
    }
    
    public void setPeriod(final long period) {
        this.period = period;
    }
    
    public synchronized String getCronEntry() {
        return this.cronEntry;
    }
    
    public synchronized void setCronEntry(final String cronEntry) {
        this.cronEntry = cronEntry;
    }
    
    public boolean isCron() {
        return this.getCronEntry() != null && this.getCronEntry().length() > 0;
    }
    
    public long getDelay() {
        return this.delay;
    }
    
    public void setDelay(final long delay) {
        this.delay = delay;
    }
    
    public Location getLocation() {
        return this.location;
    }
    
    @Override
    public String toString() {
        return "Job [id=" + this.jobId + ", startTime=" + new Date(this.startTime) + ", delay=" + this.delay + ", period=" + this.period + ", repeat=" + this.repeat + ", nextTime=" + new Date(this.nextTime) + "]";
    }
    
    static class JobLocationMarshaller extends VariableMarshaller<List<JobLocation>>
    {
        static final JobLocationMarshaller INSTANCE;
        
        @Override
        public List<JobLocation> readPayload(final DataInput dataIn) throws IOException {
            final List<JobLocation> result = new ArrayList<JobLocation>();
            for (int size = dataIn.readInt(), i = 0; i < size; ++i) {
                final JobLocation jobLocation = new JobLocation();
                jobLocation.readExternal(dataIn);
                result.add(jobLocation);
            }
            return result;
        }
        
        @Override
        public void writePayload(final List<JobLocation> value, final DataOutput dataOut) throws IOException {
            dataOut.writeInt(value.size());
            for (final JobLocation jobLocation : value) {
                jobLocation.writeExternal(dataOut);
            }
        }
        
        static {
            INSTANCE = new JobLocationMarshaller();
        }
    }
}
