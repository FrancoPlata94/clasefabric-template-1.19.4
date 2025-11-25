package com.franco.clasefabric;

import com.franco.clasefabric.world.ModConfiguredFeatures;
import com.franco.clasefabric.datagen.DatagenModelProvider;
import com.franco.clasefabric.datagen.DatagenLootTableProvider;
import com.franco.clasefabric.datagen.DatagenWorldGeneratorProvider;
import com.franco.clasefabric.datagen.DatagenItemTagProvider;
import com.franco.clasefabric.datagen.DatagenBlockTagProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;

public class ClaseFabricDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack=fabricDataGenerator.createPack();

		pack.addProvider(DatagenModelProvider::new);
		pack.addProvider(DatagenLootTableProvider::new);
		pack.addProvider(DatagenWorldGeneratorProvider::new);
		pack.addProvider(DatagenBlockTagProvider::new);

	}

	@Override
	public void buildRegistry(RegistryBuilder registryBuilder) {
		registryBuilder.addRegistry(RegistryKeys.CONFIGURED_FEATURE, ModConfiguredFeatures::bootstrap);
	}
}
