// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.tp.graph;

import java.util.Comparator;
import java.util.Iterator;
import java.util.HashSet;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;
import org.xmodel.log.Log;
import java.util.Stack;
import java.util.Map;
import java.util.List;
import java.util.Collection;

public class BasicWalker
{
    private static final Collection<Packet> nopacket;
    private long fs;
    private Collection<Packet> packets;
    private final Graph graph;
    private final List<Constraint> constraints;
    private final Map<String, Frame> fmidx;
    private final Stack<Frame> stack;
    private Collection<Endpoint> filter;
    private final FrameCollection paths;
    public static final Log log;
    
    static {
        nopacket = Collections.singletonList((Object)null);
        log = Log.getLog(BasicWalker.class);
    }
    
    public BasicWalker(final Graph graph) {
        this.fs = 0L;
        this.packets = BasicWalker.nopacket;
        this.constraints = new ArrayList<Constraint>();
        this.fmidx = new HashMap<String, Frame>();
        this.stack = new Stack<Frame>();
        this.filter = new HashSet<Endpoint>();
        this.paths = new FrameCollection();
        this.graph = graph;
    }
    
    public void start(final String eid) {
        this.graph.lock(Mutex.LM.Read);
        try {
            this.start(this.graph.getEndpoint(eid));
        }
        finally {
            this.graph.unlock(Mutex.LM.Read);
        }
        this.graph.unlock(Mutex.LM.Read);
    }
    
    public void start(final Endpoint endpoint) {
        this.start(endpoint, Constraint.Direction.Outbound);
    }
    
    public void start(final Endpoint endpoint, final Constraint.Direction direction) {
        this.start(endpoint, direction, null);
    }
    
    public void start(final Endpoint endpoint, final Constraint.Direction direction, final Link link) {
        this.init();
        this.graph.lock(Mutex.LM.Read);
        try {
            for (final Packet p : this.packets) {
                this.start(endpoint, direction, link, p);
            }
        }
        finally {
            this.graph.unlock(Mutex.LM.Read);
        }
        this.graph.unlock(Mutex.LM.Read);
    }
    
    public void add(final Constraint c) {
        this.constraints.add(c);
    }
    
    public void clearConstraints() {
        this.constraints.clear();
    }
    
    public void clearPaths() {
        this.paths.clear();
    }
    
    public FrameCollection getPaths() {
        return this.paths;
    }
    
    public List<Frame> markPath() {
        final List<Frame> path = this.getPath();
        BasicWalker.log.debug(path);
        this.paths.add(path);
        return path;
    }
    
    public Collection<Packet> getPackets() {
        return this.packets;
    }
    
    public void setPacket(final Packet p) {
        this.packets = Collections.singletonList(p);
    }
    
    public void setPackets(final Collection<Packet> p) {
        this.packets = (p.isEmpty() ? BasicWalker.nopacket : p);
    }
    
    public Graph getGraph() {
        return this.graph;
    }
    
    public boolean stackContains(final Endpoint endpoint) {
        boolean result = false;
        for (final Frame f : this.stack) {
            if (f.endpoint() == endpoint) {
                result = true;
                break;
            }
        }
        return result;
    }
    
    public Collection<Endpoint> filter() {
        return this.filter;
    }
    
    public void filter(final List<Frame> path) {
        this.filter.clear();
        for (final Frame f : path) {
            this.filter.add(f.endpoint());
        }
    }
    
    public void filter(final Collection<Endpoint> c) {
        this.filter.clear();
        this.filter.addAll(c);
    }
    
    private void init() {
        this.fs = 0L;
        this.fmidx.clear();
        this.stack.clear();
        this.paths.clear();
    }
    
    private void start(final Endpoint endpoint, final Constraint.Direction direction, final Link link, final Packet p) {
        this.graph.lock(Mutex.LM.Read);
        try {
            final Frame frame = new Frame(endpoint, direction, 0);
            frame.link(link);
            frame.setPacket(p);
            this.stack.push(frame);
            this.walk();
        }
        finally {
            this.graph.unlock(Mutex.LM.Read);
        }
        this.graph.unlock(Mutex.LM.Read);
    }
    
    private void walk() {
        while (!this.stack.empty()) {
            final Frame frame = this.stack.peek();
            if (frame.pending()) {
                this.process(frame);
            }
            else {
                this.pop();
            }
        }
    }
    
    private void process(final Frame frame) {
        switch (frame.direction()) {
            case Outbound: {
                this.walkOutbound(frame);
                break;
            }
            case Inbound: {
                this.walkInbound(frame);
                break;
            }
        }
        frame.processed();
    }
    
