package src;

public class Programm {

    public static void main(String[] args) {
        WürfelModell wModell = new WürfelModell();
        WürfelView wView = new WürfelView(wModell);
        wView.setVisible(true);
    }

}
