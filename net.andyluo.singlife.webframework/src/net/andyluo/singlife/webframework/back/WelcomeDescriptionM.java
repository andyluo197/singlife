/**
 * File created on 2007-9-21
 */
package net.andyluo.singlife.webframework.back;

import java.util.ArrayList;

import net.andyluo.singlife.webframework.services.NavigationService;
import net.andyluo.singlife.webframework.services.WelcomeDescriptionService;

/**
 * @author Luo Ming
 *
 */
public class WelcomeDescriptionM 
{
	private static ArrayList<WelcomeDescriptionService> services = new ArrayList<WelcomeDescriptionService>();
	
	public void setWelcomeDescriptionService(WelcomeDescriptionService service)
	{
		services.add(service);
	}
	
	public void unsetWelcomeDescriptionService(WelcomeDescriptionService service)
	{
		services.remove(service);
	}
	
	public ArrayList<WelcomeDescriptionService> getWelcomeDescriptionServices()
	{
		return services;
	}
}
