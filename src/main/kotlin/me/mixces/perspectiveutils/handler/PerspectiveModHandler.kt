package me.mixces.perspectiveutils.handler

import me.mixces.perspectiveutils.PerspectiveUtils
import me.mixces.perspectiveutils.config.PerspectiveUtilsConfig
import net.minecraft.client.Minecraft

object PerspectiveModHandler {

    private val mc = Minecraft.getMinecraft()

    @JvmField var cameraPrevYaw = 0f
    @JvmField var cameraPrevPitch = 0f
    @JvmField var cameraYaw = 0f
    @JvmField var cameraPitch = 0f

    private var lastActivated = false
    var keyBindToggled = false

    fun showPerspective(): Boolean {
        return mc.gameSettings.thirdPersonView == 3
    }

    fun onRender() {
        if (!PerspectiveUtilsConfig.enabled && mc.gameSettings.thirdPersonView == 3) {
            setPerspective(0)
        }
        val down = PerspectiveUtilsConfig.perspectiveKeyBind.isActive && mc.inGameHasFocus
        if (down != lastActivated) {
            lastActivated = down
            setPerspective(if (down) (if (keyBindToggled) 0 else 3) else 0)
        }
    }

    private fun setPerspective(perspective: Int) {
        if (perspective == 3) {
            keyBindToggled = true
            cameraPrevYaw = mc.thePlayer.prevRotationYaw
            cameraPrevPitch = mc.thePlayer.prevRotationPitch
            cameraYaw = mc.thePlayer.rotationYaw
            cameraPitch = mc.thePlayer.rotationPitch
        } else {
            keyBindToggled = false
        }
        if (PerspectiveUtils.isBehindYou) {
            setPerspective(perspective)
        }
        mc.gameSettings.thirdPersonView = perspective
    }

}
