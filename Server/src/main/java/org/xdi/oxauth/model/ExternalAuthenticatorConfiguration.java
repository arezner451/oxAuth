package org.xdi.oxauth.model;

import java.util.Map;

import org.xdi.model.SimpleCustomProperty;
import org.xdi.model.config.CustomAuthenticationConfiguration;
import org.xdi.oxauth.service.python.interfaces.ExternalAuthenticatorType;

/**
 * External authenticator configuration
 *
 * @author Yuriy Movchan Date: 01.24.2013
 */
public class ExternalAuthenticatorConfiguration {
	private CustomAuthenticationConfiguration customAuthenticationConfiguration;
	private ExternalAuthenticatorType externalAuthenticatorType;
	private Map<String, SimpleCustomProperty> configurationAttributes;

	public ExternalAuthenticatorConfiguration(
			CustomAuthenticationConfiguration customAuthenticationConfiguration,
			ExternalAuthenticatorType externalAuthenticatorType, Map<String, SimpleCustomProperty> configurationAttributes) {
		this.customAuthenticationConfiguration = customAuthenticationConfiguration;
		this.externalAuthenticatorType = externalAuthenticatorType;
		this.configurationAttributes = configurationAttributes;
	}

	public String getName() {
		return customAuthenticationConfiguration.getName();
	}

	public int getLevel() {
		return customAuthenticationConfiguration.getLevel();
	}

	public int getPriority() {
		return customAuthenticationConfiguration.getPriority();
	}

	public CustomAuthenticationConfiguration getCustomAuthenticationConfiguration() {
		return customAuthenticationConfiguration;
	}

	public ExternalAuthenticatorType getExternalAuthenticatorType() {
		return externalAuthenticatorType;
	}

	public Map<String, SimpleCustomProperty> getConfigurationAttributes() {
		return configurationAttributes;
	}

}