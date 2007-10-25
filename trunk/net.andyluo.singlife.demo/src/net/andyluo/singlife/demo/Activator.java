/**
 * File created on 2007-9-21
 */
package net.andyluo.singlife.demo;


import javax.servlet.Servlet;

import org.eclipse.equinox.http.helper.BundleEntryHttpContext;
import org.eclipse.equinox.http.helper.ContextPathServletAdaptor;
import org.eclipse.equinox.jsp.jasper.JspServlet;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.http.HttpContext;
import org.osgi.service.http.HttpService;
import org.osgi.util.tracker.ServiceTracker;

/**
 * @author Luo Ming
 *
 */
public class Activator implements BundleActivator {

	private ServiceTracker httpServiceTracker;

	public void start(BundleContext context) throws Exception {
		httpServiceTracker = new HttpServiceTracker(context);
		httpServiceTracker.open();
	}

	public void stop(BundleContext context) throws Exception {
		httpServiceTracker.close();
	}

	private class HttpServiceTracker extends ServiceTracker {

		public HttpServiceTracker(BundleContext context) {
			super(context, HttpService.class.getName(), null);
		}

		public Object addingService(ServiceReference reference) {
			final HttpService httpService = (HttpService) context.getService(reference);
			try {
				HttpContext commonContext = new BundleEntryHttpContext(context.getBundle(), "/web"); 
				httpService.registerResources("/net.andyluo.singlife.demo", "/", commonContext); 

				Servlet adaptedJspServlet = new ContextPathServletAdaptor(new JspServlet(context.getBundle(), "/web"), "/net.andyluo.singlife.demo");
				httpService.registerServlet("/net.andyluo.singlife.demo/*.jsp", adaptedJspServlet, null, commonContext);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return httpService;
		}

		public void removedService(ServiceReference reference, Object service) {
			final HttpService httpService = (HttpService) service;
			httpService.unregister("/net.andyluo.singlife.demo");
			httpService.unregister("/net.andyluo.singlife.demo/*.jsp");
			super.removedService(reference, service);
		}			
	}

}
