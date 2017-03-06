package boggle;
import java.util.ArrayList;

import dictionnaire.Dictionnaire;

/**
 * Composantes d'un plateau
 * @author Kilian CHOLLET, Antoine AUDRIN
 * @version 1
 */
public class Plateau {
	// Taille du plateau
	private static final int TAILLE_PLATEAU = 16;
	
	// Nombre de lignes du plateau (il s'agit d'un carr�)
	private static final int NB_LIGNES = (int) Math.sqrt(TAILLE_PLATEAU);
	
	// Le plateau contient toutes les faces visibles de chaque d�
	private static char[][] plateau;
	
	// ArrayList contenant tous les mots possible � trouver dans le plateau
	private static ArrayList<String> mots;
	
	// Tableau contenant tous les d�s du plateau
	private static D� d�s[];
	
	static {
		d�s = new D�[TAILLE_PLATEAU];
	}
	
	/**
	 * Setter des d�s qui vont constituer le plateau, en fonction d'une longue chaine de caract�res de la forme :
	 * "ABCDEF GHIJKL MNOPQR STUVWX YZABCD .........." contenant les faces de chaque d�.
	 * @param lettres
	 */
	public static void setD�s(String lettres) {
		for(int i = 0; i < TAILLE_PLATEAU; ++i) {
			d�s[i] = new D�(lettres.split(" ")[i]);
			d�s[i].m�langer();
		}
		cr�erPlateau(d�s);
	}

	/**
	 * Retourne le tableau contenant tous les d�s de la partie
	 * @return d�s
	 */
	public static D�[] getD�s() {
		return d�s;
	}

	/**
	 * Retourne les mots du plateau
	 * @return mots
	 * @see mots
	 */
	public static ArrayList<String> getMots() {
		return mots;
	}
	
	/**
	 * Retourne le nombre de lignes du plateau (et par cons�quent le nombre de colonnes)
	 * @return NB_LIGNES
	 */
	public static int getNbLignes() {
		return NB_LIGNES;
	}

	/**
	 * Cr�e un plateau � partir d'un tableau de d�s
	 * @param d�s
	 */
	public static void cr�erPlateau(D�[] d�s) {
		plateau = new char[NB_LIGNES][NB_LIGNES];
		int cpt = 0;
		for(int i = 0; i < NB_LIGNES; ++i) {
			for(int j = 0; j < NB_LIGNES; j++) {
				plateau[i][j] = d�s[cpt].getFaceVisible();
				cpt++;
			}
		}
		trouverMots();
	}
	
	/**
	 * Cr�e un plateau � partir de lettres (pas besoin de d�s)
	 * @param lettres
	 */
	public static void cr�erPlateauPersonnalis�(String lettres) {
		plateau = new char[NB_LIGNES][NB_LIGNES];
		int cpt = 0;
		for(int i = 0; i < NB_LIGNES; ++i) {
			for(int j = 0; j < NB_LIGNES; ++j) {
				plateau[i][j] = lettres.charAt(cpt);
				cpt++;
			}
			cpt++;
		}
		trouverMots();
	}
	
	/**
	 * Retourne le plateau
	 * @return plateau
	 */
	public static char[][] getPlateau() {
		return plateau;
	}
	
	/**
	 * Recherche si un mot est dans le plateau
	 * @param mot
	 * @return true si le mot est dans le plateau
	 */
	private static boolean recherche(String mot) {
		boolean[][] visit�e = new boolean[NB_LIGNES][NB_LIGNES];
		for(int i = 0; i < NB_LIGNES; ++i) {
			for(int j = 0; j < NB_LIGNES; ++j) {
				visit�e[i][j] = false;
			}
		}
		for(int i = 0; i < NB_LIGNES; ++i) {
			for(int j = 0; j < NB_LIGNES; ++j) {
				if(sousRecherche(mot, 0, i, j, visit�e)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * M�thode qui r�alise une sous-recherche pour la case du plateau donn�e
	 * @param mot (mot en cours de traitement)
	 * @param pos (position dans le mot)
	 * @param x (ligne de la case)
	 * @param y (colonne de la case)
	 * @parap visit�e (tableau de bool�ens pour savoir quelles cases ont �t� visit�es)
	 * @return true si la sous-recherche est un succ�s, false sinon
	 */
	private static boolean sousRecherche(String mot, int pos, int x, int y, boolean[][] visit�e) {
		if(pos >= mot.length()) {
			return true;
		}
		if((x > 3) || (x < 0) || (y > 3) || (y < 0)) {
			return false;
		}
		if(plateau[x][y] != mot.charAt(pos)) {
			return false;
		}
		if(visit�e[x][y]) {
			return false;
		}
		visit�e[x][y] = true;
		
		if(sousRecherche(mot, pos + 1, x+1, y, visit�e)) {
			return true;
		}
		if(sousRecherche(mot, pos + 1, x, y+1, visit�e)) {
			return true;
		}
		if(sousRecherche(mot, pos + 1, x-1, y, visit�e)) {
			return true;
		}
		if(sousRecherche(mot, pos + 1, x, y-1, visit�e)) {
			return true;
		}
		if(sousRecherche(mot, pos + 1, x+1, y+1, visit�e)) {
			return true;
		}
		if(sousRecherche(mot, pos + 1, x-1, y-1, visit�e)) {
			return true;
		}
		if(sousRecherche(mot, pos + 1, x+1, y-1, visit�e)) {
			return true;
		}
		if(sousRecherche(mot, pos + 1, x-1, y+1, visit�e)) {
			return true;
		}
		
		visit�e[x][y] = false;
		return false;
	}

	/**
	 * Trouve tous les mots valides du plateau
	 */
	private static void trouverMots() {
		Dictionnaire.setMots("src/dictionnaire/dico.txt");
		mots = new ArrayList<String>();
		mots = Dictionnaire.getMots();
		for(int i = 0; i < mots.size()-1; ++i) {
			if((!recherche(mots.get(i))) || (mots.get(i).length() < 3)) {
				mots.remove(i);
				--i;
			}
		}
		mots.remove(mots.size()-1); // (null)
	}
	
	/**
	 * Indique si un mot est contenu dans le plateau
	 * @param mot
	 * @return true si le plateau contient le mot
	 */
	public static boolean contient(String mot) {
		return mots.contains(mot.toUpperCase());
	}
}
