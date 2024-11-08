package codyhuh.seamonsters.common;

import codyhuh.seamonsters.SeaMonstersMod;
import codyhuh.seamonsters.common.entity.SeaMonster;
import codyhuh.seamonsters.registry.ModEntities;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SeaMonstersMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonEvents {

    @SubscribeEvent
    public static void createAttributes(EntityAttributeCreationEvent e) {
        e.put(ModEntities.SEA_MONSTER.get(), SeaMonster.createSeaMonsterAttributes().build());
    }
}