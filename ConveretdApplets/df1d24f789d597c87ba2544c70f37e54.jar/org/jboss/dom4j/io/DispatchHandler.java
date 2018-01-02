// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.dom4j.io;

import org.jboss.dom4j.Element;
import org.jboss.dom4j.ElementPath;
import java.util.HashMap;
import java.util.ArrayList;
import org.jboss.dom4j.ElementHandler;

class DispatchHandler implements ElementHandler
{
    private boolean atRoot;
    private String path;
    private ArrayList pathStack;
    private ArrayList handlerStack;
    private HashMap handlers;
    private ElementHandler defaultHandler;
    
    public DispatchHandler() {
        this.atRoot = true;
        this.path = "/";
        this.pathStack = new ArrayList();
        this.handlerStack = new ArrayList();
        this.handlers = new HashMap();
    }
    
    public void addHandler(final String handlerPath, final ElementHandler handler) {
        this.handlers.put(handlerPath, handler);
    }
    
    public ElementHandler removeHandler(final String handlerPath) {
        return this.handlers.remove(handlerPath);
    }
    
    public boolean containsHandler(final String handlerPath) {
        return this.handlers.containsKey(handlerPath);
    }
    
    public ElementHandler getHandler(final String handlerPath) {
        return this.handlers.get(handlerPath);
    }
    
    public int getActiveHandlerCount() {
        return this.handlerStack.size();
    }
    
    public void setDefaultHandler(final ElementHandler handler) {
        this.defaultHandler = handler;
    }
    
    public void resetHandlers() {
        this.atRoot = true;
        this.path = "/";
        this.pathStack.clear();
        this.handlerStack.clear();
        this.handlers.clear();
        this.defaultHandler = null;
    }
    
    public String getPath() {
        return this.path;
    }
    
    public void onStart(final ElementPath elementPath) {
        final Element element = elementPath.getCurrent();
        this.pathStack.add(this.path);
        if (this.atRoot) {
            this.path += element.getName();
            this.atRoot = false;
        }
        else {
            this.path = this.path + "/" + element.getName();
        }
        if (this.handlers != null && this.handlers.containsKey(this.path)) {
            final ElementHandler handler = this.handlers.get(this.path);
            this.handlerStack.add(handler);
            handler.onStart(elementPath);
        }
        else if (this.handlerStack.isEmpty() && this.defaultHandler != null) {
            this.defaultHandler.onStart(elementPath);
        }
    }
    
    public void onEnd(final ElementPath elementPath) {
        if (this.handlers != null && this.handlers.containsKey(this.path)) {
            final ElementHandler handler = this.handlers.get(this.path);
            this.handlerStack.remove(this.handlerStack.size() - 1);
            handler.onEnd(elementPath);
        }
        else if (this.handlerStack.isEmpty() && this.defaultHandler != null) {
            this.defaultHandler.onEnd(elementPath);
        }
        this.path = this.pathStack.remove(this.pathStack.size() - 1);
        if (this.pathStack.size() == 0) {
            this.atRoot = true;
        }
    }
}
