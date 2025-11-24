package com.klu.designpatterns;

//Abstract Products
interface Button { void paint(); }
interface Checkbox { void paint(); }

//Concrete Products for Windows
class WinButton implements Button {
 public void paint() { System.out.println("Painting a Windows button"); }
}
class WinCheckbox implements Checkbox {
 public void paint() { System.out.println("Painting a Windows checkbox"); }
}

//Concrete Products for Mac
class MacButton implements Button {
 public void paint() { System.out.println("Painting a MacOS button"); }
}
class MacCheckbox implements Checkbox {
 public void paint() { System.out.println("Painting a MacOS checkbox"); }
}

//Abstract Factory
interface GUIFactory {
 Button createButton();
 Checkbox createCheckbox();
}

//Concrete Factories
class WinFactory implements GUIFactory {
 public Button createButton() { return new WinButton(); }
 public Checkbox createCheckbox() { return new WinCheckbox(); }
}
class MacFactory implements GUIFactory {
 public Button createButton() { return new MacButton(); }
 public Checkbox createCheckbox() { return new MacCheckbox(); }
}

//Client Code
public class AbstractFactory {
 private Button button;
 private Checkbox checkbox;

 public AbstractFactory(GUIFactory factory) {
     button = factory.createButton();
     checkbox = factory.createCheckbox();
 }

 public void paintUI() {
     button.paint();
     checkbox.paint();
 }

 public static void main(String[] args) {
     // Based on the OS, create the appropriate factory
     String osName = System.getProperty("os.name").toLowerCase();
     GUIFactory factory;
     if (osName.contains("mac")) {
         factory = new MacFactory();
     } else {
         factory = new WinFactory();
     }
     
     AbstractFactory app = new AbstractFactory(factory);
     app.paintUI();
 }
}