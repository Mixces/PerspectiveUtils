package me.mixces.perspectiveutils.command

import cc.polyfrost.oneconfig.utils.commands.annotations.Command
import cc.polyfrost.oneconfig.utils.commands.annotations.Main
import me.mixces.perspectiveutils.PerspectiveUtils
import me.mixces.perspectiveutils.config.PerspectiveUtilsConfig

@Command(
    value = PerspectiveUtils.MODID,
    description = "Access the " + PerspectiveUtils.NAME + " GUI."
)
class PerspectiveUtilsCommand {

    @Main
    private fun handle() {
        PerspectiveUtilsConfig.openGui()
    }

}