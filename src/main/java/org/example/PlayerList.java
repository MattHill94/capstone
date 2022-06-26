package org.example;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class PlayerList {
    private List<Player> players;

    public PlayerList() {
        this.players = new LinkedList<Player>();
        getPlayersFromXML();
    }

    private List<Player> getPlayersFromXML(){
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
        return this.players;
    }

    //This method takes a players list as a parameter, make a copy of the list then sort the copy in ASC order and return the copy.
    public List<Player> sortASC(){
        List<Player> tempPlayers = copyList();
        tempPlayers.sort((p1, p2) -> p1.getNum() - p2.getNum());
        return tempPlayers;
    }

    //This method takes a players list as a parameter, make a copy of the list then sort the copy in DESC order and return the copy.
    public List<Player> sortDESC(){
        List<Player> tempPlayers = copyList();
        tempPlayers.sort((p1, p2) -> p2.getNum() - p1.getNum());
        return tempPlayers;
    }

    //This method is for copying one LinkedList to a new LinkedList to avoid making changes to the original List.
    private List<Player> copyList() {
        return new LinkedList<>(this.players);
    }

    public void printList() {
        for (Player player: this.players
             ) {
            System.out.println("Name: "+player.getName()+"\tNumber: "+player.getNum());
        }
    }
}


