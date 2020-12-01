package curs14;

import java.util.Scanner;

public class Game extends PlayerStatus{
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Welcome to " + PlayerStatus.getGameName());
		
		
		//creare jucatori
		PlayerStatus player1 = new PlayerStatus();
		PlayerStatus player2 = new PlayerStatus();
		
		
		//initializare jucatori
		System.out.println("Initializare jucator 1: nickname");
		player1.initPlayer(sc);

		System.out.println("Initializare jucator 2: nickname");
		player2.initPlayer(sc);
		
		//pozitie jucatori
		System.out.println("Introduceti coordonatele player1: p1x, p1y");
		player1.setPositionX(sc.nextInt());
		player1.setPositionY(sc.nextInt());
		
		System.out.println("Introduceti coordonatele player2: p2x, p2y");
		player2.setPositionX(sc.nextInt());
		player2.setPositionY(sc.nextInt());

		//loop pentru verificare vieti dupa fiecare fight
		while(player1.getLives() > 0 && player2.getLives() > 0) {
			
			//artefacte jucatori
			System.out.println("\nIntroduceti un numar artefact: player1, player2");
			player1.findArtifact(sc.nextInt());
			player2.findArtifact(sc.nextInt());
			
			//Info player 1
			System.out.println("Player 1 stats");
			System.out.println(player1.playerStats());
			
			//Info player 2
			System.out.println("Player 2 stats");
			System.out.println(player2.playerStats());
			
			sc.nextLine();
			
			System.out.println("\nSetati arma player1: knife, kalashnikov, sniper");
			player1.setWeaponInHand(sc.nextLine());
			
			System.out.println("\nSetati arma player2: knife, kalashnikov, sniper");
			player2.setWeaponInHand(sc.nextLine());
			
			//Info player 1
			System.out.println("Distance between players is: " + player1.distance(player2));

			System.out.println("Should attack opponent:\n P1 -> P2 " + player1.shouldAttackOpponent(player2) + "\n P2 -> P1 " +
					player2.shouldAttackOpponent(player1));
			
			if(player1.getShouldAttack()) {
				player1.figthWin();
				player2.figthLoss();
				System.out.println("Player " + player1.getNickName() + " gains 2000 points.");
				System.out.println("Player " + player2.getNickName() + " loses 1000 points and one life.");
			}else {
				player2.figthWin();
				player1.figthLoss();
				System.out.println("Player " + player2.getNickName() + " gains 2000 points.");
				System.out.println("Player " + player1.getNickName() + " loses 1000 points and one life.");
			}
			
			//Info player 1
			System.out.println("\nPlayer 1 stats");
			System.out.println(player1.playerStats());
			
			//Info player 2
			System.out.println("\nPlayer 2 stats");
			System.out.println(player2.playerStats());
			
			if(player1.getLives() == 0 || player2.getLives() ==0) {
				break;
			}
			
			//repozitionare jucatori
			System.out.println("Introduceti coordonatele player1: p1x, p1y");
			player1.movePlayerTo(sc.nextInt(), sc.nextInt());
			System.out.println("Introduceti coordonatele player2: p2x, p2y");
			player2.movePlayerTo(sc.nextInt(), sc.nextInt());
		}
		
		System.out.println("Game OVER");
		
		sc.close();
	}
}
