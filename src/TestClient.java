import java.io.*;
import java.net.*;

class TestClient {
    public static void main(String[] args) throws Exception {
        String sentence;
        String fromServer;

        // 사용자로부터 입력 받을 BufferedReader 생성
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

        // 서버에 연결할 소켓 생성
        Socket clientSocket = new Socket("localhost", 6789);

        // 서버에 데이터를 보낼 출력 스트림 생성
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());

        // 서버로부터 데이터를 받을 입력 스트림 생성
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        // 사용자로부터 메시지 입력을 받고 서버로 전송
        System.out.println("서버에 보낼 메시지를 입력하세요: ");
        sentence = inFromUser.readLine();
        outToServer.writeBytes(sentence + "\n");

        // 서버로부터 받은 응답을 출력
        fromServer = inFromServer.readLine();
        System.out.println("서버로부터 받은 메시지: " + fromServer);

        // 연결 종료
        clientSocket.close();
    }
}
