import java.util.StringTokenizer;

// 
// Decompiled by Procyon v0.5.30
// 

class ChordAnalyser
{
    private final String[] noteNamesSharp;
    private final String[] noteNamesFlat;
    private final String[] sharpNoteNames;
    private final String[] flatNoteNames;
    private final String[] addNoteNames;
    private final String[] addFlatNoteNames;
    private ChordList cl;
    private String szBaseNote;
    private String szResult;
    private String[] reverseChordArr;
    private String[] singleNotesArr;
    
    public ChordAnalyser() {
        this.noteNamesSharp = new String[] { "C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B" };
        this.noteNamesFlat = new String[] { "C", "Db", "D", "Eb", "E", "F", "Gb", "G", "Ab", "A", "Bb", "B" };
        this.sharpNoteNames = new String[] { "1", "#1", "2", "#2", "3", "4", "#4", "5", "#5", "6", "#6", "7" };
        this.flatNoteNames = new String[] { "1", "b2", "2", "b3", "3", "4", "b5", "5", "b6", "6", "b7", "7" };
        this.addNoteNames = new String[] { "1", "#1", "9", "#9", "3", "11", "#11", "5", "#5", "13", "#13", "7" };
        this.addFlatNoteNames = new String[] { "1", "b9", "9", "b3", "3", "11", "b5", "5", "b13", "13", "b7", "7" };
        this.cl = new ChordList();
        this.reverseChordArr = new String[1];
    }
    
    public ChordAnalyser(final String s, final boolean b) {
        this.noteNamesSharp = new String[] { "C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B" };
        this.noteNamesFlat = new String[] { "C", "Db", "D", "Eb", "E", "F", "Gb", "G", "Ab", "A", "Bb", "B" };
        this.sharpNoteNames = new String[] { "1", "#1", "2", "#2", "3", "4", "#4", "5", "#5", "6", "#6", "7" };
        this.flatNoteNames = new String[] { "1", "b2", "2", "b3", "3", "4", "b5", "5", "b6", "6", "b7", "7" };
        this.addNoteNames = new String[] { "1", "#1", "9", "#9", "3", "11", "#11", "5", "#5", "13", "#13", "7" };
        this.addFlatNoteNames = new String[] { "1", "b9", "9", "b3", "3", "11", "b5", "5", "b13", "13", "b7", "7" };
        this.cl = new ChordList();
        if (b) {
            this.analyseChordExpression(s);
        }
        else {
            this.analyseChordNotes(s);
        }
        this.reverseChordArr = new String[1];
    }
    
    public ChordList getCList() {
        return this.cl;
    }
    
