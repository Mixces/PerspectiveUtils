package me.mixces.perspectiveutils.config

import cc.polyfrost.oneconfig.config.Config
import cc.polyfrost.oneconfig.config.annotations.KeyBind
import cc.polyfrost.oneconfig.config.annotations.Slider
import cc.polyfrost.oneconfig.config.annotations.Switch
import cc.polyfrost.oneconfig.config.core.OneKeyBind
import cc.polyfrost.oneconfig.config.data.Mod
import cc.polyfrost.oneconfig.config.data.ModType
import cc.polyfrost.oneconfig.libs.universal.UKeyboard
import me.mixces.perspectiveutils.PerspectiveUtils

object PerspectiveUtilsConfig : Config(Mod(PerspectiveUtils.NAME, ModType.UTIL_QOL), PerspectiveUtils.MODID + ".json") {

    @KeyBind(
        name = "Toggle Perspective Mod",
        description = "Bind the perspective mod to a button."
    )
    var perspectiveKeyBind = OneKeyBind(UKeyboard.KEY_NONE)

    @Switch(
        name = "Invert Pitch"
    )
    var invertPitch = false

    @Slider(
        name = "Hurt Camera Effect Intensity",
        min = 0.0F,
        max = 100.0F,
    )
    var effectIntensity = 14.0F

    init {
        initialize()
    }

}