    private void walkOutbound(final Frame frame) {
        BasicWalker.log.debug(BasicWalker.log.isLevelEnabled(32) ? frame.toString("F/") : "");
        if (this.matchConstraint(frame)) {
            return;
        }
        Frame next = null;
        final Endpoint endpoint = frame.endpoint();
        for (final Link outbound : endpoint.getLinks(frame.link())) {
            final Endpoint otherEnd = outbound.otherEnd(endpoint);
            switch (otherEnd.type()) {
                case Network: {
                    next = new Frame(otherEnd, Constraint.Direction.Inbound, frame.metric());
                    next.setPacket(frame.getPacket());
                    next.depth(frame.depth() + 1);
                    next.link(outbound);
                    break;
                }
                case Host: {
                    next = new Frame(otherEnd, Constraint.Direction.Inbound, frame.metric());
                    next.setPacket(frame.getPacket());
                    next.depth(frame.depth() + 1);
                    next.link(outbound);
                    break;
                }
                case Interface: {
                    next = new Frame(otherEnd, Constraint.Direction.Inbound, frame.metric() + 1);
                    next.setPacket(frame.getPacket());
                    next.depth(frame.depth() + 1);
                    next.link(outbound);
                    break;
                }
            }
            this.push(next);
        }
    }
    
    private void walkInbound(final Frame frame) {
        BasicWalker.log.debug(BasicWalker.log.isLevelEnabled(32) ? frame.toString("F/") : "");
        if (this.matchConstraint(frame)) {
            return;
        }
        Frame next = null;
        final Endpoint endpoint = frame.endpoint();
        switch (endpoint.type()) {
            case Network: {
                next = new Frame(endpoint, Constraint.Direction.Outbound, frame.metric());
                next.setPacket(frame.getPacket());
                next.depth(frame.depth() + 1);
                next.link(frame.link());
                this.push(next);
                break;
            }
            case Interface: {
                this.routePacket(frame);
                break;
            }
        }
    }
    
    private void routePacket(final Frame frame) {
        final Endpoint endpoint = frame.endpoint();
        final Interface inbound = (Interface)endpoint;
        final Node node = inbound.getNode();
        final Packet packet = frame.getPacket();
        Collection<Packet> packets = (Collection<Packet>)Collections.emptyList();
        Collection<Interface> interfaces = (Collection<Interface>)Collections.emptyList();
        if (node.hasTransport()) {
            interfaces = ((inbound.getLinkCount() > 1) ? node.getInterfaces(packet) : node.getInterfaces(inbound, packet));
        }
        final NatPolicy nat = node.nat();
        for (final Interface i : interfaces) {
            packets = nat.xlate(inbound.getId(), i.getId(), packet);
            for (final Packet p : packets) {
                final Frame next = new Frame(i, Constraint.Direction.Outbound, frame.metric());
                next.setPacket(p);
                next.depth(frame.depth() + 1);
                next.link(frame.link());
                this.push(next);
            }
        }
    }
    
    private boolean matchConstraint(final Frame frame) {
        boolean result = false;
        for (final Constraint c : this.constraints) {
            if (c.match(this, frame)) {
                result = true;
                break;
            }
        }
        return result;
    }
    
    private void push(final Frame f) {
        if (this.filterMatched(f) && this.notDupFrame(f)) {
            f.sequence(this.fs++);
            this.stack.push(f);
            this.fmidx.put(this.toKey(f), f);
        }
    }
    
    private boolean filterMatched(final Frame f) {
        return this.filter.isEmpty() || this.filter.contains(f.endpoint());
    }
    
    private boolean notDupFrame(final Frame f) {
        return !this.isDupFrame(f);
    }
    
    private boolean isDupFrame(final Frame f) {
        return f.direction() == Constraint.Direction.Outbound && this.fmidx.containsKey(this.toKey(f));
    }
    
    private void pop() {
        final Frame f = this.stack.pop();
        BasicWalker.log.debug(BasicWalker.log.isLevelEnabled(32) ? f.toString("F/") : "");
        this.fmidx.remove(this.toKey(f));
    }
    
    private List<Frame> getPath() {
        Frame last = null;
        final List<Frame> result = new ArrayList<Frame>(this.stack.size());
        for (final Frame f : this.stack) {
            if (last != null && f.depth() == last.depth() + 1) {
                result.add(last);
            }
            last = f;
        }
        return result;
    }
    
    private String toKey(final Frame f) {
        return String.valueOf(f.endpoint().getId()) + ":" + f.direction().name() + ":" + f.getPacket();
    }
    
    class FrameComparator implements Comparator<Frame>
    {
        @Override
        public int compare(final Frame a, final Frame b) {
            if (b.depth() > a.depth()) {
                return -1;
            }
            if (b.depth() < a.depth()) {
                return 1;
            }
            if (b.sequence() > a.sequence()) {
                return -1;
            }
            if (b.sequence() < a.sequence()) {
                return 1;
            }
            return 0;
        }
    }
}
