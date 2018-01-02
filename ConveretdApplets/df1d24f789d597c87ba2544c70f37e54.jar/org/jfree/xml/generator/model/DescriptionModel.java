// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.xml.generator.model;

import org.jfree.util.Log;
import java.util.HashMap;
import java.util.ArrayList;

public class DescriptionModel
{
    private ArrayList sources;
    private ArrayList classes;
    private HashMap classesMap;
    private MappingModel mappingModel;
    private Comments modelComments;
    private HashMap includeComments;
    
    public DescriptionModel() {
        this.classes = new ArrayList();
        this.classesMap = new HashMap();
        this.mappingModel = new MappingModel();
        this.sources = new ArrayList();
        this.includeComments = new HashMap();
    }
    
    public void addClassDescription(final ClassDescription classDescription) {
        this.classesMap.put(classDescription.getObjectClass(), classDescription);
        if (!this.classes.contains(classDescription)) {
            this.classes.add(classDescription);
        }
    }
    
    public void removeClassDescription(final ClassDescription classDescription) {
        this.classesMap.remove(classDescription.getObjectClass());
        this.classes.remove(classDescription);
    }
    
    public ClassDescription get(final int n) {
        return this.classes.get(n);
    }
    
    public ClassDescription get(final Class clazz) {
        return this.classesMap.get(clazz);
    }
    
    public int size() {
        return this.classes.size();
    }
    
    public MappingModel getMappingModel() {
        return this.mappingModel;
    }
    
    public void addSource(final String s) {
        this.sources.add(s);
    }
    
    public String[] getSources() {
        return this.sources.toArray(new String[this.sources.size()]);
    }
    
    public void prune() {
        final ClassDescription[] array = this.classes.toArray(new ClassDescription[0]);
        for (int i = 0; i < array.length; ++i) {
            if (array[i].isUndefined()) {
                this.removeClassDescription(array[i]);
                Log.debug("Pruned: " + array[i].getName());
            }
        }
    }
    
    public void addIncludeComment(final String s, final Comments comments) {
        this.includeComments.put(s, comments);
    }
    
    public Comments getIncludeComment(final String s) {
        return this.includeComments.get(s);
    }
    
    public Comments getModelComments() {
        return this.modelComments;
    }
    
    public void setModelComments(final Comments modelComments) {
        Log.debug("Model: Comment set: " + modelComments);
        this.modelComments = modelComments;
    }
}
