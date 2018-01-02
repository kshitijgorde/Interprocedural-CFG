// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.spring;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.BeanNameAware;

public class ActiveMQConnectionFactory extends org.apache.activemq.ActiveMQConnectionFactory implements BeanNameAware
{
    private String beanName;
    private boolean useBeanNameAsClientIdPrefix;
    
    @PostConstruct
    public void afterPropertiesSet() throws Exception {
        if (this.isUseBeanNameAsClientIdPrefix() && this.getClientIDPrefix() == null) {
            this.setClientIDPrefix(this.getBeanName());
        }
    }
    
    public String getBeanName() {
        return this.beanName;
    }
    
    public void setBeanName(final String beanName) {
        this.beanName = beanName;
    }
    
    public boolean isUseBeanNameAsClientIdPrefix() {
        return this.useBeanNameAsClientIdPrefix;
    }
    
    public void setUseBeanNameAsClientIdPrefix(final boolean useBeanNameAsClientIdPrefix) {
        this.useBeanNameAsClientIdPrefix = useBeanNameAsClientIdPrefix;
    }
}
