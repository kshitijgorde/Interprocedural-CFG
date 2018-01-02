// 
// Decompiled by Procyon v0.5.30
// 

package abc.parser;

import scanner.NoSuchTokenException;
import abc.parser.def.DefinitionFactory;
import abc.notation.EndOfStaffLine;
import abc.notation.MusicPresentationElement;
import scanner.CharStreamPosition;
import abc.notation.TieDefinition;
import abc.notation.Tuplet;
import abc.notation.NotesSeparator;
import abc.notation.SlurDefinition;
import abc.notation.RepeatBarLine;
import abc.notation.BarLine;
import abc.notation.Words;
import abc.notation.KeySignature;
import abc.notation.RepeatedPart;
import abc.notation.RepeatedPartAbstract;
import abc.notation.Note;
import abc.notation.Fraction;
import abc.notation.Tempo;
import abc.notation.MultiPartsDefinition;
import scanner.InvalidCharacterEvent;
import scanner.TokenEvent;
import abc.notation.Tune;
import abc.notation.TimeSignature;
import abc.notation.NoteAbstract;
import scanner.TokenType;
import scanner.Token;
import java.util.Vector;
import scanner.ScannerListenerInterface;
import scanner.FinaleStateAutomata;
import scanner.Scanner;
import scanner.Set;

public class AbcParserAbstract
{
    protected static final Set FIRST_END_OF_LINE;
    protected static final Set FIRST_PART_SPEC;
    protected static final Set FIRST_PARTS;
    protected static final Set FIRST_NOTE_LENGTH_STRICT;
    protected static final Set FIRST_TEMPO;
    protected static final Set FIRST_METER_FRACTION;
    protected static final Set FIRST_METER;
    protected Set FIRST_GLOBAL_ACCIDENTAL;
    protected static final Set FIRST_MODE;
    protected static final Set FIRST_MODE_SPEC;
    protected static final Set FIRST_KEY_ACCIDENTAL;
    protected static final Set FIRST_KEYNOTE;
    protected static final Set FIRST_KEYSPEC;
    protected static final Set FIRST_KEY;
    protected static final Set FIRST_FIELD_KEY;
    protected static final Set FIRST_FIELD_TRANSCRNOTES;
    protected static final Set FIRST_FIELD_SOURCE;
    protected static final Set FIRST_FIELD_RHYTHM;
    protected static final Set FIRST_FIELD_TEMPO;
    protected static final Set FIRST_FIELD_PARTS;
    protected static final Set FIRST_FIELD_ORIGIN;
    protected static final Set FIRST_FIELD_NOTES;
    protected static final Set FIRST_FIELD_METER;
    protected static final Set FIRST_FIELD_DEFAULT_LENGTH;
    protected static final Set FIRST_FIELD_INFORMATION;
    protected static final Set FIRST_FIELD_HISTORY;
    protected static final Set FIRST_FIELD_GROUP;
    protected static final Set FIRST_FIELD_DISCOGRAPHY;
    protected static final Set FIRST_FIELD_COMPOSER;
    protected static final Set FIRST_FIELD_BOOK;
    protected static final Set FIRST_FIELD_AREA;
    protected static Set FIRST_COMMENT;
    protected static final Set FIRST_OTHER_FIELDS;
    protected static final Set FIRST_FIELD_TITLE;
    protected static final Set FIRST_FIELD_NUMBER;
    protected static Set FIRST_ABCHEADER;
    protected static Set FIRST_TEXT_CHAR;
    protected static Set FIRST_TEXT;
    protected static Set FIRST_LINE_FEED;
    protected static Set FIRST_NO_LINE_BREAK;
    protected static Set FIRST_LINE_BREAK;
    protected static Set FIRST_SPACE;
    protected static Set FIRST_USER_DEFINED;
    protected static Set FIRST_FIELD_WORDS;
    protected static Set FIRST_FIELD_PART;
    protected static Set FIRST_TUNE_FIELD;
    protected static Set FIRST_MID_TUNE_FIELD;
    protected static Set FIRST_END_SLUR;
    protected static Set FIRST_BEGIN_SLUR;
    protected static Set FIRST_NTH_REPEAT;
    protected static Set FIRST_BARLINE;
    protected static Set FIRST_CHORD_TYPE;
    protected Set FIRST_FORMAL_CHORD;
    protected static Set FIRST_GUITAR_CHORD;
    protected static Set FIRST_GRACE_NOTES;
    protected static Set FIRST_GRACINGS;
    protected static Set FIRST_TIE;
    protected static Set FIRST_BROKEN_RHYTHM;
    protected static Set FIRST_REST;
    protected static Set FIRST_BASE_NOTE;
    protected static Set FIRST_ACCIDENTAL;
    protected static Set FIRST_NOTE_LENGTH;
    protected static Set FIRST_OCTAVE;
    protected static Set FIRST_PITCH;
    protected static Set FIRST_NOTE_OR_REST;
    protected static Set FIRST_NOTE;
    protected static Set FIRST_MULTI_NOTE;
    protected static Set FIRST_NOTE_STEM;
    protected static Set FIRST_NOTE_ELEMENT;
    protected static Set FIRST_TUPLET_SPEC;
    protected static Set FIRST_TUPLET_ELEMENT;
    protected static Set FIRST_LINE_ENDER;
    protected static Set FIRST_ELEMENT;
    protected Set FIRST_ABC_LINE;
    protected Set FIRST_ABC_MUSIC;
    protected static Set FIRST_ABCTUNE;
    protected Scanner m_scanner;
    protected FinaleStateAutomata m_automata;
    protected ScannerListenerInterface m_scannerListener;
    protected Vector m_listeners;
    protected Token m_token;
    protected TokenType m_tokenType;
    private byte brknRthmDotsCorrection;
    private Vector slursDefinitionStack;
    private NoteAbstract lastParsedNote;
    protected NoteAbstract lastNoteFlaggedAsEndOfGroup;
    private Vector notesStartingTies;
    private short m_defaultNoteLength;
    private TimeSignature m_timeSignature;
    private Tune.Music m_music;
    protected Tune m_tune;
    
    public AbcParserAbstract() {
        this.FIRST_GLOBAL_ACCIDENTAL = new Set(AbcParserAbstract.FIRST_ACCIDENTAL);
        this.FIRST_FORMAL_CHORD = new Set(AbcParserAbstract.FIRST_BASE_NOTE);
        this.FIRST_ABC_LINE = new Set(AbcParserAbstract.FIRST_ELEMENT).union(AbcParserAbstract.FIRST_MID_TUNE_FIELD);
        this.FIRST_ABC_MUSIC = new Set(this.FIRST_ABC_LINE);
        this.m_automata = null;
        this.m_scannerListener = null;
        this.m_token = null;
        this.m_tokenType = TokenType.UNKNOWN;
        this.brknRthmDotsCorrection = 0;
        this.slursDefinitionStack = null;
        this.lastParsedNote = null;
        this.lastNoteFlaggedAsEndOfGroup = null;
        this.notesStartingTies = null;
        this.m_defaultNoteLength = 24;
        this.m_timeSignature = null;
        this.m_music = null;
        this.m_tune = null;
        this.m_scanner = new Scanner();
        this.m_automata = new FinaleStateAutomata();
        this.m_scannerListener = new ScannerListenerInterface() {
            public void tokenGenerated(final TokenEvent evt) {
            }
            
            public void invalidCharacter(final InvalidCharacterEvent evt) {
                AbcParserAbstract.this.notifyListenersForInvalidCharacter(evt);
            }
            
            public void lineProcessed(final String line) {
            }
        };
        this.m_scanner.addListener(this.m_scannerListener);
        this.m_listeners = new Vector();
        this.notesStartingTies = new Vector();
        this.slursDefinitionStack = new Vector();
    }
    
    public Scanner getScanner() {
        return this.m_scanner;
    }
    
    public void addListener(final TuneParserListenerInterface listener) {
        this.m_listeners.addElement(listener);
    }
    
    public void removeListener(final TuneParserListenerInterface listener) {
        this.m_listeners.removeElement(listener);
    }
    
    protected void init() {
        this.m_token = null;
        this.m_tokenType = TokenType.UNKNOWN;
        this.brknRthmDotsCorrection = 0;
        this.slursDefinitionStack.removeAllElements();
        this.lastParsedNote = null;
        this.lastNoteFlaggedAsEndOfGroup = null;
        this.notesStartingTies.removeAllElements();
        this.m_defaultNoteLength = 24;
        this.m_timeSignature = null;
        this.m_music = null;
        this.m_tune = null;
    }
    
    protected void parseAbcFile(final Set follow) {
        final Set current = AbcParserAbstract.FIRST_ABCTUNE.createUnion(AbcParserAbstract.FIRST_COMMENT).createUnion(AbcParserAbstract.FIRST_LINE_FEED);
        this.m_scanner.setFinaleStateAutomata(AutomataFactory.getAutomata(current.getTypes()));
        this.m_token = this.m_scanner.nextToken();
        this.m_tokenType = this.m_token.getType();
        while (this.m_token != null) {
            if (AbcParserAbstract.FIRST_ABCTUNE.contains(this.m_token.getType())) {
                this.notifyListenersForTuneBegin();
                final Tune tune = this.parseAbcTune(current.createUnion(follow));
                this.notifyListenersForTuneEnd(tune);
            }
            else if (AbcParserAbstract.FIRST_COMMENT.contains(this.m_token.getType())) {
                this.parseComment(current.createUnion(follow));
            }
            else {
                if (!AbcParserAbstract.FIRST_LINE_FEED.contains(this.m_token.getType())) {
                    continue;
                }
                this.accept(AbcTokenType.LINE_FEED, current, current.createUnion(follow));
            }
        }
    }
    
