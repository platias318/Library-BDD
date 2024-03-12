# ~ Maven project template with Cucumber and Selenium ~

A Maven project template for Java containing **Cucumber**, **Gherkin**, **Junit** and **Selenium**, created in Eclipse IDE having the following setup.

![Cucumber in action!](https://raw.githubusercontent.com/savbran/cucumber_project_template/master/eclipse_cucumber.png)

### Setup procedure

- **Software installation**

  - From the operating system, install the **[Eclipse for java](https://www.eclipse.org/downloads/packages/release/2018-09/r/eclipse-ide-java-developers "https://www.eclipse.org/downloads/packages/release/2018-09/r/eclipse-ide-java-developers")** IDE

  - From Eclipse IDE, install [M2Eclipse plugin](https://www.eclipse.org/m2e/ "www.eclipse.org/m2e"), it provides integration for Apache Maven into the Eclipse IDE. For the installation don't use Eclipse Marketplace but install it as a new software (Eclipse menu: Help> Install New Software...) adding the official site: http://download.eclipse.org/technology/m2e/releases/.

  - From Eclipse IDE, install [Cucumber Plugin](https://cucumber.github.io/cucumber-eclipse/ "https://cucumber.github.io/cucumber-eclipse/"). Cucumber is a tool that supports Behaviour-Driven Development (BDD) and this plugin enables its use in Eclipse. For the installation don't use Eclipse Marketplace but install it as a new software (Eclipse menu: Help> Install New Software...) adding the official site: http://cucumber.github.io/cucumber-eclipse/update-site/.

  - From Eclipse IDE, install ["ANSI Escape in Console" plugin](https://marketplace.eclipse.org/content/ansi-escape-console "https://marketplace.eclipse.org/content/ansi-escape-console"), used to interpret the ANSI escape sequences to correctly render and color the Eclipse Console output. With this plugin activated, the Cucumber test results written in the Eclipse Console are very clear and structured. For the installation use the Eclipse Marketplace (Eclipse menu: Help> Eclipse Marketplace... and search for "ANSI Escape in Console", then install it).

- **Project creation**

  - From the official cucumber tutorial, get the [maven command to create an empty cucumber project](https://docs.cucumber.io/guides/10-minute-tutorial/#create-an-empty-cucumber-project "https://docs.cucumber.io/guides/10-minute-tutorial/#create-an-empty-cucumber-project") and copy/paste it in your shell, having the prompt set on your new project directory.

  - Update Maven dependencies contained into the generated pom.xml, finding the latest releases on [maven repository](https://mvnrepository.com "https://mvnrepository.com").

  - In the pom.xml there are other few dependencies: webdrivermanager, selenium-java and slf4j-simple. They are used to correctly setup the TestCode in the project.
