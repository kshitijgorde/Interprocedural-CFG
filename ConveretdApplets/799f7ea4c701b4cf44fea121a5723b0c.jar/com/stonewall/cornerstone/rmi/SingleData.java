// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.rmi;

import org.xmodel.xml.IXmlIO;
import com.stonewall.cornerstone.utility.ModelBuilder;
import org.xmodel.Xlate;
import org.xmodel.Element;
import org.xmodel.IModelObject;

public class SingleData implements IData
{
    private IModelObject root;
    
    public SingleData(final IDataAdaptor adaptor, final Object param) {
        (this.root = new Element("rmi:singleData")).setAttribute("class", adaptor.getClassName(param));
        this.root.setValue(adaptor.transform(param));
    }
    
    public SingleData(final IModelObject imo) {
        this.root = imo;
    }
    
    @Override
    public Object getValue(final IDataAdaptor adaptor) {
        final String className = Xlate.get(this.root, "class", (String)null);
        return adaptor.transform(className, Xlate.get(this.root, (String)null));
    }
    
    @Override
    public IModelObject getRoot() {
        return this.root;
    }
    
    @Override
    public String toString() {
        final ModelBuilder builder = new ModelBuilder();
        return builder.writeModel(this.root, IXmlIO.Style.printable);
    }
}
