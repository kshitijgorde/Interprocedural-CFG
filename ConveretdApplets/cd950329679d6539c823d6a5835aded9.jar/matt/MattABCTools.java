// 
// Decompiled by Procyon v0.5.30
// 

package matt;

public class MattABCTools
{
    static String removeExtraNotation(final String key) {
        String ret = key.replaceAll(">", "");
        ret = ret.replaceAll("<", "");
        ret = ret.replaceAll("/", "");
        ret = ret.replace("\\", "");
        ret = ret.replace("(", "");
        ret = ret.replace(")", "");
        ret = ret.replace("/", "");
        ret = ret.replace("-", "");
        ret = ret.replace("!", "");
        ret = ret.replace("_", "");
        ret = ret.replaceAll("\\[.\\]", "");
        ret = ret.replaceAll("\\[..\\]", "");
        ret = ret.replaceAll("\\[...\\]", "");
        ret = ret.replaceAll("\\[....\\]", "");
        ret = ret.replaceAll("\\[.....\\]", "");
        ret = ret.replaceAll("\\[.*\\]", "");
        final StringBuffer ret2 = new StringBuffer();
        for (int i = 0; i < ret.length(); ++i) {
            final char cur = ret.charAt(i);
            if ((cur >= 'A' && cur <= 'G') || (cur >= 'a' && cur <= 'g')) {
                ret2.append(cur);
            }
        }
        ret = ret2.toString();
        return ret;
    }
    
    static String removeLongNotes(final String key) {
        final StringBuffer ret = new StringBuffer();
        char lastChar = '*';
        for (int i = 0; i < key.length(); ++i) {
            final char current = key.charAt(i);
            if (current != lastChar) {
                ret.append(current);
                lastChar = current;
            }
        }
        return ret.toString();
    }
    
