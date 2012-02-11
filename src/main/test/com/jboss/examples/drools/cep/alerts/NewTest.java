package com.jboss.examples.drools.cep.alerts;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.drools.ClockType;
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
import org.drools.runtime.KnowledgeSessionConfiguration;
import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.conf.ClockTypeOption;
import org.drools.time.SessionPseudoClock;

import com.jboss.examples.drools.cep.alerts.model.AlertStatus;
import com.jboss.examples.drools.cep.alerts.model.SystemAlert;
import com.jboss.examples.drools.cep.alerts.services.LoggerService;

public class NewTest {

    public static final void main(String[] args) {
        KnowledgeRuntimeLogger logger = null;
        try {
            // load up the knowledge base
            KnowledgeBase kbase = readKnowledgeBase();
            KnowledgeSessionConfiguration conf = KnowledgeBaseFactory.newKnowledgeSessionConfiguration();
            conf.setOption( ClockTypeOption.get( ClockType.PSEUDO_CLOCK.getId() ) );
            final StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession( conf, null );
            SessionPseudoClock clock = ksession.getSessionClock();
            ksession.setGlobal( "logger", new LoggerService( clock ) );
            logger = KnowledgeRuntimeLoggerFactory.newFileLogger(ksession, "test");
            // go !
            
            long currentTime = new Date().getTime();
            clock.advanceTime( currentTime, TimeUnit.MILLISECONDS ); // set clock to current timestamp
            
            SystemAlert[] sa = new SystemAlert[] {
                new SystemAlert( "1", new Date( currentTime + 10000 ), "device1", "interface1", AlertStatus.ACTIVE ),
                new SystemAlert( "2", new Date( currentTime + 50000 ), "device1", "interface1", AlertStatus.ACTIVE ),
                new SystemAlert( "3", new Date( currentTime + 90000 ), "device1", "interface1", AlertStatus.ACTIVE ),
                new SystemAlert( "4", new Date( currentTime + 150000 ), "device1", "interface1", AlertStatus.ACTIVE ),
                new SystemAlert( "5", new Date( currentTime + 170000 ), "device1", "interface1", AlertStatus.ACTIVE )
            };
            
            for( int i = 0; i < sa.length; i++ ) {
                clock.advanceTime( sa[i].getTime().getTime() - clock.getCurrentTime(), TimeUnit.MILLISECONDS );
                ksession.insert( sa[i] );
                ksession.fireAllRules();
            }
            
            clock.advanceTime( 100, TimeUnit.SECONDS );
            ksession.fireAllRules();
        } catch (Throwable t) {
            t.printStackTrace();
        } finally {
            if( logger != null ) logger.close();
        }
    }

    private static KnowledgeBase readKnowledgeBase() throws Exception {
        KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        kbuilder.add(ResourceFactory.newClassPathResource("AlertingRules.drl"), ResourceType.DRL);
        KnowledgeBuilderErrors errors = kbuilder.getErrors();
        if (errors.size() > 0) {
            for (KnowledgeBuilderError error: errors) {
                System.err.println(error);
            }
            throw new IllegalArgumentException("Could not parse knowledge.");
        }
        KnowledgeBaseConfiguration conf = KnowledgeBaseFactory.newKnowledgeBaseConfiguration();
        conf.setOption( EventProcessingOption.STREAM );
        KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase( conf );
        kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());
        return kbase;
    }
}
