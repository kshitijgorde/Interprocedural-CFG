// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.dsp.packaging;

import org.xmodel.xml.IXmlIO;
import com.stonewall.cornerstone.utility.ModelBuilder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.xmodel.Xlate;
import org.xmodel.Element;
import com.stonewall.cornerstone.component.ProductDescriptor;
import org.xmodel.IModelObject;

public class DeviceDef
{
    private IModelObject root;
    private ProductDescriptor productDescriptor;
    
    public DeviceDef(final IModelObject e, final IModelObject sw, final IModelObject vendorDef, final ProductDescriptor pd) {
        (this.root = new Element("dsp:deviceDef")).setAttribute("hardware", Xlate.get(e, "hardware", (String)null));
        this.root.setAttribute("vendor", Xlate.get(vendorDef, "id", (String)null));
        this.root.addChild(sw.cloneTree());
        final IModelObject vDef = vendorDef.cloneTree();
        this.setProtocols(e, vDef);
        this.setAdminMode(e, vDef);
        this.setResourceGroupId(e, vDef);
        this.productDescriptor = pd;
    }
    
    public String getSoftware() {
        return Xlate.get(this.root.getFirstChild("dsp:software"), (String)null);
    }
    
    public String getHardware() {
        return Xlate.get(this.root, "hardware", (String)null);
    }
    
    public String getVendor() {
        return Xlate.get(this.root, "vendor", (String)null);
    }
    
    private void setProtocols(final IModelObject def, final IModelObject vendorDef) {
        List<IModelObject> pIModelObjects = def.getChildren("dsp:protocol");
        if (pIModelObjects == null || pIModelObjects.isEmpty()) {
            pIModelObjects = vendorDef.getChildren("dsp:protocol");
        }
        for (final IModelObject p : pIModelObjects) {
            this.root.addChild(p.cloneTree());
        }
    }
    
    public List<String> getProtocols() {
        final List<String> protocols = new ArrayList<String>();
        final List<IModelObject> pIModelObjects = this.root.getChildren("dsp:protocol");
        for (final IModelObject e : pIModelObjects) {
            protocols.add(Xlate.get(e, (String)null));
        }
        return protocols;
    }
    
    private void setResourceGroupId(final IModelObject def, final IModelObject vendorDef) {
        IModelObject resourceGroupId = def.getFirstChild("dsp:resourceGroupId");
        if (resourceGroupId == null) {
            resourceGroupId = vendorDef.getFirstChild("dsp:resourceGroupId");
        }
        this.root.addChild(resourceGroupId.cloneTree());
    }
    
    public String getResourceGroupId() {
        return Xlate.get(this.root.getFirstChild("dsp:resourceGroupId"), (String)null);
    }
    
    private void setAdminMode(final IModelObject def, final IModelObject vendorDef) {
        IModelObject adminMode = def.getFirstChild("dsp:adminMode");
        if (adminMode == null) {
            adminMode = vendorDef.getFirstChild("dsp:adminMode");
        }
        this.root.addChild(adminMode.cloneTree());
    }
    
    public String getAdminMode() {
        return Xlate.get(this.root.getFirstChild("dsp:adminMode"), (String)null);
    }
    
    public ProductDescriptor getProductDescriptor() {
        return this.productDescriptor;
    }
    
    public IModelObject getRoot() {
        return this.root;
    }
    
    @Override
    public String toString() {
        final ModelBuilder builder = new ModelBuilder();
        return builder.writeModel(this.root, IXmlIO.Style.printable);
    }
}
