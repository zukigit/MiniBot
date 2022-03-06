import java.util.*;

class MiniBot {

	public static void main(String[] args) {
		var robotController = new RobotController();
		robotController.start();
	}
}

class RobotController {

	private Robot robot;
	private Scanner scanner;

	RobotController() {
		robot = new Robot();
		scanner = new Scanner(System.in);
	}

	public void start() {

		System.out.println("=====================");
		System.out.println("=======WELCOME=======");
		System.out.println("=====================");
		System.out.println();
		while(true) {
			String message = "";
			System.out.print("User -> ");
			message = scanner.nextLine();

			if("exit".equalsIgnoreCase(message)){
				break;
			}

			System.out.print("Robot -> ");
			System.out.print("..  ");
			System.out.print(robot.talk(message));
			System.out.println("  ..");
		}
		System.out.println();
		System.out.println("=====================");
		System.out.println("======Thank You======");
		System.out.println("=====================");
	}
}

class Robot {
	private Map<String, String> dictionary;
	private boolean hasQuestion = false;
	private String question = "";
	private boolean yesUser = false;
	private boolean askname = false;

	Robot() {
		dictionary = new HashMap<>(Map.of(
			"name", "my name is aki",
			"username", "",
			"askuser", ", what do you want to know?",
			"askusername", ", what is your name?",
			"ame", "it means rain in japanese",
			"thanks", "You are welcomed!",
			"thank", "You are welcomed!",
			"bye", "see ya!",
			"your name", "my name is aki",
			"hi", "hello"

		));
	}

	public String talk(String message) {

		if (hasQuestion && "y".equalsIgnoreCase(message)) {
			yesUser = true;
			return "Thank you, what is the meaning of " + question + " ?";
		}
		if(hasQuestion && yesUser) {
			dictionary.put(question, message);
			question = "";
			hasQuestion = false;
			yesUser = false;
			return "Learned!";
		}
		if(hasQuestion && !yesUser) {
			hasQuestion = false;
			return "never mind! Go on.";
		}
		if(dictionary.containsKey(message.toLowerCase())){
			if(message.equals("name") || message.equals("your name")) {
				askname = true;
				return dictionary.get(message.toLowerCase()) + dictionary.get("askusername");
			}
			return dictionary.get(message.toLowerCase());
		}
		if(askname) {
			dictionary.put("username", message);
			askname = false;
			return dictionary.get("hi") + " " + dictionary.get("username") + dictionary.get("askuser");
		}
		question = message;
		hasQuestion = true;
		return "no idea, Do you want to teach me?(y/ n)";
	}
}