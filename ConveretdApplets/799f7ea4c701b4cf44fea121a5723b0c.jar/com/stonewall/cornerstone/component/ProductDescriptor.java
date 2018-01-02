// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.component;

import org.xmodel.Xlate;
import java.io.InputStream;
import java.net.URL;
import com.stonewall.cornerstone.utility.ModelBuilder;
import org.xmodel.IModelObject;
import org.xmodel.log.Log;

public class ProductDescriptor
{
    private String name;
    private String version;
    private String build;
    private String timestamp;
    private Product product;
    public static ProductDescriptor inst;
    static final Log log;
    
    static {
        ProductDescriptor.inst = null;
        log = Log.getLog(ProductDescriptor.class);
    }
    
    public static ProductDescriptor getInstance() {
        if (ProductDescriptor.inst == null) {
            ProductDescriptor.inst = new ProductDescriptor();
        }
        return ProductDescriptor.inst;
    }
    
    public ProductDescriptor() {
        this.name = "";
        this.version = "0.0";
        this.build = "";
        this.timestamp = "";
        this.product = null;
        final IModelObject root = this.getRoot();
        this.init(root);
    }
    
    public ProductDescriptor(final IModelObject e) {
        this.name = "";
        this.version = "0.0";
        this.build = "";
        this.timestamp = "";
        this.product = null;
        this.init(e);
    }
    
    public String getVersion() {
        return this.version;
    }
    
    public String getMajor() {
        return this.version.substring(0, this.version.indexOf(46));
    }
    
    public String getMinor() {
        return this.version.substring(this.version.indexOf(46) + 1);
    }
    
    public String getBuild() {
        return this.build;
    }
    
    public String getTimestamp() {
        return this.timestamp;
    }
    
    public Product getProduct() {
        return this.product;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(this.name);
        sb.append(" version: ");
        sb.append(this.version);
        if (this.build != null && this.build.length() > 0) {
            sb.append(" (");
            sb.append(this.build);
            sb.append(')');
        }
        sb.append(" created: ");
        sb.append(this.timestamp);
        return sb.toString();
    }
    
    public IModelObject getRoot() {
        try {
            final URL resource = this.getClass().getResource("product.xml");
            final InputStream istr = resource.openStream();
            final ModelBuilder builder = new ModelBuilder();
            final IModelObject root = builder.buildModel(istr);
            return root;
        }
        catch (Exception e) {
            ProductDescriptor.log.error("reading", e);
            return null;
        }
    }
    
    @Override
    public int hashCode() {
        final String s = String.valueOf(this.product.name()) + this.version;
        return s.hashCode();
    }
    
    public boolean equals(final ProductDescriptor pd) {
        return this.hashCode() == pd.hashCode();
    }
    
    private void init(final IModelObject root) {
        this.name = Xlate.get(root.getFirstChild("en:name"), (String)null);
        this.product = Product.valueOf(root.getID());
        this.version = Xlate.get(root.getFirstChild("en:version"), (String)null);
        final IModelObject tag = root.getFirstChild("en:build");
        this.build = Xlate.get(tag, (String)null);
        this.timestamp = Xlate.get(tag, "timestamp", (String)null);
    }
    
    public enum Product
    {
        unknown("unknown", 0, 0), 
        cornerstone("cornerstone", 1, 1);
        
        int value;
        
        private Product(final String s, final int n2, final int n) {
            this.value = 0;
            this.value = n;
        }
        
        public int value() {
            return this.value;
        }
    }
}
