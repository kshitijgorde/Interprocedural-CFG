// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.data.xml;

import java.util.Iterator;
import org.xml.sax.SAXException;
import org.xml.sax.Attributes;
import org.jfree.data.DefaultKeyedValues;
import org.xml.sax.helpers.DefaultHandler;

public class CategorySeriesHandler extends DefaultHandler implements DatasetTags
{
    private RootHandler root;
    private String seriesName;
    private DefaultKeyedValues values;
    
    public CategorySeriesHandler(final RootHandler root) {
        this.root = root;
        this.values = new DefaultKeyedValues();
    }
    
    public void setSeriesName(final String name) {
        this.seriesName = name;
    }
    
    public void addItem(final Comparable key, final Number value) {
        this.values.addValue(key, value);
    }
    
    public void startElement(final String namespaceURI, final String localName, final String qName, final Attributes atts) throws SAXException {
        if (qName.equals("Series")) {
            this.setSeriesName(atts.getValue("name"));
            final ItemHandler subhandler = new ItemHandler(this.root, this);
            this.root.pushSubHandler(subhandler);
        }
        else {
            if (!qName.equals("Item")) {
                throw new SAXException("Expecting <Series> or <Item> tag...found " + qName);
            }
            final ItemHandler subhandler = new ItemHandler(this.root, this);
            this.root.pushSubHandler(subhandler);
            subhandler.startElement(namespaceURI, localName, qName, atts);
        }
    }
    
    public void endElement(final String namespaceURI, final String localName, final String qName) {
        if (this.root instanceof CategoryDatasetHandler) {
            final CategoryDatasetHandler handler = (CategoryDatasetHandler)this.root;
            for (final Comparable key : this.values.getKeys()) {
                final Number value = this.values.getValue(key);
                handler.addItem(this.seriesName, key, value);
            }
            this.root.popSubHandler();
        }
    }
}
