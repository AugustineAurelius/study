package org.example.LR2.Behaviours;

import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

import java.util.Arrays;
import java.util.List;

public class CatchInitiative extends Behaviour {
    @Override
    public void action() {
        ACLMessage recivedMessage =  myAgent.receive(MessageTemplate.MatchPerformative(ACLMessage.INFORM));
        if (recivedMessage != null){
            System.out.println(myAgent.getLocalName() + " received message "+recivedMessage.getContent()+" from "+recivedMessage.getSender().getLocalName());

            List<Double> XD = Arrays.stream(recivedMessage.getContent().split(","))
                    .map(Double::parseDouble).toList();

            myAgent.addBehaviour(new InitiateDistributedCalculation(XD.get(0), XD.get(1)));
        } else {
            block();
        }
    }

    @Override
    public boolean done() {
        return false;
    }
}
