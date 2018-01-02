// 
// Decompiled by Procyon v0.5.30
// 

package scanner;

import abc.parser.AbcTokenType;
import java.util.Vector;

public class State implements Cloneable
{
    private Vector transitions;
    private Transition[] m_transitionsArrayCache;
    private boolean isTokenState;
    private TokenType m_type;
    
    public State(final TokenType type, final boolean isTokenStateValue) {
        this.m_transitionsArrayCache = null;
        this.m_type = type;
        this.isTokenState = isTokenStateValue;
        this.transitions = new Vector();
    }
    
    public TokenType getType() {
        return this.m_type;
    }
    
    public Transition[] getTransitions() {
        if (this.m_transitionsArrayCache == null) {
            final int size = this.transitions.size();
            this.m_transitionsArrayCache = new Transition[size];
            for (int i = 0; i < size; ++i) {
                this.m_transitionsArrayCache[i] = this.transitions.elementAt(i);
            }
        }
        return this.m_transitionsArrayCache;
    }
    
    public Transition getTransitionFor(final char character) {
        Transition matchingTransition = null;
        final int m_transitionsNb = this.transitions.size();
        int transitionIndex = 0;
        while (matchingTransition == null && transitionIndex < m_transitionsNb) {
            if (this.transitions.elementAt(transitionIndex).isPossible(character)) {
                matchingTransition = this.transitions.elementAt(transitionIndex);
            }
            else {
                ++transitionIndex;
            }
        }
        return matchingTransition;
    }
    
    public boolean hasSelfTransitions() {
        for (int i = 0; i < this.transitions.size(); ++i) {
            if (this.transitions.elementAt(i).isSelfTransition()) {
                return true;
            }
        }
        return false;
    }
    
    public int countTransitions() {
        return this.transitions.size();
    }
    
    public void setType(final TokenType type) {
        this.m_type = type;
    }
    
    public void setTokenState(final boolean isToken) {
        this.isTokenState = isToken;
    }
    
    public boolean isTokenState() {
        return this.isTokenState;
    }
    
    public void addTransition(final Transition transition) {
        this.transitions.addElement(transition);
        transition.setSourceState(this);
        this.m_transitionsArrayCache = null;
    }
    
    public boolean removeTransition(final Transition transition) {
        if (this.transitions.removeElement(transition)) {
            transition.setSourceState(null);
            this.m_transitionsArrayCache = null;
            return true;
        }
        return false;
    }
    
    public State union(final State state) {
        if (this.isTokenState() && state.isTokenState()) {
            if (this.getType().equals(AbcTokenType.TEXT)) {
                return state;
            }
            if (state.getType().equals(AbcTokenType.TEXT)) {
                return this;
            }
        }
        if (this.isTokenState() && state.isTokenState()) {
            if (this.hasSelfTransitions() && state.hasSelfTransitions()) {
                throw new RuntimeException("BOTH ARE TOKENS ! : " + this.getType() + " and " + state.getType());
            }
            if (!this.hasSelfTransitions() && !state.hasSelfTransitions()) {
                return state;
            }
        }
        State returnedState = (State)this.clone();
        final boolean hasSelfTransition = this.hasSelfTransitions();
        final Transition[] targetTransitions = state.getTransitions();
        for (int i = 0; i < targetTransitions.length; ++i) {
            Transition currentTargetTransition = targetTransitions[i];
            currentTargetTransition = new Transition(currentTargetTransition.getTargetState(), currentTargetTransition.getChars());
            final Transition[] returnedStateTransitions = returnedState.getTransitions();
            for (int j = 0; j < returnedStateTransitions.length; ++j) {
                final Transition currentTransition = returnedStateTransitions[j];
                if (currentTransition.isSelfTransition() && currentTargetTransition.isSelfTransition()) {
                    currentTransition.add(currentTargetTransition.getChars());
                }
                else if (currentTransition.isSelfTransition()) {
                    returnedState.convertSelfTransition(currentTransition);
                    returnedState = returnedState.union(state);
                }
                else if (currentTargetTransition.isSelfTransition()) {
                    state.convertSelfTransition(currentTargetTransition);
                    returnedState = returnedState.union(state);
                }
                else {
                    final char[] intersection = currentTransition.intersect(currentTargetTransition.getChars());
                    if (intersection.length != 0) {
                        currentTransition.substract(intersection);
                        if (currentTransition.getChars().length == 0) {
                            returnedState.removeTransition(currentTransition);
                        }
                        currentTargetTransition.substract(intersection);
                        final State unionState = currentTransition.getTargetState().union(currentTargetTransition.getTargetState());
                        final Transition newtransition = new Transition(unionState, intersection);
                        returnedState.addTransition(newtransition);
                    }
                }
            }
            if (currentTargetTransition.getChars().length != 0) {
                returnedState.addTransition(currentTargetTransition);
            }
        }
        if (this.isTokenState()) {
            if (state.isTokenState() && hasSelfTransition) {
                returnedState.setType(state.getType());
            }
        }
        else if (state.isTokenState()) {
            returnedState.setTokenState(true);
            returnedState.setType(state.getType());
        }
        else if (this.getType().equals(state.getType())) {
            returnedState.setType(this.getType());
        }
        else {
            returnedState.setType(TokenType.UNKNOWN);
        }
        return returnedState;
    }
    
    private void convertSelfTransition(final Transition selfTransition) {
        if (selfTransition.isSelfTransition() && selfTransition.getSourceState().equals(this)) {
            this.removeTransition(selfTransition);
            final State tempState = (State)this.clone();
            final Transition transition = new Transition(tempState, selfTransition.getChars());
            this.addTransition(transition);
        }
    }
    
    public Object clone() {
        final State state = new State(this.getType(), this.isTokenState());
        for (int size = this.transitions.size(), i = 0; i < size; ++i) {
            final Transition currentTransition = this.transitions.elementAt(i);
            if (currentTransition.isSelfTransition()) {
                final Transition trans = new Transition(state, currentTransition.getChars());
                state.addTransition(trans);
            }
            else {
                final Transition trans = new Transition(currentTransition.getTargetState(), currentTransition.getChars());
                state.addTransition(trans);
            }
        }
        return state;
    }
    
    public String toString() {
        String string2return = null;
        if (this.isTokenState) {
            string2return = "*" + this.m_type + "*";
        }
        else {
            string2return = this.m_type.toString();
        }
        return string2return;
    }
}
