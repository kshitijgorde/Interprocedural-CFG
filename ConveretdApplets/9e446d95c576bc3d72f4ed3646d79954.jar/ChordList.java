import java.util.StringTokenizer;
import java.util.Enumeration;
import java.util.Hashtable;

// 
// Decompiled by Procyon v0.5.30
// 

public class ChordList
{
    String[] chordListArr;
    String[] scaleArr;
    private Hashtable chordHashtable;
    private Hashtable scaleHashtable;
    private String szChordAttrib;
    private String szScaleAttrib;
    private final int CHORDSYMBOL = 1;
    private final int CHORDNAME = 2;
    private final int CHORDFORMULA = 3;
    
    public ChordList() {
        this.chordListArr = new String[] { "major\t major\t 1\t 3\t 5", "5\t     5th\t 1\t 5", "6\t     major 6th\t 1\t 3\t 5\t 6", "6/9\t   major 6th add 9th\t 1\t 3\t 5\t 6\t 9", "7\t     7th\t 1\t 3\t 5\t b7", "7sus\t  7th suspended\t 1\t 4\t 5\t b7", "7+5\t   augmented 7th\t 1\t 3\t #5\t b7", "7-5\t   7th diminished 5th\t 1\t 3\t b5\t b7", "7/6\t   7th add 6th\t 1\t 3\t 5\t 6\t b7", "7-9\t   7th minor 9th\t 1\t 3\t 5\t b7\t b9", "7+9\t   7th augmented 9th\t 1\t 3\t 5\t b7\t #9", "7/13\t  7th add 13th\t 1\t 3\t 5\t b7\t 13", "9\t     9th\t 1\t 3\t 5\t b7\t 9", "9-5\t   9th minor 5th\t 1\t 3\t b5\t b7\t 9", "9+5\t   9th augmented 5th\t 1\t 3\t #5\t b7\t 9", "9/6\t   9th added 6th\t 1\t 3\t 5\t 6\t b7\t 9", "9+11\t  9th augmented 11th\t 1\t 3\t 5\t b7\t 9\t #11", "11\t    11th\t 1\t 3\t 5\t b7\t 9\t 11", "11-9\t  11th minor 9th\t 1\t 3\t 5\t b7\t b9\t 11", "11#7\t  11th sharp 7th\t 1\t 3\t 5\t 7\t 9\t 11", "13\t    13th\t 1\t 3\t 5\t b7\t 9\t 13", "dim\t   diminished\t 1\t b3\t b5", "dim7\t  diminished 7th\t 1\t b3\t b5\t 6", "m\t     minor\t 1\t b3\t 5", "m-5\t   minor 5th\t 1\t b3\t b5", "m6\t    minor 6th\t 1\t b3\t 5\t 6", "m6/9\t  minor 6th add 9th\t 1\t b3\t 5\t 6\t 9", "m7\t    minor 7th\t 1\t b3\t 5\t b7", "m7-5\t  minor 7th diminished 5th\t 1\t b3\t b5\t b7", "m7+5\t  minor 7th augmented 5th\t 1\t b3\t #5\t b7", "m7-9\t  minor 7th minor 9th\t 1\t b3\t 5\t b7\t b9", "m#7\t   minor sharp 7th\t 1\t b3\t 5\t 7", "m#7-5\t minor sharp 7th diminished 5th\t 1\t b3\t b5\t 7", "m#7+5\t minor sharp 7th augmented 5th\t 1\t b3\t #5\t 7", "m#7-9\t minor sharp 7th minor 9th\t 1\t b3\t 5\t 7\t b9", "m9\t    minor 9th\t 1\t b3\t 5\t b7\t 9", "madd9\t minor add 9th\t 1\t b3\t 5\t 9", "m#9\t   minor sharp 9th\t 1\t b3\t 5\t 7\t 9", "m9#7\t  minor 9th sharp 7th\t 1\t b3\t 5\t 7\t 9", "m11\t   minor 11th\t 1\t b3\t 5\t b7\t 9\t 11", "maug11\t  minor augmented 11th\t 1\t b3\t 5\t b7\t 9\t #11", "m13\t     minor 13th\t 1\t b3\t 5\t b7\t 9\t 13", "maj7\t    major 7th\t 1\t 3\t 5\t 7", "maj7-5\t  major 7th diminished 5th\t 1\t 3\t b5\t 7", "maj7+5\t  major 7th augmented 5th\t 1\t 3\t #5\t 7", "maj7sus\t major 7th suspended\t 1\t 4\t 5\t 7", "maj7-9\t  major 7th minor 9th\t 1\t 3\t 5\t 7\t b9", "maj7+9\t  major 7th augmented 9th\t 1\t 3\t 5\t 7\t #9", "maj9\t    major 9th\t 1\t 3\t 5\t 7\t 9", "maj9sus\t major 9h suspended\t 1\t 4\t 5\t 7\t 9", "maj11\t   major 11th\t 1\t 3\t 5\t 7\t 9\t 11", "maj13\t   major 13th\t 1\t 3\t 5\t 7\t 9\t 13", "add9\t    dominant\t 1\t 3\t 5\t 9\t", "sus2\t major suspended 2nd\t 1\t 2\t 5", "sus4\t major suspended 4th\t 1\t 4\t 5", "+\t    augmented (5th)\t 1\t 3\t #5", "+9\t   augmented 9th\t 1\t 3\t 5\t b7\t #9", "+11\t  augmented 11th\t 1\t 3\t 5\t b7\t 9\t #11", "-5\t   major diminished 5th\t 1\t 3\t b5", "-9\t   lowered 9th\t 1\t 3\t 5\t b7\t b9", "-9+5\t lowered 9th augmented 5th\t 1\t 3\t #5\t b7\t b9", "-9-5\t lowered 9th minor 5th\t 1\t 3\t b5\t b7\t b9", "-9+11\t lowered 9th augmented 11th\t 1\t 3\t 5\t b7\t b9\t 11", "-13\t   lowered 13th\t 1\t 3\t 5\t b7\t 9\t b13" };
        this.scaleArr = new String[] { "Major\t Ionian\t 1\t 2\t 3\t 4\t 5\t 6\t 7", "Harmonic Minor\t             \t 1\t 2\t b3\t 4\t 5\t b6\t 7", "Melodic Minor (Ascending)\t  \t 1\t 2\t b3\t 4\t 5\t 6\t 7", "Melodic Minor (Descending)\tNatural Minor, Relative Minor  \t 1\t 2\t b3\t 4\t 5\t b6\t b7", "Pentatonic Major\t           \t 1\t 2\t 3\t 5\t 6", "Pentatonic Minor\t           \t 1\t b3\t 4\t 5\t b7", "Pentatonic Blues\t           \t 1\t b3\t 4\t b5\t 5\t b7", "Pentatonic Neutral\t         \t 1\t 2\t 4\t 5\t b7", "Ionian            \tMajor    \t 1\t 2\t 3\t 4\t 5\t 6\t 7", "Dorian\t                     \t 1\t 2\t b3\t 4\t 5\t 6\t b7", "Phrygian\t                   \t 1\t b2\t b3\t 4\t 5\t b6\t b7", "Lydian\t                     \t 1\t 2\t 3\t #4\t 5\t 6\t 7", "Mixolydian\t                 \t 1\t 2\t 3\t 4\t 5\t 6\t b7", "Aeolian\t                    \t 1\t 2\t b3\t 4\t 5\t b6\t b7", "Locrian\t                    \t 1\t b2\t b3\t 4\t b5\t b6\t b7", "Half Diminished\t            \t 1\t b2\t b3\t 3\t b5\t 5\t 6\t b7", "Whole Diminished\t           \t 1\t 2\t b3\t 4\t b5\t #5\t 6\t 7", "Whole\t                      \t 1\t 2\t 3\t #4\t #5\t #6", "Augmented\t                  \t 1\t #2\t 3\t #4\t #5\t 7", "Chromatic\t                  \t 1\t b2\t 2\t b3\t 3\t 4\t b5\t 5\t #5\t 6\t b7\t 7", "Roumanian Minor\t            \t 1\t 2\t b3\t b5\t 5\t 6\t b7", "Spanish Gypsy\t              \t 1\t b2\t 3\t 4\t 5\t #5\t b7", "Blues\t                      \t 1\t b3\t 4\t b5\t 5\t b7", "Double Harmonic\t            \t 1\t b2\t 3\t 4\t 5\t #5\t 7", "Eight Tone Spanish\t         \t 1\t b2\t b3\t 3\t 4\t b5\t #5\t b7", "Enigmatic\t                  \t 1\t b2\t 3\t b5\t #5\t b7\t 7", "Leading Whole Tone\t         \t 1\t 2\t 3\t b5\t #5\t 6\t b7", "Lydian Augmented\t           \t 1\t 2\t 3\t #4\t #5\t 6\t 7", "Neopolitan Major\t           \t 1\t b2\t b3\t 4\t 5\t 6\t 7", "Neopolitan Minor\t           \t 1\t b2\t b3\t 4\t 5\t b6\t b7", "Pelog\t                      \t 1\t b2\t b3\t b5\t b7\t 7", "Prometheus\t                 \t 1\t 2\t 3\t b5\t 6\t b7", "Prometheus Neopolitan\t      \t 1\t b2\t 3\t b5\t 6\t b7", "Six Tone Symmetrical\t       \t 1\t b2\t 3\t 4\t #5\t 6", "Super Locrian\t              \t 1\t b2\t b3\t 3\t b5\t #5\t b7", "Lydian Minor\t               \t 1\t 2\t 3\t b5\t 5\t b6\t b7", "Lydian Diminished\t          \t 1\t 2\t b3\t b5\t 5\t b6\t b7", "Nine Tone Scale\t            \t 1\t 2\t b3\t 3\t b5\t 5\t #5\t 6\t 7", "Auxiliary Diminished\t       \t 1\t 2\t b3\t 4\t b5\t #5\t 6\t 7", "Auxiliary Augmented\t        \t 1\t 2\t 3\t b5\t #5\t b7", "Auxiliary Diminished Blues\t \t 1\t b2\t b3\t 3\t b5\t 5\t 6\t b7", "Major Locrian\t              \t 1\t 2\t 3\t 4\t b5\t #5\t b7", "Overtone\t                   \t 1\t 2\t 3\t b5\t 5\t 6\t b7", "Diminished Whole Tone\t      \t 1\t b2\t b3\t 3\t b5\t b6\t b7", "Scale      \tDominant 7th    \t 1\t 2\t 4\t 5\t 6\t b7" };
        this.chordHashtable = new Hashtable();
        this.scaleHashtable = new Hashtable();
        this.readChordList();
        this.readScaleList();
    }
    
