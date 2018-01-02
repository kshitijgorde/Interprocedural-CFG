// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.tp.graph;

import java.util.Set;
import java.util.HashSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.ArrayList;
import org.xmodel.log.Log;
import java.util.List;

public class Router
{
    private final GraphObject parent;
    private final List<Route> routes;
    public static final Log log;
    
    static {
        log = Log.getLog(Router.class);
    }
    
    public Router(final GraphObject parent) {
        this.routes = new ArrayList<Route>();
        this.parent = parent;
    }
    
    public void add(final Route r) {
        this.lock(Mutex.LM.Write);
        try {
            this.routes.add(r);
        }
        finally {
            this.unlock(Mutex.LM.Write);
        }
        this.unlock(Mutex.LM.Write);
    }
    
    public void delete(final Route r) {
        this.lock(Mutex.LM.Write);
        try {
            this.routes.remove(r);
        }
        finally {
            this.unlock(Mutex.LM.Write);
        }
        this.unlock(Mutex.LM.Write);
    }
    
    public void clear() {
        this.lock(Mutex.LM.Write);
        try {
            this.routes.clear();
        }
        finally {
            this.unlock(Mutex.LM.Write);
        }
        this.unlock(Mutex.LM.Write);
    }
    
    public boolean isEmpty() {
        return this.routes.isEmpty();
    }
    
    public boolean hasRoutes() {
        return this.routes.size() > 0;
    }
    
    @Override
    public String toString() {
        this.lock(Mutex.LM.Write);
        try {
            final StringBuilder sb = new StringBuilder();
            sb.append("Routes:\n");
            for (final Route r : this.routes) {
                sb.append('\t');
                sb.append(r);
            }
            return sb.toString();
        }
        finally {
            this.unlock(Mutex.LM.Write);
        }
    }
    
    Collection<Interface> getInterfaces(final Packet packet) {
        final Set<Interface> result = new HashSet<Interface>();
        for (final Route r : this.routes) {
            if (r.matches(packet)) {
                result.addAll(r.interfaces());
                Router.log.debug("Route: " + r + " - matched.");
            }
        }
        return result;
    }
    
    List<Route> getRoutes() {
        return this.routes;
    }
    
    private void lock(final Mutex.LM mode) {
        this.parent.lock(mode);
    }
    
    private void unlock(final Mutex.LM mode) {
        this.parent.unlock(mode);
    }
}
