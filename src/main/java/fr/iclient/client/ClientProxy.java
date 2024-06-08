package fr.iclient.client;

import cpw.mods.fml.common.FMLCommonHandler;
import fr.iclient.common.CommonProxy;
import fr.iclient.kiwi.eventbus.EventTransmitter;
import fr.iclient.kiwi.gui.GuiManager;
import net.minecraftforge.common.MinecraftForge;

public class ClientProxy extends CommonProxy
{
    public void registerRenders()
    {
        EventTransmitter event_transmitter = new EventTransmitter();
        GuiManager gui_manager = new GuiManager();
        
        MinecraftForge.EVENT_BUS.register(event_transmitter);
        FMLCommonHandler.instance().bus().register(event_transmitter);
        
        MinecraftForge.EVENT_BUS.register(gui_manager);
        FMLCommonHandler.instance().bus().register(gui_manager);
        
        super.registerRenders();
    }
}   