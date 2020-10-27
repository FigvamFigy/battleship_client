package network.protocol_classes;

import graphicLogic.MainLogic;

public class GameStartProtocolClass implements IProtocolClass{


    public GameStartProtocolClass() {


    }


    @Override
    public void execute() {

        MainLogic.startGame();
    }
}
