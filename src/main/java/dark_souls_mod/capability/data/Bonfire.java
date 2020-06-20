package dark_souls_mod.capability.data;

import dark_souls_mod.Constants;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;

public class Bonfire {
    public final String name;
    public final ExactBlockPos exact_block_pos;

    public Bonfire(String name, ExactBlockPos exact_block_pos) {
        this.name = name;
        this.exact_block_pos = exact_block_pos;
    }

    public CompoundNBT write() {
        CompoundNBT nbt = new CompoundNBT();
        nbt.putString(Constants.NBT_NAME, name);
        nbt.putString(Constants.NBT_DIMENSION_KEY, exact_block_pos.dimension_key.toString());
        nbt.putInt(Constants.NBT_POS_X, exact_block_pos.getX());
        nbt.putInt(Constants.NBT_POS_Y, exact_block_pos.getY());
        nbt.putInt(Constants.NBT_POS_Z, exact_block_pos.getZ());
        return nbt;
    }

    public static Bonfire read(CompoundNBT nbt) {
        return new Bonfire(nbt.getString(Constants.NBT_NAME), new ExactBlockPos(nbt.getInt(Constants.NBT_POS_X), nbt.getInt(Constants.NBT_POS_Y), nbt.getInt(Constants.NBT_POS_Z), new ResourceLocation(nbt.getString(Constants.NBT_DIMENSION_KEY))));
    }

    public void spawnPlayer(PlayerEntity player) {
        //TODO
    }

    public void setSpawnPoint(PlayerEntity player) {
        //TODO
    }
}
