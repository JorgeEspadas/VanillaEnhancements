package com.ezpadaz.vanillaenhancements.Commands;

import com.ezpadaz.vanillaenhancements.Commands.Mochila.AdminBackpackCommand;
import com.ezpadaz.vanillaenhancements.Commands.Mochila.BackpackCommand;
import com.ezpadaz.vanillaenhancements.Commands.Teleport.AceptarCommand;
import com.ezpadaz.vanillaenhancements.Commands.Teleport.IrCommand;
import com.ezpadaz.vanillaenhancements.Commands.Teleport.RechazarCommand;
import com.ezpadaz.vanillaenhancements.Commands.Teleport.RegresarCommand;
import com.ezpadaz.vanillaenhancements.Commands.Teleport.TraerCommand;

public class CommandBootloader {
    public CommandBootloader() {
        new IrCommand();
        new TraerCommand();
        new AceptarCommand();
        new RechazarCommand();
        new RegresarCommand();
        new BackpackCommand();
        new AdminBackpackCommand();
    }
}
