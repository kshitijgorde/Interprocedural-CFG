// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.broker.scheduler;

import org.apache.kahadb.util.VariableMarshaller;
import org.slf4j.LoggerFactory;
import java.io.DataOutput;
import java.io.DataInput;
import org.apache.kahadb.util.Marshaller;
import org.apache.kahadb.util.LongMarshaller;
import org.apache.activemq.util.ServiceStopper;
import java.util.Collection;
import javax.jms.MessageFormatException;
import org.apache.kahadb.journal.Location;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Map;
import java.io.IOException;
import org.apache.kahadb.page.Transaction;
import org.apache.kahadb.util.ByteSequence;
import java.util.concurrent.CopyOnWriteArrayList;
import org.apache.activemq.util.IdGenerator;
import java.util.List;
import org.apache.kahadb.index.BTreeIndex;
import java.util.concurrent.atomic.AtomicBoolean;
import org.slf4j.Logger;
import org.apache.activemq.util.ServiceSupport;

class JobSchedulerImpl extends ServiceSupport implements Runnable, JobScheduler
{
    private static final Logger LOG;
    final JobSchedulerStore store;
    private final AtomicBoolean running;
    private String name;
    BTreeIndex<Long, List<JobLocation>> index;
    private Thread thread;
    private final List<JobListener> jobListeners;
    private static final IdGenerator ID_GENERATOR;
    private final ScheduleTime scheduleTime;
    
