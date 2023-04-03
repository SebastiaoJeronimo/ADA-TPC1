import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		String n1 = input.readLine(); // Number of routes
		int n = Integer.parseInt(n1);
		String[] routes; // Routes vec
		routes = new String[n + 1];// n+1 just in case
		for (int i = 0; i < n; i++) {
			String input1 = input.readLine(); // Routes
			routes[i] = input1; // Save routes in a vec
		}
		input.close();
		
		for (int j = 0 ; j < n ; j++)
		{
			Route r = new Route(routes[j]);
			int minCostOfPath = r.getMinimum(routes[j]);
			System.out.println(minCostOfPath);
		}
	}
}