package methods;
import java.util.*;
import java.lang.reflect.*;
import java.awt.*;
/**
 * The main method to execute the program
 * @author CPSC 449 Team 23
 */

public class Methods {

	static String jar_name = null;
	static String class_name = "Commands";
	static boolean verbose_mode = false;

	static final String SYNOPSIS = "Synopsis:\n"
		+ "  methods\n"
		+ "  methods { -h | -? | --help }+\n"
		+ "  methods {-v --verbose}* <jar-file> [<class-name>]\n"
		+ " Arguments:\n"
		+ "  <jar-file>:   The .jar file that contains the class to load (see next line).\n"
		+ "  <class-name>: The fully qualified class name containing public static command methods to call. [Default=\"Commands\"]\n"
		+ " Qualifiers:\n"
		+ "  -v --verbose: Print out detailed errors, warning, and tracking.\n"
		+ "  -h -? --help: Print out a detailed help message.\n"
		+ "Single-char qualifiers may be grouped; long qualifiers may be truncated to unique prefixes and are not case sensitive.";

	static final String SYNOPSIS_HELP = "\nThis program interprets commands of the format '(<method> {arg}*)' on the command line, finds corresponding\n"
		+ "methods in <class-name>, and executes them, printing the result to sysout.";

	static final String HELP = "q           : Quit the program.\n"
		+ "v           : Toggle verbose mode (stack traces).\n"
		+ "f           : List all known functions.\n"
		+ "?           : Print this helpful text.\n"
		+ "<expression>: Evaluate the expression.\n"
		+ "Expressions can be integers, floats, strings (surrounded in double quotes) or function\n"
		+ "calls of the form '(identifier {expression}*)'.";

	/**
	 * Prints out the synopsis message plus the additional help statement
	 */
	public static void printHelp(){
		System.out.println(SYNOPSIS);
		System.out.println(SYNOPSIS_HELP);
		System.exit(0);
	}

	public static void main(String[] args) {
		boolean help_qualifier = false;

		if (args.length == 0)
			System.out.println(SYNOPSIS);

		else {

			int arg_count = 0;

			for (int i = 0; i < args.length; i++) {
				String input = args[i];
				// checking validity of the inputted qualifiers
				if (input.startsWith("-")) {
					// checks if long qualifiers are valid
					if (input.startsWith("--")) {
						String longQualifier = input.substring(2);
						if (longQualifier.equalsIgnoreCase("help"))
							help_qualifier = true;
						else if (longQualifier.equalsIgnoreCase("verbose"))
							verbose_mode = true;
						else
							FatalErrors.unrecognizedQualifier(longQualifier);
					}
					// checks if single-letter qualifiers are valid
					else {
						for (char c : input.substring(1).toCharArray()) {
							if ((c == 'h') || (c == '?'))
								help_qualifier = true;
							else if (c == 'v')
								verbose_mode = true;
							else
								FatalErrors.unrecognizedQualifier(c, input);
						}
					}	
				}
				// checking validity of inputted command line arguments
				else {
					arg_count++;
					// checks if the first cmd line arg is a jar file, sets it as the jar file if true
					if (arg_count == 1) {
						if (input.contains(".jar"))
							jar_name = input;
						else
							FatalErrors.invalidFirstArg();
					}
					// sets the class name from the second cmd line arg
					if (arg_count == 2) 
						class_name = input;
					// checks if the correct number of args is inputted
					if (arg_count > 2)
						FatalErrors.invalidNumArgs();
				}				
			}
		}

		if (help_qualifier == true) {
			if (jar_name != null)
				FatalErrors.invalidHelp();
			else
				printHelp();
		}

		try {
			JarExtractor.jarExtractor(jar_name, class_name);	
		}
		catch (Exception e) {

		} 
		System.out.println(HELP);
		Scanner sc = new Scanner(System.in);
		String input;

//		start infinite loop & wait for program interaction
		while (true) {
			System.out.print("> ");
			if (sc.hasNextLine())
				input = sc.nextLine();
			else 
				break;
			input = input.trim();
			switch(input) {
				case "?":
					System.out.println(HELP);
					break;
				case "f":
					System.out.println("print function list here");
					break;
				case "v":
					if (verbose_mode == false) {
						verbose_mode = true;
						System.out.println("Verbose on.");
					}
					else {
						verbose_mode = false;
						System.out.println("Verbose off.");
					}
					break;
				case "q":
					System.out.println("bye.");
					System.exit(0);
				default: 
					System.out.println("evaluate expression function here");
			}
		}
	}
}