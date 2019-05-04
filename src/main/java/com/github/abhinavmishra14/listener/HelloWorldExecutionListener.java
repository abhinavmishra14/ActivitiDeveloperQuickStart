package com.github.abhinavmishra14.listener;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloWorldExecutionListener implements ExecutionListener {
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(HelloWorldExecutionListener.class);

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 8622151859987479246L;

	/* (non-Javadoc)
	 * @see org.activiti.engine.delegate.ExecutionListener#notify(org.activiti.engine.delegate.DelegateExecution)
	 */
	@Override
	public void notify(final DelegateExecution execution) {
		LOGGER.info("HelloWorldExecutionListener started..");
		execution.setVariable("name", "Abhinav");
		LOGGER.info("HelloWorldExecutionListener finished.");
	}
}
