package com.jamoorev;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.layout.PatternLayout;

@Mod(modid = "LogFix", name = "ModFix", version = "1.0", acceptableRemoteVersions="*")
public class LogFix {

    @EventHandler
    public void preinit(FMLPreInitializationEvent event) {
        LoggerContext ctx = (LoggerContext) LogManager.getContext(false);
        Configuration conf = ctx.getConfiguration();
        System.out.println(conf.getAppenders());
        for (String appender : new String[]{"FmlSysOut", "SysOut", "ServerGuiConsole"}) {
            Appender console = conf.getAppenders().get(appender);   // <-- your appender name
            PatternLayout layout = (PatternLayout) console.getLayout();
            layout.setConversionPattern("%d{yyyy-MM-dd HH:mm:ss} [%level] %msg%n");
        }
        ctx.updateLoggers(conf);
    }
}