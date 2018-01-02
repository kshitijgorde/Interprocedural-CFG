// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.db;

import java.util.HashMap;
import java.util.List;
import java.util.Iterator;
import java.util.Comparator;
import java.util.Collections;
import java.util.Collection;
import java.util.ArrayList;
import com.stonewall.cornerstone.utility.StackTrace;
import java.util.concurrent.ConcurrentHashMap;
import org.xmodel.log.Log;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class Profiler implements ProfilerMBean
{
    static Profiler inst;
    private boolean enabled;
    private final ReentrantLock lock;
    private final Map<String, String> queries;
    private final Map<String, String> locations;
    private final Map<String, Statistic> statsByQuery;
    private final Map<String, Map<String, Statistic>> statsByLocation;
    static final Log log;
    
    static {
        Profiler.inst = null;
        log = Log.getLog(Profiler.class);
    }
    
    public static Profiler getInstance() {
        if (Profiler.inst == null) {
            Profiler.inst = new Profiler();
        }
        return Profiler.inst;
    }
    
    private Profiler() {
        this.lock = new ReentrantLock();
        this.queries = new ConcurrentHashMap<String, String>();
        this.locations = new ConcurrentHashMap<String, String>();
        this.statsByQuery = new ConcurrentHashMap<String, Statistic>();
        this.statsByLocation = new ConcurrentHashMap<String, Map<String, Statistic>>();
        final String property = System.getProperty("cornerstone.db.profiler.enabled", "false");
        this.enabled = Boolean.parseBoolean(property);
        Profiler.log.info("Database profiler enabled: " + this.enabled);
    }
    
    public void executed(final BasicStatement stmt, final long tm) {
        if (this.enabled) {
            final String name = stmt.extractName();
            if (name != null) {
                this.executed(name, stmt.queryBuilder().content, tm);
                this.executed(name, new StackTrace(1).filter("com\\.stonewall"), tm);
            }
        }
    }
    
    public void sessionCommitted(final long tm) {
        final String name = "commit";
        if (!this.enabled) {
            return;
        }
        this.lock.lock();
        try {
            Statistic s = this.statsByQuery.get("commit");
            if (s == null) {
                s = new Statistic("commit");
                this.statsByQuery.put("commit", s);
            }
            s.executed(tm);
            this.sessionCommitted(new StackTrace().filter("com\\.stonewall"), tm);
        }
        finally {
            this.lock.unlock();
        }
        this.lock.unlock();
    }
    
    public void sessionOpened(final long tm) {
        final String name = "open-session";
        if (!this.enabled) {
            return;
        }
        this.lock.lock();
        try {
            Statistic s = this.statsByQuery.get("open-session");
            if (s == null) {
                s = new Statistic("open-session");
                this.statsByQuery.put("open-session", s);
            }
            s.executed(tm);
        }
        finally {
            this.lock.unlock();
        }
        this.lock.unlock();
    }
    
    public void resultGet(final String type, final long tm) {
        final String name = "result:get-" + type;
        if (!this.enabled) {
            return;
        }
        this.lock.lock();
        try {
            Statistic s = this.statsByQuery.get(name);
            if (s == null) {
                s = new Statistic(name);
                this.statsByQuery.put(name, s);
            }
            s.executed(tm);
        }
        finally {
            this.lock.unlock();
        }
        this.lock.unlock();
    }
    
    public void sessionClosed(final long tm) {
        final String name = "close-session";
        if (!this.enabled) {
            return;
        }
        this.lock.lock();
        try {
            Statistic s = this.statsByQuery.get("close-session");
            if (s == null) {
                s = new Statistic("close-session");
                this.statsByQuery.put("close-session", s);
            }
            s.executed(tm);
        }
        finally {
            this.lock.unlock();
        }
        this.lock.unlock();
    }
    
    @Override
    public boolean getEnabled() {
        return this.enabled;
    }
    
    @Override
    public void setEnabled(final boolean flag) {
        this.enabled = flag;
    }
    
    @Override
    public void clear() {
        this.lock.lock();
        try {
            this.statsByQuery.clear();
            this.statsByLocation.clear();
            this.queries.clear();
            this.locations.clear();
        }
        finally {
            this.lock.unlock();
        }
        this.lock.unlock();
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        this.lock.lock();
        try {
            sb.append("Profiler:\n");
            sb.append(this.basicStatistics());
            sb.append(this.advancedStatistics());
        }
        finally {
            this.lock.unlock();
        }
        this.lock.unlock();
        return sb.toString();
    }
    
    public String basicStatistics() {
        long totalQueries = 0L;
        long totalDuration = 0L;
        Statistic longest = new Statistic(".");
        final StringBuilder sb = new StringBuilder();
        sb.append("Basic:\n");
        sb.append("\t------------------ queries -------------------\n");
        for (final Object k : this.queries.keySet()) {
            final String q = this.queries.get(k);
            sb.append('\t');
            sb.append("(" + k + ")\n");
            sb.append('\t');
            sb.append(q.replaceAll("\n", "\n\t"));
            sb.append("\n");
        }
        sb.append("\t------------------ statistics -------------------\n");
        final List<Statistic> list = new ArrayList<Statistic>();
        list.addAll(this.statsByQuery.values());
        Collections.sort(list, new HighestCount());
        for (final Statistic s : list) {
            totalQueries += s.count;
            totalDuration += s.duration;
            if (s.avg() > longest.avg()) {
                longest = s;
            }
            sb.append('\t');
            sb.append(s);
            sb.append('\n');
        }
        final float totalAvg = (totalQueries == 0L) ? 0L : (totalDuration / totalQueries);
        sb.append("\t----------------- Totals --------------------\n");
        sb.append("\t Total Count: " + totalQueries);
        sb.append("\n\t Total Duration: " + totalDuration + " (ms)\n");
        sb.append("\t Total Avg: " + totalAvg + " (ms)\n");
        sb.append("\t Longest Avg: " + longest.avg() + " (ms) qid: " + longest.name);
        sb.append("\n\t-----------------------------------------------\n");
        sb.append('\n');
        return sb.toString();
    }
    
    public String advancedStatistics() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Advanced:\n");
        sb.append("\t------------------ locations ------------------\n");
        for (final Object k : this.locations.keySet()) {
            final String l = this.locations.get(k);
            sb.append('\t');
            sb.append("(" + k + ")\n\t");
            sb.append(l);
            final Map<String, Statistic> qs = this.statsByLocation.get(k);
            if (qs == null) {
                continue;
            }
            final List<Statistic> list = new ArrayList<Statistic>();
            list.addAll(qs.values());
            Collections.sort(list, new HighestCount());
            for (final Statistic s : list) {
                sb.append("\n\t\t");
                sb.append(s);
                sb.append('\n');
            }
            sb.append("\n");
        }
        return sb.toString();
    }
    
    @Override
    public String showReport() {
        return this.toString();
    }
    
    @Override
    public void traceReport() {
        Profiler.log.info(this);
    }
    
    public void shutdown() {
        System.exit(0);
    }
    
    private void executed(final String name, final String query, final long tm) {
        this.lock.lock();
        try {
            Statistic s = this.statsByQuery.get(name);
            if (s == null) {
                s = new Statistic(name);
                this.statsByQuery.put(name, s);
                this.queries.put(name, query);
            }
            s.executed(tm);
        }
        finally {
            this.lock.unlock();
        }
        this.lock.unlock();
    }
    
    private void executed(final String query, final StackTrace trace, final long tm) {
        this.lock.lock();
        try {
            final String location = Integer.toHexString(trace.hashCode());
            Map<String, Statistic> qs = this.statsByLocation.get(location);
            if (qs == null) {
                qs = new HashMap<String, Statistic>();
                this.statsByLocation.put(location, qs);
                this.locations.put(location, trace.toString());
            }
            Statistic s = qs.get(query);
            if (s == null) {
                s = new Statistic(query);
                qs.put(query, s);
            }
            s.executed(tm);
        }
        finally {
            this.lock.unlock();
        }
        this.lock.unlock();
    }
    
    private void sessionCommitted(final StackTrace trace, final long tm) {
        this.lock.lock();
        try {
            final String query = "session-committed";
            final String location = Integer.toHexString(trace.hashCode());
            Map<String, Statistic> qs = this.statsByLocation.get(location);
            if (qs == null) {
                qs = new HashMap<String, Statistic>();
                this.statsByLocation.put(location, qs);
                this.locations.put(location, trace.toString());
            }
            Statistic s = qs.get(query);
            if (s == null) {
                s = new Statistic(query);
                qs.put(query, s);
            }
            s.executed(tm);
        }
        finally {
            this.lock.unlock();
        }
        this.lock.unlock();
    }
}
