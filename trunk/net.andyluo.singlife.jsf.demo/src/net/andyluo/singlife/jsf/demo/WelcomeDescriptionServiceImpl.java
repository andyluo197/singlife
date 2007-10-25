/**
 * File created on 2007-9-24
 */
package net.andyluo.singlife.jsf.demo;

import net.andyluo.singlife.webframework.services.WelcomeDescriptionService;

/**
 * @author Luo Ming
 *
 */
public class WelcomeDescriptionServiceImpl implements WelcomeDescriptionService {

	public String getTitle() {
		return "JSF Demo Component";
	}
	public String getSummary() {
		return "This is a demo for JSF development";
	}
	public String getDescription() {
		return "JSF development in OSGi is easy with a minor hack of JspServlet.";
	}

}
