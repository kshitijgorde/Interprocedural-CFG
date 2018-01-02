// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.trax;

import org.apache.xalan.xsltc.DOM;
import javax.xml.transform.Transformer;
import org.apache.xalan.xsltc.runtime.AbstractTranslet;
import org.apache.xalan.xsltc.Translet;
import java.security.AccessController;
import java.security.PrivilegedAction;
import org.apache.xalan.xsltc.compiler.util.ErrorMsg;
import javax.xml.transform.TransformerConfigurationException;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import javax.xml.transform.URIResolver;
import java.util.Properties;
import org.apache.xalan.xsltc.runtime.Hashtable;
import java.io.Serializable;
import javax.xml.transform.Templates;

public final class TemplatesImpl implements Templates, Serializable
{
    private static String ABSTRACT_TRANSLET;
    private String _name;
    private byte[][] _bytecodes;
    private Class[] _class;
    private int _transletIndex;
    private Hashtable _auxClasses;
    private Properties _outputProperties;
    private int _indentNumber;
    private transient URIResolver _uriResolver;
    private transient ThreadLocal _sdom;
    private transient TransformerFactoryImpl _tfactory;
    
    protected TemplatesImpl(final byte[][] bytecodes, final String transletName, final Properties outputProperties, final int indentNumber, final TransformerFactoryImpl tfactory) {
        this._name = null;
        this._bytecodes = null;
        this._class = null;
        this._transletIndex = -1;
        this._auxClasses = null;
        this._uriResolver = null;
        this._sdom = new ThreadLocal();
        this._tfactory = null;
        this._bytecodes = bytecodes;
        this._name = transletName;
        this._outputProperties = outputProperties;
        this._indentNumber = indentNumber;
        this._tfactory = tfactory;
    }
    
    protected TemplatesImpl(final Class[] transletClasses, final String transletName, final Properties outputProperties, final int indentNumber, final TransformerFactoryImpl tfactory) {
        this._name = null;
        this._bytecodes = null;
        this._class = null;
        this._transletIndex = -1;
        this._auxClasses = null;
        this._uriResolver = null;
        this._sdom = new ThreadLocal();
        this._tfactory = null;
        this._class = transletClasses;
        this._name = transletName;
        this._transletIndex = 0;
        this._outputProperties = outputProperties;
        this._indentNumber = indentNumber;
        this._tfactory = tfactory;
    }
    
    public TemplatesImpl() {
        this._name = null;
        this._bytecodes = null;
        this._class = null;
        this._transletIndex = -1;
        this._auxClasses = null;
        this._uriResolver = null;
        this._sdom = new ThreadLocal();
        this._tfactory = null;
    }
    
    private void readObject(final ObjectInputStream is) throws IOException, ClassNotFoundException {
        is.defaultReadObject();
        if (is.readBoolean()) {
            this._uriResolver = (URIResolver)is.readObject();
        }
        this._tfactory = new TransformerFactoryImpl();
    }
    
    private void writeObject(final ObjectOutputStream os) throws IOException, ClassNotFoundException {
        os.defaultWriteObject();
        if (this._uriResolver instanceof Serializable) {
            os.writeBoolean(true);
            os.writeObject(this._uriResolver);
        }
        else {
            os.writeBoolean(false);
        }
    }
    
    public synchronized void setURIResolver(final URIResolver resolver) {
        this._uriResolver = resolver;
    }
    
    protected synchronized void setTransletBytecodes(final byte[][] bytecodes) {
        this._bytecodes = bytecodes;
    }
    
    public synchronized byte[][] getTransletBytecodes() {
        return this._bytecodes;
    }
    
    public synchronized Class[] getTransletClasses() {
        try {
            if (this._class == null) {
                this.defineTransletClasses();
            }
        }
        catch (TransformerConfigurationException ex) {}
        return this._class;
    }
    
    public synchronized int getTransletIndex() {
        try {
            if (this._class == null) {
                this.defineTransletClasses();
            }
        }
        catch (TransformerConfigurationException ex) {}
        return this._transletIndex;
    }
    