    protected Tune parseAbcTune(final Set follow) {
        final Set current = new Set().union(this.FIRST_ABC_MUSIC);
        this.parseAbcHeader(current.createUnion(follow));
        current.remove(this.FIRST_ABC_MUSIC);
        this.parseAbcMusic(current.createUnion(follow));
        return this.m_tune;
    }
    
    private void parseOtherFields(final Set follow) {
        if (this.m_tokenType.equals(AbcTokenType.FIELD_HISTORY)) {
            this.parseFieldHistory(follow);
        }
        else if (this.m_tokenType.equals(AbcTokenType.FIELD_DEFAULT_LENGTH)) {
            final short defaultNoteLength = this.parseFieldDefaultLength(follow);
            if (defaultNoteLength != -1) {
                this.m_defaultNoteLength = defaultNoteLength;
            }
        }
        else if (this.m_tokenType.equals(AbcTokenType.FIELD_METER)) {
            final TimeSignature meter = this.parseFieldMeter(follow);
            if (meter != null) {
                this.m_music.addElement(meter);
                this.m_defaultNoteLength = meter.getDefaultNoteLength();
                this.m_timeSignature = meter;
            }
        }
        else if (this.m_tokenType.equals(AbcTokenType.FIELD_PARTS)) {
            final MultiPartsDefinition parts = this.parseFieldParts(follow);
            if (parts != null) {
                this.m_tune.setMultiPartsDefinition(parts);
            }
        }
        else if (this.m_tokenType.equals(AbcTokenType.FIELD_TEMPO)) {
            final Tempo tempo = this.parseFieldTempo(follow);
            if (tempo != null) {
                this.m_music.addElement(tempo);
            }
        }
        else if (this.m_tokenType.equals(AbcTokenType.COMMENT)) {
            this.parseComment(follow);
        }
        else {
            final AbcTextField field = this.parseField(this.m_tokenType, follow);
            if (field != null) {
                if (field.getType() == 1) {
                    this.m_tune.setArea(field.getText());
                }
                else if (field.getType() == 2) {
                    this.m_tune.setBook(field.getText());
                }
                else if (field.getType() == 3) {
                    this.m_tune.setComposer(field.getText());
                }
                else if (field.getType() == 4) {
                    this.m_tune.setDiscography(field.getText());
                }
                else if (field.getType() == 6) {
                    this.m_tune.setGroup(field.getText());
                }
                else if (field.getType() == 7) {
                    this.m_tune.addHistory(field.getText());
                }
                else if (field.getType() == 8) {
                    this.m_tune.setInformation(field.getText());
                }
                else if (field.getType() == 9) {
                    this.m_tune.setNotes(field.getText());
                }
                else if (field.getType() == 10) {
                    this.m_tune.setOrigin(field.getText());
                }
                else if (field.getType() == 11) {
                    this.m_tune.setRhythm(field.getText());
                }
                else if (field.getType() == 12) {
                    this.m_tune.setSource(field.getText());
                }
                else if (field.getType() == 13) {
                    this.m_tune.addTranscriptionNotes(field.getText());
                }
            }
        }
    }
    
    private AbcTextField parseField(final TokenType tokenType, final Set follow) {
        final Set current = new Set().union(AbcParserAbstract.FIRST_END_OF_LINE).union(AbcTokenType.TEXT);
        final String ret = this.accept(tokenType, current, follow);
        current.remove(AbcTokenType.TEXT);
        final String text = this.accept(AbcTokenType.TEXT, current, follow);
        current.remove(AbcParserAbstract.FIRST_END_OF_LINE);
        this.parseEndOfLine(current.createUnion(follow));
        if (ret == null || text == null) {
            return null;
        }
        if (tokenType.equals(AbcTokenType.FIELD_AREA)) {
            return new AbcTextField((byte)1, text);
        }
        if (tokenType.equals(AbcTokenType.FIELD_BOOK)) {
            return new AbcTextField((byte)2, text);
        }
        if (tokenType.equals(AbcTokenType.FIELD_COMPOSER)) {
            return new AbcTextField((byte)3, text);
        }
        if (tokenType.equals(AbcTokenType.FIELD_DISCOGRAPHY)) {
            return new AbcTextField((byte)4, text);
        }
        if (tokenType.equals(AbcTokenType.FIELD_GROUP)) {
            return new AbcTextField((byte)6, text);
        }
        if (tokenType.equals(AbcTokenType.FIELD_INFORMATION)) {
            return new AbcTextField((byte)8, text);
        }
        if (tokenType.equals(AbcTokenType.FIELD_NOTES)) {
            return new AbcTextField((byte)9, text);
        }
        if (tokenType.equals(AbcTokenType.FIELD_ORIGIN)) {
            return new AbcTextField((byte)10, text);
        }
        if (tokenType.equals(AbcTokenType.FIELD_RHYTHM)) {
            return new AbcTextField((byte)11, text);
        }
        if (tokenType.equals(AbcTokenType.FIELD_SOURCE)) {
            return new AbcTextField((byte)12, text);
        }
        if (tokenType.equals(AbcTokenType.FIELD_TITLE)) {
            return new AbcTextField((byte)15, text);
        }
        if (tokenType.equals(AbcTokenType.FIELD_TRANSCRNOTES)) {
            return new AbcTextField((byte)13, text);
        }
        if (tokenType.equals(AbcTokenType.FIELD_WORDS)) {
            return new AbcTextField((byte)14, text);
        }
        return null;
    }
    
    private short parseFieldDefaultLength(final Set follow) {
        final Set current = new Set().union(AbcParserAbstract.FIRST_NOTE_LENGTH_STRICT).union(AbcParserAbstract.FIRST_END_OF_LINE);
        this.accept(AbcTokenType.FIELD_DEFAULT_LENGTH, current, follow);
        current.remove(AbcParserAbstract.FIRST_NOTE_LENGTH_STRICT);
        final short noteLength = this.parseNoteLengthStrict(current.createUnion(follow));
        current.remove(AbcParserAbstract.FIRST_END_OF_LINE);
        this.parseEndOfLine(current.createUnion(follow));
        return noteLength;
    }
    
    private Fraction parseNoteLength(final Set follow) {
        final Set current = new Set(AbcTokenType.NUMBER).union(AbcTokenType.FRACTION);
        byte num = 1;
        byte denom = 1;
        if (this.m_tokenType.equals(AbcTokenType.NUMBER)) {
            final String acc = this.accept(AbcTokenType.NUMBER, current, follow, true);
            if (acc != null) {
                num = Byte.parseByte(acc);
            }
        }
        if (this.m_tokenType.equals(AbcTokenType.FRACTION)) {
            current.remove(AbcTokenType.FRACTION);
            this.accept(AbcTokenType.FRACTION, current, follow, true);
            denom = 2;
            current.remove(AbcTokenType.NUMBER);
            if (this.m_tokenType.equals(AbcTokenType.NUMBER)) {
                final String acc = this.accept(AbcTokenType.NUMBER, current, follow);
                if (acc != null) {
                    denom = Byte.parseByte(acc);
                }
            }
        }
        return new Fraction(num, denom);
    }
    
    private short parseNoteLengthStrict(final Set follow) {
        final Set current = new Set(AbcTokenType.NUMBER).union(AbcTokenType.FRACTION);
        short noteLength = -1;
        final String numString = this.accept(AbcTokenType.NUMBER, current, follow);
        current.remove(AbcTokenType.FRACTION);
        this.accept(AbcTokenType.FRACTION, current, follow);
        current.remove(AbcTokenType.NUMBER);
        final String denomString = this.accept(AbcTokenType.NUMBER, current, follow);
        if (numString != null && denomString != null) {
            final int num = Integer.parseInt(numString);
            final int denom = Integer.parseInt(denomString);
            noteLength = Note.convertToNoteLengthStrict(num, denom);
        }
        return noteLength;
    }
    
    private TimeSignature parseFieldMeter(final Set follow) {
        final Set current = new Set().union(AbcParserAbstract.FIRST_METER).union(AbcParserAbstract.FIRST_END_OF_LINE);
        TimeSignature meter = null;
        this.accept(AbcTokenType.FIELD_METER, current, follow);
        current.remove(AbcParserAbstract.FIRST_METER);
        meter = this.parseMeter(current.createUnion(follow));
        current.remove(AbcParserAbstract.FIRST_END_OF_LINE);
        this.parseEndOfLine(current.createUnion(follow));
        return meter;
    }
    
    private TimeSignature parseMeter(final Set follow) {
        TimeSignature meter = null;
        if (this.m_tokenType.equals(AbcTokenType.C_METER)) {
            final String C = this.accept(AbcTokenType.C_METER, null, follow);
            if (C.equals("C")) {
                meter = new TimeSignature(4, 4);
            }
            else {
                meter = new TimeSignature(2, 2);
            }
        }
        else {
            meter = this.parseMeterFraction(follow);
        }
        return meter;
    }
    