    public ChordList(final String s) {
        this.chordListArr = new String[] { "major\t major\t 1\t 3\t 5", "5\t     5th\t 1\t 5", "6\t     major 6th\t 1\t 3\t 5\t 6", "6/9\t   major 6th add 9th\t 1\t 3\t 5\t 6\t 9", "7\t     7th\t 1\t 3\t 5\t b7", "7sus\t  7th suspended\t 1\t 4\t 5\t b7", "7+5\t   augmented 7th\t 1\t 3\t #5\t b7", "7-5\t   7th diminished 5th\t 1\t 3\t b5\t b7", "7/6\t   7th add 6th\t 1\t 3\t 5\t 6\t b7", "7-9\t   7th minor 9th\t 1\t 3\t 5\t b7\t b9", "7+9\t   7th augmented 9th\t 1\t 3\t 5\t b7\t #9", "7/13\t  7th add 13th\t 1\t 3\t 5\t b7\t 13", "9\t     9th\t 1\t 3\t 5\t b7\t 9", "9-5\t   9th minor 5th\t 1\t 3\t b5\t b7\t 9", "9+5\t   9th augmented 5th\t 1\t 3\t #5\t b7\t 9", "9/6\t   9th added 6th\t 1\t 3\t 5\t 6\t b7\t 9", "9+11\t  9th augmented 11th\t 1\t 3\t 5\t b7\t 9\t #11", "11\t    11th\t 1\t 3\t 5\t b7\t 9\t 11", "11-9\t  11th minor 9th\t 1\t 3\t 5\t b7\t b9\t 11", "11#7\t  11th sharp 7th\t 1\t 3\t 5\t 7\t 9\t 11", "13\t    13th\t 1\t 3\t 5\t b7\t 9\t 13", "dim\t   diminished\t 1\t b3\t b5", "dim7\t  diminished 7th\t 1\t b3\t b5\t 6", "m\t     minor\t 1\t b3\t 5", "m-5\t   minor 5th\t 1\t b3\t b5", "m6\t    minor 6th\t 1\t b3\t 5\t 6", "m6/9\t  minor 6th add 9th\t 1\t b3\t 5\t 6\t 9", "m7\t    minor 7th\t 1\t b3\t 5\t b7", "m7-5\t  minor 7th diminished 5th\t 1\t b3\t b5\t b7", "m7+5\t  minor 7th augmented 5th\t 1\t b3\t #5\t b7", "m7-9\t  minor 7th minor 9th\t 1\t b3\t 5\t b7\t b9", "m#7\t   minor sharp 7th\t 1\t b3\t 5\t 7", "m#7-5\t minor sharp 7th diminished 5th\t 1\t b3\t b5\t 7", "m#7+5\t minor sharp 7th augmented 5th\t 1\t b3\t #5\t 7", "m#7-9\t minor sharp 7th minor 9th\t 1\t b3\t 5\t 7\t b9", "m9\t    minor 9th\t 1\t b3\t 5\t b7\t 9", "madd9\t minor add 9th\t 1\t b3\t 5\t 9", "m#9\t   minor sharp 9th\t 1\t b3\t 5\t 7\t 9", "m9#7\t  minor 9th sharp 7th\t 1\t b3\t 5\t 7\t 9", "m11\t   minor 11th\t 1\t b3\t 5\t b7\t 9\t 11", "maug11\t  minor augmented 11th\t 1\t b3\t 5\t b7\t 9\t #11", "m13\t     minor 13th\t 1\t b3\t 5\t b7\t 9\t 13", "maj7\t    major 7th\t 1\t 3\t 5\t 7", "maj7-5\t  major 7th diminished 5th\t 1\t 3\t b5\t 7", "maj7+5\t  major 7th augmented 5th\t 1\t 3\t #5\t 7", "maj7sus\t major 7th suspended\t 1\t 4\t 5\t 7", "maj7-9\t  major 7th minor 9th\t 1\t 3\t 5\t 7\t b9", "maj7+9\t  major 7th augmented 9th\t 1\t 3\t 5\t 7\t #9", "maj9\t    major 9th\t 1\t 3\t 5\t 7\t 9", "maj9sus\t major 9h suspended\t 1\t 4\t 5\t 7\t 9", "maj11\t   major 11th\t 1\t 3\t 5\t 7\t 9\t 11", "maj13\t   major 13th\t 1\t 3\t 5\t 7\t 9\t 13", "add9\t    dominant\t 1\t 3\t 5\t 9\t", "sus2\t major suspended 2nd\t 1\t 2\t 5", "sus4\t major suspended 4th\t 1\t 4\t 5", "+\t    augmented (5th)\t 1\t 3\t #5", "+9\t   augmented 9th\t 1\t 3\t 5\t b7\t #9", "+11\t  augmented 11th\t 1\t 3\t 5\t b7\t 9\t #11", "-5\t   major diminished 5th\t 1\t 3\t b5", "-9\t   lowered 9th\t 1\t 3\t 5\t b7\t b9", "-9+5\t lowered 9th augmented 5th\t 1\t 3\t #5\t b7\t b9", "-9-5\t lowered 9th minor 5th\t 1\t 3\t b5\t b7\t b9", "-9+11\t lowered 9th augmented 11th\t 1\t 3\t 5\t b7\t b9\t 11", "-13\t   lowered 13th\t 1\t 3\t 5\t b7\t 9\t b13" };
        this.scaleArr = new String[] { "Major\t Ionian\t 1\t 2\t 3\t 4\t 5\t 6\t 7", "Harmonic Minor\t             \t 1\t 2\t b3\t 4\t 5\t b6\t 7", "Melodic Minor (Ascending)\t  \t 1\t 2\t b3\t 4\t 5\t 6\t 7", "Melodic Minor (Descending)\tNatural Minor, Relative Minor  \t 1\t 2\t b3\t 4\t 5\t b6\t b7", "Pentatonic Major\t           \t 1\t 2\t 3\t 5\t 6", "Pentatonic Minor\t           \t 1\t b3\t 4\t 5\t b7", "Pentatonic Blues\t           \t 1\t b3\t 4\t b5\t 5\t b7", "Pentatonic Neutral\t         \t 1\t 2\t 4\t 5\t b7", "Ionian            \tMajor    \t 1\t 2\t 3\t 4\t 5\t 6\t 7", "Dorian\t                     \t 1\t 2\t b3\t 4\t 5\t 6\t b7", "Phrygian\t                   \t 1\t b2\t b3\t 4\t 5\t b6\t b7", "Lydian\t                     \t 1\t 2\t 3\t #4\t 5\t 6\t 7", "Mixolydian\t                 \t 1\t 2\t 3\t 4\t 5\t 6\t b7", "Aeolian\t                    \t 1\t 2\t b3\t 4\t 5\t b6\t b7", "Locrian\t                    \t 1\t b2\t b3\t 4\t b5\t b6\t b7", "Half Diminished\t            \t 1\t b2\t b3\t 3\t b5\t 5\t 6\t b7", "Whole Diminished\t           \t 1\t 2\t b3\t 4\t b5\t #5\t 6\t 7", "Whole\t                      \t 1\t 2\t 3\t #4\t #5\t #6", "Augmented\t                  \t 1\t #2\t 3\t #4\t #5\t 7", "Chromatic\t                  \t 1\t b2\t 2\t b3\t 3\t 4\t b5\t 5\t #5\t 6\t b7\t 7", "Roumanian Minor\t            \t 1\t 2\t b3\t b5\t 5\t 6\t b7", "Spanish Gypsy\t              \t 1\t b2\t 3\t 4\t 5\t #5\t b7", "Blues\t                      \t 1\t b3\t 4\t b5\t 5\t b7", "Double Harmonic\t            \t 1\t b2\t 3\t 4\t 5\t #5\t 7", "Eight Tone Spanish\t         \t 1\t b2\t b3\t 3\t 4\t b5\t #5\t b7", "Enigmatic\t                  \t 1\t b2\t 3\t b5\t #5\t b7\t 7", "Leading Whole Tone\t         \t 1\t 2\t 3\t b5\t #5\t 6\t b7", "Lydian Augmented\t           \t 1\t 2\t 3\t #4\t #5\t 6\t 7", "Neopolitan Major\t           \t 1\t b2\t b3\t 4\t 5\t 6\t 7", "Neopolitan Minor\t           \t 1\t b2\t b3\t 4\t 5\t b6\t b7", "Pelog\t                      \t 1\t b2\t b3\t b5\t b7\t 7", "Prometheus\t                 \t 1\t 2\t 3\t b5\t 6\t b7", "Prometheus Neopolitan\t      \t 1\t b2\t 3\t b5\t 6\t b7", "Six Tone Symmetrical\t       \t 1\t b2\t 3\t 4\t #5\t 6", "Super Locrian\t              \t 1\t b2\t b3\t 3\t b5\t #5\t b7", "Lydian Minor\t               \t 1\t 2\t 3\t b5\t 5\t b6\t b7", "Lydian Diminished\t          \t 1\t 2\t b3\t b5\t 5\t b6\t b7", "Nine Tone Scale\t            \t 1\t 2\t b3\t 3\t b5\t 5\t #5\t 6\t 7", "Auxiliary Diminished\t       \t 1\t 2\t b3\t 4\t b5\t #5\t 6\t 7", "Auxiliary Augmented\t        \t 1\t 2\t 3\t b5\t #5\t b7", "Auxiliary Diminished Blues\t \t 1\t b2\t b3\t 3\t b5\t 5\t 6\t b7", "Major Locrian\t              \t 1\t 2\t 3\t 4\t b5\t #5\t b7", "Overtone\t                   \t 1\t 2\t 3\t b5\t 5\t 6\t b7", "Diminished Whole Tone\t      \t 1\t b2\t b3\t 3\t b5\t b6\t b7", "Scale      \tDominant 7th    \t 1\t 2\t 4\t 5\t 6\t b7" };
        this.chordHashtable = new Hashtable();
        this.scaleHashtable = new Hashtable();
        this.readChordList();
        this.readScaleList();
        this.getChord(s);
    }
    
