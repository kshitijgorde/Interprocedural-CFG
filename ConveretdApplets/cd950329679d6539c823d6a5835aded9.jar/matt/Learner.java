// 
// Decompiled by Procyon v0.5.30
// 

package matt;

import java.util.ArrayList;

public class Learner
{
    public static final int ORNAMENTED = 0;
    public static final int UNORNAMENTED = 1;
    int currentFeature;
    private String unOrnamented;
    private String ornamented;
    private ArrayList learned;
    StringBuffer[] notes;
    StringBuffer[] feature;
    int[] charPos;
    int[] notePos;
    int[] length;
    char[] c;
    int artifact;
    private String rhythm;
    private String tuneName;
    private String musician;
    private String key;
    private ArrayList parts;
    
    public Learner() {
        this.currentFeature = 0;
        this.learned = new ArrayList();
        this.notes = new StringBuffer[2];
        this.feature = new StringBuffer[2];
        this.charPos = new int[2];
        this.notePos = new int[2];
        this.length = new int[2];
        this.c = new char[2];
        this.feature[1] = new StringBuffer();
        this.feature[0] = new StringBuffer();
        final StringBuffer[] notes = new StringBuffer[2];
        final StringBuffer[] feature = new StringBuffer[2];
        this.parts = new ArrayList();
    }
    
    public String getUnOrnamented() {
        return this.unOrnamented;
    }
    
    public void setUnOrnamented(final String unOrnamented) {
        this.unOrnamented = unOrnamented;
    }
    
    public String getOrnamented() {
        return this.ornamented;
    }
    
    public void setOrnamented(final String ornamented) {
        this.ornamented = ornamented;
    }
    
    private void clearBuffers() {
        ++this.currentFeature;
        this.feature[1].delete(0, this.feature[1].length());
        this.feature[0].delete(0, this.feature[0].length());
    }
    
    String findPreviousNote(final StringBuffer notes, int pos) {
        char retValue = '\0';
        do {
            retValue = notes.charAt(pos);
            if (retValue == '!') {
                return "z!Breath!";
            }
            if (this.toDigit(retValue) == -1) {
                break;
            }
        } while (--pos > 0);
        return new Character(retValue).toString();
    }
    
    public void learn() {
        new Thread() {
            public void run() {
                Learner.this.learna();
            }
        }.start();
    }
    
