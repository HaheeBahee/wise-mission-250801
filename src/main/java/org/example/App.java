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

            } else if (command.startsWith("삭제")) {
                actionDelete(command);

            } else if (command.equals("종료")) {
                break;
            }
        }
    }

    private void actionDelete(String command) {

        String[] commandBits = command.split("=");

        if (commandBits.length < 2) {
            System.out.println("번호를 입력해주세요.");
            return;
        }

        String idStr = commandBits[1];
        int id = Integer.parseInt(idStr);
        boolean result = delete(id);
        if (result) {
            System.out.println("%d번 명언이 삭제되었습니다".formatted(id));
        } else {
            System.out.println("%d번 명령은 존재하지 않습니다.".formatted(id));
        }


    }

    public boolean delete(int id) {

        int deleteTargetIndex = -1;

        for (int i = 0; i < lastIndex; i++) {
            if (wiseSayings[i].id == id) {
                deleteTargetIndex = i;

            }
        }

        if (deleteTargetIndex == -1) {
            return false;
        }

        for (int i = deleteTargetIndex; i < lastIndex; i++) {
            wiseSayings[i] = wiseSayings[i + 1];
        }
        lastIndex--;

        return true;
    }


    public void actionList() {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");

        //처리된 데이터 받기
        WiseSaying[] wiseSayings = findListDesc();

        for (WiseSaying wiseSaying : wiseSayings) {
            System.out.println("%d / %s/ %s".formatted(wiseSaying.id, wiseSaying.saying, wiseSaying.author));
        }
    }

    public WiseSaying[] findListDesc() {

        //내림차순으로 반환해주기
        WiseSaying[] resultList = new WiseSaying[lastIndex];
        int resultListIndex = 0;

        for (int i = lastIndex - 1; i >= 0; i--) {
            resultList[resultListIndex] = wiseSayings[i];
            resultListIndex++;
        }

        return resultList;
    }

    public void actionWrite() {

        System.out.print("명언: ");
        String saying = sc.nextLine();
        System.out.print("작가: ");
        String author = sc.nextLine();


        WiseSaying wiseSaying = write(saying, author);

        System.out.println("%d번 명언이 등록되었습니다.".formatted(wiseSaying.id));
    }

    public WiseSaying write(String saying, String author) {

        lastNo++;
        WiseSaying wiseSaying = new WiseSaying(); //객체 생성
        wiseSaying.id = lastNo;
        wiseSaying.saying = saying;
        wiseSaying.author = author;

        wiseSayings[lastIndex++] = wiseSaying;

        return wiseSaying;
    }

}
