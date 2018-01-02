// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity;

import org.xmodel.Element;
import com.stonewall.cornerstone.utility.SecureDocument;
import java.io.OutputStream;
import org.xmodel.xml.IXmlIO;
import com.stonewall.cornerstone.utility.ModelBuilder;
import java.util.Collection;
import org.jdom.Namespace;
import java.util.List;
import java.util.ArrayList;
import com.stonewall.cornerstone.db.XDate;
import org.xmodel.Xlate;
import java.util.Iterator;
import java.util.Collections;
import java.util.WeakHashMap;
import org.xmodel.IModelObject;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class Task extends Entity
{
    public static final String tag;
    public static final String TaskParent = "en:children";
    final ReentrantLock lock;
    static Map<IModelObject, ReentrantLock> locks;
    
    static {
        tag = EntityFactory.EntityType.task.getQualifiedName();
        Task.locks = Collections.synchronizedMap(new WeakHashMap<IModelObject, ReentrantLock>());
    }
    
    public Task(final IModelObject root) {
        super(Task.tag);
        this.lock = this.getLock();
        this.root = root;
    }
    
    public Task() {
        super(Task.tag);
        this.lock = this.getLock();
        this.init();
    }
    
    @Override
    public String getId() {
        this.lock.lock();
        try {
            return super.getId();
        }
        finally {
            this.lock.unlock();
        }
    }
    
    @Override
    protected void setId() {
    }
    
    public EntityReference getParentReference() {
        EntityReference result = null;
        IModelObject parent = this.root.getParent();
        if (parent != null) {
            parent = this.root.getParent();
            result = new EntityReference(EntityFactory.EntityType.valueOf(parent), parent.getID());
        }
        return result;
    }
    
    public String getjid() {
        this.lock.lock();
        try {
            final String id = this.getId();
            final int end = id.indexOf(46);
            if (end > 0) {
                return id.substring(0, end);
            }
        }
        finally {
            this.lock.unlock();
        }
        this.lock.unlock();
        throw new IllegalStateException("malformed ID");
    }
    
    public String getpid() {
        this.lock.lock();
        try {
            final String id = this.getId();
            final int end = id.lastIndexOf(46);
            if (end > 0) {
                return id.substring(0, end);
            }
        }
        finally {
            this.lock.unlock();
        }
        this.lock.unlock();
        throw new IllegalStateException("malformed ID");
    }
    
    public void setId(final String pid, int suffix) {
        final String id = String.valueOf(pid) + "." + suffix;
        super.setId(id);
        suffix = 0;
        for (final Task t : this.getChildren()) {
            t.setId(id, ++suffix);
        }
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
    
    public String getItinerary() {
        this.lock.lock();
        try {
            return Xlate.get(this.root.getFirstChild("en:itinerary"), (String)null);
        }
        finally {
            this.lock.unlock();
        }
    }
    
    public void setItinerary(final String itinerary) {
        this.lock.lock();
        try {
            this.root.getFirstChild("en:itinerary").setValue(itinerary);
        }
        finally {
            this.lock.unlock();
        }
        this.lock.unlock();
    }
    
    public String getState() {
        this.lock.lock();
        try {
            return Xlate.get(this.root.getFirstChild("en:state"), (String)null);
        }
        finally {
            this.lock.unlock();
        }
    }
    
    public String getStateTimestamp() {
        this.lock.lock();
        try {
            return Xlate.get(this.root.getFirstChild("en:state"), "tm", (String)null);
        }
        finally {
            this.lock.unlock();
        }
    }
    
    public void setState(final String state) {
        this.lock.lock();
        try {
            final IModelObject stateRoot = this.root.getFirstChild("en:state");
            stateRoot.setValue(state);
            stateRoot.setAttribute("tm", XDate.dateTime());
        }
        finally {
            this.lock.unlock();
        }
        this.lock.unlock();
    }
    
    public void setState(final State state) {
        this.setState(state.toString());
    }
    
    public boolean getFinished() {
        this.lock.lock();
        try {
            final IModelObject it = this.root.getFirstChild("en:itinerary");
            final String flag = Xlate.get(it, "finished", (String)null);
            return Boolean.valueOf(flag);
        }
        finally {
            this.lock.unlock();
        }
    }
    
    public void setFinished(final boolean flag) {
        this.lock.lock();
        try {
            final IModelObject it = this.root.getFirstChild("en:itinerary");
            it.setAttribute("finished", Boolean.toString(flag));
        }
        finally {
            this.lock.unlock();
        }
        this.lock.unlock();
    }
    
    public String getCorrelation() {
        this.lock.lock();
        try {
            final String suffix = Long.toHexString(this.getId().hashCode());
            final TaskCorrelation tc = new TaskCorrelation(this.getId(), suffix);
            return tc.toString();
        }
        finally {
            this.lock.unlock();
        }
    }
    
    public int getPriority() {
        this.lock.lock();
        try {
            return Integer.parseInt(Xlate.get(this.root.getFirstChild("en:priority"), (String)null));
        }
        finally {
            this.lock.unlock();
        }
    }
    
    public void setPriority(final int p) {
        this.lock.lock();
        try {
            this.root.getFirstChild("en:priority").setValue(String.valueOf(p));
        }
        finally {
            this.lock.unlock();
        }
        this.lock.unlock();
    }
    
    public IModelObject getContent() {
        this.lock.lock();
        try {
            return this.root.getFirstChild("en:content");
        }
        finally {
            this.lock.unlock();
        }
    }
    
    public IModelObject getContent(final int index) {
        this.lock.lock();
        try {
            return this.getContent().getChild(index);
        }
        finally {
            this.lock.unlock();
        }
    }
    
    public IModelObject getContent(final String name) {
        final ArrayList<IModelObject> list = new ArrayList<IModelObject>(1);
        this.getContent(name, list);
        return (list.size() > 0) ? list.get(0) : null;
    }
    
    public List<IModelObject> getContent(final String name, final List<IModelObject> result) {
        int n = 0;
        this.lock.lock();
        try {
            final List<IModelObject> list = this.getContent().getChildren(name);
            for (final IModelObject o : list) {
                ++n;
                result.add(o);
            }
        }
        finally {
            this.lock.unlock();
        }
        this.lock.unlock();
        if (n == 0) {
            final Task p = this.getParent();
            if (p != null) {
                p.getContent(name, result);
            }
        }
        return result;
    }
    
    public IModelObject getContent(final String name, final Namespace ns) {
        final ArrayList<IModelObject> list = new ArrayList<IModelObject>(1);
        this.getContent(name, ns, list);
        return (list.size() > 0) ? list.get(0) : null;
    }
    
    public List<IModelObject> getContent(final String name, final Namespace ns, final List<IModelObject> result) {
        int n = 0;
        this.lock.lock();
        try {
            final List<IModelObject> list = this.getContent().getChildren(name);
            for (final IModelObject o : list) {
                ++n;
                result.add(o);
            }
        }
        finally {
            this.lock.unlock();
        }
        this.lock.unlock();
        if (n == 0) {
            final Task p = this.getParent();
            if (p != null) {
                p.getContent(name, ns, result);
            }
        }
        return result;
    }
    
    public void addContent(final IModelObject content) {
        this.lock.lock();
        try {
            this.root.getFirstChild("en:content").addChild(content);
        }
        finally {
            this.lock.unlock();
        }
        this.lock.unlock();
    }
    
    public void setContent(final IModelObject content) {
        this.lock.lock();
        try {
            this.root.getFirstChild("en:content").addChild(content);
        }
        finally {
            this.lock.unlock();
        }
        this.lock.unlock();
    }
    
    public void setContent(final Collection<IModelObject> c) {
        this.lock.lock();
        try {
            for (final IModelObject o : c) {
                this.root.getFirstChild("en:content").addChild(o);
            }
        }
        finally {
            this.lock.unlock();
        }
        this.lock.unlock();
    }
    
    public void addContent(final Collection<IModelObject> c) {
        this.lock.lock();
        try {
            for (final IModelObject o : c) {
                this.root.getFirstChild("en:content").addChild(o);
            }
        }
        finally {
            this.lock.unlock();
        }
        this.lock.unlock();
    }
    
    public void setAction(final String action) {
        this.lock.lock();
        try {
            this.root.getFirstChild("en:action").setValue(action);
        }
        finally {
            this.lock.unlock();
        }
        this.lock.unlock();
    }
    
    public String getAction() {
        this.lock.lock();
        try {
            return Xlate.get(this.root.getFirstChild("en:action"), (String)null);
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
            this.root.getFirstChild("en:action").setAttribute("accessGroup", ag);
        }
        finally {
            this.lock.unlock();
        }
        this.lock.unlock();
    }
    
    public String getAccessGroup() {
        this.lock.lock();
        try {
            return Xlate.get(this.root.getFirstChild("en:action"), "accessGroup", (String)null);
        }
        finally {
            this.lock.unlock();
        }
    }
    
    public Task getParent() {
        Task result = null;
        this.lock.lock();
        try {
            IModelObject p = this.root;
            for (int i = 0; i < 2; ++i) {
                p = p.getParent();
                if (p == null) {
                    break;
                }
            }
            if (p != null && p.getType().equals(Task.tag)) {
                result = new Task(p);
            }
        }
        finally {
            this.lock.unlock();
        }
        this.lock.unlock();
        return result;
    }
    
    public List<Task> getChildren() {
        final List<Task> result = new ArrayList<Task>();
        this.lock.lock();
        try {
            for (final Object o : this.root.getFirstChild("en:children").getChildren("en:task")) {
                final Task t = new Task((IModelObject)o);
                result.add(t);
            }
        }
        finally {
            this.lock.unlock();
        }
        this.lock.unlock();
        return result;
    }
    
    public List<Task> getReadyChildren() {
        final List<Task> result = new ArrayList<Task>();
        for (final Task t : this.getChildren()) {
            if (t.getFinished()) {
                continue;
            }
            result.add(t);
        }
        return result;
    }
    
    public void addChild(final Task task) {
        this.lock.lock();
        try {
            final String id = this.getId();
            final IModelObject taskParent = this.root.getFirstChild("en:children");
            int suffix = taskParent.getChildren("en:task").size();
            task.setId(String.valueOf(id) + "." + String.valueOf(++suffix));
            taskParent.addChild(task.root);
        }
        finally {
            this.lock.unlock();
        }
        this.lock.unlock();
    }
    
    public void update(final Task ds) {
        this.lock.lock();
        try {
            final IModelObject myChildren = this.root.getFirstChild("en:children");
            myChildren.removeFromParent();
            this.root.removeChildren();
            final List<IModelObject> content = new ArrayList<IModelObject>(ds.getPrunedRoot().getChildren());
            for (final IModelObject o : content) {
                this.root.addChild(o);
            }
            this.root.removeChildren("en:children");
            this.root.addChild(myChildren);
        }
        finally {
            this.lock.unlock();
        }
        this.lock.unlock();
    }
    
    public State state() {
        this.lock.lock();
        try {
            return State.value(this.getState());
        }
        finally {
            this.lock.unlock();
        }
    }
    
    public boolean nested() {
        this.lock.lock();
        try {
            final IModelObject p = this.root.getParent();
            if (p.getType().equals(Task.tag)) {
                return true;
            }
        }
        catch (Exception e) {
            Task.log.debug(this, e);
            return false;
        }
        finally {
            this.lock.unlock();
        }
        this.lock.unlock();
        return false;
    }
    
    public boolean notNested() {
        this.lock.lock();
        try {
            return !this.nested();
        }
        finally {
            this.lock.unlock();
        }
    }
    
    public boolean waiting() {
        this.lock.lock();
        try {
            return this.state() == State.wait;
        }
        finally {
            this.lock.unlock();
        }
    }
    
    public boolean complete() {
        this.lock.lock();
        try {
            return this.state() == State.complete && this.getFinished();
        }
        finally {
            this.lock.unlock();
        }
    }
    
    public boolean customState() {
        this.lock.lock();
        try {
            return this.state() == State.custom;
        }
        finally {
            this.lock.unlock();
        }
    }
    
    public boolean failed() {
        this.lock.lock();
        try {
            return this.state() == State.failed;
        }
        finally {
            this.lock.unlock();
        }
    }
    
    public boolean waitFailed() {
        boolean result = false;
        final List<Task> tasks = this.getChildren();
        for (final Task child : tasks) {
            if (child.failed() && child.getFinished()) {
                result = true;
                break;
            }
        }
        return result;
    }
    
    public void reset(final State state) {
        this.lock.lock();
        try {
            this.setState(state);
            this.setFinished(false);
        }
        finally {
            this.lock.unlock();
        }
        this.lock.unlock();
    }
    
    @Override
    public String toString() {
        this.lock.lock();
        try {
            this.lock.lock();
            try {
                final IModelObject pruned = this.root.cloneTree();
                pruned.removeChildren("task");
                final ModelBuilder builder = new ModelBuilder();
                return builder.writeModel(pruned, IXmlIO.Style.printable);
            }
            finally {
                this.lock.unlock();
            }
        }
        finally {
            this.lock.unlock();
        }
    }
    
    public void write(final OutputStream ostr, final boolean pruned) {
        this.lock.lock();
        try {
            IModelObject root = this.root;
            if (pruned) {
                root = this.getPrunedRoot();
            }
            final SecureDocument sd = new SecureDocument(root);
            sd.encrypt();
            final ModelBuilder builder = new ModelBuilder();
            builder.writeModel(sd.getRoot(), ostr, IXmlIO.Style.compact);
        }
        catch (Exception e) {
            Task.log.error(this, e);
            return;
        }
        finally {
            this.lock.unlock();
        }
        this.lock.unlock();
    }
    
    public IModelObject getPrunedRoot() {
        final IModelObject taskParent = this.root.getFirstChild("en:children");
        taskParent.removeFromParent();
        final IModelObject result = this.root.cloneTree();
        result.addChild(new Element("en:children"));
        this.root.addChild(taskParent);
        return result;
    }
    
    @Override
    public boolean equals(final Object o) {
        this.lock.lock();
        try {
            if (o instanceof Task) {
                final Task t = (Task)o;
                return this.getId().equals(t.getId());
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
    
    protected void init() {
        final String dt = XDate.dateTime();
        this.root.setAttribute("id", "0");
        this.root.getCreateChild("en:created").setValue(dt);
        this.root.getCreateChild("en:itinerary").setAttribute("finished", "false");
        final IModelObject state = new Element("en:state");
        state.setValue("created");
        state.setAttribute("tm", dt);
        this.root.addChild(state);
        this.root.getCreateChild("en:priority").setValue("0");
        this.root.addChild(new Element("en:action"));
        this.root.addChild(new Element("en:content"));
        this.root.addChild(new Element("en:children"));
    }
    
    private ReentrantLock getLock() {
        ReentrantLock result = Task.locks.get(this.root);
        if (result == null) {
            result = new ReentrantLock();
            Task.locks.put(this.root, result);
        }
        return result;
    }
    
    public enum State
    {
        created("created", 0), 
        open("open", 1), 
        custom("custom", 2), 
        failed("failed", 3), 
        complete("complete", 4), 
        cancelled("cancelled", 5), 
        wait("wait", 6);
        
        private State(final String s, final int n) {
        }
        
        public static State value(final String s) {
            try {
                return valueOf(s);
            }
            catch (Exception e) {
                return State.custom;
            }
        }
    }
}
