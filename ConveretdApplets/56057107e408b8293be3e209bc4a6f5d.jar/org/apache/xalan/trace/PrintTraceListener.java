// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.trace;

import org.apache.xalan.templates.ElemTemplate;
import org.apache.xalan.templates.ElemTextLiteral;
import javax.xml.transform.TransformerException;
import org.w3c.dom.Node;
import org.w3c.dom.traversal.NodeIterator;
import org.apache.xpath.objects.XObject;
import org.apache.xpath.axes.ContextNodeList;
import org.apache.xalan.templates.ElemTemplateElement;
import java.io.PrintWriter;

public class PrintTraceListener implements TraceListener
{
    PrintWriter m_pw;
    public boolean m_traceTemplates;
    public boolean m_traceElements;
    public boolean m_traceGeneration;
    public boolean m_traceSelection;
    
    public PrintTraceListener(final PrintWriter pw) {
        this.m_traceTemplates = false;
        this.m_traceElements = false;
        this.m_traceGeneration = false;
        this.m_traceSelection = false;
        this.m_pw = pw;
    }
    
    public void generated(final GenerateEvent ev) {
        if (this.m_traceGeneration) {
            switch (ev.m_eventtype) {
                case 1: {
                    this.m_pw.println("STARTDOCUMENT");
                    break;
                }
                case 2: {
                    this.m_pw.println("ENDDOCUMENT");
                    break;
                }
                case 3: {
                    this.m_pw.println("STARTELEMENT: " + ev.m_name);
                    break;
                }
                case 4: {
                    this.m_pw.println("ENDELEMENT: " + ev.m_name);
                    break;
                }
                case 5: {
                    final String chars = new String(ev.m_characters, ev.m_start, ev.m_length);
                    this.m_pw.println("CHARACTERS: " + chars);
                    break;
                }
                case 10: {
                    final String chars = new String(ev.m_characters, ev.m_start, ev.m_length);
                    this.m_pw.println("CDATA: " + chars);
                    break;
                }
                case 8: {
                    this.m_pw.println("COMMENT: " + ev.m_data);
                    break;
                }
                case 7: {
                    this.m_pw.println("PI: " + ev.m_name + ", " + ev.m_data);
                    break;
                }
                case 9: {
                    this.m_pw.println("ENTITYREF: " + ev.m_name);
                    break;
                }
                case 6: {
                    this.m_pw.println("IGNORABLEWHITESPACE");
                    break;
                }
            }
        }
    }
    
    public void selected(final SelectionEvent ev) throws TransformerException {
        if (this.m_traceSelection) {
            final ElemTemplateElement ete = ev.m_styleNode;
            if (ev.m_styleNode.getLineNumber() == 0) {
                final ElemTemplateElement parent = (ElemTemplateElement)ete.getParentNode();
                if (parent == ete.getStylesheetRoot().getDefaultRootRule()) {
                    this.m_pw.print("(default root rule) ");
                }
                else if (parent == ete.getStylesheetRoot().getDefaultTextRule()) {
                    this.m_pw.print("(default text rule) ");
                }
                else if (parent == ete.getStylesheetRoot().getDefaultRule()) {
                    this.m_pw.print("(default rule) ");
                }
                this.m_pw.print(String.valueOf(ete.getNodeName()) + ", " + ev.m_attributeName + "='" + ev.m_xpath.getPatternString() + "': ");
            }
            else {
                this.m_pw.print("Line #" + ev.m_styleNode.getLineNumber() + ", " + "Column #" + ev.m_styleNode.getColumnNumber() + ": " + ete.getNodeName() + ", " + ev.m_attributeName + "='" + ev.m_xpath.getPatternString() + "': ");
            }
            final int type = ev.m_selection.getType();
            final XObject selection = ev.m_selection;
            if (type == 4) {
                this.m_pw.println();
                NodeIterator nl = ev.m_selection.nodeset();
                if (nl instanceof ContextNodeList) {
                    try {
                        nl = ((ContextNodeList)nl).cloneWithReset();
                    }
                    catch (CloneNotSupportedException ex) {
                        this.m_pw.println("     [Can't trace nodelist because it it threw a CloneNotSupportedException]");
                        return;
                    }
                    Node pos = nl.nextNode();
                    if (pos == null) {
                        this.m_pw.println("     [empty node list]");
                    }
                    else {
                        while (pos != null) {
                            this.m_pw.println("     " + pos);
                            pos = nl.nextNode();
                        }
                    }
                }
                else {
                    this.m_pw.println("     [Can't trace nodelist because it isn't a ContextNodeList]");
                }
            }
            else {
                this.m_pw.println(ev.m_selection.str());
            }
        }
    }
    
    public void trace(final TracerEvent ev) {
        switch (ev.m_styleNode.getXSLToken()) {
            case 78: {
                if (this.m_traceElements) {
                    this.m_pw.print("Line #" + ev.m_styleNode.getLineNumber() + ", " + "Column #" + ev.m_styleNode.getColumnNumber() + " -- " + ev.m_styleNode.getNodeName() + ": ");
                    final ElemTextLiteral etl = (ElemTextLiteral)ev.m_styleNode;
                    final String chars = new String(etl.getChars(), 0, etl.getChars().length);
                    this.m_pw.println("    " + chars.trim());
                    break;
                }
                break;
            }
            case 19: {
                if (this.m_traceTemplates || this.m_traceElements) {
                    final ElemTemplate et = (ElemTemplate)ev.m_styleNode;
                    this.m_pw.print("Line #" + et.getLineNumber() + ", " + "Column #" + et.getColumnNumber() + ": " + et.getNodeName() + " ");
                    if (et.getMatch() != null) {
                        this.m_pw.print("match='" + et.getMatch().getPatternString() + "' ");
                    }
                    if (et.getName() != null) {
                        this.m_pw.print("name='" + et.getName() + "' ");
                    }
                    this.m_pw.println();
                    break;
                }
                break;
            }
            default: {
                if (this.m_traceElements) {
                    this.m_pw.println("Line #" + ev.m_styleNode.getLineNumber() + ", " + "Column #" + ev.m_styleNode.getColumnNumber() + ": " + ev.m_styleNode.getNodeName());
                    break;
                }
                break;
            }
        }
    }
}
