package codyhuh.seamonsters.registry;

import codyhuh.seamonsters.SeaMonstersMod;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, SeaMonstersMod.MOD_ID);

    public static final RegistryObject<Item> SEA_MONSTER_SPAWN_EGG = ITEMS.register("sea_monster_spawn_egg", () -> new ForgeSpawnEggItem(ModEntities.SEA_MONSTER, 0x00, 0x00, new Item.Properties()));
}
