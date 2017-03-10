package methods;

class FatalErrors { 

	// fatal error checking

	// System.err.println("Unrecognized qualifier '<letter>' in '-<allLettersInTheQualPhraseOnTheComandLine>'.")			system.exit(-1)		print Methods.SYNOPSIS
	static void unrecognizedQualifier(char letter, String qualifier) {
		System.err.println("Unrecognized qualifier '" + letter + "' in '" + qualifier + "'.");
		System.err.println(Methods.SYNOPSIS);
		System.exit(-1);
	}

	// System.err.println("Unrecognized qualifier: --<longQualifier>.")														system.exit(-1)		print Methods.SYNOPSIS
	static void unrecognizedQualifier(String qualifier) {
		System.err.println("Unrecognized qualifier: --" + qualifier + ".");
		System.err.println(Methods.SYNOPSIS);
		System.exit(-1);
	}

	// System.err.println("This program takes at most two command line arguments.")											system.exit(-2)		print Methods.SYNOPSIS
	static void invalidNumArgs() {
		System.err.println("This program takes at most two command line arguments.");
		System.err.println(Methods.SYNOPSIS);
		System.exit(-2);
	}

	// System.err.println("This program requires a jar file as the first command line argument (after any qualifiers).")	system.exit(-3)		print Methods.SYNOPSIS
	static void invalidFirstArg() {
		System.err.println("This program requires a jar file as the first command line argument (after any qualifiers).");
		System.err.println(Methods.SYNOPSIS);
		System.exit(-3);
	}

	// System.err.println("Qualifier --help (-h, -?) should not appear with any command-line arguments.")					system.exit(-4)		print Methods.SYNOPSIS
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