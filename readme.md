# Warung Animation (Java)

Quick steps to run this program.

## Prerequisites
- JDK 11+ installed and on your PATH, or a newer JDK.
- Visual Studio Code with Java support (e.g. "Extension Pack for Java" or "Language Support for Java(TM) by Red Hat" and "Debugger for Java").

## Steps
1. Download the repository ZIP from the GitHub link and extract it into a folder.
2. Open that folder in VS Code (`File > Open Folder...`).
3. The program entry point is the `main` method in [`WarungAnimation.main`](WarungAnimation.java). You can run it in two ways:

### Run in VS Code
- Open `WarungAnimation.java`.
- Use the "Run" code lens above `main`, or right-click the file and choose "Run Java", or use the Debug pane to start.

### Run from the command line
- Open a terminal in the folder containing the `.java` files.
- Compile:
```sh
javac *.java
```
- Run:
```sh
java WarungAnimation
```

## Files
- [WarungAnimation.java](WarungAnimation.java) — contains the `main` method.
- [Cloud.java](Cloud.java)
- [CustomerObject.java](CustomerObject.java)

## Notes
- If the files declare a package, run/compile from the project root and use the fully qualified class name when running `java`.
- If you see compilation errors, ensure all three `.java` files are in the same folder and you used the correct JDK.