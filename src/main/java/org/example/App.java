package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    private Scanner sc = new Scanner(System.in);
    private int lastId = 0;
    private List<WiseSaying> wiseSayings = new ArrayList<>();

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

            } else if (command.startsWith("수정")) {
                actionModify(command);

            } else if (command.equals("종료")) {
                break;
            }
        }
    }

    private void actionModify(String command) {

        String[] commandBits = command.split("=");

        if (commandBits.length < 2) {
            System.out.println("번호를 입력해주세요.");
            return;
        }

        String idStr = commandBits[1];
        int id = Integer.parseInt(idStr);

        int modifyTargetIndex = findIndexById(id);

        if (modifyTargetIndex == -1) {
            System.out.println("%d번 명령은 존재하지 않습니다.".formatted(id));
            return;
        }

        WiseSaying modifyTargetWiseSaying = wiseSayings.get(modifyTargetIndex);

        System.out.println("명언(기존) :) %s".formatted(modifyTargetWiseSaying.getSaying()));
        System.out.print("명언 : ");
        String newSaying = sc.nextLine();
        System.out.println("명언(기존) : %s".formatted(modifyTargetWiseSaying.getAuthor()));
        System.out.print("작가 : ");
        String newAuthor = sc.nextLine();

        modify(modifyTargetWiseSaying, newSaying, newAuthor);

    }

    private void modify(WiseSaying modifyTargetWiseSaying, String newSaying, String newAuthor) {
        modifyTargetWiseSaying.setSaying(newSaying);
        modifyTargetWiseSaying.setAuthor(newAuthor);

    }

    //명언 입력 시 저장된 명언의 인덱스 찾기
    private int findIndexById(int id) {

        //저장된 명령의 인덱스 찾으면 해당 인덱스 반환.
        for (int i = 0; i < wiseSayings.size(); i++) {
            if (wiseSayings.get(i).getId() == id) {
                return i;
            }
        }
        //인덱스 못 찾으면 false 반환
        return -1;

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

    private boolean delete(int id) {

        int deleteTargetIndex = findIndexById(id);

        if (deleteTargetIndex == -1) {
            return false;
        }
        wiseSayings.remove(deleteTargetIndex);

        return true;
    }


    private void actionList() {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");

        List<WiseSaying> wiseSayings = findListDesc();


        for (WiseSaying wiseSaying : wiseSayings) {
            System.out.println("%d / %s/ %s".formatted(wiseSaying.getId(), wiseSaying.getSaying(), wiseSaying.getAuthor()));
        }
    }

    private List<WiseSaying> findListDesc() {
        return wiseSayings.reversed();

    }

    private void actionWrite() {

        System.out.print("명언: ");
        String saying = sc.nextLine();
        System.out.print("작가: ");
        String author = sc.nextLine();


        WiseSaying wiseSaying = write(saying, author);

        System.out.println("%d번 명언이 등록되었습니다.".formatted(wiseSaying.getId()));
    }

    private WiseSaying write(String saying, String author) {
        lastId++;
        WiseSaying wiseSaying = new WiseSaying(lastId, saying, author);
        wiseSayings.add(wiseSaying);
        return wiseSaying;
    }

}
