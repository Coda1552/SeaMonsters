package codyhuh.seamonsters.registry;

import codyhuh.seamonsters.SeaMonstersMod;
import codyhuh.seamonsters.common.entity.SeaMonster;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, SeaMonstersMod.MOD_ID);

    public static final RegistryObject<EntityType<SeaMonster>> SEA_MONSTER = ENTITIES.register("sea_monster", () -> EntityType.Builder.of(SeaMonster::new, MobCategory.WATER_CREATURE).sized(1.5F, 1.1F).build("sea_monster"));
}
