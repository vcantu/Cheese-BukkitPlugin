package me.wezzyFTW;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Cheese extends JavaPlugin{

	public Permission command = new Permission("cheese.command");
	public Permission craft = new Permission("cheese.craft");
	
	@Override
	public void onEnable() {
		new cheeseListener(this);
		
		//Recipe add
		ItemStack cheese = new ItemStack(Material.SPONGE, 1);
		ItemMeta cheeseMeta = cheese.getItemMeta();
		cheeseMeta.setDisplayName(ChatColor.YELLOW + "Cheese");
		cheese.setItemMeta(cheeseMeta);
		//
		ShapelessRecipe R = new ShapelessRecipe(cheese);
		R.addIngredient(2, Material.MILK_BUCKET);
		//
		this.getServer().addRecipe(R);
		//Recipe Add

		//Permission
		PluginManager pm = getServer().getPluginManager();
		pm.addPermission(command);
		pm.addPermission(craft);
	}
	
	@Override
	public void onDisable() {
		
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player)
		{
			Player p = (Player)sender;
			if (cmd.getName().equalsIgnoreCase("cheese"))
			{
				if (p.hasPermission("cheese.command"))
				{
					int amt = 1;
					try {
						amt = Integer.parseInt(args[0]);
					} catch (Exception e){
					}
					
					ItemStack cheese = new ItemStack(Material.SPONGE, amt);
					ItemMeta cheeseMeta = cheese.getItemMeta();
					cheeseMeta.setDisplayName(ChatColor.YELLOW + "Cheese");
					cheese.setItemMeta(cheeseMeta);
					
					p.getInventory().addItem(cheese);
					return true;
				}
			}
		}
		return false;
	}
}
