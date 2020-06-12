package dark_souls_mod.block;

import dark_souls_mod.tileentity.BonfireTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nullable;

public class BonfireBlock extends Block {
    public BonfireBlock() {
        super(Block.Properties.create(Material.WOOD)
                .harvestTool(ToolType.AXE)
                .hardnessAndResistance(4.0f, 4.0f)
                .lightValue(7)
        );
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new BonfireTileEntity();
    }
}
