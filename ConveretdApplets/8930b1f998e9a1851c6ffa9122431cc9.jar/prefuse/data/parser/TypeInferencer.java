// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.parser;

import java.util.ArrayList;

public class TypeInferencer
{
    public ParserFactory m_template;
    public ArrayList m_factories;
    
    public TypeInferencer() {
        this(new ParserFactory());
    }
    
    public TypeInferencer(final ParserFactory template) {
        this.m_factories = new ArrayList();
        this.m_template = template;
    }
    
    private void rangeCheck(final int n, final boolean b) {
        if (n < 0 || (!b && n >= this.m_factories.size())) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + n);
        }
        if (n < this.m_factories.size()) {
            return;
        }
        for (int i = this.m_factories.size(); i <= n; ++i) {
            this.m_factories.add(this.m_template.clone());
        }
    }
    
    public void sample(final int n, final String s) {
        this.rangeCheck(n, true);
        this.m_factories.get(n).sample(s);
    }
    
    public Class getType(final int n) {
        return this.getParser(n).getType();
    }
    
    public DataParser getParser(final int n) {
        this.rangeCheck(n, false);
        return this.m_factories.get(n).getParser();
    }
}
