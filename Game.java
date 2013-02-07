import java.util.Random;

public class Game {
	boolean over;
	int pop, area, bulshel, year, priceOfLand, harvestPerAcre;
	Random rnd;
	
	//Last year stats
	int starvedLastYear, harvestPerAcreLastYear, harvestedLastYear, migLastYear;

	public Game() {
		pop = 100;
		area = 1000;
		bulshel = 3000;
		year = 0;
		over = false;
		starvedLastYear = 0;
		
		rnd = new Random();
		
		harvestPerAcreLastYear = generateRandomHarvest();
		priceOfLand = generateRandomPrice();
		
		harvestedLastYear = 0;
		migLastYear = 0;
	}
	//get
	public boolean isOver() {
		return over;
	}
	public int getPop() {
		return pop;
	}
	public int getArea() {
		return area;
	}
	public int getBulshel() {
		return bulshel;
	}
	public int getYear() {
		return year;
	}
	public int getStarvedLastYear() {
		return starvedLastYear;
	}
	public int getHarvestPerAcreLastYear() {
		return harvestPerAcreLastYear;
	}
	public int getHarvestedLastYear() {
		return harvestedLastYear;
	}
	public int getPriceOfLand() {
		return priceOfLand;
	}
	public int getMigLastYear() {
		return migLastYear;
	}
	//
	
	int generateRandomPrice() {
		return (rnd.nextInt(26-17+1) + 17);
	}
	
	int generateRandomHarvest() {
		return (rnd.nextInt(3-1+1) + 1);
	}
	
	boolean validateData(int foodAlloc, int toPlant, int toBuy) {
		boolean result = false;
		if(toPlant/10 <= pop || toPlant > area) {
			result = true;
		} else {
				System.out.println("Not enough population to plant OR Not enough Area");
		}
		return result;
	}
	
	//Priority : Harvest > Food Allocation > Selling/Buying
	
	void doIt(int foodAlloc, int toPlant, int toBuy) { 
		//Planting & Harvesting
		bulshel -= toPlant;
		harvestPerAcre = generateRandomHarvest();
		bulshel += toPlant * harvestPerAcre;
		
		harvestPerAcreLastYear = harvestPerAcre;
		harvestedLastYear = toPlant;
		
		//Food Allocation
		
		bulshel -= foodAlloc;
		
		int foodShort = pop * 20 - foodAlloc;
		if(foodShort >= 20) {
			starvedLastYear =  foodShort/20;
			
			if(pop - starvedLastYear >= 0) {
				pop -= starvedLastYear; //Some died
			} else {
				pop = 0; //Everyone died!
			}
			
		} else if(foodShort<20 && foodShort>-20) {
			starvedLastYear = 0; //No one Died
		} else if(foodShort <= -20) {
			migLastYear = -(foodShort/20);
			pop += migLastYear; //Some migrated
		}

		
		//Buying/Selling
		bulshel -= priceOfLand * toBuy;
		area += toBuy;
		
		priceOfLand = generateRandomPrice();
		
		year++;
		
		checkGameState();
	}
	
	void checkGameState() {
		if(pop==0) {
			System.out.println("You killed everyone, idiot");
			over = true;
		} else if(bulshel < -(pop * 20)) {
			System.out.println("You ruined the kingdom, idiot");
			over = true;
		} else if(year == 10) {
			System.out.println("You did well");
			over = true;
		}
	}
}