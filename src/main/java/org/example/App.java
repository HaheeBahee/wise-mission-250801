package org.example;

import java.util.Scanner;

public class App {
    Scanner sc = new Scanner(System.in);
    int lastNo = 0;
    int lastIndex = 0; //가장 최근 배열의 위치
    WiseSaying[] wiseSayings = new WiseSaying[100];

    public void run() {

        System.out.println("== 명언 앱 ==");

        while (true) {
            System.out.print("명령) ");
            String command = sc.nextLine(); //입력값 받기

            if (command.equals("등록")) {

                actionWrite();

            } else if (command.equals("목록")) {

                actionList();


            } else if (command.equals("종료")) {
                break;
            }
        }
    }


    public void actionList() {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");
        for (int i = lastIndex - 1; i >= 0; i--) {
            WiseSaying target = wiseSayings[i];

            if (target == null) {
                break;
            }

            System.out.println("%d / %s/ %s".formatted(target.id, target.saying, target.author));
        }
    }

    public void actionWrite() {

        System.out.print("명언: ");
        String saying = sc.nextLine();
        System.out.print("작가: ");
        String author = sc.nextLine();


        write(saying, author);

        System.out.println("%d번 명언이 등록되었습니다.".formatted(lastNo));

    }

    public void write(String saying, String author) {

        lastNo++;
        WiseSaying wiseSaying = new WiseSaying(); //객체 생성
        wiseSaying.id = lastNo;
        wiseSaying.saying = saying;
        wiseSaying.author = author;

        wiseSayings[lastIndex++] = wiseSaying;
    }

}
