package curs14;

import java.util.Scanner;

public class PlayerStatus {
	private String nickName;
	private int score = 9000;
	private int lives = 3;
	private int health = 100;
	private String weaponInHand = "knife";
	private double positionX;
	private double positionY;
	private static String gameName = "CS:GO";
	private int counterDefaultName = 1;
	private boolean shouldAttack = false;
	
	public void initPlayer() {
		this.nickName = "DEFAULT_NAME" + counterDefaultName;
		counterDefaultName++;
	}
	
	public void initPlayer(Scanner nickName) {
		this.nickName = nickName.nextLine();
	}
	
	public void findArtifact(int artifactCode) {
		if(numarPerfect(artifactCode)) {
			this.score += 5000;
			this.lives += 1;
			this.health = 100;
		}
		else if(numarPrim(artifactCode)) {
			this.score += 1000;
			this.lives += 2;
			if(this.health + 25 > 100) {
				this.health = 100;
			}else {
				this.health += 25;
			}
		}
		else if(numarParSumaCifreDiv3(artifactCode)) {
			this.score -= 3000;
			if(this.health - 25 <= 0) {
				this.lives -= 1;
				if(this.lives == 0) {
					this.health = 0;
					System.out.println("Game Over");
					System.exit(0);
				}else {
					this.health = 100;
				}
			}
		}
		else {
			this.score += artifactCode;
		}
	}
	
	public boolean setWeaponInHand(String weaponInHand) {
		String[] weaponsRange = new String[]{"knife", "kalashnikov", "sniper"};
		int[] weaponsCost = new int[] {1000, 10000, 20000};
		int weaponNumber = 0;
		for (int i = 0; i < weaponsRange.length; i++) {
			if(weaponsRange[i].equals(weaponInHand)) {
				weaponNumber = i;
			}
		}
		
		if(this.score >= weaponsCost[weaponNumber] && weaponsRange[weaponNumber].equals(weaponInHand)) {
			this.weaponInHand = weaponInHand;
			this.score -= weaponsCost[weaponNumber];
			return true;
		}else {
			return false;
		}
	}

	public void movePlayerTo(double positionX, double positionY) {
		this.positionX = positionX;
		this.positionY = positionY;
	}
	
	public boolean shouldAttackOpponent(PlayerStatus opponent) {
		double winProbabilityPlayer = 0;
		double winProbabilityOpponent = 0;
		if(this.weaponInHand.equals(opponent.getWeaponInHand())) {
			winProbabilityPlayer = (3*this.health + this.score/1000)/4;
			winProbabilityOpponent = (3*opponent.health + opponent.score/1000)/4;
			
			if(winProbabilityPlayer > winProbabilityOpponent) {
				shouldAttack = true;
			}
		}else {
			if((distance(opponent) > 1000 && weaponInHand.equals("sniper")) || (distance(opponent) > 1000 && (weaponInHand.equals("kalashnikov") && opponent.getWeaponInHand().equals("knife")))) {
				shouldAttack = true;
			}
			else if((distance(opponent) < 1000 && weaponInHand.equals("kalashnikov")) || (distance(opponent) < 1000 && (weaponInHand.equals("sniper") && opponent.getWeaponInHand().equals("knife")))) {
				shouldAttack = true;
			}
			else {
				shouldAttack = false;
			}
		}
		return shouldAttack;
	}
	
	public void figthWin() {
		this.score += 2000;
	}
	
	public void figthLoss() {
		this.lives--;
		this.score -= 1000;
		
	}
	
	private boolean numarPerfect(int artifactCode) {
		int sum = 0;
		for(int i = 1; i <= artifactCode/2; i++) {
			if(artifactCode%i == 0) {
				sum += i;
			}
		}
		if(sum == artifactCode) {
			return true;
		}else {
			return false;
		}
	}
	
	private boolean numarPrim(int artifactCode) {
		for(int i = 2; i <= artifactCode/2; i++) {
			if(artifactCode%i == 0) {
				return false;
			}
		}
		return true;
	}
	
	private boolean numarParSumaCifreDiv3(int artifactCode) {
		int copyArtifactCode = artifactCode;
		int sumaCifre = 1;
		if(artifactCode%2 == 0) {
			while(copyArtifactCode/10 != 0) {
				sumaCifre = 0;
				while(copyArtifactCode != 0) {
					sumaCifre += copyArtifactCode%10;
					copyArtifactCode /= 10;
				}
				copyArtifactCode = sumaCifre;
			}
		}
		
		if(sumaCifre%3 == 0) {
			return true;
		}else {
			return false;
		}
	}
	
	public double distance(PlayerStatus opponent) {
		return Math.sqrt(Math.pow((this.positionX - opponent.positionX), 2)+Math.pow((this.positionX - opponent.positionX), 2));
	}
	
	public String getNickName() {
		return this.nickName;
	}
	
	public String getWeaponInHand() {
		return this.weaponInHand;
	}
	
	
	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}
	
	public double getPositionX() {
		return this.positionX;
	}
	
	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}
	
	public double getPositionY() {
		return this.positionY;
	}
	
	static void setGameName(String newGameName) {
		gameName = newGameName;
	}
	
	public static String getGameName() {
		return gameName;
	}
	
	public boolean getShouldAttack() {
		return this.shouldAttack;
	}
	
	public int getScore() {
		return this.score;
	}
	
	public int getLives() {
		return this.lives;
	}
	
	public int getHealth() {
		return this.health;
	}
	
	public String playerStats() {
		return "Nickname " + getNickName() + ", lives " + getLives() + ", health " + getHealth() + ", weapon " + getWeaponInHand() + ", score " + getScore() + ".";
	}
}
