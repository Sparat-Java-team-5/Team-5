package camp.database;

import camp.model.Score;

import java.util.List;

public class ScoreStore {
    //점수 데이터를 저장하는 리스트
    private static List<Score> scoreStore;

    //생성자 : 점수 저장소를 초기화
    public ScoreStore(List<Score> scoreStore) {
        //초기화된 점수 리슽츠를 클래스에 할당
        this.scoreStore = scoreStore;
    }
    // 점수 저장소 리스트를 반환하는 getter 메서드
    public List<Score> getScoreStore() {
        return this.scoreStore;
    }
    //새로운 점수를 점수 저장소에 추가하는 메서드
    public void setScoreStore(Score scoreObject) {
        //전달받은 점수 객체를 리스트에 추가
        this.scoreStore.add(scoreObject);
    }
}
