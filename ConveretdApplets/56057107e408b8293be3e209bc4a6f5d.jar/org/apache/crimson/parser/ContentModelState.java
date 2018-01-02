// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.crimson.parser;

class ContentModelState
{
    private ContentModel model;
    private boolean sawOne;
    private ContentModelState next;
    
    ContentModelState(final ContentModel model) {
        this(model, null);
    }
    
    private ContentModelState(final Object content, final ContentModelState next) {
        this.model = (ContentModel)content;
        this.next = next;
        this.sawOne = false;
    }
    
    boolean terminate() {
        switch (this.model.type) {
            case '+': {
                if (!this.sawOne && !this.model.empty()) {
                    return false;
                }
                return this.next == null || this.next.terminate();
            }
            case '*':
            case '?': {
                return this.next == null || this.next.terminate();
            }
            case '|': {
                return this.model.empty() && (this.next == null || this.next.terminate());
            }
            case ',': {
                ContentModel m;
                for (m = this.model; m != null && m.empty(); m = m.next) {}
                return m == null && (this.next == null || this.next.terminate());
            }
            case '\0': {
                return false;
            }
            default: {
                throw new InternalError();
            }
        }
    }
    
    ContentModelState advance(final String token) throws EndOfInputException {
        switch (this.model.type) {
            case '*':
            case '+': {
                if (this.model.first(token)) {
                    this.sawOne = true;
                    if (this.model.content instanceof String) {
                        return this;
                    }
                    return new ContentModelState(this.model.content, this).advance(token);
                }
                else {
                    if ((this.model.type == '*' || this.sawOne) && this.next != null) {
                        return this.next.advance(token);
                    }
                    break;
                }
                break;
            }
            case '?': {
                if (this.model.first(token)) {
                    if (this.model.content instanceof String) {
                        return this.next;
                    }
                    return new ContentModelState(this.model.content, this.next).advance(token);
                }
                else {
                    if (this.next != null) {
                        return this.next.advance(token);
                    }
                    break;
                }
                break;
            }
            case '|': {
                for (ContentModel m = this.model; m != null; m = m.next) {
                    if (m.content instanceof String) {
                        if (token == m.content) {
                            return this.next;
                        }
                    }
                    else if (((ContentModel)m.content).first(token)) {
                        return new ContentModelState(m.content, this.next).advance(token);
                    }
                }
                if (this.model.empty() && this.next != null) {
                    return this.next.advance(token);
                }
                break;
            }
            case ',': {
                if (this.model.first(token)) {
                    if (this.model.type == '\0') {
                        return this.next;
                    }
                    ContentModelState nextState;
                    if (this.model.next == null) {
                        nextState = new ContentModelState(this.model.content, this.next);
                    }
                    else {
                        nextState = new ContentModelState(this.model.content, this);
                        this.model = this.model.next;
                    }
                    return nextState.advance(token);
                }
                else {
                    if (this.model.empty() && this.next != null) {
                        return this.next.advance(token);
                    }
                    break;
                }
                break;
            }
            case '\0': {
                if (this.model.content == token) {
                    return this.next;
                }
                break;
            }
        }
        throw new EndOfInputException();
    }
}
