/**
 * File created on 2007-9-19
 */
package net.andyluo.singlife.webframework;

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
				httpService.registerResources("/fw", "/", commonContext); 

				Servlet adaptedJspServlet = new ContextPathServletAdaptor(new JspServlet(context.getBundle(), "/web"), "/fw");
				httpService.registerServlet("/fw/*.jsp", adaptedJspServlet, null, commonContext);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return httpService;
		}

		public void removedService(ServiceReference reference, Object service) {
			final HttpService httpService = (HttpService) service;
			httpService.unregister("/fw");
			httpService.unregister("/fw/*.jsp");
			super.removedService(reference, service);
		}			
	}

}
