package Bowling;


import bowling.MultiPlayerGame;
import bowling.SinglePlayerGame;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author pedago
 */
public class MultiPlayerBowling implements MultiPlayerGame{
    //SinglePlayerGame [] gameList;
    ArrayList<SinglePlayerGame> gameList = new ArrayList<SinglePlayerGame>(); 
    String[]PlayerName;
    int JoueurCourant=0;
    
    /**
	 * Démarre une nouvelle partie pour un groupe de joueurs
	 * @param playerName un tableau des noms de joueurs (il faut au moins un joueur)
	 * @return une chaîne de caractères indiquant le prochain joueur,
	 * de la forme "Prochain tir : joueur Bastide, tour n° 1, boule n° 1"
	 * @throws java.lang.Exception si le tableau est vide ou null
	 */
    @Override
	public String startNewGame(String[] playerName) throws Exception{
            if(playerName.length==0 || playerName==null ){
                throw new IllegalArgumentException("La liste des joueur ne doit pas etre nulle");
            }
            this.PlayerName=playerName;
            for(int i = 0; i<playerName.length; i++){
                SinglePlayerGame game = new SinglePlayerGame();
                this.gameList.add(i,game);
            }
            return "Prochain tir : joueur "+playerName[this.JoueurCourant]+", tour n°"+this.gameList.get(this.JoueurCourant).getFrameNumber()+", boule n°"+this.gameList.get(this.JoueurCourant).getNextBallNumber();
        }
	
	/**
	 * Enregistre le nombre de quilles abattues pour le joueur courant, dans le frame courant, pour la boule courante
	 * @param nombreDeQuillesAbattues : nombre de quilles abattue à ce lancer
	 * @return une chaîne de caractères indiquant le prochain joueur,
	 * de la forme "Prochain tir : joueur Bastide, tour n° 5, boule n° 2",
	 * ou bien "Partie terminée" si la partie est terminée.
	 * @throws java.lang.Exception si la partie n'est pas démarrée.
	 */
    @Override
	public String lancer(int nombreDeQuillesAbattues) throws Exception{
            if(this.gameList.size() == 0){
                throw new IllegalArgumentException("La partie n'est pas démarrée!");
            }
            
            this.gameList.get(this.JoueurCourant).lancer(nombreDeQuillesAbattues);
            this.JoueurCourant = (this.JoueurCourant+1)%gameList.size();
            
            return "Prochain tir : joueur "+this.PlayerName[this.JoueurCourant]+", tour n°"+this.gameList.get(this.JoueurCourant).getFrameNumber()+", boule n°"+this.gameList.get(this.JoueurCourant).getNextBallNumber();
        }
	
	/**
	 * Donne le score pour le joueur playerName
	 * @param playerName le nom du joueur recherché
	 * @return le score pour ce joueur
	 * @throws Exception si le playerName ne joue pas dans cette partie
	 */
    @Override
	public int scoreFor(String playerName) throws Exception{
            for(int i = 0; i < this.PlayerName.length; i++){
                if(this.PlayerName[i] == playerName){
                    return this.gameList.get(i).score(); 
                }
            }
            throw new IllegalArgumentException("Ce joueur ne joue pas dans cette partie"); 
        }
}
