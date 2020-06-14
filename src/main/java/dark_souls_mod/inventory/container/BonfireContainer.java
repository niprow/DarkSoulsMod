package dark_souls_mod.inventory.container;

import dark_souls_mod.item.ItemRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;

import javax.annotation.Nonnull;

public class BonfireContainer extends Container {
    public BonfireContainer(int id, PlayerEntity player) {
        super(ContainerTypeRegistry.BONFIRE_CONTAINER_TYPE, id);

        int amount = 0;

        for(ItemStack stack : player.inventory.mainInventory) {
            if(stack.getItem() == ItemRegistry.EMPTY_ESTUS_FLASK) {
                amount += stack.getCount();
                stack.shrink(stack.getCount());
            }
        }
        for(ItemStack stack : player.inventory.offHandInventory) {
            if(stack.getItem() == ItemRegistry.EMPTY_ESTUS_FLASK) {
                amount += stack.getCount();
                stack.shrink(stack.getCount());
            }
        }

        while(true) {
            if(amount > 15) {
                player.inventory.addItemStackToInventory(new ItemStack(ItemRegistry.FULL_ESTUS_FLASK, 15));
                amount -= 15;
            }
            else {
                if(amount > 0) {
                    player.inventory.addItemStackToInventory(new ItemStack(ItemRegistry.FULL_ESTUS_FLASK, amount));
                }
                break;
            }
        }
    }

    public BonfireContainer(int id, PlayerInventory player_inventory, PacketBuffer packet_buffer) {
        super(ContainerTypeRegistry.BONFIRE_CONTAINER_TYPE, id);
    }

    @Override
    public boolean canInteractWith(@Nonnull PlayerEntity player) {
        return true;
    }
}
