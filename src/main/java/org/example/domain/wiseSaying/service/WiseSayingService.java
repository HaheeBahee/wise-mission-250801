package org.example.domain.wiseSaying.service;

import org.example.domain.wiseSaying.WiseSaying;
import org.example.domain.wiseSaying.repository.WiseSayingRepository;

import java.util.List;

public class WiseSayingService {

    private int lastId = 0;
    private WiseSayingRepository wiseSayingRepository = new WiseSayingRepository();

    //재료 가공 - 요리 - 서비스
    public void modify(WiseSaying wiseSaying, String newSaying, String newAuthor) {
        wiseSaying.setSaying(newSaying);
        wiseSaying.setAuthor(newAuthor);
    }

    public WiseSaying write(String saying, String author) {
        lastId++;
        WiseSaying wiseSaying = new WiseSaying(lastId, saying, author);
        wiseSayingRepository.save(wiseSaying);
        return wiseSaying;
    }

    public List<WiseSaying> findListDesc() {
        return wiseSayingRepository.findListDesc();
    }
    public  WiseSaying getByIdOrNull(int id){
        return wiseSayingRepository.findByIdOrNull(id);
    }
    public boolean delete(int id) {
        return wiseSayingRepository.delete(id);
    }

}
