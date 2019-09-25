package bowling;

/**
 * Cette classe a pour but d'enregistrer le nombre de quilles abattues lors des
 * lancés successifs d'<b>un seul et même</b> joueur, et de calculer le score
 * final de ce joueur
 */
public class SinglePlayerGame {

        private int scorefinal;
        private int quilleRestantes;
        private int boule;
        private int tour;
        private int listeDeStrike [];
        private int listeDeSpare [];
	/**
	 * Constructeur
	 */
	public SinglePlayerGame() {
            this.scorefinal = 0;
            this.quilleRestantes = 10;
            this.tour=0;
            this.boule = 1;
            this.listeDeStrike = new int[10];
            this.listeDeSpare = new int[10];
	}

	/**
	 * Cette méthode doit être appelée à chaque lancé de boule
	 *
	 * @param nombreDeQuillesAbattues le nombre de quilles abattues lors de
	 * ce lancé
	 */
	public void lancer(int nombreDeQuillesAbattues) {
          if(this.tour<10){
                // point du spare d'avant
                if(this.listeDeSpare[this.tour]==1){
                     this.scorefinal += nombreDeQuillesAbattues;
                }
                // point du strike d'avant
                if(this.listeDeStrike[this.tour]==1){
                     this.scorefinal += nombreDeQuillesAbattues;
                }
                
                // cas normal
                if(this.quilleRestantes > nombreDeQuillesAbattues){
                    this.scorefinal += nombreDeQuillesAbattues;
                    this.quilleRestantes -= nombreDeQuillesAbattues;
                    this.tour++;
                }

                //spare
                // si on est au deuxieme lancé et qu'on fait tomber toutes les quilles restantes
                if((this.boule== 1)&&(nombreDeQuillesAbattues == this.quilleRestantes)){
                    this.listeDeSpare[this.tour+1]+=1;
                    this.scorefinal += nombreDeQuillesAbattues;
                    this.quilleRestantes -= nombreDeQuillesAbattues;
                    this.boule++;
                }

                //strike
                if ((this.boule == 1) && (nombreDeQuillesAbattues == 10)){   
                    this.listeDeStrike[this.tour+1]+=1;
                    this.listeDeStrike[this.tour+2]+=1;
                    this.scorefinal += nombreDeQuillesAbattues;
                    this.quilleRestantes -= nombreDeQuillesAbattues;
                    this.tour++;
                }


                if(this.quilleRestantes == 0 || (this.boule == 2)){
                    this.quilleRestantes = 10;
                    this.boule=1;
                    this.tour++;
                }
          }
          else if(this.tour>9 && this.tour<12){
            this.scorefinal += nombreDeQuillesAbattues;
            this.quilleRestantes -= nombreDeQuillesAbattues;
            this.tour++;
            if(this.quilleRestantes == 0){
                this.quilleRestantes = 10;
            }
          }
          else{
              System.out.println("La partie est fini!");
          }
                 
	}

	/**
	 * Cette méthode donne le score du joueur
	 *
	 * @return Le score du joueur
	 */
	public int score() {
		//throw new UnsupportedOperationException("Pas encore implémenté");
                
            return this.scorefinal;   
                
	}
}
