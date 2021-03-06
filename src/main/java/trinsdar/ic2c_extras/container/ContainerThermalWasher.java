package trinsdar.ic2c_extras.container;

import ic2.core.inventory.container.ContainerTileComponent;
import ic2.core.inventory.gui.components.base.FluidTankComp;
import ic2.core.inventory.gui.components.base.MachineChargeComp;
import ic2.core.inventory.gui.components.base.MachineProgressComp;
import ic2.core.inventory.gui.components.base.MachineSpeedComp;
import ic2.core.inventory.slots.SlotCustom;
import ic2.core.inventory.slots.SlotDischarge;
import ic2.core.inventory.slots.SlotOutput;
import ic2.core.inventory.slots.SlotUpgrade;
import ic2.core.platform.registry.Ic2GuiComp;
import ic2.core.util.math.Box2D;
import ic2.core.util.math.Vec2i;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import trinsdar.ic2c_extras.tileentity.TileEntityThermalWasher;

public class ContainerThermalWasher extends ContainerTileComponent<TileEntityThermalWasher> {
    public static Vec2i speedTextPos = new Vec2i(80, 53);

    public ContainerThermalWasher(InventoryPlayer player, TileEntityThermalWasher tile) {
        super(tile);
        this.addSlotToContainer(new SlotDischarge(tile, 2147483647, 0, 56, 53));
        this.addSlotToContainer(new SlotCustom(tile, 1, 56, 17, tile.filter));
        this.addSlotToContainer(new SlotOutput(player.player, tile, 2, 111, 17));
        this.addSlotToContainer(new SlotOutput(player.player, tile, 3, 111, 35));
        this.addSlotToContainer(new SlotOutput(player.player, tile, 4, 111, 53));
        this.addSlotToContainer(new SlotCustom(tile, 5, 8, 12, new ContainerOreWashingPlant.FluidItemFilter()));
        this.addSlotToContainer(new SlotCustom(tile, 6, 8, 57, null));

        for (int i = 0; i < 2; ++i) {
            this.addSlotToContainer(new SlotUpgrade(tile, 7 + i, 152, 8 + i * 18));
        }

        this.addPlayerInventory(player);
        this.addComponent(new MachineChargeComp(tile, Ic2GuiComp.machineChargeBox, Ic2GuiComp.machineChargePos));
        this.addComponent(new MachineProgressComp(tile, Ic2GuiComp.machineProgressBox, Ic2GuiComp.machineProgressPos));
        this.addComponent(new FluidTankComp(new Box2D(13, 32, 16, 58), tile.waterTank, new Vec2i(176, 133), new Box2D(32, 13, 16, 58)));
        this.addComponent(new MachineSpeedComp(tile, tile.getSpeedName(), speedTextPos));
    }

    @Override
    public ResourceLocation getTexture() {
        return this.getGuiHolder().getTexture();
    }

    @Override
    public int guiInventorySize() {
        return this.getGuiHolder().slotCount;
    }

    @Override
    public boolean canInteractWith(EntityPlayer entityPlayer) {
        return this.getGuiHolder().canInteractWith(entityPlayer);
    }
}
