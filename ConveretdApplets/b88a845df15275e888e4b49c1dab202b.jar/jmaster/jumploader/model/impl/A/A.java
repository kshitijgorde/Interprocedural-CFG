// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.jumploader.model.impl.A;

import jmaster.jumploader.model.api.common.IAttribute;
import jmaster.jumploader.model.api.file.IFile;
import java.io.InputStream;
import java.net.URL;
import org.xml.sax.SAXException;
import org.xml.sax.Attributes;
import java.util.ArrayList;
import java.util.List;

public class A extends jmaster.util.E.A
{
    static final String D = "requiredMetadataOk";
    protected Long F;
    protected Long C;
    protected String I;
    protected List H;
    protected String B;
    protected String E;
    protected Boolean G;
    
    public A() {
        this.I = null;
        this.H = new ArrayList();
    }
    
    public Long F() {
        return this.F;
    }
    
    public void B(final Long f) {
        this.F = f;
    }
    
    public Long D() {
        return this.C;
    }
    
    public void A(final Long c) {
        this.C = c;
    }
    
    public String H() {
        return this.I;
    }
    
    public void B(final String i) {
        this.I = i;
    }
    
    public List C() {
        return this.H;
    }
    
    public void A(final List h) {
        this.H = h;
    }
    
    public String B() {
        return this.B;
    }
    
    public void D(final String b) {
        this.B = b;
    }
    
    public String I() {
        return this.E;
    }
    
    public void A(final String e) {
        this.E = e;
    }
    
    public void startElement(final String s, final String s2, final String s3, final Attributes attributes) throws SAXException {
        super.startElement(s, s2, s3, attributes);
        if ("metadata".equals(s3)) {
            this.F = new Long(attributes.getValue("width"));
            this.C = new Long(attributes.getValue("height"));
            this.I = attributes.getValue("title");
            this.B = attributes.getValue("ok");
            this.E = attributes.getValue("cancel");
        }
        if ("input".equals(s3)) {
            final B b = new B();
            b.B(attributes.getValue("type"));
            b.A(attributes.getValue("name"));
            b.C(attributes.getValue("label"));
            b.D(attributes.getValue("value"));
            try {
                b.A(new Long(attributes.getValue("rows")));
            }
            catch (Exception ex) {}
            b.A(new Boolean(attributes.getValue("required")));
            this.H.add(b);
        }
    }
    
    public void C(final String s) throws Exception {
        final InputStream inputStream = new URL(s).openConnection().getInputStream();
        try {
            this.A(inputStream);
        }
        finally {
            jmaster.util.F.A.A(inputStream);
        }
    }
    
    public boolean E() {
        if (this.G == null) {
            boolean b = false;
            for (int n = 0; !b && n < this.H.size(); ++n) {
                if (Boolean.TRUE.equals(((B)this.H.get(n)).B())) {
                    b = true;
                }
            }
            this.G = b;
        }
        return this.G;
    }
    
    public boolean G() {
        boolean b = true;
        if (this.E()) {
            for (int n = 0; b && n < this.H.size(); ++n) {
                final B b2 = this.H.get(n);
                if (Boolean.TRUE.equals(b2.B()) && jmaster.util.B.A.C(b2.I())) {
                    b = false;
                }
            }
        }
        return b;
    }
    
    public boolean A(final IFile file) {
        if (!this.E()) {
            return true;
        }
        boolean booleanValue = false;
        if (file.getAttributeSet() != null) {
            final IAttribute attributeByName = file.getAttributeSet().getAttributeByName("requiredMetadataOk");
            if (attributeByName != null && attributeByName.getValue() != null) {
                booleanValue = (boolean)attributeByName.getValue();
            }
        }
        return booleanValue;
    }
    
    public static boolean A(final IFile file, final boolean b) {
        final boolean b2 = false;
        if (file.getAttributeSet() != null) {
            final IAttribute attributeByName = file.getAttributeSet().getAttributeByName("requiredMetadataOk");
            if (attributeByName == null) {
                file.getAttributeSet().createAttribute("requiredMetadataOk", new Boolean(b)).setSendToServer(false);
            }
            else {
                attributeByName.setValue(new Boolean(b));
            }
        }
        return b2;
    }
}