    public String analyseChordNotes(final String s) {
        int n = 0;
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) != '#' && s.charAt(i) != 'b') {
                ++n;
            }
        }
        final String[] array = new String[n];
        for (int j = 0, n2 = 0; j < s.length(); ++j, ++n2) {
            if (j + 1 < s.length()) {
                if (s.charAt(j + 1) == '#' || s.charAt(j + 1) == 'b') {
                    array[n2] = s.substring(j, j + 2);
                    ++j;
                }
                else {
                    array[n2] = s.substring(j, j + 1);
                }
            }
            else {
                array[n2] = s.substring(j, j + 1);
            }
        }
        this.singleNotesArr = this.removeDoubleNotes(array);
        final int length = this.singleNotesArr.length;
        final String verifyFormula = this.cl.verifyFormula(this.convertNotesToFormula(this.singleNotesArr));
        if (!verifyFormula.equals("No matching chord found.")) {
            return this.szBaseNote + verifyFormula;
        }
        if (length > 0) {
            final boolean b = true;
            int n3 = 0;
            while (b) {
                final String s2 = this.singleNotesArr[0];
                for (int k = 0; k < length - 1; ++k) {
                    this.singleNotesArr[k] = this.singleNotesArr[k + 1];
                }
                this.singleNotesArr[length - 1] = s2;
                final String verifyFormula2 = this.cl.verifyFormula(this.convertNotesToFormula(this.singleNotesArr));
                if (!verifyFormula2.equals("No matching chord found.")) {
                    return this.szBaseNote + verifyFormula2;
                }
                if (n3 == length) {
                    return "No matching chord found.";
                }
                ++n3;
            }
        }
        return "No matching chord found.";
    }
    
    public void findReverseChords(final String s) {
        int n = 0;
        int n2 = 0;
        boolean b = true;
        if (s != "" && s != null && s != "--------") {
            for (int i = 0; i < s.length(); ++i) {
                if (s.charAt(i) == ',') {
                    ++n;
                }
            }
            final String[] array = new String[n];
            final StringTokenizer stringTokenizer = new StringTokenizer(s);
            int n3 = 0;
            while (stringTokenizer.hasMoreTokens()) {
                final String nextToken = stringTokenizer.nextToken();
                array[n3++] = nextToken.substring(0, nextToken.length() - 1);
            }
            final String[] array2 = new String[array.length];
            final String[] array3 = new String[n];
            for (int j = 0; j < n; ++j) {
                array3[j] = "";
            }
            int n4 = 0;
            while (b) {
                final String s2 = array[0];
                for (int k = 0; k < n - 1; ++k) {
                    array[k] = array[k + 1];
                }
                array[n - 1] = s2;
                final String verifyFormula = this.cl.verifyFormula(this.convertNotesToFormula(array));
                if (!verifyFormula.equals("No matching chord found.") && !array3[0].equals(this.szBaseNote + verifyFormula)) {
                    array3[n4++] = this.szBaseNote + verifyFormula;
                    this.reverseChordArr = new String[n4 - 1];
                }
                if (n2 == n) {
                    b = false;
                }
                ++n2;
            }
            for (int l = 0; l < this.reverseChordArr.length; ++l) {
                this.reverseChordArr[l] = array3[l];
            }
        }
    }
    
    public String[] getReverseChords() {
        if (this.reverseChordArr == null) {
            this.reverseChordArr[0] = "No matching chord found.";
        }
        return this.reverseChordArr;
    }
    
    private String[] removeDoubleNotes(String[] array) {
        final String[] array2 = array;
        for (int i = 0; i < array.length; ++i) {
            for (int j = 0; j < array.length; ++j) {
                if (array[i].equals(array2[j]) && i != j) {
                    array2[j] = "§";
                }
            }
        }
        int n = 0;
        for (int k = 0; k < array.length; ++k) {
            if (!array2[k].equals("§")) {
                ++n;
            }
        }
        array = new String[n];
        int l = 0;
        int n2 = 0;
        while (l < array2.length) {
            if (!array2[l].equals("§")) {
                array[n2++] = array2[l];
            }
            ++l;
        }
        return array;
    }
    
    private String[] sortNotes(final String[] array) {
        final int[] array2 = new int[array.length];
        int n = 0;
        for (int i = 0; i < array.length; ++i) {
            for (int j = 0; j < this.noteNamesSharp.length; ++j) {
                if (array[i].equals(this.noteNamesSharp[j])) {
                    array2[n++] = j;
                }
                else if (array[i].equals(this.noteNamesFlat[j])) {
                    array2[n++] = j;
                }
            }
        }
        boolean b;
        do {
            b = true;
            for (int k = 0; k < array2.length - 1; ++k) {
                if (array2[k] > array2[k + 1]) {
                    final int n2 = array2[k];
                    array2[k] = array2[k + 1];
                    array2[k + 1] = n2;
                    b = false;
                }
            }
        } while (!b);
        final String[] array3 = new String[array.length];
        for (int l = 0; l < array2.length; ++l) {
            array3[l] = this.noteNamesSharp[array2[l]];
        }
        return array3;
    }
    
    public String convertNotesToFormula(final String[] array) {
        final String[] array2 = new String[array.length];
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        if (array != null && array.length > 0) {
            for (int i = 0; i < array.length; ++i) {
                for (int j = 0; j < 12; ++j) {
                    if (array[i].equals(this.noteNamesSharp[j])) {
                        if (n3 == 0) {
                            n2 = j;
                            this.szBaseNote = array[i];
                            n3 = 1;
                            array2[n++] = this.sharpNoteNames[0];
                        }
                        else if (n3 != 0) {
                            final int n4 = (j - n2 < 0) ? (j - n2 + 12) : (j - n2);
                            if (this.sharpNoteNames[n4].equals("#2")) {
                                array2[n++] = "b3";
                            }
                            else if (this.sharpNoteNames[n4].equals("#4")) {
                                array2[n++] = "b5";
                            }
                            else if (this.sharpNoteNames[n4].equals("#6")) {
                                array2[n++] = "b7";
                            }
                            else {
                                array2[n++] = this.sharpNoteNames[n4];
                            }
                        }
                    }
                    else if (array[i].equals(this.noteNamesFlat[j])) {
                        if (n3 == 0) {
                            n2 = j;
                            this.szBaseNote = array[i];
                            n3 = 1;
                            array2[n++] = this.flatNoteNames[0];
                        }
                        else if (n3 != 0) {
                            array2[n++] = this.flatNoteNames[(j - n2 < 0) ? (j - n2 + 12) : (j - n2)];
                        }
                    }
                }
            }
        }
        return this.getSortedByHarmonicRules(array2);
    }
    
    private String getSortedByHarmonicRules(final String[] array) {
        String s = "";
        int n = 0;
        boolean b = false;
        int n2 = 12;
        int n3 = 12;
        if (array != null && array.length > 0) {
            for (int i = 0; i < array.length; ++i) {
                if ((array[i].equals("b3") || array[i].equals("3") || array[i].equals("4")) && n == 0) {
                    n2 = i;
                    n = 1;
                }
                if (array[i].equals("b5") || array[i].equals("5") || array[i].equals("#5")) {
                    n3 = i;
                    b = true;
                }
            }
            if (n != 0) {
                for (int j = 0; j < array.length; ++j) {
                    if (array[j].equals("#1") || array[j].equals("b2")) {
                        array[j] = "b9";
                    }
                    if (array[j].equals("2")) {
                        array[j] = "9";
                    }
                    if (array[j].equals("b3") && j != n2) {
                        array[j] = "#9";
                    }
                }
            }
            if (b) {
                for (int k = 0; k < array.length; ++k) {
                    if (array[k].equals("4") && array.length > 4) {
                        array[k] = "11";
                    }
                    if (array[k].equals("b5") && k != n3) {
                        array[k] = "#11";
                    }
                    if (array[k].equals("b6") || (array[k].equals("#5") && k != n3)) {
                        array[k] = "b13";
                    }
                    if (array[k].equals("6") && array.length > 4) {
                        array[k] = "13";
                    }
                }
            }
            final int[] array2 = new int[array.length];
            final short[] array3 = new short[array.length];
            int n4 = 0;
            for (int l = 0; l < array.length; ++l) {
                for (int n5 = 0; n5 < this.noteNamesSharp.length; ++n5) {
                    if (array[l].equals(this.sharpNoteNames[n5])) {
                        array3[n4] = 1000;
                        array2[n4++] = n5;
                    }
                    else if (array[l].equals(this.flatNoteNames[n5])) {
                        array3[n4] = 1100;
                        array2[n4++] = n5;
                    }
                    else if (array[l].equals(this.addNoteNames[n5])) {
                        array3[n4] = 1110;
                        array2[n4++] = n5;
                    }
                    else if (array[l].equals(this.addFlatNoteNames[n5])) {
                        array3[n4] = 1111;
                        array2[n4++] = n5;
                    }
                }
            }
            boolean b2;
            do {
                b2 = true;
                for (int n6 = 0; n6 < array2.length - 1; ++n6) {
                    if (array2[n6] > array2[n6 + 1]) {
                        final int n7 = array2[n6];
                        final short n8 = array3[n6];
                        array2[n6] = array2[n6 + 1];
                        array3[n6] = array3[n6 + 1];
                        array2[n6 + 1] = n7;
                        array3[n6 + 1] = n8;
                        b2 = false;
                    }
                }
            } while (!b2);
            for (int n9 = 0; n9 < array2.length; ++n9) {
                if (array3[n9] == 1000) {
                    s = s + this.sharpNoteNames[array2[n9]] + "\t";
                }
                else if (array3[n9] == 1100) {
                    s = s + this.flatNoteNames[array2[n9]] + "\t";
                }
            }
            for (int n10 = 0; n10 < array2.length; ++n10) {
                if (array3[n10] == 1110) {
                    s = s + this.addNoteNames[array2[n10]] + "\t";
                }
                else if (array3[n10] == 1111) {
                    s = s + this.addFlatNoteNames[array2[n10]] + "\t";
                }
            }
            if (s.equals("1\tb3\tb5\t13\t")) {
                s = "1\tb3\tb5\t6\t";
            }
            if (s.equals("1\t5\t7\t9\t11\t")) {
                s = "1\t4\t5\t7\t9\t";
            }
        }
        return s;
    }
    
    public void analyseChordExpression(final String s) {
        if (!s.equals("")) {
            if (s != null) {
                if (s.length() > 1) {
                    final String substring = s.substring(1, 2);
                    if (substring.equals("b") || substring.equals("#")) {
                        this.szBaseNote = s.substring(0, 2);
                        this.cl.getChord(s.substring(2, s.length()));
                    }
                    else {
                        this.szBaseNote = s.substring(0, 1);
                        this.cl.getChord(s.substring(1, s.length()));
                    }
                }
                else {
                    this.szBaseNote = s.substring(0, 1);
                    this.cl.getChord(s.substring(1, 1));
                }
                this.szBaseNote = this.szBaseNote.substring(0, 1).toUpperCase() + this.szBaseNote.substring(1, this.szBaseNote.length()).toLowerCase();
            }
        }
        this.szResult = "Basenote: " + this.szBaseNote;
    }
    
    public void analyseScaleExpression(final String s) {
        if (!s.equals("")) {
            if (s != null) {
                if (s.length() > 1) {
                    final String substring = s.substring(1, 2);
                    if (substring.equals("b") || substring.equals("#")) {
                        this.szBaseNote = s.substring(0, 2);
                        this.cl.getScale(s.substring(2, s.length()));
                    }
                    else {
                        this.szBaseNote = s.substring(0, 1);
                        this.cl.getScale(s.substring(1, s.length()));
                    }
                }
                else {
                    this.szBaseNote = s.substring(0, 1);
                    this.cl.getScale(s.substring(1, 1));
                }
                this.szBaseNote = this.szBaseNote.substring(0, 1).toUpperCase() + this.szBaseNote.substring(1, this.szBaseNote.length()).toLowerCase();
            }
        }
        this.szResult = "Basenote: " + this.szBaseNote;
    }
    
    public String getSingleNotes() {
        int n = 0;
        boolean b = false;
        final String chordFormula = this.cl.getChordFormula();
        String string = "";
        if (this.szBaseNote != null) {
            for (int i = 0; i < 12; ++i) {
                if (this.szBaseNote.equals(this.noteNamesSharp[i])) {
                    n = i;
                    b = true;
                }
            }
            if (!b) {
                for (int j = 0; j < 12; ++j) {
                    if (this.szBaseNote.equals(this.noteNamesFlat[j])) {
                        n = j;
                    }
                }
            }
        }
        if (n == 0 && !b) {
            return "--------";
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(chordFormula);
        while (stringTokenizer.hasMoreTokens()) {
            string = string + this.getRealNote(n, stringTokenizer.nextToken()) + ", ";
        }
        return this.szResult = string;
    }
    
    public String getScaleNotes() {
        int n = 0;
        boolean b = false;
        final String scaleFormula = this.cl.getScaleFormula();
        String string = "";
        if (this.szBaseNote != null) {
            for (int i = 0; i < 12; ++i) {
                if (this.szBaseNote.equals(this.noteNamesSharp[i])) {
                    n = i;
                    b = true;
                }
            }
            if (!b) {
                for (int j = 0; j < 12; ++j) {
                    if (this.szBaseNote.equals(this.noteNamesFlat[j])) {
                        n = j;
                    }
                }
            }
        }
        if (n == 0 && !b) {
            return "--------";
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(scaleFormula);
        while (stringTokenizer.hasMoreTokens()) {
            string = string + this.getRealNote(n, stringTokenizer.nextToken()) + ", ";
        }
        return this.szResult = string;
    }
    
    private String parseFormulaMask(final int n, final String s, final String[] array) {
        if (s != null) {
            for (int i = 0; i < 12; ++i) {
                if (s.equals(array[i])) {
                    int n2;
                    if (i + n >= 12) {
                        n2 = i + n - 12;
                    }
                    else {
                        n2 = i + n;
                    }
                    return this.noteNamesSharp[n2];
                }
            }
        }
        return "ZERO";
    }
    
    public String toString() {
        return this.szResult;
    }
    
    private String getRealNote(final int n, final String s) {
        if (!this.parseFormulaMask(n, s, this.sharpNoteNames).equals("ZERO")) {
            return this.parseFormulaMask(n, s, this.sharpNoteNames);
        }
        if (!this.parseFormulaMask(n, s, this.flatNoteNames).equals("ZERO")) {
            return this.parseFormulaMask(n, s, this.flatNoteNames);
        }
        if (!this.parseFormulaMask(n, s, this.addNoteNames).equals("ZERO")) {
            return this.parseFormulaMask(n, s, this.addNoteNames);
        }
        if (!this.parseFormulaMask(n, s, this.addFlatNoteNames).equals("ZERO")) {
            return this.parseFormulaMask(n, s, this.addFlatNoteNames);
        }
        return this.szResult = "no matching note found";
    }
    
    public int getNoteIndex(final String s) {
        for (int i = 0; i < this.noteNamesSharp.length; ++i) {
            if (this.noteNamesSharp[i].equals(s)) {
                return i;
            }
        }
        return -1;
    }
    
    public String getNoteFromArr(final int n) {
        if (n >= 0 && n <= 11) {
            return this.noteNamesSharp[n];
        }
        return "";
    }
    
    public String getSharpNoteNames(final int n) {
        if (n >= 0 && n <= 11) {
            return this.sharpNoteNames[n];
        }
        return "";
    }
    
    public String getFlatNoteNames(final int n) {
        if (n >= 0 && n <= 11) {
            return this.flatNoteNames[n];
        }
        return "";
    }
    
    public String getAddNoteNames(final int n) {
        if (n >= 0 && n <= 11) {
            return this.addNoteNames[n];
        }
        return "";
    }
    
    public String getAddFlatNoteNames(final int n) {
        if (n >= 0 && n <= 11) {
            return this.addFlatNoteNames[n];
        }
        return "";
    }
    
    public String getChordSymbol() {
        return this.cl.getChordSymbol();
    }
    
    public String getChordFormula() {
        return this.cl.getChordFormula();
    }
    
    public String getChordName() {
        return this.cl.getChordName();
    }
    
    public String getScaleFormula() {
        return this.cl.getScaleFormula();
    }
    
    public String getScaleName() {
        return this.cl.getScaleName();
    }
}
