package org.com.xing_zi.essenceevolve.items.herb.attribute;
/**
 *用于外界注册药物属性的接口 **/
public interface HerbAttributeProvider {
    /**
     * 获取药物的nbtkey
     * **/
    String getNbtKey();
    /**
     * 获取药物的nbtvalue
     * **/
    int getNbtValue();
}
