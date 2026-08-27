package org.com.xing_zi.essenceevolve.items.herb.attribute;

//HerbAttributeProvider的实现枚举类
public enum HerbAttributeType implements HerbAttributeProvider {
     VIRID_LEAF("ess.wood", 1),
     UNDALUME_LOTUS("ess.water", 1),
     IGNIFLARE("ess.fire", 1),
     TERRAPETRA("ess.earth", 1),
    AROMAHOLD("ess.time", 1),
    AUREUS_LOTUS("ess.metal", 1),
    WIND_SPIKE("ess.wind",1),
    THUNDER_SPIKE("ess.thunder",1);


    private String key;
    private int value;
    HerbAttributeType(String key, int value){
        this.key = key;
        this.value = value;
    }

    @Override
    public String getNbtKey() {
        return key;
    }

    @Override
    public int getNbtValue() {
        return value;
    }
}
