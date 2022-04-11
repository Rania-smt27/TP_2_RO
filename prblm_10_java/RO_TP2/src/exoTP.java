import ilog.concert.*;

import ilog.cplex.*;


public class exoTP {
	public static void main(String[] args) {
		calcul ();
	}


	public static void calcul (){
		//declaration :
	
		int nville = 6;
		int nhub = 2;
		//distance entre ville:
		int[][] d = new int[5][5];
		d[0][0] = 945;
		d[0][1] = 605;
		d[0][2] = 4667;
		d[0][3] = 4749;
		d[0][4] = 4749;
		d[1][0] = 0;
		d[1][1] = 866;
		d[1][2] = 3726;
		d[1][3] = 3806;
		d[1][4] = 3448;
		d[2][0] = 0;
		d[2][1] = 0;
		d[2][2] = 4471;
		d[2][3] = 4541;
		d[2][4] = 4152;
		d[3][0] = 0;
		d[3][1] = 0;
		d[3][2] = 0;
		d[3][3] = 109;
		d[3][4] = 415;
		d[4][0] = 0;
		d[4][2] = 0;
		d[4][1] = 0;
		d[4][3] = 0;
		d[4][4] = 431;
		
		//quantite de fret entre ville:
		int[][] q = new int[6][6];
		q[0][0] = 0;
		q[0][1] = 500;
		q[0][2] = 1000;
		q[0][3] = 300;
		q[0][4] = 400;
		q[0][5] = 1500;
		q[1][0] = 1500;
		q[1][1] = 0;
		q[1][2] = 250;
		q[1][3] = 630;
		q[1][4] = 360;
		q[1][5] = 1140;
		q[2][0] = 400;
		q[2][1] = 510;
		q[2][2] = 0;
		q[2][3] = 460;
		q[2][4] = 320;
		q[2][5] = 490;
		q[3][0] = 300;
		q[3][1] = 600;
		q[3][2] = 810;
		q[3][3] = 0;
		q[3][4] = 820;
		q[3][5] = 310;
		q[4][0] = 400;
		q[4][2] = 100;
		q[4][1] = 420;
		q[4][3] = 730;
		q[4][4] = 0;
		q[4][5] = 970;
		q[5][0] = 350;
		q[5][2] = 1020;
		q[5][1] = 260;
		q[5][3] = 580;
		q[5][4] = 380;
		q[5][5] = 0;
		
		//model
		try {
		IloCplex simplexe = new IloCplex ();
		
		// déclaration des Variables de décision:
		
		
		//le cout de transport:
		IloNumVar[][] C = new IloNumVar[nville][nville];
		for (int i=0; i< nville;i++){
			for (int j=0; j< nville;j++) {
				C[i][j]  = d[i][j]*(8/10);
			}
		
		};
		//les flow: binary variable ( 0 ou 1)
		IloNumVar[][] F = new IloNumVar[nville][]; 
		for(int j=0; j<nville; j++){
		    F[j]= cplex.numVarArray(nville,0, 1, IloNumVarType.Int);//ILP
		}
		
		//les K hubs:
		IloNumVar[][] H = new IloNumVar[nville][]; 
		for(int k=0; k<nhub; k++){
		    H[k] = cplex.numVarArray(nhub,0, 1, IloNumVarType.Int);//ILP
		}
		
		// declaration de la fonction objectif
		IloLinearNumExpr objectif = simplexe.linearNumExpr();
		
		// Définition des coefficients de la fonction objectif:
		
		simplexe.addMinimize(objectif);
		for (int i=0; i< nville;i++){
			for (int j=0; j< nville;j++) {
				objectif.addTerm(C[i][j], F[i][j],q[i][j]);
			}
		
		};
		// contrainte
		for(int i=0; i< nville;i++){
		IloLinearNumExpr expr = simplexe.linearNumExpr();
			for (int j=0; j< nville;j++) {
				expr.addTerm(1.0,F[i][j]);
			}
		}
		
		
		IloLinearNumExpr expr2 = simplexe.linearNumExpr();
		expr2.addTerm(1, H[nhub][nhub]);

		simplexe.solve(); // lancer la resolution
		// Afficher des résultat
		System.out.println("Voici la valeur de la fonction objectif "+ simplexe.getObjValue());
		System.out.println(" Voici les valeurs des variables de décision: ") ;
		System.out.println( "X1 = "+ simplexe.getValue(expr));
		System.out.println( "X2 = "+ simplexe.getValue(expr2));
		} catch (IloException e){
		System.out.print("Exception levée " + e);
		}*/
		}
}