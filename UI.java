import java.util.Scanner;

public class UI {
	public static void main(String[] args) {
		int toPlant, foodAlloc, toBuy;
		Scanner inputSc = new Scanner(System.in);
		Game game = new Game();
		while(game.isOver() == false) {
			
			System.out.println("\n\n\n\n*********====STATS====********\n");
			
			System.out.println("The year is: " + game.getYear());
			System.out.println("Current population is: " + game.getPop());
			System.out.println("Bulshels left: " + game.getBulshel());
			System.out.println("Acres of land: " + game.getArea());
			
			System.out.println("Last year, " + game.getStarvedLastYear() + " people starved");
			System.out.println("Last year, we harvested " + game.getHarvestedLastYear() + " acres at " + game.getHarvestPerAcreLastYear() + " per acre");
			System.out.println(game.getMigLastYear() + " people migrated last year");
			
			System.out.println("Price of the land is " + game.getPriceOfLand() + " per acre");
			
			//Input
			
			do {
				System.out.println("\n\n\n\n*********====Input====********\n");
				System.out.println("Enter the bulshels to be allocated for citizens:");
				foodAlloc = Integer.parseInt(inputSc.nextLine());
				System.out.println("Enter the acres of land to plant seeds on:");
				toPlant = Integer.parseInt(inputSc.nextLine());
				System.out.println("Enter the acres of land to buy, negative values to sell, 0 for none:");
				toBuy = Integer.parseInt(inputSc.nextLine());
			} while(game.validateData(foodAlloc, toPlant, toBuy) == false);
			
			game.doIt(foodAlloc, toPlant, toBuy);
			
		}
		
		System.out.println("END");
	}
}