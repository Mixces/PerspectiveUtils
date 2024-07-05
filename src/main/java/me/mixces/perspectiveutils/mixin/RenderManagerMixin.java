package me.mixces.perspectiveutils.mixin;

import me.mixces.perspectiveutils.config.PerspectiveUtilsConfig;
import me.mixces.perspectiveutils.handler.PerspectiveModHandler;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RenderManager.class)
public class RenderManagerMixin {

    @Shadow public float playerViewX;
    @Shadow public float playerViewY;

    @Inject(
            method = "cacheActiveRenderInfo",
            at = @At(
                    value = "FIELD",
                    opcode = Opcodes.PUTFIELD,
                    target = "Lnet/minecraft/client/renderer/entity/RenderManager;playerViewX:F",
                    ordinal = 1,
                    shift = At.Shift.AFTER
            )
    )
    private void perspectiveUtils$reAssignPlayerViewX(World worldIn, FontRenderer textRendererIn, Entity livingPlayerIn, Entity pointedEntityIn, GameSettings optionsIn, float partialTicks, CallbackInfo ci) {
        if (!PerspectiveModHandler.INSTANCE.getKeyBindToggled() || !PerspectiveUtilsConfig.INSTANCE.enabled) { return; }
        playerViewX = PerspectiveModHandler.cameraPitch;
    }

    @Inject(
            method = "cacheActiveRenderInfo",
            at = @At(
                    value = "FIELD",
                    opcode = Opcodes.PUTFIELD,
                    target = "Lnet/minecraft/client/renderer/entity/RenderManager;playerViewX:F",
                    ordinal = 1,
                    shift = At.Shift.AFTER
            )
    )
    private void perspectiveUtils$reAssignPlayerViewY(World worldIn, FontRenderer textRendererIn, Entity livingPlayerIn, Entity pointedEntityIn, GameSettings optionsIn, float partialTicks, CallbackInfo ci) {
        if (!PerspectiveModHandler.INSTANCE.getKeyBindToggled() || !PerspectiveUtilsConfig.INSTANCE.enabled) { return; }
        playerViewY = PerspectiveModHandler.cameraYaw;
    }

}
