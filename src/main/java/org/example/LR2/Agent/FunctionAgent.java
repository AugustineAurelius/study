package org.example.LR2.Agent;


import jade.core.Agent;
import jade.lang.acl.ACLMessage;
import org.example.LR2.Behaviours.CalcMyFynction;
import org.example.LR2.Behaviours.CatchInitiative;
import org.example.LR2.Service.AgentService;

public class FunctionAgent extends Agent {
    @Override
    protected void setup() {
        System.out.println(getLocalName() + " born");
        AgentService.registerAgent(this, "FunctionAgent");

        this.addBehaviour(new CalcMyFynction());
        this.addBehaviour(new CatchInitiative());

        if (this.getLocalName().equals("Agent1")){
            System.out.println("started");
            ACLMessage initiative = new ACLMessage(ACLMessage.INFORM);
            initiative.setContent(0 + "," + 0.5);
            initiative.addReceiver(this.getAID());
            this.send(initiative);
        }
    }
}