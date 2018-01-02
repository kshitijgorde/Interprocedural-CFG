// 
// Decompiled by Procyon v0.5.30
// 

package scanner;

public class AutomataDefinition
{
    private State m_startingState;
    
    public AutomataDefinition() {
        this.m_startingState = null;
        this.m_startingState = new State(TokenType.UNKNOWN, false);
    }
    
    public AutomataDefinition(final State startingState) {
        this.m_startingState = null;
        this.m_startingState = startingState;
    }
    
    public State getStartingState() {
        return this.m_startingState;
    }
    
    public AutomataDefinition union(final AutomataDefinition def) {
        return new AutomataDefinition(this.m_startingState.union(def.getStartingState()));
    }
    
    public void copyFrom(final AutomataDefinition definition) {
        this.m_startingState = definition.getStartingState();
    }
    
    public String toString() {
        return "FSA : [" + this.m_startingState.toString() + "]";
    }
}
