package trinsdar.ic2c_extras.blocks.tileentity;

import ic2.api.classic.recipe.machine.IMachineRecipeList;
import ic2.api.classic.tile.MachineType;
import ic2.api.recipe.IRecipeInput;
import ic2.core.block.base.tile.TileEntityBasicElectricMachine;
import ic2.core.inventory.gui.GuiComponentContainer;
import ic2.core.item.recipe.entry.RecipeInputItemStack;
import ic2.core.item.recipe.entry.RecipeInputOreDict;
import ic2.core.platform.lang.components.base.LocaleComp;
import ic2.core.platform.registry.Ic2Sounds;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import trinsdar.ic2c_extras.util.references.Ic2cExtrasResourceLocations;
import trinsdar.ic2c_extras.util.references.Ic2cExtrasLang;

import static trinsdar.ic2c_extras.util.Ic2cExtrasRecipes.extruding;

public class TileEntityExtruder extends TileEntityBasicElectricMachine {
    public TileEntityExtruder(){
        super(3, 5, 400, 32);
    }

    public MachineType getType() {
        return MachineType.macerator;
    }

    public LocaleComp getBlockName()
    {
        return Ic2cExtrasLang.extruder;
    }

    @Override
    public IMachineRecipeList.RecipeEntry getOutputFor(ItemStack input) {
        return extruding.getRecipeInAndOutput(input, false);
    }

    @Override
    public ResourceLocation getGuiTexture() {
        return Ic2cExtrasResourceLocations.extruder;
    }

    public Class<? extends GuiScreen> getGuiClass(EntityPlayer player)
    {
        return GuiComponentContainer.class;
    }

    public ResourceLocation getStartSoundFile() {
        return Ic2Sounds.compressorOp;
    }

    public ResourceLocation getInterruptSoundFile() {
        return Ic2Sounds.interruptingSound;
    }

    public double getWrenchDropRate() {
        return 0.8500000238418579D;
    }

    public boolean isValidInput(ItemStack par1) {
        if (par1 == null) {
            return false;
        } else {
            return extruding.getRecipeInAndOutput(par1, true) != null && super.isValidInput(par1);
        }
    }

    int index = 0;
    public static IMachineRecipeList[] recipeList;
//    public IMachineRecipeList.RecipeEntry getRecipeForStack(ItemStack input)
//    {
//        return recipeList[index].getRecipe(input);
//    }

    @Override
    public IMachineRecipeList getRecipeList() {
        return extruding;
    }

    public static void addRecipe(ItemStack input, ItemStack output) {
        addRecipe((new RecipeInputItemStack(input)), output);
    }

    public static void addRecipe(ItemStack input, int stacksize, ItemStack output) {
        addRecipe((new RecipeInputItemStack(input, stacksize)), output);
    }

    public static void addRecipe(String input, int stacksize, ItemStack output) {
        addRecipe((new RecipeInputOreDict(input, stacksize)), output);
    }

    public static void addRecipe(ItemStack input, ItemStack output, float exp) {
        addRecipe((new RecipeInputItemStack(input)), output, exp);
    }

    public static void addRecipe(ItemStack input, int stacksize, ItemStack output, float exp) {
        addRecipe((new RecipeInputItemStack(input, stacksize)), output, exp);
    }

    public static void addRecipe(String input, int stacksize, ItemStack output, float exp) {
        addRecipe((new RecipeInputOreDict(input, stacksize)), output, exp);
    }

    public static void addRecipe(IRecipeInput input, ItemStack output) {
        addRecipe(input, output, 0.0F);
    }

    public static void addRecipe(IRecipeInput input, ItemStack output, float exp) {
        extruding.addRecipe(input, output, exp, output.getDisplayName());
    }
}