import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {

        try {
            Socket socket = new Socket("localhost", 8000);

            DataInputStream in = new DataInputStream(socket.getInputStream());

            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            Scanner scanner = new Scanner(System.in);

            double weight = 0.0;
            double height = 0.0;

            while (true) {
                System.out.println("Weight in kilos: ");
                weight = scanner.nextDouble();
                out.writeDouble(weight);

                System.out.println("Height in meters: ");
                height = scanner.nextDouble();
                out.writeDouble(height);

                double bmi = in.readDouble();

                System.out.print("BMI is " + bmi + ".");
                if (bmi < 18.5)
                    System.out.println(" Underweight");
                else if (bmi < 25)
                    System.out.println(" Normal");
                else if (bmi < 30)
                    System.out.println(" Overweight");
                else
                    System.out.println(" Obese");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
