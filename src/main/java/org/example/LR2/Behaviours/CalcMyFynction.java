package org.example.LR2.Behaviours;

import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import org.example.LR2.Behaviours.Functions.Agent1FunctionImpl;
import org.example.LR2.Behaviours.Functions.Agent2FunctionImpl;
import org.example.LR2.Behaviours.Functions.Agent3FunctionImpl;
import org.example.LR2.Behaviours.Functions.Functions;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class CalcMyFynction extends Behaviour {
    private HashMap<String, Functions> FuncMap = new HashMap<>();

    public CalcMyFynction() {
        FuncMap.put("Agent1", new Agent1FunctionImpl());
        FuncMap.put("Agent2", new Agent2FunctionImpl());
        FuncMap.put("Agent3", new Agent3FunctionImpl());
    }


    @Override
    public void action() {
        ACLMessage msg =  myAgent.receive(MessageTemplate.MatchPerformative(ACLMessage.REQUEST));
        if (msg != null){
            System.out.println(myAgent.getLocalName() + " received message "+msg.getContent()+" from "+msg.getSender().getLocalName());

            String arrayOfY = Arrays.stream(msg.getContent().split(","))
                    .map(Double::parseDouble)
                    .map(this::MyFunc)
                    .map(String::valueOf)
                    .collect(Collectors.joining(","));


            ACLMessage reply = msg.createReply();
            reply.setPerformative(ACLMessage.ACCEPT_PROPOSAL);
            reply.setContent(arrayOfY);
            myAgent.send(reply);

        } else {
            block();
        }

    }

    @Override
    public boolean done() {
        return false;
    }

    private double MyFunc(double x) {
        return FuncMap.get(myAgent.getLocalName()).Calculate(x);
    }



}
