package org.orchestra.connectors.tomcat;

import static org.mockito.Mockito.*;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

/**
 * 
 * @author jtoepfer
 */
public class TomcatManagerTest {
	
	private static final String username = "abc";
	
	private static final String password = "abc1";
	
	private TomcatManagerImpl tomcatManager;
	
	private ConfigurationService configurationService;
	
	@Before
	public void onSetup() {
		DefaultHttpClient httpClient = new DefaultHttpClient(new ThreadSafeClientConnManager());
		
		UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(username, password);
		AuthScope authScope = new AuthScope(AuthScope.ANY_HOST, AuthScope.ANY_PORT, AuthScope.ANY_REALM);
		httpClient.getCredentialsProvider().setCredentials(authScope, credentials);
		
		HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(httpClient);
		RestTemplate restTemplate = new RestTemplate(factory);
		
		tomcatManager = new TomcatManagerImpl();
		configurationService = Mockito.mock(ConfigurationService.class);
		
		ReflectionTestUtils.setField(tomcatManager, "restTemplate", restTemplate);
		ReflectionTestUtils.setField(tomcatManager, "configurationService", configurationService);
	}

	@Test
	public void testFetchAListOfApplications() {
		
		when(configurationService.getServiceUri()).thenReturn("http://localhost:8080/manager/text/list");
		
		tomcatManager.getListOfInstalledApps();
	}
}