    protected synchronized void setTransletName(final String name) {
        this._name = name;
    }
    
    protected synchronized String getTransletName() {
        return this._name;
    }
    
    private void defineTransletClasses() throws TransformerConfigurationException {
        if (this._bytecodes == null) {
            final ErrorMsg err = new ErrorMsg("NO_TRANSLET_CLASS_ERR");
            throw new TransformerConfigurationException(err.toString());
        }
        final TransletClassLoader loader = AccessController.doPrivileged((PrivilegedAction<TransletClassLoader>)new PrivilegedAction() {
            public Object run() {
                return new TransletClassLoader(ObjectFactory.findClassLoader());
            }
        });
        try {
            final int classCount = this._bytecodes.length;
            this._class = new Class[classCount];
            if (classCount > 1) {
                this._auxClasses = new Hashtable();
            }
            for (int i = 0; i < classCount; ++i) {
                this._class[i] = loader.defineClass(this._bytecodes[i]);
                final Class superClass = this._class[i].getSuperclass();
                if (superClass.getName().equals(TemplatesImpl.ABSTRACT_TRANSLET)) {
                    this._transletIndex = i;
                }
                else {
                    this._auxClasses.put(this._class[i].getName(), this._class[i]);
                }
            }
            if (this._transletIndex < 0) {
                final ErrorMsg err2 = new ErrorMsg("NO_MAIN_TRANSLET_ERR", this._name);
                throw new TransformerConfigurationException(err2.toString());
            }
        }
        catch (ClassFormatError e) {
            final ErrorMsg err3 = new ErrorMsg("TRANSLET_CLASS_ERR", this._name);
            throw new TransformerConfigurationException(err3.toString());
        }
        catch (LinkageError e2) {
            final ErrorMsg err2 = new ErrorMsg("TRANSLET_OBJECT_ERR", this._name);
            throw new TransformerConfigurationException(err2.toString());
        }
    }
    
    private Translet getTransletInstance() throws TransformerConfigurationException {
        try {
            if (this._name == null) {
                return null;
            }
            if (this._class == null) {
                this.defineTransletClasses();
            }
            final AbstractTranslet translet = this._class[this._transletIndex].newInstance();
            translet.postInitialization();
            translet.setTemplates(this);
            if (this._auxClasses != null) {
                translet.setAuxiliaryClasses(this._auxClasses);
            }
            return translet;
        }
        catch (InstantiationException e) {
            final ErrorMsg err = new ErrorMsg("TRANSLET_OBJECT_ERR", this._name);
            throw new TransformerConfigurationException(err.toString());
        }
        catch (IllegalAccessException e2) {
            final ErrorMsg err2 = new ErrorMsg("TRANSLET_OBJECT_ERR", this._name);
            throw new TransformerConfigurationException(err2.toString());
        }
    }
    
    public synchronized Transformer newTransformer() throws TransformerConfigurationException {
        final TransformerImpl transformer = new TransformerImpl(this.getTransletInstance(), this._outputProperties, this._indentNumber, this._tfactory);
        if (this._uriResolver != null) {
            transformer.setURIResolver(this._uriResolver);
        }
        return transformer;
    }
    
    public synchronized Properties getOutputProperties() {
        try {
            return this.newTransformer().getOutputProperties();
        }
        catch (TransformerConfigurationException e) {
            return null;
        }
    }
    
    public DOM getStylesheetDOM() {
        return this._sdom.get();
    }
    
    public void setStylesheetDOM(final DOM sdom) {
        this._sdom.set(sdom);
    }
    
    static {
        TemplatesImpl.ABSTRACT_TRANSLET = "org.apache.xalan.xsltc.runtime.AbstractTranslet";
    }
    
    static final class TransletClassLoader extends ClassLoader
    {
        TransletClassLoader(final ClassLoader parent) {
            super(parent);
        }
        
        Class defineClass(final byte[] b) {
            return this.defineClass(null, b, 0, b.length);
        }
    }
}
