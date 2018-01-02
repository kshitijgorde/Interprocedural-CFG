// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.broker.scheduler;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import org.apache.kahadb.util.ByteSequence;

public class JobImpl implements Job
{
    private final JobLocation jobLocation;
    private final byte[] payload;
    
    protected JobImpl(final JobLocation location, final ByteSequence bs) {
        this.jobLocation = location;
        this.payload = new byte[bs.getLength()];
        System.arraycopy(bs.getData(), bs.getOffset(), this.payload, 0, bs.getLength());
    }
    
    @Override
    public String getJobId() {
        return this.jobLocation.getJobId();
    }
    
    @Override
    public byte[] getPayload() {
        return this.payload;
    }
    
    @Override
    public long getPeriod() {
        return this.jobLocation.getPeriod();
    }
    
    @Override
    public int getRepeat() {
        return this.jobLocation.getRepeat();
    }
    
    @Override
    public long getStart() {
        return this.jobLocation.getStartTime();
    }
    
    @Override
    public long getDelay() {
        return this.jobLocation.getDelay();
    }
    
    @Override
    public String getCronEntry() {
        return this.jobLocation.getCronEntry();
    }
    
    @Override
    public String getNextExecutionTime() {
        return getDateTime(this.jobLocation.getNextTime());
    }
    
    @Override
    public String getStartTime() {
        return getDateTime(this.getStart());
    }
    
    public static long getDataTime(final String value) throws Exception {
        final DateFormat dfm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        final Date date = dfm.parse(value);
        return date.getTime();
    }
    
    public static String getDateTime(final long value) {
        final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        final Date date = new Date(value);
        return dateFormat.format(date);
    }
}