    private TimeSignature parseMeterFraction(final Set follow) {
        final Set current = new Set(AbcTokenType.NUMBER).union(AbcTokenType.FRACTION);
        TimeSignature fraction = null;
        final String numString = this.accept(AbcTokenType.NUMBER, current, follow);
        current.remove(AbcTokenType.FRACTION);
        this.accept(AbcTokenType.FRACTION, current, follow);
        current.remove(AbcTokenType.NUMBER);
        final String denomString = this.accept(AbcTokenType.NUMBER, current, follow);
        if (numString != null && denomString != null) {
            final int num = Integer.parseInt(numString);
            final int denom = Integer.parseInt(denomString);
            fraction = new TimeSignature(num, denom);
        }
        return fraction;
    }
    
    private MultiPartsDefinition parseFieldParts(final Set follow) {
        final Set current = new Set().union(AbcParserAbstract.FIRST_PARTS).union(AbcParserAbstract.FIRST_END_OF_LINE);
        this.accept(AbcTokenType.FIELD_PARTS, current, follow);
        current.remove(AbcParserAbstract.FIRST_PARTS);
        final MultiPartsDefinition parts = this.parseParts(current.createUnion(follow));
        current.remove(AbcParserAbstract.FIRST_END_OF_LINE);
        this.parseEndOfLine(current.createUnion(follow));
        return parts;
    }
    
    private char parseFieldPart(final Set follow) {
        final Set current = new Set().union(AbcParserAbstract.FIRST_END_OF_LINE).union(AbcTokenType.PART);
        this.accept(AbcTokenType.FIELD_PARTS, current, follow);
        current.remove(AbcTokenType.PART);
        final String partLabelString = this.accept(AbcTokenType.PART, current, follow);
        char partLabel = ' ';
        if (partLabelString != null) {
            partLabel = partLabelString.charAt(0);
        }
        current.remove(AbcParserAbstract.FIRST_END_OF_LINE);
        this.parseEndOfLine(current.createUnion(follow));
        return partLabel;
    }
    
    private MultiPartsDefinition parseParts(final Set follow) {
        final Set current = new Set().union(AbcParserAbstract.FIRST_PART_SPEC);
        final MultiPartsDefinition parts = new MultiPartsDefinition();
        do {
            final RepeatedPartAbstract partSpec = this.parsePartSpec(current.createUnion(follow));
            if (partSpec != null) {
                parts.addPart(partSpec);
            }
        } while (AbcParserAbstract.FIRST_PART_SPEC.contains(this.m_tokenType));
        return parts;
    }
    
    private RepeatedPartAbstract parsePartSpec(final Set follow) {
        final Set current = new Set().union(AbcParserAbstract.FIRST_PART_SPEC).union(AbcTokenType.PART).union(AbcTokenType.PARENTHESIS_OPEN).union(AbcTokenType.PARENTHESIS_CLOSE).union(AbcTokenType.DIGIT);
        RepeatedPartAbstract parts = null;
        if (this.m_tokenType.equals(AbcTokenType.PARENTHESIS_OPEN)) {
            current.remove(AbcTokenType.PARENTHESIS_OPEN);
            this.accept(AbcTokenType.PARENTHESIS_OPEN, current, follow);
            parts = new MultiPartsDefinition();
            do {
                final RepeatedPartAbstract partSpec = this.parsePartSpec(current.createUnion(follow));
                if (partSpec != null) {
                    ((MultiPartsDefinition)parts).addPart(partSpec);
                }
            } while (AbcParserAbstract.FIRST_PART_SPEC.contains(this.m_tokenType));
            current.remove(AbcParserAbstract.FIRST_PART_SPEC);
            current.remove(AbcTokenType.PARENTHESIS_CLOSE);
            this.accept(AbcTokenType.PARENTHESIS_CLOSE, current, follow, true);
        }
        else {
            current.remove(AbcTokenType.PART);
            final String partNameAsString = this.accept(AbcTokenType.PART, current, follow, true);
            current.remove(AbcTokenType.PARENTHESIS_OPEN);
            current.remove(AbcTokenType.PARENTHESIS_CLOSE);
            if (partNameAsString != null) {
                final char partName = partNameAsString.charAt(0);
                if (this.m_tune.getPart(partName) != null) {
                    parts = new RepeatedPart(this.m_tune.getPart(partName));
                }
                else {
                    parts = new RepeatedPart(this.m_tune.createPart(partName));
                }
            }
        }
        if (this.m_tokenType.equals(AbcTokenType.DIGIT)) {
            current.remove(AbcTokenType.DIGIT);
            final String digit = this.accept(AbcTokenType.DIGIT, current, follow);
            if (digit != null && parts != null) {
                parts.setNumberOfRepeats(Integer.parseInt(digit));
            }
        }
        return parts;
    }
    
    private Tempo parseFieldTempo(final Set follow) {
        final Set current = new Set().union(AbcParserAbstract.FIRST_TEMPO).union(AbcParserAbstract.FIRST_END_OF_LINE);
        this.accept(AbcTokenType.FIELD_TEMPO, current, follow);
        current.remove(AbcParserAbstract.FIRST_TEMPO);
        final Tempo tempo = this.parseTempo(current.createUnion(follow));
        current.remove(AbcParserAbstract.FIRST_END_OF_LINE);
        this.parseEndOfLine(current.createUnion(follow));
        return tempo;
    }
    
    private Tempo parseTempo(final Set follow) {
        Set current = null;
        Tempo tempo = null;
        int tempoValue = -1;
        if (this.m_tokenType.equals(AbcTokenType.C_TEMPO)) {
            current = new Set().union(AbcParserAbstract.FIRST_NOTE_LENGTH).union(AbcTokenType.EQUALS).union(AbcTokenType.NUMBER);
            short refLength = -1;
            this.accept(AbcTokenType.C_TEMPO, current, follow);
            current = new Set(AbcTokenType.EQUALS).union(AbcTokenType.NUMBER);
            Fraction length = new Fraction(1, 1);
            if (AbcParserAbstract.FIRST_NOTE_LENGTH.contains(this.m_tokenType)) {
                length = this.parseNoteLength(current.createUnion(follow));
                if (length != null) {
                    refLength = (short)(this.m_defaultNoteLength * length.floatValue());
                }
            }
            current.remove(AbcTokenType.EQUALS);
            this.accept(AbcTokenType.EQUALS, current, follow);
            current.remove(AbcTokenType.NUMBER);
            final String tempoString = this.accept(AbcTokenType.NUMBER, current, follow);
            if (tempoString != null) {
                tempoValue = new Integer(tempoString);
            }
            if (refLength != -1 && tempoValue != -1) {
                tempo = new Tempo(refLength, tempoValue);
            }
        }
        else {
            current = new Set(AbcTokenType.FRACTION);
            String tempoString2 = this.accept(AbcTokenType.NUMBER, current, follow, true);
            if (this.m_tokenType.equals(AbcTokenType.FRACTION)) {
                final String num = tempoString2;
                current = new Set(AbcTokenType.NUMBER).union(AbcTokenType.EQUALS);
                this.accept(AbcTokenType.FRACTION, current, follow);
                final String denom = this.accept(AbcTokenType.NUMBER, current, follow);
                current.remove(AbcTokenType.EQUALS);
                this.accept(AbcTokenType.EQUALS, current, follow);
                current.remove(AbcTokenType.NUMBER);
                tempoString2 = this.accept(AbcTokenType.NUMBER, current, follow);
                if (num != null && denom != null && tempoString2 != null) {
                    try {
                        tempo = new Tempo(Note.convertToNoteLengthStrict(new Integer(num), new Integer(denom)), new Integer(tempoString2));
                    }
                    catch (IllegalArgumentException ex) {}
                }
            }
            else if (tempoString2 != null) {
                tempo = new Tempo(this.m_defaultNoteLength, new Integer(tempoString2));
            }
        }
        return tempo;
    }
    
    private AbcTextField parseFieldHistory(final Set follow) {
        final Set current = new Set().union(AbcParserAbstract.FIRST_END_OF_LINE).union(AbcTokenType.TEXT);
        AbcTextField history = null;
        this.accept(AbcTokenType.FIELD_HISTORY, current, follow);
        do {
            final String acc = this.accept(AbcTokenType.TEXT, current, follow);
            if (acc != null) {
                if (history == null) {
                    history = new AbcTextField((byte)7, acc);
                }
                else {
                    final String text = history.getText().concat(acc);
                    history = new AbcTextField((byte)7, text);
                }
            }
            this.parseEndOfLine(current.createUnion(follow));
        } while (this.m_tokenType.equals(AbcTokenType.TEXT));
        return history;
    }
    
    private KeySignature parseFieldKey(final Set follow) {
        KeySignature key = null;
        Set current = new Set().union(AbcParserAbstract.FIRST_KEY).union(AbcParserAbstract.FIRST_END_OF_LINE);
        this.accept(AbcTokenType.FIELD_KEY, current, follow);
        current.remove(AbcParserAbstract.FIRST_KEY);
        key = this.parseKey(current.createUnion(follow));
        current.remove(AbcParserAbstract.FIRST_END_OF_LINE);
        current = current.union(this.FIRST_ABC_MUSIC);
        this.parseEndOfLine(current.createUnion(follow));
        return key;
    }
    
