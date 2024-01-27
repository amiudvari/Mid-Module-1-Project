import java.util.*;

public class Main {
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\033[1;31m";
    public static final String BLUE = "\033[1;34m";
    public static final String CYAN = "\033[1;36m";
    public static final String PURPLE = "\033[1;35m";
    public static final String YELLOW = "\033[1;33m";
    public static void main(String[] args) {

        String[] baristaNames = {"John", "Jane", "Tom", "Alice"};
        double subtotal = 0.0;
        Scanner input = new Scanner(System.in);
        List<String> listedOrder = new ArrayList<>();
        Map<String, Double> foodMenu = new HashMap<>();
        List<String> drinkMenu = new ArrayList<>();
        final double OH_STATE_TAX = 0.07;

        System.out.println(BLUE + "Welcome! Please enter the name for your order:" + RESET);
        String userInputName = input.nextLine();
        List<String> drinkMenuList = new ArrayList<>();
        drinkMenu.add("Americano");
        drinkMenu.add("Cappuccino");
        drinkMenu.add("Macchiato");
        drinkMenu.add("Chai Latte");
        drinkMenu.add("Hot Chocolate");

        Map<String, Double> foodMenuList = new HashMap<>();
        foodMenuList.put("Blueberry Muffin", 1.50);
        foodMenuList.put("Chocolate Chip Muffin", 1.50);
        foodMenuList.put("Egg Bites", 2.25);
        foodMenuList.put("Blueberry Scone", 4.50);
        foodMenuList.put("Apple Cinnamon Scone", 4.30);

        Map<String, Double> drinkPrices = new HashMap<>();
        drinkPrices.put("Large", 5.50);
        drinkPrices.put("Medium", 5.20);
        drinkPrices.put("Small", 4.90);


        while (true) {
            System.out.println( YELLOW + "Thank you, " + userInputName + "." + " Please enter the number that corresponds with what you would like to do:" + RESET);
            System.out.println(CYAN + "1 - Order Food" + RESET);
            System.out.println(CYAN + "2 - Order Drinks" + RESET);
            System.out.println(CYAN + "3 - View My Order" + RESET);
            System.out.println(CYAN + "4 - Check-Out" + RESET);
            String userInputMenuSelection = input.nextLine();
            if (userInputMenuSelection.contains("1")) {
                subtotal = foodMenuLoop(foodMenuList, listedOrder, subtotal);
            } else if (userInputMenuSelection.equals("2")) {
                subtotal = drinkMenuLoop(drinkMenu, drinkPrices, listedOrder, subtotal, input, userInputName);
            } else if (userInputMenuSelection.contains("3")) {
                viewOrder(listedOrder, subtotal);
            } else if (userInputMenuSelection.contains("4")) {
                checkoutResult(subtotal, baristaNames, OH_STATE_TAX);
                break;
            } else {
                System.out.println(RED + "Opps, you gave an invalid response. Please enter a number that corresponds with what you would like to to! " + RESET);
            }
        }

    }

    public static double foodMenuLoop(
            Map<String, Double> foodMenuList,
            List<String> listedOrder,
            double subtotal
    ) {
        Scanner input = new Scanner(System.in);
        boolean continueOrdering = true;
        while (continueOrdering) {
            System.out.println(CYAN + "Food Menu: " + RESET);
            for (String item : foodMenuList.keySet()) {
                System.out.println(CYAN + item + RESET);
            }
            System.out.println(BLUE + "Enter the name of the item you would like, or enter 'done' to return to the main menu" + RESET);

            String userInputItemChoiceFood = input.nextLine();
            if (userInputItemChoiceFood.equals("done")) {
                break;
            }
            if (foodMenuList.containsKey(userInputItemChoiceFood)) {
                subtotal += foodMenuList.get(userInputItemChoiceFood);
                listedOrder.add(userInputItemChoiceFood);
                String reformattedSubtotal = String.format("%.2f", subtotal);
                System.out.println(YELLOW + "Your order of a(n) " + userInputItemChoiceFood + " has been placed. Your subtotal is now: $" + reformattedSubtotal + RESET);

                while (true) {
                    System.out.println(BLUE + "Would you like to order more food? (Y/N)" + RESET);
                    String userInputOrderMoreFood = input.nextLine();
                    if (!userInputOrderMoreFood.equals("Y") && !userInputOrderMoreFood.equals("N")) {
                        System.out.println(RED + "Please enter a valid response (Y/N)" + RESET);
                    } else if (userInputOrderMoreFood.equals("N")) {
                        continueOrdering = false;
                        break;
                    } else {
                        break;
                    }
                }
            } else {
                System.out.println(RED + "That item is not on our menu. Please enter a valid item, or 'done' to return." + RESET);
            }
        }
        return subtotal;
    }

    public static double drinkMenuLoop(
            List<String> drinkMenu,
            Map<String, Double> drinkPrices,
            List<String> listedOrder,
            double subtotal,
            Scanner input,
            String userInputName
    ) {
        boolean continueOrderingDrinks = true;

        while (continueOrderingDrinks) {
            System.out.println(CYAN + "Drink Menu:" + RESET);
            for (String currentDrink : drinkMenu) {
                System.out.println(CYAN + currentDrink + RESET);
            }
            System.out.println(BLUE + "Enter the name of the item you would like or 'done' to return:" + RESET);
            String userInputItemChoice = input.nextLine();
            listedOrder.add(userInputItemChoice);

            if (userInputItemChoice.equals("done")) {
                break;
            }
            if (drinkMenu.contains(userInputItemChoice)) {

                String userInputSizeChoice;
                while (true) {
                    System.out.println(BLUE + "Choose a size: Small, Medium, or Large" + RESET);
                    userInputSizeChoice = input.nextLine();

                    if (drinkPrices.containsKey(userInputSizeChoice)) {
                        break;
                    } else {
                        System.out.println(RED + "Please enter a valid size: Small, Medium, or Large" + RESET);
                    }
                }
                subtotal += drinkPrices.get(userInputSizeChoice);
                String reformattedSubtotal = String.format("%.2f", subtotal);
                System.out.println(YELLOW + "Thank you, " + userInputName + ". Your order of a " + userInputSizeChoice + " " + userInputItemChoice + " has been placed. Your subtotal is now: $" + reformattedSubtotal + RESET);
                while (true) {
                    System.out.println(BLUE + "Would you like to order more items? (Y/N)" + RESET);
                    String userInputOrderMoreFood = input.nextLine();
                    if (!userInputOrderMoreFood.equals("Y") && !userInputOrderMoreFood.equals("N")) {
                        System.out.println( RED + "Please enter a valid response (Y/N)" + RESET);

                    } else if (userInputOrderMoreFood.equals("N")) {
                        continueOrderingDrinks = false;
                        break;
                    } else {
                        break;
                    }
                }
            } else {
                System.out.println(RED + "Unfortunately that item is not on our menu. Please enter a valid item or 'done' to return." + RESET);
            }
        }
        return subtotal;
    }

    public static void viewOrder(
            List<String> listedOrder,
            double subtotal
    ) {
        System.out.println(PURPLE + "Your current order contains: " + RESET);

        for (String itemInOrder : listedOrder) {
            System.out.println(PURPLE + itemInOrder + RESET);
        }

        String reformattedSubtotal = String.format("%.2f", subtotal);
        System.out.println(PURPLE + "The current subtotal of your order is: $" + reformattedSubtotal + RESET);
    }

    public static void checkoutResult(
            double subtotal,
            String[] baristaNames,
            double OH_STATE_TAX
    ) {
        double tax = subtotal * OH_STATE_TAX;
        double totalWithTax = subtotal + tax;
        Random name = new Random();
        int randomIndex = name.nextInt(baristaNames.length);
        String randomBarista = baristaNames[randomIndex];

        Random orderId = new Random();
        int newOrderId = orderId.nextInt(100);
        String reformattedTotal = String.format("%.2f", totalWithTax);
        System.out.println(YELLOW + "Great, your total with tax is " + "$" + reformattedTotal + ". Your order ID is " + newOrderId + ". " + randomBarista + " will take your payment and have your order ready for you when you arrive. Thank you for your business!" + RESET);

    }
}
