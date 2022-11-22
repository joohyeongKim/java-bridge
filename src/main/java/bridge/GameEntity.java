package bridge;

import static bridge.BridgeStructure.FALSE;
import static bridge.BridgeStructure.FIRST_TRUE;
import static bridge.BridgeStructure.LAST_TRUE;
import static bridge.BridgeStructure.MIDDLE_TRUE;
import static bridge.BridgeStructure.UN_KNOWN;
import static bridge.Expression.DOWN;
import static bridge.Expression.UP;
import static bridge.GameStatus.QUIT;
import static bridge.GameStatus.SELECT_RE_TRY;
import static bridge.UserInterface.FAIL;
import static bridge.UserInterface.RESULT;
import static bridge.UserInterface.SELECT_ROW;
import static bridge.UserInterface.SUCCESS;
import static bridge.UserInterface.SUCCESSFUL;
import static bridge.UserInterface.TRY_COUNT;

import java.util.List;

public class GameEntity extends BridgeGame implements GameRepository {

    String readMoving;
    List<String> bridgeEntity;
    private StringBuilder upRow;
    private StringBuilder downRow;


    public GameEntity() {
        this.readMoving = new FrontController().readMoving();
        this.bridgeEntity = new BridgeEntity().manageBridgeStatus();
        this.upRow = new StringBuilder("[");
        this.downRow = new StringBuilder("[");
    }

    @Override
    public List<String> manageGameStatus() {
        String result = RESULT.interact();
        String table = move();
        String successful = SUCCESSFUL.interact() + predicateWin();
        String tryResult = TRY_COUNT.interact() + String.format(" %d", countTry);

        List<String> gameStatus = List.of(result, table, successful, tryResult);

        return gameStatus;
    }

    private String predicateWin() {
        String predicate = "";
        if (bridgeEntity.get(bridgeEntity.size()).equals(LAST_TRUE.buildStructure())) {
            predicate = SUCCESS.interact();
        }
        if (bridgeEntity.get(bridgeEntity.size()).equals(FALSE.buildStructure())) {
            predicate = FAIL.interact();
        }

        return predicate;
    }

    // 이동 기능
    // bridge.size() = 5+(n-1)*4, 5 9 13..
    @Override
    public String move() {
        if (readMoving.equals(UP.expressThat())) {
            upRow.replace(0, downRow.length(), FIRST_TRUE.buildStructure());
        }
        if (readMoving.equals(DOWN.expressThat())) {
            downRow.replace(0, downRow.length(), FIRST_TRUE.buildStructure());
        }
        return upRow + "\n" + downRow;
    }

    public String moveNTimes() {
        String upRowNTimes = "";
        String DownNTimes = "";
        for (String index : bridgeEntity) {

            upRowNTimes = finishUpRow(index);
            DownNTimes = finishDownRow(index);

        }

        return upRowNTimes + "\n" + DownNTimes;
    }

    private String finishDownRow(String index) {
        String DownNTimes = "";
        if (index.equals(DOWN.expressThat())) {
            StringBuilder NTimeUpColumn = upRow.append(UN_KNOWN);
            NTimeUpColumn.setCharAt(NTimeUpColumn.length() - 1, ']');
            StringBuilder NTimeDownColumn = downRow.append(MIDDLE_TRUE.buildStructure());
            NTimeDownColumn.setCharAt(NTimeDownColumn.length() - 1, ']');
            DownNTimes = NTimeDownColumn + "\n" + NTimeUpColumn;
        }
        return DownNTimes;
    }

    private String finishUpRow(String index) {
        String upRowNTimes = "";
        if (index.equals(UP.expressThat())) {
            StringBuilder NTimeUpColumn = upRow.append(MIDDLE_TRUE);
            NTimeUpColumn.setCharAt(NTimeUpColumn.length() - 1, ']');
            StringBuilder NTimeDownColumn = downRow.append(UN_KNOWN.buildStructure());
            NTimeDownColumn.setCharAt(NTimeDownColumn.length() - 1, ']');
            upRowNTimes = NTimeUpColumn + "\n" + NTimeDownColumn;
        }
        return upRowNTimes;
    }

    @Override
    public int retry(String gameCommand) {

        if (gameCommand.equals(QUIT.tellCommand())) {
            return countTry;
        }
        if (gameCommand.equals(SELECT_RE_TRY.tellCommand())) {
            System.out.println(SELECT_ROW.interact());
            countTry++;
            move();
        }

        return countTry;
    }


}
