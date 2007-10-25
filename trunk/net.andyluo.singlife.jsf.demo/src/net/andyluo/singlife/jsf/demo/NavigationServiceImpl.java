/**
 * File created on 2007-9-21
 */
package net.andyluo.singlife.jsf.demo;

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
		items.add(new NavigationItem("JSF Demo", "/net.andyluo.singlife.jsf.demo/index.jsf"));
		return items;
	}

	public String getTitle() {
		return "JSF Demo";
	}
}
