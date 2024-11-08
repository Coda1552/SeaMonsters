package codyhuh.seamonsters.registry;

import codyhuh.seamonsters.SeaMonstersMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Items;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, SeaMonstersMod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> SEA_MONSTERS_TAB = TABS.register("sea_monsters_tab", () -> CreativeModeTab.builder()
            .icon(Items.FILLED_MAP::getDefaultInstance)
            .title(Component.translatable("itemGroup." + SeaMonstersMod.MOD_ID))
            .displayItems((itemDisplayParameters, output) -> {
                ModItems.ITEMS.getEntries().forEach(itemRegistryObject -> output.accept(itemRegistryObject.get()));
            })
            .build());
}