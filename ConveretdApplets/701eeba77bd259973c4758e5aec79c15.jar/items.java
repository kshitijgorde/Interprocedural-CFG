// 
// Decompiled by Procyon v0.5.30
// 

class items
{
    public int getID(final char c) {
        int n = 0;
        switch (c) {
            case '£': {
                n = 3;
                break;
            }
            case '$': {
                n = 22;
                break;
            }
            case 'y': {
                n = 10;
                break;
            }
            case 'u': {
                n = 11;
                break;
            }
            case 'i': {
                n = 4;
                break;
            }
            case 'o': {
                n = 5;
                break;
            }
            case 'p': {
                n = 6;
                break;
            }
            case '@': {
                n = 8;
                break;
            }
            case '*': {
                n = 46;
                break;
            }
            case '¬': {
                n = 40;
                break;
            }
            case '/': {
                n = 59;
                break;
            }
            case 'c': {
                n = 41;
                break;
            }
            case 'Y': {
                n = 34;
                break;
            }
            case 'U': {
                n = 35;
                break;
            }
            case 'H': {
                n = 44;
                break;
            }
            case 'J': {
                n = 45;
                break;
            }
            case 'w': {
                n = 36;
                break;
            }
            case 'z': {
                n = 18;
                break;
            }
            case 'x': {
                n = 19;
                break;
            }
            case 'n': {
                n = 28;
                break;
            }
            case 'm': {
                n = 29;
                break;
            }
            case ';': {
                n = 112;
                break;
            }
            case '\u00e0': {
                n = 70;
                break;
            }
            case '\u00e1': {
                n = 71;
                break;
            }
            case '\u00e2': {
                n = 72;
                break;
            }
            case '\u00e3': {
                n = 73;
                break;
            }
            case '\u00e4': {
                n = 74;
                break;
            }
            case '\u00e6': {
                n = 75;
                break;
            }
            case '\u00e7': {
                n = 76;
                break;
            }
            case '\u00e9': {
                n = 77;
                break;
            }
            case '\u00ea': {
                n = 78;
                break;
            }
            case '\u00eb': {
                n = 79;
                break;
            }
            case '\u00ec': {
                n = 80;
                break;
            }
            case '\u00ed': {
                n = 81;
                break;
            }
            case '\u00f4': {
                n = 82;
                break;
            }
            case '\u00f5': {
                n = 83;
                break;
            }
            case '\u00f6': {
                n = 84;
                break;
            }
            case '^': {
                n = 20;
                break;
            }
            case '.': {
                n = 52;
                break;
            }
            case '-': {
                n = 55;
                break;
            }
            case '(': {
                n = 21;
                break;
            }
            case ')': {
                n = 9;
                break;
            }
            case '#': {
                n = 1;
                break;
            }
            case '[': {
                n = 30;
                break;
            }
            case ']': {
                n = 31;
                break;
            }
            case '%': {
                n = 32;
                break;
            }
            case '&': {
                n = 33;
                break;
            }
            case '©': {
                n = 13;
                break;
            }
            case '1': {
                n = 2;
                break;
            }
            case '2': {
                n = 7;
                break;
            }
            case '3': {
                n = 12;
                break;
            }
            case '4': {
                n = 17;
                break;
            }
            case '9': {
                n = 49;
                break;
            }
            case 'g': {
                n = 50;
                break;
            }
            case 'f': {
                n = 53;
                break;
            }
            case 'h': {
                n = 51;
                break;
            }
            case '~': {
                n = 56;
                break;
            }
            case '_': {
                n = 60;
                break;
            }
            case '+': {
                n = 61;
                break;
            }
            case '{': {
                n = 62;
                break;
            }
            case '}': {
                n = 63;
                break;
            }
            case '|': {
                n = 64;
                break;
            }
            case ':': {
                n = 65;
                break;
            }
            case '\"': {
                n = 66;
                break;
            }
            case '<': {
                n = 67;
                break;
            }
            case '>': {
                n = 68;
                break;
            }
            case '?': {
                n = 69;
                break;
            }
            case 'k': {
                n = 42;
                break;
            }
            case 'G': {
                n = 96;
                break;
            }
            case 'W': {
                n = 92;
                break;
            }
            case 'b': {
                n = 87;
                break;
            }
            case 'B': {
                n = 88;
                break;
            }
            case 'D': {
                n = 94;
                break;
            }
            case 'Q': {
                n = 85;
                break;
            }
            case 'T': {
                n = 90;
                break;
            }
            case 'P': {
                n = 100;
                break;
            }
            case 'O': {
                n = 102;
                break;
            }
            case 'S': {
                n = 104;
                break;
            }
            case 'Z': {
                n = 106;
                break;
            }
            case '!': {
                n = 108;
                break;
            }
            default: {
                n = 0;
                break;
            }
        }
        return n;
    }
    
