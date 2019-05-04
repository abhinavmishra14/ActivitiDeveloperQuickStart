package com.github.abhinavmishra14.app;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class HelloWorldRequest.
 */
public class HelloWorldRequest {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(HelloWorldRequest.class);


	/**
	 * The main method.
	 *
	 * @param args the args
	 */
	public static void main(String[] args) {
		final ProcessEngineConfiguration cfg = new StandaloneProcessEngineConfiguration()
		.setJdbcUrl("jdbc:h2:mem:activiti;DB_CLOSE_DELAY=1000")
		.setJdbcUsername("sa")
		.setJdbcPassword("")
		.setJdbcDriver("org.h2.Driver")
		.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
		
		final ProcessEngine processEngine = cfg.buildProcessEngine();
		final String processName = processEngine.getName();
		final String version = ProcessEngine.VERSION;
		LOGGER.info("ProcessEngine [" + processName + "] Version: [" + version + "]");

		final RepositoryService repositoryService = processEngine.getRepositoryService();
		final Deployment deployment = repositoryService.createDeployment()
				.addClasspathResource("helloWorld.bpmn").deploy();
		final ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
				.deploymentId(deployment.getId()).singleResult();
		LOGGER.info("Found process definition ["+ processDefinition.getName() + "] with id ["+ processDefinition.getId() + "]");

		final RuntimeService runtimeService = processEngine.getRuntimeService();
		final ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("helloWorld");
		LOGGER.info("Onboarding process started with process instance id [" 
				+ processInstance.getProcessInstanceId() + "] key [" + processInstance.getProcessDefinitionKey() + "]");
	}
}
