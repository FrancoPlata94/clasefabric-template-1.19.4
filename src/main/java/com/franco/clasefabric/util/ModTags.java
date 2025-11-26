package com.franco.clasefabric.util;

import com.franco.clasefabric.ClaseFabric;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModTags {
    public static class Blocks{
        public static final TagKey<Block> NEEDS_CRISTAL_TOOL=createTag("needs_cristal_tool");
        public static final TagKey<Block> INCORRECT_FOR_CRISTAL_TOOL=createTag("incorrect_for_cristal_tool");

        public static TagKey<Block> createTag(String name){
            return TagKey.of(RegistryKeys.BLOCK, Identifier.of(ClaseFabric.MOD_ID,name));}
    }
    public static class Items{
        public static TagKey<Item> createTag(String name){
            return TagKey.of(RegistryKeys.ITEM, Identifier.of(ClaseFabric.MOD_ID,name));
        }

    }
}
