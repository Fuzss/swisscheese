package fuzs.swisscheese.config;

import fuzs.swisscheese.SwissCheese;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.fml.client.config.GuiConfig;

public  class ConfigGui extends GuiConfig {
    public ConfigGui(GuiScreen parentScreen) {
        super(parentScreen, new ConfigElement(ConfigHandler.config.getCategory(ConfigHandler.categoryGeneral)).getChildElements(), SwissCheese.MODID, false, false, GuiConfig.getAbridgedConfigPath(ConfigHandler.config.toString()));
    }
}
