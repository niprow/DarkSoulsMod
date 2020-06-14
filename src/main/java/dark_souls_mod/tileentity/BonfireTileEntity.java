package dark_souls_mod.tileentity;

import dark_souls_mod.Constants;
import dark_souls_mod.inventory.container.BonfireContainer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.text.ITextComponent;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class BonfireTileEntity extends TileEntity implements INamedContainerProvider {
    public BonfireTileEntity() {
        super(TileEntityTypeRegistry.BONFIRE_TILE_ENTITY_TYPE);
    }

    private int id = -1;

    @Override
    public void read(CompoundNBT compound) {
        this.id = compound.getInt(Constants.NBT_ID);
        super.read(compound);
    }

    @Nonnull
    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.putInt(Constants.NBT_ID, this.id);
        return super.write(compound);
    }

    @Nonnull
    @Override
    public ITextComponent getDisplayName() {
        return Constants.TLT_CONTAINER_BONFIRE;
    }

    @Nullable
    @Override
    public Container createMenu(int id, @Nonnull PlayerInventory player_inventory, @Nonnull PlayerEntity player_entity) {
        System.out.println("it iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii");
        return new BonfireContainer(id, player_inventory.player);
    }


}
