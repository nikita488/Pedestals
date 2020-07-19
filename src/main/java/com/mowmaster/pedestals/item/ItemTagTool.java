package com.mowmaster.pedestals.item;

import com.mowmaster.pedestals.blocks.BlockPedestalTE;
import com.mowmaster.pedestals.item.pedestalUpgrades.ItemUpgradeBaseMachine;
import com.mowmaster.pedestals.tiles.TilePedestal;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

import static com.mowmaster.pedestals.pedestals.PEDESTALS_TAB;
import static com.mowmaster.pedestals.references.Reference.MODID;

public class ItemTagTool extends Item {

    public ItemTagTool() {
        super(new Properties().maxStackSize(1).containerItem(TAG).group(PEDESTALS_TAB));
    }

    @Override
    public boolean hasContainerItem(ItemStack stack) {
        return true;
    }

    @Override
    public ItemStack getContainerItem(ItemStack itemStack) {
        return new ItemStack(this.getItem());
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World p_77659_1_, PlayerEntity p_77659_2_, Hand p_77659_3_) {
        String tags = p_77659_2_.getHeldItemOffhand().getItem().getTags().toString();
        if(p_77659_1_.isRemote)
        {
            if(!p_77659_2_.getHeldItemOffhand().isEmpty())
            {
                TranslationTextComponent output = new TranslationTextComponent("Tags: ");
                output.func_240702_b_(tags);
                output.func_240699_a_(TextFormatting.WHITE);
                p_77659_2_.sendMessage(output,p_77659_2_.getUniqueID());
            }
        }

        return super.onItemRightClick(p_77659_1_, p_77659_2_, p_77659_3_);
    }

    public static final Item TAG = new ItemTagTool().setRegistryName(new ResourceLocation(MODID, "tagtool"));

    @SubscribeEvent
    public static void onItemRegistryReady(RegistryEvent.Register<Item> event)
    {
        event.getRegistry().register(TAG);
    }




}