    private KeySignature parseKey(final Set follow) {
        if (this.m_tokenType.equals(AbcTokenType.KEY_HP)) {
            this.accept(AbcTokenType.KEY_HP, null, follow);
            return null;
        }
        return this.parseKeySpec(follow);
    }
    
    private KeySignature parseKeySpec(final Set follow) {
        Set current = new Set().union(AbcParserAbstract.FIRST_MODE_SPEC).union(AbcTokenType.SPACE).union(this.FIRST_GLOBAL_ACCIDENTAL);
        KeySignature key = null;
        Note note = null;
        byte modeSpec = 5;
        note = this.parseKeyNote(current.createUnion(follow));
        if (AbcParserAbstract.FIRST_MODE_SPEC.contains(this.m_tokenType)) {
            current = new Set(AbcTokenType.SPACE).union(this.FIRST_GLOBAL_ACCIDENTAL);
            modeSpec = this.parseModeSpec(current.createUnion(follow));
        }
        if (note != null && modeSpec != -1) {
            key = new KeySignature(note.getHeight(), note.getAccidental(), modeSpec);
        }
        while (this.m_tokenType.equals(AbcTokenType.SPACE)) {
            this.accept(AbcTokenType.SPACE, current, follow, true);
            if (this.FIRST_GLOBAL_ACCIDENTAL.contains(this.m_tokenType)) {
                final byte[] ga = this.parseGlobalAccidental(current.createUnion(follow));
                if (ga == null || key == null) {
                    continue;
                }
                key.setAccidental(ga[0], ga[1]);
            }
        }
        return key;
    }
    
    private Note parseKeyNote(final Set follow) {
        final Set current = new Set(AbcTokenType.KEY_ACCIDENTAL);
        Note keyNote = null;
        String note = null;
        String accidental = null;
        note = this.accept(AbcTokenType.BASE_NOTE, current, follow, true);
        if (this.m_tokenType.equals(AbcTokenType.KEY_ACCIDENTAL)) {
            current.remove(AbcTokenType.KEY_ACCIDENTAL);
            accidental = this.accept(AbcTokenType.KEY_ACCIDENTAL, current, follow);
        }
        if (note != null) {
            keyNote = new Note(Note.convertToNoteType(note), KeySignature.convertToAccidentalType(accidental));
        }
        return keyNote;
    }
    
    private byte parseModeSpec(final Set follow) {
        final Set current = new Set().union(AbcParserAbstract.FIRST_MODE).union(AbcTokenType.TEXT);
        byte modeType = 5;
        current.remove(AbcParserAbstract.FIRST_MODE);
        final String stringMode = this.accept(AbcTokenType.MODE, current, follow, true);
        modeType = KeySignature.convertToModeType(stringMode);
        if (this.m_tokenType.equals(AbcTokenType.TEXT)) {
            current.remove(AbcTokenType.TEXT);
            this.accept(AbcTokenType.TEXT, current, follow);
        }
        return modeType;
    }
    
    private void parseEndOfLine(final Set follow) {
        final Set current = new Set(AbcTokenType.SPACE).union(AbcTokenType.COMMENT).union(AbcTokenType.TEXT).union(AbcTokenType.LINE_FEED);
        while (this.m_tokenType.equals(AbcTokenType.SPACE)) {
            this.accept(AbcTokenType.SPACE, current, follow);
        }
        current.remove(AbcTokenType.SPACE);
        if (this.m_tokenType.equals(AbcTokenType.COMMENT)) {
            current.remove(AbcTokenType.COMMENT);
            this.accept(AbcTokenType.COMMENT, current, follow);
            current.remove(AbcTokenType.TEXT);
            this.accept(AbcTokenType.TEXT, current, follow);
        }
        else {
            current.remove(AbcTokenType.COMMENT);
            current.remove(AbcTokenType.TEXT);
        }
        current.remove(AbcTokenType.LINE_FEED);
        this.accept(AbcTokenType.LINE_FEED, current, follow);
    }
    
    protected Tune parseAbcHeader(final Set follow) {
        this.m_tune = new Tune();
        this.m_music = this.m_tune.getMusic();
        this.brknRthmDotsCorrection = 0;
        this.slursDefinitionStack.removeAllElements();
        this.lastParsedNote = null;
        this.m_defaultNoteLength = 24;
        this.m_timeSignature = null;
        Set current = new Set().union(AbcParserAbstract.FIRST_COMMENT).union(AbcParserAbstract.FIRST_FIELD_TITLE).union(AbcParserAbstract.FIRST_OTHER_FIELDS).union(AbcParserAbstract.FIRST_FIELD_KEY);
        final Integer number = this.parseFieldNumber(current.createUnion(follow));
        if (number != null) {
            this.m_tune.setReferenceNumber(number);
        }
        while (this.m_tokenType.equals(AbcTokenType.COMMENT)) {
            this.parseComment(current.createUnion(follow));
        }
        current = new Set().union(AbcParserAbstract.FIRST_FIELD_TITLE).union(AbcParserAbstract.FIRST_OTHER_FIELDS).union(AbcParserAbstract.FIRST_FIELD_KEY);
        do {
            final AbcTextField title = this.parseField(AbcTokenType.FIELD_TITLE, current.createUnion(follow));
            if (title != null) {
                this.m_tune.addTitle(title.getText());
            }
        } while (this.m_tokenType.equals(AbcTokenType.FIELD_TITLE));
        current.remove(AbcParserAbstract.FIRST_FIELD_TITLE);
        while (AbcParserAbstract.FIRST_OTHER_FIELDS.contains(this.m_tokenType)) {
            this.parseOtherFields(current.createUnion(follow));
        }
        current.remove(AbcParserAbstract.FIRST_OTHER_FIELDS);
        current.remove(AbcParserAbstract.FIRST_FIELD_KEY);
        final KeySignature key = this.parseFieldKey(current.createUnion(follow));
        if (key != null) {
            this.m_music.addElement(key);
        }
        return this.m_tune;
    }
    
    private Integer parseFieldNumber(final Set follow) {
        final Set current = new Set(AbcTokenType.NUMBER).union(AbcParserAbstract.FIRST_END_OF_LINE);
        Integer number = null;
        this.accept(AbcTokenType.FIELD_NUMBER, current, follow);
        current.remove(AbcTokenType.NUMBER);
        final String acc = this.accept(AbcTokenType.NUMBER, current, follow);
        if (acc != null) {
            number = new Integer(acc);
        }
        current.remove(AbcParserAbstract.FIRST_END_OF_LINE);
        this.parseEndOfLine(current.createUnion(follow));
        return number;
    }
    
    private byte[] parseGlobalAccidental(final Set follow) {
        final Set current = new Set(AbcTokenType.BASE_NOTE);
        final byte[] globalAccidental = new byte[2];
        final String keyAcc = this.accept(AbcTokenType.ACCIDENTAL, current, follow);
        final byte accidentalType = Note.convertToAccidentalType(keyAcc);
        current.remove(AbcTokenType.BASE_NOTE);
        final String noteHeigthString = this.accept(AbcTokenType.BASE_NOTE, current, follow);
        final byte noteHeigth = Note.convertToNoteType(noteHeigthString);
        globalAccidental[0] = noteHeigth;
        globalAccidental[1] = accidentalType;
        return globalAccidental;
    }
    
    protected void parseComment(final Set follow) {
        final Set current = new Set(AbcTokenType.TEXT).union(AbcTokenType.LINE_FEED).union(AbcTokenType.LINE_BREAK).union(AbcTokenType.NO_LINE_BREAK);
        this.accept(AbcTokenType.COMMENT, current, follow);
        current.remove(AbcTokenType.TEXT);
        this.accept(AbcTokenType.TEXT, current, follow);
        current.remove(AbcTokenType.LINE_FEED);
        current.remove(AbcTokenType.LINE_BREAK);
        current.remove(AbcTokenType.NO_LINE_BREAK);
        if (this.m_tokenType.equals(AbcTokenType.LINE_BREAK)) {
            this.accept(AbcTokenType.LINE_BREAK, current, follow);
        }
        else if (this.m_tokenType.equals(AbcTokenType.NO_LINE_BREAK)) {
            this.accept(AbcTokenType.NO_LINE_BREAK, current, follow);
        }
        else {
            this.accept(AbcTokenType.LINE_FEED, current, follow);
        }
    }
    
    protected void parseAbcMusic(final Set follow) {
        final Set current = new Set().union(this.FIRST_ABC_LINE).union(AbcTokenType.LINE_FEED);
        do {
            this.parseAbcLine(current.createUnion(follow));
        } while (this.FIRST_ABC_LINE.contains(this.m_tokenType));
    }
    
