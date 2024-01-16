package net.nicwatson.bresetenabler;

import net.minecraft.init.Blocks;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

@Mod(modid = ModBReset.MODID, name = ModBReset.MODNAME, version = ModBReset.VERSION, acceptableRemoteVersions = "*")
public class ModBReset
{
    public static final String MODID = "bresetenabler";
    public static final String MODNAME = "Command Block Server Reset Enabler";
    public static final String VERSION = "0.99_1.12";
    
    @Instance
    public static ModBReset instance = new ModBReset();
    
    public static Configuration config;
    
    
    private long startTime;			// Tracks time when server was started
    private long lockout;			// How long should security lockout on the command be?
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
    	config = new Configuration(event.getSuggestedConfigurationFile());
    	syncConfig();
    }
    
    public void syncConfig()
    {
    	lockout = 3600;
    	try
    	{
    		config.load();
    		Property lockoutTime = config.get(Configuration.CATEGORY_GENERAL, 
    				"lockoutTime", 
    				3600, 
    				"Time (in seconds) after server start before breset command is enabled", 
    				0, 
    				259200);
    		lockout = (long)(lockoutTime.getInt()) * 1000;
    	} catch (Exception e)
    	{
    	} finally
    	{
    		if(config.hasChanged())
    		{
    			config.save();
    		}
    	}
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    	
    }

    @EventHandler
    public void serverLoad(FMLServerStartingEvent event)
    {
    	this.startTime = System.currentTimeMillis();	// Record time that server started (used for abuse-prevention lockout)
    	event.registerServerCommand(new CommandUncheckedStop());	// Register new version of the stop command
    }
    
    // Accessor for startTime
    public long upSince()
    {
    	return this.startTime;
    }
    
    // Accessor for lockout
    public long getLockout()
    {
    	return this.lockout;
    }
    
    
}
