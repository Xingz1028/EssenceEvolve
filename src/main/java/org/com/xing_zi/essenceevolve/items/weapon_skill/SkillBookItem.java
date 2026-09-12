package org.com.xing_zi.essenceevolve.items.weapon_skill;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class SkillBookItem extends Item {
    private final SkillType skill;
    private  String skillKey;
    private String skillId;
    private  int skillLevel;

    public SkillBookItem(Properties pProperties, SkillType skill) {
        super(pProperties);
        this.skill = skill;
    }

    public SkillType getSkill() {
        return skill;
    }

    @Override
    public ItemStack getDefaultInstance() {
        skillKey = skill.getSkillKey();
        skillId = skill.getSkillId();
        skillLevel = skill.getSkillLevel();
        return super.getDefaultInstance();
    }

    public int getSkillLevel() {
        return skillLevel;
    }

    public String getSkillId() {
        return skillId;
    }

    public String getSkillKey() {
        return skillKey;
    }
}