    protected void parseAbcLine(final Set follow) {
        if (AbcParserAbstract.FIRST_ELEMENT.contains(this.m_tokenType)) {
            final Set current = new Set().union(AbcParserAbstract.FIRST_ELEMENT).union(AbcParserAbstract.FIRST_LINE_ENDER);
            final Set currentUnionFollow = current.createUnion(follow);
            do {
                this.parseElement(currentUnionFollow);
            } while (AbcParserAbstract.FIRST_ELEMENT.contains(this.m_tokenType));
            current.remove(AbcParserAbstract.FIRST_ELEMENT);
            current.remove(AbcParserAbstract.FIRST_LINE_ENDER);
            final Object lineEnder = this.parseLineEnder(current.createUnion(follow));
            if (lineEnder != null) {
                this.m_music.addElement(lineEnder);
            }
        }
        else if (AbcParserAbstract.FIRST_MID_TUNE_FIELD.contains(this.m_tokenType)) {
            this.parseMidTuneField(follow);
        }
    }
    
    private void parseMidTuneField(final Set follow) {
        this.parseTuneField(follow);
    }
    
    private void parseTuneField(final Set follow) {
        if (AbcParserAbstract.FIRST_FIELD_KEY.contains(this.m_tokenType)) {
            final KeySignature key = this.parseFieldKey(follow);
            if (key != null) {
                this.m_music.addElement(key);
            }
        }
        else if (AbcParserAbstract.FIRST_FIELD_DEFAULT_LENGTH.contains(this.m_tokenType)) {
            final short defaultNoteLength = this.parseFieldDefaultLength(follow);
            if (defaultNoteLength != -1) {
                this.m_defaultNoteLength = defaultNoteLength;
            }
        }
        else if (AbcParserAbstract.FIRST_FIELD_METER.contains(this.m_tokenType)) {
            final TimeSignature meter = this.parseFieldMeter(follow);
            if (meter != null) {
                this.m_music.addElement(meter);
                this.m_defaultNoteLength = meter.getDefaultNoteLength();
                this.m_timeSignature = meter;
            }
        }
        else if (AbcParserAbstract.FIRST_FIELD_PART.contains(this.m_tokenType)) {
            final char partLabel = this.parseFieldPart(follow);
            if (this.m_tune.getPart(partLabel) != null) {
                this.m_music = this.m_tune.getPart(partLabel).getMusic();
            }
            else {
                this.m_music = this.m_tune.createPart(partLabel).getMusic();
            }
        }
        else if (AbcParserAbstract.FIRST_FIELD_TEMPO.contains(this.m_tokenType)) {
            final Tempo tempo = this.parseFieldTempo(follow);
            if (tempo != null) {
                this.m_music.addElement(tempo);
            }
        }
        else if (AbcParserAbstract.FIRST_FIELD_WORDS.contains(this.m_tokenType)) {
            final AbcTextField text = this.parseField(AbcTokenType.FIELD_WORDS, follow);
            if (text != null) {
                this.m_music.addElement(new Words(text.getText()));
            }
        }
        else {
            this.parseField(AbcTokenType.FIELD_TITLE, follow);
        }
    }
    
    private void parseElement(final Set follow) {
        if (AbcParserAbstract.FIRST_NOTE_ELEMENT.contains(this.m_tokenType)) {
            final NoteAbstract note = this.parseNoteElement(follow);
            this.m_music.addElement(note);
        }
        else if (AbcParserAbstract.FIRST_TUPLET_ELEMENT.contains(this.m_tokenType)) {
            final Tuplet tuplet = this.parseTupletElement(follow);
            final Vector notes = tuplet.getNotesAsVector();
            for (int i = 0; i < notes.size(); ++i) {
                this.m_music.addElement(notes.elementAt(i));
            }
        }
        else if (AbcParserAbstract.FIRST_BARLINE.contains(this.m_tokenType)) {
            final byte[] barLineTypes = BarLine.convertToBarLine(this.accept(AbcTokenType.BARLINE, null, follow));
            for (int j = 0; j < barLineTypes.length; ++j) {
                this.m_music.addElement(new BarLine(barLineTypes[j]));
            }
        }
        else if (AbcParserAbstract.FIRST_NTH_REPEAT.contains(this.m_tokenType)) {
            final byte repeatNumber = convertToRepeatBarLine(this.accept(AbcTokenType.NTH_REPEAT, null, follow));
            this.m_music.addElement(new RepeatBarLine(repeatNumber));
        }
        else if (this.m_tokenType.equals(AbcTokenType.BEGIN_SLUR)) {
            this.accept(AbcTokenType.BEGIN_SLUR, null, follow);
            final SlurDefinition def = new SlurDefinition();
            this.slursDefinitionStack.addElement(def);
        }
        else if (this.m_tokenType.equals(AbcTokenType.END_SLUR)) {
            this.accept(AbcTokenType.END_SLUR, null, follow);
            if (!this.slursDefinitionStack.isEmpty()) {
                final SlurDefinition slurDef = this.slursDefinitionStack.elementAt(this.slursDefinitionStack.size() - 1);
                slurDef.setEnd(this.lastParsedNote);
                this.slursDefinitionStack.removeElementAt(this.slursDefinitionStack.size() - 1);
                this.lastParsedNote.setSlurDefinition(slurDef);
            }
        }
        else if (this.m_tokenType.equals(AbcTokenType.SPACE)) {
            this.accept(AbcTokenType.SPACE, null, follow);
            final NoteAbstract lastScoreNote = this.m_music.getLastNote();
            if (lastScoreNote != null && !lastScoreNote.equals(this.lastNoteFlaggedAsEndOfGroup)) {
                this.m_music.addElement(new NotesSeparator());
            }
        }
    }
    
    private Tuplet parseTupletElement(final Set follow) {
        final Set current = new Set().union(AbcParserAbstract.FIRST_NOTE_ELEMENT);
        final int[] tupletSpec = this.parseTupletSpec(current.createUnion(follow));
        if (tupletSpec[1] == -1) {
            if (tupletSpec[0] == 2 || tupletSpec[0] == 4 || tupletSpec[0] == 8) {
                tupletSpec[1] = 3;
            }
            else if (tupletSpec[0] == 3 || tupletSpec[0] == 6) {
                tupletSpec[1] = 2;
            }
            else if (tupletSpec[0] == 5 || tupletSpec[0] == 7 || tupletSpec[0] == 9) {
                if (this.m_timeSignature.isCoumpound()) {
                    tupletSpec[1] = 3;
                }
                else {
                    tupletSpec[1] = 2;
                }
            }
            else {
                System.err.println("Cannot evaluate tuplet : no time signature");
            }
        }
        if (tupletSpec[2] == -1) {
            tupletSpec[2] = tupletSpec[0];
        }
        final Vector notes = new Vector();
        int notesNumber = tupletSpec[2];
        do {
            final NoteAbstract note = this.parseNoteElement(current.createUnion(follow));
            if (note != null) {
                notes.addElement(note);
            }
            --notesNumber;
        } while (AbcParserAbstract.FIRST_NOTE_ELEMENT.contains(this.m_tokenType) && notesNumber > 0);
        return new Tuplet(notes, tupletSpec[1], this.m_defaultNoteLength);
    }
    
    private int[] parseTupletSpec(final Set follow) {
        final Set current = new Set(AbcTokenType.COMA).union(AbcTokenType.DIGIT);
        final int[] tupletDesc = { -1, -1, -1 };
        String t = this.accept(AbcTokenType.TUPLET_SPEC, current, follow, true);
        if (t != null) {
            tupletDesc[0] = Character.getNumericValue(t.charAt(t.length() - 1));
        }
        if (this.m_tokenType.equals(AbcTokenType.COMA)) {
            this.accept(AbcTokenType.COMA, current, follow, true);
            if (this.m_tokenType.equals(AbcTokenType.DIGIT)) {
                t = this.accept(AbcTokenType.DIGIT, current, follow);
                tupletDesc[1] = Integer.parseInt(t);
            }
            if (this.m_tokenType.equals(AbcTokenType.COMA)) {
                this.accept(AbcTokenType.COMA, current, follow, true);
                if (this.m_tokenType.equals(AbcTokenType.DIGIT)) {
                    t = this.accept(AbcTokenType.DIGIT, current, follow, true);
                    tupletDesc[2] = Integer.parseInt(t);
                }
            }
        }
        return tupletDesc;
    }
    
    private NoteAbstract parseNoteElement(final Set follow) {
        final Set current = new Set().union(AbcParserAbstract.FIRST_BROKEN_RHYTHM);
        final NoteAbstract note = this.parseNoteStem(current.createUnion(follow));
        if (note != null && this.brknRthmDotsCorrection != 0) {
            if (this.brknRthmDotsCorrection > 0) {
                note.setDotted(this.brknRthmDotsCorrection);
            }
            else if (this.brknRthmDotsCorrection < 0) {
                final short correctedDuration = (short)(((Note)note).getStrictDuration() / Math.pow(2.0, -this.brknRthmDotsCorrection));
                try {
                    ((Note)note).setStrictDuration(correctedDuration);
                }
                catch (IllegalArgumentException e) {
                    ((Note)note).setDuration(correctedDuration);
                }
            }
            this.brknRthmDotsCorrection = 0;
        }
        if (this.m_tokenType.equals(AbcTokenType.BROKEN_RHYTHM)) {
            current.remove(AbcParserAbstract.FIRST_BROKEN_RHYTHM);
            final String brokenRhythmString = this.accept(AbcTokenType.BROKEN_RHYTHM, current, follow);
            final byte brokenRhythm = convertBrokenRhythm(brokenRhythmString);
            if (note != null) {
                if (brokenRhythm > 0) {
                    note.setDotted(brokenRhythm);
                    this.brknRthmDotsCorrection = (byte)(-brokenRhythm);
                }
                else if (brokenRhythm < 0) {
                    final short correctedDuration2 = (short)(((Note)note).getStrictDuration() / Math.pow(2.0, -brokenRhythm));
                    try {
                        ((Note)note).setStrictDuration(correctedDuration2);
                    }
                    catch (IllegalArgumentException e2) {
                        ((Note)note).setDuration(correctedDuration2);
                    }
                    this.brknRthmDotsCorrection = (byte)(-brokenRhythm);
                }
            }
        }
        return note;
    }
    
