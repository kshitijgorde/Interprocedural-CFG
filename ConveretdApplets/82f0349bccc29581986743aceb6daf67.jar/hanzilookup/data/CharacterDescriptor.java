// 
// Decompiled by Procyon v0.5.30
// 

package hanzilookup.data;

public class CharacterDescriptor
{
    public static final int MAX_CHARACTER_STROKE_COUNT = 48;
    public static final int MAX_CHARACTER_SUB_STROKE_COUNT = 64;
    private Character character;
    private int characterType;
    private int strokeCount;
    private int subStrokeCount;
    private double[] directions;
    private double[] lengths;
    
    public CharacterDescriptor() {
        this.directions = new double[64];
        this.lengths = new double[64];
    }
    
    public Character getCharacter() {
        return this.character;
    }
    
    public void setCharacter(final Character character) {
        this.character = character;
    }
    
    public int getCharacterType() {
        return this.characterType;
    }
    
    public void setCharacterType(final int characterType) {
        this.characterType = characterType;
    }
    
    public int getStrokeCount() {
        return this.strokeCount;
    }
    
    public void setStrokeCount(final int strokeCount) {
        this.strokeCount = strokeCount;
    }
    
    public int getSubStrokeCount() {
        return this.subStrokeCount;
    }
    
    public void setSubStrokeCount(final int subStrokeCount) {
        this.subStrokeCount = subStrokeCount;
    }
    
    public double[] getDirections() {
        return this.directions;
    }
    
    public double[] getLengths() {
        return this.lengths;
    }
}
