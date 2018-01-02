// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.parser;

import org.jdom.Element;
import org.jdom.Document;
import com.stonewall.parser.interpreter.Event;
import com.stonewall.parser.interpreter.Dispatcher;
import com.stonewall.parser.interpreter.Interpreter;

public interface Function
{
    String execute(final Context p0, final String[] p1) throws Exception;
    
    public static class Context
    {
        protected Interpreter interpreter;
        protected Dispatcher dispatcher;
        
        public Context(final Dispatcher dispatcher, final Interpreter interpreter) {
            this.dispatcher = dispatcher;
            this.interpreter = interpreter;
        }
        
        public void raiseEvent(final Event.Severity severity, final String msg) {
            this.interpreter.raiseEvent(severity, msg);
        }
        
        public void raiseEvent(final Event.Severity severity, final String msg, final Throwable e) {
            this.interpreter.raiseEvent(severity, msg, e);
        }
        
        public Document model() {
            return this.interpreter.model();
        }
        
        public Dictionary<String> dictionary(final String name) {
            return this.dictionaries().get(name);
        }
        
        public Dictionary<Dictionary<String>> dictionaries() {
            return this.interpreter.dictionaries();
        }
        
        public Dictionary<String> references() {
            return this.interpreter.references();
        }
        
        public Dictionary<Element> nodeReferences() {
            return this.interpreter.nodeReferences();
        }
        
        public Dispatcher dispatcher() {
            return this.dispatcher;
        }
        
        public Interpreter interpreter() {
            return null;
        }
    }
}
