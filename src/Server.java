import java.io.*;
import java.net.*;

public class Server {

    private static Socket socket;

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8999);
            System.out.println("Server Started and listening to the port 8999\n");
            while (true) {
                socket = serverSocket.accept();
                System.out.println("Client connected.");
                InputStream is = socket.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);
                String clientMassage = br.readLine();
                String receivedFromClient[]= clientMassage.split("/");
                System.out.println("First  Number receive from client is : " + receivedFromClient[2]);
                System.out.println("Second Number receive from client is : " + receivedFromClient[3]);
                System.out.println("Request operation from client is : " + receivedFromClient[1]);
                int operation = Integer.valueOf(receivedFromClient[1]);
                double firstOperand = Double.valueOf(receivedFromClient[2]);
                double secondOperand = Double.valueOf(receivedFromClient[3]);
                double answer = 0;
                if(operation == 1){
                    answer = firstOperand + secondOperand;
                }
                else if(operation == 2 ){
                    answer = firstOperand - secondOperand;
                }
                else if(operation == 3 ){
                    answer = firstOperand / secondOperand;
                }
                else if(operation == 4 ){
                    answer = firstOperand * secondOperand;
                }
                else if(operation == 5 ){
                    answer = Math.pow(firstOperand, secondOperand);
                }
                else if(operation == 6 ){
                    answer = Math.sqrt(firstOperand);
                }


                String returnMessage = Double.toString(answer);
                OutputStream os = socket.getOutputStream();
                OutputStreamWriter osw = new OutputStreamWriter(os);
                BufferedWriter bw = new BufferedWriter(osw);
                bw.write(returnMessage + "\n");
                System.out.println("Answer sent to the client is " + returnMessage);
                bw.flush();
                System.out.println("Calculated Success.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
                System.out.println("Client Disconnected");
            } catch (Exception e) {
                //System.out.println("hello");
            }
        }
    }
}