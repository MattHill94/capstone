package org.example;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class PlayerList {
    public static void main(String[] args) {
        List<Player> oldPlayers = getPlayersFromXML();
//        List<Player> players = sortASC(oldPlayers);
        List<Player> players = sortDESC(oldPlayers);
        for (Player player:players
             ) {
            System.out.println("Name: "+player.getName()+" \tNumber: "+player.getNum());
        }
        System.out.println("***************************************************");
        for (Player player:oldPlayers
        ) {
            System.out.println("Name: "+player.getName()+" \tNumber: "+player.getNum());
        }
    }


    public static List<Player> getPlayersFromXML(){
        List<Player> players = new LinkedList<>();
        try{
            File file = new File("../capstone/src/main/resources/data.xml");
            if (file.exists()) {
                DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
                Document document = documentBuilder.parse("../capstone/src/main/resources/data.xml");

                //Gets the elements "Name" and "Numbers" from the data.xml file and add them to a node list
                NodeList[] player = {document.getElementsByTagName("name"), document.getElementsByTagName("number")};

                for (int i = 0; i < player[0].getLength(); i++) {
                    //Iterate through the node list and collect the string values of both "name" and "number" then pass the as parameters to new Player
                    String name = player[0].item(i).getTextContent();
                    int number = Integer.parseInt(player[1].item(i).getTextContent());
                    Player newPlayer = new Player(name,number);
                    //Then add the new player to the players List
                    players.add(newPlayer);
                }
            }else {
                System.out.println("File not found.");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        //We then return the final collection of players
        return players;
    }

    //This method takes a players list as a parameter
    public static List<Player> sortASC(List<Player> players){
        List<Player> tempPlayers = copyList(players);
        tempPlayers.sort((p1, p2) -> p1.getNum() - p2.getNum());
        return tempPlayers;
    }

    //This method takes the
    public static List<Player> sortDESC(List<Player> players){
        List<Player> tempPlayers = copyList(players);
        tempPlayers.sort((p1, p2) -> p2.getNum() - p1.getNum());
        return tempPlayers;
    }

    //This method is for copying one LinkedList to a new LinkedList to avoid making changes to the original List.
    private static List<Player> copyList(List<Player> players) {
        return new LinkedList<>(players);
    }
}


