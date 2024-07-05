package me.mixces.perspectiveutils.mixin;


import me.mixces.perspectiveutils.config.PerspectiveUtilsConfig;
import me.mixces.perspectiveutils.handler.PerspectiveModHandler;
import net.minecraft.client.renderer.ActiveRenderInfo;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(ActiveRenderInfo.class)
public class ActiveRenderInfoMixin {

    @ModifyVariable(
            method = "updateRenderInfo",
            at = @At(
                    value = "STORE",
                    ordinal = 0
            ),
            index = 2
    )
    private static float perspectiveUtils$modifyH(float h) {
        if (!PerspectiveModHandler.INSTANCE.getKeyBindToggled() || !PerspectiveUtilsConfig.INSTANCE.enabled) { return h; }
        return PerspectiveModHandler.cameraYaw;
    }

    @ModifyVariable(
            method = "updateRenderInfo",
            at = @At(
                    value = "STORE",
                    ordinal = 0
            ),
            index = 3
    )
    private static float perspectiveUtils$modifyJ(float j) {
        if (!PerspectiveModHandler.INSTANCE.getKeyBindToggled() || !PerspectiveUtilsConfig.INSTANCE.enabled) { return j; }
        return PerspectiveModHandler.cameraPitch;
    }

}
