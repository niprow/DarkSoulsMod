package dark_souls_mod.capability;

import com.google.common.collect.Maps;
import dark_souls_mod.Constants;
import dark_souls_mod.capability.data.Bonfire;
import dark_souls_mod.capability.data.ExactBlockPos;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Objects;

public class WorldCapability {
    /*-------------------------------- bonfire --------------------------------*/
    private static final HashMap<Integer, Bonfire> LIT_BONFIRES = Maps.newHashMap();

    public Collection<Bonfire> getBonfirePositions() {
        return LIT_BONFIRES.values();
    }

    /**
     * ids of bonfires
     * -1 = error / not lit
     * 0 = fire link shrine
     * 1, 2, 3... player's bonfires
     */
    private static int next_bonfire_id = 1;

    private static int getNextBonfireId() {
        return next_bonfire_id++;
    }

    /**
     * @param pos BlockPos of block bonfire
     * @param name showed name of bonfire
     * @return the id of the bonfire in {@link WorldCapability#LIT_BONFIRES}
     */
    public int createNewLitBonfire(BlockPos pos, String name) {
        Bonfire bonfire = new Bonfire(name, new ExactBlockPos(pos, DimensionType.getKey(world.dimension.getType())));
        int id = getNextBonfireId();
        LIT_BONFIRES.put(id, bonfire);
        return id;
    }

    /*-------------------------------- capability --------------------------------*/
    private ServerWorld world;

    public static void register()
    {
        CapabilityManager.INSTANCE.register(WorldCapability.class, new WorldCapability.Storage(), WorldCapability::new);
    }

    public static WorldCapability getInstance(@Nonnull ServerWorld world)
    {
        WorldCapability capability = world.getCapability(Capability.WORLD_CAPABILITY)
                .orElseThrow(() -> new NullPointerException("WorldCapability of World {" + world + "} is null"));
        capability.world = world;
        return capability;

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
                            instance.orElseThrow(() -> new IllegalArgumentException("LazyOptional cannot be null")),
                            null);
        }

        @Override
        public void deserializeNBT(CompoundNBT nbt)
        {
            WORLD_CAPABILITY.getStorage()
                    .readNBT(WORLD_CAPABILITY,
                            instance.orElseThrow(() -> new IllegalArgumentException("LazyOptional cannot be null")),
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
            CompoundNBT nbt = new CompoundNBT();
            Bonfire[] bonfires = LIT_BONFIRES.values().toArray(new Bonfire[0]);
            Integer[] keys = LIT_BONFIRES.keySet().toArray(new Integer[0]);

            nbt.putInt(Constants.NBT_LIT_BONFIRES_SIZE, LIT_BONFIRES.size());
            for(int i = 0; i < keys.length; i++) {
                nbt.putInt(Constants.NBT_LIT_BONFIRES_KEY + i, keys[i]);
                nbt.put(Constants.NBT_LIT_BONFIRES_VALUE + i, bonfires[i].write());
            }

            return nbt;
        }

        @Override
        public void readNBT(net.minecraftforge.common.capabilities.Capability<WorldCapability> capability, WorldCapability instance, Direction side, INBT nbt)
        {
            CompoundNBT compound = (CompoundNBT) nbt;
            int size = compound.getInt(Constants.NBT_LIT_BONFIRES_SIZE);
            for(int i = 0; i < size; i++) {
                LIT_BONFIRES.put(compound.getInt(Constants.NBT_LIT_BONFIRES_KEY + i), Bonfire.read((CompoundNBT) Objects.requireNonNull(compound.get(Constants.NBT_LIT_BONFIRES_VALUE + i))));
            }
        }
    }
}
