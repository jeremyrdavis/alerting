package com.jboss.examples.drools.cep.alerts.tests;

import static org.junit.Assert.assertNotNull;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseConfiguration;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderError;
import org.drools.builder.KnowledgeBuilderErrors;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.conf.EventProcessingOption;
import org.drools.io.ResourceFactory;
import org.drools.logger.KnowledgeRuntimeLogger;
import org.drools.logger.KnowledgeRuntimeLoggerFactory;
import org.drools.runtime.StatefulKnowledgeSession;

public class BaseAlertTest {
	
	protected static String[] drlFiles;
	
	protected static String[] flowFiles;

	protected StatefulKnowledgeSession createKnowledgeSession()
			throws Exception {
		KnowledgeBase kbase = readKnowledgeBase();
		assertNotNull("KnowledgeBase should not be null", kbase);
		StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();
		assertNotNull("KnowledgeSession should not be null", ksession);
		KnowledgeRuntimeLogger logger = KnowledgeRuntimeLoggerFactory
				.newFileLogger(ksession, "testInValidPick");
		return ksession;
	}

	protected static KnowledgeBase readKnowledgeBase() throws Exception {
		KnowledgeBuilder kbuilder = KnowledgeBuilderFactory
				.newKnowledgeBuilder();
		
		// add .drl files
		for (int i = 0; i < drlFiles.length; i++) {
			kbuilder.add(ResourceFactory.newClassPathResource(drlFiles[i]),
					ResourceType.DRL);
		}
		
		// if we have ruleflow files add them
		if( flowFiles != null && 1 >= flowFiles.length){
			for( int i = 0; i < flowFiles.length; i++ )
				kbuilder.add(ResourceFactory.newClassPathResource(flowFiles[i]), ResourceType.DRF);
		}
		
		// check to see if we have any errors before continuing
		KnowledgeBuilderErrors errors = kbuilder.getErrors();
		if (errors.size() > 0) {
			for (KnowledgeBuilderError error : errors) {
				System.err.println(error);
			}
			throw new IllegalArgumentException("Could not parse knowledge.");
		}
		// set to streaming
		KnowledgeBaseConfiguration kbCfg = KnowledgeBaseFactory
				.newKnowledgeBaseConfiguration();
		kbCfg.setOption(EventProcessingOption.STREAM);

		// create the KnowledgeBase and add packages
		KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
		kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());

		return kbase;
	}

}
