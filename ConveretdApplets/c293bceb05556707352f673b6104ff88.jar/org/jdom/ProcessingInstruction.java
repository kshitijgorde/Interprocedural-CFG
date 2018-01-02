// 
// Decompiled by Procyon v0.5.30
// 

package org.jdom;

import org.jdom.output.XMLOutputter;
import com.sun.java.util.collections.HashMap;
import com.sun.java.util.collections.Iterator;
import com.sun.java.util.collections.Set;
import com.sun.java.util.collections.ArrayList;
import com.sun.java.util.collections.List;
import com.sun.java.util.collections.Map;
import java.io.Serializable;

public class ProcessingInstruction implements Serializable, Cloneable
{
    private static final String CVS_ID = "@(#) $RCSfile: ProcessingInstruction.java,v $ $Revision: 1.27 $ $Date: 2002/03/20 15:16:32 $ $Name: jdom_1_0_b8 $";
    protected String target;
    protected String rawData;
    protected Map mapData;
    protected Object parent;
    
    protected ProcessingInstruction() {
    }
    
    public ProcessingInstruction(final String target, final Map data) {
        final String reason;
        if ((reason = Verifier.checkProcessingInstructionTarget(target)) != null) {
            throw new IllegalTargetException(target, reason);
        }
        this.target = target;
        this.setData(data);
    }
    
    public ProcessingInstruction(final String target, final String data) {
        final String reason;
        if ((reason = Verifier.checkProcessingInstructionTarget(target)) != null) {
            throw new IllegalTargetException(target, reason);
        }
        this.target = target;
        this.setData(data);
    }
    
    public Element getParent() {
        if (this.parent instanceof Element) {
            return (Element)this.parent;
        }
        return null;
    }
    
    protected ProcessingInstruction setParent(final Element parent) {
        this.parent = parent;
        return this;
    }
    
    public ProcessingInstruction detach() {
        if (this.parent instanceof Element) {
            ((Element)this.parent).removeContent(this);
        }
        else if (this.parent instanceof Document) {
            ((Document)this.parent).removeContent(this);
        }
        return this;
    }
    
    public Document getDocument() {
        if (this.parent instanceof Document) {
            return (Document)this.parent;
        }
        if (this.parent instanceof Element) {
            return ((Element)this.parent).getDocument();
        }
        return null;
    }
    
    protected ProcessingInstruction setDocument(final Document document) {
        this.parent = document;
        return this;
    }
    
    public String getTarget() {
        return this.target;
    }
    
    public String getData() {
        return this.rawData;
    }
    
    public List getNames() {
        final Set mapDataSet = this.mapData.entrySet();
        final List nameList = new ArrayList();
        final Iterator i = mapDataSet.iterator();
        while (i.hasNext()) {
            final String wholeSet = i.next().toString();
            final String attrName = wholeSet.substring(0, wholeSet.indexOf("="));
            nameList.add(attrName);
        }
        return nameList;
    }
    
    public ProcessingInstruction setData(final String data) {
        this.rawData = data;
        this.mapData = this.parseData(data);
        return this;
    }
    
    public ProcessingInstruction setData(final Map data) {
        this.rawData = this.toString(data);
        this.mapData = data;
        return this;
    }
    
    public String getValue(final String name) {
        return (String)this.mapData.get(name);
    }
    
    public ProcessingInstruction setValue(final String name, final String value) {
        this.mapData.put(name, value);
        this.rawData = this.toString(this.mapData);
        return this;
    }
    
    public boolean removeValue(final String name) {
        if (this.mapData.remove(name) != null) {
            this.rawData = this.toString(this.mapData);
            return true;
        }
        return false;
    }
    
    private String toString(final Map mapData) {
        final StringBuffer rawData = new StringBuffer();
        for (final String name : mapData.keySet()) {
            final String value = (String)mapData.get(name);
            rawData.append(name).append("=\"").append(value).append("\" ");
        }
        rawData.setLength(rawData.length() - 1);
        return rawData.toString();
    }
    
    private Map parseData(final String rawData) {
        final Map data = new HashMap();
        String inputData = rawData.trim();
        while (!inputData.trim().equals("")) {
            String name = "";
            String value = "";
            int startName = 0;
            char previousChar = inputData.charAt(startName);
            int pos = 1;
            while (pos < inputData.length()) {
                final char currentChar = inputData.charAt(pos);
                if (currentChar == '=') {
                    name = inputData.substring(startName, pos).trim();
                    value = this.extractQuotedString(inputData.substring(pos + 1).trim());
                    if (value == null) {
                        return new HashMap();
                    }
                    pos += value.length() + 1;
                    break;
                }
                else {
                    if (Character.isWhitespace(previousChar) && !Character.isWhitespace(currentChar)) {
                        startName = pos;
                    }
                    previousChar = currentChar;
                    ++pos;
                }
            }
            inputData = inputData.substring(pos);
            if (name.length() > 0 && value != null) {
                data.put(name, value);
            }
        }
        return data;
    }
    
    private String extractQuotedString(final String rawData) {
        boolean inQuotes = false;
        char quoteChar = '\"';
        int start = 0;
        for (int pos = 0; pos < rawData.length(); ++pos) {
            final char currentChar = rawData.charAt(pos);
            if (currentChar == '\"' || currentChar == '\'') {
                if (!inQuotes) {
                    quoteChar = currentChar;
                    inQuotes = true;
                    start = pos + 1;
                }
                else if (quoteChar == currentChar) {
                    inQuotes = false;
                    return rawData.substring(start, pos);
                }
            }
        }
        return null;
    }
    
    public String toString() {
        return "[ProcessingInstruction: " + new XMLOutputter().outputString(this) + "]";
    }
    
    public final boolean equals(final Object ob) {
        return ob == this;
    }
    
    public final int hashCode() {
        return super.hashCode();
    }
    
    public Object clone() {
        ProcessingInstruction pi = null;
        try {
            pi = (ProcessingInstruction)super.clone();
        }
        catch (CloneNotSupportedException ex) {}
        pi.parent = null;
        if (this.mapData != null) {
            pi.mapData = this.parseData(this.rawData);
        }
        return pi;
    }
}
