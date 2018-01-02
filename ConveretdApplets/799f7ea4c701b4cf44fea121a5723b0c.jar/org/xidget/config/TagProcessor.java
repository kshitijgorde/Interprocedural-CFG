// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.config;

import org.xmodel.PathSyntaxException;
import org.xmodel.xpath.expression.StatefulContext;
import org.xmodel.xpath.XPath;
import org.xmodel.Xlate;
import java.util.Collection;
import org.xmodel.IModelObject;
import java.util.Iterator;
import org.xidget.Log;
import java.util.Stack;
import java.util.ArrayList;
import org.xmodel.util.HashMultiMap;
import java.util.List;
import org.xmodel.util.MultiMap;
import org.xmodel.xpath.expression.IContext;

public class TagProcessor
{
    private IContext context;
    private ITagHandler parent;
    private ClassLoader loader;
    private MultiMap<String, ITagHandler> elementHandlers;
    private MultiMap<String, ITagHandler> attributeHandlers;
    private List<Object> roots;
    
    public TagProcessor() {
        this(null);
    }
    
    public TagProcessor(final ITagHandler parent) {
        this.loader = this.getClass().getClassLoader();
        this.parent = parent;
        this.elementHandlers = new HashMultiMap<String, ITagHandler>();
        this.attributeHandlers = new HashMultiMap<String, ITagHandler>();
        this.roots = new ArrayList<Object>();
    }
    
    public void setClassLoader(final ClassLoader loader) {
        this.loader = loader;
    }
    
    public ClassLoader getClassLoader() {
        return this.loader;
    }
    
    public ITagHandler getParent() {
        return this.parent;
    }
    
    public void addRoot(final Object o) {
        this.roots.add(o);
    }
    
    public IContext getContext() {
        return this.context;
    }
    
    public List<Object> process(final IContext context) throws TagException {
        return this.process(null, context);
    }
    
    public List<Object> process(final ITagHandler tagHandler, final IContext context) throws TagException {
        final long nanoTime = System.nanoTime();
        this.roots.clear();
        this.context = context;
        final Stack<Entry> stack = new Stack<Entry>();
        stack.push(new Entry(tagHandler, null, context.getObject(), false));
        while (!stack.empty()) {
            final Entry entry = stack.pop();
            if (entry.end) {
                entry.handler.exit(this, entry.parent, entry.node);
            }
            else if (entry.attribute) {
                this.processAttributeHandlers(stack, entry);
            }
            else {
                this.processElementHandlers(stack, entry);
            }
        }
        Log.printf("perf", "TagProcessor.process: time=%3.3fms\n", (System.nanoTime() - nanoTime) / 1000000.0f);
        return this.roots;
    }
    
    private void processAttributeHandlers(final Stack<Entry> stack, final Entry entry) throws TagException {
        final List<ITagHandler> value = this.attributeHandlers.get(entry.node.getType());
        if (value != null) {
            for (final ITagHandler tagHandler : value) {
                if (tagHandler.filter(this, entry.parent, entry.node)) {
                    tagHandler.enter(this, entry.parent, entry.node);
                    tagHandler.exit(this, entry.parent, entry.node);
                }
            }
        }
    }
    
    private void processElementHandlers(final Stack<Entry> stack, final Entry entry) throws TagException {
        this.replaceInserts(entry.node);
        final List<ITagHandler> handlers = this.getHandlers(entry.node);
        if (handlers == null) {
            final List<IModelObject> attributes = this.getAttributes(entry.node);
            for (int i = attributes.size() - 1; i >= 0; --i) {
                stack.add(new Entry(entry.parent, null, attributes.get(i), true, false));
            }
            final List<IModelObject> children = entry.node.getChildren();
            for (int j = children.size() - 1; j >= 0; --j) {
                stack.add(new Entry(entry.parent, null, children.get(j), false));
            }
        }
        else if (handlers.size() == 1) {
            final ITagHandler tagHandler = handlers.get(0);
            if (tagHandler.filter(this, entry.parent, entry.node) && tagHandler.enter(this, entry.parent, entry.node)) {
                final List<IModelObject> children2 = entry.node.getChildren();
                stack.push(new Entry(entry.parent, tagHandler, entry.node, true));
                final List<IModelObject> attributes2 = this.getAttributes(entry.node);
                for (int k = attributes2.size() - 1; k >= 0; --k) {
                    stack.add(new Entry(tagHandler, null, attributes2.get(k), true, false));
                }
                for (int l = children2.size() - 1; l >= 0; --l) {
                    stack.add(new Entry(tagHandler, null, children2.get(l), false));
                }
            }
        }
        else if (handlers.size() > 1) {
            final List<ITagHandler> process = this.process(handlers, entry);
            final List<IModelObject> children3 = entry.node.getChildren();
            for (final ITagHandler tagHandler2 : process) {
                stack.push(new Entry(entry.parent, tagHandler2, entry.node, true));
                final List<IModelObject> attributes3 = this.getAttributes(entry.node);
                for (int n = attributes3.size() - 1; n >= 0; --n) {
                    stack.add(new Entry(tagHandler2, null, attributes3.get(n), true, false));
                }
                for (int n2 = children3.size() - 1; n2 >= 0; --n2) {
                    stack.add(new Entry(tagHandler2, null, children3.get(n2), false));
                }
            }
        }
    }
    
