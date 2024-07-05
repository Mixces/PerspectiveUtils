package me.mixces.perspectiveutils.mixin;

import me.mixces.perspectiveutils.config.PerspectiveUtilsConfig;
import me.mixces.perspectiveutils.handler.PerspectiveModHandler;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityAnimal;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(EntityRenderer.class)
public class EntityRendererMixin {

    @Redirect(
            method = "orientCamera",
            at = @At(
                    value = "FIELD",
                    opcode = Opcodes.GETFIELD,
                    target = "Lnet/minecraft/entity/Entity;rotationYaw:F"
            )
    )
    private float perspectiveUtils$orientCamera(Entity instance) {
        if (!PerspectiveModHandler.INSTANCE.getKeyBindToggled() || !PerspectiveUtilsConfig.INSTANCE.enabled) { return instance.rotationYaw; }
        return PerspectiveModHandler.cameraYaw;
    }

    @Redirect(
            method = "orientCamera",
            at = @At(
                    value = "FIELD",
                    opcode = Opcodes.GETFIELD,
                    target = "Lnet/minecraft/entity/Entity;prevRotationYaw:F"
            )
    )
    private float perspectiveUtils$orientCamera2(Entity instance) {
        if (!PerspectiveModHandler.INSTANCE.getKeyBindToggled() || !PerspectiveUtilsConfig.INSTANCE.enabled) { return instance.prevRotationYaw; }
        return PerspectiveModHandler.cameraYaw;
    }

    @Redirect(
            method = "orientCamera",
            at = @At(
                    value = "FIELD",
                    opcode = Opcodes.GETFIELD,
                    target = "Lnet/minecraft/entity/Entity;rotationPitch:F"
            )
    )
    private float perspectiveUtils$orientCamera3(Entity instance) {
        if (!PerspectiveModHandler.INSTANCE.getKeyBindToggled() || !PerspectiveUtilsConfig.INSTANCE.enabled) { return instance.rotationPitch; }
        return PerspectiveModHandler.cameraPitch;
    }

