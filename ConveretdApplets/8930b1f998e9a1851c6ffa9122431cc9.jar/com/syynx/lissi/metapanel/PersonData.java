// 
// Decompiled by Procyon v0.5.30
// 

package com.syynx.lissi.metapanel;

import java.util.Hashtable;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import java.util.Calendar;
import java.net.URL;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.parsers.SAXParser;
import java.util.TreeMap;
import java.util.Vector;
import java.util.Stack;
import org.xml.sax.helpers.DefaultHandler;

public class PersonData extends DefaultHandler
{
    Stack<String> curElement;
    public Vector<ConceptHashtable> concepts;
    public Vector<PublicationHashtable> publications;
    public TreeMap<String, String> pubsperyear;
    public ConceptHashtable concept;
    public PublicationHashtable publication;
    public String person;
    public String name;
    public String initial;
    public String forename;
    public String memberid;
    private PersonDataEvents eventHandler;
    private static SAXParser p;
    
    public static PersonData newInstance(final PersonDataEvents pde) {
        final SAXParserFactory pf = SAXParserFactory.newInstance();
        try {
            PersonData.p = pf.newSAXParser();
            return new PersonData(pde);
        }
        catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }
    }
    
    public void load(final String URL) {
        try {
            PersonData.p.reset();
            PersonData.p.parse(new URL(URL).openStream(), this);
        }
        catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    
    public PersonData(final PersonDataEvents pde) {
        this.curElement = new Stack<String>();
        this.concepts = new Vector<ConceptHashtable>();
        this.publications = new Vector<PublicationHashtable>();
        this.pubsperyear = new TreeMap<String, String>();
        this.eventHandler = pde;
    }
    
    public void startDocument() throws SAXException {
        this.memberid = "0";
        this.concepts.clear();
        this.publications.clear();
        this.pubsperyear.clear();
        for (int yearInt = Calendar.getInstance().get(1), i = yearInt - 5; i <= yearInt; ++i) {
            this.pubsperyear.put(Integer.toString(i), Integer.toString(0));
        }
    }
    
    public void startElement(final String uri, final String localName, final String qName, final Attributes attributes) throws SAXException {
        this.curElement.push(qName);
        if (qName.equals("concept")) {
            this.concept = new ConceptHashtable();
            for (int i = 0; i < attributes.getLength(); ++i) {
                this.concept.put(attributes.getQName(i), attributes.getValue(i));
            }
            this.concepts.add(this.concept);
        }
        if (qName.equals("publication")) {
            this.publication = new PublicationHashtable();
            for (int i = 0; i < attributes.getLength(); ++i) {
                this.publication.put(attributes.getQName(i), attributes.getValue(i));
            }
            this.publications.add(this.publication);
        }
        String year = "";
        String doccount = "0";
        if (qName.equals("timelineitem")) {
            for (int i = 0; i < attributes.getLength(); ++i) {
                if (attributes.getQName(i).equals("year")) {
                    year = attributes.getValue(i);
                }
                if (attributes.getQName(i).equals("doccount")) {
                    doccount = attributes.getValue(i);
                }
            }
            this.pubsperyear.put(year, doccount);
        }
    }
    
    public void endElement(final String uri, final String localName, final String qName) throws SAXException {
        this.curElement.pop();
    }
    
    public void characters(final char[] ch, final int start, final int length) throws SAXException {
        if (this.curElement.peek().equals("name")) {
            this.name = String.valueOf(ch, start, length);
            this.person = this.name;
        }
        if (this.curElement.peek().equals("initial")) {
            this.initial = String.valueOf(ch, start, length);
        }
        if (this.curElement.peek().equals("forename")) {
            this.forename = String.valueOf(ch, start, length);
        }
        if (this.curElement.peek().equals("memberid")) {
            this.memberid = String.valueOf(ch, start, length);
        }
    }
    
    public void endDocument() throws SAXException {
        this.eventHandler.personDataReady();
    }
    
    class ConceptHashtable extends Hashtable<String, String> implements Comparable
    {
        public int compareTo(final Object o) {
            return (int)Math.round(Double.parseDouble(((Hashtable<K, String>)this).get("rank")) - Double.parseDouble(((Hashtable)o).get("rank")));
        }
    }
    
    class PublicationHashtable extends Hashtable<String, String> implements Comparable
    {
        public int compareTo(final Object o) {
            return (int)Math.round(Double.parseDouble(((Hashtable<K, String>)this).get("rank")) - Double.parseDouble(((Hashtable)o).get("rank")));
        }
    }
}
