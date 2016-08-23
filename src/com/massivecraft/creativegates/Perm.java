package com.massivecraft.creativegates;

import org.bukkit.permissions.Permissible;

import com.massivecraft.massivecore.Identified;
import com.massivecraft.massivecore.util.PermissionUtil;

public enum Perm implements Identified
{
	// -------------------------------------------- //
	// ENUM
	// -------------------------------------------- //
	
<<<<<<< HEAD
	CREATE("create"),
	USE("use"),
	CG("cg"),
	CG_WORLD("cg.world"),
	CG_WORLD_LIST("cg.world.list"),
	CG_WORLD_DELETE("cg.world.delete"),
	CG_VERSION("cg.version"),
        LIMITED("limited"),
        RANDOM("random"),
=======
	CREATE,
	USE,
	CG,
	CG_WORLD,
	CG_WORLD_LIST,
	CG_WORLD_DELETE,
	CG_VERSION,
>>>>>>> upstream/master
	
	// END OF LIST
	;
	
	// -------------------------------------------- //
	// FIELDS
	// -------------------------------------------- //
	
	private final String id;
	@Override public String getId() { return this.id; }
	
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	Perm()
	{
		this.id = PermissionUtil.createPermissionId(CreativeGates.get(), this);
	}
	
	// -------------------------------------------- //
	// HAS
	// -------------------------------------------- //
	
<<<<<<< HEAD
	public boolean has(CommandSender sender, boolean informSenderIfNot)
	{  
		return PermUtil.has(sender, this.node, informSenderIfNot);
=======
	public boolean has(Permissible permissible, boolean verboose)
	{
		return PermissionUtil.hasPermission(permissible, this, verboose);
>>>>>>> upstream/master
	}
	
	public boolean has(Permissible permissible)
	{
		return PermissionUtil.hasPermission(permissible, this);
	}
	
}
