package me.wezzyFTW;

import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class cheeseListener implements Listener  {

	Cheese cheese;
	public cheeseListener(Cheese plugin) {
		cheese = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onCraft(CraftItemEvent ev)
	{
		Player p = (Player) ev.getWhoClicked();
		if (!p.hasPermission("cheese.craft") && ev.getCurrentItem().getType() == Material.SPONGE && ev.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "Cheese"))
		{
			ev.setCancelled(true);
			p.sendMessage("You don't have permission to craft " + ChatColor.YELLOW + "cheese");
		}
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent ev)
	{
		Player p = ev.getPlayer();
		Action a = ev.getAction();

		//Eating the cheese
		if (p.getItemInHand().getType() == Material.SPONGE&& p.getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "Cheese"))
		{
			if (a == Action.RIGHT_CLICK_AIR || a == Action.RIGHT_CLICK_BLOCK)
			{
				if (p.getFoodLevel() < 20)
				{			
					p.setFoodLevel(p.getFoodLevel() + 3);
					p.getItemInHand().setAmount(p.getItemInHand().getAmount() - 1);
					p.getWorld().playEffect(p.getLocation(), Effect.STEP_SOUND, Material.SPONGE);
					p.getWorld().playSound(p.getLocation(), Sound.EAT, 10, 1);
				}	
			}
		}
	}
	
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent ev) {
		Player p = ev.getPlayer();
		if (p.getItemInHand().getType() == Material.SPONGE && p.getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "Cheese"))
		{
			ev.setCancelled(true);
			p.sendMessage("You cannot place " + ChatColor.YELLOW + "Cheese");
		}
	}
}
