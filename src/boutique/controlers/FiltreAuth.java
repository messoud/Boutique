package boutique.controlers;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class FiltreAuth
 */
@WebFilter(filterName="/FiltreAuth",urlPatterns={"/espacePrive/*", "/mesServlets/*"}, dispatcherTypes = {DispatcherType.REQUEST, DispatcherType.FORWARD})
public class FiltreAuth implements Filter {

	 public static final String ACCES_PUBLIC     = "/index.jsp";
	    public static final String ATT_SESSION_USER = "sessionUtilisateur";

	    public void init( FilterConfig config ) throws ServletException {
	    }

	    public void doFilter( ServletRequest req, ServletResponse res, FilterChain chain ) throws IOException,
	            ServletException {
	        /* Cast des objets request et response */
	        HttpServletRequest request = (HttpServletRequest) req;
	        HttpServletResponse response = (HttpServletResponse) res;

	        /* Récupération de la session depuis la requête */
	        HttpSession session = request.getSession(false);

	        /**
	         * Si l'objet utilisateur n'existe pas dans la session en cours, alors
	         * l'utilisateur n'est pas connecté.
	         */
	        if ( (session==null) || (session.getAttribute( ATT_SESSION_USER ) == null )) {
	            /* Redirection vers la page publique */
	            response.sendRedirect( request.getContextPath() + ACCES_PUBLIC );
	        } else {
	            /* Affichage de la page restreinte */
	            chain.doFilter( request, response );
	        }
	    }

	    public void destroy() {
	    }

}