    @Redirect(
            method = "orientCamera",
            at = @At(
                    value = "FIELD",
                    opcode = Opcodes.GETFIELD,
                    target = "Lnet/minecraft/entity/Entity;prevRotationPitch:F"
            )
    )
    private float perspectiveUtils$orientCamera4(Entity instance) {
        if (!PerspectiveModHandler.INSTANCE.getKeyBindToggled() || !PerspectiveUtilsConfig.INSTANCE.enabled) { return instance.prevRotationPitch; }
        return PerspectiveModHandler.cameraPitch;
    }

//    @Unique private static final ThreadLocal<Entity> mixcesAnimations$entity = ThreadLocal.withInitial(() -> null);
//    @Unique private static final ThreadLocal<Float> mixcesAnimations$partialTicks = ThreadLocal.withInitial(() -> 0.0F);
//
//    @ModifyVariable(
//            method = "orientCamera",
//            at = @At(
//                    value = "HEAD",
//                    ordinal = 0
//            ),
//            index = 1,
//            argsOnly = true
//    )
//    private float mixcesAnimations$capturePartialTicks(float partialTicks) {
//        mixcesAnimations$partialTicks.set(partialTicks);
//        return partialTicks;
//    }
//
//    @ModifyVariable(
//            method = "orientCamera",
//            at = @At(
//                    value = "STORE",
//                    ordinal = 0
//            ),
//            index = 2
//    )
//    private Entity mixcesAnimations$captureEntity(Entity entity) {
//        mixcesAnimations$entity.set(entity);
//        return entity;
//    }
//
//    @ModifyArg(
//            method = "orientCamera",
//            at = @At(
//                    value = "INVOKE",
//                    target = "Lnet/minecraft/client/renderer/GlStateManager;rotate(FFFF)V",
//                    ordinal = 1
//            ),
//            index = 0
//    )
//    private float perspectiveUtils$modifyYawInterp(float angle) {
//        if (!PerspectiveModHandler.INSTANCE.getKeyBindToggled() || !PerspectiveUtilsConfig.INSTANCE.enabled) { return angle; }
//        return PerspectiveModHandler.cameraYaw + 180.0F;
//    }
//
//    @ModifyArg(
//            method = "orientCamera",
//            at = @At(
//                    value = "INVOKE",
//                    target = "Lnet/minecraft/client/renderer/GlStateManager;rotate(FFFF)V",
//                    ordinal = 2
//            ),
//            index = 0
//    )
//    private float perspectiveUtils$modifyPitchInterp(float angle) {
//        if (!PerspectiveModHandler.INSTANCE.getKeyBindToggled() || !PerspectiveUtilsConfig.INSTANCE.enabled) { return angle; }
//        return PerspectiveModHandler.cameraPitch;
//    }
//
//    @ModifyArg(
//            method = "orientCamera",
//            at = @At(
//                    value = "INVOKE",
//                    target = "Lnet/minecraft/client/renderer/GlStateManager;rotate(FFFF)V",
//                    ordinal = 4
//            ),
//            index = 0
//    )
//    private float perspectiveUtils$modifyPitch2(float angle) {
//        if (!PerspectiveModHandler.INSTANCE.getKeyBindToggled() || !PerspectiveUtilsConfig.INSTANCE.enabled) { return angle; }
//        return angle + PerspectiveModHandler.cameraPitch - mixcesAnimations$entity.get().rotationPitch;
//    }
//
//    @ModifyArg(
//            method = "orientCamera",
//            at = @At(
//                    value = "INVOKE",
//                    target = "Lnet/minecraft/client/renderer/GlStateManager;rotate(FFFF)V",
//                    ordinal = 5
//            ),
//            index = 0
//    )
//    private float perspectiveUtils$modifyYaw2(float angle) {
//        if (!PerspectiveModHandler.INSTANCE.getKeyBindToggled() || !PerspectiveUtilsConfig.INSTANCE.enabled) { return angle; }
//        return angle + PerspectiveModHandler.cameraYaw - mixcesAnimations$entity.get().rotationYaw;
//    }
//
//    @ModifyArg(
//            method = "orientCamera",
//            at = @At(
//                    value = "INVOKE",
//                    target = "Lnet/minecraft/client/renderer/GlStateManager;rotate(FFFF)V",
//                    ordinal = 6
//            ),
//            index = 0
//    )
//    private float perspectiveUtils$modifyYaw3(float angle) {
//        if (!PerspectiveModHandler.INSTANCE.getKeyBindToggled() || !PerspectiveUtilsConfig.INSTANCE.enabled) { return angle; }
//        return angle + mixcesAnimations$entity.get().rotationYaw - PerspectiveModHandler.cameraYaw;
//    }
//
//    @ModifyArg(
//            method = "orientCamera",
//            at = @At(
//                    value = "INVOKE",
//                    target = "Lnet/minecraft/client/renderer/GlStateManager;rotate(FFFF)V",
//                    ordinal = 7
//            ),
//            index = 0
//    )
//    private float perspectiveUtils$modifyPitch3(float angle) {
//        if (!PerspectiveModHandler.INSTANCE.getKeyBindToggled() || !PerspectiveUtilsConfig.INSTANCE.enabled) { return angle; }
//        return angle + mixcesAnimations$entity.get().rotationPitch - PerspectiveModHandler.cameraPitch;
//    }
//
//    @ModifyArg(
//            method = "orientCamera",
//            at = @At(
//                    value = "INVOKE",
//                    target = "Lnet/minecraft/client/renderer/GlStateManager;rotate(FFFF)V",
//                    ordinal = 9
//            ),
//            index = 0
//    )
//    private float perspectiveUtils$modifyPitch4(float angle) {
//        if (!PerspectiveModHandler.INSTANCE.getKeyBindToggled() || !PerspectiveUtilsConfig.INSTANCE.enabled) { return angle; }
//        return PerspectiveModHandler.cameraPitch;
//    }
//
//    @ModifyArg(
//            method = "orientCamera",
//            at = @At(
//                    value = "INVOKE",
//                    target = "Lnet/minecraft/client/renderer/GlStateManager;rotate(FFFF)V",
//                    ordinal = 10
//            ),
//            index = 0
//    )
//    private float perspectiveUtils$modifyYaw4(float angle) {
//        if (!PerspectiveModHandler.INSTANCE.getKeyBindToggled() || !PerspectiveUtilsConfig.INSTANCE.enabled) { return angle; }
//        return PerspectiveModHandler.cameraYaw + 180.0F;
//    }

    @ModifyArg(
            method = "hurtCameraEffect",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/renderer/GlStateManager;rotate(FFFF)V",
                    ordinal = 2
            ),
            index = 0
    )
    private float perspectiveUtils$modifyEffectIntensity(float original) {
        if (!PerspectiveUtilsConfig.INSTANCE.enabled) { return original; }
        return original * PerspectiveUtilsConfig.INSTANCE.getEffectIntensity() / 14.0F;
    }

}
