package camp.database;

import camp.model.Score;

import java.util.List;

public class ScoreStore {
    private static List<Score> scoreStore;

    public ScoreStore(List<Score> scoreStore) {
        this.scoreStore = scoreStore;
    }

    public List<Score> getScoreStore() {
        return this.scoreStore;
    }

    public void setScoreStore(Score scoreObject) {
        this.scoreStore.add(scoreObject);
    }
}
