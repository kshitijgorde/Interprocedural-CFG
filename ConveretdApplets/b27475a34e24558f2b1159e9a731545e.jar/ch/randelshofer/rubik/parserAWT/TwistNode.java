// 
// Decompiled by Procyon v0.5.30
// 

package ch.randelshofer.rubik.parserAWT;

import ch.randelshofer.util.SingletonEnumeration;
import java.util.Enumeration;
import ch.randelshofer.rubik.RubiksCubeCore;

public class TwistNode extends ScriptNode
{
    private int symbol;
    
    public TwistNode() {
        this.symbol = 84;
        this.setAllowsChildren(false);
    }
    
    public TwistNode(final int symbol) {
        this.symbol = symbol;
        this.setAllowsChildren(false);
    }
    
    public TwistNode(final int symbol, final int n, final int n2) {
        super(n, n2);
        this.symbol = symbol;
        this.setAllowsChildren(false);
    }
    
    public int getSymbol() {
        return this.symbol;
    }
    
    public void setSymbol(final int symbol) {
        this.symbol = symbol;
    }
    
    public void applyTo(final RubiksCubeCore rubiksCubeCore) {
        this.applyTo(rubiksCubeCore, false);
    }
    
    public void applyTo(final RubiksCubeCore rubiksCubeCore, final boolean b) {
        ScriptParser.applyTo(rubiksCubeCore, this.symbol, b);
    }
    
    public void applyInverseTo(final RubiksCubeCore rubiksCubeCore) {
        this.applyTo(rubiksCubeCore, true);
    }
    
    public int getFullTurnCount() {
        final int layerMask = ScriptParser.getLayerMask(this.symbol);
        return (layerMask != 0 && layerMask != 7) ? 1 : 0;
    }
    
    public int getQuarterTurnCount() {
        final int layerMask = ScriptParser.getLayerMask(this.symbol);
        final int abs = Math.abs(ScriptParser.getAngle(this.symbol));
        if (layerMask != 0 && layerMask != 7) {
            return (layerMask == 2) ? (abs * 2) : abs;
        }
        return 0;
    }
    
    public void append(final ScriptParser scriptParser, final StringBuffer sb) {
        sb.append(scriptParser.getFirstToken(this.symbol));
    }
    
    public Enumeration resolvedEnumeration(final boolean b) {
        if (b) {
            final TwistNode twistNode = (TwistNode)this.clone();
            twistNode.inverse();
            return new SingletonEnumeration(twistNode);
        }
        return new SingletonEnumeration(this);
    }
    
    public void transform(final int n) {
        this.symbol = ScriptParser.transformSymbol(n, this.symbol);
    }
    
    public void inverse() {
        this.symbol = ScriptParser.inverseSymbol(this.symbol);
    }
    
    public void reflect() {
        this.symbol = ScriptParser.reflectSymbol(this.symbol);
    }
    
    public boolean isRotationSymbol() {
        return ScriptParser.isRotationSymbol(this.symbol);
    }
}
