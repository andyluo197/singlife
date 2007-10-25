package net.andyluo.singlife.jsf.demo;

import java.io.IOException;
import java.util.Dictionary;
import java.util.Hashtable;

import javax.faces.webapp.FacesServlet;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.eclipse.equinox.http.helper.BundleEntryHttpContext;
import org.eclipse.equinox.http.helper.ContextPathServletAdaptor;
import org.eclipse.equinox.jsp.jasper.JspServlet;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.http.HttpContext;
import org.osgi.service.http.HttpService;
import org.osgi.util.tracker.ServiceTracker;

import com.sun.faces.config.ConfigureListener;

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

		private static final String PATH = "/net.andyluo.singlife.jsf.demo";

		public HttpServiceTracker(BundleContext context) {
			super(context, HttpService.class.getName(), null);
		}

		public Object addingService(ServiceReference reference) {
			final HttpService httpService = (HttpService) context
					.getService(reference);
			try {
				HttpContext commonContext = new BundleEntryHttpContext(context
						.getBundle(), "/pages");
				httpService.registerResources(PATH, "/", commonContext);

				JspServlet jspServlet = new JspServlet(context.getBundle(),
						"/pages");
				Servlet adaptedJspServlet = new ContextPathServletAdaptor(
						jspServlet, PATH);
				httpService.registerServlet(PATH + "/*.jsp", adaptedJspServlet,
						null, commonContext);

				Dictionary initparams = new Hashtable();
				initparams.put("servlet-name", "Faces Servlet");
				Servlet adaptedFacesServlet = new ServletContextListenerServletAdaptor(
						new ConfigureListener(), new FacesServlet(), jspServlet
								.getJspLoader());
				adaptedFacesServlet = new ContextPathServletAdaptor(
						adaptedFacesServlet, PATH);
				httpService.registerServlet(PATH + "/*.jsf",
						adaptedFacesServlet, initparams, commonContext);

			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return httpService;
		}

		public void removedService(ServiceReference reference, Object service) {
			final HttpService httpService = (HttpService) service;
			httpService.unregister(PATH); //$NON-NLS-1$
			httpService.unregister(PATH + "/*.jsp"); //$NON-NLS-1$
			httpService.unregister(PATH + "/*.jsf"); //$NON-NLS-1$			
			super.removedService(reference, service);
		}
	}

	public class ServletContextListenerServletAdaptor implements Servlet {
		private ServletConfig config;
		private ServletContextListener listener;
		private Servlet delegate;
		private ClassLoader jspLoader;

		public ServletContextListenerServletAdaptor(
				ServletContextListener listener, Servlet delegate,
				ClassLoader jspLoader) {
			this.listener = listener;
			this.delegate = delegate;
			this.jspLoader = jspLoader;
		}

		public void init(ServletConfig config) throws ServletException {
			this.config = config;
			ClassLoader original = Thread.currentThread()
					.getContextClassLoader();
			try {
				Thread.currentThread().setContextClassLoader(jspLoader);
				listener.contextInitialized(new ServletContextEvent(config
						.getServletContext()));
				delegate.init(config);
			} finally {
				Thread.currentThread().setContextClassLoader(original);
			}
		}

		public void service(ServletRequest req, ServletResponse resp)
				throws ServletException, IOException {
			ClassLoader original = Thread.currentThread()
					.getContextClassLoader();
			try {
				Thread.currentThread().setContextClassLoader(jspLoader);
				delegate.service(req, resp);
			} finally {
				Thread.currentThread().setContextClassLoader(original);
			}
		}

		public void destroy() {
			ClassLoader original = Thread.currentThread()
					.getContextClassLoader();
			try {
				Thread.currentThread().setContextClassLoader(jspLoader);
				delegate.destroy();
				listener.contextDestroyed(new ServletContextEvent(config
						.getServletContext()));
				config = null;
			} finally {
				Thread.currentThread().setContextClassLoader(original);
			}
		}

		public ServletConfig getServletConfig() {
			return config;
		}

		public String getServletInfo() {
			return "";
		}
	}

}