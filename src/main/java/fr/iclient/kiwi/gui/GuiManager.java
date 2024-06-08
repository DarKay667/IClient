package fr.iclient.kiwi.gui;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraftforge.client.event.GuiOpenEvent;

public class GuiManager
{
    /*@SubscribeEvent
    public void onOpenGui(GuiOpenEvent e)
    {
        try {
            if (e != null && e.gui != null && e.gui.getClass() != null && e.gui.getClass().getName() != null && (e.gui instanceof GuiMainMenu || (e.gui.getClass().getName().equalsIgnoreCase("fr.paladium.palamod.client.gui.PGuiMainMenu") && (e.gui instanceof GuiMainMenu || e.gui.getClass().getName().equalsIgnoreCase("fr.nathox.arkadium.gui.GuiCustomMainMenu")))))
            {
                e.gui = new CustomGuiMainMenu();
            }
        } catch (Exception exception) {}
    }*/
}   