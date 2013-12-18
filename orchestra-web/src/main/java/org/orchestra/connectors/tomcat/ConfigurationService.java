package org.orchestra.connectors.tomcat;

public interface ConfigurationService {

	/**
	 * This is the fully qualifed node URI
	 * @return
	 */
	String getNodeUri();
 
	boolean isSSLEnabled();

}
