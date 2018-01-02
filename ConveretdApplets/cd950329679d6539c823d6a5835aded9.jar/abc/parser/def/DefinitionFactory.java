// 
// Decompiled by Procyon v0.5.30
// 

package abc.parser.def;

import abc.parser.AbcTokenType;
import scanner.AutomataDefinition;
import scanner.TokenType;
import java.util.Vector;

public class DefinitionFactory
{
    public static Vector m_allPreviouslyCreatedDefinitions;
    
    public static AutomataDefinition getDefinition(final TokenType abcTokenType) {
        final TokenType[] tokenTypes = { abcTokenType };
        AutomataDefinition automataDef = null;
        automataDef = getAlreadyCreatedDefinition(tokenTypes);
        if (automataDef == null) {
            if (abcTokenType == AbcTokenType.FIELD_NUMBER) {
                automataDef = new FieldNumberDefinition();
            }
            else if (abcTokenType == AbcTokenType.FIELD_TITLE) {
                automataDef = new FieldTitleDefinition();
            }
            else if (abcTokenType == AbcTokenType.FIELD_AREA) {
                automataDef = new FieldAreaDefinition();
            }
            else if (abcTokenType == AbcTokenType.FIELD_BOOK) {
                automataDef = new FieldBookDefinition();
            }
            else if (abcTokenType == AbcTokenType.FIELD_COMPOSER) {
                automataDef = new FieldComposerDefinition();
            }
            else if (abcTokenType == AbcTokenType.FIELD_DISCOGRAPHY) {
                automataDef = new FieldDiscographyDefinition();
            }
            else if (abcTokenType == AbcTokenType.FIELD_GROUP) {
                automataDef = new FieldGroupDefinition();
            }
            else if (abcTokenType == AbcTokenType.FIELD_HISTORY) {
                automataDef = new FieldHistoryDefinition();
            }
            else if (abcTokenType == AbcTokenType.FIELD_INFORMATION) {
                automataDef = new FieldInformationDefinition();
            }
            else if (abcTokenType == AbcTokenType.FIELD_DEFAULT_LENGTH) {
                automataDef = new FieldDefaultLengthDefinition();
            }
            else if (abcTokenType == AbcTokenType.FIELD_METER) {
                automataDef = new FieldMeterDefinition();
            }
            else if (abcTokenType == AbcTokenType.FIELD_NOTES) {
                automataDef = new FieldNotesDefinition();
            }
            else if (abcTokenType == AbcTokenType.FIELD_ORIGIN) {
                automataDef = new FieldOriginDefinition();
            }
            else if (abcTokenType == AbcTokenType.FIELD_PARTS) {
                automataDef = new FieldPartsDefinition();
            }
            else if (abcTokenType == AbcTokenType.FIELD_RHYTHM) {
                automataDef = new FieldRhythmDefinition();
            }
            else if (abcTokenType == AbcTokenType.FIELD_SOURCE) {
                automataDef = new FieldSourceDefinition();
            }
            else if (abcTokenType == AbcTokenType.FIELD_TEMPO) {
                automataDef = new FieldTempoDefinition();
            }
            else if (abcTokenType == AbcTokenType.FIELD_TRANSCRNOTES) {
                automataDef = new FieldTranscriptionNotesDefinition();
            }
            else if (abcTokenType == AbcTokenType.FIELD_KEY) {
                automataDef = new FieldKeyDefinition();
            }
            else if (abcTokenType == AbcTokenType.FIELD_WORDS) {
                automataDef = new FieldWordsDefinition();
            }
            else if (abcTokenType == AbcTokenType.KEY_HP) {
                automataDef = new KeyHPDefinition();
            }
            else if (abcTokenType == AbcTokenType.C_METER) {
                automataDef = new MeterCDefinition();
            }
            else if (abcTokenType == AbcTokenType.TEXT) {
                automataDef = new TextDefinition();
            }
            else if (abcTokenType == AbcTokenType.NUMBER) {
                automataDef = new NumberDefinition();
            }
            else if (abcTokenType == AbcTokenType.DIGIT) {
                automataDef = new DigitDefinition();
            }
            else if (abcTokenType == AbcTokenType.FRACTION) {
                automataDef = new FractionDefinition();
            }
            else if (abcTokenType == AbcTokenType.PART) {
                automataDef = new PartDefinition();
            }
            else if (abcTokenType == AbcTokenType.PARENTHESIS_OPEN) {
                automataDef = new ParenthesisOpenDefinition();
            }
            else if (abcTokenType == AbcTokenType.PARENTHESIS_CLOSE) {
                automataDef = new ParenthesisCloseDefinition();
            }
            else if (abcTokenType == AbcTokenType.FRACTION) {
                automataDef = new FractionDefinition();
            }
            else if (abcTokenType == AbcTokenType.SPACE) {
                automataDef = new SpaceDefinition();
            }
            else if (abcTokenType == AbcTokenType.LINE_FEED) {
                automataDef = new LineFeedDefinition();
            }
            else if (abcTokenType == AbcTokenType.LINE_BREAK) {
                automataDef = new LineBreakDefinition();
            }
            else if (abcTokenType == AbcTokenType.NO_LINE_BREAK) {
                automataDef = new NoLineBreakDefinition();
            }
            else if (abcTokenType == AbcTokenType.BASE_NOTE) {
                automataDef = new BaseNoteDefinition();
            }
            else if (abcTokenType == AbcTokenType.KEY_ACCIDENTAL) {
                automataDef = new KeyAccidentalDefinition();
            }
            else if (abcTokenType == AbcTokenType.ACCIDENTAL) {
                automataDef = new AccidentalDefinition();
            }
            else if (abcTokenType == AbcTokenType.MODE) {
                automataDef = new ModeDefinition();
            }
            else if (abcTokenType == AbcTokenType.COMMENT) {
                automataDef = new CommentDefinition();
            }
            else if (abcTokenType == AbcTokenType.GUITAR_CHORD) {
                automataDef = new GuitarChordDefinition();
            }
            else if (abcTokenType == AbcTokenType.GRACING_BEGIN) {
                automataDef = new GracingBeginDefinition();
            }
            else if (abcTokenType == AbcTokenType.GRACING_END) {
                automataDef = new GracingEndDefinition();
            }
            else if (abcTokenType == AbcTokenType.GRACING) {
                automataDef = new GracingDefinition();
            }
            else if (abcTokenType == AbcTokenType.REST) {
                automataDef = new RestDefinition();
            }
            else if (abcTokenType == AbcTokenType.BARLINE) {
                automataDef = new BarlineDefinition();
            }
            else if (abcTokenType == AbcTokenType.NTH_REPEAT) {
                automataDef = new NthRepeatDefinition();
            }
            else if (abcTokenType == AbcTokenType.BEGIN_SLUR) {
                automataDef = new SlurBeginDefinition();
            }
            else if (abcTokenType == AbcTokenType.END_SLUR) {
                automataDef = new SlurEndDefinition();
            }
            else if (abcTokenType == AbcTokenType.USER_DEFINED) {
                automataDef = new UserDefinedDefinition();
            }
            else if (abcTokenType == AbcTokenType.OCTAVE) {
                automataDef = new OctaveDefinition();
            }
            else if (abcTokenType == AbcTokenType.BROKEN_RHYTHM) {
                automataDef = new BrokenRhythmDefinition();
            }
            else if (abcTokenType == AbcTokenType.TIE) {
                automataDef = new TieDefinition();
            }
            else if (abcTokenType == AbcTokenType.TUPLET_SPEC) {
                automataDef = new TupletSpecDefinition();
            }
            else if (abcTokenType == AbcTokenType.MULTI_NOTE_BEGIN) {
                automataDef = new MultiNoteBeginDefinition();
            }
            else if (abcTokenType == AbcTokenType.MULTI_NOTE_END) {
                automataDef = new MultiNoteEndDefinition();
            }
            else if (abcTokenType == AbcTokenType.CHORD_NAME) {
                automataDef = new ChordNameDefinition();
            }
            else if (abcTokenType == AbcTokenType.EQUALS) {
                automataDef = new EqualsDefinition();
            }
            else if (abcTokenType == AbcTokenType.C_TEMPO) {
                automataDef = new TempoCDefinition();
            }
            else {
                if (abcTokenType != AbcTokenType.COMA) {
                    throw new RuntimeException("NO AUTOMATA FOR " + abcTokenType);
                }
                automataDef = new ComaDefinition();
            }
            DefinitionFactory.m_allPreviouslyCreatedDefinitions.add(new DefinitionCache(tokenTypes, automataDef));
        }
        return automataDef;
    }
    