    private NoteAbstract parseNoteStem(final Set follow) {
        final Set current = new Set().union(AbcParserAbstract.FIRST_GUITAR_CHORD).union(AbcParserAbstract.FIRST_GRACE_NOTES).union(AbcParserAbstract.FIRST_GRACINGS).union(AbcParserAbstract.FIRST_NOTE).union(AbcParserAbstract.FIRST_MULTI_NOTE);
        NoteAbstract note = null;
        Note[] graceNotes = null;
        boolean hasGeneralOrnament = false;
        boolean up = false;
        boolean down = false;
        boolean staccato = false;
        String chordName = null;
        current.remove(AbcParserAbstract.FIRST_GUITAR_CHORD);
        if (AbcParserAbstract.FIRST_GUITAR_CHORD.contains(this.m_tokenType)) {
            chordName = this.parseGuitarChord(current.createUnion(follow));
        }
        current.remove(AbcParserAbstract.FIRST_GRACE_NOTES);
        if (AbcParserAbstract.FIRST_GRACE_NOTES.contains(this.m_tokenType)) {
            graceNotes = this.parseGraceNotes(current.createUnion(follow));
        }
        while (this.m_tokenType.equals(AbcTokenType.GRACING)) {
            final String acc = this.accept(AbcTokenType.GRACING, current, follow);
            if (acc != null) {
                if (acc.equals(".")) {
                    staccato = true;
                }
                else if (acc.equals("~")) {
                    hasGeneralOrnament = true;
                }
                else if (acc.equals("u")) {
                    up = true;
                }
                else {
                    if (!acc.equals("v")) {
                        continue;
                    }
                    down = true;
                }
            }
        }
        current.remove(AbcParserAbstract.FIRST_GRACINGS);
        current.remove(AbcParserAbstract.FIRST_NOTE);
        current.remove(AbcParserAbstract.FIRST_MULTI_NOTE);
        if (this.m_tokenType.equals(AbcTokenType.MULTI_NOTE_BEGIN)) {
            final Vector notes = this.parseMultiNote(current.createUnion(follow));
            if (notes.size() != 0) {
                note = new PositionableMultiNote(notes);
            }
        }
        else {
            note = this.parseNote(current.createUnion(follow));
            if (note != null) {
                if (staccato) {
                    note.setStaccato(true);
                }
                if (hasGeneralOrnament) {
                    note.setGeneralGracing(true);
                }
                if (up) {
                    note.setBow((byte)2);
                }
                if (down) {
                    note.setBow((byte)1);
                }
                if (chordName != null) {
                    note.setChordName(chordName);
                }
                if (graceNotes != null) {
                    note.setGracingNotes(graceNotes);
                }
                if (!this.slursDefinitionStack.isEmpty()) {
                    note.setPartOfSlur(true);
                    final SlurDefinition currentSlurDef = this.slursDefinitionStack.elementAt(this.slursDefinitionStack.size() - 1);
                    if (currentSlurDef.getStart() == null) {
                        currentSlurDef.setStart(note);
                        note.setSlurDefinition(currentSlurDef);
                    }
                }
                this.lastParsedNote = note;
            }
        }
        return note;
    }
    
    private Vector parseMultiNote(final Set follow) {
        final Set current = new Set().union(AbcParserAbstract.FIRST_NOTE).union(AbcTokenType.MULTI_NOTE_END);
        final Vector notes = new Vector();
        this.accept(AbcTokenType.MULTI_NOTE_BEGIN, current, follow);
        while (AbcParserAbstract.FIRST_NOTE.contains(this.m_tokenType)) {
            final Note note = this.parseNote(current.createUnion(follow));
            if (note != null) {
                notes.addElement(note);
            }
        }
        current.remove(AbcParserAbstract.FIRST_NOTE);
        current.remove(AbcTokenType.MULTI_NOTE_END);
        this.accept(AbcTokenType.MULTI_NOTE_END, current, follow);
        return notes;
    }
    
    private Note[] parseGraceNotes(final Set follow) {
        final Set current = new Set().union(AbcParserAbstract.FIRST_PITCH).union(AbcTokenType.GRACING_END);
        this.accept(AbcTokenType.GRACING_BEGIN, current, follow);
        final Vector gracingNotes = new Vector();
        while (AbcParserAbstract.FIRST_PITCH.contains(this.m_tokenType)) {
            final Note note = this.parsePitch(current.createUnion(follow));
            if (note != null) {
                gracingNotes.addElement(note);
            }
        }
        current.remove(AbcParserAbstract.FIRST_PITCH);
        current.remove(AbcTokenType.GRACING_END);
        this.accept(AbcTokenType.GRACING_END, current, follow);
        if (gracingNotes.isEmpty()) {
            return null;
        }
        final Note[] gracings = new Note[gracingNotes.size()];
        for (int i = 0; i < gracingNotes.size(); ++i) {
            gracings[i] = gracingNotes.elementAt(i);
        }
        return gracings;
    }
    
    private String parseGuitarChord(final Set follow) {
        final Set current = new Set().union(AbcTokenType.GUITAR_CHORD).union(AbcTokenType.CHORD_NAME);
        String chordName = null;
        this.accept(AbcTokenType.GUITAR_CHORD, current, follow);
        current.remove(AbcTokenType.CHORD_NAME);
        chordName = this.accept(AbcTokenType.CHORD_NAME, current, follow);
        current.remove(AbcTokenType.GUITAR_CHORD);
        this.accept(AbcTokenType.GUITAR_CHORD, current, follow);
        return chordName;
    }
    
    private Note parseNote(final Set follow) {
        final Set current = new Set().union(AbcParserAbstract.FIRST_NOTE_LENGTH).union(AbcParserAbstract.FIRST_TIE);
        boolean isTied = false;
        PositionableNote note = null;
        CharStreamPosition startPosition = null;
        if (this.m_token != null) {
            startPosition = this.m_token.getPosition();
        }
        note = (PositionableNote)this.parseNoteOrRest(current.createUnion(follow));
        current.remove(AbcParserAbstract.FIRST_NOTE_LENGTH);
        if (AbcParserAbstract.FIRST_NOTE_LENGTH.contains(this.m_tokenType)) {
            final Fraction length = this.parseNoteLength(current.createUnion(follow));
            if (note != null) {
                try {
                    final AbcToolkit.DurationDescription d = AbcToolkit.getAbsoluteDurationFor(length, this.m_defaultNoteLength);
                    note.setStrictDuration(d.getStrictDuration());
                    note.setDotted(d.countDots());
                }
                catch (IllegalArgumentException e) {
                    note.setDuration((short)(this.m_defaultNoteLength * length.floatValue()));
                }
            }
        }
        else if (note != null) {
            note.setStrictDuration(this.m_defaultNoteLength);
        }
        current.remove(AbcParserAbstract.FIRST_TIE);
        if (this.m_tokenType.equals(AbcTokenType.TIE)) {
            this.accept(AbcTokenType.TIE, current, follow);
            isTied = true;
        }
        else {
            isTied = false;
        }
        if (note != null) {
            final CharStreamPosition endPosition = this.m_scanner.getPosition();
            final int length2 = endPosition.getCharactersOffset() - startPosition.getCharactersOffset();
            note.setBeginPosition(startPosition);
            note.setLength(length2);
            if (isTied) {
                final TieDefinition tieDef = new TieDefinition();
                tieDef.setStart(note);
                note.setTieDefinition(tieDef);
                this.addNoteStartingTieFor(note);
            }
            else {
                final Note startTieNote = this.getNoteStartingTieFor(note);
                if (startTieNote != null) {
                    startTieNote.getTieDefinition().setEnd(note);
                    note.setTieDefinition(startTieNote.getTieDefinition());
                    this.removeNoteStartingTieFor(startTieNote);
                }
            }
        }
        return note;
    }
    
    private Note parseNoteOrRest(final Set follow) {
        if (this.m_tokenType.equals(AbcTokenType.REST)) {
            final PositionableNote note = new PositionableNote(Note.convertToNoteType(this.accept(AbcTokenType.REST, null, follow)), (byte)10);
            return note;
        }
        return this.parsePitch(follow);
    }
    
