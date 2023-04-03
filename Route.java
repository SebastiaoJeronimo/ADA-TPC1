
public class Route {
	private int[][] problemMatrix;
	private String 	route;
	
	public Route(String route) {
		this.route = route;
		problemMatrix = new int[4][route.length()];
	}

	public String getRoute() {
		return route;
	}
	
	/***
	 * returns the minimum cost of traveling the whole route
	 * @param route its the route that we are going to cross
	 * @returns the minimum cost of crossing the route
	 */
	public int getMinimum(String route) {
		fillTheBaseCase(problemMatrix);
		fillTheRest(problemMatrix);
		return findMin(this.problemMatrix, route.length());
		
	}

	/***
	 * fills the rest of the dynamic programming matrix solution
	 * @param matrix its the matrix of the problem
	 */
	private void fillTheRest(int[][] matrix) {
		int lastCollum = this.route.length();
		for (int y = 1; y < lastCollum; y++) { //testar
			char c = this.route.charAt(y);
			int plot = "ehpc3td".indexOf(c);
			// fazer hash map ou uma matrix valores pq O(n*7)
			// fazer uma funcao para retornar um valor baseado numa letra interpeter
			for (int x = 0; x < 4; x++) {
				if (plot >= 4) { //computes the cost of a tile with a monster
					matrix[x][y] = compCostMonster(x, y, plot, matrix );
				} else { 		 //computes the cost of a tile with an item or empty
					matrix[x][y] = compCostEmptyOrItem(x, y, plot, matrix);
				}
			}
		}
	}

	/***
	 * 
	 * @param matrix the matrix of the problem 
	 * @param y the number of the collum the index is -1 of that number
	 * @returns the lowest value of the collum and if you pass the index as y it returns the min
	 * of the previous indexed collum 
	 */
	private static int findMin(int[][] matrix, int y) {
        int min1 = Math.min(matrix[0][y - 1], matrix[1][y - 1]);
        int min2 = Math.min(matrix[2][y - 1], matrix[3][y - 1]);
        return Math.min(min1, min2);
    }

	/**
	 * treats the base cases of the dynamic programming matrix
	 * it fills with one the empty row 
	 * fills a certain item row with a 2 if it starts with that item 
	 * fills the rest with infinity (MAX_VALUE)
	 * @param matrix the problem matrix
	 */
	private void fillTheBaseCase(int[][] matrix) {
		char c = this.route.charAt(0);
		// 0 if does not exist 1 if harp 2 if potion 3 if clock
		int firstPosPotion = "hpc".indexOf(c) + 1;
		// fill the first collum
		for (int i = 0; i < 4; i++) {
			if (i == 0)
				matrix[i][0] = 1;
			else if (i == firstPosPotion && i != 0)
				matrix[i][0] = 2;
			else
				matrix[i][0] = Integer.MAX_VALUE;
		}
	}
	
	/***
	 * Computes the cost when a plot is empty or has a specific item
	 * @param x row
	 * @param y collum
	 * @param plot type of plot
	 * @param matrix matrix of the solution
	 * @returns the cost of this tile to each square in our solution matrix 
	 */
	private static int compCostEmptyOrItem(int x, int y, int plot, int[][] matrix) {
		boolean existsItem = (plot >= 1 && plot <= 3);
		if (x == 0) {
			if (matrix[x][y - 1] == Integer.MAX_VALUE)
				return findMin(matrix, y) + 2;
			else
				return  matrix[x][y - 1] + 1; // else somas mais 1 ao resultado do vazio anterior
		} else if (existsItem && x == plot) {
			return matrix[0][y] + 1;
		} else {
			if (matrix[x][y - 1] == Integer.MAX_VALUE)
				return  Integer.MAX_VALUE;
			else
				return matrix[x][y - 1] + 3;
		}
	}
	
	/**
	 * computes the cost of a monster tile
	 * @param x corresponds to the row element of the table 0 for nothing 1 for harp 2 for potion 3 for cloak
	 * @param y corresponds to the collum element of the table
	 * @param plot its the type of plot in the matrix 
	 * @param matrix the solution matrix
	 * @returns the cost of passing across this specific monster tile
	 */
	private static int compCostMonster(int x, int y, int plot, int[][] matrix) {
		if (x == 0)
			return Integer.MAX_VALUE;
		else if (x + 3 >= plot && matrix[x][y - 1] != Integer.MAX_VALUE) // fazer hash ou funcao que // intrepreta o char	
			return matrix[x][y - 1] + (x + 3);
		else
			return Integer.MAX_VALUE;
	}
}
	