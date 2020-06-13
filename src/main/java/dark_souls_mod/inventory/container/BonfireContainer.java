package dark_souls_mod.inventory.container;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.network.PacketBuffer;

import javax.annotation.Nonnull;

public class BonfireContainer extends Container {
    public BonfireContainer(int id, PlayerEntity player) {
        super(ContainerTypeRegistry.BONFIRE_CONTAINER_TYPE, id);
        System.out.println("there is a container man!");
    }

    public BonfireContainer(int id, PlayerInventory player_inventory, PacketBuffer packet_buffer) {
        super(ContainerTypeRegistry.BONFIRE_CONTAINER_TYPE, id);
    }

    @Override
    public boolean canInteractWith(@Nonnull PlayerEntity player) {
        return true;
    }
}
