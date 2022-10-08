package start;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

/**
 * The one and only application bean.
 * 
 * @author Manfred Riem (mriem@manorrock.com)
 */
@ApplicationScoped
@Named("applicationBean")
public class ApplicationBean {
}
