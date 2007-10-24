/**
 * File created on 2007-9-21
 */
package net.andyluo.singlife.webframework.back;

import java.util.ArrayList;

import net.andyluo.singlife.webframework.services.NavigationService;

/**
 * @author Luo Ming
 *
 */
public class NavigationM 
{
	private static ArrayList<NavigationService> services = new ArrayList<NavigationService>();
	
	public void setNavigationService(NavigationService service)
	{
		services.add(service);
	}
	
	public void unsetNavigationService(NavigationService service)
	{
		services.remove(service);
	}
	
	public ArrayList<NavigationService> getNavigationServices()
	{
		return services;
	}
}
