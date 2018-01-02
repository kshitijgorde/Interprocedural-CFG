// 
// Decompiled by Procyon v0.5.30
// 

package hanzilookup.data;

import java.util.Map;

public class CharacterTypeRepository
{
    public static final int GENERIC_TYPE = 0;
    public static final int SIMPLIFIED_TYPE = 1;
    public static final int TRADITIONAL_TYPE = 2;
    public static final int EQUIVALENT_TYPE = 3;
    public static final int NOT_FOUND = -1;
    private Map typeMap;
    
    public CharacterTypeRepository(final Map typeMap) {
        this.typeMap = typeMap;
    }
    
    public TypeDescriptor lookup(final Character character) {
        final TypeDescriptor descriptor = this.typeMap.get(character);
        return descriptor;
    }
    
    public int getType(final Character character) {
        final TypeDescriptor typeDescriptor = this.lookup(character);
        if (typeDescriptor != null) {
            if (typeDescriptor.type == 0 || typeDescriptor.type == 1 || typeDescriptor.type == 2) {
                return typeDescriptor.type;
            }
            if (typeDescriptor.type == 3) {
                return this.getType(typeDescriptor.altUnicode);
            }
        }
        return -1;
    }
    
    public static class TypeDescriptor
    {
        private int type;
        private Character unicode;
        private Character altUnicode;
        
        public TypeDescriptor(final int type, final Character character, final Character altCharacter) {
            this.type = type;
            this.unicode = character;
            this.altUnicode = altCharacter;
        }
        
        public int getType() {
            return this.type;
        }
        
        public Character getUnicode() {
            return this.unicode;
        }
        
        public Character getAlUnicode() {
            return this.altUnicode;
        }
    }
}
