package be.vdab.filters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet Filter implementation class RequestsFilter
 */
@WebFilter("*.htm")
public class RequestsFilter implements Filter {
	
	private static final String REQUESTSMAP = "requestsMap";
	private static final List<String> EXCLUDES = new ArrayList<>(Arrays.asList("png", "gif", "jpg", "ico", "css", "js"));

    /**
     * Default constructor. 
     */
    public RequestsFilter() {
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		if (request instanceof HttpServletRequest) {
    		HttpServletRequest httprequest = (HttpServletRequest) request;
    		
    		String URL = httprequest.getRequestURI();
    		
    		if (!EXCLUDES.contains(URL.substring(URL.length()-3, URL.length()).toLowerCase())) {
    			@SuppressWarnings("unchecked")
				ConcurrentHashMap<String, AtomicInteger> requestsMap = 
    					(ConcurrentHashMap<String, AtomicInteger>) request.getServletContext().getAttribute(REQUESTSMAP);
    			AtomicInteger counter = requestsMap.putIfAbsent(URL, new AtomicInteger(1));
    			if (counter != null) {
    				counter.incrementAndGet();
    			}
    		}
    	}

		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		fConfig.getServletContext().setAttribute(REQUESTSMAP, new ConcurrentHashMap<String, AtomicInteger>());
		
	}

}