    private Note parsePitch(final Set follow) {
        final Set current = new Set().union(AbcParserAbstract.FIRST_BASE_NOTE).union(AbcParserAbstract.FIRST_OCTAVE);
        Note note = null;
        byte accidental = 10;
        byte noteHeigth = 0;
        byte octaveTransposition = 0;
        if (this.m_tokenType.equals(AbcTokenType.ACCIDENTAL)) {
            accidental = Note.convertToAccidentalType(this.accept(AbcTokenType.ACCIDENTAL, current, follow));
        }
        current.remove(AbcParserAbstract.FIRST_BASE_NOTE);
        final String heigth = this.accept(AbcTokenType.BASE_NOTE, current, follow, true);
        if (heigth != null) {
            noteHeigth = Note.convertToNoteType(heigth);
        }
        current.remove(AbcParserAbstract.FIRST_OCTAVE);
        if (AbcParserAbstract.FIRST_OCTAVE.contains(this.m_tokenType)) {
            final String octave = this.accept(AbcTokenType.OCTAVE, current, follow);
            if (octave != null) {
                octaveTransposition = convertToOctaveTransposition(octave);
            }
        }
        if (heigth != null) {
            note = new PositionableNote(noteHeigth, accidental, octaveTransposition);
        }
        return note;
    }
    
    private MusicPresentationElement parseLineEnder(final Set follow) {
        MusicPresentationElement lineEnder = null;
        if (AbcParserAbstract.FIRST_COMMENT.contains(this.m_tokenType)) {
            this.parseComment(follow);
        }
        else if (this.m_tokenType.equals(AbcTokenType.LINE_BREAK)) {
            lineEnder = ((this.accept(AbcTokenType.LINE_BREAK, null, follow) == null) ? null : new EndOfStaffLine());
        }
        else if (this.m_tokenType.equals(AbcTokenType.NO_LINE_BREAK)) {
            this.accept(AbcTokenType.NO_LINE_BREAK, null, follow);
        }
        else {
            lineEnder = ((this.accept(AbcTokenType.LINE_FEED, null, follow) == null) ? null : new EndOfStaffLine());
        }
        return lineEnder;
    }
    
    protected Note getNoteStartingTieFor(final Note aNote) {
        for (int i = 0; i < this.notesStartingTies.size(); ++i) {
            if (this.notesStartingTies.elementAt(i).getHeight() == aNote.getHeight()) {
                return this.notesStartingTies.elementAt(i);
            }
        }
        return null;
    }
    
    protected boolean removeNoteStartingTieFor(final Note aNote) {
        return this.notesStartingTies.removeElement(aNote);
    }
    
    protected void addNoteStartingTieFor(final Note aNote) {
        this.notesStartingTies.addElement(aNote);
    }
    
    protected String accept(final TokenType tokenType, final Set current, final Set follow) {
        return this.accept(tokenType, current, follow, false);
    }
    
    protected String accept(final TokenType tokenType, final Set current, final Set follow, final boolean isCurrentOptional) {
        String value2return = null;
        if (this.m_tokenType == tokenType) {
            this.notifyListenersForValidToken(this.m_token);
            value2return = this.m_token.getValue();
            if (isCurrentOptional) {
                final Set union = this.getSetResultingUnionFrom(current, follow);
                this.m_automata.setDefinition(DefinitionFactory.getDefinition(union.getTypes()));
                this.m_scanner.setFinaleStateAutomata(this.m_automata);
            }
            else if (current != null && current.size() != 0) {
                this.m_automata.setDefinition(DefinitionFactory.getDefinition(current.getTypes()));
                this.m_scanner.setFinaleStateAutomata(this.m_automata);
            }
            else {
                this.m_automata.setDefinition(DefinitionFactory.getDefinition(follow.getTypes()));
                this.m_scanner.setFinaleStateAutomata(this.m_automata);
            }
            try {
                this.m_token = this.m_scanner.nextToken();
                this.m_tokenType = this.m_token.getType();
            }
            catch (NoSuchTokenException e) {
                this.m_token = null;
                this.m_tokenType = TokenType.UNKNOWN;
            }
        }
        else {
            if (this.m_token != null) {
                this.notifyListenersForInvalidToken(this.m_token, this.m_token.getPosition(), tokenType);
            }
            else {
                this.notifyListenersForInvalidToken(null, this.m_scanner.getPosition(), tokenType);
            }
            this.skipTo(current, follow, isCurrentOptional);
        }
        return value2return;
    }
    
    private Set getSetResultingUnionFrom(final Set current, final Set follow) {
        if (current == null) {
            return follow;
        }
        return current.createUnion(follow);
    }
    
    protected void skipTo(final Set current, final Set follow, final boolean isCurrentOptionnal) {
        Set targetSet = null;
        if (!isCurrentOptionnal && current != null && current.size() != 0) {
            targetSet = current;
        }
        else {
            targetSet = this.getSetResultingUnionFrom(current, follow);
        }
        this.m_automata.setDefinition(DefinitionFactory.getDefinition(targetSet.getTypes()));
        this.m_scanner.setFinaleStateAutomata(this.m_automata);
        try {
            boolean tokenFound = false;
            while (!tokenFound) {
                tokenFound = targetSet.contains(this.m_tokenType);
                if (!tokenFound) {
                    this.m_token = this.m_scanner.nextToken();
                    this.m_tokenType = this.m_token.getType();
                }
            }
        }
        catch (NoSuchTokenException e) {
            this.m_token = null;
            this.m_tokenType = TokenType.UNKNOWN;
        }
    }
    
    protected void notifyListenersForTuneBegin() {
        for (int i = 0; i < this.m_listeners.size(); ++i) {
            this.m_listeners.elementAt(i).tuneBegin();
        }
    }
    
    protected void notifyListenersForTuneEnd(final Tune tune) {
        for (int i = 0; i < this.m_listeners.size(); ++i) {
            this.m_listeners.elementAt(i).tuneEnd(tune);
        }
    }
    
    protected void notifyListenersForValidToken(final Token token) {
        final TokenEvent evt = new TokenEvent(this, token);
        for (int i = 0; i < this.m_listeners.size(); ++i) {
            this.m_listeners.elementAt(i).validToken(evt);
        }
    }
    
    protected void notifyListenersForInvalidToken(final Token token, final CharStreamPosition position, final TokenType expectedTokenType) {
        InvalidTokenEvent evt = null;
        if (token != null) {
            evt = new InvalidTokenEvent(this, token, expectedTokenType);
        }
        else {
            evt = new InvalidTokenEvent(this, position, expectedTokenType);
        }
        for (int i = 0; i < this.m_listeners.size(); ++i) {
            this.m_listeners.elementAt(i).invalidToken(evt);
        }
    }
    
    protected void notifyListenersForInvalidCharacter(final InvalidCharacterEvent evt) {
        for (int i = 0; i < this.m_listeners.size(); ++i) {
            this.m_listeners.elementAt(i).invalidCharacter(evt);
        }
    }
    
    protected static byte convertToRepeatBarLine(final String barLine) {
        if (barLine.equals("[1")) {
            return 1;
        }
        if (barLine.equals("[2")) {
            return 2;
        }
        if (barLine.equals("|1")) {
            return 1;
        }
        if (barLine.equals(":|2")) {
            return 2;
        }
        return -1;
    }
    
    protected static byte convertBrokenRhythm(final String brokenRhythm) {
        final byte br = (byte)brokenRhythm.length();
        if (brokenRhythm.charAt(0) == '<') {
            return (byte)(-br);
        }
        if (brokenRhythm.charAt(0) == '>') {
            return br;
        }
        return 0;
    }
    
    protected static byte convertToOctaveTransposition(final String octave) {
        if (octave.charAt(0) == '\'') {
            return (byte)octave.length();
        }
        if (octave.charAt(0) == ',') {
            return (byte)(-octave.length());
        }
        return 0;
    }
    
