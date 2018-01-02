// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xsd.check;

import org.xmodel.xml.IXmlIO;
import org.xmodel.xml.XmlIO;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import org.xmodel.IModelObject;

public abstract class AbstractCheck implements ICheck
{
    private IModelObject A;
    protected IModelObject errorLocus;
    protected List<ICheck> errored;
    
    public AbstractCheck(final IModelObject a) {
        this.A = a;
    }
    
    @Override
    public boolean validate(final IModelObject errorLocus) {
        this.errorLocus = null;
        if (this.errored != null) {
            this.errored.clear();
        }
        if (!this.validateImpl(errorLocus) && this.errorLocus == null) {
            this.errorLocus = errorLocus;
        }
        return this.errorLocus == null;
    }
    
    protected abstract boolean validateImpl(final IModelObject p0);
    
    protected void addFailed(final ICheck check) {
        if (this.errored == null) {
            this.errored = new ArrayList<ICheck>();
        }
        this.errored.add(check);
    }
    
    @Override
    public IModelObject getSchemaLocus() {
        return this.A;
    }
    
    @Override
    public void getErrors(final List<SchemaError> list) {
        final List<ICheck> failed = this.getFailed();
        if (failed != null && failed.size() > 0) {
            final Iterator<ICheck> iterator = failed.iterator();
            while (iterator.hasNext()) {
                iterator.next().getErrors(list);
            }
        }
    }
    
    public List<ICheck> getFailed() {
        return this.errored;
    }
    
    @Override
    public String toString() {
        final XmlIO xmlIO = new XmlIO();
        xmlIO.setOutputStyle(IXmlIO.Style.printable);
        if (this.errorLocus == null) {
            final StringBuilder sb = new StringBuilder();
            sb.append("PASS:\n");
            sb.append(xmlIO.write(this.A));
            sb.append('\n');
            return sb.toString();
        }
        if (this.errored != null && this.errored.size() > 0) {
            final StringBuilder sb2 = new StringBuilder();
            final Iterator<ICheck> iterator = this.errored.iterator();
            while (iterator.hasNext()) {
                sb2.append(iterator.next().toString());
            }
            return sb2.toString();
        }
        final StringBuilder sb3 = new StringBuilder();
        sb3.append("FAIL:\n");
        sb3.append(xmlIO.write(this.A));
        sb3.append('\n');
        sb3.append(xmlIO.write(this.errorLocus));
        sb3.append('\n');
        return sb3.toString();
    }
}
