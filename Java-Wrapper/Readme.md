# CLI and Gradle demo

## Purpose

A small demo project to show the usage of different Gradle tasks as well as 
the Apache CLI library for parsing command line arguments.

## Do it yourself

To get going with a Gradle project of your own, follow these simple steps  
(This assumes you have Gradle installed)

1. Create a folder, e.g. `mkdir CLIdemo`
1. Enter it `cd CLIdemo`
1. Create a project structure with `gradle init --type java-library` for a Java lib
or `gradle init --type java-application` for a Java application
1. Start IntelliJ and Open the project (double-click the folder)
1. This will start the Import Project from Gradle dialog. Select 'use auto-import'
1. Click OK and have a look at the created structure. 
1. It contains Java source and test folders (under `src`), a settings.gradle and a build.gradle. 
Open the last in the editor and edit it to have the contents as in this project. 
**Make sure you adjust `group` and `mainClassName` according to your project!**

## Gradle tasks

The Gradle Tasks/build section will show three tasks of immediate interest (besides many other useful ones):  

1. Task **jar** will create `CLIdemo-Jar-1.1-SNAPSHOT.jar` under `/build/libs` that will have a 
main manifest attribute, but no corresponding `/lib` folder for dependencies. 
This will suffice if your project has no dependencies. If there are any (like Apache CLI!), you will
get errors like  

```
Exception in thread "main" java.lang.NoClassDefFoundError: org/apache/commons/cli/ParseException
at nl.bioinf.arguments_provider_demo.CommandLineArgsRunner.main(CommandLineArgsRunner.java:29)
Caused by: java.lang.ClassNotFoundException: org.apache.commons.cli.ParseException
at java.net.URLClassLoader.findClass(URLClassLoader.java:381)
at java.lang.ClassLoader.loadClass(ClassLoader.java:424)
at sun.misc.Launcher$AppClassLoader.loadClass(Launcher.java:331)
at java.lang.ClassLoader.loadClass(ClassLoader.java:357)
... 1 more
```  

1. Task **fatJar** will create `CLIdemo-Fatjar-1.1-SNAPSHOT.jar` under `build/libs` that will have a 
main manifest attribute, and dependencies packaged within the jar.

1. Task **install** will create `CLIdemo-Jar-1.1-SNAPSHOT.jar` under `/build/libs` that will have a 
main manifest attribute, and all dependencies will be copied to the `/build/libs/lib` folder
 
1. Task **build** will create `CLIdemo-1.1-SNAPSHOT.zip` under `build/distributions` that has this structure:  

```
CLIdemo-Jar-1.1-SNAPSHOT.zip  
    /bin  
        /CLIdemo
        /CLIdemo.bat
    /lib
        /CLIdemo-Jar-1.1-SNAPSHOT.jar
        /commons-cli-1.4.jar
        
```

So , an executable is created for both Linux and Windows, with packaged dependencies under lib.

## Versioning your project

When using git versioning, you should create a `.gitignore` file with something like below in it!
Adjust this to your needs, of course.


```
.gradle
/build/
/.idea
/gradle
gradlew
gradlew.bat
CLIdemo.iml

*.class
*.log
*.jar
```
