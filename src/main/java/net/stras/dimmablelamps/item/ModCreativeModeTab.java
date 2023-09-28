package net.stras.dimmablelamps.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.stras.lampmod.block.ModBlocks;

public class ModCreativeModeTab {
    public static final CreativeModeTab DIMMABLE_LAMPS = new CreativeModeTab("dimmablelamps") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModBlocks.LAMP_BLOCK.get());
        }
    };
}
