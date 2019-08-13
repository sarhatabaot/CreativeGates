
package com.massivecraft.creativegates;

import com.massivecraft.creativegates.cmd.CmdCg;
import com.massivecraft.creativegates.cmd.type.TypePermissionDefault;
import com.massivecraft.creativegates.entity.MConf;
import com.massivecraft.creativegates.entity.MConfColl;
import com.massivecraft.creativegates.entity.UConfColl;
import com.massivecraft.creativegates.entity.UGateColls;
import com.massivecraft.creativegates.index.IndexCombined;
import com.massivecraft.massivecore.Aspect;
import com.massivecraft.massivecore.AspectColl;
import com.massivecraft.massivecore.MassivePlugin;
import com.massivecraft.massivecore.Multiverse;
import net.milkbowl.vault.permission.Permission;
import com.massivecraft.massivecore.command.type.RegistryType;
																				   
import com.massivecraft.massivecore.command.type.enumeration.TypePermissionDefault;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.permissions.PermissionDefault;

import java.util.EnumSet;
import java.util.Set;

public class CreativeGates extends MassivePlugin
{
	// -------------------------------------------- //
	// CONSTANTS
	// -------------------------------------------- //
	
	public final static Set<Material> VOID_MATERIALS = EnumSet.of(Material.AIR); 
	
	// -------------------------------------------- //
	// INSTANCE & CONSTRUCT
	// -------------------------------------------- //
	
	private static CreativeGates i;
	public static CreativeGates get() { return i; }
	public CreativeGates()
	{
		CreativeGates.i = this;
		this.setVersionSynchronized(false);
	}
  
						 
									 
  
	
	// -------------------------------------------- //
	// FIELDS
	// -------------------------------------------- //
	
	/*// Aspects
	private Aspect aspect;
	public Aspect getAspect() { return this.aspect; }
	public Multiverse getMultiverse() { return this.getAspect().getMultiverse(); }
	Permission permission = null;
	private Player player;*/
	// Index
	private final IndexCombined index = new IndexCombined();
	public IndexCombined getIndex() { return this.index; };
	
	// Filling
	private boolean filling = false;
	public boolean isFilling() { return this.filling; }
	public void setFilling(boolean filling) { this.filling = filling; }
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public void onEnableInner()
	{
		setupPermissions();
		/*// Initialize Aspects
		this.aspect = AspectColl.get().get(Const.ASPECT, true);
		this.aspect.register();
		this.aspect.setDesc(
			"<i>What gates do exist.",
			"<i>What the config options are set to."
		);*/

		// Index
		this.getIndex().clear();
		
		// types
		RegistryType.register(PermissionDefault.class, TypePermissionDefault.get());
		
		// Activate
		this.activate(
			// Coll
			MConfColl.class,
			UConfColls.class,
			UGateColl.class,
		
			// Engine
			EngineMain.class,
			
			// Command
			CmdCg.class
		);
	
		// Schedule a permission update.
		// Possibly it will be useful due to the way Bukkit loads permissions.
		Bukkit.getScheduler().scheduleSyncDelayedTask(this, () -> MConf.get().updatePerms());
		/*Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
			@Override
			public void run()
			{
				MConf.get().updatePerms();
			}
		});*/
	}
	
	@Override
	public void onDisable()
	{
		this.getIndex().clear();
		super.onDisable();
	}
	
	// -------------------------------------------- //
	// UTIL
	// -------------------------------------------- //
	
	public static boolean isVoid(Material material)
	{
		return VOID_MATERIALS.contains(material);
	}
	
	public static boolean isVoid(Block block)
	{
		return isVoid(block.getType());
	}
	private boolean setupPermissions()
	{
		RegisteredServiceProvider<Permission> permissionProvider = getServer()
		.getServicesManager().getRegistration(net.milkbowl.vault.permission.Permission.class);
		if (permissionProvider != null)
		{
			permission = permissionProvider.getProvider();
		}
		return (permission != null);
	}
	public String getPrimary(Player player)
	{
		return permission.getPrimaryGroup(player);
	}
}