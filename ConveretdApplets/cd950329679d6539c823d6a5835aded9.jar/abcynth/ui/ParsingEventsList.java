// 
// Decompiled by Procyon v0.5.30
// 

package abcynth.ui;

import java.util.EventObject;
import java.util.Vector;
import javax.swing.table.AbstractTableModel;
import scanner.InvalidCharacterEvent;
import scanner.TokenEvent;
import abc.parser.InvalidTokenEvent;
import abc.notation.Tune;
import javax.swing.table.TableModel;
import abc.parser.TuneParser;
import abc.parser.TuneParserListenerInterface;
import javax.swing.JTable;

public class ParsingEventsList extends JTable implements TuneParserListenerInterface
{
    private TuneParser m_parser;
    private ParsingEventsTableModel m_model;
    
    public ParsingEventsList(final TuneParser parser) {
        this.m_parser = null;
        this.m_model = null;
        this.setModel(this.m_model = new ParsingEventsTableModel(parser));
        this.setSelectionMode(0);
        (this.m_parser = parser).addListener(this);
    }
    
    public void setParser(final TuneParser parser) {
        this.m_parser.removeListener(this);
        (this.m_parser = parser).addListener(this);
    }
    
    public void tuneBegin() {
        ((ParsingEventsTableModel)this.getModel()).removeAllErrors();
    }
    
    public void tuneEnd(final Tune tune) {
        this.m_model.fireTableDataChanged();
    }
    
    public void invalidToken(final InvalidTokenEvent evt) {
        ((ParsingEventsTableModel)this.getModel()).addEvent(evt);
    }
    
    public void validToken(final TokenEvent evt) {
        ((ParsingEventsTableModel)this.getModel()).addEvent(evt);
    }
    
    public void invalidCharacter(final InvalidCharacterEvent evt) {
        ((ParsingEventsTableModel)this.getModel()).addEvent(evt);
    }
    
    public class ParsingEventsTableModel extends AbstractTableModel
    {
        private TuneParser m_tuneParser;
        private Vector m_events;
        
        public ParsingEventsTableModel(final TuneParser parser) {
            this.m_tuneParser = null;
            this.m_events = null;
            this.m_tuneParser = parser;
            this.m_events = new Vector();
        }
        
        public int getSize() {
            return this.m_events.size();
        }
        
        public void removeAllErrors() {
            this.m_events.removeAllElements();
        }
        
        public EventObject getEvent(final int index) {
            return this.m_events.elementAt(index);
        }
        
        public void addEvent(final Object o) {
            this.m_events.addElement(o);
        }
        
        public int getColumnCount() {
            return 4;
        }
        
        public int getRowCount() {
            return this.m_events.size();
        }
        
        public Object getValueAt(final int row, final int col) {
            try {
                if (this.m_events.elementAt(row) != null && (this.m_events.elementAt(row) instanceof TokenEvent || this.m_events.elementAt(row) instanceof InvalidCharacterEvent)) {
                    if (this.m_events.elementAt(row) instanceof InvalidTokenEvent) {
                        final InvalidTokenEvent evt = this.m_events.elementAt(row);
                        if (col == 0) {
                            if (evt.getToken() == null) {
                                return "NO TYPE";
                            }
                            return evt.getToken().getType();
                        }
                        else if (col == 1) {
                            if (evt.getToken() == null) {
                                return "NO VALUE";
                            }
                            return evt.getToken().getValue();
                        }
                        else {
                            if (col == 2) {
                                return evt.getPosition();
                            }
                            if (col == 3) {
                                return evt.getExpectedTokenType();
                            }
                        }
                    }
                    else if (this.m_events.elementAt(row) instanceof TokenEvent) {
                        final TokenEvent evt2 = this.m_events.elementAt(row);
                        if (col == 0) {
                            return evt2.getToken().getType();
                        }
                        if (col == 1) {
                            return evt2.getToken().getValue();
                        }
                        if (col == 2) {
                            return evt2.getToken().getPosition();
                        }
                        if (col == 3) {
                            return "";
                        }
                    }
                    else if (this.m_events.elementAt(row) instanceof InvalidCharacterEvent) {
                        final InvalidCharacterEvent evt3 = this.m_events.elementAt(row);
                        if (col == 0) {
                            return "Invalid Char";
                        }
                        if (col == 1) {
                            return new Character(evt3.getCharacter());
                        }
                        if (col == 2) {
                            return evt3.getPosition();
                        }
                        if (col == 3) {
                            return "";
                        }
                    }
                }
            }
            catch (Exception e) {
                e.printStackTrace();
                System.out.println("CRASHING FOR " + this.m_events.elementAt(row));
            }
            return "";
        }
    }
}
