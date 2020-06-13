package dark_souls_mod.block;

import dark_souls_mod.capability.WorldCapability;
import dark_souls_mod.tileentity.BonfireTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
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

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if(!player.getEntityWorld().isRemote) {
            WorldCapability.getInstance((ServerWorld) player.getEntityWorld());
        }
        return ActionResultType.SUCCESS;
    }
}
