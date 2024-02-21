package sklepanjeTesti.config;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:Config/${env}.properties"})
public interface EnvironmentConfig extends Config {

	String baseURL();
	
	@Config.Key("itriglav.email")
	String iTriglavEmail();
	
	@Config.Key("itriglav.password")
	String iTriglavPass();
	
	String jwtToken();
}
