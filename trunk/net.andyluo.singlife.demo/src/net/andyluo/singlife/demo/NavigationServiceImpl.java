/**
 * File created on 2007-9-21
 */
package net.andyluo.singlife.demo;

import java.util.ArrayList;

import net.andyluo.singlife.webframework.beans.NavigationItem;
import net.andyluo.singlife.webframework.services.NavigationService;

/**
 * @author Luo Ming
 *
 */
public class NavigationServiceImpl implements NavigationService 
{

	public ArrayList<NavigationItem> getNavigationItems() {
		ArrayList<NavigationItem> items = new ArrayList<NavigationItem>();
		items.add(new NavigationItem("DemoService", "/net.andyluo.singlife.demo/DemoService.jsp"));
		items.add(new NavigationItem("DemoService2", "/net.andyluo.singlife.demo/DemoService2.jsp"));
		return items;
	}

	public String getTitle() {
		return "SingLife Demo";
	}
}
