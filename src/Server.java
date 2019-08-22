import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {

        try {
            ServerSocket server = new ServerSocket(8000);

            System.out.println("Serveren er oppe!");

            Socket socket = server.accept();

            System.out.println("Serveren har modtaget en forbindelse fra " + socket.getRemoteSocketAddress().toString());

            while (true) {

                DataInputStream in = new DataInputStream(socket.getInputStream());

                DataOutputStream out = new DataOutputStream(socket.getOutputStream());

                double weight = in.readDouble();
                double height = in.readDouble();
                System.out.println("height: " + height + " weight: " + weight);

                double bmi = weight / (height * height);

                out.writeDouble(bmi);

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
            System.out.println("Serveren oplever teknisk fejl");

            e.printStackTrace();
        }
    }
}
