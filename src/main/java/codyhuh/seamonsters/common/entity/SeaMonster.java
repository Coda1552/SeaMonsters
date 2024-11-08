package codyhuh.seamonsters.common.entity;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl;
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.entity.PartEntity;

public class SeaMonster extends WaterAnimal {
    private final SeaMonsterPart[] subEntities;
    private final SeaMonsterPart body;
    private final SeaMonsterPart head;
    private final SeaMonsterPart head2;
    private final SeaMonsterPart tail;
    private final SeaMonsterPart tail2;

    public SeaMonster(EntityType<? extends WaterAnimal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.moveControl = new SmoothSwimmingMoveControl(this, 85, 10, 0.1F, 0.1F, true);
        this.lookControl = new SmoothSwimmingLookControl(this, 10);
        this.body = new SeaMonsterPart(this, "body", 1.5F, 1.1F);
        this.head = new SeaMonsterPart(this, "head", 1.0F, 0.75F);
        this.head2 = new SeaMonsterPart(this, "head2", 1.0F, 0.75F);
        this.tail = new SeaMonsterPart(this, "tail", 1.0F, 0.85F);
        this.tail2 = new SeaMonsterPart(this, "tail2", 1.0F, 0.85F);
        this.subEntities = new SeaMonsterPart[]{this.body, this.head, this.head2, this.tail, this.tail2};
        this.setId(ENTITY_COUNTER.getAndAdd(this.subEntities.length + 1) + 1);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new PanicGoal(this, 1.25D));
        this.goalSelector.addGoal(5, new RandomSwimmingGoal(this, 1.0D, 10));
    }

    @Override
    protected PathNavigation createNavigation(Level pLevel) {
        return new WaterBoundPathNavigation(this, pLevel);
    }

    @Override
    public void travel(Vec3 pTravelVector) {
        if (this.isEffectiveAi() && this.isInWater()) {
            this.moveRelative(0.01F, pTravelVector);
            this.move(MoverType.SELF, this.getDeltaMovement());
            this.setDeltaMovement(this.getDeltaMovement().scale(0.9D));
            if (this.getTarget() == null) {
                this.setDeltaMovement(this.getDeltaMovement().add(0.0D, -0.005D, 0.0D));
            }
        } else {
            super.travel(pTravelVector);
        }

    }

    public static AttributeSupplier.Builder createSeaMonsterAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 60.0D).add(Attributes.MOVEMENT_SPEED, 0.25F).add(Attributes.KNOCKBACK_RESISTANCE, 0.5F);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
    }

    @Override
    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
    }

    @Override
    public void setId(int pId) {
        super.setId(pId);
        for (int i = 0; i < this.subEntities.length; i++)
            this.subEntities[i].setId(pId + i + 1);
    }

    @Override
    public void recreateFromPacket(ClientboundAddEntityPacket packet) {
        super.recreateFromPacket(packet);
        SeaMonsterPart[] parts = this.getSubEntities();

        for(int i = 0; i < parts.length; ++i) {
            parts[i].setId(i + packet.getId());
        }
    }

    @Override
    public boolean isMultipartEntity() {
        return true;
    }

    @Override
    public PartEntity<?>[] getParts() {
        return this.subEntities;
    }

    public SeaMonsterPart[] getSubEntities() {
        return this.subEntities;
    }

    protected void movePart(SeaMonsterPart part, double dX, double dY, double dZ) {
        Vec3 lastPos = new Vec3(part.getX(), part.getY(), part.getZ());

        part.setPos(this.getX() + dX, this.getY() + dY, this.getZ() + dZ);

        part.xo = lastPos.x;
        part.yo = lastPos.y;
        part.zo = lastPos.z;
        part.xOld = lastPos.x;
        part.yOld = lastPos.y;
        part.zOld = lastPos.z;
    }

    protected void updateParts() {
        Vec3 body = Vec3.ZERO;
        Vec3 head = new Vec3(0.0D, 0.2D, 1.0D).xRot(-getXRot() * ((float) Math.PI / 180f)).yRot(-yBodyRot * ((float) Math.PI / 180f));
        Vec3 head2 = new Vec3(0.0D, 0.2D, 2.0D).xRot(-getXRot() * ((float) Math.PI / 180f)).yRot(-yBodyRot * ((float) Math.PI / 180f));
        Vec3 tail = new Vec3(0.0D, 0.2D, -1.0D).xRot(-getXRot() * ((float) Math.PI / 180f)).yRot(-yBodyRot * ((float) Math.PI / 180f));
        Vec3 tail2 = new Vec3(0.0D, 0.2D, -2.0D).xRot(-getXRot() * ((float) Math.PI / 180f)).yRot(-yBodyRot * ((float) Math.PI / 180f));

        movePart(this.body, body.x, body.y, body.z);
        movePart(this.head, head.x, head.y, head.z);
        movePart(this.head2, head2.x, head2.y, head2.z);
        movePart(this.tail, tail.x, tail.y, tail.z);
        movePart(this.tail2, tail2.x, tail2.y, tail2.z);
    }

    @Override
    public boolean hurt(DamageSource pSource, float pAmount) {
        return super.hurt(pSource, pAmount);
    }

    public boolean damagePart(SeaMonsterPart part, DamageSource source, float damage) {
        if (!level().isClientSide()) System.out.println(part.name);
        return hurt(source, damage);
    }

    @Override
    protected float getStandingEyeHeight(Pose pPose, EntityDimensions pSize) {
        return pSize.height - 0.1F;
    }

    @Override
    public void aiStep() {
        super.aiStep();
        updateParts();
    }

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    public InteractionResult mobInteract(Player pPlayer, InteractionHand pHand) {
        return super.mobInteract(pPlayer, pHand);
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.EMPTY;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.EMPTY;
    }

    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundEvents.EMPTY;
    }
}