    public String learna() {
        int rollLength = 0;
        int noteLength = 0;
        int tripletLength = 0;
        final boolean inBang = false;
        this.notes[0] = new StringBuffer(this.stripWhiteSpace(this.stripComments(this.skipHeaders(this.ornamented))));
        final int iFirstBar = this.notes[0].indexOf("|");
        final String prefix = this.notes[0].substring(0, iFirstBar);
        if (this.noteLength(new StringBuffer(prefix)) != 8) {
            this.artifact = 12;
            this.feature[0] = new StringBuffer(prefix);
            this.createArtifact();
            this.clearBuffers();
            this.notes[0] = new StringBuffer(this.stripBarDivisions(this.stripWhiteSpace(this.notes[0].substring(iFirstBar))));
        }
        else {
            this.notes[0] = new StringBuffer(this.stripBarDivisions(this.stripWhiteSpace(this.notes[0].toString())));
        }
        this.getParts(this.skipHeaders(this.ornamented));
        this.notes[1] = new StringBuffer(this.stripBarDivisions(this.stripWhiteSpace(this.stripComments(this.skipHeaders(this.unOrnamented)))));
        boolean finished = false;
        this.currentFeature = 0;
        this.artifact = 0;
        this.length[1] = this.notes[1].length();
        this.length[0] = this.notes[0].length();
        while (!finished) {
            if (this.charPos[1] == this.length[1]) {
                if (this.artifact == 1 || this.artifact == 0) {
                    this.createArtifact();
                    this.clearBuffers();
                    this.artifact = 11;
                }
            }
            else {
                this.c[1] = this.notes[1].charAt(this.charPos[1]);
            }
            if (this.charPos[0] == this.length[0]) {
                this.createArtifact();
                this.clearBuffers();
                finished = true;
            }
            else {
                this.c[0] = this.notes[0].charAt(this.charPos[0]);
                if (this.artifact == 11) {
                    this.feature[0].append(this.c[0]);
                    final int[] charPos = this.charPos;
                    final int n = 0;
                    ++charPos[n];
                }
                else {
                    if (this.c[0] == '~') {
                        this.createArtifact();
                        this.clearBuffers();
                        rollLength = this.notes[0].charAt(this.charPos[0] + 2) - '0';
                        this.artifact = 2;
                        final int[] notePos = this.notePos;
                        final int n2 = 0;
                        notePos[n2] += rollLength;
                        final int[] notePos2 = this.notePos;
                        final int n3 = 1;
                        notePos2[n3] += rollLength;
                    }
                    if (this.artifact == 2) {
                        if (this.feature[0].length() < 3) {
                            this.feature[0].append(this.c[0]);
                            final int[] charPos2 = this.charPos;
                            final int n4 = 0;
                            ++charPos2[n4];
                        }
                        if (this.noteLength(this.feature[1]) < rollLength) {
                            this.feature[1].append(this.c[1]);
                            final int[] charPos3 = this.charPos;
                            final int n5 = 1;
                            ++charPos3[n5];
                        }
                        else {
                            this.createArtifact();
                            this.clearBuffers();
                            this.artifact = 0;
                        }
                    }
                    else if (this.c[0] == '{') {
                        this.createArtifact();
                        this.clearBuffers();
                        final String prev = this.findPreviousNote(this.notes[0], this.charPos[0] - 1);
                        this.feature[0].append(prev);
                        this.feature[0].append('{');
                        this.feature[1].append(this.notes[1].charAt(this.charPos[1] - 1));
                        this.artifact = 7;
                        final int[] charPos4 = this.charPos;
                        final int n6 = 0;
                        ++charPos4[n6];
                    }
                    else if (this.c[0] == '}') {
                        this.feature[0].append('}');
                        this.feature[0].append(this.notes[0].charAt(this.charPos[0] + 1));
                        this.feature[1].append(this.notes[1].charAt(this.charPos[1]));
                        final int[] notePos3 = this.notePos;
                        final int n7 = 1;
                        ++notePos3[n7];
                        final int[] notePos4 = this.notePos;
                        final int n8 = 0;
                        ++notePos4[n8];
                        final char nxt = this.notes[0].charAt(this.charPos[0] + 1);
                        if (nxt == '=' || nxt == '^') {
                            this.feature[0].append(this.notes[0].charAt(this.charPos[0] + 2));
                            final int[] charPos5 = this.charPos;
                            final int n9 = 0;
                            ++charPos5[n9];
                        }
                        this.createArtifact();
                        this.clearBuffers();
                        final int[] notePos5 = this.notePos;
                        final int n10 = 1;
                        --notePos5[n10];
                        final int[] notePos6 = this.notePos;
                        final int n11 = 0;
                        --notePos6[n11];
                        this.artifact = 0;
                        final int[] charPos6 = this.charPos;
                        final int n12 = 0;
                        ++charPos6[n12];
                        if (this.toDigit(this.c[1]) == -1) {
                            continue;
                        }
                        this.notes[1].replace(this.charPos[1], this.charPos[1] + 1, this.notes[1].substring(this.charPos[1] - 1, this.charPos[1]));
                    }
                    else if (this.artifact == 7) {
                        this.feature[0].append(this.c[0]);
                        final int[] charPos7 = this.charPos;
                        final int n13 = 0;
                        ++charPos7[n13];
                    }
                    else if (this.c[0] == '!') {
                        if (this.artifact != 8) {
                            if (!this.feature[0].toString().endsWith("z")) {
                                this.createArtifact();
                            }
                            else {
                                final int[] notePos7 = this.notePos;
                                final int n14 = 1;
                                --notePos7[n14];
                                final int[] notePos8 = this.notePos;
                                final int n15 = 0;
                                --notePos8[n15];
                            }
                            this.clearBuffers();
                            this.artifact = 8;
                            this.feature[0].append(this.notes[0].charAt(this.charPos[0] - 2));
                            this.feature[0].append(this.notes[0].charAt(this.charPos[0] - 1));
                            this.feature[0].append(this.c[0]);
                            final int[] charPos8 = this.charPos;
                            final int n16 = 0;
                            ++charPos8[n16];
                            final int[] notePos9 = this.notePos;
                            final int n17 = 0;
                            ++notePos9[n17];
                            this.feature[1].append(this.notes[1].charAt(this.charPos[1] - 2));
                            this.feature[1].append(this.notes[1].charAt(this.charPos[1] - 1));
                            final int[] notePos10 = this.notePos;
                            final int n18 = 1;
                            ++notePos10[n18];
                        }
                        else {
                            this.feature[0].append(this.c[0]);
                            final int[] charPos9 = this.charPos;
                            final int n19 = 0;
                            ++charPos9[n19];
                            this.createArtifact();
                            this.artifact = 0;
                        }
                    }
                    else if (this.c[0] == '^' || this.c[0] == '=') {
                        this.createArtifact();
                        this.clearBuffers();
                        this.artifact = 9;
                        this.feature[0].append(this.c[0]);
                        final int[] charPos10 = this.charPos;
                        final int n20 = 0;
                        ++charPos10[n20];
                        this.feature[0].append(this.notes[0].charAt(this.charPos[0]));
                        final int[] charPos11 = this.charPos;
                        final int n21 = 0;
                        ++charPos11[n21];
                        final int[] notePos11 = this.notePos;
                        final int n22 = 0;
                        ++notePos11[n22];
                        this.feature[1].append(this.c[1]);
                        final int[] charPos12 = this.charPos;
                        final int n23 = 1;
                        ++charPos12[n23];
                        final int[] notePos12 = this.notePos;
                        final int n24 = 1;
                        ++notePos12[n24];
                        this.createArtifact();
                        this.artifact = 0;
                    }
                    else if (this.artifact == 8) {
                        this.feature[0].append(this.c[0]);
                        final int[] charPos13 = this.charPos;
                        final int n25 = 0;
                        ++charPos13[n25];
                    }
                    else if (this.c[0] == '(') {
                        this.createArtifact();
                        this.clearBuffers();
                        this.artifact = 10;
                        tripletLength = this.toDigit(this.notes[0].charAt(this.charPos[0] + 1));
                        this.feature[0].append("(" + tripletLength);
                        final int[] charPos14 = this.charPos;
                        final int n26 = 0;
                        charPos14[n26] += 2;
                        this.feature[0].append(this.notes[0].substring(this.charPos[0], this.charPos[0] + tripletLength));
                        this.feature[1].append(this.notes[1].substring(this.charPos[1], this.charPos[1] + tripletLength - 1));
                        final int[] charPos15 = this.charPos;
                        final int n27 = 0;
                        charPos15[n27] += tripletLength;
                        final int[] charPos16 = this.charPos;
                        final int n28 = 1;
                        charPos16[n28] += tripletLength - 1;
                        final int[] notePos13 = this.notePos;
                        final int n29 = 0;
                        notePos13[n29] += tripletLength - 1;
                        final int[] notePos14 = this.notePos;
                        final int n30 = 1;
                        notePos14[n30] += tripletLength - 1;
                        this.createArtifact();
                        this.clearBuffers();
                        this.artifact = 0;
                    }
                    else if (this.c[1] == this.c[0] && this.notePos[1] == this.notePos[0]) {
                        if (this.artifact != 1) {
                            this.createArtifact();
                            this.clearBuffers();
                        }
                        this.artifact = 1;
                        this.feature[1].append(this.c[1]);
                        this.feature[0].append(this.c[0]);
                        final int[] charPos17 = this.charPos;
                        final int n31 = 1;
                        ++charPos17[n31];
                        final int[] charPos18 = this.charPos;
                        final int n32 = 0;
                        ++charPos18[n32];
                        final int[] notePos15 = this.notePos;
                        final int n33 = 1;
                        ++notePos15[n33];
                        final int[] notePos16 = this.notePos;
                        final int n34 = 0;
                        ++notePos16[n34];
                    }
                    else if (this.artifact != 6 && this.artifact != 0) {
                        this.createArtifact();
                        this.artifact = 0;
                    }
                    else {
                        this.artifact = 6;
                        this.feature[0].append(this.c[0]);
                        if (noteLength > 0) {
                            --noteLength;
                        }
                        else {
                            this.feature[1].append(this.c[1]);
                            final int[] notePos17 = this.notePos;
                            final int n35 = 1;
                            ++notePos17[n35];
                            final int[] charPos19 = this.charPos;
                            final int n36 = 1;
                            ++charPos19[n36];
                        }
                        if (this.toDigit(this.c[1]) > 2) {
                            noteLength = this.toDigit(this.c[1]) - 2;
                            final int[] notePos18 = this.notePos;
                            final int n37 = 1;
                            notePos18[n37] += noteLength;
                        }
                        final int[] notePos19 = this.notePos;
                        final int n38 = 0;
                        ++notePos19[n38];
                        final int[] charPos20 = this.charPos;
                        final int n39 = 0;
                        ++charPos20[n39];
                    }
                }
            }
        }
        Logger.log("I learned " + Artifact.nextId + " artifacts");
        return this.learned.toString();
    }
    
