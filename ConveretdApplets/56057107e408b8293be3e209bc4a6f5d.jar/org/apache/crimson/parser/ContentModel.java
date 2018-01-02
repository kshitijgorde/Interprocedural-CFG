// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.crimson.parser;

final class ContentModel
{
    public char type;
    public Object content;
    public ContentModel next;
    private SimpleHashtable cache;
    
    public ContentModel(final String element) {
        this.cache = new SimpleHashtable();
        this.type = '\0';
        this.content = element;
    }
    
    public ContentModel(final char type, final ContentModel content) {
        this.cache = new SimpleHashtable();
        this.type = type;
        this.content = content;
    }
    
    public boolean empty() {
        switch (this.type) {
            case '*':
            case '?': {
                return true;
            }
            case '\0':
            case '+': {
                return false;
            }
            case '|': {
                if (this.content instanceof ContentModel && ((ContentModel)this.content).empty()) {
                    return true;
                }
                for (ContentModel m = this.next; m != null; m = m.next) {
                    if (m.empty()) {
                        return true;
                    }
                }
                return false;
            }
            case ',': {
                if (!(this.content instanceof ContentModel)) {
                    return false;
                }
                if (!((ContentModel)this.content).empty()) {
                    return false;
                }
                for (ContentModel i = this.next; i != null; i = i.next) {
                    if (!i.empty()) {
                        return false;
                    }
                }
                return true;
            }
            default: {
                throw new InternalError();
            }
        }
    }
    
    public boolean first(final String token) {
        final Boolean b = (Boolean)this.cache.get(token);
        if (b != null) {
            return b;
        }
        boolean retval = false;
        switch (this.type) {
            case '\0':
            case '*':
            case '+':
            case '?': {
                if (this.content instanceof String) {
                    retval = (this.content == token);
                    break;
                }
                retval = ((ContentModel)this.content).first(token);
                break;
            }
            case ',': {
                if (this.content instanceof String) {
                    retval = (this.content == token);
                    break;
                }
                retval = (((ContentModel)this.content).first(token) || (((ContentModel)this.content).empty() && this.next != null && this.next.first(token)));
                break;
            }
            case '|': {
                retval = ((this.content instanceof String && this.content == token) || ((ContentModel)this.content).first(token) || (this.next != null && this.next.first(token)));
                break;
            }
            default: {
                throw new InternalError();
            }
        }
        if (retval) {
            this.cache.put(token, Boolean.TRUE);
        }
        else {
            this.cache.put(token, Boolean.FALSE);
        }
        return retval;
    }
}