    JobSchedulerImpl(final JobSchedulerStore store) {
        this.running = new AtomicBoolean();
        this.jobListeners = new CopyOnWriteArrayList<JobListener>();
        this.scheduleTime = new ScheduleTime();
        this.store = store;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    @Override
    public String getName() {
        return this.name;
    }
    
    @Override
    public void addListener(final JobListener l) {
        this.jobListeners.add(l);
    }
    
    @Override
    public void removeListener(final JobListener l) {
        this.jobListeners.remove(l);
    }
    
    @Override
    public synchronized void schedule(final String jobId, final ByteSequence payload, final long delay) throws IOException {
        this.store.getPageFile().tx().execute((Transaction.Closure<Throwable>)new Transaction.Closure<IOException>() {
            @Override
            public void execute(final Transaction tx) throws IOException {
                JobSchedulerImpl.this.schedule(tx, jobId, payload, "", 0L, delay, 0);
            }
        });
    }
    
    @Override
    public synchronized void schedule(final String jobId, final ByteSequence payload, final String cronEntry) throws Exception {
        this.store.getPageFile().tx().execute((Transaction.Closure<Throwable>)new Transaction.Closure<IOException>() {
            @Override
            public void execute(final Transaction tx) throws IOException {
                JobSchedulerImpl.this.schedule(tx, jobId, payload, cronEntry, 0L, 0L, 0);
            }
        });
    }
    
    @Override
    public synchronized void schedule(final String jobId, final ByteSequence payload, final String cronEntry, final long delay, final long period, final int repeat) throws IOException {
        this.store.getPageFile().tx().execute((Transaction.Closure<Throwable>)new Transaction.Closure<IOException>() {
            @Override
            public void execute(final Transaction tx) throws IOException {
                JobSchedulerImpl.this.schedule(tx, jobId, payload, cronEntry, delay, period, repeat);
            }
        });
    }
    
    @Override
    public synchronized void remove(final long time) throws IOException {
        this.store.getPageFile().tx().execute((Transaction.Closure<Throwable>)new Transaction.Closure<IOException>() {
            @Override
            public void execute(final Transaction tx) throws IOException {
                JobSchedulerImpl.this.remove(tx, time);
            }
        });
    }
    
    synchronized void removeFromIndex(final long time, final String jobId) throws IOException {
        this.store.getPageFile().tx().execute((Transaction.Closure<Throwable>)new Transaction.Closure<IOException>() {
            @Override
            public void execute(final Transaction tx) throws IOException {
                JobSchedulerImpl.this.removeFromIndex(tx, time, jobId);
            }
        });
    }
    
    public synchronized void remove(final long time, final String jobId) throws IOException {
        this.store.getPageFile().tx().execute((Transaction.Closure<Throwable>)new Transaction.Closure<IOException>() {
            @Override
            public void execute(final Transaction tx) throws IOException {
                JobSchedulerImpl.this.remove(tx, time, jobId);
            }
        });
    }
    
    @Override
    public synchronized void remove(final String jobId) throws IOException {
        this.store.getPageFile().tx().execute((Transaction.Closure<Throwable>)new Transaction.Closure<IOException>() {
            @Override
            public void execute(final Transaction tx) throws IOException {
                JobSchedulerImpl.this.remove(tx, jobId);
            }
        });
    }
    
    @Override
    public synchronized long getNextScheduleTime() throws IOException {
        final Map.Entry<Long, List<JobLocation>> first = this.index.getFirst(this.store.getPageFile().tx());
        return (first != null) ? first.getKey() : -1L;
    }
    
    @Override
    public synchronized List<Job> getNextScheduleJobs() throws IOException {
        final List<Job> result = new ArrayList<Job>();
        this.store.getPageFile().tx().execute((Transaction.Closure<Throwable>)new Transaction.Closure<IOException>() {
            @Override
            public void execute(final Transaction tx) throws IOException {
                final Map.Entry<Long, List<JobLocation>> first = JobSchedulerImpl.this.index.getFirst(JobSchedulerImpl.this.store.getPageFile().tx());
                if (first != null) {
                    for (final JobLocation jl : first.getValue()) {
                        final ByteSequence bs = JobSchedulerImpl.this.getPayload(jl.getLocation());
                        final Job job = new JobImpl(jl, bs);
                        result.add(job);
                    }
                }
            }
        });
        return result;
    }
    
    @Override
    public synchronized List<Job> getAllJobs() throws IOException {
        final List<Job> result = new ArrayList<Job>();
        this.store.getPageFile().tx().execute((Transaction.Closure<Throwable>)new Transaction.Closure<IOException>() {
            @Override
            public void execute(final Transaction tx) throws IOException {
                final Iterator<Map.Entry<Long, List<JobLocation>>> iter = JobSchedulerImpl.this.index.iterator(JobSchedulerImpl.this.store.getPageFile().tx());
                while (iter.hasNext()) {
                    final Map.Entry<Long, List<JobLocation>> next = iter.next();
                    if (next == null) {
                        break;
                    }
                    for (final JobLocation jl : next.getValue()) {
                        final ByteSequence bs = JobSchedulerImpl.this.getPayload(jl.getLocation());
                        final Job job = new JobImpl(jl, bs);
                        result.add(job);
                    }
                }
            }
        });
        return result;
    }
    
    @Override
    public synchronized List<Job> getAllJobs(final long start, final long finish) throws IOException {
        final List<Job> result = new ArrayList<Job>();
        this.store.getPageFile().tx().execute((Transaction.Closure<Throwable>)new Transaction.Closure<IOException>() {
            @Override
            public void execute(final Transaction tx) throws IOException {
                final Iterator<Map.Entry<Long, List<JobLocation>>> iter = JobSchedulerImpl.this.index.iterator(JobSchedulerImpl.this.store.getPageFile().tx(), start);
                while (iter.hasNext()) {
                    final Map.Entry<Long, List<JobLocation>> next = iter.next();
                    if (next == null || next.getKey() > finish) {
                        break;
                    }
                    for (final JobLocation jl : next.getValue()) {
                        final ByteSequence bs = JobSchedulerImpl.this.getPayload(jl.getLocation());
                        final Job job = new JobImpl(jl, bs);
                        result.add(job);
                    }
                }
            }
        });
        return result;
    }
    
    @Override
    public synchronized void removeAllJobs() throws IOException {
        this.store.getPageFile().tx().execute((Transaction.Closure<Throwable>)new Transaction.Closure<IOException>() {
            @Override
            public void execute(final Transaction tx) throws IOException {
                JobSchedulerImpl.this.destroy(tx);
            }
        });
    }
    
    @Override
    public synchronized void removeAllJobs(final long start, final long finish) throws IOException {
        this.store.getPageFile().tx().execute((Transaction.Closure<Throwable>)new Transaction.Closure<IOException>() {
            @Override
            public void execute(final Transaction tx) throws IOException {
                JobSchedulerImpl.this.destroy(tx, start, finish);
            }
        });
    }
    
    ByteSequence getPayload(final Location location) throws IllegalStateException, IOException {
        return this.store.getPayload(location);
    }
    
    void schedule(final Transaction tx, final String jobId, final ByteSequence payload, final String cronEntry, final long delay, final long period, final int repeat) throws IOException {
        long startTime = System.currentTimeMillis();
        startTime = startTime / 1000L * 1000L;
        long time = 0L;
        if (cronEntry != null && cronEntry.length() > 0) {
            try {
                time = CronParser.getNextScheduledTime(cronEntry, startTime);
            }
            catch (MessageFormatException e) {
                throw new IOException(e.getMessage());
            }
        }
        if (time == 0L) {
            time = startTime;
        }
        if (delay > 0L) {
            time += delay;
        }
        else {
            time += period;
        }
        final Location location = this.store.write(payload, false);
        final JobLocation jobLocation = new JobLocation(location);
        this.store.incrementJournalCount(tx, location);
        jobLocation.setJobId(jobId);
        jobLocation.setStartTime(startTime);
        jobLocation.setCronEntry(cronEntry);
        jobLocation.setDelay(delay);
        jobLocation.setPeriod(period);
        jobLocation.setRepeat(repeat);
        if (JobSchedulerImpl.LOG.isDebugEnabled()) {
            JobSchedulerImpl.LOG.debug("Scheduling " + jobLocation);
        }
        this.storeJob(tx, jobLocation, time);
        this.scheduleTime.newJob();
    }
    
    synchronized void storeJob(final JobLocation jobLocation, final long nextExecutionTime) throws IOException {
        this.store.getPageFile().tx().execute((Transaction.Closure<Throwable>)new Transaction.Closure<IOException>() {
            @Override
            public void execute(final Transaction tx) throws IOException {
                JobSchedulerImpl.this.storeJob(tx, jobLocation, nextExecutionTime);
            }
        });
    }
    
    void storeJob(final Transaction tx, final JobLocation jobLocation, final long nextExecutionTime) throws IOException {
        List<JobLocation> values = null;
        jobLocation.setNextTime(nextExecutionTime);
        if (this.index.containsKey(tx, nextExecutionTime)) {
            values = this.index.remove(tx, nextExecutionTime);
        }
        if (values == null) {
            values = new ArrayList<JobLocation>();
        }
        values.add(jobLocation);
        this.index.put(tx, nextExecutionTime, values);
    }
    
    void remove(final Transaction tx, final long time, final String jobId) throws IOException {
        final JobLocation result = this.removeFromIndex(tx, time, jobId);
        if (result != null) {
            this.store.decrementJournalCount(tx, result.getLocation());
        }
    }
    
    JobLocation removeFromIndex(final Transaction tx, final long time, final String jobId) throws IOException {
        JobLocation result = null;
        final List<JobLocation> values = this.index.remove(tx, time);
        if (values != null) {
            for (int i = 0; i < values.size(); ++i) {
                final JobLocation jl = values.get(i);
                if (jl.getJobId().equals(jobId)) {
                    values.remove(i);
                    if (!values.isEmpty()) {
                        this.index.put(tx, time, values);
                    }
                    result = jl;
                    break;
                }
            }
        }
        return result;
    }
    
    void remove(final Transaction tx, final long time) throws IOException {
        final List<JobLocation> values = this.index.remove(tx, time);
        if (values != null) {
            for (final JobLocation jl : values) {
                this.store.decrementJournalCount(tx, jl.getLocation());
            }
        }
    }
    
    void remove(final Transaction tx, final String id) throws IOException {
        final Iterator<Map.Entry<Long, List<JobLocation>>> i = this.index.iterator(tx);
        while (i.hasNext()) {
            final Map.Entry<Long, List<JobLocation>> entry = i.next();
            final List<JobLocation> values = entry.getValue();
            if (values != null) {
                for (final JobLocation jl : values) {
                    if (jl.getJobId().equals(id)) {
                        this.remove(tx, entry.getKey(), id);
                    }
                }
            }
        }
    }
    
    synchronized void destroy(final Transaction tx) throws IOException {
        final List<Long> keys = new ArrayList<Long>();
        final Iterator<Map.Entry<Long, List<JobLocation>>> i = this.index.iterator(tx);
        while (i.hasNext()) {
            final Map.Entry<Long, List<JobLocation>> entry = i.next();
            keys.add(entry.getKey());
            final List<JobLocation> values = entry.getValue();
            if (values != null) {
                for (final JobLocation jl : values) {
                    this.store.decrementJournalCount(tx, jl.getLocation());
                }
            }
        }
        for (final Long l : keys) {
            this.index.remove(tx, l);
        }
    }
    
    synchronized void destroy(final Transaction tx, final long start, final long finish) throws IOException {
        final List<Long> keys = new ArrayList<Long>();
        final Iterator<Map.Entry<Long, List<JobLocation>>> i = this.index.iterator(tx, start);
        while (i.hasNext()) {
            final Map.Entry<Long, List<JobLocation>> entry = i.next();
            if (entry.getKey() > finish) {
                break;
            }
            keys.add(entry.getKey());
            final List<JobLocation> values = entry.getValue();
            if (values == null) {
                continue;
            }
            for (final JobLocation jl : values) {
                this.store.decrementJournalCount(tx, jl.getLocation());
            }
        }
        for (final Long l : keys) {
            this.index.remove(tx, l);
        }
    }
    
    private synchronized Map.Entry<Long, List<JobLocation>> getNextToSchedule() throws IOException {
        if (!this.store.isStopped() && !this.store.isStopping()) {
            final Map.Entry<Long, List<JobLocation>> first = this.index.getFirst(this.store.getPageFile().tx());
            return first;
        }
        return null;
    }
    
    void fireJob(final JobLocation job) throws IllegalStateException, IOException {
        if (JobSchedulerImpl.LOG.isDebugEnabled()) {
            JobSchedulerImpl.LOG.debug("Firing " + job);
        }
        final ByteSequence bs = this.store.getPayload(job.getLocation());
        for (final JobListener l : this.jobListeners) {
            l.scheduledJob(job.getJobId(), bs);
        }
    }
    
    @Override
    public void run() {
        try {
            this.mainLoop();
        }
        catch (Throwable e) {
            if (this.running.get() && this.isStarted()) {
                JobSchedulerImpl.LOG.error(this + " Caught exception in mainloop", e);
            }
            if (this.running.get()) {
                try {
                    this.stop();
                }
                catch (Exception e2) {
                    JobSchedulerImpl.LOG.error("Failed to stop " + this);
                }
            }
        }
        finally {
            if (this.running.get()) {
                try {
                    this.stop();
                }
                catch (Exception e3) {
                    JobSchedulerImpl.LOG.error("Failed to stop " + this);
                }
            }
        }
    }
    
    @Override
    public String toString() {
        return "JobScheduler:" + this.name;
    }
    
    protected void mainLoop() {
        while (this.running.get()) {
            this.scheduleTime.clearNewJob();
            try {
                final long currentTime = System.currentTimeMillis();
                synchronized (this) {
                    final Map.Entry<Long, List<JobLocation>> first = this.getNextToSchedule();
                    if (first != null) {
                        final List<JobLocation> list = new ArrayList<JobLocation>(first.getValue());
                        final long executionTime = first.getKey();
                        long nextExecutionTime = 0L;
                        if (executionTime <= currentTime) {
                            for (final JobLocation job : list) {
                                int repeat = job.getRepeat();
                                nextExecutionTime = this.calculateNextExecutionTime(job, currentTime, repeat);
                                long waitTime = nextExecutionTime - currentTime;
                                this.scheduleTime.setWaitTime(waitTime);
                                if (!job.isCron()) {
                                    this.fireJob(job);
                                    if (repeat == 0) {
                                        continue;
                                    }
                                    --repeat;
                                    job.setRepeat(repeat);
                                    this.removeFromIndex(executionTime, job.getJobId());
                                    this.storeJob(job, nextExecutionTime);
                                }
                                else {
                                    if (repeat == 0) {
                                        this.fireJob(job);
                                    }
                                    if (nextExecutionTime <= currentTime) {
                                        continue;
                                    }
                                    this.removeFromIndex(executionTime, job.getJobId());
                                    this.storeJob(job, nextExecutionTime);
                                    if (repeat == 0) {
                                        continue;
                                    }
                                    final String jobId = JobSchedulerImpl.ID_GENERATOR.generateId();
                                    final ByteSequence payload = this.getPayload(job.getLocation());
                                    this.schedule(jobId, payload, "", job.getDelay(), job.getPeriod(), job.getRepeat());
                                    waitTime = ((job.getDelay() != 0L) ? job.getDelay() : job.getPeriod());
                                    this.scheduleTime.setWaitTime(waitTime);
                                }
                            }
                            this.remove(executionTime);
                            final Map.Entry<Long, List<JobLocation>> nextUp = this.getNextToSchedule();
                            if (nextUp != null) {
                                final long timeUntilNextScheduled = nextUp.getKey() - currentTime;
                                if (timeUntilNextScheduled < this.scheduleTime.getWaitTime()) {
                                    this.scheduleTime.setWaitTime(timeUntilNextScheduled);
                                }
                            }
                        }
                        else {
                            if (JobSchedulerImpl.LOG.isDebugEnabled()) {
                                JobSchedulerImpl.LOG.debug("Not yet time to execute the job, waiting " + (executionTime - currentTime) + " ms");
                            }
                            this.scheduleTime.setWaitTime(executionTime - currentTime);
                        }
                    }
                }
                this.scheduleTime.pause();
            }
            catch (Exception ioe) {
                JobSchedulerImpl.LOG.error(this.name + " Failed to schedule job", ioe);
                try {
                    this.store.stop();
                }
                catch (Exception e) {
                    JobSchedulerImpl.LOG.error(this.name + " Failed to shutdown JobSchedulerStore", e);
                }
            }
        }
    }
    
    @Override
    protected void doStart() throws Exception {
        this.running.set(true);
        (this.thread = new Thread(this, "JobScheduler:" + this.name)).setDaemon(true);
        this.thread.start();
    }
    
    @Override
    protected void doStop(final ServiceStopper stopper) throws Exception {
        this.running.set(false);
        this.scheduleTime.wakeup();
        final Thread t = this.thread;
        if (t != null) {
            t.join(1000L);
        }
    }
    
    long calculateNextExecutionTime(final JobLocation job, final long currentTime, final int repeat) throws MessageFormatException {
        long result = currentTime;
        final String cron = job.getCronEntry();
        if (cron != null && cron.length() > 0) {
            result = CronParser.getNextScheduledTime(cron, result);
        }
        else if (job.getRepeat() != 0) {
            result += job.getPeriod();
        }
        return result;
    }
    
    void createIndexes(final Transaction tx) throws IOException {
        this.index = new BTreeIndex<Long, List<JobLocation>>(this.store.getPageFile(), tx.allocate().getPageId());
    }
    
    void load(final Transaction tx) throws IOException {
        this.index.setKeyMarshaller(LongMarshaller.INSTANCE);
        this.index.setValueMarshaller(ValueMarshaller.INSTANCE);
        this.index.load(tx);
    }
    
    void read(final DataInput in) throws IOException {
        this.name = in.readUTF();
        (this.index = new BTreeIndex<Long, List<JobLocation>>(this.store.getPageFile(), in.readLong())).setKeyMarshaller(LongMarshaller.INSTANCE);
        this.index.setValueMarshaller(ValueMarshaller.INSTANCE);
    }
    
    public void write(final DataOutput out) throws IOException {
        out.writeUTF(this.name);
        out.writeLong(this.index.getPageId());
    }
    
    static {
        LOG = LoggerFactory.getLogger(JobSchedulerImpl.class);
        ID_GENERATOR = new IdGenerator();
    }
    
    static class ValueMarshaller extends VariableMarshaller<List<JobLocation>>
    {
        static ValueMarshaller INSTANCE;
        
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
            ValueMarshaller.INSTANCE = new ValueMarshaller();
        }
    }
    
    static class ScheduleTime
    {
        private final int DEFAULT_WAIT = 500;
        private final int DEFAULT_NEW_JOB_WAIT = 100;
        private boolean newJob;
        private long waitTime;
        private final Object mutex;
        
        ScheduleTime() {
            this.waitTime = 500L;
            this.mutex = new Object();
        }
        
        long getWaitTime() {
            return this.waitTime;
        }
        
        void setWaitTime(final long waitTime) {
            if (!this.newJob) {
                this.waitTime = ((waitTime > 0L) ? waitTime : 500L);
            }
        }
        
        void pause() {
            synchronized (this.mutex) {
                try {
                    this.mutex.wait(this.waitTime);
                }
                catch (InterruptedException ex) {}
            }
        }
        
        void newJob() {
            this.newJob = true;
            this.waitTime = 100L;
            this.wakeup();
        }
        
        void clearNewJob() {
            this.newJob = false;
        }
        
        void wakeup() {
            synchronized (this.mutex) {
                this.mutex.notifyAll();
            }
        }
    }
}
