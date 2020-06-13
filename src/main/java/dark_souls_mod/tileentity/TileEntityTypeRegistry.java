package dark_souls_mod.tileentity;

import com.google.common.collect.Lists;
import dark_souls_mod.Constants;
import dark_souls_mod.block.BlockRegistry;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;

import java.util.List;

public class TileEntityTypeRegistry {
    /*-------------------------------- constants --------------------------------*/
    public static final List<TileEntityType<?>> TILE_ENTITY_TYPES = Lists.newArrayList();
    public static final TileEntityType<BonfireTileEntity> BONFIRE_TILE_ENTITY_TYPE = register(Constants.BONFIRE, TileEntityType.Builder.create(BonfireTileEntity::new, BlockRegistry.BONFIRE).build(null));

    /*-------------------------------- automation --------------------------------*/
    public static <T extends TileEntity> TileEntityType<T> register(ResourceLocation tag, TileEntityType<?> tile_entity_type) {
        tile_entity_type.setRegistryName(tag);
        TILE_ENTITY_TYPES.add(tile_entity_type);
        return (TileEntityType<T>) tile_entity_type;
    }
    public static TileEntityType<?>[] getAll() {
        return TILE_ENTITY_TYPES.toArray(new TileEntityType[0]);
    }
}