    public static AutomataDefinition getDefinition(final TokenType[] tokenTypes) {
        final AutomataDefinition alreadyCreated = getAlreadyCreatedDefinition(tokenTypes);
        if (alreadyCreated != null) {
            return alreadyCreated;
        }
        AutomataDefinition definition = getDefinition(tokenTypes[0]);
        for (int i = 1; i < tokenTypes.length; ++i) {
            definition = definition.union(getDefinition(tokenTypes[i]));
        }
        DefinitionFactory.m_allPreviouslyCreatedDefinitions.addElement(new DefinitionCache(tokenTypes, definition));
        return definition;
    }
    
    private static AutomataDefinition getAlreadyCreatedDefinition(final TokenType[] tokenTypes) {
        AutomataDefinition alreadyCreatedAutomata = null;
        for (int i = 0; i < DefinitionFactory.m_allPreviouslyCreatedDefinitions.size() && alreadyCreatedAutomata == null; ++i) {
            if (DefinitionFactory.m_allPreviouslyCreatedDefinitions.elementAt(i).isValidFor(tokenTypes)) {
                alreadyCreatedAutomata = DefinitionFactory.m_allPreviouslyCreatedDefinitions.elementAt(i).getAutomataResult();
            }
        }
        return alreadyCreatedAutomata;
    }
    