    static {
        FIRST_END_OF_LINE = new Set(AbcTokenType.LINE_FEED).union(AbcTokenType.SPACE).union(AbcTokenType.COMMENT);
        FIRST_PART_SPEC = new Set(AbcTokenType.PART).union(AbcTokenType.PARENTHESIS_OPEN);
        FIRST_PARTS = AbcParserAbstract.FIRST_PART_SPEC;
        FIRST_NOTE_LENGTH_STRICT = new Set(AbcTokenType.NUMBER);
        FIRST_TEMPO = new Set(AbcTokenType.NUMBER).union(AbcTokenType.C_TEMPO).union(AbcParserAbstract.FIRST_NOTE_LENGTH_STRICT);
        FIRST_METER_FRACTION = new Set(AbcTokenType.NUMBER);
        FIRST_METER = new Set(AbcParserAbstract.FIRST_METER_FRACTION).union(AbcTokenType.C_METER);
        FIRST_MODE = new Set(AbcTokenType.MODE);
        FIRST_MODE_SPEC = new Set(AbcParserAbstract.FIRST_MODE);
        FIRST_KEY_ACCIDENTAL = new Set(AbcTokenType.KEY_ACCIDENTAL);
        FIRST_KEYNOTE = new Set(AbcTokenType.BASE_NOTE);
        FIRST_KEYSPEC = new Set(AbcParserAbstract.FIRST_KEYNOTE);
        FIRST_KEY = new Set(AbcParserAbstract.FIRST_KEYSPEC).union(AbcTokenType.KEY_HP);
        FIRST_FIELD_KEY = new Set(AbcTokenType.FIELD_KEY);
        FIRST_FIELD_TRANSCRNOTES = new Set(AbcTokenType.FIELD_TRANSCRNOTES);
        FIRST_FIELD_SOURCE = new Set(AbcTokenType.FIELD_SOURCE);
        FIRST_FIELD_RHYTHM = new Set(AbcTokenType.FIELD_RHYTHM);
        FIRST_FIELD_TEMPO = new Set(AbcTokenType.FIELD_TEMPO);
        FIRST_FIELD_PARTS = new Set(AbcTokenType.FIELD_PARTS);
        FIRST_FIELD_ORIGIN = new Set(AbcTokenType.FIELD_ORIGIN);
        FIRST_FIELD_NOTES = new Set(AbcTokenType.FIELD_NOTES);
        FIRST_FIELD_METER = new Set(AbcTokenType.FIELD_METER);
        FIRST_FIELD_DEFAULT_LENGTH = new Set(AbcTokenType.FIELD_DEFAULT_LENGTH);
        FIRST_FIELD_INFORMATION = new Set(AbcTokenType.FIELD_INFORMATION);
        FIRST_FIELD_HISTORY = new Set(AbcTokenType.FIELD_HISTORY);
        FIRST_FIELD_GROUP = new Set(AbcTokenType.FIELD_GROUP);
        FIRST_FIELD_DISCOGRAPHY = new Set(AbcTokenType.FIELD_DISCOGRAPHY);
        FIRST_FIELD_COMPOSER = new Set(AbcTokenType.FIELD_COMPOSER);
        FIRST_FIELD_BOOK = new Set(AbcTokenType.FIELD_BOOK);
        FIRST_FIELD_AREA = new Set(AbcTokenType.FIELD_AREA);
        AbcParserAbstract.FIRST_COMMENT = new Set(AbcTokenType.COMMENT);
        FIRST_OTHER_FIELDS = new Set(AbcParserAbstract.FIRST_FIELD_AREA).union(AbcParserAbstract.FIRST_FIELD_BOOK).union(AbcParserAbstract.FIRST_FIELD_COMPOSER).union(AbcParserAbstract.FIRST_FIELD_DISCOGRAPHY).union(AbcParserAbstract.FIRST_FIELD_GROUP).union(AbcParserAbstract.FIRST_FIELD_HISTORY).union(AbcParserAbstract.FIRST_FIELD_INFORMATION).union(AbcParserAbstract.FIRST_FIELD_DEFAULT_LENGTH).union(AbcParserAbstract.FIRST_FIELD_METER).union(AbcParserAbstract.FIRST_FIELD_NOTES).union(AbcParserAbstract.FIRST_FIELD_ORIGIN).union(AbcParserAbstract.FIRST_FIELD_PARTS).union(AbcParserAbstract.FIRST_FIELD_RHYTHM).union(AbcParserAbstract.FIRST_FIELD_SOURCE).union(AbcParserAbstract.FIRST_FIELD_TEMPO).union(AbcParserAbstract.FIRST_FIELD_TRANSCRNOTES).union(AbcParserAbstract.FIRST_COMMENT);
        FIRST_FIELD_TITLE = new Set(AbcTokenType.FIELD_TITLE);
        FIRST_FIELD_NUMBER = new Set(AbcTokenType.FIELD_NUMBER);
        AbcParserAbstract.FIRST_ABCHEADER = new Set(AbcParserAbstract.FIRST_FIELD_NUMBER);
        AbcParserAbstract.FIRST_TEXT_CHAR = new Set(AbcTokenType.TEXT);
        AbcParserAbstract.FIRST_TEXT = new Set(AbcParserAbstract.FIRST_TEXT_CHAR);
        AbcParserAbstract.FIRST_LINE_FEED = new Set(AbcTokenType.LINE_FEED);
        AbcParserAbstract.FIRST_NO_LINE_BREAK = new Set(AbcTokenType.NO_LINE_BREAK);
        AbcParserAbstract.FIRST_LINE_BREAK = new Set(AbcTokenType.LINE_BREAK);
        AbcParserAbstract.FIRST_SPACE = new Set(AbcTokenType.SPACE);
        AbcParserAbstract.FIRST_USER_DEFINED = new Set(AbcTokenType.USER_DEFINED);
        AbcParserAbstract.FIRST_FIELD_WORDS = new Set(AbcTokenType.FIELD_WORDS);
        AbcParserAbstract.FIRST_FIELD_PART = new Set(AbcTokenType.FIELD_PARTS);
        AbcParserAbstract.FIRST_TUNE_FIELD = new Set(AbcParserAbstract.FIRST_FIELD_KEY).union(AbcParserAbstract.FIRST_FIELD_DEFAULT_LENGTH).union(AbcParserAbstract.FIRST_FIELD_METER).union(AbcParserAbstract.FIRST_FIELD_PART).union(AbcParserAbstract.FIRST_FIELD_TEMPO).union(AbcParserAbstract.FIRST_FIELD_TITLE).union(AbcParserAbstract.FIRST_FIELD_WORDS);
        AbcParserAbstract.FIRST_MID_TUNE_FIELD = AbcParserAbstract.FIRST_TUNE_FIELD;
        AbcParserAbstract.FIRST_END_SLUR = new Set(AbcTokenType.END_SLUR);
        AbcParserAbstract.FIRST_BEGIN_SLUR = new Set(AbcTokenType.BEGIN_SLUR);
        AbcParserAbstract.FIRST_NTH_REPEAT = new Set(AbcTokenType.NTH_REPEAT);
        AbcParserAbstract.FIRST_BARLINE = new Set(AbcTokenType.BARLINE);
        AbcParserAbstract.FIRST_CHORD_TYPE = new Set(AbcTokenType.CHORD_TYPE);
        AbcParserAbstract.FIRST_GUITAR_CHORD = new Set(AbcTokenType.GUITAR_CHORD);
        AbcParserAbstract.FIRST_GRACE_NOTES = new Set(AbcTokenType.GRACING_BEGIN);
        AbcParserAbstract.FIRST_GRACINGS = new Set(AbcTokenType.GRACING);
        AbcParserAbstract.FIRST_TIE = new Set(AbcTokenType.TIE);
        AbcParserAbstract.FIRST_BROKEN_RHYTHM = new Set(AbcTokenType.BROKEN_RHYTHM);
        AbcParserAbstract.FIRST_REST = new Set(AbcTokenType.REST);
        AbcParserAbstract.FIRST_BASE_NOTE = new Set(AbcTokenType.BASE_NOTE);
        AbcParserAbstract.FIRST_ACCIDENTAL = new Set(AbcTokenType.ACCIDENTAL);
        AbcParserAbstract.FIRST_NOTE_LENGTH = new Set(AbcTokenType.NUMBER).union(AbcTokenType.FRACTION);
        AbcParserAbstract.FIRST_OCTAVE = new Set(AbcTokenType.OCTAVE);
        AbcParserAbstract.FIRST_PITCH = new Set(AbcParserAbstract.FIRST_ACCIDENTAL).union(AbcParserAbstract.FIRST_BASE_NOTE);
        AbcParserAbstract.FIRST_NOTE_OR_REST = new Set(AbcParserAbstract.FIRST_PITCH).union(AbcParserAbstract.FIRST_REST);
        AbcParserAbstract.FIRST_NOTE = new Set(AbcParserAbstract.FIRST_NOTE_OR_REST);
        AbcParserAbstract.FIRST_MULTI_NOTE = new Set(AbcTokenType.MULTI_NOTE_BEGIN);
        AbcParserAbstract.FIRST_NOTE_STEM = new Set(AbcParserAbstract.FIRST_GUITAR_CHORD).union(AbcParserAbstract.FIRST_GRACE_NOTES).union(AbcParserAbstract.FIRST_GRACINGS).union(AbcParserAbstract.FIRST_NOTE).union(AbcParserAbstract.FIRST_MULTI_NOTE);
        AbcParserAbstract.FIRST_NOTE_ELEMENT = new Set(AbcParserAbstract.FIRST_NOTE_STEM);
        AbcParserAbstract.FIRST_TUPLET_SPEC = new Set(AbcTokenType.TUPLET_SPEC);
        AbcParserAbstract.FIRST_TUPLET_ELEMENT = new Set(AbcParserAbstract.FIRST_TUPLET_SPEC);
        AbcParserAbstract.FIRST_LINE_ENDER = new Set(AbcTokenType.COMMENT).union(AbcTokenType.LINE_FEED).union(AbcTokenType.LINE_BREAK).union(AbcTokenType.NO_LINE_BREAK);
        AbcParserAbstract.FIRST_ELEMENT = new Set(AbcParserAbstract.FIRST_NOTE_ELEMENT).union(AbcParserAbstract.FIRST_TUPLET_ELEMENT).union(AbcTokenType.BARLINE).union(AbcTokenType.NTH_REPEAT).union(AbcTokenType.BEGIN_SLUR).union(AbcTokenType.END_SLUR).union(AbcTokenType.SPACE);
        AbcParserAbstract.FIRST_ABCTUNE = new Set(AbcParserAbstract.FIRST_ABCHEADER);
    }
}
