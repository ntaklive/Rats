package com.github.alexthe666.rats.server.world.structure;

import com.github.alexthe666.rats.server.world.gen.RatStructure;
import com.github.alexthe666.rats.server.world.gen.RatsStructureProcessor;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.LockableLootTileEntity;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.structure.TemplateStructurePiece;
import net.minecraft.world.gen.feature.template.BlockIgnoreStructureProcessor;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;
import net.minecraft.world.storage.loot.LootTables;

import java.util.List;
import java.util.Random;

public class RatlantisRuinsPiece {
    private static final BlockPos STRUCTURE_OFFSET = new BlockPos(4, 0, 15);

    public static void func_204760_a(TemplateManager p_204760_0_, BlockPos p_204760_1_, Rotation p_204760_2_, List<StructurePiece> p_204760_3_, Random p_204760_4_) {
        RatStructure model;
        int chance = p_204760_4_.nextInt(100);
        if (chance < 10) {
            model = p_204760_4_.nextBoolean() ? RatStructure.CHEESE_STATUETTE : RatStructure.GIANT_CHEESE;
        } else if (chance < 50) {
            switch (p_204760_4_.nextInt(5)) {
                case 1:
                    model = RatStructure.PILLAR;
                    break;
                case 2:
                    model = RatStructure.PILLAR_LEANING;
                    break;
                case 3:
                    model = RatStructure.PILLAR_COLLECTION;
                    break;
                case 4:
                    model = RatStructure.PILLAR_THIN;
                    break;
                default:
                    model = RatStructure.PILLAR_LEANING;
                    break;
            }
        } else if (chance < 70) {
            switch (p_204760_4_.nextInt(5)) {
                case 1:
                    model = RatStructure.TOWER;
                    break;
                case 2:
                    model = RatStructure.FORUM;
                    break;
                case 3:
                    model = RatStructure.HUT;
                    break;
                case 4:
                    model = RatStructure.PALACE;
                    break;
                default:
                    model = RatStructure.TEMPLE;
                    break;
            }
        } else if (chance < 96) {
            switch (p_204760_4_.nextInt(4)) {
                case 1:
                    model = RatStructure.SPHINX;
                    break;
                case 2:
                    model = RatStructure.LINCOLN;
                    break;
                case 3:
                    model = RatStructure.CHEESE_STATUETTE;
                    break;
                default:
                    model = RatStructure.HEAD;
                    break;
            }
        } else {
            model = RatStructure.COLOSSUS;
        }
        p_204760_3_.add(new RatlantisRuinsPiece.Piece(p_204760_0_, model.structureLoc, p_204760_1_, p_204760_2_, p_204760_4_));
    }

    public static class Piece extends TemplateStructurePiece {
        private final Rotation rotation;
        private final ResourceLocation field_204756_e;
        private final Random random;

        public Piece(TemplateManager p_i48904_1_, ResourceLocation p_i48904_2_, BlockPos p_i48904_3_, Rotation p_i48904_4_, Random random) {
            super(RatlantisStructureRegistry.RAT_RUINS_TYPE, 0);
            this.templatePosition = p_i48904_3_;
            this.rotation = p_i48904_4_;
            this.field_204756_e = p_i48904_2_;
            this.func_204754_a(p_i48904_1_);
            this.template = p_i48904_1_.getTemplate(p_i48904_2_);
            this.random = new Random();
        }

        public Piece(TemplateManager p_i50445_1_, CompoundNBT p_i50445_2_) {
            super(RatlantisStructureRegistry.RAT_RUINS_TYPE, p_i50445_2_);
            this.field_204756_e = new ResourceLocation(p_i50445_2_.getString("Template"));
            this.rotation = Rotation.valueOf(p_i50445_2_.getString("Rot"));
            this.func_204754_a(p_i50445_1_);
            this.template = p_i50445_1_.getTemplate(RatStructure.FORUM.structureLoc);
            this.random = new Random();
        }

        protected void readAdditional(CompoundNBT p_143011_1_) {
            super.readAdditional(p_143011_1_);
            p_143011_1_.putString("Template", this.field_204756_e.toString());
            p_143011_1_.putString("Rot", this.rotation.name());
        }

        private void func_204754_a(TemplateManager p_204754_1_) {
            Template lvt_2_1_ = p_204754_1_.getTemplateDefaulted(this.field_204756_e);
            PlacementSettings lvt_3_1_ = (new PlacementSettings()).setRotation(this.rotation).setMirror(Mirror.NONE).setCenterOffset(RatlantisRuinsPiece.STRUCTURE_OFFSET).addProcessor(BlockIgnoreStructureProcessor.AIR_AND_STRUCTURE_BLOCK);
            this.setup(lvt_2_1_, this.templatePosition, lvt_3_1_);
        }

        protected void handleDataMarker(String p_186175_1_, BlockPos p_186175_2_, IWorld p_186175_3_, Random p_186175_4_, MutableBoundingBox p_186175_5_) {
            if ("map_chest".equals(p_186175_1_)) {
                LockableLootTileEntity.setLootTable(p_186175_3_, p_186175_4_, p_186175_2_.down(), LootTables.CHESTS_SHIPWRECK_MAP);
            } else if ("treasure_chest".equals(p_186175_1_)) {
                LockableLootTileEntity.setLootTable(p_186175_3_, p_186175_4_, p_186175_2_.down(), LootTables.CHESTS_SHIPWRECK_TREASURE);
            } else if ("supply_chest".equals(p_186175_1_)) {
                LockableLootTileEntity.setLootTable(p_186175_3_, p_186175_4_, p_186175_2_.down(), LootTables.CHESTS_SHIPWRECK_SUPPLY);
            }

        }

        public boolean addComponentParts(IWorld world, Random random, MutableBoundingBox bb, ChunkPos chunkCoords) {
            this.placeSettings.func_215219_b().addProcessor(new RatsStructureProcessor(0.75F + random.nextFloat() * 0.75F));
            BlockPos inital = this.templatePosition.add(this.template.getSize().getX() / 2, 0, this.template.getSize().getZ() / 2);
            int lvt_8_1_ = world.getHeight(Heightmap.Type.OCEAN_FLOOR_WG, chunkCoords.asBlockPos().getX(), chunkCoords.asBlockPos().getZ()) - random.nextInt(4);
            BlockPos pos = new BlockPos(chunkCoords.asBlockPos().getX(), lvt_8_1_, chunkCoords.asBlockPos().getZ());
            while(world.getBlockState(pos.down()).getMaterial().isLiquid() && pos.getY() > 3){
                pos = pos.down();
            }
            this.templatePosition = new BlockPos(this.templatePosition.getX(), pos.getY() - 3, this.templatePosition.getZ());
            return super.addComponentParts(world, random, bb, chunkCoords);
        }
    }
}