    private void replaceInserts(final IModelObject modelObject) throws TagException {
        final ArrayList<IModelObject> list = (ArrayList<IModelObject>)new ArrayList<Object>(modelObject.getChildren());
        for (int n = 1, i = 0; i < list.size(); ++i, ++n) {
            final IModelObject modelObject2 = list.get(i);
            if (modelObject2.isType("insert")) {
                try {
                    final List<IModelObject> query = XPath.compileExpression(Xlate.get(modelObject2, "")).query(new StatefulContext(this.context, modelObject2), null);
                    modelObject2.removeFromParent();
                    --n;
                    for (final IModelObject modelObject3 : query) {
                        this.replaceInserts(modelObject3);
                        modelObject.addChild(modelObject3.cloneTree(), n++);
                    }
                }
                catch (PathSyntaxException ex) {
                    throw new TagException("Error in insert expression: ", ex);
                }
            }
        }
    }
    
    private List<ITagHandler> getHandlers(final IModelObject modelObject) {
        final List<ITagHandler> value = this.elementHandlers.get(null);
        final List<ITagHandler> value2 = this.elementHandlers.get(modelObject.getType());
        if (value == null) {
            return value2;
        }
        if (value2 == null) {
            return value;
        }
        final ArrayList<Object> list = (ArrayList<Object>)new ArrayList<ITagHandler>();
        list.addAll(value);
        list.addAll(this.elementHandlers.get(modelObject.getType()));
        return (List<ITagHandler>)list;
    }
    
    private List<ITagHandler> process(final List<ITagHandler> list, final Entry entry) throws TagException {
        final ArrayList<ITagHandler> list2 = new ArrayList<ITagHandler>();
        for (int i = list.size() - 1; i >= 0; --i) {
            final ITagHandler tagHandler = list.get(i);
            if (tagHandler.filter(this, entry.parent, entry.node) && tagHandler.enter(this, entry.parent, entry.node)) {
                list2.add(tagHandler);
            }
        }
        return list2;
    }
    
    private List<IModelObject> getAttributes(final IModelObject modelObject) {
        final ArrayList<IModelObject> list = new ArrayList<IModelObject>();
        final Iterator<String> iterator = modelObject.getAttributeNames().iterator();
        while (iterator.hasNext()) {
            list.add(modelObject.getAttributeNode(iterator.next()));
        }
        return list;
    }
    
    public void addHandler(final ITagHandler tagHandler) {
        this.addHandler(null, tagHandler);
    }
    
    public void removeHandler(final ITagHandler tagHandler) {
        this.removeHandler(null, tagHandler);
    }
    
    public void addHandler(final String s, final ITagHandler tagHandler) {
        this.elementHandlers.put(s, tagHandler);
    }
    
    public void removeHandler(final String s, final ITagHandler tagHandler) {
        this.elementHandlers.remove(s, tagHandler);
    }
    
    public void replaceHandler(final String s, final ITagHandler tagHandler) {
        final List<ITagHandler> handlers = this.getHandlers(s);
        if (handlers != null && handlers.size() > 0) {
            this.removeHandler(s, handlers.get(0));
        }
        this.addHandler(s, tagHandler);
    }
    
    public List<ITagHandler> getHandlers(final String s) {
        return this.elementHandlers.get(s);
    }
    
    public void addAttributeHandler(final String s, final ITagHandler tagHandler) {
        this.attributeHandlers.put(s, tagHandler);
    }
    
    public void removeAttibuteHandler(final String s, final ITagHandler tagHandler) {
        this.attributeHandlers.remove(s, tagHandler);
    }
    
    public List<ITagHandler> getAttibuteHandlers(final String s) {
        return this.attributeHandlers.get(s);
    }
    
    private class Entry
    {
        public ITagHandler parent;
        public ITagHandler handler;
        public IModelObject node;
        public boolean attribute;
        public boolean end;
        
        public Entry(final ITagHandler parent, final ITagHandler handler, final IModelObject node, final boolean end) {
            this.parent = parent;
            this.handler = handler;
            this.node = node;
            this.attribute = false;
            this.end = end;
        }
        
        public Entry(final ITagHandler parent, final ITagHandler handler, final IModelObject node, final boolean attribute, final boolean end) {
            this.parent = parent;
            this.handler = handler;
            this.node = node;
            this.attribute = attribute;
            this.end = end;
        }
    }
}
