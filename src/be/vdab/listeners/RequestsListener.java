package be.vdab.listeners;

//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

/**
 * REPLACED BY REQUESTFILTER
 * 
 */
public class RequestsListener implements ServletContextListener, ServletRequestListener {
	
//	private static final String REQUESTSMAP = "requestsMap";
//	private static final List<String> EXCLUDES = new ArrayList<>(Arrays.asList("png", "gif", "jpg", "ico", "css", "js"));

	/**
     * @see ServletRequestListener#requestInitialized(ServletRequestEvent)
     */
    public void requestInitialized(ServletRequestEvent sre)  { 
    	
//    	if (sre.getServletRequest() instanceof HttpServletRequest) {
//    		HttpServletRequest request = (HttpServletRequest) sre.getServletRequest();
//    		
//    		String URL = request.getRequestURI();
//    		
//    		if (!EXCLUDES.contains(URL.substring(URL.length()-3, URL.length()).toLowerCase())) {
//    			@SuppressWarnings("unchecked")
//				ConcurrentHashMap<String, AtomicInteger> requestsMap = 
//    					(ConcurrentHashMap<String, AtomicInteger>) sre.getServletContext().getAttribute(REQUESTSMAP);
//    			AtomicInteger counter = requestsMap.putIfAbsent(URL, new AtomicInteger(1));
//    			if (counter != null) {
//    				counter.incrementAndGet();
//    			}
//    		}
//    	}
    }

	@Override
	public void requestDestroyed(ServletRequestEvent sre) {
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
//		sce.getServletContext().setAttribute(REQUESTSMAP, new ConcurrentHashMap<String, AtomicInteger>());
	}

	
}
