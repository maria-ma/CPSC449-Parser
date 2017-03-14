package methods;

class FatalErrors { 

	/**
	 * Prints a fatal error message indicating that the user's inputted 
	 * single qualifier is not recognized. Exits with the exit code -1.
	 * @param	letter		the unrecognized character in the inputted qualifier
	 * @param	qualifier	a user-inputted string qualifier from the command line
	 */
	static void unrecognizedQualifier(char letter, String qualifier) {
		System.err.println("Unrecognized qualifier '" + letter + "' in '" + qualifier + "'.");
		System.err.println(Methods.SYNOPSIS);
		System.exit(-1);
	}

	/**
	 * Prints a fatal error message if the inputted long qualifier is
	 * not recognized. Exits with the exit code -1.
	 * @param	qualifier	the user-inputted long qualifier
	 */
	static void unrecognizedQualifier(String qualifier) {
		System.err.println("Unrecognized qualifier: --" + qualifier + ".");
		System.err.println(Methods.SYNOPSIS);
		System.exit(-1);
	}

	/**
	 * Prints a fatal error message indicating that the user has inputted too many
	 * command line arguments (maximum is 2). Exits with exit code -2.
	 */
	static void invalidNumArgs() {
		System.err.println("This program takes at most two command line arguments.");
		System.err.println(Methods.SYNOPSIS);
		System.exit(-2);
	}

	/**
	 * Prints a fatal error message when the user did not input a jar file as the first command line arguments
	 * (After any qualifiers). Exits with exit code -3.
	 */
	static void invalidFirstArg() {
		System.err.println("This program requires a jar file as the first command line argument (after any qualifiers).");
		System.err.println(Methods.SYNOPSIS);
		System.exit(-3);
	}

	/**
	 * Prints a fatal error message when the user inputted a command-line argument with the help qualifier.
	 * Exits with exit code -4.
	 */
	static void invalidHelp() {
		System.err.println("Qualifier --help (-h, -?) should not appear with any command-line arguments.");
		System.err.println(Methods.SYNOPSIS);
		System.exit(-4);
	}

	// System.err.println("Could not load jar file: <fileName>")															system.exit(-5)		no print
	static void invalidFile(String fileName) {
		System.err.println("Could not load jar file: " + fileName);
		System.exit(-5);
	}

	// System.err.println("Could not find class: <className>")																system.exit(-6)		no print
	static void invalidClass(String className){
		System.err.println("Could not find class: " + className);
		System.exit(-6);
	}

}