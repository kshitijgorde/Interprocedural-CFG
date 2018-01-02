// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.swing.xmleditor;

import org.xidget.ifeature.model.ISingleValueUpdateFeature;
import org.xidget.ifeature.model.ISingleValueWidgetFeature;
import javax.swing.text.Highlighter;
import java.awt.Container;
import javax.swing.BorderFactory;
import java.awt.Component;
import javax.swing.JComponent;
import java.util.concurrent.Executors;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import org.xidget.IXidget;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.FutureTask;
import org.xmodel.xml.XmlIO;
import javax.swing.JScrollPane;
import org.xml.sax.ErrorHandler;
import org.xmodel.IModelObject;
import java.util.concurrent.Callable;
import javax.swing.event.DocumentListener;
import org.xidget.swing.feature.SwingWidgetCreationFeature;

public class XmlTextPaneWidgetCreationFeature extends SwingWidgetCreationFeature
{
    private DocumentListener documentListener;
    private Callable<IModelObject> parseCallable;
    private ErrorHandler errorHandler;
    private JScrollPane jScrollPane;
    private XmlTextPane xmlTextPane;
    private ErrorHighlightPainter errorHighlighter;
    private XmlIO xmlIO;
    private FutureTask<IModelObject> future;
    private ExecutorService executor;
    
    public XmlTextPaneWidgetCreationFeature(final IXidget xidget) {
        super(xidget);
        this.documentListener = new DocumentListener() {
            @Override
            public void changedUpdate(final DocumentEvent documentEvent) {
                XmlTextPaneWidgetCreationFeature.this.updateEditor();
            }
            
            @Override
            public void insertUpdate(final DocumentEvent documentEvent) {
                XmlTextPaneWidgetCreationFeature.this.updateEditor();
            }
            
            @Override
            public void removeUpdate(final DocumentEvent documentEvent) {
                XmlTextPaneWidgetCreationFeature.this.updateEditor();
            }
        };
        this.parseCallable = new Callable<IModelObject>() {
            @Override
            public IModelObject call() throws Exception {
                final IModelObject read = XmlTextPaneWidgetCreationFeature.this.xmlIO.read(XmlTextPaneWidgetCreationFeature.this.xmlTextPane.getText());
                SwingUtilities.invokeLater(new Commiter(read));
                return read;
            }
        };
        this.errorHandler = new ErrorHandler() {
            @Override
            public void warning(final SAXParseException ex) throws SAXException {
                XmlTextPaneWidgetCreationFeature.this.setErrorMarker(ex.getLineNumber(), ex.getColumnNumber());
            }
            
            @Override
            public void error(final SAXParseException ex) throws SAXException {
                XmlTextPaneWidgetCreationFeature.this.setErrorMarker(ex.getLineNumber(), ex.getColumnNumber());
            }
            
            @Override
            public void fatalError(final SAXParseException ex) throws SAXException {
                XmlTextPaneWidgetCreationFeature.this.setErrorMarker(ex.getLineNumber(), ex.getColumnNumber());
            }
        };
        (this.xmlIO = new XmlIO()).setErrorHandler(this.errorHandler);
        this.errorHighlighter = new ErrorHighlightPainter();
        this.executor = Executors.newCachedThreadPool();
    }
    
    @Override
    protected JComponent createSwingWidget() {
        this.xmlTextPane = new XmlTextPane();
        this.xmlTextPane.getDocument().addDocumentListener(this.documentListener);
        (this.jScrollPane = new JScrollPane(this.xmlTextPane)).setBorder(BorderFactory.createEmptyBorder());
        return this.jScrollPane;
    }
    
    @Override
    public void destroyWidgets() {
        this.executor.shutdownNow();
        this.jScrollPane.setEnabled(false);
        final Container parent = this.jScrollPane.getParent();
        if (parent != null) {
            parent.remove(this.jScrollPane);
        }
        this.jScrollPane = null;
    }
    
    @Override
    public Object[] getLastWidgets() {
        return new Object[] { this.jScrollPane, this.xmlTextPane };
    }
    
    public JScrollPane getJScrollPane() {
        return this.jScrollPane;
    }
    
    public XmlTextPane getXmlTextPane() {
        return this.xmlTextPane;
    }
    
    private void setErrorMarker(final int n, final int n2) {
        final int absoluteLocation = this.getAbsoluteLocation(n - 1, n2 - 1);
        try {
            this.xmlTextPane.getHighlighter().removeAllHighlights();
            this.xmlTextPane.getHighlighter().addHighlight(absoluteLocation, absoluteLocation + 1, (Highlighter.HighlightPainter)this.errorHighlighter);
        }
        catch (Exception ex) {}
    }
    
    private int getAbsoluteLocation(int n, final int n2) {
        int n3 = 0;
        final String text = this.xmlTextPane.getText();
        final char[] charArray = text.toCharArray();
        while (n3 < text.length() && n > 0) {
            if (charArray[n3] == '\n') {
                --n;
            }
            ++n3;
        }
        final int n4 = n3 + n2;
        if (n > 0 || n4 >= text.length()) {
            return text.length() - 1;
        }
        return n4;
    }
    
    private void updateEditor() {
        this.xmlTextPane.getHighlighter().removeAllHighlights();
        if (this.future != null) {
            this.future.cancel(false);
        }
        this.future = new FutureTask<IModelObject>(this.parseCallable);
        this.executor.submit(this.future);
    }
    
    private class Commiter implements Runnable
    {
        private IModelObject element;
        
        public Commiter(final IModelObject element) {
            this.element = element;
        }
        
        @Override
        public void run() {
            final XmlTextPaneSingleValueWidgetFeature xmlTextPaneSingleValueWidgetFeature = XmlTextPaneWidgetCreationFeature.this.xidget.getFeature((Class<XmlTextPaneSingleValueWidgetFeature>)ISingleValueWidgetFeature.class);
            xmlTextPaneSingleValueWidgetFeature.ignoreUpdate(true);
            try {
                XmlTextPaneWidgetCreationFeature.this.xidget.getFeature(ISingleValueUpdateFeature.class).commit(this.element);
            }
            finally {
                xmlTextPaneSingleValueWidgetFeature.ignoreUpdate(false);
            }
            xmlTextPaneSingleValueWidgetFeature.ignoreUpdate(false);
        }
    }
}
