package com.leverx.leverxspringproj.config;

import com.sap.cloud.sdk.cloudplatform.security.AuthTokenFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration; 

import com.sap.cloud.sdk.cloudplatform.CloudPlatform;
import com.sap.cloud.sdk.cloudplatform.CloudPlatformAccessor;
import com.sap.cloud.sdk.cloudplatform.ScpCfCloudPlatform; 

@Configuration
public class CloudConfig {

	@Bean
	public CloudPlatform platform() {
		return CloudPlatformAccessor.getCloudPlatform();
	}

	@Bean
	public ScpCfCloudPlatform platformScp() {
		return ScpCfCloudPlatform.getInstanceOrThrow();
	}

	@Bean
	public AuthTokenFacade authtoken() {
		return new AuthTokenFacade();
	}
} 

