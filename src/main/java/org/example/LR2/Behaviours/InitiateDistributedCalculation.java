package org.example.LR2.Behaviours;

import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import org.example.LR2.Service.AgentService;

import java.text.DecimalFormat;
import java.util.*;

public class InitiateDistributedCalculation extends Behaviour {
    private double x;
    private double d;
    private boolean done = false;
    private List<AID> agents;
    private List<Double> newY = new ArrayList<>(Arrays.asList(0.0, 0.0, 0.0));
    int i = 0;

    public InitiateDistributedCalculation(double x, double d) {
        this.x = x;
        this.d = d;
    }

    @Override
    public void onStart() {
        ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
        msg.setContent((x - d) + "," + x + "," + (x + d));

        agents = AgentService.findAgents(myAgent, "FunctionAgent");
        agents.forEach(msg::addReceiver);

        myAgent.send(msg);
    }

    @Override
    public void action() {



        ACLMessage recivedMessage = myAgent.receive(MessageTemplate.MatchPerformative(ACLMessage.ACCEPT_PROPOSAL));
        if (recivedMessage != null) {
            System.out.println(myAgent.getLocalName() + " received message " + recivedMessage.getContent() + " from " + recivedMessage.getSender().getLocalName());
            //Получаем значения из сообщения
            List<Double> arrayOfY = Arrays.stream(recivedMessage.getContent().split(","))
                    .map(Double::parseDouble)
                    .toList();


            newY.set(0, newY.get(0) + arrayOfY.get(0));
            newY.set(1, newY.get(1) + arrayOfY.get(1));
            newY.set(2, newY.get(2) + arrayOfY.get(2));

            i ++;
        } else {
            block();
        }

        if (i == agents.size() ){
            FindNewX(newY);
            this.done = true;
        }
    }

    @Override
    public int onEnd() {


        if (isAccuracyReached()){
            System.out.println(x);
        }else {
            AID newInitiator = agents.get((int) (Math.random() * agents.size()));
            while(newInitiator == myAgent.getAID()){
                newInitiator = agents.get((int) (Math.random() * agents.size()));
            }


            ACLMessage calculationMessage = new ACLMessage(ACLMessage.INFORM);
            calculationMessage.setContent(x + "," + d);
            calculationMessage.addReceiver(newInitiator);
            myAgent.send(calculationMessage);
        }
        return 0;
    }

    private boolean isAccuracyReached() {
        return d < 0.001;
    }

    @Override
    public boolean done() {
        return done;
    }


    private void FindNewX(List<Double> newY){

        if (newY.get(1) < newY.get(0) && newY.get(1)  < newY.get(2)){
            this.d = d / 2;
        }else if ( newY.get(1)  < newY.get(0) && newY.get(1)  > newY.get(2)){
            this.x = x + d;
        }else {
            this.x = x - d;
        }

    }
}
