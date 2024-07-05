package me.mixces.perspectiveutils

import cc.polyfrost.oneconfig.utils.commands.CommandManager
import me.mixces.perspectiveutils.command.PerspectiveUtilsCommand
import me.mixces.perspectiveutils.config.PerspectiveUtilsConfig
import net.minecraftforge.fml.common.Loader
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.event.FMLInitializationEvent

@Mod(
    modid = PerspectiveUtils.MODID,
    name = PerspectiveUtils.NAME,
    version = PerspectiveUtils.VERSION,
    modLanguageAdapter = "cc.polyfrost.oneconfig.utils.KotlinLanguageAdapter"
)
object PerspectiveUtils {

    const val MODID: String = "@ID@"
    const val NAME: String = "@NAME@"
    const val VERSION: String = "@VER@"

    @JvmField var isBehindYou: Boolean = false

    @Mod.EventHandler
    fun onInit(event: FMLInitializationEvent?) {
        PerspectiveUtilsConfig
        CommandManager.INSTANCE.registerCommand(PerspectiveUtilsCommand())
        isBehindYou = Loader.isModLoaded("behindyouv3")
    }

}
