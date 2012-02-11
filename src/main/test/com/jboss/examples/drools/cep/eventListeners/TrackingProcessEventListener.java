package com.jboss.examples.drools.cep.eventListeners;

import java.util.ArrayList;
import java.util.List;

import org.drools.event.process.DefaultProcessEventListener;
import org.drools.event.process.ProcessCompletedEvent;
import org.drools.event.process.ProcessNodeTriggeredEvent;

public class TrackingProcessEventListener extends DefaultProcessEventListener {

	List<String> rulesFiredList = new ArrayList<String>();
	List<String> nodesTriggered = new ArrayList<String>();
	
	@Override
	public void beforeNodeTriggered(ProcessNodeTriggeredEvent event) {
//		System.out.println( "beforeNodeTriggered : " + event.getNodeInstance().getNodeName() );
	}
	
	@Override
	public void afterNodeTriggered(ProcessNodeTriggeredEvent event) {
		nodesTriggered.add(event.getNodeInstance().getNodeName());
		System.out.println( "Node Triggered : " + event.getNodeInstance().getNodeName() );
	};
	
	@Override
	public void afterProcessCompleted(ProcessCompletedEvent event) {
		System.out.println("Process completed");
	}

	/**
	 * Returns true if the rule matching ruleName arg has been fired.
	 * 
	 * @param ruleName String identifer of the rule in question.
	 * @return true if the rule matching the ruleName arg has been fired.
	 */
	public boolean isRuleFired(String ruleName) {
		for (String firedRuleName : rulesFiredList) {
			if (firedRuleName.equals(ruleName)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Clears out the List of fired rules and triggered nodes;
	 */
	public void reset(){
		this.rulesFiredList.clear();
	}

	/**
	 * 
	 * @param nodeName String name of the node to lookup.
	 * @return true if the nodeName arg is stored in the member variable.
	 */
	public boolean isNodeTriggered(String nodeName) {
		return nodesTriggered.contains(nodeName);
	}
}
