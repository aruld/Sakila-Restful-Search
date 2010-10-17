package client;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.lifecycle.SingletonResourceProvider;
import org.junit.Before;
import resources.SakilaResource;

/**
 * User: aruld
 * Date: 10/16/10
 * Time: 9:53 PM
 */
public class SakilaSearchServer {

    public static void main(String[] args) {
        JAXRSServerFactoryBean sf = new JAXRSServerFactoryBean();
        sf.setResourceClasses(SakilaResource.class);
        sf.getInInterceptors().add(new LoggingInInterceptor());
        sf.getOutInterceptors().add(new LoggingOutInterceptor());
        sf.setResourceProvider(SakilaResource.class, new SingletonResourceProvider(new SakilaResource()));
        sf.setAddress("http://localhost:9000");
        sf.create();
        System.out.println("Rest Server started @ " + sf.getAddress());
    }
}
