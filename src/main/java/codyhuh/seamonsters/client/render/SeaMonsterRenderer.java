package codyhuh.seamonsters.client.render;

import codyhuh.seamonsters.SeaMonstersMod;
import codyhuh.seamonsters.client.model.SeaMonsterModel;
import codyhuh.seamonsters.common.entity.SeaMonster;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class SeaMonsterRenderer extends MobRenderer<SeaMonster, SeaMonsterModel<SeaMonster>> {
    private static final ResourceLocation LOC = new ResourceLocation(SeaMonstersMod.MOD_ID, "textures/entity/sea_monster.png");

    public SeaMonsterRenderer(EntityRendererProvider.Context cntxt) {
        super(cntxt, new SeaMonsterModel<>(cntxt.bakeLayer(SeaMonsterModel.LAYER_LOCATION)), 1.0F);
    }

    public ResourceLocation getTextureLocation(SeaMonster entity) {
        return LOC;
    }

    @Override
    public void render(SeaMonster pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        pPoseStack.pushPose();

        pPoseStack.scale(3.0F, 3.0F, 3.0F);

        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);

        pPoseStack.popPose();
    }
}