    public String verifyFormula(final String s) {
        String string = "";
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) != '\t') {
                string += s.substring(i, i + 1).trim();
            }
        }
        final Enumeration<String> keys = this.chordHashtable.keys();
        while (keys.hasMoreElements()) {
            this.szChordAttrib = (String)this.chordHashtable.get(keys.nextElement());
            final String chordToken = this.getChordToken(3);
            String string2 = "";
            for (int j = 0; j < chordToken.length(); ++j) {
                if (chordToken.charAt(j) != '\t') {
                    string2 += chordToken.substring(j, j + 1).trim();
                }
            }
            if (string.equals(string2)) {
                return this.getChordToken(1);
            }
        }
        return "No matching chord found.";
    }
    
    public String getChordSymbol() {
        if (this.szChordAttrib != null) {
            return this.getChordToken(1);
        }
        return "--------";
    }
    
    public String getChordFormula() {
        if (this.szChordAttrib != null) {
            return this.getChordToken(3);
        }
        return "--------";
    }
    
    public String getChordName() {
        if (this.szChordAttrib != null) {
            return this.getChordToken(2);
        }
        return "--------";
    }
    
    public String getScaleSymbol() {
        if (this.szScaleAttrib != null) {
            return this.getScaleToken(1);
        }
        return "--------";
    }
    
    public String getScaleFormula() {
        if (this.szScaleAttrib != null) {
            return this.getScaleToken(3);
        }
        return "--------";
    }
    
    public String getScaleName() {
        if (this.szScaleAttrib != null) {
            return this.getScaleToken(2);
        }
        return "--------";
    }
    
    private String getScaleToken(final int n) {
        final StringBuffer sb = new StringBuffer(this.szScaleAttrib);
        int n2 = 0;
        final int[] array = new int[14];
        for (int i = 0; i < sb.length(); ++i) {
            if (sb.charAt(i) == '\t') {
                array[n2++] = i;
            }
        }
        int n3 = 0;
        int length = 0;
        switch (n) {
            case 1: {
                n3 = 0;
                length = array[0];
                break;
            }
            case 2: {
                n3 = ++array[0];
                length = array[1];
                break;
            }
            case 3: {
                n3 = array[1];
                length = this.szScaleAttrib.length();
                break;
            }
            default: {
                return "210:getChordToken Error";
            }
        }
        return this.szScaleAttrib.substring(n3, length);
    }
    
    private String getChordToken(final int n) {
        final StringBuffer sb = new StringBuffer(this.szChordAttrib);
        int n2 = 0;
        final int[] array = new int[14];
        for (int i = 0; i < sb.length(); ++i) {
            if (sb.charAt(i) == '\t') {
                array[n2++] = i;
            }
        }
        int n3 = 0;
        int length = 0;
        switch (n) {
            case 1: {
                n3 = 0;
                length = array[0];
                break;
            }
            case 2: {
                n3 = ++array[0];
                length = array[1];
                break;
            }
            case 3: {
                n3 = array[1];
                length = this.szChordAttrib.length();
                break;
            }
            default: {
                return "210:getChordToken Error";
            }
        }
        return this.szChordAttrib.substring(n3, length);
    }
    
    public String getChord(String s) {
        if (s.equals("")) {
            s = "major";
        }
        final Enumeration<String> keys = this.chordHashtable.keys();
        while (keys.hasMoreElements()) {
            final String s2 = keys.nextElement();
            if (s.equals(s2)) {
                return this.szChordAttrib = (String)this.chordHashtable.get(s2);
            }
        }
        return "Unknown chord";
    }
    
    public String getScale(final String s) {
        final Enumeration<String> keys = this.scaleHashtable.keys();
        while (keys.hasMoreElements()) {
            final String s2 = keys.nextElement();
            if (s.trim().equals(s2)) {
                return this.szScaleAttrib = (String)this.scaleHashtable.get(s2);
            }
        }
        return "Unknown chord";
    }
    
    private void readChordList() {
        for (int i = 0; i < this.chordListArr.length; ++i) {
            this.chordHashtable.put(new StringTokenizer(this.chordListArr[i]).nextToken(), this.chordListArr[i]);
        }
    }
    
    private void readScaleList() {
        for (int i = 0; i < this.scaleArr.length; ++i) {
            int n;
            for (n = 0; n < this.scaleArr[i].length() && this.scaleArr[i].charAt(n) != '\t'; ++n) {}
            this.scaleHashtable.put(this.scaleArr[i].substring(0, n).trim(), this.scaleArr[i]);
        }
    }
    
    public String[] getChordSymbolList() {
        final String[] chordListArr = this.chordListArr;
        for (int i = 0; i < this.chordListArr.length; ++i) {
            this.szChordAttrib = this.chordListArr[i];
            chordListArr[i] = this.getChordToken(1);
        }
        return chordListArr;
    }
    
    public String[] getScaleList() {
        final String[] scaleArr = this.scaleArr;
        for (int i = 0; i < this.scaleArr.length; ++i) {
            this.szChordAttrib = this.scaleArr[i];
            scaleArr[i] = this.getChordToken(1);
        }
        return scaleArr;
    }
}
