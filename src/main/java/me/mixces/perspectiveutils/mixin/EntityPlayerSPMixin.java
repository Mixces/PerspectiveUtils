package me.mixces.perspectiveutils.mixin;

import com.mojang.authlib.GameProfile;
import me.mixces.perspectiveutils.config.PerspectiveUtilsConfig;
import me.mixces.perspectiveutils.handler.PerspectiveModHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(EntityPlayerSP.class)
public class EntityPlayerSPMixin extends AbstractClientPlayer {

    public EntityPlayerSPMixin(World worldIn, GameProfile playerProfile) {
        super(worldIn, playerProfile);
    }

    @Override
    public void setAngles(final float yaw, final float pitch) {
        if (!PerspectiveUtilsConfig.INSTANCE.enabled) { super.setAngles(yaw, pitch); }
        PerspectiveModHandler.INSTANCE.onRender();
        if (PerspectiveModHandler.INSTANCE.showPerspective()) {
            PerspectiveModHandler.cameraPrevYaw = PerspectiveModHandler.cameraYaw;
            PerspectiveModHandler.cameraPrevPitch = PerspectiveModHandler.cameraPitch;
            PerspectiveModHandler.cameraYaw += (float)(yaw * 0.15);
            PerspectiveModHandler.cameraPitch += (float)(pitch * (PerspectiveUtilsConfig.INSTANCE.getInvertPitch() ? 0.15 : -0.15));
            PerspectiveModHandler.cameraPitch = MathHelper.clamp_float(PerspectiveModHandler.cameraPitch, -90.0f, 90.0f);
            if (PerspectiveModHandler.cameraYaw != PerspectiveModHandler.cameraPrevYaw || this.cameraPitch != PerspectiveModHandler.cameraPrevPitch) {
                Minecraft.getMinecraft().renderGlobal.setDisplayListEntitiesDirty();
            }
        } else {
            super.setAngles(yaw, pitch);
        }
    }

}
