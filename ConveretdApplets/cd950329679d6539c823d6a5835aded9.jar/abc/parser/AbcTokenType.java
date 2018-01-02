// 
// Decompiled by Procyon v0.5.30
// 

package abc.parser;

import scanner.TokenType;

public class AbcTokenType implements TokenType
{
    public static AbcTokenType ALPHA;
    public static AbcTokenType FIELD_AREA;
    public static AbcTokenType FIELD_BOOK;
    public static AbcTokenType FIELD_COMPOSER;
    public static AbcTokenType FIELD_DISCOGRAPHY;
    public static AbcTokenType FIELD_GROUP;
    public static AbcTokenType FIELD_HISTORY;
    public static AbcTokenType FIELD_INFORMATION;
    public static AbcTokenType FIELD_DEFAULT_LENGTH;
    public static AbcTokenType FIELD_KEY;
    public static AbcTokenType FIELD_METER;
    public static AbcTokenType FIELD_NOTES;
    public static AbcTokenType FIELD_ORIGIN;
    public static AbcTokenType FIELD_PARTS;
    public static AbcTokenType FIELD_TEMPO;
    public static AbcTokenType FIELD_RHYTHM;
    public static AbcTokenType FIELD_SOURCE;
    public static AbcTokenType FIELD_TITLE;
    public static AbcTokenType FIELD_NUMBER;
    public static AbcTokenType FIELD_TRANSCRNOTES;
    public static AbcTokenType FIELD_WORDS;
    public static AbcTokenType TEXT;
    public static AbcTokenType CHORD_NAME;
    public static AbcTokenType COMMENT;
    public static AbcTokenType NUMBER;
    public static AbcTokenType DIGIT;
    public static AbcTokenType FRACTION;
    public static AbcTokenType PARENTHESIS_OPEN;
    public static AbcTokenType PARENTHESIS_CLOSE;
    public static AbcTokenType LINE_FEED;
    public static AbcTokenType LINE_BREAK;
    public static AbcTokenType NO_LINE_BREAK;
    public static AbcTokenType MODE;
    public static AbcTokenType KEY_HP;
    public static AbcTokenType KEY_ACCIDENTAL;
    public static AbcTokenType C_METER;
    public static AbcTokenType EQUALS;
    public static AbcTokenType C_TEMPO;
    public static AbcTokenType BASE_NOTE;
    public static AbcTokenType PART;
    public static AbcTokenType ACCIDENTAL;
    public static AbcTokenType REST;
    public static AbcTokenType BARLINE;
    public static AbcTokenType REPEAT_OPEN;
    public static AbcTokenType REPEAT_CLOSE;
    public static AbcTokenType SPACE;
    public static AbcTokenType GUITAR_CHORD;
    public static AbcTokenType BEGIN_SLUR;
    public static AbcTokenType END_SLUR;
    public static AbcTokenType GRACING_BEGIN;
    public static AbcTokenType GRACING_END;
    public static AbcTokenType GRACING;
    public static AbcTokenType OCTAVE;
    public static AbcTokenType TIE;
    public static AbcTokenType BROKEN_RHYTHM;
    public static AbcTokenType MULTI_NOTE_BEGIN;
    public static AbcTokenType MULTI_NOTE_END;
    public static AbcTokenType TUPLET_SPEC;
    public static AbcTokenType USER_DEFINED;
    public static AbcTokenType NTH_REPEAT;
    public static AbcTokenType CHORD_TYPE;
    public static AbcTokenType COMA;
    private String m_name;
    
    public AbcTokenType(final String typeName) {
        this.m_name = null;
        this.m_name = typeName;
    }
    
    public boolean isField() {
        return this.equals(AbcTokenType.FIELD_NUMBER) || this.equals(AbcTokenType.FIELD_BOOK) || this.equals(AbcTokenType.FIELD_COMPOSER) || this.equals(AbcTokenType.FIELD_DISCOGRAPHY) || this.equals(AbcTokenType.FIELD_GROUP) || this.equals(AbcTokenType.FIELD_METER) || this.equals(AbcTokenType.FIELD_ORIGIN) || this.equals(AbcTokenType.FIELD_RHYTHM) || this.equals(AbcTokenType.FIELD_DEFAULT_LENGTH) || this.equals(AbcTokenType.FIELD_SOURCE) || this.equals(AbcTokenType.FIELD_TITLE) || this.equals(AbcTokenType.FIELD_NOTES) || this.equals(AbcTokenType.FIELD_TEMPO) || this.equals(AbcTokenType.FIELD_TRANSCRNOTES) || this.equals(AbcTokenType.FIELD_KEY);
    }
    
    public String toString() {
        return this.m_name;
    }
    
