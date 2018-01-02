// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity;

import com.stonewall.cornerstone.security.User;
import org.xmodel.Element;
import org.xmodel.xpath.expression.IExpression;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.expression.Context;
import org.xmodel.xpath.XPath;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import com.stonewall.cornerstone.db.XDate;
import com.stonewall.cornerstone.utility.Transaction;
import org.xmodel.Xlate;
import java.util.Collections;
import java.util.WeakHashMap;
import org.xmodel.IModelObject;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class Job extends Entity
{
    Statistic statistics;
    final ReentrantLock lock;
    static Map<IModelObject, ReentrantLock> locks;
    public static final String tag;
    public static final String TaskParent = "en:children";
    
    static {
        Job.locks = Collections.synchronizedMap(new WeakHashMap<IModelObject, ReentrantLock>());
        tag = EntityFactory.EntityType.job.getQualifiedName();
    }
    
    public Job(final Type type) {
        super(Job.tag);
        this.statistics = new Statistic();
        this.lock = this.getLock();
        this.init(type);
    }
    
    public Job(final IModelObject root) {
        super(root);
        this.statistics = new Statistic();
        this.lock = this.getLock();
    }
    
    public void add(final Task task) {
        this.lock.lock();
        try {
            final IModelObject taskParent = this.getChildren();
            int suffix = taskParent.getChildren("en:task").size();
            task.setId(this.getId(), ++suffix);
            taskParent.addChild(task.root);
        }
        finally {
            this.lock.unlock();
        }
        this.lock.unlock();
    }
    
    public Type getType() {
        this.lock.lock();
        try {
            return Type.valueOf(Xlate.get(this.root.getFirstChild("en:type"), (String)null));
        }
        finally {
            this.lock.unlock();
        }
    }
    
    public void setType(final Type type) {
        this.lock.lock();
        try {
            this.root.getFirstChild("en:type").setValue(type.toString());
        }
        finally {
            this.lock.unlock();
        }
        this.lock.unlock();
    }
    
    public String getAccessGroup() {
        this.lock.lock();
        try {
            return Xlate.get(this.root.getFirstChild("en:type"), "accessGroup", (String)null);
        }
        finally {
            this.lock.unlock();
        }
    }
    
    public void setAccessGroup(final String ag) {
        if (ag == null) {
            throw new IllegalArgumentException("AG may not be null.");
        }
        this.lock.lock();
        try {
            this.root.getFirstChild("en:type").setAttribute("accessGroup", ag);
        }
        finally {
            this.lock.unlock();
        }
        this.lock.unlock();
    }
    
    public String getUserid() {
        this.lock.lock();
        try {
            return Xlate.get(this.root.getFirstChild("en:userid"), (String)null);
        }
        finally {
            this.lock.unlock();
        }
    }
    
    public void setUserid(final String userid) {
        this.lock.lock();
        try {
            this.root.getFirstChild("en:userid").setValue(userid);
        }
        finally {
            this.lock.unlock();
        }
        this.lock.unlock();
    }
    
    public boolean getPersistent() {
        this.lock.lock();
        try {
            return Boolean.parseBoolean(Xlate.get(this.root.getFirstChild("en:persistent"), (String)null));
        }
        finally {
            this.lock.unlock();
        }
    }
    
    public void setPersistent(final boolean flag) {
        this.lock.lock();
        try {
            this.root.getFirstChild("en:persistent").setValue(Boolean.toString(flag));
        }
        finally {
            this.lock.unlock();
        }
        this.lock.unlock();
    }
    
    public String getTransaction() {
        this.lock.lock();
        try {
            return Xlate.get(this.root.getFirstChild("en:transaction"), (String)null);
        }
        finally {
            this.lock.unlock();
        }
    }
    
    public void setTransaction(final String tid) {
        this.lock.lock();
        try {
            this.root.getFirstChild("en:transaction").setValue(tid);
        }
        finally {
            this.lock.unlock();
        }
        this.lock.unlock();
    }
    
    public void setTransaction() {
        final Transaction tr = Transaction.getCurrent();
        this.lock.lock();
        try {
            this.root.getFirstChild("en:transaction").setValue((tr == null) ? null : tr.getId());
        }
        finally {
            this.lock.unlock();
        }
        this.lock.unlock();
    }
    
    public Status getStatus() {
        this.lock.lock();
        try {
            final String st = Xlate.get(this.root.getFirstChild("en:status"), (String)null);
            return Status.valueOf(st);
        }
        finally {
            this.lock.unlock();
        }
    }
    
    public String getStatusTimestamp() {
        this.lock.lock();
        try {
            return Xlate.get(this.root.getFirstChild("en:status"), "tm", (String)null);
        }
        finally {
            this.lock.unlock();
        }
    }
    
    public void setStatus(final Status status) {
        this.lock.lock();
        try {
            final IModelObject statusRoot = this.root.getFirstChild("en:status");
            statusRoot.setValue(status.name());
            statusRoot.setAttribute("tm", XDate.dateTime());
            switch (status) {
                case complete:
                case failed:
                case cancelled: {
                    this.statistics.suppressTimeRemaining();
                    break;
                }
            }
        }
        finally {
            this.lock.unlock();
        }
        this.lock.unlock();
    }
    
    public String getCreated() {
        this.lock.lock();
        try {
            return Xlate.get(this.root.getFirstChild("en:created"), (String)null);
        }
        finally {
            this.lock.unlock();
        }
    }
    
    public void setCreated(final String created) {
        this.lock.lock();
        try {
            this.root.getFirstChild("en:created").setValue(created);
        }
        finally {
            this.lock.unlock();
        }
        this.lock.unlock();
    }
    
    public List<Task> getTasks() {
        this.lock.lock();
        try {
            return this.convertList(this.getChildren().getChildren("en:task"));
        }
        finally {
            this.lock.unlock();
        }
    }
    
    public IModelObject getChildren() {
        this.lock.lock();
        try {
            return this.root.getFirstChild("en:children");
        }
        finally {
            this.lock.unlock();
        }
    }
    
    public void setChildren(final IModelObject replacement) {
        this.lock.lock();
        try {
            this.getChildren().removeFromParent();
            this.root.addChild(replacement);
        }
        finally {
            this.lock.unlock();
        }
        this.lock.unlock();
    }
    
    public List<Task> getReadyTasks() {
        final List<Task> result = new ArrayList<Task>();
        this.lock.lock();
        try {
            for (final Object o : this.root.getFirstChild("en:children").getChildren("en:task")) {
                final IModelObject node = (IModelObject)o;
                final Task task = new Task(node);
                if (!task.getFinished()) {
                    result.add(task);
                }
            }
        }
        finally {
            this.lock.unlock();
        }
        this.lock.unlock();
        return result;
    }
    
    public List<Task> getAllTasks() {
        List<Task> result = Collections.emptyList();
        final String exp = "//en:task";
        this.lock.lock();
        try {
            final IExpression xpath = XPath.createExpression("//en:task");
            final List list = xpath.evaluateNodes(new Context(this.root));
            result = this.convertList(list);
        }
        catch (Exception e) {
            Job.log.error("//en:task", e);
            return result;
        }
        finally {
            this.lock.unlock();
        }
        this.lock.unlock();
        return result;
    }
    
    public void updateTask(final Task ds) {
        final String exp = "//en:task[@id = $tid]";
        this.lock.lock();
        try {
            final IExpression xpath = XPath.createExpression("//en:task[@id = $tid]");
            xpath.setVariable("tid", ds.getId());
            final IModelObject taskRoot = xpath.queryFirst(this.root);
            if (taskRoot == null) {
                Job.log.warn("Task " + ds.getId() + " not-found, not updated.");
                return;
            }
            final Task task = new Task(taskRoot);
            task.update(ds);
        }
        catch (Exception e) {
            Job.log.error("//en:task[@id = $tid]", e);
            return;
        }
        finally {
            this.lock.unlock();
        }
        this.lock.unlock();
    }
    
    public int taskCount() {
        double result = 0.0;
        final String exp = "count(//en:task)";
        this.lock.lock();
        try {
            final IExpression xpath = XPath.createExpression("count(//en:task)");
            result = xpath.evaluateNumber(new Context(this.root));
        }
        catch (Exception e) {
            Job.log.error("count(//en:task)", e);
            return (int)result;
        }
        finally {
            this.lock.unlock();
        }
        this.lock.unlock();
        return (int)result;
    }
    
    public int finishedCount() {
        double result = 0.0;
        final String exp = "count(//en:task[en:itinerary/@finished = 'true'])";
        this.lock.lock();
        try {
            final IExpression xpath = XPath.createExpression("count(//en:task[en:itinerary/@finished = 'true'])");
            result = xpath.evaluateNumber(new Context(this.root));
        }
        catch (Exception e) {
            Job.log.error("count(//en:task[en:itinerary/@finished = 'true'])", e);
            return (int)result;
        }
        finally {
            this.lock.unlock();
        }
        this.lock.unlock();
        return (int)result;
    }
    
    public List<String> getAccessGroups() {
        final String exp = "//en:task/en:action/@accessGroup";
        final List<String> result = new ArrayList<String>();
        final String ag = this.getAccessGroup();
        if (ag != null) {
            result.add(ag);
        }
        this.lock.lock();
        try {
            final IExpression xpath = XPath.createExpression("//en:task/en:action/@accessGroup");
            for (final IModelObject o : xpath.evaluateNodes(new Context(this.root))) {
                result.add(Xlate.get(o, (String)null));
            }
        }
        catch (Exception e) {
            Job.log.error("//en:task/en:action/@accessGroup", e);
            return result;
        }
        finally {
            this.lock.unlock();
        }
        this.lock.unlock();
        return result;
    }
    
    public int errorCount() {
        double result = 0.0;
        final String exp = "count(//en:task[en:state = 'failed'])";
        this.lock.lock();
        try {
            final IExpression xpath = XPath.createExpression("count(//en:task[en:state = 'failed'])");
            result = xpath.evaluateNumber(new Context(this.root));
        }
        catch (Exception e) {
            Job.log.error("count(//en:task[en:state = 'failed'])", e);
            return (int)result;
        }
        finally {
            this.lock.unlock();
        }
        this.lock.unlock();
        return (int)result;
    }
    
    public void reset(final Status status) {
        Task.State state = Task.State.created;
        switch (status) {
            case created: {
                state = Task.State.created;
                break;
            }
            case open: {
                state = Task.State.open;
                break;
            }
            default: {
                throw new IllegalArgumentException();
            }
        }
        this.setStatus(status);
        for (final Task t : this.getAllTasks()) {
            if (t.failed()) {
                continue;
            }
            t.reset(state);
        }
    }
    
    public boolean nonPersistent() {
        return !this.getPersistent();
    }
    
    @Override
    public String toString() {
        this.lock.lock();
        try {
            return super.toString();
        }
        finally {
            this.lock.unlock();
        }
    }
    
    @Override
    public boolean equals(final Object o) {
        this.lock.lock();
        try {
            if (o instanceof Job) {
                final Job jb = (Job)o;
                return this.getId().equals(jb.getId());
            }
        }
        finally {
            this.lock.unlock();
        }
        this.lock.unlock();
        return false;
    }
    
    @Override
    public int hashCode() {
        this.lock.lock();
        try {
            return this.getId().hashCode();
        }
        finally {
            this.lock.unlock();
        }
    }
    
    public void updateStatistics() {
        this.lock.lock();
        try {
            this.statistics.total = this.taskCount();
            this.statistics.finished = this.finishedCount();
            this.statistics.errors = this.errorCount();
            switch (this.getStatus()) {
                case complete:
                case failed:
                case cancelled: {
                    this.statistics.suppressTimeRemaining();
                    break;
                }
            }
        }
        finally {
            this.lock.unlock();
        }
        this.lock.unlock();
    }
    
    public Statistic statistic() {
        this.lock.lock();
        try {
            return (this.statistics == null) ? null : new Statistic(this.statistics);
        }
        finally {
            this.lock.unlock();
        }
    }
    
    public void increment(final Stat stat) {
        this.lock.lock();
        try {
            if (this.statistics != null) {
                this.statistics.increment(stat);
            }
        }
        catch (Exception e) {
            Job.log.error(this.getId(), e);
            return;
        }
        finally {
            this.lock.unlock();
        }
        this.lock.unlock();
    }
    
    protected List<Task> convertList(final List list) {
        final List<Task> result = new ArrayList<Task>();
        for (final Object o : list) {
            result.add(new Task((IModelObject)o));
        }
        return result;
    }
    
    void init(final Type t) {
        final String dt = XDate.dateTime();
        final Transaction tr = Transaction.getCurrent();
        this.root.getCreateChild("en:type").setValue(t.name());
        this.root.getCreateChild("en:persistent").setValue("true");
        this.root.getCreateChild("en:transaction").setValue((tr == null) ? null : tr.getId());
        final IModelObject o = new Element("en:status");
        o.setValue(Status.created.name());
        o.setAttribute("tm", dt);
        this.root.addChild(o);
        this.root.getCreateChild("en:userid").setValue(User.getCurrent().getId());
        this.root.getCreateChild("en:created").setValue(dt);
        this.root.addChild(new Element("en:children"));
    }
    
    private ReentrantLock getLock() {
        ReentrantLock result = Job.locks.get(this.root);
        if (result == null) {
            result = new ReentrantLock();
            Job.locks.put(this.root, result);
        }
        return result;
    }
    
    public enum Stat
    {
        total("total", 0), 
        finished("finished", 1), 
        errors("errors", 2);
        
        private Stat(final String s, final int n) {
        }
    }
    
    public enum Status
    {
        created("created", 0), 
        open("open", 1), 
        complete("complete", 2), 
        failed("failed", 3), 
        cancelled("cancelled", 4);
        
        private Status(final String s, final int n) {
        }
    }
    
    public enum Type
    {
        admin("admin", 0), 
        rmi("rmi", 1), 
        deviceDiscovery("deviceDiscovery", 2), 
        deviceDeploy("deviceDeploy", 3), 
        deviceAudit("deviceAudit", 4), 
        deviceBaseline("deviceBaseline", 5), 
        policyPreview("policyPreview", 6), 
        policyDeploy("policyDeploy", 7), 
        policyAudit("policyAudit", 8), 
        compliance("compliance", 9), 
        test("test", 10);
        
        private Type(final String s, final int n) {
        }
    }
    
    public class Statistic
    {
        int total;
        int finished;
        int errors;
        long started;
        long samples;
        long lastUpdated;
        boolean str;
        
        Statistic() {
            this.total = 0;
            this.finished = 0;
            this.errors = 0;
            this.started = System.currentTimeMillis();
            this.samples = 0L;
            this.lastUpdated = 0L;
            this.str = false;
        }
        
        Statistic(final Statistic s) {
            this.total = 0;
            this.finished = 0;
            this.errors = 0;
            this.started = System.currentTimeMillis();
            this.samples = 0L;
            this.lastUpdated = 0L;
            this.str = false;
            this.total = s.total;
            this.finished = s.finished;
            this.errors = s.errors;
            this.started = s.started;
            this.samples = s.samples;
            this.lastUpdated = s.lastUpdated;
            this.str = s.str;
        }
        
        public int pct() {
            final float t = this.total;
            final float f = this.finished;
            float p = f / t * 100.0f;
            if (p > 100.0f) {
                p = 100.0f;
            }
            if (p < 1.0f) {
                p = 1.0f;
            }
            return (int)p;
        }
        
        public long timeRemaining() {
            if (this.str) {
                return 0L;
            }
            final int remaining = this.total - this.finished;
            return this.avg() * remaining;
        }
        
        public long avg() {
            if (this.samples == 0L) {
                return 2000L;
            }
            final long duration = this.lastUpdated - this.started;
            final float result = duration / this.samples;
            return (long)result;
        }
        
        public int total() {
            return this.total;
        }
        
        public int finished() {
            return this.finished;
        }
        
        public int errors() {
            return this.errors;
        }
        
        public String duration() {
            String unit = " (ms)";
            long result = this.lastUpdated - this.started;
            if (result < 0L) {
                result = 0L;
            }
            if (result > 1000L) {
                result /= 1000L;
                unit = " (sec)";
            }
            return String.valueOf(result) + unit;
        }
        
        void increment(final Stat stat) {
            switch (stat) {
                case total: {
                    ++this.total;
                    break;
                }
                case finished: {
                    ++this.finished;
                    ++this.samples;
                    this.lastUpdated = System.currentTimeMillis();
                    break;
                }
                case errors: {
                    ++this.errors;
                    break;
                }
            }
        }
        
        void suppressTimeRemaining() {
            this.str = true;
        }
    }
}
