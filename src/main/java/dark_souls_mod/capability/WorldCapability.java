package dark_souls_mod.capability;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class WorldCapability {
    /*-------------------------------- bonfire --------------------------------*/
    /**
     * ids of bonfires
     * -1 = error
     * 0 = fire link shrine
     * 1, 2, 3... player's bonfires
     */
    private static int next_bonfire_id = 1;

    public static int getNextBonfireId() {
        return next_bonfire_id++;
    }




    /*-------------------------------- capability --------------------------------*/
    public static void register()
    {
        CapabilityManager.INSTANCE.register(WorldCapability.class, new WorldCapability.Storage(), WorldCapability::new);
    }

    public static WorldCapability getInstance(@Nonnull World world)
    {
        if (world.isRemote) throw new IllegalArgumentException("world  {" + world + "} is remote");
        return world.getCapability(Capability.WORLD_CAPABILITY)
                .orElseThrow(() -> new NullPointerException("WorldCapability of Player {" + world + "} is null"));
    }

    public static class Capability implements
            ICapabilitySerializable<CompoundNBT>
    {
        @CapabilityInject(WorldCapability.class)
        public static final net.minecraftforge.common.capabilities.Capability<WorldCapability> WORLD_CAPABILITY = null;
        private LazyOptional<WorldCapability> instance = LazyOptional.of(WORLD_CAPABILITY::getDefaultInstance);


        @Nonnull
        @Override
        public <T> LazyOptional<T> getCapability(@Nonnull net.minecraftforge.common.capabilities.Capability<T> cap, @Nullable Direction side)
        {
            return WORLD_CAPABILITY.orEmpty(cap, instance);
        }

        @Override
        public CompoundNBT serializeNBT()
        {
            return (CompoundNBT) WORLD_CAPABILITY.getStorage()
                    .writeNBT(WORLD_CAPABILITY,
                            instance.orElseThrow(() -> new IllegalArgumentException("LazyOptional can not be null")),
                            null);
        }

        @Override
        public void deserializeNBT(CompoundNBT nbt)
        {
            WORLD_CAPABILITY.getStorage()
                    .readNBT(WORLD_CAPABILITY,
                            instance.orElseThrow(() -> new IllegalArgumentException("LazyOptional con no be null")),
                            null,
                            nbt);
        }
    }

    public static class Storage
            implements net.minecraftforge.common.capabilities.Capability.IStorage<WorldCapability>
    {
        @Nullable
        @Override
        public INBT writeNBT(net.minecraftforge.common.capabilities.Capability<WorldCapability> capability, WorldCapability instance, Direction side)
        {
            return new CompoundNBT();
        }

        @Override
        public void readNBT(net.minecraftforge.common.capabilities.Capability<WorldCapability> capability, WorldCapability instance, Direction side, INBT nbt)
        {

        }
    }
}
