// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.parsers;

import java.util.Vector;
import org.apache.xerces.xni.grammars.Grammar;
import org.apache.xerces.impl.xs.SchemaGrammar;
import org.apache.xerces.xni.XNIException;
import org.w3c.dom.ls.DOMInputSource;
import org.apache.xerces.dom3.as.DOMASException;
import org.apache.xerces.xni.parser.XMLInputSource;
import org.apache.xerces.util.XMLGrammarPoolImpl;
import org.apache.xerces.dom3.as.ASModel;
import org.apache.xerces.xni.grammars.XMLGrammarPool;
import org.apache.xerces.util.SymbolTable;
import org.apache.xerces.xni.parser.XMLParserConfiguration;
import org.apache.xerces.dom.ASModelImpl;
import org.apache.xerces.impl.xs.XSGrammarBucket;
import org.apache.xerces.dom3.as.DOMASBuilder;

public class DOMASBuilderImpl extends DOMBuilderImpl implements DOMASBuilder
{
    protected static final String SCHEMA_FULL_CHECKING = "http://apache.org/xml/features/validation/schema-full-checking";
    protected static final String ERROR_REPORTER = "http://apache.org/xml/properties/internal/error-reporter";
    protected static final String SYMBOL_TABLE = "http://apache.org/xml/properties/internal/symbol-table";
    protected static final String ENTITY_MANAGER = "http://apache.org/xml/properties/internal/entity-manager";
    protected XSGrammarBucket fGrammarBucket;
    protected ASModelImpl fAbstractSchema;
    
    public DOMASBuilderImpl() {
        super(new XMLGrammarCachingConfiguration());
    }
    
    public DOMASBuilderImpl(final XMLGrammarCachingConfiguration config) {
        super(config);
    }
    
    public DOMASBuilderImpl(final SymbolTable symbolTable) {
        super(new XMLGrammarCachingConfiguration(symbolTable));
    }
    
    public DOMASBuilderImpl(final SymbolTable symbolTable, final XMLGrammarPool grammarPool) {
        super(new XMLGrammarCachingConfiguration(symbolTable, grammarPool));
    }
    
    public ASModel getAbstractSchema() {
        return this.fAbstractSchema;
    }
    
    public void setAbstractSchema(final ASModel abstractSchema) {
        this.fAbstractSchema = (ASModelImpl)abstractSchema;
        XMLGrammarPool grammarPool = (XMLGrammarPool)super.fConfiguration.getProperty("http://apache.org/xml/properties/internal/grammar-pool");
        if (grammarPool == null) {
            grammarPool = new XMLGrammarPoolImpl();
            super.fConfiguration.setProperty("http://apache.org/xml/properties/internal/grammar-pool", grammarPool);
        }
        if (this.fAbstractSchema != null) {
            this.initGrammarPool(this.fAbstractSchema, grammarPool);
        }
    }
    
    public ASModel parseASURI(final String uri) throws DOMASException, Exception {
        final XMLInputSource source = new XMLInputSource(null, uri, null);
        return this.parseASInputSource(source);
    }
    
    public ASModel parseASInputSource(final DOMInputSource is) throws DOMASException, Exception {
        final XMLInputSource xis = this.dom2xmlInputSource(is);
        try {
            return this.parseASInputSource(xis);
        }
        catch (XNIException e) {
            final Exception ex = e.getException();
            throw ex;
        }
    }
    
    ASModel parseASInputSource(final XMLInputSource is) throws Exception {
        if (this.fGrammarBucket == null) {
            this.fGrammarBucket = new XSGrammarBucket();
        }
        this.initGrammarBucket();
        final XMLGrammarCachingConfiguration gramConfig = (XMLGrammarCachingConfiguration)super.fConfiguration;
        gramConfig.lockGrammarPool();
        final SchemaGrammar grammar = gramConfig.parseXMLSchema(is);
        gramConfig.unlockGrammarPool();
        ASModelImpl newAsModel = null;
        if (grammar != null) {
            newAsModel = new ASModelImpl();
            this.fGrammarBucket.putGrammar(grammar, true);
            this.addGrammars(newAsModel, this.fGrammarBucket);
        }
        return newAsModel;
    }
    
    private void initGrammarBucket() {
        this.fGrammarBucket.reset();
        if (this.fAbstractSchema != null) {
            this.initGrammarBucketRecurse(this.fAbstractSchema);
        }
    }
    
    private void initGrammarBucketRecurse(final ASModelImpl currModel) {
        if (currModel.getGrammar() != null) {
            this.fGrammarBucket.putGrammar(currModel.getGrammar());
        }
        for (int i = 0; i < currModel.getInternalASModels().size(); ++i) {
            final ASModelImpl nextModel = currModel.getInternalASModels().elementAt(i);
            this.initGrammarBucketRecurse(nextModel);
        }
    }
    
    private void addGrammars(final ASModelImpl model, final XSGrammarBucket grammarBucket) {
        final SchemaGrammar[] grammarList = grammarBucket.getGrammars();
        for (int i = 0; i < grammarList.length; ++i) {
            final ASModelImpl newModel = new ASModelImpl();
            newModel.setGrammar(grammarList[i]);
            model.addASModel(newModel);
        }
    }
    
    private void initGrammarPool(final ASModelImpl currModel, final XMLGrammarPool grammarPool) {
        final Grammar[] array;
        final Grammar[] grammars = array = new Grammar[] { null };
        final int n = 0;
        final SchemaGrammar grammar = currModel.getGrammar();
        array[n] = grammar;
        if (grammar != null) {
            grammarPool.cacheGrammars(grammars[0].getGrammarDescription().getGrammarType(), grammars);
        }
        final Vector modelStore = currModel.getInternalASModels();
        for (int i = 0; i < modelStore.size(); ++i) {
            this.initGrammarPool(modelStore.elementAt(i), grammarPool);
        }
    }
}
