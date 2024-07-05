package me.mixces.perspectiveutils.mixin;

import me.mixces.perspectiveutils.PerspectiveUtils;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.ModContainer;
import net.minecraftforge.fml.common.network.handshake.FMLHandshakeMessage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.Map;

@Mixin(
        value = FMLHandshakeMessage.ModList.class,
        remap = false
)
public class ModListMixin extends FMLHandshakeMessage {

    @Shadow private Map<String, String> modTags;

    @Inject(
            method = "<init>(Ljava/util/List;)V",
            at = @At(
                    value = "RETURN"
            )
    )
    private void perspectiveUtils$removeModID(List<ModContainer> modList, CallbackInfo ci) {
        if (Minecraft.getMinecraft().isSingleplayer()) { return; }
        modTags.keySet().removeIf(key -> key.equalsIgnoreCase(PerspectiveUtils.MODID));
    }

}