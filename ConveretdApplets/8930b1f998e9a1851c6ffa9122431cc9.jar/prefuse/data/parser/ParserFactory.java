// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.parser;

import java.util.Arrays;

public class ParserFactory implements Cloneable
{
    private static final DataParser[] DEFAULT_PARSERS;
    private static final ParserFactory DEFAULT_FACTORY;
    private DataParser[] m_parsers;
    private boolean[] m_isCandidate;
    
    public static ParserFactory getDefaultFactory() {
        return ParserFactory.DEFAULT_FACTORY;
    }
    
    public ParserFactory() {
        this(ParserFactory.DEFAULT_PARSERS);
    }
    
    public Object clone() {
        return new ParserFactory(this.m_parsers);
    }
    
    public ParserFactory(final DataParser[] parsers) {
        for (int i = 0; i < parsers.length; ++i) {
            if (parsers[i] == null) {
                throw new IllegalArgumentException("Input parsers must be non-null");
            }
        }
        this.m_parsers = parsers;
        this.m_isCandidate = new boolean[this.m_parsers.length];
        this.reset();
    }
    
    protected void reset() {
        Arrays.fill(this.m_isCandidate, true);
    }
    
    protected void sample(final String s) {
        for (int i = 0; i < this.m_parsers.length; ++i) {
            if (this.m_isCandidate[i]) {
                this.m_isCandidate[i] = this.m_parsers[i].canParse(s);
            }
        }
    }
    
    protected DataParser getParser() {
        for (int i = 0; i < this.m_parsers.length; ++i) {
            if (this.m_isCandidate[i]) {
                return this.m_parsers[i];
            }
        }
        return null;
    }
    
    public DataParser getParser(final Class clazz) {
        for (int i = 0; i < this.m_parsers.length; ++i) {
            if (this.m_parsers[i].getType().equals(clazz)) {
                return this.m_parsers[i];
            }
        }
        return null;
    }
    
    public DataParser getParser(final String[] array, final int n) {
        return this.getParser(new String[][] { array }, 0, n);
    }
    
    public DataParser getParser(final String[][] array, final int n, final int n2) {
        if (array == null || array.length == 0) {
            return null;
        }
        final int length = array.length;
        this.reset();
        for (int i = n2; i < length; ++i) {
            this.sample(array[i][n]);
        }
        return this.getParser();
    }
    
    static {
        DEFAULT_PARSERS = new DataParser[] { new IntParser(), new LongParser(), new DoubleParser(), new FloatParser(), new BooleanParser(), new ColorIntParser(), new DateParser(), new TimeParser(), new DateTimeParser(), new IntArrayParser(), new LongArrayParser(), new FloatArrayParser(), new DoubleArrayParser(), new StringParser() };
        DEFAULT_FACTORY = new ParserFactory(ParserFactory.DEFAULT_PARSERS);
    }
}
