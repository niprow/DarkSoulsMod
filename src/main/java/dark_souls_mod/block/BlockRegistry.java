package dark_souls_mod.block;

import com.google.common.collect.Lists;
import dark_souls_mod.Constants;
import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;

import java.util.List;

public class BlockRegistry {
    /*-------------------------------- constants --------------------------------*/
    public static final List<Block> BLOCKS = Lists.newArrayList();
    public static final Block BONFIRE = register(Constants.BONFIRE, new BonfireBlock());

    /*-------------------------------- automation --------------------------------*/
    public static Block register(ResourceLocation tag, Block block) {
        block.setRegistryName(tag);
        BLOCKS.add(block);
        return block;
    }
    public static Block[] getAll() {
        return BLOCKS.toArray(new Block[0]);
    }
}
