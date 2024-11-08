package codyhuh.seamonsters.client;

import codyhuh.seamonsters.SeaMonstersMod;
import codyhuh.seamonsters.client.model.SeaMonsterModel;
import codyhuh.seamonsters.client.render.SeaMonsterRenderer;
import codyhuh.seamonsters.registry.ModEntities;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SeaMonstersMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEvents {

    @SubscribeEvent
    public static void registerRenders(EntityRenderersEvent.RegisterRenderers e) {
        e.registerEntityRenderer(ModEntities.SEA_MONSTER.get(), SeaMonsterRenderer::new);
    }

    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions e) {
        e.registerLayerDefinition(SeaMonsterModel.LAYER_LOCATION, SeaMonsterModel::createBodyLayer);
    }
}