    public static int skipHeaders(final String tune) {
        int i = 0;
        int inChars = 0;
        for (boolean inHeader = true; i < tune.length() && inHeader; ++i, ++inChars) {
            final char c = tune.charAt(i);
            if (inChars == 1) {
                if (c == ':' && tune.charAt(i - 1) != '|') {
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
        return i;
    }
    
    public static String expandParts(final String notes) {
        final StringBuffer retValue = new StringBuffer(notes);
        int start = 0;
        int end = 0;
        final String endToken = ":|";
        while (true) {
            end = retValue.indexOf(endToken);
            if (end == -1) {
                break;
            }
            final int newStart = retValue.lastIndexOf("|:", end);
            if (newStart != -1) {
                start = newStart + 2;
            }
            if (retValue.length() > end + 2 && Character.isDigit(retValue.charAt(end + 2))) {
                int numSpecialBars = 1;
                final StringBuffer expanded = new StringBuffer();
                int normalPart = retValue.lastIndexOf("|", end);
                if (!Character.isDigit(retValue.charAt(normalPart + 1))) {
                    normalPart = retValue.lastIndexOf("|", normalPart - 1);
                    ++numSpecialBars;
                }
                expanded.append(retValue.substring(start, normalPart));
                expanded.append("|");
                expanded.append(retValue.substring(normalPart + 2, end));
                int secondTime = end;
                while (numSpecialBars-- > 0) {
                    secondTime = retValue.indexOf("|", secondTime + 2);
                }
                expanded.append("|");
                expanded.append(retValue.substring(start, normalPart));
                expanded.append("|");
                expanded.append(retValue.substring(end + 3, secondTime));
                expanded.append("|");
                retValue.replace(start, secondTime, expanded.toString());
            }
            else {
                final StringBuffer expanded2 = new StringBuffer();
                expanded2.append(retValue.substring(start, end));
                expanded2.append("|");
                expanded2.append(retValue.substring(start, end));
                retValue.replace(start, end + 2, expanded2.toString());
                start += expanded2.toString().length();
            }
        }
        return retValue.toString();
    }
    
    public static String stripBarDivisions(final String notes) {
        final StringBuffer retValue = new StringBuffer();
        for (int i = 0; i < notes.length(); ++i) {
            final char c = notes.charAt(i);
            if (c != '|' && c != ':') {
                retValue.append(c);
            }
        }
        return retValue.toString();
    }
    
    public static String removeTripletMarks(final String notes) {
        final StringBuffer retValue = new StringBuffer();
        final boolean inOrnament = false;
        for (int i = 0; i < notes.length(); ++i) {
            final char c = notes.charAt(i);
            if (c == '(' && Character.isDigit(notes.charAt(i + 1))) {
                ++i;
            }
            else {
                retValue.append(c);
            }
        }
        return retValue.toString();
    }
    
    public static String expandLongNotes(final String notes) {
        final StringBuffer retValue = new StringBuffer();
        boolean inOrnament = false;
        for (int i = 0; i < notes.length(); ++i) {
            final char c = notes.charAt(i);
            if (c == '{') {
                inOrnament = true;
            }
            else if (c == '}') {
                inOrnament = false;
            }
            else if (c != '~' && !inOrnament && c != ',' && c != '=' && c != '^' && c != '\'') {
                retValue.append(c);
            }
        }
        for (int i = 1; i < retValue.length(); ++i) {
            final char c = retValue.charAt(i);
            final char p = retValue.charAt(i - 1);
            if (Character.isDigit(c) && Character.isLetter(p)) {
                String expanded = "";
                for (int howMany = c - '0', j = 0; j < howMany; ++j) {
                    expanded += p;
                }
                retValue.replace(i - 1, i + 1, expanded);
            }
        }
        return retValue.toString();
    }
    
    public static String stripNonNotes(String notes) {
        final StringBuffer retValue = new StringBuffer();
        notes = stripComments(notes);
        for (int i = 0; i < notes.length(); ++i) {
            final char c = notes.charAt(i);
            if ((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z') || (c >= '1' && c <= '9') || c == '(') {
                retValue.append(c);
            }
        }
        return retValue.toString();
    }
    
    public static String stripWhiteSpace(final String transcription) {
        final StringBuffer retValue = new StringBuffer();
        for (int i = 0; i < transcription.length(); ++i) {
            if (transcription.charAt(i) != ' ' && transcription.charAt(i) != '\r' && transcription.charAt(i) != '\n') {
                retValue.append(transcription.charAt(i));
            }
        }
        return retValue.toString();
    }
    
    public static String stripComments(final String transcription) {
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
    
    public static String stripAll(String key) {
        key = stripComments(key);
        key = stripWhiteSpace(key);
        key = expandLongNotes(key);
        key = expandParts(key);
        key = stripBarDivisions(key);
        key = removeTripletMarks(key);
        key = removeExtraNotation(key);
        return key.toUpperCase();
    }
    
    public static void main(final String[] args) {
        String test = "";
        test += "A2FA df~f2|dfef dB~B2|A2FA dffe|dBAG FDDB|";
        test += "A2FA df~f2|afef dB~B2|A2FA dffe|dBAG FDD2||";
        test += "|:a2~a2 afdf|afef dB~B2|fbba bafa|bfaf feef|";
        test += "bf~f2 af~f2|afef dB~B2|A2FA dffe|1 dBAF ADD2:|2 dBAF ADDB||";
        test += "A2FA dfef|df (3efe dB~B2|A2FA defe|dBAG FD~D2|";
        test += "A2FA df~f2|afgf efdB|(3ABA FA defe|dBAG FD~D2||";
        test += "|:~a3z afdf|afef dB~B2|fbba babc'|d'c'ba feef|";
        test += "bf~f2 af~f2|afef efdB|(3ABA FA defe|1 dBAF ADD2:|2 dBAF ADD2||";
        test = stripComments(test);
        test = stripWhiteSpace(test);
        test = expandLongNotes(test);
        test = expandParts(test);
        test = stripBarDivisions(test);
        test = removeTripletMarks(test);
        test = test.toUpperCase();
        System.out.println(test);
    }
    
    enum partTypes
    {
        NORMAL, 
        REPEAT, 
        SPECIAL12;
    }
}
