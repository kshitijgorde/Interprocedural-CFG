// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.trace;

import org.xml.sax.Attributes;
import org.apache.xalan.transformer.TransformerImpl;
import java.util.EventListener;

public class GenerateEvent implements EventListener
{
    public TransformerImpl m_processor;
    public int m_eventtype;
    public static final int EVENTTYPE_STARTDOCUMENT = 1;
    public static final int EVENTTYPE_ENDDOCUMENT = 2;
    public static final int EVENTTYPE_STARTELEMENT = 3;
    public static final int EVENTTYPE_ENDELEMENT = 4;
    public static final int EVENTTYPE_CHARACTERS = 5;
    public static final int EVENTTYPE_IGNORABLEWHITESPACE = 6;
    public static final int EVENTTYPE_PI = 7;
    public static final int EVENTTYPE_COMMENT = 8;
    public static final int EVENTTYPE_ENTITYREF = 9;
    public static final int EVENTTYPE_CDATA = 10;
    public char[] m_characters;
    public int m_start;
    public int m_length;
    public String m_name;
    public String m_data;
    public Attributes m_atts;
    
    public GenerateEvent(final TransformerImpl processor, final int eventType) {
        this.m_processor = processor;
        this.m_eventtype = eventType;
    }
    
    public GenerateEvent(final TransformerImpl processor, final int eventType, final String data) {
        this.m_data = data;
        this.m_processor = processor;
        this.m_eventtype = eventType;
    }
    
    public GenerateEvent(final TransformerImpl processor, final int eventType, final String name, final String data) {
        this.m_name = name;
        this.m_data = data;
        this.m_processor = processor;
        this.m_eventtype = eventType;
    }
    
    public GenerateEvent(final TransformerImpl processor, final int eventType, final String name, final Attributes atts) {
        this.m_name = name;
        this.m_atts = atts;
        this.m_processor = processor;
        this.m_eventtype = eventType;
    }
    
    public GenerateEvent(final TransformerImpl processor, final int eventType, final char[] ch, final int start, final int length) {
        this.m_characters = ch;
        this.m_start = start;
        this.m_length = length;
        this.m_processor = processor;
        this.m_eventtype = eventType;
    }
}