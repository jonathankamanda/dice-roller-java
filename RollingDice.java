import java.util.Scanner;

public class RollingDice {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		boolean tryAgain = true;

		while (tryAgain) {

			int N = 0;
			int D = 0;
			int S = 0;
			boolean validTrials = false;
			boolean validDice = false;
			boolean validSides = false;

			while (!validTrials) {
				System.out.print("Enter the number of trials: ");

				if (scan.hasNextInt()) {
					N = scan.nextInt();

					if (N > 0) {
						validTrials = true;
					} else {
						System.out.println("Please enter a positive integer.");
					}

				} else {
					System.out.println("Invalid input. Please enter an integer.");
					scan.next();
				}
			}

				while (!validDice) {
			System.out.print("Enter the amount of dice to be rolled: ");

				if (scan.hasNextInt()) {
					D = scan.nextInt();

					if (D > 0) {
						validDice = true;
					} else {
						System.out.println("Please enter a positive integer.");
					}

				} else {
					System.out.println("Invalid input. Please enter an integer.");
					scan.next();
				}
				}

				while (!validSides) {
				System.out.print("Enter how many sides each dice has: ");

				if (scan.hasNextInt()) {
					 S = scan.nextInt();

					if (S > 0) {
						validSides = true;
					} else {
						System.out.println("Please enter a positive integer.");
					}

				} else {
					System.out.println("Invalid input. Please enter an integer.");
					scan.next();
				}
				}
			

			int minSum = D * 1;
			int maxSum = D * S;
			int size = maxSum - minSum + 1;

			int[] outcomes = new int[size];
			for (int i = 0; i < size; i++) {
				outcomes[i] = minSum + i;
			}

			int[] numOfOutcomes = new int[outcomes.length];

			for (int count = 0; count < N; count++) {

				int sum = 0;
				for (int d = 0; d < D; d++) {
					sum += (int) (Math.random() * S) + 1;
				}

				for (int j = 0; j < outcomes.length; j++) {

					if (sum == outcomes[j]) {
						numOfOutcomes[j] = numOfOutcomes[j] + 1;
					}
				}
			}

			double[] percentages = new double[outcomes.length];
			for (int i = 0; i < outcomes.length; i++) {
				percentages[i] = (numOfOutcomes[i] * 100) / N;

			}
			
			int maxCount = 0;
            for (int i = 0; i < numOfOutcomes.length; i++) {
                if (numOfOutcomes[i] > maxCount) {
                    maxCount = numOfOutcomes[i];
                }
            }

            int maxHistLength = 50;
            double scale = 0.0;

            if (maxCount > maxHistLength) {
                scale = (double) maxHistLength / maxCount;
            } else {
                scale = 1.0;
            }

            System.out.println("Format: \"Outcome: Number(Percentage)\"");
            
            for (int i = 0; i < outcomes.length; i++) {
                int histLength = (int)(numOfOutcomes[i] * scale);
                
                String hist = "";
                for (int s = 0; s < histLength; s++) {
                    hist += "*";
                }

                System.out.println(outcomes[i] + ": " + numOfOutcomes[i] + "(" + String.format("%.1f", percentages[i]) + "%) " + hist);
            }
            
			System.out.print("Would you like to run again? (y/n): ");
			String choice = scan.next().toLowerCase();

			if (!choice.equals("y")) {
				tryAgain = false;
				System.out.println("Goodbye");
			}

		}

	}


}

