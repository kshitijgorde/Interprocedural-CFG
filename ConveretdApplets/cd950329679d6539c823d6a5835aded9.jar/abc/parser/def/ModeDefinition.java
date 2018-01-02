// 
// Decompiled by Procyon v0.5.30
// 

package abc.parser.def;

import scanner.TokenType;
import scanner.Transition;
import scanner.State;
import abc.parser.AbcTokenType;
import scanner.AutomataDefinition;

public class ModeDefinition extends AutomataDefinition
{
    public ModeDefinition() {
        this.copyFrom(new ModeMinorDefinition().union(new ModeMajorDefinition()).union(new ModeLydianDefinition()).union(new ModeIonianDefinition()).union(new ModeMixolydianDefinition()).union(new ModeDorianDefinition()).union(new ModeAeolianDefinition()).union(new ModePhrygianDefinition()).union(new ModeLocrianDefinition()));
    }
    
    private class ModeAeolianDefinition extends AutomataDefinition
    {
        public ModeAeolianDefinition() {
            final char[] chars = { 'a', 'A' };
            final State state = new State(AbcTokenType.UNKNOWN, false);
            final Transition trans = new Transition(state, chars);
            this.getStartingState().addTransition(trans);
            final State state2 = new State(AbcTokenType.UNKNOWN, false);
            final char[] chars2 = { 'e', 'E' };
            state.addTransition(new Transition(state2, chars2));
            final State state3 = new State(AbcTokenType.MODE, true);
            final char[] chars3 = { 'o', 'O' };
            state2.addTransition(new Transition(state3, chars3));
        }
    }
    
    private class ModeDorianDefinition extends AutomataDefinition
    {
        public ModeDorianDefinition() {
            final char[] chars = { 'd', 'D' };
            final State state = new State(AbcTokenType.UNKNOWN, false);
            final Transition trans = new Transition(state, chars);
            this.getStartingState().addTransition(trans);
            final State state2 = new State(AbcTokenType.UNKNOWN, false);
            final char[] chars2 = { 'o', 'O' };
            state.addTransition(new Transition(state2, chars2));
            final State state3 = new State(AbcTokenType.MODE, true);
            final char[] chars3 = { 'r', 'R' };
            state2.addTransition(new Transition(state3, chars3));
        }
    }
    
    private class ModeIonianDefinition extends AutomataDefinition
    {
        public ModeIonianDefinition() {
            final char[] chars = { 'i', 'I' };
            final State state = new State(AbcTokenType.UNKNOWN, false);
            final Transition trans = new Transition(state, chars);
            this.getStartingState().addTransition(trans);
            final State state2 = new State(AbcTokenType.UNKNOWN, false);
            final char[] chars2 = { 'o', 'O' };
            state.addTransition(new Transition(state2, chars2));
            final State state3 = new State(AbcTokenType.MODE, true);
            final char[] chars3 = { 'n', 'N' };
            state2.addTransition(new Transition(state3, chars3));
        }
    }
    
    private class ModeLocrianDefinition extends AutomataDefinition
    {
        public ModeLocrianDefinition() {
            final char[] chars = { 'l', 'L' };
            final State state = new State(AbcTokenType.UNKNOWN, false);
            final Transition trans = new Transition(state, chars);
            this.getStartingState().addTransition(trans);
            final State state2 = new State(AbcTokenType.UNKNOWN, false);
            final char[] chars2 = { 'o', 'O' };
            state.addTransition(new Transition(state2, chars2));
            final State state3 = new State(AbcTokenType.MODE, true);
            final char[] chars3 = { 'c', 'C' };
            state2.addTransition(new Transition(state3, chars3));
        }
    }
    
    private class ModeLydianDefinition extends AutomataDefinition
    {
        public ModeLydianDefinition() {
            final char[] chars = { 'l', 'L' };
            final State state = new State(AbcTokenType.UNKNOWN, false);
            final Transition trans = new Transition(state, chars);
            this.getStartingState().addTransition(trans);
            final State state2 = new State(AbcTokenType.UNKNOWN, false);
            final char[] chars2 = { 'y', 'Y' };
            state.addTransition(new Transition(state2, chars2));
            final State state3 = new State(AbcTokenType.MODE, true);
            final char[] chars3 = { 'd', 'D' };
            state2.addTransition(new Transition(state3, chars3));
        }
    }
    
    private class ModeMajorDefinition extends AutomataDefinition
    {
        public ModeMajorDefinition() {
            final char[] chars = { 'm', 'M' };
            final State state = new State(AbcTokenType.UNKNOWN, false);
            final Transition trans = new Transition(state, chars);
            this.getStartingState().addTransition(trans);
            final State state2 = new State(AbcTokenType.UNKNOWN, false);
            final char[] chars2 = { 'a', 'A' };
            state.addTransition(new Transition(state2, chars2));
            final State state3 = new State(AbcTokenType.MODE, true);
            final char[] chars3 = { 'j', 'J' };
            state2.addTransition(new Transition(state3, chars3));
        }
    }
    
    private class ModeMinorDefinition extends AutomataDefinition
    {
        public ModeMinorDefinition() {
            final char[] chars = { 'm', 'M' };
            final State state = new State(AbcTokenType.MODE, true);
            final Transition trans = new Transition(state, chars);
            this.getStartingState().addTransition(trans);
            final State state2 = new State(AbcTokenType.UNKNOWN, false);
            final char[] chars2 = { 'i', 'I' };
            state.addTransition(new Transition(state2, chars2));
            final State state3 = new State(AbcTokenType.MODE, true);
            final char[] chars3 = { 'n', 'N' };
            state2.addTransition(new Transition(state3, chars3));
        }
    }
    
    private class ModeMixolydianDefinition extends AutomataDefinition
    {
        public ModeMixolydianDefinition() {
            final char[] chars = { 'm', 'M' };
            final State state = new State(AbcTokenType.UNKNOWN, false);
            final Transition trans = new Transition(state, chars);
            this.getStartingState().addTransition(trans);
            final State state2 = new State(AbcTokenType.UNKNOWN, false);
            final char[] chars2 = { 'i', 'I' };
            state.addTransition(new Transition(state2, chars2));
            final State state3 = new State(AbcTokenType.MODE, true);
            final char[] chars3 = { 'x', 'X' };
            state2.addTransition(new Transition(state3, chars3));
        }
    }
    
    private class ModePhrygianDefinition extends AutomataDefinition
    {
        public ModePhrygianDefinition() {
            final char[] chars = { 'p', 'P' };
            final State state = new State(AbcTokenType.UNKNOWN, false);
            final Transition trans = new Transition(state, chars);
            this.getStartingState().addTransition(trans);
            final State state2 = new State(AbcTokenType.UNKNOWN, false);
            final char[] chars2 = { 'h', 'H' };
            state.addTransition(new Transition(state2, chars2));
            final State state3 = new State(AbcTokenType.MODE, true);
            final char[] chars3 = { 'r', 'R' };
            state2.addTransition(new Transition(state3, chars3));
        }
    }
}
