package org.example.domain.wiseSaying.repository;

import org.example.domain.wiseSaying.WiseSaying;

import java.util.ArrayList;
import java.util.List;

public class WiseSayingRepository {
    private List<WiseSaying> wiseSayings = new ArrayList<>();


    public WiseSaying findByIdOrNull(int id) {
        return wiseSayings.stream()
                .filter(w -> w.getId() == id)
                .findFirst()
                .orElse(null);
    }public boolean delete(int id) {
        return wiseSayings.removeIf(w -> w.getId() == id);
    }

    public void save(WiseSaying wiseSaying){
        wiseSayings.add(wiseSaying);
    }
    public List<WiseSaying> findListDesc() {
        return wiseSayings.reversed();
    }

}