    public String getName(final int n) {
        String s = null;
        switch (n) {
            case 42: {
                s = "A Key";
                break;
            }
            case 50: {
                s = "Gold";
                break;
            }
            case 53: {
                s = "Food";
                break;
            }
            case 51: {
                s = "Extra Health";
                break;
            }
            case 60: {
                s = "Henbane";
                break;
            }
            case 61: {
                s = "Slippery Elm";
                break;
            }
            case 62: {
                s = "Belladonna";
                break;
            }
            case 63: {
                s = "Mandrake";
                break;
            }
            case 64: {
                s = "Poppy";
                break;
            }
            case 65: {
                s = "Psylosybin";
                break;
            }
            case 66: {
                s = "Bones";
                break;
            }
            case 67: {
                s = "Fly Agaric";
                break;
            }
            case 68: {
                s = "Datura";
                break;
            }
            case 69: {
                s = "Rowan";
                break;
            }
            case 96: {
                s = "Ghost";
                break;
            }
            case 92: {
                s = "Devil";
                break;
            }
            case 87: {
                s = "Bat";
                break;
            }
            case 88: {
                s = "Bat";
                break;
            }
            case 94: {
                s = "Demon";
                break;
            }
            case 85: {
                s = "Death";
                break;
            }
            case 90: {
                s = "Trader";
                break;
            }
            case 100: {
                s = "Evil Plant";
                break;
            }
            case 102: {
                s = "Grunt";
                break;
            }
            case 104: {
                s = "Spider";
                break;
            }
            case 106: {
                s = "Nice Zombie";
                break;
            }
            case 108: {
                s = "Wizard";
                break;
            }
            case 70: {
                s = "Scroll: Sorbum Granus";
                break;
            }
            case 71: {
                s = "Scroll: Treorbum Tanis";
                break;
            }
            case 72: {
                s = "Scroll: Fugit Vim";
                break;
            }
            case 73: {
                s = "Scroll: Caldus Divum";
                break;
            }
            case 74: {
                s = "Scroll: Raniam Vim";
                break;
            }
            case 75: {
                s = "Scroll: Beezaltium";
                break;
            }
            case 76: {
                s = "Scroll: Ticum Deum";
                break;
            }
            case 77: {
                s = "Scroll: Popus Lupis";
                break;
            }
            case 78: {
                s = "Scroll: Natum Tan";
                break;
            }
            case 79: {
                s = "Scroll: Frenis Vim";
                break;
            }
            case 80: {
                s = "Scroll: Voidum";
                break;
            }
            case 81: {
                s = "Scroll: Asi Tacil";
                break;
            }
            case 82: {
                s = "Scroll: Fugor Arbum";
                break;
            }
            case 83: {
                s = "Scroll: Ad Argum";
                break;
            }
            case 84: {
                s = "Scroll: Transgildor";
                break;
            }
            case 110: {
                s = "Sorbum Granus";
                break;
            }
            case 111: {
                s = "Treorbum Tanis";
                break;
            }
            case 112: {
                s = "Fugit Vim";
                break;
            }
            case 113: {
                s = "Caldus Divum";
                break;
            }
            case 114: {
                s = "Raniam Vim";
                break;
            }
            case 115: {
                s = "Beezaltium";
                break;
            }
            case 116: {
                s = "Ticum Deum";
                break;
            }
            case 117: {
                s = "Popus Lupis";
                break;
            }
            case 118: {
                s = "Natum Tan";
                break;
            }
            case 119: {
                s = "Frenis Vim";
                break;
            }
            case 120: {
                s = "Voidum";
                break;
            }
            case 121: {
                s = "Asi Tacil";
                break;
            }
            case 122: {
                s = "Fugor Arbum";
                break;
            }
            case 123: {
                s = "Ad Argum";
                break;
            }
            case 124: {
                s = "Transgildor";
                break;
            }
            default: {
                s = "Nothing";
                break;
            }
        }
        return s;
    }
}
