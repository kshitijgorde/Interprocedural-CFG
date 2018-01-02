// 
// Decompiled by Procyon v0.5.30
// 

package org.joni;

import org.joni.constants.TokenType;

final class Token
{
    TokenType type;
    boolean escaped;
    int base;
    int backP;
    private int INT1;
    private int INT2;
    private int INT3;
    private int INT4;
    private int INT5;
    private int[] INTA1;
    
    int getC() {
        return this.INT1;
    }
    
    void setC(final int c) {
        this.INT1 = c;
    }
    
    int getCode() {
        return this.INT1;
    }
    
    void setCode(final int code) {
        this.INT1 = code;
    }
    
    int getAnchor() {
        return this.INT1;
    }
    
    void setAnchor(final int anchor) {
        this.INT1 = anchor;
    }
    
    int getSubtype() {
        return this.INT1;
    }
    
    void setSubtype(final int subtype) {
        this.INT1 = subtype;
    }
    
    int getRepeatLower() {
        return this.INT1;
    }
    
    void setRepeatLower(final int lower) {
        this.INT1 = lower;
    }
    
    int getRepeatUpper() {
        return this.INT2;
    }
    
    void setRepeatUpper(final int upper) {
        this.INT2 = upper;
    }
    
    boolean getRepeatGreedy() {
        return this.INT3 != 0;
    }
    
    void setRepeatGreedy(final boolean greedy) {
        this.INT3 = (greedy ? 1 : 0);
    }
    
    boolean getRepeatPossessive() {
        return this.INT4 != 0;
    }
    
    void setRepeatPossessive(final boolean possessive) {
        this.INT4 = (possessive ? 1 : 0);
    }
    
    int getBackrefNum() {
        return this.INT1;
    }
    
    void setBackrefNum(final int num) {
        this.INT1 = num;
    }
    
    int getBackrefRef1() {
        return this.INT2;
    }
    
    void setBackrefRef1(final int ref1) {
        this.INT2 = ref1;
    }
    
    int[] getBackrefRefs() {
        return this.INTA1;
    }
    
    void setBackrefRefs(final int[] refs) {
        this.INTA1 = refs;
    }
    
    boolean getBackrefByName() {
        return this.INT3 != 0;
    }
    
    void setBackrefByName(final boolean byName) {
        this.INT3 = (byName ? 1 : 0);
    }
    
    boolean getBackrefExistLevel() {
        return this.INT4 != 0;
    }
    
    void setBackrefExistLevel(final boolean existLevel) {
        this.INT4 = (existLevel ? 1 : 0);
    }
    
    int getBackrefLevel() {
        return this.INT5;
    }
    
    void setBackrefLevel(final int level) {
        this.INT5 = level;
    }
    
    int getCallNameP() {
        return this.INT1;
    }
    
    void setCallNameP(final int nameP) {
        this.INT1 = nameP;
    }
    
    int getCallNameEnd() {
        return this.INT2;
    }
    
    void setCallNameEnd(final int nameEnd) {
        this.INT2 = nameEnd;
    }
    
    int getCallGNum() {
        return this.INT3;
    }
    
    void setCallGNum(final int gnum) {
        this.INT3 = gnum;
    }
    
    int getPropCType() {
        return this.INT1;
    }
    
    void setPropCType(final int ctype) {
        this.INT1 = ctype;
    }
    
    boolean getPropNot() {
        return this.INT2 != 0;
    }
    
    void setPropNot(final boolean not) {
        this.INT2 = (not ? 1 : 0);
    }
}
