// 
// Decompiled by Procyon v0.5.30
// 

package hanzilookup.data;

import java.util.regex.Matcher;
import java.io.IOException;
import java.util.HashMap;
import java.io.InputStream;
import java.util.regex.Pattern;
import java.util.Map;
import kiang.io.LineParser;

public class CharacterTypeParser extends LineParser
{
    private Map typeMap;
    private Pattern linePattern;
    
    public CharacterTypeParser(final InputStream typeStreamIn) throws IOException {
        this.typeMap = new HashMap();
        this.linePattern = Pattern.compile("^([a-f0-9]{4})\\s*\\|\\s*(\\d)(\\s*\\|\\s*([a-f0-9]{4}))?\\s*$");
        try {
            this.parse(typeStreamIn);
            typeStreamIn.close();
        }
        catch (IOException ioe) {
            final IOException thrownIOE = new IOException("Error reading character type data!");
            thrownIOE.initCause(ioe);
            throw thrownIOE;
        }
    }
    
    public CharacterTypeRepository buildCharacterTypeRepository() {
        final CharacterTypeRepository typeRepository = new CharacterTypeRepository(this.typeMap);
        return typeRepository;
    }
    
    protected boolean parseLine(final int lineNum, final String line) {
        boolean parseSuccessful = false;
        final Matcher lineMatcher = this.linePattern.matcher(line);
        if (lineMatcher.matches()) {
            final String unicodeString = lineMatcher.group(1);
            final String typeString = lineMatcher.group(2);
            final Character unicode = new Character((char)Integer.parseInt(unicodeString, 16));
            final int type = Integer.parseInt(typeString);
            Character alternateUnicode = null;
            if (type == 0) {
                parseSuccessful = true;
            }
            else if (1 == type || 2 == type || 3 == type) {
                final String altUnicodeString = lineMatcher.group(4);
                if (altUnicodeString != null) {
                    alternateUnicode = new Character((char)Integer.parseInt(altUnicodeString, 16));
                    parseSuccessful = true;
                }
            }
            if (parseSuccessful) {
                final CharacterTypeRepository.TypeDescriptor charType = new CharacterTypeRepository.TypeDescriptor(type, unicode, alternateUnicode);
                this.typeMap.put(unicode, charType);
                return true;
            }
        }
        return false;
    }
}
