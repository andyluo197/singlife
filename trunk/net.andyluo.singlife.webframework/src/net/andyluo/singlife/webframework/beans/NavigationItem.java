/**
 * File created on 2007-9-20
 */
package net.andyluo.singlife.webframework.beans;

/**
 * @author Luo Ming
 *
 */
public class NavigationItem {
	/**
	 * display title
	 */
	private String title;
	/**
	 * link location
	 */
	private String location;
	
	public NavigationItem(String title, String location) {
		super();
		this.title = title;
		this.location = location;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
}
