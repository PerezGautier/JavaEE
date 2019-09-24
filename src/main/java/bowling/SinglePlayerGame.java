package bowling;

/**
 * Cette classe a pour but d'enregistrer le nombre de quilles abattues lors des
 * lancés successifs d'<b>un seul et même</b> joueur, et de calculer le score
 * final de ce joueur
 */
public class SinglePlayerGame {

        private int scorefinal;
        private int quilleRestantes;
        private int strike;
        private int spare;
        private int compteur;
	/**
	 * Constructeur
	 */
	public SinglePlayerGame() {
            this.scorefinal = 0;
            this.quilleRestantes = 10;
            this.strike = 0;
            this.spare = 0;
            this.compteur = 0;
	}

	/**
	 * Cette méthode doit être appelée à chaque lancé de boule
	 *
	 * @param nombreDeQuillesAbattues le nombre de quilles abattues lors de
	 * ce lancé
	 */
	public void lancer(int nombreDeQuillesAbattues) {
          if(this.compteur<22){
                // point du spare d'avant
                if(this.spare == 1){
                     this.scorefinal += nombreDeQuillesAbattues;
                     this.spare = 0;
                }
                // point du strike d'avant
                if(this.strike > 0){
                     this.scorefinal += nombreDeQuillesAbattues;
                     this.strike--;
                }

                // cas normal
                if(this.quilleRestantes > nombreDeQuillesAbattues){
                    this.scorefinal += nombreDeQuillesAbattues;
                    this.quilleRestantes -= nombreDeQuillesAbattues;
                    this.compteur++;
                }

                //spare
                // si on est au deuxieme lancé et qu'on fait tomber toutes les quilles restantes
                if(((this.compteur%2) == 1)&&(nombreDeQuillesAbattues == this.quilleRestantes) && (this.compteur<20)){
                    this.spare = 1;
                    this.scorefinal += nombreDeQuillesAbattues;
                    this.quilleRestantes -= nombreDeQuillesAbattues;
                    this.compteur++;
                }

                //strike
                if (((this.compteur%2) == 0) && (nombreDeQuillesAbattues == 10) && (this.compteur<20)){
                    this.strike = 2;
                    this.scorefinal += nombreDeQuillesAbattues;
                    this.quilleRestantes -= nombreDeQuillesAbattues;
                    this.compteur+=2;
                }


                if(this.quilleRestantes == 0 || (this.compteur%2) == 0){
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