    static {
        AbcTokenType.ALPHA = new AbcTokenType("ALPHA");
        AbcTokenType.FIELD_AREA = new AbcTokenType("FIELD AREA");
        AbcTokenType.FIELD_BOOK = new AbcTokenType("FIELD BOOK");
        AbcTokenType.FIELD_COMPOSER = new AbcTokenType("FIELD COMPOSER");
        AbcTokenType.FIELD_DISCOGRAPHY = new AbcTokenType("FIELD DISCOGRAPHY");
        AbcTokenType.FIELD_GROUP = new AbcTokenType("FIELD GROUP");
        AbcTokenType.FIELD_HISTORY = new AbcTokenType("FIELD HISTORY");
        AbcTokenType.FIELD_INFORMATION = new AbcTokenType("FIELD INFORMATION");
        AbcTokenType.FIELD_DEFAULT_LENGTH = new AbcTokenType("FIELD DEFAULT LENGTH");
        AbcTokenType.FIELD_KEY = new AbcTokenType("FIELD KEY");
        AbcTokenType.FIELD_METER = new AbcTokenType("FIELD METER");
        AbcTokenType.FIELD_NOTES = new AbcTokenType("FIELD NOTES");
        AbcTokenType.FIELD_ORIGIN = new AbcTokenType("FIELD ORIGIN");
        AbcTokenType.FIELD_PARTS = new AbcTokenType("FIELD PARTS");
        AbcTokenType.FIELD_TEMPO = new AbcTokenType("FIELD TEMPO");
        AbcTokenType.FIELD_RHYTHM = new AbcTokenType("FIELD RHYTHM");
        AbcTokenType.FIELD_SOURCE = new AbcTokenType("FIELD SOURCE");
        AbcTokenType.FIELD_TITLE = new AbcTokenType("FIELD TITLE");
        AbcTokenType.FIELD_NUMBER = new AbcTokenType("FIELD NUMBER");
        AbcTokenType.FIELD_TRANSCRNOTES = new AbcTokenType("FIELD TRANSCRNOTES");
        AbcTokenType.FIELD_WORDS = new AbcTokenType("FIELD WORDS");
        AbcTokenType.TEXT = new AbcTokenType("TEXT");
        AbcTokenType.CHORD_NAME = new AbcTokenType("CHORD NAME");
        AbcTokenType.COMMENT = new AbcTokenType("COMMENT");
        AbcTokenType.NUMBER = new AbcTokenType("NUMBER");
        AbcTokenType.DIGIT = new AbcTokenType("DIGIT");
        AbcTokenType.FRACTION = new AbcTokenType("FRACTION");
        AbcTokenType.PARENTHESIS_OPEN = new AbcTokenType("PARENTHESIS OPEN");
        AbcTokenType.PARENTHESIS_CLOSE = new AbcTokenType("PARENTHESIS CLOSE");
        AbcTokenType.LINE_FEED = new AbcTokenType("LINE FEED");
        AbcTokenType.LINE_BREAK = new AbcTokenType("LINE BREAK");
        AbcTokenType.NO_LINE_BREAK = new AbcTokenType("NO LINE BREAK");
        AbcTokenType.MODE = new AbcTokenType("MODE");
        AbcTokenType.KEY_HP = new AbcTokenType("KEY HP");
        AbcTokenType.KEY_ACCIDENTAL = new AbcTokenType("KEY ACCIDENTAL");
        AbcTokenType.C_METER = new AbcTokenType("C METER");
        AbcTokenType.EQUALS = new AbcTokenType("EQUALS");
        AbcTokenType.C_TEMPO = new AbcTokenType("C TEMPO");
        AbcTokenType.BASE_NOTE = new AbcTokenType("BASE NOTE");
        AbcTokenType.PART = new AbcTokenType("PART");
        AbcTokenType.ACCIDENTAL = new AbcTokenType("ACCIDENTAL");
        AbcTokenType.REST = new AbcTokenType("REST");
        AbcTokenType.BARLINE = new AbcTokenType("BARLINE");
        AbcTokenType.REPEAT_OPEN = new AbcTokenType("REPEAT OPEN");
        AbcTokenType.REPEAT_CLOSE = new AbcTokenType("REPEAT CLOSE");
        AbcTokenType.SPACE = new AbcTokenType("SPACE");
        AbcTokenType.GUITAR_CHORD = new AbcTokenType("GUITAR CHORD");
        AbcTokenType.BEGIN_SLUR = new AbcTokenType("BEGIN SLUR");
        AbcTokenType.END_SLUR = new AbcTokenType("END SLUR");
        AbcTokenType.GRACING_BEGIN = new AbcTokenType("GRACE BEGIN");
        AbcTokenType.GRACING_END = new AbcTokenType("GRACE END");
        AbcTokenType.GRACING = new AbcTokenType("GRACING");
        AbcTokenType.OCTAVE = new AbcTokenType("OCTAVE");
        AbcTokenType.TIE = new AbcTokenType("TIE");
        AbcTokenType.BROKEN_RHYTHM = new AbcTokenType("BROKEN RHYTHM");
        AbcTokenType.MULTI_NOTE_BEGIN = new AbcTokenType("MULTI NOTE BEGIN");
        AbcTokenType.MULTI_NOTE_END = new AbcTokenType("MULTI NOTE END");
        AbcTokenType.TUPLET_SPEC = new AbcTokenType("TUPLET SPEC");
        AbcTokenType.USER_DEFINED = new AbcTokenType("USER DEFINED");
        AbcTokenType.NTH_REPEAT = new AbcTokenType("NTH REPEAT");
        AbcTokenType.CHORD_TYPE = new AbcTokenType("CHORD TYPE");
        AbcTokenType.COMA = new AbcTokenType("COMA");
    }
}
