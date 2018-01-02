// 
// Decompiled by Procyon v0.5.30
// 

abstract class JavaMiner102V01Hostile extends JavaMiner102V01MoveableObject
{
    public int shotHits() {
        this.dispose();
        return 1;
    }
    
    public int goldHits() {
        return this.shotHits();
    }
    
    public int goldCollected() {
        return 0;
    }
    
    public boolean isDirectionPossible(final int n, final int n2, final int n3, final JavaMiner102V01Level javaMiner102V01Level, final JavaMiner102V01Object[] array) {
        switch (n3) {
            case 1: {
                return n + 1 < 14 && javaMiner102V01Level.isWay(n + 1, n2) && !this.goldBlocks(n + 1, n2, array);
            }
            case 2: {
                return n > 0 && javaMiner102V01Level.isWay(n - 1, n2) && !this.goldBlocks(n - 1, n2, array);
            }
            case 4: {
                return n2 > 0 && javaMiner102V01Level.isWay(n, n2 - 1) && !this.goldBlocks(n, n2 - 1, array);
            }
            case 8: {
                return n2 + 1 < 13 && javaMiner102V01Level.isWay(n, n2 + 1) && !this.goldBlocks(n, n2 + 1, array);
            }
            default: {
                return false;
            }
        }
    }
    
    public int getPossibilities(final int n, final int n2, final JavaMiner102V01Level javaMiner102V01Level, final JavaMiner102V01Object[] array) {
        int n3 = 0;
        if (this.isDirectionPossible(n, n2, 2, javaMiner102V01Level, array)) {
            ++n3;
        }
        if (this.isDirectionPossible(n, n2, 1, javaMiner102V01Level, array)) {
            ++n3;
        }
        if (this.isDirectionPossible(n, n2, 4, javaMiner102V01Level, array)) {
            ++n3;
        }
        if (this.isDirectionPossible(n, n2, 8, javaMiner102V01Level, array)) {
            ++n3;
        }
        return n3;
    }
    
    public int getPossibleDirections(final int n, final int n2, final JavaMiner102V01Level javaMiner102V01Level, final JavaMiner102V01Object[] array) {
        int n3 = 0;
        if (this.isDirectionPossible(n, n2, 1, javaMiner102V01Level, array)) {
            ++n3;
        }
        if (this.isDirectionPossible(n, n2, 2, javaMiner102V01Level, array)) {
            n3 += 2;
        }
        if (this.isDirectionPossible(n, n2, 4, javaMiner102V01Level, array)) {
            n3 += 4;
        }
        if (this.isDirectionPossible(n, n2, 8, javaMiner102V01Level, array)) {
            n3 += 8;
        }
        return n3;
    }
    
    public int getOppositeDirection(final int n) {
        if (n == 1) {
            return 2;
        }
        if (n == 2) {
            return 1;
        }
        if (n == 4) {
            return 8;
        }
        if (n == 8) {
            return 4;
        }
        System.out.println("Fehler in JavaMiner102VXXHostile: ung\u00fcltige Richtung!");
        return -1;
    }
    
    public final boolean stayAtLevelRestart() {
        return false;
    }
    
    public int getObjectType() {
        return 2;
    }
}
