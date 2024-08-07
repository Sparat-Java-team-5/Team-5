package camp.database;

import camp.model.Score;

import java.util.List;

//점수를 저장하는 데이터저장소
public class ScoreStore {
    //점수를 저장하는 리스트 자료구조
    private static List<Score> scoreStore;

    //생성자
    public ScoreStore(List<Score> scoreStore) {
        this.scoreStore = scoreStore;
    }

    //getter
    public List<Score> getScoreStore() {
        return this.scoreStore;
    }

    //setter
    public void setScoreStore(Score scoreObject) {
        this.scoreStore.add(scoreObject);
    }
}
