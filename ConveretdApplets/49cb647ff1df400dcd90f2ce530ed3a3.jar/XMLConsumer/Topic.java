// 
// Decompiled by Procyon v0.5.30
// 

package XMLConsumer;

public class Topic
{
    private String m_url;
    private String m_name;
    
    public Topic(final String url, final String name) {
        this.m_url = url;
        this.m_name = name;
    }
    
    public void copyFrom(final Topic topic) {
        this.m_url = topic.m_url;
        this.m_name = topic.m_name;
    }
    
    public String getURL() {
        return this.m_url;
    }
    
    public String getName() {
        return this.m_name;
    }
}
