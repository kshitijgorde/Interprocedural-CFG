// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel;

public class ModelRegistry implements IModelRegistry
{
    private static ThreadLocal<ModelRegistry> B;
    private static ThreadLocal<IModel> A;
    
    static {
        ModelRegistry.B = new ThreadLocal<ModelRegistry>();
    }
    
    public ModelRegistry() {
        ModelRegistry.B = new ThreadLocal<ModelRegistry>();
    }
    
    public IModel getModel() {
        if (ModelRegistry.A == null) {
            ModelRegistry.A = new ThreadLocal<IModel>();
        }
        IModel model = ModelRegistry.A.get();
        if (model == null) {
            model = new Model();
            ModelRegistry.A.set(model);
        }
        return model;
    }
    
    public IModelObject createCollection(final String s) {
        final ModelObject modelObject = new ModelObject(s);
        this.getModel().addRoot(s, modelObject);
        return modelObject;
    }
    
    public static ModelRegistry getInstance() {
        ModelRegistry modelRegistry = ModelRegistry.B.get();
        if (modelRegistry == null) {
            modelRegistry = new ModelRegistry();
            ModelRegistry.B.set(modelRegistry);
        }
        return modelRegistry;
    }
}
