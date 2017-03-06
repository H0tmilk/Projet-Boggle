package boggle;

import java.util.ArrayList;

/**
 * Composantes d'un joueur
 * @author Kilian CHOLLET, Antoine AUDRIN
 * @version 1
 */
public class Joueur {
	// Nom du joueur
	private String nom;
	
	// Les mots entr�s du joueur sont plac�s dans une ArrayList
	private ArrayList<String> motsEntr�s;
	
	// Mots valides entr�s par le joueur
	private ArrayList<String> motsValides;
	
	// Nombre de points du joueur
	private int points;
	
	/**
	 * Constructeur du joueur (instancie les ArrayList et d�finit le nom)
	 * @param nom
	 */
	public Joueur(String nom) {
		this.nom = nom;
		this.motsValides = new ArrayList<String>();
		this.motsEntr�s = new ArrayList<String>();
	}

	/**
	 * Retourne le nom du joueur
	 * @return nom
	 */
	public String getNom() {
		return this.nom;
	}

	/**
	 * Retourne les mots entr�s par le joueur dans une ArrayList
	 * @return motsEntr�s
	 */
	public ArrayList<String> getMotsEntr�s() {
		return this.motsEntr�s;
	}
	
	/**
	 * Ajoute un mot � la liste des mots entr�s par le joueur
	 * @param mot
	 */
	public void entrerMot(String mot) {
		this.motsEntr�s.add(mot);
	}
	
	/**
	 * Supprime un mot de la liste des mots entr�s par le joueur
	 * @param i, indice du mot
	 */
	public void supprimerMot(int i) {
		this.motsEntr�s.remove(i);
	}
	
	/**
	 * Reourne les mots valides entr�s par le joueur
	 * @return motsValides
	 */
	public ArrayList<String> getMotsValides() {
		return this.motsValides;
	}

	/**
	 * Retourne les points du joueur
	 * @return points
	 */
	public int getPoints() {
		return this.points;
	}
	
	/**
	 * Calcule les points du joueur
	 */
	public void calculerPoints() {
		boolean motEntr�PlusieursFois = false;
		for(int i = 0; i < this.motsEntr�s.size(); ++i) {
			if(motEntr�PlusieursFois) {
				--i;
			}
			
			// Suppression des doublons de chaque joueur
			for(int j = 0; j < this.motsEntr�s.size(); ++j) {
				if((i != j) && (this.motsEntr�s.get(i).equals(this.motsEntr�s.get(j)))) {
					this.motsEntr�s.remove(j);
				}
			}
			
			// Suppression des mots entr�s par plusieurs joueurs
			motEntr�PlusieursFois = false;
			for(int j = 0; j < Boggle.getNbJoueurs(); ++j) {
				
				if(!Boggle.getJoueurs().get(j).equals(this)) {
					for(int k = 0; k < Boggle.getJoueurs().get(j).getMotsEntr�s().size(); ++k) {
						if(Boggle.getJoueurs().get(j).getMotsEntr�s().get(k).equals(this.motsEntr�s.get(i))) {
							Boggle.getJoueurs().get(j).supprimerMot(k);
							Boggle.ajouterMotInvalide(this.motsEntr�s.get(i)+" (mot entr� par 2 joueurs)");
							motEntr�PlusieursFois = true;
						}
					}
				}
			}

			
			if(motEntr�PlusieursFois) {
				this.supprimerMot(i);
			}
		}
			
		// D�finition des mots valides (contenus dans le plateau) et des mots invalides
		for(int i = 0; i < this.motsEntr�s.size(); ++i) {
			if(Plateau.contient(this.motsEntr�s.get(i))) {
				this.motsValides.add(this.motsEntr�s.get(i));
			}
			else {
				Boggle.ajouterMotInvalide(this.motsEntr�s.get(i)
						+" (mot de moins de 3 lettres ou non existant"
						+ " dans le dictionnaire ou dans le plateau)");
			}
		}
		
		// Attribution des points
		for(int i = 0; i < this.motsValides.size(); ++i) {
			if((this.motsValides.get(i).length() == 3) || (this.motsValides.get(i).length() == 4)) {
				++this.points;
			}
			else if (this.motsValides.get(i).length() == 5) {
				this.points += 2;
			}
			else if (this.motsValides.get(i).length() == 6) {
				this.points += 3;
			}
			else if (this.motsValides.get(i).length() == 7) {
				this.points += 5;
			}
			else if (this.motsValides.get(i).length() > 7) {
				this.points += 11;
			}
		}
		
	}

}
