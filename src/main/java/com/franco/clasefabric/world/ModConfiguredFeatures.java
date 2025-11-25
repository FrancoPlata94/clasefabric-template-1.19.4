package com.franco.clasefabric.world;

import com.franco.clasefabric.ClaseFabric;
import com.franco.clasefabric.blocks.Modblock;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.BlobFoliagePlacer;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;

public class ModConfiguredFeatures {
    public static final RegistryKey<ConfiguredFeature<?, ?>> DRUG_MAPLE_BLOCK_KEY =registerKey("drug_maple");

    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>>context){
        register(context, DRUG_MAPLE_BLOCK_KEY, Feature.TREE, new TreeFeatureConfig.Builder(
                BlockStateProvider.of(Modblock.LOG_BLOCK.getLeft()),
                new StraightTrunkPlacer(3,3,9),
                BlockStateProvider.of(Modblock.LEAVES_BLOCK.getLeft()),
                new BlobFoliagePlacer(ConstantIntProvider.create(2),ConstantIntProvider.create(12), 4),
                new TwoLayersFeatureSize(45,2,4)).build());


    }


    public static  RegistryKey<ConfiguredFeature<?, ?>> registerKey(String name){
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, new Identifier(ClaseFabric.MOD_ID, name));
    }
    public static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?, ?>> context,
                                                                                  RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration){
        context.register(key, new ConfiguredFeature<>(feature,configuration));
    }
}
