package com.pdamjanovic.config;

import java.net.InetAddress;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.pdamjanovic.repositories.elasticsearch")
@EnableJpaRepositories(basePackages = "com.pdamjanovic.repositories.jpa")
public class ApplicationConfiguration extends SpringBootServletInitializer {

	@Value("${elasticsearch.host}")
	private String еsHost;

	@Value("${elasticsearch.port}")
	private int еsPort;

	@Value("${elasticsearch.clustername}")
	private String еsClusterName;

	@Bean
	public Client client() throws Exception {

		Settings esSettings = Settings.settingsBuilder().put("cluster.name", еsClusterName).build();

		return TransportClient.builder().settings(esSettings).build()
				.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(еsHost), еsPort));
	}

	@Bean
	public ElasticsearchOperations elasticsearchTemplate() throws Exception {
		return new ElasticsearchTemplate(client());
	}

}
