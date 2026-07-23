package org.com.xing_zi.essenceevolve.EssItem.EssenceVial;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.com.xing_zi.essenceevolve.EssItem.EssItems;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class EssenceVial extends Item {
    // 木元素方块集合：全部植物、树木、植被、作物、花草
    private static final Set<Block> WOOD_ESSENCE_BLOCKS = new HashSet<>(Arrays.asList(
            // 原木树干 LOG
            Blocks.OAK_LOG,
            Blocks.BIRCH_LOG,
            Blocks.SPRUCE_LOG,
            Blocks.JUNGLE_LOG,
            Blocks.ACACIA_LOG,
            Blocks.DARK_OAK_LOG,
            Blocks.CHERRY_LOG,
            Blocks.MANGROVE_LOG,
            // 去皮树干
            Blocks.STRIPPED_OAK_LOG,
            Blocks.STRIPPED_BIRCH_LOG,
            Blocks.STRIPPED_SPRUCE_LOG,
            Blocks.STRIPPED_JUNGLE_LOG,
            Blocks.STRIPPED_ACACIA_LOG,
            Blocks.STRIPPED_DARK_OAK_LOG,
            Blocks.STRIPPED_CHERRY_LOG,
            Blocks.STRIPPED_MANGROVE_LOG,

            // 整木 WOOD
            Blocks.OAK_WOOD,
            Blocks.BIRCH_WOOD,
            Blocks.SPRUCE_WOOD,
            Blocks.JUNGLE_WOOD,
            Blocks.ACACIA_WOOD,
            Blocks.DARK_OAK_WOOD,
            Blocks.CHERRY_WOOD,
            Blocks.MANGROVE_WOOD,
            // 去皮整木
            Blocks.STRIPPED_OAK_WOOD,
            Blocks.STRIPPED_BIRCH_WOOD,
            Blocks.STRIPPED_SPRUCE_WOOD,
            Blocks.STRIPPED_JUNGLE_WOOD,
            Blocks.STRIPPED_ACACIA_WOOD,
            Blocks.STRIPPED_DARK_OAK_WOOD,
            Blocks.STRIPPED_CHERRY_WOOD,
            Blocks.STRIPPED_MANGROVE_WOOD,

            // 树叶
            Blocks.OAK_LEAVES,
            Blocks.BIRCH_LEAVES,
            Blocks.SPRUCE_LEAVES,
            Blocks.JUNGLE_LEAVES,
            Blocks.ACACIA_LEAVES,
            Blocks.DARK_OAK_LEAVES,
            Blocks.CHERRY_LEAVES,
            Blocks.MANGROVE_LEAVES,

            // 植被地表方块
            Blocks.GRASS_BLOCK,
            Blocks.PODZOL,
            Blocks.MOSS_BLOCK,
            Blocks.MOSS_CARPET,

            // 杂草、蕨类
            Blocks.GRASS,
            Blocks.FERN,
            Blocks.TALL_GRASS,
            Blocks.LARGE_FERN,

            // 小型单花
            Blocks.DANDELION,
            Blocks.POPPY,
            Blocks.BLUE_ORCHID,
            Blocks.ALLIUM,
            Blocks.AZURE_BLUET,
            Blocks.RED_TULIP,
            Blocks.ORANGE_TULIP,
            Blocks.WHITE_TULIP,
            Blocks.PINK_TULIP,
            Blocks.OXEYE_DAISY,
            Blocks.CORNFLOWER,
            Blocks.LILY_OF_THE_VALLEY,
            Blocks.WITHER_ROSE,

            // 双层大型花卉
            Blocks.SUNFLOWER,
            Blocks.LILAC,
            Blocks.ROSE_BUSH,
            Blocks.PEONY,

            // 农作物
            Blocks.WHEAT,
            Blocks.CARROTS,
            Blocks.POTATOES,
            Blocks.BEETROOTS,
            Blocks.PUMPKIN_STEM,
            Blocks.MELON_STEM,

            // 水生/特殊植物
            Blocks.SUGAR_CANE,
            Blocks.BAMBOO,
            Blocks.LILY_PAD,
            Blocks.VINE,
            Blocks.CACTUS,

            // 红树林根系
            Blocks.MANGROVE_ROOTS,
            Blocks.MUDDY_MANGROVE_ROOTS
    ));

    // 土元素方块集合：纯土石、砂石、黏土、矿石、陶瓦（无任何植物方块）
    private static final Set<Block> EARTH_ESSENCE_BLOCKS = new HashSet<>(Arrays.asList(
            // 基础泥土
            Blocks.DIRT,
            Blocks.COARSE_DIRT,
            Blocks.ROOTED_DIRT,
            Blocks.MYCELIUM,
            Blocks.FARMLAND,

            // 红砂岩、各色陶瓦
            Blocks.RED_SAND,
            Blocks.RED_SANDSTONE,
            Blocks.CUT_RED_SANDSTONE,
            Blocks.CHISELED_RED_SANDSTONE,
            Blocks.SMOOTH_RED_SANDSTONE,
            Blocks.TERRACOTTA,
            Blocks.WHITE_TERRACOTTA,
            Blocks.ORANGE_TERRACOTTA,
            Blocks.MAGENTA_TERRACOTTA,
            Blocks.LIGHT_BLUE_TERRACOTTA,
            Blocks.YELLOW_TERRACOTTA,
            Blocks.LIME_TERRACOTTA,
            Blocks.PINK_TERRACOTTA,
            Blocks.GRAY_TERRACOTTA,
            Blocks.LIGHT_GRAY_TERRACOTTA,
            Blocks.CYAN_TERRACOTTA,
            Blocks.PURPLE_TERRACOTTA,
            Blocks.BLUE_TERRACOTTA,
            Blocks.BROWN_TERRACOTTA,
            Blocks.GREEN_TERRACOTTA,
            Blocks.RED_TERRACOTTA,
            Blocks.BLACK_TERRACOTTA,

            // 普通沙子、砂砾
            Blocks.SAND,
            Blocks.SANDSTONE,
            Blocks.CUT_SANDSTONE,
            Blocks.CHISELED_SANDSTONE,
            Blocks.SMOOTH_SANDSTONE,
            Blocks.GRAVEL,

            // 黏土、泥制品
            Blocks.CLAY,
            Blocks.MUD,
            Blocks.MUD_BRICKS,
            Blocks.PACKED_MUD,

            // 基础石头系列
            Blocks.STONE,
            Blocks.COBBLESTONE,
            Blocks.MOSSY_COBBLESTONE,
            Blocks.SMOOTH_STONE,
            Blocks.STONE_BRICKS,
            Blocks.MOSSY_STONE_BRICKS,
            Blocks.CRACKED_STONE_BRICKS,
            Blocks.CHISELED_STONE_BRICKS,

            // 变种岩石
            Blocks.GRANITE,
            Blocks.POLISHED_GRANITE,
            Blocks.DIORITE,
            Blocks.POLISHED_DIORITE,
            Blocks.ANDESITE,
            Blocks.POLISHED_ANDESITE,

            // 洞穴岩石
            Blocks.TUFF,
            Blocks.CALCITE,
            Blocks.DRIPSTONE_BLOCK,
            Blocks.POINTED_DRIPSTONE,

            // 浅层矿石
            Blocks.COAL_ORE,
            Blocks.IRON_ORE,
            Blocks.GOLD_ORE,
            Blocks.COPPER_ORE,
            Blocks.LAPIS_ORE,
            Blocks.REDSTONE_ORE,
            Blocks.DIAMOND_ORE,
            Blocks.EMERALD_ORE
    ));

    //构造
    public EssenceVial(Properties pProperties) {
        super(pProperties);
    }

    //重写useOn方法
    @Override
    public InteractionResult useOn(UseOnContext context) {
        //因为这些逻辑是跑在服务端的，所以要对客户端做一个判断，如果是客户端就不运行代码，直接返回PASS


        //创建玩家对象
        Player player = context.getPlayer();
        //创建物品实例对象传入手部物品
        ItemStack handItemStack = context.getItemInHand();
        //创建方块坐标对象
        BlockPos clickPos = context.getClickedPos();
        //创建方块状态对象
        BlockState blockState = context.getLevel().getBlockState(clickPos);//将方块坐标对象传入方块状态对象
        //创建被点击方块对象
        Block clickBlock = blockState.getBlock();//方块状态对象中getBlock();方法来进行获取被点击的方块的对象
        //进行对被点击的方块进行判断
        if (player != null) {
            //方块中心坐标
            //水
            if (clickBlock == Blocks.WATER_CAULDRON || clickBlock == Blocks.WATER) {
                if (context.getPlayer() != null) {
                    context.getLevel().playSound(null,context.getPlayer().blockPosition(), SoundEvents.WATER_AMBIENT, SoundSource.BLOCKS, 1.0F, 1.0F);
                }
                if (context.getLevel().isClientSide()) {
                    for (int i = 0; i < 10; i++) {
                        double x = clickPos.getX() + (Math.random() - 0.5D)*2;
                        double y = clickPos.getY() + 1.2D;
                        double z = clickPos.getZ() + (Math.random() - 0.5D)*2;
                        context.getLevel().addParticle(
                                ParticleTypes.FALLING_WATER,
                                true,
                                x, y, z,
                                4D, -4D, 4D
                        );
                    }
                }
                int slotMatchingItem = player.getInventory().findSlotMatchingItem(new ItemStack(EssItems.ESSENCE_VIAL_WATER.get()));
                handItemStack.shrink(1);
                int freeSlot = player.getInventory().getFreeSlot();
                if (freeSlot >= 0) {
                    player.getInventory().add(new ItemStack(EssItems.ESSENCE_VIAL_WATER.get()));
                } else if (freeSlot == -1 && slotMatchingItem == -1) {
                    player.drop(new ItemStack(EssItems.ESSENCE_VIAL_WATER.get()), false);
                }
                //木
            } else if (WOOD_ESSENCE_BLOCKS.contains(clickBlock)) {
                if (context.getPlayer() != null) {
                    context.getLevel().playSound(null,context.getPlayer().blockPosition(), SoundEvents.SOUL_SOIL_BREAK, SoundSource.BLOCKS, 1.0F, 1.0F);
                }
                for (int i = 0; i < 10; i++) {
                    double x = clickPos.getX() + (Math.random() - 0.5D)*2;
                    double y = clickPos.getY() + 1.2D;
                    double z = clickPos.getZ() + (Math.random() - 0.5D)*2;
                    context.getLevel().addParticle(
                            ParticleTypes.EGG_CRACK,
                            true,
                            x, y, z,
                            4D, -4D, 4D
                    );
                }
                int slotMatchingItem = player.getInventory().findSlotMatchingItem(new ItemStack(EssItems.ESSENCE_VIAL_WOOD.get()));
                handItemStack.shrink(1);
                int freeSlot = player.getInventory().getFreeSlot();
                if (freeSlot >= 0) {
                    player.getInventory().add(new ItemStack(EssItems.ESSENCE_VIAL_WOOD.get()));
                } else if (freeSlot == -1 && slotMatchingItem == -1) {
                    player.drop(new ItemStack(EssItems.ESSENCE_VIAL_WOOD.get()), false);
                }
                //火
            } else if (clickBlock == Blocks.FIRE || clickBlock == Blocks.LAVA_CAULDRON || clickBlock == Blocks.LAVA) {
                for (int i = 0; i < 10; i++) {
                    double x = clickPos.getX() + (Math.random() - 0.5D)*2;
                    double y = clickPos.getY() + 1.2D;
                    double z = clickPos.getZ() + (Math.random() - 0.5D)*2;
                    context.getLevel().addParticle(
                            ParticleTypes.FALLING_LAVA,
                            true,
                            x, y, z,
                            4D, -4D, 4D
                    );
                }
                int slotMatchingItem = player.getInventory().findSlotMatchingItem(new ItemStack(EssItems.ESSENCE_VIAL_FIRE.get()));
                handItemStack.shrink(1);
                int freeSlot = player.getInventory().getFreeSlot();
                if (freeSlot >= 0) {
                    player.getInventory().add(new ItemStack(EssItems.ESSENCE_VIAL_FIRE.get()));
                } else if (freeSlot == -1 && slotMatchingItem == -1) {
                    player.drop(new ItemStack(EssItems.ESSENCE_VIAL_FIRE.get()), false);
                }
                //金
            } else if (clickBlock == Blocks.GOLD_BLOCK) {
                if (context.getPlayer() != null) {
                    context.getLevel().playSound(null,context.getPlayer().blockPosition(), SoundEvents.PLAYER_LEVELUP, SoundSource.BLOCKS, 1.0F, 1.0F);
                }
                for (int i = 0; i < 10; i++) {
                    double x = clickPos.getX() + (Math.random() - 0.5D)*2;
                    double y = clickPos.getY() + 1.2D;
                    double z = clickPos.getZ() + (Math.random() - 0.5D)*2;
                    context.getLevel().addParticle(
                            ParticleTypes.END_ROD,
                            true,
                            x, y, z,
                            4D, -4D, 4D
                    );
                }
                int slotMatchingItem = player.getInventory().findSlotMatchingItem(new ItemStack(EssItems.ESSENCE_VIAL_METAL.get()));
                handItemStack.shrink(1);
                int freeSlot = player.getInventory().getFreeSlot();
                if (freeSlot >= 0) {
                    player.getInventory().add(new ItemStack(EssItems.ESSENCE_VIAL_METAL.get()));
                } else if (freeSlot == -1 && slotMatchingItem == -1) {
                    player.drop(new ItemStack(EssItems.ESSENCE_VIAL_METAL.get()), false);
                }
                //土
            } else if (EARTH_ESSENCE_BLOCKS.contains(clickBlock)) {
                if (context.getPlayer() != null) {
                    context.getLevel().playSound(null,context.getPlayer().blockPosition(), SoundEvents.CAVE_VINES_PICK_BERRIES, SoundSource.BLOCKS, 1.0F, 1.0F);
                }
                for (int i = 0; i < 10; i++) {
                    double x = clickPos.getX() + (Math.random() - 0.5D)*2;
                    double y = clickPos.getY() + 1.2D;
                    double z = clickPos.getZ() + (Math.random() - 0.5D)*2;
                    context.getLevel().addParticle(
                            ParticleTypes.WAX_ON,
                            true,
                            x, y, z,
                            4D, -4D, 4D
                    );
                }
                int slotMatchingItem = player.getInventory().findSlotMatchingItem(new ItemStack(EssItems.ESSENCE_VIAL_EARTH.get()));
                handItemStack.shrink(1);
                int freeSlot = player.getInventory().getFreeSlot();
                if (freeSlot >= 0) {
                    player.getInventory().add(new ItemStack(EssItems.ESSENCE_VIAL_EARTH.get()));
                } else if (freeSlot == -1 && slotMatchingItem == -1) {
                    player.drop(new ItemStack(EssItems.ESSENCE_VIAL_EARTH.get()), false);
                }
            }
        }

        //返回运行成功
        return InteractionResult.SUCCESS;
    }
}