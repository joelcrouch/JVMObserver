package jvmobservability;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.lang.instrument.Instrumentation;



public class Agent {
    private static final Logger logger = LoggerFactory.getLogger(Agent.class);
    public static void premain(String agentArgs, Instrumentation inst) {
        logger.info(" Agent started!");
        System.out.println("[Agent] Hello from premain!");
    }
}