    private static boolean contains(final TokenType type, final TokenType[] types) {
        boolean contains = false;
        int index = 0;
        while (!contains && index < types.length) {
            if (type.equals(types[index])) {
                contains = true;
            }
            else {
                ++index;
            }
        }
        return contains;
    }
    
    public static String toString(final TokenType[] types) {
        String s = "[";
        for (int i = 0; i < types.length; ++i) {
            s = s.concat("," + types[i].toString());
        }
        s = s.concat("]");
        return s;
    }
    
    static {
        DefinitionFactory.m_allPreviouslyCreatedDefinitions = new Vector();
    }
    
    public static class DefinitionCache
    {
        private TokenType[] m_tokenTypes;
        private AutomataDefinition m_automataDefResult;
        
        public DefinitionCache(final TokenType[] tokenTypes, final AutomataDefinition automataDef) {
            this.m_tokenTypes = null;
            this.m_automataDefResult = null;
            this.m_tokenTypes = tokenTypes;
            this.m_automataDefResult = automataDef;
        }
        
        public boolean isValidFor(final TokenType[] tokenTypes) {
            boolean isValidFor = true;
            if (this.m_tokenTypes.length == tokenTypes.length) {
                for (int i = 0; i < this.m_tokenTypes.length && isValidFor; ++i) {
                    if (!contains(this.m_tokenTypes[i], tokenTypes)) {
                        isValidFor = false;
                    }
                }
            }
            else {
                isValidFor = false;
            }
            return isValidFor;
        }
        
        public AutomataDefinition getAutomataResult() {
            return this.m_automataDefResult;
        }
        
        public TokenType[] getTokenTypes() {
            return this.m_tokenTypes;
        }
    }
}
