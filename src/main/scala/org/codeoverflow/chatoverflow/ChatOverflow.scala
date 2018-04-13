package org.codeoverflow.chatoverflow

import org.apache.log4j.Logger
import org.codeoverflow.chatoverflow.framework.{PluginFramework, PluginManagerImpl}

object ChatOverflow {

  val pluginFolder = "plugins/"
  private val logger = Logger.getLogger(this.getClass)

  def main(args: Array[String]): Unit = {
    println("Minzig!")

    logger info "Started Chat Overflow Framework. Hello everybody!"

    // Create plugin framework and manager instance
    val pluginFramework = PluginFramework(pluginFolder)
    val pluginManager = new PluginManagerImpl

    // Initialize plugin framework
    pluginFramework.init(pluginManager)
    logger info s"Loaded plugins list:\n ${pluginFramework.getLoadedPlugins.mkString("\n")}"

    if (pluginFramework.getNotLoadedPlugins.nonEmpty) {
      logger info s"Unable to load:\n ${pluginFramework.getNotLoadedPlugins.mkString("\n")}"
    }

    // Start plugins
    for (plugin <- pluginFramework.getLoadedPlugins) {
      pluginFramework.asyncStartPlugin(plugin)
    }
  }
}
