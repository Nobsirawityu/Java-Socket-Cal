import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {

    private static Socket socket;

    public static void main(String args[]) {
        System.out.println("Please select the operand : \n" + "1-Add\n" +
                "2-Subtract\n" +
                "3-Divide\n" +
                "4-Multiply\n" +
                "5-Pow\n" +
                "6-Sqrt\n");
        Scanner scan = new Scanner(System.in);
        int operation = scan.nextInt();
        String massage = "";

        if(operation >=1 && operation <=4){
            System.out.println("Enter first number : ");
            double first = scan.nextDouble();
            System.out.println("Enter second number : ");
            double sec = scan.nextDouble();
            massage = "/"+Integer.toString(operation)+"/"+ Double.toString(first)+"/"+Double.toString(sec)+"/";
        }
        else if(operation == 5){
            System.out.println("Enter base   number : ");
            double first = scan.nextDouble();
            System.out.println("Enter degree number : ");
            double sec = scan.nextDouble();
            massage = "/"+Integer.toString(operation)+"/"+ Double.toString(first)+"/"+Double.toString(sec)+"/";
        }
        else if(operation == 6 ){
            System.out.println("Enter base number : ");
            double first = scan.nextDouble();
            double sec = 0;
            massage = "/"+Integer.toString(operation)+"/"+ Double.toString(first)+"/"+Double.toString(sec)+"/";
        }




        try {
            socket = new Socket("localhost", 8999);

            OutputStream os = socket.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(osw);



            String sendMessage = massage + "\n";
            bw.write(sendMessage);
            bw.flush();
            System.out.println("Message sent to the server : " + sendMessage);

            InputStream is = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String message = br.readLine();
            System.out.println("Answer from server : " + message);
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Wrong Input");
            }
        }
    }
}