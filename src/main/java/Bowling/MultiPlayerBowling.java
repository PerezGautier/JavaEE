package Bowling;


import bowling.MultiPlayerGame;
import bowling.SinglePlayerGame;

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
    SinglePlayerGame [] gameList;
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
            
            for(int i = 0; i<playerName.length; i++){
                SinglePlayerGame game = new SinglePlayerGame();
                gameList[i] = game;
            }
            
            return "Prochain tir : joueur "+playerName[1]+", tour n° "+gameList[0].getFrameNumber()+", boule n°"+gameList[0].getNextBallNumber();
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
            return "";
        }
	
	/**
	 * Donne le score pour le joueur playerName
	 * @param playerName le nom du joueur recherché
	 * @return le score pour ce joueur
	 * @throws Exception si le playerName ne joue pas dans cette partie
	 */
    @Override
	public int scoreFor(String playerName) throws Exception{
            return 0;
        }
}
