/**
 * File created on 2007-9-20
 */
package net.andyluo.singlife.webframework.services;

import java.util.ArrayList;

import net.andyluo.singlife.webframework.beans.NavigationItem;

/**
 * Service interface for navigation place.
 * @author Luo Ming
 *
 */
public interface NavigationService {
	/**
	 * Get navigation items' title and their link value pairs.
	 * @return title-linkLocation pairs
	 */
	ArrayList<NavigationItem> getNavigationItems();
	
	/**
	 * Get the display title of the service.
	 * @return title of service
	 */
	String getTitle();
}
