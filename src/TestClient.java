import java.io.*;
import java.net.*;

public class TestClient {

    public static void main(String[] args) throws Exception {
        // 클라이언트에서 서버로 보낼 메시지를 저장할 변수
        String sentence;
        // 서버로부터 받은 메시지를 저장할 변수
        String fromServer;

        // 사용자로부터 입력을 받기 위한 BufferedReader 생성
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

        // localhost의 6789 포트에 연결할 클라이언트 소켓 생성
        Socket clientSocket = new Socket("localhost", 6789);

        // 서버로 데이터를 보낼 출력 스트림 생성
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());

        // 서버로부터 데이터를 받을 입력 스트림 생성
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        while (true) {
            // 사용자에게 메시지 입력 요청
            System.out.print("보내기 > ");
            // 사용자가 입력한 메시지를 읽기
            sentence = inFromUser.readLine();
            // 서버로 메시지를 보냄
            outToServer.writeBytes(sentence + "\n");

            // 사용자가 입력한 메시지가 "bye"일 경우 연결 종료
            if (sentence.equals("bye")) {
                System.out.println("연결이 종료되었습니다.");
                // 클라이언트 소켓을 닫고 루프를 종료함
                clientSocket.close();
                break;
            }

            // 서버에서 받은 메시지를 읽음
            fromServer = inFromServer.readLine();
            // 서버에서 받은 메시지를 출력함
            System.out.println("서버 > " + fromServer);
        }
    }
}
