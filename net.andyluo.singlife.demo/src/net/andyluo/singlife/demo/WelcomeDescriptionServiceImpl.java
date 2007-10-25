/**
 * File created on 2007-9-24
 */
package net.andyluo.singlife.demo;

import net.andyluo.singlife.webframework.services.WelcomeDescriptionService;

/**
 * @author Luo Ming
 *
 */
public class WelcomeDescriptionServiceImpl implements WelcomeDescriptionService {

	public String getTitle() {
		return "Demo Component";
	}
	public String getSummary() {
		return "This is a demo OSGi component";
	}
	public String getDescription() {
		return "This component implements WelcomeDescriptionService to provide these messages.";
	}

}
