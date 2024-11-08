package codyhuh.seamonsters;

import codyhuh.seamonsters.registry.ModCreativeTabs;
import codyhuh.seamonsters.registry.ModEntities;
import codyhuh.seamonsters.registry.ModItems;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(SeaMonstersMod.MOD_ID)
public class SeaMonstersMod {
    public static final String MOD_ID = "seamonsters";

    public SeaMonstersMod() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        ModCreativeTabs.TABS.register(bus);
        ModEntities.ENTITIES.register(bus);
        ModItems.ITEMS.register(bus);
    }
}
