// 
// Decompiled by Procyon v0.5.30
// 

package scanner;

public class FinaleStateAutomata
{
    private State m_currentState;
    private StringBuffer receivedCharacters;
    private AutomataDefinition m_definition;
    
    public FinaleStateAutomata() {
        this(new AutomataDefinition());
    }
    
    public FinaleStateAutomata(final AutomataDefinition def) {
        this.m_currentState = null;
        this.receivedCharacters = null;
        this.m_definition = null;
        this.setDefinition(def);
    }
    
    public AutomataDefinition getDefinition() {
        return this.m_definition;
    }
    
    public void setDefinition(final AutomataDefinition definition) {
        this.m_definition = definition;
        this.initialize();
    }
    
    public void sendChar(final char character) throws NoTransitionFoundException {
        final Transition matchingTransition = this.getTransitionFor(character);
        if (matchingTransition == null) {
            throw new NoTransitionFoundException();
        }
        this.m_currentState = matchingTransition.getTargetState();
        this.receivedCharacters.append(character);
    }
    
    public Transition getTransitionFor(final char character) {
        return this.m_currentState.getTransitionFor(character);
    }
    
    public State getStartingState() {
        return this.m_definition.getStartingState();
    }
    
    public State getCurrentState() {
        return this.m_currentState;
    }
    
    public String getReceivedCharacters() {
        return new String(this.receivedCharacters);
    }
    
    public void initialize() {
        this.receivedCharacters = new StringBuffer();
        this.m_currentState = this.m_definition.getStartingState();
    }
    
    public String toString() {
        return "FSA : [" + this.getStartingState().toString() + "]";
    }
}