    private String skipHeaders(final String tune) {
        int i = 0;
        int inChars = 0;
        for (boolean inHeader = true; i < tune.length() && inHeader; ++i, ++inChars) {
            final char c = tune.charAt(i);
            if (inChars == 1) {
                if (c == ':') {
                    inHeader = true;
                }
                else {
                    inHeader = false;
                    i -= 2;
                }
            }
            if (c == '\r' || c == '\n') {
                inChars = -1;
            }
        }
        return tune.substring(i);
    }
    
    private String stripBarDivisions(final String notes) {
        final StringBuffer retValue = new StringBuffer();
        for (int i = 0; i < notes.length(); ++i) {
            final char c = notes.charAt(i);
            if (c != '|') {
                retValue.append(c);
            }
        }
        return retValue.toString();
    }
    
    private String stripWhiteSpace(final String transcription) {
        final StringBuffer retValue = new StringBuffer();
        for (int i = 0; i < transcription.length(); ++i) {
            if (transcription.charAt(i) != ' ' && transcription.charAt(i) != '\r' && transcription.charAt(i) != '\n') {
                retValue.append(transcription.charAt(i));
            }
        }
        return retValue.toString();
    }
    
    private String stripComments(final String transcription) {
        final StringBuffer retValue = new StringBuffer();
        int i = 0;
        boolean inComment = false;
        while (i < transcription.length()) {
            if (transcription.charAt(i) == '\"') {
                if (inComment) {
                    inComment = false;
                    ++i;
                    continue;
                }
                inComment = true;
            }
            if (!inComment) {
                retValue.append(transcription.charAt(i));
            }
            ++i;
        }
        return retValue.toString();
    }
    
