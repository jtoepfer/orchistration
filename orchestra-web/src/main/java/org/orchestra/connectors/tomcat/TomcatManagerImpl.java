package org.orchestra.connectors.tomcat;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * 
 * @author jtoepfer
 *
 */
public class TomcatManagerImpl implements TomcatManager {
	
	private Log log = LogFactory.getLog(getClass());

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private ConfigurationService configurationService;
	
	
	public void getServerInfo() {
		
	}
	
	public void getListOfInstalledApps() {
		
		String protocol = "http";
		if ( configurationService.isSSLEnabled() ) {
			protocol = "https";
		}
		
		StringBuilder sb = new StringBuilder(protocol)
			.append("://").append(configurationService.getNodeUri())
			.append("/manager/text/list");
		
		final String uri = sb.toString();
		ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class, new Object[] {});
		String body = response.getBody();
		if ( log.isDebugEnabled() ) {
			log.debug("Uri: " + uri + " Response: " + body);
		}
		
	}
}