    private int toDigit(final char c) {
        if (c >= '0' && c <= '9') {
            return c - '0';
        }
        return -1;
    }
    
    private int noteLength(final StringBuffer notes) {
        final int stL = notes.length();
        int notL = 0;
        int i = 0;
        boolean inOrnament = false;
        while (i < stL) {
            final char c = notes.charAt(i++);
            if (c == '{' || c == '}') {
                inOrnament = !inOrnament;
            }
            else {
                if (inOrnament) {
                    continue;
                }
                if (c == '~') {
                    continue;
                }
                if (this.toDigit(c) != -1) {
                    notL += this.toDigit(c) - 1;
                }
                else {
                    if (c == '|') {
                        continue;
                    }
                    ++notL;
                }
            }
        }
        return notL;
    }
    
    public String getTuneName() {
        return this.tuneName;
    }
    
    public void setTuneName(final String tuneName) {
        this.tuneName = tuneName;
    }
    
    public String getMusician() {
        return this.musician;
    }
    
    public void setMusician(final String musician) {
        this.musician = musician;
    }
    
    public String getKey() {
        return this.key;
    }
    
    public void setKey(final String key) {
        this.key = key;
    }
    
    public String getRhythm() {
        return this.rhythm;
    }
    
    public void setRhythm(final String rhythm) {
        this.rhythm = rhythm;
    }
    
    void createArtifact() {
        if (this.artifact != 0) {
            if (this.feature[0].length() == 0 && this.feature[1].length() == 0) {
                return;
            }
            int featureLength;
            if (this.feature[1].length() != 0) {
                featureLength = this.noteLength(this.feature[1]);
            }
            else {
                featureLength = this.noteLength(this.feature[0]);
            }
            int realNotePos;
            if (this.artifact == 11) {
                realNotePos = this.notePos[1];
            }
            else {
                realNotePos = this.notePos[1] - featureLength;
            }
            final int bar = realNotePos / 8 % 8;
            final int part = realNotePos / 64;
            String partName;
            if (part >= this.parts.size()) {
                partName = "";
            }
            else {
                partName = this.parts.get(part);
            }
            final Artifact newArtifact = new Artifact(bar, partName, this.artifact, featureLength, realNotePos, this.feature[1].toString(), this.feature[0].toString(), this.key, this.tuneName, this.musician, this.rhythm);
            if (this.artifact != 1) {
                this.learned.add(newArtifact);
                Logger.log(newArtifact.toString());
            }
        }
    }
    
    public void getParts(final String tune) {
        final int length = tune.length();
        int i = 0;
        boolean inPart = false;
        final StringBuffer part = new StringBuffer();
        while (i < length) {
            final char c = tune.charAt(i);
            if (c == '\"') {
                if (inPart) {
                    this.parts.add(part.substring(0, 2));
                    inPart = false;
                    part.delete(0, part.length());
                }
                else {
                    inPart = true;
                }
            }
            else if (inPart) {
                part.append(c);
            }
            ++i;
        }
    }
}
